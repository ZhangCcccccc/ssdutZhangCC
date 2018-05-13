<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>
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
					<td><fmt:message key="lab.labnumber.title"/></td>
					<td>${labAnnex.labNumber}</td>
				</tr>
				<tr>
					<td><fmt:message key="lab.labname.title"/></td>
					<td>${labAnnex.labName}</td>
				</tr>
				<tr>
					<td><fmt:message key="lms.labaddress.title"/></td>
					<td>${labAnnex.build.buildName}</td>
				</tr>
				<%--<tr>
					<td><fmt:message key="department.name"/></td>
					<td>${labAnnex.area}</td>
				</tr>
				--%><tr>
					<td><fmt:message key="create.time"/></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${labAnnex.createdAt.time}"/></td>
				</tr>
				<tr>
					<td><fmt:message key="update.time"/></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${labAnnex.updatedAt.time}"/></td>
				</tr>
            </table>
          </div>
            	<div class="moudle_footer">
        			<ul class="top_tittle" style="float:right;margin-right:40px;"><a onclick="window.history.go(-1)" href="javascript:void(0)">返回</a></ul>
    			</div>
</div>
</body>
</html>


