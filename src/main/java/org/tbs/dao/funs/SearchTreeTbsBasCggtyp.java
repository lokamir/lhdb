/**
 	 * 定位反担保信息树节点
	 * by lokamir
	 */

package org.tbs.dao.funs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.dao.bas.TbsBasCggtypDao;
import com.bstek.dorado.annotation.Expose;

@Component
public class SearchTreeTbsBasCggtyp {
	@Resource
	private TbsBasCggtypDao tbsBasCggtypDao;
	
	@Expose
	public List<Integer> getNodePath(Map<String, Object> params) throws Exception {
		String catname = (String)params.get("catname");
		List<Integer> path = new ArrayList<Integer>();
		Integer id = null;
		Session session = tbsBasCggtypDao.getSessionFactory().openSession();
		try{
			String idSql = "select id from tbs.tbs_bas_cggtyp where NAME_='"+catname+"'";
			SQLQuery idSqlQuery=session.createSQLQuery(idSql);	
			if(idSqlQuery.list().get(0)==null){
				String Sql = "select id from tbs.tbs_bas_cggtyp where NAME_='"+catname+"'";
				SQLQuery SqlQuery=session.createSQLQuery(Sql);	
				String str_id = SqlQuery.uniqueResult().toString();
				id = Integer.parseInt(str_id);
				path.add(id);
			}else{
				id = (Integer)idSqlQuery.list().get(0);
				path.add(id);
				while( id != null){
					path.add(id);
					String pidSql = "select pid from tbs.tbs_bas_cggtyp where id="+id;
					SQLQuery pidSqlQuery=session.createSQLQuery(pidSql);		
					if(pidSqlQuery.list().get(0)==null){
						id=null;
					}else{
						id= (Integer)pidSqlQuery.list().get(0);
					}
				}
			}
		}finally {
			session.flush();
			session.close();
		}
		Collections.reverse(path);
		return path;
	}
	
}
