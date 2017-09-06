package org.tbs.dao.projs.cfm;

/**
 * 3.2 上会通知单
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
import org.tbs.entity.TbsProjcfm0;
import org.tbs.entity.TbsProjcfm0Attend;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;
@Component
public class TbsProjcfm0Dao extends HibernateDao {

	//pl
	@DataProvider
    public Collection<TbsProjcfm0> loadSingleData(String BusinessId, String processInstanceId) {
	return this.query("from " + TbsProjcfm0.class.getName() + " where tbsProj.id = "
		+ BusinessId + " and by3 = " + processInstanceId + " and del=0");
    } //pl
	
	@DataProvider
	public void loadAll(Page<TbsProjcfm0> page,Map<String, Object> params) throws Exception {
		if (null != params) {
			String valid = (String)params.get("valid");
			Date date = (Date)params.get("jtrq");
			String location = (String)params.get("location");
			String sn = (String)params.get("sn");
			Integer keyinId = (Integer)params.get("keyinId");
			String whereCase="";
			
			if (StringHelper.isNotEmpty(location)) {
				whereCase += " AND location like '%" + location + "%' ";
			}
			if (StringHelper.isNotEmpty(sn)) {
				whereCase += " AND sn like '%" + sn + "%' ";
			}
			if (StringHelper.isNotEmpty(valid)) {
				if(valid.equals("1")||valid.equals("0")){
					whereCase += " AND valid ="+valid;
				}
			}
			if (params.get("keyinId")!=null&&params.get("keyinId")!="") {
				whereCase += " AND keyinId =" + keyinId ;
			}
			if (null!=date) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    String meeting_date = df.format(date);
				whereCase += " AND jtrq ='" + meeting_date+"'";
			}
			String hql="from "+TbsProjcfm0.class.getName()+" where Del=0 " + whereCase+" order by id desc";;
			this.pagingQuery(page, hql, "select count(*) "+hql);
			
		} else
		{
			String nohql="from "+TbsProjcfm0.class.getName()+" where id is null";
			this.pagingQuery(page, nohql, "select count(*) "+nohql);
		}
	}
	@DataProvider
	public Collection<TbsProjcfm0Attend> loadTbsProjcfm0Attend(Integer Mid) throws Exception {
		if(Mid != null){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("mid", Mid);
			String hql="from " + TbsProjcfm0Attend.class.getName()+" where PROJCFM0_ID= :mid";
			return this.query(hql,param);
		}else {	
	
		return null;
		}
	}
	
	
	@DataResolver
	public void save(Collection<TbsProjcfm0> tbsProjcfm0s) throws Exception {
		Session session = this.getSessionFactory().openSession();
		Date now = new Date();
		try{
			for(TbsProjcfm0 tbsProjcfm0 : tbsProjcfm0s){
				EntityState state = EntityUtils.getState(tbsProjcfm0);
				if(state.equals(EntityState.NEW)){
					session.save(tbsProjcfm0);
				}
				if(state.equals(EntityState.MODIFIED)){
					//update时间
					tbsProjcfm0.setTimestampUpdate(now);
					session.update(tbsProjcfm0);
				}
				if(state.equals(EntityState.DELETED)){
					tbsProjcfm0.setDel(true);
					session.update(tbsProjcfm0);
				}Collection<TbsProjcfm0Attend> attendchilds = tbsProjcfm0.getTbsProjcfm0AttendSet();
				if (attendchilds != null){
					for (TbsProjcfm0Attend attendchild:attendchilds){
						attendchild.setTbsProjcfm0(tbsProjcfm0);   
					}
					saveAllbizvtc(attendchilds);
				}


			}
		} finally{
			session.flush();
			session.close();
		}}
	@DataResolver
	public void saveAllbizvtc(Collection<TbsProjcfm0Attend> tbsProjcfm0Attends) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsProjcfm0Attend tbsProjcfm0Attend : tbsProjcfm0Attends){
				EntityState state = EntityUtils.getState(tbsProjcfm0Attend);
				if(state.equals(EntityState.NEW)){
					session.save(tbsProjcfm0Attend);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsProjcfm0Attend);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsProjcfm0Attend);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
