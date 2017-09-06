/**
 * 定位反担保信息树节点
 * by lokamir
 */

package org.tbs.dao.funs;

import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.dao.bas.TbsBasCggtypDao;
import com.bstek.dorado.annotation.Expose;

@Component
public class GetCggDetails {
	@Resource
	private TbsBasCggtypDao tbsBasCggtypDao;

	@Expose
	public Integer getNodeTreeId(Map<String, Object> params) throws Exception {
		String catname = (String) params.get("catname");
		Session session = tbsBasCggtypDao.getSessionFactory().openSession();
		try {
			String Sql = "select id from tbs.tbs_bas_cggtyp where NAME_='"
					+ catname + "'";
			SQLQuery SqlQuery = session.createSQLQuery(Sql);
			String str_id = SqlQuery.uniqueResult().toString();
			Integer id = Integer.parseInt(str_id);
			return id;
		} finally {
			session.flush();
			session.close();
		}
	}

}
