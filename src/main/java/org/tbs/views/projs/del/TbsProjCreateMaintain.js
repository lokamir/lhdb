// Global variables
var psid;
var valid;
var docid;
var projid;



/*=======================主表单Details按钮===================*/
/** @Bind #btnDetails.onClick */
!function(self,arg,iFrameDetails,dialogMainForm,datasetTbsProj){
	
	var data = datasetTbsProj.getData("#");
	if(data) {
		var id = data.get("id");
		var psid = data.get("tbsBasPs").get("id");
		var sn = data.get("sn");
	}
	var processInstanceId = data.get("by3");
	var path="org.tbs.views.projs.creation.TbsProjDetails.d?id=" + id
	+ "&psid=" + psid + "&fromAppr=0&sn=" + sn +
	"&processInstanceId=" + processInstanceId +
	"&dt=" + new Date();
	if(id){
		if(path){
			dialogMainForm.show();
			iFrameDetails.set("path",path);
		}
	} else {
		dorado.MessageBox.alert("请先选择一个项目！",{title:"趣博信息技术"});
	}
};

/*=======================主表单查询按钮===================*/
/** @Bind #btnQuery.onClick */
!function(self, arg, autoformCondition, datasetTbsProj) {
	// 获取autoformCondition绑定的实体对象
	var condition = autoformCondition.get("entity");
	// 将实体对象作为参数传入，并异步刷新
	datasetTbsProj.set("parameter", condition).flushAsync();
};


/* =======================主表删除按钮=================== */
/** @Bind #btnDel.onClick */
!function(self, arg, datasetTbsProj,ajaxDelProj) 
	{
	var projid = datasetTbsProj.getData("#.id");
	dorado.MessageBox.confirm("您真的要删除此项目吗？\n此操作无法恢复！",
			{title:"趣博信息技术",
			 detailCallback:function(btnID, text){
			 									  if (btnID == "yes"){
			 										 ajaxDelProj.set("parameter",{"projid":projid}).execute();
				                                                    }
												  } 	
			}
	);

};

/** @Bind #ajaxDelProj.onSuccess */
!function(autoformCondition,datasetTbsProj){
	var condition = autoformCondition.get("entity");
	datasetTbsProj.set("parameter", condition).flushAsync();
};

/** @Bind #ajaxDelProj.beforeExecute */
!function(ajaxDelCfm0,datasetTbsProj){
	var projid = datasetTbsProj.getData("#.id");
	ajaxDelCfm0.set("parameter",{"projid":projid}).execute();
};

/** @Bind #ajaxDelCfm0.beforeExecute */
!function(ajaxDelCfm1,datasetTbsProj){
	var projid = datasetTbsProj.getData("#.id");
	ajaxDelCfm1.set("parameter",{"projid":projid}).execute();
};

/** @Bind #ajaxDelCfm1.beforeExecute */
!function(ajaxDelCfm2,datasetTbsProj){
	var projid = datasetTbsProj.getData("#.id");
	ajaxDelCfm2.set("parameter",{"projid":projid}).execute();
};

/** @Bind #ajaxDelCfm2.beforeExecute */
!function(ajaxDelEaa,datasetTbsProj){
	var projid = datasetTbsProj.getData("#.id");
	ajaxDelEaa.set("parameter",{"projid":projid}).execute();
};

/** @Bind #ajaxDelEaa.beforeExecute */
!function(ajaxDelCusCompsry,datasetTbsProj){
	var cusid = datasetTbsProj.getData("#.tbsCustomer.id");
	ajaxDelCusCompsry.set("parameter",{"cusid":cusid}).execute();
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


