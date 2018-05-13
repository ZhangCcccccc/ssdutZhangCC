<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.systemcampus-resources"/>
<html>
<head>
<title>List <fmt:message key="systemcampus.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="systemcampus.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newSystemCampus"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="systemcampus.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="systemcampus.campusnumber.title"/></th>
					<th class="thead"><fmt:message key="systemcampus.campusname.title"/></th>
					<th class="thead"><fmt:message key="systemcampus.campusdefault.title"/></th>
					<th class="thead"><fmt:message key="systemcampus.createdat.title"/></th>
					<th class="thead"><fmt:message key="systemcampus.updatedat.title"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${systemcampuss}" var="current" varStatus="i">
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
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectSystemCampus?campusNumberKey=${current.campusNumber}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editSystemCampus?campusNumberKey=${current.campusNumber}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteSystemCampus?campusNumberKey=${current.campusNumber}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.campusNumber}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.campusName}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.campusDefault}
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