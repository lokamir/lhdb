package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * uflo_his_variable:
 */
@Entity
@Table(name = "uflo_his_variable")
public class UfloHisVariable implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private long id;

	/**
	 * HIS_PROCESS_INSTANCE_ID_:
	 */
	private long hisProcessInstanceId;

	/**
	 * KEY_:
	 */
	private String key;

	/**
	 * VALUE_:
	 */
	private String value;

	/**
	 * TYPE_:
	 */
	private String type;

	public UfloHisVariable() {
		super();
	}

	public UfloHisVariable(long id, long hisProcessInstanceId, String key,
			String value, String type) {
		super();
		this.id = id;
		this.hisProcessInstanceId = hisProcessInstanceId;
		this.key = key;
		this.value = value;
		this.type = type;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", nullable = false)
	public long getId() {
		return id;
	}

	public void setHisProcessInstanceId(long hisProcessInstanceId) {
		this.hisProcessInstanceId = hisProcessInstanceId;
	}

	@Column(name = "HIS_PROCESS_INSTANCE_ID_")
	public long getHisProcessInstanceId() {
		return hisProcessInstanceId;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "KEY_", length = 60)
	public String getKey() {
		return key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "VALUE_", length = 255)
	public String getValue() {
		return value;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE_", length = 15)
	public String getType() {
		return type;
	}

	public String toString() {
		return "UfloHisVariable [id=" + id + ",hisProcessInstanceId="
				+ hisProcessInstanceId + ",key=" + key + ",value=" + value
				+ ",type=" + type + "]";
	}

}
