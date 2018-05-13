<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<html>
<head>
<meta name="decorator" content="iframe" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
 <script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#my_show").jqprint();
	});
});	
</script> 
<script type="text/javascript">
function exportSelect(){
	 document.form.action="exportExcelSelectUser";
	 document.form.submit();
	}
</script>
</head>

<body>

<!-- 导航栏 -->
<div class="navigation">
<div id="navigation">
<ul>
<li><a href="">排课管理</a></li>
<li class="end"><a href="${pageContext.request.contextPath}/system/listUser?currpage=1">我的选课记录</a></li>
</ul>
</div>
</div>

<!-- 导航栏 -->
<div class="iStyle_RightInner">
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<!-- 查询、导出、打印 -->
<%-- <div class="tool-box">
    <form:form name="form" method="Post" action="${pageContext.request.contextPath}/system/listUser?currpage=1" modelAttribute="timetableGroupStudents">
	<ul>
	    <li> 课程或选课组：
	    <form:select class="chzn-select"  path="user.username" id="user.username" cssStyle="width:600px" >
	    <c:forEach items="${userMap}" var="curr"  varStatus="i">	
	       <form:option value="${curr.username}" label="${curr.username};${curr.cname};${curr.schoolAcademy.academyName}" />  
	    </c:forEach>
        </form:select></li>
		<li><input type="button" value="查询"></li>
		<li><a href="javascript:void(0)"><input type="button" value="取消查询"></a></li>
		<li><input type="button" value="导出" ></li>
		<li><input type="button" value="打印" id="print"></li>
	</ul>
</form:form>
</div>       
 --%>
<div class="content-box">
<div class="title">学生选课情况</div>
<table class="tb" cellspacing="0" id="my_show"> 
<thead>
<tr>
    <th>序号</th>
    <th>选课状态</th>
    <th>课程编号</th>
    <th>课程名称</th>
    <th>选课组编号</th>
    <th>指导教师</th>
    <th>实验室</th>
    <th>排课安排</th>
    <th>批次</th>
    <th>选课时间</th>
    <th>操作</th>
</tr>
</thead>
<tbody>
<c:forEach items="${timetableGroupStudentses}" var="current"  varStatus="i">	
<tr>
   <td>${i.count}</td>
   <td>
   <c:if test="${current.timetableGroup.timetableBatch.ifselect==1}">
                          学生选课                   
   </c:if>
    <c:if test="${current.timetableGroup.timetableBatch.ifselect==0}">
        自动选课
   </c:if>
   &nbsp;</td>
   <td>${current.timetableGroup.timetableAppointment.schoolCourse.schoolCourseInfo.courseNumber}</td>
   <td>${current.timetableGroup.timetableAppointment.schoolCourse.courseName}</td>
   <td>${current.timetableGroup.timetableBatch.courseCode}</td>
  <%--  <!--实验项目  -->
   <td>
    <c:forEach items="${current.timetableGroup.timetableAppointment.timetableItemRelateds}" var="var"  varStatus="j">
    ${var.operationItem.itemName}<br>
    </c:forEach>
   </td> --%>
   <td>
    <c:forEach items="${current.timetableGroup.timetableAppointment.timetableTeacherRelateds}" var="var"  varStatus="j">
    ${var.user.cname}<br>
    </c:forEach>
   </td>
   <td>
    <c:forEach items="${current.timetableGroup.timetableAppointment.timetableLabRelateds}" var="var"  varStatus="j">
    ${var.labRoom.labRoomName}<br>
    </c:forEach>
   </td>
   <td>星期${current.timetableGroup.timetableAppointment.weekday}
   节次：[${current.timetableGroup.timetableAppointment.startClass}-${current.timetableGroup.timetableAppointment.endClass}]
   周次：[${current.timetableGroup.timetableAppointment.startWeek}-${current.timetableGroup.timetableAppointment.endWeek}]
   </td>
   <td>${current.timetableGroup.timetableBatch.batchName}</td>
   <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${current.timetableGroup.timetableBatch.startDate.time}" type="both"/>至<br>
       <fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${current.timetableGroup.timetableBatch.endDate.time}" type="both"/></td>
   <td><a class="btn btn-common" href='javascript:void(0)'>选课</a></td>
</tr>
</c:forEach>
<tr>
<td colspan=12>
<c:choose><c:when test='${newFlag}'>
    <div class="pagination">
    <a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.firstPage}" target="_self"><fmt:message key="firstpage.title"/></a>				    
	<a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.previousPage}" target="_self"><fmt:message key="previouspage.title"/></a>
	 第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/userList?currpage=${page}">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/userList?currpage=${j.index}">${j.index}</option></c:if></c:forEach></select>页
	<a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.nextPage}" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.lastPage}" target="_self"><fmt:message key="lastpage.title"/></a>
    </div>
    <div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
    <strong><fmt:message key="totalpage.title"/>:${pageModel.totalPage}&nbsp;</strong>
</div>
</c:when><c:otherwise>
<div class="pagination_desc" style="float: left">
    <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
</div>
</c:otherwise></c:choose>
</td>
</tr>
</tbody>
</table>    
</div>
</div>
</div>
</div>
</div>
</div>

</body>
</html>

