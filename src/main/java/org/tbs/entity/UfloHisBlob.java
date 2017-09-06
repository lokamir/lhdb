package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * uflo_his_blob:
 */
@Entity
@Table(name = "uflo_his_blob")
public class UfloHisBlob implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private long id;

	/**
	 * BLOB_VALUE_:
	 */
	private byte[] blobValue;

	public UfloHisBlob() {
		super();
	}

	public UfloHisBlob(long id, byte[] blobValue) {
		super();
		this.id = id;
		this.blobValue = blobValue;
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

	public String toString() {
		return "UfloHisBlob [id=" + id + ",blobValue=" + blobValue + "]";
	}

}
