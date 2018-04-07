package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasMeetingtype;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasMeetingtypeDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasMeetingtype> loadAll() throws Exception {
		return this.query("from " + TbsBasMeetingtype.class.getName());
	}
	
	@DataResolver
	public void save(Collection<TbsBasMeetingtype> tbsBasMeetingtypes) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasMeetingtype tbsBasMeetingtype : tbsBasMeetingtypes){
				EntityState state = EntityUtils.getState(tbsBasMeetingtype);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasMeetingtype);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasMeetingtype);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasMeetingtype);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
