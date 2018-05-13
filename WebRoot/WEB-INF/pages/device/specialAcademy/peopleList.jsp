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
	var idStr="";
	var valueStr="";
   	    for(var i=0;i<c.length;i++){   
            if(c[i].type=="radio" && c[i].checked){
           		idArray.push(c[i].id);
           		valueArray.push(c[i].value);
           		idStr+=c[i].id+"S";
           		valueStr+=c[i].value+"S";
       		}
      	}
   	    if(valueArray.length>0){
    	    var url="${pageContext.request.contextPath}/device/saveTrainResultRest/"+ ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}" +"/"+${username}+ "/"+${page}+ "/"+ idStr + "/" + valueStr+ "/"+"${schoolDevice_allowAppointment}";
    	 	window.location.href=url;
   	    }else{
			$.messager.confirm('提示','您没有对培训结果进行改动，点击确定返回，点击取消留在当前页面',function(r){
			    if (r){
			    	editDeviceTrainingRest(${device.id});
			    }
			});
		}
 }
 
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/viewDeivce.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/editDevice.js"></script>

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
	 <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceInfoRest(${device.id})">设备详情</a>
		</li>
		<%-- <c:if test="${device.CActiveByAllowSecurityAccess.id == 1}"> --%>
		<c:if test="${device.CDictionaryByAllowSecurityAccess.CCategory=='c_active' && device.CDictionaryByAllowSecurityAccess.CNumber=='1'}">
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceTrainingRest(${device.id})">培训计划</a>
		</li>
		</c:if>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceSettingRest(${device.id})">参数设置</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceImageRest(${device.id})">相关图片</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceVideoRest(${device.id})">相关视频</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceDocumentRest(${device.id})">相关文档</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceDimensionalCodeRest(${device.id})">二维码</a>
		</li>
	</ul>
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			
			<div class="content-box">
				<div class="title">培训人员名单
				
				</div>
				<div class="tool-box">
					<table>
					<tr>
					<td>时间：</td>
					<td><fmt:formatDate value="${train.time.time}" pattern="yyyy-MM-dd"/>   </td>
					<td>学期：</td>
					<td>${train.schoolTerm.termName}</td>
					<td>教师：</td>
					<td>${train.user.cname}</td>
					</tr>
					<tr>
					<td>地点：</td>
					<td>${train.address}</td>
					<td>人数：</td>
					<td>${train.number}</td>
					<td>内容：</td>
					<td>${train.content}</td>
					</tr>
					<tr>
					<td><%--<a class="btn btn-new" href="${pageContext.request.contextPath}/device/deviceTraining?deviceId=${device.id}">返回</a>--%>
					<c:if test="${tag==1 }">
					<a class="btn btn-new" href="javascript:void(0);" onclick="editDeviceTrainingRest(${device.id})";>返回</a></td>
					</c:if>
					<c:if test="${tag==2 }">
					<a class="btn btn-new" href="javascript:void(0);" onclick="viewDeviceTrainingRest(${device.id})";>返回</a></td>
					</c:if>
					</tr>
					</table>	
					
				</div>	
				<div class="content-double">
					
					<c:if test="${toChangeAudit==1 }">	
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
									${p.CDictionary.CName}
								</c:if>
							</td>
							<td>
								<%-- <c:if test="${empty p.CTrainingResult}"> --%>
								<c:if test="${empty p.CDictionary}">
									<c:forEach items="${results}" var="result"  varStatus="s">
									<input type="radio" id="${p.id}" name="p${i.count}" value="${result.CNumber}" />${result.CName}
									</c:forEach>
								</c:if>
								<%-- <c:if test="${not empty p.CTrainingResult}"> --%>
								<c:if test="${not empty p.CDictionary}">
									<a class="changeResult">修改结果</a>
									<c:forEach items="${results}" var="result"  varStatus="s">
										<%-- <c:if test="${p.CTrainingResult.id==result.id}"> --%>
										<c:if test="${p.CDictionary.id==result.id}">
										<span class="edit-edit"><input type="radio" id="${p.id}" name="p${i.count}" value="${result.CNumber}" checked="checked">${result.CName}</span>
										</c:if>
										
										<%-- <c:if test="${p.CTrainingResult.id!=result.id}"> --%>
										<c:if test="${p.CDictionary.id!=result.id}">
										<span class="edit-edit"><input type="radio" id="${p.id}" name="p${i.count}" value="${result.CNumber}">${result.CName}</span>
										</c:if>
									</c:forEach>
								</c:if>
							</td>
						</tr>
						</c:forEach>
						<form id="trainFrom" method="post">
						<table>
						<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN,ROLE_LABMANAGER">
							<tr>
								<td>
								<input type="button" value="提交" onclick="trainFrom();">
								</td>
							</tr>
						</sec:authorize>
						</table>
						</form>
						
						
					</table>
					</c:if>
					
					<c:if test="${toChangeAudit==2 }">	
					<table id="radioTable">
						<thead>
							<tr>
								<th>序号</th>
								<th>学号</th>
								<th>姓名</th>
								<th>联系电话</th>
								<th>邮箱</th>
								<th>培训结果</th>
							</tr>
						</thead>
						<c:forEach items="${peoples}" var="pp" varStatus="i">
						<tr>
							<td>${i.count}</td>
							<td>${pp.user.username}</td>
							<td>${pp.user.cname}</td>
							<td>${pp.telephone}</td>
							<td>${pp.eMail}</td>
							<td>
							<%-- <c:if test="${empty pp.CTrainingResult}"> --%>
							<c:if test="${empty pp.CDictionary}">
							未知
							</c:if>
							<%-- <c:if test="${not empty pp.CTrainingResult}">
							${pp.CTrainingResult.name} --%>
							<c:if test="${not empty pp.CDictionary}">
							${pp.CDictionary.CName}
							</c:if>
							</td>
						</tr>
						</c:forEach>
						
					</table>
					</c:if>
				</div>
						     	
				<input type="hidden" id="labRoomId" value="${labRoomId }">
				<input type="hidden" id="deviceName" value="${deviceName }">
				<input type="hidden" id="deviceNumber" value="${deviceNumber }">
				<input type="hidden" id="username" value="${username }">
				<input type="hidden" id="page" value="${page }">
      			<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
      			<input type="hidden" id="schoolDevice_allowAppointment" value="${schoolDevice_allowAppointment}">
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
