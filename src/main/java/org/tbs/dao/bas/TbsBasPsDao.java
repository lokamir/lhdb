package org.tbs.dao.bas;

import java.util.Collection;

import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasPs;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;

@Component
public class TbsBasPsDao extends HibernateDao {

    @DataProvider
    public Collection<TbsBasPs> getAll(){
	return this.query("from " + TbsBasPs.class.getName());
    }
}
