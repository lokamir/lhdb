package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_risk_projcfm:风险控制委员会会议（含决议）
 */
@Entity
@Table(name = "tbs_risk_projcfm")
public class TbsRiskProjcfm implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 表单编号='锡联担保风审字'+单据日期+序号:表单编号='锡联担保风审字'+单据日期+序号
	 */
	private String sn;

	/**
	 * 是否有效，默认为0无效，审批过后为1有效。:是否有效，默认为0无效，审批过后为1有效。
	 */
	private int valid;
	
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
	 * PROJ_SN:
	 */
	private String projSn;

	/**
	 * 会议日期:会议日期
	 */
	private Date jtrq;

	/**
	 * 会议时间:会议时间
	 */
	private String jtsj;

	/**
	 * 会议地点:会议地点
	 */
	private String location;

	/**
	 * 上会类型（风险项目，代偿项目）:上会类型（风险项目，代偿项目）
	 */
	private String typ;

	/**
	 * 决议:决议
	 */
	private String results;

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
	 * tbs_risk_projcfm_attend:风险控制委员会会议-参会人员子表
	 */
	private Set<TbsRiskProjcfmAttend> tbsRiskProjcfmAttendSet = new HashSet<TbsRiskProjcfmAttend>(
			0);
	
	private Bdf2User bdf2User;
	private Bdf2Dept bdf2Dept;
	private TbsProj tbsProj;

	public TbsRiskProjcfm() {
		super();
	}

	public TbsRiskProjcfm(int id, String sn, int valid,
			Date timestampInput, Date timestampUpdate, int keyinId,
			String memo, boolean del, String projSn, Date jtrq, String jtsj,
			String location, String typ, String results, String by1,
			String by2, String by3, Bdf2User bdf2User, Bdf2Dept bdf2Dept,TbsProj tbsProj,
			Set<TbsRiskProjcfmAttend> tbsRiskProjcfmAttendSet) {
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
		this.jtsj = jtsj;
		this.location = location;
		this.typ = typ;
		this.results = results;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsRiskProjcfmAttendSet = tbsRiskProjcfmAttendSet;
		this.bdf2User = bdf2User;
		this.bdf2Dept = bdf2Dept;
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

	@Column(name = "SN", length = 25)
	public String getSn() {
		return sn;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public int getValid() {
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "JTRQ")
	public Date getJtrq() {
		return jtrq;
	}

	public void setJtsj(String jtsj) {
		this.jtsj = jtsj;
	}

	@Column(name = "JTSJ", length = 30)
	public String getJtsj() {
		return jtsj;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "LOCATION", length = 60)
	public String getLocation() {
		return location;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	@Column(name = "TYP", length = 10)
	public String getTyp() {
		return typ;
	}

	public void setResults(String results) {
		this.results = results;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "RESULTS", columnDefinition = "CLOB")
	public String getResults() {
		return results;
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

	public void setTbsRiskProjcfmAttendSet(
			Set<TbsRiskProjcfmAttend> tbsRiskProjcfmAttendSet) {
		this.tbsRiskProjcfmAttendSet = tbsRiskProjcfmAttendSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsRiskProjcfm")
	public Set<TbsRiskProjcfmAttend> getTbsRiskProjcfmAttendSet() {
		return tbsRiskProjcfmAttendSet;
	}

	public void setBdf2User(Bdf2User bdf2User) {
		this.bdf2User = bdf2User;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MGMUID")
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
	
	public void setTbsProj(TbsProj tbsProj) {
		this.tbsProj = tbsProj;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJ_ID")
	public TbsProj getTbsProj() {
		return tbsProj;
	}
	
	public String toString() {
		return "TbsRiskProjcfm [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",memo=" + memo
				+ ",del=" + del + ",projSn=" + projSn + ",jtrq=" + jtrq
				+ ",jtsj=" + jtsj + ",location=" + location + ",typ=" + typ
				+ ",results=" + results + ",by1=" + by1 + ",by2=" + by2
				+ ",by3=" + by3 + ",tbsRiskProjcfmAttendSet="
				+ tbsRiskProjcfmAttendSet + ",bdf2User=" + bdf2User + ",bdf2Dept=" + bdf2Dept 
				+ ",tbsProj=" + tbsProj + "]";
	}

}
