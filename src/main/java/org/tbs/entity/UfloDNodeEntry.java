package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * uflo_d_node_entry:
 */
@Entity
@Table(name = "uflo_d_node_entry")
public class UfloDNodeEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * MODIFIABLE_:
	 */
	private boolean modifiable;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * NODE_TYPE_:
	 */
	private String nodeType;

	/**
	 * PROCESS_ENTRY_ID_:
	 */
	private String processEntryId;

	/**
	 * REMOVABLE_:
	 */
	private boolean removable;

	public UfloDNodeEntry() {
		super();
	}

	public UfloDNodeEntry(String id, boolean modifiable, String name,
			String nodeType, String processEntryId, boolean removable) {
		super();
		this.id = id;
		this.modifiable = modifiable;
		this.name = name;
		this.nodeType = nodeType;
		this.processEntryId = processEntryId;
		this.removable = removable;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 36, nullable = false)
	public String getId() {
		return id;
	}

	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}

	@Column(name = "MODIFIABLE_")
	public boolean isModifiable() {
		return modifiable;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 36, unique = true)
	public String getName() {
		return name;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	@Column(name = "NODE_TYPE_", length = 64)
	public String getNodeType() {
		return nodeType;
	}

	public void setProcessEntryId(String processEntryId) {
		this.processEntryId = processEntryId;
	}

	@Column(name = "PROCESS_ENTRY_ID_", length = 36)
	public String getProcessEntryId() {
		return processEntryId;
	}

	public void setRemovable(boolean removable) {
		this.removable = removable;
	}

	@Column(name = "REMOVABLE_")
	public boolean isRemovable() {
		return removable;
	}

	public String toString() {
		return "UfloDNodeEntry [id=" + id + ",modifiable=" + modifiable
				+ ",name=" + name + ",nodeType=" + nodeType
				+ ",processEntryId=" + processEntryId + ",removable="
				+ removable + "]";
	}

}
