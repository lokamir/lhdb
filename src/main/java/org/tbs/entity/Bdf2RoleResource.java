package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * bdf2_role_resource:
 */
@Entity
@Table(name = "bdf2_role_resource")
public class Bdf2RoleResource implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * PACKAGE_ID_:
	 */
	private String packageId;

	/**
	 * ROLE_ID_:
	 */
	private String roleId;

	/**
	 * URL_ID_:
	 */
	private String urlId;

	public Bdf2RoleResource() {
		super();
	}

	public Bdf2RoleResource(String id, String packageId, String roleId,
			String urlId) {
		super();
		this.id = id;
		this.packageId = packageId;
		this.roleId = roleId;
		this.urlId = urlId;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 60, nullable = false)
	public String getId() {
		return id;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	@Column(name = "PACKAGE_ID_", length = 60)
	public String getPackageId() {
		return packageId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_ID_", length = 60)
	public String getRoleId() {
		return roleId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	@Column(name = "URL_ID_", length = 60)
	public String getUrlId() {
		return urlId;
	}

	public String toString() {
		return "Bdf2RoleResource [id=" + id + ",packageId=" + packageId
				+ ",roleId=" + roleId + ",urlId=" + urlId + "]";
	}

}
