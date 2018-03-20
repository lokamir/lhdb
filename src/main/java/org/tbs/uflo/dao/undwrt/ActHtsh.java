package org.tbs.uflo.dao.undwrt;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProjHtsh;

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
public class ActHtsh implements ActionHandler {

	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
	
	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		String docid=processInstance.getBusinessId();  //businessId需要view绑定的
		if (StringUtils.isNotEmpty(docid)){
			Session session = context.getSession();
			
			//====全部审批通过,更新psid和valid===
			TbsProjHtsh htsh=(TbsProjHtsh)session.get(TbsProjHtsh.class, Integer.valueOf(docid) ); 
			int projid=htsh.getTbsProj().getId();
			//String sql_a="call p_hisstatus("+projid+",13,11)";
			//SQLQuery sqlquery_a=session.createSQLQuery(sql_a);
			//sqlquery_a.executeUpdate();
			String sql="update tbs_proj_htsh set valid=1 where id="+docid+"";
			SQLQuery sqlquery=session.createSQLQuery(sql);
			sqlquery.executeUpdate();
			
			//===发送全部审批通过的消息===
			String projname = htsh.getTbsProj().getProjName();
			String promoter=processInstance.getPromoter(); //获取promoter
			DefaultUser receiver = (DefaultUser)session.get(DefaultUser.class,promoter );  //获得消息的接收人
			DefaultUser sender = (DefaultUser)session.get(DefaultUser.class,"SystemSender" );  //获得消息的发送人，默认为“系统发信人”
			Collection<IUser> receivers = new ArrayList<IUser>();
			receivers.add(receiver);
			MessagePacket Msgpkt= new MessagePacket();
			Msgpkt.setTitle("【合同审批】审批已全部通过！");
			Msgpkt.setContent("您发送的【合同审批】\n【项目名称："+projname+"】\n已经全部通过审批！\n单据已生效！");
			Msgpkt.setTo(receivers);	
			Msgpkt.setSender(sender);	
			SendMsg.send(Msgpkt);
			
		}
	}

}
