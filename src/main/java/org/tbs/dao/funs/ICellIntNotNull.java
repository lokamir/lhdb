package org.tbs.dao.funs;

import org.springframework.stereotype.Component;
import com.bstek.bdf2.importexcel.interceptor.impl.RequiredInterceptor;

@Component
public class ICellIntNotNull extends RequiredInterceptor {
	    public Object execute(Object cellValue) throws Exception {
	        super.execute(cellValue);
	        if ("".equals(cellValue)||null==cellValue) {
	            return 0;
	        } else {
	            return cellValue;
	        }
	    }
	    public String getName() {
	        return "Int类型非空";
	    }
	    
	}

