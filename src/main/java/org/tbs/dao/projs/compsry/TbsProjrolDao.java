package org.tbs.dao.projs.compsry;

/**
 * 7.4 风险解除（追偿）
 * 
 * */

import java.util.Collection;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjrol;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;


@Component
public class TbsProjrolDao extends HibernateDao {
   
    
	@DataProvider  // mainview 用
    public void loadByUndwrtid(Page<TbsProj> page, Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjrol.class.getName()
					+ " where del = 0 and undwrt_id= " + id + " order by id desc";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		} else {
				String hql = "from " + TbsProjrol.class.getName()
						+ " where id is null";
				this.pagingQuery(page, hql, "select count(*) " + hql);
		}
    }
	
	@DataProvider  // mainview 用（根据请款单获取风险解除表单）
    public void loadByCompsryPayid(Page<TbsProj> page, Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjrol.class.getName()
					+ " where del = 0 and PROJCOMPSRY_PAY_ID= " + id + " order by id desc";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		} else {
				String hql = "from " + TbsProjrol.class.getName()
						+ " where id is null";
				this.pagingQuery(page, hql, "select count(*) " + hql);
		}
    }
	
	@DataProvider  // detailview 用
    public Collection<TbsProjrol> loadById(Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjrol.class.getName()
					+ " where del = 0 and id= " + id + " order by id desc";
			return this.query(hql);
		} else {
				String hql = "from " + TbsProjrol.class.getName()
						+ " where id is null";
				return this.query(hql);
		}
    }
	
	
	@DataResolver
	public void saveAll(Collection<TbsProjrol> tbsProjrols) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProjrol tbsProjrol : tbsProjrols) {
				EntityState state = EntityUtils.getState(tbsProjrol);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsProjrol);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsProjrol);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsProjrol.setDel(true);
					session.update(tbsProjrol);
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
			String sql="update Tbs_projrol set del=1 where id="+id;
			SQLQuery query=session.createSQLQuery(sql);
			query.executeUpdate();
			}finally {
			session.flush();
			session.close();
		}
	}
	
	
	
}
