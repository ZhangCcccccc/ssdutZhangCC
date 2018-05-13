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
</head>

<body>

<!-- 导航栏 -->
<div class="navigation">
<div id="navigation">
<ul>
<li><a href="">排课管理</a></li>
<li class="end"><a href="${pageContext.request.contextPath}/system/listUser?currpage=1">学生选课</a></li>
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

<div class="content-box">
<div class="title">开始选课
<a class="btn btn-common" href='${pageContext.request.contextPath}/timetable/reTimetable/listGroupTimetableStudentSelect'>返回</a></div>
<form:form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/reTimetable/doStudentSelectGroup"	>
<table class="tb" cellspacing="0" id="my_show"> 
<thead>
<tr>
    <th>序号</th>
    <th>选课状态</th>
    <th>课程编号</th>
    <th width="80px;">课程名称</th>
    <th>选课组编号</th>
    <th>批次</th>
    <th>组名</th>
    <th>
        <table>
        <tr><td>星期</td>
            <td width="40px;">节次</td>
            <td width="50px;">周次</td>
            <td width="80px;">实验项目</td>
            <td width="50px;">授课教师</td>
            <td width="50px;">选课地点</td>
        </tr>
        </table>
    
    </th>
    
    <th>选课情况</th>
    <th>选择分组</th>
</tr>
</thead>
<tbody>
<c:forEach items="${timetableGroups}" var="current"  varStatus="i">	
<tr>
   <td>${i.count}</td>
   <!--如何为第一行则加rowspan  -->
   <c:if test="${i.count==1}">
   <td rowspan=${timetableGroups.size()}>
       <c:if test="${current.timetableBatch.ifselect==1}">
                          学生选课                   
       </c:if>
       <c:if test="${current.timetableBatch.ifselect==0}">
                         自动选课
       </c:if>&nbsp;
   </td>
   </c:if>
   
   <c:if test="${i.count==1}">
   <td rowspan=${timetableGroups.size()}>
       ${current.timetableAppointments.iterator().next().schoolCourseInfo.courseNumber}
   </td>
   
   </c:if>
   
   <c:if test="${i.count==1}">
   <td rowspan=${timetableGroups.size()}>
       ${current.timetableAppointments.iterator().next().schoolCourseInfo.courseName}
   </td>
   </c:if>
   
   <c:if test="${i.count==1}">
   <td  rowspan=${timetableGroups.size()}>${current.timetableBatch.courseCode}</td>
   </c:if>

   <c:if test="${i.count==1}">
       <td rowspan=${timetableGroups.size()}>${current.timetableBatch.batchName}</td>
   </c:if>
     <!--分组情况  -->
   <td>${current.groupName}</td>
    <!--排课情况  -->
   <td>
      <c:forEach items="${current.timetableAppointments}" var="var">
                        
      <table>
     <c:if test="${var.timetableAppointmentSameNumbers.size()==0}">
     <tr>    <td > 星期${var.weekday}
             </td>
             <td width="40px;">
             <c:if test="${var.startClass==var.endClass}">
             ${var.startClass }
             </c:if>
             <c:if test="${var.startClass!=var.endClass}">
             ${var.startClass }-${var.endClass }
             </c:if>
             </td>
             <td width="50px;">
             <c:if test="${var.startWeek==var.endWeek}">
             ${var.startWeek }
             </c:if>
             <c:if test="${var.startWeek!=var.endWeek}">
             ${var.startWeek }-${var.endWeek }
             </c:if>
             </td>
              <!--选择实验项目 -->
	     <td width="80px;">
	       <c:forEach items="${var.timetableItemRelateds}" var="var1"  varStatus="j">
	           <c:if test="${not empty var1.operationItem||var1.operationItem.id!=0}">
	              <c:out value="${var1.operationItem.lpName}" /><br>
	           </c:if>
	           <c:if test="${empty var1.operationItem&&var1.operationItem.id==0}">
	              ${var.schoolCourseInfo.courseName}(课程名称)<br>
	           </c:if>
	        </c:forEach>
	     </td>
	     <!--选择授课教师 -->
	       <td width="50px;">
	       <c:forEach items="${var.timetableTeacherRelateds}" var="var1"  varStatus="j">
	            <c:out value="${var1.user.cname}" />  <br>
	       </c:forEach>
	       </td> 
	       <td>
		       <c:forEach items="${var.timetableLabRelateds}" var="var2"  varStatus="k">
		             ${var2.labRoom.labRoomNumber}<br>
		       </c:forEach> 
		   </td>      
      </tr>
      </c:if>
     <c:if test="${var.timetableAppointmentSameNumbers.size()>0}">
         <c:set var="sameStart" value="-"></c:set>
         <c:forEach var="entry" items="${var.timetableAppointmentSameNumbers}"   varStatus="p"> 
         <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
         <c:if test="${fn:indexOf(sameStart,v_param)<0}">
         <tr>
         <td > 星期${var.weekday}
         </td>
         <td width="40px;">
             <c:if test="${entry.startClass==entry.endClass}">
             ${entry.startClass }
             </c:if>
             <c:if test="${entry.startClass!=entry.endClass}">
             ${entry.startClass }-${entry.endClass }
             </c:if>
         </td>
         <td width="50px;">
         <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
         <c:forEach var="entry1" items="${var.timetableAppointmentSameNumbers}"  varStatus="q">  
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
         <!--选择实验项目 -->
	     <td width="80px;">
	       <c:forEach items="${var.timetableItemRelateds}" var="var1"  varStatus="j">
	           <c:if test="${not empty var1.operationItem||var1.operationItem.id!=0}">
	              <c:out value="${var1.operationItem.lpName}" /><br>
	           </c:if>
	           <c:if test="${empty var1.operationItem&&var1.operationItem.id==0}">
	              ${var.schoolCourseInfo.courseName}(课程名称)<br>
	           </c:if>
	        </c:forEach>
	     </td>
	       <!--选择授课教师 -->
	       <td width="50px;">
	       <c:forEach items="${var.timetableTeacherRelateds}" var="var1"  varStatus="j">
	            <c:out value="${var1.user.cname}" />  <br>
	       </c:forEach>
	       </td>
	       <!--选课地点 -->
		   <td>
		       <c:forEach items="${var.timetableLabRelateds}" var="var2"  varStatus="k">
		             ${var2.labRoom.labRoomNumber}<br>
		       </c:forEach> 
		   </td>    
         </tr>
         </c:if>
         </c:forEach>
     </c:if>
     </table>
       </c:forEach>
   </td>
        
   
   <!--选课情况 -->
   <td>${current.timetableGroupStudentses.size()}/${current.numbers}</td>
   <td>
   <c:if test="${current.numbers>current.timetableGroupStudentses.size()&&current.timetableAppointments.size()>0}">
   <c:if test="${current.timetableBatch.ifselect==1 }">
   <input type="radio" name="group" value="${current.id}" checked="checked" />
   </c:if>
   </c:if>
   </td>
</tr>
</c:forEach>
<tr>
<td colspan=12>
<hr>
<br>
<c:if test="${ifselect==1 }">
<font color=red>当前批次的选课已完成</font>
<a class="btn btn-common" href='${pageContext.request.contextPath}/timetable/reTimetable/listGroupTimetableStudentSelect'>返回</a>
</c:if>
<c:if test="${timetableGroups.get(0).numbers!=current.timetableGroupStudentses.size()}">
<c:if test="${timetableGroups.get(0).timetableBatch.ifselect==1 }">
<c:if test="${ifselect==0 }">
<input type="submit" value="提交" onclick="$('#doSelectCourse').window('close', true)" > 
</c:if>
</c:if>
</c:if>
</td>
</tr>
</tbody>
</table>  
</form:form>  
</div>
</div>
</div>
</div>
</div>
</div>
<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="查看学生名单" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>
<!-- 进行学生选课 -->
<div id="doSelectCourse" class="easyui-window" title="进行学生选课" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

</body>
</html>

