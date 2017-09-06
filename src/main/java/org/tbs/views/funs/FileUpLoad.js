/**
 * @author Hank
 */

/*============================单个文件上传=====================*/
/**@Bind #singleUploadAction.onFilesAdded */
!function(self, arg){	
	arg.files.each(function(file){
		if (file.size < 10000000) {  //小于10MB才加入list   10485760
			return;
		}else{
			dorado.MessageBox.alert("文件 【"+file.name+"】 \n 大小超过10MB \n 请重新选择！",{title:"趣博信息科技"});
			quit;
		}
	});
};
/** @Bind #singleUploadAction.onUploadProgress */
!function(arg, aupb) {
	aupb.set("value", arg.total.percent);
};
/** @Bind #singleUploadAction.onFileUploaded */
!function(arg,self,upbutton,resetButton) {
	var info = arg.returnValue;
	var filename = info.fileName;
    //var fileurl = info.absolutePath;
	upbutton.set("visible",false);
	resetButton.set("visible",true);
	dorado.MessageBox.alert("文件 【"+filename+"】 \n上传成功!",{title:"趣博信息科技"});
	quit;
};
/** @Bind #resetButton.onClick */
!function(arg,self,aupb,upbutton){
	aupb.set("value", 0);
	upbutton.set("visible",true);
	self.set("visible",false);
};


/*=======================多个文件上传===================*/
/** @Bind #btnadd.onClick */
!function(self, btnstart,btndel){
	btnstart.set("disabled", false);
	btndel.set("disabled", false);
};
//@Bind #multiFileUpload.onFilesAdded
!function(arg, dsFiles1){	
	arg.files.each(function(file){
		if (file.size < 10000000) {  //小于10MB才加入list   10485760
 			dsFiles1.insert({
			id : file.id,
			name : file.name,
			size : file.size,
			percent : file.percent,
			status : file.status
		});
 		//dorado.MessageBox.alert(file.id+" "+file.name);
		}else{
			dorado.MessageBox.alert("文件 【"+file.name+"】 \n 大小超过10MB \n 请重新选择！",{title:"趣博信息科技"});
		}
	});
};
/** @Bind #gridFiles1.onCreate */
!function(self){
	self.set("&percent.renderer", new ProgressCellRenderer());
};
var prettySize = function(value) {
	var _format = function(value, unit) {
		return (value.toFixed(1) + ' ' + unit).replace('.0', '');
	};
	var K = 1024;
	var M = K * K;
	var G = M * K;
	var T = G * K;
	var dividers = [ T, G, M, K, 1 ];
	var units = [ 'TB', 'GB', 'MB', 'KB', 'B' ];
	if (value == 0) {
		return '0B';
	} else if (value < 0) {
		return 'Invalid size';
	}
	var result = '';
	var temp = 0;
	for ( var i = 0; i < dividers.length; i++) {
		var divider = dividers[i];
		if (value >= divider) {
			temp = value / divider;
			if (temp < 1.05) {
				result = _format(value,
						units[((i + 1) < units.length) ? (i + 1) : i]);
			} else {
				result = _format(temp, units[i]);
			}
			break;
		}
	}
	return result;
};
/** @Bind #gridFiles1.#size.onRenderCell */
!function(arg){
	arg.dom.innerHTML = prettySize(arg.data.get('size'));
};
/** @Bind #multiFileUpload.onFileUploaded */
!function(arg, dsFiles1){
	var file = arg.file;
	var entity = dsFiles1.getData("[@.get('id')=='"+file.id+"']");
	entity.set('percent', 100);
	entity.set('status', file.status);
	//dorado.MessageBox.alert(file.name);
};
/** @Bind #btndel.onClick */
!function(self,multiFileUpload, gridFiles1,btnadd,rsc){
	var selections = gridFiles1.get("selection");
	if(!selections){
		return;
	}
	if(!selections.length){
		selections = [selections];
	}
	var selectionCopy = [];
	for (var i = 0; i < selections.length; i++) {
		selectionCopy.push(selections[i]);
	}
	for(var i=0;i<selectionCopy.length;i++){
		var entity = selectionCopy[i];
		multiFileUpload.removeFile(entity.get('id'));
		entity.remove();
	}
	btnadd.set("disabled",false);
	rsc.set("visible",true);
	self.set("caption","删除文件");
};
/** @Bind #btnstart.onClick */
!function(self, btnstop, multiFileUpload){
	btnstop.set("disabled", false);
	multiFileUpload.start();
};
/** @Bind #btnstop.onClick */
!function(self, multiFileUpload){
	self.set("disabled", true);
	multiFileUpload.stop();
};
var ProgressCellRenderer = $extend(dorado.widget.grid.SubControlCellRenderer, {
    createSubControl: function(arg) {
        return new dorado.widget.ProgressBar({
            value:arg.data.get("percent")
        });
    },

    refreshSubControl: function(progressBar, arg) {
		progressBar.set("value", arg.data.get("percent"));
    }
});
/** @Bind #multiFileUpload.onError */
!function(self, arg, btnstop){
	btnstop.set("disabled", true);
	return false;
};
/** @Bind #multiFileUpload.onUploadComplete */
!function(arg,self,btnstart,btnstop,btnadd,gridFiles1,rsc,btndel){
	var filenames= "";
	arg.files.each(function(file){
		filenames=filenames+"【"+file.name+"】\n";
	});	
	btnstart.set("disabled", true);
	btnstop.set("disabled", true);
	if (filenames != ""){
		dorado.MessageBox.alert("文件上传成功!\n"+filenames,{title:"趣博信息科技"});	
		gridFiles1.selectAll();
		rsc.set("visible",false);
		btnadd.set("disabled",true);
		btndel.set("caption","清空上传队列");
	}else{
		dorado.MessageBox.alert("没有文件需要上传！\n",{title:"趣博信息科技"});
	}
	
	
};