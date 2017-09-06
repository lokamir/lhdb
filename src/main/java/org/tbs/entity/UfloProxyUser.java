package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * uflo_proxy_user:
 */
@Entity
@Table(name = "uflo_proxy_user")
public class UfloProxyUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * PROXY_DEF_ID_:
	 */
	private String proxyDefId;

	/**
	 * PROXY_USER_:
	 */
	private String proxyUser;

	/**
	 * PROXY_USER_CNAME_:
	 */
	private String proxyUserCname;

	public UfloProxyUser() {
		super();
	}

	public UfloProxyUser(String id, String proxyDefId, String proxyUser,
			String proxyUserCname) {
		super();
		this.id = id;
		this.proxyDefId = proxyDefId;
		this.proxyUser = proxyUser;
		this.proxyUserCname = proxyUserCname;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 60, nullable = false)
	public String getId() {
		return id;
	}

	public void setProxyDefId(String proxyDefId) {
		this.proxyDefId = proxyDefId;
	}

	@Column(name = "PROXY_DEF_ID_", length = 255)
	public String getProxyDefId() {
		return proxyDefId;
	}

	public void setProxyUser(String proxyUser) {
		this.proxyUser = proxyUser;
	}

	@Column(name = "PROXY_USER_", length = 255)
	public String getProxyUser() {
		return proxyUser;
	}

	public void setProxyUserCname(String proxyUserCname) {
		this.proxyUserCname = proxyUserCname;
	}

	@Column(name = "PROXY_USER_CNAME_", length = 255)
	public String getProxyUserCname() {
		return proxyUserCname;
	}

	public String toString() {
		return "UfloProxyUser [id=" + id + ",proxyDefId=" + proxyDefId
				+ ",proxyUser=" + proxyUser + ",proxyUserCname="
				+ proxyUserCname + "]";
	}

}
