package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * tbs_projchange_majcont_cfmattend:
 */
@Entity
@Table(name = "tbs_projchange_majcont_cfmattend")
public class TbsProjchangeMajcontCfmattend implements Serializable {

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
	 * tbs_projchange_majcont_cfm:项目三要素变更决议录入表
	 */
	private TbsProjchangeMajcontCfm tbsProjchangeMajcontCfm;
	private Bdf2User bdf2User;
	
	
	public TbsProjchangeMajcontCfmattend() {
		super();
	}

	public TbsProjchangeMajcontCfmattend(int id, String by1, String by2,
			String by3, TbsProjchangeMajcontCfm tbsProjchangeMajcontCfm,
			Bdf2User bdf2User) {
		super();
		this.id = id;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProjchangeMajcontCfm = tbsProjchangeMajcontCfm;
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

	public void setTbsProjchangeMajcontCfm(
			TbsProjchangeMajcontCfm tbsProjchangeMajcontCfm) {
		this.tbsProjchangeMajcontCfm = tbsProjchangeMajcontCfm;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CFM_ID")
	public TbsProjchangeMajcontCfm getTbsProjchangeMajcontCfm() {
		return tbsProjchangeMajcontCfm;
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
		return "TbsProjchangeMajcontCfmattend [id=" + id + ",by1=" + by1
				+ ",by2=" + by2 + ",by3=" + by3 
				+ ",tbsProjchangeMajcontCfm=" + tbsProjchangeMajcontCfm 
				+ ",by2User=" + bdf2User
				+ "]";
	}

}
