package org.tbs.dao.funs;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.Expose;
@Component
public class tempAjax extends HibernateDao {
		@Expose
		public void ajaxActionSql(String sql) throws Exception {
			Session session = this.getSessionFactory().openSession();
			try {
				SQLQuery sqlquery=session.createSQLQuery(sql);
				sqlquery.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.flush();
				session.close();
			}
		}
}
