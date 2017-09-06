package org.tbs.dao.bas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasBiztype;
import org.tbs.entity.TbsBasBizvar;


import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Component
public class TbsBasBiztypevarDao extends HibernateDao  {
	
	@DataProvider  //主表
	public Collection<TbsBasBiztype> loadBiztype() throws Exception {
		return this.query("from "+TbsBasBiztype.class.getName()+"");
	}
	
	@DataProvider  //子表   分页成功，但view里面datagrid中的过滤栏会失效,不影响使用，但暂时无解
	public void loadVar(Page<TbsBasBizvar> page,Integer typeid) throws Exception {
		if (typeid != null){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("typeid", typeid);
			String hql = "from "+TbsBasBizvar.class.getName()+" where tbsBasBiztype.id = :typeid ";
			this.pagingQuery(page, hql, "select count(*)"+hql,param);
		}
	}
	
	/*@DataProvider  //无分页查询
	public Collection<TbsBasBizvar> loadVar(Integer typeid) throws Exception {
		if (typeid != null){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("typeid", typeid);
			String hql = "from "+TbsBasBizvar.class.getName()+" where tbsBasBiztype.id = :typeid ";
			return this.query(hql,param);
		} else {
			return null;
		}
	}*/
	
	@DataResolver
	public void saveAll(Collection<TbsBasBiztype> tbsBasBiztypes) throws Exception {  //处理主表
		Session session = this.getSessionFactory().openSession();
		try{	
			for (TbsBasBiztype tbsBasBiztype:tbsBasBiztypes){
				EntityState state = EntityUtils.getState(tbsBasBiztype);  //dorado7里面的持久化数据库这里必须拆分为new,update,delete三种状态处理
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasBiztype);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasBiztype);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasBiztype);
				}
				// if (EntityState.isVisible(state)) {  这句屏蔽后依然生效
					Collection<TbsBasBizvar> childs = tbsBasBiztype.getTbsBasBizvarSet();
					if (childs != null){
						for (TbsBasBizvar child:childs){
							child.setTbsBasBiztype(tbsBasBiztype);   //下级节点和上级节点关联
						}
						saveAllbizvar(childs);
					}
				}
				
			//}
		}finally{
			session.flush();
			session.close();
			}
	}
			
	
	@DataResolver  //处理子表datagrid里面的数据，主从表是两张表的话，就必须分开写save 
	public void saveAllbizvar (Collection<TbsBasBizvar> tbsBasBizvars) throws Exception {
		Session session = this.getSessionFactory().openSession();        
		try{	
			for (TbsBasBizvar tbsBasBizvar:tbsBasBizvars){
				EntityState state = EntityUtils.getState(tbsBasBizvar);  //dorado7里面的持久化数据库这里必须拆分为new,update,delete三种状态处理
				if(state.equals(EntityState.NEW)){
					session.save(tbsBasBizvar);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsBasBizvar);
				}
				if(state.equals(EntityState.DELETED)){
					session.delete(tbsBasBizvar);
				}
			}
		}finally{
			session.flush();
			session.close();
			}
		}			
}
