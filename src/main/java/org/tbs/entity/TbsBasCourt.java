package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_bas_court:法院基本信息
 */
@Entity
@Table(name = "tbs_bas_court")
public class TbsBasCourt implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 法院名称:法院名称
	 */
	private String name;

	/**
	 * 详细信息:详细信息
	 */
	private String detail;

	/**
	 * VALID:
	 */
	private boolean valid;

	/**
	 * DEL:
	 */
	private int del;

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
	 * tbs_bas_area:客户基本信息-区域
	 */
	private TbsBasArea tbsBasArea;

	/**
	 * tbs_proj:项目基本信息主表
	 */
	private Set<TbsProjBizvt> tbsProjBizvtSet = new HashSet<TbsProjBizvt>(0);

	public TbsBasCourt() {
		super();
	}

	public TbsBasCourt(int id, String name, String detail, boolean valid, int del,
			String by1, String by2, String by3, TbsBasArea tbsBasArea,
			Set<TbsProjBizvt> tbsProjBizvtSet) {
		super();
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.valid = valid;
		this.del = del;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsBasArea = tbsBasArea;
		this.tbsProjBizvtSet = tbsProjBizvtSet;
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

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", length = 45)
	public String getName() {
		return name;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "DETAIL", length = 500)
	public String getDetail() {
		return detail;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean getValid() {
		return valid;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public int getDel() {
		return del;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	@Column(name = "BY1", length = 300)
	public String getBy1() {
		return by1;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	@Column(name = "BY2", length = 300)
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

	public void setTbsBasArea(TbsBasArea tbsBasArea) {
		this.tbsBasArea = tbsBasArea;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AREAID")
	public TbsBasArea getTbsBasArea() {
		return tbsBasArea;
	}

	public void setTbsProjBizvtSet(Set<TbsProjBizvt> tbsProjBizvtSet) {
		this.tbsProjBizvtSet = tbsProjBizvtSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasCourt")
	public Set<TbsProjBizvt> getTbsProjBizvtSet() {
		return tbsProjBizvtSet;
	}

	public String toString() {
		return "TbsBasCourt [id=" + id + ",name=" + name + ",detail=" + detail
				+ ",valid=" + valid + ",del=" + del + ",by1=" + by1 + ",by2="
				+ by2 + ",by3=" + by3 + ",tbsBasArea=" + tbsBasArea
				+ ",tbsProjBizvtSet=" + tbsProjBizvtSet + "]";
	}

}
