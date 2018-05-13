//新增或编辑图片文件夹
function newImageFolder(chapterType,chapterId,lessonId,folderId){
	
	if(folderId != -1){
		$.ajax({
    		type: "POST",
    		url: $("#contextPath").val()+"/tcoursesite/editImageFolder",
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
		document.getElementById("imagesList").innerHTML="";
		document.getElementById("imagesNameList").innerHTML="";
		$("#imageFolderId").val("");
		$("#imageFolderName").val("");
	}
	//刷新学习单元下拉框
	changeImageLessonsByChapterId(chapterId)
	$("#editImageFolder").fadeIn(100);
	$("#documentChapterType").val(chapterType);
	$("#wkChapterImageId").val(chapterId);
	$("#wkLessonImageId").val(lessonId);
	$(".select_chosen").trigger("liszt:updated");
	
	$('.select_chosen').chosen();
	
	$("#editPic").fadeIn(100);
	//图片上传插件
	$("#imageUploadifyPic").uploadify({
		
		'auto' : true, 		
		'formData':{type:1},    //传值
    	'swf': $("#contextPath").val()+'/swfupload/swfupload.swf',  
    	'uploader':$("#contextPath").val()+'/tcoursesite/fileUpload;jsessionid='+$("#sessionId").val(),	
    	//提交的controller和要在火狐下使用必须要加的id
    	'buttonText':'选择图片文件',
    	cancelImage:'/swfupload/uploadify-cancel.png',
    	'simUploadLimit' : 99, // 一次同步上传的文件数目
        'fileSizeLimit' : 0, //300mb,以字节为单位，0为不限制。1MB:1*1024*1024
        'queueSizeLimit' : 99, // 队列中同时存在的文件个数限制
        'fileTypeDesc' : '支持格式:jpg/jpeg等', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
        'fileTypeExts' : '*.jpg;*.jpeg;*.png;*.bmp;*.gif;',// 允许的格式      
        'multi':true,
    	'onUploadError':function(file, errorCode, errorMsg, errorString) {
        	alert("上传失败，请检查文件大小和格式！");
    	} ,
    	'onUploadSuccess' : function(file, data, response) {
    		//alert("文件："+file.name+"  上传成功");
    		$("#imagesNameList").append("<li class='pic_list hg9 lh35 ptb5 rel ovh'>" +
    				"<div class='cf rel zx1 z c_category'>" +
    				"<div class=''l mlr15 cc1 c_tool poi'>" +
    				file.name +
    				"</div></div></li>");
    		var cur=$("#imagesList").val();
    		$("#imagesList").val(cur+data+",");
    		
		},
    	onQueueComplete : function(event, data) {//当队列中的所有文件全部完成上传时触发　 
		}
	});
}

//查看图片
function imageLook(folderId,currpage){
	$.ajax({
		type: "POST",
		url: $("#contextPath").val()+"/tcoursesite/lookImage",
		data: {'folderId':folderId,'currpage':currpage},
		dataType:'json',
		success:function(data){
			$.each(data,function(key,values){
				if(key=="imageUrlLook"){
					$("#imageUrlLook").attr("src", $("#contextPath").val()+values);
				}else if(key=="imageNameLook"){
					$("#imageNameLook").html(values);
				}else{
					if(values==0){
							$("#"+key).css("cursor", "default");
							$("#"+key).css("color","#333");
							//$("#"+key).attr("onclick", "imageLook("+folderId+","+values+")");
						}else{
							$("#"+key).removeAttr("style");
							$("#"+key).attr("onclick", "imageLook("+folderId+","+values+")");
						}
				}
			 }); 
		},
		error:function(){
			alert("信息错误！");
			}
	});
 	$("#imageLook").fadeIn(100);
}

//根据章节查看图片
function imageLookByChapter(chapterId,lessonId,currpage){
	$.ajax({
		type: "POST",
		url: $("#contextPath").val()+"/tcoursesite/lookImageByChapter",
		data: {'chapterId':chapterId,'lessonId':lessonId,'currpage':currpage},
		dataType:'json',
		success:function(data){
			$.each(data,function(key,values){
				if(key=="imageUrlLook"){
					$("#imageUrlLook").attr("src", $("#contextPath").val()+values);
				}else if(key=="imageNameLook"){
					$("#imageNameLook").html(values);
				}else{
					if(values==0){
							$("#"+key).css("cursor", "default");
							$("#"+key).css("color","#333");
							//$("#"+key).attr("onclick", "imageLook("+folderId+","+values+")");
						}else{
							$("#"+key).removeAttr("style");
							$("#"+key).attr("onclick", "imageLookByChapter("+chapterId+","+lessonId+","+values+")");
						}
				}
			 }); 
		},
		error:function(){
			alert("信息错误！");
			}
	});
 	$("#imageLook").fadeIn(100);
}
//根据分类获取对应的章节列表选项
function changeImageChaptersByModuleType(tCourseSiteId,moduleType){
	var options = changeChaptersByModuleType(tCourseSiteId,moduleType);
	document.getElementById("wkChapterImageId").innerHTML=options;
	var lessonOptions = "<option value='-1'>请选择</option>";
	document.getElementById("wkLessonImageId").innerHTML=lessonOptions;
	$(".select_chosen").trigger("liszt:updated");
}
//根据章节回去对应的课时列表选项
function changeImageLessonsByChapterId(chapterId){
	var options = changeLessonsByChapterId(chapterId);
	document.getElementById("wkLessonImageId").innerHTML=options;
	$(".select_chosen").trigger("liszt:updated");
}

//提交图片时检测是否选择章节
function submitImageFolder() {
	var chapterId = $("#wkChapterImageId").val();
	if(chapterId==-1){
		alert("请选择章节");
		return false;
	}
	return true;
}