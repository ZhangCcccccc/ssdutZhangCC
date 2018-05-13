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
	           			var labRoom_id = $("#labRoom_id").val();
	           		    var schoolDevice_deviceNumber = $("#schoolDevice_deviceNumber").val();
	           		    var schoolDevice_deviceName =$("#schoolDevice_deviceName").val();
	           		    var username = $("#username").val();
	           		    //alert(schoolDevice_deviceName);
	           			if($("#labRoom_id").val()==""){
	           			  labRoom_id ="-1";
	           			}
	           			if($("#schoolDevice_deviceNumber").val()==""){
	           			  schoolDevice_deviceNumber ="-1";
	           			}
	           			if($("#schoolDevice_deviceName").val()==""){
	           			  schoolDevice_deviceName ="-1";
	           			}
	           			if($("#username").val()==""){
	           				username ="-1";
	           			}
	           			
	           			var url = "${pageContext.request.contextPath}/device/doDeviceReservation/" + labRoom_id + "/"+ schoolDevice_deviceNumber + "/" + schoolDevice_deviceName + "/" + username +"/${page}/" + id+"/"+1;
	           			//alert(url);
	           			window.location.href=url;
	           			//alert("您已经通过安全准入验证"+data);
	                  	//window.location.href="${pageContext.request.contextPath}/device/reservationDevice?id="+id;
	           		}else if(data=="error"){
	           			alert("您还未通过培训,请先预约培训!");
	           			//window.location.href="${pageContext.request.contextPath}/device/findAllTrainingByDeviceId?deviceId="+id;
	           		}else{
	           			alert("该设备还未添加设备管理员，暂不能预约，请联系相关人员进行添加！")
	           		}    
	           }
	});
	
}


//参数设置
function deviceParameters(id){
	window.location.href="${pageContext.request.contextPath}/device/deviceParameters?deviceId="+id+"&modelId=338";
 }
//开放设置
function deviceOpenSetting(id){
	window.location.href="${pageContext.request.contextPath}/device/deviceOpenSetting?deviceId="+id+"&modelId=339";
}

function generateDimensionalCode(){
	$.ajax({
	           url:"${pageContext.request.contextPath}/device/generateDimensionalCode",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	           		if(data=="success"){
	           			alert("批量生成二维码完成");
	           		}else{
	           			alert("生成二维码过程出错，请检查网络情况");
	           		}    
	           }
	});
	
} 
</script>

<script>
function openSetupLink(id){
    var labRoom_id = $("#labRoom_id").val();
    var schoolDevice_deviceNumber = $("#schoolDevice_deviceNumber").val();
    var schoolDevice_deviceName =$("#schoolDevice_deviceName").val();
    var username = $("#username").val();
    //alert(schoolDevice_deviceName);
	if($("#labRoom_id").val()==""){
	  labRoom_id ="-1";
	}
	if($("#schoolDevice_deviceNumber").val()==""){
	  schoolDevice_deviceNumber ="-1";
	}
	if($("#schoolDevice_deviceName").val()==""){
	  schoolDevice_deviceName ="-1";
	}
	if($("#username").val()==""){
		username ="-1";
	}
	
	var url = "${pageContext.request.contextPath}/device/editDeviceInfoRest/" + labRoom_id + "/"+ schoolDevice_deviceNumber + "/" + schoolDevice_deviceName + "/" + username +"/${page}/" + id;
	//alert(url);
	window.location.href=url;
}

//培训预约(编辑--针对老师)
function editDeviceTrainingRest(id){
    var labRoom_id = $("#labRoom_id").val();
    var schoolDevice_deviceNumber = $("#schoolDevice_deviceNumber").val();
    var schoolDevice_deviceName =$("#schoolDevice_deviceName").val();
    var username = $("#username").val();
    //alert(schoolDevice_deviceName);
	if($("#labRoom_id").val()==""){
	  labRoom_id ="-1";
	}
	if($("#schoolDevice_deviceNumber").val()==""){
	  schoolDevice_deviceNumber ="-1";
	}
	if($("#schoolDevice_deviceName").val()==""){
	  schoolDevice_deviceName ="-1";
	}
	if($("#username").val()==""){
		username ="-1";
	}
	var url = "${pageContext.request.contextPath}/device/editDeviceTrainingRest/"+ labRoom_id + "/"+ schoolDevice_deviceNumber + "/" + schoolDevice_deviceName + "/" + username +"/${page}/" + id;
	//alert(url);
	window.location.href=url;
}

//培训预约(查看--针对学生)
function viewDeviceTrainingRest(id){
    var labRoom_id = $("#labRoom_id").val();
    var schoolDevice_deviceNumber = $("#schoolDevice_deviceNumber").val();
    var schoolDevice_deviceName =$("#schoolDevice_deviceName").val();
    var username = $("#username").val();
    //alert(schoolDevice_deviceName);
	if($("#labRoom_id").val()==""){
	  labRoom_id ="-1";
	}
	if($("#schoolDevice_deviceNumber").val()==""){
	  schoolDevice_deviceNumber ="-1";
	}
	if($("#schoolDevice_deviceName").val()==""){
	  schoolDevice_deviceName ="-1";
	}
	if($("#username").val()==""){
		username ="-1";
	}
	var url = "${pageContext.request.contextPath}/device/viewDeviceTrainingRest/" + labRoom_id + "/"+ schoolDevice_deviceNumber + "/" + schoolDevice_deviceName + "/" + username +"/${page}/" + id;
	//alert(url);
	window.location.href=url;
}
//查看设备详情（传参版）
function viewDeviceInfoRest(id){
    var labRoom_id = $("#labRoom_id").val();
    var schoolDevice_deviceNumber = $("#schoolDevice_deviceNumber").val();
    var schoolDevice_deviceName =$("#schoolDevice_deviceName").val();
    var username = $("#username").val();
	if($("#labRoom_id").val()==""){
	  labRoom_id ="-1";
	}
	if($("#schoolDevice_deviceNumber").val()==""){
	  schoolDevice_deviceNumber ="-1";
	}
	if($("#schoolDevice_deviceName").val()==""){
	  schoolDevice_deviceName ="-1";
	}
	if($("#username").val()==""){
		username ="-1";
	}
	var url = "${pageContext.request.contextPath}/device/viewDeviceInfoRest/" + labRoom_id + "/"+ schoolDevice_deviceNumber + "/" + schoolDevice_deviceName + "/" + username +"/${page}/" + id;
	//alert(url);
	window.location.href=url;
}

/*
 *查看设备信息
 */
function listDeviceInfo(id) {
	var sessionId = $("#sessionId").val();
	var con = '<iframe scrolling="yes" id="message1" frameborder="0"  src="${pageContext.request.contextPath}/device/viewDeviceInfo?id='
			+ id
			+ '" style="width:100%;height:100%;"></iframe>'
	$('#listDeviceInfo').html(con);
	$("#listDeviceInfo").show();
	//获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#listDeviceInfo').window({left:"px", top:topPos+"px"});
	$('#listDeviceInfo').window('open');
}
</script>
</head>

<body>
    <div >
	    
			
        <div class="tool-box">
            <form:form name="queryForm" action="${pageContext.request.contextPath}/device/listLabRoomDevice?page=1" method="post" modelAttribute="labRoomDevice">
					<table class="list_form">
						<tr>
							<td>实验室：
								<form:select class="chzn-select"  path="labRoom.id" id="labRoom_id">
								<form:option value="${labRoom.id }">${labRoom.labRoomName }</form:option>
								<form:options cssStyle="width:200px;" items="${rooms}" itemLabel="labRoomName" itemValue="id"/>
								</form:select>		    				    				            
							</td>
							<td>设备编号：
								<form:select class="chzn-select"  path="schoolDevice.deviceNumber" id="schoolDevice_deviceNumber">
								<form:option value="">请选择</form:option>
								<form:options cssStyle="width:200px;" items="${listLabRoomDeviceAll}" itemLabel="schoolDevice.deviceNumber" itemValue="schoolDevice.deviceNumber"/>
								</form:select>
							</td> 
				     
							<td>设备名称：
								<form:select class="chzn-select"  path="schoolDevice.deviceName" id="schoolDevice_deviceName">
								<form:option value="">请选择</form:option>
								<c:forEach items="${listLabRoomDeviceAll}" var="curr">
								<form:option cssStyle="width:200px;" value="${curr.schoolDevice.deviceName }">${curr.schoolDevice.deviceName}[${curr.schoolDevice.deviceNumber }]</form:option>
								</c:forEach>
								</form:select>
							</td> 
							<td>设备开放：
								<form:select class="chzn-select"  path="CDictionaryByAllowAppointment.CNumber" id="schoolDevice_allowAppointment">
								<form:option value="">请选择</form:option>
								<form:option cssStyle="width:200px;" value="1">是</form:option>
								<form:option cssStyle="width:200px;" value="2">否</form:option>
								</form:select>
							</td>  
							<td>设备管理员：
								<form:select class="chzn-select"  path="user.username" id="username">
								<form:option value="">请选择</form:option>
								<form:options cssStyle="width:200px;" items="${userMap}"/>
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
            设备管理(只有添加了设备管理员，设备才可以出借) 
            <a class="btn btn-common"  href="javascript:void(0)" onclick="generateDimensionalCode();">生成所有的二维码</a>
            </span> </div>
            <div class="page" >
		        ${totalRecords}条记录,共${pageModel.totalPage}页
		    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/device/listLabRoomDevice?page=1" target="_self">首页</a>			    
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
            <table>
            	<c:forEach items="${listLabRoomDevice}" var="labRoomDevice" varStatus="i">
            	<tr>
                    <td class="equipment_img">
                    <c:set var="count" value="0" />
                    <c:forEach items="${labRoomDevice.commonDocuments}" var="d">
		              <c:if test="${d.type==1}">
		              <c:set var="count" value="${count+1 }" />
			              <c:if test="${count==1}">
			              	<img alt="" src="${pageContext.request.contextPath}/${d.documentUrl}" style="width:100%" onclick="showImage(this);">
			              </c:if>
		              </c:if>
		              </c:forEach>
		              <c:if test="${count==0}">
		              	<img src="${pageContext.request.contextPath}/images/no-img.jpg">
		              </c:if>
                    </td>
                    <td>
                        <div class="equipment_box">
                            <div class="equipment_name">${labRoomDevice.schoolDevice.deviceName}</div>
                            <ul class="equipment_introduce">
                            	<li><span>规格:</span>${labRoomDevice.schoolDevice.devicePattern}</li>
                            	<li><span>主要技术指标:</span>${labRoomDevice.indicators}</li>
                                <li><span>所在实验室:</span>${labRoomDevice.labRoom.labRoomName}</li>
                                <li><span>设备管理员:</span>
                                <c:if test="${not empty labRoomDevice.user }">
                                ${labRoomDevice.user.cname}
                                </c:if>
                                <c:if test="${empty labRoomDevice.user }">
                                <font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未设置设备管理员，暂时不能预约！</font>
                                </c:if>
                                </li>
                                <%-- <li><span>状态:</span>${labRoomDevice.CDeviceStatus.name}</li> --%>
                                <li><span>状态:</span>${labRoomDevice.CDictionaryByDeviceStatus.CName}</li>
                                <li><span>所在地点:</span>${labRoomDevice.labRoom.systemRoom.systemBuild.systemCampus.campusName}
                                ${labRoomDevice.labRoom.systemRoom.systemBuild.buildName}
                                ${labRoomDevice.labRoom.systemRoom.roomName}(${labRoomDevice.labRoom.systemRoom.roomNo})
                                </li>
                            </ul>
                            <div>
                            	<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN,ROLE_TEACHER,ROLE_ASSISTANT,ROLE_STUDENT">
                            	<%-- <c:if test="${(labRoomDevice.CDeviceStatus.id == 1 || empty labRoomDevice.CDeviceStatus) && labRoomDevice.CActiveByAllowAppointment.id==1}"> --%>
                            	<c:if test="${(empty labRoomDevice.CDictionaryByDeviceStatus|| (labRoomDevice.CDictionaryByDeviceStatus.CCategory =='c_lab_room_device_status' && labRoomDevice.CDictionaryByDeviceStatus.CNumber == '1')) && (labRoomDevice.CDictionaryByAllowAppointment.CCategory =='c_active' && labRoomDevice.CDictionaryByAllowAppointment.CNumber == '1')}">
                        		<a class="btn btn-new"  href="javascript:void(0)" onclick="Access('${labRoomDevice.id}');"  >预约</a>
                        		</c:if>
                        		<%-- <c:if test="${labRoomDevice.CDeviceStatus.id == 1 && labRoomDevice.CActiveByAllowLending.id==1 && labRoomDevice.user.username != null }"> --%>
                        		<c:if test="${(labRoomDevice.CDictionaryByDeviceStatus.CCategory =='c_lab_room_device_status' && labRoomDevice.CDictionaryByDeviceStatus.CNumber == '1') && (labRoomDevice.CDictionaryByAllowLending.CCategory =='c_active' && labRoomDevice.CDictionaryByAllowLending.CNumber == '1') && labRoomDevice.user.username != null }">
                        		<a class="btn btn-new"  href="${pageContext.request.contextPath}/device/deviceLendApply?idKey=${labRoomDevice.id}"> 出借</a>
                        		</c:if>
                        		<%-- <c:if test="${labRoomDevice.CDeviceStatus.id == 4 && labRoomDevice.user.username != null  }"> --%>
                        		<c:if test="${(labRoomDevice.CDictionaryByDeviceStatus.CCategory =='c_lab_room_device_status' && labRoomDevice.CDictionaryByDeviceStatus.CNumber == '4') && labRoomDevice.user.username != null  }">
                        		<a class="btn btn-new"   href="${pageContext.request.contextPath}/device/newDeviceService?td=${labRoomDevice.id}"> 报修</a>
                        		</c:if>
                        		</sec:authorize>
                        		
								<a class="btn btn-new" onclick="viewDeviceInfoRest(${labRoomDevice.id})" href="javascript:void(0);"  title="查看">查看</a>
								
                        		<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR">
                        		<a class="btn btn-new" href="javascript:void(0);" onclick="openSetupLink(${labRoomDevice.id})" title="设置">设置</a>
                        		</sec:authorize>
                        		<sec:authorize ifNotGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR">
                        		<sec:authorize ifAnyGranted="ROLE_TEACHER">
                        			<c:set var="allowSet" value="0"></c:set>
                        			<c:if test="${labRoomDevice.user.username eq user.username }"><!-- 设备管理员 -->
                        			<c:set var="allowSet" value="1"></c:set>
	                        		</c:if>
	                        		<%--<c:forEach itmes="${labRoomDevice.labRoom.labRoomAdmins}" var="curr">
	                        			<c:if test="${curr.user.username eq user.username && curr.typeId eq 1}"><!-- 实验室管理员 -->
	                        				<c:set var="allowSet" value="1"></c:set>
	                        			</c:if>
	                        		</c:forEach>--%>
	                        		<c:if test="${allowSet eq 1 }">
	                        			<a class="btn btn-new" href="javascript:void(0);" onclick="openSetupLink(${labRoomDevice.id})" title="设置">设置</a>
	                        		</c:if>
                        		</sec:authorize>
                        		</sec:authorize>
                        		
                        		<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_TEACHER,ROLE_EXPERIMENTALTEACHING,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN,ROLE_STUDENT">
                       			<%-- <c:if test="${labRoomDevice.CActiveByAllowSecurityAccess.id == 1}"> --%>
                       			<c:if test="${(labRoomDevice.CDictionaryByAllowSecurityAccess.CCategory =='c_active' && labRoomDevice.CDictionaryByAllowSecurityAccess.CNumber == '1')}">
                                <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_TEACHER,ROLE_EXPERIMENTALTEACHING,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN">
                                <a class="btn btn-new" href="javascript:void(0);" onclick="editDeviceTrainingRest(${labRoomDevice.id})";>培训预约</a>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_STUDENT">
                                <sec:authorize ifNotGranted="ROLE_SUPERADMIN,ROLE_TEACHER,ROLE_EXPERIMENTALTEACHING,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN">
                                <a class="btn btn-new" href="javascript:void(0);" onclick="viewDeviceTrainingRest(${labRoomDevice.id})";>培训预约</a>
                                </sec:authorize>
                                </sec:authorize>
                                </c:if>
                                </sec:authorize>
                                
                            </div>
                        </div>
                    </td>
                </tr>
            	</c:forEach>
            </table>
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
            
            <script type="text/javascript">
                $("tr:odd").css("background", "#fafafa")
            </script>
        </div>
		
			
			
			

    </div>
    
    <!-- 查看设备信息 -->
	<div id="listDeviceInfo" class="easyui-window" title="查看设备信息" modal="true"	closed="true" iconCls="icon-add" style="width:1000px;height:600px">
	</div>
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


