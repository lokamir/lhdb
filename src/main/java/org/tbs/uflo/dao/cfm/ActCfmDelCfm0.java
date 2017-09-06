package org.tbs.uflo.dao.cfm;

import java.util.ArrayList;
import java.util.Collection;


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

/**
 * @author Bob 动作的实现类
 * 
 */

@Component
public class ActCfmDelCfm0 implements ActionHandler {

    @Autowired
    @Qualifier(InternalMessageSender.BEAN_ID)
    private InternalMessageSender SendMsg;
    
    @Autowired
    @Qualifier(ProcessClient.BEAN_ID)
    private ProcessClient processClient;

    @Override
    public void handle(ProcessInstance processInstance, Context context) {
	Session session = context.getSession();
	String businessId = processInstance.getBusinessId();
	String projcfm0Id = (String)processClient.getProcessVariable("cfm0Id", processInstance);

	TbsProj tbsProj = (TbsProj) session.get(TbsProj.class,
		Integer.valueOf(businessId));
	// generate cfm0, use A role as keyin user
	try{
	 // update process instance id into cfm0
	    String sqlCfm0Update = "update tbs_projcfm0 set del = 1" +
	    		" where ID = " + projcfm0Id;
	    SQLQuery queryCfm0Update = session.createSQLQuery(sqlCfm0Update);
	    queryCfm0Update.executeUpdate();
	} finally {
	    session.flush();
	}

	Collection<IUser> receivers = new ArrayList<IUser>();
	// get receiver
	DefaultUser receiver = (DefaultUser) session.get(DefaultUser.class,
		processInstance.getPromoter());
	receivers.add(receiver);
	DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,
		"SystemSender");

	MessagePacket Msgpkt = new MessagePacket();
	Msgpkt.setTitle("【" + tbsProj.getProjName() + "】已经被驳回！");
	Msgpkt.setContent("【项目名称：" + tbsProj.getProjName() + "】\n已被驳回！");
	Msgpkt.setTo(receivers);
	Msgpkt.setSender(sender);
	SendMsg.send(Msgpkt);
    }

}
