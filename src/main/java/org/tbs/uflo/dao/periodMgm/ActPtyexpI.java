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
import org.tbs.entity.TbsPtyexp;

import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.ActionHandler;


@Component
public class ActPtyexpI implements ActionHandler {

	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
	
	@SuppressWarnings("unchecked")
	
	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		String docid=processInstance.getBusinessId();  //businessId需要view绑定的
		if (StringUtils.isNotEmpty(docid)){
			Session session = context.getSession();
			//====全部审批通过,更新valid===
			TbsPtyexp tbsPtyexp=(TbsPtyexp)session.get(TbsPtyexp.class, Integer.valueOf(docid));
			String sql="UPDATE `tbs`.`tbs_ptyexp` SET `valid`='1' WHERE `ID`="+docid+"";
			SQLQuery sqlquery=session.createSQLQuery(sql);
			sqlquery.executeUpdate();			
			//===发送全部审批通过的消息===
			String sn = tbsPtyexp.getSn();
			TbsProj tbsProj = tbsPtyexp.getTbsProj();
			String projname = tbsProj.getProjName();
			Bdf2User userA = tbsProj.getBdf2User_A();
			Bdf2User userB = tbsProj.getBdf2User_B();
			DefaultUser receiverA = (DefaultUser) session.get(
					DefaultUser.class, userA.getUsername());
			DefaultUser receiverB = (DefaultUser) session.get(
					DefaultUser.class, userB.getUsername());
			DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,
					"SystemSender"); // 获得消息的发送人，默认为“系统发信人”
			Collection<IUser> receivers = new ArrayList<IUser>();
			String sql_fgid="select account from tbs.tbs_approver where title like '%分管财务总经理%'";
			SQLQuery query_fgid=session.createSQLQuery(sql_fgid);
			List<String> str_fgid = query_fgid.list();
			for(String temp:str_fgid){
				DefaultUser receiverFG = (DefaultUser) session.get(
						DefaultUser.class, temp);
				receivers.add(receiverFG);//分管财务总经理
			}
			receivers.add(receiverA);
			receivers.add(receiverB);
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String today = dateFormat.format(now);
			MessagePacket Msgpkt= new MessagePacket();
			Msgpkt.setTitle("【费用收取】审批已全部通过！");
			Msgpkt.setContent("您发送的【费用收取】\n【项目名称："+projname+"】\n"+"【单据编号】"+sn+"\n审批已全部通过！\n日期： "+today);
			Msgpkt.setTo(receivers);	
			Msgpkt.setSender(sender);	
			SendMsg.send(Msgpkt);
			
		}
	}

}
