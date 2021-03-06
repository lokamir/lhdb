/**
 * @author Hank
 * 
 * 自动指定当前Node的审批人。 部门经理根据发起人判定； 其他人员取approver表内数据；或者B_Role
 * 
 **/
package org.tbs.uflo.dao.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbs.entity.TbsProj;

import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessDefinition;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.AssignmentHandler;
import com.bstek.uflo.process.node.TaskNode;



@Component
public class GetApprover implements AssignmentHandler {
	
	@Autowired
    @Qualifier(ProcessClient.BEAN_ID)
    private ProcessClient processClient;
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> handle(TaskNode taskNode, ProcessInstance processInstance, Context context) {
		Session session = context.getSession();
		List<String> users = new ArrayList<String>();
		String promoter = processInstance.getPromoter();
		String cn = processInstance.getCurrentNode(); // 当前节点名称 //当前任务名称 String// ct=processInstance.getCurrentTask();
		String docid = processInstance.getBusinessId();
		Long pid = processInstance.getProcessId();
		ProcessDefinition process =(ProcessDefinition)session.get(ProcessDefinition.class, pid);
		String pname=process.getName();

		if (cn.equals("A角补全信息") && pname.equals("projcreate")) {
			String sql = "select b.USERNAME_ from tbs_proj a, bdf2_user b where b.id=a.A_ROLE_ID and a.id="
					+ docid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("A角确认") && pname.equals("projcreate")) {
			String sql = "select b.USERNAME_ from tbs_proj a, bdf2_user b where b.id=a.A_ROLE_ID and a.id="
					+ docid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("立项单修正") && pname.equals("projcreate")) {
			String sql = "select b.USERNAME_ from tbs_proj a, bdf2_user b where b.id=a.A_ROLE_ID and a.id="
					+ docid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("B角确认") && pname.equals("projcreate")) {
			String sql = "select b.USERNAME_ from tbs_proj a, bdf2_user b where b.id=a.B_ROLE_ID and a.id="
					+ docid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("B角确认") && pname.equals("projeaa")) {
			String sql = "select c.USERNAME_ from tbs_proj a, tbs_projeaa b, bdf2_user c where a.id=b.PROJ_ID and c.id=a.B_ROLE_ID and b.id="
					+ docid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("B角确认") && (pname.equals("projcfm") || pname.equals("changemajcont"))) {
			String sql = "select b.USERNAME_ from tbs_proj a, bdf2_user b where b.id=a.B_ROLE_ID and a.id="
					+ docid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		// 适用于决策审批和项目三要素变更流程
		if (cn.indexOf("评审会秘书") >= 0
				&& (pname.equals("projcfm") || pname.equals("changemajcont"))) {
			String sql = "select account from tbs_approver where trim(title) = '评审会秘书' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("评委审批") && (pname.equals("projcfm") || pname.equals("changemajcont"))) {
			String cfm0Id = (String) processClient.getProcessVariable("cfm0Id",
					processInstance);
			String sql = "select b.USERNAME_ from tbs_proj_opinion a, "
					+ "bdf2_user b where a.uid=b.ID and a.CFM0_ID = " + cfm0Id
					+ " and a.CFMTYPE = 0 and a.del = 0 ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}

		if (cn.equals("主任委员审批") && (pname.equals("projcfm") || pname.equals("changemajcont"))) {
			String sql = "select account from tbs_approver where title like '%主任委员%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("法律合规部门经理审批") && (pname.equals("projcfm") || pname.equals("changemajcont"))) {
			String sql = "select account from tbs_approver where title like '%法律合规部门经理%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		 
		// 决策审批1500w的限制金额判断是tbsproj.totloc,适用于项目三要素变更流程
		// 2017年3月15日陈雯雯要求改2000万
		// 2018年7月5日邓婕要求改4500万
		if (cn.equals("决策人审批")
				&& pname.equals("changemajcont")) {
			String sqlAmount = "SELECT NEWTOTLOC,PROJ_ID FROM tbs.tbs_projchange_majcont where id = "
					+ docid;
			SQLQuery queryList = session.createSQLQuery(sqlAmount);
			List<String> resultList = queryList.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			List<String> resultnewtotloc =  new ArrayList<String>() ;
			List<String> resultproj_id = new ArrayList<String>() ;
			String amount = "0";
			for (Object object : resultList) {  
	            Map obj = (Map) object;  
	            String newtotloc = obj.get("NEWTOTLOC").toString();  
	            String proj_id = obj.get("PROJ_ID").toString();  
	            resultnewtotloc.add(newtotloc);
	            resultproj_id.add(proj_id);
	        }  
			if(!resultnewtotloc.isEmpty()){
				amount = resultnewtotloc.get(0);
			}else{
				amount = "0";
			}
			float tm = Float.valueOf(amount);
			Boolean overlimit = ifOverlimit(context, resultproj_id.get(0));
			if (tm > 45000000.0||overlimit) {
				// 董事长审批
				String sql = "select account from tbs_approver where title like '董事长' and deptname = '董事局' ";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				users = sqlquery.list();
			} else {
				// 总经理审批
				String sql = "select account from tbs_approver where title like '总经理' and deptname = '总经理办公室' ";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				users = sqlquery.list();
			}
		}
		// 决策审批2000w的限制金额判断是tbsproj.totloc,适用于决策审批
		if (cn.equals("决策人审批")
				&& pname.equals("projcfm") ) {
			//String sqlAmount = "select TOTLOC from tbs_proj where id = " + docid;
			String sqlAmount = "select TOTLOC from v_cfmValid0 where PROJ_ID = " + docid + " and by3 = " + processInstance.getId() ;
			SQLQuery queryAmount = session.createSQLQuery(sqlAmount);
			String amount = queryAmount.uniqueResult().toString();
			float tm = Float.valueOf(amount);
			Boolean overlimit = ifOverlimit(context, docid);
			if (tm > 45000000.0||overlimit) {
				// 董事长审批
				String sql = "select account from tbs_approver where title like '董事长' and deptname = '董事局' ";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				users = sqlquery.list();
			} else {
				// 总经理审批
				String sql = "select account from tbs_approver where title like '总经理' and deptname = '总经理办公室' ";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				users = sqlquery.list();
			}
		}
		if (cn.equals("决策人审批")&& pname.equals("compsry")) {
			String sqlProjid = "select distinct proj_id from tbs_projcompsry where id = "
					+ docid ;
			SQLQuery queryProjid = session.createSQLQuery(sqlProjid);
			String projid = queryProjid.uniqueResult().toString();
			String sqlAmount = "select INITOTLOC from tbs_proj where id = "
					+ projid;
			SQLQuery queryAmount = session.createSQLQuery(sqlAmount);
			String amount = queryAmount.uniqueResult().toString();
			float tm = Float.valueOf(amount);
			Boolean overlimit = ifOverlimit(context, projid);
			if (tm > 45000000.0||overlimit) {
				// 董事长审批
				String sql = "select account from tbs_approver where title like '董事长' and deptname = '董事局' ";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				users = sqlquery.list();
			} else {
				// 总经理审批
				String sql = "select account from tbs_approver where title like '总经理' and deptname = '总经理办公室' ";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				users = sqlquery.list();
			}
		}
		if (cn.equals("B角确认") && pname.equals("undwrt")) {
			String sql = "select c.USERNAME_ from tbs_proj a, tbs_projundwrt b, bdf2_user c where a.id=b.PROJ_ID and c.id=a.B_ROLE_ID and b.id="
					+ docid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("B角审批") && pname.equals("undwrtcfm")) {
			String sql = "select c.USERNAME_ from tbs_proj a, tbs_projundwrt b, tbs_projundwrt_cfmar d, bdf2_user c "
					+ " where a.id=b.PROJ_ID and c.id=a.B_ROLE_ID and b.id=d.projundwrt_id and d.id="
					+ docid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("B角确认") && pname.equals("changemajcont")) {
			String sql = "select c.USERNAME_ from tbs_proj a, tbs_projchange_majcont b, bdf2_user c where a.id=b.PROJ_ID and c.id=a.B_ROLE_ID and b.id="
					+ docid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}

		// ===== 通用 开始=====
		if (cn.equals("部门经理分配AB角")) {
			String sql = "select account from bdf2_user_dept a, tbs_approver b where a.dept_id_=b.deptid and b.title like '%部门经理%' and b.title not like ('%部门经理助理%') and a.username_='"
					+ promoter + "' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("部门经理审批")) {
			String sql = "select account from bdf2_user_dept a, tbs_approver b where a.dept_id_=b.deptid and b.title like '%部门经理%' and b.title not like ('%部门经理助理%') and a.username_='"
					+ promoter + "' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("业务总监审批")) {
			String sql = "select account from tbs_approver where trim(title)='业务总监' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("分管业务总经理")) {
			String sql = "select account from tbs_approver where trim(title)='分管业务总经理' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("分管风险领导")) {
			String sql = "select account from tbs_approver where trim(title) in ('分管风管总经理') ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("风管经理审批")) {
			String sql = "select account from tbs_approver where trim(title)='风管经理' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("风管经理调整授信额度")) {
			String sql = "select account from tbs_approver where trim(title)='风管经理' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("风管经理决定是否调整授信额度")) {
			String sql = "select account from tbs_approver where trim(title)='风管经理' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("风管部门经理审批")) {
			String sql = "select account from tbs_approver where trim(title) like '%风险管理部门经理%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("法务经理审批")) {
			String sql = "select account from tbs_approver where trim(title)='法务经理' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("主任委员审批(业务)")) {
			String sql = "select account from tbs_approver where trim(title)='业管会主任委员' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("总经理审批")) {
			String sql = "select account from tbs_approver where trim(title) like '总经理' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("业务审核委员会审批")) {
			String sql = "select account from tbs_approver where title like '%业务审核委员会成员%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("出纳费用确认")) {
			String sql = "select account from tbs_approver where trim(title)='出纳' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("财务部门经理审批")) {
			String sql = "select account from tbs_approver where trim(title)='财务部门经理' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("分管风管总经理")) {
			String sql = "select account from tbs_approver where trim(title) in ('分管风管总经理') ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("董事长审批")) {
			String sql = "select account from tbs_approver where title like '董事长' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		// 2017年3月19日注释 决策人根据金额判断董事长还是总经理
		if (cn.equals("决策人审批") && pname.equals("undwrt")) {
			String sqlProjid = "select PROJ_ID from tbs_projundwrt where id = "
					+ docid ;
			SQLQuery queryProjid = session.createSQLQuery(sqlProjid);
			String projid = queryProjid.uniqueResult().toString();		
			String sqlAmount = "select INITTOTLOC from tbs_proj where id = (select PROJ_ID from tbs_projundwrt where id = "
					+ docid + ')';
			SQLQuery queryAmount = session.createSQLQuery(sqlAmount);
			String amount = queryAmount.uniqueResult().toString();
			float tm = Float.valueOf(amount);
			Boolean overlimit = ifOverlimit(context, projid);
			if (tm > 45000000||overlimit) {
				String sql = "select account from tbs_approver where title like '董事长' ";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				users = sqlquery.list();
			} else {
				String sql = "select account from tbs_approver where title like '总经理' ";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				users = sqlquery.list();
			}
		}
		if (cn.equals("请印审批")) {
			//String adsign = (String)processClient.getProcessVariable("adsign", processInstance);
			String sql = "select account from tbs_approver where title in  ('行政经理') or title in  ('综合管理部门经理')" ;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		// ====通用结束====

		// =====期间管理 开始========
		if (cn.equals("风管部门经理检查")
				&& (pname.equals("ireginsp") || pname.equals("perinsp"))) {
			String sql = "select account from tbs_approver where trim(title) like '%风险管理部门经理%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if ((cn.equals("分管总经理（风险）审批") || cn.equals("是否上会"))
				&& (pname.equals("ireginsp") || pname.equals("perinsp"))) {
			String sql = "select account from tbs_approver where trim(title)='分管风管总经理' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("评审会秘书录入“会议决议单”")
				&& (pname.equals("ireginsp") || pname.equals("perinsp"))) {
			String sql = "select account from tbs_approver where title = '评审会秘书' ";// 3月15日																					
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("部门经理审批")
				&& (pname.equals("ireginsp") || pname.equals("perinsp"))) {
			String sql = "select account from bdf2_user_dept a, tbs_approver b where a.dept_id_=b.deptid and b.title like '%部门经理%' and b.title not like ('%部门经理助理%') and a.username_='"
					+ promoter + "' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		// =======期间管理 结束=======

		// =======费用收退 开始 =========
		if ((pname.equals("ptyexpi") || pname.equals("ptyexpo"))) {
			String sqlptyexp = "select proj_id from tbs.tbs_ptyexp where id ='"
					+ docid + "' ";
			SQLQuery sqlqueryptyexp = session.createSQLQuery(sqlptyexp);
			Integer projid = (Integer) sqlqueryptyexp.uniqueResult();
			if (projid != null) {
				TbsProj tbsproj = (TbsProj) session.get(TbsProj.class, projid);
				if (cn.equals("A角部门经理审批")
						&& (pname.equals("ptyexpi") || pname.equals("ptyexpo"))) {
					String sql = "select account from bdf2_user_dept a, tbs_approver b where a.dept_id_=b.deptid and b.title like '%部门经理%' and b.title not like ('%部门经理助理%') and a.username_='"
							+ tbsproj.getBdf2User_A().getUsername() + "' ";
					SQLQuery sqlquery = session.createSQLQuery(sql);
					users = sqlquery.list();
				}
			}
		}
		if ((cn.equals("出纳审核") || cn.equals("出纳付款"))
				&& (pname.equals("ptyexpi") || pname.equals("ptyexpo"))) {
			String sql = "select account from tbs_approver where trim(title)='出纳' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("财务经理审批")
				&& (pname.equals("ptyexpi") || pname.equals("ptyexpo"))) {
			String sql = "select account from tbs_approver where trim(title)='财务部门经理' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("财务部门经理审核")
				&& (pname.equals("ptyexpi") || pname.equals("ptyexpo"))) {
			String sql = "select account from tbs_approver where trim(title)='财务部门经理' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		// =======费用收退 结束 =========

		// =======风险项目管理 开始 =========
		if (cn.equals("B角确认") && pname.equals("compsry")) {
			String sql = "select c.USERNAME_ from tbs_proj a, tbs.tbs_projcompsry b, bdf2_user c where a.id=b.PROJ_ID and c.id=a.B_ROLE_ID and b.id="
					+ docid;
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (pname.equals("compsry")) {
			String sqlcompsry = "select proj_id from tbs.tbs_projcompsry where id ='"
					+ docid + "' ";
			SQLQuery sqlquerycompsry = session.createSQLQuery(sqlcompsry);
			Integer projid_compsry = (Integer) sqlquerycompsry.uniqueResult();
			if (projid_compsry != null) {
				TbsProj tbsproj = (TbsProj) session.get(TbsProj.class,
						projid_compsry);
				if (cn.equals("A部门经理审批")
						&& (pname.equals("compsry") || pname
								.equals("compsrypay"))) {
					String sql = "select account from bdf2_user_dept a, tbs_approver b where a.dept_id_=b.deptid and b.title like '%部门经理%' and b.title not like ('%部门经理助理%') and a.username_='"
							+ tbsproj.getBdf2User_A().getUsername() + "' ";
					SQLQuery sqlquery = session.createSQLQuery(sql);
					users = sqlquery.list();
				}
			}
		}
		if (pname.equals("compsrypay")) {
			String sqlcompsry = "select proj_id from tbs.tbs_projcompsry_pay where id ='"
					+ docid + "' ";
			SQLQuery sqlquerycompsry = session.createSQLQuery(sqlcompsry);
			Integer projid_compsry = (Integer) sqlquerycompsry.uniqueResult();
			if (projid_compsry != null) {
				TbsProj tbsproj = (TbsProj) session.get(TbsProj.class,
						projid_compsry);
				if (cn.equals("A部门经理审批")
						&& (pname.equals("compsry") || pname
								.equals("compsrypay"))) {
					String sql = "select account from bdf2_user_dept a, tbs_approver b where a.dept_id_=b.deptid and b.title like '%部门经理%' and b.title not like ('%部门经理助理%') and a.username_='"
							+ tbsproj.getBdf2User_A().getUsername() + "' ";
					SQLQuery sqlquery = session.createSQLQuery(sql);
					users = sqlquery.list();
				}
			}
		}
		if (cn.equals("风管部门经理审批")
				&& (pname.equals("compsry") || pname.equals("compsrypay"))) {
			String sql = "select account from tbs_approver where trim(title) like '%风险管理部门经理%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("业务审核委员会秘书录入会议决议") && pname.equals("compsry")) {
			String sql = "select account from tbs_approver where title = '评审会秘书' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("风险管理审核委员会秘书录入会议决议") && pname.equals("compsry")) {
			String sql = "select account from tbs_approver where title = '风险管理委员会秘书' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("决定是否召开风险管理会议") && pname.equals("compsry")) {
			String sql = "select account from tbs_approver where title = '风险管理委员会秘书' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("财务部门经理审批")
				&& (pname.equals("compsrypay") || pname.equals("projrol"))) {
			String sql = "select account from tbs_approver where title like '%财务部门经理%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("分管总经理（风险）") && pname.equals("compsrypay")) {
			String sql = "select account from tbs_approver where title like '%分管风管总经理%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("总经理审批") && pname.equals("compsrypay")) {
			String sql = "select account from tbs_approver where title like '%总经理%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("出纳付款") && pname.equals("compsrypay")) {
			String sql = "select account from tbs_approver where title like '%出纳%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("风管部经理审批") && (pname.equals("projrol"))) {
			String sql = "select account from tbs_approver where trim(title) like '%风险管理部门经理%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("出纳确认金额") && (pname.equals("projrol"))) {
			String sql = "select account from tbs_approver where trim(title)='出纳' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		// =======风险项目管理 开结束=========

		// =======反担保物解除开始=========
		if ((cn.equals("风管部负责人审批") || cn.equals("风管负责人审批"))
				&& (pname.equals("cggrevoke"))) {
			String sql = "select account from tbs_approver where trim(title) like ('%风险管理部门经理%') ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("总经理审批") || (cn.equals("总经理审核"))
				&& pname.equals("cggrevoke")) {
			String sql = "select account from tbs_approver where title like '总经理' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("财务部门经理审批") && (pname.equals("cggrevoke"))) {
			String sql = "select account from tbs_approver where title like '%财务部门经理%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		if (cn.equals("评委会秘书录入") && pname.equals("cggrevoke")) {
			String sql = "select account from tbs_approver where title like '%评审会秘书%' ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			users = sqlquery.list();
		}
		// =======反担保物解除结束=========

		if (promoter.equals("admin")) {
			System.out.println(users);
			users.clear();
			users.add("admin");
			if (cn.equals("评委审批") && pname.equals("projCfm")) {
				String cfm0Id = (String) processClient.getProcessVariable(
						"cfm0Id", processInstance);
				String sql = "select b.USERNAME_ from tbs_proj_opinion a, "
						+ "bdf2_user b where a.uid=b.ID and a.CFM0_ID = "
						+ cfm0Id + " and a.CFMTYPE = 0 and a.del = 0";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				users = sqlquery.list();
			} else {
				users.add("admin");
			}
		}
		if (promoter.equals("admin2")) {
			System.out.println(users);
			users.clear();
			users.add("admin2");
			if (cn.equals("评委审批") && pname.equals("projCfm")) {
				String cfm0Id = (String) processClient.getProcessVariable(
						"cfm0Id", processInstance);
				String sql = "select b.USERNAME_ from tbs_proj_opinion a, "
						+ "bdf2_user b where a.uid=b.ID and a.CFM0_ID = "
						+ cfm0Id + " and a.CFMTYPE = 0 and a.del = 0";
				SQLQuery sqlquery = session.createSQLQuery(sql);
				users = sqlquery.list();
			} else {
				users.add("admin2");
			}
		}
				
		return users;
	}
	

	private Boolean ifOverlimit(Context context,String projid){
		Session session = context.getSession();
		String sql = "select overlimit from tbs_proj where id  = "
				+ projid ;
		SQLQuery sqlquery = session.createSQLQuery(sql);
		Integer overlimitint = (Integer)sqlquery.uniqueResult();
		if(overlimitint==null){
			overlimitint = 0;
		}
		boolean overlimit=(overlimitint==1)?true:false;
		return overlimit;
	}
}