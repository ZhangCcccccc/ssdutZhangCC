<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式 -->	
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<!-- 打印开始 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<!-- 打印结束 -->

<!-- 打印、导出组件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/LodopFuncs.js"></script>
<script type="text/javascript">
	// 跳转
	function targetUrl(url)
	{
	  document.queryForm.action=url;
	  document.queryForm.submit();
	}
	// 取消
	function cancelQuery(){
		window.location.href="${pageContext.request.contextPath}/device/listLabRoomDeviceUsage?page=1";
	}	
	function print(){
	    $('#my_show').jqprint();
	}
</script>

</head>

<body>
<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">实验室设备预约</a></li>
						<li class="end"><a href="javascript:void(0)">设备使用报表</a></li>
					</ul>
				</div>
			</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
 <ul class="TabbedPanelsTabGroup">
  
			<li class="TabbedPanelsTab selected" tabindex="0" id="s0">
			<a href="${pageContext.request.contextPath}/device/listLabRoomDeviceUsage?page=1">设备使用报表</a>
			</li>
		
			<li class="TabbedPanelsTab"  tabindex="0" id="s1">
			<a href="${pageContext.request.contextPath}/device/listLabRoomDeviceUsageInAppointment?page=1">设备教学使用</a>
			</li>
		
	  </ul>
	<div class="TabbedPanelsTabGroup-box">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		     <div class="tool-box">
		     <form:form name="queryForm" action="${pageContext.request.contextPath}/device/listLabRoomDeviceUsage?page=1" method="post" modelAttribute="reservation">
				<table class="list_form">
					<tr>
						
						<td>
							学期：
							<form:select path="schoolTerm.id" class="chzn-select">
							<form:option value="">请选择</form:option>
							<form:options items="${terms}" itemLabel="termName" itemValue="id"/>
							</form:select>
							
						</td>
						
						<td>
						设备名称：<form:input path="labRoomDevice.schoolDevice.deviceName"/>
						</td>
						
						<td>
							使用人：
							<form:select path="userByReserveUser.username" class="chzn-select">
							<form:option value="">请选择</form:option>
							<c:forEach var="reserveUser" items="${reserveUsers}"> 
							<form:option value="${reserveUser.key}">[${reserveUser.key}]${reserveUser.value}</form:option>
							</c:forEach>
							</form:select>
							
						</td>
						
						<td>
							课题组：
							<form:select path="researchProject.id" class="chzn-select">
							<form:option value="">请选择</form:option>
							<c:forEach var="research" items="${researchs}"> 
							<form:option value="${research.key}">[${research.key}]${research.value}</form:option>
							</c:forEach>
							</form:select>
						</td>
						
						<td>
							设备管理员：
							<form:select path="labRoomDevice.user.username" class="chzn-select">
							<form:option value="">请选择</form:option>
							<c:forEach var="manageUser" items="${manageUsers}"> 
							<form:option value="${manageUser.key}">[${manageUser.key}]${manageUser.value}</form:option>
							</c:forEach>
							</form:select>
							
						</td>
					</tr>
					<tr>	
						<td>
						实验室：
							<form:select path="labRoomDevice.labRoom.id" class="chzn-select">
								<form:option value="">请选择</form:option>
								<form:options items="${labrooms}" />
							</form:select>
						</td>
						<td>使用时间
                            <input id="begintime" class="Wdate" type="text" name="begintime" 
                            onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm'})" value="${begintime }" style="width:100px;" 
                            readonly />
                            
                            <input id="endtime" class="Wdate" type="text" name="endtime" 
                            onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm'})" value="${endtime }" style="width:100px;" 
                            readonly />
                        </td>
						
						<td>
							使用机时：
							<form:input type="text" path="reserveHours" style="width:50px;"/>小时
						</td>
						
						<td>
							<input type="button" value="取消" onclick="cancelQuery();">
							<input type="submit" value="查询">
						</td>
					</tr >
				</table>
			</form:form>
		       
		    </div>
    	<div class="content-box">
    		<div class="title">
    			<div id="title">设备使用列表</div>
    			<a class="btn btn-new" onclick="exportLabRoomDeviceUsage()">导出</a>
    			<a class="btn btn-new" onclick="print()" >打印</a>
    		</div>
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                    	<th style="width:3%">序号</th>
                        <th style="width:8%">预约设备</th>
                        <th style="width:8%">申请人</th>
                        <%--<th style="width:5%">指导教师</th>--%>
                        <th style="width:10%">使用内容</th>
                        <th style="width:10%">课题组</th>
                        <th style="width:5%">使用机时/小时</th>
                        <th style="width:5%">日期</th>
                        <th style="width:10%">使用时间</th>
                        <th style="width:8%">设备管理员</th>
                        <th style="width:8%">实验室</th>
                        <th style="width:6%">收费情况</th>
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
                        <td>${reservation.userByReserveUser.cname}</td>
                        <%--<td>${reservation.userByTeacher.cname}<a hidden="${reservation.userByTeacher.username}"></a> </td>--%>
                        <td><p>${reservation.content}</p></td>
                        <td><p>${reservation.researchProject.name}</p></td>
                        <td><fmt:formatNumber value="${reservation.reserveHours}" type="number"  maxFractionDigits="2"/></td>
                        <td>
	                        	<fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>
                        <c:if test="${reservation.begintime.time.day eq reservation.endtime.time.day}">
	                        	<fmt:formatDate value="${reservation.begintime.time}" pattern="HH:mm:ss"/>-<fmt:formatDate value="${reservation.endtime.time}" pattern="HH:mm:ss"/>
                        </c:if>
                        
                        <c:if test="${reservation.begintime.time.day ne reservation.endtime.time.day}">
	                        	起<fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy/MM/dd HH:mm:ss"/><br>止<fmt:formatDate value="${reservation.endtime.time}" pattern="yyyy/MM/dd HH:mm:ss"/>
                        </c:if>
                        </td>
                        <td>${reservation.labRoomDevice.user.cname}</td>
                        <td>${reservation.labRoomDevice.labRoom.labRoomName}</td>
                        <td>
	                        <%-- <c:if test="${not empty reservation.labRoomDevice.CDeviceCharge.name}">
	                        	${reservation.labRoomDevice.CDeviceCharge.name}<br> --%>
	                        <c:if test="${not empty reservation.labRoomDevice.CDictionaryByDeviceCharge.CName}">
	                        	${reservation.labRoomDevice.CDictionaryByDeviceCharge.CName}<br>
	                        	<c:if test="${not empty reservation.labRoomDevice.price }">
	                        	<fmt:formatNumber value="${reservation.labRoomDevice.price*reservation.reserveHours}" type="currency"/>
	                        	</c:if>
	                        	<c:if test="${empty reservation.labRoomDevice.price }">
	                        	0元
	                        	</c:if>
	                        </c:if>	
	                        <%-- <c:if test="${empty reservation.labRoomDevice.CDeviceCharge.name}"> --%>
	                        <c:if test="${empty reservation.labRoomDevice.CDictionaryByDeviceCharge.CName}">
	                        	<font color="red">标准未设置</font>
	                        </c:if>	
                        </td>
                        </tr>
                		</c:forEach>
                       
                </tbody>
            </table>
            
	<!-- 分页模块 -->
		<div class="page" >
	        ${totalRecords}条记录,共${pageModel.totalPage}页
	    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceUsage?page=1')" target="_self">首页</a>			    
		<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceUsage?page=${pageModel.previousPage}')" target="_self">上一页</a>
		第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
		<option selected="selected" value="${pageContext.request.contextPath}/device/listLabRoomDeviceUsage?page=${page}">${page}</option>
		<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
	    <c:if test="${j.index!=pageModel.currPage}">
	    <option value="${pageContext.request.contextPath}/device/listLabRoomDeviceUsage?page=${j.index}">${j.index}</option>
	    </c:if>
	    </c:forEach></select>页
		<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceUsage?page=${pageModel.nextPage}')" target="_self">下一页</a>
	 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceUsage?page=${pageModel.lastPage}')" target="_self">末页</a>
	    </div>
	<!-- 分页模块 -->
</div>

</div>
</div>
</div>
</div>
</div>


<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
    function exportLabRoomDeviceUsage(){
    	document.queryForm.action = "${pageContext.request.contextPath}/device/exportLabRoomDeviceUsage";
		document.queryForm.submit();
    }
    function query(){
    	document.queryForm.action = "${pageContext.request.contextPath}/device/listLabRoomDeviceUsage?page=1";
		document.queryForm.submit();
    }
    
</script>
<!-- 下拉框的js -->

</body>
</html>


