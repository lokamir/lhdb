package org.tbs.dao.projs.change;

/**
 * 8.1 项目三要素变更_决议单和参会人员
 * 
 * */

import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.dao.funs.GetSysInfo;
import org.tbs.entity.TbsProjchangeMajcontCfm;
import org.tbs.entity.TbsProjchangeMajcontCfmattend;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.Expose;



@Component
public class TbsProjchangeMajcontCfmDao extends HibernateDao {
   
	@Resource
    private GetSysInfo gsi;
	
	@DataProvider  
    public Collection<TbsProjchangeMajcontCfm> loadByChgid(Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjchangeMajcontCfm.class.getName()
					+ " where del = 0 and change_id= " + id + " order by id desc";
			return this.query(hql);
		} else {
				String hql = "from " + TbsProjchangeMajcontCfm.class.getName()
						+ " where id is null";
				return this.query(hql);
		}
    }
	
	@DataProvider  
    public Collection<TbsProjchangeMajcontCfmattend> loadByCfmidForAttend(Integer id)
	    throws Exception {
		if (id != null) {
			String hql = "from " + TbsProjchangeMajcontCfmattend.class.getName()
					+ " where cfm_id= " + id ;
			return this.query(hql);
		} else {
				String hql = "from " + TbsProjchangeMajcontCfmattend.class.getName()
						+ " where id is null";
				return this.query(hql);
		}
    }
	
	@Expose
	public void ChangeCfmAll(Map<String, Object> params) throws Exception {
		Session session = this.getSessionFactory().openSession();
		String opid =  Integer.toString((int) params.get("opid"))+","; 	
		String loginusername =  (String) params.get("loginusername"); 
		String keyinid = Integer.toString((int) gsi.getUserID(loginusername, session))+","; 
		String changeid = Integer.toString((int) params.get("projchangeid"))+",";
		String projid = Integer.toString((int) params.get("projid"));
		try {
			String sql = "call p_changecfmall(" + opid + keyinid + changeid + projid + ")";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			sqlquery.executeUpdate();
			}finally {
			session.flush();
			session.close();
		}
	}
		
}