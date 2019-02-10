package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_projchange_majcont:项目变更。(授信额度，期限，反担保)
 */
@Entity
@Table(name = "tbs_projchange_majcont")
public class TbsProjchangeMajcont implements Serializable {

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
	 * PROJ_SN:
	 */
	private String projSn;

	/**
	 * TIMESTAMP_INPUT:
	 */
	private Date timestampInput;

	/**
	 * KEYIN_ID:
	 */
	private int keyinId;

	/**
	 * VALID:
	 */
	private int valid;

	/**
	 * DEL:
	 */
	private boolean del;

	/**
	 * NEWBDATE:
	 */
	private Date newbdate;

	/**
	 * NEWEDATE:
	 */
	private Date newedate;

	/**
	 * NEWPERIODCFM:
	 */
	private int newperiodcfm;

	/**
	 * OLDBDATE:
	 */
	private Date oldbdate;

	/**
	 * OLDEDATE:
	 */
	private Date oldedate;

	/**
	 * OLDPERIODCFM:
	 */
	private int oldperiodcfm;

	/**
	 * CGGCHANGE:
	 */
	private boolean cggchange;

	/**
	 * 修改proj.INITFALOC:修改proj.INITFALOC
	 */
	private BigDecimal newfaloc;

	/**
	 * NEWNFALOC:
	 */
	private BigDecimal newnfaloc;

	/**
	 * NEWOTLOC:
	 */
	private BigDecimal newotloc;

	/**
	 * NEWTOTLOC:
	 */
	private BigDecimal newtotloc;

	/**
	 * proj.intfaloc:proj.intfaloc
	 */
	private BigDecimal oldfaloc;

	/**
	 * OLDNFALOC:
	 */
	private BigDecimal oldnfaloc;

	/**
	 * OLDOTLOC:
	 */
	private BigDecimal oldotloc;

	/**
	 * OLDTOTLOC:
	 */
	private BigDecimal oldtotloc;

	/**
	 * OLDPROJNAME:
	 */
	private String oldprojname;

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
	 * 是否关联授信合并超限
	 */
	private Boolean overlimit;
	
	
	private TbsProj tbsProj;
	private String memo;
	private Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet = new HashSet<TbsProjchangeMajcontCfm>(0);
	
	public TbsProjchangeMajcont() {
		super();
	}

	public TbsProjchangeMajcont(int id, String sn, String projSn,
			Date timestampInput, int keyinId, int valid, boolean del,
			Date newbdate, Date newedate, int newperiodcfm, Date oldbdate,
			Date oldedate, int oldperiodcfm, boolean cggchange, String memo,
			BigDecimal newfaloc, BigDecimal newnfaloc, BigDecimal newotloc,
			BigDecimal newtotloc, BigDecimal oldfaloc, BigDecimal oldnfaloc,
			BigDecimal oldotloc, BigDecimal oldtotloc, String oldprojname,
			String by1, String by2, String by3, Boolean overlimit,TbsProj tbsProj,
			Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet) {
		super();
		this.id = id;
		this.sn = sn;
		this.projSn = projSn;
		this.timestampInput = timestampInput;
		this.keyinId = keyinId;
		this.valid = valid;
		this.del = del;
		this.newbdate = newbdate;
		this.newedate = newedate;
		this.newperiodcfm = newperiodcfm;
		this.oldbdate = oldbdate;
		this.oldedate = oldedate;
		this.oldperiodcfm = oldperiodcfm;
		this.cggchange = cggchange;
		this.newfaloc = newfaloc;
		this.newnfaloc = newnfaloc;
		this.newotloc = newotloc;
		this.newtotloc = newtotloc;
		this.oldfaloc = oldfaloc;
		this.oldnfaloc = oldnfaloc;
		this.oldotloc = oldotloc;
		this.oldtotloc = oldtotloc;
		this.oldprojname = oldprojname;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.overlimit = overlimit;
		this.tbsProj = tbsProj;
		this.memo = memo;
		this.tbsProjchangeMajcontCfmSet = tbsProjchangeMajcontCfmSet;
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

	public void setProjSn(String projSn) {
		this.projSn = projSn;
	}

	@Column(name = "PROJ_SN", length = 20)
	public String getProjSn() {
		return projSn;
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

	public void setNewbdate(Date newbdate) {
		this.newbdate = newbdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NEWBDATE")
	public Date getNewbdate() {
		return newbdate;
	}

	public void setNewedate(Date newedate) {
		this.newedate = newedate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NEWEDATE")
	public Date getNewedate() {
		return newedate;
	}

	public void setNewperiodcfm(int newperiodcfm) {
		this.newperiodcfm = newperiodcfm;
	}

	@Column(name = "NEWPERIODCFM")
	public int getNewperiodcfm() {
		return newperiodcfm;
	}

	public void setOldbdate(Date oldbdate) {
		this.oldbdate = oldbdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OLDBDATE")
	public Date getOldbdate() {
		return oldbdate;
	}

	public void setOldedate(Date oldedate) {
		this.oldedate = oldedate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OLDEDATE")
	public Date getOldedate() {
		return oldedate;
	}

	public void setOldperiodcfm(int oldperiodcfm) {
		this.oldperiodcfm = oldperiodcfm;
	}

	@Column(name = "OLDPERIODCFM")
	public int getOldperiodcfm() {
		return oldperiodcfm;
	}

	public void setCggchange(boolean cggchange) {
		this.cggchange = cggchange;
	}

	@Column(name = "CGGCHANGE")
	public boolean isCggchange() {
		return cggchange;
	}

	public void setNewfaloc(BigDecimal newfaloc) {
		this.newfaloc = newfaloc;
	}

	@Column(name = "NEWFALOC")
	public BigDecimal getNewfaloc() {
		return newfaloc;
	}

	public void setNewnfaloc(BigDecimal newnfaloc) {
		this.newnfaloc = newnfaloc;
	}

	@Column(name = "NEWNFALOC")
	public BigDecimal getNewnfaloc() {
		return newnfaloc;
	}

	public void setNewotloc(BigDecimal newotloc) {
		this.newotloc = newotloc;
	}

	@Column(name = "NEWOTLOC")
	public BigDecimal getNewotloc() {
		return newotloc;
	}

	public void setNewtotloc(BigDecimal newtotloc) {
		this.newtotloc = newtotloc;
	}

	@Column(name = "NEWTOTLOC")
	public BigDecimal getNewtotloc() {
		return newtotloc;
	}

	public void setOldfaloc(BigDecimal oldfaloc) {
		this.oldfaloc = oldfaloc;
	}

	@Column(name = "OLDFALOC")
	public BigDecimal getOldfaloc() {
		return oldfaloc;
	}

	public void setOldnfaloc(BigDecimal oldnfaloc) {
		this.oldnfaloc = oldnfaloc;
	}

	@Column(name = "OLDNFALOC")
	public BigDecimal getOldnfaloc() {
		return oldnfaloc;
	}

	public void setOldotloc(BigDecimal oldotloc) {
		this.oldotloc = oldotloc;
	}

	@Column(name = "OLDOTLOC")
	public BigDecimal getOldotloc() {
		return oldotloc;
	}

	public void setOldtotloc(BigDecimal oldtotloc) {
		this.oldtotloc = oldtotloc;
	}

	@Column(name = "OLDTOTLOC")
	public BigDecimal getOldtotloc() {
		return oldtotloc;
	}

	public void setOldprojname(String oldprojname) {
		this.oldprojname = oldprojname;
	}

	@Column(name = "OLDPROJNAME", length = 200)
	public String getOldprojname() {
		return oldprojname;
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

	public void setOverlimit(Boolean overlimit) {
		this.overlimit = overlimit;
	}

	@Column(name = "OVERLIMIT")
	public Boolean getOverlimit() {
		return overlimit;
	}
	
	public void setTbsProj(TbsProj tbsProj) {
		this.tbsProj = tbsProj;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJ_ID")
	public TbsProj getTbsProj() {
		return tbsProj;
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
	
	public void setTbsProjchangeMajcontCfmSet(
			Set<TbsProjchangeMajcontCfm> tbsProjchangeMajcontCfmSet) {
		this.tbsProjchangeMajcontCfmSet = tbsProjchangeMajcontCfmSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsProjchangeMajcont")
	public Set<TbsProjchangeMajcontCfm> getTbsProjchangeMajcontCfmSet() {
		return tbsProjchangeMajcontCfmSet;
	}
	
	public String toString() {
		return "TbsProjchangeMajcont [id=" + id + ",sn=" + sn + ",projSn="
				+ projSn + ",timestampInput=" + timestampInput + ",keyinId="
				+ keyinId + ",valid=" + valid + ",del=" + del + ",newbdate="
				+ newbdate + ",newedate=" + newedate + ",newperiodcfm="
				+ newperiodcfm + ",oldbdate=" + oldbdate + ",oldedate="
				+ oldedate + ",oldperiodcfm=" + oldperiodcfm + ",cggchange="
				+ cggchange + ",newfaloc=" + newfaloc + ",newnfaloc="
				+ newnfaloc + ",newotloc=" + newotloc + ",newtotloc="
				+ newtotloc + ",oldfaloc=" + oldfaloc + ",oldnfaloc="
				+ oldnfaloc + ",oldotloc=" + oldotloc + ",oldtotloc="
				+ oldtotloc + ",oldprojname=" + oldprojname + ",by1=" + by1
				+ ",by2=" + by2 + ",by3=" + by3 + ",overlimit=" + overlimit
				+ ",tbsProj=" + tbsProj + ",memo=" + memo
				+ ",tbsProjchangeMajcontCfmSet=" + tbsProjchangeMajcontCfmSet
				+ "]";
	}

}
