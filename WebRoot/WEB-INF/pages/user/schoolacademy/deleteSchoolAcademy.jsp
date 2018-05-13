<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<html>
<head>
<title>View <fmt:message key="user.title"/> <fmt:message key="schoolacademy.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="navigation.view"/> <fmt:message key="schoolacademy.title"/></h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectUser?usernameKey=${user_username}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.academynumber.title"/>:
						</td>
						<td>
							${schoolacademy.academyNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.academyname.title"/>:
						</td>
						<td>
							${schoolacademy.academyName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.isvaild.title"/>:
						</td>
						<td>
							${schoolacademy.isVaild}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.academytype.title"/>:
						</td>
						<td>
							${schoolacademy.academyType}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.createdat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${schoolacademy.createdAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.updatedat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${schoolacademy.updatedAt.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteUserSchoolAcademy?user_username=${user_username}&related_schoolacademy_academyNumber=${schoolacademy.academyNumber}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
			<div class="clear">&nbsp;</div>
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>
