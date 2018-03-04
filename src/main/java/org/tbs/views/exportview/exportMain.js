
var postjson= {
		ureportFileName:"${request.getParameter('ureportFileName')}",//xxxx.ureport.xml
		beginDateProperty:"${request.getParameter('beginDateProperty')}",//ture or false
		endDateProperty:"${request.getParameter('endDateProperty')}",//ture or false
		projNameProperty:"${request.getParameter('projNameProperty')}",//ture or false
		exportFileName:"${request.getParameter('exportFileName')}",
		type:"${request.getParameter('type')}"//[1,6]
};


/** @Bind view.onReady */
!function(self,arg){
	if(postjson.projNameProperty =="false"){
		view.get("#formCondition").getElement("projname").set("visible",false);
	}
	if(postjson.beginDateProperty=="false"){
		view.get("#formCondition").getElement("beginDate").set("visible",false);
	}
	if(postjson.endDateProperty=="false"){
		view.get("#formCondition").getElement("endDate").set("visible",false);
	}
	debugger;
};


/** @Bind #preview.onClick */
!function(self){
var param = view.get("#formCondition.entity");
var proj_name = param.get("projname");
var bd = param.get("beginDate");
var ed = param.get("endDate");
var beginDate;
var endDate;
if(!proj_name){
	proj_name = '';
}
if(bd){
	beginDate = bd.getFullYear() + '-' + (bd.getMonth() + 1) + '-' + bd.getDate()  ;
}else{
	beginDate = '1900-1-1';
}
if(ed){
	endDate = ed.getFullYear() + '-' + (ed.getMonth() + 1) + '-' + ed.getDate()  ;
}else{
	endDate = '2999-12-31';
}
debugger;
var subViewHolderExport = view.get("#subViewHolderExport");
//subViewHolderExport.set("path","./Export_Daily.jsp?bdate="+beginDate+"&edate="+endDate);
subViewHolderExport.set("path",
	"./ureport/preview?_t="+postjson.type
	+"&_u=file:"+postjson.ureportFileName
	+"&_n="+postjson.exportFileName
	+"&proj_name="+proj_name
	+"&beginDate="+beginDate+"&endDate="+endDate);
};

