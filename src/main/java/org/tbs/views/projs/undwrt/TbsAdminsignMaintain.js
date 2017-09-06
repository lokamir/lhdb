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
}

/*=======控制按钮,通过审批和审批中的禁止修改和删除和再次发起审批===*/
/** @Bind #Main.onDataRowClick */
/** @Bind #dataSetTbsAdminsign.onLoadData */
!function(self,arg,dataSetTbsAdminsign,buttonDel,buttonUflo,dataSetHistoryTask){
	GetCRStatus(dataSetTbsAdminsign);
    if (valid==0 && psid == 28){
    	buttonDel.set("disabled",false);
    	buttonUflo.set("disabled",false);
    	dataSetTbsAdminsign.set("readOnly",false);
    }else{
    	buttonDel.set("disabled",true);
    	buttonUflo.set("disabled",true);
    	dataSetTbsAdminsign.set("readOnly",true);
    }
  //histask
    dataSetHistoryTask.set("parameter",processInstanceId).flushAsync();
};

/*========审批历史创建时刷新=========*/
/** @Bind #dataSetHistoryTask.onCreate */
!function(self,arg){ 
self.flushAsync();
};

/*=======================dialog确认按钮及下拉框空值解决方案===================*/
/** @Bind #buttonSave.onClick */
!function(self,arg,dialogEdit,dataSetTbsAdminsign){
	dialogEdit.hide();
	};

	/*=======================dialog取消按钮-清空当前数据操作===================*/
	/** @Bind #buttonCancel.onClick */
	!function(self,arg,dialogEdit,ddlAutoform,dataSetTbsAdminsign){
		dialogEdit.hide();
		dataSetTbsAdminsign.get("data:#").cancel();
	};
	
	/*============autoformCondition快速查询===================*/
	/**@Bind @tempConditions.onDataChange */
	!function(self,arg,autoformCondition,dataSetTbsAdminsign){
	var entity = autoformCondition.get("entity");
	dataSetTbsAdminsign.set("parameter",entity).flushAsync();
	};
	
	/*=======================主表单查询按钮===================*/
	/** @Bind #buttonQuery.onClick */
	!function(self,arg,autoformCondition,dataSetTbsAdminsign){
		//获取autoformCondition绑定的实体对象
		var entity = autoformCondition.get("entity");
		//将实体对象作为参数传入，并异步刷新
		dataSetTbsAdminsign.set("parameter",entity).flushAsync();
	};

	/*=======================主表删除按钮===================*/
	/** @Bind #buttonDel.onClick */
	!function(self,arg,dataSetTbsAdminsign){
		var entity = dataSetTbsAdminsign.get("data:#");
		entity.remove();
	};

	/*=======================主表单修改按钮===================*/
	/** @Bind #buttonEdit.onClick */
	!function(self,arg,dialogEdit){
		dialogEdit.show();
	};
	
	/*========保存后刷新，执行query相同的方法=========*/
	/**@Bind #updateAction.onSuccess */
	!function(self,arg,autoformCondition,dataSetTbsAdminsign){ 
	//获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	//将实体对象作为参数传入，并异步刷新
	dataSetTbsAdminsign.set("parameter",entity).flushAsync();
	};

	/*=======================发送审批===================*/
	/** @Bind #buttonUflo.onClick */ 
	!function(self,arg,dataSetTbsAdminsign,ajaxAction1){
		var entity = dataSetTbsAdminsign.getData("#");
		var id_value = entity.get("id");
		var maParamers = {docid:id_value}; 
		ajaxAction1.set("parameter",maParamers).execute(function(result){
			dorado.MessageBox.alert(result,{title:"趣博信息科技"});
		});
		dataSetTbsAdminsign.flushAsync();
	}; 