package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_cgg_bzjzy:反担保信息-保证金质押
 */
@Entity
@Table(name = "tbs_cgg_bzjzy")
public class TbsCggBzjzy implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 反担保信息编号=catgory+'-'+日期+序列号001开始每个日期后都是新序号:反担保信息编号=catgory+'-'+日期+序列号001开始每个日期后都是新序号
	 */
	private String sn;

	/**
	 * 反担保状态【已办妥，未办妥】，默认为0无效，审批过后为1有效。:反担保状态【已办妥，未办妥】，默认为0无效，审批过后为1有效。
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
	 * 反担保总分类:反担保总分类
	 */
	private int cat1;

	/**
	 * 反担保大类:反担保大类
	 */
	private int cat2;

	/**
	 * 反担保类型:反担保类型
	 */
	private int cat3;

	/**
	 * 录入人:录入人
	 */
	private int keyinId;

	/**
	 * 出质人名称来自cus_id可修改:出质人名称来自cus_id可修改
	 */
	private String cusName;

	/**
	 * 该反担保信息的参考价值:该反担保信息的参考价值
	 */
	private BigDecimal val;

	/**
	 * 保证金金额（元）:保证金金额（元）
	 */
	private BigDecimal bzjje;

	/**
	 * 备注:备注
	 */
	private String memo;

	/**
	 * 删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据:删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据
	 */
	private int del;

	/**
	 * 起始日:起始日
	 */
	private Date bdate;

	/**
	 * 到期日:到期日
	 */
	private Date edate;

	/**
	 * SW:
	 */
	private String sw;

	/**
	 * 关联文件上传表中的ID，如果有上传了多个文件，则这里用加号或者逗号作为分隔符把ID号分隔开。:关联文件上传表中的ID，如果有上传了多个文件，则这里用加号或者逗号作为分隔符把ID号分隔开。
	 */
	private String fulId;

	/**
	 * 删除日期:删除日期
	 */
	private Date delDate;

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
	 * tbs_customer:客户基本信息
	 */
	private TbsCustomer tbsCustomer;

	public TbsCggBzjzy() {
		super();
	}

	public TbsCggBzjzy(int id, String sn, boolean valid, Date timestampInput,
			Date timestampUpdate, int cat1, int cat2, int cat3, int keyinId,
			String cusName, BigDecimal val, BigDecimal bzjje, String memo,
			int del, Date bdate, Date edate, String sw, String fulId,
			Date delDate, String by1, String by2, String by3,
			TbsCustomer tbsCustomer) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.keyinId = keyinId;
		this.cusName = cusName;
		this.val = val;
		this.bzjje = bzjje;
		this.memo = memo;
		this.del = del;
		this.bdate = bdate;
		this.edate = edate;
		this.sw = sw;
		this.fulId = fulId;
		this.delDate = delDate;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsCustomer = tbsCustomer;
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

	@Column(name = "SN", length = 20, unique = true, nullable = false)
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

	public void setCat1(int cat1) {
		this.cat1 = cat1;
	}

	@Column(name = "CAT1", nullable = false)
	public int getCat1() {
		return cat1;
	}

	public void setCat2(int cat2) {
		this.cat2 = cat2;
	}

	@Column(name = "CAT2", nullable = false)
	public int getCat2() {
		return cat2;
	}

	public void setCat3(int cat3) {
		this.cat3 = cat3;
	}

	@Column(name = "CAT3", nullable = false)
	public int getCat3() {
		return cat3;
	}

	public void setKeyinId(int keyinId) {
		this.keyinId = keyinId;
	}

	@Column(name = "KEYIN_ID")
	public int getKeyinId() {
		return keyinId;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	@Column(name = "CUS_NAME", length = 200)
	public String getCusName() {
		return cusName;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}

	@Column(name = "VAL")
	public BigDecimal getVal() {
		return val;
	}

	public void setBzjje(BigDecimal bzjje) {
		this.bzjje = bzjje;
	}

	@Column(name = "BZJJE")
	public BigDecimal getBzjje() {
		return bzjje;
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

	public void setDel(int del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public int isDel() {
		return del;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BDATE")
	public Date getBdate() {
		return bdate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EDATE")
	public Date getEdate() {
		return edate;
	}

	public void setSw(String sw) {
		this.sw = sw;
	}

	@Column(name = "SW", length = 45)
	public String getSw() {
		return sw;
	}

	public void setFulId(String fulId) {
		this.fulId = fulId;
	}

	@Column(name = "FUL_ID", length = 50)
	public String getFulId() {
		return fulId;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEL_DATE")
	public Date getDelDate() {
		return delDate;
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

	public void setTbsCustomer(TbsCustomer tbsCustomer) {
		this.tbsCustomer = tbsCustomer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUS_ID")
	public TbsCustomer getTbsCustomer() {
		return tbsCustomer;
	}

	public String toString() {
		return "TbsCggBzjzy [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",cat1=" + cat1 + ",cat2=" + cat2
				+ ",cat3=" + cat3 + ",keyinId=" + keyinId + ",cusName="
				+ cusName + ",val=" + val + ",bzjje=" + bzjje + ",memo=" + memo
				+ ",del=" + del + ",bdate=" + bdate + ",edate=" + edate
				+ ",sw=" + sw + ",fulId=" + fulId + ",delDate=" + delDate
				+ ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3
				+ ",tbsCustomer=" + tbsCustomer + "]";
	}

}
