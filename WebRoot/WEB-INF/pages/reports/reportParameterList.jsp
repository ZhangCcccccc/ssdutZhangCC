<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta name="decorator" content="iframe"/>
    <title>报表参数设置</title>
    
</head>
  
<body>
    <div class="title">
        <div id="title">报表统计--参数设置 </div>
        <a class="btn btn-new" href="${pageContext.request.contextPath}/report/reportMain">返回</a>
        <a class="btn btn-new" href="${pageContext.request.contextPath}/report/newReportParameter">新建</a>
    </div>
    
    <div class="content-box">
<table class="tb">
<thead>
  <tr>
  	<th>序号</th>
    <th>学院编号</th>
    <th>学院名称</th>
    <th>学期</th>
    <th>实验室生均面积</th>
    <th>学科系数</th>
    <th>设备年平均机时</th>
    <th>额定课时数</th>
    <th>操作</th>
  </tr>
</thead>
<tbody>

  <c:forEach items="${reportParameters}" var="curr" varStatus="i">
  <tr>
  	<td>${i.count}</td>
    <td>${curr.schoolAcademy.academyNumber}</td>
    <td>${curr.schoolAcademy.academyName}</td>
    <td>${curr.schoolTerm.termName}</td>
    <td>${curr.labAvgArea}</td>
    <td>${curr.subjectFactor}</td>
    <td>${curr.deviceAvgTime}</td>
    <td>${curr.ratedCourseTime}</td>
    <td>
      <a href="${pageContext.request.contextPath}/report/editReportParameter?id=${curr.id}">修改</a> 
      <a href="${pageContext.request.contextPath}/report/deleteReportParameter?id=${curr.id}"  onclick="return confirm('删除后不能恢复，确定删除？');">删除</a>
    </td>
  </tr>
  </c:forEach>
</tbody>
</table>
</div>
</body>
</html>
