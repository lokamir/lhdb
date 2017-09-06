package org.tbs.tst;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.tbs.entity.Bdf2Dept;
import org.tbs.entity.TbsProjcfm1;
import org.tbs.entity.TbsProjeaa;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.provider.Page;


@Component
public class Hqltst extends HibernateDao {
	
	@DataProvider    /*Page<TbsProjcfm1> page,Map<String, Object> params*/
	public  Collection<TbsProjeaa> loadAll() throws Exception {
		
		String hql = "from TbsProjeaa a where a.tbsProj.tbsBasPs.id=6";
		return this.query(hql);
	}
}
