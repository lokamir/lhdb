package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;


//让springMVC自动扫描和对应到数据库的view
@Entity  
@Table(name = "v_cggdelall")  
public class Vcggdelall implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Fields (这里是用虚拟外键关联到另一个class)
	private VcggallSn sn;
	
	//Constructors
	//default
	public Vcggdelall(){
	
	}
	//full Constructors	
	public Vcggdelall(VcggallSn sn){
		this.sn = sn;
	}
	
	//Property accessors
	@EmbeddedId
	public VcggallSn getSn(){
		return this.sn;
	}
	
	public void setSn(VcggallSn sn){
		this.sn = sn;
	}	

}
