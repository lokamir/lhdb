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

@Component
public class ActGnrtProjeaa  implements ActionHandler  {

	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
	
    @Override
    public void handle(ProcessInstance processInstance, Context context)  {
	Session session = context.getSession();
	String projid = processInstance.getBusinessId();
	TbsProj tbsProj = (TbsProj) session.get(TbsProj.class, Integer.valueOf(projid));
	int uid = tbsProj.getBdf2User_A().getId();
	Bdf2User userA = tbsProj.getBdf2User_A();
	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String today = dateFormat.format(now);
	
	
	DefaultUser receiverA = (DefaultUser) session.get(DefaultUser.class, userA.getUsername());
	DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,"SystemSender"); // 获得消息的发送人，默认为“系统发信人”
	Collection<IUser> receivers = new ArrayList<IUser>();
	receivers.add(receiverA);
	
	String projeaa_sn = "";
	try {
	String sql="call p_eaa(1,"+projid+","+uid+")";
	SQLQuery query=session.createSQLQuery(sql);
	projeaa_sn=query.uniqueResult().toString();
	} catch (Exception e){
		System.out.println("projeaa_sn() catch (Exception e)");
		e.printStackTrace();
	    }	
	
	
	MessagePacket Msgpkt = new MessagePacket();
	Msgpkt.setTitle("【" + tbsProj.getProjName() + "】已生成立项审批单！");
	Msgpkt.setContent("【项目名称：" + tbsProj.getProjName()
		+ "】\n已通过【受理审批】并生成【立项审批单】\n【立项审批单号】 "+projeaa_sn+"\n日期："+today);
	Msgpkt.setTo(receivers);
	Msgpkt.setSender(sender);
	SendMsg.send(Msgpkt);	
		
	}

}
