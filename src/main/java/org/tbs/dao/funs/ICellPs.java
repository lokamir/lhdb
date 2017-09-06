package org.tbs.dao.funs;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import com.bstek.bdf2.importexcel.interceptor.impl.RequiredInterceptor;

@Component
public class ICellPs extends RequiredInterceptor {
	@Resource El el;
	    public Object execute(Object cellValue) throws Exception {
	        super.execute(cellValue);
	        Map<String,Integer> fk = el.mapPsid();
	        if (fk.containsKey(cellValue)) {
	            return fk.get(cellValue);
	        } else {
	            return "error";
	        }
	    }
	    public String getName() {
	        return "ICellPs";
	    }
	    
	}

