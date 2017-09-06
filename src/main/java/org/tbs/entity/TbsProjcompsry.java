package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_projcompsry:代偿申请单
 */
@Entity
@Table(name = "tbs_projcompsry")
public class TbsProjcompsry implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 项目编号=DCSQ+日期+序号,每个新日期都从001开始编号:项目编号=DCSQ+日期+序号,每个新日期都从001开始编号
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
	 * 代偿日期:代偿日期
	 */
	private Date date;

	/**
	 * bizmemo:
	 */
	private String bizmemo;
	

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
	private Set<TbsProjcompsryPay> tbsProjcompsryPaySet = new HashSet<TbsProjcompsryPay>(
			0);
	
	/** 
	 * 二期改库 2016-06-01 
	 */
	private TbsProjundwrt tbsProjundwrt;
	private String riskmemo;
	private boolean del;
	
	

	public TbsProjcompsry() {
		super();
	}

	public TbsProjcompsry(int id, String sn, int valid,
			Date timestampInput, Date timestampUpdate, int keyinId,
			String projSn, String arolename, String brolename, BigDecimal zbe, Date date,
			BigDecimal dcye, BigDecimal bcdcje, BigDecimal bcdcbj,
			BigDecimal bcdclx, String dcfx, String fulId, String bizmemo,
			String by1, String by2, String by3, TbsProj tbsProj,
			Bdf2Dept bdf2Dept, Bdf2User bdf2User,
			Set<TbsProjcompsryPay> tbsProjcompsryPaySet,
			boolean del, TbsProjundwrt tbsProjundwrt, String riskmemo  ) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.date = date;
		this.keyinId = keyinId;
		this.bizmemo = bizmemo;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsProjcompsryPaySet = tbsProjcompsryPaySet;
		this.tbsProjundwrt = tbsProjundwrt;
		this.del = del;
		this.riskmemo = riskmemo;
		
		
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

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_")
	public Date getDate() {
		return date;
	}

	public void setBizmemo(String bizmemo) {
		this.bizmemo = bizmemo;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "bizmemo", columnDefinition = "CLOB")
	public String getBizmemo() {
		return bizmemo;
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

	public void setTbsProjcompsryPaySet(
			Set<TbsProjcompsryPay> tbsProjcompsryPaySet) {
		this.tbsProjcompsryPaySet = tbsProjcompsryPaySet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjcompsry")
	public Set<TbsProjcompsryPay> getTbsProjcompsryPaySet() {
		return tbsProjcompsryPaySet;
	}
	
	public void setTbsProjundwrt(TbsProjundwrt tbsProjundwrt) {
		this.tbsProjundwrt = tbsProjundwrt;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UNDWRT_ID")
	public TbsProjundwrt getTbsProjundwrt() {
		return tbsProjundwrt;
	}

	public void setRiskmemo(String riskmemo) {
		this.riskmemo = riskmemo;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "riskmemo", columnDefinition = "CLOB")
	public String getRiskmemo() {
		return riskmemo;
	}
	
	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}
	
	public String toString() {
		return "TbsProjcompsry [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",date=" + date +",bizmemo=" + bizmemo + ",by1=" + by1 + ",by2="
				+ by2 + ",by3=" + by3 + ",tbsProj=" + tbsProj + ",tbsProjcompsryPaySet="
				+ tbsProjcompsryPaySet 
				+ ",del=" + del
				+ ",riskmemo=" + riskmemo
				+ ",tbsProjundwrt=" + tbsProjundwrt
				+ "]";
	}

}
