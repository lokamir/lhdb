package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * tbs_projcfm2_bizvt:签批决议录入表子表-业务类型品种和金额
 */
@Entity
@Table(name = "tbs_projcfm2_bizvt")
public class TbsProjcfm2Bizvt implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * PROJCFM2_SN:
	 */
	private String projcfm2Sn;

	/**
	 * 可受理担保授信金额。回插入tbs_proj_bizvt.vloc:可受理担保授信金额。回插入tbs_proj_bizvt.vloc
	 */
	private BigDecimal vloc;

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
	 * tbs_projcfm2:签批决议录入表。表单数据回插入tbs_proj和tbs_proj_hisloc
	 */
	private TbsProjcfm2 tbsProjcfm2;

	/**
	 * tbs_bas_bizvar:业务品种
	 */
	private TbsBasBizvar tbsBasBizvar;

	/**
	 * tbs_bas_biztype:业务类型
	 */
	private TbsBasBiztype tbsBasBiztype;

	public TbsProjcfm2Bizvt() {
		super();
	}

	public TbsProjcfm2Bizvt(int id, String projcfm2Sn, BigDecimal vloc,
			String by1, String by2, String by3, TbsProjcfm2 tbsProjcfm2,
			TbsBasBizvar tbsBasBizvar, TbsBasBiztype tbsBasBiztype) {
		super();
		this.id = id;
		this.projcfm2Sn = projcfm2Sn;
		this.vloc = vloc;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProjcfm2 = tbsProjcfm2;
		this.tbsBasBizvar = tbsBasBizvar;
		this.tbsBasBiztype = tbsBasBiztype;
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

	public void setProjcfm2Sn(String projcfm2Sn) {
		this.projcfm2Sn = projcfm2Sn;
	}

	@Column(name = "PROJCFM2_SN", length = 20)
	public String getProjcfm2Sn() {
		return projcfm2Sn;
	}

	public void setVloc(BigDecimal vloc) {
		this.vloc = vloc;
	}

	@Column(name = "VLOC")
	public BigDecimal getVloc() {
		return vloc;
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

	public void setTbsProjcfm2(TbsProjcfm2 tbsProjcfm2) {
		this.tbsProjcfm2 = tbsProjcfm2;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJCFM2_ID")
	public TbsProjcfm2 getTbsProjcfm2() {
		return tbsProjcfm2;
	}

	public void setTbsBasBizvar(TbsBasBizvar tbsBasBizvar) {
		this.tbsBasBizvar = tbsBasBizvar;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BIZVAR_ID")
	public TbsBasBizvar getTbsBasBizvar() {
		return tbsBasBizvar;
	}

	public void setTbsBasBiztype(TbsBasBiztype tbsBasBiztype) {
		this.tbsBasBiztype = tbsBasBiztype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BIZTYPE_ID")
	public TbsBasBiztype getTbsBasBiztype() {
		return tbsBasBiztype;
	}

	public String toString() {
		return "TbsProjcfm2Bizvt [id=" + id + ",projcfm2Sn=" + projcfm2Sn
				+ ",vloc=" + vloc + ",by1=" + by1 + ",by2=" + by2 + ",by3="
				+ by3 + ",tbsProjcfm2=" + tbsProjcfm2 + ",tbsBasBizvar="
				+ tbsBasBizvar + ",tbsBasBiztype=" + tbsBasBiztype + "]";
	}

}
