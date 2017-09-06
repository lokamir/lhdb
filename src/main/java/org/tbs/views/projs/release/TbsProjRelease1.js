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
	var rlsfaloc = dataSet.get("tbsProjundwrtSet").current.get("rlsfaloc");
	var rlsnfaloc = dataSet.get("tbsProjundwrtSet").current.get("rlsnfaloc");
	var rlsotloc = dataSet.get("tbsProjundwrtSet").current.get("rlsotloc");
	var rlstotloc = dataSet.get("tbsProjundwrtSet").current.get("rlstotloc");
	var releaseid = dataSet.get("tbsProjundwrtSet").current.get("tbsProjReleaseSet").current.get("id");
	var releasevalid = dataSet.get("tbsProjundwrtSet").current.get("tbsProjReleaseSet").current.get("valid");
	var processInstanceId = dataSet.get("tbsProjundwrtSet").current.get("tbsProjReleaseSet").current.get("by1");
	var detailpath="org.tbs.views.projs.release.DetailDialog1.d?aprv=0&projid=" + projid 
	         + "&releaseid=" + releaseid + "&releasevalid=" + releasevalid 
	         + "&processInstanceId=" + processInstanceId + "&projname=" + projname
	         + "&rlsfaloc=" + rlsfaloc + "&rlsnfaloc=" + rlsnfaloc
	         + "&rlsotloc=" + rlsotloc + "&rlstotloc=" + rlstotloc
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
	var rlsfaloc = dataSet.get("tbsProjundwrtSet").current.get("rlsfaloc");
	var rlsnfaloc = dataSet.get("tbsProjundwrtSet").current.get("rlsnfaloc");
	var rlsotloc = dataSet.get("tbsProjundwrtSet").current.get("rlsotloc");
	var rlstotloc = dataSet.get("tbsProjundwrtSet").current.get("rlstotloc");	
	var detailpath="org.tbs.views.projs.release.DetailDialog1.d?aprv=2&projid=" 
			+ projid + "&undwrtid=" + undwrtid + "&projname=" + projname
			+ "&rlsfaloc=" + rlsfaloc + "&rlsnfaloc=" + rlsnfaloc
			+ "&rlsotloc=" + rlsotloc + "&rlstotloc=" + rlstotloc
			+ "&dt=" + new Date();
	iFrameProjDetails.set("path",detailpath);
	dialogProjDetails.show();
};

/*=======================主表单Del按钮===================*/
/** @Bind #btnDel.onClick */
!function(self,arg,dataSetTbsProj,ajaxDel){
	var dataSet = dataSetTbsProj.getData("#");
	var releasedataSet = dataSet.get("tbsProjundwrtSet").current.get("tbsProjReleaseSet").current;
	var releaseid = dataSet.get("tbsProjundwrtSet").current.get("tbsProjReleaseSet").current.get("id");
	var releasevalid = dataSet.get("tbsProjundwrtSet").current.get("tbsProjReleaseSet").current.get("valid");
	if (releasevalid == 0) {
		dorado.MessageBox.confirm("您真的要删除此【项目解保单】吗？\n此操作无法恢复！",
			{title:"趣博信息科技",
			 detailCallback:function(btnID, text){
			 									  if (btnID == "yes"){
			 										 releasedataSet.remove();
			 										 ajaxDel.set("parameter",releaseid).execute();
				                                                    }
												  } 	
			});
	} else {
		dorado.MessageBox.alert("无法删除！\n此【项目解保单】已经通过审核，或正在审核中！", 
				{ title : "趣博信息科技"	});
	}; 
};