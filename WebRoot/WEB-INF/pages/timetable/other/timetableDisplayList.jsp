<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<head>
<script type="text/javascript">
/* $(function(){
<c:forEach items="${weekdays}" var="current" varStatus="i">
var index=${current.key};
var week=${week};

var str="all_"+index;
 $('#'+str).each(function() {
    $(this).html("<p>Nice to meet you</p>");
});
</c:forEach>
}); */
</script>
</head>
<div>
<table id=spdata >
<thead><tr><th></th><th></th><th colspan="11">${week}周</th></tr><tr><th></th><th width="50px"></th><c:forEach items="${classMap}" var="curr" varStatus="i"><th>${curr.value}</th></c:forEach></tr></thead>
<c:forEach items="${weekdays}" var="current" varStatus="i">  <tr><td id="${current.key}">
           ${current.value}
            </td><td id="all_${current.key}" colspan="12"><table><c:forEach items="${labs}" var="lab" varStatus="j">
            <tr> <td width="45px">${lab.labName}</td><c:forEach items="${classMap}" var="curr" varStatus="k"><td><c:forEach items="${lab.courseAppointments}" var="appoint" varStatus="h"><c:if test="${appoint.courseArrange.arrangedWeek==week&&appoint.courseArrange.weekday==current.key&&appoint.courseArrange.arrangeClass==curr.key}">序号：${appoint.courseArrange.courseDetail.courseNumber}人数：${fn:length(appoint.courseArrange.courseDetail.courseStudents)}教师：${appoint.courseArrange.userByTeacherNumber.cname}</c:if></c:forEach></td></c:forEach></tr>
            </c:forEach></table></td></tr></c:forEach>
</table>
</div>
