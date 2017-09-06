package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * tbs_bas_cggtyp:反担保信息类型-质押和抵押才有
 */
@Entity
@Table(name = "tbs_bas_cggtyp")
public class TbsBasCggtyp implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID:
	 */
	private int id;

	/**
	 * 名称，注意，在操作单据的界面内，如果是质押资产，则只需要出现质押相关的类型即可。抵押资产，只需要出现抵押相关的资产:名称，注意，在操作单据的界面内，如果是质押资产，则只需要出现质押相关的类型即可。抵押资产，只需要出现抵押相关的资产
	 */
	private String name;

	/**
	 * 默认为1，生效:默认为1，生效
	 */
	private boolean valid;

	/**
	 * SUBTABLE:
	 */
	private String subtable;

	/**
	 * tbs_bas_cggtyp:反担保信息类型-质押和抵押才有
	 */
	private TbsBasCggtyp tbsBasCggtyp;

	/**
	 * tbs_bas_cggtyp:反担保信息类型-质押和抵押才有
	 */
	private Set<TbsBasCggtyp> tbsBasCggtypSet = new HashSet<TbsBasCggtyp>(0);

	public TbsBasCggtyp() {
		super();
	}

	public TbsBasCggtyp(int id, String name, boolean valid, String subtable,
			TbsBasCggtyp tbsBasCggtyp, Set<TbsBasCggtyp> tbsBasCggtypSet) {
		super();
		this.id = id;
		this.name = name;
		this.valid = valid;
		this.subtable = subtable;
		this.tbsBasCggtyp = tbsBasCggtyp;
		this.tbsBasCggtypSet = tbsBasCggtypSet;
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

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 10)
	public String getName() {
		return name;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
	}

	public void setSubtable(String subtable) {
		this.subtable = subtable;
	}

	@Column(name = "SUBTABLE", length = 20)
	public String getSubtable() {
		return subtable;
	}

	public void setTbsBasCggtyp(TbsBasCggtyp tbsBasCggtyp) {
		this.tbsBasCggtyp = tbsBasCggtyp;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PID")
	public TbsBasCggtyp getTbsBasCggtyp() {
		return tbsBasCggtyp;
	}

	public void setTbsBasCggtypSet(Set<TbsBasCggtyp> tbsBasCggtypSet) {
		this.tbsBasCggtypSet = tbsBasCggtypSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsBasCggtyp")
	public Set<TbsBasCggtyp> getTbsBasCggtypSet() {
		return tbsBasCggtypSet;
	}

	public String toString() {
		return "TbsBasCggtyp [id=" + id + ",name=" + name + ",valid=" + valid
				+ ",subtable=" + subtable + ",tbsBasCggtyp=" + tbsBasCggtyp
				+ ",tbsBasCggtypSet=" + tbsBasCggtypSet + "]";
	}

}
