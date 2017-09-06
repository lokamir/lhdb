package org.tbs.tst;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;
import org.tbs.dao.bas.TbsBasAreaDao;
import org.tbs.entity.TbsBasArea;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.entity.EntityUtils;


public class VInterception {

    private TbsBasAreaDao tbsBasAreaDao;
    
    @DataProvider
    public Collection<TbsBasArea> getAll() throws Exception {
	List<TbsBasArea> results = new ArrayList<TbsBasArea>();
	Collection<TbsBasArea> tbsBasAreas = tbsBasAreaDao.loadAll();
	int a = 0;
	for (TbsBasArea tbsBasAreaDao:tbsBasAreas){
	    TbsBasArea targetProduct = EntityUtils.toEntity(tbsBasAreaDao);
	    EntityUtils.setValue(targetProduct, "prop1", a++);
	    results.add(targetProduct);
	}
	return results;
    }
}
