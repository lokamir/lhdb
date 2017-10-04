package org.tbs.dao.bas;


import java.util.Collection;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.tbs.entity.Vcggall;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;

@Component
public class TbsCggVcggallDao extends HibernateDao {

	@DataProvider
	public Collection<Vcggall> getAllForVcggall(Map<String, Object> params)throws Exception {
		String whereCase = "";
		if (null != params) {
			Integer cusid = (Integer) params.get("cusid");
			if (cusid!=null) {
				whereCase += " AND cusid =" + cusid ;
			}
			String hql = "from " + Vcggall.class.getName() + " where 1=1 "+ whereCase;
			Collection<Vcggall> A =this.query(hql);
			return A;
			
		}else{
			return null;
		}
	}
}
