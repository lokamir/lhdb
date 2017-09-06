package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasIndutype;


import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasIndutypeDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasIndutype> loadAll() throws Exception {
		return this.query("from " + TbsBasIndutype.class.getName());
	}
	
	@DataResolver
	public void save(Collection<TbsBasIndutype> tbsBasIndutypes) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasIndutype tbsBasIndutype : tbsBasIndutypes){
				EntityState state = EntityUtils.getState(tbsBasIndutype);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasIndutype);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasIndutype);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasIndutype);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
