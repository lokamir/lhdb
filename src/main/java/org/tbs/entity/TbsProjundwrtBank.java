package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * tbs_projundwrt_bank:承保录入单子表-银行信息
 */
@Entity
@Table(name = "tbs_projundwrt_bank")
public class TbsProjundwrtBank implements Serializable {

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
	 * tbs_projundwrt:承保审批单
	 */
	private TbsProjundwrt tbsProjundwrt;

	/**
	 * tbs_bas_bank:银行
	 */
	private TbsBasBank tbsBasBank_M;

	/**
	 * tbs_bas_bank:银行
	 */
	private TbsBasBank tbsBasBank_S;

	public TbsProjundwrtBank() {
		super();
	}

	public TbsProjundwrtBank(int id, String by1, String by2, String by3,
			TbsProjundwrt tbsProjundwrt, TbsBasBank tbsBasBank_M,
			TbsBasBank tbsBasBank_S) {
		super();
		this.id = id;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProjundwrt = tbsProjundwrt;
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

	public void setTbsProjundwrt(TbsProjundwrt tbsProjundwrt) {
		this.tbsProjundwrt = tbsProjundwrt;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJUNDWRT_ID")
	public TbsProjundwrt getTbsProjundwrt() {
		return tbsProjundwrt;
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
		return "TbsProjundwrtBank [id=" + id + ",by1=" + by1 + ",by2=" + by2
				+ ",by3=" + by3 + ",tbsProjundwrt=" + tbsProjundwrt
				+ ",tbsBasBank_M=" + tbsBasBank_M + ",tbsBasBank_S=" + tbsBasBank_S
				+ "]";
	}

}
