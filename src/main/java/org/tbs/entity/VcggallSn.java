package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;


@Embeddable  
public class VcggallSn implements Serializable {
	    
	private static final long serialVersionUID = 1L;
		
		//Fields view里面的字段
		private int tableid;
		private String sn;
		private int cusid;
		private String cusname;
		private int cat1;
		private String cat1name;
		private int cat2;
		private String cat2name;
		private int cat3;
		private String cat3name;

		//Constructors view里面的构造对应,具体的项目 
		//default
		public VcggallSn() {
		
		}
		//full
		public VcggallSn(int tableid, String sn, int cusid, String cusname, int cat1,
				String cat1name, int cat2, String cat2name, int cat3,
				String cat3name) {
			this.tableid = tableid;
			this.sn = sn;
			this.cusid = cusid;
			this.cusname = cusname;
			this.cat1 = cat1;
			this.cat1name = cat1name;
			this.cat2 = cat2;
			this.cat2name = cat2name;
			this.cat3 = cat3;
			this.cat3name = cat3name;
		}

		//Property accessors
		@Column(name = "TABLEID", length = 50)  
		   public int getTableid() {  
		        return this.tableid;  
		}  
		   public void setTableid(int tableid) {  
		       this.tableid = tableid;  
		}

		@Column(name = "SN", length = 20)  
		   public String getSn() {  
		        return this.sn;  
		}  
		   public void setSn(String sn) {  
		       this.sn = sn;  
		}
		   
		@Column(name = "CUSID", length = 50)  
		   public int getCusid() {  
		        return this.cusid;  
		}  
		   public void setCusid(int cusid) {  
		       this.cusid = cusid;  
		}
		   
		@Column(name = "CUSNAME", length = 200)  
		   public String getCusname() {  
		        return this.cusname;  
		}  
		   public void setCusname(String cusname) {  
		       this.cusname = cusname;  
		}
		
		@Column(name = "CAT1", length = 50)  
		   public int getCat1() {  
		        return this.cat1;  
		}  
		   public void setCat1(int cat1) {  
		       this.cat1 = cat1;  
		}
		   
		@Column(name = "CAT2", length = 50)  
		   public int getCat2() {  
		        return this.cat2;  
		}  
		   public void setCat2(int cat2) {  
		       this.cat2 = cat2;  
		}
		   
		@Column(name = "CAT3", length = 50)  
		   public int getCat3() {  
		        return this.cat3;  
		}  
		   public void setCat3(int cat3) {  
		       this.cat3 = cat3;  
		}
			   
		@Column(name = "CAT1NAME", length = 10)  
		   public String getCat1name() {  
		        return this.cat1name;  
		}  
		   public void setCat1name(String cat1name) {  
		       this.cat1name = cat1name;  
		}
		
		@Column(name = "CAT2NAME", length = 10)  
		   public String getCat2name() {  
		        return this.cat2name;  
		}  
		   public void setCat2name(String cat2name) {  
		       this.cat2name = cat2name;  
		}
		   
		@Column(name = "CAT3NAME", length = 10)  
		   public String getCat3name() {  
		        return this.cat3name;  
		}  
		   public void setCat3name(String cat3name) {  
		       this.cat3name = cat3name;  
		}
				
}
