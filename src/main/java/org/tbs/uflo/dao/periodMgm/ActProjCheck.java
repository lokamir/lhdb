package org.tbs.uflo.dao.periodMgm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.Bdf2User;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjCheck;

import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.ActionHandler;

@Component
public class ActProjCheck implements ActionHandler {

	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
	@Autowired
	@Qualifier(ProcessClient.BEAN_ID)
	private ProcessClient processClient;

	@SuppressWarnings("unchecked")
	
	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		String docid = processInstance.getBusinessId(); // businessId需要view绑定的
		if (StringUtils.isNotEmpty(docid)) {
			Session session = context.getSession();
			TbsProjCheck tbsProjCheck = (TbsProjCheck) session.get(TbsProjCheck.class,
					Integer.valueOf(docid));
			TbsProj tbsProj = tbsProjCheck.getTbsProj();
			Long processInstanceId = processInstance.getId();
			//更新risk
			String sql = "UPDATE `tbs`.`tbs_proj` SET `risk` = '1' WHERE `ID`="
					+ tbsProj.getId() + "";
			SQLQuery sqlquery1 = session.createSQLQuery(sql);
			sqlquery1.executeUpdate();
			// 获取menu参数（来自定期检查还是不定期检查）
			String menu = (String) processClient.getProcessVariable("menu",
					processInstanceId);
			// ====定期检查更新valid===
			if(menu.equals("PerInsp")){
				String sql2 = "update tbs.tbs_proj_check set valid = 1"
						+ " where del = 0 and id=" + docid + " ";
				SQLQuery sqlquery2 = session.createSQLQuery(sql2);
				sqlquery2.executeUpdate();
			}
			// ===发送项目存在风险的消息===
			String projname = tbsProj.getProjName();
			Bdf2User userA = tbsProj.getBdf2User_A();
			Bdf2User userB = tbsProj.getBdf2User_B();
			// 获取A、B角
			DefaultUser receiverA = (DefaultUser) session.get(
					DefaultUser.class, userA.getUsername());
			DefaultUser receiverB = (DefaultUser) session.get(
					DefaultUser.class, userB.getUsername());
			DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,
					"SystemSender"); // 获得消息的发送人，默认为“系统发信人”
			Collection<IUser> receivers = new ArrayList<IUser>();
			// 获取A角部门经理，风管经理，风管部门经理，分管总经理（风险）
			String sql_fgid = "select account from tbs.bdf2_user_dept a, tbs.tbs_approver b where b.title like '%分管风管总经理%' or b.title like '%风险管理部门经理%' or b.title like '%风管经理%' or a.dept_id_=b.deptid and b.title like '%部门经理%' and a.username_='"
					+ userA.getUsername() + "'";
			SQLQuery query_fgid = session.createSQLQuery(sql_fgid);
			List<String> str_fgid = query_fgid.list();
			// 获取到的名单去重
			List<String> tempList = new ArrayList<String>();
			for (String temp : str_fgid) {
				if (!tempList.contains(temp)) {
					tempList.add(temp);
				}
			}
			// 添加收件人
			for (String temp : tempList) {
				DefaultUser receiverX = (DefaultUser) session.get(
						DefaultUser.class, temp);
				receivers.add(receiverX);
			}
			receivers.add(receiverA);
			receivers.add(receiverB);
			//发送消息
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String today = dateFormat.format(now);
			MessagePacket Msgpkt = new MessagePacket();
			if (menu.equals("IregInsp")) {
				Msgpkt.setTitle("【不定期检查】风险预警！");
				Msgpkt.setContent("您发送的【不定期检查】\n【项目名称：" + projname
						+ "】\n标记为风险项目！\n日期： "+today);
			} else if (menu.equals("PerInsp")) {
				Msgpkt.setTitle("【定期检查】风险预警！");
				Msgpkt.setContent("您发送的【定期检查】\n【项目名称：" + projname
						+ "】\n标记为风险项目！\n日期： "+today);
			}
			Msgpkt.setTo(receivers);
			Msgpkt.setSender(sender);
			SendMsg.send(Msgpkt);

		}
	}

}
