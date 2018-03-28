var projid ;


/** @Bind view.onReady */
!function(self,arg,dataSetVcfmall){
	debugger;
	projid = "${request.getParameter('projid')}";
	if(projid){
		dataSetVcfmall.set("parameter", projid).flushAsync();
	}else{
		dataSetVcfmall.set("parameter", projid).flushAsync();
	}
};



//==========双击显示审议单明细===============
/** @Bind #dataGridCfm.onDataRowDoubleClick */
!function(self,dataSetVcfmall,dataSetTbsProjcfm1,dataSetTbsProjcfm2,dialogCfm,dataSetTbsProj,dataSetHistoryTask){
	var doccat = dataSetVcfmall.getData("#.detail.doccat");
	var cfmid = dataSetVcfmall.getData("#.detail.cfmid");
	var projid = dataSetVcfmall.getData("#.detail.projid");
	dataSetTbsProj.set("parameter", projid).flushAsync();
	if(doccat.indexOf("cfm1")>0){
		view.get("#tabCfm2").set("visible",false);
		dataSetTbsProjcfm1.set("parameter", cfmid).flushAsync();
	}else if(doccat.indexOf("cfm2")>0){
		view.get("#tabCfm1").set("visible",false);
		dataSetTbsProjcfm2.set("parameter", cfmid).flushAsync();
	}else{
		alert("出错了，不知道为什么找不到决议单");
	}
	dialogCfm.show();
};

/** @Bind #tabHis.onClick */
!function(self,dataSetVcfmall,dataSetHistoryTask){
	var by3 = dataSetVcfmall.getData("#.detail.by3");
	dataSetHistoryTask.set("parameter", by3).flushAsync();
};

//==========双击审批历史明细记录===============
/** @Bind #dataGridHis.onDataRowDoubleClick */
!function(self) {
	var path = "org.tbs.views.funs.MyTbsFunApprc.d?id=";
	var taskHisId = self.getCurrentItem().get("id");
	view.get("#DialogTbsFunApprc").show();
	view.get("#iFrameTbsFunApprc").set("path", path+taskHisId);
};

//=========双击显示反担保详细信息==========
/** @Bind #dgTbsProjCgg.onDataRowDoubleClick */
!function() {
	var path = "";
	var entity = view.get("#dgTbsProjCgg").getCurrentEntity("entity").get("cggStr");
	var cggSn = view.get("#dgTbsProjCgg").getCurrentEntity("entity").get("cggSn");
	var cat1name;
	var cat2name;
	var cat3name;
	var customerId = view.get("#dataSetTbsProjcfm0").getData("#.tbsProj").get("tbsCustomer").get("id");;
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

/**
 * 文件处理 开始
 */

/** @Bind #tabAttach.onClick */
!function(self,arg,iFrameAttach,dataSetVcfmall){
	debugger;
	var title = "ProjChangeMajcont";
	var projsn = dataSetVcfmall.getData("#.detail").get("projsn");
	var fid ="项目三要素变更";
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