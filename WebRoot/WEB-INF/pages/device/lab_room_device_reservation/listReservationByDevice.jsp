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

<script type="text/javascript">
	// 跳转
	function targetUrl(url)
	{
	  document.queryForm.action=url;
	  document.queryForm.submit();
	}
	// 取消
	function cancelQuery(){
		window.location.href="${pageContext.request.contextPath}/device/listReservationByDevice?deviceNumber=${deviceNumber}&page=1";
	}	

</script>

</head>

<body>
<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">${deviceName }</a></li>
						<li class="end"><a href="javascript:void(0)">使用情况</a></li>
					</ul>
				</div>
			</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsTabGroup-box">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		     <div class="tool-box">
		     <form:form name="queryForm" action="${pageContext.request.contextPath}/device/listReservationByDevice?deviceNumber=${deviceNumber}&page=1" method="post" modelAttribute="reservation">
				<table class="list_form">
					<tr>
						
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
							导师：
							<form:select path="userByTeacher.username" class="chzn-select">
							<form:option value="">请选择</form:option>
							<c:forEach var="teacher" items="${teachers}"> 
							<form:option value="${teacher.key}">[${teacher.key}]${teacher.value}</form:option>
							</c:forEach>
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
							<input type="submit" value="查询">
							<input type="button" value="取消" onclick="cancelQuery();">
						</td>
					</tr >
				</table>
			</form:form>
		       
		    </div>
    	<div class="content-box">
    		<div class="title">
    			<div id="title">全部</div>
    			<a class="btn btn-new" href="${pageContext.request.contextPath}/report/reportLargeDeviceUsedAcademy?academyNumber=${academyNumber }">返回</a>
    		</div>
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                    	<th style="width:3%">序号</th>
                        <th style="width:15%">预约设备</th>
                        <th style="width:5%">申请人</th>
                        <th style="width:5%">指导教师</th>
                        <th style="width:15%">使用内容</th>
                        <th style="width:5%">使用机时</th>
                        <th style="width:7%">日期</th>
                        <th style="width:8%">使用时间</th>
                        <th style="width:8%">设备管理员</th>
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
                        <td>${reservation.userByTeacher.cname}<a hidden="${reservation.userByTeacher.username}"></a> </td>
                        <td><p>${reservation.content}</p></td>
                        <td>
                        <fmt:formatNumber value="${reservation.reserveHours}" type="number"  maxFractionDigits="2"/>小时</td>
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
                        <td>${reservation.labRoomDevice.user.cname}[${reservation.labRoomDevice.user.username}]</td>
                        </tr>
                		</c:forEach>
                       
                </tbody>
            </table>
            
	<!-- 分页模块 -->
		<div class="page" >
	        ${totalRecords}条记录,共${pageModel.totalPage}页
	    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/listReservationByDevice?deviceNumber=${deviceNumber}&page=1')" target="_self">首页</a>			    
		<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/listReservationByDevice?deviceNumber=${deviceNumber}&page=${pageModel.previousPage}')" target="_self">上一页</a>
		第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
		<option selected="selected" value="${pageContext.request.contextPath}/device/listReservationByDevice?deviceNumber=${deviceNumber}&page=${pageModel.currPage}">${pageModel.currPage}</option>
		<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
	    <c:if test="${j.index!=pageModel.currPage}">
	    <option value="${pageContext.request.contextPath}/device/listReservationByDevice?deviceNumber=${deviceNumber}&page=${j.index}">${j.index}</option>
	    </c:if>
	    </c:forEach></select>页
		<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/listReservationByDevice?deviceNumber=${deviceNumber}&page=${pageModel.nextPage}')" target="_self">下一页</a>
	 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/listReservationByDevice?deviceNumber=${deviceNumber}&page=${pageModel.lastPage}')" target="_self">末页</a>
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
    
</script>
<!-- 下拉框的js -->

</body>
</html>


