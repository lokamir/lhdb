package org.tbs.uflo.dao.cfm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
public class ActCfmGrntProjCfm0 implements ActionHandler {

    @Autowired
    @Qualifier(InternalMessageSender.BEAN_ID)
    private InternalMessageSender SendMsg;
    
    @Autowired
    @Qualifier(ProcessClient.BEAN_ID)
    private ProcessClient processClient;

    @Override
    public void handle(ProcessInstance processInstance, Context context) {
	// TODO Auto-generated method stub
	Session session = context.getSession();
	String businessId = processInstance.getBusinessId();

	TbsProj tbsProj = (TbsProj) session.get(TbsProj.class,
		Integer.valueOf(businessId));
	int aRoleId = tbsProj.getBdf2User_A().getId();
	String projcfm0_sn="";
	// generate cfm0, use A role as keyin user
	try {
	    String sqlProcedure = "call p_cfm0(1," + businessId + "," + aRoleId
		    + ")";
	    SQLQuery query = session.createSQLQuery(sqlProcedure);
	    
	    /* 20160203 add for TbsProjOpinion --- Start **/
	    String promoter = processInstance.getPromoter();
	    // call procedure and get SN created
	    projcfm0_sn = query.uniqueResult().toString();
	    // get cfm0 ID
	    String sqlProjcfm0ID = "select ID from tbs_projcfm0 where SN ='" + projcfm0_sn + "'";
	    SQLQuery queryProjcfm0ID = session.createSQLQuery(sqlProjcfm0ID);
	    String projcfm0Id = queryProjcfm0ID.uniqueResult().toString();
	    Long piId = processInstance.getId();
	    
	    // save cfm0Id into process variables
	    processClient.saveProcessVariable(piId, "cfm0Id", projcfm0Id);
	    
	    // update process instance id into cfm0
	    String sqlCfm0Update = "update tbs_projcfm0 set BY3 = '" + piId.toString() + "'" +
	    		" where ID = " + projcfm0Id;
	    SQLQuery queryCfm0Update = session.createSQLQuery(sqlCfm0Update);
	    queryCfm0Update.executeUpdate();
	    
	    // prepare for insert datetime
	    Date now = new Date();
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fNow = df.format(now);
	    
	    // insert attendees for cfm0
	    String sqlInsert = "insert into tbs_proj_opinion(PROJ_ID,CFM0_ID,CFMTYPE,TIMESTAMP_INPUT,UID)" +
	    		" select " + businessId + ", " + projcfm0Id + "," + 0 + ", '" + fNow + "'," +
		    "brb.uid from (select uid from tbs_approver " + "where title like '%业务审核委员会%') brb;";
	    SQLQuery queryInsert = session.createSQLQuery(sqlInsert);
	    queryInsert.executeUpdate();
	    
	    // get department head uid
	    String sqlDHUid = "select b.UID from bdf2_user_dept a, tbs_approver b where a.dept_id_=b.deptid and" +
	    		" b.title like '%部门经理%' and a.username_='" + promoter + "' ";
	    String sqlUid1 = "select UID from tbs_approver where title like '总经理' and deptname = '总经理办公室' ";
	    String sqlUid2 = "select UID from tbs_approver where trim(title) in ('分管风管总经理') ";
	    String sqlUid3 = "select UID from tbs_approver where trim(title) in ('风管经理','风险管理部门经理[副]') ";
//	    SQLQuery sqlquery = session.createSQLQuery(sql);
//	    String dhID = sqlquery.uniqueResult().toString();
	    
	    // update promoter head's Title in the inserted items
	    String sqlUpdate = "update tbs_proj_opinion set TITLE = '发起人部门经理'" +
	    		" where PROJ_ID = " + businessId +  
	    		" and CFM0_ID = " + projcfm0Id +
	    		" and UID in (" + sqlDHUid + ")";
	    SQLQuery queryUpdate = session.createSQLQuery(sqlUpdate);
	    queryUpdate.executeUpdate();
	    
	    
	    String sqlUpdate1 = "update tbs_proj_opinion set TITLE = '总经理'" +
	    		" where PROJ_ID = " + businessId +  
	    		" and CFM0_ID = " + projcfm0Id +
	    		" and UID in (" + sqlUid1 + ")";
	    SQLQuery queryUpdate1 = session.createSQLQuery(sqlUpdate1);
	    queryUpdate1.executeUpdate();
	    String sqlUpdate2 = "update tbs_proj_opinion set TITLE = '分管风管总经理'" +
	    		" where PROJ_ID = " + businessId +  
	    		" and CFM0_ID = " + projcfm0Id +
	    		" and UID in (" + sqlUid2 + ")";
	    SQLQuery queryUpdate2 = session.createSQLQuery(sqlUpdate2);
	    queryUpdate2.executeUpdate();
	    String sqlUpdate3 = "update tbs_proj_opinion set TITLE = '风管经理'" +
	    		" where PROJ_ID = " + businessId +  
	    		" and CFM0_ID = " + projcfm0Id +
	    		" and UID in (" + sqlUid3 + ")";
	    SQLQuery queryUpdate3 = session.createSQLQuery(sqlUpdate3);
	    queryUpdate3.executeUpdate();
	    
	    /* 20160203 add for TbsProjOpinion --- End **/
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
	Msgpkt.setTitle("【" + tbsProj.getProjName() + "】已生成上会通知单！");
	Msgpkt.setContent("【项目名称：" + tbsProj.getProjName() + "】\n已生成上会通知单！【上会通知单号】 "+projcfm0_sn);
	Msgpkt.setTo(receivers);
	Msgpkt.setSender(sender);
	SendMsg.send(Msgpkt);
    }

}
