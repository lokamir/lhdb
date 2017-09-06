package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * uflo_proxy_process:
 */
@Entity
@Table(name = "uflo_proxy_process")
public class UfloProxyProcess implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * PROCESS_DEF_ID_:
	 */
	private long processDefId;

	/**
	 * PROCESS_DEF_NAME_:
	 */
	private String processDefName;

	/**
	 * PROXY_DEF_ID_:
	 */
	private String proxyDefId;

	public UfloProxyProcess() {
		super();
	}

	public UfloProxyProcess(String id, long processDefId,
			String processDefName, String proxyDefId) {
		super();
		this.id = id;
		this.processDefId = processDefId;
		this.processDefName = processDefName;
		this.proxyDefId = proxyDefId;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 60, nullable = false)
	public String getId() {
		return id;
	}

	public void setProcessDefId(long processDefId) {
		this.processDefId = processDefId;
	}

	@Column(name = "PROCESS_DEF_ID_")
	public long getProcessDefId() {
		return processDefId;
	}

	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}

	@Column(name = "PROCESS_DEF_NAME_", length = 255)
	public String getProcessDefName() {
		return processDefName;
	}

	public void setProxyDefId(String proxyDefId) {
		this.proxyDefId = proxyDefId;
	}

	@Column(name = "PROXY_DEF_ID_", length = 255)
	public String getProxyDefId() {
		return proxyDefId;
	}

	public String toString() {
		return "UfloProxyProcess [id=" + id + ",processDefId=" + processDefId
				+ ",processDefName=" + processDefName + ",proxyDefId="
				+ proxyDefId + "]";
	}

}
