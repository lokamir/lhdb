/*=======================主表单查询按钮===================*/
/** @Bind #btnQuery.onClick */
!function(self, arg, autoformCondition, dataSetTbsProj,dataSetTbsProjundwrt) {
	// 获取autoformCondition绑定的实体对象
	var params = autoformCondition.get("entity");
	// 将实体对象作为参数传入，并异步刷新
	params.set("qrytyp","1");
	dataSetTbsProj.set("parameter", params).flushAsync();
};

/*=======================主表单Details按钮===================*/
/** @Bind #btnDetails.onClick */
!function(self,arg,iFrameProjDetails,iFrameProjAttach,dialogProjDetails,dataSetTbsProj){
	var dataSet = dataSetTbsProj.getData("#");
	var projname = dataSet.get("projName");
	var projid = dataSet.get("id");
	var projrolid = dataSet.get("tbsProjCompsryPaySet").current.get("tbsProjrolSet").current.get("id");
	var projrolvalid = dataSet.get("tbsProjCompsryPaySet").current.get("tbsProjrolSet").current.get("valid");
	var processInstanceId = dataSet.get("tbsProjCompsryPaySet").current.get("tbsProjrolSet").current.get("by1");
	var detailpath="org.tbs.views.projs.compsry.DetailDialog3.d?aprv=0&projid=" + projid 
	         + "&projrolid=" + projrolid + "&projrolvalid=" + projrolvalid 
	         + "&processInstanceId=" + processInstanceId + "&projname=" + projname
	         + "&dt=" + new Date();
	iFrameProjDetails.set("path",detailpath);
	dialogProjDetails.show();
};

/*=======================主表单Add按钮===================*/
/** @Bind #btnAdd.onClick */
!function(self,arg,iFrameProjDetails,dialogProjDetails,dataSetTbsProj){
	var dataSet = dataSetTbsProj.getData("#");
	var compsrypayvalid = dataSet.get("tbsProjCompsryPaySet").current.get("valid");
	if (compsrypayvalid == 1) {
	var projid = dataSet.get("id");
	var projname = dataSet.get("projName");
	var compsryPayid = dataSet.get("tbsProjCompsryPaySet").current.get("id");	
	var undwrtid = dataSet.get("tbsProjCompsryPaySet").current.get("tbsProjundwrt").get("id");	
	var rlsfaloc = dataSet.get("tbsProjCompsryPaySet").current.get("tbsProjundwrt").get("rlsfaloc");	
	var rlsnfaloc = dataSet.get("tbsProjCompsryPaySet").current.get("tbsProjundwrt").get("rlsnfaloc");	
	var rlsotloc = dataSet.get("tbsProjCompsryPaySet").current.get("tbsProjundwrt").get("rlsotloc");	
	var rlstotloc = dataSet.get("tbsProjCompsryPaySet").current.get("tbsProjundwrt").get("rlstotloc");	
	var customerid = dataSet.get("tbsCustomer").get("id");	
	var customername = dataSet.get("tbsCustomer").get("name");	
	var detailpath="org.tbs.views.projs.compsry.DetailDialog3.d?aprv=2&projid=" 
			+ projid + "&undwrtid=" + undwrtid + "&projname=" + projname
			+ "&customerid=" + customerid + "&customername=" + customername
			+ "&compsryPayid=" + compsryPayid 
			+ "&rlsfaloc=" + rlsfaloc  + "&rlsnfaloc=" + rlsnfaloc
			+ "&rlsotloc=" + rlsotloc  + "&rlstotloc=" + rlstotloc
			+ "&dt=" + new Date();
	iFrameProjDetails.set("path",detailpath);
	dialogProjDetails.show();
	}else {
		dorado.MessageBox.alert("此【代偿请款单】未通过审核，或正在审核中！", 
				{ title : "趣博信息科技"	});
	}; 
};

/*=======================主表单Del按钮===================*/
/** @Bind #btnDel.onClick */
!function(self,arg,dataSetTbsProj,ajaxDel){
	var dataSet = dataSetTbsProj.getData("#");
	var compsrySet = dataSet.get("tbsProjCompsryPaySet").current.get("tbsProjrolSet").current;
	var compsryid = dataSet.get("tbsProjCompsryPaySet").current.get("tbsProjrolSet").current.get("id");
	var compsryvalid = dataSet.get("tbsProjCompsryPaySet").current.get("tbsProjrolSet").current.get("valid");
	if (compsryvalid == 0) {
		dorado.MessageBox.confirm("您真的要删除此【追偿申请书】吗？\n此操作无法恢复！",
			{title:"趣博信息科技",
			 detailCallback:function(btnID, text){
			 									  if (btnID == "yes"){
			 										 compsrySet.remove();
			 										 ajaxDel.set("parameter",compsryid).execute();
				                                                    }
												  } 	
			});
	} else {
		dorado.MessageBox.alert("无法删除！\n此【追偿申请书】已经通过审核，或正在审核中！", 
				{ title : "趣博信息科技"	});
	}; 
};