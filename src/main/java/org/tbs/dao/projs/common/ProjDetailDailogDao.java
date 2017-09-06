/**
 * 项目详细信息查询，用于detailDialog
 *
 ***/

package org.tbs.dao.projs.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.tbs.dao.funs.GetSysInfo;
import org.tbs.entity.Bdf2User;
import org.tbs.entity.TbsProj;
import org.tbs.entity.TbsProjCgg;
import org.tbs.entity.TbsProjundwrt;
import org.tbs.entity.TbsProjundwrtCfmar;
import org.tbs.entity.Vcggall;
import com.bstek.bdf2.core.orm.hibernate.HibernateDao;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.entity.EntityUtils;


@Component
public class ProjDetailDailogDao extends HibernateDao {

	@Resource
	private GetSysInfo gsi;

	@DataProvider
	// for single data with v-property Autoform显示
	public Collection<TbsProj> loadSingleProj(Integer param) throws Exception {
		List<TbsProj> results = new ArrayList<TbsProj>();
		Collection<TbsProj> tbsProjs = this.query("from "
				+ TbsProj.class.getName() + " where id = " + param);
		for (TbsProj tbsProj : tbsProjs) {
			TbsProj targetData = EntityUtils.toEntity(tbsProj);
			List<Bdf2User> bdf2Users = (List<Bdf2User>) gsi
					.getCnameById(tbsProj.getKeyinId());
			EntityUtils.setValue(targetData, "VOcname", bdf2Users.get(0)
					.getCname());
			results.add(targetData);
		}
		return results;
	}
	
	@DataProvider  // 承保审批单 DataGrid列表显示 
	public Collection<TbsProjundwrt> loadUndwrtByProjid(String projId) throws Exception {
		return this.query("from " + TbsProjundwrt.class.getName() + " where proj_id=" + projId + " order by id desc");
	}
	
	@DataProvider  // 承保补录单 DataGrid列表显示
	public Collection<TbsProjundwrtCfmar> loadUndwrtcfmarByProjid(String projId) throws Exception {
		return this.query("from " + TbsProjundwrtCfmar.class.getName() + " where proj_id=" + projId + " order by id desc");
	}
	
	@DataProvider  // 承保审批单 单条数据 Autoform 显示
	public Collection<TbsProjundwrt> loadSingleUndwrt(String undwrtId) throws Exception {
		return this.query("from " + TbsProjundwrt.class.getName() + " where id="+ undwrtId);
	}

	@DataProvider
	public Collection<Vcggall> getDy(Integer i) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> sns = this.query("select cggSn from "
				+ TbsProjCgg.class.getName());
		if (sns.size() == 0) {
			sns.add("");
		}
		map.put("sns", sns);
		map.put("i", i);
		if (i != null) {
			Collection<Vcggall> A = this.query(
					"from " + Vcggall.class.getName() + " where cusid = :i"
							+ " and sn.sn not in (:sns)", map);
			return A;
		} else {
			return this.query("from " + Vcggall.class.getName());
		}
	}
}
