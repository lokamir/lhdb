package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasCourt;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasCourtDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasCourt> loadAll() throws Exception {
		return this.query("from " + TbsBasCourt.class.getName() + " where del=0");
	}
	
	@DataResolver
	public void save(Collection<TbsBasCourt> tbsBasCourts) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasCourt tbsBasCourt : tbsBasCourts){
				EntityState state = EntityUtils.getState(tbsBasCourt);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasCourt);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasCourt);
				}
				if(state.equals(EntityState.DELETED)){
					tbsBasCourt.setDel(1);
					session.update(tbsBasCourt);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
