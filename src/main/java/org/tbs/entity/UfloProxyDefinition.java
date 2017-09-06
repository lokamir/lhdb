package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * uflo_proxy_definition:
 */
@Entity
@Table(name = "uflo_proxy_definition")
public class UfloProxyDefinition implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * END_:
	 */
	private Date end;

	/**
	 * IS_PROXY_ALL_:
	 */
	private boolean isProxyAll;

	/**
	 * ISSUE_USER_:
	 */
	private String issueUser;

	/**
	 * ISSUE_USER_CNAME_:
	 */
	private String issueUserCname;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * START_:
	 */
	private Date start;

	public UfloProxyDefinition() {
		super();
	}

	public UfloProxyDefinition(String id, Date end, boolean isProxyAll,
			String issueUser, String issueUserCname, String name, Date start) {
		super();
		this.id = id;
		this.end = end;
		this.isProxyAll = isProxyAll;
		this.issueUser = issueUser;
		this.issueUserCname = issueUserCname;
		this.name = name;
		this.start = start;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 60, nullable = false)
	public String getId() {
		return id;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_")
	public Date getEnd() {
		return end;
	}

	public void setIsProxyAll(boolean isProxyAll) {
		this.isProxyAll = isProxyAll;
	}

	@Column(name = "IS_PROXY_ALL_")
	public boolean isIsProxyAll() {
		return isProxyAll;
	}

	public void setIssueUser(String issueUser) {
		this.issueUser = issueUser;
	}

	@Column(name = "ISSUE_USER_", length = 255)
	public String getIssueUser() {
		return issueUser;
	}

	public void setIssueUserCname(String issueUserCname) {
		this.issueUserCname = issueUserCname;
	}

	@Column(name = "ISSUE_USER_CNAME_", length = 255)
	public String getIssueUserCname() {
		return issueUserCname;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 255)
	public String getName() {
		return name;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_")
	public Date getStart() {
		return start;
	}

	public String toString() {
		return "UfloProxyDefinition [id=" + id + ",end=" + end + ",isProxyAll="
				+ isProxyAll + ",issueUser=" + issueUser + ",issueUserCname="
				+ issueUserCname + ",name=" + name + ",start=" + start + "]";
	}

}
