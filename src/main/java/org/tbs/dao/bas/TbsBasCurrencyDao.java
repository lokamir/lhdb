package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasCurrency;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasCurrencyDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasCurrency> loadAll() throws Exception {
		return this.query("from " + TbsBasCurrency.class.getName() + " where valid=1");
	}
	
	@DataResolver
	public void save(Collection<TbsBasCurrency> tbsBasCurrencys) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasCurrency tbsBasCurrency : tbsBasCurrencys){
				EntityState state = EntityUtils.getState(tbsBasCurrency);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasCurrency);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasCurrency);
				}
				if(state.equals(EntityState.DELETED)){
					tbsBasCurrency.setValid(false);
					session.update(tbsBasCurrency);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
