package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * uflo_task_reminder:
 */
@Entity
@Table(name = "uflo_task_reminder")
public class UfloTaskReminder implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private long id;

	/**
	 * CRON_:
	 */
	private String cron;

	/**
	 * PROCESS_ID_:
	 */
	private long processId;

	/**
	 * REMINDER_HANDLER_BEAN_:
	 */
	private String reminderHandlerBean;

	/**
	 * START_DATE_:
	 */
	private Date startDate;

	/**
	 * TASK_ID_:
	 */
	private long taskId;

	/**
	 * TASK_NODE_NAME_:
	 */
	private String taskNodeName;

	/**
	 * REMINDER_TYPE_:
	 */
	private String reminderType;

	public UfloTaskReminder() {
		super();
	}

	public UfloTaskReminder(long id, String cron, long processId,
			String reminderHandlerBean, Date startDate, long taskId,
			String taskNodeName, String reminderType) {
		super();
		this.id = id;
		this.cron = cron;
		this.processId = processId;
		this.reminderHandlerBean = reminderHandlerBean;
		this.startDate = startDate;
		this.taskId = taskId;
		this.taskNodeName = taskNodeName;
		this.reminderType = reminderType;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", nullable = false)
	public long getId() {
		return id;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	@Column(name = "CRON_", length = 60)
	public String getCron() {
		return cron;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	@Column(name = "PROCESS_ID_")
	public long getProcessId() {
		return processId;
	}

	public void setReminderHandlerBean(String reminderHandlerBean) {
		this.reminderHandlerBean = reminderHandlerBean;
	}

	@Column(name = "REMINDER_HANDLER_BEAN_", length = 120)
	public String getReminderHandlerBean() {
		return reminderHandlerBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE_")
	public Date getStartDate() {
		return startDate;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	@Column(name = "TASK_ID_")
	public long getTaskId() {
		return taskId;
	}

	public void setTaskNodeName(String taskNodeName) {
		this.taskNodeName = taskNodeName;
	}

	@Column(name = "TASK_NODE_NAME_", length = 60)
	public String getTaskNodeName() {
		return taskNodeName;
	}

	public void setReminderType(String reminderType) {
		this.reminderType = reminderType;
	}

	@Column(name = "REMINDER_TYPE_", length = 20)
	public String getReminderType() {
		return reminderType;
	}

	public String toString() {
		return "UfloTaskReminder [id=" + id + ",cron=" + cron + ",processId="
				+ processId + ",reminderHandlerBean=" + reminderHandlerBean
				+ ",startDate=" + startDate + ",taskId=" + taskId
				+ ",taskNodeName=" + taskNodeName + ",reminderType="
				+ reminderType + "]";
	}

}
