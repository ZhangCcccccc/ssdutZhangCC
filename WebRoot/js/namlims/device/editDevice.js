//返回到设备列表页面
function openSetupLink(){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/listLabRoomDeviceRest/"+labRoomId+"/"+deviceNumber+"/"+deviceName+"/"+username+"/"+page+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//培训计划
function editDeviceTrainingRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val();  
	var url = $("#pageContext").val()+"/device/editDeviceTrainingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//设备详情
function editDeviceInfoRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/editDeviceInfoRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//参数设置
function editDeviceSettingRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/editDeviceSettingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//相关图片
function editDeviceImageRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/editDeviceImageRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//相关视频
function editDeviceVideoRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/editDeviceVideoRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//相关文档
function editDeviceDocumentRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/editDeviceDocumentRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//二维码
function editDeviceDimensionalCodeRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val();  
	var url = $("#pageContext").val()+"/device/editDeviceDimensionalCodeRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}

//注意事项
function editDeviceAttentionRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/editDeviceAttentionRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	window.location.href=url;
}

//保存注意事项
function saveDeviceAttentionRest(id){
	
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val();  
	console.log(page);
    var deviceAttention = $("#applicationAttentions").val();
    if($("#applicationAttentions").val()==""){
    	deviceAttention="-1";
    }
    
	//特殊字符
	//1. +  URL 中+号表示空格 %2B
	//2. 空格 URL中的空格可以用+号或者编码 %20
	//3. /  分隔目录和子目录 %2F 
	//4. ?  分隔实际的 URL 和参数 %3F 
	//5. % 指定特殊字符 %25 
	//6. # 表示书签 %23 
	//7. & URL 中指定的参数间的分隔符 %26 
	//8. = URL 中指定参数的值 %3D 
    deviceAttention=deviceAttention.replace(/\+/g,"[Geng1Shang]");//将特殊字符"+"转化为[Geng1Shang]
    deviceAttention=deviceAttention.replace(/\ /g,"[Geng2Shang]");//将特殊字符" "转化为[Geng2Shang]
    deviceAttention=deviceAttention.replace(/\//g,"[Geng3Shang]");//将特殊字符"/"转化为[Geng3Shang]
    deviceAttention=deviceAttention.replace(/\?/g,"[Geng4Shang]");//将特殊字符"?"转化为[Geng4Shang]
    deviceAttention=deviceAttention.replace(/\%/g,"[Geng5Shang]");//将特殊字符"%"转化为[Geng5Shang]
    deviceAttention=deviceAttention.replace(/\#/g,"[Geng6Shang]");//将特殊字符"#"转化为[Geng6Shang]
    deviceAttention=deviceAttention.replace(/\&/g,"[Geng7Shang]");//将特殊字符"&"转化为[Geng7Shang]
    deviceAttention=deviceAttention.replace(/\=/g,"[Geng8Shang]");//将特殊字符"="转化为[Geng8Shang]
	var url = $("#pageContext").val()+"/device/saveDeviceAttentionRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+
	"/"+deviceAttention+"/"+ id+"/"+schoolDevice_allowAppointment;
	window.location.href=url;
}

//删除文件（图片、文档）
function deleteDeviceDocumentRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/deleteDeviceDocumentRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}

//删除文件（视频）
function deleteLabRoomVideoRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/deleteLabRoomVideoRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}


//针对培训预约

//根据设备id查询培训
function findTrainingPeopleByTrainIdRest(id,toChangeAudit){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/findTrainingPeopleByTrainIdRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/" +username + "/"+page+"/" + id+"/"+1 +"/"+toChangeAudit+"/"+schoolDevice_allowAppointment;
	window.location.href=url;
}

//保存实验设备培训人
function joinTrainingRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
 var url = $("#pageContext").val()+"/device/joinTrainingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
 //alert(url);
 window.location.href=url;
}

//删除培训
function deleteTrainingRest(id){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
 var url = $("#pageContext").val()+"/device/deleteTrainingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
 //alert(url);
 window.location.href=url;
}

//取消培训
function cancelTrainingRest(id){
	$.ajax({
        url:$("#pageContext").val()+"/device/cancelTraining?id="+id,
        type:"POST",
        success:function(data){//AJAX查询成功
        		if(data=="success"){
        			window.location.reload(); 
        		}   
        }
	});
}

var trainId=0;
function moveId(id){
	trainId = id;
}

//更新选课时间
function info(){
	var begintime=$("#begintime").val();
	if(begintime==null||begintime==''){
		alert("请选择时间");
	}else{
		$.ajax({
		    url:$("#pageContext").val()+"/device/altTrainTime?id="+trainId,
		    type:"POST",
		    data: {'begintime':begintime},
		       success:function(data){//AJAX查询成功
		            if(data == 'success'){
		                $(".edit-edit").slideUp();
		                $(".btn-edit").slideDown();//修改信息显示
		                window.location.reload();//刷新页面
		            }else{
		                alert("未能修改成功，请稍后重试");
		            }
		       }
		});
	}
}

//保存培训信息
function saveLabRoomDeviceTrainingRest(id){//将labRoomId deviceNumber deviceName page传递到后台 
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var time = $("#time").val();
	var address = $("#address").val();
	var content = $("#content").val();
	var usernameTeacher = $("#usernameTeacher").val();
	var number = $("#number").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/saveLabRoomDeviceTrainingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName + "/"+username+"/"+page+ "/"+ time
	+ "/"+ address+ "/"+ content+ "/"+ usernameTeacher+ "/"+ number+"/" + id+"/"+schoolDevice_allowAppointment;
	window.location.href=url;
}

