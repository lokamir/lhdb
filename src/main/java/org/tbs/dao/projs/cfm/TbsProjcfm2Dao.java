package org.tbs.dao.projs.cfm;

/**
 * 3.4 签批审议
 * */

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.stereotype.Component;
import org.tbs.entity.Bdf2Dept;
import org.tbs.entity.Bdf2User;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjcfm2;
import org.tbs.entity.TbsProjcfm2Bizvt;
import org.tbs.entity.TbsProjcfm2Opinion;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Component
public class TbsProjcfm2Dao extends HibernateDao {

	// pl
    @DataProvider
    public Collection<TbsProjcfm2> loadSingleResult(String BusinessId, String processInstanceId){
	return this.query("from " + TbsProjcfm2.class.getName() + " where tbsProj.id = "
		+ BusinessId + " and by3 = " + processInstanceId + " and del=0");
    }
    // pl
	@DataProvider
	public void loadAll(Page<TbsProjcfm2> page, Map<String, Object> params)
			throws Exception {
		if (null != params) {
			String sn = (String) params.get("sn");
			String valid = (String) params.get("valid");
			String aRoleName = (String) params.get("aRoleName");
			String bRoleName = (String) params.get("bRoleName");
			String loantype = (String) params.get("loantype");
			String vtotlocMin = (String) params.get("vtotlocMin");
			String vtotlocMax = (String) params.get("vtotlocMax");
			String periodCfm = (String) params.get("periodCfm");

			String whereCase = "";
			if (params.get("tbsProj") instanceof TbsProj) {
				TbsProj tbsProjvalue = (TbsProj) params.get("tbsProj");
				if (tbsProjvalue != null && tbsProjvalue.getId() != 0) {
					whereCase += " AND tbsProj = " + tbsProjvalue.getId();
				}
			}
			if (params.get("deptId") instanceof Bdf2Dept) {
				Bdf2Dept deptId = (Bdf2Dept) params.get("deptId");
				if (deptId != null && deptId.getDid() != 0) {
					whereCase += " AND DEPTDID = " + deptId.getDid();
				}
			}
			if (params.get("mgmAccount") instanceof Bdf2User) {
				Bdf2User mgmAccount = (Bdf2User) params.get("mgmAccount");
				if (mgmAccount != null && mgmAccount.getId() != 0) {
					whereCase += " AND MGMUID = " + mgmAccount.getId();
				}
			}
			if (StringHelper.isNotEmpty(sn)) {
				whereCase += " AND sn like '%" + sn + "%' ";
			}
			if (StringHelper.isNotEmpty(aRoleName)) {
				whereCase += " AND aRoleName like '%" + aRoleName + "%' ";
			}
			if (StringHelper.isNotEmpty(bRoleName)) {
				whereCase += " AND bRoleName like '%" + bRoleName + "%' ";
			}
			if (StringHelper.isNotEmpty(loantype)) {
				whereCase += " AND loantype like '%" + loantype + "%' ";
			}

			if (StringHelper.isNotEmpty(vtotlocMin)) {
				whereCase += " AND vtotloc >=" + vtotlocMin;
			}
			if (StringHelper.isNotEmpty(vtotlocMax)) {
				whereCase += " AND vtotloc <=" + vtotlocMax;
			}
			if (StringHelper.isNotEmpty(valid)) {
				if (valid.equals("1") || valid.equals("0")) {
					whereCase += " AND valid =" + valid;
				}
			}
			if (StringHelper.isNotEmpty(periodCfm)) {
				whereCase += " AND periodCfm =" + periodCfm;
			}

			String hql = "from " + TbsProjcfm2.class.getName()
					+ " where Del=0 " + whereCase + " order by id desc";
			this.pagingQuery(page, hql, "select count(*) " + hql);

		} else {
			String nohql = "from " + TbsProjcfm2.class.getName()
					+ " where id is null";
			this.pagingQuery(page, nohql, "select count(*) " + nohql);
		}
	}

	@DataProvider
	public Collection<TbsProjcfm2Bizvt> loadTbsProjcfm2Bizvt(Integer Mid)
			throws Exception {
		if (Mid != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("mid", Mid);
			String hql = "from " + TbsProjcfm2Bizvt.class.getName()
					+ " where PROJCFM2_ID= :mid";
			return this.query(hql, param);
		} else {
			return null;
		}
	}
	@DataProvider
	public Collection<TbsProjcfm2Opinion> loadOpinion(Integer Mid) throws Exception {
		if(Mid != null){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("mid", Mid);
			String hql="from " + TbsProjcfm2Opinion.class.getName()+" where PROJCFM2_ID= :mid";
			return this.query(hql,param);
		}else {	
			return null;
		}
	}
	@DataResolver
	public void save(Collection<TbsProjcfm2> tbsProjcfm2s) throws Exception {
		Session session = this.getSessionFactory().openSession();
		Date now = new Date();
		try {
			for (TbsProjcfm2 tbsProjcfm2 : tbsProjcfm2s) {
				EntityState state = EntityUtils.getState(tbsProjcfm2);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsProjcfm2);
				}
				if (state.equals(EntityState.MODIFIED)) {
					tbsProjcfm2.setTimestampUpdate(now);
					session.update(tbsProjcfm2);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsProjcfm2.setDel(true);
					session.update(tbsProjcfm2);
				}
				Collection<TbsProjcfm2Bizvt> bizvtchilds = tbsProjcfm2
						.getTbsProjcfm2BizvtSet();
				if (bizvtchilds != null) {
					for (TbsProjcfm2Bizvt bizvtchild : bizvtchilds) {
						bizvtchild.setTbsProjcfm2(tbsProjcfm2);
					}
					saveAllbizvtc(bizvtchilds);
				}

			}
		} finally {
			session.flush();
			session.close();
		}
	}

	@DataResolver
	public void saveAllbizvtc(Collection<TbsProjcfm2Bizvt> tbsProjcfm2Bizvts)
			throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProjcfm2Bizvt tbsProjcfm2Bizvt : tbsProjcfm2Bizvts) {
				EntityState state = EntityUtils.getState(tbsProjcfm2Bizvt);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsProjcfm2Bizvt);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsProjcfm2Bizvt);
				}
				if (state.equals(EntityState.DELETED)) {
					session.delete(tbsProjcfm2Bizvt);
				}
			}
		} finally {
			session.flush();
			session.close();
		}
	}
}
