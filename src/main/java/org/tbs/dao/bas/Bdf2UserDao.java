package org.tbs.dao.bas;

import java.util.Collection;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.stereotype.Component;
import org.tbs.entity.Bdf2User;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;

@Component
public class Bdf2UserDao extends HibernateDao {

	@DataProvider
	public Collection<Bdf2User> loadAll() throws Exception {
		return this.query("from " + Bdf2User.class.getName());
	}

  @DataProvider  //下拉框带筛选
  public Collection<Bdf2User> getAllByName(String name) throws Exception {
	if(StringHelper.isNotEmpty(name)){
	    return this.query("from " + Bdf2User.class.getName() + " where ename not like 'adm%' and cname like '%" + name + "%' ");
	} else {
	    return this.query("from " + Bdf2User.class.getName() + " where ename not like 'adm%' " );
	}
    }
  
  @DataProvider  //下拉框请印审批专用
  public Collection<Bdf2User> getCName(Collection<String> name) throws Exception {
	  String cname ="";
	if(!name.isEmpty()){
		for(String n:name){
			cname +=n+"','";
		}
	    return this.query("from " + Bdf2User.class.getName() + " where ename not like 'adm%' and cname in ('" + cname + "null') ");
	} else {
	    return this.query("from " + Bdf2User.class.getName() + " where ename not like 'adm%' " );
	}
    }
}
