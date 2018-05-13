//新增或编辑参考文件文件夹
function newDocumentFolder(chapterType,chapterId,lessonId,parentFolderId,folderId){
	//id不为1时为编辑
	changeLessonsByChapterId(chapterId);
	if(folderId != -1){
		$.ajax({
    		type: "POST",
    		url: $("#contextPath").val()+"/tcoursesite/editDocumentFolder",
    		data: {'folderId':folderId},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){  
    				//alert(key+"="+values);
    				//document.getElementById(key).innerHTML=values;
    				if(key=="documentsNameList"){
    					document.getElementById(key).innerHTML=values;
    				}else{
    					$("#"+key).val(""+values);
    				}
    			 }); 
    		},
    		error:function(){
    			alert("信息错误！");
    			}
    	});
	}else{
		document.getElementById("documentsList").innerHTML="";
		document.getElementById("documentsNameList").innerHTML="";
		$("#documentFolderId").val("");
		$("#documentFolderName").val("");
	}
	changeDocumentLessonsByChapterId(chapterId)
	$("#editDocumentFolder").fadeIn(100);
	$("#documentChapterType").val(chapterType);
	$("#wkChapterDocumentId").val(chapterId);
	$("#wkLessonDocumentId").val(lessonId);
	$(".select_chosen").trigger("liszt:updated");
	
	$('.select_chosen').chosen();
	$('#documentChapterType').chosen();
	//文件上传插件
	$("#DocumentUploadifyPic").uploadify({
		'auto' : true, 		
		'formData':{type:2},    //传值
    	'swf': $("#contextPath").val()+'/swfupload/swfupload.swf',  
    	'uploader':$("#contextPath").val()+'/tcoursesite/fileUpload;jsessionid='+$("#sessionId").val(),		
    	'fileSizeLimit' : 0, //以字节为单位，0为不限制。1MB:1*1024*1024
        'buttonText':'选择文件',
        'cancelImage':'/swfupload/uploadify-cancel.png',
        'simUploadLimit' : 88, // 一次同步上传的文件数目
    	'onUploadError':function(file, errorCode, errorMsg, errorString) {
        	//alert("上传失败，请检查文件大小和格式！");
    	} ,
    	'onUploadSuccess' : function(file, data, response) {
    		//alert("文件："+file.name+"  上传成功");
    		$("#documentsNameList").append("<li class='pic_list hg9 lh35 ptb5 rel ovh' id='document"+data+"'>" +
					        				"<div class='cf rel zx1 z c_category'>" +
					        				"<div class='l mlr15 cc1 c_tool poi'>" +
					        				file.name +
					        				"<i class='fa fa-trash-o g9 f14 mr5 poi' onclick='deleteThisDocument("+data+")'></i>"+
					        				"</div></div></li>")
    		var cur=$("#documentsList").val();
    		$("#documentsList").val(cur+data+",");
    		
		},
    	onQueueComplete : function(event, data) {//当队列中的所有文件全部完成上传时触发　 
		}
	});
	
}

//提交文件夹时检测是否选择章节
function submitDocumentFolder() {
	var chapterId = $("#wkChapterDocumentId").val();
	if(chapterId==-1){
		alert("请选择章节");
		return false;
	}
	return true;
}

//根据分类获取对应的章节列表选项
function changeDocumentChaptersByModuleType(tCourseSiteId,moduleType){
	var options = changeChaptersByModuleType(tCourseSiteId,moduleType);
	document.getElementById("wkChapterDocumentId").innerHTML=options;
	var lessonOptions = "<option value='-1'>请选择</option>";
	document.getElementById("wkLessonDocumentId").innerHTML=lessonOptions;
	$(".select_chosen").trigger("liszt:updated");
}
//根据章节回去对应的课时列表选项
function changeDocumentLessonsByChapterId(chapterId){
	var options = changeLessonsByChapterId(chapterId);
	document.getElementById("wkLessonDocumentId").innerHTML=options;
	$(".select_chosen").trigger("liszt:updated");
}
//上传文件中删除
function deleteThisDocument(id){
	//去除隐藏标签中文件id列表的待删除文件的id
	var cur=$("#documentsList").val();
	var tCourseSiteId=$("#tCourseSiteId").val();
	var moduleType=$("#moduleType").val();
	$("#documentsList").val(cur.replace(id+',',''));
	$("#document"+id).remove();
	if(confirm("是否确认删除？"))
	   {
		$.ajax({
			async: false,
			type: "POST",
			url: $("#contextPath").val()+"/tcoursesite/deleteFile",
			data: {'id':id,'tCourseSiteId':tCourseSiteId,'moduleType':moduleType},
			dataType:"text",
			success:function(data){
				$("#"+id).remove();
			},
			error:function(){
				alert("删除失败！");
				}
		});
	   }
}