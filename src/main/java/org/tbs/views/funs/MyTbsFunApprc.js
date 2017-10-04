var id = "${request.getParameter('id')}";
/** @Bind #dataSetHistoryTask.onReady */
!function(self){
	self.set("parameter",id).flushAsync();
};