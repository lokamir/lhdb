package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * uflo_d_process_entry:
 */
@Entity
@Table(name = "uflo_d_process_entry")
public class UfloDProcessEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * ADDABLE_:
	 */
	private boolean addable;

	/**
	 * CREATE_DATE_:
	 */
	private Date createDate;

	/**
	 * MODIFIABLE_:
	 */
	private boolean modifiable;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * PROCESS_ID_:
	 */
	private long processId;

	public UfloDProcessEntry() {
		super();
	}

	public UfloDProcessEntry(String id, boolean addable, Date createDate,
			boolean modifiable, String name, long processId) {
		super();
		this.id = id;
		this.addable = addable;
		this.createDate = createDate;
		this.modifiable = modifiable;
		this.name = name;
		this.processId = processId;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 36, nullable = false)
	public String getId() {
		return id;
	}

	public void setAddable(boolean addable) {
		this.addable = addable;
	}

	@Column(name = "ADDABLE_")
	public boolean isAddable() {
		return addable;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE_")
	public Date getCreateDate() {
		return createDate;
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

	@Column(name = "NAME_", length = 36)
	public String getName() {
		return name;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	@Column(name = "PROCESS_ID_")
	public long getProcessId() {
		return processId;
	}

	public String toString() {
		return "UfloDProcessEntry [id=" + id + ",addable=" + addable
				+ ",createDate=" + createDate + ",modifiable=" + modifiable
				+ ",name=" + name + ",processId=" + processId + "]";
	}

}
