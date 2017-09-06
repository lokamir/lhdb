package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_proj_hisloc:项目基本信息-与项目相关的金额表(决策审议后的金额V，承保APP，解保的金额JB)
 */
@Entity
@Table(name = "tbs_proj_hisloc")
public class TbsProjHisloc implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * PROJ_SN:
	 */
	private String projSn;

	/**
	 * 插入时间戳（变更日期）:插入时间戳（变更日期）
	 */
	private Date timestampInput;

	/**
	 * 可用融资担保授信金额:可用融资担保授信金额
	 */
	private BigDecimal faloc;

	/**
	 * 可用非融资担保授信金额:可用非融资担保授信金额
	 */
	private BigDecimal nfaloc;

	/**
	 * 可用其他类担保授信金额:可用其他类担保授信金额
	 */
	private BigDecimal otloc;

	/**
	 * 总可用担保授信金额:总可用担保授信金额
	 */
	private BigDecimal totloc;

	/**
	 * 实际担保期限（月）:实际担保期限（月）
	 */
	private int periodCfm;

	/**
	 * 项目担保授信期间（开始）:项目担保授信期间（开始）
	 */
	private Date bdate;

	/**
	 * 项目担保授信期间（结束）:项目担保授信期间（结束）
	 */
	private Date edate;

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
	 *  12.16.2015 手动加入
	 */
	private String cusName;
	private int cusId;
	private String docSn;
	private String docCat;
	private int bizvarId;
	private int biztypeId;
	private BigDecimal loc;
	private String oper;
	
	
	public TbsProjHisloc() {
		super();
	}

	public TbsProjHisloc(int id, String projSn, Date timestampInput,
			BigDecimal faloc, BigDecimal nfaloc, BigDecimal otloc,
			BigDecimal totloc, int periodCfm, Date bdate, Date edate,
			String by1, String by2, String by3, TbsProj tbsProj,
			String cusName,int cusId,String docSn,String docCat,
			int bizvarId,int biztypeId,BigDecimal loc,String oper) {
		super();
		this.id = id;
		this.projSn = projSn;
		this.timestampInput = timestampInput;
		this.faloc = faloc;
		this.nfaloc = nfaloc;
		this.otloc = otloc;
		this.totloc = totloc;
		this.periodCfm = periodCfm;
		this.bdate = bdate;
		this.edate = edate;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.cusName = cusName;
		this.cusId = cusId;
		this.docSn = docSn;
		this.docCat = docCat;
		this.bizvarId = bizvarId;
		this.biztypeId = biztypeId;
		this.loc = loc;
		this.oper = oper;		
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

	public void setProjSn(String projSn) {
		this.projSn = projSn;
	}

	@Column(name = "PROJ_SN", length = 20)
	public String getProjSn() {
		return projSn;
	}

	public void setTimestampInput(Date timestampInput) {
		this.timestampInput = timestampInput;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_INPUT")
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setFaloc(BigDecimal faloc) {
		this.faloc = faloc;
	}

	@Column(name = "FALOC")
	public BigDecimal getFaloc() {
		return faloc;
	}

	public void setNfaloc(BigDecimal nfaloc) {
		this.nfaloc = nfaloc;
	}

	@Column(name = "NFALOC")
	public BigDecimal getNfaloc() {
		return nfaloc;
	}

	public void setOtloc(BigDecimal otloc) {
		this.otloc = otloc;
	}

	@Column(name = "OTLOC")
	public BigDecimal getOtloc() {
		return otloc;
	}

	public void setTotloc(BigDecimal totloc) {
		this.totloc = totloc;
	}

	@Column(name = "TOTLOC")
	public BigDecimal getTotloc() {
		return totloc;
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
	
	/** 
	 *  12.16.2015 手动加入
	 */
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	@Column(name = "CUS_NAME", length = 200)
	public String getCusName() {
		return cusName;
	}
	
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	@Column(name = "CUS_ID")
	public int getCusId() {
		return cusId;
	}
	
	public void setDocSn(String docSn) {
		this.docSn = docSn;
	}
	@Column(name = "DOC_SN", length = 50)
	public String getDocSn() {
		return docSn;
	}
	
	public void setDocCat(String docCat) {
		this.docCat = docCat;
	}
	@Column(name = "DOC_CAT", length = 10)
	public String getDocCat() {
		return docCat;
	}
	
	public void setBizvarId(int bizvarId) {
		this.bizvarId = bizvarId;
	}
	@Column(name = "BIZVAR_ID")
	public int getBizvarId() {
		return bizvarId;
	}
	
	public void setBiztypeId(int biztypeId) {
		this.biztypeId = biztypeId;
	}
	@Column(name = "BIZTYPE_ID")
	public int getBiztypeId() {
		return biztypeId;
	}
	
	public void setLoc(BigDecimal loc) {
		this.loc = loc;
	}
	@Column(name = "LOC")
	public BigDecimal getLoc() {
		return loc;
	}
	
	public void setOper(String oper) {
		this.oper = oper;
	}
	@Column(name = "OPER", length = 10)
	public String getOper() {
		return oper;
	}

	public String toString() {
		return "TbsProjHisloc [id=" + id + ",projSn=" + projSn
				+ ",timestampInput=" + timestampInput + ",faloc=" + faloc
				+ ",nfaloc=" + nfaloc + ",otloc=" + otloc + ",totloc="
				+ totloc + ",periodCfm=" + periodCfm + ",bdate=" + bdate
				+ ",edate=" + edate + ",by1=" + by1 + ",by2=" + by2 + ",by3="
				+ by3 + ",tbsProj=" + tbsProj + ",cusName=" + cusName +",cusId=" 
				+ cusId +",docSn=" + docSn +",docCat=" + docCat +",bizvarId=" + 
				bizvarId +",biztypeId=" + biztypeId +",loc=" + loc +",oper=" + oper + "]";
	}

}
