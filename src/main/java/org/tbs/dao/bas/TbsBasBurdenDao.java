package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasBurden;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasBurdenDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasBurden> loadAll() throws Exception {
		return this.query("from " + TbsBasBurden.class.getName());
	}
	
	@DataResolver
	public void save(Collection<TbsBasBurden> tbsBasBurdens) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasBurden tbsBasBurden : tbsBasBurdens){
				EntityState state = EntityUtils.getState(tbsBasBurden);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasBurden);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasBurden);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasBurden);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
