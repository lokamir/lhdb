package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_bas_ps:项目状态
 */
@Entity
@Table(name = "tbs_bas_ps")
public class TbsBasPs implements Serializable {

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
	 * 默认为1，有效:默认为1，有效
	 */
	private boolean valid;

	/**
	 * tbs_proj:项目基本信息主表
	 */
	private Set<TbsProj> tbsProjSet = new HashSet<TbsProj>(0);

	/**
	 * tbs_proj_hisstatus:项目基本信息子表-项目历史状态
	 */
	private Set<TbsProjHisstatus> tbsProjHisstatusSet = new HashSet<TbsProjHisstatus>(
			0);

	public TbsBasPs() {
		super();
	}

	public TbsBasPs(int id, String name, boolean valid,
			Set<TbsProj> tbsProjSet, Set<TbsProjHisstatus> tbsProjHisstatusSet) {
		super();
		this.id = id;
		this.name = name;
		this.valid = valid;
		this.tbsProjSet = tbsProjSet;
		this.tbsProjHisstatusSet = tbsProjHisstatusSet;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasPs")
	public Set<TbsProj> getTbsProjSet() {
		return tbsProjSet;
	}

	public void setTbsProjHisstatusSet(Set<TbsProjHisstatus> tbsProjHisstatusSet) {
		this.tbsProjHisstatusSet = tbsProjHisstatusSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasPs")
	public Set<TbsProjHisstatus> getTbsProjHisstatusSet() {
		return tbsProjHisstatusSet;
	}

	public String toString() {
		return "TbsBasPs [id=" + id + ",name=" + name + ",valid=" + valid
				+ ",tbsProjSet=" + tbsProjSet + ",tbsProjHisstatusSet="
				+ tbsProjHisstatusSet + "]";
	}

}
