package org.tbs.uflo.dao.undwrt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProjundwrt;
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
public class ActUndwrt implements ActionHandler {
	
	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
	
	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		String docid=processInstance.getBusinessId();  //businessId需要view绑定的
		if (StringUtils.isNotEmpty(docid)){
			Session session = context.getSession();
						
			//====全部审批通过,更新psid和valid===
			TbsProjundwrt undwrt=(TbsProjundwrt)session.get(TbsProjundwrt.class, Integer.valueOf(docid) ); 
			int projid=undwrt.getTbsProj().getId();
			int psid = undwrt.getTbsProj().getTbsBasPs().getId();
			if (psid == 15){
				String sql_a="call p_hisstatus("+projid+",28,15)";
				SQLQuery sqlquery_a=session.createSQLQuery(sql_a);
				sqlquery_a.executeUpdate();
			}else if (psid == 20 ){
				String sql_a="call p_hisstatus("+projid+",28,20)";
				SQLQuery sqlquery_a=session.createSQLQuery(sql_a);
				sqlquery_a.executeUpdate();
			}
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fNow = df.format(now);
			String sql="update tbs_projundwrt set valid=1,validdate='"+fNow+"' where del = 0 and id="+docid+" and proj_id = "+projid+" ";
			SQLQuery sqlquery=session.createSQLQuery(sql);
			sqlquery.executeUpdate();
			
			//===发送全部审批通过的消息===
    		String promoter=processInstance.getPromoter(); //获取promoter
			String projname = undwrt.getTbsProj().getProjName();
			DefaultUser receiver = (DefaultUser)session.get(DefaultUser.class,promoter );  //获得消息的接收人
			DefaultUser sender = (DefaultUser)session.get(DefaultUser.class,"SystemSender" );  //获得消息的发送人，默认为“系统发信人”
			Collection<IUser> receivers = new ArrayList<IUser>();
			receivers.add(receiver);
			MessagePacket Msgpkt= new MessagePacket();
			Msgpkt.setTitle("【承保审批】已全部通过！");
			Msgpkt.setContent("您发送的【承保审批】\n【项目名称："+projname+"】\n已经全部通过审批！\n单据已生效！\n已自动生成用印申请单！");
			Msgpkt.setTo(receivers);	
			Msgpkt.setSender(sender);	
			SendMsg.send(Msgpkt);
		
			//====生成用印申请
			String sql_uid="select id from bdf2_user where username_='"+promoter+"'";
			SQLQuery query_uid=session.createSQLQuery(sql_uid);		
			String str_uid = query_uid.uniqueResult().toString();
			int uid=Integer.parseInt(str_uid);
    		String sql_adminsign="CALL P_ADMINSIGN(1,"+projid+","+uid+")";
    		SQLQuery query_adminsign=session.createSQLQuery(sql_adminsign);
    		query_adminsign.executeUpdate();
			
			}
	}

}
