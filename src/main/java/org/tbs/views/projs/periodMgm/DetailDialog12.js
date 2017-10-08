// Global variables
var aprv = 1; // 表示来自审批界面, 0表示来自detail按钮, 2来自新增按钮
var projid;//项目id
var keyinid;//录入人
var projsn ;//项目编号
var projName ;//项目名称
var menu;//表示来自哪一个页面
var chktype;//1表示不定期检查、 2表示定期检查
var projcheckby2;//表示是否生成过决议单

/** @Bind view.onReady */
!function(self, arg) {
	if ("${request.getParameter('aprv')}"){
		aprv = "${request.getParameter('aprv')}";
	}	
	projid = "${request.getParameter('projid')}";
	keyinid = "${request.getParameter('keyinid')}";
	projsn = "${request.getParameter('projsn')}";
	projName = "${request.getParameter('projName')}";
	menu = "${request.getParameter('menu')}";
	projcheckby2 = "${request.getParameter('projcheckby2')}";
	view.get("#dataSetTbsProj").set("parameter",projid).flush();
	var tbsProj = view.get("#dataSetTbsProj").getData("#");
	if (aprv == 2) {
		var rq = new Date();
		var autoform = view.get("#AutoFormTbsProjCheck");
		var dataSet = view.get("#dataSetTbsProjCheck");
		view.get("#tabInAppr").set("visible",false);
		view.get("#tabTbsProj").set("visible",false);
		view.get("#tabTbsProjundwrt").set("visible",false);
		view.get("#tabHistoryTask").set("visible",false);
		view.get("#btnSave").set("visible",true);
		view.get("#btnSave").set("action","saveTbsProjCheck");
		view.get("#btnClose").set("visible",true);
		view.get("#btnSubmit").set("visible",false);
		if(menu == "IregInsp"){
			chktype = 1;
		}else if(menu == "PerInsp"){
			chktype = 0;
		}
		dataSet.set("readOnly",false);
		dataSet.insert({
			"projSn":projsn,
			"tbsProj":tbsProj,
			"chktype":chktype
		});
		autoform.set("entity.timestampInput", rq);
	} else if (aprv == 0) {
		var projcheckid = "${request.getParameter('projcheckid')}";
		var projcheckvalid = "${request.getParameter('projcheckvalid')}";
		if (projcheckvalid == "0"||projcheckvalid == ""){
			view.get("#dataSetTbsProjCheck").set("readOnly",false);
			view.get("#btnSubmit").set("visible", true);
			view.get("#btnSave").set("visible",true);
		}else{
			view.get("#dataSetTbsProjCheck").set("readOnly",true);
			view.get("#btnSubmit").set("visible", false);
			view.get("#btnSave").set("visible",false);
		}
		if (menu == "IregInsp"){
			view.get("#tabTbsProjCheck").set("caption","不定期检查");
		}else if(menu == "PerInsp"){
			view.get("#tabTbsProjCheck").set("caption","定期检查");
		}
		if(tbsProj.get("risk") == 1 && projcheckby2 == 1 ){
			view.get("#tabRiskProjcfm").set("visible", true);
			var autoformTbsRiskProjcfm = view.get("#autoformTbsRiskProjcfm");
			var keyin = autoformTbsRiskProjcfm.getElement("keyinId");
			keyin.set("visible", true);//显示该填表人名字
		}else{
			view.get("#tabRiskProjcfm").set("visible", false);
		}
		view.get("#tabInAppr").set("visible",false);
		view.get("#btnSave").set("action","saveTbsProjCheck");
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
		view.get("#dataSetTbsProjCheck").set("parameter", projcheckid).flushAsync();
	} else {
		view.get("#dataSetTbsProjCheck").flushAsync();
		var taskId = "${request.getParameter('taskId')}";
		var param = {
			taskId : taskId
		};	
		view.get("#btnPanel").set("visible",false);
		view.get("#tabControlMain").set("height","99%");
		view.get("#ajaxActionGetMenu").set("parameter", param);
		view.get("#ajaxActionGetMenu").execute();
	}
};

/** @Bind #ajaxActionGetMenu.onSuccess */ 
!function(self, dataSetTbsProj,listDdlOutcome,tabRiskProjcfm){
	var tabInAppr = view.get("#tabInAppr");
	var projid = view.get("#dataSetTbsProjCheck").getData("#").get("tbsProj.id"); 
	view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();  
	view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
	valiables = self.get("returnValue");  // 获取valiables
	menu = valiables.menu;
	var projcheckby2 = view.get("#dataSetTbsProjCheck").getData("#").get("by2");
	// 权限控制
	if (valiables.aprv == "approve") {
		listDdlOutcome.set("items", [ "正常", "不正常" ]);
		tabInAppr.set("visible", true);
	}
	if (valiables.node == "是否上会") {
		view.get("#outcome").set("label","是否上会");
		listDdlOutcome.set("items", [ "是","否" ]);
		tabInAppr.set("visible", true);
		view.get("#dataSetTbsProjCheck").getData("#").set("valid",3);
	}
	if (valiables.node == "风管委员会秘书录入“风管委员会决议单”") {
		listDdlOutcome.set("items", [ "确认" ]);
		tabInAppr.set("visible", true);
		dataSetTbsProj.set("readOnly", false);;
	}
	if (valiables.menu == "PerInsp") { //控制TAB显示	
		tabRiskProjcfm.set("visible", false);
	}
	if (valiables.menu == "IregInsp") { //控制TAB隐藏
		if(projcheckby2 == 1){
		tabRiskProjcfm.set("visible", true);
		}else{
			tabRiskProjcfm.set("visible", false);
		}
	}
};

/*=======数据保存后刷新父窗口，关闭自身========*/
/** @Bind #btnSave.onClick */
!function(self, arg, saveTbsProjCheck) {
	saveTbsProjCheck.execute({
		callback : function(result) {  //用回调方法是为了让字段的必填校验在execute失败时，界面上做出错提示
			if (result == true){    
				window.parent.$id("dialogProjDetails").objects[0].hide();
			}
		}
	});
};

/*======关闭窗口按钮==========*/
/** @Bind #btnClose.onClick */
!function(self, arg) {
	var dialogMainForm = window.parent.$id("dialogProjDetails").objects[0];
	dialogMainForm.close();
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

/*=======================发起流程===================*/
/** @Bind #btnSubmit.onClick */
!function(self, arg, dataSetTbsProj, dataSetTbsProjCheck, ajaxactionStartProcess, saveTbsProjCheck) {
	var projcheckid = dataSetTbsProjCheck.getData("#").get("id");
	var projchecksn = dataSetTbsProjCheck.getData("#").get("sn");
	var params ={
			projid : projid,
			keyinid : keyinid,
			projcheckid : projcheckid,
			projchecksn : projchecksn,
			projName : projName,
			menu : menu,
			projsn : projsn
	};
	saveTbsProjCheck.execute();
	ajaxactionStartProcess.set("parameter", params).execute(function(result) {
		dorado.MessageBox.alert(result, {
			title : "趣博信息科技"
		});
	});
	window.parent.$id("dataSetTbsProj").objects[0].flushAsync();
	dataSetTbsProjCheck.flushAsync();
	dataSetTbsProjCheck.set("readOnly",true);
	self.set("visible",false);
	view.get("#btnSave").set("visible",false);
};

/*======================待办任务，进行审批==================*/
/** @Bind #btnApprSubmit.onClick */ 
!function(self,arg,autoformOpinion,ajaxactionApprSubmit) {
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
				taskId : taskId,
				docid : docid,
				outcome : outcome,
				comment : comment,
			};
		if (outcome == "确认" ) {
			var userid = "${dorado.getDataProvider('el#Uid').getResult()}";
			var dataSetTbsProj = view.get("#dataSetTbsProj");
			var entity = dataSetTbsProj.getData("#.#tbsRiskProjcfmSet");
			entity.set("valid",3);
			entity.set("keyinId",userid);
			view.get("#saveTbsRiskProjcfm").execute(function(){
				ajaxactionApprSubmit.set("parameter", params).execute();
				self.set("action", ajaxactionApprSubmit);
			});
		} else {
			ajaxactionApprSubmit.set("parameter", params).execute();
			self.set("action", ajaxactionApprSubmit);
		};		
	};
};

/*=========待办任务界面关闭窗口============*/
/** @Bind #ajaxactionApprSubmit.onSuccess */
/** @Bind #btnApprClose.onClick */
!function(self, arg) {
	window.parent.closeProcessDialog('${request.getParameter("type")}');
};

/* =================审批历史纪录=============== */
/** @Bind #tabControlMain.onTabChange */
!function(self,dataSetTbsProjCheck) {
	var tabControlMain = view.get("#tabControlMain");
	if (tabControlMain.get("currentTab.caption") == "审批历史记录") {
		var processInstanceId;
		processInstanceId = dataSetTbsProjCheck.getData("#").get("by1");
		view.get("#dataSetHistoryTask").set("parameter", processInstanceId).flushAsync();
	}
};


/**
 * 文件处理 开始
 */

/** @Bind #tabProjAttach.onClick */
!function(self,arg,iFrameAttach,dataSetTbsProj){
	var title = "periodMgm";
	var projsn = dataSetTbsProj.getData("#").get("sn");
	var fid =null;
	if (menu == "IregInsp") {
		fid = "不定期检查";
	} else if (menu == "PerInsp") {
		fid = "定期检查";
	}
	var attachpath="org.tbs.views.funs.MyFile.d?by1=" + title 
			+ "&by2=" + projsn
			+ "&by3=" + null
			+ "&fid=" + fid
			+ "&typid=" + null
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