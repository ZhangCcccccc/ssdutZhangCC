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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/viewDeivce.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/editDevice.js"></script>  


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
	 <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceInfoRest(${device.id})";>设备详情</a></li>
		<%-- <c:if test="${device.CActiveByAllowSecurityAccess.id == 1}"> --%>	
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceTrainingRest(${device.id})";>培训计划</a>
		</li>
		<%--<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceSettingRest(${device.id})";>参数详情</a></li>--%>
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
		<%--<li class="TabbedPanelsTab selected" tabindex="0">
        <a href="javascript:void(0);" onclick="viewDeviceReservationRestAll(${device.id},1)";>汇总统计</a></li>--%>
	</ul>
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		     <div class="tool-box">
			     <form:form name="queryForm" action="${pageContext.request.contextPath}/device/listReservationByDevice?deviceId=${deviceId}&page=1" method="post" modelAttribute="reservation">
				</form:form>
			 </div>
			 	<div align="center">
	            	<font color="blue">该设备的使用机时数为：${totalHourBD }时，预约次数为：${totalRecords}次</font>
	            </div>
	    	<div class="content-box">
	    		<div class="title">
	    			<div id="title">汇总统计</div>
	    			<a class="btn btn-new"  herf="#" onclick="openSetupLink();">返回</a>	
	    		</div>
	            <table class="tb" id="my_show"> 
	                <thead>
	                    <tr>
	                    	<th style="width:3%">序号</th>
	                        <th style="width:5%">预约人</th>
	                        <th style="width:10%">预约时间</th>
	                        <th style="width:10%">使用机时数</th>
	                        <th style="width:5%">导师</th>
	                    </tr>
	                </thead>
	                <tbody>
	                		<c:forEach items="${reservationList}" var="reservation" varStatus="i">
	                		<tr>
	                			<c:if test="${i.count eq 1 }">
	                				<c:set var="startTime" value="${reservation.time.time}"></c:set>
	                			</c:if>
	                			<c:if test="${i.count eq reservationList.size() }">
	                				<c:set var="endTime" value="${reservation.time.time}"></c:set>
	                			</c:if>
		                        <td>${i.count+(page-1)*pageSize}</td>
		                        <td>${reservation.userByReserveUser.cname}[${reservation.userByReserveUser.username}]</td>
		                        <td><fmt:formatDate value="${reservation.time.time}" pattern="yyyy-MM-dd HH:mm"/></td>
		                        <td>
		                        <fmt:formatNumber value="${reservation.reserveHours}" type="number"  maxFractionDigits="2"/>
		                        </td>
		                        <td>${reservation.userByTeacher.cname}[${reservation.userByTeacher.username}] </td>
	                        </tr>
	                		</c:forEach>
	                </tbody>
	            </table>
	<!-- 分页模块 -->
		<div class="page" >
	        ${totalRecords}条记录,共${pageModel.totalPage}页
	    <a href="javascript:void(0)" onclick="viewDeviceReservationRestAll(${device.id},1)" target="_self">首页</a>			    
		<a href="javascript:void(0)" onclick="viewDeviceReservationRestAll(${device.id},${pageModel.previousPage})" target="_self">上一页</a>
		<a href="javascript:void(0)" onclick="viewDeviceReservationRestAll(${device.id},${pageModel.nextPage})" target="_self">下一页</a>
	 	<a href="javascript:void(0)" onclick="viewDeviceReservationRestAll(${device.id},${pageModel.lastPage})" target="_self">末页</a>
	    </div>
	<!-- 分页模块 -->
	</div>
	
	</div>
  			<input type="hidden" id="labRoomId" value="${labRoomId }">
  			<input type="hidden" id="deviceName" value="${deviceName }">
  			<input type="hidden" id="deviceNumber" value="${deviceNumber }">
  			<input type="hidden" id="username" value="${username }">
  			<input type="hidden" id="page" value="${page }">
  			<input type="hidden" id="currpage" value="${currpage }">
    		<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
		</div>
	  </div>
	</div>
	
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>

</body>
</html>
