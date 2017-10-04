// Global variables
var aprv = 1; // 表示来自审批界面, 0 表示来自detail按钮 2来自新增按钮
var projid;
var projsn;
var spcbtn; // 给"评审会秘书录入决议"专用的判断条件,special button
var loginusername = "${loginUsername}";

/** @Bind view.onReady */
!function(self, arg) {
	if ("${request.getParameter('aprv')}"){
			aprv = "${request.getParameter('aprv')}";
	}	
	projid = "${request.getParameter('projid')}";
	projsn = "${request.getParameter('projsn')}";
	if (aprv == 2) {
		var faloc = "${request.getParameter('faloc')}";
		var nfaloc = "${request.getParameter('nfaloc')}";
		var otloc = "${request.getParameter('otloc')}";
		var totloc = "${request.getParameter('totloc')}";
		var periodcfm = "${request.getParameter('periodcfm')}";
		var bdate = "${request.getParameter('bdate')}";
		var edate = "${request.getParameter('edate')}";
		var oldprojname = "${request.getParameter('oldprojname')}";
		var rq = new Date();
		var autoform = view.get("#AutoFormChangeMajcont");
		var dataSet = view.get("#dataSetTbsProjchangeMajcont"); 
		view.get("#tabInAppr").set("visible",false);
		view.get("#tabTbsProj").set("visible",false);
		view.get("#tabTbsProjundwrt").set("visible",false);
		view.get("#tabProjAttach").set("visible",false);
		view.get("#tabHistoryTask").set("visible",false);
		view.get("#btnSave").set("visible",true);
		view.get("#btnSave").set("action","saveChangeMajcont");
		view.get("#btnClose").set("visible",true);
		view.get("#btnSubmit").set("visible",false);
		dataSet.insert();
		dataSet.getData("#").set("projSn",projsn);
		dataSet.getData("#").set("tbsProj.id",projid);
		autoform.set("entity.oldprojname", oldprojname);
		autoform.set("entity.timestampInput", rq);
		autoform.set("entity.oldfaloc", faloc);
		autoform.set("entity.oldnfaloc", nfaloc);
		autoform.set("entity.oldotloc", otloc);
		autoform.set("entity.oldtotloc", totloc);
		autoform.set("entity.oldperiodcfm", periodcfm);
		autoform.set("entity.oldbdate", bdate);
		autoform.set("entity.oldedate", edate);	
		autoform.set("entity.newfaloc", faloc);
		autoform.set("entity.newnfaloc", nfaloc);
		autoform.set("entity.newotloc", otloc);
		autoform.set("entity.newtotloc", totloc);
		autoform.set("entity.newperiodcfm", periodcfm);
		autoform.set("entity.newbdate", bdate);
		autoform.set("entity.newedate", edate);	
	} else if (aprv == 0) {
		var projchangeid = "${request.getParameter('projchangeid')}";
		var projchangevalid = "${request.getParameter('projchangevalid')}";
		var autoform = view.get("#AutoFormChangeMajcont");
		if (projchangevalid != "0"){
			view.get("#dataSetTbsProjchangeMajcont").set("readOnly",true);
			view.get("#btnSubmit").set("visible", false);
			view.get("#btnSave").set("visible",false);
			view.get("#tabChangeMajcontCfm").set("visible",true);
		};
		view.get("#dataSetTbsProjchangeMajcont").set("parameter", projchangeid).flush();
		view.get("#tabInAppr").set("visible",false);
		view.get("#btnSave").set("action","saveChangeMajcont");
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
		var newfaloc = autoform.get("entity.newfaloc");
		autoform.set("entity.newfaloc", newfaloc);		
	} else {
		var autoform = view.get("#AutoFormChangeMajcont");
		var businessId = "${request.getParameter('businessId')}";  
		var taskId = "${request.getParameter('taskId')}";
		var params = { taskid : taskId,	};
		view.get("#dataSetTbsProjchangeMajcont").set("parameter", businessId).flush();  // 这里为了立刻让dataset有值只能用flush，不能用flushasync
		var dataSet = view.get("#dataSetTbsProjchangeMajcont").getData("#"); 
		var projid = dataSet.get("tbsProj.id"); 
		var projchangevalid = dataSet.get("valid");
		if (projchangevalid !=3 ) {	
			view.get("#listDdlOutcome").set("items",["通过","驳回"]);
			view.get("#dataSetTbsProjchangeMajcont").set("readOnly",true);
		} else {
			view.get("#listDdlOutcome").set("items",["修改确认"]);
		}
		view.get("#tabChangeMajcontCfm").set("visible",true);
		view.get("#btnPanel").set("visible",false);
		view.get("#tabControlMain").set("height","99%");
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();  
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
		view.get("#ajaxactionGetUfloNode").set("parameter",params).execute(
				function(nodename){
				if (nodename == "评审会秘书录入决议"){
					spcbtn = 1; // 到这个审批节点dataset为可写
					view.get("#dataSetTbsProjchangeMajcont").set("readOnly",false);
				}	
				});
		var newfaloc = autoform.get("entity.newfaloc");
		autoform.set("entity.newfaloc", newfaloc);	
		
		//通过主表查子表set内的id未用
		//cfmid = dataSet.get("tbsProjchangeMajcontCfmSet").current.get("id"); 
		//var nodename = dataSet.get("tbsProjchangeMajcontCfmSet").current.get("by1");
		
	}
};

/*========== 页面计算===========*/
/** @Bind #newfaloc.onPost */
/** @Bind #newnfaloc.onPost */
/** @Bind #newotloc.onPost */
!function(self,arg,newtotloc,AutoFormChangeMajcont,dataSetTbsProjchangeMajcont){
	var autoform = AutoFormChangeMajcont.get("entity"); 
	var dataSet = dataSetTbsProjchangeMajcont.getData("#");
	var faloc = autoform.get("newfaloc"); 
	var nfaloc = autoform.get("newnfaloc"); 
	var otloc = autoform.get("newotloc");
	dataSet.set("newtotloc",faloc+nfaloc+otloc);
};

/*=======数据保存后刷新父窗口，关闭自身========*/
/** @Bind #btnSave.onClick */  
!function(self, arg ,saveChangeMajcont) {
	saveChangeMajcont.execute({
		callback : function(result) {  //用回调方法是为了让字段的必填校验在界面上做出错提示
			if (result == true){    
				var autoformCondition = window.parent.$id("autoformCondition").objects[0];
				var dataSet = window.parent.$id("dataSetTbsProj").objects[0];
				var entity = autoformCondition.get("entity");
				var dialogMainForm = window.parent.$id("dialogProjDetails").objects[0];
				dialogMainForm.hide();
				dataSet.set("parameter", entity).flushAsync();
			}
		}
	});
};

/** @Bind #btnClose.onClick */
!function(self, arg) {
	var dialogMainForm = window.parent.$id("dialogProjDetails").objects[0];
	dialogMainForm.close();
};

/*=======================发起流程===================*/
/** @Bind #btnSubmit.onClick */
!function(self, arg, dataSetTbsProj, dataSetTbsProjchangeMajcont, ajaxactionStartProcess, saveChangeMajcont, ajaxactionChangeCfmAll) {
	var projid = dataSetTbsProj.getData("#").get("id");
	var projchangeid = dataSetTbsProjchangeMajcont.getData("#").get("id");
	var params ={
			opid : 1,
			loginusername : loginusername,
			projid : projid,
			projchangeid : projchangeid,
	};
	saveChangeMajcont.execute({
		callback : function(result) {  //用回调方法是为了让字段的必填校验在界面上做出错提示
			if (result == true){
				ajaxactionStartProcess.set("parameter", params).execute(function(result) {
					dorado.MessageBox.alert(result, {
						title : "趣博信息科技"
					});
				});
				window.parent.$id("dataSetTbsProj").objects[0].flushAsync();
				dataSetTbsProjchangeMajcont.flushAsync();
				dataSetTbsProjchangeMajcont.set("readOnly",true);
				self.set("visible",false);
				view.get("#btnSave").set("visible",false);
				ajaxactionChangeCfmAll.set("parameter", params).execute();
			}
		}
	});
};

/*======================待办任务，进行审批==================*/
/** @Bind #btnApprSubmit.onClick */ 
!function(self,arg,autoformOpinion,saveChangeMajcont,ajaxactionApprSubmit) {
	var outcome = autoformOpinion.get("entity.outcome");
	var comment = autoformOpinion.get("entity.comment");
	if (!outcome) {
		dorado.MessageBox.alert("请先选择审批结果！", {
			title : "趣博信息科技"
		});
		return;
	}
	if (outcome == "驳回" && !comment) {
		dorado.MessageBox.alert("驳回时审批意见不能为空！", {
			title : "趣博信息科技"
		});
		return;
	}
	else {
		if (!comment) {
			comment = "无意见";
		}
		var taskId = "${request.getParameter('taskId')}";
		var docid = "${request.getParameter('businessId')}";
		var params = {
				taskid : taskId,
				docid : docid,
				outcome : outcome,
				comment : comment,
			};
		if (outcome == "修改确认" || spcbtn == "1" ) {
			saveChangeMajcont.execute({
				callback : function(result) {  //用回调方法是为了让字段的必填校验在界面上做出错提示
					if (result == true){
						ajaxactionApprSubmit.set("parameter", params).execute();
					}
				}
			});
		}else{
			ajaxactionApprSubmit.set("parameter", params).execute();
		};
	};
};

/*=========待办任务界面关闭窗口============*/
/** @Bind #ajaxactionApprSubmit.onSuccess */
/** @Bind #btnApprClose.onClick */
!function(self, arg) {
	window.parent.closeProcessDialog('${request.getParameter("type")}');
};

/* =======================承保审批明细表=================== */
/** @Bind #datagridTbsProjundwrt.onDataRowDoubleClick */
!function(self){
	view.get("#dialogTbsProjundwrt").show();
};
/** @Bind #buttonClose.onClick */
!function(self){
	view.get("#dialogTbsProjundwrt").hide();
};


/**
 * 文件处理 开始
 */

/** @Bind #tabProjAttach.onClick */
!function(self,arg,iFrameAttach,dataSetTbsProj){
	debugger;
	var title = "ProjChangeMajcont";
	var projsn = dataSetTbsProj.getData("#").get("sn");
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

//=========双击显示反担保详细信息==========
/** @Bind #datagridTbsProjCgg.onDataRowDoubleClick */
!function() {
	var path = "";
	var entity = view.get("#datagridTbsProjCgg").getCurrentEntity("entity").get("cggStr");
	var cggSn = view.get("#datagridTbsProjCgg").getCurrentEntity("entity").get("cggSn");
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