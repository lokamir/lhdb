var projid;




/*=======================主表单Details按钮===================*/
/** @Bind #btnDetails.onClick */
!function(self,arg,iFrameDetails,dialogMainForm,datasetTbsProj){
	
	var data = datasetTbsProj.getData("#");
	if(data) {
		var id = data.get("id");
		var sn = data.get("sn");
	}
	var processInstanceId = data.get("by3");
	var path="org.tbs.views.projs.creation.TbsProjDetails.d?id=" + id
	+ "&psid=" + 0 + "&fromAppr=0&sn=" + sn +
	"&processInstanceId=" + processInstanceId ;
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

/*=======================导出表格按钮===================*/
/** @Bind #buttonExport.onClick */
!function(self, arg, DataGridTbsProj) {
	// 获取autoformCondition绑定的实体对象
	var id=[];
	var DataGridTbsProj = view.get("#DataGridTbsProj.selection");
	DataGridTbsProj.each(function(entity){
		id.push(entity.get("id"));
	});
	window.open('./ExportTbsProj_Main.jsp?id='+id);
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

	