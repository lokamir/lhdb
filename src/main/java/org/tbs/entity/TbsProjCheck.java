package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * tbs_proj_check:定期与不定期检查记录表
 */
@Entity
@Table(name = "tbs_proj_check")
public class TbsProjCheck implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * SN:
	 */
	private String sn;

	/**
	 * TIMESTAMP_INPUT:
	 */
	private Date timestampInput;

	/**
	 * KEYIN_ID:
	 */
	private int keyinId;

	/**
	 * 0=未发送审批1=审批通过2=审批中3=驳回:0=未发送审批1=审批通过2=审批中3=驳回
	 */
	private int valid;

	/**
	 * DEL:
	 */
	private boolean del;

	/**
	 * 默认为0定期检查，1不定期检查:默认为0定期检查，1不定期检查
	 */
	private int chktype;

	/**
	 * 检查日期:检查日期
	 */
	private Date chkdate;

	/**
	 * 备注:备注
	 */
	private String memo;

	/**
	 * processInstanceId:processInstanceId
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

	public TbsProjCheck() {
		super();
	}

	public TbsProjCheck(int id, String sn, Date timestampInput, int keyinId,
			int valid, boolean del, int chktype, Date chkdate, String memo,
			String by1, String by2, String by3, TbsProj tbsProj) {
		super();
		this.id = id;
		this.sn = sn;
		this.timestampInput = timestampInput;
		this.keyinId = keyinId;
		this.valid = valid;
		this.del = del;
		this.chktype = chktype;
		this.chkdate = chkdate;
		this.memo = memo;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
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

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "SN", length = 20)
	public String getSn() {
		return sn;
	}

	public void setTimestampInput(Date timestampInput) {
		this.timestampInput = timestampInput;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_INPUT")
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setKeyinId(int keyinId) {
		this.keyinId = keyinId;
	}

	@Column(name = "KEYIN_ID")
	public int getKeyinId() {
		return keyinId;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public int getValid() {
		return valid;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}

	public void setChktype(int chktype) {
		this.chktype = chktype;
	}

	@Column(name = "CHKTYPE")
	public int getChktype() {
		return chktype;
	}

	public void setChkdate(Date chkdate) {
		this.chkdate = chkdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CHKDATE")
	public Date getChkdate() {
		return chkdate;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "MEMO", columnDefinition = "CLOB")
	public String getMemo() {
		return memo;
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

	public void setTbsProj(TbsProj tbsProj) {
		this.tbsProj = tbsProj;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJ_ID")
	public TbsProj getTbsProj() {
		return tbsProj;
	}
	
	public String toString() {
		return "TbsProjCheck [id=" + id + ",sn=" + sn + ",timestampInput="
				+ timestampInput + ",keyinId=" + keyinId + ",valid=" + valid
				+ ",del=" + del + ",chktype=" + chktype + ",chkdate=" + chkdate
				+ ",memo=" + memo + ",by1=" + by1 + ",by2=" + by2 + ",by3="
				+ by3 + ",tbsProj=" + tbsProj + "]";
	}

}
