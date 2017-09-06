package org.tbs.uflo.dao.cggrevoke;

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
import com.bstek.dorado.annotation.Expose;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.client.service.TaskClient;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskOpinion;

@Component
public class WfCggrevoke extends HibernateDao {
	@Autowired
	@Qualifier(ProcessClient.BEAN_ID)
	private ProcessClient processClient;

	@Autowired
	@Qualifier(TaskClient.BEAN_ID)
	private TaskClient taskClient;

	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;

	// 发起流程
	@Expose
	@SuppressWarnings("unchecked")
	public String startWF(Map<String, Object> params) throws Exception {
		String result = "";
		if (params != null) {
			// 获取customer表主键
			String id = Integer.toString((int) params.get("docid"));
			// 保证金质押判断的参数，有保证金 value为1 ，没有为0
			String value = Integer.toString((int) params.get("bzjzy"));
			// 获取对应需要解除的反担保物的SN,都是String类型的数组
			String projName = (String) params.get("projName");
			ArrayList<String> revokecggSn = (ArrayList<String>) params.get("revokecggSn");
			// 发起流程实例
			StartProcessInfo info = new StartProcessInfo();
			// 单据id
			info.setBusinessId(id);
			// 发起人
			info.setPromoter(ContextHolder.getLoginUser().getUsername());
			// 开始流程就直接往下走，而不是停留在开始节点等待发起人完成开始节点
			info.setCompleteStartTask(true);
			// 对流程实例打个标记，以备后续使用
			info.setTag("Cggdismis");
			// 反担保物的id作为流程内variables参数传进流程
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("bzjzy", value);
			variables.put("revokecggSn", revokecggSn);
			variables.put("projName", projName);
			info.setVariables(variables);
			ProcessInstance pi = processClient.startProcessByName("cggrevoke",info);
			Long processInstanceId = pi.getId();
			result = "提交审批成功！";
			Session session = this.getSession();
			try {
				//TbsProj tbsproj = (TbsProj) session.get(TbsProj.class,Integer.valueOf(id));
				if (revokecggSn != null && revokecggSn.size() > 0) {
					for (int i = 0; i < revokecggSn.size(); i++) {
						String sqlupdate = "update tbs.tbs_proj_cgg set state=1,by1 ="+processInstanceId.toString()+" where valid = 1 and cgg_sn='"
								+ revokecggSn.get(i) + "'";
						SQLQuery sqlqueryupdate = session
								.createSQLQuery(sqlupdate);
						sqlqueryupdate.executeUpdate();
					}
				}
				//tbsproj.setBy5(processInstanceId.toString()); // 把processInstanceId放入单据的by5内
				//session.update(tbsproj);
			} finally {
				session.flush();
				session.close();
			}
		} else {
			result = "当前反担保物状态不能提请解除";
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Expose
	public void ApprWF(Map<String, Object> params) throws Exception {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String today = dateFormat.format(now);
		ArrayList<String> revokecggSn = (ArrayList<String>) params
				.get("revokecggSn");
		String docid = (String) params.get("docid"); // 获取信息
		String taskid = (String) params.get("taskid");
		String outcome = (String) params.get("outcome");
		String comment = (String) params.get("comment");
		String opinion = outcome + ": " + comment;
		TaskOpinion taskOpinion = new TaskOpinion(opinion); // 获取结束
		taskClient.start(Long.valueOf(taskid));
		Session session = this.getSessionFactory().openSession();
		try {
			// ===获取流程内的一些值===
			TbsProj tbsproj = (TbsProj) session.get(TbsProj.class,
					Integer.valueOf(docid));
			String projName = tbsproj.getProjName();
			Task uflotask = (Task) session
					.get(Task.class, Long.valueOf(taskid));
			String nodename = uflotask.getNodeName();
			Long processInstanceId = uflotask.getProcessInstanceId(); // 获取processinstanceId
			ProcessInstance pi = (ProcessInstance) session.get(
					ProcessInstance.class, processInstanceId);
			String promoter = pi.getPromoter(); // 获取promoter
			//先取出原來的cggsn
			ArrayList<String> revokecggSnReject = (ArrayList<String>)processClient.getProcessVariable("revokecggSn", Long.valueOf(processInstanceId));
			//再放入新修改的cggsn
			if (revokecggSn != null) {
				processClient.saveProcessVariable(processInstanceId, "revokecggSn",
						revokecggSn);
			}
			// ===消息处理===
			DefaultUser receiver = (DefaultUser) session.get(DefaultUser.class,
					promoter); // 获得消息的接收人
			DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,
					"SystemSender"); // 获得消息的发送人，默认为“系统发信人”
			Collection<IUser> receivers = new ArrayList<IUser>();
			receivers.add(receiver);
			MessagePacket Msgpkt = new MessagePacket();
			Msgpkt.setTo(receivers);
			Msgpkt.setSender(sender);

			// ===审批处理===
			if (outcome.equals("通过")) {
				Msgpkt.setTitle("【反担保物解除】审批已通过【" + nodename + "】审批！");
				Msgpkt.setContent("您发送的【反担保物解除】\n【项目名称：" + projName
						+ "】\n已经通过【" + nodename + "】审批！\n" + today);
				SendMsg.send(Msgpkt);
			} else if (outcome.equals("驳回")) {
				Msgpkt.setTitle("【反担保物解除】审批已驳回！");
				Msgpkt.setContent("您发送的【反担保物解除】\n【项目名称：" + projName
						+ "】\n已经被驳回，驳回节点为【" + nodename + "】\n" + today);
				SendMsg.send(Msgpkt);
			} else if (outcome.equals("修改确认")) {
				Msgpkt.setTitle("【反担保物解除】单据已完成修改！");
				Msgpkt.setContent("您发送的【反担保物解除】\n【项目名称：" + projName + "】\n已经被【"
						+ nodename + "】\n" + today);
				SendMsg.send(Msgpkt);
				if (revokecggSnReject != null && revokecggSnReject.size() > 0) {
				for (String entity : revokecggSnReject ) {
					String sqlupdate = "update tbs.tbs_proj_cgg set state=0,by1 = "+null+" where valid = 1 and cgg_sn='"
							+ entity + "'";
					SQLQuery sqlqueryupdate = session.createSQLQuery(sqlupdate);
					sqlqueryupdate.executeUpdate();
					}
				}
			}

		} finally {
			session.flush();
			session.close();
			taskClient.complete(Long.valueOf(taskid), outcome, taskOpinion); // 在中间可以加入路径名称“通过”等字样，流程图里面的路径需要写明。complete只能放在这里才可以确保后续的路程运行顺利
		}

	}
}
