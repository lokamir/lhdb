package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_projcfm0:项目决策审批上会通知单
 */
@Entity
@Table(name = "tbs_projcfm0")
public class TbsProjcfm0 implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 表单编号='SHTZ'+单据日期+序号:表单编号='SHTZ'+单据日期+序号
	 */
	private String sn;

	/**
	 * 是否有效，默认为0无效，审批过后为1有效。:是否有效，默认为0无效，审批过后为1有效。
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
	 * 会议日期:会议日期
	 */
	private Date jtrq;

	/**
	 * 会议地点:会议地点
	 */
	private String location;

	/**
	 * 会议时间:会议时间
	 */
	private String jtsj;

	/**
	 * 上会类型。【签批，会议】
	 */
	private String type;

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
	 * tbs_projcfm0_attend:项目决策审批上会通知单-参会人员子表
	 */
	private Set<TbsProjcfm0Attend> tbsProjcfm0AttendSet = new HashSet<TbsProjcfm0Attend>(
			0);
	
	private Set<TbsProjOpinion> tbsProjOpinionSet = new HashSet<TbsProjOpinion>(0);

	public TbsProjcfm0() {
		super();
	}

	public TbsProjcfm0(int id, String sn, boolean valid, Date timestampInput,
			Date timestampUpdate, int keyinId, String memo, boolean del,
			String projSn, Date jtrq, String location, String jtsj, String type,
			String by1, String by2, String by3, TbsProj tbsProj,
			Set<TbsProjcfm0Attend> tbsProjcfm0AttendSet, Set<TbsProjOpinion> tbsProjOpinionSet) {
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
		this.jtrq = jtrq;
		this.location = location;
		this.jtsj = jtsj;
		this.type = type;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsProjcfm0AttendSet = tbsProjcfm0AttendSet;
		this.tbsProjOpinionSet = tbsProjOpinionSet;
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

	public void setJtrq(Date jtrq) {
		this.jtrq = jtrq;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "JTRQ")
	public Date getJtrq() {
		return jtrq;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "LOCATION", length = 60)
	public String getLocation() {
		return location;
	}

	public void setJtsj(String jtsj) {
		this.jtsj = jtsj;
	}

	@Column(name = "JTSJ",length=10)
	public String getJtsj() {
		return jtsj;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE",length=5)
	public String getType() {
		return type;
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

	public void setTbsProjcfm0AttendSet(
			Set<TbsProjcfm0Attend> tbsProjcfm0AttendSet) {
		this.tbsProjcfm0AttendSet = tbsProjcfm0AttendSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjcfm0")
	public Set<TbsProjcfm0Attend> getTbsProjcfm0AttendSet() {
		return tbsProjcfm0AttendSet;
	}
	
	public void setTbsProjOpinionSet(
			Set<TbsProjOpinion> tbsProjOpinionSet) {
		this.tbsProjOpinionSet = tbsProjOpinionSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjcfm0")
	public Set<TbsProjOpinion> getTbsProjOpinionSet() {
		return tbsProjOpinionSet;
	}

	public String toString() {
		return "TbsProjcfm0 [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",memo=" + memo
				+ ",del=" + del + ",projSn=" + projSn + ",jtrq=" + jtrq
				+ ",location=" + location + ",jtsj=" + jtsj + ",type=" + type
				+ ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3 + ",tbsProj="
				+ tbsProj + ",tbsProjcfm0AttendSet=" + tbsProjcfm0AttendSet
				+ ",tbsProjOpinionSet=" + tbsProjOpinionSet + "]";
	}

}
