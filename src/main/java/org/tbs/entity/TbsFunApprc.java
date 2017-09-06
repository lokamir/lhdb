package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * tbs_fun_apprc:
 */
@Entity
@Table(name = "tbs_fun_apprc")
public class TbsFunApprc implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private long id;

	/**
	 * 输入时间戳:输入时间戳
	 */
	private Date timestampInput;

	/**
	 * UFLO_HIS_TASK表内的ID:UFLO_HIS_TASK表内的ID
	 */
	private long ufloid;

	/**
	 * DECS:
	 */
	private String decs;

	/**
	 * NODENAME:
	 */
	private String nodename;

	/**
	 * PROCESSID:
	 */
	private long processid;

	/**
	 * ASSIGNEE:
	 */
	private String assignee;

	/**
	 * 对应businessID
	 */
	private String bizid;

	/**
	 * CREATEDATE:
	 */
	private Date createdate;

	/**
	 * DUEDATE:
	 */
	private Date duedate;

	/**
	 * ENDDATE:
	 */
	private Date enddate;

	/**
	 * HIS_PROCESS_INSTANCE_ID_:HIS_PROCESS_INSTANCE_ID_
	 */
	private long hispid;

	/**
	 * OPINION:
	 */
	private String opinion;

	/**
	 * OWNERS:
	 */
	private String owners;

	/**
	 * PROCESS_INSTANCE_ID_:PROCESS_INSTANCE_ID_
	 */
	private long pid;

	/**
	 * ROOT_PROCESS_INSTANCE_ID_:ROOT_PROCESS_INSTANCE_ID_
	 */
	private long rootpid;

	/**
	 * STATE:
	 */
	private String state;

	/**
	 * TASKID:
	 */
	private long taskid;

	/**
	 * TASKNAME:
	 */
	private String taskname;

	/**
	 * type:type
	 */
	private String typ;

	/**
	 * URL:
	 */
	private String url;
	private String del;

	public TbsFunApprc() {
		super();
	}

	public TbsFunApprc(long id, Date timestampInput, long ufloid, String decs,
			String nodename, long processid, String assignee, String bizid,
			Date createdate, Date duedate, Date enddate, long hispid,
			String opinion, String owners, long pid, long rootpid,
			String state, long taskid, String taskname, String typ, String url, String del) {
		super();
		this.id = id;
		this.timestampInput = timestampInput;
		this.ufloid = ufloid;
		this.decs = decs;
		this.nodename = nodename;
		this.processid = processid;
		this.assignee = assignee;
		this.bizid = bizid;
		this.createdate = createdate;
		this.duedate = duedate;
		this.enddate = enddate;
		this.hispid = hispid;
		this.opinion = opinion;
		this.owners = owners;
		this.pid = pid;
		this.rootpid = rootpid;
		this.state = state;
		this.taskid = taskid;
		this.taskname = taskname;
		this.typ = typ;
		this.url = url;
		this.del = del;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	public long getId() {
		return id;
	}

	public void setTimestampInput(Date timestampInput) {
		this.timestampInput = timestampInput;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_INPUT", nullable = false)
	public Date getTimestampInput() {
		return timestampInput;
	}

	public void setUfloid(long ufloid) {
		this.ufloid = ufloid;
	}

	@Column(name = "UFLOID", nullable = false)
	public long getUfloid() {
		return ufloid;
	}

	public void setDecs(String decs) {
		this.decs = decs;
	}

	@Column(name = "DECS", length = 120)
	public String getDecs() {
		return decs;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	@Column(name = "NODENAME", length = 60)
	public String getNodename() {
		return nodename;
	}

	public void setProcessid(long processid) {
		this.processid = processid;
	}

	@Column(name = "PROCESSID")
	public long getProcessid() {
		return processid;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	@Column(name = "ASSIGNEE", length = 60)
	public String getAssignee() {
		return assignee;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	@Column(name = "BIZID", length = 60)
	public String getBizid() {
		return bizid;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreatedate() {
		return createdate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DUEDATE")
	public Date getDuedate() {
		return duedate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDATE")
	public Date getEnddate() {
		return enddate;
	}

	public void setHispid(long hispid) {
		this.hispid = hispid;
	}

	@Column(name = "HISPID")
	public long getHispid() {
		return hispid;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Column(name = "OPINION", length = 200)
	public String getOpinion() {
		return opinion;
	}

	public void setOwners(String owners) {
		this.owners = owners;
	}

	@Column(name = "OWNERS", length = 60)
	public String getOwners() {
		return owners;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	@Column(name = "PID")
	public long getPid() {
		return pid;
	}

	public void setRootpid(long rootpid) {
		this.rootpid = rootpid;
	}

	@Column(name = "ROOTPID")
	public long getRootpid() {
		return rootpid;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "STATE", length = 20)
	public String getState() {
		return state;
	}

	public void setTaskid(long taskid) {
		this.taskid = taskid;
	}

	@Column(name = "TASKID")
	public long getTaskid() {
		return taskid;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	@Column(name = "TASKNAME", length = 60)
	public String getTaskname() {
		return taskname;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	@Column(name = "TYP", length = 20)
	public String getTyp() {
		return typ;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "URL", length = 120)
	public String getUrl() {
		return url;
	}

	public void setDel(String del) {
		this.del = del;
	}

	@Column(name = "DEL", length = 45)
	public String getDel() {
		return del;
	}
	
	public String toString() {
		return "TbsFunApprc [id=" + id + ",timestampInput=" + timestampInput
				+ ",ufloid=" + ufloid + ",decs=" + decs + ",nodename="
				+ nodename + ",processid=" + processid + ",assignee="
				+ assignee + ",bizid=" + bizid + ",createdate=" + createdate
				+ ",duedate=" + duedate + ",enddate=" + enddate + ",hispid="
				+ hispid + ",opinion=" + opinion + ",owners=" + owners
				+ ",pid=" + pid + ",rootpid=" + rootpid + ",state=" + state
				+ ",taskid=" + taskid + ",taskname=" + taskname + ",typ=" + typ
				+ ",url=" + url + ",del=" + del + "]";
	}

}
