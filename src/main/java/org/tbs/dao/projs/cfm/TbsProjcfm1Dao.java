package org.tbs.dao.projs.cfm;

/**
 * 3.3 会议审议
 * */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.tbs.entity.TbsProjcfm1;
import org.tbs.entity.TbsProjcfm1Bizvt;
import org.tbs.entity.TbsProjcfm1Opinion;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;
@Component
public class TbsProjcfm1Dao extends HibernateDao {

	// pl
    @DataProvider
    public Collection<TbsProjcfm1> loadSingleResult(String BusinessId, String processInstanceId){
	return this.query("from " + TbsProjcfm1.class.getName() + " where tbsProj.id = "
		+ BusinessId + " and by3 = " + processInstanceId + " and del=0");
    }
    // pl
	@DataProvider
	public void loadAll(Page<TbsProjcfm1> page,Map<String, Object> params) throws Exception {
		if (null != params) {
			String sn = (String)params.get("sn");
			String valid=(String)params.get("valid");	
			String aRoleName = (String) params.get("aRoleName");
			String bRoleName = (String) params.get("bRoleName");
			String meetingloc = (String)params.get("meetingloc");
			String loantype = (String) params.get("loantype");
			String vtotlocMin = (String) params.get("vtotlocMin");
			String vtotlocMax = (String) params.get("vtotlocMax");
			String periodCfm = (String) params.get("periodCfm");
			Date meetingdate = (Date)params.get("meetingdate");	
			String whereCase="";
			if (params.get("tbsProj") instanceof TbsProj) {
				TbsProj tbsProjvalue = (TbsProj) params.get("tbsProj");
				if (tbsProjvalue != null && tbsProjvalue.getId() != 0) {
					whereCase += " AND tbsProj = " + tbsProjvalue.getId();
				}
			}	
			if (params.get("deptId") instanceof Bdf2Dept) {
				Bdf2Dept deptId = (Bdf2Dept) params
						.get("deptId");
				if (deptId != null && deptId.getDid() != 0) {
					whereCase += " AND DEPTDID = " + deptId.getDid();
				}
			}
			if (params.get("mgmAccount") instanceof Bdf2User) {
				Bdf2User mgmAccount = (Bdf2User) params
						.get("mgmAccount");
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

			if (StringHelper.isNotEmpty(meetingloc)) {
				whereCase += " AND meetingloc like '%" + meetingloc + "%' ";
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
				if(valid.equals("1")||valid.equals("0")){
					whereCase += " AND valid ="+valid;
				}
			}

			if (StringHelper.isNotEmpty(periodCfm)) {
				whereCase += " AND periodCfm =" + periodCfm;
			}

			if (null!=meetingdate) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String meetingdate_date = df.format(meetingdate);
				whereCase += " AND meetingdate ='" + meetingdate_date+"'";
			}
			String hql="from "+TbsProjcfm1.class.getName()+" where Del=0 " + whereCase+" order by id desc";
			this.pagingQuery(page, hql, "select count(*) "+hql);
		} else
		{
			String nohql="from "+TbsProjcfm1.class.getName()+" where id is null";
			this.pagingQuery(page, nohql, "select count(*) "+nohql);
		}
	}
	
	@DataProvider
	public Collection<TbsProjcfm1Opinion> loadOpinion(Integer Mid) throws Exception {
		if(Mid != null){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("mid", Mid);
			String hql="from " + TbsProjcfm1Opinion.class.getName()+" where PROJCFM1_ID= :mid";
			return this.query(hql,param);
		}else {	
			return null;
		}
	}
	
	@DataProvider
	public Collection<TbsProjcfm1Bizvt> loadTbsProjcfm1Bizvt(Integer Mid) throws Exception {
		if(Mid != null){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("mid", Mid);
			String hql="from " + TbsProjcfm1Bizvt.class.getName()+" where PROJCFM1_ID= :mid";
			return this.query(hql,param);
		}else {	
			return null;
		}
	}
	
	@DataResolver
	public void save(Collection<TbsProjcfm1> tbsProjcfm1s) throws Exception {
		Session session = this.getSessionFactory().openSession();
		Date now =new Date();
		try{
			for(TbsProjcfm1 tbsProjcfm1 : tbsProjcfm1s){
				EntityState state = EntityUtils.getState(tbsProjcfm1);
				if(state.equals(EntityState.NEW)){
					session.save(tbsProjcfm1);
				}
				if(state.equals(EntityState.MODIFIED)){
					tbsProjcfm1.setTimestampUpdate(now);
					session.update(tbsProjcfm1);
				}
				if(state.equals(EntityState.DELETED)){
					tbsProjcfm1.setDel(true);
					session.update(tbsProjcfm1);
				}
				Collection<TbsProjcfm1Bizvt> bizvtchilds = tbsProjcfm1.getTbsProjcfm1BizvtSet();
				if (bizvtchilds != null){
					for (TbsProjcfm1Bizvt bizvtchild:bizvtchilds){
						bizvtchild.setTbsProjcfm1(tbsProjcfm1);   
					}
					saveAllbizvtc(bizvtchilds);
				}
			}
		} finally{
			session.flush();
			session.close();
		}}
	
	@DataResolver
	public void saveAllbizvtc(Collection<TbsProjcfm1Bizvt> tbsProjcfm1Bizvts) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsProjcfm1Bizvt tbsProjcfm1Bizvt : tbsProjcfm1Bizvts){
				EntityState state = EntityUtils.getState(tbsProjcfm1Bizvt);
				if(state.equals(EntityState.NEW)){
					session.save(tbsProjcfm1Bizvt);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsProjcfm1Bizvt);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsProjcfm1Bizvt);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
