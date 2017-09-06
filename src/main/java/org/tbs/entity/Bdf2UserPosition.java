package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * bdf2_user_position:
 */
@Entity
@Table(name = "bdf2_user_position")
public class Bdf2UserPosition implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * POSITION_ID_:
	 */
	private String positionId;

	/**
	 * USERNAME_:
	 */
	private String username;

	public Bdf2UserPosition() {
		super();
	}

	public Bdf2UserPosition(String id, String positionId, String username) {
		super();
		this.id = id;
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

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	@Column(name = "POSITION_ID_", length = 60, nullable = false)
	public String getPositionId() {
		return positionId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "USERNAME_", length = 60, nullable = false)
	public String getUsername() {
		return username;
	}

	public String toString() {
		return "Bdf2UserPosition [id=" + id + ",positionId=" + positionId
				+ ",username=" + username + "]";
	}

}
