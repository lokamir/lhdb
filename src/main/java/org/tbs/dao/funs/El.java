package org.tbs.dao.funs;

import java.util.Date;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.Bdf2Dept;
import org.tbs.entity.Bdf2User;
import org.tbs.entity.TbsBasArea;
import org.tbs.entity.TbsBasBank;
import org.tbs.entity.TbsBasCggtyp;
import org.tbs.entity.TbsBasConsway;
import org.tbs.entity.TbsBasCourt;
import org.tbs.entity.TbsBasCurrency;
import org.tbs.entity.TbsBasEntproperty;
import org.tbs.entity.TbsBasEntscale;
import org.tbs.entity.TbsBasGovfund;
import org.tbs.entity.TbsBasIdcardtype;
import org.tbs.entity.TbsBasIndutype;
import org.tbs.entity.TbsBasPs;
import org.tbs.entity.TbsCustomer;
import org.tbs.entity.TbsProj;
import org.tbs.entity.Vcggall;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;

@Component
public class El extends HibernateDao {

	// 获取当前时间
	// ${dorado.getDataProvider("el#GetDate").getResult()}
	@DataProvider
	public Date GetDate() {
		Date now = new Date();
		return now;
	}

	// 获取当前登录人ID
	// ${dorado.getDataProvider("el#Uid").getResult()}
	@DataProvider
	public int Uid() {
		Session session = this.getSessionFactory().openSession();
		try {
			int uid;
			String str_uid;
			String UserName = ContextHolder.getLoginUser().getCname();
			String sql = "select id from bdf2_user where cname_='" + UserName
					+ "'";
			SQLQuery query = session.createSQLQuery(sql);
			str_uid = query.uniqueResult().toString();
			uid = Integer.parseInt(str_uid);
			return uid;
		} finally {
			//session.clear();
			session.flush();
			session.close();
		}
	}

	/*
	 * // 获取当前登录人部门ID并判断 已无效 //
	 * ${dorado.getDataProvider("el#Deptid").getResult()}
	 * 
	 * @DataProvider public int Deptid(){ List<Integer> uid=new
	 * ArrayList<Integer>(); List<IDept> listDepts = new ArrayList<IDept>();
	 * listDepts=ContextHolder.getLoginUser().getDepts(); for(IDept listDept :
	 * listDepts){ if
	 * (listDept.getName().contains("风险")||listDept.getName().contains("法务") ){
	 * uid.add(Integer.parseInt(listDept.getId())); } }
	 * 
	 * return uid.get(0); }
	 */

	// 部门名称与部门id映射 用于DataType的mapValues属性
	// ${dorado.getDataProvider("el#mapBdf2Depts").getResult()}

	@DataProvider
	public Map<Integer, String> mapBdf2Depts() {
		List<Bdf2Dept> bdf2Depts = this.query("from "
				+ Bdf2Dept.class.getName());
		Map<Integer, String> mapValue = new LinkedHashMap<Integer, String>();
		mapValue.put(null, "");
		for (Bdf2Dept bdf2Dept : bdf2Depts) {
			mapValue.put(Integer.parseInt(bdf2Dept.getId()), bdf2Dept.getName());
		}
		return mapValue;
	}

	// 用户名与用户id映射 用于DataType的mapValues属性
	// ${dorado.getDataProvider("el#mapBdf2Users").getResult()}
	@DataProvider
	public Map<Integer, String> mapBdf2Users() {
		List<Bdf2User> bdf2Users = this.query("from "
				+ Bdf2User.class.getName());
		Map<Integer, String> mapValue = new LinkedHashMap<Integer, String>();
		mapValue.put(null, "");
		for (Bdf2User bdf2User : bdf2Users) {
			mapValue.put(bdf2User.getId(), bdf2User.getCname());
		}
		return mapValue;
	}
	
	// 用户名与用户名映射 用于DataType的mapValues属性
		// ${dorado.getDataProvider("el#mapBdf2Usernames").getResult()}
		@DataProvider
		public Map<String, String> mapBdf2Usernames() {
			List<Bdf2User> bdf2Users = this.query("from "
					+ Bdf2User.class.getName());
			Map<String, String> mapValue = new LinkedHashMap<String, String>();
			for (Bdf2User bdf2User : bdf2Users) {
				mapValue.put(bdf2User.getUsername(), bdf2User.getCname());
			}
			return mapValue;
		}

	// 是否有效
	// ${dorado.getDataProvider("el#mapValid").getResult()}
	@DataProvider
	public Map<String, String> mapValid() {
		Map<String, String> mapValue = new LinkedHashMap<String, String>();
		mapValue.put("2", "全部");
		mapValue.put("1", "是");
		mapValue.put("0", "否");
		return mapValue;
	}

	// 银行与银行id映射 用于DataType的mapValues属性
	// ${dorado.getDataProvider("el#mapTbsBasBanks").getResult()}
	@DataProvider
	public Map<Integer, String> mapTbsBasBanks() {
		List<TbsBasBank> tbsBasBanks = this.query("from "
				+ TbsBasBank.class.getName());
		Map<Integer, String> mapValue = new LinkedHashMap<Integer, String>();
		mapValue.put(null, "");
		for (TbsBasBank tbsBasBank : tbsBasBanks) {
			mapValue.put(tbsBasBank.getId(), tbsBasBank.getName());
		}
		return mapValue;
	}

	// proj_creaet_cgg映射 用于DataType的mapValues属性
	// ${dorado.getDataProvider("el#mapCgg").getResult()}
	@DataProvider
	public Map<String, String> mapCgg(Integer param) {
		List<Vcggall> vcggalls = this.query("from " + Vcggall.class.getName()
				+ " a where cusid=" + param); // 85是一个传入的参数
		Map<String, String> mapValue = new LinkedHashMap<String, String>();
		mapValue.put(null, "");
		for (Vcggall vcggall : vcggalls) {
			mapValue.put(vcggall.getSn().getSn(), vcggall.getSn().getCusname()
					+ " || " + vcggall.getSn().getCat1name() + " || "
					+ vcggall.getSn().getCat2name() + " || "
					+ vcggall.getSn().getCat3name());
		}
		return mapValue;
	}

	// CFM的评审意见
	// ${dorado.getDataProvider("el#mapApr").getResult()}
	@DataProvider
	public Map<String, String> mapApr() {
		Map<String, String> mapValue = new LinkedHashMap<String, String>();
		mapValue.put("2", "回避");
		mapValue.put("1", "同意");
		mapValue.put("0", "反对");
		mapValue.put("3", "未知");
		return mapValue;
	}

	// 是否为科技型企业
	// ${dorado.getDataProvider("el#mapCfm1By1").getResult()}
	@DataProvider
	public Map<String, String> mapCfm1By1() {
		Map<String, String> mapValue = new LinkedHashMap<String, String>();
		mapValue.put("1", "是");
		mapValue.put("0", "否");
		return mapValue;
	}

	// 二期审批状态，用valid来判断 0=未发送审批,1=审批通过,2=审批中,3=驳回
	// ${dorado.getDataProvider("el#mapValidFor2").getResult()}
	@DataProvider
	public Map<String, String> mapValidFor2() {
		Map<String, String> mapValue = new LinkedHashMap<String, String>();
		mapValue.put("0", "未审批");
		mapValue.put("1", "审批通过");
		mapValue.put("2", "审批中");
		mapValue.put("3", "审批驳回");
		return mapValue;
	}
	
	// 二期审批状态，用valid来判断 不定期检查用
		// ${dorado.getDataProvider("el#mapValidForIreginsp").getResult()}
		@DataProvider
		public Map<String, String> mapValidForIreginsp() {
			Map<String, String> mapValue = new LinkedHashMap<String, String>();
			mapValue.put("0", "未审批");
			mapValue.put("1", "审批通过");
			mapValue.put("2", "审批中");
			mapValue.put("3", "审批通过");
			return mapValue;
		}

	// 二期反担保解除用State判断反担保物状态
	// ${dorado.getDataProvider("el#mapCggRevokeState").getResult()}
	@DataProvider
	public Map<String, String> mapCggRevokeState() {
		Map<String, String> mapValue = new LinkedHashMap<String, String>();
		mapValue.put("", "");
		mapValue.put("0", "");
		mapValue.put("1", "反担保物解除中");
		mapValue.put("2", "反担保物解除被驳回");
		return mapValue;
	}

	// 客户名与客户id映射 用于DataType的mapValues属性
	// ${dorado.getDataProvider("el#mapCustomer").getResult()}
	@DataProvider
	public Map<Integer, String> mapCustomer() {
		List<TbsCustomer> customers = this.query("from "
				+ TbsCustomer.class.getName());
		Map<Integer, String> mapValue = new LinkedHashMap<Integer, String>();
		mapValue.put(null, "");
		for (TbsCustomer customer : customers) {
			mapValue.put(customer.getId(), customer.getName());
		}
		return mapValue;
	}

	// 以下为import专用
	// 用户名与用户id映射
	@DataProvider
	public Map<String, Integer> mapUid() {
		List<Bdf2User> users = this.query("from " + Bdf2User.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (Bdf2User user : users) {
			mapValue.put(user.getCname(), user.getId());
		}
		return mapValue;
	}

	// 客户名称和客户Id映射
	@DataProvider
	public Map<String, Integer> mapCustomerId() {
		List<TbsCustomer> customers = this.query("from "
				+ TbsCustomer.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsCustomer customer : customers) {
			mapValue.put(customer.getName(), customer.getId());
		}
		return mapValue;
	}

	// 反担保类型和反担保id映射
	@DataProvider
	public Map<String, Integer> mapCggTyp() {
		List<TbsBasCggtyp> tbsBasCggtyps = this.query("from "
				+ TbsBasCggtyp.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsBasCggtyp tbsBasCggtyp : tbsBasCggtyps) {
			mapValue.put(tbsBasCggtyp.getName(), tbsBasCggtyp.getId());
		}
		return mapValue;
	}

	// 反担保类型和反担保id映射
	@DataProvider
	public Map<String, Integer> mapIdcardtype() {
		List<TbsBasIdcardtype> tbsBasIdcardtypes = this.query("from "
				+ TbsBasIdcardtype.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsBasIdcardtype tbsBasIdcardtype : tbsBasIdcardtypes) {
			mapValue.put(tbsBasIdcardtype.getName(), tbsBasIdcardtype.getId());
		}
		return mapValue;
	}

	// 反担保类型和反担保id映射
	@DataProvider
	public Map<String, Integer> mapArea() {
		List<TbsBasArea> tbsBasAreas = this.query("from "
				+ TbsBasArea.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsBasArea tbsBasArea : tbsBasAreas) {
			mapValue.put(tbsBasArea.getName(), tbsBasArea.getId());
		}
		return mapValue;
	}
	
	// 企业性质
	@DataProvider
	public Map<String, Integer> mapEntproperty() {
		List<TbsBasEntproperty> tbsBasEntpropertys = this.query("from "
				+ TbsBasEntproperty.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsBasEntproperty tbsBasEntproperty : tbsBasEntpropertys) {
			mapValue.put(tbsBasEntproperty.getName(), tbsBasEntproperty.getId());
		}
		return mapValue;
	}
	
	// 企业规模
	@DataProvider
	public Map<String, Integer> mapEntscale() {
		List<TbsBasEntscale> tbsBasEntscales = this.query("from "
				+ TbsBasEntscale.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsBasEntscale tbsBasEntscale : tbsBasEntscales) {
			mapValue.put(tbsBasEntscale.getName(), tbsBasEntscale.getId());
		}
		return mapValue;
	}
	
	// 行业类型
	@DataProvider
	public Map<String, Integer> mapIndutype() {
		List<TbsBasIndutype> tbsBasIndutypes = this.query("from "
				+ TbsBasIndutype.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsBasIndutype tbsBasIndutype : tbsBasIndutypes) {
			mapValue.put(tbsBasIndutype.getName(), tbsBasIndutype.getId());
		}
		return mapValue;
	}
	
	// 政府基金
	@DataProvider
	public Map<String, Integer> mapGovfund() {
		List<TbsBasGovfund> tbsBasGovfunds = this.query("from "
				+ TbsBasGovfund.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsBasGovfund tbsBasGovfund : tbsBasGovfunds) {
			mapValue.put(tbsBasGovfund.getName(), tbsBasGovfund.getId());
		}
		return mapValue;
	}
	
	// 担保币种
	@DataProvider
	public Map<String, Integer> mapCurrency() {
		List<TbsBasCurrency> tbsBasCurrencys = this.query("from "
				+ TbsBasCurrency.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsBasCurrency tbsBasCurrency : tbsBasCurrencys) {
			mapValue.put(tbsBasCurrency.getName(), tbsBasCurrency.getId());
		}
		return mapValue;
	}
	
	// 咨询方式
	@DataProvider
	public Map<String, Integer> mapConsway() {
		List<TbsBasConsway> tbsBasConsways = this.query("from "
				+ TbsBasConsway.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsBasConsway tbsBasConsway : tbsBasConsways) {
			mapValue.put(tbsBasConsway.getName(), tbsBasConsway.getId());
		}
		return mapValue;
	}

	// 法院
	@DataProvider
	public Map<String, Integer> mapCourt() {
		List<TbsBasCourt> tbsBasCourts = this.query("from "
				+ TbsBasCourt.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsBasCourt tbsBasCourt : tbsBasCourts) {
			mapValue.put(tbsBasCourt.getName(), tbsBasCourt.getId());
		}
		return mapValue;
	}

	// Ps_id
	@DataProvider
	public Map<String, Integer> mapPsid() {
		List<TbsBasPs> tbsBasPss = this.query("from "
				+ TbsBasPs.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsBasPs tbsBasPs : tbsBasPss) {
			mapValue.put(tbsBasPs.getName(), tbsBasPs.getId());
		}
		return mapValue;
	}
	
	// proj_id
	@DataProvider
	public Map<String, Integer> mapProjid() {
		List<TbsProj> tbsProjs = this.query("from "
				+ TbsProj.class.getName());
		Map<String, Integer> mapValue = new LinkedHashMap<String, Integer>();
		for (TbsProj tbsProj : tbsProjs) {
			mapValue.put(tbsProj.getProjName(), tbsProj.getId());
		}
		return mapValue;
	}
	// proj_sn
	@DataProvider
	public Map<String, String> mapProjsn() {
		List<TbsProj> tbsProjs = this.query("from "
				+ TbsProj.class.getName());
		Map<String, String> mapValue = new LinkedHashMap<String, String>();
		for (TbsProj tbsProj : tbsProjs) {
			mapValue.put(tbsProj.getProjName(), tbsProj.getSn());
		}
		return mapValue;
	}

}
