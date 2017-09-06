package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * tbs_proj_opinion:项目评审意见表
 */
@Entity
@Table(name = "tbs_proj_opinion")
public class TbsProjOpinion implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * CFMTYPE:CFMTYPE
	 */
	private int cfmtype;

	/**
	 * 输入时间戳:输入时间戳
	 */
	private Date timestampInput;

	/**
	 * 最近一次更新的时间戳:最近一次更新的时间戳
	 */
	private Date timestampUpdate;

	/**
	 * 删除标记:删除标记
	 */
	private boolean del;

	/**
	 * 职位:职位
	 */
	private String title;

	/**
	 * 默认为0，不通过。1表示通过2表示回避:默认为0，不通过。1表示通过2表示回避
	 */
	private String outcome;

	/**
	 * 签批时间（只给cfm2用）:签批时间（只给cfm2用）
	 */
	private Date ocDate;

	/**
	 * 审批意见:审批意见
	 */
	private String comment;

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

	private TbsProj tbsProj;
	private TbsProjcfm0 tbsProjcfm0;
	private TbsProjcfm1 tbsProjcfm1;
	private TbsProjcfm2 tbsProjcfm2;
	private Bdf2User bdf2User;

	public TbsProjOpinion() {
		super();
	}

	public TbsProjOpinion(int id, int cfmtype, Date timestampInput,
			Date timestampUpdate, boolean del, String title, String outcome,
			Date ocDate, String comment, String by1, String by2, String by3,
			TbsProj tbsProj, TbsProjcfm0 tbsProjcfm0, TbsProjcfm1 tbsProjcfm1,
			TbsProjcfm2 tbsProjcfm2, Bdf2User bdf2User) {
		super();
		this.id = id;
		this.cfmtype = cfmtype;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.del = del;
		this.title = title;
		this.outcome = outcome;
		this.ocDate = ocDate;
		this.comment = comment;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsProjcfm0 = tbsProjcfm0;
		this.tbsProjcfm1 = tbsProjcfm1;
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

	public void setCfmtype(int cfmtype) {
		this.cfmtype = cfmtype;
	}

	@Column(name = "CFMTYPE")
	public int getCfmtype() {
		return cfmtype;
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

	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TITLE", length = 60)
	public String getTitle() {
		return title;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	@Column(name = "OUTCOME", length = 4)
	public String getOutcome() {
		return outcome;
	}

	public void setOcDate(Date ocDate) {
		this.ocDate = ocDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OC_DATE")
	public Date getOcDate() {
		return ocDate;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "COMMENT", length = 100)
	public String getComment() {
		return comment;
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

	public void setBdf2User(Bdf2User bdf2User) {
		this.bdf2User = bdf2User;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UID")
	public Bdf2User getBdf2User() {
		return bdf2User;
	}
	
	public void setTbsProjcfm0(TbsProjcfm0 tbsProjcfm0) {
		this.tbsProjcfm0 = tbsProjcfm0;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CFM0_ID")
	public TbsProjcfm0 getTbsProjcfm0() {
		return tbsProjcfm0;
	}
	
	public void setTbsProjcfm1(TbsProjcfm1 tbsProjcfm1) {
		this.tbsProjcfm1 = tbsProjcfm1;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CFM1_ID")
	public TbsProjcfm1 getTbsProjcfm1() {
		return tbsProjcfm1;
	}

	public void setTbsProjcfm2(TbsProjcfm2 tbsProjcfm2) {
		this.tbsProjcfm2= tbsProjcfm2;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CFM2_ID")
	public TbsProjcfm2 getTbsProjcfm2() {
		return tbsProjcfm2;
	}

	public void setTbsProj(TbsProj tbsProj) {
		this.tbsProj = tbsProj;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJ_ID")
	public TbsProj getTbsProj() {
		return tbsProj;
	}
	
	public String toString() {
		return "TbsProjOpinion [id=" + id + ",cfmtype=" + cfmtype
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",del=" + del + ",title=" + title
				+ ",outcome=" + outcome + ",ocDate=" + ocDate + ",comment="
				+ comment + ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3 
				+ ",tbsProj=" + tbsProj 
				+ ",tbsProjcfm2=" + tbsProjcfm2 
				+ ",tbsProjcfm1=" + tbsProjcfm1 
				+ ",tbsProjcfm0=" + tbsProjcfm0
				+ ",bdf2User=" + bdf2User + "]";
	}

}
