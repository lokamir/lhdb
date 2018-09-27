package org.tbs.uflo.dao.change;

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
import org.tbs.entity.TbsProjcfm0;
import org.tbs.entity.TbsProjchangeMajcont;

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
import com.bstek.uflo.model.task.TaskState;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskOpinion;

@Component
public class WfChangeMajcont extends HibernateDao {
	
	@Autowired
	@Qualifier(ProcessClient.BEAN_ID)
	private ProcessClient processClient;

	@Autowired
	@Qualifier(TaskClient.BEAN_ID)
	private TaskClient taskClient;
	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
	
    private int pass = 0;
    private int confirm = 0;//三位领导必须签批（0没有完成，1完成签批）
    private int cmpt = 0; // 表示通过	
	
	@Expose
	public String StartWF(Map<String, Object> params) throws Exception {
		String result = "";
		if (null != params) {
			Session session = this.getSessionFactory().openSession();
			Integer projid = Integer.valueOf((int) params.get("projid"));
			String docid = Integer.toString((int) params.get("projchangeid")); 
			TbsProjchangeMajcont tpcm = (TbsProjchangeMajcont) session.get(TbsProjchangeMajcont.class, Integer.valueOf(docid));
			StartProcessInfo info = new StartProcessInfo();
			info.setBusinessId(docid); // 单据id
			info.setPromoter(ContextHolder.getLoginUser().getUsername()); // 发起人
			info.setCompleteStartTask(true); // 在开始节点，流程就直接往下走，而不是停留在开始节点等待发起人完成开始节点，必须这样做
			info.setTag("changemajcont"); // 对流程实例打个标记，以备后续使用
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("projSn", tpcm.getProjSn());
			variables.put("projName", tpcm.getTbsProj().getProjName());
			variables.put("projId", projid);
			variables.put("cmpt", false);
		    variables.put("passCount", 0);
		    variables.put("approverCount", 0);
		    variables.put("cfm0Id", 0);
		    variables.put("cfmFlag", 0);
		    variables.put("cfm1r2Id", 0);
			info.setVariables(variables); 
			//ProcessInstance pi = processClient.startProcessByName("changemajcont", info);
			ProcessInstance pi = processClient.startProcessByName("changemajcont", info);
			Long processInstanceId = pi.getId();
			result = "提交审批成功！";
			try {
				tpcm.setBy1(processInstanceId.toString());  //把processInstanceId放入单据的by1内
				if (!pi.getCurrentNode().equals("结束")) {  // 开始流程后直接执行action并结束时需要做这个判断
					tpcm.setValid(2);    //单据状态改为审批中 
				} else {
					tpcm.setValid(1); 
				}
				session.update(tpcm);				
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
		String uid = (String) params.get("uid");
		String opinion = outcome + ": " + comment;
		
		String nodename;
		Long processInstanceId;
		ProcessInstance pi;
		TaskOpinion taskOpinion = new TaskOpinion(opinion); //获取结束
		TaskState state = taskClient.getTask(Long.valueOf(taskid)).getState();
		if(state==state.Created){
			taskClient.start(Long.valueOf(taskid)); 
		}
		Session session = this.getSessionFactory().openSession();
		try {
			TbsProjchangeMajcont tpcm = (TbsProjchangeMajcont) session.get(TbsProjchangeMajcont.class, Integer.valueOf(docid));
			Task uflotask = (Task) session.get(Task.class, Long.valueOf(taskid));
			nodename = uflotask.getNodeName();
			processInstanceId = uflotask.getProcessInstanceId(); // 获取processinstanceId
			pi = (ProcessInstance) session.get(ProcessInstance.class, processInstanceId);
			String projName = (String)processClient.getProcessVariable("projName", processInstanceId);
			String cfm0Id = processClient.getProcessVariable("cfm0Id", processInstanceId).toString();
			Integer projId = Integer.parseInt(processClient.getProcessVariable("projId",processInstanceId).toString());	
		if(nodename.equals("评审会秘书录入会议信息")){
				// ===获取流程内的一些值===
			String cfmType = new String();
			if(outcome.equals("会议")){
				processClient.saveProcessVariable(processInstanceId,"cfmFlag", 1);
				cfmType = "1";
			}else if(outcome.equals("签批")) {
				processClient.saveProcessVariable(processInstanceId,"cfmFlag", 2);
				cfmType = "2";
				// reset counter sign flags
				this.cmpt = 0;
				processClient.saveProcessVariable(processInstanceId,"cmpt", 0);
				}
			TbsProj tbsproj = (TbsProj) session.get(TbsProj.class, Integer.valueOf(projId));
			TbsProjcfm0 projCfm0 = (TbsProjcfm0) session.get(TbsProjcfm0.class, Integer.parseInt(cfm0Id));
			//Bdf2User user = (Bdf2User) session.get(Bdf2User.class, Integer.valueOf(uid));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sqlInsert = "insert into tbs_proj_opinion(PROJ_ID,CFM0_ID,CFMTYPE,TIMESTAMP_INPUT,UID,TITLE)"
					+ " select " + projId + ", " + cfm0Id + "," + cfmType + ", '" + sdf.format(now) + "',"
					+ "cfm0.UID, cfm0.TITLE from (select UID, TITLE from tbs_proj_opinion "
					+ "where PROJ_ID = " + projId + " and CFM0_ID = "
					+ cfm0Id + " and CFMTYPE = 0 and del = 0) cfm0";
				SQLQuery queryInsert = session.createSQLQuery(sqlInsert);
				queryInsert.executeUpdate();  
			
		}		
		if(nodename.equals("评委审批")){	  
			// total approver count except those ones who choose "avoid"
			String sqlTotalTobe = "select count(*) from tbs_proj_opinion where CFM0_ID = "+ cfm0Id
					+ " and CFMTYPE = 2 and del = 0 and (outcome <> '回避' ) and (outcome <> '缺席' )";
			SQLQuery queryTotalTobe = session.createSQLQuery(sqlTotalTobe);
			String totalTobe = queryTotalTobe.uniqueResult().toString();
			float ttTobe = Float.valueOf(totalTobe);

			// total approver count who choose "agree"
			String sqlAgrees = "select count(*) from tbs_proj_opinion where CFM0_ID = "+ cfm0Id
					+ " and CFMTYPE = 2 and del = 0 and outcome = '同意'";
			SQLQuery querysqlAgrees = session.createSQLQuery(sqlAgrees);
			String agrees = querysqlAgrees.uniqueResult().toString();
			float ag = Float.valueOf(agrees);
			// total approver count who choose "agree"
				
				
			String sqlDisagrees = "select count(*) from tbs_proj_opinion where CFM0_ID = "+ cfm0Id
					+ " and CFMTYPE = 2 and del = 0 and outcome = '反对'";
			SQLQuery queryDisagrees = session.createSQLQuery(sqlDisagrees);
			String disagrees = queryDisagrees.uniqueResult().toString();
			
			// total approver count who choose "Waivers"
			String sqlWaivers = "select count(*) from tbs_proj_opinion where CFM0_ID = "
					+ cfm0Id
					+ " and CFMTYPE = 2 and del = 0 and outcome = '弃权'";
			SQLQuery querysqlWaivers = session.createSQLQuery(sqlWaivers);
			String Waivers = querysqlWaivers.uniqueResult().toString();
			float wa = Float.valueOf(Waivers);
			
			// total approver count who choose "Waivers"
			String sqlDebarbs = "select count(*) from tbs_proj_opinion where CFM0_ID = "
					+ cfm0Id
					+ " and CFMTYPE = 2 and del = 0 and outcome = '回避'";
			SQLQuery querysqlDebarbs = session.createSQLQuery(sqlDebarbs);
			String Debarbs = querysqlDebarbs.uniqueResult().toString();
			float db = Float.valueOf(Debarbs);
				
			/*String sqlconfirmcount = "select count(*) from tbs_proj_opinion where CFM0_ID = "+ cfm0Id
					+ " and CFMTYPE = 2 and del = 0 and title in ('总经理','分管风管总经理','风管经理')";
			SQLQuery queryconfirmcount = session.createSQLQuery(sqlconfirmcount);
			String confirmcount = queryconfirmcount.uniqueResult().toString();
			String sqlconfirm = "select count(*) from tbs_proj_opinion where CFM0_ID = "+ cfm0Id
					+ " and outcome <> '未知' and CFMTYPE = 2 and del = 0 and title in ('总经理','分管风管总经理','风管经理')";
			SQLQuery queryconfirm = session.createSQLQuery(sqlconfirm);
			String confirmresult = queryconfirm.uniqueResult().toString();
			Integer cfmre = Integer.valueOf(confirmresult);
			Integer cfmct = Integer.valueOf(confirmcount);
			if(cfmre==cfmct){
				confirm=1;
			}else{
				confirm=0;
			}*/
			
				
			// count how many approvers left
			String sqlTaskAssignees = "select count(*) from uflo_task where PROCESS_INSTANCE_ID_ = "+ processInstanceId;
			SQLQuery queryTaskAssignees = session.createSQLQuery(sqlTaskAssignees);
			String taskAssignees = queryTaskAssignees.uniqueResult().toString();

			int countTA = Integer.valueOf(taskAssignees);
			float da = Float.valueOf(disagrees);
			double twoThird = 0.59; // 2/3审批通过
			double oneThird = 0.29; // 1/3驳回通过

			if (outcome.equals("同意")) {
				if (ag / ttTobe >= twoThird&& countTA == 1 ) {
					this.pass = 1;
					this.cmpt = 1;
					processClient.saveProcessVariable(Long.valueOf(processInstanceId), "cmpt", 1);
				}else if (da / ttTobe >= oneThird&& countTA == 1) {
					this.pass = 0;
					this.cmpt = 1;
					processClient.saveProcessVariable(Long.valueOf(processInstanceId), "cmpt", 1);
				}
			} else if (outcome.equals("反对") ) {
				if (da / ttTobe >= oneThird&& countTA == 1) {
					this.pass = 0;
					this.cmpt = 1;
					processClient.saveProcessVariable(Long.valueOf(processInstanceId), "cmpt", 1);
				}else if (ag / ttTobe >= twoThird&& countTA == 1 ) {
					this.pass = 1;
					this.cmpt = 1;
					processClient.saveProcessVariable(Long.valueOf(processInstanceId), "cmpt", 1);
				}
			} else {
				if (da / ttTobe >= oneThird && countTA == 1) {
					this.pass = 0;
					this.cmpt = 1;
					processClient.saveProcessVariable(Long.valueOf(processInstanceId), "cmpt", 1);
				} else if (ag / ttTobe >= twoThird && countTA == 1) {
					this.pass = 1;
					this.cmpt = 1;
					processClient.saveProcessVariable(Long.valueOf(processInstanceId), "cmpt", 1);
				} else if (countTA == 1) {
					this.pass = 0;
					this.cmpt = 1;
					processClient.saveProcessVariable(Long.valueOf(processInstanceId), "cmpt", 1);
				}
			}
	    }
		if(nodename.equals("决策人审批")){	 
			String sql="update tbs_projchange_majcont set valid=1 where ID = "+docid;
		    SQLQuery sqlquery=session.createSQLQuery(sql);
		    sqlquery.executeUpdate();
		}
		if(cmpt==1&&pass==1){
			outcome="通过";
			cmpt=2;
			pass=2;
		}else if(cmpt==1&&pass==0){
			outcome="驳回";
			cmpt=0;
		}
		taskClient.complete(Long.valueOf(taskid), outcome, taskOpinion); // 在中间可以加入路径名称“通过”等字样，流程图里面的路径需要写明。complete只能放在这里才可以确保后续的路程运行顺利
		String promoter = pi.getPromoter(); // 获取promoter
		DefaultUser receiver = (DefaultUser)session.get(DefaultUser.class,promoter );  //获得消息的接收人
		DefaultUser sender = (DefaultUser)session.get(DefaultUser.class,"SystemSender" );  //获得消息的发送人，默认为“系统发信人”
		Collection<IUser> receivers = new ArrayList<IUser>();
		receivers.add(receiver);
		MessagePacket Msgpkt= new MessagePacket();
		Msgpkt.setTo(receivers);	
		Msgpkt.setSender(sender);	
		
		//===审批处理===
		if (outcome.equals("通过")){			
			Msgpkt.setTitle("【项目三要素变更】审批已通过！");
			Msgpkt.setContent("您发送的【项目三要素变更】\n【项目名称："+projName+"】\n已经通过【"+nodename+"】审批！\n审批意见: " + comment+"\n日期："+today);
			SendMsg.send(Msgpkt);
		}else if (outcome.equals("驳回")) { 
			tpcm.setValid(3);
			session.update(tpcm);
			Msgpkt.setTitle("【项目三要素变更】审批已驳回！");
			Msgpkt.setContent("您发送的【项目三要素变更】\n【项目名称："+projName+"】\n已经被驳回,驳回节点为【"+nodename+"】\n审批意见: " + comment+"\n日期："+today);
			SendMsg.send(Msgpkt);				
		}else if(outcome.equals("确认修改")){
			tpcm.setValid(2);
			session.update(tpcm);
			Msgpkt.setTitle("【项目三要素变更】单据已完成修改！");
			Msgpkt.setContent("您发送的【项目三要素变更】\n【项目名称："+projName+"】\n已经被【"+nodename+"】\n审批意见: " + comment+"\n日期："+today);
			SendMsg.send(Msgpkt);
		}
		} finally {
			session.flush();
			session.close();
		}
	}
}
