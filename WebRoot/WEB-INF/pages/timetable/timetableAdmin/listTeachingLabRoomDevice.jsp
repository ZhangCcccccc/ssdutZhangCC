<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/css/iconFont.css">
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
</head>

<div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">排课管理</a></li>
		<li class="end"><a href="javascript:void(0)">查看教学用设备</a></li>
	  </ul>
	</div>
</div>

<div class="iStyle_RightInner">
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
			<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">教学用设备列表
								<c:if test="${appointment.deviceOrLab eq 2 }">
									<font color="red">(不允许教学外设备对外开放，下表为实验室所有的设备)</font>
								</c:if>
								<c:if test="${appointment.deviceOrLab eq 1 }">
									<font color="blue">(允许教学外设备对外开放，下表为教学用的设备)</font>
								</c:if>
							</div>
							<a class="btn btn-new" onClick="addDevice();">添加设备</a>
						</div>
						<table> 
							<thead>
								<tr>
									<th>序号</th>
									<th>设备名称</th>
									<th>所属实验室</th>
									<th>设备管理员</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${deviceList}" var="curr" varStatus="i">
									<tr>
										<td>${i.count }</td>
										<td>${curr.schoolDevice.deviceName }[${curr.schoolDevice.deviceNumber }]</td>
										<td>${curr.labRoom.labRoomName }</td>
										<td>${curr.user.cname }[${curr.user.username }]</td>
									</tr>	
								</c:forEach>	
							</tbody>
						</table>
					<!-- 添加设备管理员 -->
	<div id="addDevice" class="easyui-window " title="添加设备管理员" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 600px; height: 500px;">
		<div class="content-box"> 
		<a class="btn btn-new" onclick="deviceAdd()" >添加</a>
		<table id="my_show">
		<thead>
			<tr>
				<th style="width:10% !important"><input type="checkbox"  onclick="checkAll();"  id="check-all"/></th>
				<th style="width:30% !important">设备名称</th>
				<th style="width:30% !important">所属实验室</th>
				<th style="width:30% !important">设备管理员</th>
				
			</tr>
		</thead>
			
		<tbody id="user_body">
			<c:forEach items="${labRoomDeviceAll}" var="curr" varStatus="i">
				<tr>
					<td><input type="checkbox" value="${curr.id}" name='CK' id='CK'/></td>
					<td>${curr.schoolDevice.deviceName }[${curr.schoolDevice.deviceNumber }]</td>
					<td>${curr.labRoom.labRoomName }</td>
					<td>${curr.user.cname }[${curr.user.username }]</td>
				</tr>	
		</c:forEach>
		</tbody>
					
	</table>
	</div>
</div>	
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
function addDevice( ){  
    $("#addDevice").show();
    $("#addDevice").window('open');   
    
 }
var config = {
	'.chzn-select' : {
		search_contains : true
	},
	'.chzn-select-deselect' : {
		allow_single_deselect : true
	},
	'.chzn-select-no-single' : {
		disable_search_threshold : 10
	},
	'.chzn-select-no-results' : {
		no_results_text : 'Oops, nothing found!'
	},
	'.chzn-select-width' : {
		width : "95%"
	}
}
for ( var selector in config) {
	$(selector).chosen(config[selector]);
}

function deviceAdd(){
	var array=new Array();
	var flag=false; //判断是否一个未选   
	$("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
	            if ($(this).is(':checked')) { //判断是否选中    
	                flag = true; //只要有一个被选择 设置为 true  
	            } 
	        });  
	      if (flag) {  
	         $("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
	                      if ($(this).is(':checked')) { //判断是否选中
	                          array.push($(this).val()); //将选中的值 添加到 array中 
	                      }  
	                  });
	         window.location.href="${pageContext.request.contextPath}/timetable/saveLabRoomDeviceRelated?devices="+array+"&timetableId=${timetableId}&labRoomId=${labRoomId}";
//	         
	      } else {   
	      	alert("请至少选择一条记录");  
	      	}  
	  	}

	//遍历复选框
	function checkAll(){
		var checkBoxAll = document.getElementById("check-all"); 	
	$("input[name='CK']:checkbox").each(function() { //遍历所有的name为CK的 checkbox  
       $(this).attr('checked', checkBoxAll.checked);
     })    
	}
</script>
<!-- 下拉框的js -->
