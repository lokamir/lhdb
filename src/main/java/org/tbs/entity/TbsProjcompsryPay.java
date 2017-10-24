package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_projcompsry_pay:代偿请款单
 */
@Entity
@Table(name = "tbs_projcompsry_pay")
public class TbsProjcompsryPay implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 单据编号=DCQK+日期+序号,每个新日期都从001开始编号:单据编号=DCQK+日期+序号,每个新日期都从001开始编号
	 */
	private String sn;

	/**
	 * 单据状态，默认为0,审批通过为1:单据状态，默认为0,审批通过为1
	 */
	private int valid;

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

	
	private BigDecimal zbe;

	
	private BigDecimal dcye;

	
	private BigDecimal bcdczje;

	
	private BigDecimal bcdczbj;

	
	private BigDecimal bcdczlx;
	
	
	private BigDecimal bcdczsr;

	/**
	 * 代偿日期:代偿日期
	 */
	private Date date;

	/**
	 * MEMO:
	 */
	private String memo;

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
	 * tbs_projcompsry:代偿申请单
	 */
	private TbsProjcompsry tbsProjcompsry;

	/**
	 * tbs_projrol:追偿申请单
	 */
	private Set<TbsProjrol> tbsProjrolSet = new HashSet<TbsProjrol>(0);

	/** 
	 * 二期改库 2016-06-01 
	 */
	private TbsProjundwrt tbsProjundwrt;
	private boolean del;
	private TbsCustomer tbsCustomer;
	private BigDecimal faloc;
	private BigDecimal nfaloc;
	private BigDecimal otloc;
	private BigDecimal falocLx;
	private BigDecimal nfalocLx;
	private BigDecimal otlocLx;
		
	public TbsProjcompsryPay() {
		super();
	}

	public TbsProjcompsryPay(int id, String sn, int valid,
			Date timestampInput, Date timestampUpdate, int keyinId,
			BigDecimal zbe, BigDecimal dcye, BigDecimal bcdczje, BigDecimal bcdczbj, BigDecimal bcdczlx, BigDecimal bcdczsr,Date date,
			String memo, String by1, String by2, String by3, TbsProj tbsProj,
			TbsProjcompsry tbsProjcompsry, TbsCustomer tbsCustomer,
			Set<TbsProjrol> tbsProjrolSet, boolean del, TbsProjundwrt tbsProjundwrt,int cusId,
			BigDecimal faloc, BigDecimal nfaloc, BigDecimal otloc, BigDecimal falocLx, BigDecimal nfalocLx, BigDecimal otlocLx) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.keyinId = keyinId;
		this.zbe = zbe;
		this.dcye = dcye;
		this.bcdczje = bcdczje;
		this.bcdczbj = bcdczbj;
		this.bcdczlx = bcdczlx;
		this.bcdczsr = bcdczsr;
		this.date = date;
		this.memo = memo;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsProjcompsry = tbsProjcompsry;
		this.tbsProjrolSet = tbsProjrolSet;
		this.tbsProjundwrt = tbsProjundwrt;
		this.del = del;
		this.tbsCustomer = tbsCustomer;
		this.faloc = faloc;
		this.nfaloc = nfaloc;
		this.otloc = otloc;
		this.falocLx = falocLx;
		this.nfalocLx = nfalocLx;
		this.otlocLx = otlocLx;
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

	@Column(name = "SN", length = 20)
	public String getSn() {
		return sn;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public int getValid() {
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

	
	public void setZbe(BigDecimal zbe) {
		this.zbe = zbe;
	}

	@Column(name = "ZBE")
	public BigDecimal getZbe() {
		return zbe;
	}

	public void setDcye(BigDecimal dcye) {
		this.dcye = dcye;
	}

	@Column(name = "DCYE")
	public BigDecimal getDcye() {
		return dcye;
	}

	public void setBcdczje(BigDecimal bcdczje) {
		this.bcdczje = bcdczje;
	}

	@Column(name = "BCDCZJE")
	public BigDecimal getBcdczje() {
		return bcdczje;
	}

	public void setBcdczbj(BigDecimal bcdczbj) {
		this.bcdczbj = bcdczbj;
	}

	@Column(name = "BCDCZBJ")
	public BigDecimal getBcdczbj() {
		return bcdczbj;
	}

	public void setBcdczlx(BigDecimal bcdczlx) {
		this.bcdczlx = bcdczlx;
	}

	@Column(name = "BCDCZLX")
	public BigDecimal getBcdczlx() {
		return bcdczlx;
	}
	
	public void setBcdczsr(BigDecimal bcdczsr) {
		this.bcdczsr = bcdczsr;
	}
	
	@Column(name = "BCDCZSR")
	public BigDecimal getBcdczsr() {
		return bcdczsr;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_")
	public Date getDate() {
		return date;
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

	public void setTbsProjcompsry(TbsProjcompsry tbsProjcompsry) {
		this.tbsProjcompsry = tbsProjcompsry;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJCOMPSRY_ID")
	public TbsProjcompsry getTbsProjcompsry() {
		return tbsProjcompsry;
	}

	public void setTbsProjrolSet(Set<TbsProjrol> tbsProjrolSet) {
		this.tbsProjrolSet = tbsProjrolSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjcompsryPay")
	public Set<TbsProjrol> getTbsProjrolSet() {
		return tbsProjrolSet;
	}
	
	public void setTbsProjundwrt(TbsProjundwrt tbsProjundwrt) {
		this.tbsProjundwrt = tbsProjundwrt;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UNDWRT_ID")
	public TbsProjundwrt getTbsProjundwrt() {
		return tbsProjundwrt;
	}
	
	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}
	
	public void setTbsCustomer(TbsCustomer tbsCustomer) {
		this.tbsCustomer = tbsCustomer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUS_ID", nullable = false)
	public TbsCustomer getTbsCustomer() {
		return tbsCustomer;
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
	
	public void setFalocLx(BigDecimal falocLx) {
		this.falocLx = falocLx;
	}

	@Column(name = "FALOC_LX")
	public BigDecimal getFalocLx() {
		return falocLx;
	}
	
	public void setNfalocLx(BigDecimal nfalocLx) {
		this.nfalocLx = nfalocLx;
	}

	@Column(name = "NFALOC_LX")
	public BigDecimal getNfalocLx() {
		return nfalocLx;
	}
	
	public void setOtlocLx(BigDecimal otlocLx) {
		this.otlocLx = otlocLx;
	}

	@Column(name = "OTLOC_LX")
	public BigDecimal getOtlocLx() {
		return otlocLx;
	}
	
	public String toString() {
		return "TbsProjcompsryPay [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",zbe=" + zbe + ",dcye=" + dcye
				+ ",bcdczje=" + bcdczje + ",bcdczbj=" + bcdczbj + ",bcdczlx="
				+ bcdczlx + ",bcdczsr=" + bcdczsr + ",date=" + date + ",memo=" + memo + ",by1=" + by1
				+ ",by2=" + by2 + ",by3=" + by3 + ",tbsProj=" + tbsProj
				+ ",tbsProjcompsry=" + tbsProjcompsry + ",tbsProjrolSet=" + tbsProjrolSet
				+ ",tbsProjundwrt=" + tbsProjundwrt 
				+ ",del=" + del + ",tbsCustomer=" + tbsCustomer
				+ ",faloc=" + faloc + ",nfaloc=" + nfaloc + ",otloc=" + otloc
				+ ",falocLx=" + falocLx + ",nfalocLx=" + nfalocLx + ",otlocLx=" + otlocLx 
				+ "]";
	}

}
