package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class VusermgmDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	// Fields view里面的字段
	private int uid;
	private String cname;
	private String account;
	private String title;
	private String dept;
	private String deptid;
	private int deptdid;
	
	// Constructors view里面的构造对应,具体的项目
	// default
	public VusermgmDetail() {

	}

	// full
	public VusermgmDetail(int uid, int deptdid, String cname,
			String account, String title, String dept, String deptid) {
		this.uid = uid;
		this.deptdid = deptdid;
		this.account = account;
		this.cname = cname;
		this.title = title;
		this.dept = dept;
		this.deptid = deptid;
	}

	
	// Property accessors
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public int getDeptdid() {
		return deptdid;
	}

	public void setDeptdid(int deptdid) {
		this.deptdid = deptdid;
	}

	
	
	
	
	
}
