package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasEntscale;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasEntscaleDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasEntscale> loadAll() throws Exception {
		return this.query("from " + TbsBasEntscale.class.getName());
	}
	
	@DataResolver
	public void save(Collection<TbsBasEntscale> tbsBasEntscales) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasEntscale tbsBasEntscale : tbsBasEntscales){
				EntityState state = EntityUtils.getState(tbsBasEntscale);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasEntscale);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasEntscale);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasEntscale);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
