package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_bas_indutype:客户基本信息-行业类型
 */
@Entity
@Table(name = "tbs_bas_indutype")
public class TbsBasIndutype implements Serializable {

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
	 * tbs_customer:客户基本信息
	 */
	private Set<TbsCustomer> tbsCustomerSet = new HashSet<TbsCustomer>(0);

	public TbsBasIndutype() {
		super();
	}

	public TbsBasIndutype(int id, String name, boolean valid,
			Set<TbsCustomer> tbsCustomerSet) {
		super();
		this.id = id;
		this.name = name;
		this.valid = valid;
		this.tbsCustomerSet = tbsCustomerSet;
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

	public void setTbsCustomerSet(Set<TbsCustomer> tbsCustomerSet) {
		this.tbsCustomerSet = tbsCustomerSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasIndutype")
	public Set<TbsCustomer> getTbsCustomerSet() {
		return tbsCustomerSet;
	}

	public String toString() {
		return "TbsBasIndutype [id=" + id + ",name=" + name + ",valid=" + valid
				+ ",tbsCustomerSet=" + tbsCustomerSet + "]";
	}

}
