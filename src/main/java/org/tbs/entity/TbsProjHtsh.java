package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * tbs_proj_htsh:项目基本信息子表-4.1合同审核  这个流程只在系统里走个过场不涉及任何其他单据
 */
@Entity
@Table(name = "tbs_proj_htsh")
public class TbsProjHtsh implements Serializable {

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
	 * 默认为0，审核未通过，如果通过了，变为1同时upadtestatus表内的数据:默认为0，审核未通过，如果通过了，变为1同时upadtestatus表内的数据
	 */
	private boolean valid;

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

	public TbsProjHtsh() {
		super();
	}

	public TbsProjHtsh(int id, String projSn, boolean valid, String by1,
			String by2, String by3, TbsProj tbsProj) {
		super();
		this.id = id;
		this.projSn = projSn;
		this.valid = valid;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
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

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
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

	public String toString() {
		return "TbsProjHtsh [id=" + id + ",projSn=" + projSn + ",valid="
				+ valid + ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3
				+ ",tbsProj=" + tbsProj + "]";
	}

}
