<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta name="decorator" content="iframe"/>
	<title>绩效报表</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/jquery-1.8.2.min.js"></script>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		
</head>
<body>
<div class="title">
  <div id="title">报表统计--实验室利用率中实验室面积详细组成 </div>
  <a class="btn btn-new" onclick="window.history.go(-1);">返回</a>
</div> 

<div class="content-box">
<table class="tb">
<thead>
  <tr>
  	<th>序号</th>
    <th>实验室名称</th>
    <th>实验室编号</th>
    <th>实验室面积</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${labRooms}" var="labRoom"  varStatus="i">
    <tr>
      <td>${i.count}</td>
      <td>${labRoom.labRoomName}</td> 
      <td>${labRoom.labRoomNumber}</td> 
      <td>${labRoom.labRoomArea}</td> 
    </tr>
  </c:forEach>
</tbody>
</table>
</div>
</body>
</html>