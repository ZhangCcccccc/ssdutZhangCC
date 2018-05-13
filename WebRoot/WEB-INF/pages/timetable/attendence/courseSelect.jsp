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
                            ${course.schoolCourseInfo.courseNumber}
                            </td>
                         </tr>
                         <tr>
                             <th align="left"style="width:10%;"><fmt:message key="course"/></th>
                            <td style="width:23%;">
                            ${course.courseName}
                            </td>
                        </tr>
                        <tr>
                        <th align="left"style="width:10%;"><fmt:message key="teacher"/></th>
                            <td style="width:23%;">
                            ${course.userByTeacher.cname}
                            </td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="lab.name"/></th>
                            <td style="width:23%;">
                            <c:forEach items="${courseDetail}" var="current">
                            	<c:forEach items="${current.courseAppointments}" var="curr">
                            	${curr.labAnnex.labName}
                            	</c:forEach>
                            </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;">实验室中心</th>
                            <td style="width:23%;">
                            <c:forEach items="${courseDetail}" var="current">
                            	<c:forEach items="${current.courseAppointments}" var="curr">
                            	${curr.labAnnex.labCenter.centerName}
                            	</c:forEach>
                            </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="academy"/></th>
                            <td style="width:23%;">
                            ${course.schoolAcademy.academyName}
                            </td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="team"/></th>
                            <td style="width:23%;">
                            ${course.schoolTerm.termName}
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
        <!-- 实验室软件信息开始 -->
        <div class="tab_container">
            <div id="labSoftwareInfo" class="module_content">
            <table class="tablesorter" cellspacing="0">
             <thead>
                   <tr>
                         <th><fmt:message key="week"/></th>
                         <th><fmt:message key="weeks"/></th>
                         <th><fmt:message key="day"/></th>
                         <th><fmt:message key="class"/></th>
                         <th><fmt:message key="operation"/></th>
                   </tr>
                    </thead>
                    <tbody>
                <c:forEach items="${courseArrange}" var="current"  varStatus="i">
                    <tr>
                       <td>第${current.arrangedWeek}周</td>
                       <td>星期${current.weekday}</td>
                       <td> <fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${current.createdDate.time}"/></td>
                       <td>第${current.arrangeClass}节</td>
                       <td><a href="${pageContext.request.contextPath}/courseDetail?courseArrangeId=${current.id}&courseId=${current.courseDetail.id}&id=${course.id}&currpage=${page}">考勤</a></td>
                    </tr>
                </c:forEach>
                   </tbody>
                 </table>
                 <c:choose><c:when test='${newFlag}'>
        <div class="pagination">
   <a href="${pageContext.request.contextPath}/courseSelect?currpage=${pageModel.firstPage}" target="_self"><fmt:message key="firstpage.title"/></a>				    
	<a href="${pageContext.request.contextPath}/courseSelect?currpage=${pageModel.previousPage}" target="_self"><fmt:message key="previouspage.title"/></a>
	 <%--<c:forEach var="currentpage" begin="1" end="${pageModel.totalPage}"><a href="${pageContext.request.contextPath}/attendenceList?currpage=<c:out value="${currentpage}"/>" target="_self"><c:out value="${currentpage}"/></a></c:forEach>
	 第--%><select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/courseSelect?currpage=${page}">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/courseSelect?currpage=${j.index}">${j.index}</option></c:if></c:forEach></select>页
	<a href="${pageContext.request.contextPath}/courseSelect?currpage=${pageModel.nextPage}" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/courseSelect?currpage=${pageModel.lastPage}" target="_self"><fmt:message key="lastpage.title"/></a>
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
            </div>
            
            </div>
            
            </div>
</div>
    
    </div>
</body>
</html>

