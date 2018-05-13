<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<!-- 	<script type="text/javascript">
  $(function(){
     $("#print").click(function(){
	$("#my_show").jqprint();
	});
  });
  
 </script> -->
</head>
<div  class="l_right">
    <div class="list_top">
        <ul class="top_tittle"><fmt:message key="center.course.list"/></ul>  
        <%-- <ul  class="new_bulid">
                <li class="new_bulid_1"><a href="${pageContext.request.contextPath}/exportCourseList"><fmt:message key="navigation.export"/></a></li></ul>
          <ul class="new_bulid">
                <li class="new_bulid_1"><a href="javascript:void(0)" id="print">打印</a></li>
            </ul>   --%> 
    </div>
            <table class="tb" id="my_show"> 
               <thead>
                    <tr>
                        <th><fmt:message key="course.coursename.title"/></th>
                        <th><fmt:message key="course.teacher.title"/></th>
                       <th><fmt:message key="course.arrangeweek.title"/></th>
                        <th><fmt:message key="course.weekday.title"/></th>
                        <th><fmt:message key="course.allclass.title"/></th>
                        <th><fmt:message key="coursearrange.lab.title"/></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${courseArranges}" var="current"  varStatus="i">	
                        <tr>
                            <td>${current.course.courseName}</td>
                            <td>${current.course.userByTeacher.cname}</td>
                           <td>第${current.arrangedWeek}周</td>    
                           <td><c:if test="${current.weekday==1}">星期一</c:if>
                           <c:if test="${current.weekday==2}">星期二</c:if>
                           <c:if test="${current.weekday==3}">星期三</c:if>
                           <c:if test="${current.weekday==4}">星期四</c:if>
                           <c:if test="${current.weekday==5}">星期五</c:if>
                           <c:if test="${current.weekday==6}">星期六</c:if>
                           <c:if test="${current.weekday==7}">星期天</c:if></td>
                           <td>${current.memo}节</td>
                           <td><c:forEach items="${current.courseAppointments}" var="appoint">${appoint.labName}</c:forEach></td>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
   
</div>


