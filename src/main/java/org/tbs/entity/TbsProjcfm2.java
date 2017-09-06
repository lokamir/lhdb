package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_projcfm2:签批决议录入表。表单数据回插入tbs_proj和tbs_proj_hisloc
 */
@Entity
@Table(name = "tbs_projcfm2")
public class TbsProjcfm2 implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 表单编号=‘锡联担保审字’+会议日期+序号(seq.nextvalue)初始值国联提供序号是与cfm1表公用的，所以只能用函数生成:表单编号=‘锡联担保审字’+会议日期+序号(seq.nextvalue)初始值国联提供序号是与cfm1表公用的，所以只能用函数生成
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
	 * 备注:备注
	 */
	private String memo;

	/**
	 * 删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据:删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据
	 */
	private boolean del;

	/**
	 * 通过项目编号和ID找到项目名称。由于项目名称会在审批过程中发生变化，所以名称不用存在这张表里，单独有个tbs_proj_name表存放名称:通过项目编号和ID找到项目名称。由于项目名称会在审批过程中发生变化，所以名称不用存在这张表里，单独有个tbs_proj_name表存放名称
	 */
	private String projSn;

	/**
	 * 项目经理A，从项目中自动带出:项目经理A，从项目中自动带出
	 */
	private String arolename;

	/**
	 * 项目经理B，从项目中自动带出:项目经理B，从项目中自动带出
	 */
	private String brolename;

	/**
	 * 应签批委员人数:应签批委员人数
	 */
	private int tobe;

	/**
	 * 实际签批委员人数:实际签批委员人数
	 */
	private int actbe;
	
	/**
	 * 缺席委员人数:缺席委员人数
	 */
	private int nobe;

	/**
	 * 同意票数:同意票数
	 */
	private int agree;

	/**
	 * 反对票数:反对票数
	 */
	private int opoose;

	/**
	 * 弃权票数:弃权票数
	 */
	private int waiver;

	/**
	 * 回避票数:回避票数
	 */
	private int debarb;

	/**
	 * 签批类型【新项目、老项目新增、新项目变更、老项目变更、超时效项目、补议项目、其他】:签批类型【新项目、老项目新增、新项目变更、老项目变更、超时效项目、补议项目、其他】
	 */
	private String meetingtype;

	/**
	 * 可用融资担保授信金额（元）,反插入tbs_proj和tbs_proj_hisloc:可用融资担保授信金额（元）,反插入tbs_proj和tbs_proj_hisloc
	 */
	private BigDecimal vfaloc;

	/**
	 * 可用非融资担保授信金额（元）,反插入tbs_proj和tbs_proj_hisloc:可用非融资担保授信金额（元）,反插入tbs_proj和tbs_proj_hisloc
	 */
	private BigDecimal vnfaloc;

	/**
	 * 可用其他类担保授信金额（元）,反插入tbs_proj和tbs_proj_hisloc:可用其他类担保授信金额（元）,反插入tbs_proj和tbs_proj_hisloc
	 */
	private BigDecimal votloc;

	/**
	 * 总可用担保授信金额（元）,反插入tbs_proj和tbs_proj_hisloc:总可用担保授信金额（元）,反插入tbs_proj和tbs_proj_hisloc
	 */
	private BigDecimal vtotloc;

	/**
	 * 申请授信期限（月）来自tbs_proj.PERIOD，可修改反插入tbs_proj和tbs_proj_hisloc:申请授信期限（月）来自tbs_proj.PERIOD，可修改反插入tbs_proj和tbs_proj_hisloc
	 */
	private int periodCfm;

	/**
	 * 项目担保授信期间（开始）开始日期为上面的开会日期，反插入tbs_proj和tbs_proj_hisloc:项目担保授信期间（开始）开始日期为上面的开会日期，反插入tbs_proj和tbs_proj_hisloc
	 */
	private Date bdate;

	/**
	 * 项目担保授信期间（结束）结束日期为开会日期+期限,反插入tbs_proj和tbs_proj_hisloc:项目担保授信期间（结束）结束日期为开会日期+期限,反插入tbs_proj和tbs_proj_hisloc
	 */
	private Date edate;

	/**
	 * 放款方式。【一次性放款，多次放款】:放款方式。【一次性放款，多次放款】
	 */
	private String loantype;

	/**
	 * 放款说明。:放款说明。
	 */
	private String loanmem;

	/**
	 * 还款方式。【一次性还款，按月还部分，到期一次性结清，按季度还部分,到期一次性结算，其他】:还款方式。【一次性还款，按月还部分，到期一次性结清，按季度还部分,到期一次性结算，其他】
	 */
	private String repay;

	/**
	 * 每次还款额（元）:每次还款额（元）
	 */
	private BigDecimal repayinper;

	/**
	 * 还款说明。:还款说明。
	 */
	private String repaymem;

	/**
	 * 担保费率（‰/月）:担保费率（‰/月）
	 */
	private BigDecimal gatrate;

	/**
	 * 评审费用（元）:评审费用（元）
	 */
	private BigDecimal psfy;

	/**
	 * 其他费用（元）:其他费用（元）
	 */
	private BigDecimal qtfy;

	/**
	 * 担保费率说明:担保费率说明
	 */
	private String gatmem;

	/**
	 * 风险缓释措施界面显示时根据用户录入的回车键,分行显示,不要用给人感觉是一个text框一下子涵盖所有东西:风险缓释措施界面显示时根据用户录入的回车键,分行显示,不要用给人感觉是一个text框一下子涵盖所有东西
	 */
	private String riskavoid;

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
	
	private String gatrateu;

	/**
	 * tbs_proj:项目基本信息主表
	 */
	private TbsProj tbsProj;

	/**
	 * bdf2_dept:
	 */
	private Bdf2Dept bdf2Dept;

	/**
	 * bdf2_user:
	 */
	private Bdf2User bdf2User;

	/**
	 * tbs_projcfm2_bizvt:签批决议录入表子表-业务类型品种和金额
	 */
	private Set<TbsProjcfm2Bizvt> tbsProjcfm2BizvtSet = new HashSet<TbsProjcfm2Bizvt>(
			0);
	
	/**
	 * tbs_projcfm2_bizvt:签批决议录入表子表-审批意见
	 */
	private Set<TbsProjcfm2Opinion> tbsProjcfm2OpinionSet = new HashSet<TbsProjcfm2Opinion>(
			0);
	
	private Set<TbsProjOpinion> tbsProjOpinionSet = new HashSet<TbsProjOpinion>(0);

	public TbsProjcfm2() {
		super();
	}

	public TbsProjcfm2(int id, String sn, boolean valid, Date timestampInput,
			Date timestampUpdate, int keyinId, String memo, boolean del,
			String projSn, String arolename, String brolename, int tobe,
			int actbe, int agree, int nobe ,int opoose, int waiver, int debarb,
			String meetingtype, BigDecimal vfaloc, BigDecimal vnfaloc,
			BigDecimal votloc, BigDecimal vtotloc, int periodCfm, Date bdate,
			Date edate, String loantype, String loanmem, String repay,
			BigDecimal repayinper, String repaymem, BigDecimal gatrate,
			BigDecimal psfy, BigDecimal qtfy, String gatmem,
			String riskavoid, String by1, String by2, String by3,
			TbsProj tbsProj, Bdf2Dept bdf2Dept, Bdf2User bdf2User,
			Set<TbsProjcfm2Bizvt> tbsProjcfm2BizvtSet,
			Set<TbsProjcfm2Opinion> tbsProjcfm2OpinionSet,
			Set<TbsProjOpinion> tbsProjOpinionSet, String gatrateu) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.keyinId = keyinId;
		this.memo = memo;
		this.del = del;
		this.projSn = projSn;
		this.arolename = arolename;
		this.brolename = brolename;
		this.tobe = tobe;
		this.actbe = actbe;
		this.agree = agree;
		this.nobe = nobe;
		this.opoose = opoose;
		this.waiver = waiver;
		this.debarb = debarb;
		this.meetingtype = meetingtype;
		this.vfaloc = vfaloc;
		this.vnfaloc = vnfaloc;
		this.votloc = votloc;
		this.vtotloc = vtotloc;
		this.periodCfm = periodCfm;
		this.bdate = bdate;
		this.edate = edate;
		this.loantype = loantype;
		this.loanmem = loanmem;
		this.repay = repay;
		this.repayinper = repayinper;
		this.repaymem = repaymem;
		this.gatrate = gatrate;
		this.gatrateu = gatrateu;
		this.psfy = psfy;
		this.qtfy = qtfy;
		this.gatmem = gatmem;
		this.riskavoid = riskavoid;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.bdf2Dept = bdf2Dept;
		this.bdf2User = bdf2User;
		this.tbsProjcfm2BizvtSet = tbsProjcfm2BizvtSet;
		this.tbsProjcfm2OpinionSet = tbsProjcfm2OpinionSet;
		this.tbsProjOpinionSet = tbsProjOpinionSet;
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

	@Column(name = "SN", length = 50)
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

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "MEMO", columnDefinition = "CLOB")
	public String getMemo() {
		return memo;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}

	public void setProjSn(String projSn) {
		this.projSn = projSn;
	}

	@Column(name = "PROJ_SN", length = 20)
	public String getProjSn() {
		return projSn;
	}

	public void setArolename(String arolename) {
		this.arolename = arolename;
	}

	@Column(name = "AROLENAME", length = 45)
	public String getArolename() {
		return arolename;
	}

	public void setBrolename(String brolename) {
		this.brolename = brolename;
	}

	@Column(name = "BROLENAME", length = 45)
	public String getBrolename() {
		return brolename;
	}

	public void setTobe(int tobe) {
		this.tobe = tobe;
	}

	@Column(name = "TOBE")
	public int getTobe() {
		return tobe;
	}

	public void setActbe(int actbe) {
		this.actbe = actbe;
	}

	@Column(name = "ACTBE")
	public int getActbe() {
		return actbe;
	}
	
	public void setNobe(int nobe) {
		this.nobe = nobe;
	}

	@Column(name = "NOBE")
	public int getNobe() {
		return nobe;
	}

	public void setAgree(int agree) {
		this.agree = agree;
	}

	@Column(name = "AGREE")
	public int getAgree() {
		return agree;
	}

	public void setOpoose(int opoose) {
		this.opoose = opoose;
	}

	@Column(name = "OPOOSE")
	public int getOpoose() {
		return opoose;
	}

	public void setWaiver(int waiver) {
		this.waiver = waiver;
	}

	@Column(name = "WAIVER")
	public int getWaiver() {
		return waiver;
	}

	public void setDebarb(int debarb) {
		this.debarb = debarb;
	}

	@Column(name = "DEBARB")
	public int getDebarb() {
		return debarb;
	}

	public void setMeetingtype(String meetingtype) {
		this.meetingtype = meetingtype;
	}

	@Column(name = "MEETINGTYPE", length = 80)
	public String getMeetingtype() {
		return meetingtype;
	}

	public void setVfaloc(BigDecimal vfaloc) {
		this.vfaloc = vfaloc;
	}

	@Column(name = "VFALOC")
	public BigDecimal getVfaloc() {
		return vfaloc;
	}

	public void setVnfaloc(BigDecimal vnfaloc) {
		this.vnfaloc = vnfaloc;
	}

	@Column(name = "VNFALOC")
	public BigDecimal getVnfaloc() {
		return vnfaloc;
	}

	public void setVotloc(BigDecimal votloc) {
		this.votloc = votloc;
	}

	@Column(name = "VOTLOC")
	public BigDecimal getVotloc() {
		return votloc;
	}

	public void setVtotloc(BigDecimal vtotloc) {
		this.vtotloc = vtotloc;
	}

	@Column(name = "VTOTLOC")
	public BigDecimal getVtotloc() {
		return vtotloc;
	}

	public void setPeriodCfm(int periodCfm) {
		this.periodCfm = periodCfm;
	}

	@Column(name = "PERIOD_CFM")
	public int getPeriodCfm() {
		return periodCfm;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BDATE")
	public Date getBdate() {
		return bdate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EDATE")
	public Date getEdate() {
		return edate;
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

	public void setGatrate(BigDecimal gatrate) {
		this.gatrate = gatrate;
	}

	@Column(name = "GATRATE")
	public BigDecimal getGatrate() {
		return gatrate;
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

	public void setGatmem(String gatmem) {
		this.gatmem = gatmem;
	}

	@Column(name = "GATMEM",length = 500)
	public String getGatmem() {
		return gatmem;
	}

	public void setRiskavoid(String riskavoid) {
		this.riskavoid = riskavoid;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "RISKAVOID", columnDefinition = "CLOB")
	public String getRiskavoid() {
		return riskavoid;
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
	
	public void setGatrateu(String gatrateu) {
		this.gatrateu = gatrateu;
	}

	@Column(name = "GATRATEU", length = 5)
	public String getGatrateu() {
		return gatrateu;
	}
	
	public void setTbsProj(TbsProj tbsProj) {
		this.tbsProj = tbsProj;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJ_ID")
	public TbsProj getTbsProj() {
		return tbsProj;
	}

	public void setBdf2Dept(Bdf2Dept bdf2Dept) {
		this.bdf2Dept = bdf2Dept;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPTDID")
	public Bdf2Dept getBdf2Dept() {
		return bdf2Dept;
	}

	public void setBdf2User(Bdf2User bdf2User) {
		this.bdf2User = bdf2User;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MGMUID")
	public Bdf2User getBdf2User() {
		return bdf2User;
	}

	public void setTbsProjcfm2BizvtSet(Set<TbsProjcfm2Bizvt> tbsProjcfm2BizvtSet) {
		this.tbsProjcfm2BizvtSet = tbsProjcfm2BizvtSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjcfm2")
	public Set<TbsProjcfm2Bizvt> getTbsProjcfm2BizvtSet() {
		return tbsProjcfm2BizvtSet;
	}
	
	public void setTbsProjcfm2OpinionSet(Set<TbsProjcfm2Opinion> tbsProjcfm2OpinionSet) {
		this.tbsProjcfm2OpinionSet = tbsProjcfm2OpinionSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjcfm2")
	public Set<TbsProjcfm2Opinion> getTbsProjcfm2OpinionSet() {
		return tbsProjcfm2OpinionSet;
	}

	public void setTbsProjOpinionSet(
			Set<TbsProjOpinion> tbsProjOpinionSet) {
		this.tbsProjOpinionSet = tbsProjOpinionSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjcfm2")
	public Set<TbsProjOpinion> getTbsProjOpinionSet() {
		return tbsProjOpinionSet;
	}
	
	public String toString() {
		return "TbsProjcfm2 [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",memo=" + memo
				+ ",del=" + del + ",projSn=" + projSn + ",arolename="
				+ arolename + ",brolename=" + brolename + ",tobe=" + tobe
				+ ",actbe=" + actbe + ",nobe=" + nobe + ",agree=" + agree + ",opoose=" + opoose
				+ ",waiver=" + waiver + ",debarb=" + debarb + ",meetingtype="
				+ meetingtype + ",vfaloc=" + vfaloc + ",vnfaloc=" + vnfaloc
				+ ",votloc=" + votloc + ",vtotloc=" + vtotloc + ",periodCfm="
				+ periodCfm + ",bdate=" + bdate + ",edate=" + edate
				+ ",loantype=" + loantype + ",loanmem=" + loanmem + ",repay="
				+ repay + ",repayinper=" + repayinper + ",repaymem=" + repaymem
				+ ",gatrate=" + gatrate + ",psfy=" + psfy + ",qtfy=" + qtfy
				+ ",gatmem=" + gatmem + ",riskavoid=" + riskavoid + ",by1="
				+ by1 + ",by2=" + by2 + ",by3=" + by3 + ",gatrateu=" + gatrateu + ",tbsProj=" + tbsProj
				+ ",bdf2Dept=" + bdf2Dept + ",bdf2User=" + bdf2User
				+ ",tbsProjcfm2BizvtSet=" + tbsProjcfm2BizvtSet + ",tbsProjcfm2OpinionSet=" + tbsProjcfm2OpinionSet
				+ ",tbsProjOpinionSet=" + tbsProjOpinionSet + "]";
	}

}
