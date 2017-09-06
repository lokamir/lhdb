package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * bdf2_position:
 */
@Entity
@Table(name = "bdf2_position")
public class Bdf2Position implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * COMPANY_ID_:
	 */
	private String companyId;

	/**
	 * CREATE_DATE_:
	 */
	private Date createDate;

	/**
	 * DESC_:
	 */
	private String desc;

	/**
	 * NAME_:
	 */
	private String name;

	public Bdf2Position() {
		super();
	}

	public Bdf2Position(String id, String companyId, Date createDate,
			String desc, String name) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.createDate = createDate;
		this.desc = desc;
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 60, nullable = false)
	public String getId() {
		return id;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "COMPANY_ID_", length = 60)
	public String getCompanyId() {
		return companyId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE_")
	public Date getCreateDate() {
		return createDate;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "DESC_", length = 120)
	public String getDesc() {
		return desc;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 60)
	public String getName() {
		return name;
	}

	public String toString() {
		return "Bdf2Position [id=" + id + ",companyId=" + companyId
				+ ",createDate=" + createDate + ",desc=" + desc + ",name="
				+ name + "]";
	}

}
