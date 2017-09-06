package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_projeaa:立项审批表。 审批通过后，变化的数据回插入tbs_proj
 */
@Entity
@Table(name = "tbs_projeaa")
public class TbsProjeaa implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 立项审批表单编号='LXSP'+当前日期+序号:立项审批表单编号='LXSP'+当前日期+序号
	 */
	private String sn;

	/**
	 * 是否通过审批，默认为0无效，审批过后为1有效。:是否通过审批，默认为0无效，审批过后为1有效。
	 */
	private boolean valid;

	/**
	 * 输入时间戳:输入时间戳
	 */
	private Date timestampInput;

	/**
	 * 最近一次更新的时间戳:最近一次更新的时间戳
	 */
	private Date timestampUpdate;

	/**
	 * 录入人:录入人
	 */
	private int keyinId;

	/**
	 * 备注:备注
	 */
	private String memo;

	/**
	 * 删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据:删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据
	 */
	private boolean del;

	/**
	 * 通过项目编号和ID找到项目名称。由于项目名称会在审批过程中发生变化，所以名称不用存在这张表里，单独有个tbs_proj_name表存放名称:通过项目编号和ID找到项目名称。由于项目名称会在审批过程中发生变化，所以名称不用存在这张表里，单独有个tbs_proj_name表存放名称
	 */
	private String projSn;

	/**
	 * 项目经理A，来自tbs_proj,不可变:项目经理A，来自tbs_proj,不可变
	 */
	private String arolename;

	/**
	 * 项目经理B，来自tbs_proj,不可变:项目经理B，来自tbs_proj,不可变
	 */
	private String brolename;

	/**
	 * 申请融资担保授信金额（元）来自tbs_proj,可变:申请融资担保授信金额（元）来自tbs_proj,可变
	 */
	private BigDecimal faloc;

	/**
	 * 申请非融资担保授信金额（元）来自tbs_proj,可变:申请非融资担保授信金额（元）来自tbs_proj,可变
	 */
	private BigDecimal nfaloc;

	/**
	 * 申请其他类担保授信金额（元）来自tbs_proj,可变:申请其他类担保授信金额（元）来自tbs_proj,可变
	 */
	private BigDecimal otloc;

	/**
	 * 总申请担保授信金额（元）来自tbs_proj,可变:总申请担保授信金额（元）来自tbs_proj,可变
	 */
	private BigDecimal totloc;

	/**
	 * 申请授信期限从项目中自动带出来自tbs_proj,可变:申请授信期限从项目中自动带出来自tbs_proj,可变
	 */
	private int period;

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
	 * bdf2_dept:
	 */
	private Bdf2Dept bdf2Dept;

	/**
	 * bdf2_user:
	 */
	private Bdf2User bdf2User;

	public TbsProjeaa() {
		super();
	}

	public TbsProjeaa(int id, String sn, boolean valid, Date timestampInput,
			Date timestampUpdate, int keyinId, String memo, boolean del,
			String projSn, String arolename, String brolename,
			BigDecimal faloc, BigDecimal nfaloc, BigDecimal otloc,
			BigDecimal totloc, int period, String by1, String by2, String by3,
			TbsProj tbsProj, Bdf2Dept bdf2Dept, Bdf2User bdf2User) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.keyinId = keyinId;
		this.memo = memo;
		this.del = del;
		this.projSn = projSn;
		this.arolename = arolename;
		this.brolename = brolename;
		this.faloc = faloc;
		this.nfaloc = nfaloc;
		this.otloc = otloc;
		this.totloc = totloc;
		this.period = period;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.bdf2Dept = bdf2Dept;
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

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "SN", length = 20)
	public String getSn() {
		return sn;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
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

	public void setKeyinId(int keyinId) {
		this.keyinId = keyinId;
	}

	@Column(name = "KEYIN_ID")
	public int getKeyinId() {
		return keyinId;
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

	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}

	public void setProjSn(String projSn) {
		this.projSn = projSn;
	}

	@Column(name = "PROJ_SN", length = 20)
	public String getProjSn() {
		return projSn;
	}

	public void setArolename(String arolename) {
		this.arolename = arolename;
	}

	@Column(name = "AROLENAME", length = 45)
	public String getArolename() {
		return arolename;
	}

	public void setBrolename(String brolename) {
		this.brolename = brolename;
	}

	@Column(name = "BROLENAME", length = 45)
	public String getBrolename() {
		return brolename;
	}

	public void setFaloc(BigDecimal faloc) {
		this.faloc = faloc;
	}

	@Column(name = "FALOC")
	public BigDecimal getFaloc() {
		return faloc;
	}

	public void setNfaloc(BigDecimal nfaloc) {
		this.nfaloc = nfaloc;
	}

	@Column(name = "NFALOC")
	public BigDecimal getNfaloc() {
		return nfaloc;
	}

	public void setOtloc(BigDecimal otloc) {
		this.otloc = otloc;
	}

	@Column(name = "OTLOC")
	public BigDecimal getOtloc() {
		return otloc;
	}

	public void setTotloc(BigDecimal totloc) {
		this.totloc = totloc;
	}

	@Column(name = "TOTLOC")
	public BigDecimal getTotloc() {
		return totloc;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Column(name = "PERIOD")
	public int getPeriod() {
		return period;
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

	public void setBdf2Dept(Bdf2Dept bdf2Dept) {
		this.bdf2Dept = bdf2Dept;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPTDID")
	public Bdf2Dept getBdf2Dept() {
		return bdf2Dept;
	}

	public void setBdf2User(Bdf2User bdf2User) {
		this.bdf2User = bdf2User;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MGMUID")
	public Bdf2User getBdf2User() {
		return bdf2User;
	}

	public String toString() {
		return "TbsProjeaa [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",memo=" + memo
				+ ",del=" + del + ",projSn=" + projSn + ",arolename="
				+ arolename + ",brolename=" + brolename + ",faloc=" + faloc
				+ ",nfaloc=" + nfaloc + ",otloc=" + otloc + ",totloc=" + totloc
				+ ",period=" + period + ",by1=" + by1 + ",by2=" + by2 + ",by3="
				+ by3 + ",tbsProj=" + tbsProj + ",bdf2Dept=" + bdf2Dept
				+ ",bdf2User=" + bdf2User + "]";
	}

}
