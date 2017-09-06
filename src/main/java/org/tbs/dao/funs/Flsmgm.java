package org.tbs.dao.funs;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.h2.store.fs.FileUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.orm.ParseResult;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Criteria;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.uploader.UploadFile;
import com.bstek.dorado.uploader.annotation.FileResolver;
import org.tbs.entity.TbsFunFul;

@Component
public class Flsmgm extends HibernateDao {
	
	// 单文件上传
	@FileResolver      
	public Map<String, String> SingleUploader(UploadFile file,Map<String, Object> parameter) throws Exception{
		String path=uppath.getPath();
		File destpath=getDestFile(file.getFileName(), new File(path));	
		//File destpath=new File(path+file.getFileName()); 
		String by1 = (String)parameter.get("by1");
		String by2 = (String)parameter.get("by2");
		String by3 = (String)parameter.get("by3");
		String fid = (String)parameter.get("fid");
		String typid = (String)parameter.get("typid");
		try {
			file.transferTo(destpath);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, String> upfiledata = new HashMap<String, String>();
		upfiledata.put("fileName", destpath.getName());
		upfiledata.put("absolutePath", destpath.getAbsolutePath());
		saveTodb(destpath.getName(), destpath.getAbsolutePath(),by1,by2,by3,fid,typid);
		return upfiledata;	
	}

	
	//多文件上传
	@FileResolver     
	public void MultiUploader(UploadFile file, Map<String, Object> parameter) throws Exception {
		String path=uppath.getPath();
		File destpath=getDestFile(file.getFileName(), new File(path));	
		String by1 = (String)parameter.get("by1");
		String by2 = (String)parameter.get("by2");
		String by3 = (String)parameter.get("by3");
		String fid = (String)parameter.get("fid");
		String typid = (String)parameter.get("typid");
		try {
			file.transferTo(destpath);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		saveTodb(destpath.getName(), destpath.getAbsolutePath(),by1,by2,by3,fid,typid);
	}
	
	
	//判断服务器上是否已经有了该文件
	private File getDestFile(String fileName, File folder) {
		File destFile;
		// 若文件不存在直接返回
		if (!new File(folder, fileName).exists()) {
			destFile = new File(folder, fileName);
		} else {
			int i = 1, lastDotPos = fileName.lastIndexOf("."), len = fileName
					.length();

			String prefix = null, suffix = null, destFileName;
			// 文件名中不存在"."或者最后一位是"."的时候
			if (lastDotPos == len - 1 || lastDotPos == -1) {
				prefix = fileName;
				suffix = "";
			} else if (lastDotPos != -1) {
				prefix = fileName.substring(0, lastDotPos);
				suffix = fileName.substring(lastDotPos + 1);
			}
			do {
				if (suffix.length() == 0) {
					destFileName = String.format("%s_%d", prefix, i++);
				} else {
					destFileName = String.format("%s_%d.%s", prefix, i++,
							suffix);
				}
				destFile = new File(folder, destFileName);
			} while (destFile.exists());

		}

		FileUtils.createDirectory(destFile.getParent());
		return destFile; 
	}
	
	
	//将上传文件的信息插入数据库
	public void saveTodb(String filename, String path,String by1,String by2,String by3,String fid,String typid) throws Exception{
		Session session = this.getSessionFactory().openSession();
		try{ 
			if (fid == null)  {
				fid="文件管理器"; 
			};
			if (typid == null)  {
				typid="999999999"; 
			};
			String uppath = path.replace( '\\' , '/' );
			String uname=ContextHolder.getLoginUser().getUsername();
			String cname=ContextHolder.getLoginUser().getCname();
			GetSysInfo gsi=new GetSysInfo(); 
			int uid = gsi.getUserID(uname,session);
			//String sql="insert into tbs_fun_ful (fname,uppath,fclass,fid,user_id,username,comments) " +"values ('"+filename+"','"+uppath+"','','',(select id from bdf2_user where username_='"+uname+"'),(select cname_ from bdf2_user where username_='"+uname+"'),'')";
			String sql="insert into tbs_fun_ful (fname, uppath, fid, user_id, username, by1, by2, by3, typid) " +
					                 "values ('"+filename+"','"+uppath+"','"+fid+"',"+uid+",'"+cname+"','"+by1+"','"+by2+"','"+by3+"','"+typid+"')";
			SQLQuery query=session.createSQLQuery(sql); 
			query.executeUpdate();
			}finally{
			session.flush();
			session.close();
			}
		}	
	
	
    
	// 文件下载view
	@DataProvider  // 给单据用
	public void getquery(Page<TbsFunFul> page,Criteria criteria,String param) throws Exception{
		ParseResult pr=this.parseCriteria(criteria, true, "u" );
		String wherecases=" and by2 ='"+param+"'";
		
		String hql="from "+TbsFunFul.class.getName()+" u"+" where 1=1 "+wherecases;  //查询条件用hql拼接实现，好处是可以自定义HQL，可以先过滤表内数据
		if (pr!=null){
			hql+=" and "+pr.getAssemblySql().toString();
			this.pagingQuery(page, hql+" order by id desc", "select count(*) "+hql, pr.getValueMap());
		}else{
			this.pagingQuery(page, hql+" order by id desc", "select count(*)"+hql);
		}
		
	}
	@DataProvider  //文件管理器用
	public void getall(Page<TbsFunFul> page,Criteria criteria) throws Exception{
		ParseResult pr=this.parseCriteria(criteria, true, "u" );
		
		String wherecases="";
		
		String hql="from "+TbsFunFul.class.getName()+" u"+" where 1=1 "+wherecases;  //查询条件用hql拼接实现，好处是可以自定义HQL，可以先过滤表内数据
		if (pr!=null){
			hql+=" and "+pr.getAssemblySql().toString();
			this.pagingQuery(page, hql+" order by id desc", "select count(*) "+hql, pr.getValueMap());
		}else{
			this.pagingQuery(page, hql+" order by id desc", "select count(*)"+hql);
		}
		
	}

	// 文件下载 save操作
	@DataResolver
	public void saveAll(Collection<TbsFunFul> tbsFunFuls) throws Exception{
		Session session = this.getSessionFactory().openSession();
		try{
			for(TbsFunFul tbsFunFul : tbsFunFuls){
				EntityState state = EntityUtils.getState(tbsFunFul);
				if(state.equals(EntityState.NEW)){
					session.save(tbsFunFul);
				}
				if(state.equals(EntityState.MODIFIED)){
					session.update(tbsFunFul);
				}
				if(state.equals(EntityState.DELETED)){
					String uppath=tbsFunFul.getUppath();  // 删除物理文件
					File file = new File(uppath);
					file.delete();
					session.delete(tbsFunFul);
					
				}
			}
		}finally{
			session.flush();
			session.close();
		}
	}
	
	/*@DataProvider  --无分页全部查询
	public Collection<TbsFunFul> getall() throws Exception{
		String hql="from "+TbsFunFul.class.getName()+"";
		return this.query(hql);		
	}*/

	/*@DataProvider  // 查询条件用detachcriteria实现，好处是简单，全表数据全部查询出现
	public void getall(Page<TbsFunFul> page,Criteria criteria) throws Exception{
		DetachedCriteria dc=this.buildDetachedCriteria(criteria, TbsFunFul.class);
	    this.pagingQuery(page, dc);
	}*/
	
	
	/*//文件下载Action
	private DownloadFile getDownloadFile(String fileName) throws IOException {  //getDownloadFile预处理
		String name = fileName;
		ParameterUtils.validateParameterCharacters(name);
		URL url = this.getClass().getResource(name);
		InputStream stream = url.openConnection().getInputStream();
		DownloadFile file = new DownloadFile(name, stream);
		if ("UploadAction.pdf".equals(name)) {
			file.setName("UpdateAction用户指南.pdf");
			//如果文件下载时,中文名称变乱码,可以参考下列代码自定义转码方式,并设置DownloadFile的fileName属性
			//file.setFileName(new String("UpdateAction用户指南.pdf".getBytes("UTF-8"),"iso8859-1"));
		}
		return file;
	}
	
	@FileProvider
	public DownloadFile downLoad(Map<String, String> parameter)   //文件下载
			throws IOException {
		String fileName = parameter.get("fname");
		DownloadFile file = getDownloadFile(fileName);
		return file;
	}*/

}

