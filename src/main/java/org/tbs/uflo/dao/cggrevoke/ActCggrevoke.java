package org.tbs.uflo.dao.cggrevoke;

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
import org.tbs.entity.TbsProj;

import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.ActionHandler;

@Component
public class ActCggrevoke implements ActionHandler {
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
			Long processInstanceId = processInstance.getId();
			TbsProj tbsProj = (TbsProj) session.get(
					TbsProj.class, Integer.valueOf(docid));
			String promoter = processInstance.getPromoter();
			String businessId = processInstance.getBusinessId();
			ArrayList<String> revokecggSn = (ArrayList<String>) processClient
					.getProcessVariable("revokecggSn",
							Long.valueOf(processInstanceId));
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String today = dateFormat.format(now);
			//删除tbsprojcgg数据，删除反担保物字表数据（del=1）
			if (revokecggSn != null) {
				for (String sn : revokecggSn) {
					String str = sn.substring(0,4);
					if (str != null && str.equals("DYFD")) {
						String sqlupdate = "update tbs.tbs_cgg_dy set del=1 where del = 0 and sn='"
								+ sn + "'";
						SQLQuery sqlqueryupdate = session
								.createSQLQuery(sqlupdate);
						sqlqueryupdate.executeUpdate();
					}
					if (str != null && str.equals("ZYFD")) {
						String sqlupdate = "update tbs.tbs_cgg_zy set del=1 where del = 0 and sn='"
								+ sn + "'";
						SQLQuery sqlqueryupdate = session
								.createSQLQuery(sqlupdate);
						sqlqueryupdate.executeUpdate();

					}
					if (str != null && str.equals("BJZY")) {
						String sqlupdate = "update tbs.tbs_cgg_bzjzy set del=1 where del = 0 and sn='"
								+ sn + "'";
						SQLQuery sqlqueryupdate = session
								.createSQLQuery(sqlupdate);
						sqlqueryupdate.executeUpdate();
					}
					if (str != null && str.equals("GRBZ")) {
						String sqlupdate = "update tbs.tbs_cgg_grbz set del=1 where del = 0 and sn='"
								+ sn + "'";
						SQLQuery sqlqueryupdate = session
								.createSQLQuery(sqlupdate);
						sqlqueryupdate.executeUpdate();

					}
					if (str != null && str.equals("QYBZ")) {
						String sqlupdate = "update tbs.tbs_cgg_qybz set del=1 where del = 0 and sn='"
								+ sn + "'";
						SQLQuery sqlqueryupdate = session
								.createSQLQuery(sqlupdate);
						sqlqueryupdate.executeUpdate();

					}
					String sqlupdate = "delete from tbs.tbs_proj_cgg where cgg_sn= '"
							+ sn + "'";
					SQLQuery sqlqueryupdate = session.createSQLQuery(sqlupdate);
					sqlqueryupdate.executeUpdate();
				}
			}
			//tbs_cgg_chg对应数据的valid改为1
			String sqlupdate = "update tbs.tbs_cgg_chg set valid = 1 where valid = 0 and proj_id='"
					+ businessId + "'";
			SQLQuery sqlqueryupdate = session.createSQLQuery(sqlupdate);
			sqlqueryupdate.executeUpdate();
			//存储过程
			String proceId = processInstanceId.toString();
			String sql_a="call p_hisloc(7,"+docid+","+tbsProj.getTbsCustomer().getId()+",'"+ proceId +"')";
			SQLQuery sqlquery_a=session.createSQLQuery(sql_a);
			sqlquery_a.executeUpdate();
			// ===发送消息===
			DefaultUser receiverA = (DefaultUser) session.get(
					DefaultUser.class, promoter);
			Collection<IUser> receivers = new ArrayList<IUser>();
			receivers.add(receiverA);
			// 法务经理、风管经理
			String sql_fgid = "select account from tbs.tbs_approver where title like '%风管经理%'";
			SQLQuery query_fgid = session.createSQLQuery(sql_fgid);
			List<String> str_fgid = query_fgid.list();
			for (String temp : str_fgid) {
				DefaultUser receiverFG = (DefaultUser) session.get(
						DefaultUser.class, temp);
				receivers.add(receiverFG);// 风管
			}
			String sql_fwid = "select account from tbs.tbs_approver where title like '%法务经理%'";
			SQLQuery query_fwid = session.createSQLQuery(sql_fwid);
			List<String> str_fwid = query_fwid.list();
			for (String temp : str_fwid) {
				DefaultUser receiverFW = (DefaultUser) session.get(
						DefaultUser.class, temp);
				receivers.add(receiverFW);// 法务
			}
			DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,
					"SystemSender"); // 获得消息的发送人，默认为“系统发信人”
			MessagePacket Msgpkt = new MessagePacket();
			Msgpkt.setTitle("【" + tbsProj.getProjName() + "】的反担保物解除成功！");
			Msgpkt.setContent("【客户名称：" + tbsProj.getProjName() + "】\n解除成功！\n"
					+ "\n日期：" + today);
			Msgpkt.setTo(receivers);
			Msgpkt.setSender(sender);
			SendMsg.send(Msgpkt);

		}
	}
}