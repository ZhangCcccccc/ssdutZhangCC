<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta name="decorator" content="iframe"/>
	<title>绩效报表</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhulims/common/sortTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.tablesorter.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		
</head>
<body>
	<div class="title">
		<div id="title">报表统计--${academy.academyName}大型设备列表</div>
		<a class="btn btn-new" onclick="window.history.go(-1);">返回</a>
	</div> 

	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/report/reportLargeDeviceAcademy?academyNumber=${academy.academyNumber }" method="post">
			 <ul>
  				<li>学期： </li>
  				<li>
  					<select class="chzn-select" name="term">
  						<option value="">请选择</option>
	  					<c:forEach items="${schoolTerms }" var="curr">
		  					<c:if test="${curr.id eq term}">
		  						<option value="${curr.id }" selected="selected">${curr.termName }</option>
		  					</c:if>	
		  					<c:if test="${curr.id ne term}">
		  						<option value="${curr.id }">${curr.termName }</option>
		  					</c:if>	
	  					</c:forEach>
  					</select>
  				</li>
  				
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消查询" onclick="cancel();"/></li>
  				</ul>
		</form:form>
	</div>

	<div class="content-box">
		<table class="tb">
			<thead>
				<tr>
				  	<th>序号</th>
				    <th>设备编号</th>
				    <th>设备名称</th>
				    <th>使用机时</th>
				    <th>使用次数</th>
				    <th>设备价格</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${largeDevices}" var="curr" varStatus="i">
					<tr>
						<td>${i.count}</td>
						<td>${curr.deviceNumber}</td> 
						<td>${curr.deviceName}</td>
						<td>
							<a href="${pageContext.request.contextPath }/device/listReservationByDevice?deviceNumber=${curr.deviceNumber}&page=1">
							<fmt:formatNumber value="${curr.useHours }" type="number"  maxFractionDigits="2"/></a>
						</td>
						<td>
							<a href="${pageContext.request.contextPath }/device/listReservationByDevice?deviceNumber=${curr.deviceNumber}&page=1">${curr.useCount }</a>
						</td>
						<td>
						<fmt:formatNumber value="${curr.devicePrice}" type="currency"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<script type="text/javascript">
		// 取消查询
		function cancel(){
			location.href = "${pageContext.request.contextPath}/report/reportLargeDeviceAcademy?academyNumber=${academy.academyNumber }";
		}
	</script>
	
</body>
</html>