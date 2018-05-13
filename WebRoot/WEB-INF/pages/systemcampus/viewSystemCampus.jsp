<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.systemcampus-resources"/>
<html>
<head>
<title>View <fmt:message key="systemcampus.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="systemcampus.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexSystemCampus"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="systemcampus.campusnumber.title"/>:
						</td>
						<td>
							${systemcampus.campusNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="systemcampus.campusname.title"/>:
						</td>
						<td>
							${systemcampus.campusName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="systemcampus.campusdefault.title"/>:
						</td>
						<td>
							${systemcampus.campusDefault}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="systemcampus.createdat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${systemcampus.createdAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="systemcampus.updatedat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${systemcampus.updatedAt.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="labcenter.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newSystemCampusLabCenters?systemcampus_campusNumber=${systemcampus.campusNumber}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="labcenter.title"/></span></a></div>
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
					<c:forEach items="${systemcampus.labCenters}" var="current"  varStatus="i">	
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
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectSystemCampusLabCenters?systemcampus_campusNumber=${systemcampus.campusNumber}&labcenters_id=${current.id}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editSystemCampusLabCenters?systemcampus_campusNumber=${systemcampus.campusNumber}&labcenters_id=${current.id}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteSystemCampusLabCenters?systemcampus_campusNumber=${systemcampus.campusNumber}&related_labcenters_id=${current.id}&"><img src="images/icons/delete.gif" /></a>
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
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>