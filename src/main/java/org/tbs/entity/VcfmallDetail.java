package org.tbs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Embeddable
public class VcfmallDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	// Fields view里面的字段
	private int cfmid;
	private int projid;
	private int cusid;
	private int periodcfm;
	private String projsn;
	private String cusname;
	private String docsn;
	private String doccat;
	private String loantype;
	private String loanmem;
	private String repay;
	private String gatmem;
	private String repaymem;
	private BigDecimal faloc;
	private BigDecimal nfaloc;
	private BigDecimal otloc;
	private BigDecimal totloc;
	private BigDecimal repayinper;
	private BigDecimal gatrate;
	private BigDecimal psfy;
	private BigDecimal qtfy;
	private Date bdate;
	private Date edate;
	private int bizvarid;
	private int biztypeid;
	private BigDecimal loc;
	private int by3;

	// Constructors view里面的构造对应,具体的项目
	// default
	public VcfmallDetail() {

	}

	// full
	public VcfmallDetail(int cfmid, int projid, String projsn,
			String cusname, int cusid, String docsn, String doccat,
			BigDecimal faloc, BigDecimal nfaloc, BigDecimal otloc,
			BigDecimal totloc, int periodcfm, Date bdate, Date edate,
			String loantype, String loanmem, String repay,
			BigDecimal repayinper, String repaymem, BigDecimal gatrate,
			BigDecimal psfy, BigDecimal qtfy, String gatmem, int bizvarid,
			int biztypeid, BigDecimal loc,int by3) {
		this.cfmid = cfmid;
		this.projid = projid;
		this.cusid = cusid;
		this.projsn = projsn;
		this.cusname = cusname;
		this.docsn = docsn;
		this.doccat = doccat;
		this.loantype = loantype;
		this.periodcfm = periodcfm;
		this.loanmem = loanmem;
		this.repay = repay;
		this.repaymem = repaymem;
		this.gatmem = gatmem;
		this.repayinper = repayinper;
		this.faloc = faloc;
		this.nfaloc = nfaloc;
		this.otloc = otloc;
		this.totloc = totloc;
		this.bdate = bdate;
		this.edate = edate;
		this.gatrate = gatrate;
		this.psfy = psfy;
		this.qtfy = qtfy;
		this.bizvarid = bizvarid;
		this.biztypeid = biztypeid;
		this.loc = loc;
		this.by3 = by3;

	}

	// Property accessors
	@Column(name = "CFM_ID" )
	public int getCfmid() {
		return cfmid;
	}

	public void setCfmid(int cfmid) {
		this.cfmid = cfmid;
	}
	@Column(name = "PROJ_ID" )
	public int getProjid() {
		return projid;
	}

	public void setProjid(int projid) {
		this.projid = projid;
	}
	@Column(name = "CUS_ID" )
	public int getCusid() {
		return cusid;
	}

	public void setCusid(int cusid) {
		this.cusid = cusid;
	}
	
	@Column(name = "PERIOD_CFM" )
	public int getPeriodcfm() {
		return periodcfm;
	}

	public void setPeriodcfm(int periodcfm) {
		this.periodcfm = periodcfm;
	}
	@Column(name = "PROJ_SN" )
	public String getProjsn() {
		return projsn;
	}

	public void setProjsn(String projsn) {
		this.projsn = projsn;
	}
	@Column(name = "CUS_NAME" )
	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}
	@Column(name = "DOC_SN" )
	public String getDocsn() {
		return docsn;
	}

	public void setDocsn(String docsn) {
		this.docsn = docsn;
	}
	@Column(name = "DOC_CAT" )
	public String getDoccat() {
		return doccat;
	}

	public void setDoccat(String doccat) {
		this.doccat = doccat;
	}
	@Column(name = "LOANTYPE" )
	public String getLoantype() {
		return loantype;
	}

	public void setLoantype(String loantype) {
		this.loantype = loantype;
	}
	@Column(name = "LOANMEM")
	public String getLoanmem() {
		return loanmem;
	}

	public void setLoanmem(String loanmem) {
		this.loanmem = loanmem;
	}
	@Column(name = "REPAY" )
	public String getRepay() {
		return repay;
	}

	public void setRepay(String repay) {
		this.repay = repay;
	}
	@Column(name = "GATMEM" )
	public String getGatmem() {
		return gatmem;
	}

	public void setGatmem(String gatmem) {
		this.gatmem = gatmem;
	}
	@Column(name = "REPAYMEM" )
	public String getRepaymem() {
		return repaymem;
	}

	public void setRepaymem(String repaymem) {
		this.repaymem = repaymem;
	}
	@Column(name = "FALOC" )
	public BigDecimal getFaloc() {
		return faloc;
	}

	public void setFaloc(BigDecimal faloc) {
		this.faloc = faloc;
	}
	@Column(name = "NFALOC" )
	public BigDecimal getNfaloc() {
		return nfaloc;
	}

	public void setNfaloc(BigDecimal nfaloc) {
		this.nfaloc = nfaloc;
	}
	@Column(name = "OTLOC" )
	public BigDecimal getOtloc() {
		return otloc;
	}

	public void setOtloc(BigDecimal otloc) {
		this.otloc = otloc;
	}
	@Column(name = "TOTLOC" )
	public BigDecimal getTotloc() {
		return totloc;
	}

	public void setTotloc(BigDecimal totloc) {
		this.totloc = totloc;
	}
	@Column(name = "REPAYINPER" )
	public BigDecimal getRepayinper() {
		return repayinper;
	}

	public void setRepayinper(BigDecimal repayinper) {
		this.repayinper = repayinper;
	}
	@Column(name = "GATRATE" )
	public BigDecimal getGatrate() {
		return gatrate;
	}

	public void setGatrate(BigDecimal gatrate) {
		this.gatrate = gatrate;
	}
	@Column(name = "PSFY" )
	public BigDecimal getPsfy() {
		return psfy;
	}

	public void setPsfy(BigDecimal psfy) {
		this.psfy = psfy;
	}
	@Column(name = "QTFY" )
	public BigDecimal getQtfy() {
		return qtfy;
	}

	public void setQtfy(BigDecimal qtfy) {
		this.qtfy = qtfy;
	}
	@Column(name = "BDATE" )
	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	@Column(name = "EDATE" )
	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	};
	
	@Column(name = "BIZVAR_ID" )
	public int getBizvarid() {
		return bizvarid;
	}
	
	public void setBizvarid(int bizvarid) {
		this.bizvarid = bizvarid;
	}
	@Column(name = "BIZTYPE_ID" )
	public int getBiztypeid() {
		return biztypeid;
	}
	
	public void setBiztypeid(int biztypeid) {
		this.biztypeid = biztypeid;
	}
	@Column(name = "LOC" )
	public BigDecimal getLoc() {
		return loc;
	}
	
	public void setLoc(BigDecimal loc) {
		this.loc = loc;
	}
	@Column(name = "BY3" )
	public int getBy3() {
		return by3;
	}
	
	public void setBy3(int by3) {
		this.by3 = by3;
	}
	
}
