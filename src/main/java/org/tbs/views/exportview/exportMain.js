
var postjson= {
		ureportFileName:"${request.getParameter('ureportFileName')}",//xxxx.ureport.xml
		beginDateProperty:"${request.getParameter('beginDateProperty')}",//ture or false
		endDateProperty:"${request.getParameter('endDateProperty')}",//ture or false
		exportFileName:"${request.getParameter('exportFileName')}",
		type:"${request.getParameter('type')}"//[1,6]
};


/** @Bind view.onReady */
!function(self,arg){
	if(postjson.beginDateProperty){
		view.get("#formCondition").getElement("beginDate").set("visible",postjson.beginDateProperty);
	}
	if(postjson.endDateProperty){
		view.get("#formCondition").getElement("endDate").set("visible",postjson.endDateProperty);
	}
};


/** @Bind #preview.onClick */
!function(self){
var param = view.get("#formCondition.entity");
var bd = param.get("beginDate");
var ed = param.get("endDate");
var beginDate;
var endDate;
if(bd){
	beginDate = bd.getFullYear() + '-' + (bd.getMonth() + 1) + '-' + bd.getDate()  ;
}else{
	beginDate = '1900-1-1';
}
if(ed){
	endDate = ed.getFullYear() + '-' + (ed.getMonth() + 1) + '-' + ed.getDate()  ;
}else{
	endDate = '9999-12-31';
}
var subViewHolderExport = view.get("#subViewHolderExport");
//subViewHolderExport.set("path","./Export_Daily.jsp?bdate="+beginDate+"&edate="+endDate);
subViewHolderExport.set("path",
	"./ureport/preview?_t="+postjson.type
	+"&_u=file:"+postjson.ureportFileName
	+"&_n="+postjson.exportFileName
	+"&beginDate="+beginDate+"&endDate="+endDate);
};

