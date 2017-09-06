package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * bdf2_message_template:
 */
@Entity
@Table(name = "bdf2_message_template")
public class Bdf2MessageTemplate implements Serializable {

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
	 * CONTENT_:
	 */
	private String content;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * TYPE_:
	 */
	private String type;

	public Bdf2MessageTemplate() {
		super();
	}

	public Bdf2MessageTemplate(String id, String companyId, String content,
			String name, String type) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.content = content;
		this.name = name;
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

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CONTENT_", length = 1000)
	public String getContent() {
		return content;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 60)
	public String getName() {
		return name;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE_", length = 60)
	public String getType() {
		return type;
	}

	public String toString() {
		return "Bdf2MessageTemplate [id=" + id + ",companyId=" + companyId
				+ ",content=" + content + ",name=" + name + ",type=" + type
				+ "]";
	}

}
