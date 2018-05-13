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
  <div id="title">报表统计--大型设备平均利用率详细组成 </div>
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
      <td>设备机时</td> 
      <td>${largeDeviceSumTime}</td> 
    </tr>
    <tr>
      <td>2</td>
      <td>大型仪器设备数</td> 
      <td><a style="color:#0000ff;" href="${pageContext.request.contextPath}/report/reportLargeDeviceAcademy?academyNumber=<%=request.getParameter("academyNumber") %>">${largeDeviceAmount}</a></td> 
    </tr>
    <tr>
      <td>3</td>
      <td>设备年平均机时</td> 
      <td>${largeDeviceAvgTimeTerm}</td> 
    </tr>
    <tr>
      <td>4</td>
      <td>设备利用率</td> 
      <td>${largeDeviceTimeRate}%</td> 
    </tr>
</tbody>
</table>
</div>
</body>
</html>