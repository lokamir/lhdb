package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasIdcardtype;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasIdcardtypeDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasIdcardtype> loadAll() throws Exception {
		return this.query("from " + TbsBasIdcardtype.class.getName());
	}
	
	@DataProvider
	public Collection<TbsBasIdcardtype> loadHumanIdType() throws Exception {
		return this.query("from " + TbsBasIdcardtype.class.getName() + " where id = 1 or id = 4");
	}
	@DataResolver
	public void save(Collection<TbsBasIdcardtype> tbsBasIdcardtypes) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasIdcardtype tbsBasIdcardtype : tbsBasIdcardtypes){
				EntityState state = EntityUtils.getState(tbsBasIdcardtype);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasIdcardtype);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasIdcardtype);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasIdcardtype);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
