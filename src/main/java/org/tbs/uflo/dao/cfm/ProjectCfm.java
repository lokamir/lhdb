package org.tbs.uflo.dao.cfm;

import java.text.DateFormat;
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
import org.tbs.entity.TbsProj;
import org.tbs.uflo.dao.creation.ProjectCreate;

import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.dorado.annotation.Expose;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.StartProcessInfo;

@Component
public class ProjectCfm extends ProjectCreate {
    
	@Autowired
    @Qualifier(ProcessClient.BEAN_ID)
    private ProcessClient processClient;

    private int pass = 0;
    private int confirm = 0;//三位领导必须签批（0没有完成，1完成签批）
    private int cmpt = 0; // 表示通过

    @Expose
    public String startProjCfmInstance(Map<String, Object> params)
	    throws Exception {
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
	    info.setTag("projCfm");
	    // store variables into process
	    Map<String, Object> variables = new HashMap<String, Object>();
	    // variables for parallel approve
		variables.put("projSn", tbsProj.getSn());
	    variables.put("cmpt", false);
	    variables.put("passCount", 0);
	    variables.put("approverCount", 0);
	    variables.put("cfm0Id", 0);
	    variables.put("cfmFlag", 0);
	    variables.put("cfm1r2Id", 0);
	    info.setVariables(variables);

	    ProcessInstance pi = processClient.startProcessByName("projCfm",
		    info);

	    Long processInstanceId = pi.getId();
	    result = "提交审批成功！";

	    Session session = this.getSessionFactory().openSession();
	    // save processInstanceId into by2
	    try {
		// temporally save into by2
		tbsProj.setBy2(processInstanceId.toString());
		session.update(tbsProj);
	    } finally {
		session.flush();
		session.close();
	    }

	    session = this.getSessionFactory().openSession();
	    // update proj psid (status)
	    try {
		String sql = "call p_hisstatus(" + id + ",6,5)";
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

    @Expose
    public String apprSubmit(Map<String, Object> param) throws Exception {

	return super.apprSubmit(param);
    }

    @Expose
    public String cfm0ApprSubmit(Map<String, Object> param) throws Exception {
	String psid = (String) param.get("psid");
	String processInstanceId = (String) param.get("processInstanceId");
	String outcome = (String) param.get("outcome");
	String businessId = (String) param.get("businessId");
	String cfm0Id = (String)processClient.getProcessVariable("cfm0Id", Long.valueOf(processInstanceId));
	if(psid.equals("37")) {
	    // store process varibles
	    String cfmType = new String();
	    if (outcome.equals("会议")) {
		processClient.saveProcessVariable(Long.valueOf(processInstanceId),
			"cfmFlag", 1);
		cfmType = "1";
	    } else if(outcome.equals("签批")) {
		processClient.saveProcessVariable(Long.valueOf(processInstanceId),
			"cfmFlag", 2);
		cfmType = "2";
		
		// reset counter sign flags
		this.cmpt = 0;
		processClient.saveProcessVariable(Long.valueOf(processInstanceId),"cmpt", 0);
	    }
	    
	    // prepare for insert datetime
	    Date now = new Date();
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fNow = df.format(now);
	    Session session = this.getSessionFactory().openSession();
	    
	    try {
	    //生成cfm1/2的opinion
	    String sqlInsert = "insert into tbs_proj_opinion(PROJ_ID,CFM0_ID,CFMTYPE,TIMESTAMP_INPUT,UID,TITLE)"
			+ " select " + businessId + ", " + cfm0Id + "," + cfmType + ", '" + fNow + "',"
			+ "cfm0.UID, cfm0.TITLE from (select UID, TITLE from tbs_proj_opinion "
			+ "where PROJ_ID = " + businessId + " and CFM0_ID = "
			+ cfm0Id + " and CFMTYPE = 0 and del = 0) cfm0";
		SQLQuery queryInsert = session.createSQLQuery(sqlInsert);
		queryInsert.executeUpdate();  
		
		// 部门经理直接算‘回避‘
		String sqlUpdate = "update tbs_proj_opinion set outcome = '回避' where CFM0_ID = " + cfm0Id + 
			" and CFMTYPE <> 0 and TITLE = '发起人部门经理'";
		SQLQuery queryUpdate = session.createSQLQuery(sqlUpdate);
		queryUpdate.executeUpdate();
	    } finally {
		session.flush();
		session.close();
	    }
	} else if (psid.equals("22")) {
	    // counter sign case
	    Session session = this.getSessionFactory().openSession();
	    
			try {
				// total approver count except those ones who choose "avoid"
				String sqlTotal = "select count(*) from tbs_proj_opinion where CFM0_ID = "
						+ cfm0Id
						+ " and CFMTYPE = 2 and del = 0 and (outcome <> '回避' ) and (outcome <> '缺席' )";
				SQLQuery queryTotal = session.createSQLQuery(sqlTotal);
				String total = queryTotal.uniqueResult().toString();
				float tt = Float.valueOf(total);

				// total approver count who choose "agree"
				String sqlAgrees = "select count(*) from tbs_proj_opinion where CFM0_ID = "
						+ cfm0Id
						+ " and CFMTYPE = 2 and del = 0 and outcome = '同意'";
				SQLQuery querysqlAgrees = session.createSQLQuery(sqlAgrees);
				String agrees = querysqlAgrees.uniqueResult().toString();
				float ag = Float.valueOf(agrees);
				// total approver count who choose "agree"
				
				
				String sqlDisagrees = "select count(*) from tbs_proj_opinion where CFM0_ID = "
						+ cfm0Id
						+ " and CFMTYPE = 2 and del = 0 and outcome = '反对'";
				SQLQuery queryDisagrees = session.createSQLQuery(sqlDisagrees);
				String disagrees = queryDisagrees.uniqueResult().toString();
				
				String sqlconfirmcount = "select count(*) from tbs_proj_opinion where CFM0_ID = "
						+ cfm0Id
						+ " and CFMTYPE = 2 and del = 0 and title in ('总经理','分管风管总经理','风管经理')";
				SQLQuery queryconfirmcount = session.createSQLQuery(sqlconfirmcount);
				String confirmcount = queryconfirmcount.uniqueResult().toString();
				String sqlconfirm = "select count(*) from tbs_proj_opinion where CFM0_ID = "
						+ cfm0Id
						+ "and outcome <> '未知' and CFMTYPE = 2 and del = 0 and title in ('总经理','分管风管总经理','风管经理')";
				SQLQuery queryconfirm = session.createSQLQuery(sqlconfirm);
				String confirmresult = queryconfirm.uniqueResult().toString();
				Integer cfmre = Integer.valueOf(confirmresult);
				Integer cfmct = Integer.valueOf(confirmcount);
				if(cfmre==cfmct){
					confirm=1;
				}
				
				
				// count how many approvers left
				String sqlTaskAssignees = "select count(*) from uflo_task where PROCESS_INSTANCE_ID_ = "
						+ processInstanceId;
				SQLQuery queryTaskAssignees = session
						.createSQLQuery(sqlTaskAssignees);
				String taskAssignees = queryTaskAssignees.uniqueResult()
						.toString();

				int countTA = Integer.valueOf(taskAssignees);
				float da = Float.valueOf(disagrees);
				double twoThird = 0.59; // 2/3审批通过
				double oneThird = 0.29; // 1/3驳回通过

				if (outcome.equals("同意")) {
					if (ag / tt >= twoThird && confirm == 1) {
						this.pass = 1;
						this.cmpt = 1;
						processClient.saveProcessVariable(
								Long.valueOf(processInstanceId), "cmpt", 1);
					}
				} else if (outcome.equals("反对") && confirm == 1) {
					if (da / tt >= oneThird) {
						this.pass = 0;
						this.cmpt = 1;
						processClient.saveProcessVariable(
								Long.valueOf(processInstanceId), "cmpt", 1);
					}
				} else {
					if (da / tt >= oneThird && confirm == 1) {
						this.pass = 0;
						this.cmpt = 1;
						processClient.saveProcessVariable(
								Long.valueOf(processInstanceId), "cmpt", 1);
					} else if (ag / tt >= twoThird && confirm == 1) {
						this.pass = 1;
						this.cmpt = 1;
						processClient.saveProcessVariable(
								Long.valueOf(processInstanceId), "cmpt", 1);
					} else if (countTA == 1) {
						this.pass = 0;
						this.cmpt = 1;
						processClient.saveProcessVariable(
								Long.valueOf(processInstanceId), "cmpt", 1);
					}
				}
	    } finally {
		session.flush();
		session.close();
	    }
	    
	    
	}
	
	
	
	return super.apprSubmit(param);
    }

    /**
     * base on psid, generate different path name for flow to choose 1 path's
     * case, no need to do anything, just return a null value is enough
     * */
    @Override
    protected String getFlowPath(int psid, String outcome) {
	String flowPath = new String();

	// 2 destination case
	if (psid == 6 ||psid == 21 || psid == 26) {
	    if (outcome.equals("通过")) {
		flowPath = "通过";
	    } else {
		flowPath = "驳回";
	    }
	} else if (psid == 37) {
	    if (outcome.equals("会议")) {
		flowPath = "会议";
	    } else {
		flowPath = "签批";
	    }
		} else if (psid == 8) {
			if (outcome.equals("通过")) {
				flowPath = "通过";
			} else if (outcome.equals("确认修改")) {
				flowPath = "确认修改";
			} else if (outcome.equals("驳回")) {
				flowPath = "驳回";
			}
	} else if (psid == 22 && this.cmpt == 1) {
	    // psid = 22 and counter sign ends
	    if (this.pass == 1) {
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
    @Override
    protected String getSqlProcedure(int psid, String outcome, int businessId,
	    String nodeName) {
	String sqlProcedure = new String();

	if (psid == 6) {
	    // only the last node will change the psid
	    if (outcome.equals("通过") && nodeName.equals("分管风险领导")) {
		sqlProcedure = "call p_hisstatus(" + businessId + ",37,6)";  // gnr cfm0
	    } else if (outcome.equals("驳回")) {
		sqlProcedure = "call p_hisstatus(" + businessId + ",36,6)";
	    }
	} else if (psid == 36) {
	    sqlProcedure = "call p_hisstatus(" + businessId + ",6,36)";
	} else if (psid == 37) {
	    if (outcome.equals("会议")) {
		sqlProcedure = "call p_hisstatus(" + businessId + ",21,37)";  //gnr cfm1
	    } else if (outcome.equals("签批")) {
		sqlProcedure = "call p_hisstatus(" + businessId + ",22,37)";  //gnr cfm2
	    }
	    
	} else if (psid == 21) {
	    if (outcome.equals("通过")){
		sqlProcedure = "call p_hisstatus(" + businessId + ",8,21)";
	    } else if (outcome.equals("驳回")) {
		sqlProcedure = "call p_hisstatus(" + businessId + ",36,21)";
	    }
	    
	} else if (psid == 22) {
	    if(this.cmpt == 1) {
		if (this.pass == 1) {
		    sqlProcedure = "call p_hisstatus(" + businessId + ",8,22)";
		} else {
		    sqlProcedure = "call p_hisstatus(" + businessId + ",36,22)";
		}
	    }
	}else if (psid == 8) {
		if (outcome.equals("通过") && nodeName.equals("主任委员审批")){
			sqlProcedure = "call p_hisstatus(" + businessId + ",26,8)";
		} else if (outcome.equals("驳回")) {
			sqlProcedure = "call p_hisstatus(" + businessId + ",36,8)";
		}
	} else if (psid == 26) {
	    if (outcome.equals("通过") && nodeName.equals("决策人审批")){
		sqlProcedure = "call p_hisstatus(" + businessId + ",10,26)";
	    } else if (outcome.equals("驳回")) {
		sqlProcedure = "call p_hisstatus(" + businessId + ",36,26)";
	    }
	    
	}

	return sqlProcedure;
    }

    /**
     * base on psid, set different receivers
     * */
    @Override
    protected Collection<IUser> getReceivers(Session session, Long taskId,
	    int psid) {
	Collection<IUser> receivers = new ArrayList<IUser>();
	// get prepared for send messages
	Task uflotask = (Task) session.get(Task.class, Long.valueOf(taskId));
	ProcessInstance processInstance = (ProcessInstance) session.get(
		ProcessInstance.class, uflotask.getProcessInstanceId());
	// get receiver
	DefaultUser receiver = (DefaultUser) session.get(DefaultUser.class,
		processInstance.getPromoter());
	receivers.add(receiver);

	return receivers;
    }

    /**
     * base on psid, get different message title
     * */
    @Override
    protected String getMsgTitle(int psid, String outcome, String nodeName) {
	String msgTitle = new String();
	if (psid == 6) {
	    // base on outcome result, send different message contents
	    if (outcome.equals("通过")) {
		msgTitle = "【决策审批】审批已通过！";
	    } else {
		msgTitle = "【决策审批】审批被驳回！";
	    }
	} else if (psid == 36) {
	    msgTitle = "【决策审批】审批已修正！";
	} else if (psid == 37) {
	    msgTitle = "【决策审批】上会类型确认！";
	} else if (psid == 21) {
	    msgTitle = "【决策审批】会议决议已产生！";
	} else if (psid == 8 && nodeName == "评审会秘书录入会议决议单") {
	    msgTitle = "【决策审批】决议审批开始！";
	} else if (psid == 8 && nodeName == "主任委员审批") {
	    msgTitle = "【决策审批】决议审批开始！";
	} else if (psid == 26) {
	    if (outcome.equals("通过")) {
		msgTitle = "【决策审批】" + nodeName + "通过！";
	    } else {
		msgTitle = "【决策审批】" + nodeName + "被驳回！";
	    }
	}

	return msgTitle;
    }

    /**
     * base on psid, get different message contents
     * */
    @Override
    protected String getMsgContent(int psid, String outcome, String comment,
	    String nodeName, String projName) {
	String msgContent = new String();
	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String today = dateFormat.format(now);
	
	if (psid == 6) {
	    // base on outcome result, send different message contents
	    if (outcome.equals("通过")) {
		msgContent = "您发送的【决策审批】\n【项目名称："+projName+"】\n已经通过【"+nodeName+"】审批！\n"+"审批意见:"+comment+"\n日期："+today;
	    } else {
		msgContent = "您发送的【决策审批】\n【项目名称："+projName+"】\n已经被驳回，驳回节点为：【"+nodeName+"】\n"+"审批意见:"+comment+"\n日期："+today;
	    }
	} else if (psid == 36) {
	    msgContent = "您发送的【决策审批】\n【项目名称："+projName+"】\n已经通过【"+nodeName+"】\n"+"审批意见:"+comment+"\n日期："+today;
	} else if (psid == 37) {
	    msgContent = "您发送的【决策审批】\n【项目名称："+projName+"】\n已将上会类型确定为：【"+outcome+"】\n"+"审批意见:"+comment+"\n日期："+today;
	} else if (psid == 21) {
	    msgContent = "您发送的【决策审批】\n【项目名称："+projName+"】\n已将上会类型确定为：【"+outcome+"】\n"+"审批意见:"+comment+"\n日期："+today;
	} else if (psid == 8 && nodeName == "评审会秘书录入会议决议单") {
	    msgContent = "您发送的【决策审批】\n【项目名称："+projName+"】\n已经通过会议/会签。\n已开始决议审批";
	} else if (psid == 8 && nodeName == "主任委员审批") {
	    msgContent = "您发送的【决策审批】\n【项目名称："+projName+"】\n已经通过会议/会签。\n已开始主任委员审批";
	} else if (psid == 26) {
	    if (outcome.equals("通过")) {
		msgContent = "您发送的【决策审批】\n【项目名称："+projName+"】\n已经通过【"+nodeName+"】审批！\n"+"审批意见:"+comment+"\n日期："+today;
	    } else {
		msgContent = "您发送的【决策审批】\n【项目名称："+projName+"】\n已经通过【"+nodeName+"】审批！\n"+"审批意见:"+comment+"\n日期："+today;
	    }
	}

	return msgContent;
    }

}
