package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasArea;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasAreaDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasArea> loadAll() throws Exception {
		return this.query("from " + TbsBasArea.class.getName());
	}
	
	@DataResolver
	public void save(Collection<TbsBasArea> tbsBasAreas) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasArea tbsBasArea : tbsBasAreas){
				EntityState state = EntityUtils.getState(tbsBasArea);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasArea);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasArea);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasArea);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
