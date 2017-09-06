package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_bas_enttype:客户基本信息-企业类型
 */
@Entity
@Table(name = "tbs_bas_enttype")
public class TbsBasEnttype implements Serializable {

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
	 * tbs_customer_enttype:客户基本资料子表-企业类型
	 */
	private Set<TbsCustomerEnttype> tbsCustomerEnttypeSet = new HashSet<TbsCustomerEnttype>(
			0);

	public TbsBasEnttype() {
		super();
	}

	public TbsBasEnttype(int id, String name, boolean valid,
			Set<TbsCustomerEnttype> tbsCustomerEnttypeSet) {
		super();
		this.id = id;
		this.name = name;
		this.valid = valid;
		this.tbsCustomerEnttypeSet = tbsCustomerEnttypeSet;
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

	public void setTbsCustomerEnttypeSet(
			Set<TbsCustomerEnttype> tbsCustomerEnttypeSet) {
		this.tbsCustomerEnttypeSet = tbsCustomerEnttypeSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasEnttype")
	public Set<TbsCustomerEnttype> getTbsCustomerEnttypeSet() {
		return tbsCustomerEnttypeSet;
	}

	public String toString() {
		return "TbsBasEnttype [id=" + id + ",name=" + name + ",valid=" + valid
				+ ",tbsCustomerEnttypeSet=" + tbsCustomerEnttypeSet + "]";
	}

}
