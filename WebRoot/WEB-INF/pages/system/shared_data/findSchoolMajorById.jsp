<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.major-resources"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta name="decorator" content="iframe"/>
 <link href="${pageContext.request.contextPath}/css/newRoom.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div  class="l_right">
    <div class="list_top">
        <ul class="top_tittle"><fmt:message key="look.up"/></ul>
    </div>
    <div id="userInfo">
              <table class="tb" cellspacing="0">
				<tr>
					<td><fmt:message key="major.number.title"/></td>
					<td>${schoolMajor.majorNumber}</td>
				</tr>
				<tr>
					<td><fmt:message key="major.name.title"/></td>
					<td>${schoolMajor.majorName}</td>
				</tr>
				<tr>
					<td><fmt:message key="academy.name"/></td>
					<td>${schoolMajor.schoolAcademy.academyName}</td>
				</tr>
				<tr>
					<td><fmt:message key="create.time"/></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${schoolMajor.createdAt.time}"/></td>
				</tr>
				<tr>
					<td><fmt:message key="update.time"/></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${schoolMajor.updatedAt.time}"/></td>
				</tr>
				<tr>
					<td><fmt:message key="create.user"/></td>
					<td>${schoolMajor.userByCreatedBy.cname}</td>
				</tr>
				<tr>
					<td><fmt:message key="update.user"/></td>
					<td>${schoolMajor.userByUpdatedBy.cname}</td>
				</tr>
            </table>
          </div>
            	<div class="moudle_footer">
        			<ul class="top_tittle" style="float:right;margin-right:40px;"><a onclick="window.history.go(-1)" href="javascript:void(0)">返回</a></ul>
    			</div>
    				
    				
</div>
</body>
</html>


