package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_projundwrt:承保审批单
 */
@Entity
@Table(name = "tbs_projundwrt")
public class TbsProjundwrt implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 承保单编号=‘CBSP’+当前日期+序号:承保单编号=‘CBSP’+当前日期+序号
	 */
	private String sn;

	/**
	 * 是否有效，默认为0无效，审批过后为1有效。:是否有效，默认为0无效，审批过后为1有效。
	 */
	private boolean valid;

	/**
	 * 输入时间戳:输入时间戳
	 */
	private Date timestampInput;

	/**
	 * 最近一次更新的时间戳:最近一次更新的时间戳
	 */
	private Date timestampUpdate;

	/**
	 * 录入人:录入人
	 */
	private int keyinId;

	/**
	 * PROJ_SN:
	 */
	private String projSn;

	/**
	 * 可用融资担保授信金额（元）tbs_proj.vfaloc:可用融资担保授信金额（元）tbs_proj.vfaloc
	 */
	
	private BigDecimal appfaloc;

	/**
	 * 可用非融资担保授信金额（元）tbs_proj.vnfaloc:可用非融资担保授信金额（元）tbs_proj.vnfaloc
	 */
	
	private BigDecimal appnfaloc;

	/**
	 * 可用其他类担保授信金额（元）tbs_proj.votloc:可用其他类担保授信金额（元）tbs_proj.votloc
	 */
	
	private BigDecimal appotloc;

	/**
	 * 放款方式。【一次性放款，多次放款】.来自tbs_proj:放款方式。【一次性放款，多次放款】.来自tbs_proj
	 */
	private String loantype;

	/**
	 * 放款说明。.来自tbs_proj:放款说明。.来自tbs_proj
	 */
	private String loanmem;

	/**
	 * 还款方式。【一次性还款，按月还部分，到期一次性结清，按季度还部分,到期一次性结算，其他】.来自tbs_proj:还款方式。【一次性还款，按月还部分，到期一次性结清，按季度还部分,到期一次性结算，其他】.来自tbs_proj
	 */
	private String repay;

	/**
	 * 每次还款额（元）.来自tbs_proj:每次还款额（元）.来自tbs_proj
	 */
	private BigDecimal repayinper;

	/**
	 * 还款说明。.来自tbs_proj:还款说明。.来自tbs_proj
	 */
	private String repaymem;

	/**
	 * 实际担保授信期限（月）.来自tbs_proj:实际担保授信期限（月）.来自tbs_proj
	 */
	private int periodCfm;

	/**
	 * 担保费率
	 */
	private BigDecimal gatrate;

	/**
	 * 评审费用（元）:评审费用（元）
	 */
	private BigDecimal psfy;

	/**
	 * 咨询费用（元）:其他费用（元）
	 */
	private BigDecimal zxfy;
	
	/**
	 * 其他费用（元）:财务费用（元）
	 */
	private BigDecimal financial;
	
	/**
	 * 其他费用（元）:其他费用（元）
	 */
	private BigDecimal qtfy;

	/**
	 * 担保费用计算后金额
	 */
	private BigDecimal gatreckon;
	
	/**
	 * 担保费率说明:担保费率说明
	 */
	private String gatmem;

	/**
	 * 默认为0不删除:默认为0不删除
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
	 * tbs_customer:客户基本信息
	 */
	private TbsCustomer tbsCustomer;

	/**
	 * tbs_proj:项目基本信息主表
	 */
	private TbsProj tbsProj;

	/**
	 * tbs_projundwrt_bank:承保录入单子表-银行信息
	 */
	private Set<TbsProjundwrtBank> tbsProjundwrtBankSet = new HashSet<TbsProjundwrtBank>(
			0);

	/**
	 * tbs_projundwrt_bizvt:承保审批单-业务类型品种和金额
	 */
	private Set<TbsProjundwrtBizvt> tbsProjundwrtBizvtSet = new HashSet<TbsProjundwrtBizvt>(
			0);

	/**
	 * tbs_projundwrt_cfmar:承保补录
	 */
	private Set<TbsProjundwrtCfmar> tbsProjundwrtCfmarSet = new HashSet<TbsProjundwrtCfmar>(
			0);

	
	/**
	 * 2015-12-25 手动加入
	 * */
	private BigDecimal apptotloc;
	private Date bdate;
	private Date edate;
	private Date undbdate;
	private Date undedate;
	private Date validdate;
	private String gatrateu;
	
	
	/**
	 * 二期改库 2016-06-01
	 * */
	private String pid;
	private Set<TbsPtyexp> tbsPtyexpSet = new HashSet<TbsPtyexp>(0);
	private Set<TbsProjcompsry> tbsProjcompsrySet = new HashSet<TbsProjcompsry>(0);
	private Set<TbsProjcompsryPay> tbsProjcompsryPaySet = new HashSet<TbsProjcompsryPay>(0);
	private Set<TbsProjrol> tbsProjrolSet = new HashSet<TbsProjrol>(0);
	private Set<TbsProjRelease> tbsProjReleaseSet = new HashSet<TbsProjRelease>(0);
	private BigDecimal rlsfaloc;
	private BigDecimal rlsnfaloc;
	private BigDecimal rlsotloc;
	private BigDecimal rlstotloc;
	private BigDecimal cfmfaloc;
	private BigDecimal cfmnfaloc;
	private BigDecimal cfmotloc;
	private BigDecimal cfmtotloc;         
	
	
	
	public TbsProjundwrt() {
		super();
	}

	public TbsProjundwrt(int id, String sn, boolean valid, Date timestampInput,
			Date timestampUpdate, int keyinId, String projSn,
			BigDecimal appfaloc,
			BigDecimal appnfaloc,
			BigDecimal appotloc,
			BigDecimal apptotloc,
			Date bdate,Date edate,Date undbdate,Date undedate,
			String loantype, String loanmem, String repay,
			BigDecimal repayinper, String repaymem, int periodCfm,
			BigDecimal gatrate, BigDecimal psfy, BigDecimal qtfy,BigDecimal zxfy,BigDecimal financial,BigDecimal gatreckon,
			String gatmem, boolean del, String by1, String by2, String by3, String pid,
			String gatrateu,
			TbsCustomer tbsCustomer, TbsProj tbsProj,
			Set<TbsProjundwrtBank> tbsProjundwrtBankSet,
			Set<TbsProjundwrtBizvt> tbsProjundwrtBizvtSet,
			Set<TbsProjundwrtCfmar> tbsProjundwrtCfmarSet, Date validdate,
			Set<TbsPtyexp> tbsPtyexpSet,
			Set<TbsProjcompsry> tbsProjcompsrySet,
			Set<TbsProjcompsryPay> tbsProjcompsryPaySet,
			Set<TbsProjrol> tbsProjrolSet,
			Set<TbsProjRelease> tbsProjReleaseSet,
			BigDecimal rlsfaloc,
			BigDecimal rlsnfaloc,
			BigDecimal rlsotloc,
			BigDecimal rlstotloc,
			BigDecimal cfmfaloc,
			BigDecimal cfmnfaloc,
			BigDecimal cfmotloc,
			BigDecimal cfmtotloc     
			) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.keyinId = keyinId;
		this.projSn = projSn;
		this.appfaloc = appfaloc;
		this.appnfaloc = appnfaloc;
		this.appotloc = appotloc;
		this.apptotloc = apptotloc;
		this.loantype = loantype;
		this.loanmem = loanmem;
		this.repay = repay;
		this.repayinper = repayinper;
		this.repaymem = repaymem;
		this.periodCfm = periodCfm;
		this.gatrate = gatrate;
		this.gatreckon = gatreckon;
		this.psfy = psfy;
		this.qtfy = qtfy;
		this.zxfy = zxfy;
		this.financial = financial;
		this.gatmem = gatmem;
		this.del = del;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsCustomer = tbsCustomer;
		this.tbsProj = tbsProj;
		this.tbsProjundwrtBankSet = tbsProjundwrtBankSet;
		this.tbsProjundwrtBizvtSet = tbsProjundwrtBizvtSet;
		this.tbsProjundwrtCfmarSet = tbsProjundwrtCfmarSet;
		this.bdate = bdate;
		this.edate = edate;
		this.undbdate = undbdate;
		this.undedate = undedate;
		this.validdate = validdate;
		this.gatrateu = gatrateu;
		this.pid = pid;
		this.tbsPtyexpSet = tbsPtyexpSet;
		this.tbsProjcompsrySet = tbsProjcompsrySet;
		this.tbsProjcompsryPaySet = tbsProjcompsryPaySet;
		this.tbsProjrolSet = tbsProjrolSet;
		this.tbsProjReleaseSet = tbsProjReleaseSet;
		this.rlsfaloc = rlsfaloc;
		this.rlsnfaloc = rlsnfaloc;
		this.rlsotloc = rlsotloc;
		this.rlstotloc = rlstotloc;
		this.cfmfaloc = cfmfaloc;
		this.cfmnfaloc = cfmnfaloc;
		this.cfmotloc = cfmotloc;
		this.cfmtotloc = cfmtotloc;    
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

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "SN", length = 20)
	public String getSn() {
		return sn;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
	}

	public void setTimestampInput(Date timestampInput) {
		this.timestampInput = timestampInput;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_INPUT", nullable = false)
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setTimestampUpdate(Date timestampUpdate) {
		this.timestampUpdate = timestampUpdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_UPDATE", nullable = false)
	public Date getTimestampUpdate() {
		return timestampUpdate;
	}

	public void setKeyinId(int keyinId) {
		this.keyinId = keyinId;
	}

	@Column(name = "KEYIN_ID")
	public int getKeyinId() {
		return keyinId;
	}

	public void setProjSn(String projSn) {
		this.projSn = projSn;
	}

	@Column(name = "PROJ_SN", length = 20)
	public String getProjSn() {
		return projSn;
	}

	public void setAppfaloc(BigDecimal appfaloc) {
		this.appfaloc = appfaloc;
	}

	@Column(name = "APPFALOC")
	public BigDecimal getAppfaloc() {
		return appfaloc;
	}

	public void setAppnfaloc(BigDecimal appnfaloc) {
		this.appnfaloc = appnfaloc;
	}

	@Column(name = "APPNFALOC")
	public BigDecimal getAppnfaloc() {
		return appnfaloc;
	}

	public void setAppotloc(BigDecimal appotloc) {
		this.appotloc = appotloc;
	}

	@Column(name = "APPOTLOC")
	public BigDecimal getAppotloc() {
		return appotloc;
	}

	public void setLoantype(String loantype) {
		this.loantype = loantype;
	}

	@Column(name = "LOANTYPE", length = 20)
	public String getLoantype() {
		return loantype;
	}

	public void setLoanmem(String loanmem) {
		this.loanmem = loanmem;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "LOANMEM", columnDefinition = "CLOB")
	public String getLoanmem() {
		return loanmem;
	}

	public void setRepay(String repay) {
		this.repay = repay;
	}

	@Column(name = "REPAY", length = 20)
	public String getRepay() {
		return repay;
	}

	public void setRepayinper(BigDecimal repayinper) {
		this.repayinper = repayinper;
	}

	@Column(name = "REPAYINPER")
	public BigDecimal getRepayinper() {
		return repayinper;
	}

	public void setRepaymem(String repaymem) {
		this.repaymem = repaymem;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "REPAYMEM", columnDefinition = "CLOB")
	public String getRepaymem() {
		return repaymem;
	}

	public void setPeriodCfm(int periodCfm) {
		this.periodCfm = periodCfm;
	}

	@Column(name = "PERIOD_CFM")
	public int getPeriodCfm() {
		return periodCfm;
	}

	public void setGatrate(BigDecimal gatrate) {
		this.gatrate = gatrate;
	}

	@Column(name = "GATRATE")
	public BigDecimal getGatrate() {
		return gatrate;
	}
	
	public void setGatreckon(BigDecimal gatreckon) {
		this.gatreckon = gatreckon;
	}
	
	@Column(name = "GATRECKON")
	public BigDecimal getGatreckon() {
		return gatreckon;
	}

	public void setPsfy(BigDecimal psfy) {
		this.psfy = psfy;
	}

	@Column(name = "PSFY")
	public BigDecimal getPsfy() {
		return psfy;
	}

	public void setQtfy(BigDecimal qtfy) {
		this.qtfy = qtfy;
	}

	@Column(name = "QTFY")
	public BigDecimal getQtfy() {
		return qtfy;
	}
	
	public void setZxfy(BigDecimal zxfy) {
		this.zxfy = zxfy;
	}
	
	@Column(name = "ZXFY")
	public BigDecimal getZxfy() {
		return zxfy;
	}
	
	public void setFinancial(BigDecimal financial) {
		this.financial = financial;
	}
	
	@Column(name = "FINANCIAL")
	public BigDecimal getFinancial() {
		return financial;
	}

	public void setGatmem(String gatmem) {
		this.gatmem = gatmem;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "GATMEM", columnDefinition = "CLOB")
	public String getGatmem() {
		return gatmem;
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

	public void setTbsCustomer(TbsCustomer tbsCustomer) {
		this.tbsCustomer = tbsCustomer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUS_ID")
	public TbsCustomer getTbsCustomer() {
		return tbsCustomer;
	}

	public void setTbsProj(TbsProj tbsProj) {
		this.tbsProj = tbsProj;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJ_ID")
	public TbsProj getTbsProj() {
		return tbsProj;
	}

	public void setTbsProjundwrtBankSet(
			Set<TbsProjundwrtBank> tbsProjundwrtBankSet) {
		this.tbsProjundwrtBankSet = tbsProjundwrtBankSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjundwrt")
	public Set<TbsProjundwrtBank> getTbsProjundwrtBankSet() {
		return tbsProjundwrtBankSet;
	}

	public void setTbsProjundwrtBizvtSet(
			Set<TbsProjundwrtBizvt> tbsProjundwrtBizvtSet) {
		this.tbsProjundwrtBizvtSet = tbsProjundwrtBizvtSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjundwrt")
	public Set<TbsProjundwrtBizvt> getTbsProjundwrtBizvtSet() {
		return tbsProjundwrtBizvtSet;
	}

	public void setTbsProjundwrtCfmarSet(
			Set<TbsProjundwrtCfmar> tbsProjundwrtCfmarSet) {
		this.tbsProjundwrtCfmarSet = tbsProjundwrtCfmarSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjundwrt")
	public Set<TbsProjundwrtCfmar> getTbsProjundwrtCfmarSet() {
		return tbsProjundwrtCfmarSet;
	}

	public void setApptotloc(BigDecimal apptotloc) {
		this.apptotloc = apptotloc;
	}

	@Column(name = "APPTOTLOC")
	public BigDecimal getApptotloc() {
		return apptotloc;
	}
	
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	@Column(name = "BDATE")
	public Date getBdate() {
		return bdate;
	}
	
	public void setEdate(Date edate) {
		this.edate = edate;
	}

	@Column(name = "EDATE")
	public Date getEdate() {
		return edate;
	}
	
	public void setUndbdate(Date undbdate) {
		this.undbdate = undbdate;
	}

	@Column(name = "UNDBDATE")
	public Date getUndbdate() {
		return undbdate;
	}
	
	public void setUndedate(Date undedate) {
		this.undedate = undedate;
	}

	@Column(name = "UNDEDATE")
	public Date getUndedate() {
		return undedate;
	}

	public void setValiddate(Date validdate) {
		this.validdate = validdate;
	}

	@Column(name = "VALIDDATE")
	public Date getValiddate() {
		return validdate;
	}

	public void setGatrateu(String gatrateu) {
		this.gatrateu = gatrateu;
	}

	@Column(name = "GATRATEU", length =5 )
	public String getGatrateu() {
		return gatrateu;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "PID", length = 300)
	public String getPid() {
		return pid;
	}
	
	public void setTbsPtyexpSet(Set<TbsPtyexp> tbsPtyexpSet) {
		this.tbsPtyexpSet = tbsPtyexpSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjundwrt")
	public Set<TbsPtyexp> getTbsPtyexpSet() {
		return tbsPtyexpSet;
	}
	
	public void setTbsProjcompsrySet(Set<TbsProjcompsry> tbsProjcompsrySet) {
		this.tbsProjcompsrySet = tbsProjcompsrySet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjundwrt")
	public Set<TbsProjcompsry> getTbsProjcompsrySet() {
		return tbsProjcompsrySet;
	}
	
	public void setTbsProjcompsryPaySet(Set<TbsProjcompsryPay> tbsProjcompsryPaySet) {
		this.tbsProjcompsryPaySet = tbsProjcompsryPaySet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjundwrt")
	public Set<TbsProjcompsryPay> getTbsProjcompsryPaySet() {
		return tbsProjcompsryPaySet;
	}
	
	public void setTbsProjrolSet(Set<TbsProjrol> tbsProjrolSet) {
		this.tbsProjrolSet = tbsProjrolSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjundwrt")
	public Set<TbsProjrol> getTbsProjrolSet() {
		return tbsProjrolSet;
	}
	
	public void setTbsProjReleaseSet(Set<TbsProjRelease> tbsProjReleaseSet) {
		this.tbsProjReleaseSet = tbsProjReleaseSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjundwrt")
	public Set<TbsProjRelease> getTbsProjReleaseSet() {
		return tbsProjReleaseSet;
	}
	
	public void setRlsfaloc(BigDecimal rlsfaloc) {
		this.rlsfaloc = rlsfaloc;
	}

	@Column(name = "RLSFALOC")
	public BigDecimal getRlsfaloc() {
		return rlsfaloc;
	}
	
	public void setRlsnfaloc(BigDecimal rlsnfaloc) {
		this.rlsnfaloc = rlsnfaloc;
	}

	@Column(name = "RLSNFALOC")
	public BigDecimal getRlsnfaloc() {
		return rlsnfaloc;
	}
	
	public void setRlsotloc(BigDecimal rlsotloc) {
		this.rlsotloc = rlsotloc;
	}

	@Column(name = "RLSOTLOC")
	public BigDecimal getRlsotloc() {
		return rlsotloc;
	}
	
	public void setRlstotloc(BigDecimal rlstotloc) {
		this.rlstotloc = rlstotloc;
	}

	@Column(name = "RLSTOTLOC")
	public BigDecimal getRlstotloc() {
		return rlstotloc;
	}
	
	public void setCfmfaloc(BigDecimal cfmfaloc) {
		this.cfmfaloc = cfmfaloc;
	}

	@Column(name = "CFMFALOC")
	public BigDecimal getCfmfaloc() {
		return cfmfaloc;
	}
	
	public void setCfmtotloc(BigDecimal cfmtotloc) {
		this.cfmtotloc = cfmtotloc;
	}

	@Column(name = "CFMTOTLOC")
	public BigDecimal getCfmtotloc() {
		return cfmtotloc;
	}
	
	public void setCfmotloc(BigDecimal cfmotloc) {
		this.cfmotloc = cfmotloc;
	}

	@Column(name = "CFMOTLOC")
	public BigDecimal getCfmotloc() {
		return cfmotloc;
	}
	
	public void setCfmnfaloc(BigDecimal cfmnfaloc) {
		this.cfmnfaloc = cfmnfaloc;
	}

	@Column(name = "CFMNFALOC")
	public BigDecimal getCfmnfaloc() {
		return cfmnfaloc;
	}	
	
	public String toString() {
		return "TbsProjundwrt [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",projSn=" + projSn
				+ ",appfaloc=" + appfaloc
				+ ",appnfaloc=" + appnfaloc + ",appotloc=" + appotloc + ",loantype=" + loantype
				+ ",loanmem=" + loanmem + ",repay=" + repay + ",repayinper="
				+ repayinper + ",repaymem=" + repaymem + ",periodCfm="
				+ periodCfm + ",gatrate=" + gatrate + ",psfy=" + psfy + ",zxfy=" + zxfy + ",financial=" + financial + ",gatreckon=" + gatreckon
				+ ",qtfy=" + qtfy + ",gatmem=" + gatmem + ",del=" + del
				+ ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3
				+ ",tbsCustomer=" + tbsCustomer + ",tbsProj=" + tbsProj
				+ ",tbsProjundwrtBankSet=" + tbsProjundwrtBankSet
				+ ",tbsProjundwrtBizvtSet=" + tbsProjundwrtBizvtSet
				+ ",tbsProjundwrtCfmarSet=" + tbsProjundwrtCfmarSet 
				+ ",apptotloc=" + apptotloc + ",bdate=" + bdate + ",edate=" + edate + ",undbdate=" 
				+ undbdate + ",undedate=" + undedate 
				+ ",validdate=" + validdate
				+ ",gatrateu=" + gatrateu 
				+ ",pid=" + pid + ",tbsPtyexpSet=" + tbsPtyexpSet + ",tbsProjcompsrySet=" + tbsProjcompsrySet 
				+ ",tbsProjcompsryPaySet=" + tbsProjcompsryPaySet 
				+ ",tbsProjrolSet=" + tbsProjrolSet
				+ ",tbsProjReleaseSet=" + tbsProjReleaseSet
				+ ",rlsfaloc=" + rlsfaloc + ",rlsnfaloc=" + rlsnfaloc + ",rlsotloc=" + rlsotloc + ",rlstotloc=" + rlstotloc 
				+ ",cfmtotloc=" + cfmtotloc + ",cfmotloc=" + cfmotloc + ",cfmnfaloc=" + cfmnfaloc + ",cfmfaloc=" + cfmfaloc
				+ "]";
	}

}
