package org.tbs.uflo.dao.compsry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProjrol;
import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.Expose;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.client.service.TaskClient;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskOpinion;

@Component
public class WfProjrol extends HibernateDao {
	
	@Autowired
	@Qualifier(ProcessClient.BEAN_ID)
	private ProcessClient processClient;

	@Autowired
	@Qualifier(TaskClient.BEAN_ID)
	private TaskClient taskClient;

	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
		
	
	@Expose
	public String StartWF(Map<String, Object> params) throws Exception {
		String result = "";
		if (null != params) {
			Session session = this.getSessionFactory().openSession();
			String projid = Integer.toString((int) params.get("projid"));
			String docid = Integer.toString((int) params.get("projrolid")); 
			TbsProjrol tprls = (TbsProjrol) session.get(TbsProjrol.class, Integer.valueOf(docid));
			StartProcessInfo info = new StartProcessInfo();
			info.setBusinessId(docid); // 单据id
			info.setPromoter(ContextHolder.getLoginUser().getUsername()); // 发起人
			info.setCompleteStartTask(true); // 在开始节点，流程就直接往下走，而不是停留在开始节点等待发起人完成开始节点，必须这样做
			info.setTag("projrol"); // 对流程实例打个标记，以备后续使用
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("reject",0);
			variables.put("projSn", tprls.getTbsProj().getSn());
			variables.put("projName", tprls.getTbsProj().getProjName());
			variables.put("projId", projid);
			info.setVariables(variables); 
			ProcessInstance pi = processClient.startProcessByName("projrol", info);
			Long processInstanceId = pi.getId();
			result = "提交审批成功！";
			try {
				tprls.setBy1(processInstanceId.toString());  //把processInstanceId放入单据的by1内
				tprls.setValid(2); //单据状态改为审批中 
				session.update(tprls);				
			} finally {
				session.flush();
				session.close();
			}
		} else {
			result = "此单据已发起审批，请另外选择！";
		}
		return result;
	}

	@Expose
	public void ApprWF(Map<String, Object> params) throws Exception {
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = dateFormat.format(now);
		String taskid = (String) params.get("taskid"); //获取前台传入值
		String docid = (String) params.get("docid");  
		String outcome = (String) params.get("outcome");
		String comment = (String) params.get("comment");
		String opinion = outcome + ": " + comment;
		TaskOpinion taskOpinion = new TaskOpinion(opinion); //获取结束
		taskClient.start(Long.valueOf(taskid));  //开始审批，审批时一定要先开始任务，然后是下一步完成任务 //taskClient.saveTaskAppointor(arg0, arg1, arg2)  //指定下节点任务人 10 class13min
		Map<String,Object> variables = new HashMap<String,Object>();
		Session session = this.getSessionFactory().openSession();
		try {
			//===获取流程内的一些值===
			TbsProjrol tprls = (TbsProjrol) session.get(TbsProjrol.class, Integer.valueOf(docid));
			String projname = tprls.getTbsProj().getProjName();
			Task uflotask = (Task) session.get(Task.class, Long.valueOf(taskid));
			String nodename = uflotask.getNodeName();
			Long piid = uflotask.getProcessInstanceId(); // 获取processinstanceId
			ProcessInstance pi = (ProcessInstance) session.get(ProcessInstance.class, piid);
			String promoter = pi.getPromoter(); // 获取promoter

			//===消息处理===
			DefaultUser receiver = (DefaultUser)session.get(DefaultUser.class,promoter );  //获得消息的接收人
			DefaultUser sender = (DefaultUser)session.get(DefaultUser.class,"SystemSender" );  //获得消息的发送人，默认为“系统发信人”
			Collection<IUser> receivers = new ArrayList<IUser>();
			receivers.add(receiver);
			MessagePacket Msgpkt= new MessagePacket();
			Msgpkt.setTo(receivers);	
			Msgpkt.setSender(sender);	
		
			//===审批处理===
			if (outcome.equals("通过")){			
				Msgpkt.setTitle("【风险解除（追偿）】审批已通过！");
				Msgpkt.setContent("您发送的【风险解除（追偿）】\n【项目名称："+projname+"】\n已经通过【"+nodename+"】审批！\n审批意见: " + comment+"\n日期："+today);
				SendMsg.send(Msgpkt);
			}else if (outcome.equals("驳回")) { 
				tprls.setValid(3);
				session.update(tprls);
				Integer i = Integer.parseInt(processClient.getProcessVariable("reject", pi).toString());
				i=1;
				variables.put("reject", i);	
				Msgpkt.setTitle("【风险解除（追偿）】审批已驳回！");
				Msgpkt.setContent("您发送的【风险解除（追偿）】\n【项目名称："+projname+"】\n已经被驳回,驳回节点为【"+nodename+"】\n审批意见: " + comment+"\n日期："+today);
				SendMsg.send(Msgpkt);				
			}else if(outcome.equals("修改确认")){
				tprls.setValid(2);
				session.update(tprls);
				Integer i = Integer.parseInt(processClient.getProcessVariable("reject", pi).toString());
				i=0;
				variables.put("reject", i);	
				Msgpkt.setTitle("【风险解除（追偿）】已完成修改！");
				Msgpkt.setContent("您发送的【风险解除（追偿）】\n【项目名称："+projname+"】\n已经在【"+nodename+"】完成修改\n审批意见: " + comment+"\n日期："+today);
				SendMsg.send(Msgpkt);
			}
		} finally {
			session.flush();
			session.close();
			taskClient.complete(Long.valueOf(taskid), outcome, variables,taskOpinion); // 在中间可以加入路径名称“通过”等字样，流程图里面的路径需要写明。complete只能放在这里才可以确保后续的路程运行顺利
		}
	}

}
