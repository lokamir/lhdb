// Global variables
var psid;
var id;
var fromAppr = 1;
var uploadbutton = "disable";
var downloadbutton = "disable";
var taskName = "${param.taskName}";

/** @Bind view.onReady */
!function(self, arg, autoformTbsProj_main) {
	if (GetUrlParam("psid")) {
		psid = GetUrlParam("psid") - 0;
	}
	if (GetUrlParam("id")) {
		id = GetUrlParam("id") - 0;
	}
	if (GetUrlParam("fromAppr")) {
		fromAppr = GetUrlParam("fromAppr") - 0;
	}
	var datasetTbsProj = view.get("#datasetTbsProj");
	// 项目新建 psid=0
	if (!psid) {
		datasetTbsProj.insert();
		var rq = new Date();
		autoformTbsProj_main.set("entity.timestampInput", rq);
		autoformTbsProj_main.set("entity.timestampUpdate", rq);
	}

	// 读取当前这一行的数据
	if (id) {
		datasetTbsProj.set("parameter", id).flushAsync();
	}
	
	if (fromAppr && !id) {
		datasetTbsProj.flushAsync();
	}

};

// base on psid, set views' readOnly & visible property
/** @Bind #datasetTbsProj.onLoadData */
!function(self, arg) {
	var datasetTbsProj = view.get("#datasetTbsProj");
	var dataSetTbsProjcfm0 = view.get("#dataSetTbsProjcfm0");
	var datasetTbsProjOpinion = view.get("#datasetTbsProjOpinion");
	var datasetTbsProjOpinion1r2 = view.get("#datasetTbsProjOpinion1r2");
	var dataSetTbsProjcfm1 = view.get("#dataSetTbsProjcfm1");
	var dataSetTbsProjcfm2 = view.get("#dataSetTbsProjcfm2");
	var datapilotCfm0ProjOpin = view.get("#datapilotCfm0ProjOpin");
	var datagridCfm0ProjOpin = view.get("#datagridCfm0ProjOpin");
	var datagridCfm1r2ProjOpin = view.get("#datagridCfm1r2ProjOpin");
	var autoformTbsProj_main = view.get("#autoformTbsProj_main");
	var autoformTbsProjCfm0 = view.get("#autoformTbsProjCfm0");
//	var groupboxAB = view.get("#groupboxAB");
	var groupboxCfm0 = view.get("#groupboxCfm0");
	var groupboxCfm0ProjOpin = view.get("#groupboxCfm0ProjOpin");
	var groupboxCfm1r2ProjOpin = view.get("#groupboxCfm1r2ProjOpin");
	var groupboxCfm1 = view.get("#groupboxCfm1");
	var groupboxCfm2 = view.get("#groupboxCfm2");
	var groupboxAppr = view.get("#groupboxProjAppr");
	var groupboxCfm0Appr = view.get("#groupboxCfm0Appr");
	var groupboxCfm1r2Appr = view.get("#groupboxCfm1r2Appr");
	//var upbutton = view.get("#upbutton");
	var btnsaveAll = view.get("#btnsaveAll");
	var btnSave = view.get("#btnSave");
	var btnSubmit = view.get("#btnSubmit");
	var btnClose = view.get("#btnClose");
	var tabControlMain = view.get("#tabControlMain");
	var tabHis = view.get("#tabHis");
	var tabInAppr = view.get("#tabInAppr");
	var tabCfm0 = view.get("#tabCfm0");
	var tabCfm1r2 = view.get("#tabCfm1r2");
	var data = datasetTbsProj.getData("#");
	var listDdlOutcome = view.get("#listDdlOutcome");
	//var loginUser = "${loginUsername}";
	if (data) {
		psid = data.get("tbsBasPs.id");
		id = data.get("id");
		// PhaseI: when proj created & loginUser = UserA, show btnDecisionRequest 
//		if (psid == 5 && data.get("bdf2User_A.username") == loginUser) {  //No Arole No start approve
		if (psid == 5) {
			btnSubmit.set("visible", true);
			autoformTbsProj_main.set("readOnly", false);
			view.get("#datapilotTbsProjBank").set("visible", true);
			view.get("#datapilotTbsProjBizvt").set("visible", true);
			view.get("#datapilotTbsProjCgg").set("visible", true);
			view.get("#datagridTbsProjBank").set("readOnly", false);
			view.get("#datagridTbsProjBizvt").set("readOnly", false);
			view.get("#datagridTbsProjCgg").set("readOnly", false);
			//upbutton.set("disabled",false);
			uploadbutton="";
		} else {
			tabHis.set("visible", true);
			// block form update function
			btnSave.set("visible", false);
			btnSubmit.set("visible", false);
			view.get("#datapilotTbsProjBank").set("visible", false);
			view.get("#datapilotTbsProjBizvt").set("visible", false);
			view.get("#datapilotTbsProjCgg").set("visible", false);
			if(!fromAppr){
//				autoformTbsProj_main.set("readOnly", true);
				tabControlMain.set("currentIndex", 1);
				//upbutton.set("disabled",false);
				uploadbutton="";
			}
		}

		// PhaseII: after cfmo grnt
		if (psid != 5 && psid != 6 && psid != 36) {
			tabCfm0.set("visible", true);
			groupboxCfm0.set("visible", true);
			var cfm0Para = {
					BusinessId: data.get("id"),
					processInstanceId: data.get("by2")
			};
			dataSetTbsProjcfm0.set("parameter", cfm0Para).flush();
			var dataCfm0 = dataSetTbsProjcfm0.getData("#");
			var opinPara = {
					projId: data.get("id"),
					cfm0Id: dataCfm0.get("id")
			};
			datasetTbsProjOpinion.set("parameter", opinPara).flush();
			
			// meeting or countersign stage
			if (psid != 37) {
				datasetTbsProjOpinion1r2.set("parameter", opinPara).flush();
			}
		}
		
		// PhaseIII: after cfm1 or cfm2 grnt
		if (psid != 5 && psid != 6 && psid != 36 
				&& psid != 37 && psid != 21 && psid != 22) {
			tabCfm1r2.set("visible", true);
			var cfm1r2Para = {
					BusinessId: data.get("id"),
					processInstanceId: data.get("by2")
			};
			if (dataCfm0) {
				var cfmType = dataCfm0.get("type");
				if (cfmType == '会议') {
					dataSetTbsProjcfm1.set("parameter", cfm1r2Para).flush();
					groupboxCfm1.set("visible", true);
				} else if (cfmType == '签批') {
					dataSetTbsProjcfm2.set("parameter", cfm1r2Para).flush();
					groupboxCfm2.set("visible", true);
				}
			}
			
			
			
		}

		// In Approval：mainform -> readonly, show AB_role_form, hide btnSave &
		// btnSubmit
		if (fromAppr) {
			// hide all original buttons
			tabInAppr.set("visible", true);
			btnSave.set("visible", false);
			btnSubmit.set("visible", false);
			btnClose.set("visible", false);
			// show approve group box
			groupboxAppr.set("visible", true);
			tabControlMain.set("currentIndex", 0);
			// projCreate approve case
			if (psid == 6) {
				// main form read only
//				autoformTbsProj_main.set("readOnly", true);
				
				// initialize outcome options
				listDdlOutcome.set("items",["通过","驳回"]);
			} else if(psid == 37) {
				view.get("#autoformTbsProjCfm0").get("entity").set("keyinId","${dorado.getDataProvider('el#Uid').getResult()}");
				autoformTbsProjCfm0.set("readOnly", false);
				groupboxCfm0ProjOpin.set("visible", true);
				datapilotCfm0ProjOpin.set("visible", true);
				datagridCfm0ProjOpin.set("readOnly", false);
				groupboxCfm0.set("visible", true);
				groupboxAppr.set("visible", false);
				listDdlOutcome.set("items",["会议","签批"]);
				tabInAppr.set("visible", true);
				groupboxCfm0Appr.set("visible", true);
				uploadbutton="";
			} else if(psid == 36) {
				tabInAppr.set("visible", true);
				autoformTbsProj_main.set("readOnly", false);
				view.get("#datapilotTbsProjBank").set("visible", true);
				view.get("#datapilotTbsProjBizvt").set("visible", true);
				view.get("#datapilotTbsProjCgg").set("visible", true);
				listDdlOutcome.set("items",["确认修改"]);
				view.get("#datagridTbsProjBank").set("readOnly", false);
				view.get("#datagridTbsProjBizvt").set("readOnly", false);
				view.get("#datagridTbsProjCgg").set("readOnly", false);
				//upbutton.set("disabled",false);
				//btnsaveAll.set("disabled",false);
				uploadbutton="";
				downloadbutton="";
			} else if(psid == 21) {
				tabInAppr.set("visible", true);
				groupboxCfm1r2ProjOpin.set("visible", true);
				datagridCfm1r2ProjOpin.set("readOnly", false);
				groupboxCfm0.set("visible", true);
				groupboxAppr.set("visible", false);
				groupboxCfm0Appr.set("visible", true);
				listDdlOutcome.set("items",["通过","驳回"]);
				//var targetDatas = datasetTbsProjOpinion1r2.getData();
				// set promoter DH's outcome to "回避"
				//targetDatas.each(function(targetData){
				//	if (targetData.get("title") == "发起人部门经理") {
				//		targetData.set("outcome","回避");
				//	}
				//});
			} else if(psid == 22) {
				tabInAppr.set("visible", true);
				groupboxCfm1r2ProjOpin.set("visible", true);
				groupboxAppr.set("visible", false);
				groupboxCfm0.set("visible", false);
				groupboxCfm0Appr.set("visible", true);
				listDdlOutcome.set("items",["同意","反对","回避","缺席","弃权"]);
				//var targetDatas = datasetTbsProjOpinion1r2.getData();
				// set promoter DH's outcome to "回避"
				//targetDatas.each(function(targetData){
				//	if (targetData.get("title") == "发起人部门经理") {
				//		targetData.set("outcome","回避");
				//	}
				//});
			} else if(psid == 8) {
				if(taskName == "评审会秘书录入会议决议单"){
					if(view.get("#autoformCfm1").get("entity")){
						view.get("#autoformCfm1").get("entity").set("keyinId","${dorado.getDataProvider('el#Uid').getResult()}");
					}else if(view.get("#autoformCfm2").get("entity")){
						view.get("#autoformCfm2").get("entity").set("keyinId","${dorado.getDataProvider('el#Uid').getResult()}");
					}
				 	var autoformCfm1 = view.get("#autoformCfm1");
				    var dataPilotTbsProjCfm1 = view.get("#dataPilotTbsProjCfm1");
				    var DialogTbsProjCfm1 = view.get("#DialogTbsProjCfm1");
				    var autoformCfm2 = view.get("#autoformCfm2");
				    var dataPilotTbsProjCfm2 = view.get("#dataPilotTbsProjCfm2");
				    var DialogTbsProjCfm2 = view.get("#DialogTbsProjCfm2");
				    DialogTbsProjCfm1.set("readOnly", false);
				    dataPilotTbsProjCfm1.set("visible", true);
				    autoformCfm1.set("readOnly", false);
				    DialogTbsProjCfm2.set("readOnly", false);
				    dataPilotTbsProjCfm2.set("visible", true);
				    autoformCfm2.set("readOnly", false);
				    tabCfm0.set("visible", true);
				    tabInAppr.set("visible", true);
				    groupboxCfm0.set("visible", false);
				    groupboxCfm1r2ProjOpin.set("visible", true);
				    groupboxCfm1r2Appr.set("visible", true);
				    groupboxCfm0Appr.set("visible", false);
				    groupboxAppr.set("visible", false);
				    listDdlOutcome.set("items",["确认修改"]);
				}else if(taskName == "主任委员审批"){
					var autoformCfm1 = view.get("#autoformCfm1");
				    var dataPilotTbsProjCfm1 = view.get("#dataPilotTbsProjCfm1");
				    var DialogTbsProjCfm1 = view.get("#DialogTbsProjCfm1");
				    var autoformCfm2 = view.get("#autoformCfm2");
				    var dataPilotTbsProjCfm2 = view.get("#dataPilotTbsProjCfm2");
				    var DialogTbsProjCfm2 = view.get("#DialogTbsProjCfm2");
				    DialogTbsProjCfm1.set("readOnly", true);
				    dataPilotTbsProjCfm1.set("visible", true);
				    autoformCfm1.set("readOnly", true);
				    DialogTbsProjCfm2.set("readOnly", true);
				    dataPilotTbsProjCfm2.set("visible", true);
				    autoformCfm2.set("readOnly", true);
				    tabCfm0.set("visible", true);
				    tabInAppr.set("visible", true);
				    groupboxCfm0.set("visible", false);
				    groupboxCfm1r2ProjOpin.set("visible", true);
				    groupboxCfm1r2Appr.set("visible", true);
				    groupboxCfm0Appr.set("visible", false);
				    groupboxAppr.set("visible", false);
					listDdlOutcome.set("items",["通过","驳回"]);
				}
			} else if(psid == 26) {
				var dataPilotTbsProjCfm1 = view.get("#dataPilotTbsProjCfm1");
				var dataPilotTbsProjCfm2 = view.get("#dataPilotTbsProjCfm2");
				tabInAppr.set("visible", true);
				groupboxCfm0.set("visible", false);
				groupboxCfm1r2ProjOpin.set("visible", true);
				groupboxCfm1r2Appr.set("visible", true);
				groupboxAppr.set("visible", false);
				listDdlOutcome.set("items",["通过","驳回"]);
				tabCfm0.set("visible", false);
				dataPilotTbsProjCfm1.set("visible", false);
				dataPilotTbsProjCfm2.set("visible", false);
			} else {
				// initialize outcome options
				listDdlOutcome.set("items",["确认修改"]);
			}
//			 alert(window.location);
		}
	}

};

/**
 * ================================================== *
 * =======================审批页面按钮=================== *
 * ==================================================
 */
//2017-06-23 新增 决议审批autoformCfm自动统计票数
/** @Bind #tabCfm1r2.onClick */
!function(self){
	if(taskName == '评审会秘书录入会议决议单'){
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
	}/*else if(type == 2){
		var autoformCfm1 = view.get("#autoformCfm1");
		autoformCfm1.get("entity").set("tobe",tobe);
		autoformCfm1.get("entity").set("actbe",actbe);
		autoformCfm1.get("entity").set("nobe",nobe);
		autoformCfm1.get("entity").set("debarb",debarb);
		autoformCfm1.get("entity").set("waiver",waiver);
		autoformCfm1.get("entity").set("opoose",opoose);
		autoformCfm1.get("entity").set("agree",agree);
	}*/
	}
};


/** @Bind #btnApprSubmit.onClick */
!function(self, arg, autoformTbsProj_role, autoformOpinion,
		ajaxactionApprSubmit, datasetTbsProj, updateActionSave,autoformTbsProj_main) {

	//=======子表数据验证========
	//var dialogMainForm = window.parent.$id("dialogMainForm").objects[0];
	//var datasetTbsProjS = window.parent.$id("datasetTbsProj").objects[0];
	var datasetTbsProj = view.get("#datasetTbsProj");
	var updateActionSave = view.get("#updateActionSave");

	// 子tab中的datagrid去空
	var TbsProjCgg = datasetTbsProj.getData("#").get("tbsProjCggSet");
	TbsProjCgg.each(function(entity) {
		if (entity.get("cggId") == "") {
			entity.set("cggId", null);
		}
	});

	var TbsBasIdcardtype = datasetTbsProj.getData("#").get("tbsProjBizvtSet");
	TbsBasIdcardtype.each(function(entity) {
		if (entity.get("tbsBasBizvar") == "") {
			entity.set("tbsBasBizvar", null);
		}
		if (entity.get("tbsBasBiztype") == "") {
			entity.set("tbsBasBiztype", null);
		}
	});

	var TbsBasEnttype = datasetTbsProj.getData("#").get("tbsProjBankSet");
	TbsBasEnttype.each(function(entity) {
		if (entity.get("tbsBasBank_M") == "") {
			entity.set("tbsBasBank_M", null);
		}
		if (entity.get("tbsBasBank_S") == "") {
			entity.set("tbsBasBank_S", null);
		}
	});
	
	var targetData = datasetTbsProj.getData("#.tbsProjBizvtSet");
	var targetData2 = datasetTbsProj.getData("#.tbsProjBizvtSet");
	var i = 0, j = 0;
	targetData.each(function(entity) {
		if (entity.get("tbsBasBizvar.id")) {
			j++;
		}
		;
		targetData2.each(function(entity2) {
					if (entity.get("tbsBasBizvar.id") == entity2.get("tbsBasBizvar.id")) {
						i++;
					}
				});
	});
	if (i > j) {
		dorado.MessageBox.alert("业务类型和品种有重复，请删除后再确认！", {
			title : "趣博信息科技"
		});
		return;
	} else {
		updateActionSave.execute();
		//var btnSubmit = view.get("#btnSubmit");
		//btnSubmit.set("visible", true);
	}
		
	// 提交表单
	apprSubmit(psid, autoformOpinion, ajaxactionApprSubmit);
};

/** @Bind #btnApprClose.onClick */
function closeApprForm(self, arg) {
	window.parent.closeProcessDialog('${request.getParameter("type")}');
};

/** @Bind #btnCfm0ApprSubmit.onClick */
!function(self, arg, autoformCfm0Opinion,
		ajaxactionCfm0ApprSubmit, datasetTbsProj, dataSetTbsProjcfm0, 
		datasetTbsProjOpinion, datasetTbsProjOpinion1r2, updateactionCfm0, updateactionProjOpin, updateactionProjOpin1r2) {
	
	var dataProjOpins = datasetTbsProjOpinion.get("data");
	var dataProjOpin1r2s = datasetTbsProjOpinion1r2.get("data");
	var dataProj= datasetTbsProj.getData("#");
	var dataCfm0 = dataSetTbsProjcfm0.getData("#");
	var outcome = autoformCfm0Opinion.get("entity.outcome");
	
	// validations for cfm0
	if (outcome == null || outcome == "") {
		dorado.MessageBox.alert("审批结果不能为空！！", {title : "趣博信息科技"});
		return;
	}
	
	// add attendee/approver case, binding proj_id & cfm0_id 
	dataProjOpins.each(function(dataProjOpin){
		if (dataProjOpin.state == 1 ) { //1 新增 2修改
			//ERROR STATEMENTS
			//dataProjOpin.set("tbsProj", dataProj.get("id"));
			//dataProjOpin.set("tbsProjcfm0", dataCfm0.get("id"));
			dataProjOpin.set("tbsProj", dataProj.toJSON());
			dataProjOpin.set("tbsProjcfm0", dataCfm0.toJSON());

		}
	});
	
	
	if (psid == 37) {
		var r=confirm("请确认是否已经上传附件");
		if (r==true){
			// 保存cfm0和projOpinion数据
			dataCfm0.set("type", outcome);
			updateactionCfm0.execute();   // tbsProjcfm0Dao#save
			updateactionProjOpin.execute();   //tbsProjOpinionDao#save
		}else{
			return
		}
	} else if (psid == 21 || psid == 22) {
		if (psid == 22) {
			var loginUsername="${loginUser.getUsername()}";
			dataProjOpin1r2s.each(function(dataProjOpin1r2){
				
				if (dataProjOpin1r2.get("bdf2User.username") == loginUsername ) {
					dataProjOpin1r2.set("outcome", outcome);
				}
			});
		}
		updateactionProjOpin1r2.execute();  //tbsProjOpinionDao#save
	}
	
	// 提交表单
	apprSubmit(psid, autoformCfm0Opinion, ajaxactionCfm0ApprSubmit); // projectCfm#cfm0ApprSubmit
	

};

/** @Bind #btnCfm0ApprClose.onClick */
!function(self, arg) {
	closeApprForm(self, arg);
};

/** @Bind #btnCfm1r2ApprSubmit.onClick */
!function(self, arg, autoformTbsProj_role, autoformCfm1r2Opinion,
		ajaxactionApprSubmit, datasetTbsProj, updateactionCfm1, updateactionCfm2) {
	// 保存projCfm1 & projCfm2数据
	updateactionCfm1.execute();
	updateactionCfm2.execute();
	
	// 提交表单
	apprSubmit(psid, autoformCfm1r2Opinion, ajaxactionApprSubmit);
};

/** @Bind #btnCfm1r2ApprClose.onClick */
!function(self, arg) {
	closeApprForm(self, arg);
};

/** ===========	approval view			============ 
 *  ===========	Shared submit function	============*/
function apprSubmit(psid, autoformOpinion, ajaxactionApprSubmit) {
	var outcome = autoformOpinion.get("entity.outcome");
	var comment = autoformOpinion.get("entity.comment");
	
	if (outcome == "") {
		outcome = null;
	}
	
	if (comment == "") {
		comment = null;
	}
	
	// 驳回时comment不能为空
	if (outcome == "驳回" && !comment) {
		dorado.MessageBox.alert("驳回时审批意见不能为空！！", {
			title : "趣博信息科技"
		});
		// 中断方法
		return;
	}
	
	// outcome不能为空
	if (!outcome) {
		dorado.MessageBox.alert("审批意见不能为空", {
			title : "趣博信息科技"
		});
		// 中断方法
		return;
	}
	
	// 设定参数并提交workflow
	var taskId = "${request.getParameter('taskId')}";
	var businessId = "${request.getParameter('businessId')}";
	var processInstanceId = "${request.getParameter('processInstanceId')}";
	var param = {
			taskId : taskId,
			businessId : businessId,
			outcome : outcome,
			comment : comment,
			psid : psid + "",
			processInstanceId : processInstanceId
	};
	ajaxactionApprSubmit.set("parameter", param);
	ajaxactionApprSubmit.execute();/*function(result) {    //projectCfm#apprSubmit
		window.parent.closeProcessDialog('${request.getParameter("type")}');
		dorado.MessageBox.alert(result, {
			title : "趣博信息科技"
		});
	});*/
};

/** 
 * ==============Page Countting START===============
 */
/*==========bizvtloc===============*/
/** @Bind #biztype.onTextEdit */
!function(self,arg,tbsBasBizvar,datasetTbsProj){
	var bizvtDS = datasetTbsProj.getData("#.tbsProjBizvtSet");
	tbsBasBizvar.set("readOnly", false);
	bizvtDS.current.set("tbsBasBizvar","");
	bizvtDS.current.set("loc",0);
};
/** @Bind #bizvar.onPost */
!function(self,arg,tbsBasBizvar){
	tbsBasBizvar.set("readOnly", true);
};

/** @Bind #bizvtloc.onTextEdit */
!function(self,arg,datasetTbsProj,faloc,nfaloc,otloc,bizvtloc,tbsBasBizvar){
	var newloc = bizvtloc.get("text");
	var bizvtDS = datasetTbsProj.getData("#.tbsProjBizvtSet");
	var bztp = bizvtDS.current.get("tbsBasBiztype");
	if (newloc){
	bizvtDS.current.set("loc",newloc);
	}else{
		bizvtDS.current.set("loc",0);  //一定要设置0，否则删除后计算会不对
	};
	BizvtCountting(datasetTbsProj,faloc,nfaloc,otloc,bizvtloc,bztp);
};

/** @Bind #datapilotTbsProjBizvt.onSubControlAction */
!function(self,arg,datasetTbsProj,datapilotTbsProjBizvt,faloc,nfaloc,otloc,bizvtloc){
	var code=arg.code;
	var bizvtDS = datasetTbsProj.getData("#.tbsProjBizvtSet");
	switch (code) {
	case "-":
		arg.processDefault = false;
		dorado.MessageBox.confirm("您真的要删除此项目吗？",
				{title:"趣博信息科技",
				 detailCallback:function(btnID, text)
				 { if (btnID == "yes")
				 	{bizvtDS.current.set("loc",0);   
				 	 var bztp = bizvtDS.current.get("tbsBasBiztype");  
				 	 bizvtDS.remove();
				 	 BizvtCountting(datasetTbsProj,faloc,nfaloc,otloc,bizvtloc,bztp);	                                                    
				 	}
				 } 	
				}
		);
		break;
	}
};

function BizvtCountting(ds,faloc,nfaloc,otloc,bizvtloc,bztp){
	var bizvtDS = ds.getData("#.tbsProjBizvtSet");
	var projDS = ds.getData("#");
	var sum1 = 0; 
	var sum2 = 0; 
	var sum3 = 0;
	var del1 = 1; var del2 = 1; var del3 = 1; //表示全部删光
	bizvtDS.each(function(data){
			if (data.get("tbsBasBiztype") == "融资性担保") {		
				sum1 += data.get("loc")-0;
				projDS.set("faloc",sum1);
				del1 = 0;
			};
			if (data.get("tbsBasBiztype") == "非融资性担保") {		
				sum2 += data.get("loc")-0;
				projDS.set("nfaloc",sum2);
				del2 = 0;
			};
			if (data.get("tbsBasBiztype") == "其他") {
				sum3 += data.get("loc")-0;
				projDS.set("otloc",sum3);
				del3 = 0;
			}
	});
	projDS.set("totloc",sum1+sum2+sum3);
	projDS.set("nameloc",sum1+sum2+sum3);
	//全部删除的处理
	if(del1 == 1){
		projDS.set("faloc",0);
	}else if (del2 == 1) {
		projDS.set("nfaloc",0);
	}else if (del3 == 1) {
		projDS.set("otloc",0);
	}	
	return null;
};

/*=============proj.totloc===========*/
/** @Bind #faloc.onPost */
/** @Bind #nfaloc.onPost */
/** @Bind #otloc.onPost */
!function(self,arg,autoformTbsProj_main,totloc,datasetTbsProj){
	var crs = autoformTbsProj_main.get("entity"); 
	var dataSet = datasetTbsProj.getData("#");
	var faloc = crs.get("faloc"); 
	var nfaloc = crs.get("nfaloc"); 
	var otloc = crs.get("otloc");
	dataSet.set("totloc",faloc+nfaloc+otloc);
	dataSet.set("nameloc",faloc+nfaloc+otloc);
};
/** 
 * ==============Page Countting END===============
 */


/*========Close========*/
/** @Bind #updateActionSave.onSuccess */
/** @Bind #ajaxactionApprSubmit.onSuccess */
/** @Bind #ajaxactionCfm0ApprSubmit.onSuccess */
/** @Bind #btnApprClose.onClick */
!function(self, arg) {
	window.parent.closeProcessDialog('${request.getParameter("type")}');
};

/** @Bind #btnSave.onClick */
!function(self, arg) {

	//=======子表数据验证========
	//var dialogMainForm = window.parent.$id("dialogMainForm").objects[0];
	//var datasetTbsProjS = window.parent.$id("datasetTbsProj").objects[0];
	var datasetTbsProj = view.get("#datasetTbsProj");
	var updateActionSave = view.get("#updateActionSave");

	// 子tab中的datagrid去空
	var TbsProjCgg = datasetTbsProj.getData("#").get("tbsProjCggSet");
	TbsProjCgg.each(function(entity) {
		if (entity.get("cggId") == "") {
			entity.set("cggId", null);
		}
	});

	var TbsBasIdcardtype = datasetTbsProj.getData("#").get("tbsProjBizvtSet");
	TbsBasIdcardtype.each(function(entity) {
		if (entity.get("tbsBasBizvar") == "") {
			entity.set("tbsBasBizvar", null);
		}
		if (entity.get("tbsBasBiztype") == "") {
			entity.set("tbsBasBiztype", null);
		}
	});

	var TbsBasEnttype = datasetTbsProj.getData("#").get("tbsProjBankSet");
	TbsBasEnttype.each(function(entity) {
		if (entity.get("tbsBasBank_M") == "") {
			entity.set("tbsBasBank_M", null);
		}
		if (entity.get("tbsBasBank_S") == "") {
			entity.set("tbsBasBank_S", null);
		}
	});
	
	var targetData = datasetTbsProj.getData("#.tbsProjBizvtSet");
	var targetData2 = datasetTbsProj.getData("#.tbsProjBizvtSet");
	var i = 0, j = 0;
	targetData.each(function(entity) {
		if (entity.get("tbsBasBizvar.id")) {
			j++;
		}
		;
		targetData2.each(function(entity2) {
					if (entity.get("tbsBasBizvar.id") == entity2.get("tbsBasBizvar.id")) {
						i++;
					}
				});
	});
	if (i > j) {
		dorado.MessageBox.alert("业务类型和品种有重复，请删除后再确认！", {
			title : "趣博信息科技"
		});
		return;
	} else {
		updateActionSave.execute();
	}
};

/* =======================发送审批=================== */
/** @Bind #btnSubmit.onClick */
!function(self, arg, datasetTbsProj, ajaxactionStartProcess) {
	var data = datasetTbsProj.getData("#");
	var param = {
		entity : data
	};
	ajaxactionStartProcess.set("parameter", param).execute(function(result) {
		dorado.MessageBox.alert(result, {
			title : "趣博信息科技"
		});
	});
	datasetTbsProj.flushAsync();
};

/* =======================dialog取消按钮-清空当前数据操作=================== */
/** @Bind #btnClose.onClick */
!function(self, arg) {
	var dialogMainForm = window.parent.$id("dialogMainForm").objects[0];
	var datasetTbsProj = window.parent.$id("datasetTbsProj").objects[0];
	dialogMainForm.hide();
	//datasetTbsProj.get("data:#").cancel();
	datasetTbsProj.getData("#").cancel();
	datasetTbsProj.getData("#").get("tbsProjBizvtSet").cancel();
	datasetTbsProj.getData("#").get("tbsProjBankSet").cancel();
};

/* ========保存后刷新，执行query相同的方法========= */
/** @Bind #updateActionSave.onSuccess */
!function(self, arg) {
	var autoformCondition = window.parent.$id("autoformCondition").objects[0];
	var datasetTbsProj = window.parent.$id("datasetTbsProj").objects[0];
	var entity = autoformCondition.get("entity");
	datasetTbsProj.set("parameter", entity).flushAsync();
};

/* =======================反担保子表=================== */
/** @Bind #datapilotTbsProjCgg.onSubControlAction */
!function(self, arg, DialogTbsjCgg, datasetVcggall, datasetTbsProj) {
	var customerId = datasetTbsProj.getData("#").get("tbsCustomer").get("id");
	if (customerId) {
		datasetVcggall.set("parameter", customerId);
		datasetVcggall.flushAsync();
	}
	switch (arg.code) {
	case "+":
		var dialog = view.id("DialogTbsjCgg");
		dialog.show({
			caption : "选择反担保信息",
		});
		arg.processDefault = false;
		break;
	}
};

/** @Bind #add.onClick */
!function(self, arg, datasetVcggall, datasetTbsProj) {
	var entityDy = datasetVcggall.getData("#");
	var targetData = datasetTbsProj.getData("#").get("tbsProjCggSet");
	var newEntity = {
		cggSn : entityDy.get("sn.sn"),
		cggStr : entityDy.get("sn"),
		cggCname : entityDy.get("sn.cusname") + "||"
				+ entityDy.get("sn.cat1name") + "||"
				+ entityDy.get("sn.cat2name") + "||"
				+ entityDy.get("sn.cat3name")
	};
	targetData.insert(newEntity);
	entityDy.remove();
};
/** @Bind #remove.onClick */
!function(self, arg, datasetVcggall, datasetTbsProj) {
	/*
	 * var entityDy = datasetTbsProj.getData("#.#tbsProjCggSet").get("cggStr");
	 * var entity = datasetTbsProj.getData("#.#tbsProjCggSet"); var targetData =
	 * datasetVcggall.getData(); var newEntity = { sn : entityDy };
	 * targetData.insert(newEntity); entity.remove(); };
	 */
	datasetVcggall.flushAsync();
	var targetData = datasetTbsProj.getData("#").get("tbsProjCggSet");
	targetData.each(function(entity) {
		entity.cancel();
	});
};

/** @Bind #Cancel.onClick */
!function(self, arg, DialogTbsjCgg, datasetVcggall, datasetTbsProj) {
	DialogTbsjCgg.hide();
	datasetTbsProj.getData("#").get("tbsProjCggSet").cancel();
};

/** @Bind #Save.onClick */
!function(self, arg, DialogTbsjCgg, datasetVcggall, datasetTbsProj) {
	var targetData = datasetTbsProj.getData("#").get("tbsProjCggSet");
	var targetData2 = datasetTbsProj.getData("#").get("tbsProjCggSet");
	var i = 0, j = 0;
	targetData.each(function(entity) {
		if (entity.get("cggSn")) {
			j++;
		}
		;
		targetData2.each(function(entity2) {
			if (entity.get("cggSn") == entity2.get("cggSn")) {
				i++;
			}
		});
	});
	if (i > j) {
		dorado.MessageBox.alert("反担保信息重复，请删除后再确认！", {
			title : "趣博信息科技"
		});
		return;
	} else {
		DialogTbsjCgg.hide();
	}
};

/**
 * 文件处理 开始
 */

/** @Bind #tabAttach.onClick */
!function(self,arg,iFrameAttach,datasetTbsProj){
	var title = "projCfm";
	var projsn = datasetTbsProj.getData("#").get("sn");
	var fid ="决策审批";
	var by3 =null;
	var typid =null;
	var attachpath="org.tbs.views.funs.MyFile.d?by1=" + title 
			+ "&by2=" + projsn
			+ "&by3=" + by3
			+ "&fid=" + fid
			+ "&typid=" + typid
			+ "&uploadbutton=" + uploadbutton
			+ "&downloadbutton=" + downloadbutton
			+ "&dt=" + new Date();
			iFrameAttach.set("path",attachpath);
};

/**
 * 文件处理  结束
 */




// 获取页面参数的值
function GetUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};

//=========双击显示反担保详细信息==========
/** @Bind #datagridTbsProjCgg.onDoubleClick */
!function() {
	var path = "";
	var entity = view.get("#datasetTbsProj").getData("#.#tbsProjCggSet.cggStr");
	var cggSn = view.get("#datasetTbsProj").getData("#.#tbsProjCggSet.cggSn");
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
/** @Bind #dgTbsProjCgg.onDoubleClick */
!function() {
	var path = "";
	var entity = view.get("#datasetTbsProj").getData("#.#tbsProjCggSet.cggStr");
	var cggSn = view.get("#datasetTbsProj").getData("#.#tbsProjCggSet.cggSn");
	var cat1name;
	var cat2name;
	var cat3name;
	var customerId = view.get("#datasetTbsProj").getData("#").get("tbsCustomer").get("id");;
	if(entity[0]){
	cat1name = entity[0].cat1name;
	cat2name = entity[0].cat2name;
	cat3name = entity[0].cat3name;
	}else{
		cat1name = entity.get("cat1name");
		cat2name = entity.get("cat2name");
		cat3name = entity.get("cat3name");
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
/** @Bind #dgVcggall.onDoubleClick */
!function() {
	var path = "";
	var cat1name= view.get("#datasetVcggall").getData("#.sn.cat1name");
	var cat2name= view.get("#datasetVcggall").getData("#.sn.cat2name");
	var cat3name= view.get("#datasetVcggall").getData("#.sn.cat3name");
	var cggSn = view.get("#datasetVcggall").getData("#.sn.sn");
	var customerId = view.get("#datasetTbsProj").getData("#").get("tbsCustomer").get("id");;	
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
