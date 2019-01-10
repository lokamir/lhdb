// Global variables
var psid;
var id;
var fromAppr = 1; // approve form
var uploadbutton; //附件组件参数
var downloadbutton;//附件组件参数
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
		//autoformTbsProj_main.set("entity.bdate", rq);
		autoformTbsProj_main.set("entity.tbsBasPs.id", 1);
	}

	// 读取当前这一行的数据
	if (id) {
		/**ERROR  */
		datasetTbsProj.set("parameter", id).flushAsync();
		var btnSubmit = view.get("#btnSubmit");
		btnSubmit.set("visible", true);
	}
	
	if (fromAppr && !id) {
		datasetTbsProj.flushAsync();
	}
	
};

// base on psid, set views' readOnly & visible property
/** @Bind #datasetTbsProj.onLoadData */
!function(self, arg) {
	var datasetTbsProj = view.get("#datasetTbsProj");
	//******* 1.14 add  ******* start
	var dataSetTbsProjeaa = view.get("#dataSetTbsProjeaa");
	//******* 1.14 add  ********* end
	var autoformTbsProj_main = view.get("#autoformTbsProj_main");
	var projDetail = view.get("#projDetail");
	var groupboxAB = view.get("#groupboxAB");
	var groupboxAppr = view.get("#groupboxAppr");
	var groupboxEaaAppr = view.get("#groupboxProjeaaAppr");
	var btnSave = view.get("#btnSave");
	var btnSubmit = view.get("#btnSubmit");
	var btnClose = view.get("#btnClose");
	var tabHis = view.get("#tabHis");
	var tabInAppr = view.get("#tabInAppr");
	var data = datasetTbsProj.getData("#");
	var listDdlOutcome = view.get("#listDdlOutcome");
	var datapilotTbsProjBank = view.get("#datapilotTbsProjBank");
	var datapilotTbsProjBizvt = view.get("#datapilotTbsProjBizvt");
	var datapilotTbsProjCgg = view.get("#datapilotTbsProjCgg");
	//var upbutton = view.get("#upbutton");
	//var btnsaveAll = view.get("#btnsaveAll");
	var tabControlMain = view.get("#tabControlMain");
	
//	alert(loginUser);
	
	if (data) {
		psid = data.get("tbsBasPs.id");
		id = data.get("id");
		if ( psid != 1) {
			tabHis.set("visible", true);
			// block form update function
			btnSave.set("visible", false);
			btnSubmit.set("visible", false);
			datapilotTbsProjBank.set("visible", false);
			datapilotTbsProjBizvt.set("visible", false);
			datapilotTbsProjCgg.set("visible", false);
			if(!fromAppr){
				autoformTbsProj_main.set("readOnly", true);
				projDetail.set("readOnly", true);
				//upbutton.set("disabled",true);	
				//btnsaveAll.set("disabled",true);
				uploadbutton="disable";
				downloadbutton="disable";
				tabControlMain.set("currentIndex", 1);
			}
//			alert(data.get("bdf2User_A.username"));		
		}
		//老项目权限
		if ( psid == 99) {
			tabHis.set("visible", false);
			btnSave.set({"visible": true,"disabled":false,"caption":"提交更改"});
			btnSubmit.set("visible", false);
			datapilotTbsProjBank.set("visible", true);
			//datapilotTbsProjBizvt.set("visible", true);
			datapilotTbsProjCgg.set("visible", true);
			autoformTbsProj_main.set("readOnly", false);
			view.get("#faloc").set("readOnly", true);
			view.get("#nfaloc").set("readOnly", true);
			view.get("#otloc").set("readOnly", true);
			view.get("#tbsCustomer").set("readOnly", true);
			view.get("#projDetail").set("readOnly", true);
			view.get("#datagridTbsProjBizvt").getColumn("initloc").set("readOnly",false);
		}	
		
		// In Approval：mainform -> readonly, show AB_role_form, hide btnSave & btnSubmit
		if (fromAppr) {
			view.get("#panel").set("visible",false);
			//show tabInAppr
			tabInAppr.set("visible",true);
			tabControlMain.set("currentIndex", 0);
			// hide all original buttons
			btnSave.set("visible", false);
			btnSubmit.set("visible", false);
			btnClose.set("visible", false);
			// show approve group box
			groupboxAppr.set("visible", true);
			if (psid == 30) {
				datapilotTbsProjBank.set("visible", true);
				datapilotTbsProjBizvt.set("visible", true);
				datapilotTbsProjCgg.set("visible", true);
				groupboxEaaAppr.set("visible", false);
				groupboxAB.set("visible", false);
				//upbutton.set("disabled",false);	
				//btnsaveAll.set("disabled",false);
				uploadbutton="";
				downloadbutton="";
			} else {
				//upbutton.set("disabled",true);	
				//btnsaveAll.set("disabled",true);
				uploadbutton="disable";
				downloadbutton="disable";
			}
			if (psid == 35 || psid == 3 || psid == 4 || psid == 5) { //eaa appr
				dataSetTbsProjeaa.set("parameter", id).flushAsync();
				groupboxAppr.set("visible",false);
				groupboxAB.set("visible", false);
				//upbutton.set("disabled",false);
				uploadbutton="";
			}
			
			
			// projCreate approve case
			if (psid == 2) {//简化立项审批原来psid=29改成psid=2
				// main form read only
				autoformTbsProj_main.set("readOnly", true);
				projDetail.set("readOnly", true);
				// initialize outcome options
				listDdlOutcome.set("items",["通过","驳回"]);
				groupboxEaaAppr.set("visible", false);
				
			} /*else if (psid == 2) {
				// main form read only
				autoformTbsProj_main.set("readOnly", false);
				view.get("#datagridTbsProjBank").set("readOnly", false);
				view.get("#datagridTbsProjBizvt").set("readOnly", false);
				view.get("#datagridTbsProjCgg").set("readOnly", false);
				datapilotTbsProjBank.set("visible", true);
				datapilotTbsProjBizvt.set("visible", true);
				datapilotTbsProjCgg.set("visible", true);
				// initialize outcome options
				listDdlOutcome.set("items",["确认修改"]);
				groupboxEaaAppr.set("visible", false);
				groupboxAB.set("visible", false);
				//upbutton.set("disabled",false);
				uploadbutton="";
			} */else if(psid == 35 || psid == 3) {
				// initialize outcome options
				listDdlOutcome.set("items",["通过","驳回"]);
			} else {
				// initialize outcome options
				listDdlOutcome.set("items",["确认修改"]);
			}
//			 alert(window.location);
		}/*else{
			if(view.get("#datasetTbsProj").getData("#.totloc")){
				if(view.get("#datasetTbsProj").getData("#.totloc")!=0){
				//view.get("#btnSave").set("disabled",false);
				view.get("#btnSave").set("disabled",true);
				}
			}
		}*/
	}
};

/*====close detail====*/
/** @Bind #btnApprClose.onClick */
/** @Bind #btnProjeaaApprClose.onClick */
/** @Bind #updateactionProjeaa.onSuccess */
/** @Bind #ajaxactionApprSubmit.onSuccess */
!function(self, arg) {
	window.parent.closeProcessDialog('${request.getParameter("type")}');
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
	if(newloc.charAt(newloc.length-1)=="\."){
		return;
	}
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
	}
	if (del2 == 1) {
		projDS.set("nfaloc",0);
	}
	if (del3 == 1) {
		projDS.set("otloc",0);
	}
	/*if((sum1+sum2+sum3)!=0){
		view.get("#btnSave").set("disabled",false);
	}else{
		view.get("#btnSave").set("disabled",true);
	}*/
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
	/*if(faloc+nfaloc+otloc!=0){
		view.get("#btnSave").set("disabled",false);
	}else{
		view.get("#btnSave").set("disabled",true);
	}*/
};

/*==========projeaa===========*/
/** @Bind #eaafaloc.onPost */
/** @Bind #eaanfaloc.onPost */
/** @Bind #eaaotloc.onPost */
!function(self,arg,autoformProjeaa,eaatotloc,dataSetTbsProjeaa){
	var crs = autoformProjeaa.get("entity"); 
	var dataSet = dataSetTbsProjeaa.getData("#");
	var eaafaloc = crs.get("faloc"); 
	var eaanfaloc = crs.get("nfaloc"); 
	var eaaotloc = crs.get("otloc");
	dataSet.set("totloc",eaafaloc+eaanfaloc+eaaotloc);
};
/** 
 * ==============Page Countting END===============
 */

//******* 1.14 add  ******* start
//base on psid, set views' readOnly & visible property
/** @Bind #dataSetTbsProjeaa.onLoadData */
!function(self, arg, tabProjeaa,datapilotTbsProjBank,datapilotTbsProjBizvt,datapilotTbsProjCgg){
	var autoformTbsProj_main = view.get("#autoformTbsProj_main");
	var autoformProjeaa = view.get("#autoformProjeaa");
	var tabTbsProj = view.get("#tabTbsProj");
	var groupboxProjeaaAppr = view.get("#groupboxProjeaaAppr");
	autoformProjeaa.get("entity.VOcname");
	autoformProjeaa.set("entity.VOcname","${loginUser.getCname()}");
	tabProjeaa.set("visible", true);
	
	if (fromAppr) {
		autoformTbsProj_main.set("readOnly", true);
		tabTbsProj.set("visible",true);
		groupboxProjeaaAppr.set("visible",true);
		if(psid == 35 || psid == 4|| psid == 3) {
			autoformProjeaa.set("readOnly", false);
			view.get("#datagridTbsProjBank").set("readOnly", false);
			view.get("#datagridTbsProjBizvt").set("readOnly", false);
			view.get("#datagridTbsProjCgg").set("readOnly", false);
			datapilotTbsProjBank.set("visible", true);
			datapilotTbsProjBizvt.set("visible", true);
			datapilotTbsProjCgg.set("visible", true);
		}
	}
};
//******* 1.14 add  ********* end

/**
 * ================================================== *
 * =======================审批页面按钮=================== *
 * ==================================================
 */

/** @Bind #btnApprSubmit.onClick */
!function(self, arg, autoformTbsProj_role, autoformOpinion,
		ajaxactionApprSubmit, datasetTbsProj, updateActionSave) {
	// 判断 AB是否为空
	// var data = autoformTbsProj_role.get("entity");
	var userA = autoformTbsProj_role.get("entity.bdf2User_A");
	var userB = autoformTbsProj_role.get("entity.bdf2User_B");
	var outcome = autoformOpinion.get("entity.outcome");
	var data = datasetTbsProj.getData("#");
	
	if (userA == "") {
		userA = null;
	}
	if (userB == "") {
		userB = null;
	}
	
	// 通过时，userA和userB不能为空
	if (outcome == "通过" && (!userA || !userB)) {
		dorado.MessageBox.alert("请先分配项目经理A角和B角", {
			title : "趣博信息科技"
		});
		return;
	}
	if(outcome == "通过" && psid == 2) { //简化立项审批原来psid=29改成psid=2
		data.set("valid", true);
	}
	//bizvt 去重
	var targetData = datasetTbsProj.getData("#.tbsProjBizvtSet");
	var targetData2 = datasetTbsProj.getData("#.tbsProjBizvtSet");
	var i = 0, j = 0;
	targetData.each(function(entity) {
		if (entity.get("tbsBasBizvar.id")) {
			j++;
		}
		;
		targetData2
				.each(function(entity2) {
					if (entity.get("tbsBasBizvar.id") == entity2
							.get("tbsBasBizvar.id")) {
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

//******* 1.14 add  ******* start
/** @Bind #btnProjeaaApprSubmit.onClick */
!function(self, arg, autoformTbsProj_role, autoformProjeaaOpinion,updateActionSave,
		ajaxactionApprSubmit, dataSetTbsProjeaa, updateactionProjeaa) {
	var outcome = view.get("#autoformProjeaaOpinion").get("entity.outcome");
	var comment = view.get("#autoformProjeaaOpinion").get("entity.comment");
	debugger;
	if (taskName == 'A角确认'&&outcome == "通过"){
		dataSetTbsProjeaa.getData("#").set("valid",true);
	}
	if (outcome == "驳回" && !comment) {
		dorado.MessageBox.alert("驳回时审批意见不能为空！！", {
			title : "趣博信息科技"
		});
		return false;
	}else if(outcome == "通过"){
		if(!dataSetTbsProjeaa.getData("#").get("bdf2Dept")){
			dorado.MessageBox.alert("请填写申报部门", {
				title : "趣博信息科技"
			});
			return false;
		}
		if(required(dataSetTbsProjeaa.getData("#").get("otloc"),dataSetTbsProjeaa.getData("#").get("nfaloc"),dataSetTbsProjeaa.getData("#").get("faloc"))){
			dorado.MessageBox.alert("金额不能为空", {
				title : "趣博信息科技"
			});
			return false;
		}
		if(!dataSetTbsProjeaa.getData("#").get("bdf2User")){
			dorado.MessageBox.alert("请填写经办部门负责人", {
				title : "趣博信息科技"
			});
			return false;
		}else{
			//saveData(view.get("#autoformTbsProj_main"));
		}
	}
	// 保存AB角数据
	updateActionSave.execute(function(result){
		updateactionProjeaa.execute();
	});
	
	// 提交表单
	apprSubmit(psid, autoformProjeaaOpinion, ajaxactionApprSubmit);
};

function required(i,r,q){
	if(i!=null&&r!=null&&q!=null){
		return false;
	}else{
		return true;
	}
}

//******* 1.14 add  ********* end

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
		return;
	}
	
	// outcome不能为空
	if (!outcome) {
		dorado.MessageBox.alert("审批意见不能为空", {
			title : "趣博信息科技"
		});
		return;
	}
	
	// 设定参数并提交workflow
	var taskId = "${request.getParameter('taskId')}";
	var businessId = "${request.getParameter('businessId')}";
	var param = {
			taskId : taskId,
			businessId : businessId,
			outcome : outcome,
			comment : comment,
			psid : psid + ""
	};
	ajaxactionApprSubmit.set("parameter", param);
	ajaxactionApprSubmit.execute();
	/*ajaxactionApprSubmit.execute(function(result) {
		dorado.MessageBox.alert(result, {
			title : "趣博信息科技"
		});
		// window.parent.closeProcessDialog('${request.getParameter("type")}');
	});*/
	
};

/* =======================发送审批=================== */
/** @Bind #btnSubmit.onClick */
!function(self, arg, ajaxactionStartProcess, datasetTbsProj) {
	var data = datasetTbsProj.getData("#");
	var param = {
		entity : data
	};
	if(!data.get("by5")){
		dorado.MessageBox.alert("请填写企业简介", {
			title : "趣博信息科技"
		});
		return false;
	};
	if(data.get("tbsProjBizvtSet").isEmpty()){
		dorado.MessageBox.alert("业务类品金不能为空", {
			title : "趣博信息科技"
		});
		return false;
	}else if (data.get("tbsProjBizvtSet.current").isDirty()){
		dorado.MessageBox.alert("请先点击确认保存", {
			title : "趣博信息科技"
		});
		return false;
	}
	ajaxactionStartProcess.set("parameter", param).execute(function(result) {
		//var pop = view.get("#autoformTbsProj_main");
		//saveData(self,arg,pop);
		dorado.MessageBox.alert(result, {
			title : "趣博信息科技"
		});
	});
	
};

/** @Bind #ajaxactionStartProcess.onSuccess */
!function(datasetTbsProj){
	datasetTbsProj.flushAsync();
};


/* =======================dialog取消按钮-清空当前数据操作=================== */
/** @Bind #btnClose.onClick */
!function(self, arg) {
	var condition = window.parent.$id("autoformCondition").objects[0].get("entity");
	var dialogMainForm = window.parent.$id("dialogMainForm").objects[0];
	var datasetTbsProjParents = window.parent.$id("datasetTbsProj").objects[0];
	dialogMainForm.hide();
	datasetTbsProjParents.set("parameter", condition).flushAsync();
};

/*=======老项目反担保物状态更改Vaild=1=========*/
/** @Bind #updateActionSave.beforeExecute */
!function(self, arg) {
	var datasetTbsProj = this.id("datasetTbsProj");
	if (datasetTbsProj.getData("#.tbsBasPs.id")==99){
		var dataCgg = datasetTbsProj.getData("#").get("tbsProjCggSet");
		dataCgg.each(function(entity){
			entity.set("valid",1);
		});
		var dataBizvt = datasetTbsProj.getData("#").get("tbsProjBizvtSet");
		dataBizvt.each(function(entity){
			entity.set("valid",1);
		});
	}
};



/*=======项目表保存后=========*/
/** @Bind #updateActionSave.onSuccess */
!function(self, arg) {
	var autoformCondition = window.parent.$id("autoformCondition").objects[0];
	if (autoformCondition){
		var condition = autoformCondition.get("entity");
		var datasetTbsProjP = window.parent.$id("datasetTbsProj").objects[0];
		datasetTbsProjP.set("parameter", condition).flushAsync();
	}else{
		return;
	}
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
	case "+":{
		var dialog = view.id("DialogTbsjCgg");
		dialog.show({
			caption : "选择反担保信息",
		});
		arg.processDefault = false;
		break;
	}
	case "x":{
		datasetVcggall.flushAsync();
		datasetTbsProj.getData("#").get("tbsProjCggSet").cancel();
		arg.processDefault = false;
		break;
	}
	}
	
};

/** @Bind #add.onClick */
!function(self, arg, datasetVcggall, datasetTbsProj) {
	var entityDy = datasetVcggall.getData("#");
	var targetData = datasetTbsProj.getData("#").get("tbsProjCggSet");
	var newEntity = {
		//$dataType : "v:org.tbs.views.projs.creation.TbsProjDetails$datatypeTbsProj",
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

	/* var entityDy = datasetTbsProj.getData("#.#tbsProjCggSet").get("cggStr");
	 var entity = datasetTbsProj.getData("#.#tbsProjCggSet"); 
	 var targetData = datasetVcggall.getData(); 
	 var newEntity = { sn : entityDy };
	 targetData.insert(newEntity); 
	 entity.remove(); 
	};*/
	 
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
	var title = "projCreate";
	var projsn = datasetTbsProj.getData("#").get("sn");
	var fid ="项目受理";
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
/** @Bind #dgTbsProjCgg.onDataRowDoubleClick */
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
/** @Bind #dgVcggall.onDataRowDoubleClick */
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


/* =======================dialog确认按钮及下拉框空值解决方案+查询条件下拉空值解决=================== */
/** @Bind #btnSave.onClick */
function saveData(self, arg, autoformTbsProj_main) {
	var dialogMainForm = window.parent.$id("dialogMainForm").objects[0]; // get parent page
	var datasetTbsProj = view.get("#datasetTbsProj");
	var updateActionSave = view.get("#updateActionSave");
	var custom = datasetTbsProj.getData("#").get("tbsCustomer");
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
	// autoform去空
	if (autoformTbsProj_main.get("entity.tbsCustomer") == "") {
		autoformTbsProj_main.set("entity.tbsCustomer", null);
	}

	if (autoformTbsProj_main.get("entity.tbsBasConsway") == "") {
		autoformTbsProj_main.set("entity.tbsBasConsway", null);
	}

	if (autoformTbsProj_main.get("entity.tbsBasGovfund") == "") {
		autoformTbsProj_main.set("entity.tbsBasGovfund", null);
	}

	if (autoformTbsProj_main.get("entity.tbsBasCurrency") == "") {
		autoformTbsProj_main.set("entity.tbsBasCurrency", null);
	}

	if (autoformTbsProj_main.get("entity.bdf2User_A") == "") {
		autoformTbsProj_main.set("entity.bdf2User_A", null);
	}

	if (autoformTbsProj_main.get("entity.bdf2User_B") == "") {
		autoformTbsProj_main.set("entity.bdf2User_B", null);
	}

	if (autoformTbsProj_main.get("entity.tbsBasPs") == "") {
		autoformTbsProj_main.set("entity.tbsBasPs", null);
	}

	var targetData = datasetTbsProj.getData("#.tbsProjBizvtSet");
	var targetData2 = datasetTbsProj.getData("#.tbsProjBizvtSet");
	var i = 0, j = 0;
	targetData.each(function(entity) {
		if (entity.get("tbsBasBizvar.id")) {
			j++;
		}
		;
		targetData2
				.each(function(entity2) {
					if (entity.get("tbsBasBizvar.id") == entity2
							.get("tbsBasBizvar.id")) {
						i++;
					}
				});
	});
	if (i > j) {
		dorado.MessageBox.alert("业务类型和品种有重复，请删除后再确认！", {
			title : "趣博信息科技"
		});
		return;
	}else if (!custom){	
		dorado.MessageBox.alert("请选择客户！", {
			title : "趣博信息科技"
		});
		return;
	}else {
		updateActionSave.execute();
		var btnSubmit = view.get("#btnSubmit");
		btnSubmit.set("visible", true);
	}
	
	dialogMainForm.hide();
};

/** @Bind #dataGridHis.onDataRowDoubleClick */
!function(self) {
	var path = "org.tbs.views.funs.MyTbsFunApprc.d?id=";
	var taskHisId = self.getCurrentItem().get("id");
	view.get("#DialogTbsFunApprc").show();
	view.get("#iFrameTbsFunApprc").set("path", path+taskHisId);
};

// 获取页面参数的值
function GetUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};

/** @Bind #tabControlMain.onTabChange */
!function(self){
	if(self.get("currentTab.caption")=="立项审批单"){
		view.get("#panel").set("visible",true);
	}else{
		//view.get("#panel").set("visible",false);
	}
};

/** @Bind #btnPrint.onClick */
!function(self){
	var id = view.get("#datasetTbsProj").getData("#").get("id");
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
    window.open(pref+"/ureport/preview?_t=1,5&_n=立项申请表&_u=file:立项申请表.ureport.xml&id="+id);
};

/** @Bind #riskavoidTextarea.onClick */
!function(self){
	view.get("#riskavoidElement").set("visible",true);
	view.get("#memoElement").set("visible",false);
	view.get("#dialogUeditor").show();
};
/** @Bind #memoTextarea.onClick */
!function(self){
	view.get("#riskavoidElement").set("visible",false);
	view.get("#memoElement").set("visible",true);
	view.get("#dialogUeditor").show();
};


///** @Bind #uEditor.onCreate */
/*
!function(self){
	dorado.ueditor.registerMode("simple", []);
};*/

///** @Bind #riskavoidTextarea.onClick */
/*
!function(self){
	var uEditor = view.get("#uEditor");
	uEditor.set("property","riskavoid");
	view.get("#dialogUeditor").show();	

};*/

///** @Bind #memoTextarea.onClick */
/*
!function(self){
	var uEditor = view.get("#uEditor");
	uEditor.set("property","memo");
	view.get("#dialogUeditor").show();
};*/

///** @Bind #memo.onClick */
/*
!function(self){
	var tipUeditor = view.get("#tipUeditor");
	var riskavoid = view.get("#autoformTbsProj_main").get("entity.memo");
	tipUeditor.set("content", riskavoid);
	tipUeditor.set("anchorTarget", self);//利用FloatControl控件的anchorTarget特性Tip浮动的目标控件
	tipUeditor.set("align", "right");
	trackMouse: true;
	tipUeditor.show();//显示Tip控件
};*/

///** @Bind #tipUeditor.onClick */
/*
!function(self){
	self.hide();
};*/
