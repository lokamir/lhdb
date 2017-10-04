// Global variables
var psid;
var valid;
var docid;
var projid;
var processInstanceId;

/*======获取当前这条记录的状态======*/
function GetCRStatus(ds){
	var CurRec = ds.getData("#");
	docid = CurRec.get("id");
	projid = CurRec.get("tbsProj.id");
	valid = CurRec.get("valid");
	psid=CurRec.get("tbsProj.tbsBasPs.id");
	processInstanceId = CurRec.get("pid");
}

/*=======控制按钮,通过审批和审批中的禁止修改和删除和再次发起审批===*/
/** @Bind #Main.onDataRowClick */
/** @Bind #dataSettbsProjundwrtCfmar.onLoadData */
!function(self,arg,dataSettbsProjundwrtCfmar,buttonDel,buttonUflo,dataSetHistoryTask){
	GetCRStatus(dataSettbsProjundwrtCfmar);
    if (valid==0 && psid == 16){
    	buttonDel.set("disabled",false);
    	buttonUflo.set("disabled",false);
    	dataSettbsProjundwrtCfmar.set("readOnly",false);
    }else{
    	buttonDel.set("disabled",true);
    	buttonUflo.set("disabled",true);
    	dataSettbsProjundwrtCfmar.set("readOnly",true);
    }
  //histask
    dataSetHistoryTask.set("parameter",processInstanceId).flushAsync();
    
    
};

/*=============本次承保金额和日期判断=====crs表示CurrentRecordSets==========*/
/** @Bind #udtfaloc.onPost */
/** @Bind #udtnfaloc.onPost */
/** @Bind #udtotloc.onPost */
!function(self,arg,ddlAutoform,buttonSave,udttotloc,dataSettbsProjundwrtCfmar){
	var crs = ddlAutoform.get("entity"); 
	var dataSet = dataSettbsProjundwrtCfmar.getData("#");
	var udtfaloc = crs.get("udtfaloc"); var udtnfaloc = crs.get("udtnfaloc"); var udtotloc = crs.get("udtotloc");
	var vfaloc = crs.get("tbsProj.vfaloc"); var vnfaloc = crs.get("tbsProj.vnfaloc"); var votloc = crs.get("tbsProj.votloc");	
	if (udtfaloc == 0 && udtnfaloc == 0 && udtotloc == 0 ) {
		buttonSave.set("disabled",true);
		dorado.MessageBox.alert("对不起，【本次承保金额】不能都为 【0.00】 ",{title:"趣博信息科技"});
	}else if (udtfaloc > vfaloc || udtnfaloc > vnfaloc || udtotloc > votloc){
		buttonSave.set("disabled",true);
		dorado.MessageBox.alert("对不起，您输入的【本次承保金额】 大于【当前可用授信额度】，请重新录入！",{title:"趣博信息科技"});
	}else{
		buttonSave.set("disabled",false);
	    dataSet.set("udttotloc",udtfaloc+udtnfaloc+udtotloc);
	};
};

/** @Bind #undbdate.onPost */
/** @Bind #by3.onPost */
!function(self,arg,ddlAutoform,buttonSave,udttotloc,dataSettbsProjundwrtCfmar){
	var crs = ddlAutoform.get("entity"); 
	var bdate = crs.get("bdate"); var edate = crs.get("edate");
	var undbdate = crs.get("undbdate"); //var undedate = crs.get("undedate");
	var by3 = crs.get("by3");
	if (undbdate < bdate || undbdate > edate){  //undedate < bdate || undedate > edate
		dorado.MessageBox.alert("对不起，您输入的项目【承保开始日期】范围， 超出了【授信日期】的范围，请重新录入！",{title:"趣博信息科技"});
		buttonSave.set("disabled",true);
	}else if (by3 <= 0){
		dorado.MessageBox.alert("对不起，【承保期限（月）】必须输入一个大于零的数字！",{title:"趣博信息科技"});
		buttonSave.set("disabled",true);
	}else {
		buttonSave.set("disabled",false);
	};
};

/*========审批历史创建时刷新=========*/
/** @Bind #dataSetHistoryTask.onCreate */
!function(self,arg){ 
self.flushAsync();
};

/*=======================dialog确认按钮及下拉框空值解决方案===================*/
/** @Bind #buttonSave.onClick */
!function(self,arg,dialogEdit,updateAction,dataSettbsProjundwrtCfmar){
	//updateAction.execute();
	//dataSettbsProjundwrtCfmar.flushAsync();
	dialogEdit.hide();

};

/*=======================dialog取消按钮-清空当前数据操作===================*/
/** @Bind #buttonCancel.onClick */
!function(self,arg,dialogEdit,ddlAutoform,dataSettbsProjundwrtCfmar){
	dialogEdit.hide();
	dataSettbsProjundwrtCfmar.get("data:#").cancel();
};

/*============autoformCondition快速查询===================*/
/**@Bind @dataTypeConditions.onDataChange */
!function(self,arg,autoformCondition,dataSettbsProjundwrtCfmar){
var entity = autoformCondition.get("entity");
dataSettbsProjundwrtCfmar.set("parameter",entity).flushAsync();
};

/*=======================主表单查询按钮===================*/
/** @Bind #buttonQuery.onClick */
!function(self,arg,autoformCondition,dataSettbsProjundwrtCfmar){
	//获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	//将实体对象作为参数传入，并异步刷新
	dataSettbsProjundwrtCfmar.set("parameter",entity).flushAsync();
};

/*=======================主表删除按钮===================*/
/** @Bind #buttonDel.onClick */
!function(self,arg,dataSettbsProjundwrtCfmar){
	var entity = dataSettbsProjundwrtCfmar.get("data:#");
	entity.remove();
};

/*=======================主表单修改按钮===================*/
/** @Bind #buttonEdit.onClick */
!function(self,arg,dialogEdit){
	dialogEdit.show();
};

/*========保存后刷新，执行query相同的方法=========*/
/**@Bind #updateAction.onSuccess */
!function(self,arg,autoformCondition,dataSettbsProjundwrtCfmar){ 
//获取autoformCondition绑定的实体对象
var entity = autoformCondition.get("entity");
//将实体对象作为参数传入，并异步刷新
dataSettbsProjundwrtCfmar.set("parameter",entity).flushAsync();
};

/*=======================Cgg表单查询按钮===================*/
/** @Bind #buttonQueryCgg.onClick */
!function(self,arg,dataSetTbsProjCgg,dataSettbsProjundwrtCfmar,DialogTbsProjCgg){
	//获取autoformCondition绑定的实体对象
	var entity = dataSettbsProjundwrtCfmar.getData("#").get("tbsProj.id");
	//将实体对象作为参数传入，并异步刷新
	dataSetTbsProjCgg.set("parameter",entity).flushAsync();
	DialogTbsProjCgg.show();
};

/*=======================发送审批===================*/
/** @Bind #buttonUflo.onClick */ 
!function(self,arg,dataSettbsProjundwrtCfmar,Main,ajaxAction1){
	var entity = Main.getCurrentEntity("entity");
	var id_value = entity.get("id");
	var maParamers = {docid:id_value}; 
	ajaxAction1.set("parameter",maParamers).execute(function(result){
		dorado.MessageBox.alert(result,{title:"趣博信息科技"});
	});
	dataSettbsProjundwrtCfmar.flushAsync();
}; 

/**
 * 文件处理 开始
 */

/** @Bind #tabAttach.onClick */
!function(self,arg,iFrameAttach,dataSettbsProjundwrtCfmar){
	var title = "projundwrtCfmar";
	var projsn = dataSettbsProjundwrtCfmar.getData("#").get("projSn");
	var fid ="承保补录";
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
/** @Bind #dgTbsProjCgg.onDataRowDoubleClick */
!function() {
	var path = "";
	var entity = view.get("#dgTbsProjCgg").getCurrentEntity("entity").get("cggStr");
	var cggSn = view.get("#dgTbsProjCgg").getCurrentEntity("entity").get("cggSn");
	var cat1name;
	var cat2name;
	var cat3name;
	var customerId = view.get("#dataSettbsProjundwrtCfmar").getData("#.tbsProj").get("tbsCustomer").get("id");;
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