// Global variables
var psid;
var valid;
var docid;
var projid;
var docsn;
var loginUser = "${loginUsername}";
var by1;
var processInstanceId;

/*======获取当前这条记录的状态======*/
function GetCRStatus(ds){
	var CurRec = ds.getData("#");
	docid = CurRec.get("id");
	projid = CurRec.get("tbsProj.id");
	valid = CurRec.get("valid");
	psid=CurRec.get("tbsProj.tbsBasPs.id");
	docsn=CurRec.get("sn");
	by1=CurRec.get("by1");
	processInstanceId = CurRec.get("pid");
}

/*=======控制按钮,通过审批和审批中的禁止修改和删除和再次发起审批===*/
/** @Bind #Main.onDataRowClick */
/** @Bind #dataSetTbsProjundwrt.onLoadData */
!function(self,arg,dataSetTbsProjundwrt,buttonDel,buttonUflo,buttonAdminsign,buttonUfloN,dataSetTbsFunFul,upbutton,dataSetHistoryTask){
	GetCRStatus(dataSetTbsProjundwrt);
    if (valid == 0 && psid == 23 ){
    	buttonDel.set("disabled",false);
    	buttonUflo.set("disabled",false);
    	dataSetTbsProjundwrt.set("readOnly",false); 
    	dataSetTbsFunFul.set("readOnly",false); 
    	upbutton.set("disabled",false);
    }else{
    	buttonDel.set("disabled",true);
    	buttonUflo.set("disabled",true);
    	dataSetTbsProjundwrt.set("readOnly",true);
    	dataSetTbsFunFul.set("readOnly",true);
    	upbutton.set("disabled",true);
    }
   
    if (valid == 1 && (psid == 18 || psid == 16) ){  //再次承保按钮
    	buttonUfloN.set("disabled",false);
    }else{
    	buttonUfloN.set("disabled",true);
    }

    if (valid == 0 && psid == 38 ){  // 再次承保单生成
    	buttonDel.set("disabled",false);
    	buttonUflo.set("disabled",false);
    	dataSetTbsProjundwrt.set("readOnly",false); 
    	dataSetTbsFunFul.set("readOnly",false); 
    	upbutton.set("disabled",false);
    }
    
    if ( psid == 9 || psid == 20 ){ //再次承保中
    	buttonDel.set("disabled",true);
    	buttonUflo.set("disabled",true);
    	dataSetTbsProjundwrt.set("readOnly",true);
    	dataSetTbsFunFul.set("readOnly",true);
    	upbutton.set("disabled",true);
    }
   
    //histask
    dataSetHistoryTask.set("parameter",processInstanceId).flushAsync();
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
		dorado.MessageBox.alert("对不起，您输入的【本次承保金额】 大于【当前可用授信额度】，请重新录入！",{title:"趣博信息技术"});
		buttonResend.set("disabled",true);		
	}else if (undbdate < bdate || undbdate > edate){  //undedate < bdate || undedate > edate
		dorado.MessageBox.alert("对不起，您输入的项目【承保开始日期】范围， 超出了【授信日期】的范围，请重新录入！",{title:"趣博信息技术"});
		buttonResend.set("disabled",true);
	}else if (by3 <= 0){
		dorado.MessageBox.alert("对不起，【承保期限（月）】必须输入一个大于零的数字！",{title:"趣博信息技术"});
		buttonResend.set("disabled",true);
	}
	else{
		buttonResend.set("disabled",false);
	    dataSet.set("apptotloc",appfaloc+appnfaloc+appotloc);
	}
};

/*========审批历史创建时刷新=========*/
/** @Bind #dataSetHistoryTask.onCreate */
!function(self,arg){ 
self.flushAsync();
};

/*=======================dialog确认按钮===================*/
/** @Bind #buttonSave.onClick */
!function(self,arg,dialogEdit,dataSetTbsProjundwrt,updateAction){
	updateAction.execute();
	dataSetTbsProjundwrt.flushAsync();
	dialogEdit.hide();
};

/*=======================dialog取消按钮-清空当前数据操作===================*/
/** @Bind #buttonCancel.onClick */
!function(self,arg,dialogEdit,ddlAutoform,dataSetTbsProjundwrt){
	dialogEdit.hide();
	dataSetTbsProjundwrt.get("data:#").cancel();
};

/*============autoformCondition快速查询===================*/
/**@Bind @dataTypeConditions.onDataChange */
!function(self,arg,autoformCondition,dataSetTbsProjundwrt){
var entity = autoformCondition.get("entity");
dataSetTbsProjundwrt.set("parameter",entity).flushAsync();
};

/*=======================主表单查询按钮===================*/
/** @Bind #buttonQuery.onClick */
!function(self,arg,autoformCondition,dataSetTbsProjundwrt){
	//获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	//将实体对象作为参数传入，并异步刷新
	dataSetTbsProjundwrt.set("parameter",entity).flushAsync();
};

/*=======================主表删除按钮===================*/
/** @Bind #buttonDel.onClick */
!function(self,arg,dataSetTbsProjundwrt){
	var entity = dataSetTbsProjundwrt.get("data:#");
	entity.remove();
};

/*=======================主表单修改按钮===================*/
/** @Bind #buttonEdit.onClick */
!function(self,arg,dialogEdit,tabMain){
	dialogEdit.show();
	tabMain.set("currentIndex", 0);
};

/*========保存后刷新，执行query相同的方法=========*/
/**@Bind #updateAction.onSuccess */
!function(self,arg,autoformCondition,dataSetTbsProjundwrt){ 
//获取autoformCondition绑定的实体对象
var entity = autoformCondition.get("entity");
//将实体对象作为参数传入，并异步刷新
dataSetTbsProjundwrt.set("parameter",entity).flushAsync();
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



/* =======================buttonUpload按钮dialogFlsmgm显示=================== 
*//** @Bind #buttonFlsmgm.onClick *//*
!function(self, arg, dialogFlsmgm,dataSetTbsProjundwrt,dataSetTbsFunFul) {
	var by2 = dataSetTbsProjundwrt.getData("#").get("projSn");
	dataSetTbsFunFul.set("parameter",by2).flushAsync();
	dialogFlsmgm.show();
};*/

/*============================单个文件上传=====================*/
/**@Bind #singleUploadAction.onFilesAdded */
!function(self, arg){	
	arg.files.each(function(file){
		if (file.size < 10485760) {  //小于10MB才加入list   10485760
			return;
		}else{
			dorado.MessageBox.alert("文件 【"+file.name+"】 \n 大小超过10MB \n 请重新选择！",{title:"趣博信息技术"});
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
	dorado.MessageBox.alert("文件 【"+filename+"】 \n上传成功!",{title:"趣博信息技术"});
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
				title : "趣博信息技术"
			});
		}
		break;
	}
};

/* =======================文件下载=================== */
/** @Bind #fname.onRenderCell */
!function(arg, self) {
	var fname = arg.data.get("fname");
	var url = arg.data.get("url"); 
	arg.dom.innerHTML = "<a href="+url+" target='_blank' title='点击下载'>"+fname+ "</a>";
};

/*=======================发送审批===================*/
/** @Bind #buttonUflo.onClick */ 
!function(self,arg,dataSetTbsProjundwrt,Main,ajaxAction1){
	var entity = Main.getCurrentEntity("entity");
	var id_value = entity.get("id");
	var maParamers = {docid:id_value}; 
	ajaxAction1.set("parameter",maParamers).execute(function(result){
		dorado.MessageBox.alert(result,{title:"趣博信息技术"});
	});
	dataSetTbsProjundwrt.flushAsync();
}; 


/*=======再承保 start=======*/
/** @Bind #buttonUfloN.onClick */
!function(self,arg,dataSetTbsProjundwrt,ajaxUfloN,dialogUfloN){
	if (docsn){
	dorado.MessageBox.confirm("此操作将产生一张新的【承保审批单】\n您确定要再次承保该项目吗？",
			{title:"趣博信息技术",
			 detailCallback:function(btnID, text)
			 { if (btnID == "yes")
			 	{
				 var maParamers = {projid:projid,loginUser:loginUser,docsn:docsn};
				 ajaxUfloN.set("parameter",maParamers).execute(function(result){ 
					 											var Ndocsn = result.split(":")[1];
					 											dorado.MessageBox.alert("新的【承保审批单】已产生\n单据号：【"+Ndocsn+"】\n请补齐信息后【提交审批】",{title:"趣博信息技术"});
				 																});
				 dataSetTbsProjundwrt.flushAsync();
			 	}
			 } 	
			}
	);
	};
};

/*=======再承保 end=======*/

