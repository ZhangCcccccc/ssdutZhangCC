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
			<li>项目立项管理</li>
			<li class="end">项目立项详细信息</li>
			<a class="btn btn-return" href="${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=1&status=${status}">返回</a>
		</ul>
	</div>
  </div>
  
  <div class="content-box">
    <table>
      <tr>
        <th>实验室项目编号：</th><td>${labConstructionProject.projectNumber}</td>
        <th>实验室项目名称：</th><td>${labConstructionProject.projectName}</td>
        <th>所属院（系、部）：</th><td>${labConstructionProject.schoolAcademy.academyNumber}</td>
        <th>项目负责人：</th><td>${labConstructionProject.user.username}</td>
      </tr>
      <tr>
        <th>联系电话：</th><td>${labConstructionProject.telephone}</td>
        <th>Email地址：</th><td>${labConstructionProject.email}</td>
        <th>预算总额：</th><td>${labConstructionProject.projectBudget}</td>
        <th>填报时间：</th><td> <fmt:formatDate value="${labConstructionProject.createdAt.time}" pattern="yyyy-MM-dd"/> </td>
      </tr>
      <tr>
        <th>项目分析：</th><td>${labConstructionProject.projectAnalysis}</td>
      </tr>
      <tr>  
        <th>建设方案：</th><td>${labConstructionProject.projectScheme}</td>
      </tr>
      <tr>  
        <th>审核状态：</th>
        <td>
			<c:if test="${labConstructionProject.auditResults==1}">草稿</c:if>
		    <c:if test="${labConstructionProject.auditResults==2}">审核中</c:if>
		    <c:if test="${labConstructionProject.auditResults==3}">审核通过</c:if>
		    <c:if test="${labConstructionProject.auditResults==4}">审核拒绝</c:if>
		</td>
      </tr>
    </table>
  </div>
  
  <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab selected" id="s1">项目成员</li>
	</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
    <div class="content-box">
    <table>
      <thead>
      <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>工号</th>
        <th>联系电话</th>
        <th>Email</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${projectMembers}" var="pm" varStatus="i">
      <tr>
        <td>${i.count}</td>
        <td>${pm.user.cname}</td>
        <td>${pm.user.username}</td>
        <td>${pm.user.telephone}</td>
        <td>${pm.user.email}</td>
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
