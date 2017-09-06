package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * tbs_customer_idnum:客户基本资料子表-证件类型与号码
 */
@Entity
@Table(name = "tbs_customer_idnum")
public class TbsCustomerIdnum implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 证件号码:证件号码
	 */
	private String idnum;

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
	 * tbs_bas_idcardtype:证件类型
	 */
	private TbsBasIdcardtype tbsBasIdcardtype;

	public TbsCustomerIdnum() {
		super();
	}

	public TbsCustomerIdnum(int id, String idnum, String by1, String by2,
			String by3, TbsCustomer tbsCustomer,
			TbsBasIdcardtype tbsBasIdcardtype) {
		super();
		this.id = id;
		this.idnum = idnum;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsCustomer = tbsCustomer;
		this.tbsBasIdcardtype = tbsBasIdcardtype;
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

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	@Column(name = "IDNUM", length = 30)
	public String getIdnum() {
		return idnum;
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

	public void setTbsBasIdcardtype(TbsBasIdcardtype tbsBasIdcardtype) {
		this.tbsBasIdcardtype = tbsBasIdcardtype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDCARDTYPE_ID")
	public TbsBasIdcardtype getTbsBasIdcardtype() {
		return tbsBasIdcardtype;
	}

	public String toString() {
		return "TbsCustomerIdnum [id=" + id + ",idnum=" + idnum + ",by1=" + by1
				+ ",by2=" + by2 + ",by3=" + by3 + ",tbsCustomer=" + tbsCustomer
				+ ",tbsBasIdcardtype=" + tbsBasIdcardtype + "]";
	}

}
