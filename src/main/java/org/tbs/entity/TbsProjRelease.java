package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_proj_release:项目解保单
 */
@Entity
@Table(name = "tbs_proj_release")
public class TbsProjRelease implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 表单编号='XMJB'+单据日期+序号:表单编号='XMJB'+单据日期+序号
	 */
	private String sn;

	/**
	 * 是否有效，默认为0无效，审批过后为1有效。:是否有效，默认为0无效，审批过后为1有效。
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
	 * 备注:备注
	 */
	private String memo;

	/**
	 * 删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据:删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据
	 */
	private boolean del;

	/**
	 * RLSDATE:
	 */
	private Date rlsdate;

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
	 * 二期改库 2016-06-01 
	 */
	private TbsProjundwrt tbsProjundwrt;
	private TbsProj tbsProj;
	private BigDecimal faloc;
	private BigDecimal nfaloc;
	private BigDecimal otloc;
	private BigDecimal totloc;
	private boolean lnrp;

	public TbsProjRelease() {
		super();
	}

	public TbsProjRelease(int id, String sn, int valid,
			Date timestampInput, Date timestampUpdate, int keyinId,
			String memo, boolean del, Date rlsdate, String by1,
			String by2, String by3, TbsProjundwrt tbsProjundwrt, TbsProj tbsProj,
			BigDecimal faloc,BigDecimal nfaloc,BigDecimal otloc,BigDecimal totloc,
			boolean lnrp) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.keyinId = keyinId;
		this.memo = memo;
		this.del = del;
		this.rlsdate = rlsdate;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsProjundwrt = tbsProjundwrt;
		this.faloc = faloc;
		this.nfaloc = nfaloc;
		this.otloc = otloc;
		this.totloc = totloc;
		this.lnrp = lnrp;
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
	@Column(name = "TIMESTAMP_INPUT")
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setTimestampUpdate(Date timestampUpdate) {
		this.timestampUpdate = timestampUpdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_UPDATE")
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

	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}

	public void setRlsdate(Date rlsdate) {
		this.rlsdate = rlsdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RLSDATE")
	public Date getRlsdate() {
		return rlsdate;
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
	@JoinColumn(name = "UNDWRT_ID")
	public TbsProjundwrt getTbsProjundwrt() {
		return tbsProjundwrt;
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
	
	public void setLnrp(boolean lnrp) {
		this.lnrp = lnrp;
	}

	@Column(name = "LNRP")
	public boolean isLnrp() {
		return lnrp;
	}
	
	public String toString() {
		return "TbsProjRelease [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",memo=" + memo
				+ ",del=" + del + ",rlsdate=" + rlsdate + ",by1=" + by1 
				+ ",by2=" + by2 + ",by3=" + by3 
				+ ",tbsProj=" + tbsProj + ",tbsProjundwrt=" + tbsProjundwrt
				+ ",faloc=" + faloc + ",nfaloc=" + nfaloc + ",otloc=" + otloc + ",totloc=" + totloc
				+ ",lnrp=" + lnrp + "]";
	}

}
