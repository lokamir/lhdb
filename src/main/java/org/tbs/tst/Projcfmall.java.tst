package org.tbs.tst;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.tbs.entity.Vcfmall;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.provider.Page;


@Component
public class Projcfmall extends HibernateDao {
	
	@DataProvider
    public void getAllToPage(Page<Vcfmall> page, Map<String, Object> params)
	    throws Exception {
		
		 String hql = "from " + Vcfmall.class.getName() + " where detail.cfm_id=11" + " ";
		 this.pagingQuery(page, hql, "select count(*) " + hql);
	}
}
