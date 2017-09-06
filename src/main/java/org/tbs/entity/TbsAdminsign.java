package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * tbs_adminsign:综合管理部用印
 */
@Entity
@Table(name = "tbs_adminsign")
public class TbsAdminsign implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 输入时间戳:输入时间戳
	 */
	private Date timestampInput;

	/**
	 * 是否有效，默认为0无效，审批过后为1有效。:是否有效，默认为0无效，审批过后为1有效。
	 */
	private boolean valid;

	/**
	 * 请印人:请印人
	 */
	private int keyinId;

	/**
	 * PROJ_SN:
	 */
	private String projSn;

	/**
	 * 核保书份数:核保书份数
	 */
	private int hbsfs;

	/**
	 * 保证合同份数:保证合同份数
	 */
	private int bzhtfs;

	/**
	 * 请印时间:请印时间
	 */
	private Date date;

	/**
	 * 删除标记默认为0,非删除:删除标记默认为0,非删除
	 */
	private boolean del;

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
	 * tbs_proj:项目基本信息主表
	 */
	private TbsProj tbsProj;

	/**
	 * tbs_bas_bank:银行
	 */
	private TbsBasBank tbsBasBank;

	/**
	 * bdf2_user:
	 */
	private Bdf2User bdf2User;

	public TbsAdminsign() {
		super();
	}

	public TbsAdminsign(int id, Date timestampInput, boolean valid,
			int keyinId, String projSn, int hbsfs, int bzhtfs, Date date,
			boolean del, String by1, String by2, String by3, TbsProj tbsProj,
			TbsBasBank tbsBasBank, Bdf2User bdf2User) {
		super();
		this.id = id;
		this.timestampInput = timestampInput;
		this.valid = valid;
		this.keyinId = keyinId;
		this.projSn = projSn;
		this.hbsfs = hbsfs;
		this.bzhtfs = bzhtfs;
		this.date = date;
		this.del = del;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsBasBank = tbsBasBank;
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

	public void setTimestampInput(Date timestampInput) {
		this.timestampInput = timestampInput;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_INPUT", nullable = false)
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
	}

	public void setKeyinId(int keyinId) {
		this.keyinId = keyinId;
	}

	@Column(name = "KEYIN_ID")
	public int getKeyinId() {
		return keyinId;
	}

	public void setProjSn(String projSn) {
		this.projSn = projSn;
	}

	@Column(name = "PROJ_SN", length = 20)
	public String getProjSn() {
		return projSn;
	}

	public void setHbsfs(int hbsfs) {
		this.hbsfs = hbsfs;
	}

	@Column(name = "HBSFS")
	public int getHbsfs() {
		return hbsfs;
	}

	public void setBzhtfs(int bzhtfs) {
		this.bzhtfs = bzhtfs;
	}

	@Column(name = "BZHTFS")
	public int getBzhtfs() {
		return bzhtfs;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_")
	public Date getDate() {
		return date;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
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

	public void setTbsBasBank(TbsBasBank tbsBasBank) {
		this.tbsBasBank = tbsBasBank;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AFLSB_ID")
	public TbsBasBank getTbsBasBank() {
		return tbsBasBank;
	}

	public void setBdf2User(Bdf2User bdf2User) {
		this.bdf2User = bdf2User;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JBR_USER_ID")
	public Bdf2User getBdf2User() {
		return bdf2User;
	}

	public String toString() {
		return "TbsAdminsign [id=" + id + ",timestampInput=" + timestampInput
				+ ",valid=" + valid + ",keyinId=" + keyinId + ",projSn="
				+ projSn + ",hbsfs=" + hbsfs + ",bzhtfs=" + bzhtfs + ",date="
				+ date + ",del=" + del + ",by1=" + by1 + ",by2=" + by2
				+ ",by3=" + by3 + ",tbsProj=" + tbsProj + ",tbsBasBank="
				+ tbsBasBank + ",bdf2User=" + bdf2User + "]";
	}

}
