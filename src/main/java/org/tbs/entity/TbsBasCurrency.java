package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_bas_currency:币种
 */
@Entity
@Table(name = "tbs_bas_currency")
public class TbsBasCurrency implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * 默认为1，生效:默认为1，生效
	 */
	private boolean valid;

	/**
	 * tbs_proj:项目基本信息主表
	 */
	private Set<TbsProj> tbsProjSet = new HashSet<TbsProj>(0);

	public TbsBasCurrency() {
		super();
	}

	public TbsBasCurrency(int id, String name, boolean valid,
			Set<TbsProj> tbsProjSet) {
		super();
		this.id = id;
		this.name = name;
		this.valid = valid;
		this.tbsProjSet = tbsProjSet;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 45)
	public String getName() {
		return name;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
	}

	public void setTbsProjSet(Set<TbsProj> tbsProjSet) {
		this.tbsProjSet = tbsProjSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasCurrency")
	public Set<TbsProj> getTbsProjSet() {
		return tbsProjSet;
	}

	public String toString() {
		return "TbsBasCurrency [id=" + id + ",name=" + name + ",valid=" + valid
				+ ",tbsProjSet=" + tbsProjSet + "]";
	}

}
