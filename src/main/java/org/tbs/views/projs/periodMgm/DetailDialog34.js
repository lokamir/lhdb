// Global variables
var aprv = 1; // 表示来自审批界面, 0 表示来自菜单界面,2新增界面
var projid;//tbsproj  id
var undwrtid ;//tbsprojundwrt id 
var ptyexpid ; //表示费用收退表id
var io ; //1表示来自费用收0表示费用退
var menu ; // 表示menu（来自哪一个菜单）
var button;// 明细按钮还是新增按钮
var valid;//单据状态（未发送审批，审批通过，审批中，驳回）


/** @Bind view.onReady */
!function(self,dataSetTbsProj,dataSetTbsProjundwrt,dataSetTbsPtyexp,ajaxActionGetMenu) {
	projid = "${request.getParameter('projid')}";
	ptyexpid = "${request.getParameter('ptyexpid')}";
	undwrtid = "${request.getParameter('undwrtid')}";
	if ("${request.getParameter('aprv')}"){
		aprv = "${request.getParameter('aprv')}";
	}
	menu = "${request.getParameter('menu')}";
	button = "${request.getParameter('button')}";
	valid = "${request.getParameter('valid')}";
	io = "${request.getParameter('io')}";
	if (valid == 0) {
		view.get("#btnSubmit").set("visible", true);
		view.get("#btnSave").set("visible", true);
		dataSetTbsPtyexp.set("readOnly",false);
	} else  {
		view.get("#btnSubmit").set("visible", false);
		view.get("#btnSave").set("visible", false);
		dataSetTbsPtyexp.set("readOnly",true);
	}
	if(aprv == 2){
		view.get("#tabInAppr").set("visible",false);
		view.get("#tabTbsProj").set("visible",false);
		view.get("#tabTbsProjundwrt").set("visible",false);
		view.get("#tabHistoryTask").set("visible",false);
		dataSetTbsProj.set("parameter", projid).flushAsync();
		dataSetTbsProjundwrt.set("parameter", undwrtid).flushAsync();
	}else if (aprv == 0){
		view.get("#tabInAppr").set("visible",false);
		view.get("#tabTbsProj").set("visible",true);
		view.get("#tabTbsProjundwrt").set("visible",true);
		dataSetTbsProj.set("parameter", projid).flushAsync();
		dataSetTbsProjundwrt.set("parameter", undwrtid).flushAsync();
	}else {//来自于审批页面是获取参数及权限控制
		dataSetTbsPtyexp.flushAsync();
		var taskId = "${request.getParameter('taskId')}";
		var param = {
			taskId : taskId
		};
		ajaxActionGetMenu.set("parameter", param);
		ajaxActionGetMenu.execute();
		view.get("#tabInAppr").set("visible",true);
		view.get("#tabTbsProj").set("visible",true);
		view.get("#tabTbsProjundwrt").set("visible",true);
		dataSetTbsPtyexp.set("readOnly",true);
		view.get("#btnSave").set("visible",false);
		view.get("#btnClose").set("visible",false);
		view.get("#btnSubmit").set("visible",false);
	};
	if(menu=="PtyexpI"){
		view.get("#tabTbsPtyexp").set("caption","费用收取");
		view.get("#fee").set("label","收款金额(元)");
	}else if(menu=="PtyexpO"){
		view.get("#tabTbsPtyexp").set("caption","费用退回");
		view.get("#fee").set("label","付款金额(元)");
	};
};

//试用action去参数 否则无法区分来自哪个审批页面
/** @Bind #ajaxActionGetMenu.onSuccess */ 
!function(self,dataSetTbsProjundwrt,dataSetTbsProj,dataSetTbsPtyexp,listDdlOutcome){
	projid = view.get("#dataSetTbsPtyexp").getData("#").get("tbsProj").get("id");
	undwrtid = view.get("#dataSetTbsPtyexp").getData("#").get("tbsProjundwrt").get("id");
	dataSetTbsProj.set("parameter", projid).flushAsync();
	dataSetTbsProjundwrt.set("parameter", undwrtid+"").flushAsync();
	valiables = self.get("returnValue");// 获取valiables
	var tabInAppr = view.get("#tabInAppr");
	// 权限控制
	if (valiables.aprv == "approve") {
		listDdlOutcome.set("items", [ "通过", "驳回" ]);
		tabInAppr.set("visible", true);
		dataSetTbsPtyexp.set("readOnly",true);
	}
	if (valiables.aprv == "reject") {
		listDdlOutcome.set("items", [ "确认修改" ]);
		tabInAppr.set("visible", true);
		dataSetTbsPtyexp.set("readOnly",false);
	}
	if (valiables.node == "出纳付款") {
		listDdlOutcome.set("items", [ "确认" ]);
		tabInAppr.set("visible", true);
		dataSetTbsPtyexp.set("readOnly",true);
	}
	if (valiables.menu=="PtyexpI") { 
		view.get("#tabTbsPtyexp").set("caption","费用收取");
		view.get("#fee").set("label","收款金额(元)");
	}
	if (valiables.menu=="PtyexpO") { 
		view.get("#tabTbsPtyexp").set("caption","费用退回");
		view.get("#fee").set("label","付款金额(元)");
	}
};

//受制于线程阻塞问题用onLoadData事件（dataset无所谓）
/** @Bind #dataSetTbsProjundwrt.onLoadData */
!function(self,dataSetTbsProj,dataSetTbsProjundwrt,dataSetTbsPtyexp) {
if(ptyexpid&&button=="Details"){
		dataSetTbsPtyexp.set("parameter", ptyexpid).flushAsync();
	} else if (button == "Add") {
		view.get("#btnSubmit").set("visible", false);
		view.get("#btnSave").set("visible", true);
		var tbsProj = dataSetTbsProj.getData("#");
		var tbsProjundwrt = dataSetTbsProjundwrt.getData("#");
		dataSetTbsPtyexp.set("readOnly",false);
		//添加comfirm
		dorado.MessageBox.confirm("您选择的项目为【" + tbsProj.get("projName") + "】\n"
				+ "承保审批单号为【" + tbsProjundwrt.get("sn") + "】\n" + "您真的要新增此项目吗？", {
			title : "趣博信息科技",
			detailCallback : function(btnID, text) {
				if (btnID == "yes") {
					if (io == 1) {
						dataSetTbsPtyexp.insert({
							"tbsProj" : tbsProj,
							"tbsProjundwrt" : tbsProjundwrt,
							"io" : true
						});
					} else {
						dataSetTbsPtyexp.insert({
							"tbsProj" : tbsProj,
							"tbsProjundwrt" : tbsProjundwrt,
							"io" : false
						});
					}
				}
				if (btnID == "no") {
					var dialogTbsPtyexpNew = window.parent
							.$id("dialogTbsPtyexpNew").objects[0];
					dialogTbsPtyexpNew.hide();
				}
			}
		});
	}
};

/*=======================Dialog保存按钮===================*/
/** @Bind #btnSave.onClick */
!function(updateActionSaveTbsPtyexp){
	if(updateActionSaveTbsPtyexp.get("hasUpdateData")){
		updateActionSaveTbsPtyexp.execute(function(result){
			dorado.MessageBox.alert(result,{title:"趣博信息科技"});
			var dialogTbsPtyexpNew = window.parent.$id("dialogTbsPtyexpNew").objects[0];
			dialogTbsPtyexpNew.hide();
		});
		}else{
			var dialogTbsPtyexpNew = window.parent.$id("dialogTbsPtyexpNew").objects[0];
			dialogTbsPtyexpNew.hide();
		}
};

/*=======================Dialog关闭按钮===================*/
/** @Bind #btnClose.onClick */
!function(updateActionSaveTbsPtyexp){
		var dialogTbsPtyexpNew = window.parent.$id("dialogTbsPtyexpNew").objects[0];
		dialogTbsPtyexpNew.hide();
};

/*=======================发起流程===================*/
/** @Bind #btnSubmit.onClick */ 
!function(self,arg,dataSetTbsProj,autoFormTbsPtyexp,ajaxActionStartProcess,updateActionSaveTbsPtyexp){
	var entity = autoFormTbsPtyexp.get("entity");
	var id_value = entity.get("id");//费用单据ID
	var sn_value = entity.get("sn");//费用单据SN
	var proj_value = entity.get("tbsProj");//tbsproj对象
	var menu_value = menu;//费用单据名称
	var undwrt_value = entity.get("tbsProjundwrt");//tbsProjundwrt对象
	if(updateActionSaveTbsPtyexp.get("hasUpdateData")){
		updateActionSaveTbsPtyexp.execute();
	}
	var maParamers = {docid:id_value,sn:sn_value,menu:menu_value,proj:proj_value,undwrt:undwrt_value}; 
	ajaxActionStartProcess.set("parameter",maParamers).execute(function(result){
		dorado.MessageBox.alert(result,{title:"趣博信息科技"});
	});

	view.get("#dataSetTbsPtyexp").set("readOnly",true);
	self.set("visible",false);
	view.get("#btnSave").set("visible",false);
}; 

/*======================发送审批===================*/
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
		var businessId = "${request.getParameter('businessId')}";
		var param = {
				taskId : taskId,
				businessId : businessId,
				outcome : outcome,
				comment : comment,
			};
		if(outcome == "确认修改") {
			view.get("#updateActionSaveTbsPtyexp").execute();
		};
		ajaxactionApprSubmit.set("parameter", param);
		ajaxactionApprSubmit.execute(function(result) {
			dorado.MessageBox.alert(result, {
				title : "趣博信息科技"
			});
		});
	};
};


/* =================审批历史纪录伪懒加载=============== */
/** @Bind #tabControlMain.onTabChange */
!function(self,dataSetTbsPtyexp) {
	var tabControlMain = view.get("#tabControlMain");
	if (tabControlMain.get("currentTab.caption") == "审批记录") {
		var processInstanceId = dataSetTbsPtyexp.getData("#").get("by1");
		view.get("#dataSetHistoryTask").set("parameter", processInstanceId).flushAsync();
	}
};


/** @Bind #ajaxActionStartProcess.onSuccess */
/** @Bind #ajaxactionApprSubmit.onSuccess */
/** @Bind #btnApprClose.onClick */
!function(self, arg) {
	window.parent.closeProcessDialog('${request.getParameter("type")}');
};


/**
 * 文件处理 开始
 */

/** @Bind #tabProjAttach.onClick */
!function(self,arg,iFrameAttach,dataSetTbsProj){
	var title = "periodMgm";
	var projsn = dataSetTbsProj.getData("#").get("sn");
	var fid =null;
	if (menu == "PtyexpI") {
		fid = "费用收取";
	} else if (menu == "PtyexpO") {
		fid = "费用退回";
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