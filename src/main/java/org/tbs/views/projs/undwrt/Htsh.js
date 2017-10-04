/**
 * Only for UFLO
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
	projid = CurRec.get("tbsProj.id")+"";
	valid = CurRec.get("valid")+"";
	psid=CurRec.get("tbsProj.tbsBasPs.id")+"";
};

/** @Bind view.onReady */
!function(self, arg, dataSetTbsProjHtsh){
	dataSetTbsProjHtsh.flushAsync();
};

/** @Bind #dataSetTbsProjHtsh.onLoadData */
!function(self,arg,buttonAppr,buttonClose,buttonResend,DdlOutcome,OpinionGroupBox,DocGroupBox,tabInAppr,tabMain){
	GetCRStatus(self);
	if (psid == 12 ){  //驳回
		tabMain.set("currentIndex", 1);
		buttonClose.set("visible",false);
		buttonAppr.set("visible",false);
		buttonResend.set("visible",true);
		self.set("readOnly",true);  //此表单无修改内容
		OpinionGroupBox.set("visible",false);
		DocGroupBox.set("height","40%");
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
!function(self,arg){
	var opinion=view.id("OpinionAutoform").get("entity").opinion;
	if (!opinion){
		opinion="无意见";
	};
	if (psid == 12){
		self.set("confirmMessage","您确定再次发送审批？");
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

/** @Bind #dataGridHis.onDataRowDoubleClick */
!function(self) {
	var path = "org.tbs.views.funs.MyTbsFunApprc.d?id=";
	var taskHisId = self.getCurrentItem().get("id");
	view.get("#DialogTbsFunApprc").show();
	view.get("#iFrameTbsFunApprc").set("path", path+taskHisId);
};
