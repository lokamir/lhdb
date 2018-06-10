package org.tbs.uflo.dao.change;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
//import org.tbs.entity.Bdf2User;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjchangeMajcont;
//import org.tbs.entity.TbsProjHtsh;

import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
//import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.process.handler.ActionHandler;

/**
 * @author Bob 动作的实现类
 * 
 */

@Component("ActChangeMajcontCfmDelCfm1")
public class ActCfmDelCfm1 implements ActionHandler {

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
	Integer cfm1r2Id = (Integer)processClient.getProcessVariable("cfm1r2Id", processInstance);
	Integer cfmFlag = Integer.parseInt(processClient.getProcessVariable("cfmFlag", processInstance).toString());

	TbsProjchangeMajcont tbsProjchangeMajcont = (TbsProjchangeMajcont) session.get(TbsProjchangeMajcont.class,Integer.valueOf(businessId));
	String projname = tbsProjchangeMajcont.getTbsProj().getProjName();
	//int aRoleId = tbsProj.getBdf2User_A().getId();
	// generate cfm0, use A role as keyin user
	try{
	    String sqlCfm1r2Update;
	    if (cfmFlag == 1) {
		sqlCfm1r2Update = "update tbs_projcfm1 set del = 1" +
	    		" where ID = " + cfm1r2Id;
	    } else {
		sqlCfm1r2Update = "update tbs_projcfm2 set del = 1" +
	    		" where ID = " + cfm1r2Id;
	    }
	    
	    SQLQuery queryCfm1r2Update = session.createSQLQuery(sqlCfm1r2Update);
	    queryCfm1r2Update.executeUpdate();
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
	Msgpkt.setTitle("【" + projname + "】已被驳回！");
	Msgpkt.setContent("【项目名称：" + projname + "】\n已被驳回！");
	Msgpkt.setTo(receivers);
	Msgpkt.setSender(sender);
	SendMsg.send(Msgpkt);
    }

}
