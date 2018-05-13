//新增或编辑课时
function newWkLesson(id,chapterId,wkChapters){
	if(wkChapters == "[]" || wkChapters == "" || wkChapters == null){
		alert("没有章节，请先创建章节！");
		return;
	}
	if(id != -1){
		$.ajax({
    		type: "POST",
    		url: $("#contextPath").val()+"/tcoursesite/editLesson",
    		data: {'lessonId':id},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){  
    				$("#"+key).val(""+values);
    			 }); 
    			$('.searchable-select').remove();
    			$('#lessonChapterId').searchableSelect();
    		},
    		error:function(){
    			alert("信息错误！");
    			}
    	});
	}else{
		if(chapterId==-1){
			$("#lessonChapterId").val($("#lessonChapterId option:first").val());
		}else{
			$("#lessonChapterId").val(chapterId);
		}
		
		$('.searchable-select').remove();
		$('#lessonChapterId').searchableSelect();
	}
	$("#newWkLesson").fadeIn(100);
	
}
//新增或编辑章节
function newWkChapter(id){
	if(id != -1){
		$.ajax({
    		type: "POST",
    		url: $("#contextPath").val()+"/tcoursesite/editChapter",
    		data: {'chapterId':id},
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
	}
	$("#newWkChapter").fadeIn(100);
}
		
//删除章节
function deleteWkChapter(id,tCourseSiteId,moduleType){
	if(confirm("是否确认删除？"))
	   {
		$.ajax({
			async: false,
			type: "POST",
			url: $("#contextPath").val()+"/tcoursesite/deleteChapter",
			data: {'chapterId':id,'tCourseSiteId':tCourseSiteId,'moduleType':moduleType},
			dataType:"text",
			success:function(data){
				$("#chapterdiv"+id).remove();
			},
			error:function(){
				alert("删除失败！");
				}
		});
	   }
}

//删除课时
function deleteWkLesson(id,tCourseSiteId,moduleType){
	if(confirm("是否确认删除？"))
	   {
		$.ajax({
			async: false,
			type: "POST",
			url: $("#contextPath").val()+"/tcoursesite/deleteLesson",
			data: {'lessonId':id,'tCourseSiteId':tCourseSiteId,'moduleType':moduleType},
			dataType:"text",
			success:function(data){
				$("#lessondiv"+id).remove();
			},
			error:function(){
				alert("删除失败！");
				}
		});
	   }
}

//删除文件夹
function deleteWkFolder(id,tCourseSiteId,moduleType){
	if(confirm("是否确认删除？"))
	   {
		$.ajax({
			async: false,
			type: "POST",
			url: $("#contextPath").val()+"/tcoursesite/deleteFolder",
			data: {'folderId':id,'tCourseSiteId':tCourseSiteId,'moduleType':moduleType},
			dataType:"text",
			success:function(data){
				$("#folderli"+id).remove();
				$("#"+id).remove();
			},
			error:function(){
				alert("删除失败！");
				}
		});
	   }
}


//下载图片或文件
function downloadFile(id){
	window.location.href=$("#contextPath").val()+"/tcoursesite/downloadFile?id="+id;
}
//删除图片或文件
function deleteFile(id,tCourseSiteId,moduleType){
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
//预览pdf文件
function showFile(id){
	$.ajax({
		type: "POST",
		url: $("#contextPath").val()+"/tcoursesite/showFile",
		data: {'id':id},
		success:function(data){
//			window.location.href="$("#contextPath").val()+"/tcoursesite/showOnline.jsp";
		},
		error:function(){
			alert("预览失败！");
			}
	});
}

//获取当前时间
function nowTime(){
	var myDate = new Date();
	
    var seperator1 = "-";
    var seperator2 = ":";
    var month = myDate.getMonth() + 1;
    var strDate = myDate.getDate();
    var strHour = myDate.getHours();
    var strMinutes = myDate.getMinutes();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (strHour >= 0 && strHour <= 9) {
        strHour = "0" + strHour;
    }
    if (strMinutes >= 0 && strMinutes <= 9) {
    	strMinutes = "0" + strMinutes;
    }
    var currentdate = myDate.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + strHour + seperator2 + strMinutes;
    return currentdate;
}



//根据分类获取对应的章节列表选项
function changeChaptersByModuleType(tCourseSiteId,moduleType){
	var options = "<option value='-1'>请选择</option>";
	$.ajax({
		async: false,
		type: "POST",
		url: $("#contextPath").val()+"/tcoursesite/findChapterMap",
		data: {'tCourseSiteId':tCourseSiteId,'moduleType':moduleType},
		dataType:'json',
		success:function(data){
			$.each(data,function(key,values){
				options += "<option value='"+key+"'>"+values+"</option>";
				
			 }); 
		},
		error:function(){
			alert("信息错误！");
			}
	});
	return options;
}
//根据章节回去对应的课时列表选项
function changeLessonsByChapterId(chapterId){
	var options = "<option value='-1'>请选择</option>";
	$.ajax({
		async: false,
		type: "POST",
		url: $("#contextPath").val()+"/tcoursesite/findLessonMap",
		data: {'chapterId':chapterId},
		dataType:'json',
		success:function(data){
			$.each(data,function(key,values){
				options += "<option value='"+key+"'>"+values+"</option>";
				
			 }); 
		},
		error:function(){
			alert("信息错误！");
			}
	});
	return options;
}

//选择显示内容
function select(tCourseSiteId,moduleType,selectId){
	window.location.href=$("#contextPath").val()+"/tcoursesite/chaptersList?tCourseSiteId="+tCourseSiteId+"&moduleType="+moduleType+"&selectId="+selectId;
}

//显示隐藏内容
function checkMore(id){
	var count = 0;
	if($("#"+id+"Check").val()=="显示更多"){
		$("#"+id).find("div").each(function () {//遍历页面所有checkbox
			count++;
			if(count < 7){
				return true;
			}
			$(this).removeClass("hide")
	    });
		$("#"+id+"Check").val("隐藏更多")
	}else{
		$("#"+id).find("div").each(function () {//遍历页面所有checkbox
			count++;
			if(count < 7){
				return true;
			}
			$(this).addClass("hide")
	    });
		$("#"+id+"Check").val("显示更多")
	}
	
}

//选择显示内容
function goToUrl(url){
	window.location.href=url;
}
