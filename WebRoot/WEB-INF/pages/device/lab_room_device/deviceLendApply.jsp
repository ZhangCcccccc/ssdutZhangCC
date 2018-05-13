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
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">

<div class="title">
	<div id="title">出借设备申请单</div>
</div>  
	  <form:form action="${pageContext.request.contextPath}/device/saveDeviceLendApply" method="post" modelAttribute="labRoomDeviceLend">
		<table>
			<tr>
				<th>申请日期</th><td><form:input path="lendingTime"  onclick="new Calendar().show(this);" value="${date }" />  </td>
				<th>预归还日期</th><td><form:input path="returnTime" onclick="new Calendar().show(this);" value="${date }"/>  </td>
			</tr>
			<tr>
				<th>借用人</th><td>${user.cname }<form:hidden path="userByLendingUser.username" value="${user.username }"/></td>
				<th>指导老师</th><td>
					<form:select path="userByTeacher.username" class="chzn-select"  >
						<form:option value="">--请选择--</form:option>
						<form:options items="${users }" itemLabel="cname" itemValue="username"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th>借用内容</th><td><form:textarea path="content" /></td>
				<th>借用设备</th><td>${labRoomDevice.schoolDevice.deviceName}<form:hidden path="labRoomDevice.id" value="${labRoomDevice.id}"/></td>
			</tr>
			<tr><th>配件</th><td><form:input path="parts" /></td></tr>
			<form:hidden path="lendType" value="1"/>
			<tr>
			<td>
				<input type="submit" value="保存"/>
				<input type="button" onclick="window.history.go(-1)" value="返回" /></td>
			</tr>
		</table>
	  </form:form>
	</div></div></div></div>
</div>
<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
</script>
<!-- 下拉框的js -->
</body>
</html>
