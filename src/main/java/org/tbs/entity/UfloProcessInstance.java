package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * uflo_process_instance:
 */
@Entity
@Table(name = "uflo_process_instance")
public class UfloProcessInstance implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private long id;

	/**
	 * BUSINESS_ID_:
	 */
	private String businessId;

	/**
	 * CREATE_DATE_:
	 */
	private Date createDate;

	/**
	 * CURRENT_NODE_:
	 */
	private String currentNode;

	/**
	 * CURRENT_TASK_:
	 */
	private String currentTask;

	/**
	 * HIS_PROCESS_INSTANCE_ID_:
	 */
	private long hisProcessInstanceId;

	/**
	 * PARALLEL_INSTANCE_COUNT_:
	 */
	private int parallelInstanceCount;

	/**
	 * PARENT_ID_:
	 */
	private long parentId;

	/**
	 * PROCESS_ID_:
	 */
	private long processId;

	/**
	 * PROMOTER_:
	 */
	private String promoter;

	/**
	 * ROOT_ID_:
	 */
	private long rootId;

	/**
	 * STATE_:
	 */
	private String state;

	/**
	 * TAG_:
	 */
	private String tag;

	public UfloProcessInstance() {
		super();
	}

	public UfloProcessInstance(long id, String businessId, Date createDate,
			String currentNode, String currentTask, long hisProcessInstanceId,
			int parallelInstanceCount, long parentId, long processId,
			String promoter, long rootId, String state, String tag) {
		super();
		this.id = id;
		this.businessId = businessId;
		this.createDate = createDate;
		this.currentNode = currentNode;
		this.currentTask = currentTask;
		this.hisProcessInstanceId = hisProcessInstanceId;
		this.parallelInstanceCount = parallelInstanceCount;
		this.parentId = parentId;
		this.processId = processId;
		this.promoter = promoter;
		this.rootId = rootId;
		this.state = state;
		this.tag = tag;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", nullable = false)
	public long getId() {
		return id;
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

	public void setCurrentNode(String currentNode) {
		this.currentNode = currentNode;
	}

	@Column(name = "CURRENT_NODE_", length = 60)
	public String getCurrentNode() {
		return currentNode;
	}

	public void setCurrentTask(String currentTask) {
		this.currentTask = currentTask;
	}

	@Column(name = "CURRENT_TASK_", length = 60)
	public String getCurrentTask() {
		return currentTask;
	}

	public void setHisProcessInstanceId(long hisProcessInstanceId) {
		this.hisProcessInstanceId = hisProcessInstanceId;
	}

	@Column(name = "HIS_PROCESS_INSTANCE_ID_")
	public long getHisProcessInstanceId() {
		return hisProcessInstanceId;
	}

	public void setParallelInstanceCount(int parallelInstanceCount) {
		this.parallelInstanceCount = parallelInstanceCount;
	}

	@Column(name = "PARALLEL_INSTANCE_COUNT_")
	public int getParallelInstanceCount() {
		return parallelInstanceCount;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "PARENT_ID_")
	public long getParentId() {
		return parentId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	@Column(name = "PROCESS_ID_")
	public long getProcessId() {
		return processId;
	}

	public void setPromoter(String promoter) {
		this.promoter = promoter;
	}

	@Column(name = "PROMOTER_", length = 60)
	public String getPromoter() {
		return promoter;
	}

	public void setRootId(long rootId) {
		this.rootId = rootId;
	}

	@Column(name = "ROOT_ID_")
	public long getRootId() {
		return rootId;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "STATE_", length = 20)
	public String getState() {
		return state;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "TAG_", length = 30)
	public String getTag() {
		return tag;
	}

	public String toString() {
		return "UfloProcessInstance [id=" + id + ",businessId=" + businessId
				+ ",createDate=" + createDate + ",currentNode=" + currentNode
				+ ",currentTask=" + currentTask + ",hisProcessInstanceId="
				+ hisProcessInstanceId + ",parallelInstanceCount="
				+ parallelInstanceCount + ",parentId=" + parentId
				+ ",processId=" + processId + ",promoter=" + promoter
				+ ",rootId=" + rootId + ",state=" + state + ",tag=" + tag + "]";
	}

}
