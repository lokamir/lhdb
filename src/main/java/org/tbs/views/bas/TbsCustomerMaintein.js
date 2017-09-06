//打开参考文件
/** @Bind #buttonRef.onClick */
!function(self,arg){
	window.open("./flsmgm/sys/cus.pdf");
};


/*=======================dialog确认按钮及下拉框空值解决方案+查询条件下拉空值解决===================*/
/** @Bind #buttonQuery.onClick */
!function(self,arg,autoformCondition){
	//autoformCondition去空
	if(autoformCondition.get("entity.tbsBasArea") == ""){
		autoformCondition.set("entity.tbsBasArea",null);
	}

	if (autoformCondition.get("entity.tbsBasEntproperty") == ""){
		autoformCondition.set("entity.tbsBasEntproperty",null);
	}

	if(autoformCondition.get("entity.tbsBasEntscale") == ""){
		autoformCondition.set("entity.tbsBasEntscale",null);
	}

	if (autoformCondition.get("entity.tbsBasIndutype") == ""){
		autoformCondition.set("entity.tbsBasIndutype",null);
	}
	
};

/** @Bind #buttonSave.onClick */
!function(self,arg,dialogEdit,ddlAutoform,dataSetCustomer){
	dialogEdit.hide();
	//datagrid的去空
	var TbsBasIdcardtype =dataSetCustomer.getData("#").get("tbsCustomerIdnumSet");
	TbsBasIdcardtype.each(function(entity){
		if(entity.get("tbsBasIdcardtype")==""){
		entity.set("tbsBasIdcardtype",null);
		entity.set("idnum",null);
		}
	});
	
	var TbsBasEnttype =dataSetCustomer.getData("#").get("tbsCustomerEnttypeSet");
	TbsBasEnttype.each(function(entity){
		if(entity.get("tbsBasEnttype")==""){
		entity.set("tbsBasEnttype",null);
		}
	});
	//autoform去空
	if(ddlAutoform.get("entity.tbsBasArea") == ""){
		ddlAutoform.set("entity.tbsBasArea",null);
	}

	if (ddlAutoform.get("entity.tbsBasEntproperty") == ""){
		ddlAutoform.set("entity.tbsBasEntproperty",null);
	}

	if(ddlAutoform.get("entity.tbsBasEntscale") == ""){
		ddlAutoform.set("entity.tbsBasEntscale",null);
	}

	if (ddlAutoform.get("entity.tbsBasIndutype") == ""){
		ddlAutoform.set("entity.tbsBasIndutype",null);
	}

  	
};

/*=======================dialog取消按钮-清空当前数据操作===================*/
/** @Bind #buttonCancel.onClick */
!function(self,arg,dialogEdit,ddlAutoform,dataSetCustomer){
	dialogEdit.hide();
	dataSetCustomer.get("data:#").cancel();
};

/*=======================主表单查询按钮===================*/
/** @Bind #buttonQuery.onClick */
!function(self,arg,autoformCondition,dataSetCustomer){
	//获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	//将实体对象作为参数传入，并异步刷新
	dataSetCustomer.set("parameter",entity).flushAsync();
};

/*=======================主表单新增按钮===================*/
/** @Bind #buttonAdd.onClick */
!function(self,arg,dialogEdit,dataSetCustomer,ddlAutoform){
	dataSetCustomer.insert();
	dialogEdit.show();
	var rq = new Date();
	ddlAutoform.set("entity.timestampInput",rq);
	ddlAutoform.set("entity.timestampUpdate",rq);
};

/*=======================主表单修改按钮===================*/
/** @Bind #buttonEdit.onClick */
!function(self,arg,dialogEdit,dataSetCustomer){
	dialogEdit.show();
};

/*=======================主表删除按钮===================*/
/** @Bind #buttonDel.onClick */
!function(self,arg,dataSetCustomer){
	var entity = dataSetCustomer.get("data:#");
	entity.remove();
};

/*=======================主表刷新按钮===================*/
/** @Bind #buttonRefresh.onClick */
!function(self,arg,dataSetCustomer,autoformCondition){
	dataSetCustomer.flush();
};

/*========保存后刷新，执行query相同的方法=========*/
/**@Bind #updateActionSave.onSuccess */
!function(self,arg,autoformCondition,dataSetCustomer){ 
	//获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	//将实体对象作为参数传入，并异步刷新
	dataSetCustomer.set("parameter",entity).flushAsync();
};