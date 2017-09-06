package org.tbs.uflo.dao.undwrt;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
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
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.ActionHandler;
/**
 * @author Hank
 * 动作的实现类
 * 
 */

@Component
public class ActProjCgg implements ActionHandler {

	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
	
	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		String docid=processInstance.getBusinessId();  //businessId需要view绑定的
		if (StringUtils.isNotEmpty(docid)){
			Session session = context.getSession();
			
			//====全部审批通过,更新psid和valid===
			TbsProj cgg=(TbsProj)session.get(TbsProj.class, Integer.valueOf(docid) ); 
			int projid=cgg.getId();
			String sql_a="call p_hisstatus("+projid+",23,14)";
			SQLQuery sqlquery_a=session.createSQLQuery(sql_a);
			sqlquery_a.executeUpdate();
			String sqlupdate="update tbs.tbs_proj_cgg set valid=1 where proj_id="+projid;							
			SQLQuery sqlqueryupdate=session.createSQLQuery(sqlupdate);
			sqlqueryupdate.executeUpdate();
			
			//===发送全部审批通过的消息===
			String projname = cgg.getProjName();
			TbsProj tbsproj = (TbsProj)session.get(TbsProj.class,projid );  //前面已经获取了这个projid 
			String arole = processInstance.getPromoter(); //获取A角;
			String brole = tbsproj.getBdf2User_B().getUsername();//获取B角
			DefaultUser receiverA = (DefaultUser)session.get(DefaultUser.class,arole );
			DefaultUser receiverB = (DefaultUser)session.get(DefaultUser.class,brole );
			Collection<IUser> receivers = new ArrayList<IUser>();
			receivers.add(receiverA);
			receivers.add(receiverB);
			DefaultUser sender = (DefaultUser)session.get(DefaultUser.class,"SystemSender" );  //获得消息的发送人，默认为“系统发信人”
			MessagePacket Msgpkt= new MessagePacket();
			Msgpkt.setTitle("【项目与反担保关联】审批已全部通过！");
			Msgpkt.setContent("您发送的【项目与反担保关联】\n【项目名称："+projname+"】\n已经全部通过审批！\n单据已生效！");
			Msgpkt.setTo(receivers);	
			Msgpkt.setSender(sender);	
			SendMsg.send(Msgpkt);
			//====生成承保审批单
			String sql_uid="select id from bdf2_user where username_='"+arole+"'";
			SQLQuery query_uid=session.createSQLQuery(sql_uid);		
			String str_uid = query_uid.uniqueResult().toString();
			int uid=Integer.parseInt(str_uid);
    		String sql_undwrt="CALL P_UNDWRT(1,"+projid+","+uid+")";
    		SQLQuery query_undwrt=session.createSQLQuery(sql_undwrt);
    		query_undwrt.executeUpdate();
		}
	}

}
