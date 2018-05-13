<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.labroomdevicereparation-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>View <fmt:message key="labroomdevicereparation.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="labroomdevicereparation.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLabRoomDeviceReparation"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroomdevicereparation.id.title"/>:
						</td>
						<td>
							${labroomdevicereparation.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroomdevicereparation.amount.title"/>:
						</td>
						<td>
							${labroomdevicereparation.amount}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroomdevicereparation.price.title"/>:
						</td>
						<td>
							${labroomdevicereparation.price}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="cconsumables.title"/></h1>
					
						<c:if test='${labroomdevicereparation.CConsumables != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="cconsumables.id.title"/>:
						</td>
						<td>
							${labroomdevicereparation.CConsumables.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cconsumables.name.title"/>:
						</td>
						<td>
							${labroomdevicereparation.CConsumables.name}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/device/editLabRoomDeviceReparationCConsumables?labroomdevicereparation_id=${labroomdevicereparation.id}&cconsumables_id=${labroomdevicereparation.CConsumables.id}&"><span><img src="images/icons/edit.gif" /><fmt:message key="navigation.edit"/></span></a></div>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/confirmDeleteLabRoomDeviceReparationCConsumables?labroomdevicereparation_id=${labroomdevicereparation.id}&related_cconsumables_id=${labroomdevicereparation.CConsumables.id}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
						</c:if>
						<c:if test='${labroomdevicereparation.CConsumables == null}'>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newLabRoomDeviceReparationCConsumables?labroomdevicereparation_id=${labroomdevicereparation.id}&"><span><img src="images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="cconsumables.title"/></span></a></div>
						</c:if>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="labroom.title"/></h1>
					
						<c:if test='${labroomdevicereparation.labRoom != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.id.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomname.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomnumber.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomenname.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomEnName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroonabbreviation.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoonAbbreviation}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomaddress.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomAddress}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomarea.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomArea}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomcapacity.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomCapacity}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroommanageragencies.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomManagerAgencies}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomsubject.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomSubject}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labromphone.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRomPhone}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomintroduction.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomIntroduction}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomregulations.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomRegulations}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomprizeinformation.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomPrizeInformation}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomimage.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomImage}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="labroom.labroomvideo.title"/>:
						</td>
						<td>
							${labroomdevicereparation.labRoom.labRoomVideo}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/device/editLabRoomDeviceReparationLabRoom?labroomdevicereparation_id=${labroomdevicereparation.id}&labroom_id=${labroomdevicereparation.labRoom.id}&"><span><img src="images/icons/edit.gif" /><fmt:message key="navigation.edit"/></span></a></div>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/confirmDeleteLabRoomDeviceReparationLabRoom?labroomdevicereparation_id=${labroomdevicereparation.id}&related_labroom_id=${labroomdevicereparation.labRoom.id}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
						</c:if>
						<c:if test='${labroomdevicereparation.labRoom == null}'>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newLabRoomDeviceReparationLabRoom?labroomdevicereparation_id=${labroomdevicereparation.id}&"><span><img src="images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="labroom.title"/></span></a></div>
						</c:if>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="user.title"/></h1>
					
						<c:if test='${labroomdevicereparation.userByCreateUser != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="user.username.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.username}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.id.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.cardno.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.cardno}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.cname.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.cname}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.password.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.password}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.usersexy.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.userSexy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.userstatus.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.userStatus}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.teachernumber.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.teacherNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.userrole.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.userRole}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.lastlogin.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${labroomdevicereparation.userByCreateUser.lastLogin.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.createdat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${labroomdevicereparation.userByCreateUser.createdAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.updatedat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${labroomdevicereparation.userByCreateUser.updatedAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.telephone.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.telephone}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.email.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.email}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.enabled.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.enabled}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.enrollmentstatus.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.enrollmentStatus}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.ifenrollment.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.ifEnrollment}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.attendancetime.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.attendanceTime}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.grade.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByCreateUser.grade}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/device/editLabRoomDeviceReparationUserByCreateUser?labroomdevicereparation_id=${labroomdevicereparation.id}&userbycreateuser_username=${labroomdevicereparation.userByCreateUser.username}&"><span><img src="images/icons/edit.gif" /><fmt:message key="navigation.edit"/></span></a></div>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/confirmDeleteLabRoomDeviceReparationUserByCreateUser?labroomdevicereparation_id=${labroomdevicereparation.id}&related_userbycreateuser_username=${labroomdevicereparation.userByCreateUser.username}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
						</c:if>
						<c:if test='${labroomdevicereparation.userByCreateUser == null}'>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newLabRoomDeviceReparationUserByCreateUser?labroomdevicereparation_id=${labroomdevicereparation.id}&"><span><img src="images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="user.title"/></span></a></div>
						</c:if>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="user.title"/></h1>
					
						<c:if test='${labroomdevicereparation.userByReparationUser != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="user.username.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.username}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.id.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.cardno.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.cardno}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.cname.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.cname}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.password.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.password}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.usersexy.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.userSexy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.userstatus.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.userStatus}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.teachernumber.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.teacherNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.userrole.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.userRole}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.lastlogin.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${labroomdevicereparation.userByReparationUser.lastLogin.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.createdat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${labroomdevicereparation.userByReparationUser.createdAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.updatedat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${labroomdevicereparation.userByReparationUser.updatedAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.telephone.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.telephone}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.email.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.email}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.enabled.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.enabled}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.enrollmentstatus.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.enrollmentStatus}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.ifenrollment.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.ifEnrollment}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.attendancetime.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.attendanceTime}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.grade.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByReparationUser.grade}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/device/editLabRoomDeviceReparationUserByReparationUser?labroomdevicereparation_id=${labroomdevicereparation.id}&userbyreparationuser_username=${labroomdevicereparation.userByReparationUser.username}&"><span><img src="images/icons/edit.gif" /><fmt:message key="navigation.edit"/></span></a></div>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/confirmDeleteLabRoomDeviceReparationUserByReparationUser?labroomdevicereparation_id=${labroomdevicereparation.id}&related_userbyreparationuser_username=${labroomdevicereparation.userByReparationUser.username}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
						</c:if>
						<c:if test='${labroomdevicereparation.userByReparationUser == null}'>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newLabRoomDeviceReparationUserByReparationUser?labroomdevicereparation_id=${labroomdevicereparation.id}&"><span><img src="images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="user.title"/></span></a></div>
						</c:if>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="user.title"/></h1>
					
						<c:if test='${labroomdevicereparation.userByTeacher != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="user.username.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.username}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.id.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.cardno.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.cardno}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.cname.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.cname}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.password.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.password}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.usersexy.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.userSexy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.userstatus.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.userStatus}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.teachernumber.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.teacherNumber}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.userrole.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.userRole}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.lastlogin.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="both" value="${labroomdevicereparation.userByTeacher.lastLogin.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.createdat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${labroomdevicereparation.userByTeacher.createdAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.updatedat.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${labroomdevicereparation.userByTeacher.updatedAt.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.telephone.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.telephone}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.email.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.email}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.enabled.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.enabled}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.enrollmentstatus.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.enrollmentStatus}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.ifenrollment.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.ifEnrollment}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.attendancetime.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.attendanceTime}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="user.grade.title"/>:
						</td>
						<td>
							${labroomdevicereparation.userByTeacher.grade}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/device/editLabRoomDeviceReparationUserByTeacher?labroomdevicereparation_id=${labroomdevicereparation.id}&userbyteacher_username=${labroomdevicereparation.userByTeacher.username}&"><span><img src="images/icons/edit.gif" /><fmt:message key="navigation.edit"/></span></a></div>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/confirmDeleteLabRoomDeviceReparationUserByTeacher?labroomdevicereparation_id=${labroomdevicereparation.id}&related_userbyteacher_username=${labroomdevicereparation.userByTeacher.username}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
						</c:if>
						<c:if test='${labroomdevicereparation.userByTeacher == null}'>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newLabRoomDeviceReparationUserByTeacher?labroomdevicereparation_id=${labroomdevicereparation.id}&"><span><img src="images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="user.title"/></span></a></div>
						</c:if>
			<div class="clear">&nbsp;</div>
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>