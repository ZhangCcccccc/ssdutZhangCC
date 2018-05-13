<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.labroom-resources"/>
<html>
<head>
<title>List <fmt:message key="labroom.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="labroom.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newLabRoom"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="labroom.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="labroom.id.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomnumber.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomname.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomenname.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroonabbreviation.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomtype.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomaddress.title"/></th>
					<th class="thead"><fmt:message key="labroom.systemroom.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomarea.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomcapacity.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroommanageragencies.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomsubject.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomactive.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomreservation.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomaudit.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomintroduction.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomregulations.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomprizeinformation.title"/></th>
					<th class="thead"><fmt:message key="labroom.createruser.title"/></th>
					<th class="thead"><fmt:message key="labroom.isused.title"/></th>
					<th class="thead"><fmt:message key="labroom.labroomtimecreate.title"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${labrooms}" var="current" varStatus="i">
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
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectLabRoom?idKey=${current.id}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editLabRoom?idKey=${current.id}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteLabRoom?idKey=${current.id}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.id}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomNumber}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomName}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomEnName}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoonAbbreviation}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomType}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomAddress}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.systemRoom}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomArea}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomCapacity}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomManagerAgencies}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomSubject}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomActive}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomReservation}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomAudit}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomIntroduction}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomRegulations}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.labRoomPrizeInformation}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.createrUser}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.isUsed}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="both" value="${current.labRoomTimeCreate.time}"/>
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