package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * bdf2_company:
 */
@Entity
@Table(name = "bdf2_company")
public class Bdf2Company implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * DESC_:
	 */
	private String desc;

	/**
	 * NAME_:
	 */
	private String name;

	public Bdf2Company() {
		super();
	}

	public Bdf2Company(String id, String desc, String name) {
		super();
		this.id = id;
		this.desc = desc;
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 60, nullable = false)
	public String getId() {
		return id;
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

	public String toString() {
		return "Bdf2Company [id=" + id + ",desc=" + desc + ",name=" + name
				+ "]";
	}

}
