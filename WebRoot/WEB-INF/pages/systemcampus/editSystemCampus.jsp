<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.systemcampus-resources"/>
<html>
<head>
<title>Edit <fmt:message key="systemcampus.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1><fmt:message key="navigation.edit"/> <fmt:message key="systemcampus.title"/></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexSystemCampus"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveSystemCampus" method="POST" modelAttribute="systemcampus">
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="systemcampus.campusnumber.title"/>:
						</td>
						<td>
							<c:choose>
								<c:when test='${newFlag}' >
							<form:input id="systemcampus_campusNumber" path="campusNumber" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "systemcampus_campusNumber",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="systemcampus.campusnumber.help"/>", required : true}})); </script>
								</c:when>
								<c:otherwise>
							${systemcampus.campusNumber}
						&nbsp;
									<form:hidden id="systemcampus_campusNumber" path="campusNumber"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="systemcampus.campusname.title"/>:
						</td>
						<td>
							<form:input id="systemcampus_campusName" path="campusName" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "systemcampus_campusName",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="systemcampus.campusname.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="systemcampus.campusdefault.title"/>:
						</td>
						<td>
							<form:checkbox id="systemcampus_campusDefault" path="campusDefault" />
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "systemcampus_campusDefault",widgetType : "dijit.form.CheckBox",widgetAttrs : {}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="systemcampus.createdat.title"/>:
						</td>
						<td>
							<input id="systemcampus_createdAt" name="createdAt" type="text" value="<fmt:formatDate value="${systemcampus.createdAt.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="systemcampus.updatedat.title"/>:
						</td>
						<td>
							<input id="systemcampus_updatedAt" name="updatedAt" type="text" value="<fmt:formatDate value="${systemcampus.updatedAt.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
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