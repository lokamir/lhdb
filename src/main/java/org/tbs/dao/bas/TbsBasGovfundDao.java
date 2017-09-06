package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasGovfund;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsBasGovfundDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsBasGovfund> loadAll() throws Exception {
		return this.query("from " + TbsBasGovfund.class.getName());
	}
	
	@DataResolver
	public void save(Collection<TbsBasGovfund> tbsBasGovfunds) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsBasGovfund tbsBasGovfund : tbsBasGovfunds){
				EntityState state = EntityUtils.getState(tbsBasGovfund);
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasGovfund);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasGovfund);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasGovfund);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
