package org.tbs.uflo.dao.compsry;


import java.math.BigDecimal;
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
public class ActCompsryPay4 implements ActionHandler {
	
	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		String docid=processInstance.getBusinessId();
		if (StringUtils.isNotEmpty(docid)){
			Session session = context.getSession();
			
			//====全部审批通过
			TbsProjcompsryPay tprls = (TbsProjcompsryPay) session.get(TbsProjcompsryPay.class, Integer.valueOf(docid));
			int projid = tprls.getTbsProj().getId();
			int cusid = tprls.getTbsProj().getTbsCustomer().getId();

			//获取客户表单代偿余额
			String sql_cusCompsry = "select compsry from tbs_customer where id =" +cusid;
			SQLQuery sqlquery_cusCompsry = session.createSQLQuery(sql_cusCompsry);
			BigDecimal cusCompsryBig = (BigDecimal)sqlquery_cusCompsry.uniqueResult();
			//更新代偿请款单代偿余额
			String sql_compsrypay_dcye = "update tbs_projcompsry_pay set DCYE="+cusCompsryBig+" where id="+docid+" and proj_id="+projid;;
			SQLQuery sqlquery_compsrypay_dcye = session.createSQLQuery(sql_compsrypay_dcye);
			sqlquery_compsrypay_dcye.executeUpdate();
			
			
			//获取项目表单已批复总担保授信金额
			String sql_inittotloc = "select INITTOTLOC from tbs_proj where id =" +projid;
			SQLQuery sqlquery_inittotloc = session.createSQLQuery(sql_inittotloc);
			BigDecimal inittotlocBig = (BigDecimal)sqlquery_inittotloc.uniqueResult();
			String sql_vtotloc = "select VTOTLOC from tbs_proj where id =" +projid;
			SQLQuery sqlquery_vtotloc = session.createSQLQuery(sql_vtotloc);
			BigDecimal vtotlocBig = (BigDecimal)sqlquery_vtotloc.uniqueResult();
			BigDecimal zbe = inittotlocBig.subtract(vtotlocBig);
			//更新代偿请款单代偿余额
			String sql_compsrypay_zbe = "update tbs_projcompsry_pay set ZBE="+zbe+" where id="+docid+" and proj_id="+projid;;
			SQLQuery sqlquery_compsrypay_zbe = session.createSQLQuery(sql_compsrypay_zbe);
			sqlquery_compsrypay_zbe.executeUpdate();
					
			
		}
	}

}
