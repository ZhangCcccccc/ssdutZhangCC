<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.labroom-resources"/>
<html>
<head>
<title>Edit <fmt:message key="labroom.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1><fmt:message key="navigation.edit"/> <fmt:message key="labroom.title"/></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLabRoom"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveLabRoom" method="POST" modelAttribute="labroom">
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.id.title"/>:
						</td>
						<td>
							<c:choose>
								<c:when test='${newFlag}' >
							<form:input id="labroom_id" path="id" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_id",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.id.help"/>", constraints : {places:0}}})); </script>
								</c:when>
								<c:otherwise>
							${labroom.id}
						&nbsp;
									<form:hidden id="labroom_id" path="id"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomnumber.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomNumber" path="labRoomNumber" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomNumber",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroomnumber.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomname.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomName" path="labRoomName" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomName",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroomname.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomenname.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomEnName" path="labRoomEnName" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomEnName",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroomenname.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroonabbreviation.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoonAbbreviation" path="labRoonAbbreviation" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoonAbbreviation",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroonabbreviation.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomtype.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomType" path="labRoomType" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomType",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroomtype.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomaddress.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomAddress" path="labRoomAddress" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomAddress",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroomaddress.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.systemroom.title"/>:
						</td>
						<td>
							<form:input id="labroom_systemRoom" path="systemRoom" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_systemRoom",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.systemroom.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomarea.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomArea" path="labRoomArea" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomArea",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroomarea.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomcapacity.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomCapacity" path="labRoomCapacity" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomCapacity",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroomcapacity.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroommanageragencies.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomManagerAgencies" path="labRoomManagerAgencies" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomManagerAgencies",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroommanageragencies.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomsubject.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomSubject" path="labRoomSubject" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomSubject",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroomsubject.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomactive.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomActive" path="labRoomActive" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomActive",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroomactive.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomreservation.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomReservation" path="labRoomReservation" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomReservation",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroomreservation.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomaudit.title"/>:
						</td>
						<td>
							<form:input id="labroom_labRoomAudit" path="labRoomAudit" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomAudit",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.labroomaudit.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomintroduction.title"/>:
						</td>
						<td>
							<form:textarea id="labroom_labRoomIntroduction" path="labRoomIntroduction" cssStyle="width:300px; height:100px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomIntroduction",widgetType : "dijit.form.SimpleTextarea",widgetAttrs : {}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomregulations.title"/>:
						</td>
						<td>
							<form:textarea id="labroom_labRoomRegulations" path="labRoomRegulations" cssStyle="width:300px; height:100px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomRegulations",widgetType : "dijit.form.SimpleTextarea",widgetAttrs : {}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomprizeinformation.title"/>:
						</td>
						<td>
							<form:textarea id="labroom_labRoomPrizeInformation" path="labRoomPrizeInformation" cssStyle="width:300px; height:100px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomPrizeInformation",widgetType : "dijit.form.SimpleTextarea",widgetAttrs : {}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.createruser.title"/>:
						</td>
						<td>
							<form:input id="labroom_createrUser" path="createrUser" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_createrUser",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.createruser.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.isused.title"/>:
						</td>
						<td>
							<form:input id="labroom_isUsed" path="isUsed" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_isUsed",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="labroom.isused.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="labroom.labroomtimecreate.title"/>:
						</td>
						<td>
							<input id="labroom_labRoomTimeCreate" name="labRoomTimeCreate" type="text" value="<fmt:formatDate value="${labroom.labRoomTimeCreate.time}" pattern="MM/dd/yyyy h:mm a"/>" style="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "labroom_labRoomTimeCreate",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="navigation.dateTime.title"/>"}})); </script>
						</td>
					</tr>
				</tbody>
			</table>
			<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="<fmt:message key="navigation.save"/>"/></span>
			<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'save', event:'onclick'}));</script>
		</form:form>
		<div class="clear">&nbsp;</div>
	</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>