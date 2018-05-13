<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>


<html>
<head>
<meta name="decorator" content="iframe"/>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">

</head>
<body>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
			<div class="title">
				<div id="title">设备信息</div>
				<a class="btn btn-new" onclick="window.history.go(-1)">返回</a>
			</div>   	

    		<div class="content-box">   		
            <table  class="tb"  id="my_show"> 
                <tbody>

    <tr>
    <th>设备编号:</th>
    <td>${current.deviceNumber}</td></tr>
    <tr>
    <th>设备名称</th>
    <td>${current.deviceName}</td></tr>
    
    <tr>
    <th>设备类型：</th>
    <td>${current.devicePattern}</td></tr>
    
    <tr>
    <th>设备分类:</th>
    <td></td></tr>
    
    <tr>
    <th>设备价格:</th>
    <td>${current.devicePrice}</td></tr>
    
    <tr>
    <th>设备生产厂商:</th>
    <td>${current.manufacturer}</td></tr>
    
    <tr>
    <th>领用部门编号:</th>
    <td>${current.schoolAcademy.academyNumber}</td></tr>
    
    <tr>
    <th>设备入账时间：</th>
    <td><fmt:formatDate value="${current.deviceAccountedDate.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    
</tbody>
            </table>

    
</div>
</div>
</div>
</div>
</div>
</body>
</html>