/*=======================主表单查询按钮===================*/
/** @Bind #btnQuery.onClick */
!function(self, arg, autoformCondition, dataSetTbsProj) {
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
	var projid = dataSet.get("id");
	var projchangeid = dataSet.get("tbsProjchangeMajcontSet").current.get("id");
	var processInstanceId = dataSet.get("tbsProjchangeMajcontSet").current.get("by1");
	var projchangevalid = dataSet.get("tbsProjchangeMajcontSet").current.get("valid");
	var detailpath="org.tbs.views.projs.change.DetailDialog1.d?aprv=0&projid=" + projid 
	         + "&projchangeid=" + projchangeid + "&projchangevalid=" + projchangevalid
	         + "&processInstanceId=" + processInstanceId
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
	var faloc = dataSet.get("initfaloc");
	var nfaloc = dataSet.get("initnfaloc");
	var otloc = dataSet.get("initotloc");
	var totloc = dataSet.get("inittotloc");
	var periodcfm = dataSet.get("periodCfm");
	var bdate = dataSet.get("bdate").formatDate("Y-m-d");
	var edate = dataSet.get("edate").formatDate("Y-m-d");
	var oldprojname = dataSet.get("projName");
	var detailpath="org.tbs.views.projs.change.DetailDialog1.d?aprv=2&projid=" 
			+ projid + "&projsn=" + projsn + "&oldprojname=" + oldprojname
			+ "&bdate=" + bdate + "&edate=" + edate + "&periodcfm=" + periodcfm 
			+ "&faloc=" + faloc + "&nfaloc=" + nfaloc + "&otloc=" + otloc + "&totloc=" + totloc
	        + "&dt=" + new Date();
	iFrameProjDetails.set("path",detailpath);
	dialogProjDetails.show();
};

/*=======================主表单Del按钮===================*/
/** @Bind #btnDel.onClick */
!function(self,arg,dataSetTbsProj,ajaxDel){
	var dataSet = dataSetTbsProj.getData("#");
	var projchangedataSet = dataSet.get("tbsProjchangeMajcontSet").current;
	var projchangevalid = dataSet.get("tbsProjchangeMajcontSet").current.get("valid");
	var projchangeid = dataSet.get("tbsProjchangeMajcontSet").current.get("id");
	if (projchangevalid == 0) {
		dorado.MessageBox.confirm("您真的要删除此【项目变更单】吗？\n此操作无法恢复！",
			{title:"趣博信息科技",
			 detailCallback:function(btnID, text){
			 									  if (btnID == "yes"){
			 										 projchangedataSet.remove();
			 										ajaxDel.set("parameter",projchangeid).execute();
				                                                    }
												  } 	
			});
	} else {
		dorado.MessageBox.alert("无法删除！\n此【项目变更单】已经通过审核，或正在审核中！", 
				{ title : "趣博信息科技"	});
	}; 
};

