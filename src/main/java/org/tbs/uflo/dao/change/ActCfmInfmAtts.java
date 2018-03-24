package org.tbs.uflo.dao.change;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProjOpinion;
import org.tbs.entity.TbsProjcfm0;
import org.tbs.entity.TbsProjchangeMajcont;

import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.ActionHandler;

/**
 * @author Bob 动作的实现类
 * 
 */

@Component("ActChangeMajcontCfmInfmAtts")
public class ActCfmInfmAtts extends HibernateDao implements ActionHandler {

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
	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String today = dateFormat.format(now);
	
	TbsProjchangeMajcont tbsProjchangeMajcont = (TbsProjchangeMajcont) session.get(TbsProjchangeMajcont.class,
		Integer.valueOf(businessId));
	int projid = tbsProjchangeMajcont.getTbsProj().getId();
	String projname = tbsProjchangeMajcont.getTbsProj().getProjName();
	// update valid in cfm0
	TbsProjcfm0 tbsprojcfm0 = (TbsProjcfm0) session.get(TbsProjcfm0.class,
			Integer.valueOf(cfm0Id));
	tbsprojcfm0.setValid(true);
	session.update(tbsprojcfm0);
	/*String sql = "update tbs_projcfm0 set valid=1 where del=0 and id= "
		+ cfm0Id;
	SQLQuery sqlquery = session.createSQLQuery(sql);
	sqlquery.executeUpdate();*/

	Collection<IUser> receivers = new ArrayList<IUser>();
	// get receiver
	Collection<TbsProjOpinion> tbsProjOpinions = this.query("from "
		+ TbsProjOpinion.class.getName()
		+ " where tbsProj.id = " + projid + " and tbsProjcfm0.id = "
		+ cfm0Id + " and cfmtype = 0 and del = 0");
	
	for (TbsProjOpinion tbsProjOpinion : tbsProjOpinions) {
	    DefaultUser receiver = (DefaultUser) session.get(DefaultUser.class,
		    tbsProjOpinion.getBdf2User().getUsername());
	    receivers.add(receiver);
	}
	DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,
		"SystemSender");

	MessagePacket Msgpkt = new MessagePacket();
	Msgpkt.setTitle("【" + projname + "】通知上会！");
	Msgpkt.setContent("【项目名称：" + projname + "】准备上会或签批！\n"+"日期："+today);
	Msgpkt.setTo(receivers);
	Msgpkt.setSender(sender);
	SendMsg.send(Msgpkt);
    }

}
