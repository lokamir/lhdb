package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_cgg_zy:反担保信息-质押
 */
@Entity
@Table(name = "tbs_cgg_zy")
public class TbsCggZy implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 出质人，来自cus_id的名称可修改:出质人，来自cus_id的名称可修改
	 */
	private String cusName;

	/**
	 * KEYIN_ID:
	 */
	private int keyinId;

	/**
	 * 质押编号:质押编号
	 */
	private String sn;

	/**
	 * 反担保状态【已办妥，未办妥】，默认为0未办妥，审批过后为1已办妥。:反担保状态【已办妥，未办妥】，默认为0未办妥，审批过后为1已办妥。
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
	 * 删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据:删除标记。默认为0，如果有删除动作，则改为1，系统内无法显示和操作该数据
	 */
	private int del;

	/**
	 * 删除日期:删除日期
	 */
	private Date delDate;

	/**
	 * 备注，风管经理审批反担保信息时填写的说明信息插入这里。:备注，风管经理审批反担保信息时填写的说明信息插入这里。
	 */
	private String memo;

	/**
	 * 该反担保信息的参考价值（元）:该反担保信息的参考价值（元）
	 */
	private BigDecimal val;

	/**
	 * 单笔/最高额（二选一）:单笔/最高额（二选一）
	 */
	private String permax;

	/**
	 * 评估价（元）:评估价（元）
	 */
	private BigDecimal evaprc;

	/**
	 * 设定权利价值（元）:设定权利价值（元）
	 */
	private BigDecimal strv;

	/**
	 * 抵押率（%）系统自动计算设定权利价值/评估价:抵押率（%）系统自动计算设定权利价值/评估价
	 */
	private BigDecimal pledgeper;

	/**
	 * 顺位:顺位
	 */
	private String sw;

	/**
	 * 反担保起始日:反担保起始日
	 */
	private Date mbdate;

	/**
	 * 反担保到期日:反担保到期日
	 */
	private Date medate;

	/**
	 * CAT1:
	 */
	private int cat1;

	/**
	 * CAT2:
	 */
	private int cat2;

	/**
	 * CAT3:
	 */
	private int cat3;

	/**
	 * 出质股权数额/出质基金数量/质押份额:出质股权数额/出质基金数量/质押份额
	 */
	private BigDecimal se;

	/**
	 * 出质股权所在公司名称/付款方/基金名称/开户行/签发人/商标名/信托产品名称/著作权名称/专利名称:出质股权所在公司名称/付款方/基金名称/开户行/签发人/商标名/信托产品名称/著作权名称/专利名称
	 */
	private String mc;

	/**
	 * 单号:单号
	 */
	private String dh;

	/**
	 * 兑付日/提货日期:兑付日/提货日期
	 */
	private Date dfdate;

	/**
	 * 股权所在公司性质/货物种类/基金类型/类型/信托类型/种类/专利类型:股权所在公司性质/货物种类/基金类型/类型/信托类型/种类/专利类型
	 */
	private String zllx;

	/**
	 * 货物价值/票面金额/信托总额:货物价值/票面金额/信托总额
	 */
	private BigDecimal jz;

	/**
	 * 货物数量:货物数量
	 */
	private BigDecimal sl;

	/**
	 * 经营权期限:经营权期限
	 */
	private int jyqqx;

	/**
	 * 票据编号/著作权登记号/专利号/商标注册号:票据编号/著作权登记号/专利号/商标注册号
	 */
	private String hm;

	/**
	 * 商标有限期:商标有限期
	 */
	private Date sbyxq;

	/**
	 * 是否签订三方监管协议:是否签订三方监管协议
	 */
	private boolean qddsf;

	/**
	 * 是否取得确认:是否取得确认
	 */
	private boolean qdqr;

	/**
	 * 授权公告日/著作权授权公告日:授权公告日/著作权授权公告日
	 */
	private Date ggr;

	/**
	 * 信托发行机构:信托发行机构
	 */
	private String xtjg;

	/**
	 * 预计收益率:预计收益率
	 */
	private BigDecimal syl;

	/**
	 * 账号:账号
	 */
	private String zh;

	/**
	 * 账面余额:账面余额
	 */
	private BigDecimal zmye;

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

	public TbsCggZy() {
		super();
	}

	public TbsCggZy(int id, String cusName, int keyinId, String sn,
			boolean valid, Date timestampInput, Date timestampUpdate,
			int del, Date delDate, String memo, BigDecimal val,
			String permax, BigDecimal evaprc, BigDecimal strv,
			BigDecimal pledgeper, String sw, Date mbdate, Date medate,
			int cat1, int cat2, int cat3, BigDecimal se, String mc, String dh,
			Date dfdate, String zllx, BigDecimal jz, BigDecimal sl, int jyqqx,
			String hm, Date sbyxq, boolean qddsf, boolean qdqr, Date ggr,
			String xtjg, BigDecimal syl, String zh, BigDecimal zmye,
			String by1, String by2, String by3, TbsCustomer tbsCustomer) {
		super();
		this.id = id;
		this.cusName = cusName;
		this.keyinId = keyinId;
		this.sn = sn;
		this.valid = valid;
		this.timestampInput = timestampInput;
		this.timestampUpdate = timestampUpdate;
		this.del = del;
		this.delDate = delDate;
		this.memo = memo;
		this.val = val;
		this.permax = permax;
		this.evaprc = evaprc;
		this.strv = strv;
		this.pledgeper = pledgeper;
		this.sw = sw;
		this.mbdate = mbdate;
		this.medate = medate;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.se = se;
		this.mc = mc;
		this.dh = dh;
		this.dfdate = dfdate;
		this.zllx = zllx;
		this.jz = jz;
		this.sl = sl;
		this.jyqqx = jyqqx;
		this.hm = hm;
		this.sbyxq = sbyxq;
		this.qddsf = qddsf;
		this.qdqr = qdqr;
		this.ggr = ggr;
		this.xtjg = xtjg;
		this.syl = syl;
		this.zh = zh;
		this.zmye = zmye;
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

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	@Column(name = "CUS_NAME", length = 200)
	public String getCusName() {
		return cusName;
	}

	public void setKeyinId(int keyinId) {
		this.keyinId = keyinId;
	}

	@Column(name = "KEYIN_ID")
	public int getKeyinId() {
		return keyinId;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "SN", length = 40)
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

	public void setDel(int del) {
		this.del = del;
	}

	@Column(name = "DEL")
	public int isDel() {
		return del;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEL_DATE")
	public Date getDelDate() {
		return delDate;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "MEMO", length = 800)
	public String getMemo() {
		return memo;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}

	@Column(name = "VAL")
	public BigDecimal getVal() {
		return val;
	}

	public void setPermax(String permax) {
		this.permax = permax;
	}

	@Column(name = "PERMAX", length = 6)
	public String getPermax() {
		return permax;
	}

	public void setEvaprc(BigDecimal evaprc) {
		this.evaprc = evaprc;
	}

	@Column(name = "EVAPRC")
	public BigDecimal getEvaprc() {
		return evaprc;
	}

	public void setStrv(BigDecimal strv) {
		this.strv = strv;
	}

	@Column(name = "STRV")
	public BigDecimal getStrv() {
		return strv;
	}

	public void setPledgeper(BigDecimal pledgeper) {
		this.pledgeper = pledgeper;
	}

	@Column(name = "PLEDGEPER")
	public BigDecimal getPledgeper() {
		return pledgeper;
	}

	public void setSw(String sw) {
		this.sw = sw;
	}

	@Column(name = "SW", length = 45)
	public String getSw() {
		return sw;
	}

	public void setMbdate(Date mbdate) {
		this.mbdate = mbdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MBDATE")
	public Date getMbdate() {
		return mbdate;
	}

	public void setMedate(Date medate) {
		this.medate = medate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MEDATE")
	public Date getMedate() {
		return medate;
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

	public void setSe(BigDecimal se) {
		this.se = se;
	}

	@Column(name = "SE")
	public BigDecimal getSe() {
		return se;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	@Column(name = "MC", length = 100)
	public String getMc() {
		return mc;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	@Column(name = "DH", length = 100)
	public String getDh() {
		return dh;
	}

	public void setDfdate(Date dfdate) {
		this.dfdate = dfdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DFDATE")
	public Date getDfdate() {
		return dfdate;
	}

	public void setZllx(String zllx) {
		this.zllx = zllx;
	}

	@Column(name = "ZLLX", length = 100)
	public String getZllx() {
		return zllx;
	}

	public void setJz(BigDecimal jz) {
		this.jz = jz;
	}

	@Column(name = "JZ")
	public BigDecimal getJz() {
		return jz;
	}

	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}

	@Column(name = "SL")
	public BigDecimal getSl() {
		return sl;
	}

	public void setJyqqx(int jyqqx) {
		this.jyqqx = jyqqx;
	}

	@Column(name = "JYQQX")
	public int getJyqqx() {
		return jyqqx;
	}

	public void setHm(String hm) {
		this.hm = hm;
	}

	@Column(name = "HM", length = 100)
	public String getHm() {
		return hm;
	}

	public void setSbyxq(Date sbyxq) {
		this.sbyxq = sbyxq;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SBYXQ")
	public Date getSbyxq() {
		return sbyxq;
	}

	public void setQddsf(boolean qddsf) {
		this.qddsf = qddsf;
	}

	@Column(name = "QDDSF")
	public boolean isQddsf() {
		return qddsf;
	}

	public void setQdqr(boolean qdqr) {
		this.qdqr = qdqr;
	}

	@Column(name = "QDQR")
	public boolean isQdqr() {
		return qdqr;
	}

	public void setGgr(Date ggr) {
		this.ggr = ggr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GGR")
	public Date getGgr() {
		return ggr;
	}

	public void setXtjg(String xtjg) {
		this.xtjg = xtjg;
	}

	@Column(name = "XTJG", length = 60)
	public String getXtjg() {
		return xtjg;
	}

	public void setSyl(BigDecimal syl) {
		this.syl = syl;
	}

	@Column(name = "SYL")
	public BigDecimal getSyl() {
		return syl;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	@Column(name = "ZH", length = 30)
	public String getZh() {
		return zh;
	}

	public void setZmye(BigDecimal zmye) {
		this.zmye = zmye;
	}

	@Column(name = "ZMYE")
	public BigDecimal getZmye() {
		return zmye;
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
		return "TbsCggZy [id=" + id + ",cusName=" + cusName + ",keyinId="
				+ keyinId + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",del=" + del + ",delDate=" + delDate
				+ ",memo=" + memo + ",val=" + val + ",permax=" + permax
				+ ",evaprc=" + evaprc + ",strv=" + strv + ",pledgeper="
				+ pledgeper + ",sw=" + sw + ",mbdate=" + mbdate + ",medate="
				+ medate + ",cat1=" + cat1 + ",cat2=" + cat2 + ",cat3=" + cat3
				+ ",se=" + se + ",mc=" + mc + ",dh=" + dh + ",dfdate=" + dfdate
				+ ",zllx=" + zllx + ",jz=" + jz + ",sl=" + sl + ",jyqqx="
				+ jyqqx + ",hm=" + hm + ",sbyxq=" + sbyxq + ",qddsf=" + qddsf
				+ ",qdqr=" + qdqr + ",ggr=" + ggr + ",xtjg=" + xtjg + ",syl="
				+ syl + ",zh=" + zh + ",zmye=" + zmye + ",by1=" + by1 + ",by2="
				+ by2 + ",by3=" + by3 + ",tbsCustomer=" + tbsCustomer + "]";
	}

}
