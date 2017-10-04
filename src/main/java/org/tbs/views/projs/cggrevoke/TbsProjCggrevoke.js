var docid;

/*提交审批按钮权限*/
/** @Bind @dataTypeTbsProjCgg.onEntityLoad */
!function(self,arg,dataSetHistoryTask,dataSetTbsProj){
	var dataSetTbsProj = view.get("#dataSetTbsProj");
	var dataSetHistoryTask = view.get("#dataSetHistoryTask");
	var array = [];
	if(dataSetTbsProj.getData("#.tbsProjCggSet")){
	var tbsProjCggs = dataSetTbsProj.getData("#.tbsProjCggSet");
	tbsProjCggs.each(function(entity){
		if(entity.get("by1")){
			array.push(entity.get("by1"));
		}
	});
	var processInstanceId = array[0]; 
	if(processInstanceId){
		view.get("#buttonStart").set("disabled",true);
		view.get("#rowSelectColumn").set("visible",false);
		dataSetHistoryTask.set("parameter",processInstanceId).flushAsync();
	}else{
		view.get("#buttonStart").set("disabled",true);
		view.get("#rowSelectColumn").set("visible",true);
		dataSetHistoryTask.set("parameter",0).flushAsync();
		}
	}
};


/** @Bind #DataGridTbsProj.onCurrentChange */
!function(self,arg,dataSetTbsProj){
	dataSetTbsProj.getData("#.tbsProjCggSet").flushAsync();
};


/*DataGrid选中前提交按钮不能使用*/
/** @Bind #DataGridTbsProjCgg.beforeSelectionChange */
!function(self,arg){
	view.get("#buttonStart").set("disabled",false);
};

/*============autoformCondition快速查询===================*/
/**@Bind @tempConditions.onDataChange */
!function(self,arg,autoformCondition,dataSetTbsProj){
var entity = autoformCondition.get("entity");
dataSetTbsProj.set("parameter",entity).flushAsync();

};

/*=======================主表单查询按钮===================*/
/** @Bind #buttonQuery.onClick */
!function(self, arg, autoformCondition, dataSetTbsProj) {
	// 获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	// 将实体对象作为参数传入，并异步刷新
	dataSetTbsProj.set("parameter", entity).flushAsync();
};


/*=======================发送审批===================*/
/** @Bind #buttonStart.onClick */
!function(self,arg,DataGridTbsProjCgg,ajaxactionStartProcess){
	var entity = DataGridTbsProjCgg.get("selection");
	if(entity == ""){
		dorado.MessageBox.alert("请先选择反担保物！", {
			title : "趣博信息科技"
		});
	}else{
		ajaxactionStartProcess.execute();
	}
};

/** @Bind #ajaxactionStartProcess.beforeExecute */ 
!function(self,arg,dataSetTbsProj,DataGridTbsProjCgg,ajaxactionStartProcess){
	var entity = DataGridTbsProjCgg.get("selection");
	var id_value = dataSetTbsProj.getData("#.id");
	var projName = dataSetTbsProj.getData("#.projName");
	var bzjzy_value=0;
	var revokecggSn = new Array();
	entity.each(function(i){
		if (i.get("state") == 0||i.get("state") == null) {
			revokecggSn.push(i.get("cggSn"));
		}
		if (i.get("cggCname").indexOf("保证金质押") >= 0) {
			bzjzy_value = 1;
		}
	});
	var maParamers = {
			docid : id_value,
			bzjzy : bzjzy_value,
			revokecggSn : revokecggSn,
			projName : projName
	};
	ajaxactionStartProcess.set("parameter", maParamers);
}; 

/* ========保存后刷新========= */
/** @Bind #ajaxactionStartProcess.onSuccess */
!function(self, arg,dataSetTbsProj) {
	dataSetTbsProj.getData("#.tbsProjCggSet").flushAsync();
};

/* =======================项目明细表=================== */
/** @Bind #buttonDetail.onClick */
!function(self){
	view.get("#dialogTbsProj").show();
};
/** @Bind #buttonClose.onClick */
!function(self){
	view.get("#dialogTbsProj").hide();
};


/*=========增改删的cgg内容显示不同颜色==========*/
/** @Bind #DataGridTbsProjCgg.onRenderRow */
!function(self,arg){
	var state = arg.data.get("state");
	if (state == null) {
		arg.dom.style.background = "";
	} else if (state == 0) {
		arg.dom.style.background = "";
	} else if (state == 1) {
		arg.dom.style.background = "#a70000";
	} else if (state == 2) {
		arg.dom.style.background = "#CDCD00";
	} 
};

//=========双击显示反担保详细信息==========
/** @Bind #DataGridTbsProjCgg.onDoubleClick */
!function() {
	var path = "";
	var entity = view.get("#DataGridTbsProjCgg").getCurrentEntity("entity").get("cggStr");
	var cggSn = view.get("#DataGridTbsProjCgg").getCurrentEntity("entity").get("cggSn");
	var cat1name;
	var cat2name;
	var cat3name;
	var customerId = view.get("#dataSetTbsProj").getData("#").get("tbsCustomer").get("id");;
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

/** @Bind #dataGridHis.onDataRowDoubleClick */
!function(self) {
	var path = "org.tbs.views.funs.MyTbsFunApprc.d?id=";
	var taskHisId = self.getCurrentItem().get("id");
	view.get("#DialogTbsFunApprc").show();
	view.get("#iFrameTbsFunApprc").set("path", path+taskHisId);
};

/**
 * 文件处理 开始
 */
/** @Bind #buttonFlsmgm.onClick */
!function(self,arg,DialogTbsFunApprc,iFrameAttach,dataSetTbsProj){
	var title = "Cggrevoke";
	var projsn = dataSetTbsProj.getData("#").get("sn");
	var fid ="反担保解除";
	var by3 =null;
	var typid =null;
	var invisible =true;//全流程上传附件
	if(projsn){
	var attachpath="org.tbs.views.funs.MyFile.d?by1=" + title 
			+ "&by2=" + projsn
			+ "&by3=" + by3
			+ "&fid=" + fid
			+ "&typid=" + typid
			+ "&uploadbutton=" +invisible
			+ "&dt=" + new Date();
			iFrameAttach.set("path",attachpath);
			DialogTbsFunApprc.show();
	}else{
		dorado.MessageBox.alert("请选择关联客户", {
			title : "趣博信息科技"
		});
	}
};
/**
 * 文件处理  结束
 */
