package org.tbs.tst;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.annotations.common.util.StringHelper;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import org.tbs.dao.bas.Bdf2UserDao;
import org.tbs.dao.funs.GetSysInfo;
import org.tbs.entity.Bdf2User;
import org.tbs.entity.TbsBasArea;
import org.tbs.entity.TbsBasEntproperty;
import org.tbs.entity.TbsBasEntscale;
import org.tbs.entity.TbsBasIndutype;
import org.tbs.entity.TbsCustomer;
import org.tbs.entity.TbsCustomerEnttype;
import org.tbs.entity.TbsCustomerIdnum;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Criteria;
import com.bstek.dorado.data.provider.Page;


public class TbsCustomerDao extends HibernateDao {

    @Resource
    private Bdf2UserDao bdf2UserDao;

    //配置虚属性
   /* @DataProvider
    public Collection<TbsCustomer> getAll() throws Exception {
	List<TbsCustomer> results = new ArrayList<TbsCustomer>();

	Collection<TbsCustomer> tbsCustomers = this.query("from TbsCustomer");

	for (TbsCustomer tbsCustomer : tbsCustomers) {
	    // 将对象转换为一个可以被Dorado处理的实体对象
	    TbsCustomer targetData = EntityUtils.toEntity(tbsCustomer);
	    List<Bdf2User> bdf2Users = (List<Bdf2User>)bdf2UserDao.getCnameById(tbsCustomer.getKeyinId());
	    EntityUtils.setValue(targetData, "cname", bdf2Users.get(0).getCname());
	    results.add(targetData);
	}

	return results;
    }
    
    //配置虚属性并实现分页查询
    @DataProvider
    public void getAllToPage(Page<TbsCustomer> page, Criteria criteria) throws Exception{
	DetachedCriteria detachedCriteria=this.buildDetachedCriteria(criteria, TbsCustomer.class);
//	this.pagingQuery(page, "from TbsCustomer", "select count(*) from TbsCustomer");
	this.pagingQuery(page, detachedCriteria);
	
	List<TbsCustomer> results = new ArrayList<TbsCustomer>();

	Collection<TbsCustomer> tbsCustomers = page.getEntities();

	for (TbsCustomer tbsCustomer : tbsCustomers) {
	    // 将对象转换为一个可以被Dorado处理的实体对象
	    TbsCustomer targetData = EntityUtils.toEntity(tbsCustomer);
	    List<Bdf2User> bdf2Users = (List<Bdf2User>)bdf2UserDao.getCnameById(tbsCustomer.getKeyinId());
	    EntityUtils.setValue(targetData, "cname", bdf2Users.get(0).getCname());
	    results.add(targetData);
	}
	
	page.setEntities(results);
    }*/

    @DataProvider
    public Collection<TbsCustomer> loadById() throws Exception {

	return this.query("from " + TbsCustomer.class.getName()
		+ " where id is null");
    }

    @DataProvider
    public void loadAll(Page<TbsCustomer> page, Map<String, Object> params)
	    throws Exception {
	if (null != params) {
	    String sn = (String) params.get("sn");
	    String name = (String) params.get("name");
	    String regaddr = (String) params.get("regaddr");
	    String operaddr = (String) params.get("operaddr");
	    String estdate = (String) params.get("estdate");
	    String legaler = (String) params.get("legaler");
	    TbsBasArea tbsBasArea = (TbsBasArea) params.get("tbsBasArea");
	    TbsBasEntproperty tbsBasEntproperty = (TbsBasEntproperty) params
		    .get("tbsBasEntproperty");
	    TbsBasEntscale tbsBasEntscale = (TbsBasEntscale) params
		    .get("tbsBasEntscale");
	    TbsBasIndutype tbsBasIndutype = (TbsBasIndutype) params
		    .get("tbsBasIndutype");

	    String whereCase = "";
	    if (StringHelper.isNotEmpty(sn)) {
		whereCase += " AND sn like '%" + sn + "%' ";
	    }

	    if (StringHelper.isNotEmpty(name)) {
		whereCase += " AND name like '%" + name + "%' ";
	    }

	    if (StringHelper.isNotEmpty(regaddr)) {
		whereCase += " AND regaddr like '%" + regaddr + "%' ";
	    }

	    if (StringHelper.isNotEmpty(operaddr)) {
		whereCase += " AND operaddr like '%" + operaddr + "%' ";
	    }
	    if (StringHelper.isNotEmpty(estdate)) {
		whereCase += " AND estdate like '%" + estdate + "%' ";
	    }

	    if (StringHelper.isNotEmpty(legaler)) {
		whereCase += " AND legaler like '%" + legaler + "%' ";
	    }

	    if (null != tbsBasArea && tbsBasArea.getId() != 0) {
		Integer tbsBasAreaid = tbsBasArea.getId();
		whereCase += " AND tbsBasArea = " + tbsBasAreaid;
	    }

	    if (null != tbsBasEntproperty && tbsBasEntproperty.getId() != 0) {
		Integer tbsBasEntpropertyid = tbsBasEntproperty.getId();
		whereCase += " AND tbsBasEntproperty=" + tbsBasEntpropertyid;
	    }
	    if (null != tbsBasEntscale && tbsBasEntscale.getId() != 0) {
		Integer tbsBasEntscaleid = tbsBasEntscale.getId();
		whereCase += " AND tbsBasEntscale=" + tbsBasEntscaleid;
	    }
	    if (null != tbsBasIndutype && tbsBasIndutype.getId() != 0) {
		Integer tbsBasIndutypeid = tbsBasIndutype.getId();
		whereCase += " AND tbsBasIndutype=" + tbsBasIndutypeid;
	    }
	    if (null != params.get("valid")) {
		// boolean valid = (Boolean)params.get("valid");
		Integer valid = (Integer) params.get("valid");
		if (1 == valid) {
		    whereCase += " AND valid =1";
		} else if (0 == valid) {
		    whereCase += " AND valid =0";
		} else {
		}
	    }
	    // return
	    // this.query("from "+TbsCustomer.class.getName()+" where 1=1 " +
	    // whereCase);
	    String hql = "from " + TbsCustomer.class.getName()
		    + " where Del=0 " + whereCase;
	    this.pagingQuery(page, hql, "select count(*) " + hql);
	} else {
	    String nohql = "from " + TbsCustomer.class.getName()
		    + " where id is null";
	    this.pagingQuery(page, nohql, "select count(*) " + nohql);
	}
	// return this.query("from " +
	// TbsCustomer.class.getName()+" where id is null");
    }

    @DataProvider
    public Collection<TbsCustomerEnttype> loadTbsCustomerEnttype(Integer Mid)
	    throws Exception {
	if (Mid != null) {
	    Map<String, Object> param = new HashMap<String, Object>();
	    param.put("mid", Mid);
	    String hql = "from " + TbsCustomerEnttype.class.getName()
		    + " where cus_id= :mid";
	    return this.query(hql, param);
	} else {
	    return null;
	}
    }

    @DataProvider
    public Collection<TbsCustomerIdnum> loadTbsCustomerIdnum(Integer Mid)
	    throws Exception {
	if (Mid != null) {
	    Map<String, Object> param = new HashMap<String, Object>();
	    param.put("mid", Mid);
	    String hql = "from " + TbsCustomerIdnum.class.getName()
		    + " where cus_id= :mid";
	    return this.query(hql, param);
	} else {
	    return null;
	}
    }

    @DataResolver
    public void save(Collection<TbsCustomer> tbsCustomers) throws Exception {
	Session session = this.getSessionFactory().openSession();
	Date now = new Date();
	String uname = ContextHolder.getLoginUser().getUsername();
	GetSysInfo gsi = new GetSysInfo();
	try {
	    for (TbsCustomer tbsCustomer : tbsCustomers) {
		EntityState state = EntityUtils.getState(tbsCustomer);
		if (state.equals(EntityState.NEW)) {
		    // int uid = gsi.getUserID(uname,session);
		    // String cname = gsi.getUserCName(uname,session);
		    tbsCustomer.setTimestampInput(now);
		    tbsCustomer.setTimestampUpdate(now);
		    session.save(tbsCustomer);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    tbsCustomer.setTimestampUpdate(now);
		    session.update(tbsCustomer);
		}
		if (state.equals(EntityState.DELETED)) {
		    tbsCustomer.setDel(true);
		    session.update(tbsCustomer);// 把del改为1
		}
		Collection<TbsCustomerIdnum> idnumchilds = tbsCustomer
			.getTbsCustomerIdnumSet();
		if (idnumchilds != null) {
		    for (TbsCustomerIdnum idnumchild : idnumchilds) {
			idnumchild.setTbsCustomer(tbsCustomer);
		    }
		    saveAllidnum(idnumchilds);
		}
		Collection<TbsCustomerEnttype> enttypechilds = tbsCustomer
			.getTbsCustomerEnttypeSet();
		if (enttypechilds != null) {
		    for (TbsCustomerEnttype enttypechild : enttypechilds) {
			enttypechild.setTbsCustomer(tbsCustomer);
		    }
		    saveAllenttype(enttypechilds);
		}

	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }

    @DataResolver
    public void saveAllidnum(Collection<TbsCustomerIdnum> tbsCustomerIdnums)
	    throws Exception {
	Session session = this.getSessionFactory().openSession();
	try {
	    for (TbsCustomerIdnum tbsCustomerIdnum : tbsCustomerIdnums) {
		EntityState state = EntityUtils.getState(tbsCustomerIdnum);
		if (state.equals(EntityState.NEW)) {
		    session.save(tbsCustomerIdnum);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    session.update(tbsCustomerIdnum);
		}
		if (state.equals(EntityState.DELETED)) {
		    session.delete(tbsCustomerIdnum);
		}
	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }

    @DataResolver
    public void saveAllenttype(
	    Collection<TbsCustomerEnttype> tbsCustomerEnttypes)
	    throws Exception {
	Session session = this.getSessionFactory().openSession();
	try {
	    for (TbsCustomerEnttype tbsCustomerEnttype : tbsCustomerEnttypes) {
		EntityState state = EntityUtils.getState(tbsCustomerEnttype);
		if (state.equals(EntityState.NEW)) {
		    session.save(tbsCustomerEnttype);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    session.update(tbsCustomerEnttype);
		}
		if (state.equals(EntityState.DELETED)) {
		    session.delete(tbsCustomerEnttype);
		}
	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }
}
