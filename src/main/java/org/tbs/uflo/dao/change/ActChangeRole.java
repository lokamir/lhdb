package org.tbs.uflo.dao.change;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProjchangeRole;
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
public class ActChangeRole implements ActionHandler {

	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
	
	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		String docid=processInstance.getBusinessId();
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = dateFormat.format(now);
		if (StringUtils.isNotEmpty(docid)){
			Session session = context.getSession();
			
			//====全部审批通过
			TbsProjchangeRole tpcr = (TbsProjchangeRole) session.get(TbsProjchangeRole.class, Integer.valueOf(docid));
			String projname = tpcr.getTbsProj().getProjName();
			String anew_cname = tpcr.getBdf2User_AN().getCname();
			String bnew_cname = tpcr.getBdf2User_BN().getCname();
			tpcr.setValid(1);
			session.update(tpcr);
					
			//===发送全部审批通过的消息===
			String promoter=processInstance.getPromoter(); //获取promoter
			DefaultUser receiver = (DefaultUser)session.get(DefaultUser.class,promoter );  //获得消息的接收人
			DefaultUser anew_username = (DefaultUser)session.get(DefaultUser.class, tpcr.getBdf2User_AN().getUsername() );
			DefaultUser bnew_username = (DefaultUser)session.get(DefaultUser.class, tpcr.getBdf2User_BN().getUsername() );
			DefaultUser aold_username = (DefaultUser)session.get(DefaultUser.class, tpcr.getBdf2User_AO().getUsername() );
			DefaultUser bold_username = (DefaultUser)session.get(DefaultUser.class, tpcr.getBdf2User_BO().getUsername() );
			DefaultUser sender = (DefaultUser)session.get(DefaultUser.class,"SystemSender" );  //获得消息的发送人，默认为“系统发信人”
			Collection<IUser> receivers = new ArrayList<IUser>();
			receivers.add(receiver);
			receivers.add(anew_username);
			receivers.add(bnew_username);
			receivers.add(aold_username);
			receivers.add(bold_username);
			MessagePacket Msgpkt= new MessagePacket();
			Msgpkt.setTitle("【项目经理变更】审批已全部通过！");
			Msgpkt.setContent("您发送的【项目经理变更】\n【项目名称："+projname+"】\n已经全部通过审批！\n" +
					 			"新的项目经理A,B分别为【"+anew_cname+","+bnew_cname+"】\n"+
								"日期：" + today);
			Msgpkt.setTo(receivers);	
			Msgpkt.setSender(sender);	
			SendMsg.send(Msgpkt);	
		}
	}
		
}
