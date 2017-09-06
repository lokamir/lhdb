package org.tbs.dao.projs.undwrt;

/**
 * 4.3.1 承保审批单
 * 
 * */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.dao.funs.GetSysInfo;
import org.tbs.entity.TbsCustomer;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjundwrt;
import org.tbs.entity.TbsProjundwrtBank;
import org.tbs.entity.TbsProjundwrtBizvt;
import org.tbs.entity.TbsProjundwrtCfmar;
import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.message.MessagePacket;
import com.bstek.bdf2.core.message.impl.InternalMessageSender;
import com.bstek.bdf2.core.model.DefaultUser;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.client.service.TaskClient;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskOpinion;

@Component
public class TbsProjundwrtDao extends HibernateDao {
	
		
	@Autowired
	@Qualifier(ProcessClient.BEAN_ID)
	private ProcessClient processClient;

	@Autowired
	@Qualifier(TaskClient.BEAN_ID)
	private TaskClient taskClient;

	@Autowired
	@Qualifier(InternalMessageSender.BEAN_ID)
	private InternalMessageSender SendMsg;
	
	@DataProvider
	public void loadAll(Page<TbsProjundwrt> page, Map<String, Object> params)
			throws Exception {
		if (null != params) {
			String whereCase = "";
			String valid = (String) params.get("valid");
			String appfalocMin = (String) params.get("appfalocMin");
			String appfalocMax = (String) params.get("appfalocMax");
			String appnfalocMin = (String) params.get("appnfalocMin");
			String appnfalocMax = (String) params.get("appnfalocMax");
			String appotlocMin = (String) params.get("appotlocMin");
			String appotlocMax = (String) params.get("appotlocMax");
			if (StringHelper.isNotEmpty(appfalocMin)) {
				whereCase += " AND appfaloc >=" + appfalocMin;
			}
			if (StringHelper.isNotEmpty(appfalocMax)) {
				whereCase += " AND appfaloc <=" + appfalocMax;
			}
			if (StringHelper.isNotEmpty(appnfalocMin)) {
				whereCase += " AND appnfaloc >=" + appnfalocMin;
			}
			if (StringHelper.isNotEmpty(appnfalocMax)) {
				whereCase += " AND appnfaloc <=" + appnfalocMax;
			}
			if (StringHelper.isNotEmpty(appotlocMin)) {
				whereCase += " AND appotloc >=" + appotlocMin;
			}
			if (StringHelper.isNotEmpty(appotlocMax)) {
				whereCase += " AND appotloc <=" + appotlocMax;
			}
			if (StringHelper.isNotEmpty(valid)) {
				if (valid.equals("1") || valid.equals("0")) {
					whereCase += " AND valid =" + valid;
				}
			}
			if (params.get("tbsCustomer") instanceof TbsCustomer) {
				TbsCustomer tbsCustomer = (TbsCustomer) params
						.get("tbsCustomer");
				if (tbsCustomer != null && tbsCustomer.getId() != 0) {
					whereCase += " AND tbsCustomer = " + tbsCustomer.getId();
				}
			}
			if (params.get("proj_Id") instanceof TbsProj) {
				TbsProj tbsProjvalue = (TbsProj) params.get("proj_Id");
				if (tbsProjvalue != null && tbsProjvalue.getId() != 0) {
					whereCase += " AND tbsProj = " + tbsProjvalue.getId();
				}
			}
			String hql = "from " + TbsProjundwrt.class.getName()
					+ " where Del=0" + whereCase + " order by id desc";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		} else {
			String hql = "from " + TbsProjundwrt.class.getName()
					+ " where id is null";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		}
	}

	@DataProvider
	public Collection<TbsProjundwrtBank> loadBank(Integer Mid) throws Exception {
		if (Mid != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("mid", Mid);
			String hql = "from " + TbsProjundwrtBank.class.getName()
					+ " where PROJUNDWRT_ID= :mid";
			return this.query(hql, param);
		} else {
			return null;
		}
	}

	@DataProvider
	public Collection<TbsProjundwrtBizvt> loadBizvt(Integer Mid)
			throws Exception {
		if (Mid != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("mid", Mid);
			String hql = "from " + TbsProjundwrtBizvt.class.getName()
					+ " where Del=0 and PROJUNDWRT_ID= :mid";
			return this.query(hql, param);
		} else {
			return null;
		}
	}

	@DataProvider
	public Collection<TbsProjundwrtCfmar> loadCfmar(Integer Mid)
			throws Exception {
		if (Mid != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("mid", Mid);
			String hql = "from " + TbsProjundwrtCfmar.class.getName()
					+ " where Del=0 and PROJUNDWRT_ID= :mid";
			return this.query(hql, param);
		} else {
			return null;
		}
	}

	@DataProvider  // proj与undwrt表联动
    public void loadByProjid(Page<TbsProjundwrt> page, Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjundwrt.class.getName()
					+ " where del = 0 and proj_id= " + id + " order by id desc";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		} else {
				String hql = "from " + TbsProjundwrt.class.getName()
						+ " where id is null";
				this.pagingQuery(page, hql, "select count(*) " + hql);
		}
    }
	
	@DataProvider  // 单条数据 Autoform 显示，给undwrt的审批界面用 businessId传入
	public Collection<TbsProjundwrt> loadsingle(String undwrtId) throws Exception {
		return this.query("from " + TbsProjundwrt.class.getName() + " where id="+ undwrtId);
	}
	
	@DataResolver
	public void save(Collection<TbsProjundwrt> tbsProjundwrts) throws Exception {
		Session session = this.getSessionFactory().openSession();
		Date now = new Date();
		try {
			for (TbsProjundwrt tbsProjundwrt : tbsProjundwrts) {
				EntityState state = EntityUtils.getState(tbsProjundwrt);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsProjundwrt);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsProjundwrt.setDel(true);
					session.update(tbsProjundwrt);
				}
				if (state.equals(EntityState.MODIFIED)) {
					tbsProjundwrt.setTimestampUpdate(now);
					session.update(tbsProjundwrt);
				}
				Collection<TbsProjundwrtBank> bankchilds = tbsProjundwrt
						.getTbsProjundwrtBankSet();
				if (bankchilds != null) {
					for (TbsProjundwrtBank bankchild : bankchilds) {
						bankchild.setTbsProjundwrt(tbsProjundwrt);
					}
					saveAllBank(bankchilds);
				}
				Collection<TbsProjundwrtBizvt> bizvtchilds = tbsProjundwrt
						.getTbsProjundwrtBizvtSet();
				if (bizvtchilds != null) {
					for (TbsProjundwrtBizvt bizvtchild : bizvtchilds) {
						bizvtchild.setTbsProjundwrt(tbsProjundwrt);
					}
					saveAllBizvt(bizvtchilds);
				}
				Collection<TbsProjundwrtCfmar> cfmarchilds = tbsProjundwrt
						.getTbsProjundwrtCfmarSet();
				if (cfmarchilds != null) {
					for (TbsProjundwrtCfmar cfmarchild : cfmarchilds) {
						cfmarchild.setTbsProjundwrt(tbsProjundwrt);
					}
					saveAllCfmar(cfmarchilds);
				}

			}
		} finally {
			session.flush();
			session.close();
		}
	}

	@DataResolver
	public void saveAllBank(Collection<TbsProjundwrtBank> tbsProjundwrtBanks)
			throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProjundwrtBank tbsProjundwrtBank : tbsProjundwrtBanks) {
				EntityState state = EntityUtils.getState(tbsProjundwrtBank);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsProjundwrtBank);
				}
				if (state.equals(EntityState.DELETED)) {
					session.delete(tbsProjundwrtBank);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsProjundwrtBank);
				}
			}
		} finally {
			session.flush();
			session.close();
		}
	}

	@DataResolver
	public void saveAllBizvt(Collection<TbsProjundwrtBizvt> tbsProjundwrtBizvts)
			throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProjundwrtBizvt tbsProjundwrtBizvt : tbsProjundwrtBizvts) {
				EntityState state = EntityUtils.getState(tbsProjundwrtBizvt);

				if (state.equals(EntityState.NEW)) {
					/*System.out.println(state + ":" + "new");
					System.out.println("susscesIns");*/
					session.save(tbsProjundwrtBizvt);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsProjundwrtBizvt.setDel(true);
					/*System.out.println(state + ":" + "deleted");
					System.out.println("susscesDel");
					*/session.update(tbsProjundwrtBizvt);
				}
				if (state.equals(EntityState.MODIFIED)) {
					/*System.out.println(state + ":" + "modified");
					System.out.println("susscesUpd");
					*/session.update(tbsProjundwrtBizvt);
				}
			}
		} finally {
			session.flush();
			session.close();
		}
	}

	@DataResolver
	public void saveAllCfmar(Collection<TbsProjundwrtCfmar> tbsProjundwrtCfmars)
			throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProjundwrtCfmar tbsProjundwrtCfmar : tbsProjundwrtCfmars) {
				EntityState state = EntityUtils.getState(tbsProjundwrtCfmar);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsProjundwrtCfmar);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsProjundwrtCfmar.setDel(true);
					session.update(tbsProjundwrtCfmar);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsProjundwrtCfmar);
				}
			}
		} finally {
			session.flush();
			session.close();
		}
	}
	
	
	
	// ====================================WorkFlow==========================================//
		@Expose
		public String StartWF(Map<String, Object> params) throws Exception {
			String result = "";
			if (null != params) {
				Session session = this.getSessionFactory().openSession();
				String docid = Integer.toString((int) params.get("docid"));
				TbsProjundwrt undwrt = (TbsProjundwrt) session.get(TbsProjundwrt.class, Integer.valueOf(docid));
				StartProcessInfo info = new StartProcessInfo();
				info.setBusinessId(docid); // 单据id
				info.setPromoter(ContextHolder.getLoginUser().getUsername()); // 发起人
				info.setCompleteStartTask(true); // 在开始节点，流程就直接往下走，而不是停留在开始节点等待发起人完成开始节点，必须这样做
				info.setTag("undwrt"); // 对流程实例打个标记，以备后续使用
				Map<String,Object> variables = new HashMap<String,Object>();
				variables.put("reject",0);
				variables.put("projSn", undwrt.getProjSn());
				info.setVariables(variables);
				ProcessInstance pi = processClient.startProcessByName("undwrt", info);
				Long processInstanceId = pi.getId();
				result = "提交审批成功！";
				try {
					int projid = undwrt.getTbsProj().getId();
					int psid = undwrt.getTbsProj().getTbsBasPs().getId();
					if (psid == 23) { // 原始状态发起审批
						String sql = "call p_hisstatus(" + projid + ",15,23)";
						SQLQuery sqlquery = session.createSQLQuery(sql);
						sqlquery.executeUpdate();
					}else if (psid == 38) { //  再次承保
						String sql = "call p_hisstatus(" + projid + ",20,38)";
						SQLQuery sqlquery = session.createSQLQuery(sql);
						sqlquery.executeUpdate();
					} 
					undwrt.setPid(processInstanceId.toString());  //把processInstanceId放入单据的PID内
					session.update(undwrt);
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
		public void ApprWF(Map<String, Object> param) throws Exception {
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String today = dateFormat.format(now);
			String docid = (String) param.get("docid");  //获取信息
			String projid =  (String) param.get("projid"); 
			String taskid = (String) param.get("taskid");
			String outcome = (String) param.get("outcome");
			String opinion = (String) param.get("opinion");
			String opinions = (String) outcome + "-- 审批意见："+  opinion;
			TaskOpinion taskOpinion = new TaskOpinion(opinions); //获取结束
			taskClient.start(Long.valueOf(taskid));  //开始审批，审批时一定要先开始任务，然后是下一步完成任务 //taskClient.saveTaskAppointor(arg0, arg1, arg2)  //指定下节点任务人 10 class13min
			Map<String,Object> variables = new HashMap<String,Object>();
			Session session = this.getSessionFactory().openSession();
			try {
				// ===获取流程内的一些值===
				TbsProjundwrt undwrt = (TbsProjundwrt) session.get(TbsProjundwrt.class,Integer.valueOf(docid));
				String projname = undwrt.getTbsProj().getProjName();
				String by1 = undwrt.getBy1();
				Task uflotask = (Task) session.get(Task.class, Long.valueOf(taskid));
				String nodename = uflotask.getNodeName();
				Long piid = uflotask.getProcessInstanceId(); // 获取processinstanceId
				ProcessInstance pi = (ProcessInstance) session.get(ProcessInstance.class, piid);
				String promoter = pi.getPromoter(); // 获取promoter
				
				//===消息处理===
				DefaultUser receiver = (DefaultUser)session.get(DefaultUser.class,promoter );  //获得消息的接收人
				DefaultUser sender = (DefaultUser)session.get(DefaultUser.class,"SystemSender" );  //获得消息的发送人，默认为“系统发信人”
				Collection<IUser> receivers = new ArrayList<IUser>();
				receivers.add(receiver);
				MessagePacket Msgpkt= new MessagePacket();
				Msgpkt.setTo(receivers);	
				Msgpkt.setSender(sender);
				
				//===审批处理===
				if (outcome.equals("通过")){			
					Msgpkt.setTitle("【承保审批】审批已通过！");
					Msgpkt.setContent("您发送的【承保审批】\n【项目名称："+projname+"】\n已经通过【"+nodename+"】审批！\n"+today);
					SendMsg.send(Msgpkt);
				}else if (outcome.equals("驳回")) { 
					if (by1 == null){
						String sql="call p_hisstatus("+projid+",19,15)";
						SQLQuery sqlquery=session.createSQLQuery(sql);
						sqlquery.executeUpdate();
					}else {  //再次承保
						String sql="call p_hisstatus("+projid+",9,20)";
						SQLQuery sqlquery=session.createSQLQuery(sql);
						sqlquery.executeUpdate();
					}
					Integer i = Integer.parseInt(processClient.getProcessVariable("reject", pi).toString());
					i=1;
					variables.put("reject", i);				
					Msgpkt.setTitle("【承保审批】审批已驳回！");
					Msgpkt.setContent("您发送的【承保审批】\n【项目名称："+projname+"】\n已经被驳回,驳回节点为【"+nodename+"】\n"+today);
					SendMsg.send(Msgpkt);				
				}else if(outcome.equals("修改确认")){
					if (by1 == null){
						String sql="call p_hisstatus("+projid+",15,19)";
						SQLQuery sqlquery=session.createSQLQuery(sql);
						sqlquery.executeUpdate();
					}else {//再次承保
						String sql="call p_hisstatus("+projid+",20,9)";
						SQLQuery sqlquery=session.createSQLQuery(sql);
						sqlquery.executeUpdate();
					}
					Integer i = Integer.parseInt(processClient.getProcessVariable("reject", pi).toString());
					i=0;
					variables.put("reject", i);		
					Msgpkt.setTitle("【承保审批】单据已完成修改！");
					Msgpkt.setContent("您发送的【承保审批】\n【项目名称："+projname+"】\n已经被【"+nodename+"】\n"+today);
					SendMsg.send(Msgpkt);
				}
			} finally {
				session.flush();
				session.close();
				taskClient.complete(Long.valueOf(taskid), outcome, variables, taskOpinion); // 在中间可以加入路径名称“通过”等字样，流程图里面的路径需要写明。complete只能放在这里才可以确保后续的路程运行顺利
			}
		}

		//========================生成再次承保单========================//
		@Expose
		public String uflon(Map<String, Object> params) throws Exception{
			if (null != params) {
				GetSysInfo gsi = new GetSysInfo();
				Session session = this.getSessionFactory().openSession();
	    		int projid = (int) params.get("projid");
	    		String uname = (String) params.get("loginUser");
	    		String docsn = (String) params.get("docsn");
	    		int uid = gsi.getUserID(uname, session);
	    		try{
	    			TbsProj proj = (TbsProj) session.get(TbsProj.class,Integer.valueOf(projid));
	    			int psid = proj.getTbsBasPs().getId();
	    			String sql="call p_undwrtN(1,"+projid+","+uid+",'"+docsn+"')";
	    			SQLQuery query=session.createSQLQuery(sql);
	    			String result=query.uniqueResult().toString();
	    			if (psid == 18) {
	    				String sqlN = "call p_hisstatus("+projid+",38,18)";
						SQLQuery sqlqueryN=session.createSQLQuery(sqlN);
						sqlqueryN.executeUpdate();
	    			}else if (psid == 16) {
	    				String sqlN = "call p_hisstatus("+projid+",38,16)";
						SQLQuery sqlqueryN=session.createSQLQuery(sqlN);
						sqlqueryN.executeUpdate();
	    			}else if (psid == 99) {
	    				String sqlN = "call p_hisstatus("+projid+",38,99)";
						SQLQuery sqlqueryN=session.createSQLQuery(sqlN);
						sqlqueryN.executeUpdate();
	    			}
					return result;
	    		}finally{
					session.flush();
					session.close();
				}
	    	}else{
	            String result="未获得项目单据！";
	            return result;
	            }
			}

}