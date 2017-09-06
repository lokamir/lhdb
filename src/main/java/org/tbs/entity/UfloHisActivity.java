package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * uflo_his_activity:
 */
@Entity
@Table(name = "uflo_his_activity")
public class UfloHisActivity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private long id;

	/**
	 * DESCRIPTION_:
	 */
	private String description;

	/**
	 * NODE_NAME_:
	 */
	private String nodeName;

	/**
	 * PROCESS_ID_:
	 */
	private long processId;

	/**
	 * CREATE_DATE_:
	 */
	private Date createDate;

	/**
	 * END_DATE_:
	 */
	private Date endDate;

	/**
	 * HIS_PROCESS_INSTANCE_ID_:
	 */
	private long hisProcessInstanceId;

	/**
	 * LEAVE_FLOW_NAME_:
	 */
	private String leaveFlowName;

	/**
	 * PROCESS_INSTANCE_ID_:
	 */
	private long processInstanceId;

	/**
	 * ROOT_PROCESS_INSTANCE_ID_:
	 */
	private long rootProcessInstanceId;

	public UfloHisActivity() {
		super();
	}

	public UfloHisActivity(long id, String description, String nodeName,
			long processId, Date createDate, Date endDate,
			long hisProcessInstanceId, String leaveFlowName,
			long processInstanceId, long rootProcessInstanceId) {
		super();
		this.id = id;
		this.description = description;
		this.nodeName = nodeName;
		this.processId = processId;
		this.createDate = createDate;
		this.endDate = endDate;
		this.hisProcessInstanceId = hisProcessInstanceId;
		this.leaveFlowName = leaveFlowName;
		this.processInstanceId = processInstanceId;
		this.rootProcessInstanceId = rootProcessInstanceId;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", nullable = false)
	public long getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "DESCRIPTION_", length = 120)
	public String getDescription() {
		return description;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@Column(name = "NODE_NAME_", length = 60)
	public String getNodeName() {
		return nodeName;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	@Column(name = "PROCESS_ID_")
	public long getProcessId() {
		return processId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE_")
	public Date getCreateDate() {
		return createDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE_")
	public Date getEndDate() {
		return endDate;
	}

	public void setHisProcessInstanceId(long hisProcessInstanceId) {
		this.hisProcessInstanceId = hisProcessInstanceId;
	}

	@Column(name = "HIS_PROCESS_INSTANCE_ID_")
	public long getHisProcessInstanceId() {
		return hisProcessInstanceId;
	}

	public void setLeaveFlowName(String leaveFlowName) {
		this.leaveFlowName = leaveFlowName;
	}

	@Column(name = "LEAVE_FLOW_NAME_", length = 60)
	public String getLeaveFlowName() {
		return leaveFlowName;
	}

	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@Column(name = "PROCESS_INSTANCE_ID_")
	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setRootProcessInstanceId(long rootProcessInstanceId) {
		this.rootProcessInstanceId = rootProcessInstanceId;
	}

	@Column(name = "ROOT_PROCESS_INSTANCE_ID_")
	public long getRootProcessInstanceId() {
		return rootProcessInstanceId;
	}

	public String toString() {
		return "UfloHisActivity [id=" + id + ",description=" + description
				+ ",nodeName=" + nodeName + ",processId=" + processId
				+ ",createDate=" + createDate + ",endDate=" + endDate
				+ ",hisProcessInstanceId=" + hisProcessInstanceId
				+ ",leaveFlowName=" + leaveFlowName + ",processInstanceId="
				+ processInstanceId + ",rootProcessInstanceId="
				+ rootProcessInstanceId + "]";
	}

}
