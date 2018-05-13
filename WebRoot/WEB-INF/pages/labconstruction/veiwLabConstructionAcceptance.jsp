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
			<a class="btn btn-return" href="${pageContext.request.contextPath}/labconstruction/listLabConstructionAcceptances?currpage=1">返回</a>
		</ul>
	</div>
  </div>
  
  <div class="content-box">
    <table>
      <tr>
        <th>项目负责人：</th><td>${labConstructionProject.user.username}</td>
        <th>项目名称：</th><td>${labConstructionProject.projectName}</td>
      </tr>
        <th>项目编号：</th><td>${labConstructionProject.projectNumber}</td>
        <th>创建时间：</th><td><fmt:formatDate value="${labConstructionAcceptance.createdAt.time}" pattern="yyyy-MM-dd"/></td>
      <tr>
      </tr>
    </table>
  </div>
  
  
 <fieldset class="introduce-box">
		<legend>项目建设阶段资料</legend> 
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
    <div class="content-box">
    	<table>
						
			<thread>
				<tr>
					<th>序号</th>
					<th>名称</th>
					<th>上传时间</th>
					<th>上传人</th>
					<th>操作</th>
				</tr>	
			</thread>
								
			<tbody>
				<c:forEach items="${commonDocuments1}" var="d" varStatus="i">
				<tr>
					<td>${i.count}</td>
					<td>${d.documentName}</td>
					<td><fmt:formatDate value="${d.createdAt.time}" pattern="yyyy-MM-dd"/></td>
					<td>${d.user.cname}</td>
					<td>
						<a class="btn btn-common" href="${pageContext.request.contextPath}/labconstruction/downloadAcceptanceDocument?id=${d.id}">下载</a>
					</td>
				</tr>	
				</c:forEach>
			</tbody>
				
			</table>
    </div>
  </div>
  </div>
  </div>
  
  </fieldset>
  
  
  <fieldset class="introduce-box">
		<legend>项目教学阶段资料</legend> 
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
    <div class="content-box">
    	<table>
						
			<thread>
				<tr>
					<th>序号</th>
					<th>名称</th>
					<th>上传时间</th>
					<th>上传人</th>
					<th>操作</th>
				</tr>	
			</thread>
								
			<tbody>
				<c:forEach items="${commonDocuments2}" var="d" varStatus="i">
				<tr>
					<td>${i.count}</td>
					<td>${d.documentName}</td>
					<td><fmt:formatDate value="${d.createdAt.time}" pattern="yyyy-MM-dd"/></td>
					<td>${d.user.cname}</td>
					<td>
						<a class="btn btn-common" href="${pageContext.request.contextPath}/labconstruction/downloadAcceptanceDocument?id=${d.id}">下载</a>
					</td>
				</tr>	
				</c:forEach>
			</tbody>
				
			</table>
    </div>
  </div>
  </div>
  </div>
  
  </fieldset>
  
  
  <fieldset class="introduce-box">
		<legend>仪器设备资料</legend> 
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
    <div class="content-box">
    	<table>
						
			<thread>
				<tr>
					<th>序号</th>
					<th>名称</th>
					<th>上传时间</th>
					<th>上传人</th>
					<th>操作</th>
				</tr>	
			</thread>
								
			<tbody>
				<c:forEach items="${commonDocuments3}" var="d" varStatus="i">
				<tr>
					<td>${i.count}</td>
					<td>${d.documentName}</td>
					<td><fmt:formatDate value="${d.createdAt.time}" pattern="yyyy-MM-dd"/></td>
					<td>${d.user.cname}</td>
					<td>
						<a class="btn btn-common" href="${pageContext.request.contextPath}/labconstruction/downloadAcceptanceDocument?id=${d.id}">下载</a>
					</td>
				</tr>	
				</c:forEach>
			</tbody>
				
			</table>
    </div>
  </div>
  </div>
  </div>
  
  </fieldset>
  
  
  
  <fieldset class="introduce-box">
		<legend>综合效益资料</legend> 
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
    <div class="content-box">
    	<table>
						
			<thread>
				<tr>
					<th>序号</th>
					<th>名称</th>
					<th>上传时间</th>
					<th>上传人</th>
					<th>操作</th>
				</tr>	
			</thread>
								
			<tbody>
				<c:forEach items="${commonDocuments4}" var="d" varStatus="i">
				<tr>
					<td>${i.count}</td>
					<td>${d.documentName}</td>
					<td><fmt:formatDate value="${d.createdAt.time}" pattern="yyyy-MM-dd"/></td>
					<td>${d.user.cname}</td>
					<td>
						<a class="btn btn-common" href="${pageContext.request.contextPath}/labconstruction/downloadAcceptanceDocument?id=${d.id}">下载</a>
					</td>
				</tr>	
				</c:forEach>
			</tbody>
				
			</table>
    </div>
  </div>
  </div>
  </div>
  
  </fieldset>
  
</body>
</html>
