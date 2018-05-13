<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>

<head>
    <meta name="decorator" content="iframe"/>
    <link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/iconFont.css"	rel="stylesheet">
	
<!--直接排课  -->
<script>
/*
*调整排课弹出窗口
*/
function doAdminTimetable(id){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/doAdminTimetable?id=' + id + '" style="width:100%;height:100%;"></iframe>'
$('#doAdmin').html(con);  
$('#doAdmin').window({left:"0px", top:"0px"});
$('#doAdmin').window('open');
}

/*
*查看学生名单
*/
function listTimetableStudents(courseDetailNo){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/openSearchStudent?courseDetailNo=' + courseDetailNo + '" style="width:100%;height:100%;"></iframe>'
$('#doSearchStudents').html(con);  
$('#doSearchStudents').window({left:"0px", top:"0px"});
$('#doSearchStudents').window('open');
}

/*
*查看学生分组情况
*/
function listTimetableGroup(term,weekday,classids,courseCode,labroom){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/doIframeGroupReTimetable?term=' + term +'&weekday='+weekday+'&classids='+classids+'&courseCode='+courseCode+'&labroom='+labroom+'" style="width:100%;height:100%;"></iframe>'
$('#doSearchGroup').html(con);  
$('#doSearchGroup').window({left:"0px", top:"0px"});
$('#doSearchGroup').window('open');
}
</script>
</head>

<div class="iStyle_RightInner">
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">

<div class="content-box">
<div class="title">
	<div id="title">教务排课管理列表</div>
	<a class="btn btn-return" href="${pageContext.request.contextPath}/report/reportLabRate">返回</a>
</div>

<table> 
<thead>
<tr>
   <th>序号</th>
   <th>课程编号</th>
   <th>课程名称</th>
   <th>选课组编号</th>
   <th>实验项目名称</th>
   <th>排课安排</th>
   
   <th>上课教师</th>
   <th>指导教师</th>
   <th>实验室</th>
   <th>学生名单</th>
   <th></th>
   
</tr>
</thead>
<tbody>
<c:set var="ifRowspan" value="0"></c:set>
<c:set var="count" value="1"></c:set>
<c:forEach items="${timetableAppointments}" var="current"  varStatus="i">	
<c:set var="rowspan" value="0"></c:set>
<tr>
    <c:forEach items="${timetableAppointments}" var="current1"  varStatus="j">	
         <c:if test="${current1.courseCode==current.courseCode }">
            <c:set value="${rowspan + 1}" var="rowspan" />
         </c:if>
     </c:forEach>
     
     <c:if test="${rowspan>1&&ifRowspan!=current.courseCode }">
          <td rowspan="${rowspan }">${count}</td>
          <c:set var="count" value="${count+1}"></c:set>
     </c:if>
     <c:if test="${rowspan==1 }">
         <td >${count}</td>
         <c:set var="count" value="${count+1}"></c:set>
     </c:if>

    <!--课程编号  -->
     <c:if test="${rowspan==1 }">
         <td >${current.schoolCourse.schoolCourseInfo.courseNumber} </td>
     </c:if>
     <c:if test="${rowspan>1&&ifRowspan!=current.courseCode }">
         <td rowspan="${rowspan }">${current.schoolCourse.schoolCourseInfo.courseNumber}</td>
     </c:if>
     <!--课程名称  -->
     <c:if test="${rowspan==1 }">
         <td >${current.schoolCourse.courseName} </td>
     </c:if>
     <c:if test="${rowspan>1&&ifRowspan!=current.courseCode }">
         <td rowspan="${rowspan }">${current.schoolCourse.courseName}</td>
     </c:if>
     <!--选课组编号  -->
     <c:if test="${rowspan>1&&ifRowspan!=current.courseCode }">
         <td rowspan="${rowspan }">${current.schoolCourse.schoolCourseInfo.courseNumber}</td>
     </c:if>
     <c:if test="${rowspan==1 }">
         <td >${current.courseCode} </td>
     </c:if>
     <c:set var="ifRowspan" value="${current.courseCode }"></c:set>
     <td >
        <c:forEach var="entry" items="${current.timetableItemRelateds}">  
           <c:if test="${entry.operationItem.id!=0&&entry.operationItem.id!=null}">
              <c:out value="${entry.operationItem.lpName}" /><br>
           </c:if>
           <c:if test="${entry.operationItem.id==0||entry.operationItem.id==null}">
              ${current.schoolCourse.courseName}(课程名称)&nbsp;
           </c:if>
        </c:forEach>
        <c:if test="${fn:length(current.timetableItemRelateds)==0}">
         ${current.schoolCourse.courseName}(课程名称)
        </c:if>
        
     </td>
     
     <td>
                  星期 ${current.weekday}
                  周次：<c:if test="${current.weekday==1 }">[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==2 }">[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==3 }">[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==4 }">[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==5 }">[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==6 }">[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==7 }">[${current.startWeek }-${current.endWeek }]
     </c:if>
               节次：[${current.startClass}-${current.endClass}]</td>
     <td>
     <!--上课教师  -->
     <c:forEach var="entry" items="${current.timetableTeacherRelateds}">  
     <c:out value="${entry.user.cname}" />  
     </c:forEach> 
     </td>
     <td>
     <!--指导教师  -->
     <c:forEach var="entry" items="${current.timetableTutorRelateds}">  
     <c:out value="${entry.user.cname}" />  
     </c:forEach>
     </td>
     <td>
     <!--相关实验分室  -->
     <c:forEach var="entry" items="${current.timetableLabRelateds}">  
     <c:out value="${entry.labRoom.labRoomName}" /><br>  
     </c:forEach>
     </td>
     <td>
         <a class="btn btn-common" href='javascript:void(0)' onclick='listTimetableStudents("${current.schoolCourseDetail.courseDetailNo}")'>${fn:length(current.schoolCourseDetail.schoolCourseStudents)}</a></td>
     <!-- 排课方式-->

</tr>
</c:forEach> 
</tbody>
</table>
<a href="${pageContext.request.contextPath}/report/reportStudentTime?currpage=${pageModel.firstPage}&academyNumber=${academyNumber}&terms=<%=request.getParameter("terms")%>" target="_self">首页</a>				    
<a href="${pageContext.request.contextPath}/report/reportStudentTime?currpage=${pageModel.previousPage}&academyNumber=${academyNumber}&terms=<%=request.getParameter("terms")%>" target="_self"><fmt:message key="previouspage.title"/></a>
 第
<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
    <option value="${pageContext.request.contextPath}/report/reportStudentTime?currpage=${page}&academyNumber=${academyNumber}&terms=<%=request.getParameter("terms")%>">${page}</option>
    <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
        <c:if test="${j.index!=page}">
        <option value="${pageContext.request.contextPath}/report/reportStudentTime?currpage=${j.index}&academyNumber=${academyNumber}&terms=<%=request.getParameter("terms")%>">${j.index}</option>
        </c:if>
    </c:forEach>
</select>页
<a href="${pageContext.request.contextPath}/report/reportStudentTime?currpage=${pageModel.nextPage}&academyNumber=${academyNumber}&terms=<%=request.getParameter("terms")%>" target="_self"><fmt:message key="nextpage.title"/></a>
<a href="${pageContext.request.contextPath}/report/reportStudentTime?currpage=${pageModel.lastPage}&academyNumber=${academyNumber}&terms=<%=request.getParameter("terms")%>" target="_self"><fmt:message key="lastpage.title"/></a>

</div>    
<div class="pagination_desc" style="float: left">
   <fmt:message key="total"/><strong>${totalRecords}</strong> 
   <fmt:message key="record"/><strong>
   <fmt:message key="totalpage.title"/>:${pageModel.totalPage}&nbsp;</strong>

</div>

</div>
</div>
</div>
</div>
</div>

<!-- 调整排课 -->
<div id="doAdmin" class="easyui-window" title="调整排课" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="查看学生名单" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

<!-- 查看学生名单 -->
<div id="doSearchGroup" class="easyui-window" title="查看学生分组" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>
