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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhulims/device/viewDeivce.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/editDevice.js"></script>  
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
<style>
.tool-box2 th{text-align:left;}
.table_left td{
	text-align:left;
	border-bottom: 1px dotted #ccc;
	height:15px;
}
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
		<%--<a href="${pageContext.request.contextPath}/device/viewDeviceInfo?id=${device.id}">设备详情</a>
		--%><a href="javascript:void(0);" onclick="viewDeviceInfoRest(${device.id})";>设备详情</a></li>
		<%-- <c:if test="${device.CActiveByAllowSecurityAccess.id == 1}"> --%>
		<li class="TabbedPanelsTab" tabindex="0">
		<%--<a href="${pageContext.request.contextPath}/device/viewDeviceTraining?deviceId=${device.id}">培训计划</a>--%>
		<a href="javascript:void(0);" onclick="viewDeviceTrainingRest(${device.id})";>培训计划</a>
		</li>
		<%--<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/viewDeviceSetting?id=${device.id}">参数详情</a>
		<a href="javascript:void(0);" onclick="viewDeviceSettingRest(${device.id})";>参数详情</a></li>--%>
		<%--<li class="TabbedPanelsTab" tabindex="0">
        <a href="javascript:void(0);" onclick="Access(${device.id})";>设备预约</a></li>--%>
		<li class="TabbedPanelsTab" tabindex="0"><a href="javascript:void(0);" onclick="viewDeviceReservationRest(${device.id},1)";>使用情况</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<%--<a href="${pageContext.request.contextPath}/device/viewDeviceImage?deviceId=${device.id}">相关图片</a>
		--%><a href="javascript:void(0);" onclick="viewDeviceImageRest(${device.id})";>相关图片</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<%--<a href="${pageContext.request.contextPath}/device/viewDeviceVideo?deviceId=${device.id}">相关视频</a>
		--%><a href="javascript:void(0);" onclick="viewDeviceVideoRest(${device.id})";>相关视频</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<%--<a href="${pageContext.request.contextPath}/device/viewDeviceDocument?deviceId=${device.id}">相关文档</a>
		--%><a href="javascript:void(0);" onclick="viewDeviceDocumentRest(${device.id})";>相关文档</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<%--<a href="${pageContext.request.contextPath}/device/viewDimensionalCode?deviceId=${device.id}">二维码</a>
		--%><a href="javascript:void(0);" onclick="viewDimensionalCodeRest(${device.id})";>二维码</a></li>
		<%--<li class="TabbedPanelsTab" tabindex="0">
        <a href="javascript:void(0);" onclick="viewDeviceReservationRestAll(${device.id},1)";>汇总统计</a></li>--%>
	</ul>
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			
			<div class="content-box">
				<div class="title">
				<div id="title">设备详情</div>
				<a class="btn btn-new"  herf="#" onclick="openSetupLink();">返回</a>	
				</div>
					  <div>
					  	<fieldset style="width:95%">
						    <legend>基本详情</legend>
						    <table class="table_left">
						<tr>
							<th style="width:10%">设备管理员</th><td>${device.user.cname}</td>
							<th style="width:10%">管理员电话</th><td>${device.managerTelephone}</td>
							<th style="width:10%">管理员邮箱</th><td>${device.managerMail}</td>
							<th style="width:10%">管理员办公室</th><td>${device.managerOffice}</td>
						</tr>
						<tr>
							<th style="width:65px">设备状态</th><td style="width:30%"><%-- ${device.CDeviceStatus.name} --%>${device.CDictionaryByDeviceStatus.CName }</td>
							<th style="width:65px">所属类型</th><td>${device.CDictionaryByDeviceType.CName}</td>
						</tr>
						<tr>
							<th>收费标准</th><td colspan="3">${device.CDictionaryByDeviceCharge.CName}</td>
							<th>费用</th><td colspan="">${device.price}</td>
						</tr>
						
						</table> 
						    </fieldset>
						    <fieldset style="width:95%">
						    <legend>主要技术指标</legend>
						    <div style="width:100%; word-break: break-all; word-wrap: break-word;padding:8px">
						    ${device.indicators}
						    </div>
						    </fieldset>
						    
						    <fieldset style="width:95%">
						    <legend>功能应用范围</legend>
						    <div style="width:100%; word-break: break-all;word-wrap: break-word;padding:8px">
						    ${device.function}
						    </div>
						    </fieldset>
						    
						    <input type="hidden" id="labRoomId" value="${labRoomId }">
				  			<input type="hidden" id="deviceName" value="${deviceName }">
				  			<input type="hidden" id="deviceNumber" value="${deviceNumber }">
				  			<input type="hidden" id="username" value="${username }">
				  			<input type="hidden" id="page" value="${page }">
				  			<input type="hidden" id="schoolDevice_allowAppointment" value="${schoolDevice_allowAppointment}">
				  			<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
				  			
					  </div>
			</div>
		</div>
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
