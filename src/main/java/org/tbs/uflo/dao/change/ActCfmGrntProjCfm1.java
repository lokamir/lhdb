package org.tbs.uflo.dao.change;

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

@Component("ActChangeMajcontCfmGrntProjCfm1")
public class ActCfmGrntProjCfm1 implements ActionHandler {

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
	Integer cfm0Id = Integer.parseInt(processClient.getProcessVariable("cfm0Id", processInstance).toString());
	Integer cfmFlag = Integer.parseInt(processClient.getProcessVariable("cfmFlag", processInstance).toString());

	TbsProj tbsProj = (TbsProj) session.get(TbsProj.class,
		Integer.valueOf(businessId));
	int aRoleId = tbsProj.getBdf2User_A().getId();
	String projcfm1_sn = "";
	String projcfm2_sn = "";
	// generate cfm1, use A role as keyin user
	try {
	    if (cfmFlag == 1) {
		// call procedure and get SN created
		String sqlProcedure = "call p_cfm1(1," + businessId + ","
			+ aRoleId + "," + cfm0Id + ")";
		SQLQuery query = session.createSQLQuery(sqlProcedure);
		projcfm1_sn = query.uniqueResult().toString();
		// get cfm1 ID
		String sqlProjcfm1ID = "select ID from tbs_projcfm1 where SN ='"
			+ projcfm1_sn + "'";
		SQLQuery queryProjcfm0ID = session.createSQLQuery(sqlProjcfm1ID);
		String projcfm1Id = queryProjcfm0ID.uniqueResult().toString();
		Long piId = processInstance.getId();
		// update process instance id into cfm1
		String sqlCfm1Update = "update tbs_projcfm1 set BY3 = '"
			+ piId.toString() + "'" + " where ID = " + projcfm1Id;
		SQLQuery queryCfm1Update = session.createSQLQuery(sqlCfm1Update);
		queryCfm1Update.executeUpdate();

		// save cfm1Id into process variables
		processClient.saveProcessVariable(piId, "cfm1r2Id", projcfm1Id);
		
	    } else {
	    String sql = "update tbs_projcfm0 set valid=1 where del=0 and id= "+ cfm0Id;
		SQLQuery sqlquery = session.createSQLQuery(sql);
		sqlquery.executeUpdate();
		// call procedure and get SN created
		String sqlProcedure = "call p_cfm2(1," + businessId + "," + aRoleId + ")";
		SQLQuery query = session.createSQLQuery(sqlProcedure);
		projcfm2_sn = query.uniqueResult().toString();
		// get cfm2 ID
		String sqlProjcfm2ID = "select ID from tbs_projcfm2 where SN ='"
			+ projcfm2_sn + "'";
		SQLQuery queryProjcfm2ID = session.createSQLQuery(sqlProjcfm2ID);
		String projcfm2Id = queryProjcfm2ID.uniqueResult().toString();
		Long piId = processInstance.getId();
		// update process instance id into cfm2
		String sqlCfm2Update = "update tbs_projcfm2 set BY3 = '" + piId.toString() + "'" +
			" , tobe = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + businessId + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2)" +
			" , actbe = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + businessId + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2 and outcome <> '未知')" +
			" , agree = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + businessId + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2 and outcome = '同意')" +
			" , opoose = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + businessId + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2 and outcome = '反对')" +
			" , debarb = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + businessId + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2 and outcome = '回避')" +
			" , nobe = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + businessId + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2 and outcome = '缺席')" +
			" where ID = " + projcfm2Id;
		SQLQuery queryCfm2Update = session.createSQLQuery(sqlCfm2Update);
		queryCfm2Update.executeUpdate();

		// save cfm1Id into process variables
		processClient.saveProcessVariable(piId, "cfm1r2Id", projcfm2Id);
	    }
	}catch(Exception e){
		System.out.println("error!");
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
	Msgpkt.setTitle("【" + tbsProj.getProjName() + "】已生成决议单！");
	Msgpkt.setContent("【项目名称：" + tbsProj.getProjName() + "】\n已生成决议单！\n【决议单号】 "+projcfm1_sn+""+projcfm2_sn);
	Msgpkt.setTo(receivers);
	Msgpkt.setSender(sender);
	SendMsg.send(Msgpkt);
    }

}
