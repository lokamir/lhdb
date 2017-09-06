package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_cgg_qybz:反担保信息-企业保证
 */
@Entity
@Table(name = "tbs_cgg_qybz")
public class TbsCggQybz implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 反担保信息编号:反担保信息编号
	 */
	private String sn;

	/**
	 * 反担保状态【已办妥，未办妥】，默认为0无效，审批过后为1有效。:反担保状态【已办妥，未办妥】，默认为0无效，审批过后为1有效。
	 */
	private boolean valid;

	/**
	 * 反担保总分类:反担保总分类
	 */
	private int cat1;

	/**
	 * 反担保大类:反担保大类
	 */
	private int cat2;

	/**
	 * 反担保类型:反担保类型
	 */
	private int cat3;

	/**
	 * 输入时间戳:输入时间戳
	 */
	private Date timestampInput;

	/**
	 * 最近一次更新的时间戳:最近一次更新的时间戳
	 */
	private Date timestampUpdate;

	/**
	 * 录入人:录入人
	 */
	private int keyinId;

	/**
	 * 企业保证人名称:企业保证人名称
	 */
	private String cusName;

	/**
	 * 该反担保信息的参考价值（元）:该反担保信息的参考价值（元）
	 */
	private BigDecimal val;

	/**
	 * 组织机构代码证编号:组织机构代码证编号
	 */
	private String orgnum;

	/**
	 * 统一社会信用代码:统一社会信用代码
	 */
	private String bizliznum;

	/**
	 * 成立日期:成立日期
	 */
	private Date estdate;

	/**
	 * 注册地址:注册地址
	 */
	private String regaddr;

	/**
	 * 实收资本(元):实收资本(元)
	 */
	private BigDecimal puc;

	/**
	 * 法定代表人:法定代表人
	 */
	private String legaler;

	/**
	 * 主营业务概况:主营业务概况
	 */
	private String mainbiz;

	/**
	 * 联系人:联系人
	 */
	private String contacts;

	/**
	 * 联系人职位:联系人职位
	 */
	private String title;

	/**
	 * 联系人电话:联系人电话
	 */
	private String tel;

	/**
	 * 上年度末资产合计（元）:上年度末资产合计（元）
	 */
	private BigDecimal lyta;

	/**
	 * 上年度末负债合计（元）:上年度末负债合计（元）
	 */
	private BigDecimal lytl;

	/**
	 * 上年度末净资产合计（元）:上年度末净资产合计（元）
	 */
	private BigDecimal lytna;

	/**
	 * 最近一期资产合计（元）:最近一期资产合计（元）
	 */
	private BigDecimal rita;

	/**
	 * 最近一期负债合计（元）:最近一期负债合计（元）
	 */
	private BigDecimal ritl;

	/**
	 * 最近一期净资产合计（元）:最近一期净资产合计（元）
	 */
	private BigDecimal ritna;

	/**
	 * 上年度主营业务收入（元）:上年度主营业务收入（元）
	 */
	private BigDecimal lymbi;

	/**
	 * 上年度净利润总额（元）:上年度净利润总额（元）
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
	 * 保证金额(元):保证金额(元)
	 */
	private BigDecimal bzed;

	/**
	 * 保证责任类型，两个下拉框选择[单笔/最高额][一般保证/连带责任保证],数据库直接存储值，两个值之间用分隔符分开:保证责任类型，两个下拉框选择[单笔/最高额][一般保证/连带责任保证],数据库直接存储值，两个值之间用分隔符分开
	 */
	private String bzzrlx;

	/**
	 * MEMO:
	 */
	private String memo;

	/**
	 * 删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据:删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据
	 */
	private int del;

	/**
	 * 起始日:起始日
	 */
	private Date bdate;

	/**
	 * 到期日:到期日
	 */
	private Date edate;

	/**
	 * 关联文件上传表中的ID，如果有上传了多个文件，则这里用加号或者逗号作为分隔符把ID号分隔开。:关联文件上传表中的ID，如果有上传了多个文件，则这里用加号或者逗号作为分隔符把ID号分隔开。
	 */
	private String fulId;

	/**
	 * 删除日期:删除日期
	 */
	private Date delDate;

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
	 * tbs_customer:客户基本信息
	 */
	private TbsCustomer tbsCustomer;

	public TbsCggQybz() {
		super();
	}

	public TbsCggQybz(int id, String sn, boolean valid, int cat1, int cat2,
			int cat3, Date timestampInput, Date timestampUpdate, int keyinId,
			String cusName, BigDecimal val, String orgnum, String bizliznum,
			Date estdate, String regaddr, BigDecimal puc, String legaler,
			String mainbiz, String contacts, String title, String tel,
			BigDecimal lyta, BigDecimal lytl, BigDecimal lytna,
			BigDecimal rita, BigDecimal ritl, BigDecimal ritna,
			BigDecimal lymbi, BigDecimal lyntp, BigDecimal rimbi,
			BigDecimal rintp, BigDecimal bzed, String bzzrlx, String memo,
			int del, Date bdate, Date edate, String fulId, Date delDate,
			String by1, String by2, String by3, TbsCustomer tbsCustomer) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.keyinId = keyinId;
		this.cusName = cusName;
		this.val = val;
		this.orgnum = orgnum;
		this.bizliznum = bizliznum;
		this.estdate = estdate;
		this.regaddr = regaddr;
		this.puc = puc;
		this.legaler = legaler;
		this.mainbiz = mainbiz;
		this.contacts = contacts;
		this.title = title;
		this.tel = tel;
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
		this.bzed = bzed;
		this.bzzrlx = bzzrlx;
		this.memo = memo;
		this.del = del;
		this.bdate = bdate;
		this.edate = edate;
		this.fulId = fulId;
		this.delDate = delDate;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsCustomer = tbsCustomer;
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

	public void setCat1(int cat1) {
		this.cat1 = cat1;
	}

	@Column(name = "CAT1", nullable = false)
	public int getCat1() {
		return cat1;
	}

	public void setCat2(int cat2) {
		this.cat2 = cat2;
	}

	@Column(name = "CAT2", nullable = false)
	public int getCat2() {
		return cat2;
	}

	public void setCat3(int cat3) {
		this.cat3 = cat3;
	}

	@Column(name = "CAT3", nullable = false)
	public int getCat3() {
		return cat3;
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

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	@Column(name = "CUS_NAME", length = 200)
	public String getCusName() {
		return cusName;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}

	@Column(name = "VAL")
	public BigDecimal getVal() {
		return val;
	}

	public void setOrgnum(String orgnum) {
		this.orgnum = orgnum;
	}

	@Column(name = "ORGNUM", length = 50)
	public String getOrgnum() {
		return orgnum;
	}

	public void setBizliznum(String bizliznum) {
		this.bizliznum = bizliznum;
	}

	@Column(name = "BIZLIZNUM", length = 50)
	public String getBizliznum() {
		return bizliznum;
	}

	public void setEstdate(Date estdate) {
		this.estdate = estdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ESTDATE")
	public Date getEstdate() {
		return estdate;
	}

	public void setRegaddr(String regaddr) {
		this.regaddr = regaddr;
	}

	@Column(name = "REGADDR", length = 500)
	public String getRegaddr() {
		return regaddr;
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

	public void setMainbiz(String mainbiz) {
		this.mainbiz = mainbiz;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "MAINBIZ", columnDefinition = "CLOB")
	public String getMainbiz() {
		return mainbiz;
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

	public void setBzed(BigDecimal bzed) {
		this.bzed = bzed;
	}

	@Column(name = "BZED")
	public BigDecimal getBzed() {
		return bzed;
	}

	public void setBzzrlx(String bzzrlx) {
		this.bzzrlx = bzzrlx;
	}

	@Column(name = "BZZRLX", length = 20)
	public String getBzzrlx() {
		return bzzrlx;
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

	public void setDel(int del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public int isDel() {
		return del;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BDATE")
	public Date getBdate() {
		return bdate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EDATE")
	public Date getEdate() {
		return edate;
	}

	public void setFulId(String fulId) {
		this.fulId = fulId;
	}

	@Column(name = "FUL_ID", length = 50)
	public String getFulId() {
		return fulId;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEL_DATE")
	public Date getDelDate() {
		return delDate;
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

	public void setTbsCustomer(TbsCustomer tbsCustomer) {
		this.tbsCustomer = tbsCustomer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUS_ID")
	public TbsCustomer getTbsCustomer() {
		return tbsCustomer;
	}

	public String toString() {
		return "TbsCggQybz [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",cat1=" + cat1 + ",cat2=" + cat2 + ",cat3=" + cat3
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",cusName="
				+ cusName + ",val=" + val + ",orgnum=" + orgnum + ",bizliznum="
				+ bizliznum + ",estdate=" + estdate + ",regaddr=" + regaddr
				+ ",puc=" + puc + ",legaler=" + legaler + ",mainbiz=" + mainbiz
				+ ",contacts=" + contacts + ",title=" + title + ",tel=" + tel
				+ ",lyta=" + lyta + ",lytl=" + lytl + ",lytna=" + lytna
				+ ",rita=" + rita + ",ritl=" + ritl + ",ritna=" + ritna
				+ ",lymbi=" + lymbi + ",lyntp=" + lyntp + ",rimbi=" + rimbi
				+ ",rintp=" + rintp + ",bzed=" + bzed + ",bzzrlx=" + bzzrlx
				+ ",memo=" + memo + ",del=" + del + ",bdate=" + bdate
				+ ",edate=" + edate + ",fulId=" + fulId + ",delDate=" + delDate
				+ ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3
				+ ",tbsCustomer=" + tbsCustomer + "]";
	}

}
