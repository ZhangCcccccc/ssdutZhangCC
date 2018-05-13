<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.teacherchange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
</head>
<div  class="l_right">
<div class="list_top">
      <ul class="top_tittle">考勤时间段设置</ul>
    </div>
 <form:form name="form" action="${pageContext.request.contextPath}/saveAttendanceTime" method="POST" modelAttribute="attendanceTimePeriod">
            <table id="my_show" class="tb" cellspacing="0"> 
                    <tr>
                        <th>开课前考勤时间设置<font color="red">*</font>：</th>
                        <td><form:input id="end" path="beforeTime" required="true" value="10"/>(单位：分钟)</td>
                        <th>开课后考勤时间设置<font color="red">*</font>：</th>
                        <td><form:input id="end" path="afterTime" required="true" value="10"/>(单位：分钟)</td>
                    </tr>
            </table>
     <div class="moudle_footer">
        <div class="submit_link">
            <input type="submit" value="提交" class="alt_btn">
        </div>
    </div>
            </form:form>
</div>


