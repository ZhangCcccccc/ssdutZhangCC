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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css" type="text/css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">

</head>
<!-- 导航栏 -->
<div class="navigation">
<div id="navigation">
<ul>
<li><a href="">系统管理</a></li>
<li class="end"><a href="${pageContext.request.contextPath}/system/listTime?currpage=1">节次管理</a></li>
</ul>
</div>
</div>
<!-- 导航栏 -->
<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<%--<div class="tool-box">
				<ul>
					<li>学年：<select>
							<option>2012</option>
							<option>2013</option>
							<option>2014</option>
					</select>
					</li>
					<li>学期名称：<input type="text"></li>
					<li>开始时间： 从<input type="text">
					</li>
					<li>到<input type="text">
					</li>
					<li><input type="button" value="查询">
					</li>
					<li><input type="button" value="导出">
					</li>
					<li><input type="button" value="打印">
					</li>
					<li><input type="button" value="恢复">
					</li>
				</ul>
				  <ul  class="new_bulid">
                <li class="new_bulid_1"><a href="${pageContext.request.contextPath}/addTerm">新建</a></li>
            </ul>    
			</div>--%>
			<div class="right-content">	
				<div class="title">查看节次信息
					<%--<a class="btn btn-edit"  href="${pageContext.request.contextPath}/system/newTime">新建</a> 
					<a class="btn" id="save">保存</a> 
					<a class="btn btn-return">返回</a>
				--%>
				<li class="btn btn-edit"><a onclick="window.history.go(-1)">返回</a></li></div>
				<div class="tool-box1">
				<form:form id="timeForm" modelAttribute="time">
				<table>
					<tbody>
							<tr>
							<th>自动编号：</th>
							<td>${time.id}</td>
							</tr>
							<tr>
							<th>节次名称：</th>
							<td>${time.sectionName}</td>
							</tr>
							<tr>
							<th>开始时间：</th>
							<td><fmt:formatDate value="${time.startDate.time}"
										pattern="HH:mm:ss" /></td>
							</tr>
							<tr>
							<th>结束时间：</th>
							<td><fmt:formatDate value="${time.endDate.time}"
										pattern="HH:mm:ss" /></td>
							</tr>
							<tr>
							<th>创建时间：</th>
							<td><fmt:formatDate value="${time.createdDate.time}"
										pattern="yy:MM:dd HH:mm:ss" /></td>
							</tr>
							<tr>
							<th>更新时间：</th>
							<td><fmt:formatDate value="${time.updatedDate.time}"
										pattern="yy:MM:dd HH:mm:ss" /></td>
							</tr>
					</tbody>
				</table>
				</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
