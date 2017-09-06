package org.tbs.dao.bas;

import java.util.Collection;

import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasBizvar;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;

@Component
public class TbsBasBizvarDao extends HibernateDao {

    @DataProvider
    public Collection<TbsBasBizvar> getAll(int id) {
	return this.query("from " + TbsBasBizvar.class.getName()
		+ " where biztype_id = " + id);
    }
}
