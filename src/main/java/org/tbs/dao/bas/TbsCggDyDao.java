package org.tbs.dao.bas;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.dao.funs.GetSysInfo;
import org.tbs.entity.TbsCggDy;
import org.tbs.entity.TbsCustomer;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Component
public class TbsCggDyDao extends HibernateDao {

    @DataProvider
    public void getAllForCggDy(Page<TbsCggDy> page, int cggId,
	    Map<String, Object> params) throws Exception {
	String hql = getHql(cggId, params, TbsCggDy.class.getName());
	this.pagingQuery(page, hql, "select count(*) " + hql);
    }

    @DataResolver
    public void saveCggDy(Collection<TbsCggDy> tbsCggDys,
	    Map<String, Object> params) throws Exception {
	saveAll(tbsCggDys, params);
    }

    private String getHql(int cggId, Map<String, Object> params,
	    String tableName) {
	String whereCase = "";
	String hql = "";
	TbsCustomer tbsCustomer = (TbsCustomer) params.get("tbsCustomer");
	if (tbsCustomer != null && tbsCustomer.getId() != 0) {
	    whereCase += " and tbsCustomer.id = " + tbsCustomer.getId();
	    hql = "from " + tableName + " where del = 0 and cat3 = " + cggId
		    + " " + whereCase;
	}
	return hql;
    }

    @SuppressWarnings("unchecked")
	private void saveAll(Collection<TbsCggDy> tbsCggDys,
	    Map<String, Object> param) throws Exception {
	Session session = this.getSessionFactory().openSession();
	Date now = new Date();
	String uname = ContextHolder.getLoginUser().getUsername();
	GetSysInfo gsi = new GetSysInfo();
	try {
	    for (TbsCggDy tbsCggDy : tbsCggDys) {
		EntityState state = EntityUtils.getState(tbsCggDy);
		if (state.equals(EntityState.NEW)) {
		    Map<String, Object> customer = (Map<String, Object>) param
			    .get("customer");
		    int cat1 = (int) param.get("cat1");
		    int cat2 = (int) param.get("cat2");
		    int cat3 = (int) param.get("cat3");

		    int uid = gsi.getUserID(uname, session);
		    TbsCustomer tbsCustomer = new TbsCustomer();
		    tbsCustomer.setId((int) customer.get("id"));
		    // calculate pledgeper's value
		    if (tbsCggDy.getEvaprc().floatValue() != 0) {
			DecimalFormat decimalFormat=new DecimalFormat(".00");
			float pledgeper = tbsCggDy.getStrv().floatValue()
				/ tbsCggDy.getEvaprc().floatValue() * 100;
			BigDecimal pResult = new BigDecimal(decimalFormat.format(pledgeper));
			tbsCggDy.setPledgeper(pResult);
		    }
		    
		    tbsCggDy.setTbsCustomer(tbsCustomer);
		    tbsCggDy.setCusName((String) customer.get("name"));
		    tbsCggDy.setKeyinId(uid);
		    tbsCggDy.setSn("");
		    tbsCggDy.setTimestampInput(now);
		    tbsCggDy.setTimestampUpdate(now);

		    tbsCggDy.setCat1(cat1);
		    tbsCggDy.setCat2(cat2);
		    tbsCggDy.setCat3(cat3);

		    session.save(tbsCggDy);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    tbsCggDy.setTimestampUpdate(now);
		    // calculate pledgeper's value
		    if (tbsCggDy.getEvaprc().floatValue() != 0) {
			DecimalFormat decimalFormat=new DecimalFormat(".00");
			float pledgeper = tbsCggDy.getStrv().floatValue()
				/ tbsCggDy.getEvaprc().floatValue() * 100;
			BigDecimal pResult = new BigDecimal(decimalFormat.format(pledgeper));
			tbsCggDy.setPledgeper(pResult);
		    }
		    session.update(tbsCggDy);
		}
		if (state.equals(EntityState.DELETED)) {
		    tbsCggDy.setDel(1);
		    session.update(tbsCggDy);// 把del改为1
		}
	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }
}
