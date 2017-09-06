package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * tbs_projcfm1_opinion:会议决议表_意见
 */
@Entity
@Table(name = "tbs_projcfm1_opinion")
public class TbsProjcfm1Opinion implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 会议决议表名称:会议决议表名称
	 */
	private String projcfm1Sn;

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
	
	private TbsProjcfm1 tbsProjcfm1;
	private Bdf2User bdf2User;

	public TbsProjcfm1Opinion() {
		super();
	}

	public TbsProjcfm1Opinion(int id, String projcfm1Sn, Date timestampInput,
			Date timestampUpdate, int approve, String by1, String by2,
			String by3,TbsProjcfm1 tbsProjcfm1, Bdf2User bdf2User) {
		super();
		this.id = id;
		this.projcfm1Sn = projcfm1Sn;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.approve = approve;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProjcfm1 = tbsProjcfm1;
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

	public void setProjcfm1Sn(String projcfm1Sn) {
		this.projcfm1Sn = projcfm1Sn;
	}

	@Column(name = "PROJCFM1_SN", length = 20)
	public String getProjcfm1Sn() {
		return projcfm1Sn;
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
	
	public void setTbsProjcfm1(TbsProjcfm1 tbsProjcfm1) {
		this.tbsProjcfm1 = tbsProjcfm1;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJCFM1_ID")
	public TbsProjcfm1 getTbsProjcfm1() {
		return tbsProjcfm1;
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
		return "TbsProjcfm1Opinion [id=" + id + ",projcfm1Sn=" + projcfm1Sn
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",approve=" + approve + ",by1=" + by1
				+ ",by2=" + by2 + ",by3=" + by3 + ",tbsProjcfm1=" + tbsProjcfm1 + ",bdf2User=" + bdf2User + "]";
	}

}
