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
	var rlsfaloc = dataSet.get("tbsProjcompsrySet").current.get("tbsProjundwrt.rlsfaloc");	
	var rlsnfaloc = dataSet.get("tbsProjcompsrySet").current.get("tbsProjundwrt.rlsnfaloc");	
	var rlsotloc = dataSet.get("tbsProjcompsrySet").current.get("tbsProjundwrt.rlsotloc");	
	var rlstotloc = dataSet.get("tbsProjcompsrySet").current.get("tbsProjundwrt.rlstotloc");
	var compsrypayid = dataSet.get("tbsProjcompsrySet").current.get("tbsProjcompsryPaySet").current.get("id");
	var compsrypayvalid = dataSet.get("tbsProjcompsrySet").current.get("tbsProjcompsryPaySet").current.get("valid");
	var processInstanceId = dataSet.get("tbsProjcompsrySet").current.get("tbsProjcompsryPaySet").current.get("by1");
	var detailpath="org.tbs.views.projs.compsry.DetailDialog2.d?aprv=0&projid=" + projid 
	         + "&compsrypayid=" + compsrypayid + "&compsrypayvalid=" + compsrypayvalid 
	         + "&processInstanceId=" + processInstanceId + "&projname=" + projname
	         + "&rlsfaloc=" + rlsfaloc  + "&rlsnfaloc=" + rlsnfaloc
			 + "&rlsotloc=" + rlsotloc  + "&rlstotloc=" + rlstotloc
	         + "&dt=" + new Date();
	iFrameProjDetails.set("path",detailpath);
	dialogProjDetails.show();
};

/*=======================主表单Add按钮===================*/
/** @Bind #btnAdd.onClick */
!function(self,arg,iFrameProjDetails,dialogProjDetails,dataSetTbsProj){
	var dataSet = dataSetTbsProj.getData("#");
	var compsryvalid = dataSet.get("tbsProjcompsrySet").current.get("valid");
	if (compsryvalid == 1) {
	var projid = dataSet.get("id");
	var projname = dataSet.get("projName");
	var rlsfaloc = dataSet.get("tbsProjcompsrySet").current.get("tbsProjundwrt.rlsfaloc");	
	var rlsnfaloc = dataSet.get("tbsProjcompsrySet").current.get("tbsProjundwrt.rlsnfaloc");	
	var rlsotloc = dataSet.get("tbsProjcompsrySet").current.get("tbsProjundwrt.rlsotloc");	
	var rlstotloc = dataSet.get("tbsProjcompsrySet").current.get("tbsProjundwrt.rlstotloc");	
	var compsryid = dataSet.get("tbsProjcompsrySet").current.get("id");	
	var undwrtid = dataSet.get("tbsProjcompsrySet").current.get("tbsProjundwrt.id");	
	var cfmtotloc = dataSet.get("tbsProjundwrtSet").current.get("cfmtotloc");	//累计承保金额
	//var rlstotloc = dataSet.get("tbsProjundwrtSet").current.get("rlstotloc");	//累计解保金额
	var customerid = dataSet.get("tbsCustomer").get("id");	
	var customername = dataSet.get("tbsCustomer").get("name");	
	var detailpath="org.tbs.views.projs.compsry.DetailDialog2.d?aprv=2&projid=" 
			+ projid + "&undwrtid=" + undwrtid + "&projname=" + projname
			+ "&customerid=" + customerid + "&customername=" + customername
			+ "&compsryid=" + compsryid
			+ "&cfmtotloc=" + cfmtotloc 
			+ "&rlsfaloc=" + rlsfaloc  + "&rlsnfaloc=" + rlsnfaloc
			+ "&rlsotloc=" + rlsotloc  + "&rlstotloc=" + rlstotloc
			+ "&dt=" + new Date();
	iFrameProjDetails.set("path",detailpath);
	dialogProjDetails.show();
	}else {
		dorado.MessageBox.alert("此【代偿申请单】未通过审核，或正在审核中！", 
				{ title : "趣博信息科技"	});
	}; 
};

/*=======================主表单Del按钮===================*/
/** @Bind #btnDel.onClick */
!function(self,arg,dataSetTbsProj,ajaxDel){
	var dataSet = dataSetTbsProj.getData("#");
	var compsrypaySet = dataSet.get("tbsProjcompsrySet").current.get("tbsProjcompsryPaySet").current;
	var compsrypayid = dataSet.get("tbsProjcompsrySet").current.get("tbsProjcompsryPaySet").current.get("id");
	var compsrypayvalid = dataSet.get("tbsProjcompsrySet").current.get("tbsProjcompsryPaySet").current.get("valid");
	if (compsrypayvalid == 0) {
		dorado.MessageBox.confirm("您真的要删除此【代偿申请单】吗？\n此操作无法恢复！",
			{title:"趣博信息科技",
			 detailCallback:function(btnID, text){
			 									  if (btnID == "yes"){
			 										 compsrypaySet.remove();
			 										 ajaxDel.set("parameter",compsrypayid).execute();
				                                                    }
												  } 	
			});
	} else {
		dorado.MessageBox.alert("无法删除！\n此【代偿申请单】已经通过审核，或正在审核中！", 
				{ title : "趣博信息科技"	});
	}; 
};