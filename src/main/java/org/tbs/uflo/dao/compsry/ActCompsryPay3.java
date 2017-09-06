package org.tbs.uflo.dao.compsry;


import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProjcompsryPay;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.ActionHandler;
/**
 * @author Hank
 * 动作的实现类
 * 
 */

@Component
public class ActCompsryPay3 implements ActionHandler {
	
	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		String docid=processInstance.getBusinessId();
		if (StringUtils.isNotEmpty(docid)){
			Session session = context.getSession();
			
			//====全部审批通过
			TbsProjcompsryPay tprls = (TbsProjcompsryPay) session.get(TbsProjcompsryPay.class, Integer.valueOf(docid));
			int projid = tprls.getTbsProj().getId();
			//更新状态
			String sql="update tbs_projcompsry_pay set valid=1 where id="+docid+" and proj_id="+projid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			sqlquery.executeUpdate();
					
			
		}
	}

}
