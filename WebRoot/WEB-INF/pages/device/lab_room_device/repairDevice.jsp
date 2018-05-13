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
<div class="content-box" style="height : 210px;"><!--select下拉框内容显示不全，高度不够被覆盖掉了  -->

<div class="title">
	<div id="title">添加维修记录</div>
</div>  
	  <form:form action="${pageContext.request.contextPath}/device/saveNewRepair" method="post" modelAttribute="lrdr">
				<form:hidden path="id"/>
		<table>
			<tr>
			<td>维修结果:
				<%-- <form:select path="CLabRoomDeviceRepairStatus.id" class="chzn-select"> --%>
				<form:select path="CDictionaryByStatusId.id" class="chzn-select">
					<form:option value="">--请选择--</form:option>
					<form:options items="${status }" itemValue="id" itemLabel="CName"/>
					<%--<c:forEach items="${status }" var="status" varStatus="i">
						<form:option value="${status.id }">${status.name }</form:option>
					</c:forEach>
					--%>
				</form:select>
				</td>
			</tr>
			<tr>
			<td>维修记录:
				<%-- <th>申请日期</th><td><form:input path="lendingTime"  onclick="new Calendar().show(this);" value="${date }" />  </td>
				<th>预归还日期</th><td><form:input path="returnTime" onclick="new Calendar().show(this);" value="${date }"/>  </td> --%>
				<form:textarea path="repairRecords" />
			</tr>
			<tr>
			<%-- <tr>
				<th>领用人</th><td>${user.cname }<form:hidden path="userByLendingUser.username" value="${user.username }"/></td>
				<th>指导老师</th><td>
					<form:select path="userByTeacher.username" class="chzn-select"  >
						<form:option value="">--请选择--</form:option>
						<form:options items="${users }"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th>领用内容</th><td><form:textarea path="content" /></td>
				<th>领用设备</th><td>${labRoomDevice.schoolDevice.deviceName}<form:hidden path="schoolDevice.deviceNumber" value="${labRoomDevice.schoolDevice.deviceNumber }"/></td>
			</tr>
			<form:hidden path="lendType" value="2"/>
			<tr> --%>
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
