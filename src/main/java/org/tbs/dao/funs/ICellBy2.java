package org.tbs.dao.funs;
import org.springframework.stereotype.Component;
import com.bstek.bdf2.importexcel.interceptor.impl.RequiredInterceptor;

@Component
public class ICellBy2 extends RequiredInterceptor {
	    public Object execute(Object cellValue) throws Exception {
	            return "老项目";
	    }
	    public String getName() {
	        return "ICellBy2";
	    }
	    
	}

