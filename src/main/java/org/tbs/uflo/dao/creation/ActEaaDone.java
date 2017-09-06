package org.tbs.uflo.dao.creation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.Bdf2User;
import org.tbs.entity.TbsProj;

import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.ActionHandler;
/**
 * @author Bob
 * 动作的实现类
 * 
 */

@Component
public class ActEaaDone implements ActionHandler {
    @Autowired
    @Qualifier(InternalMessageSender.BEAN_ID)
    private InternalMessageSender SendMsg;

    @Override
    public void handle(ProcessInstance processInstance, Context context) {
	Session session = context.getSession();
	String businessId = processInstance.getBusinessId();
	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String today = dateFormat.format(now);
	
	//update valid in projeaa
	String sql="update tbs_projeaa set valid=1 where PROJ_ID = "+businessId+" and DEL = 0";
	SQLQuery sqlquery=session.createSQLQuery(sql);
	sqlquery.executeUpdate();

	// ===发送全部审批通过的消息===
	TbsProj tbsProj = (TbsProj) session.get(TbsProj.class,
		Integer.valueOf(businessId));
	Bdf2User userA = tbsProj.getBdf2User_A();
	Bdf2User userB = tbsProj.getBdf2User_B();

	DefaultUser receiverA = (DefaultUser) session.get(
		DefaultUser.class, userA.getUsername());
	DefaultUser receiverB = (DefaultUser) session.get(
		DefaultUser.class, userB.getUsername());
	DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,
		"SystemSender"); // 获得消息的发送人，默认为“系统发信人”
	Collection<IUser> receivers = new ArrayList<IUser>();
	receivers.add(receiverA);
	receivers.add(receiverB);

	MessagePacket Msgpkt = new MessagePacket();
	Msgpkt.setTitle("【" + tbsProj.getProjName() + "】已通过立项审批！");
	Msgpkt.setContent("【项目名称：" + tbsProj.getProjName()
		+ "】\n已通过立项审批！\n日期："+today);
	Msgpkt.setTo(receivers);
	Msgpkt.setSender(sender);
	SendMsg.send(Msgpkt);
    }
}
