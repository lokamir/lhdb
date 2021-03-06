package org.tbs.uflo.dao.cfm;

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
import org.tbs.entity.TbsProjcfm1;
import org.tbs.entity.TbsProjcfm2;

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
public class ActCfmInfmAB implements ActionHandler {

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
	Integer cfm1r2Id = Integer.parseInt(processClient.getProcessVariable("cfm1r2Id", processInstance).toString());
	int cfmFlag = (int) processClient.getProcessVariable("cfmFlag", processInstance);
	TbsProj tbsProj = (TbsProj) session.get(TbsProj.class, Integer.valueOf(businessId));
	int cusid = tbsProj.getTbsCustomer().getId();
	int projid = tbsProj.getId();
	int uid = tbsProj.getBdf2User_A().getId();
	
	if (cfmFlag == 1) {
	    TbsProjcfm1 cfm1 = (TbsProjcfm1) session.get(TbsProjcfm1.class, Integer.valueOf(cfm1r2Id));
		String docsn1 = cfm1.getSn();
		//update valid in cfm1
	    String sql="update tbs_projcfm1 set valid=1 where ID = "+cfm1r2Id;
	    SQLQuery sqlquery=session.createSQLQuery(sql);
	    sqlquery.executeUpdate();
	    //Gnrt hisloc & proj.vloc
	    String sql1="call p_hisloc (1,"+businessId+","+cusid+",'"+docsn1+"')";
	    SQLQuery sqlquery1=session.createSQLQuery(sql1);
	    sqlquery1.executeUpdate();
	} else {
	    TbsProjcfm2 cfm2 = (TbsProjcfm2) session.get(TbsProjcfm2.class, Integer.valueOf(cfm1r2Id));
		String docsn2 = cfm2.getSn();
		//update valid in cfm2
	    String sql="update tbs_projcfm2 set valid=1 where ID = "+cfm1r2Id;
	    SQLQuery sqlquery=session.createSQLQuery(sql);
	    sqlquery.executeUpdate();
	    //Gnrt hisloc & proj.vloc
	    String sql2="call p_hisloc (1,"+businessId+","+cusid+",'"+docsn2+"')";
	    SQLQuery sqlquery2=session.createSQLQuery(sql2);
	    sqlquery2.executeUpdate();
	}

	// ===发送全部审批通过的消息===
	Bdf2User userA = tbsProj.getBdf2User_A();
	Bdf2User userB = tbsProj.getBdf2User_B();
	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String today = dateFormat.format(now);
	
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
	Msgpkt.setTitle("【" + tbsProj.getProjName() + "】决议审批通过！并已产生授信额度！");
	Msgpkt.setContent("【项目名称：" + tbsProj.getProjName()
		+ "】\n已通过决议审批！并已产生授信额度！\n"+"日期："+today);
	Msgpkt.setTo(receivers);
	Msgpkt.setSender(sender);
	SendMsg.send(Msgpkt);
	//====生成承保审批单
	String sql_undwrt="CALL P_UNDWRT(1,"+projid+","+uid+")";
	SQLQuery query_undwrt=session.createSQLQuery(sql_undwrt);
	query_undwrt.executeUpdate();
    }

}
