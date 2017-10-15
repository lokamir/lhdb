package org.tbs.uflo.dao.undwrt;

/**
 * 4.2.2 反担保关联 审批流
 * 
 * */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProj;
import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.Expose;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.client.service.TaskClient;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskOpinion;

@Component
public class WfCggLinkProj extends HibernateDao {
	
	@Autowired
	@Qualifier(ProcessClient.BEAN_ID)
	private ProcessClient processClient;

	@Autowired
	@Qualifier(TaskClient.BEAN_ID)
	private TaskClient taskClient;

	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
		
	
	@DataProvider
	public Collection<TbsProj> loadsingle(String bussinessId) throws Exception {
		return this.query("from " + TbsProj.class.getName() + " where id="
				+ bussinessId);
	}
		
	@Expose
	public String StartWF(Map<String, Object> params) throws Exception {
		String result = "";
		if (null != params) {
			Session session = this.getSessionFactory().openSession();
			String docid = Integer.toString((int) params.get("docid"));
			TbsProj cgg = (TbsProj) session.get(TbsProj.class, Integer.valueOf(docid));
			String value = Integer.toString((int) params.get("bzjzy"));
			StartProcessInfo info = new StartProcessInfo();
			info.setBusinessId(docid); // 单据id
			info.setPromoter(ContextHolder.getLoginUser().getUsername()); // 发起人
			info.setCompleteStartTask(true); // 在开始节点，流程就直接往下走，而不是停留在开始节点等待发起人完成开始节点，必须这样做
			info.setTag("projcgg"); // 对流程实例打个标记，以备后续使用
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("bzjzy", value); 
			variables.put("projSn", cgg.getSn());
			variables.put("projName", cgg.getTbsCustomer().getName());
			info.setVariables(variables); 
			ProcessInstance pi = processClient.startProcessByName("projcgg", info);
			Long processInstanceId = pi.getId();
			result = "提交审批成功！";
			try {
				cgg.setBy4(processInstanceId.toString());  //把processInstanceId放入单据的by4内
				session.update(cgg);				
			} finally {
				session.flush();
				session.close();
			}
			session = this.getSessionFactory().openSession();
			try {
				int projid = cgg.getId();
				int psid = cgg.getTbsBasPs().getId();
				if (psid == 13) { // 原始状态发起审批
					String sql = "call p_hisstatus(" + projid + ",14,13)";
					SQLQuery sqlquery = session.createSQLQuery(sql);
					sqlquery.executeUpdate();
				}
			}finally {
				session.flush();
				session.close();
			}
		} else {
			result = "此单据已发起审批，请另外选择！";
		}
		return result;
	}

	@Expose
	public void ApprWF(Map<String, Object> param) throws Exception {
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = dateFormat.format(now);
		String docid = (String) param.get("docid");  //获取信息
		String projid =  (String) param.get("projid"); 
		String taskid = (String) param.get("taskid");
		String outcome = (String) param.get("outcome");
		String opinion = (String) param.get("opinion");
		TaskOpinion taskOpinion = new TaskOpinion(opinion); //获取结束
		taskClient.start(Long.valueOf(taskid));  //开始审批，审批时一定要先开始任务，然后是下一步完成任务 //taskClient.saveTaskAppointor(arg0, arg1, arg2)  //指定下节点任务人 10 class13min
		Session session = this.getSessionFactory().openSession();
		try {
			// ===获取流程内的一些值===
			TbsProj projcgg = (TbsProj) session.get(TbsProj.class,Integer.valueOf(docid));
			String projname = projcgg.getProjName();
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
				Msgpkt.setTitle("【项目与反担保关联】审批已通过！");
				Msgpkt.setContent("您发送的【项目与反担保关联】\n【项目名称："+projname+"】\n已经通过【"+nodename+"】审批！\n日期："+today);
				SendMsg.send(Msgpkt);
			}else if (outcome.equals("驳回")) { 
				String sql="call p_hisstatus("+projid+",24,14)";
				SQLQuery sqlquery=session.createSQLQuery(sql);
				sqlquery.executeUpdate();
				Msgpkt.setTitle("【项目与反担保关联】审批已驳回！");
				Msgpkt.setContent("您发送的【项目与反担保关联】\n【项目名称："+projname+"】\n已经被驳回,驳回节点为【"+nodename+"】\n日期："+today);
				SendMsg.send(Msgpkt);				
			}else if(outcome.equals("修改确认")){
				String sql="call p_hisstatus("+projid+",14,24)";
				SQLQuery sqlquery=session.createSQLQuery(sql);
				sqlquery.executeUpdate();
				Msgpkt.setTitle("【项目与反担保关联】单据已完成修改！");
				Msgpkt.setContent("您发送的【项目与反担保关联】\n【项目名称："+projname+"】\n已经被【"+nodename+"】\n日期："+today);
				SendMsg.send(Msgpkt);
			}
		} finally {
			session.flush();
			session.close();
			taskClient.complete(Long.valueOf(taskid), outcome, taskOpinion); // 在中间可以加入路径名称“通过”等字样，流程图里面的路径需要写明。complete只能放在这里才可以确保后续的路程运行顺利
		}
	}

}
