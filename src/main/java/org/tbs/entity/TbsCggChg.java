package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import com.bstek.dorado.annotation.PropertyDef;

/**
 * tbs_cgg_chg:<反担保解除>时插入的信用额度的变更数据
 */
@Entity
@Table(name = "tbs_cgg_chg")
public class TbsCggChg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	@PropertyDef(label = "ID", description = "ID:")
	private int id;
	/**
	 * 项目编号=PROJ主表内的SN
	 */
	private String sn;
	/**
	 * 插入时间戳:插入时间戳
	 */
	@PropertyDef(label = "插入时间戳", description = "插入时间戳:插入时间戳")
	private Date timestampInput;

	/**
	 * 客户id:客户id
	 */
	@PropertyDef(label = "客户id", description = "客户id:客户id")
	private int cusId;

	/**
	 * 项目id:项目id
	 */
	@PropertyDef(label = "项目id", description = "项目id:项目id")
	private int projId;
	/**
	 * (风管填写)减少融资担保授信额度:减少融资担保授信额度
	 */
	@PropertyDef(label = "(风管填写)减少融资担保授信额度", description = "(风管填写)减少融资担保授信额度")
	private BigDecimal xdeFaloc;

	/**
	 * (风管填写)减少非融资担保授信额度:减少非融资担保授信额度
	 */
	@PropertyDef(label = "(风管填写)减少非融资担保授信额度", description = "(风管填写)减少非融资担保授信额度")
	private BigDecimal xdeNfaloc;

	/**
	 * (风管填写)减少其他类担保授信额度:减少其他类担保授信额度
	 */
	@PropertyDef(label = "(风管填写)减少其他类担保授信额度", description = "(风管填写)减少其他类担保授信额度")
	private BigDecimal xdeOtloc;
	/**
	 * 减少融资担保授信额度:减少融资担保授信额度
	 */
	@PropertyDef(label = "减少融资担保授信额度", description = "减少融资担保授信额度:减少融资担保授信额度")
	private BigDecimal deFaloc;

	/**
	 * 减少非融资担保授信额度:减少非融资担保授信额度
	 */
	@PropertyDef(label = "减少非融资担保授信额度", description = "减少非融资担保授信额度:减少非融资担保授信额度")
	private BigDecimal deNfaloc;

	/**
	 * 减少其他类担保授信额度:减少其他类担保授信额度
	 */
	@PropertyDef(label = "减少其他类担保授信额度", description = "减少其他类担保授信额度:减少其他类担保授信额度")
	private BigDecimal deOtloc;

	/**
	 * 减少总担保授信额度(前三项汇总，界面上不可修改):减少总担保授信额度(前三项汇总，界面上不可修改)
	 */
	@PropertyDef(label = "减少总担保授信额度(前三项汇总，界面上不可修改)", description = "减少总担保授信额度(前三项汇总，界面上不可修改):减少总担保授信额度(前三项汇总，界面上不可修改)")
	private BigDecimal deTotloc;

	/**
	 * 标记是否计算过，防止重复计算:标记是否计算过，防止重复计算
	 */
	@PropertyDef(label = "标记是否计算过，防止重复计算", description = "标记是否计算过，防止重复计算:标记是否计算过，防止重复计算")
	private String tag;

	/**
	 * 审批通过valid=1:审批通过valid=1
	 */
	@PropertyDef(label = "审批通过valid=1", description = "审批通过valid=1:审批通过valid=1")
	private int valid;

	/**
	 * 原项目名称:原项目名称
	 */
	@PropertyDef(label = "原项目名称", description = "原项目名称:原项目名称")
	private String oldprojname;

	/**
	 * BY1:
	 */
	@PropertyDef(label = "BY1", description = "BY1:")
	private String by1;

	/**
	 * BY2:
	 */
	@PropertyDef(label = "BY2", description = "BY2:")
	private String by2;

	/**
	 * BY3:
	 */
	@PropertyDef(label = "BY3", description = "BY3:")
	private String by3;

	public TbsCggChg() {
		super();
	}

	public TbsCggChg(int id,String sn, Date timestampInput, int cusId, int projId,
			BigDecimal xdeFaloc, BigDecimal xdeNfaloc, BigDecimal xdeOtloc,
			BigDecimal deFaloc, BigDecimal deNfaloc, BigDecimal deOtloc,
			BigDecimal deTotloc, String tag, int valid, String oldprojname,
			String by1, String by2, String by3) {
		super();
		this.id = id;
		this.sn = sn;
		this.timestampInput = timestampInput;
		this.cusId = cusId;
		this.projId = projId;
		this.deFaloc = xdeFaloc;
		this.deNfaloc = xdeNfaloc;
		this.deOtloc = xdeOtloc;
		this.deFaloc = deFaloc;
		this.deNfaloc = deNfaloc;
		this.deOtloc = deOtloc;
		this.deTotloc = deTotloc;
		this.tag = tag;
		this.valid = valid;
		this.oldprojname = oldprojname;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
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

	@Column(name = "SN", length = 20, unique = true)
	public String getSn() {
		return sn;
	}
	
	public void setTimestampInput(Date timestampInput) {
		this.timestampInput = timestampInput;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_INPUT")
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	@Column(name = "CUS_ID")
	public int getCusId() {
		return cusId;
	}

	public void setProjId(int projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_ID")
	public int getProjId() {
		return projId;
	}

	public void setXdeFaloc(BigDecimal xdeFaloc) {
		this.xdeFaloc = xdeFaloc;
	}

	@Column(name = "XDE_FALOC")
	public BigDecimal getXdeFaloc() {
		return xdeFaloc;
	}

	public void setXdeNfaloc(BigDecimal xdeNfaloc) {
		this.xdeNfaloc = xdeNfaloc;
	}

	@Column(name = "XDE_NFALOC")
	public BigDecimal getXdeNfaloc() {
		return xdeNfaloc;
	}

	public void setXdeOtloc(BigDecimal xdeOtloc) {
		this.xdeOtloc = xdeOtloc;
	}

	@Column(name = "XDE_OTLOC")
	public BigDecimal getXdeOtloc() {
		return xdeOtloc;
	}
	public void setDeFaloc(BigDecimal deFaloc) {
		this.deFaloc = deFaloc;
	}
	
	@Column(name = "DE_FALOC")
	public BigDecimal getDeFaloc() {
		return deFaloc;
	}
	
	public void setDeNfaloc(BigDecimal deNfaloc) {
		this.deNfaloc = deNfaloc;
	}
	
	@Column(name = "DE_NFALOC")
	public BigDecimal getDeNfaloc() {
		return deNfaloc;
	}
	
	public void setDeOtloc(BigDecimal deOtloc) {
		this.deOtloc = deOtloc;
	}
	
	@Column(name = "DE_OTLOC")
	public BigDecimal getDeOtloc() {
		return deOtloc;
	}

	public void setDeTotloc(BigDecimal deTotloc) {
		this.deTotloc = deTotloc;
	}

	@Column(name = "DE_TOTLOC")
	public BigDecimal getDeTotloc() {
		return deTotloc;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "TAG", length = 45)
	public String getTag() {
		return tag;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public int getValid() {
		return valid;
	}

	public void setOldprojname(String oldprojname) {
		this.oldprojname = oldprojname;
	}

	@Column(name = "OLDPROJNAME", length = 200)
	public String getOldprojname() {
		return oldprojname;
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

	public String toString() {
		return "TbsCggChg [id=" + id + ",sn=" + sn
				+ ",timestampInput=" + timestampInput
				+ ",cusId=" + cusId + ",projId=" + projId +",xdeFaloc="
				+ xdeFaloc + ",xdeNfaloc=" + xdeNfaloc + ",xdeOtloc=" + deOtloc 
				+ ",deFaloc="+ deFaloc + ",deNfaloc=" + deNfaloc + ",deOtloc=" + deOtloc
				+ ",deTotloc=" + deTotloc + ",tag=" + tag + ",valid=" + valid
				+ ",oldprojname=" + oldprojname + ",by1=" + by1 + ",by2=" + by2
				+ ",by3=" + by3 + "]";
	}

}
