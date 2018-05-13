<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
			<li>项目建设管理</li>
			<li class="end">项目建设详细信息</li>
			<a class="btn btn-return" href="${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=1&status=${status}">返回</a>
		</ul>
	</div>
  </div>
  
  <div class="content-box">
    <table>
      <tr>
        <th>项目负责人：</th><td>${labConstructionProject.user.username}</td>
        <th>项目名称：</th><td>${labConstructionProject.projectName}</td>
        <th>项目编号：</th><td>${labConstructionProject.projectNumber}</td>
        <th>经费编号：</th><td>${labConstructionFunding.fundingNumber}</td>
      </tr>
      <tr>  
        <th>审核状态：</th>
        <td>
			<c:if test="${labConstructionPurchase.auditResults==1}">草稿</c:if>
		    <c:if test="${labConstructionPurchase.auditResults==2}">审核中</c:if>
		    <c:if test="${labConstructionPurchase.auditResults==3}">审核通过</c:if>
		    <c:if test="${labConstructionPurchase.auditResults==4}">审核拒绝</c:if>
		</td>
      </tr>
    </table>
  </div>
  
  <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab selected" id="s1">设备详单</li>
	</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
    <div class="content-box">
    <table>
      <thead>
      <tr>
		<th>序号</th>
		<th>设备名称</th>
		<th>型号/规格</th>
		<th>数量</th>
		<th>设备单价（元）</th>
	  </tr>
      </thead>
      <tbody>
      <c:forEach items="${labConstructionDevices}" var="curr" varStatus="i">
      <tr>
		<td>${i.count}</td>
		<td>${curr.deviceName}</td>
		<td>${curr.deviceModel}</td>
		<td>${curr.deviceQuantity}</td>
		<td>${curr.devicePriceSig}</td>
      </tr>
      </c:forEach>
      </tbody>
    </table>
    </div>
  </div>
  </div>
  </div>
  
</body>
</html>
