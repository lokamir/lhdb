package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * bdf2_user_dept:
 */
@Entity
@Table(name = "bdf2_user_dept")
public class Bdf2UserDept implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * DEPT_ID_:
	 */
	private String deptId;

	/**
	 * USERNAME_:
	 */
	private String username;

	public Bdf2UserDept() {
		super();
	}

	public Bdf2UserDept(String id, String deptId, String username) {
		super();
		this.id = id;
		this.deptId = deptId;
		this.username = username;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 60, nullable = false)
	public String getId() {
		return id;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "DEPT_ID_", length = 60, nullable = false)
	public String getDeptId() {
		return deptId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "USERNAME_", length = 60, nullable = false)
	public String getUsername() {
		return username;
	}

	public String toString() {
		return "Bdf2UserDept [id=" + id + ",deptId=" + deptId + ",username="
				+ username + "]";
	}

}
