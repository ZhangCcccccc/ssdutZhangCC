<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.course-resources"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta name="decorator" content="iframe"/>
 <link href="${pageContext.request.contextPath}/css/newRoom.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="l_right">
    <div class="list_top">
        <ul class="top_tittle"><fmt:message key="student.name.list"/></ul> 
      <ul  class="new_bulid"> <li class="new_bulid_1"><a href="${pageContext.request.contextPath}/exportCourseStudent">导出</a></li></ul>  
            <ul class="new_bulid"> 
            <li class="new_bulid_1"><a href="javascript:void(0)" onclick="window.history.go(-1)" href="javascript:void(0)">返回</a></li>
            </ul>
    </div>
            <table class="tb"> 
                <thead>
                    <tr>
                    	<%--<th><fmt:message key="order.number"/></th>--%>
                        <th><fmt:message key="student.number"/></th>
                        <th><fmt:message key="student.name"/></th>
                        <th><fmt:message key="academy.name"/></th>
                        <th><fmt:message key="major.name"/></th>
                        <th><fmt:message key="class.name"/></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${courseStudent}" var="current"  varStatus="i">	
                        <tr>
                            <%-- <td>${current.userByStudentNumber.id}</td> --%>
                            <td>${current.userByStudentNumber.username}</td>
                            <td>${current.userByStudentNumber.cname}</td>
                            <td>${current.userByStudentNumber.schoolAcademy.academyName}</td>
                            <td>${current.userByStudentNumber.schoolMajor.majorName}</td>
                            <td>${current.userByStudentNumber.schoolClasses.className}</td>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
            <c:choose><c:when test='${newFlag}'>
        <div class="pagination">
   <a href="${pageContext.request.contextPath}/findCourseById?currpage=${pageModel.firstPage}&idkey=${idkey}" target="_self"><fmt:message key="firstpage.title"/></a>				    
	<a href="${pageContext.request.contextPath}/findCourseById?currpage=${pageModel.previousPage}&idkey=${idkey}" target="_self"><fmt:message key="previouspage.title"/></a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/findCourseById?currpage=${page}&idkey=${idkey}">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/findCourseById?currpage=${j.index}&idkey=${idkey}">${j.index}</option></c:if></c:forEach></select>页
	 <%--<c:forEach var="currentpage" begin="1" end="${pageModel.totalPage}"><a href="${pageContext.request.contextPath}/roomList?currpage=<c:out value="${currentpage}"/>" target="_self"><c:out value="${currentpage}"/></a></c:forEach>
	--%><a href="${pageContext.request.contextPath}/findCourseById?currpage=${pageModel.nextPage}&idkey=${idkey}" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/findCourseById?currpage=${pageModel.lastPage}&idkey=${idkey}" target="_self"><fmt:message key="lastpage.title"/></a>
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
</body>
</html>


