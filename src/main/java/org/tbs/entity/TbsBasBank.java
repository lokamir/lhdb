package org.tbs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_bas_bank:银行
 */
@Entity
@Table(name = "tbs_bas_bank")
public class TbsBasBank implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 银行名称:银行名称
	 */
	private String name;

	/**
	 * 默认为1，生效:默认为1，生效
	 */
	private boolean valid;

	/**
	 * 备注:备注
	 */
	private String memo;

	/**
	 * 地址:地址
	 */
	private String addr;

	/**
	 * 联系信息:联系信息
	 */
	private String cont;

	/**
	 * tbs_bas_bank:银行
	 */
	private TbsBasBank tbsBasBank;

	/**
	 * tbs_adminsign:综合管理部用印
	 */
	private Set<TbsAdminsign> tbsAdminsignSet = new HashSet<TbsAdminsign>(0);

	/**
	 * tbs_bas_bank:银行
	 */
	private Set<TbsBasBank> tbsBasBankSet = new HashSet<TbsBasBank>(0);

	/**
	 * tbs_proj_bank:项目基本信息子表-银行
	 */
	private Set<TbsProjBank> tbsProjBankSet_M = new HashSet<TbsProjBank>(0);

	/**
	 * tbs_proj_bank:项目基本信息子表-银行
	 */
	private Set<TbsProjBank> tbsProjBankSet_S = new HashSet<TbsProjBank>(0);

	/**
	 * tbs_projundwrt_bank:承保录入单子表-银行信息
	 */
	private Set<TbsProjundwrtBank> tbsProjundwrtBankSet_M = new HashSet<TbsProjundwrtBank>(
			0);

	/**
	 * tbs_projundwrt_bank:承保录入单子表-银行信息
	 */
	private Set<TbsProjundwrtBank> tbsProjundwrtBankSet_S = new HashSet<TbsProjundwrtBank>(
			0);

	// 2016-04-16 银行信息基础资料改动
	private BigDecimal sxed;
	private BigDecimal zbje;
	private BigDecimal sxye;
	private	Date sxqsr;
	private Date sxdqr;
	private BigDecimal edsx;
	private BigDecimal jtsx;
	private BigDecimal fdbs;
	private int gtzcq;
	private int dckxq;
	private String fdbl;
	
	
	
	
	
	
	

	public TbsBasBank() {
		super();
	}

	public TbsBasBank(int id, String name, boolean valid, String memo,
			String addr, String cont, TbsBasBank tbsBasBank,
			Set<TbsAdminsign> tbsAdminsignSet, Set<TbsBasBank> tbsBasBankSet,
			Set<TbsProjBank> tbsProjBankSet_M, Set<TbsProjBank> tbsProjBankSet_S,
			Set<TbsProjundwrtBank> tbsProjundwrtBankSet_M,
			Set<TbsProjundwrtBank> tbsProjundwrtBankSet_S,
			BigDecimal sxed, BigDecimal zbje, BigDecimal sxye, Date sxqsr, Date sxdqr, BigDecimal edsx, BigDecimal jtsx,
			BigDecimal fdbs, int gtzcq, int dckxq, String fdbl) {
		super();
		this.id = id;
		this.name = name;
		this.valid = valid;
		this.memo = memo;
		this.addr = addr;
		this.cont = cont;
		this.tbsBasBank = tbsBasBank;
		this.tbsAdminsignSet = tbsAdminsignSet;
		this.tbsBasBankSet = tbsBasBankSet;
		this.tbsProjBankSet_M = tbsProjBankSet_M;
		this.tbsProjBankSet_S = tbsProjBankSet_S;
		this.tbsProjundwrtBankSet_M = tbsProjundwrtBankSet_M;
		this.tbsProjundwrtBankSet_S = tbsProjundwrtBankSet_S;
		this.sxed = sxed;
		this.zbje = zbje;
		this.sxye = sxye;
		this.sxqsr = sxqsr;
		this.sxdqr = sxdqr;
		this.edsx = edsx;
		this.jtsx = jtsx;
		this.fdbs = fdbs;
		this.gtzcq = gtzcq;
		this.dckxq = dckxq;
		this.fdbl = fdbl;
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

	@Column(name = "NAME_", length = 15)
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

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "MEMO", length = 500)
	public String getMemo() {
		return memo;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "ADDR", length = 100)
	public String getAddr() {
		return addr;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	@Column(name = "CONT", length = 100)
	public String getCont() {
		return cont;
	}

	public void setTbsBasBank(TbsBasBank tbsBasBank) {
		this.tbsBasBank = tbsBasBank;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PID")
	public TbsBasBank getTbsBasBank() {
		return tbsBasBank;
	}

	public void setTbsAdminsignSet(Set<TbsAdminsign> tbsAdminsignSet) {
		this.tbsAdminsignSet = tbsAdminsignSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasBank")
	public Set<TbsAdminsign> getTbsAdminsignSet() {
		return tbsAdminsignSet;
	}

	public void setTbsBasBankSet(Set<TbsBasBank> tbsBasBankSet) {
		this.tbsBasBankSet = tbsBasBankSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasBank")
	public Set<TbsBasBank> getTbsBasBankSet() {
		return tbsBasBankSet;
	}

	public void setTbsProjBankSet_M(Set<TbsProjBank> tbsProjBankSet_M) {
		this.tbsProjBankSet_M = tbsProjBankSet_M;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasBank_M")
	public Set<TbsProjBank> getTbsProjBankSet_M() {
		return tbsProjBankSet_M;
	}

	public void setTbsProjBankSet_S(Set<TbsProjBank> tbsProjBankSet_S) {
		this.tbsProjBankSet_S = tbsProjBankSet_S;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasBank_S")
	public Set<TbsProjBank> getTbsProjBankSet_S() {
		return tbsProjBankSet_S;
	}

	public void setTbsProjundwrtBankSet_M(
			Set<TbsProjundwrtBank> tbsProjundwrtBankSet_M) {
		this.tbsProjundwrtBankSet_M= tbsProjundwrtBankSet_M;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasBank_M")
	public Set<TbsProjundwrtBank> getTbsProjundwrtBankSet_M() {
		return tbsProjundwrtBankSet_M;
	}

	public void setTbsProjundwrtBankSet_S(
			Set<TbsProjundwrtBank> tbsProjundwrtBankSet_S) {
		this.tbsProjundwrtBankSet_S = tbsProjundwrtBankSet_S;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasBank_S")
	public Set<TbsProjundwrtBank> getTbsProjundwrtBankSet_S() {
		return tbsProjundwrtBankSet_S;
	}

	public void setSxed(BigDecimal sxed) {
		this.sxed = sxed;
	}

	@Column(name = "SXED")
	public BigDecimal getSxed() {
		return sxed;
	}
	
	public void setZbje(BigDecimal zbje) {
		this.zbje = zbje;
	}

	@Column(name = "ZBJE")
	public BigDecimal getZbje() {
		return zbje;
	}
	
	public void setSxye(BigDecimal sxye) {
		this.sxye = sxye;
	}

	@Column(name = "SXYE")
	public BigDecimal getSxye() {
		return sxye;
	}
	
	public void setEdsx(BigDecimal edsx) {
		this.edsx = edsx;
	}

	@Column(name = "EDSX")
	public BigDecimal getEdsx() {
		return edsx;
	}
	
	public void setJtsx(BigDecimal jtsx) {
		this.jtsx = jtsx;
	}

	@Column(name = "JTSX")
	public BigDecimal getJtsx() {
		return jtsx;
	}
	
	public void setFdbs(BigDecimal fdbs) {
		this.fdbs = fdbs;
	}

	@Column(name = "FDBS")
	public BigDecimal getFdbs() {
		return fdbs;
	}
	
	public void setFdbl(String fdbl) {
		this.fdbl = fdbl;
	}

	@Column(name = "FDBL", length = 45)
	public String getFdbl() {
		return fdbl;
	}
	
	public void setGtzcq(int gtzcq) {
		this.gtzcq = gtzcq;
	}

	@Column(name = "GTZCQ")
	public int getGtzcq() {
		return gtzcq;
	}
	
	public void setDckxq(int dckxq) {
		this.dckxq = dckxq;
	}

	@Column(name = "DCKXQ")
	public int getDckxq() {
		return dckxq;
	}

	public void setSxqsr(Date sxqsr) {
		this.sxqsr = sxqsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SXQSR")
	public Date getSxqsr() {
		return sxqsr;
	}
	
	public void setSxdqr(Date sxdqr) {
		this.sxdqr = sxdqr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SXDQR")
	public Date getSxdqr() {
		return sxdqr;
	}
	
	
	public String toString() {
		return "TbsBasBank [id=" + id + ",name=" + name + ",valid=" + valid
				+ ",memo=" + memo + ",addr=" + addr + ",cont=" + cont
				+ ",tbsBasBank=" + tbsBasBank + ",tbsAdminsignSet="
				+ tbsAdminsignSet + ",tbsBasBankSet=" + tbsBasBankSet
				+ ",tbsProjBankSet_M=" + tbsProjBankSet_M + ",tbsProjBankSet_S="
				+ tbsProjBankSet_S + ",tbsProjundwrtBankSet_M="
				+ tbsProjundwrtBankSet_M + ",tbsProjundwrtBankSet_S="
				+ tbsProjundwrtBankSet_S 
				+ ",sxed=" + sxed + ",zbje=" + zbje + ",sxye=" + sxye + ",sxqsr=" + sxqsr + ",sxdqr=" + sxdqr 
				+ ",edsx=" + edsx + ",jtsx=" + jtsx + ",fdbs=" + fdbs + ",gtzcq=" + gtzcq + ",dckxq=" + dckxq + ",fdbl=" + fdbl + 
				"]";
	}

}
