package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * tbs_fun_seq:生成共用序列号的表
 */
@Entity
@Table(name = "tbs_fun_seq")
public class TbsFunSeq implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * seq_name:
	 */
	private String seqName;

	/**
	 * current_value:
	 */
	private int currentValue;

	/**
	 * increment:
	 */
	private int increment;

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

	public TbsFunSeq() {
		super();
	}

	public TbsFunSeq(String seqName, int currentValue, int increment,
			String by1, String by2, String by3) {
		super();
		this.seqName = seqName;
		this.currentValue = currentValue;
		this.increment = increment;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	@Id
	@Column(name = "seq_name", length = 20, nullable = false)
	public String getSeqName() {
		return seqName;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}

	@Column(name = "current_value", nullable = false)
	public int getCurrentValue() {
		return currentValue;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	@Column(name = "increment", nullable = false)
	public int getIncrement() {
		return increment;
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

	public String toString() {
		return "TbsFunSeq [seqName=" + seqName + ",currentValue="
				+ currentValue + ",increment=" + increment + ",by1=" + by1
				+ ",by2=" + by2 + ",by3=" + by3 + "]";
	}

}
