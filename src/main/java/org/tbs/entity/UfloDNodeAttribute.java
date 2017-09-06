package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * uflo_d_node_attribute:
 */
@Entity
@Table(name = "uflo_d_node_attribute")
public class UfloDNodeAttribute implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * AUTHORITY_TYPE_:
	 */
	private String authorityType;

	/**
	 * LABEL_:
	 */
	private String label;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * NODE_ENTRY_ID_:
	 */
	private String nodeEntryId;

	public UfloDNodeAttribute() {
		super();
	}

	public UfloDNodeAttribute(String id, String authorityType, String label,
			String name, String nodeEntryId) {
		super();
		this.id = id;
		this.authorityType = authorityType;
		this.label = label;
		this.name = name;
		this.nodeEntryId = nodeEntryId;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 36, nullable = false)
	public String getId() {
		return id;
	}

	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

	@Column(name = "AUTHORITY_TYPE_", length = 10)
	public String getAuthorityType() {
		return authorityType;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "LABEL_", length = 64)
	public String getLabel() {
		return label;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 64)
	public String getName() {
		return name;
	}

	public void setNodeEntryId(String nodeEntryId) {
		this.nodeEntryId = nodeEntryId;
	}

	@Column(name = "NODE_ENTRY_ID_", length = 64)
	public String getNodeEntryId() {
		return nodeEntryId;
	}

	public String toString() {
		return "UfloDNodeAttribute [id=" + id + ",authorityType="
				+ authorityType + ",label=" + label + ",name=" + name
				+ ",nodeEntryId=" + nodeEntryId + "]";
	}

}
