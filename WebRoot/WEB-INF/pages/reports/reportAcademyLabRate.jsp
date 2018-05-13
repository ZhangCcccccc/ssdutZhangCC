<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta name="decorator" content="iframe"/>
	<title>绩效报表</title>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	
	<style>
	.tb td,th{
	text-indent:3.5em;
	}
</style>	
</head>
<body>
<div class="title">
  <div id="title">报表统计--实验室利用率详细组成 </div>
  <a class="btn btn-new" onclick="window.history.go(-1);">返回</a>
</div> 

<div class="content-box">
<table class="tb">
<thead>
  <tr>
  	<th>序号</th>
    <th>指标项</th>
    <th>数值</th>
  </tr>
</thead>
<tbody>
    <tr>
      <td>1</td>
      <td>实验室使用面积</td> 
      <td><a style="color:#0000ff;" href="${pageContext.request.contextPath}/report/reportLabArea?academyNumber=<%=request.getParameter("academyNumber") %>">${labArea}</a></td> 
    </tr>
    <tr>
      <td>2</td>
      <td>生均面积</td> 
      <td>${labAvgArea}</td> 
    </tr>
    <tr>
      <td>3</td>
      <td>实验室容量</td> 
      <td><a style="color:#0000ff;" href="${pageContext.request.contextPath}/report/reportLabCapacity?academyNumber=<%=request.getParameter("academyNumber") %>">${labRoomCapacity}</a></td> 
    </tr>
    <tr>
      <td>4</td>
      <td>实验室额定课时数</td> 
      <td>${ratedCourseTimeTerm}</td> 
    </tr>
    <tr>
      <td>5</td>
      <td>实验室人时数</td> 
      <td><a style="color:#0000ff;" href="${pageContext.request.contextPath}/report/reportStudentTime?currpage=1&academyNumber=<%=request.getParameter("academyNumber") %>&terms=<%=request.getParameter("terms") %>">${studentTimeSum}</a></td> 
    </tr>
    <tr>
      <td>6</td>
      <td>实验室利用率</td> 
      <td>${labRate}%</td> 
    </tr>
</tbody>
</table>
</div>
</body>
</html>