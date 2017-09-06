package org.tbs.dao.projs.change;

/**
 * 8.2 项目经理变更
 * 
 * */

import java.util.Collection;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProjchangeRole;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;


@Component
public class TbsProjchangeRoleDao extends HibernateDao {
   
    
	@DataProvider  // mainview 用
    public Collection<TbsProjchangeRole> loadByProjid(Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjchangeRole.class.getName()
					+ " where del = 0 and proj_id= " + id + " order by id desc";
			return this.query(hql);
		} else {
				String hql = "from " + TbsProjchangeRole.class.getName()
						+ " where id is null";
				return this.query(hql);
		}
    }
	
	@DataProvider  // detailview 用
    public Collection<TbsProjchangeRole> loadById(Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjchangeRole.class.getName()
					+ " where del = 0 and id= " + id + " order by id desc";
			return this.query(hql);
		} else {
				String hql = "from " + TbsProjchangeRole.class.getName()
						+ " where id is null";
				return this.query(hql);
		}
    }
	
	
	@DataResolver
	public void saveAll(Collection<TbsProjchangeRole> tbsProjchangeRoles) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProjchangeRole tbsProjchangeRole : tbsProjchangeRoles) {
				EntityState state = EntityUtils.getState(tbsProjchangeRole);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsProjchangeRole);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsProjchangeRole);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsProjchangeRole.setDel(true);
					session.update(tbsProjchangeRole);
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
			String sql="update tbs_projchange_role set del=1 where id="+id;
			SQLQuery query=session.createSQLQuery(sql);
			query.executeUpdate();
			}finally {
			session.flush();
			session.close();
		}
	}
	
	
	
}
