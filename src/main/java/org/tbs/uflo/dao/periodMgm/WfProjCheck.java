package org.tbs.uflo.dao.periodMgm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjCheck;

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
public class WfProjCheck extends HibernateDao {
	
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
	public String startPeriodMgmInstance(Map<String, Object> params)
		throws Exception {
	String result = "";
	if (params != null) {
		int id = (int) params.get("projcheckid");//检查记录单据id-businessid
		String sn = (String) params.get("projchecksn");//检查记录单据编号
		String projid = (String) params.get("projid");//项目名称
		String projName = (String) params.get("projName");//项目名称
		String menu = (String) params.get("menu");//检查记录名称
		String keyinid = (String) params.get("keyinid");//检查单据发起人
		StartProcessInfo info = new StartProcessInfo();
		// 单据id
		info.setBusinessId(Integer.toString(id));
		// 发起人
		info.setPromoter(ContextHolder.getLoginUser().getUsername());
		// 开始流程就直接往下走，而不是停留在开始节点等待发起人完成开始节点
		info.setCompleteStartTask(true);
		// 对流程实例打个标记，以备后续使用
		info.setTag(menu);
		// store variables into process
		Map<String, Object> variables = new HashMap<String, Object>();
		// variables for parallel approve
		variables.put("sn", sn);
		variables.put("projid", projid);
		variables.put("projName", projName);
		variables.put("menu", menu);
		variables.put("keyinid", keyinid);
		variables.put("aprv", "approve");// 方便前台取用，最好用String类型
		info.setVariables(variables);
		// 根据menu开始对应流程
		ProcessInstance pi = processClient.startProcessByName(
				menu.toLowerCase(), info);
		Long processInstanceId = pi.getId();
		result = "提交审批成功！";
		Session session = this.getSessionFactory().openSession();
		try {
			String sql = "update tbs.tbs_proj_check set valid = 2,by1= "
					+ processInstanceId.toString()
					+ " where del = 0 and id=" + id + " ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			sqlquery.executeUpdate();
		} finally {
			session.flush();
			session.close();
		}
	}
		return result;
	}

	// 处理提交的process请求
	@Expose
	@Transactional
	public String apprSubmit(Map<String, Object> param) throws Exception {
		// prepare properties
		String taskId = (String) param.get("taskId");
		String outcome = (String) param.get("outcome");
		String comment = (String) param.get("comment");
		String opinion = outcome + ": " + comment;
		// 获取审批意见
		TaskOpinion taskOpinion = new TaskOpinion(opinion);
		// 开始审批，审批时一定要先开始任务，然后是下一步完成任务
		// //taskClient.saveTaskAppointor(arg0,arg1,arg2)
		// 指定下节点任务人

		/** send msg to Promoter part ------------------------------- start */
		// get receivers
		Session session = this.getSessionFactory().openSession();
		Task uflotask = (Task) session.get(Task.class, Long.valueOf(taskId));
		Long processInstanceId = uflotask.getProcessInstanceId();
		ProcessInstance pi = (ProcessInstance) session.get(
				ProcessInstance.class, processInstanceId);
		String businessId = pi.getBusinessId();
		String menu = (String) processClient.getProcessVariable("menu",
				processInstanceId);
		String projid = (String) processClient.getProcessVariable("projid",
			processInstanceId);
		TbsProjCheck tbsProjCheck = (TbsProjCheck) session.get(TbsProjCheck.class,
				Integer.valueOf(businessId));
		TbsProj tbsProj = tbsProjCheck.getTbsProj();
	//	String keyinid = (String) processClient.getProcessVariable("keyinid",processInstanceId);
		if (outcome.equals("正常")||outcome.equals("确认")) {
			String sql = "update tbs.tbs_proj_check set valid = 1"
					+ " where del = 0 and id=" + businessId + " ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			sqlquery.executeUpdate();
			String sqlrisk = "UPDATE `tbs`.`tbs_proj` SET `risk` = '0' WHERE `ID`="
					+ tbsProj.getId() + "";
			SQLQuery sqlquery1 = session.createSQLQuery(sqlrisk);
			sqlquery1.executeUpdate();
		}
		if (outcome.equals("是")) {
			// ====设置by2===		
			String sqlProcedure = "update `tbs_proj_check` SET `BY2`='1' WHERE `ID`="
					+ businessId;
			SQLQuery sqlquery1 = session.createSQLQuery(sqlProcedure);
			sqlquery1.executeUpdate();
			// 生成上会通知单
			String sqlCallStoreProcedure = "call p_riskproj(1,"+projid+","+0+")";
			SQLQuery sqlquery2 = session.createSQLQuery(sqlCallStoreProcedure);
			sqlquery2.executeUpdate();				
		}
		if (outcome.equals("否")) {
			// 不上会的话打个标记
			processClient.saveProcessVariable(Long.valueOf(processInstanceId),
					"aprv", "approve");
		}
		try {
			Collection<IUser> receivers = getReceivers(session,
					Long.valueOf(taskId));
			// get sender
			DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,
					"SystemSender");
			// get menutext
			String menutext = menutotext(menu).get(menu);
			// get message title
			String msgTitle = getMsgTitle(outcome, uflotask.getNodeName(),
					menutext);
			// get message contents
			String msgContent = getMsgContent(outcome, comment,
					uflotask.getNodeName(),
					processClient.getProcessVariable("projName", pi).toString(),
					processClient.getProcessVariable("sn", pi).toString(),
					menutext);
			// send messages to participants
			sendMsg(receivers, sender, msgTitle, msgContent);
		} finally {
			session.flush();
			session.close();
		}
		taskClient.start(Long.valueOf(taskId));
		// 在中间可以加入路径名称“通过”等字样，流程图里面的路径需要写明。complete只能放在这里才可以确保后续的路程运行顺利
		if (outcome.isEmpty()) {
			taskClient.complete(Long.valueOf(taskId), taskOpinion);
		} else {
			taskClient.complete(Long.valueOf(taskId), outcome, taskOpinion);
		}
		return "提交成功！";
	}

	protected Collection<IUser> getReceivers(Session session, Long taskId) {
		Collection<IUser> receivers = new ArrayList<IUser>();
		// get prepared for send messages
		Task uflotask = (Task) session.get(Task.class, Long.valueOf(taskId));
		ProcessInstance processInstance = (ProcessInstance) session.get(
				ProcessInstance.class, uflotask.getProcessInstanceId());
		DefaultUser receiver = (DefaultUser) session.get(DefaultUser.class,
				processInstance.getPromoter());
		receivers.add(receiver);
		return receivers;
	}

	protected String getMsgTitle(String outcome, String nodeName,
			String menutext) {
		String msgTitle = new String();
		if (outcome.equals("不正常")) {
			msgTitle = "【" + menutext + "】" + "检查结果为‘不正常’！";
		} else if (outcome.equals("是")) {
			msgTitle = "【" + menutext + "】" + "标记为风险，需要上会";
		} else if (outcome.equals("否")) {
			msgTitle = "【" + menutext + "】" + "标记为风险，但无需上会";
		} else if (outcome.equals("正常")) {
			msgTitle = "【" + menutext + "】" + "检查结果为‘正常’！";
		} else if (outcome.equals("确认")) {
			msgTitle = "【" + menutext + "】" + "会议决议已录入";
		}
		return msgTitle;
	}

	protected String getMsgContent(String outcome, String comment,
			String nodeName, String projName, String sn,String menutext) {
		String msgContent = new String();
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = dateFormat.format(now);
		if (outcome.equals("正常")) {
			msgContent = "您发送的" + "【" + menutext + "】" + "\n【项目名称：" + projName+ "】" 
					+ "\n【检查单据编号：" + sn+ "】\n已在【" + nodeName + "】被评定为正常项目！\n" + "审批意见: " + comment
					+ "\n日期：" + today;
		} else if (outcome.equals("不正常")) {
			msgContent = "您发送的" + "【" + menutext + "】" + "\n【项目名称：" + projName+ "】" 
					+ "\n【检查单据编号：" + sn+ "】\n已在【" + nodeName + "】被评定为非正常项目！\n" + "审批意见: " + comment
					+ "\n日期：" + today;
		}	else if (outcome.equals("是")) {
			msgContent = "您发送的" + "【" + menutext + "】" + "\n【项目名称：" + projName+ "】" 
					+ "\n【检查单据编号：" + sn+ "】\n已在【" + nodeName + "】标记为风险，需要上会\n" + "审批意见: " + comment
					+ "\n日期：" + today;
		}   else if (outcome.equals("否")) {
			msgContent = "您发送的" + "【" + menutext + "】" + "\n【项目名称：" + projName+ "】" 
					+ "\n【检查单据编号：" + sn+ "】\n已在【" + nodeName + "】标记为风险，但是无需上会\n" + "审批意见: " + comment
					+ "\n日期：" + today;
		}	else if (outcome.equals("确认")) {
			msgContent = "您发送的" + "【" + menutext + "】" + "\n【项目名称：" + projName+ "】" 
					+ "\n【检查单据编号：" + sn+ "】\n已在【" + nodeName + "】上会决议已录入\n" + "审批意见: " + comment
					+ "\n日期：" + today;
		}
		return msgContent;
	}

	// menu翻译中文，发送信息用
	protected Map<String, String> menutotext(String menu) {
		Map<String, String> mapValue = new LinkedHashMap<String, String>();
		mapValue.put("IregInsp", "不定期检查");
		mapValue.put("PerInsp", "定期检查");
		return mapValue;
	}

	//消息发送方法
	public void sendMsg(Collection<IUser> receivers, DefaultUser sender,
			String msgTitle, String msgContent) {
		// ===发送消息===
		MessagePacket Msgpkt = new MessagePacket();
		Msgpkt.setTitle(msgTitle);
		Msgpkt.setContent(msgContent);
		Msgpkt.setTo(receivers);
		Msgpkt.setSender(sender);
		SendMsg.send(Msgpkt);
	}

	//===Ajax 获取variable参数传前台  审批时区分页面======
	@Expose
	@Transactional
	public Properties mapGetVariables(Map<String, Object> param)
			throws Exception {
		Properties mapValue = new Properties();
		Session session = this.getSessionFactory().openSession();
		try {
			String taskId = (String) param.get("taskId");
			Task uflotask = (Task) session
					.get(Task.class, Long.valueOf(taskId));
			Long processInstanceId = uflotask.getProcessInstanceId();
			String menu = (String) processClient.getProcessVariable("menu",
					processInstanceId);
			String aprv = (String) processClient.getProcessVariable("aprv",
					processInstanceId);
			mapValue.setProperty("menu", menu);
			mapValue.setProperty("aprv", aprv);
			mapValue.setProperty("node", uflotask.getNodeName());// 获取节点名称
		} finally {
			session.flush();
			session.close();
		}
		return mapValue;
	}
	
}



