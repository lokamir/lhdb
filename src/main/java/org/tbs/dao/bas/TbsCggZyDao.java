package org.tbs.dao.bas;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.dao.funs.GetSysInfo;
import org.tbs.entity.TbsCggZy;
import org.tbs.entity.TbsCustomer;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Component
public class TbsCggZyDao extends HibernateDao {
    
    @DataProvider
    public void getAllForCggZy(Page<TbsCggZy> page, int cggId,
	    Map<String, Object> params) throws Exception {
	String hql = getHql(cggId, params, TbsCggZy.class.getName());
	this.pagingQuery(page, hql, "select count(*) " + hql);
    }
    
    @DataResolver
    public void saveCggZy(Collection<TbsCggZy> tbsCggZys, Map<String, Object> params) throws Exception {
	saveAll(tbsCggZys, params);
    }

    private String getHql(int cggId, Map<String, Object> params, String tableName) {
	String whereCase = "";
	String hql = "";
	TbsCustomer tbsCustomer = (TbsCustomer) params.get("tbsCustomer");
	if (tbsCustomer != null && tbsCustomer.getId() != 0) {
	    whereCase += " and tbsCustomer.id = " + tbsCustomer.getId();
	    hql = "from " + tableName + " where del = 0 and cat2 = " + cggId
		    + " " + whereCase;
	}
	return hql;
    }

    @SuppressWarnings("unchecked")
	private void saveAll(Collection<TbsCggZy> tbsCggZys, Map<String, Object> param) throws Exception {
	Session session = this.getSessionFactory().openSession();
	Date now = new Date();
	String uname = ContextHolder.getLoginUser().getUsername();
	GetSysInfo gsi = new GetSysInfo();
	try {
	    for (TbsCggZy tbsCggZy : tbsCggZys) {
		EntityState state = EntityUtils.getState(tbsCggZy);
		if (state.equals(EntityState.NEW)) {
		    Map<String, Object> customer = (Map<String, Object>) param
			    .get("customer");
		    int cat1 = (int)param.get("cat1");
		    int cat2 = (int)param.get("cat2");
		    int cat3 = 0;

		    int uid = gsi.getUserID(uname, session);
		    TbsCustomer tbsCustomer = new TbsCustomer();
		    tbsCustomer.setId((int) customer.get("id"));
		    
		    if (tbsCggZy.getEvaprc().floatValue() != 0) {
			DecimalFormat decimalFormat=new DecimalFormat(".00");
			float pledgeper = tbsCggZy.getStrv().floatValue()
				/ tbsCggZy.getEvaprc().floatValue() * 100;
			BigDecimal pResult = new BigDecimal(decimalFormat.format(pledgeper));
			tbsCggZy.setPledgeper(pResult);
		    }
		    
		    tbsCggZy.setTbsCustomer(tbsCustomer);
		    tbsCggZy.setCusName((String) customer.get("name"));
		    tbsCggZy.setKeyinId(uid);
		    tbsCggZy.setSn("");
		    tbsCggZy.setTimestampInput(now);
		    tbsCggZy.setTimestampUpdate(now);

		    tbsCggZy.setCat1(cat1);
		    tbsCggZy.setCat2(cat2);
		    tbsCggZy.setCat3(cat3);

		    session.save(tbsCggZy);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    if (tbsCggZy.getEvaprc().floatValue() != 0) {
			DecimalFormat decimalFormat=new DecimalFormat(".00");
			float pledgeper = tbsCggZy.getStrv().floatValue()
				/ tbsCggZy.getEvaprc().floatValue() * 100;
			BigDecimal pResult = new BigDecimal(decimalFormat.format(pledgeper));
			tbsCggZy.setPledgeper(pResult);
		    }
		    tbsCggZy.setTimestampUpdate(now);
		    session.update(tbsCggZy);
		}
		if (state.equals(EntityState.DELETED)) {
		    tbsCggZy.setDel(1);
		    session.update(tbsCggZy);// 把del改为1
		}
	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }
}
