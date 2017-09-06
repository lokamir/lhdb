package org.tbs.uflo.dao.change;

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
import org.tbs.entity.TbsProjchangeMajcont;
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
public class ActChangeMajcont implements ActionHandler {

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
			TbsProjchangeMajcont tpcm = (TbsProjchangeMajcont) session.get(TbsProjchangeMajcont.class, Integer.valueOf(docid));
			String projname = tpcm.getTbsProj().getProjName();
			String projid = Integer.toString(tpcm.getTbsProj().getId());
			String cusid = Integer.toString(tpcm.getTbsProj().getTbsCustomer().getId());
			String docsn = tpcm.getSn();
			tpcm.setValid(1);
			session.update(tpcm);
			session.flush();
			
			//===发送全部审批通过的消息===
			String promoter=processInstance.getPromoter(); //获取promoter
			DefaultUser receiver = (DefaultUser)session.get(DefaultUser.class,promoter );  //获得消息的接收人
			DefaultUser roleb = (DefaultUser)session.get(DefaultUser.class, tpcm.getTbsProj().getBdf2User_B().getUsername());
			DefaultUser sender = (DefaultUser)session.get(DefaultUser.class,"SystemSender" );  //获得消息的发送人，默认为“系统发信人”
			Collection<IUser> receivers = new ArrayList<IUser>();
			receivers.add(receiver);
			receivers.add(roleb);
			MessagePacket Msgpkt= new MessagePacket();
			Msgpkt.setTitle("【项目三要素变更】审批已全部通过！");
			Msgpkt.setContent("【项目三要素变更】\n【项目名称："+projname+"】\n已经全部通过审批！\n" +
					 			"日期：" + today);
			Msgpkt.setTo(receivers);	
			Msgpkt.setSender(sender);	
			SendMsg.send(Msgpkt);	
			
			//更新金额
			String sql_hisloc = "call p_hisloc(6" + "," + projid + "," + cusid + ",'" + docsn + "')";
			SQLQuery sqlquery_hisloc = session.createSQLQuery(sql_hisloc);
			sqlquery_hisloc.executeUpdate();
		}
	}
		
}
