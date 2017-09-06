package org.tbs.dao.projs.cggrevoke;

/**
 * 9.1 反担保解除
 * 
 * */


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.dao.funs.GetSysInfo;
import org.tbs.entity.TbsCggChg;
import org.tbs.entity.TbsProjCgg;
import org.tbs.entity.VcggallSn;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsProjCggrevokeDao extends HibernateDao {
	@Resource
	private GetSysInfo gsi;
	
	//审批页面用
	@DataProvider
	public Collection<TbsProjCgg> loadTbsProjCgg(Integer id) throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjCgg.class.getName()
					+ " where proj_id= " + id;
			// 以下为虚拟属性，可以整段注销，取消该属性
			List<TbsProjCgg> results = new ArrayList<TbsProjCgg>();
			Collection<TbsProjCgg> tbsProjCggs = this.query(hql);
			for (TbsProjCgg tbsProjCgg : tbsProjCggs) {
				TbsProjCgg targetData = EntityUtils.toEntity(tbsProjCgg);
				Collection<VcggallSn> vcggallsns = gsi.getCggStr(tbsProjCgg
						.getCggSn());
				EntityUtils.setValue(targetData, "cggStr", vcggallsns);
				for (VcggallSn vcggallsn : vcggallsns) {
					String vcggallsnchilds = vcggallsn.getCusname() + "--"
							+ vcggallsn.getCat1name() + "  "
							+ vcggallsn.getCat2name() + "  "
							+ vcggallsn.getCat3name();
					EntityUtils.setValue(targetData, "cggCname",
							vcggallsnchilds);
				}
				results.add(targetData);
			}
			return results;
			// 虚拟属性结束
		} else {
			return null;
		}
	}
	@DataProvider
	// revoke审批页面反担保物表TbsProjCgg
	public Collection<TbsProjCgg> loadByCggrevoke(
			Map<String, Object> params) throws Exception {
		String businessId = (String) params.get("businessId");
		if (businessId != null) {
			String hql = "from " + TbsProjCgg.class.getName()
					+ " where valid = 1 and proj_id= " + businessId;
			List<TbsProjCgg> results = new ArrayList<TbsProjCgg>();
			Collection<TbsProjCgg> tbsProjCggs = this.query(hql);
			for (TbsProjCgg tbsProjCgg : tbsProjCggs) {
				TbsProjCgg targetData = EntityUtils.toEntity(tbsProjCgg);
				Collection<VcggallSn> vcggallsns = gsi.getCggStr(tbsProjCgg
						.getCggSn());
				EntityUtils.setValue(targetData, "cggStr", vcggallsns);
				for (VcggallSn vcggallsn : vcggallsns) {
					String vcggallsnchilds = vcggallsn.getCusname() + "--"
							+ vcggallsn.getCat1name() + "  "
							+ vcggallsn.getCat2name() + "  "
							+ vcggallsn.getCat3name();
					EntityUtils.setValue(targetData, "cggCname",
							vcggallsnchilds);
				}
				results.add(targetData);
			}
			return results;
			// 虚拟属性结束
		} else {
			return null;
		}
	}
	
	@DataProvider
	// revoke审批页面金额变更表TbsCggChg
	public Collection<TbsCggChg> loadBySingleCggChg(String processInstanceId)throws Exception {
		String hql;
		if (processInstanceId != null) {
			hql = "from " + TbsCggChg.class.getName() + " where by1= "
					+ processInstanceId;
			Collection<TbsCggChg> tbsCggChg =this.query(hql);
			 return tbsCggChg;
		} else {
			hql = "from " + TbsCggChg.class.getName() + " where by1= 0 ";
			Collection<TbsCggChg> tbsCggChg =this.query(hql);
			 return tbsCggChg;
		}
	}

	@SuppressWarnings("unchecked")
	@Expose
	public void saveCggState( Map<String, Object> params)
			throws Exception {
		ArrayList<String> revokecggSn = (ArrayList<String>) params.get("revokecggSn");
		String processInstanceId = (String)params.get("processInstanceId");
		Session session = this.getSessionFactory().openSession();
		try {
			for(String i : revokecggSn){
			String sqlupdate = "update tbs.tbs_proj_cgg set state=1,by1 ="+processInstanceId+" where valid = 1 and cgg_sn='"
					+ i + "'";
			SQLQuery sqlqueryupdate = session.createSQLQuery(sqlupdate);
			sqlqueryupdate.executeUpdate();
			}
		} finally {
			session.flush();
			session.close();
		}
	}
	
	@DataResolver
	public void saveTbsCggChg(Collection<TbsCggChg> tbsCggChgs)throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsCggChg tbsCggChg : tbsCggChgs) {
				EntityState state = EntityUtils.getState(tbsCggChg);
				if (state.equals(EntityState.NEW)) {
					Date date = new Date();
					tbsCggChg.setTimestampInput(date);
					session.save(tbsCggChg);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsCggChg);
				}
				if (state.equals(EntityState.DELETED)) {
					session.delete(tbsCggChg);
				}
			}
		} finally {
			session.flush();
			session.close();
		}
	}

}
