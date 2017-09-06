package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * uflo_variable:
 */
@Entity
@Table(name = "uflo_variable")
public class UfloVariable implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * TYPE_:
	 */
	private String type;

	/**
	 * ID_:
	 */
	private long id;

	/**
	 * KEY_:
	 */
	private String key;

	/**
	 * PROCESS_INSTANCE_ID_:
	 */
	private long processInstanceId;

	/**
	 * ROOT_PROCESS_INSTANCE_ID_:
	 */
	private long rootProcessInstanceId;

	/**
	 * BLOB_ID_:
	 */
	private long blobId;

	/**
	 * BOOLEAN_VALUE_:
	 */
	private boolean booleanValue;

	/**
	 * BYTE_VALUE_:
	 */
	private byte byteValue;

	/**
	 * CHARACTER_VALUE_:
	 */
	private String characterValue;

	/**
	 * DATE_VALUE_:
	 */
	private Date dateValue;

	/**
	 * DOUBLE_VALUE_:
	 */
	private double doubleValue;

	/**
	 * FLOAT_VALUE_:
	 */
	private float floatValue;

	/**
	 * INTEGER_VALUE_:
	 */
	private int integerValue;

	/**
	 * LONG_VALUE_:
	 */
	private long longValue;

	/**
	 * SHORT_VALUE_:
	 */
	private short shortValue;

	/**
	 * STRING_VALUE_:
	 */
	private String stringValue;

	public UfloVariable() {
		super();
	}

	public UfloVariable(String type, long id, String key,
			long processInstanceId, long rootProcessInstanceId, long blobId,
			boolean booleanValue, byte byteValue, String characterValue,
			Date dateValue, double doubleValue, float floatValue,
			int integerValue, long longValue, short shortValue,
			String stringValue) {
		super();
		this.type = type;
		this.id = id;
		this.key = key;
		this.processInstanceId = processInstanceId;
		this.rootProcessInstanceId = rootProcessInstanceId;
		this.blobId = blobId;
		this.booleanValue = booleanValue;
		this.byteValue = byteValue;
		this.characterValue = characterValue;
		this.dateValue = dateValue;
		this.doubleValue = doubleValue;
		this.floatValue = floatValue;
		this.integerValue = integerValue;
		this.longValue = longValue;
		this.shortValue = shortValue;
		this.stringValue = stringValue;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE_", length = 30, nullable = false)
	public String getType() {
		return type;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", nullable = false)
	public long getId() {
		return id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "KEY_", length = 60)
	public String getKey() {
		return key;
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

	public void setBlobId(long blobId) {
		this.blobId = blobId;
	}

	@Column(name = "BLOB_ID_")
	public long getBlobId() {
		return blobId;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	@Column(name = "BOOLEAN_VALUE_")
	public boolean isBooleanValue() {
		return booleanValue;
	}

	public void setByteValue(byte byteValue) {
		this.byteValue = byteValue;
	}

	@Column(name = "BYTE_VALUE_")
	public byte getByteValue() {
		return byteValue;
	}

	public void setCharacterValue(String characterValue) {
		this.characterValue = characterValue;
	}

	@Column(name = "CHARACTER_VALUE_", length = 1)
	public String getCharacterValue() {
		return characterValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_VALUE_")
	public Date getDateValue() {
		return dateValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	@Column(name = "DOUBLE_VALUE_")
	public double getDoubleValue() {
		return doubleValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}

	@Column(name = "FLOAT_VALUE_")
	public float getFloatValue() {
		return floatValue;
	}

	public void setIntegerValue(int integerValue) {
		this.integerValue = integerValue;
	}

	@Column(name = "INTEGER_VALUE_")
	public int getIntegerValue() {
		return integerValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	@Column(name = "LONG_VALUE_")
	public long getLongValue() {
		return longValue;
	}

	public void setShortValue(short shortValue) {
		this.shortValue = shortValue;
	}

	@Column(name = "SHORT_VALUE_")
	public short getShortValue() {
		return shortValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	@Column(name = "STRING_VALUE_", length = 255)
	public String getStringValue() {
		return stringValue;
	}

	public String toString() {
		return "UfloVariable [type=" + type + ",id=" + id + ",key=" + key
				+ ",processInstanceId=" + processInstanceId
				+ ",rootProcessInstanceId=" + rootProcessInstanceId
				+ ",blobId=" + blobId + ",booleanValue=" + booleanValue
				+ ",byteValue=" + byteValue + ",characterValue="
				+ characterValue + ",dateValue=" + dateValue + ",doubleValue="
				+ doubleValue + ",floatValue=" + floatValue + ",integerValue="
				+ integerValue + ",longValue=" + longValue + ",shortValue="
				+ shortValue + ",stringValue=" + stringValue + "]";
	}

}
