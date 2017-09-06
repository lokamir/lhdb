package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * tbs_proj_hisrole:项目基本信息子表-项目经理历史记录。此表用来查询项目下项目经理变更的记录
 */
@Entity
@Table(name = "tbs_proj_hisrole")
public class TbsProjHisrole implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 输入时间戳（变更日期）:输入时间戳（变更日期）
	 */
	private Date timestampInput;

	/**
	 * PROJ_SN:
	 */
	private String projSn;

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
	 * bdf2_user:
	 */
	private Bdf2User bdf2User_A;

	/**
	 * bdf2_user:
	 */
	private Bdf2User bdf2User_B;

	public TbsProjHisrole() {
		super();
	}

	public TbsProjHisrole(int id, Date timestampInput, String projSn,
			String by1, String by2, String by3, TbsProj tbsProj,
			Bdf2User bdf2User_A, Bdf2User bdf2User_B) {
		super();
		this.id = id;
		this.timestampInput = timestampInput;
		this.projSn = projSn;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.bdf2User_A = bdf2User_A;
		this.bdf2User_B = bdf2User_B;
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

	public void setBdf2User_A(Bdf2User bdf2User_A) {
		this.bdf2User_A = bdf2User_A;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AROLEID")
	public Bdf2User getBdf2User_A() {
		return bdf2User_A;
	}

	public void setBdf2User_B(Bdf2User bdf2User_B) {
		this.bdf2User_B = bdf2User_B;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BROLEID")
	public Bdf2User getBdf2User_B() {
		return bdf2User_B;
	}

	public String toString() {
		return "TbsProjHisrole [id=" + id + ",timestampInput=" + timestampInput
				+ ",projSn=" + projSn + ",by1=" + by1 + ",by2=" + by2 + ",by3="
				+ by3 + ",tbsProj=" + tbsProj + ",bdf2User_A=" + bdf2User_A
				+ ",bdf2User_B=" + bdf2User_B + "]";
	}

}
