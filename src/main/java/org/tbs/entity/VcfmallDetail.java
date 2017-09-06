package org.tbs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Embeddable
public class VcfmallDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	// Fields view里面的字段
	private int cfm_id;
	private int proj_id;
	private int bizvar_id;
	private int biztype_id;
	private int cus_id;
	private int period_cfm;
	private String proj_sn;
	private String cus_name;
	private String doc_sn;
	private String doc_cat;
	private String loantype;
	private String loanmem;
	private String repay;
	private String gatmem;
	private String repaymem;
	private BigDecimal faloc;
	private BigDecimal nfaloc;
	private BigDecimal otloc;
	private BigDecimal totloc;
	private BigDecimal loc;
	private BigDecimal repayinper;
	private BigDecimal gatrate;
	private BigDecimal psfy;
	private BigDecimal qtfy;
	private Date bdate;
	private Date edate;

	// Constructors view里面的构造对应,具体的项目
	// default
	public VcfmallDetail() {

	}

	// full
	public VcfmallDetail(int cfm_id, int proj_id, String proj_sn,
			String cus_name, int cus_id, String doc_sn, String doc_cat,
			BigDecimal faloc, BigDecimal nfaloc, BigDecimal otloc,
			BigDecimal totloc, int period_cfm, Date bdate, Date edate,
			String loantype, String loanmem, String repay,
			BigDecimal repayinper, String repaymem, BigDecimal gatrate,
			BigDecimal psfy, BigDecimal qtfy, String gatmem, int bizvar_id,
			int biztype_id, BigDecimal loc) {
		this.cfm_id = cfm_id;
		this.proj_id = proj_id;
		this.proj_sn = proj_sn;
		this.cus_name = cus_name;
		this.cus_id = cus_id;
		this.doc_sn = doc_sn;
		this.doc_cat = doc_cat;
		this.faloc = faloc;
		this.nfaloc = nfaloc;
		this.otloc = otloc;
		this.totloc = totloc;
		this.period_cfm = period_cfm;
		this.bdate = bdate;
		this.edate = edate;
		this.loantype = loantype;
		this.loanmem = loanmem;
		this.repay = repay;
		this.repayinper = repayinper;
		this.repaymem = repaymem;
		this.gatrate = gatrate;
		this.psfy = psfy;
		this.qtfy = qtfy;
		this.gatmem = gatmem;
		this.bizvar_id = bizvar_id;
		this.biztype_id = biztype_id;
		this.loc = loc;

	}

	// Property accessors
	
	public int getCfm_id() {
		return cfm_id;
	}

	public void setCfm_id(int cfm_id) {
		this.cfm_id = cfm_id;
	}

	public int getProj_id() {
		return proj_id;
	}

	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}

	public int getBizvar_id() {
		return bizvar_id;
	}

	public void setBizvar_id(int bizvar_id) {
		this.bizvar_id = bizvar_id;
	}

	public int getBiztype_id() {
		return biztype_id;
	}

	public void setBiztype_id(int biztype_id) {
		this.biztype_id = biztype_id;
	}

	public int getCus_id() {
		return cus_id;
	}

	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}

	public int getPeriod_cfm() {
		return period_cfm;
	}

	public void setPeriod_cfm(int period_cfm) {
		this.period_cfm = period_cfm;
	}

	public String getProj_sn() {
		return proj_sn;
	}

	public void setProj_sn(String proj_sn) {
		this.proj_sn = proj_sn;
	}

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}

	public String getDoc_sn() {
		return doc_sn;
	}

	public void setDoc_sn(String doc_sn) {
		this.doc_sn = doc_sn;
	}

	public String getDoc_cat() {
		return doc_cat;
	}

	public void setDoc_cat(String doc_cat) {
		this.doc_cat = doc_cat;
	}

	public String getLoantype() {
		return loantype;
	}

	public void setLoantype(String loantype) {
		this.loantype = loantype;
	}

	public String getLoanmem() {
		return loanmem;
	}

	public void setLoanmem(String loanmem) {
		this.loanmem = loanmem;
	}

	public String getRepay() {
		return repay;
	}

	public void setRepay(String repay) {
		this.repay = repay;
	}

	public String getGatmem() {
		return gatmem;
	}

	public void setGatmem(String gatmem) {
		this.gatmem = gatmem;
	}

	public String getRepaymem() {
		return repaymem;
	}

	public void setRepaymem(String repaymem) {
		this.repaymem = repaymem;
	}

	public BigDecimal getFaloc() {
		return faloc;
	}

	public void setFaloc(BigDecimal faloc) {
		this.faloc = faloc;
	}

	public BigDecimal getNfaloc() {
		return nfaloc;
	}

	public void setNfaloc(BigDecimal nfaloc) {
		this.nfaloc = nfaloc;
	}

	public BigDecimal getOtloc() {
		return otloc;
	}

	public void setOtloc(BigDecimal otloc) {
		this.otloc = otloc;
	}

	public BigDecimal getTotloc() {
		return totloc;
	}

	public void setTotloc(BigDecimal totloc) {
		this.totloc = totloc;
	}

	public BigDecimal getLoc() {
		return loc;
	}

	public void setLoc(BigDecimal loc) {
		this.loc = loc;
	}

	public BigDecimal getRepayinper() {
		return repayinper;
	}

	public void setRepayinper(BigDecimal repayinper) {
		this.repayinper = repayinper;
	}

	public BigDecimal getGatrate() {
		return gatrate;
	}

	public void setGatrate(BigDecimal gatrate) {
		this.gatrate = gatrate;
	}

	public BigDecimal getPsfy() {
		return psfy;
	}

	public void setPsfy(BigDecimal psfy) {
		this.psfy = psfy;
	}

	public BigDecimal getQtfy() {
		return qtfy;
	}

	public void setQtfy(BigDecimal qtfy) {
		this.qtfy = qtfy;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	};
	
	
	
}
