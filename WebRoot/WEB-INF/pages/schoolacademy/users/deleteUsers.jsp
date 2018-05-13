<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.schoolacademy-resources"/>
<html>
<head>
<title>View <fmt:message key="schoolacademy.title"/> <fmt:message key="user.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="navigation.view"/> <fmt:message key="user.title"/></h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectSchoolAcademy?academyNumberKey=${schoolacademy_academyNumber}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.username.title"/>:
						</td>
						<td>
							${user.username}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.cardno.title"/>:
						</td>
						<td>
							${user.cardno}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.cname.title"/>:
						</td>
						<td>
							${user.cname}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.password.title"/>:
						</td>
						<td>
							${user.password}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.usersexy.title"/>:
						</td>
						<td>
							${user.userSexy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.userstatus.title"/>:
						</td>
						<td>
							${user.userStatus}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.teachernumber.title"/>:
						</td>
						<td>
							${user.teacherNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.majornumber.title"/>:
						</td>
						<td>
							${user.majorNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.userrole.title"/>:
						</td>
						<td>
							${user.userRole}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.classesnumber.title"/>:
						</td>
						<td>
							${user.classesNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.lastlogin.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${user.lastLogin.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.createdat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${user.createdAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.updatedat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${user.updatedAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.telephone.title"/>:
						</td>
						<td>
							${user.telephone}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.email.title"/>:
						</td>
						<td>
							${user.email}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.enabled.title"/>:
						</td>
						<td>
							${user.enabled}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.majordirection.title"/>:
						</td>
						<td>
							${user.majorDirection}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.enrollmentstatus.title"/>:
						</td>
						<td>
							${user.enrollmentStatus}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.ifenrollment.title"/>:
						</td>
						<td>
							${user.ifEnrollment}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.usertype.title"/>:
						</td>
						<td>
							${user.userType}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.attendancetime.title"/>:
						</td>
						<td>
							${user.attendanceTime}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="user.grade.title"/>:
						</td>
						<td>
							${user.grade}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteSchoolAcademyUsers?schoolacademy_academyNumber=${schoolacademy_academyNumber}&related_users_username=${user.username}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
			<div class="clear">&nbsp;</div>
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>
