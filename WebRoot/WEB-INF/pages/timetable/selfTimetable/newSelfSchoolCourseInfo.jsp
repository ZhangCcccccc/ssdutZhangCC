<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<fmt:setBundle basename="bundles.projecttermination-resources" />
<jsp:useBean id="now" class="java.util.Date" /> 
<html>
<head>
<meta name="decorator" content="iframe" />
<title><fmt:message key="html.title" /></title>
<!-- <meta name="decorator" content="iframe"/> -->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>

<!-- 下拉框的样式 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->

<%--  <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery-1.8.3.min.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/thezzmes/icon.css" type="text/css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" />
<script type="text/javascript">

$(document).ready(function(){
$("#schoolCourse_courseNo").chosen().change(function(){

$("#courseCode").val($("#schoolCourse_courseNo").val()+ "-" + ${maxId});;

});
}); 

</script>

</head>
<body>
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li><a href="javascript:void(0)">自主排课管理</a></li>
	<li class="end"><a href="javascript:void(0)">新建课程</a></li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">
    <c:if test="${flagId == '-1' }">
    <div id="title">新建课程(<font color=red>*</font>必填)</div>
    </c:if>
	<c:if test="${flagId != '-1' }">
    <div id="title">编辑课程(<font color=red>*</font>必填)</div>
    </c:if>
</div>
<form:form action="${pageContext.request.contextPath}/timetable/selfTimetable/saveSelfSchoolCourseInfo?flagID=${flagId}" method="post" modelAttribute="schoolCourseInfo">
<!--新增或修改的标记位  -->
<fieldset class="introduce-box">
<legend>课程基本信息</legend>
<table id="listTable" width="50%" cellpadding="0">
<tr>
    
	<td>课程名称<font color=red>*</font></td>
	<td>
	<form:input path="courseName" class="validatebox"  required="true" id="courseName" />
	</td>
	<td>课程编号</td>
	<td>
	<form:input path="courseNumber" id="courseNumber" value="self-${user.schoolAcademy.academyNumber }-${maxId }" readonly="true"/>
	</td>
</tr>
<tr>
    <td>当前单位</td>
	<td>
	  <b>${user.schoolAcademy.academyName }</b>
	  <input type="hidden" name="academyNumber" value="${user.schoolAcademy.academyNumber }">
	</td>
	<td>创建日期 </td>
	<td>
       <b><fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" /></b>
       <input type="hidden" name="createdAt" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />" >
	   <input type="hidden" name="updatedAt" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />" >
	   
	</td>
	</td>
	
</tr>
</table>
</fieldset>


<div>
	<input type="submit" value="提交">&nbsp;&nbsp;
	<a  class="btn btn-common"  href="${pageContext.request.contextPath}/timetable/selfTimetable/listSchoolCourseInfo?currpage=1" >返回列表</a>
</div>
</form:form>

</div>

<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var config = {
	'.chzn-select' : {},
	'.chzn-select-deselect' : {
		allow_single_deselect : true
	},
	'.chzn-select-no-single' : {
		disable_search_threshold : 10
	},
	'.chzn-select-no-results' : {
		no_results_text : '选项, 没有发现!'
	},
	'.chzn-select-width' : {
		width : "95%"
	}
}
for ( var selector in config) {
	$(selector).chosen(config[selector]);
}
</script>

</div>
</div>
</div>
</div>
</body>
<!-------------列表结束----------->
</html>