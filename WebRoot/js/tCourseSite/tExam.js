//新增或编辑测验文件夹
function newExamFolder(chapterType,chapterId,lessonId,folderId,status){
	$('.searchable-select').remove();
	$("#editExamFolder").fadeIn(100);
	if(status==1){
		alert("此测验已发布！");
		//return false;
	}
	if(folderId != -1){
		$.ajax({
			async:false, 
    		type: "POST",
    		url: $("#contextPath").val()+"/tcoursesite/editExamFolder",
    		data: {'folderId':folderId},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){
    				if(key=="examtimelimitselect"){
    					if(values==1){
    						document.getElementById("timelimit").checked="checked";
    					}else{
    						document.getElementById("refer").checked="checked";
    						$(".refer").parents("ul").find(".submittimes").show();
    						document.getElementById("timelimitExam").value=values;
    					}
    				}else if(key=="examTAssignmentControlToGradebook"||key=="examTAssignmentControlGradeToStudent"||key=="examTAssignmentControlGradeToTotalGrade"){
    					if(values=="yes"){
    						document.getElementById(key+"Yes").checked="checked";
    					}else{
    						document.getElementById(key+"No").checked="checked";
    					}
    				}else{
    					$("#"+key).val(""+values);
    				}
    				$("#"+key).val(""+values);
    			 }); 
    		},
    		error:function(){
    			alert("信息错误！");
    			}
    	});
	}else{
		var myDate = nowTime();
		
		$("#examTitle").val("");
		$("#examId").val(null);
		$("#examAnswerAssignId").val(null);
		$("#examControlId").val(null);
		$("#TAssignmentControl_startdate").val(myDate);
		$("#TAssignmentControl_duedate").val(myDate);
		$("#examContent").val(null);
		$("#examStatus").val(0);
		$("#examCreatedTime").val(myDate);
		$("#examWkFolderId").val(null);
		$("#examScoreExam").val(100);
		$("#examContent").val("");
	}
	
	changeExamLessonsByChapterId(chapterId);
	$("#examChapterType").val(chapterType);
	$("#examWkChapterId").val(chapterId);
	$("#examWkLessonId").val(lessonId);
	$(".select_chosen").trigger("liszt:updated");
	$('.select_chosen').chosen();
	$('.select_search').searchableSelect();
}

//检测测试是否能提交
function submitExam(){
	var chapterId = $("#examWkChapterId").val();
	if(chapterId==-1){
		alert("请选择章节");
		return false;
	}
}

//开始测试的检验
function checkSubmitTimeForExam(isOverdue,submitTime,timeLimit){
	if(isOverdue){
		alert("测验已过期，无法参加测验！");
		return false;
	}
	if(timeLimit!=0&&submitTime>=timeLimit){
		alert("已达到参加次数限制，无法继续参加！");
		return false;
	}
}

//根据分类获取对应的章节列表选项
function changeExamChaptersByModuleType(tCourseSiteId,moduleType){
	var options = changeChaptersByModuleType(tCourseSiteId,moduleType);
	document.getElementById("examWkChapterId").innerHTML=options;
	var lessonOptions = "<option value='-1'>请选择</option>";
	document.getElementById("examWkLessonId").innerHTML=lessonOptions;
	$(".select_chosen").trigger("liszt:updated");
}
//根据章节回去对应的课时列表选项
function changeExamLessonsByChapterId(chapterId){
	var options = changeLessonsByChapterId(chapterId);
	document.getElementById("examWkLessonId").innerHTML=options;
	$(".select_chosen").trigger("liszt:updated");
}