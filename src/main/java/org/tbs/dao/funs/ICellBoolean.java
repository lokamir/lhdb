package org.tbs.dao.funs;

import org.springframework.stereotype.Component;
import com.bstek.bdf2.importexcel.interceptor.impl.RequiredInterceptor;

@Component
public class ICellBoolean extends RequiredInterceptor {
	    public Object execute(Object cellValue) throws Exception {
	        //super.execute(cellValue);
	        if ("是".equals(cellValue)) {
	            return 1;
	        } else {
	            return 0;
	        }
	    }
	    public String getName() {
	        return "1为是，0为否";
	    }
	    
	}

