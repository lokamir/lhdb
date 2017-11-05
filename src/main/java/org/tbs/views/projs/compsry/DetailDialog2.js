// Global variables
var aprv = 1; // 表示来自审批界面, 0表示来自detail按钮, 2来自新增按钮
var projid;
var undwrtid ;
/** @Bind view.onReady */
!function(self, arg) {
	if ("${request.getParameter('aprv')}"){
		aprv = "${request.getParameter('aprv')}";
	}	
	projid = "${request.getParameter('projid')}";
	undwrtid = "${request.getParameter('undwrtid')}";
	view.get("#tbsProj").set("visible",true);
	view.get("#tbsCustomer").set("visible",true);
	view.get("#projname").set("visible",false);
	view.get("#customername").set("visible",false);
	if (aprv == 2) {
		var rq = new Date();
		var customerid = "${request.getParameter('customerid')}";
		var customername = "${request.getParameter('customername')}";
		var compsryid = "${request.getParameter('compsryid')}";
		var cfmtotloc = "${request.getParameter('cfmtotloc')}";//累计承保金额
		var rlstotloc = "${request.getParameter('rlstotloc')}";//累计解保金额
		var dataSet = view.get("#dataSetTbsProjCompsryPay");
		var autoform = view.get("#AutoFormCompsryPay");
		var projname = "${request.getParameter('projname')}";
		view.get("#tabInAppr").set("visible",false);
		view.get("#tabTbsProj").set("visible",false);
		view.get("#tabTbsProjundwrt").set("visible",false);
		view.get("#tabProjAttach").set("visible",false);
		view.get("#tabHistoryTask").set("visible",false);
		view.get("#btnSave").set("visible",true);
		view.get("#btnSave").set("action","saveCompsryPay");
		view.get("#btnClose").set("visible",true);
		view.get("#btnSubmit").set("visible",false);
		view.get("#tbsProj").set("visible",false);
		view.get("#tbsCustomer").set("visible",false);
		view.get("#projname").set("visible",true);
		view.get("#customername").set("visible",true);
		dataSet.insert();
		autoform.set("entity.projname",projname);
		autoform.set("entity.customername",customername);
		dataSet.getData("#").set("tbsProj.id",projid);
		dataSet.getData("#").set("tbsCustomer.id",customerid);
		dataSet.getData("#").set("tbsProjundwrt.id",undwrtid);
		dataSet.getData("#").set("tbsProjcompsry.id",compsryid);
		var X = 0;
		if(cfmtotloc != "null"){
		X = parseFloat(cfmtotloc) - parseFloat(rlstotloc) ;
		}else if(cfmtotloc== "null" ){
			X= - parseInt(rlstotloc) ;
		}
		dataSet.getData("#").set("zbe",X);
		dataSet.getData("#").set("timestampInput", rq);	
		dataSet.getData("#").set("timestampUpdate", rq);
		dataSet.getData("#").set("date", rq);	
	} else if (aprv == 0) {
		var compsrypayid = "${request.getParameter('compsrypayid')}";
		var compsrypayvalid = "${request.getParameter('compsrypayvalid')}";
		if (compsrypayvalid != "0"){
			view.get("#dataSetTbsProjCompsryPay").set("readOnly",true);
			view.get("#btnSubmit").set("visible", false);
			view.get("#btnSave").set("visible",false);
		};
		view.get("#tabInAppr").set("visible",false);
		view.get("#btnSave").set("action","saveCompsryPay");
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
		view.get("#dataSetTbsProjCompsryPay").set("parameter", compsrypayid).flush();
	} else {
		var businessId = "${request.getParameter('businessId')}";  
		view.get("#dataSetTbsProjCompsryPay").set("parameter", businessId).flush();  // 这里为了立刻让dataset有值只能用flush，不能用flushasync
		var dataSet = view.get("#dataSetTbsProjCompsryPay").getData("#"); 
		var projid = dataSet.get("tbsProj.id"); 
		var compsrypayvalid = dataSet.get("valid");
		var taskId = "${request.getParameter('taskId')}";
		var params = { taskid : taskId,	};
		if (compsrypayvalid !=3 ) {	
			view.get("#listDdlOutcome").set("items",["通过","驳回"]);
			view.get("#dataSetTbsProjCompsryPay").set("readOnly",true);
		} else {
			view.get("#listDdlOutcome").set("items",["修改确认"]);
		}
		view.get("#btnPanel").set("visible",false);
		view.get("#tabControlMain").set("height","99%");
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();  
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
		view.get("#ajaxactionGetUfloNode").set("parameter",params).execute(
				function(nodename){
				if (nodename == "出纳付款"){
					view.get("#dataSetTbsProjCompsryPay").set("readOnly",true);
					view.get("#listDdlOutcome").set("items",["确认"]);
				}	
				if (nodename == "A角补录代偿证明"){
					view.get("#dataSetTbsProjCompsryPay").set("readOnly",true);
					view.get("#listDdlOutcome").set("items",["确认"]);
				}	
				});
	}
};

/*===============页面计算===================*/
/** @Bind #payfaloc.onPost */
/** @Bind #paynfaloc.onPost */
/** @Bind #payotloc.onPost */
/** @Bind #payfalocLx.onPost */
/** @Bind #paynfalocLx.onPost */
/** @Bind #payotlocLx.onPost */
!function(self, arg, AutoFormCompsryPay){
	var autoform = AutoFormCompsryPay.get("entity");
	var falocLx = autoform.get("falocLx");
	var nfalocLx = autoform.get("nfalocLx");
	var otlocLx = autoform.get("otlocLx");
	var faloc = autoform.get("faloc");
	var nfaloc = autoform.get("nfaloc");
	var otloc= autoform.get("otloc");
	autoform.set("bcdczlx", falocLx+nfalocLx+otlocLx);
	autoform.set("bcdczbj", faloc+nfaloc+otloc);
	autoform.set("bcdczje", falocLx+nfalocLx+otlocLx+faloc+nfaloc+otloc);
	 
};


/*=======数据保存后刷新父窗口，关闭自身========*/
/** @Bind #btnSave.onClick */
!function(self, arg, saveCompsryPay) {
	if(aprv==2||saveCompsryPay.get("hasUpdateData")){
		var rlsfaloc = "${request.getParameter('rlsfaloc')}"-0;
		var rlsnfaloc = "${request.getParameter('rlsnfaloc')}"-0;
		var rlsotloc = "${request.getParameter('rlsotloc')}"-0;
		if(view.get("#AutoFormCompsryPay").get("entity.faloc")>rlsfaloc 
				|| view.get("#AutoFormCompsryPay").get("entity.nfaloc")>rlsnfaloc 
				||view.get("#AutoFormCompsryPay").get("entity.otloc")>rlsotloc ){
			dorado.MessageBox.alert(
					"输入金额过大\n"+
					"当前融资在保额："+rlsfaloc+"\n"+
					"当前非融在保额："+rlsnfaloc+"\n"+
					"当前其他在额："+rlsotloc+"\n", {
				title : "趣博信息科技"
			});
			return false;
		}
		saveCompsryPay.execute();
	}
	var autoformCondition = window.parent.$id("autoformCondition").objects[0];
	var dataSet = window.parent.$id("dataSetTbsProj").objects[0];
	var condition = autoformCondition.get("entity");
	var dialogMainForm = window.parent.$id("dialogProjDetails").objects[0];
	dialogMainForm.hide();
	dataSet.set("parameter", condition).flushAsync();
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
!function(self, arg, dataSetTbsProj, dataSetTbsProjCompsryPay, ajaxactionStartProcess, saveCompsryPay) {
	var projid = dataSetTbsProj.getData("#").get("id");
	var compsrypayid = dataSetTbsProjCompsryPay.getData("#").get("id");
	var params ={
			projid : projid,
			compsrypayid : compsrypayid
	};
	if(saveCompsryPay.get("hasUpdateData")){
		saveCompsryPay.execute();
	}
	ajaxactionStartProcess.set("parameter", params).execute(function(result) {
		dorado.MessageBox.alert(result, {
			title : "趣博信息科技"
		});
	});
	window.parent.$id("dataSetTbsProj").objects[0].flushAsync();
	dataSetTbsProjCompsryPay.flushAsync();
	dataSetTbsProjCompsryPay.set("readOnly",true);
	self.set("visible",false);
	view.get("#btnSave").set("visible",false);
};

/*======================待办任务，进行审批==================*/
/** @Bind #btnApprSubmit.onClick */ 
!function(self,arg,autoformOpinion,saveCompsryPay,ajaxactionApprSubmit) {
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
		if (outcome == "修改确认" || outcome == "确认" ) {
			saveCompsryPay.execute();
		};
		ajaxactionApprSubmit.set("parameter", params).execute();
	};
};

/*=========待办任务界面关闭窗口============*/
/** @Bind #ajaxactionApprSubmit.onSuccess */
/** @Bind #btnApprClose.onClick */
!function(self, arg) {
	window.parent.closeProcessDialog('${request.getParameter("type")}');
};

/* =======================反担保子表=================== 
*//** @Bind #buttonQueryCgg.onClick *//*
!function(self,arg,dataSetTbsProjCgg,dataSetTbsProjundwrt,DialogTbsProjCgg){
	//获取autoformCondition绑定的实体对象
	var entity = dataSetTbsProjundwrt.getData("#").get("tbsProj.id");
	//将实体对象作为参数传入，并异步刷新
	dataSetTbsProjCgg.set("parameter",entity).flushAsync();
	DialogTbsProjCgg.show();
};*/





/**
 * 文件处理 开始
 */

/** @Bind #tabProjAttach.onClick */
!function(self,arg,iFrameAttach,dataSetTbsProj){
	debugger;
	var title = "CompsryPay";
	var projsn = dataSetTbsProj.getData("#").get("sn");
	var fid ="代偿请款";
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