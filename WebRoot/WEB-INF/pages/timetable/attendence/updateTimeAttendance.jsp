<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.ccoursetype-resources"/>
<head>
<meta name="decorator" content="iframe"/>
</head>
<body>
	<div class="l_right">
	<div class="list_top">
        <ul class="top_tittle"><fmt:message key="update.attendance"/></ul>     
    </div>
    <form:form id="" action="${pageContext.request.contextPath}/saveTimeAttendance?courseDetailId=${courseDetailId}&week=${week}&weekday=${weekday}&" method="post" modelAttribute="timeAttendance">
    	<table class="new">
    	<tr>
		    	<td><fmt:message key="student"/><font color="red">*</font>:
		    	<input value="${user.cname}" readonly="readonly" disabled/></td>
		    	<td><fmt:message key="student.number"/><font color="red">*</font>:
		    	<input value="${user.username}" readonly="readonly" disabled/></td>
		    	<form:hidden path="userByUserNumber.id" value="${userId}"/>
		    	<%--<form:hidden path="id" value="${timeAttendanceId}"/>
		    	<form:hidden path="courseArrange.id" value="${courseArrangeId}"/>
		    	--%><%--<form:hidden path="weekday" value="${weekday}"/>
		    	--%><form:hidden path="id"/>
		    	<c:choose><c:when test="${timeAttendanceId==0}">
		    	<form:hidden path="courseArrange.id"  value="${arrangeId}"/></c:when><c:otherwise><form:hidden path="courseArrange.id"/></c:otherwise></c:choose>
		    	<c:choose><c:when test="${timeAttendanceId==0}">
		    	<form:hidden path="attendanceMachine"  value="0"/></c:when><c:otherwise><form:hidden path="attendanceMachine"/></c:otherwise></c:choose>
		    	<%--<form:hidden path="courseAttendance.id" value="${courseDetailId}"/>
		    	--%><%--<form:hidden path="week"/>
		    	<form:hidden path="weekday"/>
    	--%></tr>
        <tr>
               <td><fmt:message key="attendance"/>:
               <form:radiobuttons path="actualAttendance" items="${attendance}" required="true"/></td>
               <td><fmt:message key="memo"/>:
               <form:select path="memo" items="${memo}" style="width: 100px"/></td>
        </tr>
    	</table>
    	    <div class="moudle_footer">
        <div class="submit_link">
            <input type="submit" value="提交" class="alt_btn">
            <script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'save', event:'onclick'}));</script>
            <input type="submit" value="取消" onclick="window.history.go(-1)">
        </div>
    </div>
    </form:form>
</div>
</body>