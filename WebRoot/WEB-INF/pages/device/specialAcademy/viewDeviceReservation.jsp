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

<!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/viewDeivce.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/editDevice.js"></script> 

<style>
.tool-box2 th{text-align:left;}
</style>
</head>


<body>

<div class="right-content">	
	<div class="tool-box1">
		<table class="equip_tab">
		    <tr>
				<td>
				    <span>设备编号：</span>
				    <p>${device.schoolDevice.deviceNumber}</p>
				</td>
				<td>
				    <span>设备名称：</span>
				    <p class="equip_name">${device.schoolDevice.deviceName}</p>
				</td>
				<td>
				    <span>仪器型号：</span>
				    <p>${device.schoolDevice.devicePattern}</p>
				</td>
			</tr>
			<tr>
				<td>
				    <span>所在实验室：</span>
				    <p>${device.labRoom.labRoomName}</p>
				</td>
				<td>
				    <span>生产国别：</span>
				    <p>${device.schoolDevice.deviceCountry}</p>
				</td>
				<td>
				    <span>生产厂家：</span>
				    <p>${device.schoolDevice.manufacturer}</p>
				</td>
			</tr>
		</table>
	</div>
	<div id="TabbedPanels1" class="TabbedPanels">
	 <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceInfoRest(${device.id})";>设备详情</a></li>
		<%-- <c:if test="${device.CActiveByAllowSecurityAccess.id == 1}"> --%>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceTrainingRest(${device.id})";>培训计划</a>
		</li>
		<%--<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceSettingRest(${device.id})";>参数详情</a></li>--%>
		<%--<li class="TabbedPanelsTab" tabindex="0">
        <a href="javascript:void(0);" onclick="Access(${device.id})";>设备预约</a></li>--%>
		<li class="TabbedPanelsTab selected" tabindex="0"><a href="javascript:void(0);" onclick="viewDeviceReservationRest(${device.id},${currpage })";>使用情况</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceImageRest(${device.id})";>相关图片</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceVideoRest(${device.id})";>相关视频</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceDocumentRest(${device.id})";>相关文档</a></li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDimensionalCodeRest(${device.id})";>二维码</a></li>
		<%--<li class="TabbedPanelsTab" tabindex="0">
        <a href="javascript:void(0);" onclick="viewDeviceReservationRestAll(${device.id},1)";>汇总统计</a></li>--%>
	</ul>
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		     <div class="tool-box">
			     <form:form name="queryForm" action="${pageContext.request.contextPath}/device/listReservationByDevice?deviceId=${deviceId}&page=1" method="post" modelAttribute="reservation">
				</form:form>
			 </div>
	    	<div class="content-box">
	    		<div class="title">
	    			<div id="title">使用情况</div>
	    			<a class="btn btn-new"  herf="#" onclick="openSetupLink();">返回</a>	
	    		</div>
	            <table class="tb" id="my_show"> 
	                <thead>
	                    <tr>
	                    	<th style="width:3%">序号</th>
	                        <th style="width:15%">预约设备</th>
	                        <th style="width:5%">申请人</th>
	                        <th style="width:8%">申请人联系电话</th>
	                        <th style="width:5%">指导教师</th>
	                        <th style="width:15%">申请内容</th>
	                        <th style="width:10%">预约时间</th>
	                        <th style="width:7%">使用日期</th>
	                        <th style="width:8%">使用时间</th>
	                        <th style="width:5%">实验室</th>
	                        <th style="width:7%">审核人</th>
	                        <th style="width:5%">状态</th>
	                    </tr>
	                </thead>
	                
	                <tbody>
	                		<c:forEach items="${reservationList}" var="reservation" varStatus="i">
	                		<tr>
	                        <td>${i.count+(page-1)*pageSize}</td>
	                        <td>
		                        <c:if test="${empty reservation.innerDeviceName }">
		                        	${reservation.labRoomDevice.schoolDevice.deviceName}[(${reservation.labRoomDevice.schoolDevice.deviceNumber})]
		                        </c:if>
		                        <c:if test="${not empty reservation.innerDeviceName }">
		                        	${reservation.innerDeviceName}<font color="red">关联设备</font>
		                        </c:if>
	                        </td>
	                        <td>${reservation.userByReserveUser.cname}[${reservation.userByReserveUser.username}]</td>
	                        <td>${reservation.phone}</td>
	                        <td>${reservation.userByTeacher.cname}<a hidden="${reservation.userByTeacher.username}"></a> </td>
	                        <td><p>${reservation.content}</p></td>
	                        <td><fmt:formatDate value="${reservation.time.time}" pattern="yyyy-MM-dd HH:mm"/></td>
	                        <td>
		                        <%-- <c:if test="${reservation.CAuditResult.id==3 || reservation.CAuditResult.id==4 || reservation.CAuditResult.id==5 || reservation.CAuditResult.id==6}"> --%>
		                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' &&(reservation.CAuditResult.CNumber=='3' || reservation.CAuditResult.CNumber=='4' || reservation.CAuditResult.CNumber=='5' || reservation.CAuditResult.CNumber=='6')}">
		                        	<fmt:formatDate value="${reservation.originalBegin.time}" pattern="yyyy-MM-dd"/>
		                        </c:if>
		                        <%-- <c:if test="${reservation.CAuditResult.id!=3 && reservation.CAuditResult.id!=4 && reservation.CAuditResult.id!=5 && reservation.CAuditResult.id!=6}"> --%>
		                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber!='3' && reservation.CAuditResult.CNumber!='4' && reservation.CAuditResult.CNumber!='5' && reservation.CAuditResult.CNumber!='6'}">
		                        	<fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy-MM-dd"/>
		                        </c:if>
	                        </td>
	                        <td>
	                        <c:if test="${reservation.begintime.time.day eq reservation.endtime.time.day}">
		                        <%-- <c:if test="${reservation.CAuditResult.id==3 || reservation.CAuditResult.id==4 || reservation.CAuditResult.id==5 || reservation.CAuditResult.id==6}"> --%>
		                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' &&(reservation.CAuditResult.CNumber=='3' || reservation.CAuditResult.CNumber=='4' || reservation.CAuditResult.CNumber=='5' || reservation.CAuditResult.CNumber=='6')}">
		                        	<fmt:formatDate value="${reservation.originalBegin.time}" pattern="HH:mm:ss"/>-<fmt:formatDate value="${reservation.originalEnd.time}" pattern="HH:mm:ss"/>
		                        </c:if>
		                        <%-- <c:if test="${reservation.CAuditResult.id!=3 && reservation.CAuditResult.id!=4 && reservation.CAuditResult.id!=5 && reservation.CAuditResult.id!=6}"> --%>
		                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber!='3' && reservation.CAuditResult.CNumber!='4' && reservation.CAuditResult.CNumber!='5' && reservation.CAuditResult.CNumber!='6'}">
		                        	<fmt:formatDate value="${reservation.begintime.time}" pattern="HH:mm:ss"/>-<fmt:formatDate value="${reservation.endtime.time}" pattern="HH:mm:ss"/>
		                        </c:if>
	                        </c:if>
	                        
	                        <c:if test="${reservation.begintime.time.day ne reservation.endtime.time.day}">
		                        <%-- <c:if test="${reservation.CAuditResult.id==3 || reservation.CAuditResult.id==4 || reservation.CAuditResult.id==5 || reservation.CAuditResult.id==6}"> --%>
		                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' &&(reservation.CAuditResult.CNumber=='3' || reservation.CAuditResult.CNumber=='4' || reservation.CAuditResult.CNumber=='5' || reservation.CAuditResult.CNumber=='6')}">
		                        	起<fmt:formatDate value="${reservation.originalBegin.time}" pattern="yyyy/MM/dd HH:mm:ss"/><br>止<fmt:formatDate value="${reservation.originalEnd.time}" pattern="yyyy/MM/dd HH:mm:ss"/>
		                        </c:if>
		                        <%-- <c:if test="${reservation.CAuditResult.id!=3 && reservation.CAuditResult.id!=4 && reservation.CAuditResult.id!=5 && reservation.CAuditResult.id!=6}"> --%>
		                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber!='3' && reservation.CAuditResult.CNumber!='4' && reservation.CAuditResult.CNumber!='5' && reservation.CAuditResult.CNumber!='6'}">
		                        	起<fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy/MM/dd HH:mm:ss"/><br>止<fmt:formatDate value="${reservation.endtime.time}" pattern="yyyy/MM/dd HH:mm:ss"/>
		                        </c:if>
	                        </c:if>
	                        </td>
	                        <td>${reservation.labRoomDevice.labRoom.labRoomName}</td>
	                        <td>
	                        <%-- <c:if test="${reservation.CAuditResult.id ne 4 && reservation.CAuditResult.id ne 5 && reservation.CAuditResult.id ne 6}">  <!-- 非审核取消状态才显示 --> --%>
	                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber!='4' && reservation.CAuditResult.CNumber!='5' && reservation.CAuditResult.CNumber!='6'}">
	                        <c:forEach items="${reservation.labRoomDeviceReservationResults}" var="s"> 
	                              <%-- <c:if test="${s.CTrainingResult.id==1}">${s.user.cname}(<font color="blue">通过</font>)<br></c:if> --%>
	                              <c:if test="${s.CTrainingResult.CCategory=='c_audit_result' && s.CTrainingResult.CNumber=='1'}">${s.user.cname}(<font color="blue">通过</font>)<br></c:if>
	                         </c:forEach>
	                         <c:forEach items="${reservation.labRoomDeviceReservationResults}" var="s"> 
	                              <%-- <c:if test="${s.CTrainingResult.id==2}">${s.user.cname}(<font color="red">拒绝</font>)<br></c:if> --%>
	                              <c:if test="${s.CTrainingResult.CCategory=='c_audit_result' && s.CTrainingResult.CNumber=='2'}">${s.user.cname}(<font color="red">拒绝</font>)<br></c:if>
	                         </c:forEach>
	                         </c:if>
	                         </td>
	                        <td>
	                        <%-- <c:if test="${reservation.CAuditResult.id ne 4 && reservation.CAuditResult.id ne 5 && reservation.CAuditResult.id ne 6}">
	                        ${reservation.CAuditResult.name} --%>
	                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber!='4' && reservation.CAuditResult.CNumber!='5' && reservation.CAuditResult.CNumber!='6'}">
	                        	${reservation.CAuditResult.CName}
	                        </c:if>
	                        <%-- <c:if test="${reservation.CAuditResult.id==4}">  <!-- 审核取消状态 -->
	                        ${reservation.CAuditResult.name}</br> --%>
	                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber=='4'}">
                       		${reservation.CAuditResult.CName}</br>
                        	<font color="red">原因：导师未在规定时间内审核</font>
	                        </c:if>
	                        <%-- <c:if test="${reservation.CAuditResult.id==5}">  <!-- 审核取消使用时间过期状态 -->
	                        ${reservation.CAuditResult.name}</br> --%>
	                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber=='5'}">
                       		${reservation.CAuditResult.CName}</br>
	                        <font color="red">原因：预约的时间已过，未审核</font>
	                        </c:if>
	                        <%-- <c:if test="${reservation.CAuditResult.id==6}">  <!-- 排课占用 -->
	                        ${reservation.CAuditResult.name}</br> --%>
	                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber=='6'}">
                       		${reservation.CAuditResult.CName}</br>
	                        <font color="red">原因：预约时间段教学排课使用，您的设备预约被取消，请另行预约时间。</font>
	                        </c:if>
	                        </td>
	                        </tr>
	                		</c:forEach>
	                       
	                </tbody>
	            </table>
	            
	<!-- 分页模块 -->
		<div class="page" >
	        ${totalRecords}条记录,共${pageModel.totalPage}页
	    <a href="javascript:void(0)" onclick="viewDeviceReservationRest(${device.id},1)" target="_self">首页</a>			    
		<a href="javascript:void(0)" onclick="viewDeviceReservationRest(${device.id},${pageModel.previousPage})" target="_self">上一页</a>
		<a href="javascript:void(0)" onclick="viewDeviceReservationRest(${device.id},${pageModel.nextPage})" target="_self">下一页</a>
	 	<a href="javascript:void(0)" onclick="viewDeviceReservationRest(${device.id},${pageModel.lastPage})" target="_self">末页</a>
	    </div>
	<!-- 分页模块 -->
	</div>
	
	</div>
  			<input type="hidden" id="labRoomId" value="${labRoomId }">
  			<input type="hidden" id="deviceName" value="${deviceName }">
  			<input type="hidden" id="deviceNumber" value="${deviceNumber }">
  			<input type="hidden" id="username" value="${username }">
  			<input type="hidden" id="page" value="${page }">
  			<input type="hidden" id="currpage" value="${currpage }">
  			<input type="hidden" id="schoolDevice_allowAppointment" value="${schoolDevice_allowAppointment}">
    		<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
		</div>
	  </div>
	</div>
	
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>

</body>
</html>
