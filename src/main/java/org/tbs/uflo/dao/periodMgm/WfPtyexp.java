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
import org.tbs.entity.TbsProjundwrt;
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
public class WfPtyexp extends HibernateDao {
	
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
	public String startPtyexpInstance(Map<String, Object> params)
			throws Exception {
		String result = "";
		if (params != null) {
			int id = (int) params.get("docid");// 费用单据ID
			String sn = (String) params.get("sn");// 费用单据SN
			String menu = (String) params.get("menu");// 费用单据名称
			TbsProj tbsproj = (TbsProj) params.get("proj");// proj对象
			TbsProjundwrt undwrt = (TbsProjundwrt) params.get("undwrt");// undwrt对象
			StartProcessInfo info = new StartProcessInfo();
			// 单据id
			info.setBusinessId(Integer.toString(id));
			// 发起人
			info.setPromoter(ContextHolder.getLoginUser().getUsername());
			// 开始流程就直接往下走，而不是停留在开始节点等待发起人完成开始节点
			info.setCompleteStartTask(true);
			Map<String, Object> variables = new HashMap<String, Object>();
			// variables for parallel approve
			variables.put("sn", sn);
			variables.put("menu", menu);
			variables.put("tbsprojid", tbsproj.getId());
			variables.put("tbsprojsn", tbsproj.getSn());
			variables.put("projName", tbsproj.getProjName());
			variables.put("undwrtid", undwrt.getId());
			variables.put("aprv", "approve");// 方便前台取用，最好用String类型
			info.setVariables(variables);
			// 对流程实例打个标记，以备后续使用
			info.setTag(menu);
			// 根据menu开始对应流程
			ProcessInstance pi = processClient.startProcessByName(
					menu.toLowerCase(), info);
			Long processInstanceId = pi.getId();
			result = "提交审批成功！";
			Session session = this.getSessionFactory().openSession();
			Map<String, String> by = new HashMap<String, String>();
			by.put("PtyexpI", "by1");
			by.put("PtyexpO", "by1");
			try {
				String sql1 = "UPDATE `tbs`.`tbs_ptyexp` SET " + by.get(menu)
						+ "=" + processInstanceId.toString()
						+ " where del = 0 and id=" + id + " ";
				SQLQuery sqlquery1 = session.createSQLQuery(sql1);
				sqlquery1.executeUpdate();
				String sql2 = "UPDATE `tbs`.`tbs_ptyexp` SET valid = 2"
						+ " where del = 0 and id=" + id + " ";
				SQLQuery sqlquery2 = session.createSQLQuery(sql2);
				sqlquery2.executeUpdate();
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
		try {
			String menu = (String) processClient.getProcessVariable("menu",
					processInstanceId);
			if (outcome.equals("驳回")) {
				processClient.saveProcessVariable(
						Long.valueOf(processInstanceId), "aprv", "reject");
				String sql = "UPDATE `tbs`.`tbs_ptyexp` SET valid = 3"
						+ " where del = 0 and id=" + pi.getBusinessId() + " ";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				sqlquery.executeUpdate();
			}
			if (outcome.equals("确认修改")) {
				processClient.saveProcessVariable(
						Long.valueOf(processInstanceId), "aprv", "approve");
				String sql = "UPDATE `tbs`.`tbs_ptyexp` SET valid = 2"
						+ " where del = 0 and id=" + pi.getBusinessId() + " ";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				sqlquery.executeUpdate();
			}
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
			String msgContent = getMsgContent(
					outcome,
					comment,
					uflotask.getNodeName(),
					processClient.getProcessVariable("projName", pi).toString(),
					menutext, processClient.getProcessVariable("sn", pi)
							.toString());
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

	// getReceivers 获取消息接收人
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

	// getMsgTitle 消息标题
	protected String getMsgTitle(String outcome, String nodeName,
			String menutext) {
		String msgTitle = new String();
		if (outcome.equals("通过")) {
			msgTitle = "【" + menutext + "】" + "审批已通过！";
		} else if (outcome.equals("确认修改")) {
			msgTitle = "【" + menutext + "】" + "已经重新提交";
		} else if (outcome.equals("驳回")) {
			msgTitle = "【" + menutext + "】" + "审批已驳回！";
		} else if (outcome.equals("确认")) {
			msgTitle = "【" + menutext + "】" + "出纳已付款";
		}
		return msgTitle;
	}

	// getMsgContent 消息内容
	protected String getMsgContent(String outcome, String comment,
			String nodeName, String projName, String menutext, String sn) {
		String msgContent = new String();
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String today = dateFormat.format(now);
		if (outcome.equals("通过")) {
			msgContent = "您发送的" + "【" + menutext + "】" + "\n【项目名称：" + projName
					+ "】\n" + "【单据编号】" + sn + "已经通过【" + nodeName + "】审批！\n"
					+ "审批意见: " + comment + "\n日期：" + today;
		} else if (outcome.equals("确认修改")) {

			msgContent = "您发送的" + "【" + menutext + "】" + "\n【项目名称：" + projName
					+ "】\n" + "【单据编号】" + sn + "已经【确认修改】并再次提交审批！\n" + "审批意见: "
					+ comment + "\n日期：" + today;
		} else if (outcome.equals("驳回")) {
			msgContent = "您发送的" + "【" + menutext + "】" + "\n【项目名称：" + projName
					+ "】\n" + "【单据编号】" + sn + "已在【" + nodeName + "】被驳回！\n"
					+ "审批意见: " + comment + "\n日期：" + today;
		} else if (outcome.equals("确认")) {
			msgContent = "您发送的" + "【" + menutext + "】" + "\n【项目名称：" + projName
					+ "】\n" + "【单据编号】" + sn + "已在【" + nodeName + "】完成付款！\n"
					+ "审批意见: " + comment + "\n日期：" + today;
		}
		return msgContent;

	}

	// menu翻译中文，发送信息用
	protected Map<String, String> menutotext(String menu) {
		Map<String, String> mapValue = new LinkedHashMap<String, String>();
		mapValue.put("PtyexpI", "费用收取");
		mapValue.put("PtyexpO", "费用退回");
		return mapValue;
	}

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

	// ===Ajax 获取variable参数传前台 审批时区分页面===
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
			String tbsprojid = String.valueOf(processClient.getProcessVariable(
					"tbsprojid", processInstanceId));
			String undwrtid = String.valueOf(processClient.getProcessVariable(
					"undwrtid", processInstanceId));
			mapValue.setProperty("menu", menu);
			mapValue.setProperty("aprv", aprv);
			mapValue.setProperty("tbsprojid", tbsprojid);
			mapValue.setProperty("undwrtid", undwrtid);
			mapValue.setProperty("node", uflotask.getNodeName());
		} finally {
			session.flush();
			session.close();
		}
		return mapValue;
	}
	
}



