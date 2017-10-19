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
	processInstanceId = CurRec.get("by3");
};

/*=======控制按钮,通过审批和审批中的禁止修改和删除和再次发起审批===*/
/** @Bind #Main.onDataRowClick */
/** @Bind #dataSetTbsProjHtsh.onLoadData */
!function(self,arg,dataSetTbsProjHtsh,buttonDel,buttonStartAppr,dataSetHistoryTask){
	GetCRStatus(dataSetTbsProjHtsh);
    if (valid == 0 && psid == 10 ){
    	buttonDel.set("disabled",false);
    	buttonStartAppr.set("disabled",false);
    	dataSetTbsProjHtsh.set("readOnly",false);
    }else{
    	buttonDel.set("disabled",true);
    	buttonStartAppr.set("disabled",true);
    	dataSetTbsProjHtsh.set("readOnly",true);
    }    
    //histask
    dataSetHistoryTask.set("parameter",processInstanceId).flushAsync();
};


/*=======================发送审批===================*/
/** @Bind #buttonStartAppr.onClick */ 
!function(self,arg,dataSetTbsProjHtsh,Main,ajaxAction1){
	if(dataSetTbsProjHtsh.getData("#.id")){
		var entity = dataSetTbsProjHtsh.getData("#");
	
		var id_value = entity.get("id");
		var maParamers = {docid:id_value}; 
		ajaxAction1.set("parameter",maParamers).execute(function(result){
			dorado.MessageBox.alert(result,{title:"趣博信息科技"});
		});
		dataSetTbsProjHtsh.flushAsync();
	}else{
		dorado.MessageBox.alert("请先保存",{title:"趣博信息科技"});
	}
};


/*========审批历史创建时刷新=========*/
/** @Bind #dataSetHistoryTask.onCreate */
!function(self,arg){ 
	self.flushAsync();
};


/*=======================dialog确认按钮及下拉框空值解决方案===================*/
/** @Bind #buttonSave.onClick */
!function(self,arg,dialogEdit,dataSetTbsProjHtsh){
	dialogEdit.hide();
	};

	/*=======================dialog取消按钮-清空当前数据操作===================*/
	/** @Bind #buttonCancel.onClick */
	!function(self,arg,dialogEdit,ddlAutoform,dataSetTbsProjHtsh){
		dialogEdit.hide();
		dataSetTbsProjHtsh.get("data:#").cancel();
	};
	/*=======================主表单查询按钮===================*/
	/** @Bind #buttonQuery.onClick */
	!function(self,arg,autoformCondition,dataSetTbsProjHtsh){
		//获取autoformCondition绑定的实体对象
		var entity = autoformCondition.get("entity");
		//将实体对象作为参数传入，并异步刷新
		dataSetTbsProjHtsh.set("parameter",entity).flushAsync();	    
	};

	/*=======================主表单新增按钮===================*/
	/** @Bind #buttonAdd.onClick */
	!function(self,arg,dialogEdit,dataSetTbsProjHtsh,ddlAutoform){
		dataSetTbsProjHtsh.set("readOnly",false);
		dataSetTbsProjHtsh.insert();
		dialogEdit.show();
	};

	/*=======================主表删除按钮===================*/
	/** @Bind #buttonDel.onClick */
	!function(self,arg,dataSetTbsProjHtsh){
		var entity = dataSetTbsProjHtsh.get("data:#");
		entity.remove();
	};

	/*=======================主表单修改按钮===================*/
	/** @Bind #buttonEdit.onClick */
	!function(self,arg,dialogEdit){
		dialogEdit.show();
	};

	/*========保存后刷新，执行query相同的方法=========*/
	/**@Bind #updateAction.onSuccess */
	!function(self,arg,autoformCondition,dataSetTbsProjHtsh){ 
	//获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	//将实体对象作为参数传入，并异步刷新
	dataSetTbsProjHtsh.set("parameter",entity).flushAsync();
	};
	
	/** @Bind #dataGridHis.onDataRowDoubleClick */
	!function(self) {
		var path = "org.tbs.views.funs.MyTbsFunApprc.d?id=";
		var taskHisId = self.getCurrentItem().get("id");
		view.get("#DialogTbsFunApprc").show();
		view.get("#iFrameTbsFunApprc").set("path", path+taskHisId);
	};
