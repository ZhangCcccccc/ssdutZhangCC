<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/iconFont.css"	rel="stylesheet">

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
</head>
<body>
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/timetable/selfTimetable/batchDeleteCourseStudents">查看学生选课情况</a></li>
</ul>
</div>
</div>
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/selfTimetable/batchDeleteCourseStudents">
<input type="hidden" value="${id }" name="id">
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title"><a onclick="return confirm('确定删除此条记录？')" href='javascript:void(0)'><input type="submit" value="批量删除"></a>&nbsp;&nbsp;&nbsp;&nbsp;<a  class="btn btn-common"  href="${pageContext.request.contextPath}/timetable/selfTimetable/listCourseCodes?currpage=1" >返回列表</a></div>
<table> 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>选择</th>
   <th>序号</th>
   <th>选课组编号</th>
   <th>课程名称</th>
   <th>学生编号</th>
   <th>学生姓名</th>
   <th>授课教师</th>
   <th>学院名称</th>
   
</tr>
</thead>
<tbody>
<c:forEach items="${timetableCourseStudents}" var="current"  varStatus="i">	
<tr>
     <td><input type='checkbox' name='VoteOption1' value="${current.id}"></td>
     <td>${i.count}</td>
     <td>${current.timetableSelfCourse.courseCode}</td>
     <td>${current.timetableSelfCourse.schoolCourseInfo.courseName}</td>
     <td>${current.user.username}</td>
     <td>${current.user.cname}</td>
     <td>${current.timetableSelfCourse.user.cname}</td>
     <td>${current.timetableSelfCourse.schoolAcademy.academyName }</td>
     
</tr>
</c:forEach> 
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
</form>

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