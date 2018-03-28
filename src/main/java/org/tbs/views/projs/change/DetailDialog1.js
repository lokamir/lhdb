// Global variables
var aprv = 1; // 表示来自审批界面, 0 表示来自detail按钮 2来自新增按钮
var projid;
var projsn;
var spcbtn; // 给"评审会秘书录入决议"专用的判断条件
var loginusername = "${loginUsername}";
var nodeName = "${param.nodeName}";
var uid = "${dorado.getDataProvider('el#Uid').getResult()}";

function getCfmOpinion(id,by1){
	var cfm0Para = {
			BusinessId: id,
			processInstanceId: by1
	};
	view.get("#dataSetTbsProjcfm0").set("parameter", cfm0Para).flush();
	var dataCfm0 = view.get("#dataSetTbsProjcfm0").getData("#");
	var opinPara = {
			projId: id,
			cfm0Id: dataCfm0.get("id")
	};
	spcbtn = dataCfm0.get("type");
	view.get("#datasetTbsProjOpinion").set("parameter", opinPara).flush();
	if(["评审会秘书录入会议决议单","评审会秘书确认","主任委员审批","决策人审批","评委审批"].indexOf(nodeName)>=0){
			view.get("#datasetTbsProjOpinion1r2").set("parameter", opinPara).flush();
	};
}

function getCfm1or2(id,by1,spcbtn){
	var cfmPara = {
			BusinessId: id,
			processInstanceId: by1
	};
	if(spcbtn=="会议"){
		view.get("#dataSetTbsProjcfm1").set("parameter",cfmPara).flush();
		view.get("#groupboxCfm1").set("visible",true);
	}else if((spcbtn=="签批")){
		view.get("#dataSetTbsProjcfm2").set("parameter",cfmPara).flush();
		view.get("#groupboxCfm2").set("visible",true);
	}
}

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
		view.get("#tabCfm1r2").set("visible",false);
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
		view.get("#btnPanel").set("visible",true);
	} else if(aprv == 0){
		//刷新projchange数据
		var projchangeid = "${request.getParameter('projchangeid')}";
		var projchangevalid = "${request.getParameter('projchangevalid')}";
		var autoform = view.get("#AutoFormChangeMajcont");
		view.get("#btnPanel").set("visible",true);
		view.get("#tabInAppr").set("visible", false);
		view.get("#dataSetTbsProjchangeMajcont").set("parameter", projchangeid).flush();
		view.get("#btnSave").set("action","saveChangeMajcont");
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
		if(view.get("#dataSetTbsProjcfm0").getData("#")){
			view.get("#groupboxCfm0").set("visible", true);
		}
		if(view.get("#datasetTbsProjOpinion").getData("#")){
			view.get("#groupboxCfm0ProjOpin").set("visible", true);			
		}
		if(view.get("#datasetTbsProjOpinion1r2").getData("#")){
			view.get("#datagridCfm1r2ProjOpin").set("visible", true);			
		}
		if(view.get("#dataSetTbsProjcfm1").getData("#")){
			view.get("#tabChangeMajcontCfm").set("visible",true);			
			view.get("#groupboxCfm1").set("visible",true);			
		}
		if(view.get("#dataSetTbsProjcfm2").getData("#")){
			view.get("#tabChangeMajcontCfm").set("visible",true);			
			view.get("#groupboxCfm2").set("visible",true);				
		}
		//如果发起审批表单不可以修改
		if (projchangevalid != "0"){
			view.get("#dataSetTbsProjchangeMajcont").set("readOnly",true);
			view.get("#btnSubmit").set("visible", false);
			view.get("#btnSave").set("visible",false);
			view.get("#dataSetTbsProjcfm0").set("readOnly",true);
			view.get("#datasetTbsProjOpinion").set("readOnly",true);
			view.get("#datasetTbsProjOpinion1r2").set("readOnly",true);
			view.get("#dataSetTbsProjcfm1").set("readOnly",true);
			view.get("#dataSetTbsProjcfm2").set("readOnly",true);
		};
		//取巧 避免没做改动时保存失败
		var newfaloc = autoform.get("entity.newfaloc");
		autoform.set("entity.newfaloc", newfaloc);	
	}
	if(["B角确认","部门经理审批","风管经理审批","风管部门经理审批","分管风险领导"].indexOf(nodeName)>=0){
		view.get("#tabChangeMajcontCfm").set("visible",false);
		view.get("#listDdlOutcome").set("items",["通过","驳回"]);
		view.get("#dataSetTbsProjcfm0").set("readOnly",true);
		view.get("#datasetTbsProjOpinion").set("readOnly",true);
		view.get("#datasetTbsProjOpinion1r2").set("readOnly",true);
		view.get("#dataSetTbsProjcfm1").set("readOnly",true);
		view.get("#dataSetTbsProjcfm2").set("readOnly",true);
		view.get("#dataSetTbsProjchangeMajcont").set("readOnly",true);
		view.get("#btnPanel").set("visible",false);
		var businessId = "${request.getParameter('businessId')}";  
		view.get("#dataSetTbsProjchangeMajcont").set("parameter", businessId).flush();  // 这里为了立刻让dataset有值只能用flush，不能用flushasync
		var dataSet = view.get("#dataSetTbsProjchangeMajcont").getData("#"); 
		var projid = dataSet.get("tbsProj.id"); 
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();  
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
	}
	if(nodeName == "评审会秘书录入会议信息"){
		var businessId = "${request.getParameter('businessId')}";  
		view.get("#dataSetTbsProjchangeMajcont").set("parameter", businessId).flush();  // 这里为了立刻让dataset有值只能用flush，不能用flushasync
		var dataSet = view.get("#dataSetTbsProjchangeMajcont").getData("#"); 
		var projid = dataSet.get("tbsProj.id"); 
		getCfmOpinion(projid,dataSet.get("by1"));
		view.get("#btnPanel").set("visible",false);
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();  
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
		view.get("#tabCfm0").set("visible",true);
		view.get("#groupboxCfm0").set("visible",true);
		view.get("#groupboxCfm0ProjOpin").set("visible",true);
		view.get("#datapilotCfm0ProjOpin").set("visible",true);
		view.get("#autoformTbsProjCfm0").set("readOnly",false);
		view.get("#datagridCfm0ProjOpin").set("readOnly",false);
		view.get("#autoformTbsProjCfm0").get("entity").set("keyinId",uid);
		view.get("#listDdlOutcome").set("items",["会议","签批"]);
	}
	if(nodeName == "评审会秘书确认"){
		var businessId = "${request.getParameter('businessId')}";  
		view.get("#dataSetTbsProjchangeMajcont").set("parameter", businessId).flush();  // 这里为了立刻让dataset有值只能用flush，不能用flushasync
		var dataSet = view.get("#dataSetTbsProjchangeMajcont").getData("#");
		var projid = dataSet.get("tbsProj.id"); 
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();  
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
		getCfmOpinion(projid,dataSet.get("by1"));
		view.get("#tabCfm0").set("visible",true);
		view.get("#groupboxCfm0").set("visible",true);
		view.get("#groupboxCfm0ProjOpin").set("visible",true);
		view.get("#datapilotCfm0ProjOpin").set("visible",true);
		view.get("#groupboxCfm0ProjOpin").set("visible",false);
		view.get("#groupboxCfm1r2ProjOpin").set("visible",true);
		view.get("#datagridCfm1r2ProjOpin").set("readOnly",false);
		view.get("#autoformTbsProjCfm0").set("readOnly",true);
		view.get("#datagridCfm0ProjOpin").set("readOnly",true);
		view.get("#dataSetTbsProjchangeMajcont").set("readOnly",true);
		view.get("#listDdlOutcome").set("items",["通过","驳回"]);
	}
	if(nodeName == "评审会秘书录入会议决议单"){
		var businessId = "${request.getParameter('businessId')}";  
		view.get("#dataSetTbsProjchangeMajcont").set("parameter", businessId).flush();  // 这里为了立刻让dataset有值只能用flush，不能用flushasync
		var dataSet = view.get("#dataSetTbsProjchangeMajcont").getData("#");
		var projid = dataSet.get("tbsProj.id"); 
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();  
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
		getCfmOpinion(projid,dataSet.get("by1"));
		getCfm1or2(projid,dataSet.get("by1"),spcbtn);
		view.get("#tabCfm0").set("visible",true);
		view.get("#groupboxCfm0").set("visible",true);
		view.get("#groupboxCfm0ProjOpin").set("visible",true);
		view.get("#datapilotCfm0ProjOpin").set("visible",true);
		view.get("#groupboxCfm0ProjOpin").set("visible",false);
		view.get("#groupboxCfm1r2ProjOpin").set("visible",true);
		view.get("#tabChangeMajcontCfm").set("visible",true);
		view.get("#dataSetTbsProjchangeMajcont").set("readOnly",false);
		view.get("#autoformTbsProjCfm0").set("readOnly",true);
		view.get("#datagridCfm0ProjOpin").set("readOnly",true);
		view.get("#dataSetTbsProjchangeMajcont").set("readOnly",true);
		view.get("#listDdlOutcome").set("items",["确认修改"]);
		if(view.get("#autoformCfm1").get("entity")){
			view.get("#autoformCfm1").get("entity").set("keyinId","${dorado.getDataProvider('el#Uid').getResult()}");
			view.get("#autoformCfm1").set("readOnly",false);
			view.get("#autoformCfm1").set({
		    	"readOnly":false,
		    	"elements.by2.label":"会议审议单编号           "+view.get("#dataSetTbsProjcfm1").getData("#.sn").substring(0,14),
		    	"elements.by2.labelWidth":270,
		    	"elements.by2.labelSpacing":0,
		    	"elements.by2.labelAlign":"right"
		    	});
		}
		else if(view.get("#autoformCfm2").get("entity")){
			view.get("#autoformCfm2").get("entity").set("keyinId","${dorado.getDataProvider('el#Uid').getResult()}");
			view.get("#autoformCfm2").set("readOnly",false);
			view.get("#autoformCfm2").set({
		    	"readOnly":false,
		    	"elements.by2.label":"签批审议单编号           "+view.get("#dataSetTbsProjcfm2").getData("#.sn").substring(0,14),
		    	"elements.by2.labelWidth":270,
		    	"elements.by2.labelSpacing":0,
		    	"elements.by2.labelAlign":"right"
		    	});
		}
	}
	if(nodeName == "主任委员审批"||nodeName =="决策人审批"){
		var businessId = "${request.getParameter('businessId')}";  
		view.get("#dataSetTbsProjchangeMajcont").set("parameter", businessId).flush();  // 这里为了立刻让dataset有值只能用flush，不能用flushasync
		var dataSet = view.get("#dataSetTbsProjchangeMajcont").getData("#");
		var projid = dataSet.get("tbsProj.id"); 
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();  
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
		getCfmOpinion(projid,dataSet.get("by1"));
		getCfm1or2(projid,dataSet.get("by1"),spcbtn);
		view.get("#tabCfm0").set("visible",true);
		view.get("#groupboxCfm0").set("visible",true);
		view.get("#groupboxCfm0ProjOpin").set("visible",true);
		view.get("#datapilotCfm0ProjOpin").set("visible",true);
		view.get("#groupboxCfm0ProjOpin").set("visible",false);
		view.get("#groupboxCfm1r2ProjOpin").set("visible",true);
		view.get("#datagridCfm1r2ProjOpin").set("readOnly",true);
		view.get("#tabChangeMajcontCfm").set("visible",true);
		view.get("#dataSetTbsProjchangeMajcont").set("readOnly",true);
		view.get("#autoformTbsProjCfm0").set("readOnly",true);
		view.get("#datagridCfm0ProjOpin").set("readOnly",true);
		view.get("#dataSetTbsProjchangeMajcont").set("readOnly",true);
		view.get("#listDdlOutcome").set("items",["通过","驳回"]);
	}
	
	if(nodeName == "评委审批"){
		var businessId = "${request.getParameter('businessId')}";  
		view.get("#dataSetTbsProjchangeMajcont").set("parameter", businessId).flush();  // 这里为了立刻让dataset有值只能用flush，不能用flushasync
		var dataSet = view.get("#dataSetTbsProjchangeMajcont").getData("#");
		var projid = dataSet.get("tbsProj.id"); 
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();  
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
		getCfm1or2(projid,dataSet.get("by1"),"签批");
		getCfmOpinion(projid,dataSet.get("by1"));
		view.get("#groupboxCfm1r2ProjOpin").set("visible", true);
		view.get("#groupboxAppr").set("visible", true);
		view.get("#groupboxCfm0").set("visible", false);
		view.get("#groupboxCfm2").set("visible", true);
		view.get("#listDdlOutcome").set("items",["同意","反对","回避","缺席","弃权"]);
		/*targetDatas = view.get("#datasetTbsProjOpinion1r2").getData();
		// set promoter DH's outcome to "回避"
		targetDatas.each(function(targetData){
			debugger;
			if (targetData.get("title") == "发起人部门经理"&&targetData.get("bdf2User.id")==uid) {
				view.get("#autoformCfm0Opinion").get("entity").set("outcome","回避");
				view.get("#autoformCfm0Opinion").getElement("outcome").set("readOnly", true);
			}
		});*/
	};
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
			projchangeid : projchangeid
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
				uid:uid
			};
		if (outcome == "确认修改" &&nodeName =="驳回修改" ) {
			saveChangeMajcont.execute({
				callback : function(result) {  //用回调方法是为了让字段的必填校验在界面上做出错提示
					if (result == true){
						ajaxactionApprSubmit.set("parameter", params).execute();
					}
				}
			});
		}else if(nodeName == "评审会秘书录入会议信息"){
			view.get("#dataSetTbsProjcfm0").getData("#").set("type",outcome);
			view.get("#updateactionCfm0").execute({
				callback : function(result) {  //用回调方法是为了让字段的必填校验在界面上做出错提示
					if (result == true){
						ajaxactionApprSubmit.set("parameter", params).execute();
					}
				}
			});
		}else if(nodeName == "评审会秘书确认"){
			if(spcbtn=="会议"){
				view.get("#updateactionProjOpin1r2").execute({
					callback : function(result) {  //用回调方法是为了让字段的必填校验在界面上做出错提示
						if (result == true){
							ajaxactionApprSubmit.set("parameter", params).execute();
						}
					}
				});
			}
			if(spcbtn=="签批"){
				view.get("#updateactionProjOpin1r2").execute({
					callback : function(result) {  //用回调方法是为了让字段的必填校验在界面上做出错提示
						if (result == true){
							ajaxactionApprSubmit.set("parameter", params).execute();
						}
					}
				});
			}
		}else if(nodeName == "评审会秘书录入会议决议单"){
			var type = view.get("#dataSetTbsProjcfm0").getData("#.type");
			if(type=="会议"){
				view.get("#updateactionCfm1").execute({
					callback : function(result) {  //用回调方法是为了让字段的必填校验在界面上做出错提示
						if (result == true){
							ajaxactionApprSubmit.set("parameter", params).execute();
						}
					}
				});
			}
			if(type=="签批"){
				view.get("#updateactionCfm2").execute({
					callback : function(result) {  //用回调方法是为了让字段的必填校验在界面上做出错提示
						if (result == true){
							ajaxactionApprSubmit.set("parameter", params).execute();
						}
					}
				});
			};
		}else if(nodeName == "评委审批"){
			debugger;
			var dataProjOpin1r2s = view.get("#datasetTbsProjOpinion1r2").get("data");
			var loginUsername="${loginUser.getUsername()}";
			dataProjOpin1r2s.each(function(dataProjOpin1r2){
				if (dataProjOpin1r2.get("bdf2User.username") == loginUsername ) {
					dataProjOpin1r2.set("outcome", outcome);
				}
			});
			view.get("#updateactionProjOpin1r2").execute({
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

/** @Bind #updateactionProjOpin1r2.beforeExecute */
!function(self){
	
};

	
/** @Bind #updateactionCfm1.beforeExecute */
!function(self,autoformTbsProj_role, autoformCfm1r2Opinion,dataSetTbsProjcfm0,
		 datasetTbsProj, updateactionCfm1){
	var type = dataSetTbsProjcfm0.getData("#.type");
	if(type == "会议"){
		if(nodeName== "评审会秘书录入会议决议单"){
		var by2 =view.get("#autoformCfm1").get("entity.by2");
		if (view.get("#dataSetTbsProjcfm1").getData("#").validate("by2")!="ok"){
			view.get("#autoformCfm1").set("entity.by2",by2);
			dorado.MessageBox.alert("决议单编号必须是三位有效数字", {
				title : "趣博信息科技"
			});
			return false;
		}
		view.get("#dataSetTbsProjcfm1").getData("#").dataType.set("validatorsDisabled", true);// 禁用当前数据对象所有的数据校验
		view.get("#autoformCfm1").set("entity.by2",view.get("#dataSetTbsProjcfm1").getData("#.sn").substring(0,14)+by2);
		
	}
	view.get("#dataSetTbsProjcfm1").getData("#").isDirty();
	}
};

/** @Bind #updateactionCfm2.beforeExecute */
!function(self,autoformTbsProj_role, autoformCfm1r2Opinion,dataSetTbsProjcfm0,
		 datasetTbsProj, updateactionCfm2){
	var type = dataSetTbsProjcfm0.getData("#.type");
	if(type == "签批"){
		if(nodeName== "评审会秘书录入会议决议单"){
		var by2 =view.get("#autoformCfm2").get("entity.by2");
		if (view.get("#dataSetTbsProjcfm2").getData("#").validate("by2")!="ok"){
			view.get("#autoformCfm2").set("entity.by2",by2);
			dorado.MessageBox.alert("决议单编号必须是三位有效数字", {
				title : "趣博信息科技"
			});
			return false;
		}
		view.get("#dataSetTbsProjcfm2").getData("#").dataType.set("validatorsDisabled", true);// 禁用当前数据对象所有的数据校验
		view.get("#autoformCfm2").set("entity.by2",view.get("#dataSetTbsProjcfm2").getData("#.sn").substring(0,14)+by2);
		
	}
	view.get("#dataSetTbsProjcfm2").getData("#").isDirty();
	}
};

//2017-06-23 新增 决议审批autoformCfm自动统计票数
/** @Bind #tabChangeMajcontCfm.onClick */
!function(self){
	if(nodeName== '评审会秘书录入会议决议单'){
	var datasetTbsProjOpinion1r2 = view.get("#datasetTbsProjOpinion1r2").getData();
	var type = view.get("#datasetTbsProjOpinion1r2").getData("#").get("cfmtype");
	var agree = 0;
	var opoose = 0;
	var waiver = 0;
	var debarb = 0;
	var tobe = 0;
	var actbe = 0;
	var nobe = 0;
	datasetTbsProjOpinion1r2.each(function(entity){
		if(entity.get("outcome") == "同意"){
			agree++;
			tobe++;
		}else if(entity.get("outcome") == "反对"){
			opoose++;
			tobe++;
		}else if(entity.get("outcome") == "弃权"){
			waiver++;
			tobe++;
		}else if(entity.get("outcome") == "回避"){
			debarb++;
			tobe++;
		}else if(entity.get("outcome") == "缺席"){
			nobe++;
			tobe++;
		}else{
			waiver++;
			tobe++;
		}
	});
	actbe = tobe - nobe;
	if(type == 1){
		var autoformCfm1 = view.get("#autoformCfm1");
		autoformCfm1.get("entity").set("tobe",tobe);
		autoformCfm1.get("entity").set("actbe",actbe);
		autoformCfm1.get("entity").set("nobe",nobe);
		autoformCfm1.get("entity").set("debarb",debarb);
		autoformCfm1.get("entity").set("waiver",waiver);
		autoformCfm1.get("entity").set("opoose",opoose);
		autoformCfm1.get("entity").set("agree",agree);
	}
	}
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

/* =======================决议审批单历史记录=================== */
/** @Bind #tabCfm1r2.onClick */
!function(self,arg,iFrameCfm1and2,dataSetTbsProj){
	var projid = dataSetTbsProj.getData("#.id");
	var cfm1and2path="org.tbs.views.funs.TbsProjCfm1and2.d?projid=" + projid ;
	iFrameCfm1and2.set("path", cfm1and2path);
};

/**
 * 文件处理 开始
 */

/** @Bind #tabProjAttach.onClick */
!function(self,arg,iFrameAttach,dataSetTbsProj){
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