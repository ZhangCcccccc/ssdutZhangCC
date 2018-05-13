//新增或编辑视频文件夹
function newVideoFolder(chapterType,chapterId,lessonId,folderId){
	//folderId不为1时为编辑
	if(folderId != -1){
		$.ajax({
    		type: "POST",
    		url: $("#contextPath").val()+"/tcoursesite/editVideoFolder",
    		data: {'folderId':folderId},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){  
    				//alert(key+"="+values);
    				//document.getElementById(key).innerHTML=values;
    				document.getElementById(key).innerHTML=values;
    				$("#"+key).val(""+values);
    			 }); 
    		},
    		error:function(){
    			alert("信息错误！");
    			}
    	});
	}else{
		$("#videoId").val("");
		$("#videoName").val("");
		$("#videoFolderId").val("");
		$("#videoFolderName").val("");
		$("#videoUrl").val("");
	}
	changeVideoLessonsByChapterId(chapterId);
	$("#videoChapterType").val(chapterType);
	$("#wkChapterVideoId").val(chapterId);
	$("#wkLessonVideoId").val(lessonId);
	$(".select_chosen").trigger("liszt:updated");
	
	$('.select_chosen').chosen();
	
	
	$("#editVideoFolder").fadeIn(100);
	//视频上传插件
	$("#uploadify").uploadify({
		'auto' : true, 		
		'formData':{type:0},    //传值
    	'swf': $("#contextPath").val()+'/swfupload/swfupload.swf',  
    	'uploader':$("#contextPath").val()+'/tcoursesite/fileUpload;jsessionid='+$("#sessionId").val(),		
    	//提交的controller和要在火狐下使用必须要加的id
    	'buttonText':'<i class="fa fa-upload mr5"></i>选择MP4视频文件',
    	cancelImage:'/swfupload/uploadify-cancel.png',
    	'simUploadLimit' : 1, // 一次同步上传的文件数目
        'fileSizeLimit' : 314572800, //300mb,以字节为单位，0为不限制。1MB:1*1024*1024
        'queueSizeLimit' : 1, // 队列中同时存在的文件个数限制
        'fileTypeDesc' : '支持格式:mp4', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
        'fileTypeExts' : '*.mp4;',// 允许的格式
        'multi':false,
    	'onUploadError':function(file, errorCode, errorMsg, errorString) {
    		alert(file);
    		alert(errorCode);
    		alert(errorMsg);
    		alert(errorString);
        	alert("上传失败，请检查文件大小和格式！");
    	}, 
    	'onUploadSuccess' : function(file, data, response) {
    		//alert("文件："+file.name+"  上传成功");
    		//alert(data);
    		$("#videoId").val(data);//设置视频id
    		$("#videoName").text(file.name);//显示上传成功的视频的名字
		}, 
    	onQueueComplete : function(file, data) {//当队列中的所有文件全部完成上传时触发　
    		//alert("文件上传成功");
    		
		}
	});
}

//根据分类获取对应的章节列表选项
function changeVideoChaptersByModuleType(tCourseSiteId,moduleType){
	var options = changeChaptersByModuleType(tCourseSiteId,moduleType);
	document.getElementById("wkChapterVideoId").innerHTML=options;
	var lessonOptions = "<option value='-1'>请选择</option>";
	document.getElementById("wkLessonVideoId").innerHTML=lessonOptions;
	$(".select_chosen").trigger("liszt:updated");
}
//根据章节回去对应的课时列表选项
function changeVideoLessonsByChapterId(chapterId){
	var options = changeLessonsByChapterId(chapterId);
	document.getElementById("wkLessonVideoId").innerHTML=options;
	$(".select_chosen").trigger("liszt:updated");
}

//提交视频时检测是否选择章节
function submitVideoFolder() {
	var chapterId = $("#wkChapterVideoId").val();
	if(chapterId==-1){
		alert("请选择章节");
		return false;
	}
	return true;
}