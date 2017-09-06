package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * tbs_projcfm1_bizvt:会议决议录入表子表-业务类型品种和金额
 */
@Entity
@Table(name = "tbs_projcfm1_bizvt")
public class TbsProjcfm1Bizvt implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * PROJCFM1_SN:
	 */
	private String projcfm1Sn;

	/**
	 * 可受理担保授信金额,回插入tbs_proj_bizvt.vloc:可受理担保授信金额,回插入tbs_proj_bizvt.vloc
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
	 * tbs_projcfm1:会议决议录入表。表单数据回插入tbs_proj和tbs_proj_hisloc
	 */
	private TbsProjcfm1 tbsProjcfm1;

	/**
	 * tbs_bas_bizvar:业务品种
	 */
	private TbsBasBizvar tbsBasBizvar;

	/**
	 * tbs_bas_biztype:业务类型
	 */
	private TbsBasBiztype tbsBasBiztype;

	public TbsProjcfm1Bizvt() {
		super();
	}

	public TbsProjcfm1Bizvt(int id, String projcfm1Sn, BigDecimal vloc,
			String by1, String by2, String by3, TbsProjcfm1 tbsProjcfm1,
			TbsBasBizvar tbsBasBizvar, TbsBasBiztype tbsBasBiztype) {
		super();
		this.id = id;
		this.projcfm1Sn = projcfm1Sn;
		this.vloc = vloc;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProjcfm1 = tbsProjcfm1;
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

	public void setProjcfm1Sn(String projcfm1Sn) {
		this.projcfm1Sn = projcfm1Sn;
	}

	@Column(name = "PROJCFM1_SN", length = 20)
	public String getProjcfm1Sn() {
		return projcfm1Sn;
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

	public void setTbsProjcfm1(TbsProjcfm1 tbsProjcfm1) {
		this.tbsProjcfm1 = tbsProjcfm1;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJCFM1_ID")
	public TbsProjcfm1 getTbsProjcfm1() {
		return tbsProjcfm1;
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
		return "TbsProjcfm1Bizvt [id=" + id + ",projcfm1Sn=" + projcfm1Sn
				+ ",vloc=" + vloc + ",by1=" + by1 + ",by2=" + by2 + ",by3="
				+ by3 + ",tbsProjcfm1=" + tbsProjcfm1 + ",tbsBasBizvar="
				+ tbsBasBizvar + ",tbsBasBiztype=" + tbsBasBiztype + "]";
	}

}
