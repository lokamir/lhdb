package org.tbs.tst;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsBasArea;
import org.tbs.entity.TbsBasBank;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;


public class TbsBasAreaDao extends HibernateDao {
    @Resource
    private TbsBasBankDao tbsBasBankDao;
    
    
    @DataProvider
    public Collection<TbsBasArea> getAll() throws Exception {
    	List<TbsBasArea> results = new ArrayList<TbsBasArea>();
    	Collection<TbsBasArea> tbsBasAreas = this.loadAll();
    	int a = 0;
    	for (TbsBasArea tbsBasAreaDao:tbsBasAreas){
    	    TbsBasArea targetProduct = EntityUtils.toEntity(tbsBasAreaDao);
    	    EntityUtils.setValue(targetProduct, "p1", a++);
    	    results.add(targetProduct);
    	}
    	return results;
    }
    	
    @DataProvider
    public Collection<TbsBasArea> getAllBanks() throws Exception{
    	List<TbsBasArea> results = new ArrayList<TbsBasArea>();
    	 
        Collection<TbsBasArea> tbsBasAreas = this.loadAll();
 
        for (TbsBasArea tbsBasArea : tbsBasAreas) {
            // 将对象转换为一个可以被Dorado处理的实体对象
            TbsBasArea targetCategory = EntityUtils.toEntity(tbsBasArea);
//            Map<String,Object> map =new HashMap<String,Object>();
//            
//            int id = tbsBasArea.getId();
//            map.put("id", id);
            List<TbsBasBank> tbsBasBanks = tbsBasBankDao.query("from TbsBasBank where rid=" + tbsBasArea.getId());
            EntityUtils.setValue(targetCategory, "bank", tbsBasBanks);
            results.add(targetCategory);
        }
 
        return results;
    }
        
    @DataProvider
    public Collection<TbsBasArea> loadAll() throws Exception {
	return this.query("from " + TbsBasArea.class.getName());
    }
	
    @DataResolver
    public void save(Collection<TbsBasArea> tbsBasAreas) throws Exception {
	Session session = this.getSessionFactory().openSession();
	try{
	    for(TbsBasArea tbsBasArea : tbsBasAreas){
		EntityState state = EntityUtils.getState(tbsBasArea);
		if(state.equals(EntityState.NEW)){
		    session.save(tbsBasArea);
		}
		if(state.equals(EntityState.MODIFIED)){
		    session.update(tbsBasArea);
		}
		if(state.equals(EntityState.DELETED)){
		    session.delete(tbsBasArea);
		}
	    }
	} finally{
	    session.flush();
	    session.close();
	}
    }
}
