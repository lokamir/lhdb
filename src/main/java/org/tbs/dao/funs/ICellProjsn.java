package org.tbs.dao.funs;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import com.bstek.bdf2.importexcel.interceptor.impl.RequiredInterceptor;

@Component
public class ICellProjsn extends RequiredInterceptor {
	@Resource El el;
	    public Object execute(Object cellValue) throws Exception {
	        super.execute(cellValue);
	        Map<String,String> fk = el.mapProjsn();
	        if (fk.containsKey("<待编辑>"+cellValue.toString())) {
	            return fk.get("<待编辑>"+cellValue.toString());
	        } else {
	            return "错误";
	        }
	    }
	    public String getName() {
	        return "ICellProjsn";
	    }
	    
	}

