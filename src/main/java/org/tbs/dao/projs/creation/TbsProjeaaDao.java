package org.tbs.dao.projs.creation;

/**
 * 2.2 立项审批单查询
 * 
 * */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.stereotype.Component;
import org.tbs.dao.funs.GetSysInfo;
import org.tbs.entity.Bdf2User;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjeaa;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Component
public class TbsProjeaaDao extends HibernateDao {

	
	@Resource
    private GetSysInfo gsi;

	
	@DataProvider  
	//下拉框带筛选,只给开始立项审批用 ,显示申请中和驳回的-- htsh不用了
    public Collection<TbsProj> getAllProjnameByName(String name){
	if(StringHelper.isNotEmpty(name)&&!name.contains("'")){
		return this.query("from " + TbsProj.class.getName() 
			+ " where del=0 and projName like '%" + name + "%'");
		} else {
			return this.query("from " + TbsProj.class.getName() 
				+ " where del=0 " );
		}
    }

	@DataProvider
    public Collection<TbsProjeaa> getSingleResult(Integer projId) throws Exception {
	String sql = "from " + TbsProjeaa.class.getName() + 
		" where tbsProj.id = " + projId + " and del = 0";
	return this.query(sql);

    }
	
	@DataProvider
	public void loadAll(Page<TbsProjeaa> page,Map<String, Object> params) throws Exception {
	if (null != params) {
			String mgmAccount = (String)params.get("mgmAccount");
			String aRoleName = (String) params.get("aRoleName");
			String bRoleName = (String)params.get("bRoleName");
			String falocMin = (String)params.get("falocMin");		
			String nfalocMin = (String)params.get("nfalocMin");
			String otlocMin = (String) params.get("otlocMin");
			String totlocMin = (String) params.get("totlocMin");
			String period = (String) params.get("period");
			String falocMax = (String)params.get("falocMax");	
			String nfalocMax = (String)params.get("nfalocMax");
			String otlocMax = (String) params.get("otlocMax");
			String totlocMax = (String) params.get("totlocMax");
			String whereCase ="";
			if (params.get("tbsProj") instanceof TbsProj) {
				TbsProj tbsProjvalue = (TbsProj) params.get("tbsProj");
				if (tbsProjvalue != null && tbsProjvalue.getId() != 0) {
					whereCase += " AND tbsProj = " + tbsProjvalue.getId();
					}
				}
			if (params.get("bdf2User") instanceof Bdf2User) {
				Bdf2User bdf2Uservalue = (Bdf2User) params.get("bdf2User");
				if (bdf2Uservalue != null && bdf2Uservalue.getId() != 0) {
					whereCase += " AND bdf2User = " + bdf2Uservalue.getId();
					}
				}
			if (StringHelper.isNotEmpty(mgmAccount)) {
				whereCase += " AND mgmAccount like '%" + mgmAccount + "%' ";
			}
			if (StringHelper.isNotEmpty(aRoleName)) {
				whereCase += " AND aRoleName like '%" + aRoleName + "%' ";
			}
			if (StringHelper.isNotEmpty(bRoleName)) {
				whereCase += " AND bRoleName like '%" + bRoleName + "%' ";
			}
			if (StringHelper.isNotEmpty(falocMin)) {
				whereCase += " AND faloc >=" + falocMin;
			}
			if (StringHelper.isNotEmpty(falocMax)) {
				whereCase += " AND faloc <=" + falocMax;
			}
			if (StringHelper.isNotEmpty(nfalocMin)){
				whereCase += " AND nfaloc >=" + nfalocMin ;
			}
			if (StringHelper.isNotEmpty(nfalocMax)){
				whereCase += " AND nfaloc <=" + nfalocMax ;
			}
			if (StringHelper.isNotEmpty(otlocMin)) {
				whereCase += " AND otloc >=" + otlocMin ;
			}
			if (StringHelper.isNotEmpty(otlocMax)) {
				whereCase += " AND otloc <=" + otlocMax ;
			}
			if (StringHelper.isNotEmpty(totlocMin)) {
				whereCase += " AND totloc >=" + totlocMin ;
			}
			if (StringHelper.isNotEmpty(totlocMax)) {
				whereCase += " AND totloc <=" + totlocMax ;
			}
			if (StringHelper.isNotEmpty(period)) {
				whereCase += " AND period =" + period ;
			}
			if (null!=params.get("valid")) {
				//boolean valid = (Boolean)params.get("valid");
				Integer valid=(Integer)params.get("valid");
				if(1==valid){
					whereCase += " AND valid =1";
				}
				else if(0==valid){
					whereCase += " AND valid =0";
				}
				else{
				}
			}	
			/*申请中和被驳回的*/
			String hql="from TbsProjeaa a where a.del=0 " +whereCase+" order by a.id desc";
			this.pagingQuery(page, hql, "select count(*) "+hql);
			//以下为虚拟属性，可以整段注销，取消该属性//
			List<TbsProjeaa> results = new ArrayList<TbsProjeaa>();
			Collection<TbsProjeaa> tbsProjeaas = page.getEntities();
			for (TbsProjeaa tbsProjeaa : tbsProjeaas) {
				TbsProjeaa targetData = EntityUtils.toEntity(tbsProjeaa);
				List<Bdf2User> bdf2Users = (List<Bdf2User>)gsi.getCnameById(tbsProjeaa.getKeyinId());
				EntityUtils.setValue(targetData, "VOcname", bdf2Users.get(0).getCname());
			    results.add(targetData);
			}
			page.setEntities(results); 
			//虚拟属性结束
		} else {
			String nohql="from "+TbsProjeaa.class.getName()+" where id is null";
			this.pagingQuery(page, nohql, "select count(*) "+nohql);
		}
	}


	
	@DataResolver
	public void save(Collection<TbsProjeaa> tbsProjeaas) throws Exception {
		Session session = this.getSessionFactory().openSession();
		Date now = new Date();
		String uname=ContextHolder.getLoginUser().getUsername();
		GetSysInfo gsi=new GetSysInfo(); 

		try{
			for(TbsProjeaa tbsProjeaa : tbsProjeaas){
				EntityState state = EntityUtils.getState(tbsProjeaa);
				if(state.equals(EntityState.NEW)){
					int uid = gsi.getUserID(uname,session);
					tbsProjeaa.setKeyinId(uid);
					//tbsProjeaa.setTimestampInput(now);
					//tbsProjeaa.setTimestampUpdate(now);
					session.save(tbsProjeaa);

				}
				if(state.equals(EntityState.MODIFIED)){
					tbsProjeaa.setTimestampUpdate(now);
					session.update(tbsProjeaa);
				}
				if(state.equals(EntityState.DELETED)){
					tbsProjeaa.setDel(true);
					session.update(tbsProjeaa);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}






}
