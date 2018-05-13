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
			<a class="btn btn-return" href="${pageContext.request.contextPath}/labconstruction/listLabConstructionPurchases?currpage=1&status=${status}">返回</a>
		</ul>
	</div>
  </div>
  
  <div class="content-box">
    <table>
      <tr>
        <th>项目负责人：</th><td>${labConstructionProject.user.username}</td>
        <th>项目名称：</th><td>${labConstructionProject.projectName}</td>
        <th>项目编号：</th><td>${labConstructionProject.projectNumber}</td>
        <th>申购部门：</th><td>${labConstructionPurchase.schoolAcademy.academyNumber}</td>
      </tr>
      <tr>
        <th>申购时间：</th><td> <fmt:formatDate value="${labConstructionPurchase.purchaseTime.time}" pattern="yyyy-MM-dd"/></td>
        <th>申请人：</th><td>${labConstructionPurchase.userByApplicant.username}</td>
        <th>保管人：</th><td>${labConstructionPurchase.userByKeeper.username}</td>
        <th>使用地点：</th><td>${labConstructionPurchase.useLocation}</td>
      </tr>
      <tr>  
        <th>申购类别：</th><td>${labConstructionPurchase.CDictionary.CName}</td>
      </tr>
      <tr>
        <th>现有状况、申购理由及要求：</th><td>${labConstructionPurchase.purchaseReason}</td>
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
		<li class="TabbedPanelsTab selected" id="s1">设备、软件配置明细表</li>
	</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
    <div class="content-box">
    <table>
      <thead>
      <tr>
		<th>序号</th>
		<th>物品名称</th>
		<th>型号/规格</th>
		<th>数量</th>
		<th>参考价格（元）</th>
		<th>要求到货时间</th>
		<th>备注（推荐供货源等）</th>
	  </tr>
      </thead>
      <tbody>
      <c:forEach items="${labConstructionDevices}" var="curr" varStatus="i">
      <tr>
		<td>${i.count}</td>
		<td>${curr.deviceName}</td>
		<td>${curr.deviceModel}</td>
		<td>${curr.deviceQuantity}</td>
		<td>${curr.devicePriceRef}</td>
		<td>   <fmt:formatDate value="${curr.arrivalTime.time}" pattern="yyyy-MM-dd"/></td>
		<td>${curr.comments}</td>
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
