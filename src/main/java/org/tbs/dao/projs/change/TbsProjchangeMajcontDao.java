package org.tbs.dao.projs.change;

/**
 * 8.1 项目三要素变更
 * 
 * */

import java.util.Collection;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProjchangeMajcont;
import org.tbs.entity.TbsProjchangeMajcontCfm;
import org.tbs.entity.TbsProjchangeMajcontCfmattend;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;


@Component
public class TbsProjchangeMajcontDao extends HibernateDao {
   
    
	@DataProvider  // mainview 用
    public Collection<TbsProjchangeMajcont> loadByProjid(Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjchangeMajcont.class.getName()
					+ " where del = 0 and proj_id= " + id + " order by id desc";
			return this.query(hql);
		} else {
				String hql = "from " + TbsProjchangeMajcont.class.getName()
						+ " where id is null";
				return this.query(hql);
		}
    }
	
	@DataProvider  // detailview 用
    public Collection<TbsProjchangeMajcont> loadById(Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjchangeMajcont.class.getName()
					+ " where del = 0 and id= " + id + " order by id desc";
			return this.query(hql);
		} else {
				String hql = "from " + TbsProjchangeMajcont.class.getName()
						+ " where id is null";
				return this.query(hql);
		}
    }
		
	@DataResolver  //Majcont增删改
	public void saveAll(Collection<TbsProjchangeMajcont> tbsProjchangeMajconts) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			for (TbsProjchangeMajcont tbsProjchangeMajcont : tbsProjchangeMajconts) {
				EntityState state = EntityUtils.getState(tbsProjchangeMajcont);
				if (state.equals(EntityState.NEW)) {
					session.save(tbsProjchangeMajcont);
				}
				if (state.equals(EntityState.MODIFIED)) {
					session.update(tbsProjchangeMajcont);
				}
				if (state.equals(EntityState.DELETED)) {
					tbsProjchangeMajcont.setDel(true);
					session.update(tbsProjchangeMajcont);
				}
				Collection<TbsProjchangeMajcontCfm> cfms = tbsProjchangeMajcont.getTbsProjchangeMajcontCfmSet();
				if (cfms != null){
					for (TbsProjchangeMajcontCfm cfm:cfms){
						cfm.setTbsProjchangeMajcont(tbsProjchangeMajcont);   
					}
					saveAllcfm(cfms);
				}
			}			
		} finally {
			session.flush();
			session.close();
		}
	}
	
	@DataResolver  //MajcontCfm增删改
	public void saveAllcfm(Collection<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfms) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsProjchangeMajcontCfm tbsProjchangeMajcontCfm : tbsProjchangeMajcontCfms){
				EntityState state = EntityUtils.getState(tbsProjchangeMajcontCfm);
				if(state.equals(EntityState.NEW)){
					session.save(tbsProjchangeMajcontCfm);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsProjchangeMajcontCfm);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsProjchangeMajcontCfm);
				}
				Collection<TbsProjchangeMajcontCfmattend> cfmattends = tbsProjchangeMajcontCfm.getTbsProjchangeMajcontCfmattendSet();
				if (cfmattends != null){
					for (TbsProjchangeMajcontCfmattend cfmattend:cfmattends){
						cfmattend.setTbsProjchangeMajcontCfm(tbsProjchangeMajcontCfm);   
					}
					saveAllcfmattend(cfmattends);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
	
	@DataResolver //MajcontCfmAttend增删改
	public void saveAllcfmattend(Collection<TbsProjchangeMajcontCfmattend> tbsProjchangeMajcontCfmattends) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsProjchangeMajcontCfmattend tbsProjchangeMajcontCfmattend : tbsProjchangeMajcontCfmattends){
				EntityState state = EntityUtils.getState(tbsProjchangeMajcontCfmattend);
				if(state.equals(EntityState.NEW)){
					session.save(tbsProjchangeMajcontCfmattend);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsProjchangeMajcontCfmattend);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsProjchangeMajcontCfmattend);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
	
	@Expose
	public void delById(Integer id) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try {
			String sql="update tbs_projchange_majcont set del=1 where id="+id;
			SQLQuery query=session.createSQLQuery(sql);
			query.executeUpdate();
			}finally {
			session.flush();
			session.close();
		}
	}
	
}
