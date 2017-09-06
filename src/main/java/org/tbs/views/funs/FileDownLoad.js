/** @Bind #flsDataPilot.onSubControlAction */
!function(self, arg, dataSetTbsFunFul) {
	var dataset =  dataSetTbsFunFul.getData("#");
	var valid = dataset.get("valid");
	switch (arg.code) {
	case "-":
		arg.processDefault = false;
		if (valid == 0){
			dataset.remove();
		}else{
			dorado.MessageBox.alert("对不起，您要删除的文件已经通过审批！", {
				title : "趣博信息科技"
			});
		}
		break;
	}
};

/** @Bind #fname.onRenderCell */
!function(arg, self) {
	var fname = arg.data.get("fname");
	var url = arg.data.get("url"); 
	arg.dom.innerHTML = "<a href="+url+" target='_blank' title='点击下载'>"+fname+ "</a>";
};



/** @Bind #dataGridTbsFunFul.onCreate */
/*
 * function global_fname(text,gid) { var gvar=text; var sid=gid; if (sid == 1){
 * return gvar; }; //dorado.MessageBox.alert(nvar);
 *  }
 * 
 * !function (arg,self){ var OperationCellRenderer =
 * $extend(dorado.widget.grid.SubControlCellRenderer, { createSubControl:
 * function(arg) { //创建单元格内部的DOM元素 if (arg.data.rowType) return null;
 * //返回按钮对象并为按钮对象绑定onClick事件 return new dorado.widget.Button({ onClick :
 * function(self) { //获取当前员工对象 var entity = arg.data; //在点击按钮后，获取文件名称 var fname =
 * entity.get("fname"); view.global_fname(fname,0);
 * 
 * view.get("#downloadAction").execute();
 * 
 * var fn=dataSetTbsFunFul.querydata("#.fname"); dorado.MessageBox.alert(fn);
 * view.get("#updateactionSave").execute(function(){
 * dorado.widget.NotifyTipManager.notify(entity //右下角的操作提示 .get("employeeName")+
 * "的密码已经重置!");}) } }); },
 * 
 * refreshSubControl: function(button, arg) {
 * //当渲染单元格时触发，在这个事件中可以通过arg.data获取当前的实体对象 button.set({caption: "下载文件",
 * disabled: false }); // var password = arg.data.get("password");
 * //设置按钮的标题，假如员工密码为默认值则按钮显示未修改密码，若员工密码已经修改则按钮显示重置密码 //如果员工密码为默认则按钮不可用
 * //button.set({ // caption : (password != "123456") ? "重置密码" : "未修改密码", //
 * disabled : (password == "123456") // }); } });
 * 
 * self.set("#download.renderer", new
 * OperationCellRenderer());//最后设置名称为download的DataColumn的渲染器为自定义的OperationCellRenderer };
 * 
 * 
 * /** @Bind #downloadAction.beforeExecute !function(arg,self){
 * view.global_fname(fname,1); var fname="取不到文件名" ;
 * dorado.MessageBox.alert(fname);
 * 
 * self.set("parameter",{ fname: fileEditor.get("value") }); };
 */