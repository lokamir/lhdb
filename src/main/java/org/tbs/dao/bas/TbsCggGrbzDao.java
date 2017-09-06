package org.tbs.dao.bas;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.dao.funs.GetSysInfo;
import org.tbs.entity.TbsCggGrbz;
import org.tbs.entity.TbsCustomer;
import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Component
public class TbsCggGrbzDao extends HibernateDao {
    
    @DataProvider
    public void getAllForCggGrbz(Page<TbsCggGrbz> page, int cggId,
	    Map<String, Object> params) throws Exception {
	String hql = getHql(cggId, params, TbsCggGrbz.class.getName());
	this.pagingQuery(page, hql, "select count(*) " + hql);
    }
    
    @DataResolver
    public void saveCggGrbz(Collection<TbsCggGrbz> tbsCggGrbzs, Map<String, Object> params) throws Exception {
	saveAll(tbsCggGrbzs, params);
    }

    private String getHql(int cggId, Map<String, Object> params, String tableName) {
	String whereCase = "";
	String hql = "";
	TbsCustomer tbsCustomer = (TbsCustomer) params.get("tbsCustomer");
	if (tbsCustomer != null && tbsCustomer.getId() != 0) {
	    whereCase += " and tbsCustomer.id = " + tbsCustomer.getId();
	    hql = "from " + tableName + " where del = 0 and cat1 = " + cggId
		    + " " + whereCase;
	}
	return hql;
    }

    @SuppressWarnings("unchecked")
	private void saveAll(Collection<TbsCggGrbz> tbsCggGrbzs, Map<String, Object> param) throws Exception {
	Session session = this.getSessionFactory().openSession();
	Date now = new Date();
	String uname = ContextHolder.getLoginUser().getUsername();
	GetSysInfo gsi = new GetSysInfo();
	try {
	    for (TbsCggGrbz tbsCggGrbz : tbsCggGrbzs) {
		EntityState state = EntityUtils.getState(tbsCggGrbz);
		if (state.equals(EntityState.NEW)) {
		    Map<String, Object> customer = (Map<String, Object>) param
			    .get("customer");
		    int cat1 = (int)param.get("cat1");
		    int cat2 = 0;
		    int cat3 = 0;

		    int uid = gsi.getUserID(uname, session);
		    TbsCustomer tbsCustomer = new TbsCustomer();
		    tbsCustomer.setId((int) customer.get("id"));
		    tbsCggGrbz.setTbsCustomer(tbsCustomer);
		    tbsCggGrbz.setCusName((String) customer.get("name"));
		    tbsCggGrbz.setKeyinId(uid);
		    tbsCggGrbz.setSn("");
		    tbsCggGrbz.setTimestampInput(now);
		    tbsCggGrbz.setTimestampUpdate(now);

		    tbsCggGrbz.setCat1(cat1);
		    tbsCggGrbz.setCat2(cat2);
		    tbsCggGrbz.setCat3(cat3);

		    session.save(tbsCggGrbz);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    tbsCggGrbz.setTimestampUpdate(now);
		    session.update(tbsCggGrbz);
		}
		if (state.equals(EntityState.DELETED)) {
		    tbsCggGrbz.setDel(1);
		    session.update(tbsCggGrbz);// 把del改为1
		}
	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }
}
