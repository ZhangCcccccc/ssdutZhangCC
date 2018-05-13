<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.authority-resources"/>
<html>
<head>
<title>View <fmt:message key="authority.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="authority.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexAuthority"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="authority.id.title"/>:
						</td>
						<td>
							${authority.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="authority.authorityname.title"/>:
						</td>
						<td>
							${authority.authorityName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="authority.cname.title"/>:
						</td>
						<td>
							${authority.cname}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="authority.type.title"/>:
						</td>
						<td>
							${authority.type}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="user.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newAuthorityUsers?authority_id=${authority.id}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="user.title"/></span></a></div>
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
					<c:forEach items="${authority.users}" var="current"  varStatus="i">	
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
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectAuthorityUsers?authority_id=${authority.id}&users_username=${current.username}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editAuthorityUsers?authority_id=${authority.id}&users_username=${current.username}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteAuthorityUsers?authority_id=${authority.id}&related_users_username=${current.username}&"><img src="images/icons/delete.gif" /></a>
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