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
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<style type="text/css">

</style>

<script type="text/javascript">
	//首页
	function first(url){
		document.queryForm.action=url;
		document.queryForm.submit();
	}
	//末页
	function last(url){
		document.queryForm.action=url;
		document.queryForm.submit();
	}
	//上一页
	function previous(url){
		var page=${page};
		if(page==1){
			page=1;
		}else{
			page=page-1;
		}
		//alert("上一页的路径："+url+page);
		document.queryForm.action=url+page;
		document.queryForm.submit();
	}
	//下一页
	function next(url){
		var totalPage=${pageModel.totalPage};
		var page=${page};
		if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1
		}
		//alert("下一页的路径："+page);
		document.queryForm.action=url+page;
		document.queryForm.submit();
	}
function cancelQuery(){
	window.location.href="${pageContext.request.contextPath}/device/listLabRoomDevice?page=1";
}	

//AJAX验证是否通过安全准入
function Access(id){
	$.ajax({
	           url:"${pageContext.request.contextPath}/device/securityAccess?id="+id,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	           		if(data=="success"){
	           			//alert("您已经通过安全准入验证"+data);
	                  	window.location.href="${pageContext.request.contextPath}/device/reservationDevice?id="+id;
	           		}else{
	           			alert("您还未通过安全准入验证！"+data);
	           			window.location.href="${pageContext.request.contextPath}/device/findAllTrainingByDeviceId?deviceId="+id;
	           		}    
	           }
	});
	
}
</script>


</head>

<body>
<div class="iStyle_RightInner">
<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">实验设备管理</a></li>
			<li class="end"><a href="javascript:void(0)">设备管理</a></li>
		</ul>
	</div>
</div>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		     <div class="tool-box">
		      	<form:form name="queryForm" action="${pageContext.request.contextPath}/device/listLabRoomDevice?page=1" method="post" modelAttribute="labRoomDevice">
					<table class="list_form">
						<tr>
							<td>实验室：
								<form:select class="chzn-select"  path="labRoom.id">
								<form:option value="">请选择</form:option>
								<form:options cssStyle="width:300px;" items="${rooms}" itemLabel="labRoomName" itemValue="id"/>
								</form:select>		    				    				            
							</td> 
							 
							<td>设备编号：
								<form:select class="chzn-select"  path="schoolDevice.deviceNumber">
								<form:option value="">请选择</form:option>
								<form:options cssStyle="width:200px;" items="${listLabRoomDeviceAll}" itemLabel="schoolDevice.deviceNumber" itemValue="schoolDevice.deviceNumber"/>
								</form:select>
							</td> 
				     
							<td>设备名称：
								<form:select class="chzn-select"  path="schoolDevice.deviceName">
								<form:option value="">请选择</form:option>
								<%-- <form:options cssStyle="width:200px;" items="${listLabRoomDeviceAll}" itemLabel="schoolDevice.deviceName" itemValue="schoolDevice.deviceName"/> --%>
+								<c:forEach items="${listLabRoomDeviceAll}" var="labRoomDevice">
+									<form:option value="${labRoomDevice.schoolDevice.deviceName}">${labRoomDevice.schoolDevice.deviceName}[${labRoomDevice.schoolDevice.deviceNumber}]</form:option>
+								</c:forEach>
								</form:select>
							</td> 
							
							<td>设备管理员：
								<form:select class="chzn-select"  path="schoolDevice.userByKeepUser.username">
								<form:option value="">请选择</form:option>
								<form:options cssStyle="width:200px;" items="${users}" itemLabel="cname" itemValue="username"/>
								</form:select>
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
			<div id="title">设备管理</div>
			<input class="btn btn-new" type="button" value="导出">
			<input class="btn btn-new" type="button" value="打印">
			
		</div>
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                    	<th>设备编号</th>
                        <th>设备名称</th>
                        <th>设备状态</th>
                        <th>实验室</th>
                        <th>设备管理员</th>
                        <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN,ROLE_TEACHER,ROLE_ASSISTANT,ROLE_STUDENT">
                        <th>操作</th>
                        </sec:authorize>
                        <th>管理</th>
                        <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN,ROLE_STUDENT">
                        <th style="width: 10%;">培训/000考试管理</th>
                        </sec:authorize>
                    </tr>
                </thead>
                
                <tbody>
                		<c:forEach items="${listLabRoomDevice}" var="labRoomDevice" varStatus="i">
                		<tr>
                        <td>${labRoomDevice.schoolDevice.deviceNumber}</td>
                        <td>${labRoomDevice.schoolDevice.deviceName}</td>
                        <%-- <td>${labRoomDevice.CDeviceStatus.name}</td> --%>
                        <td>${labRoomDevice.CDictionaryByDeviceStatus.CName}</td>
                        <td>${labRoomDevice.labRoom.labRoomName}</td>
                        <td>${labRoomDevice.user.cname}</td>
                        <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN,ROLE_TEACHER,ROLE_ASSISTANT,ROLE_STUDENT">
                        <td>
                        	<%-- <c:if test="${labRoomDevice.CActiveByAllowAppointment.id==1}"> --%>
                        	<c:if test="${labRoomDevice.CDictionaryByAllowAppointment.CCategory=='c_active' && labRoomDevice.CDictionaryByAllowAppointment.CNumber=='1'}">
                        	<a href="javascript:void(0)" onclick="Access('${labRoomDevice.id}');">预约</a>
                        	</c:if>
                        	<%-- <c:if test="${labRoomDevice.CDeviceStatus.id == 1&& labRoomDevice.user.username != null }" --%>
                        	<c:if test="${labRoomDevice.CDictionaryByDeviceStatus.CCategory=='c_lab_room_device_status' && labRoomDevice.CDictionaryByDeviceStatus.CNumber=='1'&& labRoomDevice.user.username != null}">
                        	<a  href="${pageContext.request.contextPath}/device/deviceLendApply?idKey=${labRoomDevice.id}"> 出借</a>
                        	</c:if>
                        	<c:if test="${labRoomDevice.user.username != null  }">
                        	<a  href="${pageContext.request.contextPath}/device/newDeviceService?td=${labRoomDevice.id}"> 报修</a>
                        	</c:if>
                        	
                        </td>
                        </sec:authorize>
                        <td>
                        <a href="${pageContext.request.contextPath}/device/showDeviceTraining?id=${labRoomDevice.id}">查看</a>
                        <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN">
                        <a href="${pageContext.request.contextPath}/device/editDeviceReservationInfo?id=${labRoomDevice.id}"  title="修改">编辑</a>
                        <a href="${pageContext.request.contextPath}/device/editDeviceSetting?id=${labRoomDevice.id}">设置</a>
                        </sec:authorize>
                        </td>
                        
                        <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN,ROLE_STUDENT">
                        <td><a href="${pageContext.request.contextPath}/device/findAllTrainingByDeviceId?deviceId=${labRoomDevice.id}"  >培训/考试管理</a></td>
                        </sec:authorize>
                        </tr>
                		</c:forEach>
                       
                </tbody>
            </table>
            
<!-- 分页模块 -->
<div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/device/listLabRoomDevice?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/device/listLabRoomDevice?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/device/listLabRoomDevice?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/device/listLabRoomDevice?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/device/listLabRoomDevice?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/device/listLabRoomDevice?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>
<!-- 分页模块 -->
</div>

</div>
</div>
</div>
</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_Icons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_LeftList.js"></script>
<!--<script type="text/javascript" src="js/is_ControlsDrag.js"></script>-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Downlist.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Allpages.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Interface.js"></script>
<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
						    var config = {
						      '.chzn-select': {search_contains : true},
						      '.chzn-select-deselect'  : {allow_single_deselect:true},
						      '.chzn-select-no-single' : {disable_search_threshold:10},
						      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
						      '.chzn-select-width'     : {width:"95%"}
						    }
						    for (var selector in config) {
						      $(selector).chosen(config[selector]);
						    }
						</script>

<!-- 下拉框的js -->

</body>
</html>


