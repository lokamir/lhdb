package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * bdf2_component:
 */
@Entity
@Table(name = "bdf2_component")
public class Bdf2Component implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * COMPONENT_ID_:
	 */
	private String componentId;

	/**
	 * DESC_:
	 */
	private String desc;

	/**
	 * bdf2_url_component:
	 */
	private Set<Bdf2UrlComponent> bdf2UrlComponentSet = new HashSet<Bdf2UrlComponent>(
			0);

	public Bdf2Component() {
		super();
	}

	public Bdf2Component(String id, String componentId, String desc,
			Set<Bdf2UrlComponent> bdf2UrlComponentSet) {
		super();
		this.id = id;
		this.componentId = componentId;
		this.desc = desc;
		this.bdf2UrlComponentSet = bdf2UrlComponentSet;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 60, nullable = false)
	public String getId() {
		return id;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	@Column(name = "COMPONENT_ID_", length = 60, nullable = false)
	public String getComponentId() {
		return componentId;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "DESC_", length = 120)
	public String getDesc() {
		return desc;
	}

	public void setBdf2UrlComponentSet(Set<Bdf2UrlComponent> bdf2UrlComponentSet) {
		this.bdf2UrlComponentSet = bdf2UrlComponentSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bdf2Component")
	public Set<Bdf2UrlComponent> getBdf2UrlComponentSet() {
		return bdf2UrlComponentSet;
	}

	public String toString() {
		return "Bdf2Component [id=" + id + ",componentId=" + componentId
				+ ",desc=" + desc + ",bdf2UrlComponentSet="
				+ bdf2UrlComponentSet + "]";
	}

}
