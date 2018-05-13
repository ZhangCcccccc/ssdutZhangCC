<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>


<html>
<head>
<meta name="decorator" content="iframe"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式 -->	
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_LeftList.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_Searcher.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_SystemUI_Allpages.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
</head>

<div class="navigation">
<div id="navigation">
	<ul>
		<li><a href="javascript:void(0)">实验设备管理</a></li>
		<li class="end"><a href="javascript:void(0)">借用单审核</a></li>
	</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">

<div class="title">
	<div id="title">审核借用单</div>
</div>   	
    		 		
<form:form action="${pageContext.request.contextPath}/device/saveAuditDeviceLending?idKey=${idKey }" method="POST" modelAttribute="lrdlr">
<table cellpadding="0" cellspacing="0" id="viewTable">
	<tr>
				<th>申请日期</th><td><fmt:formatDate value="${lrdl.lendingTime.time }" pattern="yyyy-MM-dd"/></td>
				<th>预归还日期</th><td><fmt:formatDate value="${lrdl.returnTime.time }" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<th>借用人</th><td>${lrdl.userByLendingUser.cname }</td>
				<th>指导老师</th><td>${lrdl.userByTeacher.cname }</td>
			</tr>
			<tr>
				<th>借用内容</th><td>${lrdl.content }</td>
				<th>借用设备</th><td>${lrdl.labRoomDevice.schoolDevice.deviceName}</td>
			</tr>
</table>    
<table cellpadding="0" cellspacing="0" id="viewTable">
	<tr><form:hidden path="labRoomDeviceLending.id" value="${idKey }"/>
		<th>审核人</th><td>${user.cname }<form:hidden path="user.username" value="${user.username }"/></td>
	</tr>
	<tr>
		<th>审核结果</th><td>
		<%-- <form:select path="CAuditResult.id" >
		<form:option value="2">审核通过</form:option>
		<form:option value="3">审核拒绝</form:option>
		</form:select></td> --%>
		<form:select path="CDictionary.id" >
		<form:option value="615">审核通过</form:option>
		<form:option value="616">审核拒绝</form:option>
		</form:select></td>
	</tr>
	<tr>
		<th>备注</th><td><form:textarea path="remark" /></td>
	</tr>
</table>
        <input type="submit" value="确定" />
        <input type="button" value="取消" onclick="window.history.go(-1);" />
</form:form>         
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
          
</div>
</div>
</div>
</div>
</body>
</html>