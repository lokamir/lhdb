package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_projundwrt_bizvt:承保审批单-业务类型品种和金额
 */
@Entity
@Table(name = "tbs_projundwrt_bizvt")
public class TbsProjundwrtBizvt implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 输入时间戳:输入时间戳
	 */
	private Date timestampInput;

	/**
	 * PROJ_SN:
	 */
	private String projSn;

	/**
	 * PROJUNDWRT_SN:
	 */
	private String projundwrtSn;

	/**
	 * 本次承保金额。数据来自tbs_proj_cfm1或2,可修改，系统将这里的金额相加，必须等于主表里面对应业务类型的的appfaloc/appnfaloc/appotloc:本次承保金额。数据来自tbs_proj_cfm1或2,可修改，系统将这里的金额相加，必须等于主表里面对应业务类型的的appfaloc/appnfaloc/appotloc
	 */
	private BigDecimal loc;

	/**
	 * 删除标记默认为0:删除标记默认为0
	 */
	private boolean del;

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
	 * tbs_projundwrt:承保审批单
	 */
	private TbsProjundwrt tbsProjundwrt;

	/**
	 * tbs_bas_bizvar:业务品种
	 */
	private TbsBasBizvar tbsBasBizvar;

	/**
	 * tbs_bas_biztype:业务类型
	 */
	private TbsBasBiztype tbsBasBiztype;

	public TbsProjundwrtBizvt() {
		super();
	}

	public TbsProjundwrtBizvt(int id, Date timestampInput, String projSn,
			String projundwrtSn, BigDecimal loc, boolean del, String by1,
			String by2, String by3, TbsProj tbsProj,
			TbsProjundwrt tbsProjundwrt, TbsBasBizvar tbsBasBizvar,
			TbsBasBiztype tbsBasBiztype) {
		super();
		this.id = id;
		this.timestampInput = timestampInput;
		this.projSn = projSn;
		this.projundwrtSn = projundwrtSn;
		this.loc = loc;
		this.del = del;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsProjundwrt = tbsProjundwrt;
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

	public void setTimestampInput(Date timestampInput) {
		this.timestampInput = timestampInput;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_INPUT", nullable = false)
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setProjSn(String projSn) {
		this.projSn = projSn;
	}

	@Column(name = "PROJ_SN", length = 20)
	public String getProjSn() {
		return projSn;
	}

	public void setProjundwrtSn(String projundwrtSn) {
		this.projundwrtSn = projundwrtSn;
	}

	@Column(name = "PROJUNDWRT_SN", length = 20)
	public String getProjundwrtSn() {
		return projundwrtSn;
	}

	public void setLoc(BigDecimal loc) {
		this.loc = loc;
	}

	@Column(name = "LOC")
	public BigDecimal getLoc() {
		return loc;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
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

	public void setTbsProjundwrt(TbsProjundwrt tbsProjundwrt) {
		this.tbsProjundwrt = tbsProjundwrt;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJUNDWRT_ID")
	public TbsProjundwrt getTbsProjundwrt() {
		return tbsProjundwrt;
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
		return "TbsProjundwrtBizvt [id=" + id + ",timestampInput="
				+ timestampInput + ",projSn=" + projSn + ",projundwrtSn="
				+ projundwrtSn + ",loc=" + loc + ",del=" + del + ",by1=" + by1
				+ ",by2=" + by2 + ",by3=" + by3 + ",tbsProj=" + tbsProj
				+ ",tbsProjundwrt=" + tbsProjundwrt + ",tbsBasBizvar="
				+ tbsBasBizvar + ",tbsBasBiztype=" + tbsBasBiztype + "]";
	}

}
