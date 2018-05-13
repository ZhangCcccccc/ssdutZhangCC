//小组作业上传附件
function upAttachment(tAssignmentId,tcoursesiteId){
	//显示上传窗口
	$("#upAttachment").fadeIn(100);		
	//小组作业上传附件	
	$("#upWkUploadUploadifyPic").uploadify({
		'auto' : true, 		
		'formData':{'tAssignmentId':tAssignmentId,'tcoursesiteId':tcoursesiteId},    //传值
    	'swf': $("#contextPath").val()+'/swfupload/swfupload.swf',  
    	'uploader':$("#contextPath").val()+'/tcoursesite/assignment/fileUpload;jsessionid='+$("#sessionId").val(),		
    	'fileSizeLimit' : 0, //以字节为单位，0为不限制。1MB:1*1024*1024
        'buttonText':'选择文件',
        'cancelImage':'/swfupload/uploadify-cancel.png',
        'simUploadLimit' : 88, // 一次同步上传的文件数目
    	'onUploadError':function(file, errorCode, errorMsg, errorString) {
        	//alert("上传失败，请检查文件大小和格式！");
    	} ,
    	'onUploadSuccess' : function(file, data, response) {
    		var suffix = file.name.substring(file.name.indexOf(".")+1);
    		var iconName;
    		var suffixList = ".chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls";
    		if(suffixList.indexOf(suffix) == -1){
    			iconName = "default.png";
    		}else{
    			iconName = suffix+".gif";
    		}
    		$("#groupAssignmentsNameList").append("<li class=\"l w25p\" id=\"attach" + data + "\">" + 
    				"<input type='checkbox' id=\"attachment" + data + 
    				"\" name='checknames' value=\"attachment" + data + 
    				"\" /><label for=\"attachment" + data + 
    				"\" ></label><img src=\""+$("#contextPath").val()+"/images/fileTypeImages/icon_"+
    				iconName +"\" alt=\"\" /><span>" + file.name + "</span></li>");
    		var cur=$("#groupAssignmentsList").val();
    		$("#groupAssignmentsList").val(cur+data+",");
		},
    	onQueueComplete : function(event, data) {//当队列中的所有文件全部完成上传时触发　 
    		$("#upAttachment").fadeOut(100);
		}
	});
}

//删除上传文件
function deleteAttachment(){
	var idList="";
	//获取选中文件id
	 $("#groupAssignmentsNameList").find("input[type='checkbox']").each(function () {//遍历页面所有checkbox
		 if(this.checked){//如果多选框被选中
			 idList += this.value.replace("attachment","")+",";
			 $("#attach"+this.value.replace("attachment","")).remove();
		 }
	});
	var cur=$("#groupAssignmentsList").val();
	cur = cur.replace(idList,"");
	$("#groupAssignmentsList").val(cur);
}

//批量下载附件
function downloadAttachments(tcoursesiteId,tAssignmentId){
	var idList="";
	//获取选中文件id
	 $("#groupAssignmentsNameList").find("input[type='checkbox']").each(function () {//遍历页面所有checkbox
		 if(this.checked){//如果多选框被选中
			 idList += this.value.replace("attachment","")+",";
		 }
	});
	 //if nothing is checked
	 if(idList == ""){
		 return null;
	 }
	 window.location.href = $("#contextPath").val()+"/tcoursesite/assignment/downloadAttachments?tcoursesiteId="+tcoursesiteId+"&tAssignmentId="+tAssignmentId+"&attachList="+idList+"";
}
