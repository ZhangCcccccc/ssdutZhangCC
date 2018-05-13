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
function print(){
    $('#my_show').jqprint();
}
	// 跳转
	function targetUrl(url)
	{
	  document.queryForm.action=url;
	  document.queryForm.submit();
	}
	// 取消
	function cancelQuery(){
		window.location.href="${pageContext.request.contextPath}/device/listLabRoomDeviceUsageInAppointment?page=1";
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
  
			<li class="TabbedPanelsTab" tabindex="0" id="s0">
			<a href="${pageContext.request.contextPath}/device/listLabRoomDeviceUsage?page=1">设备使用报表</a>
			</li>
		
			<li class="TabbedPanelsTab  selected"  tabindex="0" id="s1">
			<a href="${pageContext.request.contextPath}/device/listLabRoomDeviceUsageInAppointment?page=1">设备教学使用</a>
			</li>
		
	  </ul>
	<div class="TabbedPanelsTabGroup-box">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		     <div class="tool-box">
		     <form:form name="queryForm" action="${pageContext.request.contextPath}/device/listLabRoomDeviceUsageInAppointment?page=1" method="post" >
				<table class="list_form">
					<tr>
						
						 <td>
							设备名称：
							<select id="deviceName" name="deviceName" class="chzn-select">
							<option value="">请选择</option>
							<c:forEach var="curr" items="${devices}"> 
							<c:if test="${deviceName eq curr.labRoomDevice.schoolDevice.deviceName }">
								<option value="${curr.labRoomDevice.schoolDevice.deviceName}" selected="selected">${curr.labRoomDevice.schoolDevice.deviceName }</option>
							</c:if>
							<c:if test="${deviceName ne curr.labRoomDevice.schoolDevice.deviceName }">
								<option value="${curr.labRoomDevice.schoolDevice.deviceName}" >${curr.labRoomDevice.schoolDevice.deviceName }</option>
							</c:if>
							</c:forEach> 
							</select> 
						</td> 
						<td>
							设备编号：
							<select id="deviceNumber" name="deviceNumber" class="chzn-select">
							<option value="">请选择</option>
							<c:forEach var="curr" items="${devices}"> 
							<c:if test="${deviceNumber eq curr.labRoomDevice.schoolDevice.deviceNumber }">
							<option value="${curr.labRoomDevice.schoolDevice.deviceNumber}" selected="selected">${curr.labRoomDevice.schoolDevice.deviceNumber }</option>
							</c:if>
							<c:if test="${deviceNumber ne curr.labRoomDevice.schoolDevice.deviceNumber }">
							<option value="${curr.labRoomDevice.schoolDevice.deviceNumber}" >${curr.labRoomDevice.schoolDevice.deviceNumber }</option>
							</c:if>
							</c:forEach>
							</select> 
						</td>    
						 <td>
							课程名称：
							<select id="courseName" name="courseName" class="chzn-select">
							<option value="">请选择</option>
							<c:forEach var="curr" items="${courses}"> 
							<c:if test="${courseName eq curr[12] }">
							<option value="${curr[12]}"  selected="selected">${curr[12] }</option>
							</c:if>
							<c:if test="${courseName ne curr[12] }" >
							<option value="${curr[12]}">${curr[12] }</option>
							</c:if>
							</c:forEach>
							</select>
							
						</td>  
					</tr>
					<tr>	
 						<td>
							实验项目名称：
							<select id="itemName" name="itemName" class="chzn-select">
							<option value="">请选择</option>
							<c:forEach var="curr" items="${items}"> 
							<c:if test="${curr.operationItem.lpName eq itemName}">
								<option value="${curr.operationItem.lpName}" selected="selected">${curr.operationItem.lpName }</option>
							</c:if>
							<c:if test="${curr.operationItem.lpName ne itemName}">
								<option value="${curr.operationItem.lpName}" >${curr.operationItem.lpName }</option>
							</c:if>
							</c:forEach>
							</select>
						</td> 
 						<td>
							上课教师：
							<select id="teacherName" name="teacherName" class="chzn-select">
							<option value="">请选择</option>
							<c:forEach var="curr" items="${teachers}"> 
							<c:if test="${teacherName eq curr.user.cname }">
								<option value="${curr.user.cname}" selected="selected" >${curr.user.cname }[${curr.user.username }]</option>
							</c:if>
							<c:if test="${teacherName ne curr.user.cname }">
								<option value="${curr.user.cname}">${curr.user.cname }[${curr.user.username }]</option>
							</c:if>
							</c:forEach>
							</select>
						</td>	 
						
						<td>
							<input type="button" value="取消" onclick="cancelQuery();">
							<input type="button" onclick="query()" value="查询">
						</td>
					</tr >
				</table>
			</form:form>
		       
		    </div>
    	<div class="content-box">
    		<div class="title">
    			<div id="title">设备使用列表</div>
    			<a class="btn btn-new" onclick="exportLabRoomDeviceUsageInAppointment()">导出</a>
    			<a class="btn btn-new" onclicl="print()" >打印</a>
    		</div>
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                    	<th style="width:3%">序号</th>
                        <th style="width:8%">设备名称</th>
                        <th style="width:8%">课程名称</th>
                        <th style="width:5%">实验项目</th>
                        <th style="width:10%">星期</th>
                        <th style="width:5%">节次</th>
                        <th style="width:5%">周次</th>
                        <th style="width:10%">上课教师</th>
                        <th style="width:8%">实验室</th>
                    </tr>
                </thead>
                
                <tbody>
                		<c:forEach items="${listLabRoomDeviceUsageInAppointments}" var="curr" varStatus="i">
                		<tr>
                        <td>${i.count+(page-1)*pageSize}</td>
                        <td>${curr[2]}</td>
                        <td>${curr[12]}</td>
                        <td>${curr[10]}</td>
                        <td>${curr[4]}</td>
                        <td>${curr[6]}</td>
                        <td>${curr[5]}</td>
                        <td>${curr[8]}</td>
                        <td>${curr[9]}</td>
                        </tr>
                		</c:forEach>
                       
                </tbody>
            </table>
            
	<!-- 分页模块 -->
		<div class="page" >
	        ${totalRecords}条记录,共${pageModel.totalPage}页
	    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceUsageInAppointment?page=1')" target="_self">首页</a>			    
		<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceUsageInAppointment?page=${pageModel.previousPage}')" target="_self">上一页</a>
		第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
		<option selected="selected" value="${pageContext.request.contextPath}/device/listLabRoomDeviceUsageInAppointment?page=${page}">${page}</option>
		<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
	    <c:if test="${j.index!=page}">
	    <option value="${pageContext.request.contextPath}/device/listLabRoomDeviceUsageInAppointment?page=${j.index}">${j.index}</option>
	    </c:if>
	    </c:forEach></select>页
		<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceUsageInAppointment?page=${pageModel.nextPage}')" target="_self">下一页</a>
	 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceUsageInAppointment?page=${pageModel.lastPage}')" target="_self">末页</a>
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
    function exportLabRoomDeviceUsageInAppointment(){
    	document.queryForm.action = "${pageContext.request.contextPath}/device/exportLabRoomDeviceUsageInAppointment";
		document.queryForm.submit();
    }
    function query(){
    	document.queryForm.action = "${pageContext.request.contextPath}/device/listLabRoomDeviceUsageInAppointment?page=1";
		document.queryForm.submit();
    }
</script>
<!-- 下拉框的js -->

</body>
</html>


