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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/viewDeivce.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/editDevice.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
function cancel(){
	window.location.href="${pageContext.request.contextPath}/device/listLabRoomDevice?page=1";
}
</script>

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
		<a href="javascript:void(0);" onclick="viewDeviceInfoRest(${device.id})";>设备详情</a></li>
		<%-- <c:if test="${device.CActiveByAllowSecurityAccess.id == 1}"> --%>
		<c:if test="${device.CDictionaryByAllowSecurityAccess.CCategory == 'c_active'&& device.CDictionaryByAllowSecurityAccess.CNumber=='1'}">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceTrainingRest(${device.id})";>培训计划</a>
		</li>
		</c:if>
		<%--<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceSettingRest(${device.id})";>参数详情</a></li>--%>
		<%--<li class="TabbedPanelsTab" tabindex="0">
        <a href="javascript:void(0);" onclick="Access(${device.id})";>设备预约</a></li>--%>
		<li class="TabbedPanelsTab" tabindex="0"><a href="javascript:void(0);" onclick="viewDeviceReservationRest(${device.id},1)";>使用情况</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceImageRest(${device.id})";>相关图片</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceVideoRest(${device.id})";>相关视频</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceDocumentRest(${device.id})";>相关文档</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDimensionalCodeRest(${device.id})";>二维码</a></li>
		<%--<li class="TabbedPanelsTab" tabindex="0">
        <a href="javascript:void(0);" onclick="viewDeviceReservationRestAll(${device.id},1)";>汇总统计</a></li>--%>
	</ul>
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			
			<div class="content-box">
				<div class="title">
				<div id="title">参数设置</div>
				<a class="btn btn-new"  herf="#" onclick="openSetupLinkNew();">返回</a>
				</div>
				<table>
					<tr>
						<%--<th>是否允许出借</th>--%><%--<td>${device.CDictionaryByAllowLending.CName}</td>--%>
						<th>是否允许预约</th><td>${device.CDictionaryByAllowAppointment.CName}</td>
						<th>预约形式</th><td>${device.CDictionaryByAppointmentType.CName}</td>
					</tr>
					<tr>
						<th>预约是否需要审核</th><td>${device.CDictionaryByIsAudit.CName}</td>
						<th>是否需要实验室管理员审核</th><td>${device.CDictionaryByLabManagerAudit.CName}</td>
					</tr>
					<%-- <c:if test="${device.CActiveByIsAudit.id==1}"> --%>
					<c:if test="${device.CDictionaryByIsAudit.CCategory=='c_active' && device.CDictionaryByIsAudit.CNumber=='1'}">
					<tr>
						<%--<th>是否需要导师审核</th>--%><%--<td>${device.CDictionaryByTeacherAudit.CName}</td>--%>
						<th>是否需要设备管理员审核</th><td>${device.CDictionaryByManagerAudit.CName}</td>
						<th>是否需要安全准入</th><td>${device.CDictionaryByAllowSecurityAccess.CName}</td>
					</tr>
					</c:if>
					<tr>
						<th>培训形式</th><td>${device.CDictionaryByTrainType.CName}</td>
					</tr>
				</table>	
				
	
   			<input type="hidden" id="labRoomId" value="${labRoomId }">
  			<input type="hidden" id="deviceName" value="${deviceName }">
  			<input type="hidden" id="deviceNumber" value="${deviceNumber }">
  			<input type="hidden" id="username" value="${username }">
  			<input type="hidden" id="page" value="${page }">
      		<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
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
