package org.tbs.uflo.dao.cggrevoke;

import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.bstek.uflo.client.service.ProcessClient;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.ActionHandler;

@Component
public class ActDelchange implements ActionHandler {
	@Autowired
	@Qualifier(ProcessClient.BEAN_ID)
	private ProcessClient processClient;

	@SuppressWarnings("unchecked")
	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		Long processInstanceId = processInstance.getId();
		String businessId = processInstance.getBusinessId();
		ArrayList<String> revokecggSn = (ArrayList<String>) processClient
				.getProcessVariable("revokecggSn",
						Long.valueOf(processInstanceId));
		Session session = context.getSession();
		if (revokecggSn != null && revokecggSn.size() > 0) {
			for (int i = 0; i < revokecggSn.size(); i++) {
				String sqlupdate = "update tbs.tbs_proj_cgg set state=2 where valid = 1 and cgg_sn='"
						+ revokecggSn.get(i) + "'";
				SQLQuery sqlqueryupdate = session.createSQLQuery(sqlupdate);
				sqlqueryupdate.executeUpdate();

			}
		}
		String sql_id = "select id from tbs.tbs_cgg_chg where valid = 0 and proj_id='"
				+ businessId + "'";
		SQLQuery query_id = session.createSQLQuery(sql_id);
		Object obj_id = query_id.uniqueResult();
		if (obj_id != null) {
			String sqlupdate = "delete from tbs.tbs_cgg_chg where valid = 0 and proj_id='"
					+ businessId + "'";
			SQLQuery sqlqueryupdate = session.createSQLQuery(sqlupdate);
			sqlqueryupdate.executeUpdate();
		}
	}
}