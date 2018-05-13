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

<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/viewDeivce.js"></script>  

<script>
var trainingId;//学生当前所选培训的id，全局变量
//保存实验设备培训人之前的弹窗
function addStudentInfo(id){
	//ajax判断是否可以预约
	$.ajax({
        url:"${pageContext.request.contextPath}/device/ifPermitted?deviceId="+${device.id},
        type:"post",
        success:function(data){
            if(data=="wait"){
                alert("您已预约其他培训，请耐心等待培训结果！")
            }else if(data=="permitted"){
                alert("您不需要参加此次培训，请直接预约！")
            }else{
				 trainingId=id;
				//alert("请输入您的联系方式，方便培训老师联系" );
				$("#addStudentInfo").show();
			    //获取当前屏幕的绝对位置
			    var topPos = window.pageYOffset;
			    //使得弹出框在屏幕顶端可见
			    $('#addStudentInfo').window({left:"100px", top:"100px"});
			    $("#addStudentInfo").window('open');  
            }
        }
    });
	
    
}

//保存实验设备培训人
function joinTrainingRest(){//将labRoomId deviceNumber deviceName page传递到后台
	var telephone=$("#telephone").val();
	var eMail=$("#eMail").val();
	if(telephone==""||telephone==null){
		alert("请输入电话")
	}else if(eMail==""||eMail==null){
		alert("请输入邮箱")
	}else{
		eMail=eMail.replace(/\+/g,"[Geng1Shang]");//将特殊字符"+"转化为[Geng1Shang]
		eMail=eMail.replace(/\ /g,"[Geng2Shang]");//将特殊字符" "转化为[Geng2Shang]
		eMail=eMail.replace(/\//g,"[Geng3Shang]");//将特殊字符"/"转化为[Geng3Shang]
		eMail=eMail.replace(/\?/g,"[Geng4Shang]");//将特殊字符"?"转化为[Geng4Shang]
		eMail=eMail.replace(/\%/g,"[Geng5Shang]");//将特殊字符"%"转化为[Geng5Shang]
		eMail=eMail.replace(/\#/g,"[Geng6Shang]");//将特殊字符"#"转化为[Geng6Shang]
		eMail=eMail.replace(/\&/g,"[Geng7Shang]");//将特殊字符"&"转化为[Geng7Shang]
		eMail=eMail.replace(/\=/g,"[Geng8Shang]");//将特殊字符"="转化为[Geng8Shang]
		eMail=eMail.replace(/\./g,"[Geng9Shang]");//将特殊字符"."转化为[Geng9Shang]
		var url = "${pageContext.request.contextPath}/device/joinTrainingRest/" + ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}" +"/"+${username}+ "/"+${page}+"/" + trainingId +"/" + telephone +"/" + eMail+"/"+"${schoolDevice_allowAppointment}";
		//alert(url);
		window.location.href=url;
	}
}

//取消预约
function cancleTrainingRest(id){
	var url = "${pageContext.request.contextPath}/device/cancleTrainingRest/" + ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}" +"/"+${username}+ "/"+${page}+"/" + id+"/"+"${schoolDevice_allowAppointment}";
	//alert(url);
	window.location.href=url;
}
//根据设备id查询培训
function findTrainingPeopleByTrainIdRest(id,toChangeAudit){//将labRoomId deviceNumber deviceName page传递到后台
	var url = "${pageContext.request.contextPath}/device/findTrainingPeopleByTrainIdRest/" + ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}"+"/"+${username} + "/"+${page}+"/" + id+"/"+1 +"/"+toChangeAudit+"/"+"${schoolDevice_allowAppointment}";
	//alert(url);
	window.location.href=url;
}


//AJAX验证是否通过安全准入
function AccessOrNot(id){
	$.ajax({
	           url:"${pageContext.request.contextPath}/device/securityAccess?id="+id,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	           		if(data=="success"){
	           			alert("您已经通过培训！");
	           		}else{
	           			alert("您还未通过培训！");
	           		}    
	           }
	});
	
}
//检查邮箱格式
function checkMail(){
	var eMail = $("#eMail").val();
	if(eMail.trim()!=""){
		var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!filter.test(eMail)){
			alert('您的邮箱格式不正确');
			return false;
		}
	}
	
}

//检查手机号格式
function checkTele(){
	var telephone=$("#telephone").val();
	var reSpaceCheck = /^[1][3578][0-9]{9}$/;
    if (reSpaceCheck.test(telephone)) {
    }
    else {
    	alert("您输入的手机号不合法");
    }
}
</script>

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
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceTrainingRest(${device.id})";>培训计划</a>
		</li>
		<%--<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="viewDeviceSettingRest(${device.id})";>参数详情</a></li>--%>
		<%--<li class="TabbedPanelsTab" tabindex="0">
        <a href="javascript:void(0);" onclick="Access(${device.id})";>设备预约</a></li>--%>
		<li class="TabbedPanelsTab" tabindex="0"><a href="javascript:void(0);" onclick="viewDeviceReservationRest(${device.id},1)";>使用情况</a></li>
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
						<form:form action="${pageContext.request.contextPath}/device/findAllTrainingByDeviceId?deviceId=${device.id}" method="post" modelAttribute="train">
						<table>
						<tr>
							<td>学期:</td>
							<td>
							<form:select path="schoolTerm.id">
							<form:options items="${terms}" itemLabel="termName" itemValue="id"/>
							</form:select>
							</td>
							<td><input type="submit" value="查询"></td>
							<td><input type="button" value="取消查询" onclick="window.history.go(0)"></td>	
						</tr>
					
						</table>
						</form:form>
					
				</div>
		<div class="content-box">
			<div class="title">
			<div id="title">设备培训</div>
			<a class="btn btn-new"  herf="#" onclick="openSetupLink();">返回</a>
			</div>
				<div class="content-double">
					
					<table >
						<thead>
							<tr>
								<th width="20%">培训内容</th>
								<th>培训地点</th>
								<th>培训时间</th>
								<th>主讲教师</th>
								<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN"> 
								<th>培训人数</th>
								</sec:authorize>
								<th>学期</th>
								<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN">
								<th>状态</th>
								<th>通过率</th>
								</sec:authorize>
								<c:if test="${t.user.username==user.username || isEXCENTERDIRECTOR}">
								<th>操作</th>
								</c:if>
								<sec:authorize ifNotGranted="ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN">
								<th>培训状态</th>
								</sec:authorize>
							</tr>
						</thead>
						<c:forEach items="${trainList}" var="t">
						<tr>
							<td><p>${t.content}</p></td>
							<td>${t.address}</td>
							<td><fmt:formatDate value="${t.time.time}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
							<td>${t.user.cname}</td>
							<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN"> 
							<td>${t.joinNumber}/${t.number}</td>
							</sec:authorize>
							<td>${t.schoolTerm.termName}</td>
							<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN">
							<%-- <td>${t.CTrainingStatus.name}</td> --%>
							<td>${t.CTrainingStatus.CName}</td>
							<td>${t.passRate}</td>
							</sec:authorize>
							<%--<td><a href="${pageContext.request.contextPath}/device/findTrainingPeopleByTrainId?id=${t.id}">查看</a>
							--%>
							<c:if test="${t.user.username==user.username || isEXCENTERDIRECTOR}">
							<td>
								<a href="javascript:void(0);" onclick="findTrainingPeopleByTrainIdRest(${t.id},1)";>编辑</a>
								<a href="javascript:void(0);" onclick="findTrainingPeopleByTrainIdRest(${t.id},2)";>查看</a>
							</td>
							</c:if>
						<sec:authorize ifNotGranted="ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN"><!-- 只有学生才能参加培训 -->
							<td>
								<%-- <c:if test="${t.CTrainingStatus.id==1}"> --%><!-- 待培训 -->
								<c:if test="${t.CTrainingStatus.CCategory=='c_training_status'&&t.CTrainingStatus.CNumber=='1'}">
								<c:if test="${t.number > t.joinNumber}"><!-- 没有超出人数限制 -->
									<c:forEach items="${map}" var="m">
										<c:if test="${m.key==t.id}"><!-- 匹配培训id -->
											<c:if test="${m.value==1}">
											<%--<a href="${pageContext.request.contextPath}/device/joinTraining?id=${t.id}">预约培训</a>--%>
											<a href="javascript:void(0);"  onclick="addStudentInfo(${t.id})";>预约培训</a>
											</c:if>
											<c:if test="${m.value==0}">
											培训已预约
											<a href="javaScript:cancleTrainingRest(${t.id});"  onclick="return confirm('确定取消？')">取消预约</a>
											</c:if>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${t.number <= t.joinNumber}">
								<c:forEach items="${map}" var="m">
									<c:if test="${m.key==t.id}"><!-- 匹配培训id -->
										<c:if test="${m.value==1}">
										人数已满，无法预约
										</c:if>
										<c:if test="${m.value==0}">
										培训已预约
										<a href="javaScript:cancleTrainingRest(${t.id});"  onclick="return confirm('确定取消？')">取消预约</a>
										</c:if>
									</c:if>
									</c:forEach>
								</c:if>
								</c:if>
								
								<%-- <c:if test="${t.CTrainingStatus.id==2}"> --%>
								<c:if test="${t.CTrainingStatus.CCategory=='c_training_status'&&t.CTrainingStatus.CNumber=='2'}">
								<c:if test="${fn:contains(t.labRoomDeviceTrainingPeoples,user.username)}">
								培训结束，请<a href="javascript:void(0);" onclick="AccessOrNot('${t.labRoomDevice.id}');"  >点击</a>查看培训结果
								</c:if>
								<c:if test="${fn:indexOf(t.labRoomDeviceTrainingPeoples,user.username)==-1}">
								培训已结束！
								</c:if>
								</c:if>
								<%-- <c:if test="${t.CTrainingStatus.id==3}"> 
									${t.CTrainingStatus.name}--%>
								<c:if test="${t.CTrainingStatus.CCategory=='c_training_status'&&t.CTrainingStatus.CNumber=='3'}">
									${t.CTrainingStatus.CName}
								</c:if>
								<%-- <c:if test="${t.CTrainingStatus.id==4 }"> --%>
								<c:if test="${t.CTrainingStatus.CCategory=='c_training_status'&&t.CTrainingStatus.CNumber=='4'}">
									培训已取消
								</c:if>
								
							</td>
						</sec:authorize>
						</tr>
						</c:forEach>
						<input type="hidden" id="labRoomId" value="${labRoomId }">
			  			<input type="hidden" id="deviceName" value="${deviceName }">
			  			<input type="hidden" id="deviceNumber" value="${deviceNumber }">
			  			<input type="hidden" id="username" value="${username }">
			  			<input type="hidden" id="page" value="${page }">
						<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
						<input type="hidden" id="schoolDevice_allowAppointment" value="${schoolDevice_allowAppointment}">
					</table>
				</div>
      	
			</div>
		</div>
	
	  </div>
	</div>
	
</div>


<div id="addStudentInfo" class="easyui-window" closed="true" modal="true" minimizable="true" title="联系方式" style="width: 600px;height: 200px;padding: 20px">
	  <div class="content-box">
		  <form:form name="form_material" method="post" modelAttribute="labRoomDeviceTrainingPeople" >
			  <table class="color_tb">
			  <tr>
			    <td>移动电话</td><td><form:input path="telephone" id="telephone" required="true" onblur="checkTele()"/><form:hidden path="id" /></td>
			  </tr>
			  <tr>
			    <td>邮箱</td><td><form:input path="eMail" id="eMail" required="true" onblur="checkMail()"/></td>
			  </tr>
			  </table>
			  
			  <div class="moudle_footer">
			    <div class="submit_link">
			        <input class="btn" id="save" type="button" onclick="joinTrainingRest();" value="确定">
			    </div>
			  </div>
		  </form:form>
	</div>
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
