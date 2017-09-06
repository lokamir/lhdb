package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * bdf2_role:
 */
@Entity
@Table(name = "bdf2_role")
public class Bdf2Role implements Serializable {

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
	 * CREATE_USER_:
	 */
	private String createUser;

	/**
	 * DESC_:
	 */
	private String desc;

	/**
	 * ENABLED_:
	 */
	private boolean enabled;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * PARENT_ID_:
	 */
	private String parentId;

	/**
	 * TYPE_:
	 */
	private String type;

	public Bdf2Role() {
		super();
	}

	public Bdf2Role(String id, String companyId, Date createDate,
			String createUser, String desc, boolean enabled, String name,
			String parentId, String type) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.createDate = createDate;
		this.createUser = createUser;
		this.desc = desc;
		this.enabled = enabled;
		this.name = name;
		this.parentId = parentId;
		this.type = type;
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

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "CREATE_USER_", length = 60)
	public String getCreateUser() {
		return createUser;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "DESC_", length = 120)
	public String getDesc() {
		return desc;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "ENABLED_")
	public boolean isEnabled() {
		return enabled;
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

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE_", length = 10)
	public String getType() {
		return type;
	}

	public String toString() {
		return "Bdf2Role [id=" + id + ",companyId=" + companyId
				+ ",createDate=" + createDate + ",createUser=" + createUser
				+ ",desc=" + desc + ",enabled=" + enabled + ",name=" + name
				+ ",parentId=" + parentId + ",type=" + type + "]";
	}

}
