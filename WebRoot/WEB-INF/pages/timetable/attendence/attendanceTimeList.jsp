<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.teacherchange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
</head>
<div  class="l_right">
<div class="list_top">
      <ul class="top_tittle">考勤时间段设置列表</ul>
      <c:choose>
          <c:when test="${size==0}"><ul  class="new_bulid"> <li class="new_bulid_1">
          <a href="${pageContext.request.contextPath}/newAttendanceTime">设置</a>
          </li></ul></c:when>
          <c:otherwise></c:otherwise></c:choose> 
    </div>
           <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                        <th>开课前考勤时间设置<font color="red">*</font></th>
                        <th>开课后考勤时间设置<font color="red">*</font></th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${attendanceTimes}" var="current"  varStatus="i">	
                        <tr>
                            <td>${current.beforeTime}</td>
                            <td>${current.afterTime}</td>
                            <td><a href="${pageContext.request.contextPath}/updateAttendanceTime?id=${current.id}&"><fmt:message key="navigation.edit"/></a></td>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
</div>


