package org.tbs.dao.funs;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import com.bstek.bdf2.importexcel.interceptor.impl.RequiredInterceptor;

@Component
public class ICellProjid extends RequiredInterceptor {
	@Resource El el;
	    public Object execute(Object cellValue) throws Exception {
	        super.execute(cellValue);
	        Map<String,Integer> fk = el.mapProjid();
	        String str = "<待编辑>"+cellValue.toString();
	        if (fk.containsKey(str)) {
	            return fk.get(str);
	        } else {
	            return "错误";
	        }
	    }
	    public String getName() {
	        return "ICellProjid";
	    }
	    
	}

