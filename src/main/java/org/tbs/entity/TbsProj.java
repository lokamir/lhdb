package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_proj:项目基本信息主表
 */
@Entity
@Table(name = "tbs_proj")
public class TbsProj implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 项目编号='锡联项目'+当前日期+序号每个新日期都从001开始:项目编号='锡联项目'+当前日期+序号每个新日期都从001开始
	 */
	private String sn;

	/**
	 * 项目的有效状态0.默认，项目未启动。99.锁定此项目。表示此项目正在进行审批流程处理，只能查看，不能做任何其他操作1.只要有过一次审批，就为1有效，表示项目开始启动进行运作，这时候的项目状态就通过ps_id查看2.表示是通过转贷续保方式生成的项目。转贷续保的操作就是将老项目的数据，主表和子表完全复制一份，proj_hislocl除外，产生一个新的项目单据。同时在新单据内，OLDID=之前项目的ID号。老单据内sonid=新产生的项目单据的id3,表示发生变更的项目,处理方式同2。:项目的有效状态0.默认，项目未启动。99.锁定此项目。表示此项目正在进行审批流程处理，只能查看，不能做任何其他操作1.只要有过一次审批，就为1有效，表示项目开始启动进行运作，这时候的项目状态就通过ps_id查看2.表示是通过转贷续保方式生成的项目。转贷续保的操作就是将老项目的数据，主表和子表完全复制一份，proj_hislocl除外，产生一个新的项目单据。同时在新单据内，OLDID=之前项目的ID号。老单据内sonid=新产生的项目单据的id3,表示发生变更的项目,处理方式同2。
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
	 * 项目名称=客户名称+申请授信金额+综合授信项目:项目名称=客户名称+申请授信金额+综合授信项目
	 */
	private String projName;

	/**
	 * 受理日期:受理日期
	 */
	private Date acptdate;

	/**
	 * 是否为政府基金项目，默认为0否:是否为政府基金项目，默认为0否
	 */
	private boolean fundproj;

	/**
	 * 是否为农商行股权质押项目，默认为0否:是否为农商行股权质押项目，默认为0否
	 */
	private boolean bankpledge;

	/**
	 * 申请担保授信期限（月）:申请担保授信期限（月）
	 */
	private int period;

	/**
	 * 申请融资担保授信金额（元）,项目经理手动输入‘申请’字样开头的金额表示客户像国联申请需要担保的金额。:申请融资担保授信金额（元）,项目经理手动输入‘申请’字样开头的金额表示客户像国联申请需要担保的金额。
	 */
	private BigDecimal faloc;

	/**
	 * 可用融资担保授信金额（元），只有在3.2决议确认流程审批通过之后，才会反插入这张表。‘可用’字样开头的金额表示国联经过审查之后，同意放给客户的担保金额:可用融资担保授信金额（元），只有在3.2决议确认流程审批通过之后，才会反插入这张表。‘可用’字样开头的金额表示国联经过审查之后，同意放给客户的担保金额
	 */
	private BigDecimal vfaloc;

	/**
	 * 申请非融资担保授信金额（元）:申请非融资担保授信金额（元）
	 */
	private BigDecimal nfaloc;

	/**
	 * 可用非融资担保授信金额（元）:可用非融资担保授信金额（元）
	 */
	private BigDecimal vnfaloc;

	/**
	 * 申请其他类担保授信金额（元）:申请其他类担保授信金额（元）
	 */
	private BigDecimal otloc;

	/**
	 * 可用其他类担保授信金额（元）:可用其他类担保授信金额（元）
	 */
	private BigDecimal votloc;

	/**
	 * 总申请担保授信金额（元）=FALOC+NFALOC+OTLOC:总申请担保授信金额（元）=FALOC+NFALOC+OTLOC
	 */
	private BigDecimal totloc;

	/**
	 * 总可用担保授信金额（元）=VFALOC+VNFALOC+OTLOC:总可用担保授信金额（元）=VFALOC+VNFALOC+OTLOC
	 */
	private BigDecimal vtotloc;

	/**
	 * 实际担保授信期限（月）数据来自cfm1或者cfm2:实际担保授信期限（月）数据来自cfm1或者cfm2
	 */
	private int periodCfm;

	/**
	 * 项目担保授信期间（开始）（年月日-年月日）不用手填，数据来自tbs_projcfm1或者tbs_projcfm2:项目担保授信期间（开始）（年月日-年月日）不用手填，数据来自tbs_projcfm1或者tbs_projcfm2
	 */
	private Date bdate;

	/**
	 * 项目担保授信期间（结束）（年月日-年月日）不用手填，数据来自tbs_projcfm1或者tbs_projcfm2:项目担保授信期间（结束）（年月日-年月日）不用手填，数据来自tbs_projcfm1或者tbs_projcfm2
	 */
	private Date edate;

	/**
	 * 放款方式。【一次性放款，多次放款】数据来自cfm1或者cfm2:放款方式。【一次性放款，多次放款】数据来自cfm1或者cfm2
	 */
	private String loantype;

	/**
	 * 放款说明。数据来自cfm1或者cfm2:放款说明。数据来自cfm1或者cfm2
	 */
	private String loanmem;

	/**
	 * 还款方式。【一次性还款，按月还部分，到期一次性结清，按季度还部分,到期一次性结算，其他】数据来自cfm1或者cfm2:还款方式。【一次性还款，按月还部分，到期一次性结清，按季度还部分,到期一次性结算，其他】数据来自cfm1或者cfm2
	 */
	private String repay;

	/**
	 * 每次还款额（元）数据来自cfm1或者cfm2:每次还款额（元）数据来自cfm1或者cfm2
	 */
	private BigDecimal repayinper;

	/**
	 * 还款说明。数据来自cfm1或者cfm2:还款说明。数据来自cfm1或者cfm2
	 */
	private String repaymem;

	/**
	 * 担保费率（‰/月）数据来自cfm1或者cfm2:担保费率（‰/月）数据来自cfm1或者cfm2
	 */
	private BigDecimal gatrate;

	/**
	 * 评审费用（元）数据来自cfm1或者cfm2:评审费用（元）数据来自cfm1或者cfm2
	 */
	private BigDecimal psfy;

	/**
	 * 其他费用（元）数据来自cfm1或者cfm2:其他费用（元）数据来自cfm1或者cfm2
	 */
	private BigDecimal qtfy;

	/**
	 * 担保费率说明,数据来自cfm1或者cfm2:担保费率说明,数据来自cfm1或者cfm2
	 */
	private String gatmem;

	/**
	 * 风险缓释措施界面显示时根据用户录入的回车键,分行显示,不要用给人感觉是一个text框一下子涵盖所有东西数据来自cfm1或者cfm2:风险缓释措施界面显示时根据用户录入的回车键,分行显示,不要用给人感觉是一个text框一下子涵盖所有东西数据来自cfm1或者cfm2
	 */
	private String riskavoid;

	/**
	 * 备注数据来自cfm1或者cfm2:备注数据来自cfm1或者cfm2
	 */
	private String memo;

	/**
	 * 删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据数据来自cfm1或者cfm2:删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据数据来自cfm1或者cfm2
	 */
	private boolean del;

	/**
	 * 只有解保项目，转贷续保，变更项目，此ID才会使用，存入老的项目的ID号。:只有解保项目，转贷续保，变更项目，此ID才会使用，存入老的项目的ID号。
	 */
	private int oldid;

	/**
	 * 只有解保项目，转贷续保，变更项目，此ID才会使用，放入转贷续保操作后生成的新项目单据的ID号。:只有解保项目，转贷续保，变更项目，此ID才会使用，放入转贷续保操作后生成的新项目单据的ID号。
	 */
	private int sonid;

	/**
	 * 项目异常。默认为0不异常，如果这个risk被置为1，则项目为异常项目。系统内有个查询，叫风险预警库，专门查询为1的项目:项目异常。默认为0不异常，如果这个risk被置为1，则项目为异常项目。系统内有个查询，叫风险预警库，专门查询为1的项目
	 */
	private boolean risk;

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
	private String by4;
	private String by5;
	private String by6;
	private String by7;
	private String by8;
	private String by9;
	/**
	 * tbs_bas_court:法院基本信息
	 */
	//private TbsBasCourt tbsBasCourt;	2017-06-01
	/**
	 * tbs_customer:客户基本信息
	 */
	private TbsCustomer tbsCustomer;

	/**
	 * tbs_bas_consway:项目基本信息-咨询方式
	 */
	private TbsBasConsway tbsBasConsway;

	/**
	 * tbs_bas_ps:项目状态
	 */
	private TbsBasPs tbsBasPs;

	/**
	 * tbs_bas_govfund:项目基本信息-政府基金名称
	 */
	private TbsBasGovfund tbsBasGovfund;

	/**
	 * tbs_bas_currency:币种
	 */
	private TbsBasCurrency tbsBasCurrency;

	/**
	 * bdf2_user:
	 */
	private Bdf2User bdf2User_A;

	/**
	 * bdf2_user:
	 */
	private Bdf2User bdf2User_B;

	/**
	 * tbs_adminsign:综合管理部用印
	 */
	private Set<TbsAdminsign> tbsAdminsignSet = new HashSet<TbsAdminsign>(0);

	/**
	 * tbs_casher:出纳费用确认
	 */
	private Set<TbsCasher> tbsCasherSet = new HashSet<TbsCasher>(0);

	/**
	 * tbs_proj_bank:项目基本信息子表-银行
	 */
	private Set<TbsProjBank> tbsProjBankSet = new HashSet<TbsProjBank>(0);

	/**
	 * tbs_proj_bizvt:项目基本信息子表-业务类型品种和金额
	 */
	private Set<TbsProjBizvt> tbsProjBizvtSet = new HashSet<TbsProjBizvt>(0);

	/**
	 * tbs_proj_cgg:项目与反担保的关联关系表
	 */
	private Set<TbsProjCgg> tbsProjCggSet = new HashSet<TbsProjCgg>(0);

	/**
	 * tbs_proj_hisloc:项目基本信息-可用担保授信金额历史记录表此表内的数据默认只能查询，不做任何修改，目的是为了记录项目历史的可用授信金额。数据来自tbs_proj内的三类（融资，非融资，其他）可用担保授信金额数据。
	 */
	private Set<TbsProjHisloc> tbsProjHislocSet = new HashSet<TbsProjHisloc>(0);

	/**
	 * tbs_proj_hisrole:项目基本信息子表-项目经理历史记录。此表用来查询项目下项目经理变更的记录
	 */
	private Set<TbsProjHisrole> tbsProjHisroleSet = new HashSet<TbsProjHisrole>(
			0);

	/**
	 * tbs_proj_hisstatus:项目基本信息子表-项目历史状态
	 */
	private Set<TbsProjHisstatus> tbsProjHisstatusSet = new HashSet<TbsProjHisstatus>(
			0);

	/**
	 * tbs_proj_htsh:项目基本信息子表-4.1合同审核这个流程只在系统里走个过场不涉及任何其他单据
	 */
	private Set<TbsProjHtsh> tbsProjHtshSet = new HashSet<TbsProjHtsh>(0);

	/**
	 * tbs_projcfm0:项目决策审批上会通知单
	 */
	private Set<TbsProjcfm0> tbsProjcfm0Set = new HashSet<TbsProjcfm0>(0);

	/**
	 * tbs_projcfm1:会议决议录入表。表单数据回插入tbs_proj和tbs_proj_hisloc
	 */
	private Set<TbsProjcfm1> tbsProjcfm1Set = new HashSet<TbsProjcfm1>(0);

	/**
	 * tbs_projcfm2:签批决议录入表。表单数据回插入tbs_proj和tbs_proj_hisloc
	 */
	private Set<TbsProjcfm2> tbsProjcfm2Set = new HashSet<TbsProjcfm2>(0);

	/**
	 * tbs_projchange_role:项目经理变更。项目如果在审批状态下，禁止变更
	 */
	private Set<TbsProjchangeRole> tbsProjchangeRoleSet = new HashSet<TbsProjchangeRole>(
			0);

	/**
	 * tbs_projcompsry:代偿申请单
	 */
	private Set<TbsProjcompsry> tbsProjcompsrySet = new HashSet<TbsProjcompsry>(
			0);

	/**
	 * tbs_projcompsry_pay:代偿请款单
	 */
	private Set<TbsProjcompsryPay> tbsProjcompsryPaySet = new HashSet<TbsProjcompsryPay>(
			0);

	/**
	 * tbs_projeaa:立项审批表。审批通过后，变化的数据回插入tbs_proj
	 */
	private Set<TbsProjeaa> tbsProjeaaSet = new HashSet<TbsProjeaa>(0);

	
	private Set<TbsRiskProjcfm> tbsRiskProjcfmSet = new HashSet<TbsRiskProjcfm>(0);

	/**
	 * tbs_projrol:追偿申请单
	 */
	private Set<TbsProjrol> tbsProjrolSet = new HashSet<TbsProjrol>(0);

	
	/**
	 * tbs_projundwrt:承保审批单
	 */
	private Set<TbsProjundwrt> tbsProjundwrtSet = new HashSet<TbsProjundwrt>(0);

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
	 * tbs_ptyexp:零星费用收退表
	 */
	private Set<TbsPtyexp> tbsPtyexpSet = new HashSet<TbsPtyexp>(0);

	/**
	 * 2015-12-25 手动加入
	 * */
	private BigDecimal initfaloc;
	private BigDecimal initnfaloc;
	private BigDecimal initotloc;
	private BigDecimal inittotloc;
	private BigDecimal nameloc;
	private Set<TbsProjOpinion> tbsProjOpinionSet = new HashSet<TbsProjOpinion>(0);
	
	/**
	 * 二期改库 2016-06-01
	 * */
	private Set<TbsProjRelease> tbsProjReleaseSet = new HashSet<TbsProjRelease>(0);
	private Set<TbsProjchangeMajcont> tbsProjchangeMajcontSet = new HashSet<TbsProjchangeMajcont>(0);
	private Set<TbsProjCheck> tbsProjCheckSet = new HashSet<TbsProjCheck>(0);
	private Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet = new HashSet<TbsProjchangeMajcontCfm>(0);
	
	/**
	 * 2017-06-01 手动加入
	 */
	private BigDecimal financial; //财务顾问费用
	private boolean govplatform;//是否为政府平台项目，默认为0否
	private boolean onenine;//是否为一九分担项目，默认为0否
	
	public TbsProj() {
		super();
	}

	public TbsProj(int id, String sn, boolean valid, Date timestampInput,
			Date timestampUpdate, int keyinId, String projName, Date acptdate,
			boolean fundproj, boolean bankpledge, int period, BigDecimal faloc,
			BigDecimal vfaloc, BigDecimal nfaloc, BigDecimal vnfaloc,
			BigDecimal otloc, BigDecimal votloc, BigDecimal totloc,
			BigDecimal vtotloc, int periodCfm, Date bdate, Date edate,
			String loantype, String loanmem, String repay,
			BigDecimal repayinper, String repaymem, BigDecimal gatrate,
			BigDecimal psfy, BigDecimal qtfy, String gatmem, String riskavoid,
			String memo, boolean del, int oldid, int sonid, boolean risk,
			String by1, String by2, String by3, TbsCustomer tbsCustomer,
			boolean govplatform,boolean onenine,BigDecimal financial,//2017-06-01 手动加入
			TbsBasConsway tbsBasConsway, TbsBasPs tbsBasPs,
			TbsBasGovfund tbsBasGovfund, TbsBasCurrency tbsBasCurrency,
			Bdf2User bdf2User_A, Bdf2User bdf2User_B,
			Set<TbsAdminsign> tbsAdminsignSet, Set<TbsCasher> tbsCasherSet,
			Set<TbsProjBank> tbsProjBankSet, Set<TbsProjBizvt> tbsProjBizvtSet,
			Set<TbsProjCgg> tbsProjCggSet, Set<TbsProjHisloc> tbsProjHislocSet,
			Set<TbsProjHisrole> tbsProjHisroleSet,
			Set<TbsProjHisstatus> tbsProjHisstatusSet,
			Set<TbsProjHtsh> tbsProjHtshSet, Set<TbsProjcfm0> tbsProjcfm0Set,
			Set<TbsProjcfm1> tbsProjcfm1Set, Set<TbsProjcfm2> tbsProjcfm2Set,
			Set<TbsProjchangeRole> tbsProjchangeRoleSet,
			Set<TbsProjcompsry> tbsProjcompsrySet,
			Set<TbsProjcompsryPay> tbsProjcompsryPaySet,
			Set<TbsProjeaa> tbsProjeaaSet, Set<TbsRiskProjcfm> tbsRiskProjcfmSet,
			Set<TbsProjrol> tbsProjrolSet, 
			Set<TbsProjundwrt> tbsProjundwrtSet,
			Set<TbsProjundwrtBizvt> tbsProjundwrtBizvtSet,
			Set<TbsProjundwrtCfmar> tbsProjundwrtCfmarSet,
			Set<TbsPtyexp> tbsPtyexpSet,BigDecimal initfaloc,BigDecimal initnfaloc,BigDecimal initotloc,BigDecimal inittotloc,
			BigDecimal nameloc,
			Set<TbsProjOpinion> tbsProjOpinionSet, String by4, String by5, String by6, String by7,
			String by8, String by9, String gatrateu,//TbsBasCourt tbsBasCourt, 2017-06-01
			Set<TbsProjRelease> tbsProjReleaseSet, Set<TbsProjchangeMajcont> tbsProjchangeMajcontSet,
			Set<TbsProjCheck> tbsProjCheckSet, Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.keyinId = keyinId;
		this.projName = projName;
		this.acptdate = acptdate;
		this.fundproj = fundproj;
		this.bankpledge = bankpledge;
		this.period = period;
		this.faloc = faloc;
		this.vfaloc = vfaloc;
		this.nfaloc = nfaloc;
		this.vnfaloc = vnfaloc;
		this.otloc = otloc;
		this.votloc = votloc;
		this.totloc = totloc;
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
		this.psfy = psfy;
		this.qtfy = qtfy;
		this.gatmem = gatmem;
		this.riskavoid = riskavoid;
		this.memo = memo;
		this.del = del;
		this.oldid = oldid;
		this.sonid = sonid;
		this.risk = risk;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsCustomer = tbsCustomer;
		this.tbsBasConsway = tbsBasConsway;
		this.tbsBasPs = tbsBasPs;
		this.tbsBasGovfund = tbsBasGovfund;
		this.tbsBasCurrency = tbsBasCurrency;
		this.bdf2User_A = bdf2User_A;
		this.bdf2User_B = bdf2User_B;
		this.tbsAdminsignSet = tbsAdminsignSet;
		this.tbsCasherSet = tbsCasherSet;
		this.tbsProjBankSet = tbsProjBankSet;
		this.tbsProjBizvtSet = tbsProjBizvtSet;
		this.tbsProjCggSet = tbsProjCggSet;
		this.tbsProjHislocSet = tbsProjHislocSet;
		this.tbsProjHisroleSet = tbsProjHisroleSet;
		this.tbsProjHisstatusSet = tbsProjHisstatusSet;
		this.tbsProjHtshSet = tbsProjHtshSet;
		this.tbsProjcfm0Set = tbsProjcfm0Set;
		this.tbsProjcfm1Set = tbsProjcfm1Set;
		this.tbsProjcfm2Set = tbsProjcfm2Set;
		this.tbsProjchangeRoleSet = tbsProjchangeRoleSet;
		this.tbsProjcompsrySet = tbsProjcompsrySet;
		this.tbsProjcompsryPaySet = tbsProjcompsryPaySet;
		this.tbsProjeaaSet = tbsProjeaaSet;
		this.tbsRiskProjcfmSet = tbsRiskProjcfmSet;
		this.tbsProjrolSet = tbsProjrolSet;
		this.tbsProjundwrtSet = tbsProjundwrtSet;
		this.tbsProjundwrtBizvtSet = tbsProjundwrtBizvtSet;
		this.tbsProjundwrtCfmarSet = tbsProjundwrtCfmarSet;
		this.tbsPtyexpSet = tbsPtyexpSet;
		this.initfaloc = initfaloc;
		this.initnfaloc = initnfaloc;
		this.initotloc = initotloc;
		this.inittotloc = inittotloc;
		this.nameloc = nameloc;
		this.tbsProjOpinionSet = tbsProjOpinionSet;
		this.by4 = by4;
		this.by5 = by5;
		this.by6 = by6;
		this.by7 = by7;
		this.by8 = by8;
		this.by9 = by9;
		this.gatrateu = gatrateu;
		//this.tbsBasCourt = tbsBasCourt; 2017-06-01
		this.tbsProjReleaseSet = tbsProjReleaseSet;
		this.tbsProjchangeMajcontSet = tbsProjchangeMajcontSet;
		this.tbsProjchangeMajcontCfmSet = tbsProjchangeMajcontCfmSet;
		this.tbsProjCheckSet = tbsProjCheckSet;
		/**
		 * 2017-06-01 手动加入
		 */
		this.financial = financial;
		this.govplatform = govplatform;
		this.onenine = onenine;
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

	@Column(name = "SN", length = 20, unique = true)
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

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "PROJ_NAME", length = 200)
	public String getProjName() {
		return projName;
	}

	public void setAcptdate(Date acptdate) {
		this.acptdate = acptdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACPTDATE")
	public Date getAcptdate() {
		return acptdate;
	}

	public void setFundproj(boolean fundproj) {
		this.fundproj = fundproj;
	}

	@Column(name = "FUNDPROJ")
	public boolean isFundproj() {
		return fundproj;
	}

	public void setBankpledge(boolean bankpledge) {
		this.bankpledge = bankpledge;
	}

	@Column(name = "BANKPLEDGE")
	public boolean isBankpledge() {
		return bankpledge;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Column(name = "PERIOD")
	public int getPeriod() {
		return period;
	}

	public void setFaloc(BigDecimal faloc) {
		this.faloc = faloc;
	}

	@Column(name = "FALOC")
	public BigDecimal getFaloc() {
		return faloc;
	}

	public void setVfaloc(BigDecimal vfaloc) {
		this.vfaloc = vfaloc;
	}

	@Column(name = "VFALOC")
	public BigDecimal getVfaloc() {
		return vfaloc;
	}

	public void setNfaloc(BigDecimal nfaloc) {
		this.nfaloc = nfaloc;
	}

	@Column(name = "NFALOC")
	public BigDecimal getNfaloc() {
		return nfaloc;
	}

	public void setVnfaloc(BigDecimal vnfaloc) {
		this.vnfaloc = vnfaloc;
	}

	@Column(name = "VNFALOC")
	public BigDecimal getVnfaloc() {
		return vnfaloc;
	}

	public void setOtloc(BigDecimal otloc) {
		this.otloc = otloc;
	}

	@Column(name = "OTLOC")
	public BigDecimal getOtloc() {
		return otloc;
	}

	public void setVotloc(BigDecimal votloc) {
		this.votloc = votloc;
	}

	@Column(name = "VOTLOC")
	public BigDecimal getVotloc() {
		return votloc;
	}

	public void setTotloc(BigDecimal totloc) {
		this.totloc = totloc;
	}

	@Column(name = "TOTLOC")
	public BigDecimal getTotloc() {
		return totloc;
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

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "GATMEM", columnDefinition = "CLOB")
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

	public void setOldid(int oldid) {
		this.oldid = oldid;
	}

	@Column(name = "OLDID")
	public int getOldid() {
		return oldid;
	}

	public void setSonid(int sonid) {
		this.sonid = sonid;
	}

	@Column(name = "SONID")
	public int getSonid() {
		return sonid;
	}

	public void setRisk(boolean risk) {
		this.risk = risk;
	}

	@Column(name = "RISK")
	public boolean isRisk() {
		return risk;
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
	@JoinColumn(name = "CUS_ID", nullable = false)
	public TbsCustomer getTbsCustomer() {
		return tbsCustomer;
	}

	public void setTbsBasConsway(TbsBasConsway tbsBasConsway) {
		this.tbsBasConsway = tbsBasConsway;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONSWAY_ID")
	public TbsBasConsway getTbsBasConsway() {
		return tbsBasConsway;
	}

	public void setTbsBasPs(TbsBasPs tbsBasPs) {
		this.tbsBasPs = tbsBasPs;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PS_ID")
	public TbsBasPs getTbsBasPs() {
		return tbsBasPs;
	}

	public void setTbsBasGovfund(TbsBasGovfund tbsBasGovfund) {
		this.tbsBasGovfund = tbsBasGovfund;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "GOVFUND_ID")
	public TbsBasGovfund getTbsBasGovfund() {
		return tbsBasGovfund;
	}

	public void setTbsBasCurrency(TbsBasCurrency tbsBasCurrency) {
		this.tbsBasCurrency = tbsBasCurrency;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CURRENCY_ID")
	public TbsBasCurrency getTbsBasCurrency() {
		return tbsBasCurrency;
	}

	public void setBdf2User_A(Bdf2User bdf2User_A) {
		this.bdf2User_A = bdf2User_A;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "A_ROLE_ID")
	public Bdf2User getBdf2User_A() {
		return bdf2User_A;
	}

	public void setBdf2User_B(Bdf2User bdf2User_B) {
		this.bdf2User_B = bdf2User_B;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "B_ROLE_ID")
	public Bdf2User getBdf2User_B() {
		return bdf2User_B;
	}

	public void setTbsAdminsignSet(Set<TbsAdminsign> tbsAdminsignSet) {
		this.tbsAdminsignSet = tbsAdminsignSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsAdminsign> getTbsAdminsignSet() {
		return tbsAdminsignSet;
	}

	public void setTbsCasherSet(Set<TbsCasher> tbsCasherSet) {
		this.tbsCasherSet = tbsCasherSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsCasher> getTbsCasherSet() {
		return tbsCasherSet;
	}

	public void setTbsProjBankSet(Set<TbsProjBank> tbsProjBankSet) {
		this.tbsProjBankSet = tbsProjBankSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjBank> getTbsProjBankSet() {
		return tbsProjBankSet;
	}

	public void setTbsProjBizvtSet(Set<TbsProjBizvt> tbsProjBizvtSet) {
		this.tbsProjBizvtSet = tbsProjBizvtSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjBizvt> getTbsProjBizvtSet() {
		return tbsProjBizvtSet;
	}

	public void setTbsProjCggSet(Set<TbsProjCgg> tbsProjCggSet) {
		this.tbsProjCggSet = tbsProjCggSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjCgg> getTbsProjCggSet() {
		return tbsProjCggSet;
	}

	public void setTbsProjHislocSet(Set<TbsProjHisloc> tbsProjHislocSet) {
		this.tbsProjHislocSet = tbsProjHislocSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjHisloc> getTbsProjHislocSet() {
		return tbsProjHislocSet;
	}

	public void setTbsProjHisroleSet(Set<TbsProjHisrole> tbsProjHisroleSet) {
		this.tbsProjHisroleSet = tbsProjHisroleSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjHisrole> getTbsProjHisroleSet() {
		return tbsProjHisroleSet;
	}

	public void setTbsProjHisstatusSet(Set<TbsProjHisstatus> tbsProjHisstatusSet) {
		this.tbsProjHisstatusSet = tbsProjHisstatusSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjHisstatus> getTbsProjHisstatusSet() {
		return tbsProjHisstatusSet;
	}

	public void setTbsProjHtshSet(Set<TbsProjHtsh> tbsProjHtshSet) {
		this.tbsProjHtshSet = tbsProjHtshSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjHtsh> getTbsProjHtshSet() {
		return tbsProjHtshSet;
	}

	public void setTbsProjcfm0Set(Set<TbsProjcfm0> tbsProjcfm0Set) {
		this.tbsProjcfm0Set = tbsProjcfm0Set;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjcfm0> getTbsProjcfm0Set() {
		return tbsProjcfm0Set;
	}

	public void setTbsProjcfm1Set(Set<TbsProjcfm1> tbsProjcfm1Set) {
		this.tbsProjcfm1Set = tbsProjcfm1Set;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjcfm1> getTbsProjcfm1Set() {
		return tbsProjcfm1Set;
	}

	public void setTbsProjcfm2Set(Set<TbsProjcfm2> tbsProjcfm2Set) {
		this.tbsProjcfm2Set = tbsProjcfm2Set;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjcfm2> getTbsProjcfm2Set() {
		return tbsProjcfm2Set;
	}

	public void setTbsProjchangeRoleSet(
			Set<TbsProjchangeRole> tbsProjchangeRoleSet) {
		this.tbsProjchangeRoleSet = tbsProjchangeRoleSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjchangeRole> getTbsProjchangeRoleSet() {
		return tbsProjchangeRoleSet;
	}

	public void setTbsProjcompsrySet(Set<TbsProjcompsry> tbsProjcompsrySet) {
		this.tbsProjcompsrySet = tbsProjcompsrySet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjcompsry> getTbsProjcompsrySet() {
		return tbsProjcompsrySet;
	}

	public void setTbsProjcompsryPaySet(
			Set<TbsProjcompsryPay> tbsProjcompsryPaySet) {
		this.tbsProjcompsryPaySet = tbsProjcompsryPaySet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjcompsryPay> getTbsProjcompsryPaySet() {
		return tbsProjcompsryPaySet;
	}

	public void setTbsProjeaaSet(Set<TbsProjeaa> tbsProjeaaSet) {
		this.tbsProjeaaSet = tbsProjeaaSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjeaa> getTbsProjeaaSet() {
		return tbsProjeaaSet;
	}

	public void setTbsRiskProjcfmSet(Set<TbsRiskProjcfm> tbsRiskProjcfmSet) {
		this.tbsRiskProjcfmSet = tbsRiskProjcfmSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsRiskProjcfm> getTbsRiskProjcfmSet() {
		return tbsRiskProjcfmSet;
	}

	public void setTbsProjrolSet(Set<TbsProjrol> tbsProjrolSet) {
		this.tbsProjrolSet = tbsProjrolSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjrol> getTbsProjrolSet() {
		return tbsProjrolSet;
	}

	public void setTbsProjundwrtSet(Set<TbsProjundwrt> tbsProjundwrtSet) {
		this.tbsProjundwrtSet = tbsProjundwrtSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjundwrt> getTbsProjundwrtSet() {
		return tbsProjundwrtSet;
	}

	public void setTbsProjundwrtBizvtSet(
			Set<TbsProjundwrtBizvt> tbsProjundwrtBizvtSet) {
		this.tbsProjundwrtBizvtSet = tbsProjundwrtBizvtSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjundwrtBizvt> getTbsProjundwrtBizvtSet() {
		return tbsProjundwrtBizvtSet;
	}

	public void setTbsProjundwrtCfmarSet(
			Set<TbsProjundwrtCfmar> tbsProjundwrtCfmarSet) {
		this.tbsProjundwrtCfmarSet = tbsProjundwrtCfmarSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjundwrtCfmar> getTbsProjundwrtCfmarSet() {
		return tbsProjundwrtCfmarSet;
	}

	public void setTbsPtyexpSet(Set<TbsPtyexp> tbsPtyexpSet) {
		this.tbsPtyexpSet = tbsPtyexpSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsPtyexp> getTbsPtyexpSet() {
		return tbsPtyexpSet;
	}

	public void setInitfaloc(BigDecimal initfaloc) {
		this.initfaloc = initfaloc;
	}

	@Column(name = "INITFALOC")
	public BigDecimal getInitfaloc() {
		return initfaloc;
	}
	
	public void setInitnfaloc(BigDecimal initnfaloc) {
		this.initnfaloc = initnfaloc;
	}

	@Column(name = "INITNFALOC")
	public BigDecimal getInitnfaloc() {
		return initnfaloc;
	}
	
	public void setInitotloc(BigDecimal initotloc) {
		this.initotloc = initotloc;
	}

	@Column(name = "INITOTLOC")
	public BigDecimal getInitotloc() {
		return initotloc;
	}
	
	public void setInittotloc(BigDecimal inittotloc) {
		this.inittotloc = inittotloc;
	}

	@Column(name = "INITTOTLOC")
	public BigDecimal getInittotloc() {
		return inittotloc;
	}
	
	public void setNameloc(BigDecimal nameloc) {
		this.nameloc = nameloc;
	}

	@Column(name = "NAMELOC")
	public BigDecimal getNameloc() {
		return nameloc;
	}
		
	public void setTbsProjOpinionSet(
			Set<TbsProjOpinion> tbsProjOpinionSet) {
		this.tbsProjOpinionSet = tbsProjOpinionSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjOpinion> getTbsProjOpinionSet() {
		return tbsProjOpinionSet;
	}
	
	public void setBy4(String by4) {
		this.by4 = by4;
	}

	@Column(name = "BY4", length = 300)
	public String getBy4() {
		return by4;
	}

	public void setBy5(String by5) {
		this.by5 = by5;
	}

	@Column(name = "BY5", length = 300)
	public String getBy5() {
		return by5;
	}

	public void setBy6(String by6) {
		this.by6 = by6;
	}

	@Column(name = "BY6", length = 300)
	public String getBy6() {
		return by6;
	}

	public void setBy7(String by7) {
		this.by7 = by7;
	}

	@Column(name = "BY7", length = 300)
	public String getBy7() {
		return by7;
	}

	public void setBy8(String by8) {
		this.by8 = by8;
	}

	@Column(name = "BY8", length = 300)
	public String getBy8() {
		return by8;
	}

	public void setBy9(String by9) {
		this.by9 = by9;
	}

	@Column(name = "BY9", length = 300)
	public String getBy9() {
		return by9;
	}

	public void setGatrateu(String gatrateu) {
		this.gatrateu = gatrateu;
	}

	@Column(name = "GATRATEU", length = 5)
	public String getGatrateu() {
		return gatrateu;
	}
	/*public void setTbsBasCourt(TbsBasCourt tbsBasCourt) {
		this.tbsBasCourt = tbsBasCourt;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COURTID")
	public TbsBasCourt getTbsBasCourt() {
		return tbsBasCourt;
	}
	2017-06-01
	*/

	public void setTbsProjReleaseSet(Set<TbsProjRelease> tbsProjReleaseSet) {
		this.tbsProjReleaseSet = tbsProjReleaseSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjRelease> getTbsProjReleaseSet() {
		return tbsProjReleaseSet;
	}
	
	public void setTbsProjchangeMajcontSet(Set<TbsProjchangeMajcont> tbsProjchangeMajcontSet) {
		this.tbsProjchangeMajcontSet = tbsProjchangeMajcontSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjchangeMajcont> getTbsProjchangeMajcontSet() {
		return tbsProjchangeMajcontSet;
	}
	
	public void setTbsProjCheckSet(Set<TbsProjCheck> tbsProjCheckSet) {
		this.tbsProjCheckSet = tbsProjCheckSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjCheck> getTbsProjCheckSet() {
		return tbsProjCheckSet;
	}
	
	public void setTbsProjchangeMajcontCfmSet(
			Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet) {
		this.tbsProjchangeMajcontCfmSet = tbsProjchangeMajcontCfmSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProj")
	public Set<TbsProjchangeMajcontCfm> getTbsProjchangeMajcontCfmSet() {
		return tbsProjchangeMajcontCfmSet;
	}
	
	/**
	 * 2017-06-01 手动加入
	 */
	public void setGovplatform(boolean govplatform) {
		this.govplatform = govplatform;
	}

	@Column(name = "GOVPLATFORM")
	public boolean isGovplatform() {
		return govplatform;
	}
	
	public void setOnenine(boolean onenine) {
		this.onenine = onenine;
	}

	@Column(name = "ONENINE")
	public boolean isOnenine() {
		return onenine;
	}
	
	public void setFinancial(BigDecimal financial) {
		this.financial = financial;
	}

	@Column(name = "FINANCIAL")
	public BigDecimal getFinancial() {
		return financial;
	}
	/**
	 * 2017-06-01结束
	 */
	
	
	public String toString() {
		return "TbsProj [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",projName="
				+ projName + ",acptdate=" + acptdate + ",fundproj=" + fundproj
				+ ",bankpledge=" + bankpledge + ",period=" + period + ",faloc="
				+ faloc + ",vfaloc=" + vfaloc + ",nfaloc=" + nfaloc
				+ ",vnfaloc=" + vnfaloc + ",otloc=" + otloc + ",votloc="
				+ votloc + ",totloc=" + totloc + ",vtotloc=" + vtotloc
				+ ",periodCfm=" + periodCfm + ",bdate=" + bdate + ",edate="
				+ edate + ",loantype=" + loantype + ",loanmem=" + loanmem
				+ ",repay=" + repay + ",repayinper=" + repayinper
				+ ",repaymem=" + repaymem + ",gatrate=" + gatrate + ",psfy="
				+ psfy + ",qtfy=" + qtfy + ",gatmem=" + gatmem + ",riskavoid="
				+ riskavoid + ",memo=" + memo + ",del=" + del + ",oldid="
				+ oldid + ",sonid=" + sonid + ",risk=" + risk + ",by1=" + by1
				+ ",by2=" + by2 + ",by3=" + by3 + ",tbsCustomer=" + tbsCustomer
				+ ",tbsBasConsway=" + tbsBasConsway + ",tbsBasPs=" + tbsBasPs
				+ ",tbsBasGovfund=" + tbsBasGovfund + ",tbsBasCurrency="
				+ tbsBasCurrency + ",bdf2User_A=" + bdf2User_A + ",bdf2User_B="
				+ bdf2User_B + ",tbsAdminsignSet=" + tbsAdminsignSet
				+ ",tbsCasherSet=" + tbsCasherSet + ",tbsProjBankSet="
				+ tbsProjBankSet + ",tbsProjBizvtSet=" + tbsProjBizvtSet
				+ ",tbsProjCggSet=" + tbsProjCggSet + ",tbsProjHislocSet="
				+ tbsProjHislocSet 
				+ ",tbsProjHisroleSet=" + tbsProjHisroleSet
				+ ",tbsProjHisstatusSet=" + tbsProjHisstatusSet
				+ ",tbsProjHtshSet=" + tbsProjHtshSet + ",tbsProjcfm0Set="
				+ tbsProjcfm0Set + ",tbsProjcfm1Set=" + tbsProjcfm1Set
				+ ",tbsProjcfm2Set=" + tbsProjcfm2Set
				+ ",tbsProjchangeRoleSet=" + tbsProjchangeRoleSet
				+ ",tbsProjcompsrySet=" + tbsProjcompsrySet
				+ ",tbsProjcompsryPaySet=" + tbsProjcompsryPaySet
				+ ",tbsProjeaaSet=" + tbsProjeaaSet + ",tbsRiskProjcfmSet="
				+ tbsRiskProjcfmSet + ",tbsProjrolSet=" + tbsProjrolSet
				+ ",tbsProjundwrtSet=" + tbsProjundwrtSet + ",tbsProjundwrtBizvtSet="
				+ tbsProjundwrtBizvtSet + ",tbsProjundwrtCfmarSet="
				+ tbsProjundwrtCfmarSet + ",tbsPtyexpSet=" + tbsPtyexpSet 
				+ ",initfaloc=" + initfaloc + ",initnfaloc=" + initnfaloc 
				+ ",initotloc=" + initotloc + ",inittotloc=" + inittotloc 
				+ ",nameloc=" + nameloc
				+ ",tbsProjOpinionSet=" + tbsProjOpinionSet 
				+ ",by4=" + by4 + ",by5=" + by5 + ",by6=" + by6 + ",by7=" + by7 + ",by8=" + by8 
				+ ",by9=" + by9 + ",gatrateu=" + gatrateu + //",tbsBasCourt="+ tbsBasCourt + 2017-06-01
				",tbsProjReleaseSet=" + tbsProjReleaseSet 
				+ ",tbsProjchangeMajcontSet=" + tbsProjchangeMajcontSet
				+ ",tbsProjCheckSet=" + tbsProjCheckSet 
				+ ",tbsProjchangeMajcontCfmSet=" + tbsProjchangeMajcontCfmSet
				+ ",financial=" + financial + ",onenine=" + onenine + ",govplatform=" + govplatform //2017-06-01添加
				+"]";
	}

}
