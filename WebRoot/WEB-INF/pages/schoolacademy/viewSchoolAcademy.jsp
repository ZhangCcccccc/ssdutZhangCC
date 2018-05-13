<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.schoolacademy-resources"/>
<html>
<head>
<title>View <fmt:message key="schoolacademy.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="schoolacademy.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexSchoolAcademy"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
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
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="labcenter.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newSchoolAcademyLabCenters?schoolacademy_academyNumber=${schoolacademy.academyNumber}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="labcenter.title"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead">&nbsp;</th>
						<th class="thead"><fmt:message key="labcenter.id.title"/></th>
						<th class="thead"><fmt:message key="labcenter.centernumber.title"/></th>
						<th class="thead"><fmt:message key="labcenter.centername.title"/></th>
						<th class="thead"><fmt:message key="labcenter.centeraddress.title"/></th>
						<th class="thead"><fmt:message key="labcenter.createdat.title"/></th>
						<th class="thead"><fmt:message key="labcenter.updatedat.title"/></th>
						<th class="thead"><fmt:message key="labcenter.enabled.title"/></th>
						<th class="thead"><fmt:message key="labcenter.buildingnumber.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${schoolacademy.labCenters}" var="current"  varStatus="i">	
						<c:choose>
							<c:when test="${(i.count) % 2 == 0}">
					    		<c:set var="rowclass" value="rowtwo"/>
							</c:when>
							<c:otherwise>
					    		<c:set var="rowclass" value="rowone"/>
							</c:otherwise>
						</c:choose>
					<tr class="${rowclass}">
						<td nowrap="nowrap">
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectSchoolAcademyLabCenters?schoolacademy_academyNumber=${schoolacademy.academyNumber}&labcenters_id=${current.id}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editSchoolAcademyLabCenters?schoolacademy_academyNumber=${schoolacademy.academyNumber}&labcenters_id=${current.id}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteSchoolAcademyLabCenters?schoolacademy_academyNumber=${schoolacademy.academyNumber}&related_labcenters_id=${current.id}&"><img src="images/icons/delete.gif" /></a>
						</td>
						<td>
							${current.id}
						&nbsp;
						</td>
						<td>
							${current.centerNumber}
						&nbsp;
						</td>
						<td>
							${current.centerName}
						&nbsp;
						</td>
						<td>
							${current.centerAddress}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${current.createdAt.time}"/>
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${current.updatedAt.time}"/>
						&nbsp;
						</td>
						<td>
							${current.enabled}
						&nbsp;
						</td>
						<td>
							${current.buildingNumber}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="user.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newSchoolAcademyUsers?schoolacademy_academyNumber=${schoolacademy.academyNumber}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="user.title"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead">&nbsp;</th>
						<th class="thead"><fmt:message key="user.username.title"/></th>
						<th class="thead"><fmt:message key="user.cardno.title"/></th>
						<th class="thead"><fmt:message key="user.cname.title"/></th>
						<th class="thead"><fmt:message key="user.password.title"/></th>
						<th class="thead"><fmt:message key="user.usersexy.title"/></th>
						<th class="thead"><fmt:message key="user.userstatus.title"/></th>
						<th class="thead"><fmt:message key="user.teachernumber.title"/></th>
						<th class="thead"><fmt:message key="user.majornumber.title"/></th>
						<th class="thead"><fmt:message key="user.userrole.title"/></th>
						<th class="thead"><fmt:message key="user.classesnumber.title"/></th>
						<th class="thead"><fmt:message key="user.lastlogin.title"/></th>
						<th class="thead"><fmt:message key="user.createdat.title"/></th>
						<th class="thead"><fmt:message key="user.updatedat.title"/></th>
						<th class="thead"><fmt:message key="user.telephone.title"/></th>
						<th class="thead"><fmt:message key="user.email.title"/></th>
						<th class="thead"><fmt:message key="user.enabled.title"/></th>
						<th class="thead"><fmt:message key="user.majordirection.title"/></th>
						<th class="thead"><fmt:message key="user.enrollmentstatus.title"/></th>
						<th class="thead"><fmt:message key="user.ifenrollment.title"/></th>
						<th class="thead"><fmt:message key="user.usertype.title"/></th>
						<th class="thead"><fmt:message key="user.attendancetime.title"/></th>
						<th class="thead"><fmt:message key="user.grade.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${schoolacademy.users}" var="current"  varStatus="i">	
						<c:choose>
							<c:when test="${(i.count) % 2 == 0}">
					    		<c:set var="rowclass" value="rowtwo"/>
							</c:when>
							<c:otherwise>
					    		<c:set var="rowclass" value="rowone"/>
							</c:otherwise>
						</c:choose>
					<tr class="${rowclass}">
						<td nowrap="nowrap">
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectSchoolAcademyUsers?schoolacademy_academyNumber=${schoolacademy.academyNumber}&users_username=${current.username}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editSchoolAcademyUsers?schoolacademy_academyNumber=${schoolacademy.academyNumber}&users_username=${current.username}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteSchoolAcademyUsers?schoolacademy_academyNumber=${schoolacademy.academyNumber}&related_users_username=${current.username}&"><img src="images/icons/delete.gif" /></a>
						</td>
						<td>
							${current.username}
						&nbsp;
						</td>
						<td>
							${current.cardno}
						&nbsp;
						</td>
						<td>
							${current.cname}
						&nbsp;
						</td>
						<td>
							${current.password}
						&nbsp;
						</td>
						<td>
							${current.userSexy}
						&nbsp;
						</td>
						<td>
							${current.userStatus}
						&nbsp;
						</td>
						<td>
							${current.teacherNumber}
						&nbsp;
						</td>
						<td>
							${current.majorNumber}
						&nbsp;
						</td>
						<td>
							${current.userRole}
						&nbsp;
						</td>
						<td>
							${current.classesNumber}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${current.lastLogin.time}"/>
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.createdAt.time}"/>
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.updatedAt.time}"/>
						&nbsp;
						</td>
						<td>
							${current.telephone}
						&nbsp;
						</td>
						<td>
							${current.email}
						&nbsp;
						</td>
						<td>
							${current.enabled}
						&nbsp;
						</td>
						<td>
							${current.majorDirection}
						&nbsp;
						</td>
						<td>
							${current.enrollmentStatus}
						&nbsp;
						</td>
						<td>
							${current.ifEnrollment}
						&nbsp;
						</td>
						<td>
							${current.userType}
						&nbsp;
						</td>
						<td>
							${current.attendanceTime}
						&nbsp;
						</td>
						<td>
							${current.grade}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="clear">&nbsp;</div>
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>