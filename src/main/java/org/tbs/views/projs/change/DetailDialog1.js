// Global variables
var aprv = 1; // 表示来自审批界面, 0 表示来自detail按钮 2来自新增按钮
var cfmUnique = 0; //防止决议审批onclick事件重复出发，0表示未触发过
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
		var dataSetTbsProj = view.get("#dataSetTbsProj");
		dataSetTbsProj.set("parameter", projid).flush();
		var projby5 = dataSetTbsProj.getData("#.by5");
		view.get("#projDetail").set("entity.by3",projby5);
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
	if(["退回A角修正"].indexOf(nodeName)>=0){
		view.get("#tabChangeMajcontCfm").set("visible",false);
		view.get("#listDdlOutcome").set("items",["确认修改"]);
		view.get("#dataSetTbsProjcfm0").set("readOnly",true);
		view.get("#datasetTbsProjOpinion").set("readOnly",true);
		view.get("#datasetTbsProjOpinion1r2").set("readOnly",true);
		view.get("#dataSetTbsProjcfm1").set("readOnly",true);
		view.get("#dataSetTbsProjcfm2").set("readOnly",true);
		view.get("#dataSetTbsProjchangeMajcont").set("readOnly",false);
		view.get("#btnPanel").set("visible",false);
		var businessId = "${request.getParameter('businessId')}";  
		view.get("#dataSetTbsProjchangeMajcont").set("parameter", businessId).flush();  // 这里为了立刻让dataset有值只能用flush，不能用flushasync
		var dataSet = view.get("#dataSetTbsProjchangeMajcont").getData("#"); 
		var projid = dataSet.get("tbsProj.id"); 
		view.get("#dataSetTbsProj").set("parameter", projid).flushAsync();  
		view.get("#dataSetTbsProjundwrt").set("parameter", projid).flushAsync();
	}
	if(["B角确认","部门经理审批","风管经理审批","风管部门经理审批","分管风险领导","法律合规部门经理审批"].indexOf(nodeName)>=0){
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
		view.get("#decision").set("readOnly", false);
		view.get("#decision").set("visible", true);
		view.get("#autoFormTbsProjcfm1").set("readOnly",false);
		view.get("#autoFormTbsProjcfm2").set("readOnly",false);
		view.get("#dataSetTbsProjcfm1").set("readOnly",false);
		view.get("#dataSetTbsProjcfm2").set("readOnly",false);
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
		//秘书可以修改上会通知单和会议信息
		//view.get("#autoformTbsProjCfm0").set("readOnly",false);
		//view.get("#groupboxCfm1r2ProjOpin").set("visible",true);
		//view.get("#datagridCfm1r2ProjOpin").set("readOnly",false);
		//view.get("#dataSetTbsProjcfm0").set("readOnly",false);
		//view.get("#datasetTbsProjOpinion1r2").set("readOnly",false);
		
		if(view.get("#autoformCfm1").get("entity")){
			view.get("#autoformCfm1").get("entity").set("keyinId","${dorado.getDataProvider('el#Uid').getResult()}");
			view.get("#autoformCfm1").set("readOnly",false);
			view.get("#DialogTbsProjCfm1").set("readOnly",false);
			view.get("#autoformCfm1").set({
		    	"readOnly":false,
		    	//"elements.by2.label":"会议审议单编号           "+view.get("#dataSetTbsProjcfm1").getData("#.sn").substring(0,14),
		    	"elements.by2.label":"会议审议单编号           ",
		    	"elements.by2.labelWidth":270,
		    	"elements.by2.labelSpacing":0,
		    	"elements.by2.labelAlign":"right"
		    	});
			view.get("#decision").set("dataSet","dataSetTbsProjcfm1");
		}
		else if(view.get("#autoformCfm2").get("entity")){
			view.get("#autoformCfm2").get("entity").set("keyinId","${dorado.getDataProvider('el#Uid').getResult()}");
			view.get("#autoformCfm2").set("readOnly",false);
			view.get("#DialogTbsProjCfm2").set("readOnly",false);
			view.get("#autoformCfm2").set({
		    	"readOnly":false,
		    	//"elements.by2.label":"签批审议单编号           "+view.get("#dataSetTbsProjcfm2").getData("#.sn").substring(0,14),
		    	"elements.by2.label":"签批审议单编号           ",
		    	"elements.by2.labelWidth":270,
		    	"elements.by2.labelSpacing":0,
		    	"elements.by2.labelAlign":"right"
		    	});
			view.get("#decision").set("dataSet","dataSetTbsProjcfm2");
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
		view.get("#dataSetTbsProjcfm1").set("readOnly",true);
		view.get("#dataSetTbsProjcfm2").set("readOnly",true);
		view.get("#tabChangeMajcontCfm").set("visible",true);
		view.get("#dataSetTbsProjchangeMajcont").set("readOnly",true);
		view.get("#autoformTbsProjCfm0").set("readOnly",true);
		view.get("#datagridCfm0ProjOpin").set("readOnly",true);
		view.get("#dataSetTbsProjchangeMajcont").set("readOnly",true);
		view.get("#listDdlOutcome").set("items",["通过","驳回"]);
		view.get("#decision").set("visible", true);
		var autoformCfm1 = view.get("#autoformCfm1");
		var autoformCfm2 = view.get("#autoformCfm2");
		autoformCfm1.set("readOnly", true);
		autoformCfm2.set("readOnly", true);
		if(autoformCfm1.get("entity")){
	    	view.get("#decision").set("dataSet","dataSetTbsProjcfm1");
	    }else if(autoformCfm2.get("entity")){
	    	view.get("#decision").set("dataSet","dataSetTbsProjcfm2");
	    }
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
		}else if(nodeName == "退回A角修正"){
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
		}else if(nodeName == "主任委员审批"){
			if(spcbtn=="会议"){
				var newfaloc = view.get("#dataSetTbsProjcfm1").getData("#").get("vfaloc");
				var newnfaloc = view.get("#dataSetTbsProjcfm1").getData("#").get("vnfaloc");
				var newotloc = view.get("#dataSetTbsProjcfm1").getData("#").get("votloc");
				var newtotloc = view.get("#dataSetTbsProjcfm1").getData("#").get("vtotloc");
				view.get("#dataSetTbsProjchangeMajcont").getData("#").set({
					"newfaloc":newfaloc,
					"newnfaloc":newnfaloc,
					"newotloc":newotloc,
					"newtotloc":newtotloc
					});
			}
			if(spcbtn=="签批"){
				var newfaloc = view.get("#dataSetTbsProjcfm2").getData("#").get("vfaloc");
				var newnfaloc = view.get("#dataSetTbsProjcfm2").getData("#").get("vnfaloc");
				var newotloc = view.get("#dataSetTbsProjcfm2").getData("#").get("votloc");
				var newtotloc = view.get("#dataSetTbsProjcfm2").getData("#").get("vtotloc");
				view.get("#dataSetTbsProjchangeMajcont").getData("#").set({
					"newfaloc":newfaloc,
					"newnfaloc":newnfaloc,
					"newotloc":newotloc,
					"newtotloc":newtotloc
					});
			}
			saveChangeMajcont.execute({
				callback : function(result) {//用回调方法
					ajaxactionApprSubmit.set("parameter", params).execute();
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
		var vtotloc =  view.get("#autoformCfm1").get("entity").get("vtotloc");
		if (view.get("#dataSetTbsProjcfm1").getData("#").validate("by2")!="ok"){
			view.get("#autoformCfm1").set("entity.by2",by2);
			dorado.MessageBox.alert("决议单编号必填", {
				title : "趣博信息科技"
			});
			return false;
		}else if (vtotloc == 0 ) {
			dorado.MessageBox.alert("授信金额为必填项 ",{title:"趣博信息科技"});
			return false;
		}
		view.get("#dataSetTbsProjcfm1").getData("#").dataType.set("validatorsDisabled", true);// 禁用当前数据对象所有的数据校验
		view.get("#autoformCfm1").set("entity.by2",view.get("#dataSetTbsProjcfm1").getData("#.sn").substring(0,6)+by2);
		
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
		var vtotloc =  view.get("#autoformCfm1").get("entity").get("vtotloc");
		if (view.get("#dataSetTbsProjcfm2").getData("#").validate("by2")!="ok"){
			view.get("#autoformCfm2").set("entity.by2",by2);
			dorado.MessageBox.alert("决议单编号必填", {
				title : "趣博信息科技"
			});
			return false;
		}else if (vtotloc == 0 ) {
			dorado.MessageBox.alert("授信金额为必填项 ",{title:"趣博信息科技"});
			return false;
		}
		view.get("#dataSetTbsProjcfm2").getData("#").dataType.set("validatorsDisabled", true);// 禁用当前数据对象所有的数据校验
		view.get("#autoformCfm2").set("entity.by2",view.get("#dataSetTbsProjcfm2").getData("#.sn").substring(0,6)+by2);
		
	}
	view.get("#dataSetTbsProjcfm2").getData("#").isDirty();
	}
};

//2017-06-23 新增 决议审批autoformCfm自动统计票数
/** @Bind #tabChangeMajcontCfm.onClick */
!function(self){
	if(cfmUnique == 1){
		return;
	}
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
	
	
	var tbsProjcfm1BizvtSet = view.get("#dataSetTbsProjcfm1").getData("#.tbsProjcfm1BizvtSet");
	var tbsProjcfm2BizvtSet = view.get("#dataSetTbsProjcfm2").getData("#.tbsProjcfm2BizvtSet");
	if(tbsProjcfm1BizvtSet){
		var crs = view.get("#autoformCfm1").get("entity"); 
		crs.set("vfaloc",0); 
		crs.set("vnfaloc",0); 
		crs.set("votloc",0);
		crs.set("vtotloc",0);
		tbsProjcfm1BizvtSet.each(function(entity){
			entity.remove();
		});
	}
	else if(tbsProjcfm2BizvtSet){
		var crs = view.get("#autoformCfm2").get("entity"); 
		crs.set("vfaloc",0); 
		crs.set("vnfaloc",0); 
		crs.set("votloc",0);
		crs.set("vtotloc",0);
		tbsProjcfm2BizvtSet.each(function(entity){
			entity.set("vloc",0);
		});
	}
	}
	cfmUnique = 1; //防止onclick事件重复出发，1表示已触发过
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

//=========双击显示历史审批单明细==========
/** @Bind #dataGridHis.onDataRowDoubleClick */
!function(self) {
	var path = "org.tbs.views.funs.MyTbsFunApprc.d?id=";
	var taskHisId = self.getCurrentItem().get("id");
	view.get("#DialogTbsFunApprc").show();
	view.get("#iFrameTbsFunApprc").set("path", path+taskHisId);
};

//=========打印三要素变更业务审批表==========
/** @Bind #btnPrint.onClick */
!function(self){
	var id = view.get("#dataSetTbsProj").getData("#").get("id");
	var majcontid = view.get("#dataSetTbsProjchangeMajcont").getData("#").get("id");
	var psid = view.get("#dataSetTbsProj").getData("#").get("tbsBasPs.id");
	var str ="";
	var a = "通过";
	if(view.get("#datasetTbsProjOpinion1r2").getData("#")){
		if(view.get("#datasetTbsProjOpinion1r2").getData("#").get("cfmtype") ==1){
			str = "会议";
		}else if (view.get("#datasetTbsProjOpinion1r2").getData("#").get("cfmtype") ==2){
			str = "签批";
		}
	}else {
		str = "会议";
	}
	if(psid ==36){
		a = "驳回";
	}
	var type = str +a;
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
    window.open(pref+"/ureport/preview?_t=1,5&_n=三要素变更业务审批表&_u=file:三要素变更业务审批表.ureport.xml&id="+id+"&majcontid="+majcontid+"&type="+type);
};

/** @Bind #riskavoidTextarea.onClick */
!function(self){
	view.get("#autoFormTbsProjchangeMajcont").set("visible",true);
	view.get("#autoFormTbsProjcfm1").set("visible",false);
	view.get("#autoFormTbsProjcfm2").set("visible",false);
	view.get("#riskavoidElement").set("visible",true);
	view.get("#memoElement").set("visible",false);
	view.get("#dialogUeditor").show();
};
/** @Bind #memoTextarea.onClick */
!function(self){
	view.get("#autoFormTbsProjchangeMajcont").set("visible",true);
	view.get("#autoFormTbsProjcfm1").set("visible",false);
	view.get("#autoFormTbsProjcfm2").set("visible",false);
	view.get("#riskavoidElement").set("visible",false);
	view.get("#memoElement").set("visible",true);
	view.get("#dialogUeditor").show();
};
/** @Bind #riskavoidTextareaCfm2.onClick */
!function(self){
	view.get("#autoFormTbsProjcfm2").set("visible",true);
	view.get("#autoFormTbsProjchangeMajcont").set("visible",false);
	view.get("#riskavoidCfm2").set("visible",true);
	view.get("#memoCfm2").set("visible",false);
	view.get("#dialogUeditor").show();
};
/** @Bind #memoTextareaCfm2.onClick */
!function(self){
	view.get("#autoFormTbsProjcfm2").set("visible",true);
	view.get("#autoFormTbsProjchangeMajcont").set("visible",false);
	view.get("#riskavoidCfm2").set("visible",false);
	view.get("#memoCfm2").set("visible",true);
	view.get("#dialogUeditor").show();
};
/** @Bind #riskavoidTextareaCfm1.onClick */
!function(self){
	view.get("#autoFormTbsProjcfm1").set("visible",true);
	view.get("#autoFormTbsProjchangeMajcont").set("visible",false);
	view.get("#riskavoidCfm1").set("visible",true);
	view.get("#memoCfm1").set("visible",false);
	view.get("#dialogUeditor").show();
};
/** @Bind #memoTextareaCfm1.onClick */
!function(self){
	view.get("#autoFormTbsProjcfm1").set("visible",true);
	view.get("#autoFormTbsProjchangeMajcont").set("visible",false);
	view.get("#riskavoidCfm1").set("visible",false);
	view.get("#memoCfm1").set("visible",true);
	view.get("#dialogUeditor").show();
};

//================================新修改CFM的类品金子表单计算=====================================
//====================cfm1=============================
/*=============projCfm1.totloc===========*/
/** @Bind #vfalocCfm1.onPost */
/** @Bind #vnfalocCfm1.onPost */
/** @Bind #votlocCfm1.onPost */
!function(self,arg,autoformCfm1,dataSetTbsProjcfm1){
	var crs = autoformCfm1.get("entity"); 
	var dataSet = dataSetTbsProjcfm1.getData("#");
	var faloc = crs.get("vfaloc"); 
	var nfaloc = crs.get("vnfaloc"); 
	var otloc = crs.get("votloc");
	dataSet.set("vtotloc",faloc+nfaloc+otloc);
};

/** @Bind #biztypeCfm1.onTextEdit */
!function(self,arg,tbsBasBizvarCfm1,dataSetTbsProjcfm1){
	var bizvtDS = dataSetTbsProjcfm1.getData("#.tbsProjcfm1BizvtSet");
	tbsBasBizvarCfm1.set("readOnly", false);
	bizvtDS.current.set("tbsBasBizvar","");
	bizvtDS.current.set("vloc",0);
};
/** @Bind #bizvarCfm1.onPost */
!function(self,arg,tbsBasBizvarCfm1){
	tbsBasBizvarCfm1.set("readOnly", true);
};



/** @Bind #bizvtlocCfm1.onTextEdit */
!function(self,arg,dataSetTbsProjcfm1,vfaloc,vnfaloc,votloc,bizvtlocCfm1,tbsBasBizvarCfm1){
	var newloc = bizvtlocCfm1.get("value");
	var bizvtDS = dataSetTbsProjcfm1.getData("#.tbsProjcfm1BizvtSet");
	var bztp = bizvtDS.current.get("tbsBasBiztype");
	if (newloc){
	bizvtDS.current.set("vloc",newloc);
	}else{
		bizvtDS.current.set("vloc",0);  //一定要设置0，否则删除后计算会不对
	};
	BizvtCounttingcfm(dataSetTbsProjcfm1,vfaloc,vnfaloc,votloc,bizvtlocCfm1,bztp,"tbsProjcfm1BizvtSet");
};

/** @Bind #dataPilotTbsProjCfm1.onSubControlAction */
!function(self,arg,dataSetTbsProjcfm1,dataPilotTbsProjCfm1,vfaloc,vnfaloc,votloc,bizvtlocCfm1){
	var code=arg.code;
	var bizvtDS = dataSetTbsProjcfm1.getData("#.tbsProjcfm1BizvtSet");
	switch (code) {
	case "-":
		arg.processDefault = false;
		dorado.MessageBox.confirm("您真的要删除此项目吗？",
				{title:"趣博信息科技",
				 detailCallback:function(btnID, text)
				 { if (btnID == "yes")
				 	{bizvtDS.current.set("vloc",0);   
				 	 var bztp = bizvtDS.current.get("tbsBasBiztype");  
				 	 bizvtDS.remove();
				 	 BizvtCounttingcfm(dataSetTbsProjcfm1,vfaloc,vnfaloc,votloc,bizvtlocCfm1,bztp,"tbsProjcfm1BizvtSet");	                                                    
				 	}
				 } 	
				}
		);
		break;
	}
};

//====================cfm2=============================
/*=============projCfm2.totloc===========*/
/** @Bind #vfalocCfm2.onPost */
/** @Bind #vnfalocCfm2.onPost */
/** @Bind #votlocCfm2.onPost */
!function(self,arg,autoformCfm2,dataSetTbsProjcfm2){
	var crs = autoformCfm2.get("entity"); 
	var dataSet = dataSetTbsProjcfm2.getData("#");
	var faloc = crs.get("vfaloc"); 
	var nfaloc = crs.get("vnfaloc"); 
	var otloc = crs.get("votloc");
	dataSet.set("vtotloc",faloc+nfaloc+otloc);
};

/** @Bind #biztypeCfm2.onTextEdit */
!function(self,arg,tbsBasBizvarCfm2,dataSetTbsProjcfm2){
	var bizvtDS = dataSetTbsProjcfm2.getData("#.tbsProjcfm2BizvtSet");
	tbsBasBizvarCfm2.set("readOnly", false);
	bizvtDS.current.set("tbsBasBizvar","");
	bizvtDS.current.set("vloc",0);
};
/** @Bind #bizvarCfm2.onPost */
!function(self,arg,tbsBasBizvarCfm2){
	tbsBasBizvarCfm2.set("readOnly", true);
};



/** @Bind #bizvtlocCfm2.onTextEdit */
!function(self,arg,dataSetTbsProjcfm2,vfaloc,vnfaloc,votloc,bizvtlocCfm2,tbsBasBizvarCfm2){
	var newloc = bizvtlocCfm2.get("value");
	var bizvtDS = dataSetTbsProjcfm2.getData("#.tbsProjcfm2BizvtSet");
	var bztp = bizvtDS.current.get("tbsBasBiztype");
	if (newloc){
	bizvtDS.current.set("vloc",newloc);
	}else{
		bizvtDS.current.set("vloc",0);  //一定要设置0，否则删除后计算会不对
	};
	BizvtCounttingcfm(dataSetTbsProjcfm2,vfaloc,vnfaloc,votloc,bizvtlocCfm2,bztp,"tbsProjcfm2BizvtSet");
};

/** @Bind #dataPilotTbsProjCfm2.onSubControlAction */
!function(self,arg,dataSetTbsProjcfm2,dataPilotTbsProjCfm2,vfaloc,vnfaloc,votloc,bizvtlocCfm2){
	var code=arg.code;
	var bizvtDS = dataSetTbsProjcfm2.getData("#.tbsProjcfm2BizvtSet");
	switch (code) {
	case "-":
		arg.processDefault = false;
		dorado.MessageBox.confirm("您真的要删除此项目吗？",
				{title:"趣博信息科技",
				 detailCallback:function(btnID, text)
				 { if (btnID == "yes")
				 	{bizvtDS.current.set("vloc",0);   
				 	 var bztp = bizvtDS.current.get("tbsBasBiztype");  
				 	 bizvtDS.remove();
				 	 BizvtCounttingcfm(dataSetTbsProjcfm2,vfaloc,vnfaloc,votloc,bizvtlocCfm2,bztp,"tbsProjcfm2BizvtSet");	                                                    
				 	}
				 } 	
				}
		);
		break;
	}
};

//===================新修改CFM的类品金子表单函数===========================
function BizvtCounttingcfm(ds,faloc,nfaloc,otloc,bizvtloc,bztp,cfm){
	var cfm = cfm;
	var bizvtDS = ds.getData("#."+cfm);
	var projDS = ds.getData("#");
	var sum1 = 0; 
	var sum2 = 0; 
	var sum3 = 0;
	var del1 = 1; var del2 = 1; var del3 = 1; //表示全部删光
	bizvtDS.each(function(data){
			if (data.get("tbsBasBiztype") == "融资性担保") {		
				sum1 += data.get("vloc")-0;
				projDS.set("vfaloc",sum1);
				del1 = 0;
			};
			if (data.get("tbsBasBiztype") == "非融资性担保") {		
				sum2 += data.get("vloc")-0;
				projDS.set("vnfaloc",sum2);
				del2 = 0;
			};
			if (data.get("tbsBasBiztype") == "其他") {
				sum3 += data.get("vloc")-0;
				projDS.set("votloc",sum3);
				del3 = 0;
			}
	});
	projDS.set("vtotloc",sum1+sum2+sum3);
	//全部删除的处理
	if(del1 == 1){
		projDS.set("vfaloc",0);
	}else if (del2 == 1) {
		projDS.set("vnfaloc",0);
	}else if (del3 == 1) {
		projDS.set("votloc",0);
	}	
	return null;
};

/** @Bind #AutoFormChangeMajcont.onReady */
!function(self, arg, overlimitvalid) {
	overlimitvalid.set("mapping", [ {
		key : false,
		value : "否"
	}, {
		key : true,
		value : "是"
	} ]);
};