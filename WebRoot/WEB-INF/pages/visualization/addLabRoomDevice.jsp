<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.lab-resources" />
<html>
<head>
<meta name="decorator" content="iframe" />
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />

<!-- 下拉的样式结束 -->
<script type="text/javascript">
/**
添加设备
*/
function openwin(){
	var name=document.getElementById("deviceName").value;
	var number=document.getElementById("deviceNumber").value;
	//var maxDeviceNumber=document.getElementById("maxDeviceNumber").value;
	var deviceAddress=document.getElementById("deviceAddress").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/labRoom/findSchoolDeviceByNameAndNumber?name="+name+"&number="+number+"&deviceAddress="+deviceAddress+"&page=1",
	           
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("body").innerHTML=data;
	            
	           }
	});
     $("#openwindow").show();
     $("#openwindow").window('open');   
    
 }

//添加设备信息
function setDeviceInfo(id,deviceId){
	//alert(id+"------"+deviceId);
	document.getElementById("labRoomDeviceId").value=id;
	document.getElementById("deviceId").value=deviceId;
	$("#set").show();
	 var topPos = window.pageYOffset;
     //使得弹出框在屏幕顶端可见
     $('#set').window({left:"100px", top:topPos+"10px"});
     $("#set").window('open');   
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






function userBrowser(){  
    var browserName=navigator.userAgent.toLowerCase();  
    if(/msie/i.test(browserName) && !/opera/.test(browserName)){  
        return "IE";  
    }else if(/firefox/i.test(browserName)){  
        return "Firefox";  
    }else if(/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName)){  
        return "Chrome";  
    }else if(/opera/i.test(browserName)){  
        return "Opera";  
    }else if(/webkit/i.test(browserName) &&!(/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName))){
        return "Safari";  
    }else{  
        alert("unKnow");  
    }  
} 
//测试是否为ip地址
function testIsIp(){
	var hardwareIp=$("#hardwareIp").val();
	var reSpaceCheck = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
    if (reSpaceCheck.test(hardwareIp)) {
    	hardwareIp.match(reSpaceCheck);
        if (RegExp.$1 <= 255 && RegExp.$1 >= 0 
        		&& RegExp.$2 <= 255 && RegExp.$2 >= 0 
        		&& RegExp.$3 <= 255 && RegExp.$3 >= 0 
        		&& RegExp.$4 <= 255 && RegExp.$4 >= 0) {
        	//do nothing
        }
        else {
        	alert("您输入的ip地址不合法")
        }
    }
    else {
    	alert("您输入的ip地址不合法")
    }
}
</script>

<style>
	td{word-wrap:break-word}
</style>
</head>
<body>
	<div class="navigation">
		<div id="navigation">
			<ul>
				<li><a href="javascript:void(0)">实验室及预约管理</a>
				</li>
				<li class="end"><a href="javascript:void(0)">实验室管理</a>
				</li>
				<li class="end"><a href="javascript:void(0)">实验分室管理</a>
				</li>
				<li class="end"><a href="javascript:void(0)">实验分室信息</a>
				</li>
			</ul>
		</div>
	</div>
	<!-- 实验分室信息开始 -->
	<div class="tit-box">
		${labRoom.labRoomName} <a class="btn btn-new" href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=9">返回实验室管理页</a>
	</div>
	<!-- 左侧栏目开始 -->
	<div class="edit-left">
		<!-- 实验室详情开始 -->
		<div class="edit-content-box">
			<div class="title">
				<div id="title">实验室详情</div>
				<!-- <a class="btn btn-edit">修改</a> -->
			</div>
			<div class="edit-content">
				<table>
					<tr>
						<th>实验室名称</th>
						<td>${labRoom.labRoomName}</td>
						<th>实验室编号</th>
						<td>${labRoom.labRoomNumber}</td>
						<th>实验室地点</th>
						<td>${labRoom.labRoomAddress}</td>
						<th>实验室英文名称</th>
						<td>${labRoom.labRoomEnName}</td>
					</tr>
					<tr>
						<th>实验室简称</th>
						<td>${labRoom.labRoonAbbreviation}</td>
						<%--<th>实验室类别</th>
						<td>${labRoom.CDictionaryByLabRoom.CName}</td>
						--%><th>使用面积</th>
						<td>${labRoom.labRoomArea}</td>
						<th>实验室容量</th>
						<td>${labRoom.labRoomCapacity}</td>
					</tr>
					<tr>
						<%--<th>联系方式</th>
						<td></td>
						--%>
						<th>是否可用</th>
						<c:if test="${labRoom.labRoomActive==1}">
						<td>可用</td>
						</c:if>
						<c:if test="${labRoom.labRoomActive==0}">
						<td>不可用</td>
						</c:if>
						<th>是否可预约</th>
						<c:if test="${labRoom.labRoomReservation==0}">
						<td>不可预约</td>
						</c:if>
						<c:if test="${labRoom.labRoomReservation==1}">
						<td>可预约</td>
						</c:if>
						<th></th>
						<td></td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>实验室描述</th>
						<td colspan="6">${labRoom.labRoomIntroduction}</td>
					</tr>
					<tr>
						<th>实验室注意事项</th>
						<td colspan="6">${labRoom.labRoomRegulations}</td>
					</tr>
					<tr>
						<th>获奖信息</th>
						<td colspan="6">${labRoom.labRoomPrizeInformation}</td>
					</tr>
				</table>
			</div>
			</div>
		<!-- 实验室详情结束 -->

		<!-- 实验项目开始 -->
		<div class="edit-content-box">
			<div class="title">
				<div id="title">实验项目</div>
				<c:if test="${flag==true}">
				<a class="btn btn-new" href="javascript:void(0)" onclick="addOperationItem();">添加实验项目</a>
				</c:if>
			</div>
			<div class="edit-content">
					<table class="tb" id="my_show">
						<thead>
							<tr>
								<th width="20%">实验项目卡名称</th>
								<th width="20%">实验项目卡编号</th>
								<th width="20%">实验设计者</th>
								<c:if test="${flag==true}">
								<th width="5%">操作</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${labRoom.operationItems}" var="m">
						<tr align="center">
							<td>${m.lpName}</td>
							<td>${m.lpCodeCustom}</td>
							<td>${m.userByLpCreateUser.cname}</td>
							<c:if test="${flag==true}">
							<td><a href="${pageContext.request.contextPath}/labRoom/deleteLabRoomOperationItem?roomId=${labRoom.id}&id=${m.id}" onclick="return confirm('确认删除吗？')">删除 </a></td>
							</c:if>
						</tr>
						</c:forEach>
						</tbody>
					</table>
			</div>
			<!-- 添加实验项目 -->
	<div id="addOperationItem" class="easyui-window " title="添加实验项目" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 500px; height: 300px;">
			<form action="${pageContext.request.contextPath}/labRoom/saveLabRoomOperationItem?roomId=${labRoom.id}" method="post">
			<table>
			<tr><td>实验项目卡名称</td></tr>
			<tr>
				<td>
				<input type="hidden" id="operationItem" name="operationItem">
				<select id="operation" name="operation" class="chzn-select" multiple="multiple">
				<c:forEach items="${items}" var="m">
				<option value="${m.id}">${m.lpName}(${m.lpCodeCustom})</option>
				</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="提交" onclick="getValue();"> </td>
				<td><input type="button" value="取消"> </td>
			</tr>
			</table>
			</form>
		</div>
		</div>
		<!-- 实验项目结束 -->
		<!-- 家具开始 -->
		<div class="edit-content-box">
			<div class="title">
				<div id="title">家具</div>
				<c:if test="${flag==true}">
				<a class="btn btn-new" onclick="addRecords();">添加家具</a>
				</c:if>
			</div>
			<div class="edit-content">
				<table class="tb" id="my_show">
					<thead>
						<tr>
							<th width="5%">序号</th>
							<th width="20%">家具名称</th>
							<th width="20%">家具编号</th>
							<c:if test="${flag==true}">
							<th width="20%">操作</th>
							</c:if>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${labRoomFurniture}" var="current" varStatus="i">
							<tr align="center">
								<td>${i.count}</td>
								<td>${current.furnitureName}</td>
								<td>${current.furnitureNo}</td>
								<c:if test="${flag==true}">
								<td><a onclick="return confirm('确认要删除吗？')" href="${pageContext.request.contextPath}/labRoom/deleteLabRoomFurniture?i=${current.id}">删除</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 添加家具开始 -->
		<div id="addRecords" class="easyui-window " title="添加家具" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 300px; height: 150px;">
		</div>
		<!-- 添加家具结束 -->
		<!-- 家具结束 -->
	
		
	</div>
	<!-- 左侧栏目结束 -->


	<!-- 右侧栏目开始 -->
	<div class="edit-right">
			<!-- 物联硬件开始 -->
		<c:if test="${flag==true}">
		<div class="edit-content-box">
			<div class="title">
				<div id="title">物联硬件</div>
				<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
				<a class="btn btn-new" onclick="addAgent();">添加硬件</a>
				</sec:authorize>
			</div>
			<div class="edit-content">
				<table class="tb" id="my_show"style="table-layout:fixed">
					<thead>
						<tr>
							<th>硬件名称</th>
							<th>IP</th>
							<th style="width:10%">端口</th>
							<th>PageCam</th>
							<th>服务器</th>
							<c:if test="${flag }">
							<th>远程</th>
							<th>操作</th>
							</c:if>
						</tr>
					</thead>
					<c:forEach items="${agentList}" var="agent" varStatus="i">
						<tr align="center">
							<td>
								<c:if test="${agent.CDictionary.id==548}"><!-- 门禁 -->
									<c:if test="${flag }">
										<a href="${pageContext.request.contextPath}/labRoom/entranceList?id=${agent.id}&page=1" >${agent.CDictionary.CName}</a>
									</c:if>
									<c:if test="${!flag }">
										${agent.CDictionary.CName}
									</c:if>
								</c:if>
								
								<c:if test="${agent.CDictionary.id!=548}">
									${agent.CDictionary.CName}
								</c:if>
							</td>
							<td>${agent.hardwareIp}</td>
							<td>${agent.hardwarePort}</td>
							<td>${agent.hardwareRemark}</td>
							<td>${agent.commonServer.serverName}</td>
							<c:if test="${flag }">
							<td>
								<c:choose>
						            <c:when test="${agent.CDictionary.id  eq 548}">
						            	<a href="javascript:void(0)" onclick="opendoor();">开门</a>
						            </c:when>
						            <c:when test="${agent.CDictionary.id  eq 549}">
						            	<a href="javascript:void(0)" onclick="openVideo('${labRoom.id}','${agent.id}');">开视频</a>
						            </c:when>
						            <c:otherwise>未定义</c:otherwise>
								</c:choose> 
							</td>
							<td><a href="${pageContext.request.contextPath}/labRoom/updateLabRoomAgent?id=${agent.id}">修改</a> 
							<a onclick="return confirm('确认要删除吗？')" href="${pageContext.request.contextPath}/labRoom/deleteLabRoomAgent?id=${agent.id}">删除</a>
							</c:if>
							</td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>
		<!-- 物联硬件结束 -->
		
		<!-- 物联管理员 -->
		<div class="edit-content-box">
			<div class="title">
				<div id="title">物联管理员</div>
				<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
				<a class="btn btn-new" href="javascript:void(0)" onclick="addAdmin(2);">添加物联管理员</a>
				</sec:authorize>
			</div>
			<div class="edit-content">
				<table class="tb" id="my_show">
					<thead>
						<tr>
							<th>姓名</th>
							<th>工号</th>
							<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
							<th>操作</th>
							</sec:authorize>
						</tr>
					</thead>
						<c:forEach items="${agentAdmin}" var="admin">
						<tr align="center">
							<td>${admin.user.cname}</td>
							<td>${admin.user.username}</td>
							<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
							<td><a href="${pageContext.request.contextPath}/labRoom/deleteLabRoomAdmin?id=${admin.id}" onclick="return confirm('你确定删除吗？')">删除</a></td>
							</sec:authorize>
						</tr>
						</c:forEach>
				</table>
			</div>
		</div>
		<!-- 物联管理员结束 -->
		</c:if>
		
		
		<!-- 仪器设备开始 -->
		<div class="edit-content-box">
			<div class="title">
				<div id="title">仪器设备</div>
				<c:if test="${flag==true}">
				<a class="btn btn-new" onclick="openwin() ">添加设备</a>
				</c:if>
			</div>
			<div class="edit-content">
				<table class="tb" id="my_show">
					<thead>
						<tr>
							<th width="5%">序号</th>
							<th width="20%">设备名称</th>
							<th width="20%">设备编号</th>
							<c:if test="${flag==true}">
							<th width="20%">操作</th>
							
							 
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${labDeviceList}" var="labDevice" varStatus="i">
							<tr align="center">
								<td>${i.count}</td>
								<td>${labDevice.schoolDevice.deviceName}</td>
								<td>${labDevice.schoolDevice.deviceNumber}</td>
								<td>
								<!-- 预约的权限未配置，待确定 -->
								<%-- <c:if test="${labDevice.CActiveByAllowAppointment.id==1}">
								<a href="javascript:void(0)" onclick="Access('${labDevice.id}');">预约</a>&nbsp;&nbsp; 
								</c:if> --%>
								<c:if test="${flag==true}">
								<a href="${pageContext.request.contextPath}/labRoom/deleteLabRoomDeviceNew?labDeviceId=${labDevice.id}" onclick="return confirm('你确定删除吗？')">删除</a>
								<%-- <a href="javascript:void(0)" onclick="setDeviceInfo('${labDevice.id}','${labDevice.schoolDevice.deviceNumber}');">设置</a> --%>
								</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			
			<div id="openwindow" class="easyui-window " title="添加设备" align="left" title=""  maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 800px; height: 500px;">
			<div class="content-box">
			<form:form id="queryForm" method="post" modelAttribute="schoolDevice">
			<table>
			<tr>
				<td>
				设备名称:
				<form:input id="deviceName" path="deviceName"/>
				</td>
				<td>
				设备编号:
				<input type="text" id="deviceNumber" placeholder="请输入数字"   maxlength="40" validType="length[0,9]"/>
				</td>
				<%--<td>
				到:
				<input type="text" id="maxDeviceNumber" placeholder="请输入数字" class="easyui-numberbox"  maxlength="40" validType="length[0,9]">
				</td>
				--%><td>
				设备地点:
				<form:input id="deviceAddress" path="deviceAddress"/>
				</td>
				
				<td>
				<input type="button" value="取消" onclick="window.history.go(0)">
				</td>
				<td>
				<input type="button" value="搜索" onclick="querySchoolDevice();">
				</td>
				<td>
				<input type="button" value="添加" onclick="sss();"> 
				</td>
			</tr>
			
			</table>
			</form:form>
<script type="text/javascript">
//增加全选功能
function checkAll()
  {
    if($("#check_all").attr("checked"))
    {
      $(":checkbox").attr("checked", true);
    }
    else
    {
      $(":checkbox").attr("checked", false);
    }
  }

function sss(){
        var array=new Array();
        var flag; //判断是否一个未选   
        $("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                    if ($(this).attr("checked")) { //判断是否选中    
                        flag = true; //只要有一个被选择 设置为 true  
                    }  
                })  

        if (flag) {  
           $("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                        if ($(this).attr("checked")) { //判断是否选中
                            array.push($(this).val()); //将选中的值 添加到 array中 
                        }  
                    })  
           //alert(array);         
           //将要所有要添加的数据传给后台处理   
		   window.location.href="${pageContext.request.contextPath}/labRoom/saveLabRoomDevice?roomId="+${labRoom.id}+"&array="+array; 
        } else {   
        	alert("请至少选择一条记录");  
        	}  
    	}

function querySchoolDevice(){
	
	var name=document.getElementById("deviceName").value;
	var number=document.getElementById("deviceNumber").value;
	//var maxDeviceNumber=document.getElementById("maxDeviceNumber").value;
	var deviceAddress=document.getElementById("deviceAddress").value;
	$.ajax({
			   url:"${pageContext.request.contextPath}/labRoom/findSchoolDeviceByNameAndNumber?name="+name+"&number="+number+"&deviceAddress="+deviceAddress+"&page=1",
	           
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("body").innerHTML=data;
	            
	           }
	});
	  
}

//取消查询
function cancelQuery(){
	document.getElementById("deviceName").value="";
	document.getElementById("deviceNumber").value="";
	//document.getElementById("maxDeviceNumber").value="";
	document.getElementById("deviceAddress").value="";
	var name="";
	var number="";
	var deviceAddress="";
	//var maxDeviceNumber="";
	$.ajax({
		       url:"${pageContext.request.contextPath}/labRoom/findSchoolDeviceByNameAndNumber?name="+name+"&number="+number+"&deviceAddress="+deviceAddress+"&page=1",
	           
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("body").innerHTML=data;
	            
	           }
	         });
}
//首页
function first(page){
	var name=document.getElementById("deviceName").value;
	var number=document.getElementById("deviceNumber").value;
	//var maxDeviceNumber=document.getElementById("maxDeviceNumber").value;
	var deviceAddress=document.getElementById("deviceAddress").value;
	$.ajax({
		       url:"${pageContext.request.contextPath}/labRoom/findSchoolDeviceByNameAndNumber?name="+name+"&number="+number+"&deviceAddress="+deviceAddress+"&page=1",
	           
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("body").innerHTML=data;
	            
	           }
	});
}
//上一页
function previous(page){
	if(page==1){
			page=1;
		}else{
			page=page-1;
		}
	var name=document.getElementById("deviceName").value;
	var number=document.getElementById("deviceNumber").value;
	//var maxDeviceNumber=document.getElementById("maxDeviceNumber").value;
	var deviceAddress=document.getElementById("deviceAddress").value;
	$.ajax({
		       url:"${pageContext.request.contextPath}/labRoom/findSchoolDeviceByNameAndNumber?name="+name+"&number="+number+"&deviceAddress="+deviceAddress+"&page=1",
	           
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("body").innerHTML=data;
	            
	           }
	});
}
//下一页
function next(page,totalPage){
	if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1
		}
	var name=document.getElementById("deviceName").value;
	var number=document.getElementById("deviceNumber").value;
	//var maxDeviceNumber=document.getElementById("maxDeviceNumber").value;
	var deviceAddress=document.getElementById("deviceAddress").value;
	$.ajax({
		       url:"${pageContext.request.contextPath}/labRoom/findSchoolDeviceByNameAndNumber?name="+name+"&number="+number+"&deviceAddress="+deviceAddress+"&page=1",
	           
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("body").innerHTML=data;
	            
	           }
	});
}
//末页
function last(page){
	var name=document.getElementById("deviceName").value;
	var number=document.getElementById("deviceNumber").value;
	//var maxDeviceNumber=document.getElementById("maxDeviceNumber").value;
	var deviceAddress=document.getElementById("deviceAddress").value;
	$.ajax({
		       url:"${pageContext.request.contextPath}/labRoom/findSchoolDeviceByNameAndNumber?name="+name+"&number="+number+"&deviceAddress="+deviceAddress+"&page=1",
	           
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("body").innerHTML=data;
	            
	           }
	});
}
</script>
			<table class="eq" id="my_show">
					<thead>
						<tr>
							<th style="width:10% !important">设备编号</th>
							<th style="width:15% !important">设备名称</th>
							<th style="width:20% !important">设备型号</th>
							<%--<th style="width:10% !important">保管员</th>--%>
							<th style="width:10% !important">设备规格</th>
							<th style="width:10% !important">设备价格</th>
							<th style="width:20% !important">设备地点</th>
							<%--<th style="width:4% !important">选择</th>	--%>
							<th><input id="check_all" type="checkbox" onclick="checkAll();"/></th>
						
						</tr>
					</thead>
						
					<tbody id="body">
						
					</tbody>
					
			</table>
			</div>
				
				
			</div>
			<!-- 添加设备页面 结束-->
			<!-- 添加管理员 -->
	<!-- 添加实验室管理员结束 -->
	
	
		</div>
		<!-- 仪器设备结束 -->
		<!-- 实验室管理者开始 -->
		<div class="edit-content-box">
			<div class="title">
				<div id="title">
					实验室管理员
				</div>
				<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
				<c:if test="${not empty Access}">
				<a class="btn btn-new" onclick="refreshPermissions();"  href="javascript:void(0)">刷新权限</a>
				</c:if>
				<a class="btn btn-new" onclick="addAdmin(1) ">添加实验室管理员</a>
				</sec:authorize>
			</div>
			<div class="edit-content">
				<table class="tb" id="my_show">
					<thead>
						<tr>
							<th width="20%">姓名</th>
							<th width="20%">工号</th>
							<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
							<th width="5%">操作</th>
							</sec:authorize>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${adminList}" var="admin">
							<tr align="center">
								<td>${admin.user.cname}</td>
								<td>${admin.user.username}</td>
								<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
								<td><a href="${pageContext.request.contextPath}/labRoom/deleteLabRoomAdmin?id=${admin.id}" onclick="return confirm('你确定删除吗？')">删除</a></td>
								</sec:authorize>
							</tr>
						</c:forEach>



					</tbody>
				</table>
			</div>
		</div>


		<!-- 实验室管理者结束 -->
		
 	

	</div>
	<!-- 右侧栏目结束 -->

	<script type="text/javascript">
					$(".btn-edit").click(function(){
						$(this).parent().next().slideUp(); //原信息隐藏
						$(this).parent().next().next().children(".edit-edit").slideDown();//修改信息显示
						$(this).hide();//修改按钮隐藏
						$(this).parent().next().next().children(".edit-action").show();	//返回保存按钮显示					
					});
					$(".btn-return").click(function(){
						$(this).parent().hide();//返回保存按钮隐藏
						$(this).parent().parent().prev().prev().children(".btn-edit").show();//修改按钮显示
						$(this).parent().prev().slideUp();//修改信息隐藏
						$(this).parent().parent().prev().slideDown();//原始信息显示

					})
	</script>
	
	<!-- 添加物联硬件 -->
	<div id="openVIdeo" class="easyui-window " title="查看视频" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 1000px; height: 500px;">
		
	</div>
	<!-- 添加物联硬件结束 -->
	<!-- 下拉框的js -->
					<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript" charset="utf-8"></script>
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