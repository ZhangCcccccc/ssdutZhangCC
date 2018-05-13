<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.ccoursetype-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
</head>

<body>
<div style="float:left;width: 100%">
    <div class="sit_module width_3_quarter"style="float:left;width: 30%">
        <div class="sit_title">
            <h3 style="width: 40%"><fmt:message key="course.info"/></h3>
            <ul class="tabs1">
                <li><a href="javascript:void(0)" onclick="window.history.go(-1)"><fmt:message key="back.course.list"/></a></li>
            </ul>
        </div>
   <div id="editLabInfo" class="module_content">
    <table border="0" style="width:100%;" cellpadding="10"  >
                        <tr>  
                            <th align="left"style="width:10%;"><fmt:message key="course.number"/></th>
                            <td style="width:23%;">
                            ${courseDetail.courseNumber}
                            </td>
                         </tr>
                         <tr>
                             <th align="left"style="width:10%;"><fmt:message key="course"/></th>
                            <td style="width:23%;">
                            ${courseDetail.courseName}
                            </td>
                        </tr>
                        <tr>
                        <th align="left"style="width:10%;"><fmt:message key="teacher"/></th>
                            <td style="width:23%;">
                            ${courseDetail.user.cname}
                            </td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="lab.name"/></th>
                            <td style="width:23%;">
                           <%-- <c:forEach items="${courseDetail}" var="current">
                            	<c:forEach items="${courseDetail.courseAppointments}" var="curr">
                            	${curr.labAnnex.labName}
                            	</c:forEach>--%>
                            </td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;">实验室中心</th>
                            <td style="width:23%;">
                            <%-- <c:forEach items="${courseDetail}" var="current">
                            	<c:forEach items="${courseDetail.courseAppointments}" var="curr">
                            	${curr.labAnnex.labCenter.centerName}
                            	</c:forEach>--%>
                            </td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="academy"/></th>
                            <td style="width:23%;">
                            ${courseDetail.schoolAcademy.academyName}
                            </td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="team"/></th>
                            <td style="width:23%;">
                            ${courseDetail.schoolTerm.termName}
                            </td>
                        </tr>
                    </table>
    </div>
    </div>
    <div style="float:right;width: 65%">
    <!--实验室软件主界面开始-->
    <div class="sit_module width_quarter"style="float:right;width: 100%">
        <div class="sit_title">
            <h3 class="tabs_involved"><fmt:message key="course.list"/></h3>
        </div> 
        <!--课程安排信息开始-->
        <div class="tab_container">
            <div id="labSoftwareInfo" class="module_content">
            <table class="tablesorter" cellspacing="0">
             <thead>
                   <tr>
                         <th><fmt:message key="week"/></th>
                         <th><fmt:message key="weeks"/></th>
                         <%--<th><fmt:message key="day"/></th>
                         --%><th><fmt:message key="class"/></th>
                         <th><fmt:message key="operation"/></th>
                   </tr>
                    </thead>
                    <tbody>
                    
                <c:forEach items="${courseDetails}" var="current"  varStatus="i">
                <%--${current.totalWeeks}  --%>
                    <tr>
                       <td>
                       	第${current.endClass}周
                       </td>
                       <td><c:if test="${current.weekday==1}">星期一</c:if>
                           <c:if test="${current.weekday==2}">星期二</c:if>
                           <c:if test="${current.weekday==3}">星期三</c:if>
                           <c:if test="${current.weekday==4}">星期四</c:if>
                           <c:if test="${current.weekday==5}">星期五</c:if>
                           <c:if test="${current.weekday==6}">星期六</c:if>
                           <c:if test="${current.weekday==7}">星期天</c:if></td>
                       <%--<td> 
                       <fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${course.createdDate.time}"/>
                       </td>--%>
                       <td>
                       	${current.courseNo}节</td>
                       <%-- <td><a href="${pageContext.request.contextPath}/courseStudentList?courseDetailId=${current.id}&courseNumber=${current.courseNumber}&teacherNumber=${course.userByTeacher.username}&termId=${course.schoolTerm.id}&week=${current.startWeek}&weekday=${current.weekday}&startClass=${current.startClass}&endClass=${current.endClass}&className=${className}&currpage=1">考勤</a></td>--%>
                   	   <td><a href="${pageContext.request.contextPath}/courseStudentAttence?courseDetailId=${courseDetail.id}&week=${current.endClass}&weekday=${current.weekday}&arrangeId=${current.startClass}&currpage=1">考勤</a></td>
                    </tr>
                </c:forEach>
                   </tbody>
                 </table>	
            </div>
            
            </div>
            
            </div>
</div>
    
    </div>
</body>
</html>

