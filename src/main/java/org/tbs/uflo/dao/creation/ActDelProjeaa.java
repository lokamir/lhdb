package org.tbs.uflo.dao.creation;

import java.util.ArrayList;
import java.util.Collection;

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

@Component
public class ActDelProjeaa implements ActionHandler {
    @Autowired
    @Qualifier(InternalMessageSender.BEAN_ID)
    private InternalMessageSender SendMsg;

    @Override
    public void handle(ProcessInstance processInstance, Context context) {
	Session session = context.getSession();
	String businessId = processInstance.getBusinessId();
	    
	//update valid in projeaa
	String sql="update tbs_projeaa set DEL = 1 where PROJ_ID = "+businessId+" and DEL = 0";
	SQLQuery sqlquery=session.createSQLQuery(sql);
	sqlquery.executeUpdate();

	// ===发送消息===
	TbsProj tbsProj = (TbsProj) session.get(TbsProj.class,
		Integer.valueOf(businessId));
	Bdf2User userA = tbsProj.getBdf2User_A();

	DefaultUser receiverA = (DefaultUser) session.get(
		DefaultUser.class, userA.getUsername());
	DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,
		"SystemSender"); // 获得消息的发送人，默认为“系统发信人”
	Collection<IUser> receivers = new ArrayList<IUser>();
	receivers.add(receiverA);

	MessagePacket Msgpkt = new MessagePacket();
	Msgpkt.setTitle("【" + tbsProj.getProjName() + "】已删除立项审批单！");
	Msgpkt.setContent("【项目名称：" + tbsProj.getProjName()
		+ "】\n已删除立项审批单！请再次确认后重新生成！！");
	Msgpkt.setTo(receivers);
	Msgpkt.setSender(sender);
	SendMsg.send(Msgpkt);
    }
}
