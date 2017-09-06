package org.tbs.dao.projs.creation;

/**
 * 1.1 项目受理
 * 
 * */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.stereotype.Component;
import org.tbs.dao.funs.El;
import org.tbs.dao.funs.GetSysInfo;
import org.tbs.entity.Bdf2User;
import org.tbs.entity.TbsBasConsway;
import org.tbs.entity.TbsBasPs;
import org.tbs.entity.TbsCustomer;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjBank;
import org.tbs.entity.TbsProjBizvt;
import org.tbs.entity.TbsProjCgg;
import org.tbs.entity.Vcggall;
import org.tbs.entity.VcggallSn;
import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Component
public class TbsProjCreateDao extends HibernateDao {
   
	@Resource
    private GetSysInfo gsi;
	
	 //@Resource El el;
    
	@DataProvider  // for single data with v-property
    public Collection<TbsProj> loadSingleProj(Integer param) throws Exception {
		List<TbsProj> results = new ArrayList<TbsProj>();
		Collection<TbsProj> tbsProjs = this.query("from " + TbsProj.class.getName() + " where id = " + param);
		for (TbsProj tbsProj:tbsProjs) {
			TbsProj targetData = EntityUtils.toEntity(tbsProj);
			List<Bdf2User> bdf2Users = (List<Bdf2User>) gsi.getCnameById(tbsProj.getKeyinId());
			EntityUtils.setValue(targetData, "VOcname", bdf2Users.get(0).getCname());
			results.add(targetData);
		}
		return results;
	}
	
	@DataProvider
    public Collection<Vcggall> getDy(Integer i) {
	Map<String, Object> map = new HashMap<String, Object>();
	List<Object> sns = this.query("select cggSn from "
		+ TbsProjCgg.class.getName());
	if (sns.size() == 0) {
	    sns.add("");
	}
	map.put("sns", sns);
	map.put("i", i);
	if (i != null) {
	    Collection<Vcggall> A = this.query(
		    "from " + Vcggall.class.getName() + " where cusid = :i"
			    + " and sn.sn not in (:sns)", map);
	    return A;
	} else {
	    return this.query("from " + Vcggall.class.getName());
	}
    }
	
	@DataProvider
    public void getAllForCfm(Page<TbsProj> page, Map<String, Object> params)
	    throws Exception {

	if (null != params) {
	    String whereCase = "";
	    String projName = (String) params.get("projName");
	    if (StringHelper.isNotEmpty(projName)) {
		whereCase += " AND projName like '%" + projName + "%' ";
	    }
	    if (params.get("tbsCustomer") instanceof TbsCustomer) {
		TbsCustomer tbsCustomer = (TbsCustomer) params
			.get("tbsCustomer");
		if (tbsCustomer != null && tbsCustomer.getId() != 0) {
		    whereCase += " AND tbsCustomer = " + tbsCustomer.getId();
		}
	    }
	    if (params.get("tbsBasConsway") instanceof TbsBasConsway) {
		TbsBasConsway tbsBasConsway = (TbsBasConsway) params
			.get("tbsBasConsway");
		if (tbsBasConsway != null && tbsBasConsway.getId() != 0) {
		    int tbsBasConswayid = tbsBasConsway.getId();
		    whereCase += " AND tbsBasConsway = " + tbsBasConswayid;
		}
	    }
	    Date acptdate = (Date) params.get("acptdate");
	    if (acptdate != null) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String acptdate_date = df.format(acptdate);
		whereCase += " AND acptdate = '" + acptdate_date + "'";
	    }
	    if (params.get("tbsBasPs") instanceof TbsBasPs) {
		TbsBasPs tbsBasPs = (TbsBasPs) params.get("tbsBasPs");
		if (tbsBasPs != null && tbsBasPs.getId() != 0) {
		    int tbsBasPsid = tbsBasPs.getId();
		    whereCase += " AND tbsBasPs = " + tbsBasPsid;
		}
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
	    String hql = "from " + TbsProj.class.getName() + " where del = 0 " +
	    		"and tbsBasPs.id not in (1,2,3,4,29,30,35) "
		    + whereCase + " order by id desc";

	    this.pagingQuery(page, hql, "select count(*) " + hql);

	} else {
	    String hql = "from " + TbsProj.class.getName()
		    + " where id is null and tbsBasPs.id not in (1,2,3,4,29,30,35)";
	    this.pagingQuery(page, hql, "select count(*) " + hql);
	}

	// v-property start
	List<TbsProj> results = new ArrayList<TbsProj>();
	Collection<TbsProj> tbsProjs = page.getEntities();
	for (TbsProj tbsProj : tbsProjs) {
	    TbsProj targetData = EntityUtils.toEntity(tbsProj);
	    List<Bdf2User> bdf2Users = (List<Bdf2User>) gsi
		    .getCnameById(tbsProj.getKeyinId());
	    EntityUtils.setValue(targetData, "VOcname", bdf2Users.get(0)
		    .getCname());
	    results.add(targetData);
	}
	page.setEntities(results); // v-property endding
    }

    @DataProvider
    public void getAllToPage(Page<TbsProj> page, Map<String, Object> params)
	    throws Exception {

	if (null != params) {
		//项目经理A，B显示控制
		//String whereCase = " AND (a_role_id =" + el.Uid()+" or b_role_id = " + el.Uid()+") "; 
		String whereCase = ""; 
	    String projName = (String) params.get("projName");
	    if (StringHelper.isNotEmpty(projName)) {
		whereCase += " AND projName like '%" + projName + "%' ";
	    }
	    if (params.get("tbsCustomer") instanceof TbsCustomer) {
		TbsCustomer tbsCustomer = (TbsCustomer) params
			.get("tbsCustomer");
		if (tbsCustomer != null && tbsCustomer.getId() != 0) {
		    whereCase += " AND tbsCustomer = " + tbsCustomer.getId();
		}
	    }
	    if (params.get("tbsBasConsway") instanceof TbsBasConsway) {
		TbsBasConsway tbsBasConsway = (TbsBasConsway) params
			.get("tbsBasConsway");
		if (tbsBasConsway != null && tbsBasConsway.getId() != 0) {
		    int tbsBasConswayid = tbsBasConsway.getId();
		    whereCase += " AND tbsBasConsway = " + tbsBasConswayid;
		}
	    }
	    Date acptdate = (Date) params.get("acptdate");
	    if (acptdate != null) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String acptdate_date = df.format(acptdate);
		whereCase += " AND acptdate = '" + acptdate_date + "'";
	    }
	    if (params.get("tbsBasPs") instanceof TbsBasPs) {
		TbsBasPs tbsBasPs = (TbsBasPs) params.get("tbsBasPs");
		if (tbsBasPs != null && tbsBasPs.getId() != 0) {
		    int tbsBasPsid = tbsBasPs.getId();
		    whereCase += " AND tbsBasPs = " + tbsBasPsid;
		}
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
	    String hql = "from " + TbsProj.class.getName() + " where del = 0 "
		    + whereCase + " order by id desc";

	    this.pagingQuery(page, hql, "select count(*) " + hql);

	} else {
	    String hql = "from " + TbsProj.class.getName()
		    + " where id is null";
	    this.pagingQuery(page, hql, "select count(*) " + hql);
	}

	// 以下为虚拟属性，可以整段注销，取消该属性
	List<TbsProj> results = new ArrayList<TbsProj>();
	Collection<TbsProj> tbsProjs = page.getEntities();
	for (TbsProj tbsProj : tbsProjs) {
	    TbsProj targetData = EntityUtils.toEntity(tbsProj);
	    List<Bdf2User> bdf2Users = (List<Bdf2User>) gsi
		    .getCnameById(tbsProj.getKeyinId());
	    EntityUtils.setValue(targetData, "VOcname", bdf2Users.get(0)
		    .getCname());
	    results.add(targetData);
	}
	page.setEntities(results); // 虚拟属性结束
    }

    @DataProvider
    public Collection<TbsProjBizvt> loadTbsProjBizvt(Integer id)
	    throws Exception {
	if (id != null) {
	    String hql = "from " + TbsProjBizvt.class.getName()
		    + " where del = 0 and proj_id= " + id;
	    return this.query(hql);
	} else {
	    return null;
	}
    }

    @DataProvider
    public Collection<TbsProjCgg> loadTbsProjCgg(Integer id) throws Exception {
	if (id != null) {
	    String hql = "from " + TbsProjCgg.class.getName()
		    + " where proj_id= " + id;
	    // 以下为虚拟属性，可以整段注销，取消该属性
	    List<TbsProjCgg> results = new ArrayList<TbsProjCgg>();
	    Collection<TbsProjCgg> tbsProjCggs = this.query(hql);
	    for (TbsProjCgg tbsProjCgg : tbsProjCggs) {
		TbsProjCgg targetData = EntityUtils.toEntity(tbsProjCgg);
		Collection<VcggallSn> vcggallsns = gsi.getCggStr(tbsProjCgg
			.getCggSn());
		EntityUtils.setValue(targetData, "cggStr", vcggallsns);
		// Collection<Vcggall> vcggalls
		// =gsi.getCggSn(tbsProjCgg.getCggSn());
		// EntityUtils.setValue(targetData, "cggSns", vcggalls);
		for (VcggallSn vcggallsn : vcggallsns) {
		    String vcggallsnchilds = vcggallsn.getCusname() + "--"
			    + vcggallsn.getCat1name() + "  "
			    + vcggallsn.getCat2name() + "  "
			    + vcggallsn.getCat3name();
		    EntityUtils.setValue(targetData, "cggCname",
			    vcggallsnchilds);
		}
		results.add(targetData);
	    }
	    return results;
	    // 虚拟属性结束
	} else {
	    return null;
	}
    }

    @DataProvider
    public Collection<TbsProjBank> loadTbsProjBank(Integer id) throws Exception {
	if (id != null) {
	    // String hql = "from " + TbsProjBank.class.getName()
	    // + " where del = 0 and proj_id= " + id;
	    String hql = "from " + TbsProjBank.class.getName()
		    + " where proj_id= " + id;
	    return this.query(hql);
	} else {
	    return null;
	}
    }

    @DataResolver
    public void save(Collection<TbsProj> tbsProjs) throws Exception {
	Session session = this.getSessionFactory().openSession();
	Date now = new Date();
	String uname = ContextHolder.getLoginUser().getUsername();
	GetSysInfo gsi = new GetSysInfo();
	try {
	    for (TbsProj tbsProj : tbsProjs) {
		EntityState state = EntityUtils.getState(tbsProj);
		if (state.equals(EntityState.NEW)) {
		    int uid = gsi.getUserID(uname, session);
		    tbsProj.setKeyinId(uid);
		    tbsProj.setTimestampInput(now);
		    tbsProj.setTimestampUpdate(now);
		    tbsProj.setSn("");
		    session.save(tbsProj);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    tbsProj.setTimestampUpdate(now);
		    session.update(tbsProj);
		}
		if (state.equals(EntityState.DELETED)) {
		    tbsProj.setDel(true);
		    session.update(tbsProj);// 把del改为1
		}

		// save all sub tables
		Collection<TbsProjBizvt> tbsProjBizvts = tbsProj
			.getTbsProjBizvtSet();
		if (tbsProjBizvts != null) {
		    for (TbsProjBizvt tbsProjBizvt : tbsProjBizvts) {
			tbsProjBizvt.setTbsProj(tbsProj);
		    }
		    saveTbsProjBizvt(tbsProjBizvts);
		}

		Collection<TbsProjCgg> tbsProjCggs = tbsProj.getTbsProjCggSet();
		if (tbsProjCggs != null) {
		    for (TbsProjCgg tbsProjCgg : tbsProjCggs) {
			tbsProjCgg.setTbsProj(tbsProj);
		    }
		    saveTbsProjCgg(tbsProjCggs);
		}

		Collection<TbsProjBank> tbsProjBanks = tbsProj
			.getTbsProjBankSet();
		if (tbsProjBanks != null) {
		    for (TbsProjBank tbsProjBank : tbsProjBanks) {
			tbsProjBank.setTbsProj(tbsProj);
		    }
		    saveTbsProjBank(tbsProjBanks);
		}

	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }

    @DataResolver
    public void saveTbsProjBizvt(Collection<TbsProjBizvt> tbsProjBizvts)
	    throws Exception {
	Session session = this.getSessionFactory().openSession();
	try {
	    for (TbsProjBizvt tbsProjBizvt : tbsProjBizvts) {
		EntityState state = EntityUtils.getState(tbsProjBizvt);
		if (state.equals(EntityState.NEW)) {
		    session.save(tbsProjBizvt);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    session.update(tbsProjBizvt);
		}
		if (state.equals(EntityState.DELETED)) {
		    tbsProjBizvt.setDel(true);
		    session.update(tbsProjBizvt);
		}
	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }

    @DataResolver
    public void saveTbsProjCgg(Collection<TbsProjCgg> tbsProjCggs)
	    throws Exception {
	Session session = this.getSessionFactory().openSession();
	try {
	    for (TbsProjCgg tbsProjCgg : tbsProjCggs) {
		EntityState state = EntityUtils.getState(tbsProjCgg);
		if (state.equals(EntityState.NEW)) {
		    tbsProjCgg.setTimestampInput(new Date());
		    session.save(tbsProjCgg);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    session.update(tbsProjCgg);
		}
		if (state.equals(EntityState.DELETED)) {
		    // tbsProjCgg.setDel(true);
		    // session.update(tbsProjCgg);
		    session.delete(tbsProjCgg);
		}
	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }

    @DataResolver
    public void saveTbsProjBank(Collection<TbsProjBank> tbsProjBanks)
	    throws Exception {
	Session session = this.getSessionFactory().openSession();
	try {
	    for (TbsProjBank tbsProjBank : tbsProjBanks) {
		EntityState state = EntityUtils.getState(tbsProjBank);
		if (state.equals(EntityState.NEW)) {
		    session.save(tbsProjBank);
		}
		if (state.equals(EntityState.MODIFIED)) {
		    session.update(tbsProjBank);
		}
		if (state.equals(EntityState.DELETED)) {
		    // tbsProjBank.setDel(true);
		    // session.update(tbsProjBank);
		    session.delete(tbsProjBank);
		}
	    }
	} finally {
	    session.flush();
	    session.close();
	}
    }
}
