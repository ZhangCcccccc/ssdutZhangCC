//新增或编辑作业文件夹
function newAssignmentFolder(chapterType,chapterId,lessonId,folderId,status){
	$('.searchable-select').remove();
	if(status==1){
		alert("此作业已发布,确定要修改?");
		//return false;
	}

	if(folderId != -1){
		$.ajax({
			async:false, 
    		type: "POST",
    		url: $("#contextPath").val()+"/tcoursesite/editAssignmentFolder",
    		data: {'folderId':folderId},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){  
    				if(key=="TAssignmentControlToGradebook"||key=="TAssignmentControlGradeToStudent"||key=="TAssignmentControlGradeToTotalGrade"){
    					if(values=="yes"){
    						document.getElementById(key+"Yes").checked="checked";
    					}else{
    						document.getElementById(key+"No").checked="checked";
    					}
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
		var myDate = nowTime();
		
		$("#tAssignmentTitle").val("");
		$("#tAssignmentId").val(null);
		$("#tAssignmentAnswerAssignId").val(null);
		$("#tAssignmentControlId").val(null);
		$("#TAssignmentControl_startdate").val(myDate);
		$("#TAssignmentControl_duedate").val(myDate);
		$("#content").val(null);
		$("#assignmentStatus").val(0);
		$("#assignmentCreatedTime").val(myDate);
		$("#assignmentWkFolderId").val(null);
		$("#timelimitselect").val(-1);
		$("#assignmentContent").val("");
	}
	
	$("#editAssignmentFolder").fadeIn(100);
	
	changeAssignmentLessonsByChapterId(chapterId);
	$("#assignmentChapterType").val(chapterType);
	$("#tAssignmentWkChapterId").val(chapterId);
	$("#tAssignmentWkLessonId").val(lessonId);
	$(".select_chosen").trigger("liszt:updated");
	$('.select_chosen').chosen();
	$('.select_search').searchableSelect();
	
	$("#AttachmentUploadifyPic").uploadify({
		'auto' : true, 		
		'formData':{type:3},    //传值
    	'swf': $("#contextPath").val()+'/swfupload/swfupload.swf',  
    	'uploader':$("#contextPath").val()+'/tcoursesite/fileUpload;jsessionid='+$("#sessionId").val(),		
    	'fileSizeLimit' : 0, //以字节为单位，0为不限制。1MB:1*1024*1024
        'buttonText':'选择文件',
        'cancelImage':'/swfupload/uploadify-cancel.png',
        'simUploadLimit' : 88, // 一次同步上传的文件数目
        'multi': false,
    	'onUploadError':function(file, errorCode, errorMsg, errorString) {
        	//alert("上传失败，请检查文件大小和格式！");
    	} ,
    	'onUploadSuccess' : function(file, data, response) {
    		//alert("文件："+file.name+"  上传成功");
    		$("#attachmentName").html("<li class='pic_list hg9 lh35 ptb5 rel ovh' id='attachment"+data+"'>" +
					        				"<div class='cf rel zx1 z c_category'>" +
					        				"<div class='l mlr15 cc1 c_tool poi'>" +
					        				file.name +
					        				"</div></div></li>");
    		$("#attachment").val(data);
		},
    	onQueueComplete : function(event, data) {//当队列中的所有文件全部完成上传时触发　 
		}
	});
}

//提交作业时做判断
function submitAssignment(){
	var time = $("#timelimitselect").val();
	var chapterId = $("#tAssignmentWkChapterId").val();
	if(chapterId==-1){
		alert("请选择章节");
		return false;
	}
	if(time==-1){
		alert("请选择可提交次数！");
		return false;
	   }
	//判断是否文件上传和资源都选择
	var upload = $("#attachment").val();
	var radios = document.getElementsByName("radioname");
	var tag = false;
	for(radio in radios) {
	   if(radios[radio].checked) {
	      tag = true;
	      break;
	   }
	}
	if(tag == true && upload != ""){
		//获取选中文件id
		 $("#attachementResourceList").find("input[type='radio']").each(function () {//遍历页面所有checkbox
			 if(this.checked){//如果多选框被选中
				 this.checked = false;
			 }
		});
		$("#attachmentName").html("");
		$("#attachment").val("");
		alert("请勿同时上传附件和选择资源附件！");
		return false;
	}
	var status = $('#assignmentStatus').val();
	if(status==1){
		return confirm('是否确认发布？');
	}
}

//作业浏览提交内容
function checkSubmitNumber(number){
	if(number==0){
		alert("尚未有提交内容，无法查看！");
		return false;
	}
}

//根据分类获取对应的章节列表选项
function changeAssignmentChaptersByModuleType(tCourseSiteId,moduleType){
	var options = changeChaptersByModuleType(tCourseSiteId,moduleType);
	document.getElementById("tAssignmentWkChapterId").innerHTML=options;
	var lessonOptions = "<option value='-1'>请选择</option>";
	document.getElementById("tAssignmentWkLessonId").innerHTML=lessonOptions;
	$(".select_chosen").trigger("liszt:updated");
}
//根据章节回去对应的课时列表选项
function changeAssignmentLessonsByChapterId(chapterId){
	var options = changeLessonsByChapterId(chapterId);
	document.getElementById("tAssignmentWkLessonId").innerHTML=options;
	$(".select_chosen").trigger("liszt:updated");
}

//学生下载提交文件
