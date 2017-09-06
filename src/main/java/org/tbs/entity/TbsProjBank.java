package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * tbs_proj_bank:项目基本信息子表-银行
 */
@Entity
@Table(name = "tbs_proj_bank")
public class TbsProjBank implements Serializable {

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
	 * tbs_proj:项目基本信息主表
	 */
	private TbsProj tbsProj;

	/**
	 * tbs_bas_bank:银行
	 */
	private TbsBasBank tbsBasBank_M;

	/**
	 * tbs_bas_bank:银行
	 */
	private TbsBasBank tbsBasBank_S;

	public TbsProjBank() {
		super();
	}

	public TbsProjBank(int id, String by1, String by2, String by3,
			TbsProj tbsProj, TbsBasBank tbsBasBank_M, TbsBasBank tbsBasBank_S) {
		super();
		this.id = id;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsBasBank_M = tbsBasBank_M;
		this.tbsBasBank_S = tbsBasBank_S;
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

	public void setTbsProj(TbsProj tbsProj) {
		this.tbsProj = tbsProj;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJ_ID")
	public TbsProj getTbsProj() {
		return tbsProj;
	}

	public void setTbsBasBank_M(TbsBasBank tbsBasBank_M) {
		this.tbsBasBank_M = tbsBasBank_M;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AFLB_ID")
	public TbsBasBank getTbsBasBank_M() {
		return tbsBasBank_M;
	}

	public void setTbsBasBank_S(TbsBasBank tbsBasBank_S) {
		this.tbsBasBank_S = tbsBasBank_S;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AFLSB_ID")
	public TbsBasBank getTbsBasBank_S() {
		return tbsBasBank_S;
	}

	public String toString() {
		return "TbsProjBank [id=" + id + ",by1=" + by1 + ",by2=" + by2
				+ ",by3=" + by3 + ",tbsProj=" + tbsProj + ",tbsBasBank_M="
				+ tbsBasBank_M + ",tbsBasBank_S=" + tbsBasBank_S + "]";
	}

}
