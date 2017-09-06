package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * uflo_form:
 */
@Entity
@Table(name = "uflo_form")
public class UfloForm implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private long id;

	/**
	 * CATEGORY_ID_:
	 */
	private String categoryId;

	/**
	 * CONTENT_:
	 */
	private byte[] content;

	/**
	 * CREATE_DATE_:
	 */
	private Date createDate;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * TABLE_ID_:
	 */
	private long tableId;

	/**
	 * UPDATE_DATE_:
	 */
	private Date updateDate;

	public UfloForm() {
		super();
	}

	public UfloForm(long id, String categoryId, byte[] content,
			Date createDate, String name, long tableId, Date updateDate) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.content = content;
		this.createDate = createDate;
		this.name = name;
		this.tableId = tableId;
		this.updateDate = updateDate;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", nullable = false)
	public long getId() {
		return id;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "CATEGORY_ID_", length = 60)
	public String getCategoryId() {
		return categoryId;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "CONTENT_", columnDefinition = "BLOB")
	public byte[] getContent() {
		return content;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE_")
	public Date getCreateDate() {
		return createDate;
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

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE_")
	public Date getUpdateDate() {
		return updateDate;
	}

	public String toString() {
		return "UfloForm [id=" + id + ",categoryId=" + categoryId + ",content="
				+ content + ",createDate=" + createDate + ",name=" + name
				+ ",tableId=" + tableId + ",updateDate=" + updateDate + "]";
	}

}
