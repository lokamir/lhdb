package org.tbs.dao.bas;

import java.util.Collection;

import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.stereotype.Component;
import org.tbs.entity.Bdf2Dept;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;

@Component
public class Bdf2DeptDao extends HibernateDao {
	@DataProvider
	public Collection<Bdf2Dept> loadAll() throws Exception {
		return this.query("from " + Bdf2Dept.class.getName());
	}
	
	@DataProvider  //下拉框带筛选
	  public Collection<Bdf2Dept> getAllByName(String name) throws Exception {
		if(StringHelper.isNotEmpty(name)){
		    return this.query("from " + Bdf2Dept.class.getName() + " where name_ like '%" + name + "%'");
		} else {
		    return this.query("from " + Bdf2Dept.class.getName());
		}
	    }
}
