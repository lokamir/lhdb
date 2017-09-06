package org.tbs.dao.funs;

import java.util.Collection;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsFunApprc;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;

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
    
   
}

