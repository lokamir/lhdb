package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsApprover;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Criteria;
import com.bstek.dorado.data.provider.Page;

@Component
public class TbsApproverDao extends HibernateDao {
	
	@DataProvider
	public void loadAll(Page<TbsApprover> page,Criteria criteria) throws Exception {
		DetachedCriteria dc=this.buildDetachedCriteria(criteria, TbsApprover.class);
		this.pagingQuery(page, dc);
	}
	
	
	@DataResolver
	public void save(Collection<TbsApprover> tbsApprovers) throws Exception {
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsApprover tbsApprover : tbsApprovers){
				EntityState state = EntityUtils.getState(tbsApprover);
				if(state.equals(EntityState.NEW)){
					session.save(tbsApprover);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsApprover);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsApprover);
				}
			}
		} finally{
			session.flush();
			session.close();
		}
	}
}
