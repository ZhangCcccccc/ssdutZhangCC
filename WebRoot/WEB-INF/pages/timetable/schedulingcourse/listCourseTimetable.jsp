<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
</head>

<div class="iStyle_RightInner">
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li class="end">查看排课情况</li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">查看排课情况</div>

<table  > 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>课程编号</th>
   <th>课程名称</th>
   <th>选课组编号</th>
   <th>实验项目</th>
   <th width="140px;" colspan=2>
       <table>
       <tr>
          <td width="50px;">周次</td>
          <th width="50px;">星期</th>
          <td width="20px;">节次</td>
       </tr>
       </table>
   </th>
   <th>授课教师</th>
   <c:if test="${selected_labCenter eq 12 }">
   <th>是否允许<br>教学外设备对外开放</th>
   <th>实验室（设备）</th>
   </c:if>
   <c:if test="${selected_labCenter ne 12 }">
   <th>实验室</th>
   </c:if>
</tr>
</thead>
<tbody>
<c:forEach items="${timetableAppointmentMap}" var="current"  varStatus="i">	
<tr>
    <c:if test="${timetableAppointmentMap.size()>1&&i.count==1 }">
        <td rowspan="${timetableAppointmentMap.size()}">${current.schoolCourseDetail.schoolCourse.schoolCourseInfo.courseNumber}</td>
    </c:if>
    <c:if test="${timetableAppointmentMap.size()==1 }">
        <td >
        ${current.schoolCourseInfo.courseNumber}
        </td>
    </c:if>
    
    <c:if test="${timetableAppointmentMap.size()>1&&i.count==1 }">
         <td rowspan="${timetableAppointmentMap.size()}">
             ${current.schoolCourseDetail.schoolCourse.courseName} 
         </td>
     </c:if>
     <c:if test="${timetableAppointmentMap.size()==1 }">
         <td >
             ${current.schoolCourseInfo.courseName}
         </td>
     </c:if>
   
    <c:if test="${timetableAppointmentMap.size()>1&&i.count==1 }">
        <td rowspan="${timetableAppointmentMap.size()}">${current.schoolCourseDetail.schoolCourse.courseCode}</td>
    </c:if>
    <c:if test="${timetableAppointmentMap.size()==1 }">
        <td >${current.schoolCourseDetail.schoolCourse.courseCode}</td>
    </c:if>
    
    <td>
       <c:forEach var="timetableItem" items="${current.timetableItemRelateds}">  
             <c:if test="${empty timetableItem.operationItem||timetableItem.operationItem.id==0}">
                 ${current.schoolCourseInfo.courseName}(课程名称)
             </c:if>
             <c:if test="${not empty timetableItem.operationItem&&timetableItem.operationItem.id!=0}">
                 <c:out value="${timetableItem.operationItem.lpName}" />&nbsp;
             </c:if>
         </c:forEach>
         <c:if test="${current.timetableItemRelateds.size()==0}">
             ${current.schoolCourseInfo.courseName}(课程名称)
         </c:if>
    </td>
    <td colspan=2>
     <table>
     <c:if test="${current.timetableAppointmentSameNumbers.size()==0}">
     <tr>    
             <td width="50px;">
             <c:if test="${current.startWeek==current.endWeek}">
             ${current.startWeek }
             </c:if>
             <c:if test="${current.startWeek!=current.endWeek}">
             ${current.startWeek }-${current.endWeek }
             </c:if>
             </td>
             <td width="50px;">${current.weekday}</td>
             <td width="20px;">
             <c:if test="${current.startClass==current.endClass}">
             ${current.startClass }
             </c:if>
             <c:if test="${current.startClass!=current.endClass}">
             ${current.startClass }-${current.endClass }
             </c:if>
             </td>
             
      </tr>
      </c:if>
     <c:if test="${current.timetableAppointmentSameNumbers.size()>0}">
         
         <c:set var="sameStart" value="-"></c:set>
         <c:forEach var="entry" items="${current.timetableAppointmentSameNumbers}"   varStatus="p"> 
         <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
         <c:if test="${fn:indexOf(sameStart,v_param)<0}">
         <tr>
         <td width="50px;">
         <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
         <c:forEach var="entry1" items="${current.timetableAppointmentSameNumbers}"  varStatus="q">  
             <c:if test="${entry.startClass==entry1.startClass}">
             <%-- <td>
             [${entry1.startClass }-${entry1.endClass }]
             </td> --%>
             <c:if test="${entry1.startWeek==entry1.endWeek}">
              ${entry1.startWeek }
             </c:if>
             <c:if test="${entry1.startWeek!=entry1.endWeek}">
              ${entry1.startWeek }-${entry1.endWeek }
             </c:if>
             
             </c:if>
         </c:forEach>
         </td>
         <td width="50px;">${current.weekday}</td>
         <td width="20px;">
             <c:if test="${entry.startClass==entry.endClass}">
             ${entry.startClass }
             </c:if>
             <c:if test="${entry.startClass!=entry.endClass}">
             ${entry.startClass }-${entry.endClass }
             </c:if>
         </td>
         
         </tr>
         </c:if>
         </c:forEach>
          
     </c:if>
     </table>
     </td>

    <td>
    <!--上课教师  -->
     <c:forEach var="entry" items="${current.timetableTeacherRelateds}">  
    <c:out value="${entry.user.cname}" />  
    </c:forEach> 
    </td>
    
    <c:if test="${selected_labCenter eq 12 }"><!-- 纺织中心 -->
	     <!-- 是否允许教学外设备对外开放 -->
	     <td>
     		<c:if test="${current.deviceOrLab eq 1}">是</c:if>
     		<c:if test="${current.deviceOrLab eq 2}">否</c:if>
	     </td>
	     
	  	<!--实验室(设备)  -->
	     <td>
		     <c:forEach var="entry" items="${current.timetableLabRelateds}">  
		     <label title="<c:out value="${entry.labRoom.labRoomName}" />"><c:out value="${entry.labRoom.labRoomNumber}" /></label>
		     <c:if test="${selected_labCenter eq 12 }"><!-- 纺织中心 -->
		        <a target="_blank" href="${pageContext.request.contextPath}/timetable/listTeachingLabRoomDevice?timetableId=${current.id}&labRoomId=${entry.labRoom.id}">查看教学用设备</a>
		     </c:if>    
		     <br>  
		     </c:forEach>
	     </td>
	</c:if>
	
	<c:if test="${selected_labCenter ne 12 }"><!-- 非纺织中心 -->
		<td>
			<c:forEach var="entry" items="${current.timetableLabRelateds}">  
				<c:out value="${entry.labRoom.labRoomName}" />  
			</c:forEach>
		</td>	
	</c:if>
    
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
<br>

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

