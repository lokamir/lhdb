package org.tbs.dao.funs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsFunApprc;
import org.tbs.entity.UfloProcessInstance;
import org.tbs.entity.UfloTask;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.entity.EntityUtils;

import com.bstek.uflo.utils.EnvironmentUtils;

@Component
public class GetHisTasks extends HibernateDao {

	
	/*@Autowired
	@Qualifier(HistoryService.BEAN_ID)
	private HistoryService historyService;*/
	
	 // uflo的原生处理历史记录的方法
   /* @DataProvider
    public Collection<HistoryTask> getHistoryTask(String processInstanceId)
	    throws Exception {
	HistoryTaskQuery htq = historyService.createHistoryTaskQuery();
	htq.rootProcessInstanceId(Long.valueOf(processInstanceId));
	htq.addOrderDesc("endDate");
	return htq.list();
    }	*/
	
	/* // used for doc-view
    @DataProvider
    public Collection<TbsFunApprc> loadHisTasks(Map<String, Object> param)
	    throws Exception {
	String hql = "";
	if (param != null) {
	    String processname = (String) param.get("processName");
	    Integer docId = (Integer) param.get("docId");
	    hql = "from " + UfloProcess.class.getName() + " where name='"
		    + processname + "'";
	    UfloProcess ups = (UfloProcess) this.query(hql).toArray()[0];
	    Long processid = ups.getId();
	    hql = "from " + TbsFunApprc.class.getName()
		    + " where business_id_ = " + docId + " and process_id_="
		    + processid;
	} else {
	    hql = "from " + TbsFunApprc.class.getName() + " where id is null";
	}
	return this.query(hql);
    }*/

    @DataProvider
    public Collection<TbsFunApprc> getHisTasksByPI(String processInstanceId)
	    throws Exception {
	String hql = "";
	hql = "from " + TbsFunApprc.class.getName() + " where pid='"
		+ processInstanceId + "' order by id desc";
	return this.query(hql);
    }
    
    @DataProvider
    public Collection<TbsFunApprc> getHisTasksByProcessId(String processId)
    		throws Exception {
    	String hql = "";
    	hql = "from " + TbsFunApprc.class.getName() + " where processId='"
    			+ processId + "' order by id desc";
    	return this.query(hql);
    }
    @DataProvider
    public Collection<TbsFunApprc> getHisTasksByRootId(String rootId)
    		throws Exception {
    	String hql = "";
    	hql = "from " + TbsFunApprc.class.getName() + " where rootpId='"
    			+ rootId + "' order by id desc";
    	return this.query(hql);
    }
    
    @DataProvider
    public Collection<TbsFunApprc> getHisTasksByID(String Id)
	    throws Exception {
	String hql = "";
	hql = "from " + TbsFunApprc.class.getName() + " where id='"
		+ Id + "' order by id desc";
	return this.query(hql);
    }
    
    @DataProvider
    public Collection<UfloProcessInstance> getPiByUser()
    		throws Exception {
    	String hql = "";

    	Session session = this.getSession();
    	hql = "from " + UfloProcessInstance.class.getName() + " where promoter_='"
    			+ EnvironmentUtils.getEnvironment().getLoginUser() ;
    	List<UfloProcessInstance> results = new ArrayList<>();
    	Collection<UfloProcessInstance> uflopi =  this.query(hql);
    	for(UfloProcessInstance pi :uflopi ){
    		UfloProcessInstance targetData = EntityUtils.toEntity(pi);
    		String sql1 = "SELECT assignee_ FROM tbs.uflo_task where process_id_ =" +pi.getProcessId()+" and node_name_ = '" +pi.getCurrentNode()+"'";
			SQLQuery sqlquery1 = session.createSQLQuery(sql1);
			List<String> assignee = sqlquery1.list();
			String sql2 = "SELECT description_ FROM tbs.uflo_task where process_id_ =" +pi.getProcessId();
			SQLQuery sqlquery2 = session.createSQLQuery(sql2);
			List<String> uflotask = sqlquery2.list();
    		EntityUtils.setValue(targetData, "assignee", assignee.toString());
    		EntityUtils.setValue(targetData, "description", uflotask.get(0));
			results.add(targetData);
    	}
    	return results;
    }
   
}

