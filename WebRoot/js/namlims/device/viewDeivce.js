
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

//返回到设备列表页面
function openSetupLinkNew(){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/listLabRoomDeviceNew?roomId="+labRoomId+"&page="+page +"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//查看设备详情（传参版）
function viewDeviceInfoRest(id){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/viewDeviceInfoRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}

//查看设备注意事项（传参版）
function viewDeviceAttentionRest(id){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/viewDeviceAttentionRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	window.location.href=url;
}

//查看培训计划
function viewDeviceTrainingRest(id){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/viewDeviceTrainingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//查看参数设置
function viewDeviceSettingRest(id){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/viewDeviceSettingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}

//预约
function doDeviceReservation(id, currpage){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/doDeviceReservation/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id +"/" + currpage+"/"+schoolDevice_allowAppointment;
	window.location.href=url;
}

//查看使用情况
function viewDeviceReservationRest(id, currpage){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val();  
	var url = $("#pageContext").val()+"/device/viewDeviceReservationRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id +"/" + currpage+"/"+schoolDevice_allowAppointment;
	window.location.href=url;
}

//查看使用情况--汇总
function viewDeviceReservationRestAll(id, currpage){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/viewDeviceReservationRestAll/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id +"/" + currpage+"/"+schoolDevice_allowAppointment;
	window.location.href=url;
}

//查看相关图片
function viewDeviceImageRest(id){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/viewDeviceImageRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//查看相关视频
function viewDeviceVideoRest(id){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val();  
	var url = $("#pageContext").val()+"/device/viewDeviceVideoRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//查看相关文档
function viewDeviceDocumentRest(id){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/viewDeviceDocumentRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}
//查看设备二维码
function viewDimensionalCodeRest(id){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/viewDimensionalCodeRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName +"/"+username+ "/"+page+"/" + id+"/"+schoolDevice_allowAppointment;
	//alert(url);
	window.location.href=url;
}

var targetDeviceId;
//AJAX验证是否通过安全准入
function Access(id){
	targetDeviceId = id;
	$.ajax({
	           url:$("#pageContext").val()+"/device/securityAccess?id="+id,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	           		if(data=="success"){
	           			doDeviceReservation(id,1);
	           		}else if(data=="error"){
	           			$.messager.defaults.ok = '去预约培训';
	           			$.messager.defaults.cancel = '先不预约了';
	           			$.messager.confirm('提示','您还未通过培训,请先预约培训!',function(r){
	           			    if (r){
	           			    	viewDeviceTrainingRest(id);
	           			    }
	           			});
	           		}else if(data.indexOf("<td>")>-1){
	           			document.getElementById("device_body").innerHTML=data;
	           			$("#showAttentions").show();
	           			$('#showAttentions').window({left:"100px", top:"100px"});
	           			$("#showAttentions").window('open');
	           		}else{
	           			alert("该设备还未添加设备管理员，暂不能预约，请联系相关人员进行添加！")
	           		}    
	           }
	});
	
}

//阅读注意事项后，去预约
function reservationAfterReading(){
	var labRoomId = $("#labRoomId").val();
	var deviceName = $("#deviceName").val();
	var deviceNumber = $("#deviceNumber").val();
	var username = $("#username").val();
	var page = $("#page").val();
	var schoolDevice_allowAppointment =$("#schoolDevice_allowAppointment").val(); 
	var url = $("#pageContext").val()+"/device/doDeviceReservation/" + labRoomId + "/"+ deviceNumber + "/" + deviceName + "/" + username +"/"+page+"/" + targetDeviceId +"/"+1+"/"+schoolDevice_allowAppointment;
	window.location.href=url;
}