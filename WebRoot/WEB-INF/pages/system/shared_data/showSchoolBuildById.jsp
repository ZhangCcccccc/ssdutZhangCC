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
				<div id="title">建筑信息</div>
				<a class="btn btn-new" onclick="window.history.go(-1)">返回</a>
			</div>   	

    		<div class="content-box">   		
            <table  class="tb"  id="my_show"> 
                <tbody>

    <tr>
    <th>建筑编号</th>
    <td>${current.buildNumber}</td></tr>
    <tr>
    <th>建筑名称</th>
    <td>${current.buildName}</td></tr>
    
    <tr>
    <th>学区编号：</th>
    <td>${current.systemCampus.campusNumber}</td></tr>
    
    <tr>
    <th>学院编号:</th>
    <td>${current.schoolAcademy.academyNumber}</td></tr>
    
    <tr>
    <th>面积:</th>
    <td>${current.area}</td></tr>
   
    
    <tr>
    <th>创建时间：</th>
    <td><fmt:formatDate value="${current.createdDate.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    
    <tr>
    <th>更新时间：</th>
    <td><fmt:formatDate value="${current.updatedDate.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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