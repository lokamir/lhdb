package org.tbs.dao.funs;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.Expose;

@Component
public class DelProj extends HibernateDao {
	@Expose
	public void actionDelProj(Map<String, Object> param) throws Exception {
		
		Date now = new Date(); //获取时间
		DateFormat d = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);//格式化
		String strDate = d.format(now);//字符串日期
		Integer projid = (Integer)param.get("projid");
		Session session = this.getSessionFactory().openSession();
		try {
			String sql="update tbs.tbs_proj set del = 1,by9 = "+" '删除日期 '"+ "'"+strDate+"'"+" where  ID=" + projid;
			SQLQuery sqlquery=session.createSQLQuery(sql);
			sqlquery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}
	@Expose
	public void actionDelCfm0(Map<String, Object> param) throws Exception {
		
		Date now = new Date(); //获取时间
		DateFormat d = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);//格式化
		String strDate = d.format(now);//字符串日期
		Integer projid = (Integer)param.get("projid");
		Session session = this.getSessionFactory().openSession();
		try {
			String sql="update tbs.tbs_projcfm0 set del = 1,by1 = "+" '删除日期 '"+ "'"+strDate+"'"+" where  PROJ_ID=" + projid;
			SQLQuery sqlquery=session.createSQLQuery(sql);
			sqlquery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush();
			session.close();
		}
	}
	@Expose
	public void actionDelCfm1(Map<String, Object> param) throws Exception {
		
		Date now = new Date(); //获取时间
		DateFormat d = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);//格式化
		String strDate = d.format(now);//字符串日期
		Integer projid = (Integer)param.get("projid");
		Session session = this.getSessionFactory().openSession();
		try {
			String sql="update tbs.tbs_projcfm1 set del = 1,memo = "+" '删除日期 '"+ "'"+strDate+"'"+" where  PROJ_ID=" + projid;
			SQLQuery sqlquery=session.createSQLQuery(sql);
			sqlquery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush();
			session.close();
		}
	}
	@Expose
	public void actionDelCfm2(Map<String, Object> param) throws Exception {
		
		Date now = new Date(); //获取时间
		DateFormat d = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);//格式化
		String strDate = d.format(now);//字符串日期
		Integer projid = (Integer)param.get("projid");
		Session session = this.getSessionFactory().openSession();
		try {
			String sql="update tbs.tbs_projcfm2 set del = 1,memo = "+" '删除日期 '"+ "'"+strDate+"'"+" where  PROJ_ID=" + projid;
			SQLQuery sqlquery=session.createSQLQuery(sql);
			sqlquery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush();
			session.close();
		}
	}
	@Expose
	public void actionDelEaa(Map<String, Object> param) throws Exception {
		
		Date now = new Date(); //获取时间
		DateFormat d = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);//格式化
		String strDate = d.format(now);//字符串日期
		Integer projid = (Integer)param.get("projid");
		Session session = this.getSessionFactory().openSession();
		try {
			String sql="update tbs.tbs_projeaa set del = 1,by1 = "+" '删除日期 '"+ "'"+strDate+"'"+" where  PROJ_ID=" + projid;
			SQLQuery sqlquery=session.createSQLQuery(sql);
			sqlquery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush();
			session.close();
		}
	}
	@Expose
	public void actionDelCusCompsry(Map<String, Object> param) throws Exception {
		
		Date now = new Date(); //获取时间
		DateFormat d = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);//格式化
		String strDate = d.format(now);//字符串日期
		Integer cusid = (Integer)param.get("cusid");
		Session session = this.getSessionFactory().openSession();
		try {
			String sql="update tbs.tbs_customer set COMPSRY = 0,PROJROL = 0,by2 = "+" '删除项目日期 '"+ "'"+strDate+"'"+"  where  ID=" + cusid;
			SQLQuery sqlquery=session.createSQLQuery(sql);
			sqlquery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush();
			session.close();
		}
	}
}
