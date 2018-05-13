//新增或编辑考试文件夹
function newTestFolder(chapterType,chapterId,lessonId,folderId,status){
	for(var i=0;i<23;i++){
		array[i]=0;
		}
	$('.searchable-select').remove();
	$("#editTestFolder").fadeIn(100);
	if(status==1){
		alert("此考试已发布,确定要修改？");
		//return false;
	}
	if(folderId != -1){
		$.ajax({
			async:false, 
    		type: "POST",
    		url: $("#contextPath").val()+"/tcoursesite/editTestFolder",
    		data: {'folderId':folderId},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){  
    				//alert(key+"="+values);
    				//document.getElementById(key).innerHTML=values;
    				
    				if(key=="trString"){
    					$("#itemBody").children().last().before(values);
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
		$("#testTitle").val("");
		$("#testId").val(null);
		$("#testAnswerAssignId").val(null);
		$("#testControlId").val(null);
		$("#testTAssignmentControl_startdate").val(myDate);
		$("#testTAssignmentControl_duedate").val(myDate);
		$("#testContent").val(null);
		$("#testStatus").val(0);
		$("#testCreatedTime").val(myDate);
		$("#testWkFolderId").val(null);
		$("#testScoreTest").val(100);
		$("#testContent").val("");
	}
	
	changeTestLessonsByChapterId(chapterId);
	$("#testChapterType").val(chapterType);
	$("#testWkChapterId").val(chapterId);
	$("#testWkLessonId").val(lessonId);
	$(".select_chosen").trigger("liszt:updated");
	$('.select_chosen').chosen();
	$('.select_search').searchableSelect();
}
//保存考试之前的验证
function checkForm(){
	
	var chapterId = $("#testWkChapterId").val();
	if(chapterId==-1){
		alert("请选择章节");
		return false;
	}
	
	var result = 0;
	var questionIdArray = new Array();
	var typeArray = new Array();
	var quantityArray = new Array();
	var scoreArray = new Array();
	$("input[name='itemTypeTest']").each(function(i,value){
		typeArray[i] = $(this).val().trim();
	})
	if (typeArray.length==0) {
		alert("请添加组成试卷的试题！");
		return false;
	}
	$("input[name='itemQuantityTest']").each(function(i,value){
		if ($(this).val().trim()==""||isNaN($(this).val().trim())) {
			alert("试题数量不能为空且必须为数字");
			$(this)[0].focus();
			result = 1;
			return false;
		}
		quantityArray[i] = $(this).val().trim();
	})
	if (result == 1) {
		return false;
	}
	$("input[name='itemScoreTest']").each(function(i,value){
		if ($(this).val().trim()==""||isNaN($(this).val().trim())) {
			alert("试题分值不能为空且必须为数字");
			$(this)[0].focus();
			result = 1;
			return false;
		}
		scoreArray[i] = $(this).val().trim();
	})
	if (result == 1) {
		return false;
	}
	$.ajax({
		url:$("#contextPath").val()+'/teaching/test/checkItemQuantity?questionIdArray='+questionIdArray+'&typeArray='+typeArray+'&quantityArray='+quantityArray,
		type:'post',
		async:false,  // 设置同步方式
        cache:false,
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
		success:function(data){
			if(data['result']!=""){
				alert(data['result']);
				result = 1;
			}
		}
	}); 
	if (result == 1) {
		return false;
	}
	var totalScore = 0;
	 $.each(quantityArray,function(i,value){
	 	totalScore += quantityArray[i]*scoreArray[i];
	 })
	 if(totalScore!=Number($("#testScoreTest").val().trim())){
	 	alert("已添加试题分值为"+totalScore+"分，与考试分值不符，请检查！");
	 	return false;
	 }
}

//根据分类获取对应的章节列表选项
function changeTestChaptersByModuleType(tCourseSiteId,moduleType){
	var options = changeChaptersByModuleType(tCourseSiteId,moduleType);
	document.getElementById("testWkChapterId").innerHTML=options;
	var lessonOptions = "<option value='-1'>请选择</option>";
	document.getElementById("testWkLessonId").innerHTML=lessonOptions;
	$(".select_chosen").trigger("liszt:updated");
}
//根据章节回去对应的课时列表选项
function changeTestLessonsByChapterId(chapterId){
	var options = changeLessonsByChapterId(chapterId);
	document.getElementById("testWkLessonId").innerHTML=options;
	$(".select_chosen").trigger("liszt:updated");
}

function addTr() {
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#addTr').window({left:"100px", top:(topPos+20)+"px"}); 
    $("#addTr").window('open');
}