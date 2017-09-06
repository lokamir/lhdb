/*=======================dialog确认按钮及下拉框空值解决方案===================*/
/** @Bind #buttonSave.onClick */
!function(self,arg,dialogEdit,ddlAutoform){
	dialogEdit.hide();
};

/*=======================dialog取消按钮-清空当前数据操作===================*/
/** @Bind #buttonCancel.onClick */
!function(self,arg,dialogEdit,ddlAutoform,dataSetTbsProjeaa){
	dialogEdit.hide();
	dataSetTbsProjeaa.get("data:#").cancel();
};

/*=======================主表单查询按钮===================*/
/** @Bind #buttonQuery.onClick */
!function(self,arg,autoformCondition,dataSetTbsProjeaa){
	//获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	//将实体对象作为参数传入，并异步刷新
	dataSetTbsProjeaa.set("parameter",entity).flushAsync();
};

/*=======================主表删除按钮===================*/
/** @Bind #buttonDel.onClick */
!function(self,arg,dataSetTbsProjeaa){
	var entity = dataSetTbsProjeaa.get("data:#");
	entity.remove();
	updateactionSave.execute();
};

/*=======================主表单修改按钮===================*/
/** @Bind #buttonEdit.onClick */
!function(self,arg,dialogEdit){
	dialogEdit.show();
};

/*========保存后刷新，执行query相同的方法=========*/
/**@Bind #updateAction.onSuccess */
!function(self,arg,autoformCondition,dataSetTbsProjeaa){ 
	//获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	//将实体对象作为参数传入，并异步刷新
	dataSetTbsProjeaa.set("parameter",entity).flushAsync();
};

/*=======================valid的key-vlaue===================*/
/** @Bind #autoformCondition.onReady */
!function(self,arg,text){
	text.set("mapping", [
{key:"2",
	value:"全部"},
    {        key : "1",
	        value : "是"    },
	    {        key : "0",
		        value : "否"    }]);
};
