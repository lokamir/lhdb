/*==========发起决策审批======主表单Details按钮===================*/
/** @Bind #btnDetails.onClick */
!function(self,arg,iFrameDetails,dialogMainForm,datasetTbsProj){
	
	var data = datasetTbsProj.getData("#");
	if(data) {
		var id = data.get("id");
		var psid = data.get("tbsBasPs").get("id");
		var sn = data.get("sn");
		var processInstanceId = data.get("by2");
		var path="org.tbs.views.projs.cfm.TbsProjCfmDetails.d?id=" + id
		+ "&psid=" + psid + "&fromAppr=0&sn=" + sn +
		"&processInstanceId=" + processInstanceId;
	}
	if(id){
		if(path){
			dialogMainForm.show();
			iFrameDetails.set("path",path);
		}
	} else {
		dorado.MessageBox.alert("请先选择一行!!");
	}
};

/*=======================主表单查询按钮===================*/
/** @Bind #btnQuery.onClick */
!function(self, arg, autoformCondition, datasetTbsProj) {
	// 获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	// 将实体对象作为参数传入，并异步刷新
	datasetTbsProj.set("parameter", entity).flushAsync();
};

/* =======================主表单修改按钮=================== */
/** @Bind #buttonEdit.onClick */
!function(self, arg, dialogMainForm) {
	dialogMainForm.show();
};

/* =======================主表删除按钮=================== */
/** @Bind #buttonDel.onClick */
!function(self, arg, datasetTbsProj) {
	var entity = datasetTbsProj.get("data:#");
	entity.remove();
};

/* =======================dialog确认按钮及下拉框空值解决方案+查询条件下拉空值解决=================== */
/** @Bind #buttonSave.onClick */
!function(self, arg, dialogMainForm, autoformTbsProj, datasetTbsProj) {
	// 子tab中的datagrid去空
	var TbsProjCgg = datasetTbsProj.getData("#").get("tbsProjCggSet");
	TbsProjCgg.each(function(entity) {
		if (entity.get("cggId") == "") {
			entity.set("cggId", null);
		}
	});

	var TbsBasIdcardtype = datasetTbsProj.getData("#").get("tbsProjBizvtSet");
	TbsBasIdcardtype.each(function(entity) {
		if (entity.get("tbsBasBizvar") == "") {
			entity.set("tbsBasBizvar", null);
		}
		if (entity.get("tbsBasBiztype") == "") {
			entity.set("tbsBasBiztype", null);
		}
	});

	var TbsBasEnttype = datasetTbsProj.getData("#").get("tbsProjBankSet");
	TbsBasEnttype.each(function(entity) {
		if (entity.get("tbsBasBank_M") == "") {
			entity.set("tbsBasBank_M", null);
		}
		if (entity.get("tbsBasBank_S") == "") {
			entity.set("tbsBasBank_S", null);
		}
	});
	// autoform去空
	if (autoformTbsProj.get("entity.tbsCustomer") == "") {
		autoformTbsProj.set("entity.tbsCustomer", null);
	}

	if (autoformTbsProj.get("entity.tbsBasConsway") == "") {
		autoformTbsProj.set("entity.tbsBasConsway", null);
	}

	if (autoformTbsProj.get("entity.tbsBasGovfund") == "") {
		autoformTbsProj.set("entity.tbsBasGovfund", null);
	}

	if (autoformTbsProj.get("entity.tbsBasCurrency") == "") {
		autoformTbsProj.set("entity.tbsBasCurrency", null);
	}

	if (autoformTbsProj.get("entity.bdf2User_A") == "") {
		autoformTbsProj.set("entity.bdf2User_A", null);
	}

	if (autoformTbsProj.get("entity.bdf2User_B") == "") {
		autoformTbsProj.set("entity.bdf2User_B", null);
	}

	if (autoformTbsProj.get("entity.tbsBasPs") == "") {
		autoformTbsProj.set("entity.tbsBasPs", null);
	}

	 var targetData =datasetTbsProj.getData("#").get("tbsProjBizvtSet");
	 var targetData2 =datasetTbsProj.getData("#").get("tbsProjBizvtSet");
	 var i=0,j=0;
	 targetData.each(function(entity){
	  if(entity.get("tbsBasBizvar.id")){
	   j++;
	  };
	  targetData2.each(function(entity2){
	  if(entity.get("tbsBasBizvar.id")==entity2.get("tbsBasBizvar.id")){
	   i++;
	  }
	 });
	 });
	  if(i>j){
	    dorado.MessageBox.alert("业务类型和品种有重复，请删除后再确认！",{title:"趣博信息科技"});
	   return;
	  }else{
		  dialogMainForm.hide();
	  }
};

/* =======================dialog取消按钮-清空当前数据操作=================== */
/** @Bind #buttonCancel.onClick */
!function(self, arg, dialogMainForm, datasetTbsProj) {
	dialogMainForm.hide();
	datasetTbsProj.get("data:#").cancel();
	 datasetTbsProj.getData("#").get("tbsProjBizvtSet").cancel();
	 datasetTbsProj.getData("#").get("tbsProjBankSet").cancel();
};

/* ========保存后刷新，执行query相同的方法========= */
/** @Bind #updateActionSave.onSuccess */
!function(self, arg, autoformCondition, datasetTbsProj) {
	// 获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	// 将实体对象作为参数传入，并异步刷新
	datasetTbsProj.set("parameter", entity).flushAsync();
};

/* =======================valid的key-vlaue=================== */
/** @Bind #autoformCondition.onReady */
!function(self, arg, text) {
	text.set("mapping", [ {
		key : "2",
		value : "全部"
	}, {
		key : "1",
		value : "是"
	}, {
		key : "0",
		value : "否"
	} ]);
};

/**
 * 文件处理 开始
 */

/** @Bind #tabAttach.onClick */
!function(self,arg,iFrameAttach,datasetTbsProj){
	var title = "projCreate";
	var projsn = datasetTbsProj.getData("#").get("sn");
	var fid ="项目受理";
	var by3 =null;
	var typid =null;
	var attachpath="org.tbs.views.funs.MyFile.d?by1=" + title 
			+ "&by2=" + projsn
			+ "&by3=" + by3
			+ "&fid=" + fid
			+ "&typid=" + typid
			+ "&dt=" + new Date();
			iFrameAttach.set("path",attachpath);
};

/**
 * 文件处理  结束
 */


//=========双击显示反担保详细信息==========
/** @Bind #UIdatagridTbsProjCgg.onDataRowDoubleClick */
!function() {
	var path = "";
	var entity = view.get("#UIdatagridTbsProjCgg").getCurrentEntity("entity").get("cggStr");
	var cggSn = view.get("#UIdatagridTbsProjCgg").getCurrentEntity("entity").get("cggSn");
	var cat1name;
	var cat2name;
	var cat3name;
	var customerId = view.get("#datasetTbsProj").getData("#").get("tbsCustomer").get("id");;
	if(entity[0]){
	cat1name = entity[0].cat1name;
	cat2name = entity[0].cat2name;
	cat3name = entity[0].cat3name;
	}else{
		cat1name = entity.cat1name;
		cat2name = entity.cat2name;
		cat3name = entity.cat3name;
	}	
	if (cat3name) {
		path = "org.tbs.views.funs.CggDetail.d?fromAppr=0&customerId="
				+ customerId + "&cat=" + cat3name + "&cggSn=" + cggSn;
	} else if (cat2name) {
		path = "org.tbs.views.funs.CggDetail.d?fromAppr=0&customerId="
				+ customerId + "&cat=" + cat2name + "&cggSn=" + cggSn;
	} else if (cat1name) {
		path = "org.tbs.views.funs.CggDetail.d?fromAppr=0&customerId="
				+ customerId + "&cat=" + cat1name + "&cggSn=" + cggSn;
	}
	view.get("#dialogCggMaintain").show();
	view.get("#iFrameCggMaintain").set("path", path);
};
