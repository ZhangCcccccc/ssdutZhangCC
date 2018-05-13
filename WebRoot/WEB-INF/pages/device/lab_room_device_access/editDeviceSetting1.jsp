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

<style>
.tool-box2 th{text-align:left;}
</style>
</head>


<body>

<div class="right-content">	
	<div class="tool-box1">
		<table>
			<tr>
				<th>设备编号</th><td>${device.schoolDevice.deviceNumber}</td>
				<th>设备名称</th><td>${device.schoolDevice.deviceName}</td>
			</tr>
			<tr>
				<th>所在实验室</th><td>${device.labRoom.labRoomName}</td>
				<th>生产国别</th><td>${device.schoolDevice.deviceCountry}</td>
			</tr>
			<tr>
				<th>仪器型号</th><td>${device.schoolDevice.devicePattern}</td>
				<th>生产厂家</th><td>${device.schoolDevice.manufacturer}</td>
			</tr>
		</table>
	</div>
	<div id="TabbedPanels1" class="TabbedPanels">
	 
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			
			<div class="content-box">
				<div class="title">参数设置</div>
					<form:form action="${pageContext.request.contextPath}/device/saveDeviceSetting" method="post" modelAttribute="device">
					<div class="new-classroom">
						<table>
			<tr>
				<form:hidden path="id"/>
				<td>是否允许出借:</td>
				<td>
				<c:forEach items="${CActives}" var="active">
				
				<form:radiobutton path="CDictionaryByAllowLending.id" value="${active.id}" />${active.CName}
				
				</c:forEach>										
				</td>
			</tr>	
			<tr>
				<td>是否允许预约:</td>
				<td>
				<c:forEach items="${CActives}" var="active">
				
				<form:radiobutton path="CDictionaryByAllowAppointment.id" value="${active.id}" />${active.CName}
				
				</c:forEach>										
				</td>
			</tr>	
			<tr>
				<td>预约形式选择:</td>
				<td>
				<c:forEach items="${CAppointmentTypes}" var="type">
				
				<form:radiobutton path="CDictionaryByAppointmentType.id" value="${type.id}" />${type.CName}
				
				</c:forEach>									
				</td>
			</tr>	
			<tr>
				<td>预约是否需要审核:</td>
				<td>
				<c:forEach items="${CActives}" var="activ">
				
				<form:radiobutton path="CDictionaryByIsAudit.id" value="${activ.id}" />${activ.CName}
				
				</c:forEach>								
				</td>
			</tr>	
			<tr>
				<td>是否需要导师审核:</td>
				<td>
				<c:forEach items="${CActives}" var="activ">
				
				<form:radiobutton path="CDictionaryByTeacherAudit.id" value="${activ.id}" />${activ.CName}
				
				</c:forEach>								
				</td>
			</tr>	
			<tr>
				<td>是否需要实验室管理员审核:</td>
				<td>
				<c:forEach items="${CActives}" var="activ">
				
				<form:radiobutton path="CDictionaryByManagerAudit.id" value="${activ.id}"/>${activ.CName}
				
				</c:forEach>								
				</td>
			</tr>	
			<tr>
				<td>是否需要安全准入:</td>
				<td>
				<c:forEach items="${CActives}" var="activ">
				
				<form:radiobutton path="CDictionaryByAllowSecurityAccess.id" value="${activ.id}"/>${activ.CName}
				
				</c:forEach>								
				</td>
			</tr>	
			<tr>
				<td>准入形式选择:</td>
				<td>
				<c:forEach items="${CLabAccessTypes}" var="type">
				
				<form:radiobutton path="CDictionaryBySecurityAccessType.id" value="${type.id}"/>${type.CName}
				
				</c:forEach>								
				</td>
			</tr>	
			<tr>	
				<td>
				<input type="submit" value="确定">
				<input type="button" value="取消" >
				</td>
			</tr>
			
			</table>

					</div>
						
						
					</form:form>
				
	
    
      	
			</div>
		</div>
		
	  </div>
	</div>
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</div>

</body>
</html>
