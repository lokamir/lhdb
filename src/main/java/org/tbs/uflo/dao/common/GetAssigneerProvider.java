package org.tbs.uflo.dao.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.assign.AssigneeProvider;
import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;

/** @author Hank
 * 
 * 指定下级Node的审批人（候选人）
 * 
**/

@Component
public class GetAssigneerProvider implements AssigneeProvider {

	@Override  //返回的是ide里面的【可用任务处理人列表】
	public String getName() {
		return "TBS-由上级审批节点决定任务处理人";
	}

	@Override                               //这个Id就是下面写的"su1"
	public Collection<String> getUsers(String entiyId, Context context,
			ProcessInstance processInstance) {
		List<String> users=new ArrayList<String>();
		if (entiyId.equals("su1")){
			users.add("user1");
			users.add("user2");
			users.add("user3");
		}else{
			users.add("user-haha");
		}
		return users;
	}

	@Override
	public void queryEntities(PageQuery<Entity> pageQuery, String parentId) {
		Entity entity=new Entity("su1","任务处理候选人1");  //实际使用要查库
		List<Entity> entities = new ArrayList<Entity>();  //实际使用要查库
		pageQuery.setRecordCount(1);  //有几个指定的审批人就写几个数字
		entities.add(entity);
		pageQuery.setResult(entities);
		

	}

	@Override
	public boolean isTree() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean disable() {
		// TODO Auto-generated method stub
		return false;
	}

}
