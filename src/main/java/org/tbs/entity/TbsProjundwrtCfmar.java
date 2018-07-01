package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_projundwrt_cfmar:承保补录
 */
@Entity
@Table(name = "tbs_projundwrt_cfmar")
public class TbsProjundwrtCfmar implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 输入时间戳:输入时间戳
	 */
	private Date timestampInput;

	/**
	 * PROJ_SN:
	 */
	private String projSn;

	/**
	 * PROJUNDWRT_SN:
	 */
	private String projundwrtSn;
	
	/**
	 * 贷款利率（%）
	 */
	private BigDecimal lendingrate;

	/**
	 * 本次承保确认融资担保授信金额（元）tbs_projundwrt.appfaloc不可改:本次承保确认融资担保授信金额（元）tbs_projundwrt.appfaloc不可改
	 */
	private BigDecimal udtfaloc;

	/**
	 * 本次承保确认非融资担保授信金额（元）tbs_projundwrt.appnfaloc不可改:本次承保确认非融资担保授信金额（元）tbs_projundwrt.appnfaloc不可改
	 */
	private BigDecimal udtnfaloc;

	/**
	 * 本次承保确认其他类担保授信金额（元）tbs_projundwrt.appnfaloc不可改:本次承保确认其他类担保授信金额（元）tbs_projundwrt.appnfaloc不可改
	 */
	private BigDecimal udtotloc;

	/**
	 * 本次总承保确认担保授信金额（元）appfaloc+appnfaloc+appotloc不可改:本次总承保确认担保授信金额（元）appfaloc+appnfaloc+appotloc不可改
	 */
	private BigDecimal udttotloc;

	/**
	 * 实际承保期限（月）.来自tbs_projundwrt.period_cfm可修改:实际承保期限（月）.来自tbs_projundwrt.period_cfm可修改
	 */
	private int periodCfm;

	/**
	 * 承保期间开始日期:承保期间开始日期
	 */
	private Date bdate;

	/**
	 * 承保期间结束日期:承保期间结束日期
	 */
	private Date edate;

	/**
	 * 委托担保合同号【格式为锡联担保2015(年)-B1109（手输）-WT0623（手输）】:委托担保合同号【格式为锡联担保2015(年)-B1109（手输）-WT0623（手输）】
	 */
	private String sn;

	/**
	 * 主合同合同号:主合同合同号
	 */
	private String zht;

	/**
	 * 保证合同号:保证合同号
	 */
	private String bzht;

	/**
	 * 删除标记默认为0:删除标记默认为0
	 */
	private boolean del;

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
	 * tbs_proj:项目基本信息主表
	 */
	private TbsProj tbsProj;

	/**
	 * tbs_projundwrt:承保审批单
	 */
	private TbsProjundwrt tbsProjundwrt;
	
	/**
	 *  2015-12-25 手动插入
	 * */
	private Date undbdate;
	private Date undedate;
	private boolean valid;
	private String pid;
	
	/**
	 * *  2018-7-1 手动插入
	 * 是否为政府基金项目，默认为0否:是否为政府基金项目，默认为0否
	 */
	private boolean fundproj;
	
	/**
	 * 分摊比例
	 */
	private String burden;
	
	/**
	 * 项目基本信息-政府基金名称
	 */
	private Integer govfund;

	public TbsProjundwrtCfmar() {
		super();
	}

	public TbsProjundwrtCfmar(int id, Date timestampInput, String projSn,boolean valid,boolean fundproj,
			String projundwrtSn,BigDecimal lendingrate,BigDecimal udtfaloc, BigDecimal udtnfaloc,String burden,
			BigDecimal udtotloc, BigDecimal udttotloc, int periodCfm,Date undbdate,Date undedate,Integer govfund,
			Date bdate, Date edate, String sn, String zht, String bzht,
			boolean del, String by1, String by2, String by3, TbsProj tbsProj,
			TbsProjundwrt tbsProjundwrt, String pid) {
		super();
		this.id = id;
		this.timestampInput = timestampInput;
		this.projSn = projSn;
		this.projundwrtSn = projundwrtSn;
		this.lendingrate = lendingrate;
		this.udtfaloc = udtfaloc;
		this.udtnfaloc = udtnfaloc;
		this.udtotloc = udtotloc;
		this.udttotloc = udttotloc;
		this.periodCfm = periodCfm;
		this.bdate = bdate;
		this.edate = edate;
		this.sn = sn;
		this.zht = zht;
		this.bzht = bzht;
		this.del = del;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsProjundwrt = tbsProjundwrt;
		this.undbdate = undbdate;
		this.undedate = undedate;
		this.valid = valid;
		this.pid = pid;
		this.govfund = govfund;
		this.burden = burden;
		this.fundproj = fundproj;
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

	public void setTimestampInput(Date timestampInput) {
		this.timestampInput = timestampInput;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_INPUT", nullable = false)
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setProjSn(String projSn) {
		this.projSn = projSn;
	}

	@Column(name = "PROJ_SN", length = 20)
	public String getProjSn() {
		return projSn;
	}

	public void setProjundwrtSn(String projundwrtSn) {
		this.projundwrtSn = projundwrtSn;
	}

	@Column(name = "PROJUNDWRT_SN", length = 20)
	public String getProjundwrtSn() {
		return projundwrtSn;
	}

	public void setLendingrate(BigDecimal lendingrate) {
		this.lendingrate = lendingrate;
	}

	@Column(name = "LENDINGRATE")
	public BigDecimal getLendingrate() {
		return lendingrate;
	}
	
	public void setUdtfaloc(BigDecimal udtfaloc) {
		this.udtfaloc = udtfaloc;
	}
	
	@Column(name = "UDTFALOC")
	public BigDecimal getUdtfaloc() {
		return udtfaloc;
	}

	public void setUdtnfaloc(BigDecimal udtnfaloc) {
		this.udtnfaloc = udtnfaloc;
	}

	@Column(name = "UDTNFALOC")
	public BigDecimal getUdtnfaloc() {
		return udtnfaloc;
	}

	public void setUdtotloc(BigDecimal udtotloc) {
		this.udtotloc = udtotloc;
	}

	@Column(name = "UDTOTLOC")
	public BigDecimal getUdtotloc() {
		return udtotloc;
	}

	public void setUdttotloc(BigDecimal udttotloc) {
		this.udttotloc = udttotloc;
	}

	@Column(name = "UDTTOTLOC")
	public BigDecimal getUdttotloc() {
		return udttotloc;
	}

	public void setPeriodCfm(int periodCfm) {
		this.periodCfm = periodCfm;
	}

	@Column(name = "PERIOD_CFM")
	public int getPeriodCfm() {
		return periodCfm;
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

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "SN", length = 30)
	public String getSn() {
		return sn;
	}

	public void setZht(String zht) {
		this.zht = zht;
	}

	@Column(name = "ZHT", length = 30)
	public String getZht() {
		return zht;
	}

	public void setBzht(String bzht) {
		this.bzht = bzht;
	}

	@Column(name = "BZHT", length = 30)
	public String getBzht() {
		return bzht;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
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

	public void setTbsProj(TbsProj tbsProj) {
		this.tbsProj = tbsProj;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJ_ID")
	public TbsProj getTbsProj() {
		return tbsProj;
	}

	public void setTbsProjundwrt(TbsProjundwrt tbsProjundwrt) {
		this.tbsProjundwrt = tbsProjundwrt;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJUNDWRT_ID")
	public TbsProjundwrt getTbsProjundwrt() {
		return tbsProjundwrt;
	}

	public void setUndbdate(Date undbdate) {
		this.undbdate = undbdate;
	}

	@Column(name = "UNDBDATE")
	public Date getUndbdate() {
		return undbdate;
	}
	
	public void setUndedate(Date undedate) {
		this.undedate = undedate;
	}

	@Column(name = "UNDEDATE")
	public Date getUndedate() {
		return undedate;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "PID", length = 300)
	public String getPid() {
		return pid;
	}
	
	public void setFundproj(boolean fundproj) {
		this.fundproj = fundproj;
	}

	@Column(name = "FUNDPROJ")
	public boolean isFundproj() {
		return fundproj;
	}
	
	public void setBurden(String burden) {
		this.burden = burden;
	}

	@Column(name = "BURDEN", length = 45)
	public String getBurden() {
		return burden;
	}
	
	public void setGovfund(Integer govfund) {
		this.govfund = govfund;
	}

	@Column(name = "GOVFUND", length = 45)
	public Integer getGovfund() {
		return govfund;
	}
	
	public String toString() {
		return "TbsProjundwrtCfmar [id=" + id + ",timestampInput="
				+ timestampInput + ",projSn=" + projSn + ",projundwrtSn="
				+ projundwrtSn +",lendingrate=" + lendingrate + ",udtfaloc=" + udtfaloc + ",udtnfaloc="
				+ udtnfaloc + ",udtotloc=" + udtotloc + ",udttotloc="
				+ udttotloc + ",periodCfm=" + periodCfm + ",bdate=" + bdate
				+ ",edate=" + edate + ",sn=" + sn + ",zht=" + zht + ",bzht="
				+ bzht + ",del=" + del + ",by1=" + by1 + ",by2=" + by2
				+ ",by3=" + by3 + ",tbsProj=" + tbsProj + ",tbsProjundwrt="
				+ tbsProjundwrt + ",undbdate=" + undbdate + ",undedate=" + undedate + ",valid=" + valid 
				+ ",pid=" + pid + ",fundproj=" + fundproj + ",burden=" + burden + ",govfund=" + govfund +"]" ;
	}

}
