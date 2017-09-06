package org.tbs.tst;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProj;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.core.DoradoAbout;

@Component
public class AjaxTest extends HibernateDao {

	@Expose
	public String toUpperCase(String str) {  //变大写
		return "input:\n" + str + "\n\n" + "output:\n" + str.toUpperCase();
	}

	@Expose
	public int multiply(int num1, int num2) {  //两个数字乘法
		return num1 * num2;
	}

	@Expose
	public int multiply2(List<Integer> nums) {  //多个数字乘法
		int result = 1;
		for (int num : nums) {
			result *= num;
		}
		return result;
	}

	@Expose
	public void errorAction() {  //出错提示
		System.out.println(100 / 0);
	}

	@Expose
	public Properties getSystemInfo() {  //系统信息
		Properties info = new Properties();
		info.setProperty("product", DoradoAbout.getProductTitle());
		info.setProperty("vendor", DoradoAbout.getVendor());
		info.setProperty("version", DoradoAbout.getVersion());
		return info;
	}

	@Expose
	public Map<String, Long> getMemInfo() {  //系统内存
		Map<String, Long> info = new HashMap<String, Long>();
		Runtime runtime = Runtime.getRuntime();
		info.put("freeMemory", runtime.freeMemory());
		info.put("totalMemory", runtime.totalMemory());
		return info;
	}
	
	
}
