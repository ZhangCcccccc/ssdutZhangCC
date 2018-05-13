<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<html>
<head>
<title>Edit <fmt:message key="user.title"/> <fmt:message key="schoolacademy.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1><fmt:message key="navigation.edit"/> <fmt:message key="schoolacademy.title"/></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectUser?usernameKey=${user_username}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveUserSchoolAcademy" method="POST" modelAttribute="schoolacademy">
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.academynumber.title"/>:
						</td>
						<td>
							<c:choose>
								<c:when test='${newFlag}' >
							<form:input id="schoolacademy_academyNumber" path="academyNumber" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "schoolacademy_academyNumber",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="schoolacademy.academynumber.help"/>", required : true}})); </script>
								</c:when>
								<c:otherwise>
							${schoolacademy.academyNumber}
						&nbsp;
									<form:hidden id="schoolacademy_academyNumber" path="academyNumber"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.academyname.title"/>:
						</td>
						<td>
							<form:input id="schoolacademy_academyName" path="academyName" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "schoolacademy_academyName",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="schoolacademy.academyname.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.isvaild.title"/>:
						</td>
						<td>
							<form:checkbox id="schoolacademy_isVaild" path="isVaild" />
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "schoolacademy_isVaild",widgetType : "dijit.form.CheckBox",widgetAttrs : {}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.academytype.title"/>:
						</td>
						<td>
							<form:input id="schoolacademy_academyType" path="academyType" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "schoolacademy_academyType",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="schoolacademy.academytype.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.createdat.title"/>:
						</td>
						<td>
							<input id="schoolacademy_createdAt" name="createdAt" type="text" value="<fmt:formatDate value="${schoolacademy.createdAt.time}" pattern="MM/dd/yyyy h:mm a"/>" style="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "schoolacademy_createdAt",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="navigation.dateTime.title"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="schoolacademy.updatedat.title"/>:
						</td>
						<td>
							<input id="schoolacademy_updatedAt" name="updatedAt" type="text" value="<fmt:formatDate value="${schoolacademy.updatedAt.time}" pattern="MM/dd/yyyy h:mm a"/>" style="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "schoolacademy_updatedAt",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="navigation.dateTime.title"/>"}})); </script>
						</td>
					</tr>
				</tbody>
			</table>
			<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="<fmt:message key="navigation.save"/>"/></span>
			<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'save', event:'onclick'}));</script>
				<input type="hidden" name="user_username" value="${user_username}" >
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
		</form:form>
		<div class="clear">&nbsp;</div>
	</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>
