var businessId = "${request.getParameter('businessId')}";
var processInstanceId = "${request.getParameter('processInstanceId')}";
var initfaloc;
var initnfaloc;
var initotloc;

/** @Bind #dataSetTbsProj.onLoadData */
!function(self, arg,dataSetTbsProj,dataSetTbsCggChg) {
var taskId = "${request.getParameter('taskId')}";
		var params = { taskid : taskId,	};
		view.get("#ajaxactionGetUfloNode").set("parameter",params).execute(
				function(nodename){
					nodeName = nodename;
				if (nodename == "驳回修正"){
					view.get("#listDdlOutcome").set("items",["修改确认"]);
					view.get("#RowSelectColumn").set("visible",true);
				}else if (nodename == "风管经理调整授信额度"){
					view.get("#listDdlOutcome").set("items",["确认"]);
					view.get("#tabTbsCggChg").set("visible", true);
					var projId = dataSetTbsProj.getData("#").get("id");
					var cusId = dataSetTbsProj.getData("#").get("tbsCustomer.id");
					var sn = dataSetTbsProj.getData("#").get("sn");
					var oldprojname = dataSetTbsProj.getData("#.projName");
					view.get("#autoformTbsCggChg").getElement("xdeFaloc").set("visible",true);
					view.get("#autoformTbsCggChg").getElement("xdeNfaloc").set("visible",true);
					view.get("#autoformTbsCggChg").getElement("xdeOtloc").set("visible",true);
					initfaloc = view.get("#dataSetTbsProj").getData("#.initfaloc");
					initnfaloc = view.get("#dataSetTbsProj").getData("#.initnfaloc");
					initotloc = view.get("#dataSetTbsProj").getData("#.initotloc");
					dataSetTbsCggChg.insert({
						sn : sn,
						cusId : cusId,
						projId : projId,
						oldprojname : oldprojname,
						valid : 0,
						by1 : processInstanceId
					});
				}else{
					if (nodename == "评委会秘书录入"){
						view.get("#tabTbsCggChg").set("visible", true);
						dataSetTbsCggChg.set("parameter", processInstanceId).flushAsync();
						view.get("#autoformTbsCggChg").getElement("xdeFaloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("xdeFaloc").set("readOnly",true);
						view.get("#autoformTbsCggChg").getElement("xdeNfaloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("xdeNfaloc").set("readOnly",true);
						view.get("#autoformTbsCggChg").getElement("xdeOtloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("xdeOtloc").set("readOnly",true);
						view.get("#autoformTbsCggChg").getElement("deFaloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("deNfaloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("deOtloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("deTotloc").set("visible",true);
					}
					if(nodeName ==  "风管负责人审批"|| nodeName == "总经理审核"){
						view.get("#tabTbsCggChg").set("visible",true);
						dataSetTbsCggChg.set("parameter", processInstanceId).flushAsync();
						view.get("#autoformTbsCggChg").getElement("xdeFaloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("xdeFaloc").set("readOnly",true);
						view.get("#autoformTbsCggChg").getElement("xdeNfaloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("xdeNfaloc").set("readOnly",true);
						view.get("#autoformTbsCggChg").getElement("xdeOtloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("xdeOtloc").set("readOnly",true);
						view.get("#autoformTbsCggChg").getElement("deFaloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("deFaloc").set("readOnly",true);
						view.get("#autoformTbsCggChg").getElement("deNfaloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("deNfaloc").set("readOnly",true);
						view.get("#autoformTbsCggChg").getElement("deOtloc").set("visible",true);
						view.get("#autoformTbsCggChg").getElement("deOtloc").set("readOnly",true);
						view.get("#autoformTbsCggChg").getElement("deTotloc").set("visible",true);
					}		
					view.get("#listDdlOutcome").set("items",["通过","驳回"]);
				}	
				});
};
		
/*========== 页面计算===========*/
/** @Bind #xdeFaloc.onPost */
/** @Bind #xdeNfaloc.onPost */
/** @Bind #xdeOtloc.onPost */
!function(self,arg){
	var autoform = view.get("#autoformTbsCggChg").get("entity");
	var xdeFaloc =autoform.get("xdeFaloc"); 
	var xdeNfaloc =autoform.get("xdeNfaloc"); 
	var xdeOtloc = autoform.get("xdeOtloc");
	if((xdeFaloc>initfaloc||xdeNfaloc>initnfaloc||xdeOtloc>initotloc)){
		dorado.MessageBox.alert("对不起，您输入的【减少金额】大于【现有授信金额】。\n请重新输入！ ",{title:"趣博信息科技"});
	}
};

/** @Bind #deFaloc.onPost */
/** @Bind #deNfaloc.onPost */
/** @Bind #deOtloc.onPost */
!function(self,arg){
	var autoform = view.get("#autoformTbsCggChg").get("entity");
	var deFaloc =autoform.get("deFaloc"); 
	var deNfaloc =autoform.get("deNfaloc"); 
	var deOtloc = autoform.get("deOtloc");
	var xdeFaloc =autoform.get("xdeFaloc"); 
	var xdeNfaloc =autoform.get("xdeNfaloc"); 
	var xdeOtloc = autoform.get("xdeOtloc");
	//debugger;
	if(deFaloc>xdeFaloc||deNfaloc>xdeNfaloc||deOtloc>xdeOtloc){
		dorado.MessageBox.alert("对不起，填写金额不能大于风管经理建议金额。\n请重新输入！ ",{title:"趣博信息科技"});
		autoform.set("deTotloc",null);
	}else{
		var deTotloc = 0;
		//deTotloc = Number(deFaloc)+Number(deNfaloc)+Number(deOtloc);
		deTotloc = deFaloc+deNfaloc+deOtloc;
		autoform.set("deTotloc",deTotloc);
	}
};
/*========== 页面计算结束===========*/


/** @Bind #tabControlMain.onReady */ 
!function(self,arg,dataSetTbsProjCgg,dataSetTbsProj) {
		var params = {
				businessId:businessId,
				};
		dataSetTbsProjCgg.set("parameter", params).flushAsync();
		dataSetTbsProj.set("parameter", businessId).flushAsync();	
};



/** @Bind #btnClose.onClick */
!function(self, arg) {
	var dialogMainForm = window.parent.$id("dialogProjDetails").objects[0];
	dialogMainForm.close();
};

/*======================待办任务，进行审批==================*/
/** @Bind #btnApprSubmit.onClick */ 
!function(self,arg,autoformTbsCggChg,autoformOpinion,SaveCgg,SaveTbsCggChg,ajaxactionApprSubmit,DataGridTbsProjCgg) {
	var outcome = autoformOpinion.get("entity.outcome");
	var comment = autoformOpinion.get("entity.comment");
	var xdeFaloc = autoformTbsCggChg.get("entity.xdeFaloc");
	var xdeNfaloc = autoformTbsCggChg.get("entity.xdeNfaloc");
	var xdeOtloc = autoformTbsCggChg.get("entity.xdeOtloc");
	var deFaloc =autoformTbsCggChg.get("entity.deFaloc"); 
	var deNfaloc =autoformTbsCggChg.get("entity.deNfaloc"); 
	var deOtloc = autoformTbsCggChg.get("entity.deOtloc");
	var deTotloc = autoformTbsCggChg.get("entity.deTotloc");
	var taskId = "${request.getParameter('taskId')}";
	var docid = "${request.getParameter('businessId')}";
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
	if (nodeName == "风管经理调整授信额度"
		&& ((!xdeFaloc&&xdeFaloc!=0)||(!xdeNfaloc&&xdeNfaloc!=0)||(!xdeOtloc&&xdeOtloc!=0))){
		dorado.MessageBox.alert("对不起，【减少金额】必填！", {
			title : "趣博信息科技"
		});
		return;
		}else if(nodeName == "风管经理调整授信额度"){
			SaveTbsCggChg.execute();
	}
	if(nodeName == "评委会秘书录入"&&outcome == "驳回"){
		a=1;
		}else if((nodeName == "评委会秘书录入")
				&&((!deFaloc&&deFaloc!=0)||(!deNfaloc&&deNfaloc!=0)||(!deOtloc&&deOtloc!=0)||!deTotloc&&deTotloc!=0)) {
			
			dorado.MessageBox.alert("对不起，【减少金额】必填！", {
			title : "趣博信息科技"
				});	
			return;
		}else if(nodeName == "评委会秘书录入"){
			SaveTbsCggChg.execute(
					{callback : function(result){
						if (!result) {
							return
							}
						}
					});	
			}
	if (!comment) {
		comment = "无意见";
	}
	if (outcome == "修改确认") {
		var entity = DataGridTbsProjCgg.get("selection");
		if (entity != "") {
			var revokecggSn = new Array();
			entity.each(function(data) {
				var cggSn = data.get("cggSn");
				revokecggSn.push(cggSn);
			});
			var params = {
					taskid : taskId,
					docid : docid,
					outcome : outcome,
					comment : comment,
				};
			//先执行ajaxactionApprSubmit，再执行savecgg
			params["revokecggSn"] = revokecggSn;// 把修改后的反担保编号写入流程
			ajaxactionApprSubmit.set("parameter", params).execute(
					{callback : function(result){
						if (result == true) {
							SaveCgg.set("parameter", {revokecggSn:revokecggSn,processInstanceId:processInstanceId}).execute();
						}
					}
			});
		} else {
			dorado.MessageBox.alert("请选择需要解除的反担保物", {
				title : "趣博信息科技"
			});
		}
	} else {
		var params = {
				taskid : taskId,
				docid : docid,
				outcome : outcome,
				comment : comment,
			};
		ajaxactionApprSubmit.set("parameter", params).execute();
	}
};

/*=========待办任务界面关闭窗口============*/
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
	var title = "Cggrevoke";
	var projsn = dataSetTbsProj.getData("#").get("sn");
	var fid ="反担保解除";
	var by3 =null;
	var typid =null;
	var invisible ="invisible";//禁上传（invisible）
	var attachpath="org.tbs.views.funs.MyFile.d?by1=" + title 
			+ "&by2=" + projsn
			+ "&by3=" + by3
			+ "&fid=" + fid
			+ "&typid=" + typid
			+ "&uploadbutton=" +invisible
			+ "&dt=" + new Date();
			iFrameAttach.set("path",attachpath);
};
/**
 * 文件处理  结束
 */

/*=========增改删的cgg内容显示不同颜色==========*/
/** @Bind #DataGridTbsProjCgg.onRenderRow */
!function(self,arg){
	var state = arg.data.get("state");
	if (state == 0) {
		arg.dom.style.background = "";
	} else if (state == 1) {
		arg.dom.style.background = "#a70000";
	} else if (state == 2) {
		arg.dom.style.background = "#CDCD00";
	} 
};

//=========双击显示反担保详细信息==========
/** @Bind #DataGridTbsProjCgg.onDataRowDoubleClick */
!function() {
	var path = "";
	var entity = view.get("#DataGridTbsProjCgg").getCurrentEntity("entity").get("cggStr");
	var cggSn = view.get("#DataGridTbsProjCgg").getCurrentEntity("entity").get("cggSn");
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