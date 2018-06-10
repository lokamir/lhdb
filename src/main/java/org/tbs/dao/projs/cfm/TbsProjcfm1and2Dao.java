package org.tbs.dao.projs.cfm;

/**
 *  会议决议历史记录
 * */

import java.util.Collection;

import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProjcfm1;
import org.tbs.entity.TbsProjcfm2;
import org.tbs.entity.Vcfmall;


import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;

@Component
public class TbsProjcfm1and2Dao extends HibernateDao {
	@DataProvider
	public Collection<Vcfmall> loadAll(Integer projid) throws Exception {
		if( projid!=0&&projid != null ){
			String hql=" FROM " + Vcfmall.class.getName() + " where proj_id = " +projid + " order by CFM_ID";
			return this.query(hql) ;
		}else{
			String hql=" FROM " + Vcfmall.class.getName();
			return this.query(hql);
		}
		
	}
	
	@DataProvider
    public Collection<TbsProjcfm1> loadSingleResultCfm1(String cfmid){
	return this.query("from " + TbsProjcfm1.class.getName() + " where id = "
		+ cfmid + " and valid = 1 and del=0");
    }
	
	@DataProvider
    public Collection<TbsProjcfm2> loadSingleResultCfm2(String cfmid){
	return this.query("from " + TbsProjcfm2.class.getName() + " where id = "
		+ cfmid  + " and valid = 1 and del=0");
    }
}