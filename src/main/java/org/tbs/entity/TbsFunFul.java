package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * tbs_fun_ful:文件信息管理表
 */
@Entity
@Table(name = "tbs_fun_ful")
public class TbsFunFul implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 注释:注释
	 */
	private String comments;

	/**
	 * 上传日期:上传日期
	 */
	private Date date;

	/**
	 * 文件类型:文件类型
	 */
	private String fclass;

	/**
	 * 文件名称:文件名称
	 */
	private String fname;

	/**
	 * 文件上传的识别号，识别来自哪个单据，:文件上传的识别号，识别来自哪个单据，
	 */
	private String fid;

	/**
	 * 上传路径:上传路径
	 */
	private String uppath;

	/**
	 * 用户ID:用户ID
	 */
	private int userId;

	/**
	 * USERNAME:
	 */
	private String username;

	/**
	 * URL:
	 */
	private String url;

	/**
	 * 0未审核1审核通过:0未审核1审核通过
	 */
	private boolean valid;

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
	 * BY4:
	 */
	private String by4;

	/**
	 * tbs_fun_ful_typ:
	 */
	private TbsFunFulTyp tbsFunFulTyp;

	public TbsFunFul() {
		super();
	}

	public TbsFunFul(int id, String comments, Date date, String fclass,
			String fname, String fid, String uppath, int userId,
			String username, String url, boolean valid, String by1, String by2,
			String by3, String by4, TbsFunFulTyp tbsFunFulTyp) {
		super();
		this.id = id;
		this.comments = comments;
		this.date = date;
		this.fclass = fclass;
		this.fname = fname;
		this.fid = fid;
		this.uppath = uppath;
		this.userId = userId;
		this.username = username;
		this.url = url;
		this.valid = valid;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.by4 = by4;
		this.tbsFunFulTyp = tbsFunFulTyp;
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

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "COMMENTS", length = 800)
	public String getComments() {
		return comments;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_")
	public Date getDate() {
		return date;
	}

	public void setFclass(String fclass) {
		this.fclass = fclass;
	}

	@Column(name = "FCLASS", length = 10)
	public String getFclass() {
		return fclass;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "FNAME", length = 100)
	public String getFname() {
		return fname;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	@Column(name = "FID", length = 50)
	public String getFid() {
		return fid;
	}

	public void setUppath(String uppath) {
		this.uppath = uppath;
	}

	@Column(name = "UPPATH", length = 200)
	public String getUppath() {
		return uppath;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "USERNAME", length = 45)
	public String getUsername() {
		return username;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "URL", length = 100)
	public String getUrl() {
		return url;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID", nullable = false)
	public boolean isValid() {
		return valid;
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

	public void setBy4(String by4) {
		this.by4 = by4;
	}

	@Column(name = "BY4", length = 400)
	public String getBy4() {
		return by4;
	}

	public void setTbsFunFulTyp(TbsFunFulTyp tbsFunFulTyp) {
		this.tbsFunFulTyp = tbsFunFulTyp;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TYPID")
	public TbsFunFulTyp getTbsFunFulTyp() {
		return tbsFunFulTyp;
	}

	public String toString() {
		return "TbsFunFul [id=" + id + ",comments=" + comments + ",date="
				+ date + ",fclass=" + fclass + ",fname=" + fname + ",fid="
				+ fid + ",uppath=" + uppath + ",userId=" + userId
				+ ",username=" + username + ",url=" + url + ",valid=" + valid
				+ ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3 + ",by4=" + by4
				+ ",tbsFunFulTyp=" + tbsFunFulTyp + "]";
	}

}
