package org.tbs.tst.VO;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasBank;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

public class TbsBasBankDao extends HibernateDao {
	
    	@DataProvider
	public Collection<TbsBasBank> getAll() throws Exception {
		return this.query("from "+TbsBasBank.class.getName());
	}
    
	@DataProvider
	public Collection<TbsBasBank> loadMain() throws Exception {
		return this.query("from "+TbsBasBank.class.getName()+" where tbsBasBank.id is null");
	}
	
	@DataProvider
	public Collection<TbsBasBank> loadSub(Integer MainID) throws Exception {
		if(MainID != null){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("mapid", MainID);
			String hql= "from "+TbsBasBank.class.getName()+" where tbsBasBank.id = :mapid";
			return this.query(hql,param);
		}else {	
			return null;
		}
	}
	
	@DataResolver
	public void saveAll(Collection<TbsBasBank> tbsBasBanks) throws Exception {  //处理主节点
		Session session = this.getSessionFactory().openSession();
		try{	
			for (TbsBasBank tbsBasBank:tbsBasBanks){
				EntityState state = EntityUtils.getState(tbsBasBank);  //dorado7里面的持久化数据库这里必须拆分为new,update,delete三种状态处理
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasBank);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasBank);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasBank);
				}
				Collection<TbsBasBank> childs = tbsBasBank.getTbsBasBankSet();   //	获得下级节点集合（子集合）
				if (childs != null){
					for (TbsBasBank child:childs){
						child.setTbsBasBank(tbsBasBank);   //下级节点和上级节点关联
					}
					EntityState childsstate = EntityUtils.getState(childs);
					if(childsstate.equals(EntityState.NEW)){
						session.save(childs);
					}
					if(childsstate.equals(EntityState.MODIFIED)){
						session.update(childs);
					}
					if(childsstate.equals(EntityState.DELETED)){
						session.delete(childs);
					}
					saveAll(childs);  //超过两级节点，采用递归调用
				}
			}
		}finally{
			session.flush();
			session.close();
		}			
	}
		
}

