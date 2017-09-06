package org.tbs.dao.projs.common;
/**
 * 用于查询项目的主要信息。 给main_view用
 * 主表：tbsproj
 * 子表：银行信息表，业务类品金，反担保信息：
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
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.stereotype.Component;
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

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;



@Component
public class TbsProjDao extends HibernateDao {

    @Resource
    private GetSysInfo gsi;
    
    @DataProvider
    public void getAllToPage(Page<TbsProj> page, Map<String, Object> params)
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
		whereCase += " AND acptdate = '"+ acptdate_date + "'";
	    }
	    if (params.get("tbsBasPs") instanceof TbsBasPs) {
		TbsBasPs tbsBasPs = (TbsBasPs) params.get("tbsBasPs");
		if (tbsBasPs != null && tbsBasPs.getId() != 0) {
			int tbsBasPsid = tbsBasPs.getId();
			whereCase += " AND tbsBasPs = " + tbsBasPsid;
		    }
	    }
	    if (null!=params.get("valid")) {
			//boolean valid = (Boolean)params.get("valid");
			Integer valid=(Integer)params.get("valid");
			if(1==valid){
				whereCase += " AND valid =1";
			}
			else if(0==valid){
				whereCase += " AND valid =0";
			}
			else{
			}
		}	    
	    String hql = "from " + TbsProj.class.getName() + " where del = 0 "
		    + whereCase + " order by id desc";

	    this.pagingQuery(page, hql, "select count(*) " + hql);
	} else {
	    String hql = "from " + TbsProj.class.getName() + " where id is null";
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
//	    String hql = "from " + TbsProjCgg.class.getName()
//		    + " where del = 0 and proj_id= " + id;
	    String hql = "from " + TbsProjCgg.class.getName()
		    + " where proj_id= " + id;
	    return this.query(hql);
	} else {
	    return null;
	}
    }

    @DataProvider
    public Collection<TbsProjBank> loadTbsProjBank(Integer id) throws Exception {
	if (id != null) {
//	    String hql = "from " + TbsProjBank.class.getName()
//		    + " where del = 0 and proj_id= " + id;
	    String hql = "from " + TbsProjBank.class.getName()
		    + " where proj_id= " + id;
	    return this.query(hql);
	} else {
	    return null;
	}
    }

    
    /*============== 以下为二期mainview的proj联动查询=================
    /*代偿审议专用 */
    @DataProvider 
    public void getAllForProjCompsry(Page<TbsProj> page, Map<String, Object> params)
	    throws Exception {
	if (null != params) 
	{
	    String whereCase = "";
	    String projName = (String) params.get("projName");
	    String qrytyp = (String) params.get("qrytyp");
	    if (StringHelper.isNotEmpty(projName)) {
		whereCase += " AND projName like '%" + projName + "%' ";
	    }
	    if (params.get("tbsCustomer") instanceof TbsCustomer) {
	    	TbsCustomer tbsCustomer = (TbsCustomer) params.get("tbsCustomer");
	    	if (tbsCustomer != null && tbsCustomer.getId() != 0) {
	    		whereCase += " AND tbsCustomer = " + tbsCustomer.getId();
	    	}
	    }
	    if (qrytyp.equals("1")){	
	    	String hql = "from " + TbsProj.class.getName() + " where del = 0 and valid =1 "
	    		+"and ps_id = 18 "
	    		+ whereCase + " order by id desc";
	    	this.pagingQuery(page, hql, "select count(*) " + hql);
	    }
	} else {
	    	String hql = "from " + TbsProj.class.getName() + " where id is null";
	    	this.pagingQuery(page, hql, "select count(*) " + hql);
			}
	}
    /*代偿请款专用 */
    @DataProvider 
    public void getAllForProjCompsryPay(Page<TbsProj> page, Map<String, Object> params)
    		throws Exception {
    	if (null != params) 
    	{
    		String whereCase = "";
    		String projName = (String) params.get("projName");
    		String qrytyp = (String) params.get("qrytyp");
    		if (StringHelper.isNotEmpty(projName)) {
    			whereCase += " AND projName like '%" + projName + "%' ";
    		}
    		if (params.get("tbsCustomer") instanceof TbsCustomer) {
    			TbsCustomer tbsCustomer = (TbsCustomer) params.get("tbsCustomer");
    			if (tbsCustomer != null && tbsCustomer.getId() != 0) {
    				whereCase += " AND tbsCustomer = " + tbsCustomer.getId();
    			}
    		}
    		if (qrytyp.equals("1")){	  
    			String hql = "from " + TbsProj.class.getName() + " where del = 0 and valid =1 "
    					+"and ps_id = 18  "
    					+ whereCase + " order by id desc";
    			this.pagingQuery(page, hql, "select count(*) " + hql);
    		}
    	} else {
    		String hql = "from " + TbsProj.class.getName() + " where id is null";
    		this.pagingQuery(page, hql, "select count(*) " + hql);
    	}
    }
    /*风险解除（追偿）专用*/
    @DataProvider 
    public void getAllForProjrol(Page<TbsProj> page, Map<String, Object> params)
    		throws Exception {
    	if (null != params) 
    	{
    		String whereCase = "";
    		String projName = (String) params.get("projName");
    		String qrytyp = (String) params.get("qrytyp");
    		if (StringHelper.isNotEmpty(projName)) {
    			whereCase += " AND projName like '%" + projName + "%' ";
    		}
    		if (params.get("tbsCustomer") instanceof TbsCustomer) {
    			TbsCustomer tbsCustomer = (TbsCustomer) params.get("tbsCustomer");
    			if (tbsCustomer != null && tbsCustomer.getId() != 0) {
    				whereCase += " AND tbsCustomer = " + tbsCustomer.getId();
    			}
    		}
    		if (qrytyp.equals("1")){	   
    			String hql = "from " + TbsProj.class.getName() + " where del = 0 and valid =1 "
    					+"and ps_id = 18 "
    					+ whereCase + " order by id desc";
    			this.pagingQuery(page, hql, "select count(*) " + hql);
    		}
    	} else {
    		String hql = "from " + TbsProj.class.getName() + " where id is null";
    		this.pagingQuery(page, hql, "select count(*) " + hql);
    	}
    }    
    
    /*项目解保专用 * 显示不同状态的proj记录*/
    @DataProvider 
    public void getAllForProjRelease(Page<TbsProj> page, Map<String, Object> params)
	    throws Exception {
	if (null != params) 
	{
	    String whereCase = "";
	    String projName = (String) params.get("projName");
	    String qrytyp = (String) params.get("qrytyp");
	    if (StringHelper.isNotEmpty(projName)) {
		whereCase += " AND projName like '%" + projName + "%' ";
	    }
	    if (params.get("tbsCustomer") instanceof TbsCustomer) {
	    	TbsCustomer tbsCustomer = (TbsCustomer) params.get("tbsCustomer");
	    	if (tbsCustomer != null && tbsCustomer.getId() != 0) {
	    		whereCase += " AND tbsCustomer = " + tbsCustomer.getId();
	    	}
	    }
	    if (qrytyp.equals("1")){	//项目解保   
	    	String hql = "from " + TbsProj.class.getName() + " where del = 0 and valid =1 "
	    		+"and ps_id = 18 "
	    		+ whereCase + " order by id desc";
	    	this.pagingQuery(page, hql, "select count(*) " + hql);
	    }
	} else {
	    	String hql = "from " + TbsProj.class.getName() + " where id is null";
	    	this.pagingQuery(page, hql, "select count(*) " + hql);
			}
	}
    
    
    /*项目变更专用 * 显示不同状态的proj记录*/
    @DataProvider 
    public void getAllForProjChange(Page<TbsProj> page, Map<String, Object> params)
	    throws Exception {
	if (null != params) 
	{
	    String whereCase = "";
	    String projName = (String) params.get("projName");
	    String qrytyp = (String) params.get("qrytyp");
	    if (StringHelper.isNotEmpty(projName)) {
		whereCase += " AND projName like '%" + projName + "%' ";
	    }
	    if (params.get("tbsCustomer") instanceof TbsCustomer) {
	    	TbsCustomer tbsCustomer = (TbsCustomer) params.get("tbsCustomer");
	    	if (tbsCustomer != null && tbsCustomer.getId() != 0) {
	    		whereCase += " AND tbsCustomer = " + tbsCustomer.getId();
	    	}
	    }
	    if (qrytyp.equals("1")){	// 三要素变更  	    
	    	String hql = "from " + TbsProj.class.getName() + " where del = 0 and valid=1 "
	    		+"and ps_id>8 and ps_id not in (21,22,26,27,28,29,30,31,32,34,35,36,37) "
	    		+ whereCase + " order by id desc";
	    	this.pagingQuery(page, hql, "select count(*) " + hql);
	    } else if (qrytyp.equals("2")){  //项目经理变更
	    	String hql = "from " + TbsProj.class.getName() + " where del = 0 and valid=1 "
		    		+""
		    		+ whereCase + " order by id desc";
		    this.pagingQuery(page, hql, "select count(*) " + hql);
	    } else if (qrytyp.equals("3")){  //项目非重要信息变更
	    	String hql = "from " + TbsProj.class.getName() + " where del = 0 and valid=1 "
		    		+""
		    		+ whereCase + " order by id desc";
		    this.pagingQuery(page, hql, "select count(*) " + hql);
		    
	    }
	} else {
	    	String hql = "from " + TbsProj.class.getName() + " where id is null";
	    	this.pagingQuery(page, hql, "select count(*) " + hql);
			}
	}
    
    /*定期，不定期检查*/
    @DataProvider
	public void getAllForProjCheck(Page<TbsProj> page, Map<String, Object> params)
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
			String hql = "from " + TbsProj.class.getName() + " where del = 0 and valid = 1 "
					+ " and  ps_id = 18 " + whereCase
					+ " order by id desc";
			this.pagingQuery(page, hql, "select count(*) " + hql);

		} else {
			String hql = "from " + TbsProj.class.getName()
					+ " where id is null";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		}
	}
    
    /*费用收退 mainview查询按钮调用*/
	@DataProvider
	public void getAllForTbsPtyexp(Page<TbsProj> page, Map<String, Object> params)
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
			String hql = "from " + TbsProj.class.getName()
					+ " where del = 0 and valid = 1 and ps_id = 18 "
					+ whereCase + " order by id desc";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		} else {
			String hql = "from " + TbsProj.class.getName()
					+ " where id is null ";
			this.pagingQuery(page, hql, "select count(*) " + hql);
		}
	}
	
	/*反担保物解除 TbsProjCggrevoke查询按钮调用*/
	@DataProvider
    public void getRevokeToPage(Page<TbsProj> page, Map<String, Object> params)
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
			Map<String, Object> map = new HashMap<String, Object>();
			List<Object> projsns = this.query("select distinct projSn from "
				+ TbsProjCgg.class.getName() + " where valid = 1");
			if (projsns.size() == 0) {
				projsns.add("");
			}
			map.put("projsns", projsns);
	    String hql = "from " + TbsProj.class.getName() + " where del = 0 and sn in (:projsns)"
		    + whereCase + " order by id desc";

	    this.pagingQuery(page, hql, "select count(*) " + hql,map);

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
    public Collection<TbsProjCgg> loadTbsProjCggForRevoke(Integer id) throws Exception {
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
}
