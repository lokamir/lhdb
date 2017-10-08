/**
 * Only for UFLO
 * */

// Global variables 
var docid;
var projid;
var psid;
var valid;
var taskid = "${request.getParameter('taskId')}";
var taskName = "${param.taskName}";
var outcome = "修改确认";
var readonly = false;
/*======Get status from CurrentRecordSets======*/
function GetCRStatus(ds){
	var CurRec = ds.getData("#");
	docid = CurRec.get("id")+"";
	projid = CurRec.get("tbsProj.id")+"";
	valid = CurRec.get("valid")+"";
	psid=CurRec.get("tbsProj.tbsBasPs.id")+"";
};

/** @Bind view.onReady */
!function(self, arg, dataSetTbsProjundwrt){
	dataSetTbsProjundwrt.flushAsync();
};

/** @Bind #dataSetTbsProjundwrt.onLoadData */
!function(self,arg,buttonAppr,buttonClose,buttonResend,DdlOutcome,OpinionGroupBox,DocGroupBox,dataSetTbsFunFul,upbutton,tabInAppr,tabMain,chunaGroupBox){
	GetCRStatus(self);
	//var loginU="${loginUser.getUsername()}";
	if (psid == 19 || psid == 9  ){  //驳回
		tabMain.set("currentIndex", 1);
		buttonClose.set("visible",false);
		buttonAppr.set("visible",false);
		buttonResend.set("visible",true);
		self.set("readOnly",false);  
		OpinionGroupBox.set("visible",false);
		DocGroupBox.set("height","88%");	
		//dataSetTbsFunFul.set("readOnly",false);  //uploader 
		//upbutton.set("disabled",false);
		readonly = "false";
	}else if(taskName =='出纳费用确认'||taskName =='财务部门经理审批') {
		tabMain.set("currentIndex", 0);
		tabInAppr.set("visible",true);
		buttonClose.set("visible",true);
		buttonAppr.set("visible",true);
		buttonResend.set("visible",false);
		DdlOutcome.set("items",["通过","驳回"]);
		readonly = "true";
		self.set("readOnly",false);  
		view.get("#tabCN").set("visible",true);
	}else{
		tabMain.set("currentIndex", 0);
		tabInAppr.set("visible",true);
		buttonClose.set("visible",true);
		buttonAppr.set("visible",true);
		buttonResend.set("visible",false);
		DdlOutcome.set("items",["通过","驳回"]);
		self.set("readOnly",true);
		//dataSetTbsFunFul.set("readOnly",true); 
		readonly = "true";
    	/*if (loginU == 'admin' || loginU == 'xinc') {  // xinc在审批时需要填写承保单
    		self.set("readOnly",false);
    	};*/
    	
	};
};
//Global End

/*============Execute approving=============*/
/** @Bind #ajaxActionAppr.beforeExecute */
!function(self,arg,updateActionSave,dataSetTbsProjundwrt,ddlAutoform){
	var opinion=view.id("OpinionAutoform").get("entity").opinion;
	if (!opinion){
		opinion="无意见";
	};
	if (psid == 19 || psid == 9 ){
		//self.set("confirmMessage","您确定再次发送审批？");
		
		var entity = ddlAutoform.get("entity");
		var repay = entity.get("repay");
		var repayinper = entity.get("repayinper");
		var loantype = entity.get("loantype");
		var by3 = entity.get("by3");
		if((!repay||!loantype||!by3||by3==0)||(repay!="到期一次性结清"&&!repayinper)){
			arg.processDefault=false;
			dorado.MessageBox.alert("\v\v\v承保期限、放款方式、还款方式、每次还款额必填 \n还款方式若为\"到期一次性结清\"，则后面每次还款额可以不填",{title:"趣博信息科技"});
			return false;
		}
		var bizvtDS = dataSetTbsProjundwrt.getData("#.tbsProjundwrtBizvtSet");
		bizvtDS.each(function(data){
			if ((data.get("tbsBasBiztype.name") == "融资性担保")&&!dataSetTbsProjundwrt.getData("#.tbsProjundwrtBankSet.tbsBasBank_M")) {		
				arg.processDefault=false;
				dorado.MessageBox.alert("含有融资性担保，需要选择银行",{title:"趣博信息科技"});
				return false;
				};	
			});
		
		updateActionSave.execute();
		self.set("parameter",{docid:docid,projid:projid,psid:psid,taskid:taskid,outcome:outcome,opinion:opinion});
	}else {
			outcome = view.id("OpinionAutoform").get("entity").outcome;
			if(!outcome){
				dorado.MessageBox.alert("请先选择【审批结果】", {title : "趣博信息科技"});	
				throw "exit" ;
			}else {
				self.set("confirmMessage","您确定发送审批？");
				updateActionSave.execute();
				self.set("parameter",{docid:docid,projid:projid,psid:psid,taskid:taskid,outcome:outcome,opinion:opinion});
			}
	};
};

/*==============Close===================*/
/** @Bind #ajaxActionAppr.onSuccess */
/** @Bind #buttonClose.onClick */
!function(self,arg){
	//var entity=view.id("OpinionAutoform").get("entity");
	window.parent.closeProcessDialog('${request.getParameter("type")}'); 
	//dataSetTbsProjHtsh.get("data:#").cancel();
};

/*=============本次承保金额和日期判断=====crs表示CurrentRecordSets==========*/
/** @Bind #appfaloc.onPost */
/** @Bind #appnfaloc.onPost */
/** @Bind #appotloc.onPost */
!function(self,arg,ddlAutoform,buttonResend,buttonAppr,apptotloc,dataSetTbsProjundwrt){
	var crs = ddlAutoform.get("entity"); 
	var dataSet = dataSetTbsProjundwrt.getData("#");
	var appfaloc = crs.get("appfaloc"); var appnfaloc = crs.get("appnfaloc"); var appotloc = crs.get("appotloc"); 
	var vfaloc = crs.get("tbsProj.vfaloc"); var vnfaloc = crs.get("tbsProj.vnfaloc"); var votloc = crs.get("tbsProj.votloc");
	if (appfaloc == 0 && appnfaloc == 0 && appotloc == 0 ) {
		buttonResend.set("disabled",true);
		buttonAppr.set("disabled",true);
		dorado.MessageBox.alert("对不起，【本次承保金额】不能都为 【0.00】 ",{title:"趣博信息科技"});
	}else if (appfaloc > vfaloc || appnfaloc > vnfaloc || appotloc > votloc ){
		buttonResend.set("disabled",true);	
		buttonAppr.set("disabled",true);
		dorado.MessageBox.alert("对不起，您输入的【本次承保金额】 大于【当前可用授信额度】，请重新录入！",{title:"趣博信息科技"});
	}else{
	    dataSet.set("apptotloc",appfaloc+appnfaloc+appotloc);
	    buttonResend.set("disabled",false);
	    buttonAppr.set("disabled",false);
	};
};

///** @Bind #undbdate.onPost */
///** @Bind #by3.onPost */
/*
!function(self,arg,ddlAutoform,buttonResend,apptotloc,dataSetTbsProjundwrt){
	var crs = ddlAutoform.get("entity"); 
	var bdate = crs.get("bdate"); var edate = crs.get("edate");
	var undbdate = crs.get("undbdate"); //var undedate = crs.get("undedate");
	var by3 = crs.get("by3");
	if (undbdate < bdate || undbdate > edate){  //undedate < bdate || undedate > edate
		buttonResend.set("disabled",true);
		dorado.MessageBox.alert("对不起，您输入的项目【承保开始日期】范围， 超出了【授信日期】的范围，请重新录入！",{title:"趣博信息科技"});	
	}else if (by3 <= 0){
		buttonResend.set("disabled",true);
		dorado.MessageBox.alert("对不起，【承保期限（月）】必须输入一个大于零的数字！",{title:"趣博信息科技"});
	}else{
		buttonResend.set("disabled",false);
	}
};*/

/** @Bind #gatrateu.onPost */
/** @Bind #gatrate.onPost */
/** @Bind #appotloc.onPost */
/** @Bind #tabCN.onClick */
!function(self,arg,dataSetTbsProjundwrt){
	var cns = view.get("#cnAutoform").get("entity"); 
	var month = dataSetTbsProjundwrt.getData("#.by3");
	var apptotloc = dataSetTbsProjundwrt.getData("#.apptotloc");
	var gatrateu = cns.get("gatrateu");
	switch(gatrateu)
	{
	case "‰/月":
		var gatrate = cns.get("gatrate");
		var gatreckon = gatrate*apptotloc*month/1000;
		cns.set("gatreckon",gatreckon);
	  break;
	case "‰/笔":
		var gatrate = cns.get("gatrate");
		var gatreckon = gatrate*apptotloc/1000;
		cns.set("gatreckon",gatreckon);
	  break;
	case "%/年":
		var gatrate = cns.get("gatrate");
		var gatreckon = gatrate*apptotloc*month/1200;
		cns.set("gatreckon",gatreckon);
	  break;
	}
};

/*=======================Cgg表单查询按钮===================*/
/** @Bind #buttonQueryCgg.onClick */
!function(self,arg,dataSetTbsProjCgg,dataSetTbsProjundwrt,DialogTbsProjCgg){
	//获取autoformCondition绑定的实体对象
	var entity = dataSetTbsProjundwrt.getData("#").get("tbsProj.id");
	//将实体对象作为参数传入，并异步刷新
	dataSetTbsProjCgg.set("parameter",entity).flushAsync();
	DialogTbsProjCgg.show();
};


/**
 * 文件处理 开始
 */

/** @Bind #tabAttach.onClick */
!function(self,arg,iFrameAttach,dataSetTbsProjundwrt){
	var title = "projundwrt";
	var projsn = dataSetTbsProjundwrt.getData("#").get("projSn");
	var fid ="承保审批";
	var attachpath="org.tbs.views.funs.MyFile.d?by1=" + title 
			+ "&by2=" + projsn
			+ "&by3=" + null
			+ "&fid=" + fid
			+ "&readonly=" + readonly
			+ "&typid=" + null
			+ "&dt=" + new Date();
			iFrameAttach.set("path",attachpath);
};


/**
 * 文件处理  结束
 */
//=========双击显示反担保详细信息==========
/** @Bind #dgTbsProjCgg.onDataRowDoubleClick */
!function() {
	var path = "";
	var entity = view.get("#dgTbsProjCgg").getCurrentEntity("entity").get("cggStr");
	var cggSn = view.get("#dgTbsProjCgg").getCurrentEntity("entity").get("cggSn");
	var cat1name;
	var cat2name;
	var cat3name;
	var customerId = view.get("#dataSetTbsProjundwrt").getData("#").get("tbsCustomer").get("id");;
	if(entity[0]){
	cat1name = entity[0].cat1name;
	cat2name = entity[0].cat2name;
	cat3name = entity[0].cat3name;
	}else{
		cat1name = entity.cat1name;
		cat2name = entity.cat2name;
		cat3name = entity.cat3name;
	}	
	if (cat3name) {
		path = "org.tbs.views.funs.CggDetail.d?fromAppr=0&customerId="
				+ customerId + "&cat=" + cat3name + "&cggSn=" + cggSn;
	} else if (cat2name) {
		path = "org.tbs.views.funs.CggDetail.d?fromAppr=0&customerId="
				+ customerId + "&cat=" + cat2name + "&cggSn=" + cggSn;
	} else if (cat1name) {
		path = "org.tbs.views.funs.CggDetail.d?fromAppr=0&customerId="
				+ customerId + "&cat=" + cat1name + "&cggSn=" + cggSn;
	}
	view.get("#dialogCggMaintain").show();
	view.get("#iFrameCggMaintain").set("path", path);
};

/** @Bind #dataGridHis.onDataRowDoubleClick */
!function(self) {
	var path = "org.tbs.views.funs.MyTbsFunApprc.d?id=";
	var taskHisId = self.getCurrentItem().get("id");
	view.get("#DialogTbsFunApprc").show();
	view.get("#iFrameTbsFunApprc").set("path", path+taskHisId);
};

/*==========bizvtloc===============*/
/** @Bind #biztype.onTextEdit */
!function(self,arg,tbsBasBizvar,dataSetTbsProjundwrt){
	var bizvtDS = dataSetTbsProjundwrt.getData("#.tbsProjundwrtBizvtSet");
	tbsBasBizvar.set("readOnly", false);
	bizvtDS.current.set("tbsBasBizvar","");
	bizvtDS.current.set("loc",0);
};
/** @Bind #ddlTbsBasBizvar.beforeExecute */
!function(self,arg,tbsBasBizvar,datasetTbsBasBizvar,DialogDataGridTbsProjundwrtBizvt){
	var bizid = DialogDataGridTbsProjundwrtBizvt.getCurrentEntity().get("tbsBasBiztype").get("id");
	datasetTbsBasBizvar.set("parameter",bizid).flushAsync();
	//tbsBasBizvar.set("readOnly", true);
};



/** @Bind #bizvtloc.onTextEdit */
!function(self,arg,dataSetTbsProjundwrt,appfaloc,appnfaloc,appotloc,bizvtloc,tbsBasBizvar){
	var newloc = bizvtloc.get("text");
	var bizvtDS = dataSetTbsProjundwrt.getData("#.tbsProjundwrtBizvtSet");
	if (newloc){
	bizvtDS.current.set("loc",newloc);
	}else{
		bizvtDS.current.set("loc",0);  //一定要设置0，否则删除后计算会不对
	};
	BizvtCountting(dataSetTbsProjundwrt,appfaloc,appnfaloc,appotloc,bizvtloc);
};

/** @Bind #DialogDataPilot_1.onSubControlAction */
!function(self,arg,dataSetTbsProjundwrt,datapilotTbsProjBizvt,appfaloc,appnfaloc,appotloc,bizvtloc){
	var code=arg.code;
	var bizvtDS = dataSetTbsProjundwrt.getData("#.tbsProjundwrtBizvtSet");
	debugger;
	switch (code) {
	case "-":
		arg.processDefault = false;
		dorado.MessageBox.confirm("您真的要删除此项目吗？",
				{title:"趣博信息科技",
				 detailCallback:function(btnID, text)
				 { if (btnID == "yes")
				 	{bizvtDS.current.set("loc",0);    
				 	 bizvtDS.remove();
				 	 BizvtCountting(dataSetTbsProjundwrt,appfaloc,appnfaloc,appotloc);	                                                    
				 	}
				 } 	
				}
		);
		break;
	}
};

function BizvtCountting(ds,appfaloc,appnfaloc,appotloc,bizvtloc){
	var bizvtDS = ds.getData("#.tbsProjundwrtBizvtSet");
	var projDS = ds.getData("#");
	var sum1 = 0; 
	var sum2 = 0; 
	var sum3 = 0;
	var del1 = 1; var del2 = 1; var del3 = 1; //表示全部删光
	bizvtDS.each(function(data){
			if (data.get("tbsBasBiztype.name") == "融资性担保") {		
				sum1 += data.get("loc")-0;
				projDS.set("appfaloc",sum1);
				del1 = 0;
			};
			if (data.get("tbsBasBiztype.name") == "非融资性担保") {		
				sum2 += data.get("loc")-0;
				projDS.set("appnfaloc",sum2);
				del2 = 0;
			};
			if (data.get("tbsBasBiztype.name") == "其他") {
				sum3 += data.get("loc")-0;
				projDS.set("appotloc",sum3);
				del3 = 0;
			}
	});
	projDS.set("apptotloc",sum1+sum2+sum3);
	//全部删除的处理
	if(del1 == 1){
		projDS.set("appfaloc",0);
	}
	if (del2 == 1) {
		projDS.set("appnfaloc",0);
	}
	if (del3 == 1) {
		projDS.set("appotloc",0);
	}
	/*if((sum1+sum2+sum3)!=0){
		view.get("#btnSave").set("disabled",false);
	}else{
		view.get("#btnSave").set("disabled",true);
	}*/
	return null;
};
