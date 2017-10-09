/*=======================主表单查询按钮===================*/
/** @Bind #btnQuery.onClick */
!function(self, arg, autoformCondition, dataSetTbsProj) {
	// 获取autoformCondition绑定的实体对象
	var params = autoformCondition.get("entity");
	// 将实体对象作为参数传入，并异步刷新
	dataSetTbsProj.set("parameter", params).flushAsync();
};

/*=======================主表单Details按钮===================*/
/** @Bind #btnDetails.onClick */
!function(self,arg,iFrameProjDetails,iFrameProjAttach,dialogProjDetails,dataSetTbsProj){
	var dataSet = dataSetTbsProj.getData("#");
	var projid = dataSet.get("id");
	var projsn = dataSet.get("sn");
	var keyinid = dataSet.get("keyinId");
	var projName = dataSet.get("projName");
	var projcheckid = dataSet.get("tbsProjCheckSet").current.get("id"); 
	var processInstanceId = dataSet.get("tbsProjCheckSet").current.get("by1");  
	var projcheckvalid = dataSet.get("tbsProjCheckSet").current.get("valid");  
	var projcheckby2 = dataSet.get("tbsProjCheckSet").current.get("by2"); 
	var detailpath="org.tbs.views.projs.periodMgm.DetailDialog12.d?menu=PerInsp&aprv=0&projid=" + projid 
	+ "&projid=" + projid 
	+ "&keyinid=" + keyinid 
	+ "&projsn=" + projsn 
	+ "&projName=" + projName 
	+ "&processInstanceId=" + processInstanceId
	+ "&projcheckvalid=" + projcheckvalid
	+ "&projcheckid=" + projcheckid
	+ "&projcheckby2=" + projcheckby2
	+ "&dt=" + new Date();
	iFrameProjDetails.set("path",detailpath);
	dialogProjDetails.show();
};

/*=======================主表单Add按钮===================*/
/** @Bind #btnAdd.onClick */
!function(self,arg,iFrameProjDetails,iFrameProjAttach,dialogProjDetails,dataSetTbsProj){
	var dataSet = dataSetTbsProj.getData("#");
	var projid = dataSet.get("id");
	var projsn = dataSet.get("sn");
	var detailpath="org.tbs.views.projs.periodMgm.DetailDialog12.d?menu=PerInsp&aprv=2&projid=" + projid 
			+ "&projid=" + projid
			+ "&projsn=" + projsn
			+ "&dt=" + new Date();
	iFrameProjDetails.set("path",detailpath);
	dialogProjDetails.show();
};

/*=======================主表单Del按钮===================*/
/** @Bind #btnDel.onClick */
!function(self,arg,dataSetTbsProj,ajaxDel){
	var dataSet = dataSetTbsProj.getData("#");
	var projcheckSet = dataSet.get("tbsProjCheckSet").current;
	var projcheckvalid = dataSet.get("tbsProjCheckSet").current.get("valid");
	if (projcheckvalid == 0) {
		dorado.MessageBox.confirm("您真的要删除此【定期检查记录】吗？\n此操作无法恢复！",
			{title:"趣博信息科技",
			 detailCallback:function(btnID, text){
			 									  if (btnID == "yes"){
			 										 projcheckSet.remove();
			 										 view.get("#updateActionSaveTbsProjCheck").execute();
				                                                    }
												  } 	
			});
	} else {
		dorado.MessageBox.alert("无法删除！\n此【定期检查记录】已经通过审核，或正在审核中！", 
				{ title : "趣博信息科技"	});
	}; 
};

/*============如果是risk项目就禁止再次检查============*/
///** @Bind #DataGridTbsProj.onDataRowClick */
///** @Bind #dataSetTbsProj.onLoadData */
/*!function(self,arg,dataSetTbsProj,btnAdd){
	var dataSet = dataSetTbsProj.getData("#");
	var risk = dataSet.get("risk");
	if (risk == true){
		btnAdd.set("disabled",true);
	}else{
		btnAdd.set("disabled",false);
	};
};*/

/** @Bind #DataGridTbsProj.onRenderRow */
!function(self, arg) {
	var risk = arg.data.get("risk");
	if (risk == 1) {
		arg.dom.style.background = "#EE0000";
		arg.dom.style.color = "#0A0A0A";
	}else if(risk == 0){
		//arg.dom.style.background = "#FFFFFF";
		arg.dom.style.color = "#0A0A0A";
	};
};

/** @Bind #risk.onRenderCell */
!function(self, arg) {
	if(arg.data.get("risk")==true){
		arg.dom.innerText = "不正常";
	}else if(arg.data.get("risk")==false){
		arg.dom.innerText = "正常";
	}
	
};

/** @Bind #dialogProjDetails.onHide */
!function(dataSetTbsProj){
	dataSetTbsProj.getData("#.tbsProjCheckSet").flushAsync();
};