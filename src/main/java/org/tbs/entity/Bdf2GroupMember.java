package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * bdf2_group_member:
 */
@Entity
@Table(name = "bdf2_group_member")
public class Bdf2GroupMember implements Serializable {

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
	 * GROUP_ID_:
	 */
	private String groupId;

	/**
	 * POSITION_ID_:
	 */
	private String positionId;

	/**
	 * USERNAME_:
	 */
	private String username;

	public Bdf2GroupMember() {
		super();
	}

	public Bdf2GroupMember(String id, String deptId, String groupId,
			String positionId, String username) {
		super();
		this.id = id;
		this.deptId = deptId;
		this.groupId = groupId;
		this.positionId = positionId;
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

	@Column(name = "DEPT_ID_", length = 60)
	public String getDeptId() {
		return deptId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "GROUP_ID_", length = 60)
	public String getGroupId() {
		return groupId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	@Column(name = "POSITION_ID_", length = 60)
	public String getPositionId() {
		return positionId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "USERNAME_", length = 60)
	public String getUsername() {
		return username;
	}

	public String toString() {
		return "Bdf2GroupMember [id=" + id + ",deptId=" + deptId + ",groupId="
				+ groupId + ",positionId=" + positionId + ",username="
				+ username + "]";
	}

}
