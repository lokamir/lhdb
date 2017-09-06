package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * uflo_d_process_entry_assignee:
 */
@Entity
@Table(name = "uflo_d_process_entry_assignee")
public class UfloDProcessEntryAssignee implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * ASSIGNEE_ID_:
	 */
	private String assigneeId;

	/**
	 * ASSIGNEE_NAME_ID_:
	 */
	private String assigneeNameId;

	/**
	 * ASSIGNEE_PROVIDER_ID_:
	 */
	private String assigneeProviderId;

	/**
	 * PROCESS_ENTRY_ID_:
	 */
	private String processEntryId;

	public UfloDProcessEntryAssignee() {
		super();
	}

	public UfloDProcessEntryAssignee(String id, String assigneeId,
			String assigneeNameId, String assigneeProviderId,
			String processEntryId) {
		super();
		this.id = id;
		this.assigneeId = assigneeId;
		this.assigneeNameId = assigneeNameId;
		this.assigneeProviderId = assigneeProviderId;
		this.processEntryId = processEntryId;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 36, nullable = false)
	public String getId() {
		return id;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

	@Column(name = "ASSIGNEE_ID_", length = 36)
	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeNameId(String assigneeNameId) {
		this.assigneeNameId = assigneeNameId;
	}

	@Column(name = "ASSIGNEE_NAME_ID_", length = 100)
	public String getAssigneeNameId() {
		return assigneeNameId;
	}

	public void setAssigneeProviderId(String assigneeProviderId) {
		this.assigneeProviderId = assigneeProviderId;
	}

	@Column(name = "ASSIGNEE_PROVIDER_ID_", length = 100)
	public String getAssigneeProviderId() {
		return assigneeProviderId;
	}

	public void setProcessEntryId(String processEntryId) {
		this.processEntryId = processEntryId;
	}

	@Column(name = "PROCESS_ENTRY_ID_", length = 36)
	public String getProcessEntryId() {
		return processEntryId;
	}

	public String toString() {
		return "UfloDProcessEntryAssignee [id=" + id + ",assigneeId="
				+ assigneeId + ",assigneeNameId=" + assigneeNameId
				+ ",assigneeProviderId=" + assigneeProviderId
				+ ",processEntryId=" + processEntryId + "]";
	}

}
