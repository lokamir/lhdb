package org.tbs.dao.projs.cfm;

/**
 * 审批意见
 * 
 * */

import java.util.Collection;
import java.util.Date;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProjOpinion;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

@Component
public class TbsProjOpinionDao extends HibernateDao {

    @DataProvider
    public Collection<TbsProjOpinion> getAllforCfm0(String projId, String cfm0Id) {
	String sql = "from " + TbsProjOpinion.class.getName()
		+ " where tbsProj.id = " + projId + " and tbsProjcfm0.id = "
		+ cfm0Id + " and cfmtype = 0 and del = 0";
	return this.query(sql);
    }
    
    @DataProvider
    public Collection<TbsProjOpinion> getAllforCfm1r2(String projId, String cfm0Id) {
	String sql = "from " + TbsProjOpinion.class.getName()
		+ " where tbsProj.id = " + projId + " and tbsProjcfm0.id = "
		+ cfm0Id + " and cfmtype <> 0 and del = 0";
	return this.query(sql);
    }

    @DataResolver
    public void save(Collection<TbsProjOpinion> tbsProjOpinions) throws Exception {
	Session session = this.getSessionFactory().openSession();
	Date now = new Date();
	try {
	    for (TbsProjOpinion tbsProjOpinion : tbsProjOpinions) {
		EntityState state = EntityUtils.getState(tbsProjOpinion);
		if (state.equals(EntityState.NEW)) {
		    tbsProjOpinion.setTimestampUpdate(now);
		    tbsProjOpinion.setTimestampInput(now);
		    tbsProjOpinion.setTitle("new");
		    session.save(tbsProjOpinion);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    // update时间
		    tbsProjOpinion.setTimestampUpdate(now);
		    tbsProjOpinion.setOcDate(now);
		    session.update(tbsProjOpinion);
		}
		if (state.equals(EntityState.DELETED)) {
		    tbsProjOpinion.setDel(true);
		    tbsProjOpinion.setTimestampUpdate(now);
		    session.update(tbsProjOpinion);
		}
	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }

}
