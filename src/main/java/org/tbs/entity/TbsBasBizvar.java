package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_bas_bizvar:业务品种
 */
@Entity
@Table(name = "tbs_bas_bizvar")
public class TbsBasBizvar implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * 默认为1，生效:默认为1，生效
	 */
	private boolean valid;

	/**
	 * tbs_bas_biztype:业务类型
	 */
	private TbsBasBiztype tbsBasBiztype;

	/**
	 * tbs_proj_bizvt:项目基本信息子表-业务类型品种和金额
	 */
	private Set<TbsProjBizvt> tbsProjBizvtSet = new HashSet<TbsProjBizvt>(0);

	/**
	 * tbs_projcfm1_bizvt:会议决议录入表子表-业务类型品种和金额
	 */
	private Set<TbsProjcfm1Bizvt> tbsProjcfm1BizvtSet = new HashSet<TbsProjcfm1Bizvt>(
			0);

	/**
	 * tbs_projcfm2_bizvt:签批决议录入表子表-业务类型品种和金额
	 */
	private Set<TbsProjcfm2Bizvt> tbsProjcfm2BizvtSet = new HashSet<TbsProjcfm2Bizvt>(
			0);

	/**
	 * tbs_projundwrt_bizvt:承保审批单-业务类型品种和金额
	 */
	private Set<TbsProjundwrtBizvt> tbsProjundwrtBizvtSet = new HashSet<TbsProjundwrtBizvt>(
			0);

	public TbsBasBizvar() {
		super();
	}

	public TbsBasBizvar(int id, String name, boolean valid,
			TbsBasBiztype tbsBasBiztype, Set<TbsProjBizvt> tbsProjBizvtSet,
			Set<TbsProjcfm1Bizvt> tbsProjcfm1BizvtSet,
			Set<TbsProjcfm2Bizvt> tbsProjcfm2BizvtSet,
			Set<TbsProjundwrtBizvt> tbsProjundwrtBizvtSet) {
		super();
		this.id = id;
		this.name = name;
		this.valid = valid;
		this.tbsBasBiztype = tbsBasBiztype;
		this.tbsProjBizvtSet = tbsProjBizvtSet;
		this.tbsProjcfm1BizvtSet = tbsProjcfm1BizvtSet;
		this.tbsProjcfm2BizvtSet = tbsProjcfm2BizvtSet;
		this.tbsProjundwrtBizvtSet = tbsProjundwrtBizvtSet;
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

	@Column(name = "NAME_", length = 20)
	public String getName() {
		return name;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
	}

	public void setTbsBasBiztype(TbsBasBiztype tbsBasBiztype) {
		this.tbsBasBiztype = tbsBasBiztype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BIZTYPE_ID")
	public TbsBasBiztype getTbsBasBiztype() {
		return tbsBasBiztype;
	}

	public void setTbsProjBizvtSet(Set<TbsProjBizvt> tbsProjBizvtSet) {
		this.tbsProjBizvtSet = tbsProjBizvtSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasBizvar")
	public Set<TbsProjBizvt> getTbsProjBizvtSet() {
		return tbsProjBizvtSet;
	}

	public void setTbsProjcfm1BizvtSet(Set<TbsProjcfm1Bizvt> tbsProjcfm1BizvtSet) {
		this.tbsProjcfm1BizvtSet = tbsProjcfm1BizvtSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasBizvar")
	public Set<TbsProjcfm1Bizvt> getTbsProjcfm1BizvtSet() {
		return tbsProjcfm1BizvtSet;
	}

	public void setTbsProjcfm2BizvtSet(Set<TbsProjcfm2Bizvt> tbsProjcfm2BizvtSet) {
		this.tbsProjcfm2BizvtSet = tbsProjcfm2BizvtSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasBizvar")
	public Set<TbsProjcfm2Bizvt> getTbsProjcfm2BizvtSet() {
		return tbsProjcfm2BizvtSet;
	}

	public void setTbsProjundwrtBizvtSet(
			Set<TbsProjundwrtBizvt> tbsProjundwrtBizvtSet) {
		this.tbsProjundwrtBizvtSet = tbsProjundwrtBizvtSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasBizvar")
	public Set<TbsProjundwrtBizvt> getTbsProjundwrtBizvtSet() {
		return tbsProjundwrtBizvtSet;
	}

	public String toString() {
		return "TbsBasBizvar [id=" + id + ",name=" + name + ",valid=" + valid
				+ ",tbsBasBiztype=" + tbsBasBiztype + ",tbsProjBizvtSet="
				+ tbsProjBizvtSet + ",tbsProjcfm1BizvtSet="
				+ tbsProjcfm1BizvtSet + ",tbsProjcfm2BizvtSet="
				+ tbsProjcfm2BizvtSet + ",tbsProjsfaBizvtSet="
				+ ",tbsProjundwrtBizvtSet="
				+ tbsProjundwrtBizvtSet + "]";
	}

}
