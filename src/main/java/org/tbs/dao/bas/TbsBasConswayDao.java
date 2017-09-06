package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasConsway;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasConswayDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasConsway> loadAll() throws Exception {
		return this.query("from " + TbsBasConsway.class.getName());
	}
	
	@DataResolver
	public void save(Collection<TbsBasConsway> tbsBasConsways) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasConsway tbsBasConsway : tbsBasConsways){
				EntityState state = EntityUtils.getState(tbsBasConsway);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasConsway);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasConsway);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasConsway);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
