package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasEntproperty;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasEntpropertyDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasEntproperty> loadAll() throws Exception {
		return this.query("from " + TbsBasEntproperty.class.getName());
	}
	
	@DataResolver
	public void save(Collection<TbsBasEntproperty> tbsBasEntpropertys) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasEntproperty tbsBasEntproperty : tbsBasEntpropertys){
				EntityState state = EntityUtils.getState(tbsBasEntproperty);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasEntproperty);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasEntproperty);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasEntproperty);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
