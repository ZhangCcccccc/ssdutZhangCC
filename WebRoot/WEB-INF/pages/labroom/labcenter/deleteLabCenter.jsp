<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.labroom-resources"/>
<html>
<head>
<title>View <fmt:message key="labroom.title"/> <fmt:message key="labcenter.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="navigation.view"/> <fmt:message key="labcenter.title"/></h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectLabRoom?idKey=${labroom_id}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labcenter.id.title"/>:
						</td>
						<td>
							${labcenter.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labcenter.centernumber.title"/>:
						</td>
						<td>
							${labcenter.centerNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labcenter.centername.title"/>:
						</td>
						<td>
							${labcenter.centerName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labcenter.centeraddress.title"/>:
						</td>
						<td>
							${labcenter.centerAddress}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labcenter.createdat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${labcenter.createdAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labcenter.updatedat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${labcenter.updatedAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labcenter.enabled.title"/>:
						</td>
						<td>
							${labcenter.enabled}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labcenter.buildingnumber.title"/>:
						</td>
						<td>
							${labcenter.buildingNumber}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteLabRoomLabCenter?labroom_id=${labroom_id}&related_labcenter_id=${labcenter.id}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
			<div class="clear">&nbsp;</div>
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>
