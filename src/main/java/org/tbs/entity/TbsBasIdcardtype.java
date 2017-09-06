package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_bas_idcardtype:证件类型
 */
@Entity
@Table(name = "tbs_bas_idcardtype")
public class TbsBasIdcardtype implements Serializable {

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
	 * tbs_cgg_grbz:反担保信息-个人保证
	 */
	private Set<TbsCggGrbz> tbsCggGrbzSet = new HashSet<TbsCggGrbz>(0);

	/**
	 * tbs_customer_idnum:客户基本资料子表-证件类型与号码
	 */
	private Set<TbsCustomerIdnum> tbsCustomerIdnumSet = new HashSet<TbsCustomerIdnum>(
			0);

	public TbsBasIdcardtype() {
		super();
	}

	public TbsBasIdcardtype(int id, String name, boolean valid,
			Set<TbsCggGrbz> tbsCggGrbzSet,
			Set<TbsCustomerIdnum> tbsCustomerIdnumSet) {
		super();
		this.id = id;
		this.name = name;
		this.valid = valid;
		this.tbsCggGrbzSet = tbsCggGrbzSet;
		this.tbsCustomerIdnumSet = tbsCustomerIdnumSet;
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

	public void setTbsCggGrbzSet(Set<TbsCggGrbz> tbsCggGrbzSet) {
		this.tbsCggGrbzSet = tbsCggGrbzSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasIdcardtype")
	public Set<TbsCggGrbz> getTbsCggGrbzSet() {
		return tbsCggGrbzSet;
	}

	public void setTbsCustomerIdnumSet(Set<TbsCustomerIdnum> tbsCustomerIdnumSet) {
		this.tbsCustomerIdnumSet = tbsCustomerIdnumSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasIdcardtype")
	public Set<TbsCustomerIdnum> getTbsCustomerIdnumSet() {
		return tbsCustomerIdnumSet;
	}

	public String toString() {
		return "TbsBasIdcardtype [id=" + id + ",name=" + name + ",valid="
				+ valid + ",tbsCggGrbzSet=" + tbsCggGrbzSet
				+ ",tbsCustomerIdnumSet=" + tbsCustomerIdnumSet + "]";
	}

}
