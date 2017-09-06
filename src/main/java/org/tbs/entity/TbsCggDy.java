package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tbs_cgg_dy:反担保信息-抵押
 */
@Entity
@Table(name = "tbs_cgg_dy")
public class TbsCggDy implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 抵押人，默认为客户名称，从cus_id同步过来，但是可以修改以varchar存入数据库:抵押人，默认为客户名称，从cus_id同步过来，但是可以修改以varchar存入数据库
	 */
	private String cusName;

	/**
	 * 录入人:录入人
	 */
	private int keyinId;

	/**
	 * 反担保编号=‘DY’+日期+序号:反担保编号=‘DY’+日期+序号
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
	 * 名称:名称
	 */
	private String mc;

	/**
	 * 数量:数量
	 */
	private float sl;

	/**
	 * 品牌型号:品牌型号
	 */
	private String ppxh;

	/**
	 * 品牌:品牌
	 */
	private String pp;

	/**
	 * 型号:型号
	 */
	private String xh;

	/**
	 * 所在地,坐落:所在地,坐落
	 */
	private String szd;

	/**
	 * 取得价格/发票价格（元）:取得价格/发票价格（元）
	 */
	private BigDecimal fpjg;

	/**
	 * 账面净值（元）:账面净值（元）
	 */
	private BigDecimal zmjz;

	/**
	 * 土地用途（工矿仓储/住宅/商服用地/研发/林地/农地/工业建设用地）:土地用途（工矿仓储/住宅/商服用地/研发/林地/农地/工业建设用地）
	 */
	private String tdyt;

	/**
	 * 是否(浮动抵押/为在建船舶):是否(浮动抵押/为在建船舶)
	 */
	private boolean blon;

	/**
	 * 使用权类型（承包/转承包/租赁/流转）（出让/划拨）:使用权类型（承包/转承包/租赁/流转）（出让/划拨）
	 */
	private String lx;

	/**
	 * 取得方式（购买/自建):取得方式（购买/自建)
	 */
	private String qdfs;

	/**
	 * 年租金（元）:年租金（元）
	 */
	private BigDecimal nzj;

	/**
	 * 面积:面积
	 */
	private BigDecimal mj;

	/**
	 * 国籍:国籍
	 */
	private String gj;

	/**
	 * 规划用途:规划用途
	 */
	private String ghyt;

	/**
	 * 房产证编号、船舶证书号码、车辆登记证编号:房产证编号、船舶证书号码、车辆登记证编号
	 */
	private String bh;

	/**
	 * 船舶所有权证书的颁发机关:船舶所有权证书的颁发机关
	 */
	private String bzjg;

	/**
	 * 车辆类型:车辆类型
	 */
	private String cllx;

	/**
	 * 使用权到期年月:使用权到期年月
	 */
	private Date edate;

	/**
	 * (车辆购买/船舶购买、建成/土地使用权取得)年月:(车辆购买/船舶购买、建成/土地使用权取得)年月
	 */
	private Date qddate;

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
	 * BY4:
	 */
	private String by4;

	/**
	 * tbs_customer:客户基本信息
	 */
	private TbsCustomer tbsCustomer;

	public TbsCggDy() {
		super();
	}

	public TbsCggDy(int id, String cusName, int keyinId, String sn,
			boolean valid, Date timestampInput, Date timestampUpdate,
			int del, Date delDate, String memo, BigDecimal val,
			String permax, BigDecimal evaprc, BigDecimal strv,
			BigDecimal pledgeper, String sw, Date mbdate, Date medate,
			int cat1, int cat2, int cat3, String mc, float sl, String ppxh,
			String pp, String xh, String szd, BigDecimal fpjg, BigDecimal zmjz,
			String tdyt, boolean blon, String lx, String qdfs, BigDecimal nzj,
			BigDecimal mj, String gj, String ghyt, String bh, String bzjg,
			String cllx, Date edate, Date qddate, String by1, String by2,
			String by3, String by4, TbsCustomer tbsCustomer) {
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
		this.mc = mc;
		this.sl = sl;
		this.ppxh = ppxh;
		this.pp = pp;
		this.xh = xh;
		this.szd = szd;
		this.fpjg = fpjg;
		this.zmjz = zmjz;
		this.tdyt = tdyt;
		this.blon = blon;
		this.lx = lx;
		this.qdfs = qdfs;
		this.nzj = nzj;
		this.mj = mj;
		this.gj = gj;
		this.ghyt = ghyt;
		this.bh = bh;
		this.bzjg = bzjg;
		this.cllx = cllx;
		this.edate = edate;
		this.qddate = qddate;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.by4 = by4;
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

	@Column(name = "SW", length = 10)
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

	public void setMc(String mc) {
		this.mc = mc;
	}

	@Column(name = "MC", length = 50)
	public String getMc() {
		return mc;
	}

	public void setSl(float sl) {
		this.sl = sl;
	}

	@Column(name = "SL")
	public float getSl() {
		return sl;
	}

	public void setPpxh(String ppxh) {
		this.ppxh = ppxh;
	}

	@Column(name = "PPXH", length = 100)
	public String getPpxh() {
		return ppxh;
	}

	public void setPp(String pp) {
		this.pp = pp;
	}

	@Column(name = "PP", length = 100)
	public String getPp() {
		return pp;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Column(name = "XH", length = 100)
	public String getXh() {
		return xh;
	}

	public void setSzd(String szd) {
		this.szd = szd;
	}

	@Column(name = "SZD", length = 50)
	public String getSzd() {
		return szd;
	}

	public void setFpjg(BigDecimal fpjg) {
		this.fpjg = fpjg;
	}

	@Column(name = "FPJG")
	public BigDecimal getFpjg() {
		return fpjg;
	}

	public void setZmjz(BigDecimal zmjz) {
		this.zmjz = zmjz;
	}

	@Column(name = "ZMJZ")
	public BigDecimal getZmjz() {
		return zmjz;
	}

	public void setTdyt(String tdyt) {
		this.tdyt = tdyt;
	}

	@Column(name = "TDYT", length = 12)
	public String getTdyt() {
		return tdyt;
	}

	public void setBlon(boolean blon) {
		this.blon = blon;
	}

	@Column(name = "BLON")
	public boolean isBlon() {
		return blon;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	@Column(name = "LX", length = 45)
	public String getLx() {
		return lx;
	}

	public void setQdfs(String qdfs) {
		this.qdfs = qdfs;
	}

	@Column(name = "QDFS", length = 10)
	public String getQdfs() {
		return qdfs;
	}

	public void setNzj(BigDecimal nzj) {
		this.nzj = nzj;
	}

	@Column(name = "NZJ")
	public BigDecimal getNzj() {
		return nzj;
	}

	public void setMj(BigDecimal mj) {
		this.mj = mj;
	}

	@Column(name = "MJ")
	public BigDecimal getMj() {
		return mj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	@Column(name = "GJ", length = 10)
	public String getGj() {
		return gj;
	}

	public void setGhyt(String ghyt) {
		this.ghyt = ghyt;
	}

	@Column(name = "GHYT", length = 30)
	public String getGhyt() {
		return ghyt;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	@Column(name = "BH", length = 30)
	public String getBh() {
		return bh;
	}

	public void setBzjg(String bzjg) {
		this.bzjg = bzjg;
	}

	@Column(name = "BZJG", length = 60)
	public String getBzjg() {
		return bzjg;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	@Column(name = "CLLX", length = 20)
	public String getCllx() {
		return cllx;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EDATE")
	public Date getEdate() {
		return edate;
	}

	public void setQddate(Date qddate) {
		this.qddate = qddate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QDDATE")
	public Date getQddate() {
		return qddate;
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

	public void setBy4(String by4) {
		this.by4 = by4;
	}

	@Column(name = "BY4", length = 400)
	public String getBy4() {
		return by4;
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
		return "TbsCggDy [id=" + id + ",cusName=" + cusName + ",keyinId="
				+ keyinId + ",sn=" + sn + ",valid=" + valid
				+ ",timestampInput=" + timestampInput + ",timestampUpdate="
				+ timestampUpdate + ",del=" + del + ",delDate=" + delDate
				+ ",memo=" + memo + ",val=" + val + ",permax=" + permax
				+ ",evaprc=" + evaprc + ",strv=" + strv + ",pledgeper="
				+ pledgeper + ",sw=" + sw + ",mbdate=" + mbdate + ",medate="
				+ medate + ",cat1=" + cat1 + ",cat2=" + cat2 + ",cat3=" + cat3
				+ ",mc=" + mc + ",sl=" + sl + ",ppxh=" + ppxh + ",pp=" + pp
				+ ",xh=" + xh + ",szd=" + szd + ",fpjg=" + fpjg + ",zmjz="
				+ zmjz + ",tdyt=" + tdyt + ",blon=" + blon + ",lx=" + lx
				+ ",qdfs=" + qdfs + ",nzj=" + nzj + ",mj=" + mj + ",gj=" + gj
				+ ",ghyt=" + ghyt + ",bh=" + bh + ",bzjg=" + bzjg + ",cllx="
				+ cllx + ",edate=" + edate + ",qddate=" + qddate + ",by1="
				+ by1 + ",by2=" + by2 + ",by3=" + by3 + ",by4=" + by4
				+ ",tbsCustomer=" + tbsCustomer + "]";
	}

}
