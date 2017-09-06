package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * tbs_projchange_role:项目经理变更。 项目如果在审批状态下，禁止变更
 */
@Entity
@Table(name = "tbs_projchange_role")
public class TbsProjchangeRole implements Serializable {

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
	 * TIMESTAMP_INPUT:
	 */
	private Date timestampInput;

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
	private Bdf2User bdf2User_AO;

	/**
	 * bdf2_user:
	 */
	private Bdf2User bdf2User_BO;

	/**
	 * bdf2_user:
	 */
	private Bdf2User bdf2User_AN;

	/**
	 * bdf2_user:
	 */
	private Bdf2User bdf2User_BN;

	
	/**
	 * 二期改库 2016-06-01
	 * */
	private int valid;
	private boolean del;
	private int keyinId;
	private String sn;
	private String memo;
	
	
	public TbsProjchangeRole() {
		super();
	}

	public TbsProjchangeRole(int id, String sn, String projSn, Date timestampInput,
			String by1, String by2, String by3, TbsProj tbsProj,
			Bdf2User bdf2User_AO, Bdf2User bdf2User_BO, Bdf2User bdf2User_AN,
			Bdf2User bdf2User_BN, String memo,
			int valid, boolean del, int keyinId) {
		super();
		this.id = id;
		this.projSn = projSn;
		this.timestampInput = timestampInput;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.bdf2User_AO = bdf2User_AO;
		this.bdf2User_BO = bdf2User_BO;
		this.bdf2User_AN = bdf2User_AN;
		this.bdf2User_BN = bdf2User_BN;
		this.valid = valid;
		this.del = del;
		this.keyinId = keyinId;
		this.sn = sn;
		this.memo = memo;
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

	public void setBdf2User_AN(Bdf2User bdf2User_AN) {
		this.bdf2User_AN = bdf2User_AN;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NEWAROLE")
	public Bdf2User getBdf2User_AN() {
		return bdf2User_AN;
	}

	public void setBdf2User_BN(Bdf2User bdf2User_BN) {
		this.bdf2User_BN = bdf2User_BN;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NEWBROLE")
	public Bdf2User getBdf2User_BN() {
		return bdf2User_BN;
	}

	public void setBdf2User_AO(Bdf2User bdf2User_AO) {
		this.bdf2User_AO = bdf2User_AO;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OLDAROLE")
	public Bdf2User getBdf2User_AO() {
		return bdf2User_AO;
	}

	public void setBdf2User_BO(Bdf2User bdf2User_BO) {
		this.bdf2User_BO = bdf2User_BO;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OLDBROLE")
	public Bdf2User getBdf2User_BO() {
		return bdf2User_BO;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public int getValid() {
		return valid;
	}
	
	public void setKeyinId(int keyinId) {
		this.keyinId = keyinId;
	}

	@Column(name = "KEYIN_ID")
	public int getKeyinId() {
		return keyinId;
	}
	
	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "SN", length = 20)
	public String getSn() {
		return sn;
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
	
	public String toString() {
		return "TbsProjchangeRole [id=" + id + ",projSn=" + projSn
				+ ",timestampInput=" + timestampInput + ",by1=" + by1 + ",by2="
				+ by2 + ",by3=" + by3 + ",tbsProj=" + tbsProj + ",bdf2User_BO="
				+ bdf2User_BO + ",bdf2User_AN=" + bdf2User_AN + ",bdf2User_BN=" + bdf2User_BN
				+ ",bdf2User_AO=" + bdf2User_AO 
				+ ",del=" + del + ",keyinId=" + keyinId + ",valid=" + valid
				+ ",sn=" + sn + ",memo=" + memo + "]";
	}

}
