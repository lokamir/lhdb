package org.tbs.uflo.dao.compsry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProjcompsry;

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
public class ActCompsry1 implements ActionHandler {

	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
	
	@SuppressWarnings("unchecked")
	
	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		String docid=processInstance.getBusinessId();
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = dateFormat.format(now);
		if (StringUtils.isNotEmpty(docid)){
			Session session = context.getSession();
			
			TbsProjcompsry tprls = (TbsProjcompsry) session.get(TbsProjcompsry.class, Integer.valueOf(docid));
			String projname = tprls.getTbsProj().getProjName();
					
			String promoter=processInstance.getPromoter(); //获取promoter
			DefaultUser receiver = (DefaultUser)session.get(DefaultUser.class,promoter );  //获得消息的接收人
			Collection<IUser> receivers = new ArrayList<IUser>();
			// 获取业务审核委员会成员
			List<String> userList = new ArrayList<String>();
			String sql_fgid = "select account from tbs_approver where title like '%业务审核委员会%'";
			SQLQuery sqlquery_fgid = session.createSQLQuery(sql_fgid);
			if (!sqlquery_fgid.list().isEmpty()) {
				userList = sqlquery_fgid.list();
			}
			DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,
					"SystemSender"); // 获得消息的发送人，默认为“系统发信人”
			receivers.add(receiver);
			for (String temp : userList) {
				DefaultUser receiverX = (DefaultUser) session.get(
						DefaultUser.class, temp);
				receivers.add(receiverX);
			}
			MessagePacket Msgpkt= new MessagePacket();
			Msgpkt.setTitle("【项目名称："+projname+"】【代偿申请】召开业务审核委员会会议！");
			Msgpkt.setContent("【代偿申请】\n【项目名称："+projname+"】\n召开业务审核委员会会议！\n" +
					 			"日期：" + today );
			Msgpkt.setTo(receivers);	
			Msgpkt.setSender(sender);	
			SendMsg.send(Msgpkt);		
		}
	}

}
