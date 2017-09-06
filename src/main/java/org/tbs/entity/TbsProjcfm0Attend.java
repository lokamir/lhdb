package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * tbs_projcfm0_attend:项目决策审批上会通知单-参会人员子表
 */
@Entity
@Table(name = "tbs_projcfm0_attend")
public class TbsProjcfm0Attend implements Serializable {

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
	 * tbs_projcfm0:项目决策审批上会通知单
	 */
	private TbsProjcfm0 tbsProjcfm0;

	/**
	 * bdf2_user:
	 */
	private Bdf2User bdf2User;

	public TbsProjcfm0Attend() {
		super();
	}

	public TbsProjcfm0Attend(int id, String by1, String by2, String by3,
			TbsProjcfm0 tbsProjcfm0, Bdf2User bdf2User) {
		super();
		this.id = id;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProjcfm0 = tbsProjcfm0;
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

	public void setTbsProjcfm0(TbsProjcfm0 tbsProjcfm0) {
		this.tbsProjcfm0 = tbsProjcfm0;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJCFM0_ID")
	public TbsProjcfm0 getTbsProjcfm0() {
		return tbsProjcfm0;
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
		return "TbsProjcfm0Attend [id=" + id + ",by1=" + by1 + ",by2=" + by2
				+ ",by3=" + by3 + ",tbsProjcfm0=" + tbsProjcfm0 + ",bdf2User="
				+ bdf2User + "]";
	}

}
