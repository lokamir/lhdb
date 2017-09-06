package org.tbs.dao.funs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import org.tbs.dao.funs.uppath;

public final class uppath {
	private static String param;
	static {
		Calendar Datetime=Calendar.getInstance();//获取当前日期 格式化 便于单独提取年月日
		Properties prop = new Properties();//properties实例
		String uppath = uppath.class.getResource("/").getPath();
		//String in =(uppath.replace("/target/classes", "/src/main/webapp/WEB-INF") + "/uppath.properties").replaceFirst("/", "");	//jetty	
		String in =(uppath.replace("/classes", "") + "uppath.properties").replaceFirst("/", "");	//tomcat	
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(in);
			prop.load(fileInputStream);
			param = prop.getProperty("path").trim()+Datetime.get(Calendar.YEAR)+"/";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		
	private uppath() {
	}

	public static String getPath() {
		return param;
	}
}
