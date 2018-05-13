<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<html>
<head>
<title>List <fmt:message key="user.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="user.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newUser"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="user.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
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
				<c:forEach items="${users}" var="current" varStatus="i">
					<c:choose>
						<c:when test="${(i.count) % 2 == 0}">
		    				<c:set var="rowclass" value="rowtwo"/>
						</c:when>
						<c:otherwise>
		    				<c:set var="rowclass" value="rowone"/>
						</c:otherwise>
					</c:choose>	
				<tr class="${rowclass}">
					<td nowrap="nowrap" class="tabletd">
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectUser?usernameKey=${current.username}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editUser?usernameKey=${current.username}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteUser?usernameKey=${current.username}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.username}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.cardno}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.cname}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.password}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.userSexy}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.userStatus}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.teacherNumber}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.majorNumber}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.userRole}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.classesNumber}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="both" value="${current.lastLogin.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.createdAt.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.updatedAt.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.telephone}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.email}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.enabled}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.majorDirection}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.enrollmentStatus}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.ifEnrollment}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.userType}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.attendanceTime}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.grade}
						&nbsp;
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>