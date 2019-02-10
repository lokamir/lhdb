package org.tbs.uflo.dao.change;

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
import org.tbs.entity.TbsProjchangeMajcont;

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

@Component("ActChangeMajcontCfmInfmAB")
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
	Integer cfm1r2Id = (Integer)processClient.getProcessVariable("cfm1r2Id", processInstance);
	Integer projId = (Integer)processClient.getProcessVariable("projId", processInstance);
	int cfmFlag = (int) processClient.getProcessVariable("cfmFlag", processInstance);
	TbsProjchangeMajcont tbsProjchangeMajcont = (TbsProjchangeMajcont) session.get(TbsProjchangeMajcont.class,Integer.valueOf(businessId));
	String projname = tbsProjchangeMajcont.getTbsProj().getProjName();
	String projid = Integer.toString(tbsProjchangeMajcont.getTbsProj().getId());
	String cusid = Integer.toString(tbsProjchangeMajcont.getTbsProj().getTbsCustomer().getId());
	String docsn = tbsProjchangeMajcont.getSn();
	TbsProj tbsProj = tbsProjchangeMajcont.getTbsProj();
	if (cfmFlag == 1) {
	    TbsProjcfm1 cfm1 = (TbsProjcfm1) session.get(TbsProjcfm1.class, Integer.valueOf(cfm1r2Id));
		String docsn1 = cfm1.getSn();
		//update valid in cfm1
	    String sql="update tbs_projcfm1 set valid=1 where ID = "+cfm1r2Id;
	    SQLQuery sqlquery=session.createSQLQuery(sql);
	    sqlquery.executeUpdate();
	    //Gnrt hisloc & proj.vloc
	    String sql1="call p_hisloc (1,"+projId+","+cusid+",'"+docsn1+"')";
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
	    String sql2="call p_hisloc (1,"+projId+","+cusid+",'"+docsn2+"')";
	    SQLQuery sqlquery2=session.createSQLQuery(sql2);
	    sqlquery2.executeUpdate();
	}
	if(tbsProjchangeMajcont.getOverlimit() == true){
		String sqlOverlimitUpdate="update tbs_proj set OVERLIMIT = 1 where ID = " + projid;
	    SQLQuery sqlqueryOverlimitUpdate=session.createSQLQuery(sqlOverlimitUpdate);
	    sqlqueryOverlimitUpdate.executeUpdate();
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
	Msgpkt.setTitle("【项目三要素变更】审批已全部通过！");
	Msgpkt.setContent("【项目三要素变更】\n【项目名称："+projname+"】\n已经全部通过审批！\n" +
			 			"日期：" + today);
	Msgpkt.setTo(receivers);
	Msgpkt.setSender(sender);
	SendMsg.send(Msgpkt);

	//2019年1月20日修复数据库存储过程<call p_hisloc  6>bug,为其添加一条红冲（本次上会议决审批后的金额）
	//存储过程<call p_hisloc  6>再完成一次变更审议后，再也不会增加红冲了，是个BUG，在这里修复得话改动最小
	String sql_count_hisloc_enbale = " select count(*) "
			+ "from v_cfmall"
			+ " WHERE proj_id=:projid and cus_id=:cusid " 
			+ " and not exists (select * from tbs_proj_hisloc "
			+ " where proj_id=:projid and cus_id=:cusid and oper=61) ";
	SQLQuery sqlquery_count_hisloc_enbale = session.createSQLQuery(sql_count_hisloc_enbale);
	int c = Integer.valueOf(sqlquery_count_hisloc_enbale.setString("projid", projid).setString("cusid", cusid).uniqueResult().toString());
	if(c==0){
		String sql_insert_hisloc = " insert into tbs_proj_hisloc    " +
				"(PROJ_ID,PROJ_SN,CUS_NAME,CUS_ID,DOC_SN,DOC_CAT,FALOC,NFALOC,OTLOC,TOTLOC,PERIOD_CFM,BDATE,EDATE,bizvar_id,biztype_id,loc,oper)" +
				"select PROJ_ID,PROJ_SN,CUS_NAME,CUS_ID,concat('【变更】',DOC_SN), " +
				" DOC_CAT,0-FALOC,0-NFALOC,0-OTLOC,0-TOTLOC,PERIOD_CFM,BDATE,EDATE,if(bizvar_id='',null,bizvar_id)," +
				" if(biztype_id='',null,biztype_id),if(loc='',null,loc),'61' " +
				" from v_cfmall WHERE by3 =  :processId ";
		SQLQuery sqlquery_insert_hisloc = session.createSQLQuery(sql_insert_hisloc);
		sqlquery_insert_hisloc.setLong("processId",processInstance.getId()).executeUpdate();
	}
	
	//更新金额
	String sql_hisloc = "call p_hisloc(6" + "," + projid + "," + cusid + ",'" + docsn + "')";
	SQLQuery sqlquery_hisloc = session.createSQLQuery(sql_hisloc);
	sqlquery_hisloc.executeUpdate();
	

    }

}
