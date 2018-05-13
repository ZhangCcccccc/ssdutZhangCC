<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<!--判断是否选择了实验室或者选择了冲突的实验室-->
<script type="text/javascript">
function saveAppointment(){
  var checkboxs=$("input[type='checkbox']:checked");
   $("#reflect_explain").attr("value","");
   var index=0;
  //如果有选择的则看是否选择冲突的实验室了
  if(checkboxs.length>0){
  var reflectCheckboxs=$("table#reflect_table").find("input[type='checkbox']:checked");
   //如果reflectCheckboxs的大小大于0
   if(reflectCheckboxs.length>0){
    alert("实验室有冲突");
    $("#reflect_explain").val("有冲突");
   }
  index=1;
  }else{
   alert("请选择实验室！");
  }
  if(index==1){
   $("#form_appointment").attr("action","${pageContext.request.contextPath}/saveTimetable");
  }
}
</script>
<!--判断是否选择了实验室或者选择了冲突的实验室-->
</head>

<div>
<!--教师需求开始-->
<div style="float:left;width: 95%">
    <div class="sit_module width_3_quarter"style="float:left;width: 100%">
        <div class="sit_title">
            <h3 style="width: 40%"><fmt:message key="teacherneed.title"/></h3>
            <ul class="tabs1">
                <li><a onclick="window.history.go(-1)"><fmt:message key="navigation.back"/></a></li>
            </ul>
        </div>    
   <form:form name="form_edit" modelAttribute="teacherNeed">   
    <table border="1" style="width:100%;">
  <tr><th><fmt:message key="course.type.title"/></th><td>${course.courseType}</td>
<th><fmt:message key="course.term.title"/></th><td colspan="5"><form:hidden path="schoolTerm.id" value="${course.schoolTerm.id}"/>${course.schoolTerm.termName}</td>
   </tr>
   <tr>
   <th><fmt:message key="course.coursename.title"/></th><td>${course.courseName}</td>
   <th><fmt:message key="course.classesname.title"/></th><td>${course.classesName}</td>
   <th><fmt:message key="course.majorname.title"/></th><td>${course.schoolMajor.majorName}</td>
   <th><fmt:message key="course.student.number"/></th><td>${fn:length(detail.courseStudents)}</td>
   </tr>
   <tr>
   <th><fmt:message key="course.coursenumber.title"/></th><td>${course.courseNumber}</td>
   <th><fmt:message key="course.academyname.title"/></th><td>${course.schoolAcademy.academyName}</td>
   <th><fmt:message key="course.teacher.title"/></th><td>${course.userByTeacher.cname}</td>
   <th><fmt:message key="course.studenttype.title"/></th><td>${course.CStudentType.name}</td>
   </tr>
    <tr>
   <th><fmt:message key="real.course.arrange"/></th><td colspan="7"><c:if test="${detail.weekday==1}">星期一</c:if>
                           <c:if test="${detail.weekday==2}">星期二</c:if>
                           <c:if test="${detail.weekday==3}">星期三</c:if>
                           <c:if test="${detail.weekday==4}">星期四</c:if>
                           <c:if test="${detail.weekday==5}">星期五</c:if>
                           <c:if test="${detail.weekday==6}">星期六</c:if>
                           <c:if test="${detail.weekday==7}">星期天</c:if>[${detail.startWeek}-${detail.totalWeeks}] ${detail.classroomType}[${detail.startClass}-${detail.endClass}]</td>
   </tr>
    <tr>
   <th><fmt:message key="total.course.time"/></th><td><c:set var="totalHours" value="${0}"/><c:forEach items="${courseDetails}" var="current"  varStatus="i"><c:set var="totalHours" value="${totalHours + current.totalClassHour}"/></c:forEach>${totalHours}</td>
   <th><fmt:message key="plan.lab.time"/></th><td><c:set var="labHours" value="${0}"/><c:forEach items="${courseDetails}" var="current"  varStatus="i"><c:set var="totalHours" value="${labHours + current.experimentalClassHour}"/></c:forEach>${labHours}</td>
   <th><fmt:message key="lab.course.proportion"/></th><td>${teacherNeed.experimentalProportion}%</td>
   <th><fmt:message key="lab.project.number"/></th><td>${teacherNeed.experimentNumber}</td>
   </tr>
    <tr>
   <th><fmt:message key="lab.address.title"/></th><td colspan="7">${detail.classroomType}</td>
   </tr>
    <tr>
   <th><fmt:message key="lab.computer.time"/></th><td colspan="7">${teacherNeed.experimentalMachineTime}</td>
   </tr>
    <tr>
   <th><fmt:message key="course.labcondition.title"/></th><td colspan="7">${teacherNeed.belongOperateSystem}${teacherNeed.labContent}</td>
   </tr>
    <tr>
   <th><fmt:message key="lab.project.arrange"/></th><td colspan="7">${teacherNeed.projectArrange}</td>
   </tr>
 </table>
 </form:form>
    </div>
</div>

<!--教师需求结束-->
<form:form id="form_appointment" method="post" modelAttribute="courseAppointment">   
<!-- 添加课程的实验室 -->
<div style="float:left;width:95%">
<div class="sit_module width_3_quarter"style="float:left;width: 100%">
        <div class="sit_title">
            <h3 style="width: 40%"><fmt:message key="course.arrangelab.title"/></h3>
        </div>    
<div>
 <!-- 课程安排 -->   
<table border="1" style="width:100%;"> 
               <thead>
                    <tr>
                        <th><fmt:message key="course.coursename.title"/></th>
                        <th><fmt:message key="course.arrangeweek.title"/></th>
                        <th><fmt:message key="course.weekday.title"/></th>
                        <th><fmt:message key="course.allclass.title"/></th>
                        <sec:authorize ifAnyGranted="ROLE_ADMINISTRATOR,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER,ROLE_EXPERIMENTALTEACHING">
                        <th><fmt:message key="navigation.operate"/></th>
                        </sec:authorize>
                    </tr>
                </thead>
                <tbody>
                        <tr>
                            <td>${teacherNeed.course.courseName}</td>
                            <td>${detail.startWeek}-${detail.totalWeeks}</td>
                            <td><c:if test="${detail.weekday==1}">星期一</c:if>
                           <c:if test="${detail.weekday==2}">星期二</c:if>
                           <c:if test="${detail.weekday==3}">星期三</c:if>
                           <c:if test="${detail.weekday==4}">星期四</c:if>
                           <c:if test="${detail.weekday==5}">星期五</c:if>
                           <c:if test="${detail.weekday==6}">星期六</c:if>
                           <c:if test="${detail.weekday==7}">星期天</c:if></td>
                            <td>${detail.startClass}-${detail.endClass}节</td>
                            <sec:authorize ifAnyGranted="ROLE_ADMINISTRATOR,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER,ROLE_EXPERIMENTALTEACHING">
                            <td><a href="${pageContext.request.contextPath}/adjustTimetable?id=${detail.id}&weekday=${detail.weekday}">调整</a></td>
                            </sec:authorize>
</tr>
</tbody>
</table> 
</div>
</div>   
<div class="sit_module width_3_quarter"style="float:left;width: 100%">
        <div class="sit_title">
            <h3 style="width: 40%">请选择实验室</h3>
             <ul class="tabs1">
                <li><input type="submit" value="保存" class="alt_btn" onclick="saveAppointment()"> </li>
        </ul>
        </div> 
 <!-- 课程安排 -->   
 <table border="1" style="width:100%;"> 
 <thead>
 <tr>
 </tr>
 </thead>
 <tbody>
 <tr>
<c:forEach items="${noLabs}" var="current"  varStatus="i">	
                        <c:if test="${(i.count) % 5 == 0}">
                        <td><form:checkbox path="arrangeDetail" value="${current.id}"/></td>
                            <td>${current.labName}</td>
                            <td></td>
                        </td>
                        </tr>
                        <tr>
                        </c:if>
                        <c:if test="${(i.count) % 5 == 1}">
                        <td><form:checkbox path="arrangeDetail" value="${current.id}"/></td>
                            <td>${current.labName}</td>
                            <td>${current.labCapacity}</td>
                        </td>
                        </c:if>
                        <c:if test="${(i.count) % 5 == 2}">
                        <td><form:checkbox path="arrangeDetail" value="${current.id}"/></td>
                            <td>${current.labName}</td>
                            <td>${current.labCapacity}</td>
                        </td>
                        </c:if>
                        <c:if test="${(i.count) % 5 == 3}">
                        <td><form:checkbox path="arrangeDetail" value="${current.id}"/></td>
                            <td>${current.labName}</td>
                            <td>${current.labCapacity}</td>
                        </td>
                        </c:if>
                        <c:if test="${(i.count) % 5 == 4}">
                        <td><form:checkbox path="arrangeDetail" value="${current.id}"/></td>
                            <td>${current.labName}</td>
                            <td>${current.labCapacity}</td>
                        </td>
                        </c:if>
</c:forEach>
</tr>
 </tbody>
</table>    
 <!--课程安排-->

 <form:hidden path="courseDetail.id" value="${detail.id}"/>
 <form:hidden path="memo" id="reflect_explain"/>
 </div>
 </div>
 </div>
 </form:form>
<!-- 添加课程的实验室 --> 
 
</div>