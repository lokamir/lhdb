package org.tbs.dao.bas;

import java.util.Collection;

import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasBiztype;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;

@Component
public class TbsBasBiztypeDao extends HibernateDao {

    @DataProvider
    public Collection<TbsBasBiztype> getAll(){
	return this.query("from " + TbsBasBiztype.class.getName());
    }
}
