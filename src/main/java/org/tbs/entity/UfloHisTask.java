package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * uflo_his_task:
 */
@Entity
@Table(name = "uflo_his_task")
public class UfloHisTask implements Serializable {

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
	 * ASSIGNEE_:
	 */
	private String assignee;

	/**
	 * BUSINESS_ID_:
	 */
	private String businessId;

	/**
	 * CREATE_DATE_:
	 */
	private Date createDate;

	/**
	 * DUEDATE_:
	 */
	private Date duedate;

	/**
	 * END_DATE_:
	 */
	private Date endDate;

	/**
	 * HIS_PROCESS_INSTANCE_ID_:
	 */
	private long hisProcessInstanceId;

	/**
	 * OPINION_:
	 */
	private String opinion;

	/**
	 * OWNER_:
	 */
	private String owner;

	/**
	 * PROCESS_INSTANCE_ID_:
	 */
	private long processInstanceId;

	/**
	 * ROOT_PROCESS_INSTANCE_ID_:
	 */
	private long rootProcessInstanceId;

	/**
	 * STATE_:
	 */
	private String state;

	/**
	 * TASK_ID_:
	 */
	private long taskId;

	/**
	 * TASK_NAME_:
	 */
	private String taskName;

	/**
	 * TYPE_:
	 */
	private String type;

	/**
	 * URL_:
	 */
	private String url;

	public UfloHisTask() {
		super();
	}

	public UfloHisTask(long id, String description, String nodeName,
			long processId, String assignee, String businessId,
			Date createDate, Date duedate, Date endDate,
			long hisProcessInstanceId, String opinion, String owner,
			long processInstanceId, long rootProcessInstanceId, String state,
			long taskId, String taskName, String type, String url) {
		super();
		this.id = id;
		this.description = description;
		this.nodeName = nodeName;
		this.processId = processId;
		this.assignee = assignee;
		this.businessId = businessId;
		this.createDate = createDate;
		this.duedate = duedate;
		this.endDate = endDate;
		this.hisProcessInstanceId = hisProcessInstanceId;
		this.opinion = opinion;
		this.owner = owner;
		this.processInstanceId = processInstanceId;
		this.rootProcessInstanceId = rootProcessInstanceId;
		this.state = state;
		this.taskId = taskId;
		this.taskName = taskName;
		this.type = type;
		this.url = url;
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

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	@Column(name = "ASSIGNEE_", length = 60)
	public String getAssignee() {
		return assignee;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Column(name = "BUSINESS_ID_", length = 60)
	public String getBusinessId() {
		return businessId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE_")
	public Date getCreateDate() {
		return createDate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DUEDATE_")
	public Date getDuedate() {
		return duedate;
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

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Column(name = "OPINION_", length = 200)
	public String getOpinion() {
		return opinion;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "OWNER_", length = 60)
	public String getOwner() {
		return owner;
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

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "STATE_", length = 20)
	public String getState() {
		return state;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	@Column(name = "TASK_ID_")
	public long getTaskId() {
		return taskId;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Column(name = "TASK_NAME_", length = 60)
	public String getTaskName() {
		return taskName;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE_", length = 20)
	public String getType() {
		return type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "URL_", length = 120)
	public String getUrl() {
		return url;
	}

	public String toString() {
		return "UfloHisTask [id=" + id + ",description=" + description
				+ ",nodeName=" + nodeName + ",processId=" + processId
				+ ",assignee=" + assignee + ",businessId=" + businessId
				+ ",createDate=" + createDate + ",duedate=" + duedate
				+ ",endDate=" + endDate + ",hisProcessInstanceId="
				+ hisProcessInstanceId + ",opinion=" + opinion + ",owner="
				+ owner + ",processInstanceId=" + processInstanceId
				+ ",rootProcessInstanceId=" + rootProcessInstanceId + ",state="
				+ state + ",taskId=" + taskId + ",taskName=" + taskName
				+ ",type=" + type + ",url=" + url + "]";
	}

}
