package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * tbs_proj_cgg:项目与反担保的关联关系表
 */
@Entity
@Table(name = "tbs_proj_cgg")
public class TbsProjCgg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * PROJ_SN:
	 */
	private String projSn;

	/**
	 * 反担保ID:反担保ID
	 */
	private int cggId;

	/**
	 * 反担保SN:反担保SN
	 */
	private String cggSn;

	/**
	 * 插入时间戳（变更日期）:插入时间戳（变更日期）
	 */
	private Date timestampInput;

	/**
	 * 是否有效，默认为1有效，如果走了反担保解除流程，则这里改为0:是否有效，默认为1有效，如果走了反担保解除流程，则这里改为0
	 */
	private boolean valid;
	
	/**
	 * PROJ_State:
	 */
	private Integer state;
	
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

	public TbsProjCgg() {
		super();
	}

	public TbsProjCgg(int id, String projSn, int cggId, String cggSn,
			Date timestampInput, boolean valid, Integer state,String by1, String by2,
			String by3, TbsProj tbsProj) {
		super();
		this.id = id;
		this.projSn = projSn;
		this.cggId = cggId;
		this.cggSn = cggSn;
		this.timestampInput = timestampInput;
		this.valid = valid;
		this.state = state ;
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

	public void setProjSn(String projSn) {
		this.projSn = projSn;
	}

	@Column(name = "PROJ_SN", length = 20)
	public String getProjSn() {
		return projSn;
	}

	public void setCggId(int cggId) {
		this.cggId = cggId;
	}

	@Column(name = "CGG_ID")
	public int getCggId() {
		return cggId;
	}

	public void setCggSn(String cggSn) {
		this.cggSn = cggSn;
	}

	@Column(name = "CGG_SN", length = 40)
	public String getCggSn() {
		return cggSn;
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

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "STATE")
	public Integer getState() {
		return state;
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
		return "TbsProjCgg [id=" + id + ",projSn=" + projSn + ",cggId=" + cggId
				+ ",cggSn=" + cggSn + ",timestampInput=" + timestampInput
				+ ",valid=" + valid + ",state=" + state + ",by1=" + by1 + ",by2=" + by2 + ",by3="
				+ by3 + ",tbsProj=" + tbsProj + "]";
	}

}
