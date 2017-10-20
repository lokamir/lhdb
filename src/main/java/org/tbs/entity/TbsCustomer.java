package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_customer:客户基本信息
 */
@Entity
@Table(name = "tbs_customer")
public class TbsCustomer implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 客户编号.'锡联客号+日期+序号（每个日期后都是001开始）':客户编号.'锡联客号+日期+序号（每个日期后都是001开始）'
	 */
	private String sn;

	/**
	 * 是否有效，默认为0无效，审批过后为1有效。:是否有效，默认为0无效，审批过后为1有效。
	 */
	private boolean valid;

	/**
	 * 输入时间戳，默认值为当前日期:输入时间戳，默认值为当前日期
	 */
	private Date timestampInput;

	/**
	 * 最近一次更新的时间戳，update时同时更新此字段:最近一次更新的时间戳，update时同时更新此字段
	 */
	private Date timestampUpdate;

	/**
	 * 录入人:录入人
	 */
	private int keyinId;

	/**
	 * 客户名称:客户名称
	 */
	private String name;

	/**
	 * 注册地:注册地
	 */
	private String regaddr;

	/**
	 * 实际经营地址:实际经营地址
	 */
	private String operaddr;

	/**
	 * 成立日期:成立日期
	 */
	private Date estdate;

	/**
	 * 实收资本(元):实收资本(元)
	 */
	private BigDecimal puc;

	/**
	 * 法定代表人:法定代表人
	 */
	private String legaler;

	/**
	 * 股东:股东
	 */
	private String stockholder;

	/**
	 * 主营业务概况:主营业务概况
	 */
	private String mainbiz;

	/**
	 * 上年度末资产合计（元):上年度末资产合计（元)
	 */
	private BigDecimal lyta;

	/**
	 * 上年度末负债合计(元):上年度末负债合计(元)
	 */
	private BigDecimal lytl;

	/**
	 * 上年度末净资产合计（元）:上年度末净资产合计（元）
	 */
	private BigDecimal lytna;

	/**
	 * 最近一期资产合计（元):最近一期资产合计（元)
	 */
	private BigDecimal rita;

	/**
	 * 最近一期负债合计（元):最近一期负债合计（元)
	 */
	private BigDecimal ritl;

	/**
	 * 最近一期净资产合计（元):最近一期净资产合计（元)
	 */
	private BigDecimal ritna;

	/**
	 * 上年度主营业务收入(元):上年度主营业务收入(元)
	 */
	private BigDecimal lymbi;

	/**
	 * 上年度净利润总额(元):上年度净利润总额(元)
	 */
	private BigDecimal lyntp;

	/**
	 * 最近一期本年累计主营业务收入（元）:最近一期本年累计主营业务收入（元）
	 */
	private BigDecimal rimbi;

	/**
	 * 最近一期本年累计净利润总额（元）:最近一期本年累计净利润总额（元）
	 */
	private BigDecimal rintp;

	/**
	 * 职工人数:职工人数
	 */
	private int employee;

	/**
	 * 联系人:联系人
	 */
	private String contacts;

	/**
	 * 职位:职位
	 */
	private String title;

	/**
	 * 电话:电话
	 */
	private String tel;

	/**
	 * MEMO:
	 */
	private String memo;

	/**
	 * 删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据:删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据
	 */
	private boolean del;

	/**
	 * 关联文件上传FID:关联文件上传FID
	 */
	private String fulId;

	/**
	 * BY1:
	 */
	private String by1;

	/**
	 * BY2:
	 */
	private String by2;

	/**
	 * BY3:
	 */
	private String by3;

	/**
	 * tbs_bas_area:客户基本信息-区域
	 */
	private TbsBasArea tbsBasArea;

	/**
	 * tbs_bas_entproperty:客户基本信息-企业性质
	 */
	private TbsBasEntproperty tbsBasEntproperty;

	/**
	 * tbs_bas_entscale:客户基本信息-企业规模
	 */
	private TbsBasEntscale tbsBasEntscale;

	/**
	 * tbs_bas_indutype:客户基本信息-行业类型
	 */
	private TbsBasIndutype tbsBasIndutype;

	/**
	 * tbs_casher:出纳费用确认
	 */
	private Set<TbsCasher> tbsCasherSet = new HashSet<TbsCasher>(0);

	/**
	 * tbs_cgg_bzjzy:反担保信息-保证金质押
	 */
	private Set<TbsCggBzjzy> tbsCggBzjzySet = new HashSet<TbsCggBzjzy>(0);

	/**
	 * tbs_cgg_dy:反担保信息-抵押
	 */
	private Set<TbsCggDy> tbsCggDySet = new HashSet<TbsCggDy>(0);

	/**
	 * tbs_cgg_grbz:反担保信息-个人保证
	 */
	private Set<TbsCggGrbz> tbsCggGrbzSet = new HashSet<TbsCggGrbz>(0);

	/**
	 * tbs_cgg_qybz:反担保信息-企业保证
	 */
	private Set<TbsCggQybz> tbsCggQybzSet = new HashSet<TbsCggQybz>(0);

	/**
	 * tbs_cgg_zy:反担保信息-质押
	 */
	private Set<TbsCggZy> tbsCggZySet = new HashSet<TbsCggZy>(0);

	/**
	 * tbs_customer_enttype:客户基本资料子表-企业类型
	 */
	private Set<TbsCustomerEnttype> tbsCustomerEnttypeSet = new HashSet<TbsCustomerEnttype>(
			0);

	/**
	 * tbs_customer_idnum:客户基本资料子表-证件类型与号码
	 */
	private Set<TbsCustomerIdnum> tbsCustomerIdnumSet = new HashSet<TbsCustomerIdnum>(
			0);

	/**
	 * tbs_proj:项目基本信息主表
	 */
	private Set<TbsProj> tbsProjSet = new HashSet<TbsProj>(0);

	/**
	 * tbs_projundwrt:承保审批单
	 */
	private Set<TbsProjundwrt> tbsProjundwrtSet = new HashSet<TbsProjundwrt>(0);
	
	/**
	 * 二期改库 2016-06-01
	 * */
	private BigDecimal compsry;
	private BigDecimal projrol;
	private Set<TbsProjcompsryPay> tbsProjcompsryPaySet = new HashSet<TbsProjcompsryPay>(0);
	
	
	public TbsCustomer() {
		super();
	}

	public TbsCustomer(int id, String sn, boolean valid, Date timestampInput,
			Date timestampUpdate, int keyinId, String name, String regaddr,
			String operaddr, Date estdate, BigDecimal puc, String legaler,
			String stockholder, String mainbiz, BigDecimal lyta,
			BigDecimal lytl, BigDecimal lytna, BigDecimal rita,
			BigDecimal ritl, BigDecimal ritna, BigDecimal lymbi,
			BigDecimal lyntp, BigDecimal rimbi, BigDecimal rintp, int employee,
			String contacts, String title, String tel, String memo,
			boolean del, String fulId, String by1, String by2, String by3,
			TbsBasArea tbsBasArea, TbsBasEntproperty tbsBasEntproperty,
			TbsBasEntscale tbsBasEntscale, TbsBasIndutype tbsBasIndutype,
			Set<TbsCasher> tbsCasherSet, Set<TbsCggBzjzy> tbsCggBzjzySet,
			Set<TbsCggDy> tbsCggDySet, Set<TbsCggGrbz> tbsCggGrbzSet,
			Set<TbsCggQybz> tbsCggQybzSet, Set<TbsCggZy> tbsCggZySet,
			Set<TbsCustomerEnttype> tbsCustomerEnttypeSet,
			Set<TbsCustomerIdnum> tbsCustomerIdnumSet, Set<TbsProj> tbsProjSet,
			Set<TbsProjundwrt> tbsProjundwrtSet, BigDecimal compsry,BigDecimal projrol,
			Set<TbsProjcompsryPay> tbsProjcompsryPaySet) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.keyinId = keyinId;
		this.name = name;
		this.regaddr = regaddr;
		this.operaddr = operaddr;
		this.estdate = estdate;
		this.puc = puc;
		this.legaler = legaler;
		this.stockholder = stockholder;
		this.mainbiz = mainbiz;
		this.lyta = lyta;
		this.lytl = lytl;
		this.lytna = lytna;
		this.rita = rita;
		this.ritl = ritl;
		this.ritna = ritna;
		this.lymbi = lymbi;
		this.lyntp = lyntp;
		this.rimbi = rimbi;
		this.rintp = rintp;
		this.employee = employee;
		this.contacts = contacts;
		this.title = title;
		this.tel = tel;
		this.memo = memo;
		this.del = del;
		this.fulId = fulId;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsBasArea = tbsBasArea;
		this.tbsBasEntproperty = tbsBasEntproperty;
		this.tbsBasEntscale = tbsBasEntscale;
		this.tbsBasIndutype = tbsBasIndutype;
		this.tbsCasherSet = tbsCasherSet;
		this.tbsCggBzjzySet = tbsCggBzjzySet;
		this.tbsCggDySet = tbsCggDySet;
		this.tbsCggGrbzSet = tbsCggGrbzSet;
		this.tbsCggQybzSet = tbsCggQybzSet;
		this.tbsCggZySet = tbsCggZySet;
		this.tbsCustomerEnttypeSet = tbsCustomerEnttypeSet;
		this.tbsCustomerIdnumSet = tbsCustomerIdnumSet;
		this.tbsProjSet = tbsProjSet;
		this.tbsProjundwrtSet = tbsProjundwrtSet;
		this.compsry = compsry;
		this.projrol = projrol;
		this.tbsProjcompsryPaySet = tbsProjcompsryPaySet;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	public int getId() {
		return id;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "SN", length = 20, unique = true, nullable = false)
	public String getSn() {
		return sn;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
	}

	public void setTimestampInput(Date timestampInput) {
		this.timestampInput = timestampInput;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_INPUT", nullable = false)
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setTimestampUpdate(Date timestampUpdate) {
		this.timestampUpdate = timestampUpdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_UPDATE", nullable = false)
	public Date getTimestampUpdate() {
		return timestampUpdate;
	}

	public void setKeyinId(int keyinId) {
		this.keyinId = keyinId;
	}

	@Column(name = "KEYIN_ID")
	public int getKeyinId() {
		return keyinId;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 200, nullable = false)
	public String getName() {
		return name;
	}

	public void setRegaddr(String regaddr) {
		this.regaddr = regaddr;
	}

	@Column(name = "REGADDR", length = 500)
	public String getRegaddr() {
		return regaddr;
	}

	public void setOperaddr(String operaddr) {
		this.operaddr = operaddr;
	}

	@Column(name = "OPERADDR", length = 500)
	public String getOperaddr() {
		return operaddr;
	}

	public void setEstdate(Date estdate) {
		this.estdate = estdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ESTDATE")
	public Date getEstdate() {
		return estdate;
	}

	public void setPuc(BigDecimal puc) {
		this.puc = puc;
	}

	@Column(name = "PUC")
	public BigDecimal getPuc() {
		return puc;
	}

	public void setLegaler(String legaler) {
		this.legaler = legaler;
	}

	@Column(name = "LEGALER", length = 10)
	public String getLegaler() {
		return legaler;
	}

	public void setStockholder(String stockholder) {
		this.stockholder = stockholder;
	}

	@Column(name = "STOCKHOLDER", length = 500)
	public String getStockholder() {
		return stockholder;
	}

	public void setMainbiz(String mainbiz) {
		this.mainbiz = mainbiz;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "MAINBIZ", columnDefinition = "CLOB")
	public String getMainbiz() {
		return mainbiz;
	}

	public void setLyta(BigDecimal lyta) {
		this.lyta = lyta;
	}

	@Column(name = "LYTA")
	public BigDecimal getLyta() {
		return lyta;
	}

	public void setLytl(BigDecimal lytl) {
		this.lytl = lytl;
	}

	@Column(name = "LYTL")
	public BigDecimal getLytl() {
		return lytl;
	}

	public void setLytna(BigDecimal lytna) {
		this.lytna = lytna;
	}

	@Column(name = "LYTNA")
	public BigDecimal getLytna() {
		return lytna;
	}

	public void setRita(BigDecimal rita) {
		this.rita = rita;
	}

	@Column(name = "RITA")
	public BigDecimal getRita() {
		return rita;
	}

	public void setRitl(BigDecimal ritl) {
		this.ritl = ritl;
	}

	@Column(name = "RITL")
	public BigDecimal getRitl() {
		return ritl;
	}

	public void setRitna(BigDecimal ritna) {
		this.ritna = ritna;
	}

	@Column(name = "RITNA")
	public BigDecimal getRitna() {
		return ritna;
	}

	public void setLymbi(BigDecimal lymbi) {
		this.lymbi = lymbi;
	}

	@Column(name = "LYMBI")
	public BigDecimal getLymbi() {
		return lymbi;
	}

	public void setLyntp(BigDecimal lyntp) {
		this.lyntp = lyntp;
	}

	@Column(name = "LYNTP")
	public BigDecimal getLyntp() {
		return lyntp;
	}

	public void setRimbi(BigDecimal rimbi) {
		this.rimbi = rimbi;
	}

	@Column(name = "RIMBI")
	public BigDecimal getRimbi() {
		return rimbi;
	}

	public void setRintp(BigDecimal rintp) {
		this.rintp = rintp;
	}

	@Column(name = "RINTP")
	public BigDecimal getRintp() {
		return rintp;
	}

	public void setEmployee(int employee) {
		this.employee = employee;
	}

	@Column(name = "EMPLOYEE")
	public int getEmployee() {
		return employee;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Column(name = "CONTACTS", length = 10)
	public String getContacts() {
		return contacts;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TITLE", length = 50)
	public String getTitle() {
		return title;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "TEL", length = 50)
	public String getTel() {
		return tel;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "MEMO", columnDefinition = "CLOB")
	public String getMemo() {
		return memo;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}

	public void setFulId(String fulId) {
		this.fulId = fulId;
	}

	@Column(name = "FUL_ID", length = 50)
	public String getFulId() {
		return fulId;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	@Column(name = "BY1", length = 100)
	public String getBy1() {
		return by1;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	@Column(name = "BY2", length = 200)
	public String getBy2() {
		return by2;
	}

	public void setBy3(String by3) {
		this.by3 = by3;
	}

	@Column(name = "BY3", length = 300)
	public String getBy3() {
		return by3;
	}

	public void setTbsBasArea(TbsBasArea tbsBasArea) {
		this.tbsBasArea = tbsBasArea;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AREA_ID")
	public TbsBasArea getTbsBasArea() {
		return tbsBasArea;
	}

	public void setTbsBasEntproperty(TbsBasEntproperty tbsBasEntproperty) {
		this.tbsBasEntproperty = tbsBasEntproperty;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ENTPROPERTY_ID")
	public TbsBasEntproperty getTbsBasEntproperty() {
		return tbsBasEntproperty;
	}

	public void setTbsBasEntscale(TbsBasEntscale tbsBasEntscale) {
		this.tbsBasEntscale = tbsBasEntscale;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ENTSCALE_ID")
	public TbsBasEntscale getTbsBasEntscale() {
		return tbsBasEntscale;
	}

	public void setTbsBasIndutype(TbsBasIndutype tbsBasIndutype) {
		this.tbsBasIndutype = tbsBasIndutype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "INDUTYPE_ID")
	public TbsBasIndutype getTbsBasIndutype() {
		return tbsBasIndutype;
	}

	public void setTbsCasherSet(Set<TbsCasher> tbsCasherSet) {
		this.tbsCasherSet = tbsCasherSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsCustomer")
	public Set<TbsCasher> getTbsCasherSet() {
		return tbsCasherSet;
	}

	public void setTbsCggBzjzySet(Set<TbsCggBzjzy> tbsCggBzjzySet) {
		this.tbsCggBzjzySet = tbsCggBzjzySet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsCustomer")
	public Set<TbsCggBzjzy> getTbsCggBzjzySet() {
		return tbsCggBzjzySet;
	}

	public void setTbsCggDySet(Set<TbsCggDy> tbsCggDySet) {
		this.tbsCggDySet = tbsCggDySet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsCustomer")
	public Set<TbsCggDy> getTbsCggDySet() {
		return tbsCggDySet;
	}

	public void setTbsCggGrbzSet(Set<TbsCggGrbz> tbsCggGrbzSet) {
		this.tbsCggGrbzSet = tbsCggGrbzSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsCustomer")
	public Set<TbsCggGrbz> getTbsCggGrbzSet() {
		return tbsCggGrbzSet;
	}

	public void setTbsCggQybzSet(Set<TbsCggQybz> tbsCggQybzSet) {
		this.tbsCggQybzSet = tbsCggQybzSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsCustomer")
	public Set<TbsCggQybz> getTbsCggQybzSet() {
		return tbsCggQybzSet;
	}

	public void setTbsCggZySet(Set<TbsCggZy> tbsCggZySet) {
		this.tbsCggZySet = tbsCggZySet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsCustomer")
	public Set<TbsCggZy> getTbsCggZySet() {
		return tbsCggZySet;
	}

	public void setTbsCustomerEnttypeSet(
			Set<TbsCustomerEnttype> tbsCustomerEnttypeSet) {
		this.tbsCustomerEnttypeSet = tbsCustomerEnttypeSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsCustomer")
	public Set<TbsCustomerEnttype> getTbsCustomerEnttypeSet() {
		return tbsCustomerEnttypeSet;
	}

	public void setTbsCustomerIdnumSet(Set<TbsCustomerIdnum> tbsCustomerIdnumSet) {
		this.tbsCustomerIdnumSet = tbsCustomerIdnumSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsCustomer")
	public Set<TbsCustomerIdnum> getTbsCustomerIdnumSet() {
		return tbsCustomerIdnumSet;
	}

	public void setTbsProjSet(Set<TbsProj> tbsProjSet) {
		this.tbsProjSet = tbsProjSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsCustomer")
	public Set<TbsProj> getTbsProjSet() {
		return tbsProjSet;
	}

	public void setTbsProjundwrtSet(Set<TbsProjundwrt> tbsProjundwrtSet) {
		this.tbsProjundwrtSet = tbsProjundwrtSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsCustomer")
	public Set<TbsProjundwrt> getTbsProjundwrtSet() {
		return tbsProjundwrtSet;
	}

	public void setCompsry(BigDecimal compsry) {
		this.compsry = compsry;
	}

	@Column(name = "COMPSRY")
	public BigDecimal getCompsry() {
		return compsry;
	}
	
	public void setProjrol(BigDecimal projrol) {
		this.projrol = projrol;
	}
	
	@Column(name = "PROJROL")
	public BigDecimal getProjrol() {
		return projrol;
	}
	
	public void setTbsProjcompsryPaySet (Set<TbsProjcompsryPay> tbsProjcompsryPaySet) {
		this.tbsProjcompsryPaySet = tbsProjcompsryPaySet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsCustomer")
	public Set<TbsProjcompsryPay> getTbsProjcompsryPaySet() {
		return tbsProjcompsryPaySet;
	}
	
	public String toString() {
		return "TbsCustomer [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",name=" + name
				+ ",regaddr=" + regaddr + ",operaddr=" + operaddr + ",estdate="
				+ estdate + ",puc=" + puc + ",legaler=" + legaler
				+ ",stockholder=" + stockholder + ",mainbiz=" + mainbiz
				+ ",lyta=" + lyta + ",lytl=" + lytl + ",lytna=" + lytna
				+ ",rita=" + rita + ",ritl=" + ritl + ",ritna=" + ritna
				+ ",lymbi=" + lymbi + ",lyntp=" + lyntp + ",rimbi=" + rimbi
				+ ",rintp=" + rintp + ",employee=" + employee + ",contacts="
				+ contacts + ",title=" + title + ",tel=" + tel + ",memo="
				+ memo + ",del=" + del + ",fulId=" + fulId + ",by1=" + by1
				+ ",by2=" + by2 + ",by3=" + by3 + ",tbsBasArea=" + tbsBasArea
				+ ",tbsBasEntproperty=" + tbsBasEntproperty
				+ ",tbsBasEntscale=" + tbsBasEntscale + ",tbsBasIndutype="
				+ tbsBasIndutype + ",tbsCasherSet=" + tbsCasherSet
				+ ",tbsCggBzjzySet=" + tbsCggBzjzySet + ",tbsCggDySet="
				+ tbsCggDySet + ",tbsCggGrbzSet=" + tbsCggGrbzSet
				+ ",tbsCggQybzSet=" + tbsCggQybzSet + ",tbsCggZySet="
				+ tbsCggZySet + ",tbsCustomerEnttypeSet="
				+ tbsCustomerEnttypeSet + ",tbsCustomerIdnumSet="
				+ tbsCustomerIdnumSet + ",tbsProjSet=" + tbsProjSet
				+ ",tbsProjundwrtSet=" + tbsProjundwrtSet 
				+ ",compsry=" + compsry + ",projrol=" + projrol + ",tbsProjcompsryPaySet=" + tbsProjcompsryPaySet
				+ "]";
	}

}
