package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * uflo_context_property:
 */
@Entity
@Table(name = "uflo_context_property")
public class UfloContextProperty implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * KEY_:
	 */
	private String key;

	/**
	 * VALUE_:
	 */
	private String value;

	public UfloContextProperty() {
		super();
	}

	public UfloContextProperty(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Id
	@Column(name = "KEY_", length = 120, nullable = false)
	public String getKey() {
		return key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "VALUE_", length = 35)
	public String getValue() {
		return value;
	}

	public String toString() {
		return "UfloContextProperty [key=" + key + ",value=" + value + "]";
	}

}
