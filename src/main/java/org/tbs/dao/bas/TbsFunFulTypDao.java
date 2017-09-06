package org.tbs.dao.bas;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsFunFulTyp;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsFunFulTypDao extends HibernateDao {
	
	@DataProvider
	public Collection<TbsFunFulTyp> loadAll() throws Exception {
		return this.query("from " + TbsFunFulTyp.class.getName() + " where del=0");
	}
	
	@DataResolver
	public void save(Collection<TbsFunFulTyp> tbsFunFulTyps) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsFunFulTyp tbsFunFulTyp : tbsFunFulTyps){
				Date now = new Date();
				EntityState state = EntityUtils.getState(tbsFunFulTyp);
				if(state.equals(EntityState.NEW)){
					session.save(tbsFunFulTyp);
				}
				if(state.equals(EntityState.MODIFIED)){
					tbsFunFulTyp.setTimestampUpdate(now);
					session.update(tbsFunFulTyp);
				}
				if(state.equals(EntityState.DELETED)){
					tbsFunFulTyp.setDel(true);
					session.update(tbsFunFulTyp);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
