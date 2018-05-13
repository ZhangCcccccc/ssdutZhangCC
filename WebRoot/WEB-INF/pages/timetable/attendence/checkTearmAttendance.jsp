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
		<th>周次：[${t.startWeek}-${t.endWeek}]</th>
	   	<th>选课组编号：${t.courseCode}</th>
	   	<th>学生人数：${checkList.size()}</th>
		</tr>
		</table>
	</div>
<div class="content-box">
<div class="title">成绩查看列表</div>
<table> 
<thead>
<tr>
   <th>序号</th>
   <th>学号</th>
   <th>姓名</th>
  <c:forEach begin="1" end="${time}" var="t">
  <th>${t}</th>
  </c:forEach>
   
</tr>
</thead>
<tbody>
<c:forEach items="${checkList}" var="current"  varStatus="i">	

<tr>
    <td>${i.count}</td>
    <td>${current.studentnumber}</td>
    <td>${current.studentname}</td>
    <c:forEach begin="1" end="${time}" var="t">
    <c:forEach items="${current.map}" var="m">
    <c:if test="${t==m.key}">
    <td>${m.value}</td>
    </c:if>
    </c:forEach>
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

