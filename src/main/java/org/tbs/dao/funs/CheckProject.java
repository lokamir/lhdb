package org.tbs.dao.funs;

import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.Expose;

@Component
public class CheckProject extends HibernateDao {
	@Expose
	public void actionUpdate(Map<String, Object> param) throws Exception {
		Integer projid = (Integer)param.get("projid");
		Session session = this.getSessionFactory().openSession();
		try {
			String sql="update tbs.tbs_proj set ps_id = 18 where PS_ID = 99 AND ID=" + projid;
			SQLQuery sqlquery=session.createSQLQuery(sql);
			sqlquery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
