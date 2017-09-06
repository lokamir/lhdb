package org.tbs.dao.projs.periodMgm;

/**
 * 5.1定期，5.2不定期检查
 * 
 * */

import java.util.Collection;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjCheck;
import org.tbs.entity.TbsRiskProjcfm;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Component
public class TbsProjCheckDao extends HibernateDao {
	
	//mainview 使用
	@DataProvider
	public void loadProjCheck0(Page<TbsProjCheck> page,Integer id) throws Exception {
		String hql = "from " + TbsProjCheck.class.getName()
				+ " where del = 0 and CHKTYPE = 0 and proj_id = " + id
				+ "order by id desc";
		this.pagingQuery(page, hql, "select count(*) " + hql);
	}
	
	@DataProvider
	public void loadProjCheck1(Page<TbsProjCheck> page,Integer id) throws Exception {
		String hql = "from " + TbsProjCheck.class.getName()
				+ " where del = 0 and CHKTYPE = 1 and proj_id = " + id
				+ "order by id desc";
		this.pagingQuery(page, hql, "select count(*) " + hql);
	}
	
	
	//detail12_view使用
	@DataProvider
	public Collection<TbsProj> loadSingleProj(Integer id) throws Exception {
		if (id != null) {
			String hql = "from " + TbsProj.class.getName() + " where del = 0 "
					+ " and id = " + id ;
			return this.query(hql);
		} else {
			String hql = "from " + TbsProj.class.getName()
					+ " where id is null ";
			return this.query(hql);
		}
	}
	
	@DataProvider
	public Collection<TbsRiskProjcfm> loadSingleRiskProjcfm(Integer id)
			throws Exception {
		String hql = "from " + TbsRiskProjcfm.class.getName()
				+ " where del = 0 " + " and proj_id = " + id;
		return this.query(hql);
	}
	
	@DataProvider
	public Collection<TbsProjCheck> loadProjCheck(Integer id) throws Exception {
		String hql = "from " + TbsProjCheck.class.getName()
				+ " where del = 0 and id = " + id;
		return this.query(hql);
	}
	
	
	@DataResolver
	public void saveMain(Collection<TbsProj> tbsProjs) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProj tbsProj : tbsProjs) {
				
				 EntityState state = EntityUtils.getState(tbsProj); if
				 (state.equals(EntityState.DELETED)) { tbsProj.setDel(true);
				 session.update(tbsProj);// 把del改为1 
				 }
				 
				Collection<TbsProjCheck> tbsProjChecks = tbsProj
						.getTbsProjCheckSet();
				if (tbsProjChecks != null) {
					for (TbsProjCheck tbsProjCheck : tbsProjChecks) {
						tbsProjCheck.setTbsProj(tbsProj);
					}
					saveTbsProjCheck(tbsProjChecks);
				}
				Collection<TbsRiskProjcfm> tbsRiskProjcfms = tbsProj
						.getTbsRiskProjcfmSet();
				if (tbsRiskProjcfms != null) {
					for (TbsRiskProjcfm tbsRiskProjcfm : tbsRiskProjcfms) {
						tbsRiskProjcfm.setTbsProj(tbsProj);
					}
					saveTbsRiskProjcfm(tbsRiskProjcfms);
				}
			}
		} finally {
			session.flush();
			session.close();
		}
	}

	@DataResolver
	public void saveTbsProjCheck(Collection<TbsProjCheck> tbsProjChecks)
			throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProjCheck tbsProjCheck : tbsProjChecks) {
				EntityState state = EntityUtils.getState(tbsProjCheck);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsProjCheck);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsProjCheck);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsProjCheck.setDel(true);
					session.update(tbsProjCheck);
				}
			}
		} finally {
			session.flush();
			session.close();
		}
	}
	
	@DataResolver
	public void saveTbsRiskProjcfm(Collection<TbsRiskProjcfm> tbsRiskProjcfms)
			throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsRiskProjcfm tbsRiskProjcfm : tbsRiskProjcfms) {
				EntityState state = EntityUtils.getState(tbsRiskProjcfm);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsRiskProjcfm);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsRiskProjcfm);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsRiskProjcfm.setDel(true);
					session.update(tbsRiskProjcfm);
				}
			}
		} finally {
			session.flush();
			session.close();
		}
	}

}
