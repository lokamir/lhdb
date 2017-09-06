// Global variables
var psid;
var valid;
var docid;
var projid;

/*======获取当前这条记录的状态======*/
function GetCRStatus(ds){
	var CurRec = ds.getData("#");
	/*docid = CurRec.get("id");
	projid = CurRec.get("tbsProj.id");*/
	valid = CurRec.get("valid");
	psid=CurRec.get("tbsBasPs.id");
}

/** @Bind #DataGridTbsProj.onDataRowClick */
/** @Bind #datasetTbsProj.onLoadData */
!function(self,arg,datasetTbsProj,btnDel){
	GetCRStatus(datasetTbsProj);
    if (psid<2 && valid==0){
    	btnDel.set("visible",true);
    }else{
    	btnDel.set("visible",false);
    }
};


/*=======================主表单New按钮===================*/
/** @Bind #btnAdd.onClick */
!function(self,arg,iFrameDetails,dialogMainForm,datasetTbsProj){
	dialogMainForm.show();
	
	var path="org.tbs.views.projs.creation.TbsProjDetails.d?psid=0&fromAppr=0";
	if(path){
		iFrameDetails.set("path",path);
	}
};

/*=======================主表单Details按钮===================*/
/** @Bind #btnDetails.onClick */
!function(self,arg,iFrameDetails,dialogMainForm,datasetTbsProj){
	
	var data = datasetTbsProj.getData("#");
	if(data) {
		var id = data.get("id");
		var psid = data.get("tbsBasPs").get("id");
		var sn = data.get("sn");
	}
	var processInstanceId = data.get("by3");
	var path="org.tbs.views.projs.creation.TbsProjDetails.d?id=" + id
	+ "&psid=" + psid + "&fromAppr=0&sn=" + sn +
	"&processInstanceId=" + processInstanceId +
	"&dt=" + new Date();
	if(id){
		if(path){
			dialogMainForm.show();
			iFrameDetails.set("path",path);
		}
	} else {
		dorado.MessageBox.alert("请先选择一个项目！",{title:"趣博信息技术"});
	}
};

/*=======================主表单查询按钮===================*/
/** @Bind #btnQuery.onClick */
!function(self, arg, autoformCondition, datasetTbsProj) {
	// 获取autoformCondition绑定的实体对象
	var condition = autoformCondition.get("entity");
	// 将实体对象作为参数传入，并异步刷新
	datasetTbsProj.set("parameter", condition).flushAsync();
};

/* =======================主表单新增按钮=================== */ 
/** @Bind #btnAdd.onClick */
!function(self, arg, dialogMainForm, datasetTbsProj) {
	datasetTbsProj.insert();
	dialogMainForm.show();
};

/* =======================主表删除按钮=================== */
/** @Bind #btnDel.onClick */
!function(self, arg, datasetTbsProj,updateActionSave) 
	{
	var entity = datasetTbsProj.get("data:#");
	dorado.MessageBox.confirm("您真的要删除此项目吗？\n此操作无法恢复！",
			{title:"趣博信息技术",
			 detailCallback:function(btnID, text){
			 									  if (btnID == "yes"){
			  								                         entity.remove();
			  								                         updateActionSave.execute();
				                                                    }
												  } 	
			}
	);
};

/* =======================dialog确认按钮及下拉框空值解决方案+查询条件下拉空值解决=================== */
/** @Bind #buttonSave.onClick */
!function(self, arg, dialogMainForm, autoformTbsProj, datasetTbsProj) {
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

	 var targetData =datasetTbsProj.getData("#").get("tbsProjBizvtSet");
	 var targetData2 =datasetTbsProj.getData("#").get("tbsProjBizvtSet");
	 var i=0,j=0;
	 targetData.each(function(entity){
	  if(entity.get("tbsBasBizvar.id")){
	   j++;
	  };
	  targetData2.each(function(entity2){
	  if(entity.get("tbsBasBizvar.id")==entity2.get("tbsBasBizvar.id")){
	   i++;
	  }
	 });
	 });
	  if(i>j){
	    dorado.MessageBox.alert("业务类型和品种有重复，请删除后再确认！",{title:"趣博信息技术"});
	   return;
	  }else{
		  dialogMainForm.hide();
	  }
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


//=========双击显示反担保详细信息==========
/** @Bind #UIdatagridTbsProjCgg.onDataRowDoubleClick */
!function() {
	var path = "";
	var entity = view.get("#UIdatagridTbsProjCgg").getCurrentEntity("entity").get("cggStr");
	var cggSn = view.get("#UIdatagridTbsProjCgg").getCurrentEntity("entity").get("cggSn");
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

/*=======控制按钮,通过审批和审批中的禁止修改和删除和再次发起审批===*/
/** @Bind #DataGridTbsProj.onDataRowClick */
/** @Bind #datasetTbsProj.onLoadData */
!function(self,arg,buttonCheck,datasetTbsProj){
	var psid = datasetTbsProj.getData("#").get("tbsBasPs.id");
	if(psid == 99){
		buttonCheck.set("disabled",false);
    }else{
    	buttonCheck.set("disabled",true);
	}
};
	

/*=======老项目审核=======*/
/** @Bind #buttonCheck.onClick */
!function(self,arg,datasetTbsProj,ajaxCheck,dialogUfloN){
	var projid =datasetTbsProj.getData("#").get("id");
	if (projid){
	dorado.MessageBox.confirm("请确认老项目已审核完毕",
			{title:"趣博信息科技",
			 detailCallback:function(btnID, text)
			 { if (btnID == "yes")
			 	{
				 var maParamers = {projid:projid};
				 ajaxCheck.set("parameter",maParamers).execute(function(result){ 
					 											//var Ndocsn = result.split(":")[1];
					 											dorado.MessageBox.alert("审核成功",{title:"趣博信息科技"});
				 																});
				 datasetTbsProj.flushAsync();
			 	}
			 } 	
			}
	);
	};
};

/*=======老项目审核=======*/