<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>

<html>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<!--直接排课  -->
<script type="text/javascript">

function listTimetableStudents(id,week){
var url="${pageContext.request.contextPath}/timetable/operatStudentAttendance?id="+id+"&week="+week;
var con = "<iframe scrolling='yes' id='message' frameborder='0'  src='"+url+"' style='width:100%;height:100%;''></iframe>";
$('#doSearchStudents').html(con);  
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#doSearchStudents').window({left:"0px", top:topPos+"px"});
$('#doSearchStudents').window('open');
}

</script>

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
<div class="title">考勤成绩管理列表
<a class="btn btn-new" onclick="window.history.go(-1)">返回</a> 
</div>

<table> 
<thead>
<tr>
   <th>选课组编号</th>
   <th>学院</th>
  <!--  <th>课程名称</th> -->
   <th>实验室</th>
   <th>上课教师</th>
   <th>日期</th>
   <th>周次</th>
   <th>星期</th>
   <th>节次</th>
   <th>排课方式</th>
   <th>操作</th>
   
</tr>
</thead>
<tbody>
<c:set var="ifRowspan" value="0"></c:set>
<c:set var="count" value="1"></c:set>


<c:forEach items="${list}" var="current"  varStatus="i">	
<c:set var="rowspan" value="0"></c:set>

     <tr>
     <!-- 选课组编号 -->
     <td >
	     <c:if test="${t.timetableStyle ne 5 && t.timetableStyle ne 6 }">
		  ${t.schoolCourseDetail.schoolCourse.courseCode}
		</c:if>   
		<c:if test="${t.timetableStyle eq 5 || t.timetableStyle eq 6 }">
		  ${t.timetableSelfCourse.courseCode}
		</c:if>
     </td>
     <!-- 学院 -->
     <td >
     	<c:if test="${t.timetableStyle ne 5 && t.timetableStyle ne 6 }">
		  ${t.schoolCourseDetail.schoolCourse.schoolAcademy.academyName}
		</c:if>   
		<c:if test="${t.timetableStyle eq 5 || t.timetableStyle eq 6 }">
		  ${t.timetableSelfCourse.schoolAcademy.academyName}
		</c:if>
     </td>
    
     <td>
     <c:forEach var="entry" items="${t.timetableLabRelateds}">  
     ${entry.labRoom.labRoomName}<br>  
     </c:forEach>
     </td>
     <!--上课教师  -->
     <td>
     <c:forEach var="entry" items="${t.timetableTeacherRelateds}">  
     <c:out value="${entry.user.cname}" />  
     </c:forEach> 
     </td>
     <!-- 日期 -->
     <td>
     ${t.schoolCourseDetail.schoolCourse.schoolTerm.termName}
     </td>
     <!-- 周次 -->
     <td>
     ${current}
     </td>
     <!-- 星期 -->
     <td>
     ${t.weekday}
     </td>
     <!-- 节次 -->
     <td>[${t.startClass}-${t.endClass}]</td>
     <!-- 排课方式 -->
     <td>
     	<c:if test="${t.timetableStyle==1}"> 教务直接排课</c:if>
        <c:if test="${t.timetableStyle==2}">教务调整排课</c:if>
        <c:if test="${t.timetableStyle==3}">二次不分组排课</c:if>
        <c:if test="${t.timetableStyle==4}">二次分组排课</c:if>
        <c:if test="${t.timetableStyle==5}">自主排课</c:if>
        <c:if test="${t.timetableStyle==6}">自主分组排课</c:if>
     </td>
     <!-- 操作 -->
      <td>
      <!-- 直接排课、调整排课、二次不分组排课 -->
      <c:if test="${t.timetableStyle!=4&&t.timetableStyle!=6&&t.timetableStyle!=5}">
			<a class="btn btn-common"  href='javascript:void(0)' onclick="listTimetableStudents(${t.id},${current})">
         	考勤/成绩：${t.schoolCourseDetail.schoolCourseStudents.size()}
         	</a>
       </c:if>
       <!-- 二次分组排课 -->
       <c:if test="${t.timetableStyle==4}">
         <c:forEach items="${t.timetableGroups}" var="varGroup">
         	<a class="btn btn-common"  href='javascript:void(0)' onclick="listTimetableStudents(${t.id},${current})">
         	考勤/成绩：${varGroup.timetableGroupStudentses.size()}
         	</a>
          </c:forEach>  
     	</c:if>
     	<!-- 自主排课 -->
 		<c:if test="${t.timetableStyle==5}">
 			<a class="btn btn-common"  href='javascript:void(0)' onclick="listTimetableStudents(${t.id},${current})">
	    	考勤/成绩：${t.timetableSelfCourse.timetableCourseStudents.size()}
	    	</a>
     	</c:if>
     	<!-- 自主分组排课 -->
     	<c:if test="${t.timetableStyle==6}">
         <c:forEach items="${t.timetableGroups}" var="varGroup">
         	<a class="btn btn-common"  href='javascript:void(0)' onclick="listTimetableStudents(${t.id},${current})">
	    	考勤/成绩：${varGroup.timetableGroupStudentses.size()}
	    	</a>
         </c:forEach>         
     	</c:if>
     </td>
     
     </tr>
    
     

</c:forEach> 




</tbody>
<!-- 分页导航 -->
		
</table>
</div>
</div>
</div>
</div>
</div>
</div>

<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="查看学生名单" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
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
    };
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
</script>
<!-- 下拉框的js -->
</body>

</html>
