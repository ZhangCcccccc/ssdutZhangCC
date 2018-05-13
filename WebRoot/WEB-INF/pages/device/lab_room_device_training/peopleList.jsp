<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>

<html>
<head>
<title></title>
<meta name="decorator" content="iframe"/>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-sacale=1.0,user-scalable=no"/>
<style type="text/css" media="screen">
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css");
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css");
			@import url("${pageContext.request.contextPath}/css/style.css");
</style>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>

<script type="text/javascript">
  /**
 培训结果表单提交 
 */
 function trainFrom(){
 	var c=document.getElementById("radioTable").getElementsByTagName("input"); 
 			var idArray=new Array();
 			var valueArray=new Array();
       	    for(var i=0;i<c.length;i++){   
                if(c[i].type=="radio" && c[i].checked){
                		idArray.push(c[i].id);
                		valueArray.push(c[i].value);
           		}
          	}
          	var url = "${pageContext.request.contextPath}/device/saveTrainResult?idArray="+idArray+"&valueArray="+valueArray;
          	document.resultFrom.action=url;
       	 	document.resultFrom.submit();
 }
 
</script>

<style>
.tool-box2 th{text-align:left;}
</style>
</head>


<body>

<div class="right-content">	
	<div class="tool-box1">
		<table>
			<tr>
				<th>设备编号</th><td>${device.schoolDevice.deviceNumber}</td>
				<th>设备名称</th><td>${device.schoolDevice.deviceName}</td>
			</tr>
			<tr>
				<th>所在实验室</th><td>${device.labRoom.labRoomName}</td>
				<th>生产国别</th><td>${device.schoolDevice.deviceCountry}</td>
			</tr>
			<tr>
				<th>仪器型号</th><td>${device.schoolDevice.devicePattern}</td>
				<th>生产厂家</th><td>${device.schoolDevice.manufacturer}</td>
			</tr>
		</table>
	</div>
	<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">培训人员名单</div>
				<div class="content-double">
					<table id="radioTable">
						<thead>
							<tr>
								<th>序号</th>
								<th>学号</th>
								<th>姓名</th>
								<th>联系电话</th>
								<th>邮箱</th>
								<th>培训结果</th>
								<th>操作</th>
							</tr>
						</thead>
						<c:forEach items="${peoples}" var="p" varStatus="i">
						<tr>
							<td>${i.count}</td>
							<td>${p.user.username}</td>
							<td>${p.user.cname}</td>
							<td>${p.telephone}</td>
							<td>${p.eMail}</td>
							<td>
								<%-- <c:if test="${empty p.CTrainingResult}"> --%>
								<c:if test="${empty p.CDictionary}">
									<font color="red">未录入</font>
								</c:if>
								<%-- <c:if test="${not empty p.CTrainingResult}">
									${p.CTrainingResult.name} --%>
								<c:if test="${not empty p.CDictionary}">
									${p.CDictionary.CNname}
								</c:if>
							</td>
							<td>
								<%-- <c:if test="${empty p.CTrainingResult}"> --%>
								<c:if test="${empty p.CDictionary}">
									<c:forEach items="${results}" var="result"  varStatus="s">
									<%-- <input type="radio" id="${p.id}" name="p${i.count}" value="${result.id}" />${result.name} --%>
									<input type="radio" id="${p.id}" name="p${i.count}" value="${result.id}" />${result.CName}
									</c:forEach>
								</c:if>
								<%-- <c:if test="${not empty p.CTrainingResult}"> --%>
								<c:if test="${not empty p.CDictionary}">
									<a class="changeResult">修改结果</a>
									<c:forEach items="${results}" var="result"  varStatus="s">
										<%-- <c:if test="${p.CTrainingResult.id==result.id}"> --%>
										<c:if test="${p.CDictionary.id==result.id}">
										<span class="edit-edit"><input type="radio" id="${p.id}" name="p${i.count}" value="${result.id}" checked="checked">${result.CName}</span>
										</c:if>
										
										<%-- <c:if test="${p.CTrainingResult.id!=result.id}"> --%>
										<c:if test="${p.CDictionary.id!=result.id}">
										<span class="edit-edit"><input type="radio" id="${p.id}" name="p${i.count}" value="${result.id}">${result.CName}</span>
										</c:if>
									</c:forEach>
								</c:if>
							</td>
						</tr>
						</c:forEach>
						<form id="trainFrom" name="resultFrom" method="post">
							<table>
								<tr>
									<td>
										<input type="button" value="提交" onclick="trainFrom();">
										<input type="button" value="返回" onclick="window.history.go(-1);">
									</td>
								</tr>
							</table>
						</form>
					</table>
				</div>
			</div>
		</div>
	
	  </div>
	</div>
	
</div>
	<script type="text/javascript">
		$(".changeResult").click(function(){
			//$(".btn-edit").slideUp(); //原信息隐藏
			$(this).hide();//修改按钮隐藏
			$(this).parent().find(".edit-edit").slideDown();//修改信息显示
		});
	</script>
</body>
</html>
