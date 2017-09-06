package org.tbs.dao.projs.compsry;

/**
 * 6.1 项目解保
 * 
 * */

import java.util.Collection;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjcompsryPay;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;


@Component
public class TbsProjCompsryPayDao extends HibernateDao {
   
    
	@DataProvider  // mainview 用
    public void loadByUndwrtid(Page<TbsProj> page, Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjcompsryPay.class.getName()
					+ " where del = 0 and undwrt_id= " + id + " order by id desc";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		} else {
				String hql = "from " + TbsProjcompsryPay.class.getName()
						+ " where id is null";
				this.pagingQuery(page, hql, "select count(*) " + hql);
		}
    }
	
	@DataProvider  // detailview 用
    public Collection<TbsProjcompsryPay> loadById(Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjcompsryPay.class.getName()
					+ " where del = 0 and id= " + id + " order by id desc";
			return this.query(hql);
		} else {
				String hql = "from " + TbsProjcompsryPay.class.getName()
						+ " where id is null";
				return this.query(hql);
		}
    }
	
	@DataProvider  // 风险解除用mainview（根据项目获取代偿请款单）
	public void loadByProjId(Page<TbsProj> page, Integer id)
			throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjcompsryPay.class.getName()
					+ " where del = 0 and proj_id= " + id + " order by id desc";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		} else {
			String hql = "from " + TbsProjcompsryPay.class.getName()
					+ " where id is null";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		}
	}
	
	@DataProvider  // 代偿请款用mainview（根据代偿审议获取代偿请款单）
	public void loadByCompsryId(Page<TbsProj> page, Integer id)
			throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjcompsryPay.class.getName()
					+ " where del = 0 and projcompsry_id= " + id + " order by id desc";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		} else {
			String hql = "from " + TbsProjcompsryPay.class.getName()
					+ " where id is null";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		}
	}
	@DataResolver
	public void saveAll(Collection<TbsProjcompsryPay> tbsProjcompsryPays) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProjcompsryPay tbsProjcompsryPay : tbsProjcompsryPays) {
				EntityState state = EntityUtils.getState(tbsProjcompsryPay);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsProjcompsryPay);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsProjcompsryPay);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsProjcompsryPay.setDel(true);
					session.update(tbsProjcompsryPay);
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
			String sql="update Tbs_projcompsry_pay set del=1 where id="+id;
			SQLQuery query=session.createSQLQuery(sql);
			query.executeUpdate();
			}finally {
			session.flush();
			session.close();
		}
	}
	
	
	
}
