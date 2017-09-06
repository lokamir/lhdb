package org.tbs.uflo.dao.creation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
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
 * @author Bob 动作的实现类
 * 
 */

@Component
public class ActInformRoleAB implements ActionHandler {
    @Autowired
    @Qualifier(InternalMessageSender.BEAN_ID)
    private InternalMessageSender SendMsg;

    @Override
    public void handle(ProcessInstance processInstance, Context context) {
	String docid = processInstance.getBusinessId(); // businessId需要view绑定的
	if (StringUtils.isNotEmpty(docid)) {
	    Session session = context.getSession();
	    String businessId = processInstance.getBusinessId();
	    Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = dateFormat.format(now);
		
	    // ===发送消息===
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
	    Msgpkt.setTitle("【" + tbsProj.getProjName() + "】已立项受理！");
	    Msgpkt.setContent("【项目名称：" + tbsProj.getProjName()
		    + "】\n已受理！\n并已分配AB角色。\nA：" + receiverA.getCname() + "B："
		    + receiverB.getCname()+"\n日期："+today);
	    Msgpkt.setTo(receivers);
	    Msgpkt.setSender(sender);
	    SendMsg.send(Msgpkt);

	}
    }

}
