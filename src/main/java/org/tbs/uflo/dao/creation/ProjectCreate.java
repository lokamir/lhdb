package org.tbs.uflo.dao.creation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tbs.entity.TbsProj;

import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.Expose;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.client.service.TaskClient;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskOpinion;

@Component
public class ProjectCreate extends HibernateDao {
    @Autowired
    @Qualifier(ProcessClient.BEAN_ID)
    private ProcessClient processClient;

    @Autowired
    @Qualifier(TaskClient.BEAN_ID)
    private TaskClient taskClient;

    @Autowired
    @Qualifier(InternalMessageSender.BEAN_ID)
    private InternalMessageSender SendMsg;

    // =========================WorkFlow========================//
    @Expose
    public String startProjCreateInstance(Map<String, Object> params) throws Exception {
	String result = "";
	if (params != null) {
	    TbsProj tbsProj = (TbsProj) params.get("entity");
	    int id = tbsProj.getId();
	    StartProcessInfo info = new StartProcessInfo();
	    // 单据id
	    info.setBusinessId(Integer.toString(id));
	    // 发起人
	    info.setPromoter(ContextHolder.getLoginUser().getUsername());
	    // 开始流程就直接往下走，而不是停留在开始节点等待发起人完成开始节点
	    info.setCompleteStartTask(true);
	    // 对流程实例打个标记，以备后续使用
	    info.setTag("projCreate");
	    // store variables into process
	    Map<String, Object> variables = new HashMap<String, Object>();
	    // variables for parallel approve
		variables.put("projSn", tbsProj.getSn());
		variables.put("projName", tbsProj.getTbsCustomer().getName());
	    info.setVariables(variables);
	    
	    ProcessInstance pi = processClient.startProcessByName("projCreate",
		    info);
	    Long processInstanceId = pi.getId();
	    result = "提交审批成功！";

	    Session session = this.getSessionFactory().openSession();
	    // save processInstanceId into by3
	    try {
	    	tbsProj.setBy3(processInstanceId.toString());
	    	session.update(tbsProj);
	    } finally {
		session.flush();
		session.close();
	    }

	    session = this.getSessionFactory().openSession();
	    // update proj psid (status)
	    try {
		String sql = "call p_hisstatus(" + id + ",2,1)";
		SQLQuery sqlquery = session.createSQLQuery(sql);
		sqlquery.executeUpdate();

	    } finally {
		session.flush();
		session.close();
	    }
	} else {
	    result = "此单据已发起审批，请另外选择！";
	}
	return result;
    }

    // 处理提交的process请求
    @Expose
    @Transactional
    public String apprSubmit(Map<String, Object> param) throws Exception {
	// prepare properties
	String taskId = (String) param.get("taskId");
	String businessId = (String) param.get("businessId");
	String outcome = (String) param.get("outcome");
	String comment = (String) param.get("comment");
	String psid = (String) param.get("psid");
	
	if (comment == null) {
	    comment = "";
	}

	String opinion = outcome + ":" + comment;
	// 获取审批意见
	TaskOpinion taskOpinion = new TaskOpinion(opinion);
	// 开始审批，审批时一定要先开始任务，然后是下一步完成任务 //taskClient.saveTaskAppointor(arg0,arg1,
	// arg2) //指定下节点任务人 10 class13min
	taskClient.start(Long.valueOf(taskId));
	Session session = this.getSessionFactory().openSession();
	// get prepared for send messages
	Task uflotask = (Task) session.get(Task.class, Long.valueOf(taskId));
	// update tbs_proj data
	TbsProj tbsProj = (TbsProj) session.get(TbsProj.class,
		Integer.valueOf(businessId));

	// base on psid & outcome case, choose different path for flow
	String flowPath = getFlowPath(Integer.valueOf(psid), outcome);
	// base on psid & outcome case, call different procedure in DB
	String sqlProcedure = getSqlProcedure(Integer.valueOf(psid), outcome,
		Integer.valueOf(businessId), uflotask.getNodeName());
	
	/** send msg to Promoter part ------------------------------- start */
	// get receivers
	Collection<IUser> receivers = getReceivers(session,Long.valueOf(taskId),Integer.valueOf(psid));
	// get sender
	DefaultUser sender = (DefaultUser) session.get(DefaultUser.class,"SystemSender");
	// get message title
	String msgTitle = getMsgTitle(Integer.valueOf(psid), outcome, uflotask.getNodeName());
	// get message contents
	String msgContent = getMsgContent(Integer.valueOf(psid), outcome, 
		comment, uflotask.getNodeName(), tbsProj.getProjName());
	// send messages to participants
	sendMsg(receivers, sender, msgTitle, msgContent);
	/** send msg to Promoter part ----------------------------------- end */

	try {
	    // only call the procedure when sqlProcedure string is not null
	    if (!sqlProcedure.isEmpty()) {
		// do procedures in database when needed
		SQLQuery sqlquery = session.createSQLQuery(sqlProcedure);
		sqlquery.executeUpdate();
	    }
	} finally {
	    session.flush();
	    session.close();
	    // 在中间可以加入路径名称“通过”等字样，流程图里面的路径需要写明。complete只能放在这里才可以确保后续的路程运行顺利
	    if (flowPath.isEmpty()) {
		taskClient.complete(Long.valueOf(taskId), taskOpinion);
	    } else {
		taskClient.complete(Long.valueOf(taskId), flowPath, taskOpinion);
	    }
	}

	return "提交成功！";
    }

    /**
     * base on psid, generate different path name for flow to choose 1 path's
     * case, no need to do anything, just return a null value is enough
     * */
    protected String getFlowPath(int psid, String outcome) {
	String flowPath = new String();
	
	// 2 destination case
	if (psid == 29 || psid == 35 || psid == 3 || psid ==2) { //简化立项审批添加psid==2
	    if (outcome.equals("通过")) {
		flowPath = "通过";
	    } else {
		flowPath = "驳回";
	    }
	}

	return flowPath;
    }

    /**
     * base on psid, generate different procedure sql for calling
     * */
    protected String getSqlProcedure(int psid, String outcome, int businessId, String nodeName) {
	String sqlProcedure = new String();

	if (psid == 29) {//2017年7月简化项目流程，直接分配AB角
	    /*if (outcome.equals("通过")) {
		// proj state from 29:"新建项目审批中" to 2:"项目已分配AB角"
		sqlProcedure = "call p_hisstatus(" + businessId + ",2,29)";
	    } else {
		// proj state from 29:"新建项目审批中" to 30:"新建项目驳回"
		sqlProcedure = "call p_hisstatus(" + businessId + ",30,29)";
	    }*/
	} else if (psid == 30) {
	    // proj state from 30:"新建项目驳回" to 2:"项目已分配AB角"
	    sqlProcedure = "call p_hisstatus(" + businessId + ",2,30)";
	} else if (psid == 2) {
		if (outcome.equals("通过")) {
	    // proj state from 2:"项目已分配AB角" to 35:"立项审批单确认"
	    sqlProcedure = "call p_hisstatus(" + businessId + ",35,2)";
		}else {
			// proj state from 22:"新建项目审批中" to 30:"新建项目驳回"
			sqlProcedure = "call p_hisstatus(" + businessId + ",30,2)";
		    }
	} else if (psid == 35) {
	    if (outcome.equals("通过")) {
		// proj state from 35:"立项审批单确认" to 3:"立项申请中"
		sqlProcedure = "call p_hisstatus(" + businessId + ",3,35)";
	    } else {
		// proj state from 35:"立项审批单确认" to 2:"项目已分配AB角"
		sqlProcedure = "call p_hisstatus(" + businessId + ",2,35)";
	    }
	} else if (psid == 3) {
	    // only the last node will change the psid
	    if (outcome.equals("通过") && nodeName.equals("分管业务总经理")) {
		// proj state from 3:"立项申请中" to 5:"立项申请通过"
		sqlProcedure = "call p_hisstatus(" + businessId + ",5,3)";
	    } else if (outcome.equals("驳回")) {
		// proj state from 2:"项目已分配AB角" to 4:"立项申请驳回"
		sqlProcedure = "call p_hisstatus(" + businessId + ",4,3)";
	    }
	} else if (psid == 4) {
	    // proj state from 4:"立项申请驳回" to 3:"立项申请中"
	    sqlProcedure = "call p_hisstatus(" + businessId + ",3,4)";
	}

	return sqlProcedure;
    }

    /**
     * base on psid, set different receivers
     * */
    protected Collection<IUser> getReceivers(Session session, Long taskId, int psid) {
	Collection<IUser> receivers = new ArrayList<IUser>();
	// get prepared for send messages
	Task uflotask = (Task) session.get(Task.class, Long.valueOf(taskId));
	ProcessInstance processInstance = (ProcessInstance) session.get(ProcessInstance.class, uflotask.getProcessInstanceId());
	// get receiver
	if (psid == 1 || psid == 29 || psid == 30) {
	    // before AB role were assigned, receiver is workflow promoter
	    DefaultUser receiver = (DefaultUser) session.get(DefaultUser.class, processInstance.getPromoter());
	    receivers.add(receiver);
	} else if (psid == 2 || psid == 35 || psid == 3 || psid == 4) {
	    // after AB role were assigned, receiver is role A
	    String businessId = processInstance.getBusinessId();
	    // get A role user
	    TbsProj tbsProj = (TbsProj) session.get(TbsProj.class,Integer.valueOf(businessId));
	    DefaultUser receiver = (DefaultUser) session.get(DefaultUser.class, tbsProj.getBdf2User_A().getUsername());
	    receivers.add(receiver);
	}
	
	return receivers;
    }

    /**
     * base on psid, get different message title
     * */
    protected String getMsgTitle(int psid, String outcome, String nodeName) {
	String msgTitle = new String();
	if (psid == 29) {
	    // base on outcome result, send different message contents
	    if (outcome.equals("通过")) {
		msgTitle = "【项目受理】审批已通过！";
	    } else {
		msgTitle = "【项目受理】审批被驳回！";
	    }
	} else if (psid == 30) {
	    msgTitle = "【项目受理】单据已完成修改！";
	} else if (psid == 2) {
	    msgTitle = "【A角信息已补全】";
	} else if (psid == 35) {
	    // base on outcome result, send different message contents
	    if (outcome.equals("通过")) {
		msgTitle = "【项目受理审批_立项审批】已被确认！";
	    } else {
		msgTitle = "【项目受理审批_立项审批】已被退回！";
	    }
	} else if (psid == 3) {
	 // base on outcome result, send different message contents
	    if (outcome.equals("通过")) {
		msgTitle = "【项目受理审批_立项审批】在" + nodeName + "环节通过！";
	    } else {
		msgTitle = "【项目受理审批_立项审批】在" + nodeName + "环节驳回！";
	    }
	} else if (psid == 4) {
	    msgTitle = "【项目受理审批_立项审批】单据已完成修改！";
	}

	return msgTitle;
    }

    /**
     * base on psid, get different message contents
     * */
    protected String getMsgContent(int psid, String outcome, String comment,
	    String nodeName, String projName) {
	String msgContent = new String();
	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String today = dateFormat.format(now);
	
	if (psid == 29) {
	    // base on outcome result, send different message contents
	    if (outcome.equals("通过")) {
		msgContent = "您发送的【项目受理审批】\n【项目名称："+projName+"】\n已经通过【"+nodeName+"】审批！\n"+"审批意见:"+comment+"\n日期："+today;
	    } else {
		msgContent = "您发送的【项目受理审批】\n【项目名称："+projName+"】\n已经被驳回，驳回节点为：【"+nodeName+"】\n"+"审批意见:"+comment+"\n日期："+today;
	    }
	} else if (psid == 30) {
	    msgContent = "您发送的【项目受理审批】\n【项目名称："+projName+"】\n已经通过【"+nodeName+"】审批！\n"+"审批意见:"+comment+"\n日期："+today;
	} else if (psid == 2) {
	    msgContent = "您发送的【项目受理审批】\n【项目名称："+projName+"】\n已经通过【"+nodeName+"】审批！\n"+"审批意见:"+comment+"\n日期："+today;;
	} else if (psid == 35) {
	    // base on outcome result, send different message contents
	    if (outcome.equals("通过")) {
		msgContent = "您发送的【项目受理审批_立项审批】\n【项目名称："+projName+"】\n已经通过【"+nodeName+"】审批！\n"+"审批意见:"+comment+"\n日期："+today;
	    } else {
		msgContent = "您发送的【项目受理审批_立项审批】\n【项目名称："+projName+"】\n已经被驳回，驳回节点为：【"+nodeName+"】\n"+"审批意见:"+comment+"\n日期："+today;
	    }
	} else if (psid == 3) {
	 // base on outcome result, send different message contents
	    if (outcome.equals("通过")) {
		msgContent = "您发送的【项目受理审批_立项审批】\n【项目名称："+projName+"】\n已经通过【"+nodeName+"】审批！\n"+"审批意见:"+comment+"\n"+today;
	    } else {
		msgContent = "您发送的【项目受理审批_立项审批】\n【项目名称："+projName+"】\n已经被驳回，驳回节点为：【"+nodeName+"】\n"+"审批意见:"+comment+"\n"+today;
	    }
	} else if (psid == 4) {
	    msgContent = "您发送的【项目受理审批_立项审批】\n【项目名称："+projName+"】\n已经通过【"+nodeName+"】审批！\n"+"审批意见:"+comment+"\n"+today;
	}

	return msgContent;
    }

    /**
     * simplified bdf2 send message function
     * */
    public void sendMsg(Collection<IUser> receivers, DefaultUser sender,
	    String msgTitle, String msgContent) {
	// ===发送消息===
	MessagePacket Msgpkt = new MessagePacket();
	Msgpkt.setTitle(msgTitle);
	Msgpkt.setContent(msgContent);
	Msgpkt.setTo(receivers);
	Msgpkt.setSender(sender);
	SendMsg.send(Msgpkt);
    }
}

