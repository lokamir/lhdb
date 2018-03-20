package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;


//让springMVC自动扫描和对应到数据库的view
@Entity  
@Table(name = "v_cfmall")  
public class Vcfmall implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Fields (这里是用虚拟外键关联到另一个class)
	private VcfmallDetail detail;
	
	//Constructors
	//default
	public Vcfmall(){
	
	}
	//full Constructors	
	public Vcfmall(VcfmallDetail detail){
		this.detail = detail;
	}
	
	//Property accessors
	@EmbeddedId
	public VcfmallDetail getDetail(){
		return this.detail;
	}
	
	public void setDetail(VcfmallDetail detail){
		this.detail = detail;
	}	

}
