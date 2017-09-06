package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;

//让springMVC自动扫描和对应到数据库的view
@Entity  
@Table(name = "v_user_mgm")  
public class Vusermgm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Fields (这里是用虚拟外键关联到另一个class)
	private VusermgmDetail detail;
	
	//Constructors
	//default
	public Vusermgm(){
	
	}
	//full Constructors	
	public Vusermgm(VusermgmDetail detail){
		this.detail = detail;
	}
	
	//Property accessors
	@EmbeddedId
	public VusermgmDetail getDetail(){
		return this.detail;
	}
	
	public void setDetail(VusermgmDetail detail){
		this.detail = detail;
	}	

}
