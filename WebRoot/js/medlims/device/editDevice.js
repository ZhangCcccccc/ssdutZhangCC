//返回到设备列表页面
function openSetupLink(){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var url = $("#pageContext").val()+"/device/listLabRoomDeviceRest/"+labRoomId+"/"+deviceNumber+"/"+deviceName+"/"+username+"/"+page;
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
	var url = $("#pageContext").val()+"/device/editDeviceTrainingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id;
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
	var url = $("#pageContext").val()+"/device/editDeviceInfoRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id;
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
	var url = $("#pageContext").val()+"/device/editDeviceSettingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id;
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
	var url = $("#pageContext").val()+"/device/editDeviceImageRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id;
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
	var url = $("#pageContext").val()+"/device/editDeviceVideoRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id;
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
	var url = $("#pageContext").val()+"/device/editDeviceDocumentRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id;
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
	var url = $("#pageContext").val()+"/device/editDeviceDimensionalCodeRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id;
	//alert(url);
	window.location.href=url;
}

//删除文件（图片、文档）
function deleteDeviceDocumentRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var url = $("#pageContext").val()+"/device/deleteDeviceDocumentRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id;
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
	var url = $("#pageContext").val()+"/device/deleteLabRoomVideoRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id;
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
	var url = $("#pageContext").val()+"/device/findTrainingPeopleByTrainIdRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/" +username + "/"+page+"/" + id+"/"+1 +"/"+toChangeAudit;
	window.location.href=url;
}

//保存实验设备培训人
function joinTrainingRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
 var url = $("#pageContext").val()+"/device/joinTrainingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id;
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
 var url = $("#pageContext").val()+"/device/deleteTrainingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id;
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
        			window.history.go(0);
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
	var schoolDevice_deviceName =$("#schoolDevice_deviceName").val();
	var url = $("#pageContext").val()+"/device/saveLabRoomDeviceTrainingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName + "/"+username+"/"+page+ "/"+ time
	+ "/"+ address+ "/"+ content+ "/"+ usernameTeacher+ "/"+ number+"/" + id;
	//alert(url);
	window.location.href=url;
}

