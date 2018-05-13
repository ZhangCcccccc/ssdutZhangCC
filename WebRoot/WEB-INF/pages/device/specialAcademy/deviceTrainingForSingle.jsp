<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>

<html>
<head>
<title></title>
<meta name="decorator" content="iframe"/>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-sacale=1.0,user-scalable=no"/>
<style type="text/css" media="screen">
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css");
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css");
			@import url("${pageContext.request.contextPath}/css/style.css");
</style>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<script type="text/javascript">
function addStudent(page){
	var page=page;
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/device/findStudentByCnameAndUsername?cname="+cname+"&username="+username+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	           		$(user_body).html(data);
	           }
	});
    $("#addStudent").show();
    $("#addStudent").window('open');   
    
 }

function addUser(id){
		var usernameStr="";
        var flag; //判断是否一个未选   
        $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为CK_name的 checkbox  
                    if ($(this).attr("checked")) { //判断是否选中    
                        flag = true; //只要有一个被选择 设置为 true  
                    }  
                })  

        if (flag) {  
           $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                        if ($(this).attr("checked")) { //判断是否选中
                        	usernameStr+=$(this).val()+"S";
                        }  
                    })  
           //将要所有要添加的数据传给后台处理   
		   window.location.href="${pageContext.request.contextPath}/device/saveTrainSigleResultRest/"+ ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}" +"/"+${username}+ "/"+${page}+ "/"+ usernameStr + "/" +1+ "/" + id; 
        } else {   
        	alert("请至少选择一条记录");  
        	}  
    	}
//取消查询
function Cancel(){
	document.getElementById("cname").value="";
	document.getElementById("username").value="";
	var cname="";
	var username="";
	$.ajax({
	           url:"${pageContext.request.contextPath}/device/findStudentByCnameAndUsername?cname="+cname+"&username="+username+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  $(user_body).html(data);
	            
	           }
	         });
}


</script>

<script>
//根据设备id查询培训
function findTrainingPeopleByTrainIdRest(id,toChangeAudit){//将labRoomId deviceNumber deviceName page传递到后台
	var url = "${pageContext.request.contextPath}/device/findTrainingPeopleByTrainIdRest/" + ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}" +"/"+${username}+ "/"+${page}+"/" + id+"/"+1 +"/"+toChangeAudit;
	//alert(url);
	window.location.href=url;
}

//保存实验设备培训人
function joinTrainingRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var url = "${pageContext.request.contextPath}/device/joinTrainingRest/" + ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}" +"/"+${username}+ "/"+${page}+"/" + id;
	//alert(url);
	window.location.href=url;
}

//保存培训信息
function saveLabRoomDeviceTrainingRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	var time = $("#time").val();
    var address = $("#address").val();
    var content = $("#content").val();
    var username = $("#username").val();
    var number = $("#number").val();
    var schoolDevice_deviceName =$("#schoolDevice_deviceName").val();
	var url = "${pageContext.request.contextPath}/device/saveLabRoomDeviceTrainingRest/" + ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}" +"/"+${username}+ "/"+${page}+ "/"+ time
	+ "/"+ address+ "/"+ content+ "/"+ username+ "/"+ number+"/" + id;
	
	//alert(url);
	window.location.href=url;
}

</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/editDevice.js"></script>

<style>
.tool-box2 th{text-align:left;}
</style>
</head>


<body>

<div class="right-content">	
	<div class="tool-box1">
		<table class="equip_tab">
		    <tr>
				<td>
				    <span>设备编号：</span>
				    <p>${device.schoolDevice.deviceNumber}</p>
				</td>
				<td>
				    <span>设备名称：</span>
				    <p class="equip_name">${device.schoolDevice.deviceName}</p>
				</td>
				<td>
				    <span>仪器型号：</span>
				    <p>${device.schoolDevice.devicePattern}</p>
				</td>
			</tr>
			<tr>
				<td>
				    <span>所在实验室：</span>
				    <p>${device.labRoom.labRoomName}</p>
				</td>
				<td>
				    <span>生产国别：</span>
				    <p>${device.schoolDevice.deviceCountry}</p>
				</td>
				<td>
				    <span>生产厂家：</span>
				    <p>${device.schoolDevice.manufacturer}</p>
				</td>
			</tr>
		</table>
	</div>
	<div id="TabbedPanels1" class="TabbedPanels">
	 <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceInfoRest(${device.id})">设备详情</a>
		</li>
		<%-- <c:if test="${device.CActiveByAllowSecurityAccess.id == 1}"> --%>
		<c:if test="${device.CDictionaryByAllowSecurityAccess.CCategory=='c_active' && device.CDictionaryByAllowSecurityAccess.CNumber=='1'}">
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceTrainingRest(${device.id})">培训计划</a>
		</li>
		</c:if>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceSettingRest(${device.id})">参数设置</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceImageRest(${device.id})">相关图片</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceVideoRest(${device.id})">相关视频</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceDocumentRest(${device.id})">相关文档</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceDimensionalCodeRest(${device.id})">二维码</a>
		</li>
	</ul>
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">
					<div id="title">培训通过人员</div>
					<a class="btn btn-new"  herf="#" onclick="openSetupLink();">返回</a>
					<a class="btn btn-new" herf="#" onclick="addStudent(1);">添加学生</a>
				</div>
					
				 <div class="content-double">
					<table >
						<thead>
							<tr>
								<th>学号</th>
								<th>姓名</th>
								<%--<th>专业</th>
								--%><th>身份</th>
								<th>所属学院</th>
							</tr>
						</thead>
						<c:forEach items="${studentsPass}" var="student">
						<tr>
							<td>${student.username}</td>
							<td>${student.cname}</td>
							<td>
							<c:if test="${student.userRole==0 }">
								学生
							</c:if>
							<c:if test="${student.userRole==2 }">
								研究生
							</c:if>
							</td>
							<td>${student.schoolAcademy.academyName }</td>
						</tr>
						</c:forEach>
						
						
						
					</table>
				</div>
      	
			</div>
		</div>
		
		<div id="addStudent" class="easyui-window " title="添加培训人员" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 600px; height: 500px;">
		<div class="content-box">
		<form:form id="userForm" method="post"   modelAttribute="student">
			<table class="tb" id="my_show">
				<tr>
					<td>姓名：<form:input id="cname" path="user.cname"/></td>
					<td>工号：<form:input id="username" path="user.username"/>
						
					
						<a onclick="addStudent(1);" >搜索</a>	
						<a onclick="Cancel();" >取消搜索</a>	
						
						
					</td>
					<td>
						
						<input type="button" value="添加" onclick="addUser(${device.id});">
					</td>
				</tr>
			</table>
		</form:form>
		
		<table id="my_show">
					<thead>
						<tr>
							<th style="width:10% !important">选择</th>
							<th style="width:30% !important">姓名</th>
							<th style="width:30% !important">工号</th>
							<th style="width:30% !important">所属学院</th>
							
						</tr>
					</thead>
						
					<tbody id="user_body">
						
					</tbody>
					
			</table>
			</div>
		</div>	
			<input type="hidden" id="labRoomId" value="${labRoomId }">
            <input type="hidden" id="deviceName" value="${deviceName }">
            <input type="hidden" id="deviceNumber" value="${deviceNumber }">
            <input type="hidden" id="username" value="${username }">
            <input type="hidden" id="page" value="${page }">
            <input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
	  </div>
	</div>
	
</div>

<!-- 下拉框的js -->
						<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
						<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
						<script type="text/javascript">
						    var config = {
						      '.chzn-select': {search_contains : true},
						      '.chzn-select-deselect'  : {allow_single_deselect:true},
						      '.chzn-select-no-single' : {disable_search_threshold:10},
						      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
						      '.chzn-select-width'     : {width:"95%"}
						    }
						    for (var selector in config) {
						      $(selector).chosen(config[selector]);
						    }
						</script>
						<!-- 下拉框的js -->
</body>
</html>
