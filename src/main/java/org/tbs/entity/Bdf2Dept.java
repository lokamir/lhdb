package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * bdf2_dept:
 */
@Entity
@Table(name = "bdf2_dept")
public class Bdf2Dept implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * DID:
	 */
	private int did;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * COMPANY_ID_:
	 */
	private String companyId;

	/**
	 * CREATE_DATE_:
	 */
	private Date createDate;

	/**
	 * DESC_:
	 */
	private String desc;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * PARENT_ID_:
	 */
	private String parentId;

	/**
	 * 删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据:删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据
	 */
	private boolean del;

	/**
	 * tbs_projcfm1:会议决议录入表。表单数据回插入tbs_proj和tbs_proj_hisloc
	 */
	private Set<TbsProjcfm1> tbsProjcfm1Set = new HashSet<TbsProjcfm1>(0);

	/**
	 * tbs_projcfm2:签批决议录入表。表单数据回插入tbs_proj和tbs_proj_hisloc
	 */
	private Set<TbsProjcfm2> tbsProjcfm2Set = new HashSet<TbsProjcfm2>(0);

	/**
	 * tbs_projeaa:立项审批表。审批通过后，变化的数据回插入tbs_proj
	 */
	private Set<TbsProjeaa> tbsProjeaaSet = new HashSet<TbsProjeaa>(0);

	
	private Set<TbsRiskProjcfm> tbsRiskProjcfmSet = new HashSet<TbsRiskProjcfm>(0);
	private Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet = new HashSet<TbsProjchangeMajcontCfm>(0);
	
	
	public Bdf2Dept() {
		super();
	}

	public Bdf2Dept(int did, String id, String companyId, Date createDate,
			String desc, String name, String parentId, boolean del,
			Set<TbsProjcfm1> tbsProjcfm1Set, Set<TbsProjcfm2> tbsProjcfm2Set,
			Set<TbsProjeaa> tbsProjeaaSet, Set<TbsRiskProjcfm> tbsRiskProjcfmSet,
			Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet) {
		super();
		this.did = did;
		this.id = id;
		this.companyId = companyId;
		this.createDate = createDate;
		this.desc = desc;
		this.name = name;
		this.parentId = parentId;
		this.del = del;
		this.tbsProjcfm1Set = tbsProjcfm1Set;
		this.tbsProjcfm2Set = tbsProjcfm2Set;
		this.tbsProjeaaSet = tbsProjeaaSet;
		this.tbsRiskProjcfmSet = tbsRiskProjcfmSet;
		this.tbsProjchangeMajcontCfmSet = tbsProjchangeMajcontCfmSet;
	}

	public void setDid(int did) {
		this.did = did;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DID", nullable = false)
	public int getDid() {
		return did;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ID_", length = 60, unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "COMPANY_ID_", length = 60)
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

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "DESC_", length = 120)
	public String getDesc() {
		return desc;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 60)
	public String getName() {
		return name;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "PARENT_ID_", length = 60)
	public String getParentId() {
		return parentId;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}

	public void setTbsProjcfm1Set(Set<TbsProjcfm1> tbsProjcfm1Set) {
		this.tbsProjcfm1Set = tbsProjcfm1Set;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2Dept")
	public Set<TbsProjcfm1> getTbsProjcfm1Set() {
		return tbsProjcfm1Set;
	}

	public void setTbsProjcfm2Set(Set<TbsProjcfm2> tbsProjcfm2Set) {
		this.tbsProjcfm2Set = tbsProjcfm2Set;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2Dept")
	public Set<TbsProjcfm2> getTbsProjcfm2Set() {
		return tbsProjcfm2Set;
	}

	public void setTbsProjeaaSet(Set<TbsProjeaa> tbsProjeaaSet) {
		this.tbsProjeaaSet = tbsProjeaaSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2Dept")
	public Set<TbsProjeaa> getTbsProjeaaSet() {
		return tbsProjeaaSet;
	}

	public void setTbsRiskProjcfmSet(Set<TbsRiskProjcfm> tbsRiskProjcfmSet) {
		this.tbsRiskProjcfmSet = tbsRiskProjcfmSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2Dept")
	public Set<TbsRiskProjcfm> getTbsRiskProjcfmSet() {
		return tbsRiskProjcfmSet;
	}

	public void setTbsProjchangeMajcontCfmSet(
			Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet) {
		this.tbsProjchangeMajcontCfmSet = tbsProjchangeMajcontCfmSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2Dept")
	public Set<TbsProjchangeMajcontCfm> getTbsProjchangeMajcontCfmSet() {
		return tbsProjchangeMajcontCfmSet;
	}
	
	public String toString() {
		return "Bdf2Dept [did=" + did + ",id=" + id + ",companyId=" + companyId
				+ ",createDate=" + createDate + ",desc=" + desc + ",name="
				+ name + ",parentId=" + parentId + ",del=" + del
				+ ",tbsProjcfm1Set=" + tbsProjcfm1Set + ",tbsProjcfm2Set=" + tbsProjcfm2Set 
				+ ",tbsProjeaaSet=" + tbsProjeaaSet 
				+ ",tbsRiskProjcfmSet=" + tbsRiskProjcfmSet 
				+ ",tbsProjchangeMajcontCfmSet=" + tbsProjchangeMajcontCfmSet 
				+ "]";
	}

}
