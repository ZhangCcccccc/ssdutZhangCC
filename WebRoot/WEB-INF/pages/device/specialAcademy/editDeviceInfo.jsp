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

<!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  

<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->


<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">

 
function showImage(image){
	//alert(image.src);
	//$("#img").src=image.src;
	document.getElementById("img").src=image.src;
	$('#showImage').window({
		        top: 1200   
		     });
	$('#showImage').window('open');
}	
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhulims/device/editDevice.js"></script>
<script>
//保存设备详情
function saveDeviceReservationInfoRest(id){//将labRoomId deviceNumber deviceName page传递到后台
    var usernameManager = $("#usernameManager").val();
	var status = $("#status").val();
    var type = $("#type").val();
    var charge = $("#charge").val();
    var price = $("#price").val();
    var functionOfDevice = $("#functionOfDevice").val();
    var indicators = $("#indicators").val();
    var managerTelephone = $("#managerTelephone").val();
    var managerMail = $("#managerMail").val();
    var managerOffice = $("#managerOffice").val();
    var auditTimeLimit = -1;
    if($("#usernameManager").val()==""){
    	usernameManager="-1";
    }
    if($("#status").val()==""){
    	status=-1;
    }
    if($("#type").val()==""){
    	type=-1;
    }
    if($("#charge").val()==""){
    	charge=-1;
    }
    if($("#price").val()==""){
    	price=0;
    }
    if($("#functionOfDevice").val()==""){
    	functionOfDevice="-1";
    }
    if($("#indicators").val()==""){
    	indicators="-1";
    }
    if($("#managerTelephone").val()==""){
    	managerTelephone="-1";
    }
    if($("#managerMail").val()==""){
    	managerMail="-1";
    }
    if($("#managerOffice").val()==""){
    	managerOffice="-1";
    }
    if($("#auditTimeLimit").val()==null||$("#auditTimeLimit").val()==""){
    	//auditTimeLimit="-1";
    }else{
    	auditTimeLimit = $("#auditTimeLimit").val();
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
  	functionOfDevice=functionOfDevice.replace(/\+/g,"[Geng1Shang]");//将特殊字符"+"转化为[Geng1Shang]
    functionOfDevice=functionOfDevice.replace(/\ /g,"[Geng2Shang]");//将特殊字符" "转化为[Geng2Shang]
    functionOfDevice=functionOfDevice.replace(/\//g,"[Geng3Shang]");//将特殊字符"/"转化为[Geng3Shang]
    functionOfDevice=functionOfDevice.replace(/\?/g,"[Geng4Shang]");//将特殊字符"?"转化为[Geng4Shang]
    functionOfDevice=functionOfDevice.replace(/\%/g,"[Geng5Shang]");//将特殊字符"%"转化为[Geng5Shang]
    functionOfDevice=functionOfDevice.replace(/\#/g,"[Geng6Shang]");//将特殊字符"#"转化为[Geng6Shang]
    functionOfDevice=functionOfDevice.replace(/\&/g,"[Geng7Shang]");//将特殊字符"&"转化为[Geng7Shang]
    functionOfDevice=functionOfDevice.replace(/\=/g,"[Geng8Shang]");//将特殊字符"="转化为[Geng8Shang]
    
    indicators=indicators.replace(/\+/g,"[Geng1Shang]");//将特殊字符"+"转化为[Geng1Shang]
    indicators=indicators.replace(/\ /g,"[Geng2Shang]");//将特殊字符" "转化为[Geng2Shang]
    indicators=indicators.replace(/\//g,"[Geng3Shang]");//将特殊字符"/"转化为[Geng3Shang]
    indicators=indicators.replace(/\?/g,"[Geng4Shang]");//将特殊字符"?"转化为[Geng4Shang]
    indicators=indicators.replace(/\%/g,"[Geng5Shang]");//将特殊字符"%"转化为[Geng5Shang]
    indicators=indicators.replace(/\#/g,"[Geng6Shang]");//将特殊字符"#"转化为[Geng6Shang]
    indicators=indicators.replace(/\&/g,"[Geng7Shang]");//将特殊字符"&"转化为[Geng7Shang]
    indicators=indicators.replace(/\=/g,"[Geng8Shang]");//将特殊字符"="转化为[Geng8Shang]
	var url = "${pageContext.request.contextPath}/device/saveDeviceInfoRest/" + "${labRoomId}" + "/"+ "${deviceNumber}" + "/" + "${deviceName}" +"/"+"${username}"+ "/"+"${page}"+ "/"+usernameManager
	+ "/"+ status+ "/"+ type+ "/"+ charge+ "/"+ price+"/"+ functionOfDevice+"/"+ indicators+"/"+managerTelephone+"/"+managerMail+"/"+managerOffice+"/" +auditTimeLimit+"/" + id+ "/"+"${schoolDevice_allowAppointment}";
	
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
		<li class="TabbedPanelsTab selected" tabindex="0">
		<%--<a href="${pageContext.request.contextPath}/device/editDeviceInfo?id=${device.id}">设备详情</a>
		--%><a href="javascript:void(0);" onclick="editDeviceInfoRest(${device.id})">设备详情</a>
		</li>
		<%-- <c:if test="${device.CActiveByAllowSecurityAccess.id == 1}"> --%>
		<c:if test="${device.CDictionaryByAllowSecurityAccess.CCategory=='c_active' && device.CDictionaryByAllowSecurityAccess.CNumber=='1'}">
		<li class="TabbedPanelsTab" tabindex="0">
		<%--<a href="${pageContext.request.contextPath}/device/deviceTraining?deviceId=${device.id}">培训计划</a>
		--%><a href="javascript:void(0);" onclick="editDeviceTrainingRest(${device.id})">培训计划</a>
		</li>
		</c:if>
		<li class="TabbedPanelsTab" tabindex="0">
		<%--<a href="${pageContext.request.contextPath}/device/editDeviceSetting?id=${device.id}">参数设置</a>
		--%><a href="javascript:void(0);" onclick="editDeviceSettingRest(${device.id})">参数设置</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<%--<a href="${pageContext.request.contextPath}/device/deviceImage?deviceId=${device.id}">相关图片</a>
		--%><a href="javascript:void(0);" onclick="editDeviceImageRest(${device.id})">相关图片</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0"><%--
		<a href="${pageContext.request.contextPath}/device/deviceVideo?deviceId=${device.id}">相关视频</a>
		--%><a href="javascript:void(0);" onclick="editDeviceVideoRest(${device.id})">相关视频</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<%--<a href="${pageContext.request.contextPath}/device/deviceDocument?deviceId=${device.id}">相关文档</a>
		--%><a href="javascript:void(0);" onclick="editDeviceDocumentRest(${device.id})">相关文档</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<%--<a href="${pageContext.request.contextPath}/device/dimensionalCode?deviceId=${device.id}">二维码</a>
		--%><a href="javascript:void(0);" onclick="editDeviceDimensionalCodeRest(${device.id})">二维码</a>
		</li>
	</ul>
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			
			<div class="content-box">
				<div class="title">
				<div id="title">设备详情</div>
				<a class="btn btn-new"  herf="#" onclick="openSetupLink();">返回</a>
				</div>
					<form:form action="${pageContext.request.contextPath}/device/saveDeviceReservationInfo?deviceId=${device.id}" method="post" modelAttribute="device">
					<div class="new-classroom">
						<fieldset>
						    <label>设备管理员</label>
						      <form:select id="usernameManager" path="user.username" class="chzn-select">
						      <c:if test="${not empty device.user.username }">
						      	<form:option value="${device.user.username }">
						      	[${device.user.username }]${device.user.cname }
						      	</form:option>
						      </c:if>
						      <c:if test="${empty device.user.username }">
						      	<form:option value="">
						      	- - - 请选择- - - 
						      	</form:option>
						      </c:if>	
						      	<c:forEach items="${users}" var="t">
					              <form:option value="${t.username}">[${t.username}]${t.cname}</form:option>
					             </c:forEach>
								</form:select>
					</fieldset>	
					<fieldset>
						    <label>管理员电话</label>
						     <form:input id="managerTelephone" path="managerTelephone"/>
					</fieldset>	
					<fieldset>
						    <label>管理员邮箱</label>
						     <form:input id="managerMail" path="managerMail"/>
					</fieldset>	
					<fieldset>
						    <label>管理员办公室</label>
						     <form:input id="managerOffice" path="managerOffice"/>
					</fieldset>	
					<fieldset>
						    <label>设备状态</label>
						      <%-- <form:select id="status" path="CDeviceStatus.id" class="chzn-select">
						      	<form:option value="1">正常使用</form:option>
								<form:options items="${stutus}" itemLabel="name" itemValue="id"/>
								</form:select> --%>
								<form:select id="status" path="CDictionaryByDeviceStatus.id" class="chzn-select">
								<form:option value="">请选择</form:option>
								<form:options items="${stutus}" itemLabel="CName" itemValue="id"/>
								</form:select>
					</fieldset>	
						<fieldset>
						    <label>所属类型</label>
						      <%-- <form:select id="type" path="CDeviceType.id" class="chzn-select">
						      	<form:option value="">请选择</form:option>
								<form:options items="${types}" itemLabel="name" itemValue="id"/>
								</form:select> --%>
								<form:select id="type" path="CDictionaryByDeviceType.id" class="chzn-select">
								<form:option value="">请选择</form:option>
								<form:options items="${types}" itemLabel="CName" itemValue="id"/>
								</form:select>
					</fieldset>	
					<fieldset>
						    <label>收费标准</label>
						      <%-- <form:select id="charge" path="CDeviceCharge.id" class="chzn-select">
						      	<form:option value="">请选择</form:option>
								<form:options items="${charges}" itemLabel="name" itemValue="id"/>
								</form:select> --%>
								<form:select id="charge" path="CDictionaryByDeviceCharge.id" class="chzn-select">
								<form:option value="">请选择</form:option>
								<form:options items="${charges}" itemLabel="CName" itemValue="id"/>
								</form:select>
					</fieldset>
					<%-- <c:if test="${device.isAuditTimeLimit eq 1 &&(not empty device.CActiveByTeacherAudit&&device.CActiveByTeacherAudit.id eq 1)}"> --%>
					<c:if test="${device.isAuditTimeLimit eq 1 &&(not empty device.CDictionaryByTeacherAudit && device.CDictionaryByTeacherAudit.CCategory=='c_active' && device.CDictionaryByTeacherAudit.CNumber=='1')}">
					<fieldset>
						    <label>预约审核时间限制</label>
						      <form:select id="auditTimeLimit" path="auditTimeLimit">
						      	<form:option value="48">48小时（两天）</form:option>
						      	<form:option value="12">12小时</form:option>
						      	<form:option value="24">24小时（一天）</form:option>
						      	<form:option value="36">36小时</form:option>
						      	<form:option value="60">60小时</form:option>
						      	<form:option value="72">72小时（三天）</form:option>
						      	<form:option value="84">84小时</form:option>
						      	<form:option value="96">96小时（四天）</form:option>
							  </form:select>
					</fieldset>
					</c:if>
					<c:if test="${device.isAuditTimeLimit ne 1 }">
						<input type="hidden" id="auditTimeLimit" value="-1">
					</c:if>
						<fieldset>
						    <label>费用</label>
						     <form:input id="price" path="price"/>元
						</fieldset>
						
					 <fieldset class="introduce-box">
						     <label>主要技术指标</label>
							<form:textarea id="indicators" path="indicators"/>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>功能应用范围</label>
							<form:textarea id="functionOfDevice" path="function"/>
					 </fieldset><%--
					 <fieldset class="introduce-box">
						     <label>技术特色</label>
							<form:textarea path="features"/>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>主要应用领域</label>
							<form:textarea path="applications"/>
					 </fieldset>
					--%><sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN,ROLE_LABMANAGER">
					 <fieldset class="introduce-box">
						      <input type="button" onclick="saveDeviceReservationInfoRest(${device.id})" value="提交"/>
							<input type="button" value="返回" onclick="openSetupLink()"/>
					 </fieldset>
					 </sec:authorize>
			
					</div>
						
						
					</form:form>
		
  			<input type="hidden" id="labRoomId" value="${labRoomId }">
            <input type="hidden" id="deviceName" value="${deviceName }">
            <input type="hidden" id="deviceNumber" value="${deviceNumber }">
            <input type="hidden" id="username" value="${username }">
            <input type="hidden" id="page" value="${page }">
    		<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
      		<input type="hidden" id="schoolDevice_allowAppointment" value="${schoolDevice_allowAppointment}">
			</div>
		</div>
		
	  </div>
	</div>
	
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
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
