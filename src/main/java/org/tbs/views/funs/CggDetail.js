//Global variables
var lastContainerObj;
var datagridCggObj;
var currentUpdateAction;
var objId;
var datasetCggObj;


/** @Bind view.onReady */
!function(self,arg,getCggTypIdAction){
	var catname = "${request.getParameter('cat')}";
	getCggTypIdAction.set("parameter", {"catname":catname}).execute();
};

/** @Bind #getCggTypIdAction.onSuccess */ 
!function(self){
	objId = self.get("returnValue");  // 获取CggTypId
	var customerId = "${request.getParameter('customerId')}";
	var cggSn = "${request.getParameter('cggSn')}";
	var customer = {$dataType : "v:org.tbs.views.funs.CggDetail$datatypeTbsCustomer", id : customerId };
	//var objId = currentEntity.get("id");
	var containerObj = view.get("#cgg" + objId);
	datagridCggObj = view.get("#datagridTbsCgg" + objId);
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
			if (customer && customer != ""){
				//grant & show new cgg container
				lastContainerObj = containerObj;
				containerObj.set("visible",true);
				var param = {
						cggId: objId,
						entity: {tbsCustomer:customer}
				};
				//refresh dataset, get data from DB
				datasetCggObj.set("parameter",param).flushAsync(function(entity){

					datagridCggObj.filter([{
					    property:"sn", 
					    operator:"=", 
					    value:cggSn
					}]);
				});

			}
		}
	}
				
};


