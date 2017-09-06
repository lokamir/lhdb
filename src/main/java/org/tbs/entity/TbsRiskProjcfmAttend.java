package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * tbs_risk_projcfm_attend:风险控制委员会会议-参会人员子表
 */
@Entity
@Table(name = "tbs_risk_projcfm_attend")
public class TbsRiskProjcfmAttend implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

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
	 * tbs_risk_projcfm:风险控制委员会会议（含决议）
	 */
	private TbsRiskProjcfm tbsRiskProjcfm;
	
	private Bdf2User bdf2User;
	
	public TbsRiskProjcfmAttend() {
		super();
	}

	public TbsRiskProjcfmAttend(int id, String by1, String by2, String by3,
			TbsRiskProjcfm tbsRiskProjcfm, Bdf2User bdf2User) {
		super();
		this.id = id;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsRiskProjcfm = tbsRiskProjcfm;
		this.bdf2User = bdf2User;
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

	public void setTbsRiskProjcfm(TbsRiskProjcfm tbsRiskProjcfm) {
		this.tbsRiskProjcfm = tbsRiskProjcfm;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RPCFMID")
	public TbsRiskProjcfm getTbsRiskProjcfm() {
		return tbsRiskProjcfm;
	}
	
	public void setBdf2User(Bdf2User bdf2User) {
		this.bdf2User = bdf2User;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ATTEND_UID")
	public Bdf2User getBdf2User() {
		return bdf2User;
	}
	
	public String toString() {
		return "TbsRiskProjcfmAttend [id=" + id + ",by1=" + by1 + ",by2=" + by2
				+ ",by3=" + by3 + ",tbsRiskProjcfm=" + tbsRiskProjcfm + ",bdf2User=" + bdf2User +"]";
	}

}
