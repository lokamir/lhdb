package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * tbs_proj_hisstatus:项目基本信息子表-项目历史状态
 */
@Entity
@Table(name = "tbs_proj_hisstatus")
public class TbsProjHisstatus implements Serializable {

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
	 * 状态生效日期:状态生效日期
	 */
	private Date psdate;

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
	 * tbs_bas_ps:项目状态
	 */
	private TbsBasPs tbsBasPs;

	public TbsProjHisstatus() {
		super();
	}

	public TbsProjHisstatus(int id, String projSn, Date psdate, String by1,
			String by2, String by3, TbsProj tbsProj, TbsBasPs tbsBasPs) {
		super();
		this.id = id;
		this.projSn = projSn;
		this.psdate = psdate;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsBasPs = tbsBasPs;
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

	public void setPsdate(Date psdate) {
		this.psdate = psdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PSDATE")
	public Date getPsdate() {
		return psdate;
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

	public void setTbsBasPs(TbsBasPs tbsBasPs) {
		this.tbsBasPs = tbsBasPs;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PS_ID")
	public TbsBasPs getTbsBasPs() {
		return tbsBasPs;
	}

	public String toString() {
		return "TbsProjHisstatus [id=" + id + ",projSn=" + projSn + ",psdate="
				+ psdate + ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3
				+ ",tbsProj=" + tbsProj + ",tbsBasPs=" + tbsBasPs + "]";
	}

}
