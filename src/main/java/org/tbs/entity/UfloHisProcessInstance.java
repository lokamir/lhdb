package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * uflo_his_process_instance:
 */
@Entity
@Table(name = "uflo_his_process_instance")
public class UfloHisProcessInstance implements Serializable {

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
	 * END_DATE_:
	 */
	private Date endDate;

	/**
	 * PROCESS_ID_:
	 */
	private long processId;

	/**
	 * PROCESS_INSTANCE_ID_:
	 */
	private long processInstanceId;

	/**
	 * PROMOTER_:
	 */
	private String promoter;

	/**
	 * TAG_:
	 */
	private String tag;

	public UfloHisProcessInstance() {
		super();
	}

	public UfloHisProcessInstance(long id, String businessId, Date createDate,
			Date endDate, long processId, long processInstanceId,
			String promoter, String tag) {
		super();
		this.id = id;
		this.businessId = businessId;
		this.createDate = createDate;
		this.endDate = endDate;
		this.processId = processId;
		this.processInstanceId = processInstanceId;
		this.promoter = promoter;
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

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE_")
	public Date getEndDate() {
		return endDate;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	@Column(name = "PROCESS_ID_")
	public long getProcessId() {
		return processId;
	}

	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@Column(name = "PROCESS_INSTANCE_ID_")
	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setPromoter(String promoter) {
		this.promoter = promoter;
	}

	@Column(name = "PROMOTER_", length = 60)
	public String getPromoter() {
		return promoter;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "TAG_", length = 30)
	public String getTag() {
		return tag;
	}

	public String toString() {
		return "UfloHisProcessInstance [id=" + id + ",businessId=" + businessId
				+ ",createDate=" + createDate + ",endDate=" + endDate
				+ ",processId=" + processId + ",processInstanceId="
				+ processInstanceId + ",promoter=" + promoter + ",tag=" + tag
				+ "]";
	}

}
