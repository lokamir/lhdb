package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_casher:出纳费用确认
 */
@Entity
@Table(name = "tbs_casher")
public class TbsCasher implements Serializable {

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
	 * 是否有效，默认为0无效，审批过后为1有效。:是否有效，默认为0无效，审批过后为1有效。
	 */
	private boolean valid;

	/**
	 * 录入人:录入人
	 */
	private int keyinId;

	/**
	 * PROJ_SN:
	 */
	private String projSn;

	/**
	 * 本次总承保确认担保授信金额（元）tbs_projundwrt_cfmar.udttotloc:本次总承保确认担保授信金额（元）tbs_projundwrt_cfmar.udttotloc
	 */
	private BigDecimal udttotloc;

	/**
	 * 实际担保授信期限（月）tbs_proj:实际担保授信期限（月）tbs_proj
	 */
	private int periodCfm;

	/**
	 * 担保费率（‰/月）tbs_proj:担保费率（‰/月）tbs_proj
	 */
	private BigDecimal gatrate;

	/**
	 * 应收担保费（元）:应收担保费（元）
	 */
	private BigDecimal ysdbf;

	/**
	 * 实收担保费（元）:实收担保费（元）
	 */
	private BigDecimal ssdbf;

	/**
	 * 应收评审费（元）:应收评审费（元）
	 */
	private BigDecimal yspsf;

	/**
	 * 实收评审费（元）:实收评审费（元）
	 */
	private BigDecimal sspsf;

	/**
	 * 应收其他费用（元）:应收其他费用（元）
	 */
	private BigDecimal ysqtf;

	/**
	 * 实收其他费用（元）:实收其他费用（元）
	 */
	private BigDecimal ssqtf;

	/**
	 * 删除标记默认为0,非删除:删除标记默认为0,非删除
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
	 * tbs_customer:客户基本信息
	 */
	private TbsCustomer tbsCustomer;

	public TbsCasher() {
		super();
	}

	public TbsCasher(int id, Date timestampInput, boolean valid, int keyinId,
			String projSn, BigDecimal udttotloc, int periodCfm,
			BigDecimal gatrate, BigDecimal ysdbf, BigDecimal ssdbf,
			BigDecimal yspsf, BigDecimal sspsf, BigDecimal ysqtf,
			BigDecimal ssqtf, boolean del, String by1, String by2, String by3,
			TbsProj tbsProj, TbsCustomer tbsCustomer) {
		super();
		this.id = id;
		this.timestampInput = timestampInput;
		this.valid = valid;
		this.keyinId = keyinId;
		this.projSn = projSn;
		this.udttotloc = udttotloc;
		this.periodCfm = periodCfm;
		this.gatrate = gatrate;
		this.ysdbf = ysdbf;
		this.ssdbf = ssdbf;
		this.yspsf = yspsf;
		this.sspsf = sspsf;
		this.ysqtf = ysqtf;
		this.ssqtf = ssqtf;
		this.del = del;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
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

	public void setTimestampInput(Date timestampInput) {
		this.timestampInput = timestampInput;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_INPUT", nullable = false)
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
	}

	public void setKeyinId(int keyinId) {
		this.keyinId = keyinId;
	}

	@Column(name = "KEYIN_ID")
	public int getKeyinId() {
		return keyinId;
	}

	public void setProjSn(String projSn) {
		this.projSn = projSn;
	}

	@Column(name = "PROJ_SN", length = 20)
	public String getProjSn() {
		return projSn;
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

	public void setGatrate(BigDecimal gatrate) {
		this.gatrate = gatrate;
	}

	@Column(name = "GATRATE")
	public BigDecimal getGatrate() {
		return gatrate;
	}

	public void setYsdbf(BigDecimal ysdbf) {
		this.ysdbf = ysdbf;
	}

	@Column(name = "YSDBF")
	public BigDecimal getYsdbf() {
		return ysdbf;
	}

	public void setSsdbf(BigDecimal ssdbf) {
		this.ssdbf = ssdbf;
	}

	@Column(name = "SSDBF")
	public BigDecimal getSsdbf() {
		return ssdbf;
	}

	public void setYspsf(BigDecimal yspsf) {
		this.yspsf = yspsf;
	}

	@Column(name = "YSPSF")
	public BigDecimal getYspsf() {
		return yspsf;
	}

	public void setSspsf(BigDecimal sspsf) {
		this.sspsf = sspsf;
	}

	@Column(name = "SSPSF")
	public BigDecimal getSspsf() {
		return sspsf;
	}

	public void setYsqtf(BigDecimal ysqtf) {
		this.ysqtf = ysqtf;
	}

	@Column(name = "YSQTF")
	public BigDecimal getYsqtf() {
		return ysqtf;
	}

	public void setSsqtf(BigDecimal ssqtf) {
		this.ssqtf = ssqtf;
	}

	@Column(name = "SSQTF")
	public BigDecimal getSsqtf() {
		return ssqtf;
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

	public void setTbsCustomer(TbsCustomer tbsCustomer) {
		this.tbsCustomer = tbsCustomer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUST_ID")
	public TbsCustomer getTbsCustomer() {
		return tbsCustomer;
	}

	public String toString() {
		return "TbsCasher [id=" + id + ",timestampInput=" + timestampInput
				+ ",valid=" + valid + ",keyinId=" + keyinId + ",projSn="
				+ projSn + ",udttotloc=" + udttotloc + ",periodCfm="
				+ periodCfm + ",gatrate=" + gatrate + ",ysdbf=" + ysdbf
				+ ",ssdbf=" + ssdbf + ",yspsf=" + yspsf + ",sspsf=" + sspsf
				+ ",ysqtf=" + ysqtf + ",ssqtf=" + ssqtf + ",del=" + del
				+ ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3 + ",tbsProj="
				+ tbsProj + ",tbsCustomer=" + tbsCustomer + "]";
	}

}
