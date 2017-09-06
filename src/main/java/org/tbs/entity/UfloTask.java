package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * uflo_task:
 */
@Entity
@Table(name = "uflo_task")
public class UfloTask implements Serializable {

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
	 * COUNTERSIGN_COUNT_:
	 */
	private int countersignCount;

	/**
	 * CREATE_DATE_:
	 */
	private Date createDate;

	/**
	 * DATE_UNIT_:
	 */
	private String dateUnit;

	/**
	 * DUE_ACTION_DATE_:
	 */
	private Date dueActionDate;

	/**
	 * DUEDATE_:
	 */
	private Date duedate;

	/**
	 * OPINION_:
	 */
	private String opinion;

	/**
	 * OWNER_:
	 */
	private String owner;

	/**
	 * PREV_STATE_:
	 */
	private String prevState;

	/**
	 * PREV_TASK_:
	 */
	private String prevTask;

	/**
	 * PROCESS_INSTANCE_ID_:
	 */
	private long processInstanceId;

	/**
	 * PROGRESS_:
	 */
	private int progress;

	/**
	 * ROOT_PROCESS_INSTANCE_ID_:
	 */
	private long rootProcessInstanceId;

	/**
	 * STATE_:
	 */
	private String state;

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

	/**
	 * uflo_task_participator:
	 */
	private Set<UfloTaskParticipator> ufloTaskParticipatorSet = new HashSet<UfloTaskParticipator>(
			0);

	public UfloTask() {
		super();
	}

	public UfloTask(long id, String description, String nodeName,
			long processId, String assignee, String businessId,
			int countersignCount, Date createDate, String dateUnit,
			Date dueActionDate, Date duedate, String opinion, String owner,
			String prevState, String prevTask, long processInstanceId,
			int progress, long rootProcessInstanceId, String state,
			String taskName, String type, String url,
			Set<UfloTaskParticipator> ufloTaskParticipatorSet) {
		super();
		this.id = id;
		this.description = description;
		this.nodeName = nodeName;
		this.processId = processId;
		this.assignee = assignee;
		this.businessId = businessId;
		this.countersignCount = countersignCount;
		this.createDate = createDate;
		this.dateUnit = dateUnit;
		this.dueActionDate = dueActionDate;
		this.duedate = duedate;
		this.opinion = opinion;
		this.owner = owner;
		this.prevState = prevState;
		this.prevTask = prevTask;
		this.processInstanceId = processInstanceId;
		this.progress = progress;
		this.rootProcessInstanceId = rootProcessInstanceId;
		this.state = state;
		this.taskName = taskName;
		this.type = type;
		this.url = url;
		this.ufloTaskParticipatorSet = ufloTaskParticipatorSet;
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

	public void setCountersignCount(int countersignCount) {
		this.countersignCount = countersignCount;
	}

	@Column(name = "COUNTERSIGN_COUNT_")
	public int getCountersignCount() {
		return countersignCount;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE_")
	public Date getCreateDate() {
		return createDate;
	}

	public void setDateUnit(String dateUnit) {
		this.dateUnit = dateUnit;
	}

	@Column(name = "DATE_UNIT_", length = 20)
	public String getDateUnit() {
		return dateUnit;
	}

	public void setDueActionDate(Date dueActionDate) {
		this.dueActionDate = dueActionDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DUE_ACTION_DATE_")
	public Date getDueActionDate() {
		return dueActionDate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DUEDATE_")
	public Date getDuedate() {
		return duedate;
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

	public void setPrevState(String prevState) {
		this.prevState = prevState;
	}

	@Column(name = "PREV_STATE_", length = 20)
	public String getPrevState() {
		return prevState;
	}

	public void setPrevTask(String prevTask) {
		this.prevTask = prevTask;
	}

	@Column(name = "PREV_TASK_", length = 60)
	public String getPrevTask() {
		return prevTask;
	}

	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@Column(name = "PROCESS_INSTANCE_ID_")
	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	@Column(name = "PROGRESS_")
	public int getProgress() {
		return progress;
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

	public void setUfloTaskParticipatorSet(
			Set<UfloTaskParticipator> ufloTaskParticipatorSet) {
		this.ufloTaskParticipatorSet = ufloTaskParticipatorSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ufloTask")
	public Set<UfloTaskParticipator> getUfloTaskParticipatorSet() {
		return ufloTaskParticipatorSet;
	}

	public String toString() {
		return "UfloTask [id=" + id + ",description=" + description
				+ ",nodeName=" + nodeName + ",processId=" + processId
				+ ",assignee=" + assignee + ",businessId=" + businessId
				+ ",countersignCount=" + countersignCount + ",createDate="
				+ createDate + ",dateUnit=" + dateUnit + ",dueActionDate="
				+ dueActionDate + ",duedate=" + duedate + ",opinion=" + opinion
				+ ",owner=" + owner + ",prevState=" + prevState + ",prevTask="
				+ prevTask + ",processInstanceId=" + processInstanceId
				+ ",progress=" + progress + ",rootProcessInstanceId="
				+ rootProcessInstanceId + ",state=" + state + ",taskName="
				+ taskName + ",type=" + type + ",url=" + url
				+ ",ufloTaskParticipatorSet=" + ufloTaskParticipatorSet + "]";
	}

}
