package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * tbs_proj_bizvt:项目基本信息子表-业务类型品种和金额
 */
@Entity
@Table(name = "tbs_proj_bizvt")
public class TbsProjBizvt implements Serializable {

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
	 * 本次申请担保授信金额这个金额是三类（融资，非融资，其他）担保授信的明细金额，对应到每一个业务品种上。只有在承保阶段由业务经理才会输入。:本次申请担保授信金额这个金额是三类（融资，非融资，其他）担保授信的明细金额，对应到每一个业务品种上。只有在承保阶段由业务经理才会输入。
	 */
	private BigDecimal loc;

	/**
	 * 实际可用担保授信金额。数据回插自cfm1_bizvt或cfm2_bizvt:实际可用担保授信金额。数据回插自cfm1_bizvt或cfm2_bizvt
	 */
	private BigDecimal vloc;

	/**
	 * 生效标记默认为1，录入即有效。:生效标记默认为1，录入即有效。
	 */
	private boolean valid;

	/**
	 * 删除标记(项目部分解保6.1流程会用到这个。)默认为0如果发生删除，则改为1。项目解保，必须明细到每一个具体的业务品种上。:删除标记(项目部分解保6.1流程会用到这个。)默认为0如果发生删除，则改为1。项目解保，必须明细到每一个具体的业务品种上。
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
	 * tbs_bas_bizvar:业务品种
	 */
	private TbsBasBizvar tbsBasBizvar;
	
	private BigDecimal initloc;
	
	/**
	 * tbs_bas_biztype:业务类型
	 */
	private TbsBasBiztype tbsBasBiztype;
	
	/**
	 * tbs_bas_court:法院基本信息
	 */
	private TbsBasCourt tbsBasCourt;	//2017-06-01

	public TbsProjBizvt() {
		super();
	}

	public TbsProjBizvt(int id, String projSn, BigDecimal loc, BigDecimal vloc,
			boolean valid, boolean del, String by1, String by2, String by3,
			TbsProj tbsProj, TbsBasBizvar tbsBasBizvar,
			TbsBasCourt tbsBasCourt, //2017-06-01
			TbsBasBiztype tbsBasBiztype, BigDecimal initloc) {
		super();
		this.id = id;
		this.projSn = projSn;
		this.loc = loc;
		this.vloc = vloc;
		this.valid = valid;
		this.del = del;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsBasBizvar = tbsBasBizvar;
		this.tbsBasBiztype = tbsBasBiztype;
		this.initloc = initloc;
		this.tbsBasCourt = tbsBasCourt; //2017-06-01
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

	public void setLoc(BigDecimal loc) {
		this.loc = loc;
	}

	@Column(name = "LOC")
	public BigDecimal getLoc() {
		return loc;
	}

	public void setVloc(BigDecimal vloc) {
		this.vloc = vloc;
	}

	@Column(name = "VLOC")
	public BigDecimal getVloc() {
		return vloc;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
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
	
	public void setInitloc(BigDecimal initloc) {
		this.initloc = initloc;
	}

	@Column(name = "INITLOC")
	public BigDecimal getInitloc() {
		return initloc;
	}
	
	public void setTbsBasCourt(TbsBasCourt tbsBasCourt) {
	this.tbsBasCourt = tbsBasCourt;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COURTID")
	public TbsBasCourt getTbsBasCourt() {
		return tbsBasCourt;
	}//2017-06-01

	
	public String toString() {
		return "TbsProjBizvt [id=" + id + ",projSn=" + projSn + ",loc=" + loc
				+ ",vloc=" + vloc + ",valid=" + valid + ",del=" + del + ",by1="
				+ by1 + ",by2=" + by2 + ",by3=" + by3 + ",tbsProj=" + tbsProj
				+ ",tbsBasBizvar=" + tbsBasBizvar + ",tbsBasBiztype="
				+ ",tbsBasCourt="+ tbsBasCourt  //2017-06-01
				+ tbsBasBiztype + ",initloc=" + initloc + "]";
	}

}
