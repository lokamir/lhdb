/**
 * Only for UFLO
 * 反担保办理-反担保关联
 * */

// Global variables
var docid;
var projid;
var psid;
var valid;
var taskid = "${request.getParameter('taskId')}";
var outcome = "修改确认";

/*======Get status from CurrentRecordSets======*/
function GetCRStatus(ds){
	var CurRec = ds.getData("#");
	docid = CurRec.get("id")+"";
	projid = CurRec.get("id")+"";
	valid = CurRec.get("valid")+"";
	psid=CurRec.get("tbsBasPs.id")+"";
};

/** @Bind view.onReady */
!function(self, arg, datasetTbsProj){
	datasetTbsProj.flushAsync();
};

/** @Bind #datasetTbsProj.onLoadData */
!function(self,arg,buttonAppr,buttonClose,buttonResend,DdlOutcome,OpinionGroupBox,DocGroupBox,tabInAppr,tabMain){
	GetCRStatus(self);
	if (psid == 24 ){  //驳回
		tabMain.set("currentIndex", 1);
		buttonClose.set("visible",false);
		buttonAppr.set("visible",false);
		buttonResend.set("visible",true);
		self.set("readOnly",false);  
		OpinionGroupBox.set("visible",false);
		DocGroupBox.set("height","88%");
	}else{
		tabMain.set("currentIndex", 0);
		tabInAppr.set("visible",true);
		buttonClose.set("visible",true);
		buttonAppr.set("visible",true);
		buttonResend.set("visible",false);
		DdlOutcome.set("items",["通过","驳回"]);
		self.set("readOnly",true);
	}
};
//Global End

/*============Execute approving=============*/
/** @Bind #ajaxActionAppr.beforeExecute */
!function(self,arg,updateActionSave){
	var opinion=view.id("OpinionAutoform").get("entity").opinion;
	if (!opinion){
		opinion="无意见";
	};
	if (psid == 24){
		self.set("confirmMessage","您确定再次发送审批？");
		updateActionSave.execute();
		self.set("parameter",{docid:docid,projid:projid,psid:psid,taskid:taskid,outcome:outcome,opinion:opinion});
	}else {
			outcome = view.id("OpinionAutoform").get("entity").outcome;
			if(!outcome){
				dorado.MessageBox.alert("请先选择【审批结果】", {title : "趣博信息科技"});	
				throw "exit" ;
			}else {
				self.set("confirmMessage","您确定发送审批？");
			    self.set("parameter",{docid:docid,projid:projid,psid:psid,taskid:taskid,outcome:outcome,opinion:opinion});
			}
	};
};

/*==============Close===================*/
/** @Bind #ajaxActionAppr.onSuccess */
/** @Bind #buttonClose.onClick */
!function(self,arg){
	//var entity=view.id("OpinionAutoform").get("entity");
	window.parent.closeProcessDialog('${request.getParameter("type")}'); 
	//dataSetTbsProjHtsh.get("data:#").cancel();
};

/* =======================valid的key-vlaue=================== */
/** @Bind #autoformCondition.onReady */
!function(self, arg, text) {
	text.set("mapping", [ {
		key : "2",
		value : "全部"
	}, {
		key : "1",
		value : "是"
	}, {
		key : "0",
		value : "否"
	} ]);
};
/* =======================反担保子表=================== */
/** @Bind #datapilotTbsProjCgg.onSubControlAction */
//复写Datapilot的方法，添加按钮触发dialog
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
		cggStr:entityDy.get("sn"),
		cggCname:entityDy.get("sn.cusname")+"||"
		+entityDy.get("sn.cat1name")+"||"
		+entityDy.get("sn.cat2name")+"||"
		+entityDy.get("sn.cat3name")
	};
	targetData.insert(newEntity);
	entityDy.remove();
};

/** @Bind #remove.onClick */
!function(self, arg, datasetVcggall, datasetTbsProj) {
	/*debugger;
	var entityDy = datasetTbsProj.getData("#.#tbsProjCggSet");	
	var entity = datasetTbsProj.getData("#.#tbsProjCggSet");
	var targetData = datasetVcggall.getData();
		var newEntity = {
			sn : entityDy.get("cggStr")
		};
		targetData.insert(newEntity);
		entity.remove();
};*/
	datasetVcggall.flushAsync();
	var targetData =datasetTbsProj.getData("#").get("tbsProjCggSet");
	targetData.each(function(entity){
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
 var targetData =datasetTbsProj.getData("#").get("tbsProjCggSet");
 var targetData2 =datasetTbsProj.getData("#").get("tbsProjCggSet");
 var i=0,j=0;
 targetData.each(function(entity){
  if(entity.get("cggSn")){
   j++;
  };
  targetData2.each(function(entity2){
  if(entity.get("cggSn")==entity2.get("cggSn")){
   i++;
  }
 });
 });
  if(i>j){
    dorado.MessageBox.alert("反担保信息重复，请删除后再确认！",{title:"趣博信息科技"});
   return;
  }else{
 DialogTbsjCgg.hide();
  }
};

//=========双击显示反担保详细信息==========
/** @Bind #UIdatagridTbsProjCgg.onDoubleClick */
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

/** @Bind #dataGridHis.onDataRowDoubleClick */
!function(self) {
	var path = "org.tbs.views.funs.MyTbsFunApprc.d?id=";
	var taskHisId = self.getCurrentItem().get("id");
	view.get("#DialogTbsFunApprc").show();
	view.get("#iFrameTbsFunApprc").set("path", path+taskHisId);
};

