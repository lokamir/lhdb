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
     //业务类品金去重
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

/* =======================dialog取消按钮-清空当前数据操作=================== */
/** @Bind #buttonCancel.onClick */
!function(self, arg, dialogMainForm, datasetTbsProj) {
	dialogMainForm.hide();
	datasetTbsProj.get("data:#").cancel();
	 datasetTbsProj.getData("#").get("tbsProjBizvtSet").cancel();
	 datasetTbsProj.getData("#").get("tbsProjBankSet").cancel();
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

/** @Bind #dgVcggall.onDoubleClick */
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
	/*var entityDy = datasetTbsProj.getData("#.#tbsProjCggSet").get("cggStr");
	var entity = datasetTbsProj.getData("#.#tbsProjCggSet");
	var targetData = datasetVcggall.getData();
		var newEntity = {
			sn : entityDy
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
/** @Bind #dgTbsProjCgg.onDoubleClick */
!function(self, arg, datasetVcggall, datasetTbsProj) {
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
    dorado.MessageBox.alert("反担保信息重复，请删除后再确认！",{title:"趣博信息技术"});
   return;
  }else{
 DialogTbsjCgg.hide();
  }
};

/* =======================buttonUpload按钮dialogFlsmgm显示=================== */
/** @Bind #buttonFlsmgm.onClick */
!function(self, arg, dialogFlsmgm,datasetTbsProj,dataSetTbsFunFul) {
	var by2 = datasetTbsProj.getData("#").get("sn");
	dataSetTbsFunFul.set("parameter",by2).flushAsync();
	dialogFlsmgm.show();
};

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
!function( self,datasetTbsProj){
    //设置by1、by2,fid参数
    self.set("parameter", {
    	by1: "projCreate",
        by2: datasetTbsProj.getData("#").get("sn"),
        fid: "项目受理"
    });
};
/** @Bind #resetButton.onClick */
!function(arg,self,aupb,upbutton){
	aupb.set("value", 0);
	upbutton.set("visible",true);
	self.set("visible",false);
};
/* =======================文件下载=================== */
/** @Bind #fname.onRenderCell */
!function (arg,self){
var fname = arg.data.get("fname");
arg.dom.innerHTML = "<a href='./flsmgm/"+fname+"' target='_blank' title='点击下载'>"+fname+"</a>";
};