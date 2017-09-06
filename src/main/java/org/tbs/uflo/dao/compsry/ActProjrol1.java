package org.tbs.uflo.dao.compsry;

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
import org.tbs.entity.TbsProjrol;

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
public class ActProjrol1 implements ActionHandler {

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
			TbsProjrol tprls = (TbsProjrol) session.get(TbsProjrol.class, Integer.valueOf(docid));
			String projname = tprls.getTbsProj().getProjName();
			int cusid = tprls.getTbsProj().getTbsCustomer().getId();
			int projid = tprls.getTbsProj().getId();
			String cusname = tprls.getTbsProj().getTbsCustomer().getName();
			String arolename = tprls.getTbsProj().getBdf2User_A().getUsername();
			String brolename = tprls.getTbsProj().getBdf2User_B().getUsername();
			
			//更新状态
			String sql="update tbs_projrol set valid=1 where id="+docid+" and proj_id="+projid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			sqlquery.executeUpdate();
			
			String sql_p_compsry = "call p_compsry( " + cusid + " )";
			SQLQuery sqlquery_p_compsry = session.createSQLQuery(sql_p_compsry);
			sqlquery_p_compsry.executeUpdate();
					
			//===发送全部审批通过的消息===
			String promoter=processInstance.getPromoter(); //获取promoter
			DefaultUser receiver = (DefaultUser)session.get(DefaultUser.class,promoter );  //获得消息的接收人
			DefaultUser arole = (DefaultUser)session.get(DefaultUser.class, arolename);
			DefaultUser brole = (DefaultUser)session.get(DefaultUser.class, brolename);
			DefaultUser sender = (DefaultUser)session.get(DefaultUser.class,"SystemSender" );  //获得消息的发送人，默认为“系统发信人”
			Collection<IUser> receivers = new ArrayList<IUser>();
			receivers.add(receiver);
			receivers.add(arole);
			receivers.add(brole);
			MessagePacket Msgpkt= new MessagePacket();
			Msgpkt.setTitle("【追偿申请】审批已全部通过！");
			Msgpkt.setContent("您发送的【追偿申请】\n【项目名称："+projname+"】\n已经全部通过审批！\n" +"【"+cusname+"】代偿余额变动！\n"+
					 			"日期：" + today );
			Msgpkt.setTo(receivers);	
			Msgpkt.setSender(sender);	
			SendMsg.send(Msgpkt);		
		}
	}

}
