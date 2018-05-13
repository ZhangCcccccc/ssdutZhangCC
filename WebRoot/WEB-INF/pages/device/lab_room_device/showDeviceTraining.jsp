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
<script src="${pageContext.request.contextPath}/js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>  

<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery-1.8.3.min.js"></script>
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
				<div class="title">设备管理</div>
					
					<div class="new-classroom">
						<fieldset>
						    <label>设备管理员</label>
						      ${device.user.username}
					</fieldset>	
					<fieldset>
						    <label>设备状态</label>
						     <%-- ${device.CDeviceStatus.name} --%>
						     ${device.CDictionaryByDeviceStatus.CName}
						      
					</fieldset>	
						<fieldset>
						    <label>所属类型</label>
						     <%-- ${device.CDeviceType.name} --%>
						     ${device.CDictionaryByDeviceType.CName}
						      
					</fieldset>	
					<fieldset>
						    <label>收费标准</label>
						     <%-- ${device.CDeviceCharge.name} --%>
						     ${device.CDictionaryByDeviceCharge.CName}
						      
					</fieldset>
						<fieldset>
						    <label>费用</label>
						    ${device.price}
					</fieldset>
					 <fieldset class="introduce-box">
						     <label>主要技术指标</label>
						     <textarea rows="" cols="">${device.indicators}</textarea>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>功能应用范围</label>
						     <textarea rows="" cols="">${device.function}</textarea>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>技术特色</label>
						     <textarea rows="" cols="">${device.features}</textarea>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>主要应用领域</label>
						     <textarea rows="" cols="">${device.applications}</textarea>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>设备图片</label>
								<ul class="img-box">
								<c:forEach items="${device.commonDocuments}" var="d">
								
						    	<li><img alt="" src="${pageContext.request.contextPath}/${d.documentUrl}" width="200px" height="150px">
						    	 </li>
						    	
						     	</c:forEach>
						     	</ul>
					 </fieldset>
					  <fieldset class="introduce-box">
						     <label>设备相关视频</label>
							<ul class="img-box">
						     <c:forEach items="${device.commonVideos}" var="video" varStatus="i">
						      <li>
						      <embed src="${pageContext.request.contextPath}/${video.videoUrl}" autostart="true" loop="true" width="450" height="400" controller="pausebutton"></embed>
						      
						      </li>
						     </c:forEach>
								
								</ul>
					 </fieldset>
					

					</div>
						
				
					
	
     
    
			</div>
		</div>
		<!-- 培训开始 -->
		<%-- <div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">本学期的培训</div>
				<div class="tool-box2">
					<table>
						<tr>
							<th>培训时间</th>
							<th>培训地点</th>
							<th>培训内容</th>
							<th>培训教师</th>
							<th>培训人数</th>
						</tr>
						<c:forEach items="${trainings}" var="t" varStatus="i">
						<tr>
							<td><fmt:formatDate value="${t.time.time}" pattern="yyyy-MM-dd"/> </td>
							<td>${t.address}</td>
							<td>${t.content}</td>
							<td>${t.user.cname}</td>
							<td>${t.number}</td>
						</tr>
						</c:forEach>
						
					</table>
					
					
				</div>
			</div>
		</div> --%>
		<!-- 培训完成 -->
	  </div>
	</div>
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</div>
</body>
</html>
