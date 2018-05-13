<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>我要排课</title>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	

</head>
<body>

<div class="iStyle_RightInner">


<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">

<div class="title">选择选课组</div>
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/doReTimetableIframe" target="_parent">
<input type="hidden" name="term" value="${term }">
<input type="hidden" name="labroom" value="${labroom }">
<input type="hidden" name="weekday" value="${weekday }">
<input type="hidden" name="classids" value="${classids }">
<table > 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>&nbsp;</th>
</tr>
</thead>
<tbody>
<tr>
   <td>请选择选课组：
    <select id="courseCode" name="courseCode" class="chzn-select"  style="width:650px">
    	<c:forEach items="${courseCodes}" var="current"  varStatus="i">	
    	    <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR">
                <option value ="${current.courseCode}" >选课组编号：${current.courseCode} 课程名称：${current.courseName}[${current.schoolCourseInfo.courseNumber}] 授课老师：${current.userByTeacher.cname} ${current.userByTeacher.username} <%-- 选课组人数：${current.schoolCourseStudents.size()} --%></option>
            </sec:authorize>
            <sec:authorize ifNotGranted="ROLE_EXCENTERDIRECTOR">
                <c:if test="${current.userByTeacher.username==user.username}">
                <option value ="${current.courseCode}" >选课组编号：${current.courseCode} 课程名称：${current.courseName}[${current.schoolCourseInfo.courseNumber}] 授课老师：${current.userByTeacher.cname} ${current.userByTeacher.username} <%-- 选课组人数：${current.schoolCourseStudents.size() }--%></option>
                </c:if>
            </sec:authorize>
                    </c:forEach>
    </select>
   </td>
</tr>
<tr>
   <th colspan=6>
   &nbsp;
   </th>
</tr>
<tr>
   <th colspan=6>
   &nbsp;
   </th>
</tr><tr>
   <th colspan=6>
   &nbsp;
   </th>
</tr>
<tr>
   <td colspan=6>
   <hr>
   </td>
<tr>
   <td colspan=6>
   <input type="submit" value="确定" >
    </td>
</tr>
</tbody>
</table>
<hr>
</form>

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
</body>
</html>