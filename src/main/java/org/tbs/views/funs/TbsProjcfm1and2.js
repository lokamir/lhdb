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
	var by3 = dataSetVcfmall.getData("#.detail.by3");
	dataSetTbsProj.set("parameter", projid).flushAsync();
	if(doccat.indexOf("cfm1")>0){
		view.get("#tabCfm2").set("visible",false);
		view.get("#tabCfm1").set("visible",true);
		dataSetTbsProjcfm1.set("parameter", cfmid).flushAsync();
		dataSetHistoryTask.set("parameter", by3).flushAsync();
	}else if(doccat.indexOf("cfm2")>0){
		view.get("#tabCfm1").set("visible",false);
		view.get("#tabCfm2").set("visible",true);
		dataSetTbsProjcfm2.set("parameter", cfmid).flushAsync();
		dataSetHistoryTask.set("parameter", by3).flushAsync();
	}else{
		alert("出错了，不知道为什么找不到决议单");
	}
	dialogCfm.show();
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


//=============导出变更后的会议或者签批记录================
/** @Bind #btnPrintCfm1.onClick */
!function(self){
	var path =  "业务审核委员会决议.ureport.xml"; 
	var id = view.get("#dataSetTbsProjcfm1").getData("#").get("tbsProj.id");
	var cfmid = view.get("#dataSetTbsProjcfm1").getData("#").get("id");
	//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp      
    var curWwwPath=window.document.location.href;      
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp      
    var pathName=window.document.location.pathname;      
    var pos=curWwwPath.indexOf(pathName);      
    //获取主机地址，如： http://localhost:8083      
    var localhostPaht=curWwwPath.substring(0,pos);      
    //获取带"/"的项目名，如：/uimcardprj      
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);      
    var pref = localhostPaht+projectName;
    window.open(pref+"/ureport/preview?_t=1,5&_n=业务审核委员会决议&_u=file:"+path+"&id="+id+"&cfmid="+cfmid);
};

/** @Bind #btnPrintCfm2.onClick */
!function(self){
	var path =  "集体审定签批表.ureport.xml"; 
	var id = view.get("#dataSetTbsProjcfm2").getData("#").get("tbsProj.id");
	var cfmid = view.get("#dataSetTbsProjcfm2").getData("#").get("id");
	//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp      
    var curWwwPath=window.document.location.href;      
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp      
    var pathName=window.document.location.pathname;      
    var pos=curWwwPath.indexOf(pathName);      
    //获取主机地址，如： http://localhost:8083      
    var localhostPaht=curWwwPath.substring(0,pos);      
    //获取带"/"的项目名，如：/uimcardprj      
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);      
    var pref = localhostPaht+projectName;
    window.open(pref+"/ureport/preview?_t=1,5&_n=集体审定签批表&_u=file:"+path+"&id="+id+"&cfmid="+cfmid);
};

/** @Bind #riskavoidTextareaCfm2.onClick */
!function(self){
	view.get("#autoFormTbsProjcfm2").set("visible",true);
	view.get("#riskavoidCfm2").set("visible",true);
	view.get("#memoCfm2").set("visible",false);
	view.get("#dialogUeditor").show();
};
/** @Bind #memoTextareaCfm2.onClick */
!function(self){
	view.get("#autoFormTbsProjcfm2").set("visible",true);
	view.get("#riskavoidCfm2").set("visible",false);
	view.get("#memoCfm2").set("visible",true);
	view.get("#dialogUeditor").show();
};
/** @Bind #riskavoidTextareaCfm1.onClick */
!function(self){
	view.get("#autoFormTbsProjcfm1").set("visible",true);
	view.get("#riskavoidCfm1").set("visible",true);
	view.get("#memoCfm1").set("visible",false);
	view.get("#dialogUeditor").show();
};
/** @Bind #memoTextareaCfm1.onClick */
!function(self){
	view.get("#autoFormTbsProjcfm1").set("visible",true);
	view.get("#riskavoidCfm1").set("visible",false);
	view.get("#memoCfm1").set("visible",true);
	view.get("#dialogUeditor").show();
};