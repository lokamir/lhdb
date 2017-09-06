package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * bdf2_user:
 */
@Entity
@Table(name = "bdf2_user")
public class Bdf2User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * USERNAME_:
	 */
	private String username;

	/**
	 * ADDRESS_:
	 */
	private String address;

	/**
	 * ADMINISTRATOR_:
	 */
	private boolean administrator;

	/**
	 * BIRTHDAY_:
	 */
	private Date birthday;

	/**
	 * CNAME_:
	 */
	private String cname;

	/**
	 * COMPANY_ID_:
	 */
	private String companyId;

	/**
	 * CREATE_DATE_:
	 */
	private Date createDate;

	/**
	 * EMAIL_:
	 */
	private String email;

	/**
	 * ENABLED_:
	 */
	private boolean enabled;

	/**
	 * ENAME_:
	 */
	private String ename;

	/**
	 * MALE_:
	 */
	private boolean male;

	/**
	 * MOBILE_:
	 */
	private String mobile;

	/**
	 * PASSWORD_:
	 */
	private String password;

	/**
	 * SALT_:
	 */
	private String salt;

	/**
	 * tbs_adminsign:综合管理部用印
	 */
	private Set<TbsAdminsign> tbsAdminsignSet = new HashSet<TbsAdminsign>(0);

	/**
	 * tbs_proj:项目基本信息主表
	 */
	private Set<TbsProj> tbsProjSet_M = new HashSet<TbsProj>(0);

	/**
	 * tbs_proj:项目基本信息主表
	 */
	private Set<TbsProj> tbsProjSet_S = new HashSet<TbsProj>(0);

	/**
	 * tbs_proj_hisrole:项目基本信息子表-项目经理历史记录。此表用来查询项目下项目经理变更的记录
	 */
	private Set<TbsProjHisrole> tbsProjHisroleSet_A = new HashSet<TbsProjHisrole>(
			0);

	/**
	 * tbs_proj_hisrole:项目基本信息子表-项目经理历史记录。此表用来查询项目下项目经理变更的记录
	 */
	private Set<TbsProjHisrole> tbsProjHisroleSet_B = new HashSet<TbsProjHisrole>(
			0);

	/**
	 * tbs_projcfm0_attend:项目决策审批上会通知单-参会人员子表
	 */
	private Set<TbsProjcfm0Attend> tbsProjcfm0AttendSet = new HashSet<TbsProjcfm0Attend>(
			0);

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
	private Set<TbsProjchangeRole> tbsProjchangeRoleSet_AO = new HashSet<TbsProjchangeRole>(
			0);

	/**
	 * tbs_projchange_role:项目经理变更。项目如果在审批状态下，禁止变更
	 */
	private Set<TbsProjchangeRole> tbsProjchangeRoleSet_BO = new HashSet<TbsProjchangeRole>(
			0);

	/**
	 * tbs_projchange_role:项目经理变更。项目如果在审批状态下，禁止变更
	 */
	private Set<TbsProjchangeRole> tbsProjchangeRoleSet_AN = new HashSet<TbsProjchangeRole>(
			0);

	/**
	 * tbs_projchange_role:项目经理变更。项目如果在审批状态下，禁止变更
	 */
	private Set<TbsProjchangeRole> tbsProjchangeRoleSet_BN = new HashSet<TbsProjchangeRole>(
			0);

	/**
	 * tbs_projeaa:立项审批表。审批通过后，变化的数据回插入tbs_proj
	 */
	private Set<TbsProjeaa> tbsProjeaaSet = new HashSet<TbsProjeaa>(0);

	
	private Set<TbsRiskProjcfm> tbsRiskProjcfmSet = new HashSet<TbsRiskProjcfm>(0);
	
	private Set<TbsRiskProjcfmAttend> tbsRiskProjcfmAttendSet = new HashSet<TbsRiskProjcfmAttend>(0);

	/**
	 * tbs_approver:审批人与职位对应表
	 */
	private Set<TbsApprover> tbsApproverSet = new HashSet<TbsApprover>(0);
	
	private Set<TbsProjcfm1Opinion> tbsProjcfm1OpinionSet = new HashSet<TbsProjcfm1Opinion>(0);
	private Set<TbsProjcfm2Opinion> tbsProjcfm2OpinionSet = new HashSet<TbsProjcfm2Opinion>(0);
	private Set<TbsProjOpinion> tbsProjOpinionSet = new HashSet<TbsProjOpinion>(0);
	private Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet = new HashSet<TbsProjchangeMajcontCfm>(0);
	private Set<TbsProjchangeMajcontCfmattend> tbsProjchangeMajcontCfmattendSet = new HashSet<TbsProjchangeMajcontCfmattend>(0);
	
	public Bdf2User() {
		super();
	}

	public Bdf2User(int id, String username, String address,
			boolean administrator, Date birthday, String cname,
			String companyId, Date createDate, String email, boolean enabled,
			String ename, boolean male, String mobile, String password,
			String salt, Set<TbsAdminsign> tbsAdminsignSet,
			Set<TbsProj> tbsProjSet_M, Set<TbsProj> tbsProjSet_S,
			Set<TbsProjHisrole> tbsProjHisroleSet_A,
			Set<TbsProjHisrole> tbsProjHisroleSet_B,
			Set<TbsProjcfm0Attend> tbsProjcfm0AttendSet,
			Set<TbsProjcfm1> tbsProjcfm1Set, Set<TbsProjcfm2> tbsProjcfm2Set,
			Set<TbsProjchangeRole> tbsProjchangeRoleSet_AO,
			Set<TbsProjchangeRole> tbsProjchangeRoleSet_BO,
			Set<TbsProjchangeRole> tbsProjchangeRoleSet_AN,
			Set<TbsProjchangeRole> tbsProjchangeRoleSet_BN,
			Set<TbsProjeaa> tbsProjeaaSet, Set<TbsRiskProjcfm> tbsRiskProjcfmSet,
			Set<TbsRiskProjcfmAttend> tbsRiskProjcfmAttendSet,
			Set<TbsApprover> tbsApproverSet,
			Set<TbsProjcfm1Opinion> tbsProjcfm1OpinionSet,
			Set<TbsProjcfm2Opinion> tbsProjcfm2OpinionSet,
			Set<TbsProjOpinion> tbsProjOpinionSet,
			Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet,
			Set<TbsProjchangeMajcontCfmattend> tbsProjchangeMajcontCfmattendSet){
		super();
		this.id = id;
		this.username = username;
		this.address = address;
		this.administrator = administrator;
		this.birthday = birthday;
		this.cname = cname;
		this.companyId = companyId;
		this.createDate = createDate;
		this.email = email;
		this.enabled = enabled;
		this.ename = ename;
		this.male = male;
		this.mobile = mobile;
		this.password = password;
		this.salt = salt;
		this.tbsAdminsignSet = tbsAdminsignSet;
		this.tbsProjSet_M = tbsProjSet_M;
		this.tbsProjSet_S = tbsProjSet_S;
		this.tbsProjHisroleSet_A = tbsProjHisroleSet_A;
		this.tbsProjHisroleSet_B = tbsProjHisroleSet_B;
		this.tbsProjcfm0AttendSet = tbsProjcfm0AttendSet;
		this.tbsProjcfm1Set = tbsProjcfm1Set;
		this.tbsProjcfm2Set = tbsProjcfm2Set;
		this.tbsProjchangeRoleSet_AO = tbsProjchangeRoleSet_AO;
		this.tbsProjchangeRoleSet_BO = tbsProjchangeRoleSet_BO;
		this.tbsProjchangeRoleSet_AN = tbsProjchangeRoleSet_AN;
		this.tbsProjchangeRoleSet_BN = tbsProjchangeRoleSet_BN;
		this.tbsProjeaaSet = tbsProjeaaSet;
		this.tbsRiskProjcfmSet = tbsRiskProjcfmSet;
		this.tbsRiskProjcfmAttendSet = tbsRiskProjcfmAttendSet;
		this.tbsApproverSet = tbsApproverSet;
		this.tbsProjcfm1OpinionSet = tbsProjcfm1OpinionSet;
		this.tbsProjcfm2OpinionSet = tbsProjcfm2OpinionSet;
		this.tbsProjOpinionSet = tbsProjOpinionSet;
		this.tbsProjchangeMajcontCfmSet = tbsProjchangeMajcontCfmSet;
		this.tbsProjchangeMajcontCfmattendSet = tbsProjchangeMajcontCfmattendSet;
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

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "USERNAME_", length = 60, unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ADDRESS_", length = 120)
	public String getAddress() {
		return address;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	@Column(name = "ADMINISTRATOR_", nullable = false)
	public boolean isAdministrator() {
		return administrator;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BIRTHDAY_")
	public Date getBirthday() {
		return birthday;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CNAME_", length = 60, nullable = false)
	public String getCname() {
		return cname;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "COMPANY_ID_", length = 60, nullable = false)
	public String getCompanyId() {
		return companyId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE_")
	public Date getCreateDate() {
		return createDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "EMAIL_", length = 60)
	public String getEmail() {
		return email;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "ENABLED_", nullable = false)
	public boolean isEnabled() {
		return enabled;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "ENAME_", length = 60)
	public String getEname() {
		return ename;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	@Column(name = "MALE_", nullable = false)
	public boolean isMale() {
		return male;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "MOBILE_", length = 20)
	public String getMobile() {
		return mobile;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PASSWORD_", length = 70, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "SALT_", length = 10, nullable = false)
	public String getSalt() {
		return salt;
	}

	public void setTbsAdminsignSet(Set<TbsAdminsign> tbsAdminsignSet) {
		this.tbsAdminsignSet = tbsAdminsignSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsAdminsign> getTbsAdminsignSet() {
		return tbsAdminsignSet;
	}

	public void setTbsProjSet_M(Set<TbsProj> tbsProjSet_M) {
		this.tbsProjSet_M = tbsProjSet_M;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User_A")
	public Set<TbsProj> getTbsProjSet_M() {
		return tbsProjSet_M;
	}

	public void setTbsProjSet_S(Set<TbsProj> tbsProjSet_S) {
		this.tbsProjSet_S = tbsProjSet_S;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User_B")
	public Set<TbsProj> getTbsProjSet_S() {
		return tbsProjSet_S;
	}

	public void setTbsProjHisroleSet_A(Set<TbsProjHisrole> tbsProjHisroleSet_A) {
		this.tbsProjHisroleSet_A = tbsProjHisroleSet_A;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User_A")
	public Set<TbsProjHisrole> getTbsProjHisroleSet_A() {
		return tbsProjHisroleSet_A;
	}

	public void setTbsProjHisroleSet_B(Set<TbsProjHisrole> tbsProjHisroleSet_B) {
		this.tbsProjHisroleSet_B = tbsProjHisroleSet_B;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User_B")
	public Set<TbsProjHisrole> getTbsProjHisroleSet_B() {
		return tbsProjHisroleSet_B;
	}

	public void setTbsProjcfm0AttendSet(
			Set<TbsProjcfm0Attend> tbsProjcfm0AttendSet) {
		this.tbsProjcfm0AttendSet = tbsProjcfm0AttendSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsProjcfm0Attend> getTbsProjcfm0AttendSet() {
		return tbsProjcfm0AttendSet;
	}

	public void setTbsProjcfm1Set(Set<TbsProjcfm1> tbsProjcfm1Set) {
		this.tbsProjcfm1Set = tbsProjcfm1Set;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsProjcfm1> getTbsProjcfm1Set() {
		return tbsProjcfm1Set;
	}

	public void setTbsProjcfm2Set(Set<TbsProjcfm2> tbsProjcfm2Set) {
		this.tbsProjcfm2Set = tbsProjcfm2Set;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsProjcfm2> getTbsProjcfm2Set() {
		return tbsProjcfm2Set;
	}

	public void setTbsProjchangeRoleSet_AO(
			Set<TbsProjchangeRole> tbsProjchangeRoleSet_AO) {
		this.tbsProjchangeRoleSet_AO = tbsProjchangeRoleSet_AO;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User_AO")
	public Set<TbsProjchangeRole> getTbsProjchangeRoleSet_AO() {
		return tbsProjchangeRoleSet_AO;
	}

	public void setTbsProjchangeRoleSet_BO(
			Set<TbsProjchangeRole> tbsProjchangeRoleSet_BO) {
		this.tbsProjchangeRoleSet_BO = tbsProjchangeRoleSet_BO;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User_BO")
	public Set<TbsProjchangeRole> getTbsProjchangeRoleSet_BO() {
		return tbsProjchangeRoleSet_BO;
	}

	public void setTbsProjchangeRoleSet_AN(
			Set<TbsProjchangeRole> tbsProjchangeRoleSet_AN) {
		this.tbsProjchangeRoleSet_AN = tbsProjchangeRoleSet_AN;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User_AN")
	public Set<TbsProjchangeRole> getTbsProjchangeRoleSet_AN() {
		return tbsProjchangeRoleSet_AN;
	}

	public void setTbsProjchangeRoleSet_BN(
			Set<TbsProjchangeRole> tbsProjchangeRoleSet_BN) {
		this.tbsProjchangeRoleSet_BN = tbsProjchangeRoleSet_BN;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User_BN")
	public Set<TbsProjchangeRole> getTbsProjchangeRoleSet_BN() {
		return tbsProjchangeRoleSet_BN;
	}

	public void setTbsProjeaaSet(Set<TbsProjeaa> tbsProjeaaSet) {
		this.tbsProjeaaSet = tbsProjeaaSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsProjeaa> getTbsProjeaaSet() {
		return tbsProjeaaSet;
	}

	public void setTbsRiskProjcfmSet(Set<TbsRiskProjcfm> tbsRiskProjcfmSet) {
		this.tbsRiskProjcfmSet = tbsRiskProjcfmSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsRiskProjcfm> getTbsRiskProjcfmSet() {
		return tbsRiskProjcfmSet;
	}

	public void setTbsRiskProjcfmAttendSet(
			Set<TbsRiskProjcfmAttend> tbsRiskProjcfmAttendSet) {
		this.tbsRiskProjcfmAttendSet = tbsRiskProjcfmAttendSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsRiskProjcfmAttend> getTbsRiskProjcfmAttendSet() {
		return tbsRiskProjcfmAttendSet;
	}
	
	public void setTbsApproverSet(
			Set<TbsApprover> tbsApproverSet) {
		this.tbsApproverSet = tbsApproverSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsApprover> getTbsApproverSet() {
		return tbsApproverSet;
	}
	
	public void setTbsProjcfm1OpinionSet(
			Set<TbsProjcfm1Opinion> tbsProjcfm1OpinionSet) {
		this.tbsProjcfm1OpinionSet = tbsProjcfm1OpinionSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsProjcfm1Opinion> getTbsProjcfm1OpinionSet() {
		return tbsProjcfm1OpinionSet;
	}
	
	public void setTbsProjcfm2OpinionSet(
			Set<TbsProjcfm2Opinion> tbsProjcfm2OpinionSet) {
		this.tbsProjcfm2OpinionSet = tbsProjcfm2OpinionSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsProjcfm2Opinion> getTbsProjcfm2OpinionSet() {
		return tbsProjcfm2OpinionSet;
	}
	
	public void setTbsProjOpinionSet(
			Set<TbsProjOpinion> tbsProjOpinionSet) {
		this.tbsProjOpinionSet = tbsProjOpinionSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsProjOpinion> getTbsProjOpinionSet() {
		return tbsProjOpinionSet;
	}
	
	public void setTbsProjchangeMajcontCfmSet(
			Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet) {
		this.tbsProjchangeMajcontCfmSet = tbsProjchangeMajcontCfmSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsProjchangeMajcontCfm> getTbsProjchangeMajcontCfmSet() {
		return tbsProjchangeMajcontCfmSet;
	}
	
	public void setTbsProjchangeMajcontCfmattendSet(
			Set<TbsProjchangeMajcontCfmattend> tbsProjchangeMajcontCfmattendSet) {
		this.tbsProjchangeMajcontCfmattendSet = tbsProjchangeMajcontCfmattendSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2User")
	public Set<TbsProjchangeMajcontCfmattend> getTbsProjchangeMajcontCfmattendSet() {
		return tbsProjchangeMajcontCfmattendSet;
	}

	public String toString() {
		return "Bdf2User [id=" + id + ",username=" + username + ",address="
				+ address + ",administrator=" + administrator + ",birthday="
				+ birthday + ",cname=" + cname + ",companyId=" + companyId
				+ ",createDate=" + createDate + ",email=" + email + ",enabled="
				+ enabled + ",ename=" + ename + ",male=" + male + ",mobile="
				+ mobile + ",password=" + password + ",salt=" + salt
				+ ",tbsAdminsignSet=" + tbsAdminsignSet + ",tbsProjSet_M="
				+ tbsProjSet_M + ",tbsProjSet_S=" + tbsProjSet_S
				+ ",tbsProjHisroleSet_A=" + tbsProjHisroleSet_A
				+ ",tbsProjHisroleSet_B=" + tbsProjHisroleSet_B
				+ ",tbsProjcfm0AttendSet=" + tbsProjcfm0AttendSet
				+ ",tbsProjcfm1Set=" + tbsProjcfm1Set + ",tbsProjcfm2Set="
				+ tbsProjcfm2Set + ",tbsProjchangeRoleSet_AO="
				+ tbsProjchangeRoleSet_AO + ",tbsProjchangeRoleSet_BO="
				+ tbsProjchangeRoleSet_BO + ",tbsProjchangeRoleSet_AN="
				+ tbsProjchangeRoleSet_AN + ",tbsProjchangeRoleSet_BN="
				+ tbsProjchangeRoleSet_BN +  ",tbsProjeaaSet=" + tbsProjeaaSet
				+ ",tbsRiskProjcfmSet=" + tbsRiskProjcfmSet
				+ ",tbsRiskProjcfmAttendSet=" + tbsRiskProjcfmAttendSet 
				+ ",tbsApproverSet=" + tbsApproverSet  
				+ ",tbsProjcfm1OpinionSet=" + tbsProjcfm1OpinionSet 
				+ ",tbsProjcfm2OpinionSet=" + tbsProjcfm2OpinionSet 
				+ ",tbsProjOpinionSet=" + tbsProjOpinionSet 
				+ ",tbsProjchangeMajcontCfmSet=" + tbsProjchangeMajcontCfmSet
				+ ",tbsProjchangeMajcontCfmattendSet=" + tbsProjchangeMajcontCfmattendSet
				+ "]";
	}

}
