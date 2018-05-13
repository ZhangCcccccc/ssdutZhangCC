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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script> 
<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<script type="text/javascript"> 
/**
*添加培训
*/		
function openwin(){
     $("#openwindow").show();
     $("#openwindow").window('open');   
}	

/**
*添加不定期培训
*/		
function opennewwin(){
     $("#opennewwindow").show();
     $("#opennewwindow").window('open');   
}
 
</script>


<!-- 添加学生对应js -->
<script type="text/javascript">
function addStudent(page){
	var page=page;
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("usernameStudent").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/device/findStudentByCnameAndUsername?cname="+cname+"&username="+username+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	           		$(user_body).html(data);
	           }
	});
    $("#addStudent").show();
    $("#addStudent").window('open');   
    
 }

function addUser(id){
		var usernameStr="";
        var flag; //判断是否一个未选   
        $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为CK_name的 checkbox  
                    if ($(this).attr("checked")) { //判断是否选中    
                        flag = true; //只要有一个被选择 设置为 true  
                    }  
                })  

        if (flag) {  
           $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                        if ($(this).attr("checked")) { //判断是否选中
                        	usernameStr+=$(this).val()+"S";
                        }  
                    })  
           //将要所有要添加的数据传给后台处理   
		   window.location.href="${pageContext.request.contextPath}/device/saveTrainSigleResultRest/"+ ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}" +"/"+ ${username}+ "/"+${page}+ "/"+ usernameStr + "/" +3+"/"+ id; 
        } else {   
        	alert("请至少选择一条记录");  
        	}  
    	}
//取消查询
function Cancel(){
	document.getElementById("cname").value="";
	document.getElementById("usernameStudent").value="";
	var cname="";
	var username="";
	$.ajax({
	           url:"${pageContext.request.contextPath}/device/findStudentByCnameAndUsername?cname="+cname+"&username="+username+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  $(user_body).html(data);
	            
	           }
	         });
}

//已通过学生名单管理

function managePermitUser(){
	var sessionId=$("#sessionId").val();
	var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/device/managePermitUser?currpage=1&&deviceId=' + ${device.id} + '" style="width:100%;height:100%;"></iframe>'
	$('#managePermitUser').html(con);  
	//获取当前屏幕的绝对位置
	var topPos = window.parent.pageYOffset;
	//使得弹出框在屏幕顶端可见
	$('#managePermitUser').window({left:"0px", top:topPos+"px"});
	$('#managePermitUser').window('open');
}

</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/editDevice.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhulims/device/viewDeivce.js"></script>  
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
		<a href="javascript:void(0);" onclick="editDeviceInfoRest(${device.id})">设备详情</a>
		</li>
		<%-- <c:if test="${device.CActiveByAllowSecurityAccess.id == 1}"> --%>
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceTrainingRest(${device.id})">培训计划</a>
		</li>
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
			<div class="tool-box">
						<form:form action="${pageContext.request.contextPath}/device/findAllTrainingByDeviceId?deviceId=${device.id}" method="post" modelAttribute="train">
						<table>
						<tr>
							<c:if test="${user.username==device.user.username || isEXCENTERDIRECTOR}">
							<td>
								<a class="btn btn-new" href="javascript:void(0);" onclick="opennewwin();">添加不定期培训</a>
								<a class="btn btn-new" href="javascript:void(0);" onclick="openwin();">添加培训</a>
								<a class="btn btn-new" target="_blank" href="${pageContext.request.contextPath }/device/managePermitUser?deviceId=${device.id}&currpage=1">培训名单管理</a>
								<%--<input type="button" value="添加培训" onclick="openwin();">
								<input type="button" value="培训学生名单管理" onclick="managePermitUser();">--%>
							</td>
							</c:if>
						</tr>
					
						</table>
						</form:form>
					
				</div>
			<div class="content-box">
				<div class="title">
				<div id="title">设备培训</div>
				<a class="btn btn-new"  herf="#" onclick="openSetupLink();">返回</a>
				<%--<a class="btn btn-new" herf="#" onclick="addStudent(1);">添加学生</a>
				--%></div>
						     <div class="content-double">
					<table >
						<thead>
							<tr>
								<th width="20%">培训内容</th>
								<th>培训地点</th>
								<th>培训时间</th>
								<sec:authorize ifAnyGranted="ROLE_EQUIPMENTADMIN,ROLE_SUPERADMIN"> 
								<th>修改时间</th>
								</sec:authorize>
								<th>主讲教师</th>
								
								<sec:authorize ifNotGranted="ROLE_STUDENT"> 
								<th>培训人数</th>
								</sec:authorize>
								<th>学期</th>
								<th>状态</th>
								
								<th>通过率</th>
								<c:if test="${t.user.username==user.username || isEXCENTERDIRECTOR}">
								<th>操作</th>
								</c:if>
							</tr>
						</thead>
						<c:forEach items="${trainList}" var="t">
						<tr>
							<td><p>${t.content}</p></td>
							<td>${t.address}</td>
							<td><fmt:formatDate value="${t.time.time}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
							<sec:authorize ifAnyGranted="ROLE_EQUIPMENTADMIN,ROLE_SUPERADMIN"> 
							<td>
							<%-- <c:if test="${t.CTrainingStatus.id ne 4 }"> --%>
							<c:if test="${t.CTrainingStatus.CCategory=='c_training_status'&&t.CTrainingStatus.CNumber!='4' }">
						    	<a class="changeTime" onclick="moveId(${t.id })">修改时间</a>
						    </c:if>	
						    </td>
						    </sec:authorize>
							<td>${t.user.cname}</td>
							<sec:authorize ifNotGranted="ROLE_STUDENT"> 
							<td>${t.joinNumber}/${t.number}</td>
							</sec:authorize>
							<td>${t.schoolTerm.termName}</td>
							<%-- <td>${t.CTrainingStatus.name}</td> --%>
							<td>${t.CTrainingStatus.CName}</td>
							<td>
							<c:if test="${not empty t.passRate}">${t.passRate }</c:if>
							<c:if test="${empty t.passRate}">未有结果</c:if>
							</td>
							<td>
							<c:if test="${(t.user.username==user.username || isEXCENTERDIRECTOR)}">
							<%-- <c:if test="${t.joinNumber eq 0 && t.CTrainingStatus.id eq 1}"> --%>
							<c:if test="${t.joinNumber eq 0 && t.CTrainingStatus.CCategory=='c_training_status'&&t.CTrainingStatus.CNumber!='1'}">
								<a href="javaScript:deleteTrainingRest(${t.id})" onclick="return confirm('确定删除？')";>删除</a>  <!-- 没有学生预约时可以删除  -->
							</c:if>
							<%-- <c:if test="${t.CTrainingStatus.id eq 1}"> --%>
							<c:if test="${t.CTrainingStatus.CCategory=='c_training_status'&&t.CTrainingStatus.CNumber!='1' }">
								<a href="javascript:void(0);" onclick="cancelTrainingRest(${t.id})";>取消培训</a>
							</c:if>
							<%-- <c:if test="${t.joinNumber ne 0 && t.CTrainingStatus.id ne 4}"> --%>
							<c:if test="${t.joinNumber ne 0 && t.CTrainingStatus.CCategory=='c_training_status'&&t.CTrainingStatus.CNumber!='4'}">
								<a href="javascript:void(0);" onclick="findTrainingPeopleByTrainIdRest(${t.id},1)";>结果录入</a>
							</c:if>
							<c:if test="${t.joinNumber ne 0}">
								<a href="javascript:void(0);" onclick="findTrainingPeopleByTrainIdRest(${t.id},2)";>学生名单</a>
							</c:if>
							</c:if>
							
							<c:if test="${empty t.time && user.username eq t.user.username }">
							 	<a class="addTimeAndAddress" onclick="moveId(${t.id })">选择培训时间及地点</a>
							</c:if>
							</td>
						</tr>
						</c:forEach>
						
						
						
					</table>
				</div>
						     	
				<!-- 添加培训的层 -->
					<div id="openwindow" class="easyui-window " title="添加培训" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 500px; height: 300px;">
						<form:form action="${pageContext.request.contextPath}/device/saveLabRoomDeviceTraining?deviceId=${device.id}" method="post" modelAttribute="train">
							
							<div class="content-box">
							<table>
								<tr>
									<td>培训时间：</td>
									
									<td> <input id="time" class="Wdate" type="text" name="time" 
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',  minDate:'now()'})" style="width:140px;" 
											value="<fmt:formatDate value="${train.time.time}" 
											pattern="yyyy-MM-dd HH:mm:ss" />" readonly />
									</td>
								</tr>
								<tr>
									<td>培训地点：</td>
									<td>
									<form:input id="address" path="address"/>
									</td>
								</tr>
								<tr>
									<td>培训内容：</td>
									<td>
									<form:textarea id="content" path="content"/>
									</td>
								</tr>
								<tr>
									<td>培训教师：</td>
									<td>
									<form:select id="usernameTeacher" path="user.username" class="chzn-select">
									<c:if test="${device.user.username ne user.username}">
										<form:option value="${user.username }">[${user.username}]${user.cname}</form:option>
									</c:if>
									<form:option value="${device.user.username }">[${device.user.username}]${device.user.cname}</form:option>
									<c:forEach items="${users}" var="t">
             							<form:option value="${t.username}">[${t.username}]${t.cname}</form:option>
             						</c:forEach>
									</form:select>
									</td>
								</tr>
								<tr>
									<td>培训人数：</td>
									<td>
									<form:input id="number" path="number"/>
									</td>
								</tr>

								

								<tr>
									<td colspan='2'>
									<input type="button" onclick="saveLabRoomDeviceTrainingRest(${device.id})" value="提交"/>
									<input type="button" value="取消"/>
									</td>
								</tr>

							</table>
							</div>
						
						</form:form>
					</div>
					<!-- 添加培训的层结束 -->	
      	
      	<!-- 添加不定期培训的层 -->
					<div id="opennewwindow" class="easyui-window " title="添加不定期培训" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 500px; height: 300px;">
						<form:form action="${pageContext.request.contextPath}/device/saveNewLabRoomDeviceTrainingNew?deviceId=${device.id}" method="post" modelAttribute="train">
							
							<div class="content-box">
							<table>
								<tr>
									<td>培训内容：</td>
									<td>
									<form:textarea id="contentNew" path="content"/>
									</td>
								</tr>
								<tr>
									<td>培训教师：</td>
									<td>
									<form:select id="usernameTeacherNew" path="user.username" class="chzn-select">
									<c:if test="${device.user.username ne user.username}">
										<form:option value="${user.username }">[${user.username}]${user.cname}</form:option>
									</c:if>
									<form:option value="${device.user.username }">[${device.user.username}]${device.user.cname}</form:option>
									<c:forEach items="${users}" var="t">
             							<form:option value="${t.username}">[${t.username}]${t.cname}</form:option>
             						</c:forEach>
									</form:select>
									</td>
								</tr>
								<tr>
									<td>培训人数：</td>
									<td>
									<form:input id="numberNew" path="number"/>
									</td>
								</tr>
								<tr>
									<td colspan='2'>
									<input type="button" onclick="saveLabRoomDeviceTrainingRestNew(${device.id})" value="提交"/>
									<input type="button" value="取消"/>
									</td>
								</tr>

							</table>
							</div>
						
						</form:form>
					</div>
					<!-- 添加培训的层结束 -->	
			</div>
		</div>
	
	  </div>
	</div>
	
	<!-- 添加学生 -->
	
	<div id="addStudent" class="easyui-window " title="添加培训人员" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 600px; height: 500px;">
		<div class="content-box">
		<form:form id="userForm" method="post"   modelAttribute="student">
			<table class="tb" id="my_show">
				<tr>
					<td>姓名：<form:input id="cname" path="user.cname"/></td>
					<td>工号：<form:input id="usernameStudent" path="user.username"/>
						
					
						<a onclick="addStudent(1);" >搜索</a>	
						<a onclick="Cancel();" >取消搜索</a>	
						
						
					</td>
					<td>
						
						<input type="button" value="添加" onclick="addUser(${device.id});">
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
		</div>
			<input type="hidden" id="labRoomId" value="${labRoomId }">
			<input type="hidden" id="deviceName" value="${deviceName }">
			<input type="hidden" id="deviceNumber" value="${deviceNumber }">
			<input type="hidden" id="username" value="${username }">
			<input type="hidden" id="page" value="${page }">
			<input type="hidden" id="schoolDevice_allowAppointment" value="${schoolDevice_allowAppointment}">
			<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
</div>

<!-- 已通过学生管理-->
<div id="managePermitUser" class="easyui-window" title="已通过学生管理" closed="true"  modal="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

<form:form name="auditform" action="" method="post">
    <div class="edit-edit">
        <table>
            <tr>
                <td>培训开始日期:</td>
                <td> <input id="begintime" class="Wdate" type="text" name="begintime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',  minDate:'now()'})" style="width:150px;" value="<fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly /> </td>
            </tr>
        </table>
		<input class="btn" id="save" type="button" onclick="info()" value="保存" />
		<input class="btn btn-return" id="save" type="button" value="返回" /> 
    </div>
</form:form>
<form:form name="auditformNew" action="" method="post">
    <div class="edit-editNew box_edit" style="display:none;">
        <table>
            <tr>
                <td style="padding:10px 0 0 0;">培训开始日期:</td>
                <td style="padding:10px 0 0 0;"> <input id="begintimeNew" class="Wdate" type="text" name="begintime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',  minDate:'now()'})" style="width:150px;" value="<fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly /> </td>
            </tr>
            <tr>
                <td style="padding:10px 0 0 0;">培训地点:</td>
                <td style="padding:10px 0 0 0;"> <input id="addressNew"   required="true"/> </td>
            </tr>
            <tr>
            <td colspan="2" style="padding:20px 0 0 0;">
		<input class="btn" id="save" type="button" onclick="saveTimeAndAddress()" value="保存" />
		<input class="btn btn-returnNew" id="save" type="button" value="返回" />
		</td>
		</tr> 
        </table>
    </div>
</form:form>
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
		<script type="text/javascript">
				$(".changeTime").click(function(){
					//$(".btn-edit").slideUp(); //原信息隐藏
					$(this).hide();//修改按钮隐藏
					$(".edit-edit").slideDown();//修改信息显示
				});
				$(".btn-return").click(function(){
					$(".edit-edit").slideUp();
					$(".changeTime").slideDown();//修改信息显示
					//window.location.reload();//刷新页面
				});
				
				$(".addTimeAndAddress").click(function(){
					//$(".btn-edit").slideUp(); //原信息隐藏 
					$(".edit-editNew").slideDown();//修改信息显示
				});
				$(".btn-returnNew").click(function(){
					$(".edit-editNew").slideUp(); 
				});
				
				//保存培训信息
				function saveLabRoomDeviceTrainingRest(id){//将labRoomId deviceNumber deviceName page传递到后台 
					var labRoomId = $("#labRoomId").val();
					var deviceName = $("#deviceName").val();
					var deviceNumber = $("#deviceNumber").val();
					var username = $("#username").val();
					var page = $("#page").val();
					var time = $("#time").val();
					var address = $("#address").val();
					var content = $("#content").val();
					var usernameTeacher = $("#usernameTeacher").val();
					var number = $("#number").val();
					var schoolDevice_deviceName =$("#schoolDevice_deviceName").val(); 
					var schoolDevice_allowAppointment = $("#schoolDevice_allowAppointment").val();
					var url = $("#pageContext").val()+"/device/saveLabRoomDeviceTrainingRest/" + labRoomId + "/"+ deviceNumber + "/" + deviceName + "/"+username+"/"+page+ "/"+ time
					+ "/"+ address+ "/"+ content+ "/"+ usernameTeacher+ "/"+ number+"/" + id+"/"+schoolDevice_allowAppointment;
					window.location.href=url;
				}
				
				function saveLabRoomDeviceTrainingRestNew(id){//将labRoomId deviceNumber deviceName page传递到后台 
					var labRoomId = $("#labRoomId").val();
					var deviceName = $("#deviceName").val();
					var deviceNumber = $("#deviceNumber").val();
					var username = $("#username").val();
					var page = $("#page").val(); 
					var content = $("#contentNew").val();
					var usernameTeacher = $("#usernameTeacherNew").val();
					var number = $("#numberNew").val();
					var schoolDevice_deviceName =$("#schoolDevice_deviceName").val(); 
					var schoolDevice_allowAppointment = $("#schoolDevice_allowAppointment").val();
					var url = $("#pageContext").val()+"/device/saveLabRoomDeviceTrainingRestNew/" + labRoomId + "/"+ deviceNumber + "/" + deviceName + "/"+username+"/"+page 
					+ "/"+  content+ "/"+ usernameTeacher+ "/"+ number+"/" + id+"/"+schoolDevice_allowAppointment;
					window.location.href=url;
				}
				//更新选课时间
				function saveTimeAndAddress( ){
					var begintime=$("#begintimeNew").val();
					var address=$("#addressNew").val();
					if(begintime==null||begintime==''){
						alert("请选择时间");
					}else{
						$.ajax({
						    url:$("#pageContext").val()+"/device/addTimeAndAddress?id="+trainId,
						    type:"POST",
						    data: {'begintime':begintime, 'address':address},
						       success:function(data){//AJAX查询成功
						            if(data == 'success'){
						                $(".edit-edit").slideUp();
						                $(".btn-edit").slideDown();//修改信息显示
						                window.location.reload();//刷新页面
						            }else{
						                alert("未能修改成功，请稍后重试");
						            }
						       }
						});
					}
				}	
		</script>						
</body>
</html>
