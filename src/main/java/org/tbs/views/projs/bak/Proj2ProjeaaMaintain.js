/*=======================主表单查询按钮===================*/
/** @Bind #buttonQuery.onClick */
!function(self,arg,autoformCondition,datasetTbsProj){
	//获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	//将实体对象作为参数传入，并异步刷新
	datasetTbsProj.set("parameter",entity).flushAsync();
};

/*=======================主表单修改按钮===================*/
/** @Bind #buttonEdit.onClick */
!function(self,arg,dialogMainForm){
	dialogMainForm.show();
};

/*=======================dialog取消按钮-清空当前数据操作===================*/
/** @Bind #buttonCancel.onClick */
!function(self,arg,dialogMainForm,datasetTbsProj){
	dialogMainForm.hide();
	datasetTbsProj.get("data:#").cancel();
};

/*=======================生成新单据按钮===================*/
/** @Bind #buttonCreation.onClick */ 
!function(self,arg,datasetTbsProj,DataGridTbsProj,ajaxAction1){
	var entity = DataGridTbsProj.getCurrentEntity("entity");
	var id = entity.get("id"); 	var name = entity.get("projName");
	var maParamers = {id:id,name:name};
	ajaxAction1.set("parameter",maParamers).execute(function(result){
		dorado.MessageBox.alert(result,{title:"趣博信息技术"});
	});
	datasetTbsProj.flushAsync();
}; 