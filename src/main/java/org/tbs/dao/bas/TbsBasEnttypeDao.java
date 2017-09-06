package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasEnttype;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasEnttypeDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasEnttype> loadAll() throws Exception {
		return this.query("from " + TbsBasEnttype.class.getName());
	}
	
	@DataResolver
	public void save(Collection<TbsBasEnttype> tbsBasEnttypes) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasEnttype tbsBasEnttype : tbsBasEnttypes){
				EntityState state = EntityUtils.getState(tbsBasEnttype);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasEnttype);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasEnttype);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasEnttype);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
