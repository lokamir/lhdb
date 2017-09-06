package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * tbs_customer_enttype:客户基本资料子表-企业类型
 */
@Entity
@Table(name = "tbs_customer_enttype")
public class TbsCustomerEnttype implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * BY1:
	 */
	private String by1;

	/**
	 * BY2:
	 */
	private String by2;

	/**
	 * BY3:
	 */
	private String by3;

	/**
	 * tbs_customer:客户基本信息
	 */
	private TbsCustomer tbsCustomer;

	/**
	 * tbs_bas_enttype:客户基本信息-企业类型
	 */
	private TbsBasEnttype tbsBasEnttype;

	public TbsCustomerEnttype() {
		super();
	}

	public TbsCustomerEnttype(int id, String by1, String by2, String by3,
			TbsCustomer tbsCustomer, TbsBasEnttype tbsBasEnttype) {
		super();
		this.id = id;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsCustomer = tbsCustomer;
		this.tbsBasEnttype = tbsBasEnttype;
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

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	@Column(name = "BY1", length = 100)
	public String getBy1() {
		return by1;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	@Column(name = "BY2", length = 200)
	public String getBy2() {
		return by2;
	}

	public void setBy3(String by3) {
		this.by3 = by3;
	}

	@Column(name = "BY3", length = 300)
	public String getBy3() {
		return by3;
	}

	public void setTbsCustomer(TbsCustomer tbsCustomer) {
		this.tbsCustomer = tbsCustomer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUS_ID")
	public TbsCustomer getTbsCustomer() {
		return tbsCustomer;
	}

	public void setTbsBasEnttype(TbsBasEnttype tbsBasEnttype) {
		this.tbsBasEnttype = tbsBasEnttype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ENTTYPE_ID")
	public TbsBasEnttype getTbsBasEnttype() {
		return tbsBasEnttype;
	}

	public String toString() {
		return "TbsCustomerEnttype [id=" + id + ",by1=" + by1 + ",by2=" + by2
				+ ",by3=" + by3 + ",tbsCustomer=" + tbsCustomer
				+ ",tbsBasEnttype=" + tbsBasEnttype + "]";
	}

}
