package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * tbs_fun_ful_typ:
 */
@Entity
@Table(name = "tbs_fun_ful_typ")
public class TbsFunFulTyp implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * TIMESTAMP_INPUT:
	 */
	private Date timestampInput;

	/**
	 * TIMESTAMP_UPDATE:
	 */
	private Date timestampUpdate;

	/**
	 * TYPE:
	 */
	private String type;

	/**
	 * STEP:
	 */
	private String step;

	/**
	 * DEL:
	 */
	private boolean del;

	public TbsFunFulTyp() {
		super();
	}

	public TbsFunFulTyp(int id, Date timestampInput, Date timestampUpdate,
			String type, String step, boolean del) {
		super();
		this.id = id;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.type = type;
		this.step = step;
		this.del = del;
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
	@Column(name = "TIMESTAMP_INPUT")
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setTimestampUpdate(Date timestampUpdate) {
		this.timestampUpdate = timestampUpdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_UPDATE")
	public Date getTimestampUpdate() {
		return timestampUpdate;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE", length = 45)
	public String getType() {
		return type;
	}

	public void setStep(String step) {
		this.step = step;
	}

	@Column(name = "STEP", length = 45)
	public String getStep() {
		return step;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}

	public String toString() {
		return "TbsFunFulTyp [id=" + id + ",timestampInput=" + timestampInput
				+ ",timestampUpdate=" + timestampUpdate + ",type=" + type
				+ ",step=" + step + ",del=" + del + "]";
	}

}
