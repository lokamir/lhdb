package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * bdf2_group:
 */
@Entity
@Table(name = "bdf2_group")
public class Bdf2Group implements Serializable {

	private static final long serialVersionUID = 1L;

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
	 * bdf2_role_member:
	 */
	private Set<Bdf2RoleMember> bdf2RoleMemberSet = new HashSet<Bdf2RoleMember>(
			0);

	public Bdf2Group() {
		super();
	}

	public Bdf2Group(String id, String companyId, Date createDate, String desc,
			String name, String parentId, Set<Bdf2RoleMember> bdf2RoleMemberSet) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.createDate = createDate;
		this.desc = desc;
		this.name = name;
		this.parentId = parentId;
		this.bdf2RoleMemberSet = bdf2RoleMemberSet;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 60, nullable = false)
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

	public void setBdf2RoleMemberSet(Set<Bdf2RoleMember> bdf2RoleMemberSet) {
		this.bdf2RoleMemberSet = bdf2RoleMemberSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2Group")
	public Set<Bdf2RoleMember> getBdf2RoleMemberSet() {
		return bdf2RoleMemberSet;
	}

	public String toString() {
		return "Bdf2Group [id=" + id + ",companyId=" + companyId
				+ ",createDate=" + createDate + ",desc=" + desc + ",name="
				+ name + ",parentId=" + parentId + ",bdf2RoleMemberSet="
				+ bdf2RoleMemberSet + "]";
	}

}
