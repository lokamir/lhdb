package org.tbs.dao.funs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.Bdf2Dept;
import org.tbs.entity.Bdf2User;
import org.tbs.entity.Vcggall;
import org.tbs.entity.VcggallSn;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;

@Component
public class GetSysInfo extends HibernateDao {
	
	public int getUserID(String UserName,Session ses) throws Exception{  //用户ID
		int uid; String str_uid;
		String sql="select id from bdf2_user where username_='"+UserName+"'";
		SQLQuery query=ses.createSQLQuery(sql);		
		str_uid = query.uniqueResult().toString();
		uid=Integer.parseInt(str_uid);
		return uid;
	}

	public Collection<Bdf2User> getCnameById(int keyin_id) { //用户姓名.虚拟属性
		List<Bdf2User> bdf2Users = this.query("from Bdf2User where id =" + keyin_id);
		return bdf2Users;
	}
	
	public Collection<VcggallSn> getCggStr(String cggSn) { // 反担保明细.虚拟属性
		List<Vcggall> vcggalls = this.query("from " + Vcggall.class.getName()
				+ " where sn.sn='" + cggSn + "'");
		List<VcggallSn> vcggallsns = new ArrayList<>();
		for (Vcggall vcggall : vcggalls) {
			vcggallsns.add(vcggall.getSn());
		}
		return vcggallsns;
	}
	public Collection<Bdf2Dept> getDeptNameById(String deptid) { //部门名称.虚拟属性
		if (deptid != null){
		List<Bdf2Dept> bdf2Depts = this.query("from Bdf2Dept where id =" + deptid);
		return bdf2Depts;
		}else{
			return null;
		}
		}


}
