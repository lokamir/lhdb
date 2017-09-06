package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_ptyexp:零星费用收退表
 */
@Entity
@Table(name = "tbs_ptyexp")
public class TbsPtyexp implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * PROJ_SN:
	 */
	private String sn;

	/**
	 * KEYID_ID:
	 */
	private int keyidId;

	/**
	 * 默认为0。审批通过后1:默认为0。审批通过后1
	 */
	private int valid;

	/**
	 * 费用类型【担保费、评审费、其他费用】:费用类型【担保费、评审费、其他费用】
	 */
	private String feetype;

	/**
	 * 发生日期:发生日期
	 */
	private Date date;

	/**
	 * 收款金额（元）io=1io=0退款金额（元）:收款金额（元）io=1io=0退款金额（元）
	 */
	private BigDecimal fee;

	/**
	 * 默认为1。表示:收款金额（元）如果是0表示付款金额:默认为1。表示:收款金额（元）如果是0表示付款金额
	 */
	private boolean io;

	/**
	 * 备注:备注
	 */
	private String memo;

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
	private TbsProjundwrt tbsProjundwrt;
	private boolean del;
	
	public TbsPtyexp() {
		super();
	}

	public TbsPtyexp(int id, String sn, int keyidId, int valid,
			String feetype, Date date, BigDecimal fee, boolean io, String memo,
			String by1, String by2, String by3, TbsProj tbsProj,TbsProjundwrt tbsProjundwrt, boolean del) {
		super();
		this.id = id;
		this.sn = sn;
		this.keyidId = keyidId;
		this.valid = valid;
		this.feetype = feetype;
		this.date = date;
		this.fee = fee;
		this.io = io;
		this.memo = memo;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsProj = tbsProj;
		this.tbsProjundwrt = tbsProjundwrt;
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

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "SN", length = 20)
	public String getSn() {
		return sn;
	}

	public void setKeyidId(int keyidId) {
		this.keyidId = keyidId;
	}

	@Column(name = "KEYID_ID")
	public int getKeyidId() {
		return keyidId;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public int getValid() {
		return valid;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}

	@Column(name = "FEETYPE", length = 10)
	public String getFeetype() {
		return feetype;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_")
	public Date getDate() {
		return date;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	@Column(name = "FEE")
	public BigDecimal getFee() {
		return fee;
	}

	public void setIo(boolean io) {
		this.io = io;
	}

	@Column(name = "IO")
	public boolean isIo() {
		return io;
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

	public void setTbsProjundwrt(TbsProjundwrt tbsProjundwrt) {
		this.tbsProjundwrt = tbsProjundwrt;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UNDWRT_ID")
	public TbsProjundwrt getTbsProjundwrt() {
		return tbsProjundwrt;
	}
	
	public void setDel(boolean del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public boolean isDel() {
		return del;
	}
	
	public String toString() {
		return "TbsPtyexp [id=" + id + ",sn=" + sn + ",keyidId="
				+ keyidId + ",valid=" + valid + ",feetype=" + feetype
				+ ",date=" + date + ",fee=" + fee + ",io=" + io + ",memo="
				+ memo + ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3
				+ ",tbsProj=" + tbsProj + ",tbsProjundwrt=" + tbsProjundwrt 
				+ ",del=" + del + "]";
	}

}
