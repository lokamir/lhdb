package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_cgg_grbz:反担保信息-个人保证
 */
@Entity
@Table(name = "tbs_cgg_grbz")
public class TbsCggGrbz implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 反担保信息编号:反担保信息编号
	 */
	private String sn;

	/**
	 * 反担保状态【已办妥，未办妥】，默认为0无效，审批过后为1有效。:反担保状态【已办妥，未办妥】，默认为0无效，审批过后为1有效。
	 */
	private boolean valid;

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
	 * 保证人姓名,来自cus_id可修改:保证人姓名,来自cus_id可修改
	 */
	private String cusName;

	/**
	 * 该反担保信息的参考价值(元):该反担保信息的参考价值(元)
	 */
	private BigDecimal val;

	/**
	 * 性别:性别
	 */
	private String sex;

	/**
	 * 证件号码:证件号码
	 */
	private String idcardnum;

	/**
	 * 年龄，根据身份证号自动算出:年龄，根据身份证号自动算出
	 */
	private int age;

	/**
	 * 婚姻状况，已婚，未婚，离异:婚姻状况，已婚，未婚，离异
	 */
	private String married;

	/**
	 * 联系电话:联系电话
	 */
	private String tel;

	/**
	 * 联系地址:联系地址
	 */
	private String addr;

	/**
	 * 备注:备注
	 */
	private String memo;

	/**
	 * 删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据:删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据
	 */
	private int del;

	/**
	 * 保证金额（元）:保证金额（元）
	 */
	private float bzed;

	/**
	 * 保证责任类型，两个下拉框选择[单笔/最高额][一般保证/连带责任保证],数据库直接存储值，两个值之间用分隔符分开:保证责任类型，两个下拉框选择[单笔/最高额][一般保证/连带责任保证],数据库直接存储值，两个值之间用分隔符分开
	 */
	private String bzzrlx;

	/**
	 * 起始日:起始日
	 */
	private Date bdate;

	/**
	 * 到期日:到期日
	 */
	private Date edate;

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

	/**
	 * tbs_bas_idcardtype:证件类型
	 */
	private TbsBasIdcardtype tbsBasIdcardtype;

	public TbsCggGrbz() {
		super();
	}

	public TbsCggGrbz(int id, String sn, boolean valid, int cat1, int cat2,
			int cat3, Date timestampInput, Date timestampUpdate, int keyinId,
			String cusName, BigDecimal val, String sex, String idcardnum,
			int age, String married, String tel, String addr, String memo,
			int del, float bzed, String bzzrlx, Date bdate, Date edate,
			String fulId, Date delDate, String by1, String by2, String by3,
			TbsCustomer tbsCustomer, TbsBasIdcardtype tbsBasIdcardtype) {
		super();
		this.id = id;
		this.sn = sn;
		this.valid = valid;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.keyinId = keyinId;
		this.cusName = cusName;
		this.val = val;
		this.sex = sex;
		this.idcardnum = idcardnum;
		this.age = age;
		this.married = married;
		this.tel = tel;
		this.addr = addr;
		this.memo = memo;
		this.del = del;
		this.bzed = bzed;
		this.bzzrlx = bzzrlx;
		this.bdate = bdate;
		this.edate = edate;
		this.fulId = fulId;
		this.delDate = delDate;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.tbsCustomer = tbsCustomer;
		this.tbsBasIdcardtype = tbsBasIdcardtype;
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

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "SEX", length = 2)
	public String getSex() {
		return sex;
	}

	public void setIdcardnum(String idcardnum) {
		this.idcardnum = idcardnum;
	}

	@Column(name = "IDCARDNUM", length = 60)
	public String getIdcardnum() {
		return idcardnum;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(name = "AGE")
	public int getAge() {
		return age;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	@Column(name = "MARRIED", length = 4)
	public String getMarried() {
		return married;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "TEL", length = 45)
	public String getTel() {
		return tel;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "ADDR", length = 500)
	public String getAddr() {
		return addr;
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

	public void setBzed(float bzed) {
		this.bzed = bzed;
	}

	@Column(name = "BZED")
	public float getBzed() {
		return bzed;
	}

	public void setBzzrlx(String bzzrlx) {
		this.bzzrlx = bzzrlx;
	}

	@Column(name = "BZZRLX", length = 20)
	public String getBzzrlx() {
		return bzzrlx;
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

	public void setTbsBasIdcardtype(TbsBasIdcardtype tbsBasIdcardtype) {
		this.tbsBasIdcardtype = tbsBasIdcardtype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDCARDTYPE_ID")
	public TbsBasIdcardtype getTbsBasIdcardtype() {
		return tbsBasIdcardtype;
	}

	public String toString() {
		return "TbsCggGrbz [id=" + id + ",sn=" + sn + ",valid=" + valid
				+ ",cat1=" + cat1 + ",cat2=" + cat2 + ",cat3=" + cat3
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",keyinId=" + keyinId + ",cusName="
				+ cusName + ",val=" + val + ",sex=" + sex + ",idcardnum="
				+ idcardnum + ",age=" + age + ",married=" + married + ",tel="
				+ tel + ",addr=" + addr + ",memo=" + memo + ",del=" + del
				+ ",bzed=" + bzed + ",bzzrlx=" + bzzrlx + ",bdate=" + bdate
				+ ",edate=" + edate + ",fulId=" + fulId + ",delDate=" + delDate
				+ ",by1=" + by1 + ",by2=" + by2 + ",by3=" + by3
				+ ",tbsCustomer=" + tbsCustomer + ",tbsBasIdcardtype="
				+ tbsBasIdcardtype + "]";
	}

}
