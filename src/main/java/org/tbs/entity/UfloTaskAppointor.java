package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * uflo_task_appointor:
 */
@Entity
@Table(name = "uflo_task_appointor")
public class UfloTaskAppointor implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private long id;

	/**
	 * APPOINTOR_:
	 */
	private String appointor;

	/**
	 * APPOINTOR_NODE_:
	 */
	private String appointorNode;

	/**
	 * OWNER_:
	 */
	private String owner;

	/**
	 * PROCESS_INSTANCE_ID_:
	 */
	private long processInstanceId;

	/**
	 * TASK_NODE_NAME_:
	 */
	private String taskNodeName;

	public UfloTaskAppointor() {
		super();
	}

	public UfloTaskAppointor(long id, String appointor, String appointorNode,
			String owner, long processInstanceId, String taskNodeName) {
		super();
		this.id = id;
		this.appointor = appointor;
		this.appointorNode = appointorNode;
		this.owner = owner;
		this.processInstanceId = processInstanceId;
		this.taskNodeName = taskNodeName;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", nullable = false)
	public long getId() {
		return id;
	}

	public void setAppointor(String appointor) {
		this.appointor = appointor;
	}

	@Column(name = "APPOINTOR_", length = 60)
	public String getAppointor() {
		return appointor;
	}

	public void setAppointorNode(String appointorNode) {
		this.appointorNode = appointorNode;
	}

	@Column(name = "APPOINTOR_NODE_", length = 60)
	public String getAppointorNode() {
		return appointorNode;
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

	public void setTaskNodeName(String taskNodeName) {
		this.taskNodeName = taskNodeName;
	}

	@Column(name = "TASK_NODE_NAME_", length = 60)
	public String getTaskNodeName() {
		return taskNodeName;
	}

	public String toString() {
		return "UfloTaskAppointor [id=" + id + ",appointor=" + appointor
				+ ",appointorNode=" + appointorNode + ",owner=" + owner
				+ ",processInstanceId=" + processInstanceId + ",taskNodeName="
				+ taskNodeName + "]";
	}

}
