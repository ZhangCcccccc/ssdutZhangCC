$(function (){	
	$('.select_chosen').chosen();
});
//新增实验技能
function editExperiment(id){
	window.location.href=$("#contextPath").val()+"/tcoursesite/skill/newExperimentSkill?tCourseSiteId="+$("#tCourseSiteId").val()+"&id="+id;
	
}
//添加实验技能描述
function addExperimentSkillProfile(profile){
	$("#experimentSkillProfile").val(profile);
	$("#addExperimentSkillProfile").fadeIn(100);//新增文件夹框弹出
}

//上传实验指导书	
function upWkUpload(name,type){
	$("#upWkUpload").fadeIn(100);
	//上传实验指导书	
	$("#upWkUploadUploadifyPic").uploadify({
		'auto' : true, 		
		'formData':{type:type},    //传值
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
    		$("#"+name+"sNameList").append("<li class='pic_list hg9 lh35 ptb5 rel ovh'>" +
					        				"<div class='cf rel zx1 z c_category'>" +
					        				"<div class=''l mlr15 cc1 c_tool poi'>" +
					        				file.name +
					        				"</div></div></li>")
    		var cur=$("#"+name+"sList").val();
    		$("#"+name+"sList").val(cur+data+",");
    		
		},
    	onQueueComplete : function(event, data) {//当队列中的所有文件全部完成上传时触发　 
    		$("#upWkUpload").fadeOut(100);
		}
	});
}
////新增实验技能
//function changeLabRooms(){
//	var labRoomIds = $("#labRoomIds").val();//实验室id列表
//	var labRoomIdsString = "";
//	if(labRoomIds != null){
//		labRoomIdsString=labRoomIds.join(",");
//	}
//	//alert(labRoomIds);
//	//ajax根据实验室更新实验工具列表
//	$.ajax({
//		type: "POST",
//		url:$("#contextPath").val()+'/tcoursesite/skill/findToolsJsonByLabRooms',			
//		data : {labRoomIds:labRoomIdsString},
//		dataType:'json',
//		success:function(data){//返回已选择实验室内的所有设备
//			if(data){
//				//更新实验工具列表
//				$.each(data,function(key,values){  
//					//alert(values);
//					document.getElementById(key).innerHTML=values;
//				 }); 
//			}else{
//				document.getElementById("tools").innerHTML="";
//			}
//			
//			//刷新chosen插件
//			$(".select_chosen").trigger("liszt:updated");
//			$('.select_chosen').chosen();
//		},
//		error:function(){
//			alert("信息错误！");
//			}
//	})
//}

//新增实验技能
function changeLabRooms(){
	var labRoomIds = $("#labRoomIds").val();//实验室id列表
	var labRoomIdsString = "";
	if(labRoomIds != null){
		labRoomIdsString=labRoomIds.join(",");
	}
	var labDeviceIds = $("#devices").val();//实验室id列表
	var labDeviceIdsString = "";
	if(labDeviceIds != null){
		labDeviceIdsString=labDeviceIds.join(",");
	}
	
	//alert(labRoomIds);
	//ajax根据实验室更新实验工具列表
	$.ajax({
		type: "POST",
		url:$("#contextPath").val()+'/tcoursesite/skill/findDevicesJsonByLabRooms',			
		data : {labRoomIds:labRoomIdsString,labDeviceIds:labDeviceIdsString},
		dataType:'json',
		success:function(data){//返回已选择实验室内的所有设备
			if(data){
				//更新实验工具列表
				$.each(data,function(key,values){  
					//alert(values);
					document.getElementById(key).innerHTML=values;
				 }); 
			}else{
				document.getElementById("tools").innerHTML="";
			}
			
			//刷新chosen插件
			$(".select_chosen").trigger("liszt:updated");
			$('.select_chosen').chosen();
		},
		error:function(){
//		alert("信息错误！");
			}
	})
}

function editSkillDiscuss(discussId) {
    //var con = '<iframe id="con" scrolling="yes" id="message" frameborder="0"  src=$("#contextPath").val()+"/tcoursesite/videoLook?folderId=' + id + '" style="width:100%;height:560px;"></iframe>'
    //$("#mediaDisplay").html(con); 
    if(discussId!=-1){
    	$("#id").val(discussId);
    	$.ajax({
    		type: "POST",
    		url: $("#contextPath").val()+"/tcoursesite/skill/editSkillDiscuss",
    		data: {'discussId':discussId},
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
    	$("#id").val('');
    	$("#title").val('');
    	$("#content").val('');
    }
    $("#editSkillDiscuss").fadeIn(100);
}

//删除实验问答
function deleteSkillDiscuss(discussId,tCourseSiteId,skillId){
	if(confirm("是否确认删除？"))
	   {
		window.location = $("#contextPath").val()+'/tcoursesite/skill/deleteSkillDiscuss?discussId='+discussId+'&tCourseSiteId='+tCourseSiteId+"&skillId="+skillId;
	   }
}

//进入实验问答回复
function replySkillDiscuss(discussId,tCourseSiteId,skillId){
		window.location = $("#contextPath").val()+'/tcoursesite/skill/skillDiscussReplies?discussId='+discussId+'&tCourseSiteId='+tCourseSiteId+'&skillId='+skillId+'&currpage=1';
}
//打开实验问答回复窗口
function newSkillDiscussReply(parentId) {
    //var con = '<iframe id="con" scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/tcoursesite/videoLook?folderId=' + id + '" style="width:100%;height:560px;"></iframe>'
    //$("#mediaDisplay").html(con); 
   	$("#id").val('');
   	$("#content").val('');
    $("#newSkillDiscussReply").fadeIn(100);
}
//删除实验问答回复
function deleteSkillDiscussReply(discussId,tCourseSiteId,skillId){
	if(confirm("是否确认删除？"))
	   {
		window.location = $("#contextPath").val()+'/tcoursesite/skill/deleteSkillDiscussReply?discussId='+discussId+'&tCourseSiteId='+tCourseSiteId+'&skillId='+skillId;
	   }
}
//返回实验问答
function goBackToSkillDiscuss(tCourseSiteId,skillId)
{  
	 window.location.href= $("#contextPath").val()+"/tcoursesite/skill/discussExperimentSkill?tCourseSiteId="+tCourseSiteId+"&skillId="+skillId+"&currpage=1";
}

//正则表达式规范得分填写
function changeNumber(obj,score){
	var price=$(obj).val();
	price = price.replace(/[^\d.]/g,"");
    //必须保证第一个为数字而不是.
    price = price.replace(/^\./g,"");
    //保证只有出现一个.而没有多个.
    price = price.replace(/\.{2,}/g,".");
    //保证.只出现一次，而不能出现两次以上
    price = price.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
    if(Number(price)>score){
    	alert("打分不得高于分值，分值为100！");
    	$(obj).val(score);
    	$("#finalScoreFont").html(score);
    	return;
    }
    $(obj).val(price);
}
//下载实验报告
function downloadOneFile(id){
	var inputId = "<input type='hidden' name='id' value='"+ id +"' />";
	var inputSkillId = "<input type='hidden' name='skillId' value='"+ $("#skillId").val() +"' />";
	var inputSiteId = "<input type='hidden' name='tCourseSiteId' value='"+ $("#tCourseSiteId").val() +"' />";
	var html = "<form action='"+$("#contextPath").val()+"/tcoursesite/skill/downloadReportFile' method='post'>"+inputId+inputSkillId+inputSiteId+"</form>"; 
	jQuery(html).appendTo("body").submit().remove();
}

//下载实验报告
function lookReport(id){
	$.ajax({
		type: "POST",
		url: $("#contextPath").val()+"/tcoursesite/skill/lookReport",
		data: {'id':id},
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
	$("#lookReport").fadeIn(100);
}

//新增或编辑作业文件夹
function newReportWork(chapterId,folderId,status){
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
	
	$("#tAssignmentWkChapterId").val(chapterId);
	$(".select_chosen").trigger("liszt:updated");
	$('.select_chosen').chosen();
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


