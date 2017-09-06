package org.tbs.dao.projs.periodMgm;

/**
 * 5.3费用收取 5.4费用退回
 * 
 * */

import java.util.Collection;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjundwrt;
import org.tbs.entity.TbsPtyexp;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Component
public class TbsPtyexpDao extends HibernateDao {
	
	@DataProvider
	public void loadAllPtyexpI(Page<TbsPtyexp> page,Integer id) throws Exception {
		String hql = "from " + TbsPtyexp.class.getName()
				+ " where del = 0 and io = 1 and undwrt_id=" + id + "order by id desc";
		this.pagingQuery(page, hql, "select count(*) " + hql);
	}

	@DataProvider
	public void loadAllPtyexpO(Page<TbsPtyexp> page,Integer id) throws Exception {
		String hql = "from " + TbsPtyexp.class.getName()
				+ " where del = 0 and io = 0 and undwrt_id=" + id + "order by id desc";
		this.pagingQuery(page, hql, "select count(*) " + hql);
	}
	
	@DataProvider
	public Collection<TbsPtyexp> loadSinglePtyexp(Integer id) throws Exception {
		return this.query("from " + TbsPtyexp.class.getName() + " where id="
				+ id + " order by id desc");
	}

	

	@DataProvider
	public Collection<TbsPtyexp> loadPtyexp(Map<String, Object> param)
			throws Exception {
		String whereCase = "";
		if (param != null) {
			Integer projid = (Integer) param.get("projid");
			Integer undwrtid = (Integer) param.get("undwrtid");
			Integer io = (Integer) param.get("io");
			Integer valid = (Integer) param.get("valid");
			Integer del = (Integer) param.get("del");
			if (io != null) {
				whereCase += " and io = " + io;
			}
			if (valid != null) {
				whereCase += " and valid = " + valid;
			}
			if (del != null) {
				whereCase += " and del = " + del;
			}
			if (undwrtid != null) {
				whereCase += " and undwrt_id = " + undwrtid;
			}
			if (projid != null) {
				whereCase += " and proj_id = " + projid;
			}
			return this.query("from " + TbsPtyexp.class.getName()
					+ " where 1=1 " + whereCase + " order by id desc");
		} else {
			return null;
		}
	}

	@DataResolver
	public void saveMain(Collection<TbsProj> tbsProjs) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProj tbsProj : tbsProjs) {
				EntityState state = EntityUtils.getState(tbsProj);
				if (state.equals(EntityState.DELETED)) {
					tbsProj.setDel(true);
					session.update(tbsProj);// 把del改为1
				}
				Collection<TbsProjundwrt> tbsProjundwrts = tbsProj
						.getTbsProjundwrtSet();
				if (tbsProjundwrts != null) {
					for (TbsProjundwrt tbsProjundwrt : tbsProjundwrts) {
						tbsProjundwrt.setTbsProj(tbsProj);
					}
					saveTbsundwrt(tbsProjundwrts);
				}
			}
		} finally {
			session.flush();
			session.close();
		}
	}

	@DataResolver
	public void saveTbsundwrt(Collection<TbsProjundwrt> tbsProjundwrts)
			throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProjundwrt tbsProjundwrt : tbsProjundwrts) {
				EntityState state = EntityUtils.getState(tbsProjundwrt);
				if (state.equals(EntityState.DELETED)) {
					tbsProjundwrt.setDel(true);
					session.update(tbsProjundwrt);
				}
				Collection<TbsPtyexp> tbsPtyexps = tbsProjundwrt
						.getTbsPtyexpSet();
				if (tbsPtyexps != null) {
					for (TbsPtyexp tbsPtyexp : tbsPtyexps) {
						tbsPtyexp.setTbsProjundwrt(tbsProjundwrt);
					}
					save(tbsPtyexps);
				}
			}
		} finally {
			session.flush();
			session.close();
		}
	}

	@DataResolver
	public void save(Collection<TbsPtyexp> tbsPtyexps) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsPtyexp tbsPtyexp : tbsPtyexps) {
				EntityState state = EntityUtils.getState(tbsPtyexp);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsPtyexp);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsPtyexp);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsPtyexp.setDel(true);
					session.update(tbsPtyexp);
				}
			}
		} finally {
			session.flush();
			session.close();
		}
	}
}
