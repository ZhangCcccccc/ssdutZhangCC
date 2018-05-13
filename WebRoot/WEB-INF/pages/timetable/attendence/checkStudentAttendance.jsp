<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<!--直接排课  -->

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
</head>

<div class="iStyle_RightInner">

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
	<div class="tool-box">

		<a class="btn btn-new" onclick="window.history.go(-1)" >返回</a> 
		<table class="list_form">
		<tr>
		<th>学期：${t.schoolCourseDetail.schoolCourse.schoolTerm.termName}</th>
	   	<th>选课组编号：${t.courseCode}</th>
	   	<th>学生人数：${checkTotalAttendance.size()}</th>
		</tr>
		</table>
	</div>
<div class="content-box">
<div class="title">考勤查看列表</div>
<table> 
<thead>
<tr>
   <th>序号</th>
   <th>学号</th>
   <th>姓名</th>
  <c:forEach begin="1" end="${weeks}" var="i">
  <th>${i}</th>
  </c:forEach>
   
</tr>
</thead>
<tbody>
<c:forEach items="${checkTotalAttendance}" var="current"  varStatus="i">	

<tr>
    <td>${i.count}</td>
    <td>${current.studentnumber}</td>
    <td>${current.studentname}</td>
    <c:forEach items="${current.map}" var="m">
    <td>${m.value}</td>
    </c:forEach>
    
    
    
</tr>

</c:forEach> 
</tbody>

</table>
</div>
</div>
</div>
</div>
</div>
</div>

<div id="searchFile" class="easyui-window" title="直接排课" closed="true" iconCls="icon-add" style="width:850px;height:450px">
<br>
<form:form id="form_lab" action="" method="post"  modelAttribute="timetableAppointment">
当前学期：${schoolTerm.termName} 当前周次：${week }
<!-- schoolCourseDetail的no -->

<hr>
<br>
<table  border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
   <td></td>
   <td></td>
</tr>

</table>
<br>
<br>
<br>
<hr>
<input type="submit" value="确定">
</form:form>
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

