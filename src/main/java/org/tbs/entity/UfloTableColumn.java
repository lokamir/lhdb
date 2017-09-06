package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * uflo_table_column:
 */
@Entity
@Table(name = "uflo_table_column")
public class UfloTableColumn implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private long id;

	/**
	 * DATA_TYPE_:
	 */
	private String dataType;

	/**
	 * DEFAULT_VALUE_:
	 */
	private String defaultValue;

	/**
	 * EDITOR_TYPE_:
	 */
	private int editorType;

	/**
	 * EMPTY_:
	 */
	private boolean empty;

	/**
	 * KEY_GENERATION_TYPE_:
	 */
	private String keyGenerationType;

	/**
	 * MAPPING_:
	 */
	private String mapping;

	/**
	 * MAPPING_SOURCE_:
	 */
	private String mappingSource;

	/**
	 * MEMO_:
	 */
	private String memo;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * TABLE_ID_:
	 */
	private long tableId;

	public UfloTableColumn() {
		super();
	}

	public UfloTableColumn(long id, String dataType, String defaultValue,
			int editorType, boolean empty, String keyGenerationType,
			String mapping, String mappingSource, String memo, String name,
			long tableId) {
		super();
		this.id = id;
		this.dataType = dataType;
		this.defaultValue = defaultValue;
		this.editorType = editorType;
		this.empty = empty;
		this.keyGenerationType = keyGenerationType;
		this.mapping = mapping;
		this.mappingSource = mappingSource;
		this.memo = memo;
		this.name = name;
		this.tableId = tableId;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", nullable = false)
	public long getId() {
		return id;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Column(name = "DATA_TYPE_", length = 60, nullable = false)
	public String getDataType() {
		return dataType;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Column(name = "DEFAULT_VALUE_", length = 60)
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setEditorType(int editorType) {
		this.editorType = editorType;
	}

	@Column(name = "EDITOR_TYPE_")
	public int getEditorType() {
		return editorType;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	@Column(name = "EMPTY_")
	public boolean isEmpty() {
		return empty;
	}

	public void setKeyGenerationType(String keyGenerationType) {
		this.keyGenerationType = keyGenerationType;
	}

	@Column(name = "KEY_GENERATION_TYPE_", length = 10)
	public String getKeyGenerationType() {
		return keyGenerationType;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	@Column(name = "MAPPING_", length = 300)
	public String getMapping() {
		return mapping;
	}

	public void setMappingSource(String mappingSource) {
		this.mappingSource = mappingSource;
	}

	@Column(name = "MAPPING_SOURCE_", length = 20)
	public String getMappingSource() {
		return mappingSource;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "MEMO_", length = 120)
	public String getMemo() {
		return memo;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 60, nullable = false)
	public String getName() {
		return name;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	@Column(name = "TABLE_ID_")
	public long getTableId() {
		return tableId;
	}

	public String toString() {
		return "UfloTableColumn [id=" + id + ",dataType=" + dataType
				+ ",defaultValue=" + defaultValue + ",editorType=" + editorType
				+ ",empty=" + empty + ",keyGenerationType=" + keyGenerationType
				+ ",mapping=" + mapping + ",mappingSource=" + mappingSource
				+ ",memo=" + memo + ",name=" + name + ",tableId=" + tableId
				+ "]";
	}

}
