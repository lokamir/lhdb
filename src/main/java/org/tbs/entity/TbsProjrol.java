package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_projrol:追偿申请单
 */
@Entity
@Table(name = "tbs_projrol")
public class TbsProjrol implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 单据编号=ZCSQ+日期+序号,每个新日期都从001开始编号:单据编号=ZCSQ+日期+序号,每个新日期都从001开始编号
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

	/**
	 * MEMO:
	 */
	private String memo;

	/**
	 * 本次追偿金额:本次追偿金额
	 */
	private BigDecimal bczczje;

	/**
	 * 本次追偿本金:本次追偿本金
	 */
	private BigDecimal bczczbj;

	/**
	 * 本次追偿利息:本次追偿利息
	 */
	private BigDecimal bczczlx;

	/**
	 * 追偿日期:追偿日期
	 */
	private Date date;

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
	 * tbs_projcompsry_pay:代偿请款单
	 */
	private TbsProjcompsryPay tbsProjcompsryPay;

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
		
	public TbsProjrol() {
		super();
	}

	public TbsProjrol(int id, String sn, int valid, Date timestampInput,
			Date timestampUpdate, int keyinId, String memo, 
			BigDecimal bczczje, BigDecimal bczczbj,
			BigDecimal bczczlx, Date date, String by1,
			String by2, String by3, TbsProj tbsProj, boolean del, TbsProjundwrt tbsProjundwrt,
			TbsProjcompsryPay tbsProjcompsryPay, TbsCustomer tbsCustomer,
			BigDecimal faloc, BigDecimal nfaloc, BigDecimal otloc, BigDecimal falocLx, BigDecimal nfalocLx, BigDecimal otlocLx) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.keyinId = keyinId;
		this.memo = memo;
		this.bczczje = bczczje;
		this.bczczbj = bczczbj;
		this.bczczlx = bczczlx;
		this.date = date;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsProjcompsryPay = tbsProjcompsryPay;
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

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "MEMO", columnDefinition = "CLOB")
	public String getMemo() {
		return memo;
	}

	

	public void setBczczje(BigDecimal bczczje) {
		this.bczczje = bczczje;
	}

	@Column(name = "BCZCZJE")
	public BigDecimal getBczczje() {
		return bczczje;
	}

	public void setBczczbj(BigDecimal bczczbj) {
		this.bczczbj = bczczbj;
	}

	@Column(name = "BCZCZBJ")
	public BigDecimal getBczczbj() {
		return bczczbj;
	}

	public void setBczczlx(BigDecimal bczczlx) {
		this.bczczlx = bczczlx;
	}

	@Column(name = "BCZCZLX")
	public BigDecimal getBczczlx() {
		return bczczlx;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_")
	public Date getDate() {
		return date;
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

	public void setTbsProjcompsryPay(TbsProjcompsryPay tbsProjcompsryPay) {
		this.tbsProjcompsryPay = tbsProjcompsryPay;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJCOMPSRY_PAY_ID")
	public TbsProjcompsryPay getTbsProjcompsryPay() {
		return tbsProjcompsryPay;
	}
	
	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}
	
	public void setTbsProjundwrt(TbsProjundwrt tbsProjundwrt) {
		this.tbsProjundwrt = tbsProjundwrt;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UNDWRT_ID")
	public TbsProjundwrt getTbsProjundwrt() {
		return tbsProjundwrt;
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
		return "TbsProjrol [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",memo=" + memo
				+ ",bczcje=" + bczczje + ",bczczbj=" + bczczbj
				+ ",bczclx=" + bczczlx + ",date=" + date
				+ ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3 + ",tbsProj="
				+ tbsProj + ",tbsProjcompsryPay=" + tbsProjcompsryPay 
				+ ",tbsProjundwrt=" + tbsProjundwrt + ",del=" + del
				+ ",tbsCustomer=" + tbsCustomer
				+ ",faloc=" + faloc + ",nfaloc=" + nfaloc + ",otloc=" + otloc
				+ ",falocLx=" + falocLx + ",nfalocLx=" + nfalocLx + ",otlocLx=" + otlocLx 
				+ "]";
	}

}
