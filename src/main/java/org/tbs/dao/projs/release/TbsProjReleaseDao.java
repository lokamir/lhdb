package org.tbs.dao.projs.release;

/**
 * 6.1 项目解保
 * 
 * */

import java.util.Collection;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjRelease;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;


@Component
public class TbsProjReleaseDao extends HibernateDao {
   
    
	@DataProvider  // mainview 用
    public void loadByUndwrtid(Page<TbsProj> page, Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjRelease.class.getName()
					+ " where del = 0 and undwrt_id= " + id + " order by id desc";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		} else {
				String hql = "from " + TbsProjRelease.class.getName()
						+ " where id is null";
				this.pagingQuery(page, hql, "select count(*) " + hql);
		}
    }
	
	@DataProvider  // detailview 用
    public Collection<TbsProjRelease> loadById(Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjRelease.class.getName()
					+ " where del = 0 and id= " + id + " order by id desc";
			return this.query(hql);
		} else {
				String hql = "from " + TbsProjRelease.class.getName()
						+ " where id is null";
				return this.query(hql);
		}
    }
	
	
	@DataResolver
	public void saveAll(Collection<TbsProjRelease> tbsProjReleases) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProjRelease tbsProjRelease : tbsProjReleases) {
				EntityState state = EntityUtils.getState(tbsProjRelease);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsProjRelease);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsProjRelease);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsProjRelease.setDel(true);
					session.update(tbsProjRelease);
				}
			}			
		} finally {
			session.flush();
			session.close();
		}
	}
	
	@Expose
	public void delById(Integer id) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			String sql="update tbs_proj_release set del=1 where id="+id;
			SQLQuery query=session.createSQLQuery(sql);
			query.executeUpdate();
			}finally {
			session.flush();
			session.close();
		}
	}
	
	
	
}
