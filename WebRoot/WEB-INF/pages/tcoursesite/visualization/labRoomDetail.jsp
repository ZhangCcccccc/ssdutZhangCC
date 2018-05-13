<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.lab-resources" />
<html>
<head>
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
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
/**
添加管理员
*/
function addAdmin(typeId){
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/labRoom/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&roomId="+${labRoom.id}+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	           		document.getElementById("user_body").innerHTML=data;
	           }
	});
	document.getElementById("adminType").value=typeId;
    $("#addAdmin").show();
    $("#addAdmin").window('open');   
    
 }

/**
添加实验项目
*/
function addOperationItem(){
     $("#addOperationItem").show();
     $("#addOperationItem").window('open');   
    
 }
 function getValue(){
	 var s=[];
     $($("#operation option:selected")).each(function(){
	         s.push(this.value);
     });
 	//alert(s);
 	document.getElementById("operationItem").value=s;
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

/**
添加硬件
*/ 
function addAgent(){
	$("#addAgent").show();
	  var topPos = window.pageYOffset;
      //使得弹出框在屏幕顶端可见
      $('#addAgent').window({left:"100px", top:topPos+"10px"});

    $("#addAgent").window('open');  
}
/**
添加家具
*/ 
function addRecords(){
	$("#addRecords").show();
	var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#addRecords').window({left:"100px", top:topPos+"10px"});
    $("#addRecords").window('open');  
}
/**
刷新权限
*/ 
function refreshPermissions(){
	var roomId=${labRoom.id};
	$.post('${pageContext.request.contextPath}/labRoom/refreshPermissions?roomId='+roomId+'',function(data){  //serialize()序列化
				if(data=="sucess"){
					alert("刷新成功");
				}else{
					alert("刷新失败，请检查网络或者重新刷新。");
				}
				
			 });	
}
/**
门禁
*/ 
function opendoor(){
	var roomId=${labRoom.id};
	$.post('${pageContext.request.contextPath}/labRoom/openDoor?roomId='+roomId+'',function(data){  //serialize()序列化
				if(data=="sucess"){
					alert("门禁已经打开");
				}else{
					alert("开门失败，请检查当网络连接或者再试一次。");
				}
				
			 });
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
//打开视频
function openVideo(roomId,id) {
	$.ajax({
	           url:"${pageContext.request.contextPath}/labRoom/openVideo?roomId="+roomId+"&id="+id,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	           		var type=userBrowser();
	           		//alert(type);
	           		if(data.indexOf("webcu")>=0){//一期
	           			if(type.indexOf("IE")>=0){
	           				window.open(data);
	           			}else{
	           				//confirm("您要查看的视频为一期视频，请在IE浏览器里面打开下面链接："+data);
							window.open(data);
	           			}
	           		}else{
	           			if(type.indexOf("Firefox")>=0){
	           				window.open(data);
	           			}else if(type.indexOf("Chrome")>=0){
	           				window.open(data);
	           			}else{
	           				confirm("请您下载Firefox浏览器") ;
	           				window.open("http://www.firefox.com.cn/download/");
	           			}
	           			
	           		}
	           		/* var con ="<iframe scrolling='yes' id='message' frameborder='0' src='"+data+"' style='width:100%;height:100%;''></iframe>";
	           		//window.open(data);
	           		$("#openVIdeo").html(con);
	           		 $("#openVIdeo").window('open');   */
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
<div class="lest_content " >
		<div class="left_nav">
        	<div class="left_user_box">
                <img src="${pageContext.request.contextPath}/images/index/user.png"/>
            </div>
            <div class="left_nav_box">
                <ul class="left_nav_list">
	                <sec:authorize ifAnyGranted="ROLE_TEACHER">
	                    <li <c:if test="${select eq 'tcourse' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1">选课组管理</a>
	                    </li>
	                </sec:authorize>
	                <sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
	                    <li <c:if test="${select eq 'authority' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listUserAuthority">权限管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'user' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=1">用户管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'term' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listTerm?currpage=1">学期管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'visualization' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/visualization/roomList?page=1">可视化管理</a>
	                    </li>
		            </sec:authorize>
                </ul>
            </div>  
            <div class="power2" style="display: block;">
                Power by <a href="http://www.gvsun.com" target="_blank">Gvsun</a>
            </div>
        </div>
	<div id="TabbedPanels1" class="TabbedPanels" style="float:right;width:80%">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
	<!-- 实验分室信息开始 -->
	<div class="tit-box">
		${labRoom.labRoomName} <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
	</div>
	
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
		   window.location.href="${pageContext.request.contextPath}/tcoursesite/visualization/saveLabRoomDevice?roomId="+${labRoom.id}+"&array="+array; 
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
	<div id="addAdmin" class="easyui-window " title="添加实验室管理员" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 600px; height: 500px;">
		<div class="content-box">
		<form:form id="userForm" method="post"   modelAttribute="admin">
			<table class="tb" id="my_show">
				<tr>
					<td>姓名：<form:input id="cname" path="user.cname"/></td>
					<td>工号：<form:input id="username" path="user.username"/>
						<a onclick="queryUser();" >搜索</a>	
					</td>
					<td>
						<input type="hidden" id="adminType">
						<input type="button" value="添加" onclick="addUser();">
					</td>
				</tr>
			</table>
		</form:form>
		
		<table id="my_show">
					<thead>
						<tr>
							<th style="width:10% !important">选择</th>
							<th style="width:30% !important">姓名</th>
							<th style="width:30% !important">工号</th>
							<th style="width:30% !important">所属学院</th>
							
						</tr>
					</thead>
						
					<tbody id="user_body">
						
					</tbody>
					
			</table>
			</div>
		<script type="text/javascript">
function addUser(){
        var array=new Array();
        var flag; //判断是否一个未选   
        $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为CK_name的 checkbox  
                    if ($(this).attr("checked")) { //判断是否选中    
                        flag = true; //只要有一个被选择 设置为 true  
                    }  
                })  

        if (flag) {  
           $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                        if ($(this).attr("checked")) { //判断是否选中
                            array.push($(this).val()); //将选中的值 添加到 array中 
                        }  
                    })  
           //alert(array);         
           var typeId=document.getElementById("adminType").value;
           //将要所有要添加的数据传给后台处理   
		   window.location.href="${pageContext.request.contextPath}/labRoom/saveLabRoomAdmin?roomId="+${labRoom.id}+"&array="+array+"&typeId="+typeId; 
        } else {   
        	alert("请至少选择一条记录");  
        	}  
    	}

function queryUser(){
	
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/labRoom/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&roomId="+${labRoom.id}+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	});
	  
}

/* //取消查询
function Cancel(){
	document.getElementById("cname").value="";
	document.getElementById("username").value="";
	var cname="";
	var username="";
	$.ajax({
	           url:"${pageContext.request.contextPath}/labRoom/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&roomId="+${labRoom.id}+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	         });
} */

//首页
function firstPage(page){
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/labRoom/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&roomId="+${labRoom.id}+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	});
}
//上一页
function previousPage(page){
	if(page==1){
			page=1;
		}else{
			page=page-1;
		}	
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	          url:"${pageContext.request.contextPath}/labRoom/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&roomId="+${labRoom.id}+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	});
}
//下一页
function nextPage(page,totalPage){
	if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1
		}	
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/labRoom/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&roomId="+${labRoom.id}+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	           }
	});
}
//末页
function lastPage(page){
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/labRoom/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&roomId="+${labRoom.id}+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	});
}
</script>
	</div>
	<!-- 添加实验室管理员结束 -->
	
	
		</div>
		<!-- 仪器设备结束 -->
		</div>
		</div>
		</div>
		</div>
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