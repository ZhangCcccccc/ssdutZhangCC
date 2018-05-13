<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.labroom-resources"/>
<html>
<head>
<title>View <fmt:message key="labroom.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="labroom.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLabRoom"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.id.title"/>:
						</td>
						<td>
							${labroom.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomnumber.title"/>:
						</td>
						<td>
							${labroom.labRoomNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomname.title"/>:
						</td>
						<td>
							${labroom.labRoomName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomenname.title"/>:
						</td>
						<td>
							${labroom.labRoomEnName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroonabbreviation.title"/>:
						</td>
						<td>
							${labroom.labRoonAbbreviation}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomtype.title"/>:
						</td>
						<td>
							${labroom.labRoomType}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomaddress.title"/>:
						</td>
						<td>
							${labroom.labRoomAddress}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.systemroom.title"/>:
						</td>
						<td>
							${labroom.systemRoom}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomarea.title"/>:
						</td>
						<td>
							${labroom.labRoomArea}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomcapacity.title"/>:
						</td>
						<td>
							${labroom.labRoomCapacity}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroommanageragencies.title"/>:
						</td>
						<td>
							${labroom.labRoomManagerAgencies}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomsubject.title"/>:
						</td>
						<td>
							${labroom.labRoomSubject}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomactive.title"/>:
						</td>
						<td>
							${labroom.labRoomActive}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomreservation.title"/>:
						</td>
						<td>
							${labroom.labRoomReservation}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomaudit.title"/>:
						</td>
						<td>
							${labroom.labRoomAudit}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomintroduction.title"/>:
						</td>
						<td>
							${labroom.labRoomIntroduction}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomregulations.title"/>:
						</td>
						<td>
							${labroom.labRoomRegulations}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomprizeinformation.title"/>:
						</td>
						<td>
							${labroom.labRoomPrizeInformation}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.createruser.title"/>:
						</td>
						<td>
							${labroom.createrUser}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.isused.title"/>:
						</td>
						<td>
							${labroom.isUsed}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomtimecreate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${labroom.labRoomTimeCreate.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteLabRoom?idKey=${labroom.id}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
			<div class="clear">&nbsp;</div>
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>