package org.tbs.uflo.dao.common;

import org.springframework.stereotype.Component;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;

/**
 * @author Hank
 *
 * 路由决策的判断bean
 * 返回字符串，与下级流程联线的名称做对应关系
 * 
 */

@Component
public class GetDecisionHandler implements
		com.bstek.uflo.process.handler.DecisionHandler {

	@Override
	public String handle(Context context , ProcessInstance processInstance) {
		// TODO Auto-generated method stub
		return null;
	}

}
