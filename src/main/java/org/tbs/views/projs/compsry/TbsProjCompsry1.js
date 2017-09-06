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
	var compsryid = dataSet.get("tbsProjundwrtSet").current.get("tbsProjcompsrySet").current.get("id");
	var compsryvalid = dataSet.get("tbsProjundwrtSet").current.get("tbsProjcompsrySet").current.get("valid");
	var processInstanceId = dataSet.get("tbsProjundwrtSet").current.get("tbsProjcompsrySet").current.get("by1");
	var detailpath="org.tbs.views.projs.compsry.DetailDialog1.d?aprv=0&projid=" + projid 
	         + "&compsryid=" + compsryid + "&compsryvalid=" + compsryvalid 
	         + "&processInstanceId=" + processInstanceId + "&projname=" + projname
	         + "&dt=" + new Date();
	iFrameProjDetails.set("path",detailpath);
	dialogProjDetails.show();
};

/*=======================主表单Add按钮===================*/
/** @Bind #btnAdd.onClick */
!function(self,arg,iFrameProjDetails,dialogProjDetails,dataSetTbsProj){
	var dataSet = dataSetTbsProj.getData("#");
	var projid = dataSet.get("id");
	var projname = dataSet.get("projName");
	var undwrtid = dataSet.get("tbsProjundwrtSet").current.get("id");	
	var detailpath="org.tbs.views.projs.compsry.DetailDialog1.d?aprv=2&projid=" 
			+ projid + "&undwrtid=" + undwrtid + "&projname=" + projname
			+ "&dt=" + new Date();
	iFrameProjDetails.set("path",detailpath);
	dialogProjDetails.show();
};

/*=======================主表单Del按钮===================*/
/** @Bind #btnDel.onClick */
!function(self,arg,dataSetTbsProj,ajaxDel){
	var dataSet = dataSetTbsProj.getData("#");
	var compsrySet = dataSet.get("tbsProjundwrtSet").current.get("tbsProjcompsrySet").current;
	var compsryid = dataSet.get("tbsProjundwrtSet").current.get("tbsProjcompsrySet").current.get("id");
	var compsryvalid = dataSet.get("tbsProjundwrtSet").current.get("tbsProjcompsrySet").current.get("valid");
	if (compsryvalid == 0) {
		dorado.MessageBox.confirm("您真的要删除此【代偿申请单】吗？\n此操作无法恢复！",
			{title:"趣博信息科技",
			 detailCallback:function(btnID, text){
			 									  if (btnID == "yes"){
			 										 compsrySet.remove();
			 										 ajaxDel.set("parameter",compsryid).execute();
				                                                    }
												  } 	
			});
	} else {
		dorado.MessageBox.alert("无法删除！\n此【代偿申请单】已经通过审核，或正在审核中！", 
				{ title : "趣博信息科技"	});
	}; 
};