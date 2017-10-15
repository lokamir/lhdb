package org.tbs.dao.projs.undwrt;

/**
 * 4.3.2 综合管理部用印
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
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsAdminsign;
import org.tbs.entity.TbsProj;
import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.client.service.TaskClient;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskOpinion;

@Component
public class TbsAdminsignDao extends HibernateDao {
	
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
	public void loadAll(Page<TbsAdminsign> page,Map<String, Object> params) throws Exception {
		if (null != params) {
			String whereCase="";
			String valid=(String)params.get("valid");
			String projSn = (String)params.get("projSn");	
			Integer tbsBasBank = (Integer)params.get("tbsBasBank");	
			Integer keyinId = (Integer)params.get("keyinId");	
			if (StringHelper.isNotEmpty(projSn)) {
				whereCase += " AND projSn like '%" + projSn + "%' ";
			}
			if (null!=tbsBasBank) {
				whereCase += " AND aflsb_Id =" + tbsBasBank ;
			}
			if (null!=keyinId) {
				whereCase += " AND keyin_Id =" + keyinId ;
			}
			if (StringHelper.isNotEmpty(valid)) {
				if(valid.equals("1")||valid.equals("0")){
					whereCase += " AND valid ="+valid;
				}
			}
			if (params.get("tbsProj") instanceof TbsProj) {
				TbsProj tbsProjvalue = (TbsProj) params.get("tbsProj");
				if (tbsProjvalue != null && tbsProjvalue.getId() != 0) {
					whereCase += " AND tbsProj = " + tbsProjvalue.getId();
				}
			}	
			String hql="from "+TbsAdminsign.class.getName() +" where Del=0 "+ whereCase +" order by id desc";
			this.pagingQuery(page, hql, "select count(*) "+hql);
		}
		else{
			String hql="from "+TbsAdminsign.class.getName()+" where Del=0"+" order by id desc";
			this.pagingQuery(page, hql, "select count(*) "+hql);
		}
	}
	
	@DataResolver
	public void save(Collection<TbsAdminsign> tbsAdsminsigns) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsAdminsign tbsAdsminsign : tbsAdsminsigns){
				EntityState state = EntityUtils.getState(tbsAdsminsign);
				if(state.equals(EntityState.NEW)){
					session.save(tbsAdsminsign);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsAdsminsign);
				}
				if(state.equals(EntityState.DELETED)){
					tbsAdsminsign.setDel(true);
					session.update(tbsAdsminsign);
				}
			}
		}
			finally{
				session.flush();
				session.close();
			}
		}
	
	//=======================================WorkFlow=====================================//
	@Expose
	public String StartWF(Map<String, Object> params) throws Exception {
		String result = "";
		if (null != params) {
			Session session = this.getSessionFactory().openSession();
			String docid = Integer.toString((int) params.get("docid"));
			TbsAdminsign adminsign = (TbsAdminsign) session.get(TbsAdminsign.class, Integer.valueOf(docid));
			StartProcessInfo info = new StartProcessInfo();
			info.setBusinessId(docid); // 单据id
			info.setPromoter(ContextHolder.getLoginUser().getUsername()); // 发起人
			info.setCompleteStartTask(true); // 在开始节点，流程就直接往下走，而不是停留在开始节点等待发起人完成开始节点，必须这样做
			info.setTag("adminsign"); // 对流程实例打个标记，以备后续使用
			Map<String,Object> variables = new HashMap<String,Object>();
			variables.put("reject",0);
			variables.put("projSn", adminsign.getProjSn());
			variables.put("adsign", adminsign.getBdf2User().getUsername());
			variables.put("projName", adminsign.getTbsProj().getTbsCustomer().getName());
			info.setVariables(variables);
			ProcessInstance pi = processClient.startProcessByName("adminsign", info);
			Long processInstanceId = pi.getId();			
			result = "提交审批成功！";
			try {
				int projid = adminsign.getTbsProj().getId();
				int psid = adminsign.getTbsProj().getTbsBasPs().getId();
				if (psid == 28) { // 原始状态发起审批
					String sql = "call p_hisstatus(" + projid + ",34,28)";
					SQLQuery sqlquery = session.createSQLQuery(sql);
					sqlquery.executeUpdate();
					adminsign.setBy3(processInstanceId.toString());  //把processInstanceId放入单据的BY3内
					session.update(adminsign);
				}
			} finally {
				session.flush();
				session.close();
			}
		} else {
			result = "此单据已发起审批，请另外选择！";
		}
		return result;
	}

	@DataProvider   // 给审批界面显示单条数据
	public Collection<TbsAdminsign> loadsingle(String bussinessId) throws Exception {
		return this.query("from " + TbsAdminsign.class.getName() + " where id="
				+ bussinessId);
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
		String opinions = (String) outcome + "-- 审批意见："+  opinion;
		TaskOpinion taskOpinion = new TaskOpinion(opinions); //获取结束
		taskClient.start(Long.valueOf(taskid));  //开始审批，审批时一定要先开始任务，然后是下一步完成任务 //taskClient.saveTaskAppointor(arg0, arg1, arg2)  //指定下节点任务人 10 class13min
		Map<String,Object> variables = new HashMap<String,Object>();
		Session session = this.getSessionFactory().openSession();
		try {
			// ===获取流程内的一些值===
			TbsAdminsign adminsign = (TbsAdminsign) session.get(TbsAdminsign.class, Integer.valueOf(docid));
			String projname = adminsign.getTbsProj().getProjName();
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
				Msgpkt.setTitle("【用印申请】审批已通过！");
				Msgpkt.setContent("您发送的【用印申请】\n【项目名称："+projname+"】\n已经通过【"+nodename+"】审批！\n"+today);
				SendMsg.send(Msgpkt);
			}else if (outcome.equals("驳回")) { 
				String sql="call p_hisstatus("+projid+",32,34)";
				SQLQuery sqlquery=session.createSQLQuery(sql);
				sqlquery.executeUpdate();
				Integer i = Integer.parseInt(processClient.getProcessVariable("reject", pi).toString());
				i=1;
				variables.put("reject", i);				
				Msgpkt.setTitle("【用印申请】审批已驳回！");
				Msgpkt.setContent("您发送的【用印申请】\n【项目名称："+projname+"】\n已经被驳回,驳回节点为【"+nodename+"】\n"+today);
				SendMsg.send(Msgpkt);				
			}else if(outcome.equals("修改确认")){
				String sql="call p_hisstatus("+projid+",34,32)";
				SQLQuery sqlquery=session.createSQLQuery(sql);
				sqlquery.executeUpdate();
				Integer i = Integer.parseInt(processClient.getProcessVariable("reject", pi).toString());
				i=0;
				variables.put("reject", i);		
				Msgpkt.setTitle("【用印申请】单据已完成修改！");
				Msgpkt.setContent("您发送的【用印申请】\n【项目名称："+projname+"】\n已经被【"+nodename+"】\n"+today);
				SendMsg.send(Msgpkt);
			}
		} finally {
			session.flush();
			session.close();
			//taskClient.complete(Long.valueOf(taskid),outcome,taskOpinion); // 在中间可以加入路径名称“通过”等字样，流程图里面的路径需要写明。complete只能放在这里才可以确保后续的路程运行顺利
			taskClient.complete(Long.valueOf(taskid), outcome, variables, taskOpinion);
		}

	}
}
