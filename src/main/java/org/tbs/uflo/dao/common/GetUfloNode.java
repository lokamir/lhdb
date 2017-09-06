/**
 * @author Hank
 * 
 * 获取流程内的nodename
 * 根据nodename对审批节点做特殊处理
 * 
 **/
package org.tbs.uflo.dao.common;

import java.util.Map;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.Expose;
import com.bstek.uflo.model.task.Task;

@Component
public class GetUfloNode extends HibernateDao {
	
	@Expose
	public String GetNodeName(Map<String, Object> params) throws Exception{
		Session session = this.getSessionFactory().openSession();
		String taskid = (String) params.get("taskid"); 
		try{
			Task uflotask = (Task) session.get(Task.class, Long.valueOf(taskid));
			String nodename = uflotask.getNodeName();
			return nodename;
			}finally{
				session.clear();
				session.close();
			}
		}
}


