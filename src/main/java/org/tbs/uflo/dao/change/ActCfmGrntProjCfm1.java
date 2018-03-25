package org.tbs.uflo.dao.change;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjcfm0;
import org.tbs.entity.TbsProjcfm1;
import org.tbs.entity.TbsProjchangeMajcont;
import org.tbs.entity.TbsProjeaa;

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
    Date now = new Date();
	Session session = context.getSession();
	String businessId = processInstance.getBusinessId();
	Integer cfm0Id = Integer.parseInt(processClient.getProcessVariable("cfm0Id", processInstance).toString());
	Integer cfmFlag = Integer.parseInt(processClient.getProcessVariable("cfmFlag", processInstance).toString());
	Long piId = processInstance.getId();
	TbsProjchangeMajcont tbsProjchangeMajcont = (TbsProjchangeMajcont) session.get(TbsProjchangeMajcont.class,
		Integer.valueOf(businessId));
	TbsProjcfm0 tbsProjcfm0 = (TbsProjcfm0) session.get(TbsProjcfm0.class,cfm0Id);
	TbsProj tbsProj = tbsProjchangeMajcont.getTbsProj();
	int projid = tbsProj.getId();
	int aRoleId = tbsProjchangeMajcont.getTbsProj().getBdf2User_A().getId();
	Collection<TbsProjeaa> tbsProjeaas = tbsProj.getTbsProjeaaSet();
	TbsProjeaa eaa = new TbsProjeaa();
	Date meetingdate = now;
	for(TbsProjeaa tbsProjeaa : tbsProjeaas){
		eaa = tbsProjeaa;
	}
	String projcfm1_sn = "";
	String projcfm2_sn = "";
	// generate cfm1, use A role as keyin user
	try {
	    if (cfmFlag == 1) {
		// 为了方便历史数据查询，会议决议插入projcfm1中，不再使用projchangecfm表
	    	if(tbsProjcfm0.getJtrq()!=null){
	    		meetingdate = tbsProjcfm0.getJtrq();
	    	}
	    	TbsProjcfm1 tbsProjcfm1 = new TbsProjcfm1();
	    	tbsProjcfm1.setTbsProj(tbsProj);
	    	tbsProjcfm1.setProjSn(tbsProj.getSn());
	    	tbsProjcfm1.setBdf2Dept(eaa.getBdf2Dept());
	    	tbsProjcfm1.setBdf2User(eaa.getBdf2User());
	    	tbsProjcfm1.setMeetingdate(meetingdate);
	    	tbsProjcfm1.setMeetingloc(tbsProjcfm0.getLocation());
	    	tbsProjcfm1.setPeriodCfm(tbsProj.getPeriodCfm());
	    	Calendar calendar = Calendar.getInstance();
	    	tbsProjcfm1.setBdate(meetingdate);
	    	calendar.setTime(meetingdate);
	    	calendar.add(Calendar.MONTH, tbsProj.getPeriodCfm());
	    	Date date =  calendar.getTime();
	    	tbsProjcfm1.setEdate(date);
	    	tbsProjcfm1.setVfaloc(tbsProjchangeMajcont.getNewfaloc());
	    	tbsProjcfm1.setVnfaloc(tbsProjchangeMajcont.getNewnfaloc());
	    	tbsProjcfm1.setVotloc(tbsProjchangeMajcont.getNewotloc());
	    	tbsProjcfm1.setVtotloc(tbsProjchangeMajcont.getNewtotloc());
	    	tbsProjcfm1.setLoantype(tbsProj.getLoantype());
	    	tbsProjcfm1.setRepay(tbsProj.getRepay());
	    	tbsProjcfm1.setRepayinper(tbsProj.getRepayinper());
	    	tbsProjcfm1.setLoanmem(tbsProj.getLoanmem());
	    	tbsProjcfm1.setRepaymem(tbsProj.getRepaymem());
	    	tbsProjcfm1.setGatrate(tbsProj.getGatrate());
	    	tbsProjcfm1.setQtfy(tbsProj.getQtfy());
	    	tbsProjcfm1.setPsfy(tbsProj.getPsfy());
	    	tbsProjcfm1.setGatmem(tbsProj.getGatmem());
	    	tbsProjcfm1.setMemo(tbsProj.getMemo());
	    	tbsProjcfm1.setRiskavoid(tbsProj.getRiskavoid());
	    	tbsProjcfm1.setArolename(tbsProj.getBdf2User_A().getCname());
	    	tbsProjcfm1.setBrolename(tbsProj.getBdf2User_B().getCname());
	    	tbsProjcfm1.setTimestampInput(now);
	    	tbsProjcfm1.setTimestampUpdate(now);
	    	tbsProjcfm1.setBy3(piId.toString());
	    	session.save(tbsProjcfm1);
	    	Integer projcfm1Id = tbsProjcfm1.getId();
	    	processClient.saveProcessVariable(piId, "cfm1r2Id", projcfm1Id);
		/*String sqlProcedure = "call p_cfm1(1," + businessId + ","
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
		processClient.saveProcessVariable(piId, "cfm1r2Id", projcfm1Id);*/
		
	    } else {
	    String sql = "update tbs_projcfm0 set valid=1 where del=0 and id= "+ cfm0Id;
		SQLQuery sqlquery = session.createSQLQuery(sql);
		sqlquery.executeUpdate();
		// call procedure and get SN created
		String sqlProcedure = "call p_cfm2(1," + projid + "," + aRoleId + ")";
		SQLQuery query = session.createSQLQuery(sqlProcedure);
		projcfm2_sn = query.uniqueResult().toString();
		// get cfm2 ID
		String sqlProjcfm2ID = "select ID from tbs_projcfm2 where SN ='"
			+ projcfm2_sn + "'";
		SQLQuery queryProjcfm2ID = session.createSQLQuery(sqlProjcfm2ID);
		String projcfm2Id = queryProjcfm2ID.uniqueResult().toString();
		// update process instance id into cfm2
		String sqlCfm2Update = "update tbs_projcfm2 set BY3 = '" + piId.toString() + "'" +
			" , tobe = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + projid + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2)" +
			" , actbe = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + projid + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2 and outcome <> '未知')" +
			" , agree = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + projid + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2 and outcome = '同意')" +
			" , opoose = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + projid + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2 and outcome = '反对')" +
			" , debarb = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + projid + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2 and outcome = '回避')" +
			" , nobe = (select count(*) FROM tbs.tbs_proj_opinion where proj_id = " + projid + " and CFM0_ID = "+ cfm0Id + " and cfmtype = 2 and outcome = '缺席')" +
			" where ID = " + projcfm2Id;
		SQLQuery queryCfm2Update = session.createSQLQuery(sqlCfm2Update);
		queryCfm2Update.executeUpdate();

		// save cfm1Id into process variables
		processClient.saveProcessVariable(piId, "cfm1r2Id", projcfm2Id);
	    }
	}catch(Exception e){
		System.out.println(e);
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
