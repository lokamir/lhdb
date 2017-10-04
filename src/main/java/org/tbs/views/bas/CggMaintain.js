/**
 * customized validate at datatypeTbsCggGrbz.idcardnum's validator events
 */

//Global variables
var lastContainerObj;
var currentCustomer;
var currentDataset;
var currentUpdateAction;
var xCreatelist = [];
/** @Bind view.onReady */
!function(self,arg){
//	var datasetCggObj;
	var btnCggAddObj;
	var btnCggDelObj;
	
	for (var objId=5;objId<=23;objId++) {
		btnCggAddObj = view.get("#btnCggAdd" + objId);
		btnCggDelObj = view.get("#btnCggDel" + objId);
		
//		//choose proper dataset
//		if (objId >= 9 && objId <= 12){
//			datasetCggObj = "#datasetTbsCggDy0912";
//		} else if (objId >= 5 && objId <= 7) {
//			datasetCggObj = "#datasetTbsCggDy0507";
//		} else if (objId == 8) {
//			datasetCggObj = "#datasetTbsCggDy8";
//		}else if (objId >= 19 && objId <= 20) {
//			datasetCggObj = "#datasetTbsCggZy1920";
//		} else {
//			datasetCggObj = "#datasetTbsCggZy" + objId;
//		}
		// add all cggAdd listener
		btnCggAddObj.addListener("onClick", function(self, arg){
			var id = self._id.replace("btnCggAdd","");
			var targetDataset;
			//choose proper dataset
			if (id >= 9 && id <= 12){
				targetDataset = view.get("#datasetTbsCggDy0912");
			} else if (id >= 5 && id <= 7) {
				targetDataset = view.get("#datasetTbsCggDy0507");
			} else if (id == 8) {
				targetDataset = view.get("#datasetTbsCggDy8");
			}else if (id >= 19 && id <= 20) {
				targetDataset = view.get("#datasetTbsCggZy1920");
			} else {
				targetDataset = view.get("#datasetTbsCggZy" + id);
			}
			targetDataset.insert();
			targetDataset.getData("#new").set("by3",currentCustomer.get("name"));
		});
		
		// add all cggDel listener
		btnCggDelObj.addListener("onClick", function(self, arg){
			dorado.MessageBox.confirm("您确定要删除当前记录么？", function() {
				var id = self._id.replace("btnCggDel","");
				var targetDataset;
				//choose proper dataset
				if (id >= 9 && id <= 12){
					targetDataset = view.get("#datasetTbsCggDy0912");
				} else if (id >= 5 && id <= 7) {
					targetDataset = view.get("#datasetTbsCggDy0507");
				} else if (id == 8) {
					targetDataset = view.get("#datasetTbsCggDy8");
				}else if (id >= 19 && id <= 20) {
					targetDataset = view.get("#datasetTbsCggZy1920");
				} else {
					targetDataset = view.get("#datasetTbsCggZy" + id);
				}
				var entity = targetDataset.getData("#");
				entity.remove();
				dorado.widget.NotifyTipManager.notify("当前记录被删除！！");
			});
		});
		
	}
	
	// add cggAdd listener for cgg41
	view.get("#btnCggAdd41").addListener("onClick", function(self, arg){
		var targetDataset= view.get("#datasetTbsCggBzjzy");
		targetDataset.insert();
		targetDataset.getData("#new").set("by3",currentCustomer.get("name"));
	});
	
	// add cggDel listener for cgg41
	view.get("#btnCggDel41").addListener("onClick", function(self, arg){
		dorado.MessageBox.confirm("您确定要删除当前记录么？", function() {
			var targetDataset = view.get("#datasetTbsCggBzjzy");
			var entity = targetDataset.getData("#");
			entity.remove();
			dorado.widget.NotifyTipManager.notify("当前记录被删除！！");
		});
	});
	
};

// set right visible status when the Customer changed
/** @Bind #teTbsCustomer.onTextEdit */
!function(self,arg,autoformCondition){	
	var entity = autoformCondition.get("entity");
	var newCutomer = entity.get("tbsCustomer");
	if (newCutomer != currentCustomer) {
		if(lastContainerObj){
			lastContainerObj.set("visible",false);
		}
	}
};

/** @Bind #teTbsCustomer.onPost */
!function(self,arg,autoformCondition){
	var dataSet = view.get("#datasetGetVcggall");
	var cusid = autoformCondition.get("entity").get("tbsCustomer.id");
	dataSet.set("parameter",{cusid:cusid}).flushAsync();
};

/** @Bind #datasetGetVcggall.onLoadData */
!function(self,arg,autoformCondition){
	xCreatelist=[];
	var data = view.get("#datasetGetVcggall").getData();
	data.each(function(entity){
		xCreatelist.push(entity.get("sn").get("cat1"));
		xCreatelist.push(entity.get("sn").get("cat2"));
		xCreatelist.push(entity.get("sn").get("cat3"));
	});
	view.get("#datasetTbsBasCggtyp").flushAsync();
};

/** @Bind #dataTreeCggtyp.onRenderNode */
!function(arg) {
	var node = arg.node, data = node.get("data"), xCreateConfig = [],count=0;
	if (node.get("bindingConfig.name") == "cggtyp") {
		xCreateConfig.push({
			tagName : "SPAN",
			contentText : node.get("label")
		});
		if ($.inArray(data.get("id"), xCreatelist) >= 0) {
			xCreatelist.each(function(entity){
				if(entity==data.get("id")){
					count++;
				}
			});
			xCreateConfig.push({
				tagName : "SPAN",
				contentText : "(" + count + ")",
				style : "margin-left: 2px; color: gray"
			});
			count=0;
		}
	}
	if (xCreateConfig.length) {
		$(arg.dom).empty().xCreate(xCreateConfig);
	} else {
		arg.processDefault = true;
	}
};

// set right editable status for Age when the autoform init
/** @Bind #teTbsBasIdCardtype.onGetBindingData */
!function(self,arg,autoformTbsCgg40,teIdcardnum,teAge40){
	var entity = autoformTbsCgg40.get("entity");
	if (entity.get("tbsBasIdcardtype")) {
		var cardtypeId = entity.get("tbsBasIdcardtype").get("id");
		if(cardtypeId==1){
			teAge40.set("readOnly", true);
		} else {
			teAge40.set("readOnly", false);
		}
	} else {
		teAge40.set("readOnly", false);
	}
};

// set right editable status for Age when the IdType changed
/** @Bind #teTbsBasIdCardtype.onTextEdit */
!function(self,arg,autoformTbsCgg40,teIdcardnum,teAge40){
	var entity = autoformTbsCgg40.get("entity");
	if (entity.get("tbsBasIdcardtype")) {
		var cardtypeId = entity.get("tbsBasIdcardtype").get("id");
		if(cardtypeId==1){
			teAge40.set("readOnly", true);
		} else {
			teAge40.set("readOnly", false);
		}
	}
};

/** @Bind #dataTreeCggtyp.onClick */
!function(self,arg,autoformCondition){
	var currentEntity = view.get("#dataTreeCggtyp.currentEntity");
	var objId = currentEntity.get("id");
	var entity = autoformCondition.get("entity");
	var datasetCggObj;
	var containerObj = view.get("#cgg" + objId);
	var saveButtonObj = view.get("#btnCgg" + objId);
	var updateActionObj = view.get("#updateactionTbsCgg" + objId);
	//choose proper dataset
	if (objId >= 9 && objId <= 12){
		datasetCggObj = view.get("#datasetTbsCggDy0912");
	} else if (objId >= 5 && objId <= 7) {
		datasetCggObj = view.get("#datasetTbsCggDy0507");
	} else if (objId == 8) {
		datasetCggObj = view.get("#datasetTbsCggDy8");
	} else if (objId >= 13 && objId <= 18) {
		datasetCggObj = view.get("#datasetTbsCggZy" + objId);
	} else if (objId >= 19 && objId <= 20) {
		datasetCggObj = view.get("#datasetTbsCggZy1920");
	} else if (objId >= 21 && objId <= 23) {
		datasetCggObj = view.get("#datasetTbsCggZy" + objId);
	} else if (objId == 39) {
		datasetCggObj = view.get("#datasetTbsCggQybz");
	} else if (objId == 40) {
		datasetCggObj = view.get("#datasetTbsCggGrbz");
	} else if (objId == 41) {
		datasetCggObj = view.get("#datasetTbsCggBzjzy");
	}
	
	if (containerObj) {
		//close last cgg container
		if (lastContainerObj){
			lastContainerObj.set("visible",false);
		}
		
		//flush dataset
		if (datasetCggObj) {
			if (entity.get("tbsCustomer") && entity.get("tbsCustomer") != ""){
				//grant & show new cgg container
				lastContainerObj = containerObj;
				containerObj.set("visible",true);
				var param = {
						cggId: objId,
						entity: entity
				};
				//refresh dataset, get data from DB
				datasetCggObj.set("parameter",param).flushAsync();
				
				currentCustomer = entity.get("tbsCustomer");
				currentDataset = datasetCggObj;
				if (updateActionObj) {
					currentUpdateAction = updateActionObj;
					
					//clear all listener from save button
					saveButtonObj.removeListener("onClick");
					//add listener for save button
					saveButtonObj.addListener("onClick", function(self, arg){
						var datasetTbsCggDys = currentDataset.get("data");
						
						datasetTbsCggDys.each(function(datasetTbsCggDy){
							if(datasetTbsCggDy){
								if (datasetTbsCggDy.get("tbsCustomer") == null) {
									datasetTbsCggDy.set("cusName",currentCustomer.get("name"));
								}
							}
						});
						var cat1 = 0;
						var cat2 = 0;
						//define cat1 & cat2 's value according to cat3's value
						if(objId == 9 || objId == 10){
							// 抵押case: 土地使用权抵押's sub nodes
							cat1 = 1;
							cat2 = 2;
						} else if (objId == 11 || objId == 12) {
							// 抵押case: 不动产抵押's sub nodes
							cat1 = 1;
							cat2 = 3;
						} else if (objId >= 5 && objId <= 8) {
							// 抵押case: 动产抵押's sub nodes
							cat1 = 1;
							cat2 = 37;
						} else if (objId >= 13 && objId <= 23) {
							// 抵押case: 动产抵押's sub nodes
							cat1 = 4;
							cat2 = objId;
						} 
						else if (objId >= 39 && objId <= 41) {
							// 抵押case: 动产抵押's sub nodes
							cat1 = objId;
							cat2 = 0;
						}
						var parameter = {
								cat1: cat1,
								cat2: cat2,
								cat3: objId,
								customer: currentCustomer
						};
						currentUpdateAction.set("parameter",parameter).execute();
						datasetCggObj.set("parameter",param).flushAsync();
					});
				}
			} else {
				dorado.MessageBox.alert("请选择关联客户",{title:"趣博信息科技"});
			}
		}
		
	}
};



/**
 * =======================企业保证===================*
 */

/*=======================新增按钮===================*/
/** @Bind #btnCggAdd39.onClick */
!function(self,arg,dialogTbsCgg39,datasetTbsCggQybz){
	datasetTbsCggQybz.insert();
	datasetTbsCggQybz.getData("#new").set("tbsCustomer",currentCustomer.toJSON());
	datasetTbsCggQybz.getData("#new").set("by3",currentCustomer.get("name"));
	dialogTbsCgg39.show();
};

/*=======================修改按钮===================*/
/** @Bind #btnCggEdit39.onClick */
!function(self,arg,dialogTbsCgg39,datasetTbsCggQybz){
	var aa = datasetTbsCggQybz.getData("#").get("tbsCustomer");
	dialogTbsCgg39.show();
};

/*=======================删除按钮===================*/
/** @Bind #btnCggDel39.onClick */
!function(self,arg,datasetTbsCggQybz){
	dorado.MessageBox.confirm("您确定要删除当前记录么？", function() {
		var entity = datasetTbsCggQybz.getData("#");
		entity.remove();
		dorado.widget.NotifyTipManager.notify("当前记录被删除！！");
	});
};

/*=======================dialog确认按钮===================*/
/** @Bind #btnCggConfirm39.onClick */
!function(self,arg,dialogTbsCgg39){
	dialogTbsCgg39.hide();
};

/*=======================dialog取消按钮===================*/
/** @Bind #btnTbcCggCancel39.onClick */
!function(self,arg,dialogTbsCgg39,datasetTbsCggQybz){
	datasetTbsCggQybz.get("data:#").cancel();
	dialogTbsCgg39.hide();
};

/**
 * =======================个人保证===================*
 * */

/*=======================新增按钮===================*/
/** @Bind #btnCggAdd40.onClick */
!function(self,arg,dialogTbsCgg40,datasetTbsCggGrbz){
	datasetTbsCggGrbz.insert();
	datasetTbsCggGrbz.getData("#new").set("tbsCustomer",currentCustomer.toJSON());
	datasetTbsCggGrbz.getData("#new").set("by3",currentCustomer.get("name"));
	dialogTbsCgg40.show();
};

/*=======================修改按钮===================*/
/** @Bind #btnCggEdit40.onClick */
!function(self,arg,dialogTbsCgg40){
	dialogTbsCgg40.show();
};

/*=======================删除按钮===================*/
/** @Bind #btnCggDel40.onClick */
!function(self,arg,datasetTbsCggGrbz){
	dorado.MessageBox.confirm("您确定要删除当前记录么？", function() {
		var entity = datasetTbsCggGrbz.getData("#");
		entity.remove();
		dorado.widget.NotifyTipManager.notify("当前记录被删除！！");
	});
};

/*=======================dialog确认按钮===================*/
/** @Bind #btnCggConfirm40.onClick */
!function(self,arg,dialogTbsCgg40){
	dialogTbsCgg40.hide();
};

/*=======================dialog删除按钮===================*/
/** @Bind #btnTbcCggCancel40.onClick */
!function(self,arg,dialogTbsCgg40,datasetTbsCggGrbz){
	datasetTbsCggGrbz.get("data:#").cancel();
	dialogTbsCgg40.hide();
};

/**
 * 文件处理 开始
 */
/** @Bind #buttonFlsmgm.onClick */
!function(self,arg,dataSetTbsFunFul,dialogFlsmgm,iFrameAttach){
	var currentEntity = view.get("#dataTreeCggtyp.currentEntity");
	var objId = currentEntity.get("id");
	var by1;
	var datasetCggObj;
	// choose proper dataset
	if (objId >= 9 && objId <= 12){
		datasetCggObj = view.get("#datasetTbsCggDy0912");
		by1="dy";
		fid = "反担保-抵押";
	} else if (objId >= 5 && objId <= 7) {
		datasetCggObj = view.get("#datasetTbsCggDy0507");
		by1="dy";
		fid = "反担保-抵押";
	} else if (objId == 8) {
		datasetCggObj = view.get("#datasetTbsCggDy8");
		by1="dy";
		fid = "反担保-抵押";
	} else if (objId >= 13 && objId <= 18) {
		datasetCggObj = view.get("#datasetTbsCggZy" + objId);
		by1="zy";
		fid = "反担保-质押";
	} else if (objId >= 19 && objId <= 20) {
		datasetCggObj = view.get("#datasetTbsCggZy1920");
		by1="zy";
		fid = "反担保-质押";
	} else if (objId >= 21 && objId <= 23) {
		datasetCggObj = view.get("#datasetTbsCggZy" + objId);
		by1="zy";
		fid = "反担保-质押";
	} else if (objId == 39) {
		datasetCggObj = view.get("#datasetTbsCggQybz");
		by1="qybz";
		fid = "反担保-企业保证";
	} else if (objId == 40) {
		datasetCggObj = view.get("#datasetTbsCggGrbz");
		by1="grbz";
		fid = "反担保-个人保证";
	} else if (objId == 41) {
		datasetCggObj = view.get("#datasetTbsCggBzjzy");
		by1="bzjzy";
		fid = "反担保-保证金质押";
	}
	var by2=datasetCggObj.getData("#").get("sn");
	if (by2) {
		if (by2 != "") {
			var by3 =null;
			var typid =null;
			var attachpath="org.tbs.views.funs.MyFile.d?by1=" + by1 
				+ "&by2=" + by2
				+ "&by3=" + by3
				+ "&fid=" + fid
				+ "&typid=" + typid
				+ "&dt=" + new Date();
				iFrameAttach.set("path",attachpath);
				dialogFlsmgm.show();
			} 
	}else {
			dorado.MessageBox.alert("请选择关联客户", {
				title : "趣博信息科技"
			});
			

	};
};
/**
 * 文件处理  结束
 */





