package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * tbs_approver:审批人与职位对应表
 */
@Entity
@Table(name = "tbs_approver")
public class TbsApprover implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String cname;
	private String account;
	private String title;
	private String deptid;
	private String deptname;	
	private String by1;
	private String by2;
	private String by3;
	private String by4;
	private Bdf2User bdf2User;
	private Bdf2Dept bdf2Dept;
	
	public TbsApprover() {
		super();
	}

	public TbsApprover(int id, String title, String by1, String by2,
			String by3, String by4, Bdf2User bdf2User, String cname,
			String account, String deptid, String deptname, Bdf2Dept bdf2Dept) {
		super();
		this.id = id;
		this.title = title;
		this.cname = cname;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.by4 = by4;
		this.bdf2User = bdf2User;  //uid
		this.bdf2Dept = bdf2Dept;  //deptdid
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

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TITLE", length = 60)
	public String getTitle() {
		return title;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CNAME", length = 60)
	public String getCname() {
		return cname;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "ACCOUNT", length = 60)
	public String getAccount() {
		return account;
	}
	
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	@Column(name = "DEPTID", length = 60)
	public String getDeptid() {
		return deptid;
	}
	
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	@Column(name = "DEPTNAME", length = 60)
	public String getDeptname() {
		return deptname;
	}
	
	public void setBy1(String by1) {
		this.by1 = by1;
	}

	@Column(name = "BY1", length = 45)
	public String getBy1() {
		return by1;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	@Column(name = "BY2", length = 45)
	public String getBy2() {
		return by2;
	}

	public void setBy3(String by3) {
		this.by3 = by3;
	}

	@Column(name = "BY3", length = 45)
	public String getBy3() {
		return by3;
	}

	public void setBy4(String by4) {
		this.by4 = by4;
	}

	@Column(name = "BY4", length = 45)
	public String getBy4() {
		return by4;
	}

	public void setBdf2User(Bdf2User bdf2User) {
		this.bdf2User = bdf2User;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UID")
	public Bdf2User getBdf2User() {
		return bdf2User;
	}
	
	public void setBdf2Dept(Bdf2Dept bdf2Dept) {
		this.bdf2Dept = bdf2Dept;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPTDID")
	public Bdf2Dept getBdf2Dept() {
		return bdf2Dept;
	}
	
	public String toString() {
		return "TbsApprover [id=" + id + ",title=" + title + ",by1="
				+ by1 + ",by2=" + by2 + ",by3=" + by3 + ",by4=" + by4 + ",bdf2User=" + bdf2User 
				+ ",cname=" + cname + ",account=" + account + ",title=" + title 
				+ ",deptid=" + deptid + ",deptname=" + deptname + ",bdf2Dept=" + bdf2Dept + "]";
	}

}
