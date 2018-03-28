// Global variables
var pageid; //page controller
var psid;
var valid;
var docid;
var projid;

/*======获取当前这条记录的状态======*/
function GetCRStatus(ds){
	var CurRec = ds.getData("#");
	docid = CurRec.get("id");
	projid = CurRec.get("id");
	valid = CurRec.get("valid");
	psid=CurRec.get("tbsBasPs.id");
	processInstanceId = CurRec.get("by4");
};

//===PageController===
/** @Bind view.onReady */
!function(self,arg,buttonUflo,HisTaskTab) {
	pageid="${request.getParameter('pageid')}";
    if (pageid == 0){   // 菜单2进入
    	buttonUflo.set("visible",false);
    	HisTaskTab.set("visible",false);
    	if (valid == true) {
    		buttonEdit.set("disabled",true);
    	}else{
    		buttonDel.set("disabled",false);
        	buttonUflo.set("disabled",false);
        	datasetTbsProj.set("readOnly",false);
    	}
    }else if (pageid == 1){   // 菜单4进入
    	buttonUflo.set("visible",true);
    	HisTaskTab.set("visible",true);
    }
};

/*=======控制按钮,通过审批和审批中的禁止修改和删除和再次发起审批===*/
/** @Bind #DataGridTbsProj.onDataRowClick */
/** @Bind #datasetTbsProj.onLoadData */
!function(self,arg,datasetTbsProj,buttonDel,buttonUflo,dataSetHistoryTask){
	GetCRStatus(datasetTbsProj);
    /*if (valid == 1 && psid == 13 ){ //项目通过审批并且合同审核通过，可以进行反担保审批
    	buttonDel.set("disabled",false);
    	buttonUflo.set("disabled",false);
    	datasetTbsProj.set("readOnly",false);
    }else{
    	buttonDel.set("disabled",true);
    	buttonUflo.set("disabled",true);
    	datasetTbsProj.set("readOnly",true);
    }
  //page controller
    if (pageid == 0 && (psid == 1 || psid == 2) ){  
    	datasetTbsProj.set("readOnly",false);
    }*/
  //histask
    dataSetHistoryTask.set("parameter",processInstanceId).flushAsync();
};

/*========审批历史创建时刷新=========*/
/** @Bind #dataSetHisTask.onCreate */
!function(self,arg){ 
	self.flushAsync();
};


/*============autoformCondition快速查询===================*/
/**@Bind @tempConditions.onDataChange */
!function(self,arg,autoformCondition,datasetTbsProj){
var entity = autoformCondition.get("entity");
datasetTbsProj.set("parameter",entity).flushAsync();
};

/*=======================主表单查询按钮===================*/
/** @Bind #buttonQuery.onClick */
!function(self, arg, autoformCondition, datasetTbsProj) {
	// 获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	// 将实体对象作为参数传入，并异步刷新
	datasetTbsProj.set("parameter", entity).flushAsync();
};

/* =======================主表单新增按钮=================== */
/** @Bind #buttonAdd.onClick */
!function(self, arg, dialogMainForm, datasetTbsProj, autoformTbsProj) {
	datasetTbsProj.insert();
	dialogMainForm.show();
	var rq = new Date();
	autoformTbsProj.set("entity.timestampInput", rq);
	autoformTbsProj.set("entity.timestampUpdate", rq);
};

/* =======================主表单修改按钮=================== */
/** @Bind #buttonEdit.onClick */
!function(self, arg, dialogMainForm) {
	dialogMainForm.show();
};

/* =======================主表删除按钮=================== */
/** @Bind #buttonDel.onClick */
!function(self, arg, datasetTbsProj) {
	var entity = datasetTbsProj.get("data:#");
	entity.remove();
};

/* =======================dialog确认按钮及下拉框空值解决方案+查询条件下拉空值解决=================== */

/** @Bind #buttonSave.onClick */
!function(self, arg, dialogMainForm, autoformTbsProj, datasetTbsProj) {

	dialogMainForm.hide();

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
	if (autoformTbsProj.get("entity.tbsCustomer") == "") {
		autoformTbsProj.set("entity.tbsCustomer", null);
	}

	if (autoformTbsProj.get("entity.tbsBasConsway") == "") {
		autoformTbsProj.set("entity.tbsBasConsway", null);
	}

	if (autoformTbsProj.get("entity.tbsBasGovfund") == "") {
		autoformTbsProj.set("entity.tbsBasGovfund", null);
	}

	if (autoformTbsProj.get("entity.tbsBasCurrency") == "") {
		autoformTbsProj.set("entity.tbsBasCurrency", null);
	}

	if (autoformTbsProj.get("entity.bdf2User_A") == "") {
		autoformTbsProj.set("entity.bdf2User_A", null);
	}

	if (autoformTbsProj.get("entity.bdf2User_B") == "") {
		autoformTbsProj.set("entity.bdf2User_B", null);
	}

	if (autoformTbsProj.get("entity.tbsBasPs") == "") {
		autoformTbsProj.set("entity.tbsBasPs", null);
	}
};

/* =======================dialog取消按钮-清空当前数据操作=================== */
/** @Bind #buttonCancel.onClick */
!function(self, arg, dialogMainForm, datasetTbsProj) {
	dialogMainForm.hide();
	datasetTbsProj.get("data:#").cancel();
};

/* ========保存后刷新，执行query相同的方法========= */
/** @Bind #updateActionSave.onSuccess */
!function(self, arg, autoformCondition, datasetTbsProj) {
	// 获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	// 将实体对象作为参数传入，并异步刷新
	datasetTbsProj.set("parameter", entity).flushAsync();
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

///** @Bind #dgVcggall.onDoubleClick */
/*!function(self, arg, datasetVcggall, datasetTbsProj) {
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
};*/
/** @Bind #remove.onClick */
!function(self, arg, datasetVcggall, datasetTbsProj,UIdatagridTbsProjCgg) {
	datasetVcggall.flushAsync();
	var targetData =datasetTbsProj.getData("#").get("tbsProjCggSet");
	targetData.each(function(entity){
		entity.cancel();
	});
};
///** @Bind #dgTbsProjCgg.onDoubleClick */
/*!function(self, arg, datasetVcggall, datasetTbsProj) {
datasetVcggall.flushAsync();
var targetData =datasetTbsProj.getData("#").get("tbsProjCggSet");
targetData.each(function(entity){
	entity.cancel();
});
};*/

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

/*=======================发送审批===================*/
/** @Bind #buttonUflo.onClick */ 
!function(self,arg,datasetTbsProj,DataGridTbsProj,ajaxAction1){
	var entity = DataGridTbsProj.getCurrentEntity("entity");
	var id_value = entity.get("id");
	var catlist = entity.get("tbsProjCggSet");
	var bzjzy_value=0;
	catlist.each(function(name){
		var i=name.get("cggCname");
		if(i.indexOf("保证金质押")>=0){
			bzjzy_value=1;
		};
	});
	var maParamers = {docid:id_value,bzjzy:bzjzy_value}; 
	ajaxAction1.set("parameter",maParamers).execute(function(result){
		dorado.MessageBox.alert(result,{title:"趣博信息科技"});
	});
	datasetTbsProj.flushAsync();
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