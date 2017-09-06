package org.tbs.dao.projs.undwrt;

/**
 * 2.1 和 4.2.2 反担保关联
 * 
 * */

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsCggBzjzy;
import org.tbs.entity.TbsProjCgg;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsProjCggDao extends HibernateDao {
	@DataProvider
    public Collection<TbsProjCgg> loadTbsProjCgg() throws Exception {
	    String hql = "from " + TbsProjCgg.class.getName();
	    return this.query(hql);
    }
	
	@DataProvider
    public Collection<TbsCggBzjzy> loadTbsCggBzjzy() throws Exception {
	    String hql = "from " + TbsCggBzjzy.class.getName();
	    return this.query(hql);
    }
	
	@DataResolver
	public void save(Collection<TbsProjCgg> tbsProjCggs) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsProjCgg tbsProjCgg : tbsProjCggs){
				EntityState state = EntityUtils.getState(tbsProjCgg);
				if(state.equals(EntityState.NEW)){
					session.save(tbsProjCgg);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsProjCgg);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsProjCgg);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
