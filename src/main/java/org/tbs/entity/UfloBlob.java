package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * uflo_blob:
 */
@Entity
@Table(name = "uflo_blob")
public class UfloBlob implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private long id;

	/**
	 * BLOB_VALUE_:
	 */
	private byte[] blobValue;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * PROCESS_ID_:
	 */
	private long processId;

	public UfloBlob() {
		super();
	}

	public UfloBlob(long id, byte[] blobValue, String name, long processId) {
		super();
		this.id = id;
		this.blobValue = blobValue;
		this.name = name;
		this.processId = processId;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", nullable = false)
	public long getId() {
		return id;
	}

	public void setBlobValue(byte[] blobValue) {
		this.blobValue = blobValue;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "BLOB_VALUE_", columnDefinition = "BLOB")
	public byte[] getBlobValue() {
		return blobValue;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 60)
	public String getName() {
		return name;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	@Column(name = "PROCESS_ID_")
	public long getProcessId() {
		return processId;
	}

	public String toString() {
		return "UfloBlob [id=" + id + ",blobValue=" + blobValue + ",name="
				+ name + ",processId=" + processId + "]";
	}

}
