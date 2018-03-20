package org.tbs.uflo.dao.change;

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
import org.tbs.entity.TbsProjcfm0;
import org.tbs.entity.TbsProjchangeMajcont;

import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.ActionHandler;

/**
 * @author Bob 动作的实现类
 * 
 */

@Component("ActChangeMajcontCfmGrntProjCfm0")
public class ActCfmGrntProjCfm0 extends HibernateDao implements ActionHandler  {

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

	TbsProjchangeMajcont tbsProjchangeMajcont = (TbsProjchangeMajcont) session.get(TbsProjchangeMajcont.class,Integer.valueOf(businessId));
	int projid = tbsProjchangeMajcont.getTbsProj().getId();
	TbsProj tbsProj = (TbsProj)session.get(TbsProj.class,projid);
	String projSn = tbsProj.getSn();
	int aRoleId = tbsProj.getBdf2User_A().getId();
	Long piId = processInstance.getId();
	String promoter = processInstance.getPromoter();
	// generate cfm0, use A role as keyin user
	String projcfm0_sn;
	// prepare for insert datetime
	Date now = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String fNow = df.format(now);
	try {
		TbsProjcfm0 tbsProjcfm0 = new TbsProjcfm0();
		tbsProjcfm0.setProjSn(projSn);
		tbsProjcfm0.setTbsProj(tbsProj);
		tbsProjcfm0.setKeyinId(aRoleId);
		tbsProjcfm0.setBy3(piId.toString());
		tbsProjcfm0.setTimestampInput(now);
		tbsProjcfm0.setTimestampUpdate(now);
		session.save(tbsProjcfm0);
		
		// get cfm0 ID
		int projcfm0Id = tbsProjcfm0.getId();
		projcfm0_sn = tbsProjcfm0.getSn();
	    
	    // save cfm0Id into process variables
	    processClient.saveProcessVariable(piId, "cfm0Id", projcfm0Id);
	    
	    
	    // insert attendees for cfm0
	    String sqlInsert = "insert into tbs_proj_opinion(PROJ_ID,CFM0_ID,CFMTYPE,TIMESTAMP_INPUT,UID)" +
	    		" select " + projid + ", " + projcfm0Id + "," + 0 + ", '" + fNow + "'," +
		    "brb.uid from (select uid from tbs_approver " + "where title like '%业务审核委员会成员%') brb;";
	    SQLQuery queryInsert = session.createSQLQuery(sqlInsert);
	    queryInsert.executeUpdate();
	    
	    // get department head uid
	    String sqlDHUid = "select b.UID from bdf2_user_dept a, tbs_approver b where a.dept_id_=b.deptid and" +
	    		" b.title like '%部门经理%' and a.username_='" + promoter + "' ";
	    String sqlUid1 = "select UID from tbs_approver where title like '总经理' and deptname = '总经理办公室' ";
	    String sqlUid2 = "select UID from tbs_approver where trim(title) in ('分管风管总经理') ";
	    String sqlUid3 = "select UID from tbs_approver where trim(title) in ('风管经理','风险管理部门经理[副]','风险管理部门经理') ";
//	    SQLQuery sqlquery = session.createSQLQuery(sql);
//	    String dhID = sqlquery.uniqueResult().toString();
	    
	    // update promoter head's Title in the inserted items
	    String sqlUpdate = "update tbs_proj_opinion set TITLE = '发起人部门经理'" +
	    		" where PROJ_ID = " + projid +  
	    		" and CFM0_ID = " + projcfm0Id +
	    		" and UID in (" + sqlDHUid + ")";
	    SQLQuery queryUpdate = session.createSQLQuery(sqlUpdate);
	    queryUpdate.executeUpdate();
	    
	    
	    String sqlUpdate1 = "update tbs_proj_opinion set TITLE = '总经理'" +
	    		" where PROJ_ID = " + projid +  
	    		" and CFM0_ID = " + projcfm0Id +
	    		" and UID in (" + sqlUid1 + ")";
	    SQLQuery queryUpdate1 = session.createSQLQuery(sqlUpdate1);
	    queryUpdate1.executeUpdate();
	    String sqlUpdate2 = "update tbs_proj_opinion set TITLE = '分管风管总经理'" +
	    		" where PROJ_ID = " + projid +  
	    		" and CFM0_ID = " + projcfm0Id +
	    		" and UID in (" + sqlUid2 + ")";
	    SQLQuery queryUpdate2 = session.createSQLQuery(sqlUpdate2);
	    queryUpdate2.executeUpdate();
	    String sqlUpdate3 = "update tbs_proj_opinion set TITLE = '风管经理'" +
	    		" where PROJ_ID = " + projid +  
	    		" and CFM0_ID = " + projcfm0Id +
	    		" and UID in (" + sqlUid3 + ")";
	    SQLQuery queryUpdate3 = session.createSQLQuery(sqlUpdate3);
	    queryUpdate3.executeUpdate();
	    
	    /* 20160203 add for TbsProjOpinion --- End **/

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
	} finally {
		session.flush();
		session.close();
	}
    }

}
