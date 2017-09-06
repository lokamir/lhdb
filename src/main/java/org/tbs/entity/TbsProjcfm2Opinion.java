package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * tbs_projcfm2_opinion:签批决议表_意见
 */
@Entity
@Table(name = "tbs_projcfm2_opinion")
public class TbsProjcfm2Opinion implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 签批决议表名称:签批决议表名称
	 */
	private String projcfm2Sn;

	/**
	 * 输入时间戳:输入时间戳
	 */
	private Date timestampInput;

	/**
	 * 最近一次更新的时间戳:最近一次更新的时间戳
	 */
	private Date timestampUpdate;

	/**
	 * 默认为0，不通过。1表示通过2表示回避:默认为0，不通过。1表示通过2表示回避
	 */
	private int approve;

	/**
	 * 签批时间:签批时间
	 */
	private Date apvdate;

	/**
	 * 签批意见:签批意见
	 */
	private String content;

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
	
	private TbsProjcfm2 tbsProjcfm2;
	private Bdf2User bdf2User;
	

	public TbsProjcfm2Opinion() {
		super();
	}

	public TbsProjcfm2Opinion(int id, String projcfm2Sn, Date timestampInput,
			Date timestampUpdate, int approve, Date apvdate, String content,
			String by1, String by2, String by3, TbsProjcfm2 tbsProjcfm2, Bdf2User bdf2User) {
		super();
		this.id = id;
		this.projcfm2Sn = projcfm2Sn;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.approve = approve;
		this.apvdate = apvdate;
		this.content = content;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProjcfm2 = tbsProjcfm2;
		this.bdf2User = bdf2User;
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

	public void setProjcfm2Sn(String projcfm2Sn) {
		this.projcfm2Sn = projcfm2Sn;
	}

	@Column(name = "PROJCFM2_SN", length = 20)
	public String getProjcfm2Sn() {
		return projcfm2Sn;
	}

	public void setTimestampInput(Date timestampInput) {
		this.timestampInput = timestampInput;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_INPUT", nullable = false)
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setTimestampUpdate(Date timestampUpdate) {
		this.timestampUpdate = timestampUpdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_UPDATE", nullable = false)
	public Date getTimestampUpdate() {
		return timestampUpdate;
	}

	public void setApprove(int approve) {
		this.approve = approve;
	}

	@Column(name = "APPROVE")
	public int getApprove() {
		return approve;
	}

	public void setApvdate(Date apvdate) {
		this.apvdate = apvdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APVDATE")
	public Date getApvdate() {
		return apvdate;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CONTENT", length = 100)
	public String getContent() {
		return content;
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

	public void setTbsProjcfm2(TbsProjcfm2 tbsProjcfm2) {
		this.tbsProjcfm2 = tbsProjcfm2;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJCFM2_ID")
	public TbsProjcfm2 getTbsProjcfm2() {
		return tbsProjcfm2;
	}
	
	public void setBdf2User(Bdf2User bdf2User) {
		this.bdf2User = bdf2User;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UID")
	public Bdf2User getBdf2User() {
		return bdf2User;
	}
	
	public String toString() {
		return "TbsProjcfm2Opinion [id=" + id + ",projcfm2Sn=" + projcfm2Sn
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",approve=" + approve + ",apvdate="
				+ apvdate + ",content=" + content + ",by1=" + by1 + ",by2="
				+ by2 + ",by3=" + by3 +  ",tbsProjcfm2=" + tbsProjcfm2 + ",bdf2User=" + bdf2User + "]";
	}

}
