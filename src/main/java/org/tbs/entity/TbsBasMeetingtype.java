package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * tbs_bas_Meetingtype:会议类型
 */
@Entity
@Table(name = "tbs_bas_meetingtype")
public class TbsBasMeetingtype implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * ID:
	 */
	private int id;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * 默认为1，生效:默认为1，生效
	 */
	private boolean valid;

	
	public TbsBasMeetingtype() {
		super();
	}

	public TbsBasMeetingtype(int id, String name, boolean valid) {
		super();
		this.id = id;
		this.name = name;
		this.valid = valid;
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

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 45)
	public String getName() {
		return name;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
	}


	public String toString() {
		return "TbsBasArea [id=" + id + ",name=" + name + ",valid=" + valid + "]";
	}

}
