<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<html>
<head>
<title>View <fmt:message key="user.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="user.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexUser"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
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
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="schoolacademy.title"/></h1>
					
						<c:if test='${user.schoolAcademy != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="schoolacademy.academynumber.title"/>:
						</td>
						<td>
							${user.schoolAcademy.academyNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="schoolacademy.academyname.title"/>:
						</td>
						<td>
							${user.schoolAcademy.academyName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="schoolacademy.isvaild.title"/>:
						</td>
						<td>
							${user.schoolAcademy.isVaild}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="schoolacademy.academytype.title"/>:
						</td>
						<td>
							${user.schoolAcademy.academyType}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="schoolacademy.createdat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${user.schoolAcademy.createdAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="schoolacademy.updatedat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${user.schoolAcademy.updatedAt.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/editUserSchoolAcademy?user_username=${user.username}&schoolacademy_academyNumber=${user.schoolAcademy.academyNumber}&"><span><img src="images/icons/edit.gif" /><fmt:message key="navigation.edit"/></span></a></div>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/confirmDeleteUserSchoolAcademy?user_username=${user.username}&related_schoolacademy_academyNumber=${user.schoolAcademy.academyNumber}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
						</c:if>
						<c:if test='${user.schoolAcademy == null}'>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newUserSchoolAcademy?user_username=${user.username}&"><span><img src="images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="schoolacademy.title"/></span></a></div>
						</c:if>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="labcenter.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newUserLabCenters?user_username=${user.username}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="labcenter.title"/></span></a></div>
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
					<c:forEach items="${user.labCenters}" var="current"  varStatus="i">	
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
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectUserLabCenters?user_username=${user.username}&labcenters_id=${current.id}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editUserLabCenters?user_username=${user.username}&labcenters_id=${current.id}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteUserLabCenters?user_username=${user.username}&related_labcenters_id=${current.id}&"><img src="images/icons/delete.gif" /></a>
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
			<h1><fmt:message key="authority.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newUserAuthorities?user_username=${user.username}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="authority.title"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead">&nbsp;</th>
						<th class="thead"><fmt:message key="authority.id.title"/></th>
						<th class="thead"><fmt:message key="authority.authorityname.title"/></th>
						<th class="thead"><fmt:message key="authority.cname.title"/></th>
						<th class="thead"><fmt:message key="authority.type.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${user.authorities}" var="current"  varStatus="i">	
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
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectUserAuthorities?user_username=${user.username}&authorities_id=${current.id}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editUserAuthorities?user_username=${user.username}&authorities_id=${current.id}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteUserAuthorities?user_username=${user.username}&related_authorities_id=${current.id}&"><img src="images/icons/delete.gif" /></a>
						</td>
						<td>
							${current.id}
						&nbsp;
						</td>
						<td>
							${current.authorityName}
						&nbsp;
						</td>
						<td>
							${current.cname}
						&nbsp;
						</td>
						<td>
							${current.type}
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