<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.schoolacademy-resources"/>
<html>
<head>
<title>List <fmt:message key="schoolacademy.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="schoolacademy.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newSchoolAcademy"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="schoolacademy.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="schoolacademy.academynumber.title"/></th>
					<th class="thead"><fmt:message key="schoolacademy.academyname.title"/></th>
					<th class="thead"><fmt:message key="schoolacademy.isvaild.title"/></th>
					<th class="thead"><fmt:message key="schoolacademy.academytype.title"/></th>
					<th class="thead"><fmt:message key="schoolacademy.createdat.title"/></th>
					<th class="thead"><fmt:message key="schoolacademy.updatedat.title"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${schoolacademys}" var="current" varStatus="i">
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
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectSchoolAcademy?academyNumberKey=${current.academyNumber}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editSchoolAcademy?academyNumberKey=${current.academyNumber}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteSchoolAcademy?academyNumberKey=${current.academyNumber}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.academyNumber}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.academyName}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.isVaild}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.academyType}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="both" value="${current.createdAt.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="both" value="${current.updatedAt.time}"/>
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