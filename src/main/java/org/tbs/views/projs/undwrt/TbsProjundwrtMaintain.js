// Global variables
var psid;
var valid;
var docid;
var projid;
var docsn;
var loginUser = "${loginUsername}";
var by1;
var processInstanceId;
var uploadbutton = "disable";
var downloadbutton = "disable";
var readonly = false;

/*======获取当前这条记录的状态======*/
function GetCRStatus(ds){
	var CurRec = ds.getData("#");
	docid = CurRec.get("id");
	projid = CurRec.get("tbsProj.id");
	valid = CurRec.get("valid");
	psid=CurRec.get("tbsProj.tbsBasPs.id");
	docsn=CurRec.get("sn");
	by1=CurRec.get("by1");
	processInstanceId = CurRec.get("pid");
}

/*=======控制按钮,通过审批和审批中的禁止修改和删除和再次发起审批===*/
/** @Bind #Main.onDataRowClick */
/** @Bind #dataSetTbsProjundwrt.onLoadData */
!function(self,arg,dataSetTbsProjundwrt,buttonDel,buttonUflo,buttonAdminsign,buttonUfloN,upbutton,dataSetHistoryTask){
	GetCRStatus(dataSetTbsProjundwrt);
    if (valid == 0 && psid == 23 ){
    	buttonDel.set("disabled",false);
    	buttonUflo.set("disabled",false);
    	dataSetTbsProjundwrt.set("readOnly",false); 
    	//dataSetTbsFunFul.set("readOnly",false); 
    	readonly = "false";
    	//upbutton.set("disabled",false);
    	uploadbutton = "";
    	view.get("#ddlAutoform").get("entity").set("keyinId","${dorado.getDataProvider('el#Uid').getResult()}");
    }else{
    	buttonDel.set("disabled",true);
    	buttonUflo.set("disabled",true);
    	dataSetTbsProjundwrt.set("readOnly",true);
    	//dataSetTbsFunFul.set("readOnly",true);
    	readonly = "true";
    	//upbutton.set("disabled",true);
    	uploadbutton = "";
    }
   
    if (valid == 1 && (psid == 18 || psid == 16) ){  //再次承保按钮
    	buttonUfloN.set("disabled",false);
    }else{
    	buttonUfloN.set("disabled",true);
    }

    if (valid == 0 && psid == 38 ){  // 再次承保单生成
    	buttonDel.set("disabled",false);
    	buttonUflo.set("disabled",false);
    	dataSetTbsProjundwrt.set("readOnly",false); 
    	//dataSetTbsFunFul.set("readOnly",false); 
    	readonly = "false";
    	//upbutton.set("disabled",false);
    	uploadbutton = "";
    }
    
    if ( psid == 9 || psid == 20 ){ //再次承保中
    	buttonDel.set("disabled",true);
    	buttonUflo.set("disabled",true);
    	dataSetTbsProjundwrt.set("readOnly",true);
    	//dataSetTbsFunFul.set("readOnly",true);
    	readonly = "true";
    	//upbutton.set("disabled",true);
    	uploadbutton = "disable";
    }
   
    //histask
    dataSetHistoryTask.set("parameter",processInstanceId).flushAsync();
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

/*=============本次承保金额和日期判断=====crs表示CurrentRecordSets==========*/
/** @Bind #appfaloc.onPost */
/** @Bind #appnfaloc.onPost */
/** @Bind #appotloc.onPost */
!function(self,arg,ddlAutoform,buttonSave,apptotloc,dataSetTbsProjundwrt){
	var crs = ddlAutoform.get("entity"); 
	var dataSet = dataSetTbsProjundwrt.getData("#");
	var appfaloc = crs.get("appfaloc"); var appnfaloc = crs.get("appnfaloc"); var appotloc = crs.get("appotloc");
	var vfaloc = crs.get("tbsProj.vfaloc"); var vnfaloc = crs.get("tbsProj.vnfaloc"); var votloc = crs.get("tbsProj.votloc");
	if (appfaloc == 0 && appnfaloc == 0 && appotloc == 0 ) {
		//buttonSave.set("disabled",true);
		dorado.MessageBox.alert("对不起，【本次承保金额】不能都为 【0.00】 ",{title:"趣博信息科技"});
	} else if (appfaloc > vfaloc || appnfaloc > vnfaloc || appotloc > votloc){
		dorado.MessageBox.alert("对不起，您输入的【本次承保金额】 大于【当前可用授信额度】，请重新录入！",{title:"趣博信息科技"});
		buttonSave.set("disabled",true);		
	}else{
	    dataSet.set("apptotloc",appfaloc+appnfaloc+appotloc);
	    buttonSave.set("disabled",false);
	};
};

///** @Bind #undbdate.onPost */
///** @Bind #by3.onPost */
/*!function(self,arg,ddlAutoform,buttonSave,apptotloc,dataSetTbsProjundwrt){
	var crs = ddlAutoform.get("entity"); 
	var bdate = crs.get("bdate"); var edate = crs.get("edate");
	var undbdate = crs.get("undbdate"); //var undedate = crs.get("undedate");
	var by3 = crs.get("by3");
	if (undbdate < bdate || undbdate > edate){  //undedate < bdate || undedate > edate
		dorado.MessageBox.alert("对不起，您输入的项目【承保开始日期】范围， 超出了【授信日期】的范围，请重新录入！",{title:"趣博信息科技"});
		buttonSave.set("disabled",true);	
	}else if (by3 <= 0){
		dorado.MessageBox.alert("对不起，【承保期限（月）】必须输入一个大于零的数字！",{title:"趣博信息科技"});
		buttonSave.set("disabled",true);
	}else{
		buttonSave.set("disabled",false);
	}
};*/

/*========审批历史创建时刷新=========*/
/** @Bind #dataSetHistoryTask.onCreate */
!function(self,arg){ 
	self.flushAsync();
	};

/*=======================dialog确认按钮===================*/
/** @Bind #buttonSave.onClick */
!function(self,arg,dialogEdit,dataSetTbsProjundwrt,updateAction){
	updateAction.execute({
		callback : function(result) {  //用回调方法是为了让字段的必填校验在界面上做出错提示
			if (result == true){    
				dataSetTbsProjundwrt.flushAsync();
				dialogEdit.hide();
			}
		}
	});
};

/*=======================dialog取消按钮-清空当前数据操作===================*/
/** @Bind #buttonCancel.onClick */
!function(self,arg,dialogEdit,ddlAutoform,dataSetTbsProjundwrt){
	dialogEdit.hide();
	dataSetTbsProjundwrt.get("data:#").cancel();
};

/*============autoformCondition快速查询===================*/
/**@Bind @dataTypeConditions.onDataChange */
!function(self,arg,autoformCondition,dataSetTbsProjundwrt){
var entity = autoformCondition.get("entity");
dataSetTbsProjundwrt.set("parameter",entity).flushAsync();
};

/*=======================主表单查询按钮===================*/
/** @Bind #buttonQuery.onClick */
!function(self,arg,autoformCondition,dataSetTbsProjundwrt){
	//获取autoformCondition绑定的实体对象
	var entity = autoformCondition.get("entity");
	//将实体对象作为参数传入，并异步刷新
	dataSetTbsProjundwrt.set("parameter",entity).flushAsync();
};

/*=======================主表删除按钮===================*/
/** @Bind #buttonDel.onClick */
!function(self,arg,dataSetTbsProjundwrt){
	var entity = dataSetTbsProjundwrt.get("data:#");
	entity.remove();
};

/*=======================主表单修改按钮===================*/
/** @Bind #buttonEdit.onClick */
!function(self,arg,dialogEdit,tabMain){
	dialogEdit.show();
	tabMain.set("currentIndex", 0);
};

/*========保存后刷新，执行query相同的方法=========*/
/**@Bind #updateAction.onSuccess */
!function(self,arg,autoformCondition,dataSetTbsProjundwrt){ 
//获取autoformCondition绑定的实体对象
var entity = autoformCondition.get("entity");
//将实体对象作为参数传入，并异步刷新
dataSetTbsProjundwrt.set("parameter",entity).flushAsync();
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



/* =======================buttonUpload按钮dialogFlsmgm显示=================== 
*//** @Bind #buttonFlsmgm.onClick *//*
!function(self, arg, dialogFlsmgm,dataSetTbsProjundwrt,dataSetTbsFunFul) {
	var by2 = dataSetTbsProjundwrt.getData("#").get("projSn");
	dataSetTbsFunFul.set("parameter",by2).flushAsync();
	dialogFlsmgm.show();
};*/

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
			+ "&typid=" + null
			+ "&readonly=" + readonly
			+ "&uploadbutton=" + uploadbutton
			+ "&downloadbutton=" + downloadbutton
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

/*=======================发送审批===================*/
/** @Bind #buttonUflo.onClick */ 
!function(self,arg,dataSetTbsProjundwrt,Main,ajaxAction1){
	var entity = Main.getCurrentEntity("entity");
	var id_value = entity.get("id");
	var maParamers = {docid:id_value}; 
	ajaxAction1.set("parameter",maParamers).execute( function(result) {
		dorado.MessageBox.alert(result,{title:"趣博信息科技"});
	});
	//dataSetTbsProjundwrt.flushAsync();
}; 


/** @Bind #ajaxAction1.beforeExecute */ 
/** @Bind #updateAction.beforeExecute */ 
!function(self,arg,dataSetTbsProjundwrt,Main){
	var entity = Main.getCurrentEntity("entity");
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
		if(!data.get("tbsBasBizvar.name") ){
			arg.processDefault=false;
			dorado.MessageBox.alert("承保业务品种必填",{title:"趣博信息科技"});
			return false;
			};	
		if(!data.get("loc") ){
			arg.processDefault=false;
			dorado.MessageBox.alert("承保金额不能为0，如果不需要此业务大类，可以点击删除按钮",{title:"趣博信息科技"});
			return false;
			};	
		});
	
};

/*=======再承保 start=======*/
/** @Bind #buttonUfloN.onClick */
!function(self,arg,dataSetTbsProjundwrt,ajaxUfloN,dialogUfloN){
	if (docsn){
	dorado.MessageBox.confirm("此操作将产生一张新的【承保审批单】\n您确定要再次承保该项目吗？",
			{title:"趣博信息科技",
			 detailCallback:function(btnID, text)
			 { if (btnID == "yes")
			 	{
				 var maParamers = {projid:projid,loginUser:loginUser,docsn:docsn};
				 ajaxUfloN.set("parameter",maParamers).execute(function(result){ 
					 											var Ndocsn = result.split(":")[1];
					 											dorado.MessageBox.alert("新的【承保审批单】已产生\n单据号：【"+Ndocsn+"】\n请补齐信息后【提交审批】",{title:"趣博信息科技"});
				 																});
				 dataSetTbsProjundwrt.flushAsync();
			 	}
			 } 	
			}
	);
	};
};

/*=======再承保 end=======*/

/** @Bind #dataGridHis.onDataRowDoubleClick */
!function(self) {
	var path = "org.tbs.views.funs.MyTbsFunApprc.d?id=";
	var taskHisId = self.getCurrentItem().get("id");
	view.get("#DialogTbsFunApprc").show();
	view.get("#iFrameTbsFunApprc").set("path", path+taskHisId);
};


/** @Bind #btnPrint.onClick */
!function(self){
	var path =  "承保审批表.ureport.xml"; 
	var id = view.get("#dataSetTbsProjundwrt").getData("#").get("id");
	//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp      
    var curWwwPath=window.document.location.href;      
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp      
    var pathName=window.document.location.pathname;      
    var pos=curWwwPath.indexOf(pathName);      
    //获取主机地址，如： http://localhost:8083      
    var localhostPaht=curWwwPath.substring(0,pos);      
    //获取带"/"的项目名，如：/uimcardprj      
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);      
    var pref = localhostPaht+projectName;
    window.open(pref+"/ureport/preview?_t=1,2,3,4,9&_u=file:"+path+"&id="+id);
};
