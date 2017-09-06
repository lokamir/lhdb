/*=======================dialog确认按钮及下拉框空值解决方案===================*/
/** @Bind #buttonSave.onClick */
!function(self,arg,dialogEdit,dataSetProjsfa){
	dialogEdit.hide();
	//datagrid的去空
	var TbsProjsfaBizvtSet =dataSetProjsfa.getData("#").get("tbsProjsfaBizvtSet");
	TbsProjsfaBizvtSet.each(function(entity){
		if(entity.get("tbsBasBiztype")==""){
			entity.set("tbsBasBiztype",null);}
		if(entity.get("tbsBasBizvar")==""){
			entity.set("tbsBasBizvar",null);
		}
	});
	//补上时间戳
	var rq = new Date();
	var loginUsername="${loginUser.getCname()}";
	var TbsProjsfaOpinionSet=dataSetProjsfa.getData("#").get("tbsProjsfaOpinionSet");
	TbsProjsfaOpinionSet.each(function(entity){
		if(entity.get("timestampInput")==null){
			entity.set("timestampInput",rq);
		}
		entity.set("timestampUpdate",rq);
		entity.set("VOcname",loginUsername);
	});
};

	/*=======================dialog取消按钮-清空当前数据操作===================*/
	/** @Bind #buttonCancel.onClick */
	!function(self,arg,dialogEdit,ddlAutoform,dataSetProjsfa){
		dialogEdit.hide();
		dataSetProjsfa.get("data:#").cancel();
	};
	/*=======================主表单查询按钮===================*/
	/** @Bind #buttonQuery.onClick */
	!function(self,arg,autoformCondition,dataSetProjsfa){
		//获取autoformCondition绑定的实体对象
		var entity = autoformCondition.get("entity");
		//将实体对象作为参数传入，并异步刷新
		dataSetProjsfa.set("parameter",entity).flushAsync();
	};

	
	/*=======================主表单修改按钮===================*/
	/** @Bind #buttonEdit.onClick */
	!function(self,arg,dialogEdit){
		dialogEdit.show();
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
	