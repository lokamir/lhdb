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
!function(self, arg, dataSetTbsProjundwrt){
	dataSetTbsProjundwrt.flushAsync();
};

/** @Bind #dataSetTbsProjundwrt.onLoadData */
!function(self,arg,buttonAppr,buttonClose,buttonResend,DdlOutcome,OpinionGroupBox,DocGroupBox,dataSetTbsFunFul,upbutton,tabInAppr,tabMain,chunaGroupBox){
	GetCRStatus(self);
	var loginU="${loginUser.getUsername()}";
	if (psid == 19 || psid == 9  ){  //驳回
		tabMain.set("currentIndex", 1);
		buttonClose.set("visible",false);
		buttonAppr.set("visible",false);
		buttonResend.set("visible",true);
		self.set("readOnly",false);  
		OpinionGroupBox.set("visible",false);
		DocGroupBox.set("height","88%");	
		dataSetTbsFunFul.set("readOnly",false);  //uploader 
    	upbutton.set("disabled",false);
	}else{
		tabMain.set("currentIndex", 0);
		tabInAppr.set("visible",true);
		buttonClose.set("visible",true);
		buttonAppr.set("visible",true);
		buttonResend.set("visible",false);
		DdlOutcome.set("items",["通过","驳回"]);
		self.set("readOnly",true);
		dataSetTbsFunFul.set("readOnly",true); 
    	upbutton.set("disabled",true);
    	if (loginU == 'admin' || loginU == 'xinc') {
    		self.set("readOnly",false);
    	};
    	
	};
};
//Global End

/*============Execute approving=============*/
/** @Bind #ajaxActionAppr.beforeExecute */
!function(self,arg,updateActionSave){
	var opinion=view.id("OpinionAutoform").get("entity").opinion;
	if (!opinion){
		opinion="无";
	};
	if (psid == 19 || psid == 9 ){
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
				updateActionSave.execute();
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

/*=============本次承保金额和日期判断=====crs表示CurrentRecordSets==========*/
/** @Bind #appfaloc.onPost */
/** @Bind #appnfaloc.onPost */
/** @Bind #appotloc.onPost */
/** @Bind #undbdate.onPost */
/** @Bind #by3.onPost */
!function(self,arg,ddlAutoform,buttonResend,apptotloc,dataSetTbsProjundwrt){
	var crs = ddlAutoform.get("entity"); 
	var dataSet = dataSetTbsProjundwrt.getData("#");
	// 金额
	var appfaloc = crs.get("appfaloc"); var appnfaloc = crs.get("appnfaloc"); var appotloc = crs.get("appotloc");
	var vfaloc = crs.get("tbsProj.vfaloc"); var vnfaloc = crs.get("tbsProj.vnfaloc"); var votloc = crs.get("tbsProj.votloc");
	// 日期
	var bdate = crs.get("bdate"); var edate = crs.get("edate");
	var undbdate = crs.get("undbdate"); //var undedate = crs.get("undedate");
	var by3 = crs.get("by3");
	if (appfaloc > vfaloc || appnfaloc > vnfaloc || appotloc > votloc){
		dorado.MessageBox.alert("对不起，您输入的【本次承保金额】 大于【当前可用授信额度】，请重新录入！",{title:"趣博信息科技"});
		buttonResend.set("disabled",true);		
	}else if (undbdate < bdate || undbdate > edate){  //undedate < bdate || undedate > edate
		dorado.MessageBox.alert("对不起，您输入的项目【承保开始日期】范围， 超出了【授信日期】的范围，请重新录入！",{title:"趣博信息科技"});
		buttonResend.set("disabled",true);
	}else if (by3 <= 0){
		dorado.MessageBox.alert("对不起，【承保期限（月）】必须输入一个大于零的数字！",{title:"趣博信息科技"});
		buttonResend.set("disabled",true);
	}
	else{
		buttonResend.set("disabled",false);
	    dataSet.set("apptotloc",appfaloc+appnfaloc+appotloc);
	}
};

/*=======================Cgg表单查询按钮===================*/
/** @Bind #buttonQueryCgg.onClick */
!function(self,arg,dataSetTbsProjCgg,dataSetTbsProjundwrt,DialogTbsProjCgg){
	//获取autoformCondition绑定的实体对象
	var entity = dataSetTbsProjundwrt.getData("#").get("tbsProj.id");
	//将实体对象作为参数传入，并异步刷新
	dataSetTbsProjCgg.set("parameter",entity).flushAsync();
	DialogTbsProjCgg.show();
};


//=========flsmgm==========
/*============================单个文件上传=====================*/
/**@Bind #singleUploadAction.onFilesAdded */
!function(self, arg){	
	arg.files.each(function(file){
		if (file.size < 10485760) {  //小于10MB才加入list   10485760
			return;
		}else{
			dorado.MessageBox.alert("文件 【"+file.name+"】 \n 大小超过10MB \n 请重新选择！",{title:"趣博信息科技"});
			quit;
		}
	});
};
/** @Bind #singleUploadAction.onUploadProgress */
!function(arg, aupb) {
	aupb.set("value", arg.total.percent);
};
/** @Bind #singleUploadAction.onFileUploaded */
!function(arg,self,upbutton,dataSetTbsFunFul,resetButton) {
	var info = arg.returnValue;
	var filename = info.fileName;
	upbutton.set("visible",false);
	resetButton.set("visible",true);
	dorado.MessageBox.alert("文件 【"+filename+"】 \n上传成功!",{title:"趣博信息科技"});
	dataSetTbsFunFul.flushAsync();
};

/** @Bind #singleUploadAction.beforeFileUpload */
!function( self,dataSetTbsProjundwrt){
    //设置by1、by2参数
    self.set("parameter", {
    	by1: "projundwrt",
        by2: dataSetTbsProjundwrt.getData("#").get("projSn"),
        fid: "承保审批"
    });
};
/** @Bind #resetButton.onClick */
!function(arg,self,aupb,upbutton){
	aupb.set("value", 0);
	upbutton.set("visible",true);
	self.set("visible",false);
};


/* =======================buttonUpload按钮dialogFlsmgm显示=================== */
/** @Bind #buttonFlsmgm.onClick */
!function(self, arg, dialogFlsmgm,dataSetTbsProjundwrt,dataSetTbsFunFul) {
	var by2 = dataSetTbsProjundwrt.getData("#").get("projSn");
	dataSetTbsFunFul.set("parameter",by2).flushAsync();
	dialogFlsmgm.show();
};

/* ==========flsmgm parameter(sn)=============== */
/** @Bind #tabAttach.onClick */
!function(self, arg, dataSetTbsProjundwrt,dataSetTbsFunFul) {
	var by2 = dataSetTbsProjundwrt.getData("#").get("projSn");
	dataSetTbsFunFul.set("parameter",by2).flushAsync();
};

/* ==============flsmgm validation =================*/
/** @Bind #flsDataPilot.onSubControlAction */
!function(self, arg, dataSetTbsFunFul) {
	var dataset =  dataSetTbsFunFul.getData("#");
	var valid = dataset.get("valid");
	switch (arg.code) {
	case "-":
		arg.processDefault = false;
		if (valid == 0){
			dataset.remove();
		}else{
			dorado.MessageBox.alert("对不起，您要删除的文件已经通过审批！", {
				title : "趣博信息科技"
			});
		}
		break;
	}
};

/** @Bind #fname.onRenderCell */
!function (arg,self){
var fname = arg.data.get("fname");
arg.dom.innerHTML = "<a href='./flsmgm/"+fname+"' target='_blank' title='点击下载'>"+fname+"</a>";
};