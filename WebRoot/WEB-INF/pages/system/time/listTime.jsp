<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<head>
<meta name="decorator" content="iframe" />
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_LeftList.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_Searcher.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/js/SpryTabbedPanels.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js" type="text/javascript" ></script>
--%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">

</head>

<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			
			<div class="content-box">
				<div class="title">节次管理
					<sec:authorize ifAnyGranted="ROLE_PREEXTEACHING,ROLE_SUPERADMIN">	
					<a class="btn btn-edit"  href="${pageContext.request.contextPath}/system/newTime?campusNumber=${campusNumber}">新建</a>
					</sec:authorize>
					<%--<a class="btn" id="save">保存</a> 
					<a class="btn btn-return">返回</a>
				--%></div>
				<table>
					<thead>
						<tr>
							<th>序号</th>
							<th>节次名称</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>校区</th>
							<th>排序</th>
							<th>组合</th>	
							<sec:authorize ifAnyGranted="ROLE_PREEXTEACHING,ROLE_SUPERADMIN">					
							<th>操作</th>
							</sec:authorize>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${times}" var="current" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${current.sectionName}</td>
								<td><fmt:formatDate value="${current.startDate.time}"
										pattern="HH:mm:ss" />
								</td>
								<td><fmt:formatDate value="${current.endDate.time}"
										pattern="HH:mm:ss" />
								</td>
								<td>${current.systemCampus.campusName}</td>
								<td>${current.sequence}</td>
			    				<td>${current.combine}</td>
			    			<sec:authorize ifAnyGranted="ROLE_PREEXTEACHING,ROLE_SUPERADMIN">					
							<td>
							<a href="${pageContext.request.contextPath}/system/editTime?idKey=${current.id}&campusNumber=${campusNumber}">编辑</a>	
							</td>
							</sec:authorize>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
