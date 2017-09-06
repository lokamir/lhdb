package org.tbs.dao.bas;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.dao.funs.GetSysInfo;
import org.tbs.entity.TbsCggQybz;
import org.tbs.entity.TbsCustomer;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Component
public class TbsCggQybzDao extends HibernateDao {
    
    @DataProvider
    public void getAllForCggQybz(Page<TbsCggQybz> page, int cggId,
	    Map<String, Object> params) throws Exception {
	String hql = getHql(cggId, params, TbsCggQybz.class.getName());
	this.pagingQuery(page, hql, "select count(*) " + hql);
    }
    
    @DataResolver
    public void saveCggQybz(Collection<TbsCggQybz> tbsCggQybzs, Map<String, Object> params) throws Exception {
	saveAll(tbsCggQybzs, params);
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
	private void saveAll(Collection<TbsCggQybz> tbsCggQybzs, Map<String, Object> param) throws Exception {
	Session session = this.getSessionFactory().openSession();
	Date now = new Date();
	String uname = ContextHolder.getLoginUser().getUsername();
	GetSysInfo gsi = new GetSysInfo();
	try {
	    for (TbsCggQybz tbsCggQybz : tbsCggQybzs) {
		EntityState state = EntityUtils.getState(tbsCggQybz);
		if (state.equals(EntityState.NEW)) {
		    Map<String, Object> customer = (Map<String, Object>) param
			    .get("customer");
		    int cat1 = (int)param.get("cat1");
		    int cat2 = 0;
		    int cat3 = 0;

		    int uid = gsi.getUserID(uname, session);
		    TbsCustomer tbsCustomer = new TbsCustomer();
		    tbsCustomer.setId((int) customer.get("id"));
		    tbsCggQybz.setTbsCustomer(tbsCustomer);
		    tbsCggQybz.setCusName((String) customer.get("name"));
		    tbsCggQybz.setKeyinId(uid);
		    tbsCggQybz.setSn("");
		    tbsCggQybz.setTimestampInput(now);
		    tbsCggQybz.setTimestampUpdate(now);

		    tbsCggQybz.setCat1(cat1);
		    tbsCggQybz.setCat2(cat2);
		    tbsCggQybz.setCat3(cat3);

		    session.save(tbsCggQybz);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    tbsCggQybz.setTimestampUpdate(now);
		    session.update(tbsCggQybz);
		}
		if (state.equals(EntityState.DELETED)) {
		    tbsCggQybz.setDel(1);
		    session.update(tbsCggQybz);// 把del改为1
		}
	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }
}
