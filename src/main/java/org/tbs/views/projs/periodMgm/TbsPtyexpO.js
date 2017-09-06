// Global variables
var ptyexpid;
var valid;
var aprv;  // 0表示来自菜单界面,2新增界面

/*======获取参数传递到iframe并打开dialog（公用）======*/
function GetMenu(button){
	var tbsProj = view.get("#dataSetTbsProj").getData("#");
	var projid = tbsProj.get("id");
	if (view.get("#dataSetTbsProj").getData("#.tbsProjundwrtSet.id")) {
		var undwrtid = view.get("#dataSetTbsProj").getData(
				"#.#tbsProjundwrtSet.id");
		var projdetailspath = "org.tbs.views.projs.periodMgm.DetailDialog34.d?aprv="+aprv+"&io=0&projid="+ projid
				+ "&undwrtid="+ undwrtid
				+ "&ptyexpid="+ ptyexpid
				+"&valid="+valid
				+ "&menu=PtyexpO" 
				+ "&button=" + button
				+ "&dt=" + new Date(); 
		view.get("#iFrameProjDetails").set("path", projdetailspath);
		view.get("#dialogTbsPtyexpNew").show();
	} else {
		dorado.MessageBox.alert("请选择承保审批单", {
			title : "趣博信息科技"
		});
	}
};

/*=======================主表单查询按钮===================*/
/** @Bind #btnQuery.onClick */
!function(self, arg, autoformCondition, dataSetTbsProj,dataSetTbsProjundwrt,datagridTbsProj) {
	var entity = autoformCondition.get("entity");
	dataSetTbsProj.set("parameter", entity).flushAsync();	
};

/*=======================主表单新增按钮===================*/
/** @Bind #btnAdd.onClick */
!function(dialogTbsPtyexpNew) {
	aprv = 2;
	GetMenu("Add");
};


/*=======================主表单明细按钮===================*/
/** @Bind #btnDetails.onClick */
!function(DataGridTbsPtyexp){
	aprv = 0;
	ptyexpid = DataGridTbsPtyexp.get("currentEntity").get("id");
	valid =  DataGridTbsPtyexp.get("currentEntity").get("valid");
	GetMenu("Details");
};

/*=======================主表单删除按钮===================*/
/** @Bind #btnDel.onClick */
!function(updateActionSaveTbsPtyexp){
	var data = view.get("#dataSetTbsProj").getData("#.#tbsProjundwrtSet.tbsPtyexpSet");
	var valid=data.current.get("valid");
	if(valid == 0){
		dorado.MessageBox.confirm("您真的要删除此【费用退回单据】吗？\n此操作无法恢复！", {
			title : "趣博信息科技",
			detailCallback : function(btnID, text) {
				if (btnID == "yes") {
					data.remove();
					updateActionSaveTbsPtyexp.execute();
				}
			}
		});
	}else{
		dorado.MessageBox.alert("无法删除！\n此【费用退回单】已经通过审核，或正在审核中！",{title:"趣博信息科技"});
	}
};

/* =======================承保审批明细表=================== */
/** @Bind #datagridTbsProjundwrt.onDataRowDoubleClick */
!function(self){
	view.get("#dialogTbsProjundwrt").show();
};
/** @Bind #buttonClose.onClick */
!function(self){
	view.get("#dialogTbsProjundwrt").hide();
};

/** @Bind #dialogTbsPtyexpNew.onHide */
!function(dataSetTbsProj){
	dataSetTbsProj.getData("#.#tbsProjundwrtSet.tbsPtyexpSet").flushAsync();
};