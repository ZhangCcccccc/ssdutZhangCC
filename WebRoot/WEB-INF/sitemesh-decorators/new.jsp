<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<fmt:setBundle basename="bundles.projecttermination-resources" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<title></title>
		<meta name="Generator" content="gvsun">
		<meta name="Author" content="chenyawen">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="Keywords" content="">
		<meta name="Description" content="">
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<decorator:head></decorator:head>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/lib.css" />
		<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
			<!-- 下拉的样式结束 -->
  		 <script type="text/javascript" src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tab_slider.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/pignose.tab.min.js"></script>
  		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap/easyui.css"> 
  		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>	
		<style>
			.quick_above {
				width: 56%;
				right: 0;
			}
			
			.quick_modular {
				width: 7.2%;
				margin: 0 0 0 1%;
				border-radius: 10px;
			}
			
			.quick_modular_div {
				font-size: 12px;
			}
			
			.modular_content {
				width: 98%;
				height: 47%;
				margin: 1.8% 0 0 0.7%;
			}
			
			.modular,
			.modular_dash {
				width: 5.9%;
				margin: 0 0 0 1%;
				border-radius: 10px;
			}
			
			.modular i {
				font-size: 1.8em;
				bottom: 23%;
			}
			
			.modular_div {
				font-size: 12px;
				bottom: 13%;
			}
		</style>
	</head>

	<body>
			<div class="logo">
				<img src="${pageContext.request.contextPath}/images/img/logo.png" />
				<div class="user_name">
					<a href="${pageContext.request.contextPath}/pages/instructions.jsp" target="_blank"><span>操作手册</span></a>
					<select title="点击可下拉选择其它权限" onchange="changeUserRole()" id="currAuth">
						<c:forEach items="${authorities }" var="curr">
							<c:if test="${sessionScope.authorityName eq  curr.authorityName}">
								<option value="${curr.authorityName }" selected="selected">${curr.cname }</option>
							</c:if>
							<c:if test="${sessionScope.authorityName ne  curr.authorityName}">
								<option value="${curr.authorityName }">${curr.cname }</option>
							</c:if>
						</c:forEach>
					</select>
					<span>${user.cname }</span>
					<a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp" target="_parent"><span>退出</span> </a>  
				</div>
				<div class="quickbtn" title="点击进入模块选择">
					<i class="fa fa-th-large"></i>
				</div>
				
				<div class="quick_above">
					<c:if test="${sessionScope.authorityName eq 'STUDENT'}">
						<a href="${pageContext.request.contextPath}/newtimetable/myTimetableStudent" class="quick_modular">
							<i class="fa fa-calendar"></i>
							<div class="quick_modular_div">我的课表</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'STUDENT'}">
						<a href="${pageContext.request.contextPath}/newtimetable/doStudentCourseSelect" class="quick_modular">
							<i class="fa fa-calculator"></i>
							<div class="quick_modular_div">我的选课</div>
						</a>
						<a href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#/xidlims/lab/labAnnexList?currpage=1" class="quick_modular">
							<i class="fa fa-users"></i>
							<div class="quick_modular_div">实验室预约</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'TEACHER'}">
						<a href="${pageContext.request.contextPath}/newtimetable/myTimetableTeacher" class="quick_modular">
							<i class="fa fa-calendar"></i>
							<div class="quick_modular_div">我的课表</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'COURSETEACHER'}">
						<a href="${pageContext.request.contextPath}/newtimetable/myTimetableCourseTeacher" class="quick_modular">
							<i class="fa fa-calendar"></i>
							<div class="quick_modular_div">我的课表</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'SUPERADMIN'}">
						<a href="${pageContext.request.contextPath}/newtimetable/myTimetableAdmin" class="quick_modular">
							<i class="fa fa-calendar"></i>
								<div class="quick_modular_div">我的课表</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'TEACHER' or sessionScope.authorityName eq 'COURSETEACHER' || sessionScope.authorityName eq 'SUPERADMIN'}">
						<a href="${pageContext.request.contextPath}/newtimetable/listMySchedule?currpage=1" class="quick_modular">
							<i class="fa fa-user-plus"></i>
							<div class="quick_modular_div">我的排课</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'TEACHER' or sessionScope.authorityName eq 'COURSETEACHER' || sessionScope.authorityName eq 'SUPERADMIN' || sessionScope.authorityName eq 'LABCENTERMANAGER'}">
								<a onclick="turnToBack('timetable')" class="quick_modular">
									<i class="fa fa-user-plus"></i>
									<div class="quick_modular_div">排课管理</div>
								</a>
							</c:if>
					<c:if test="${sessionScope.authorityName eq 'ASSOCIATEDEAN' or sessionScope.authorityName eq 'EXPERIMENTALTEACHING' || sessionScope.authorityName eq 'SUPERADMIN' || sessionScope.authorityName eq 'COURSETEACHER'}">
						<a href="${pageContext.request.contextPath}/newoperation/experimentalmanagement?currpage=1" class="quick_modular">
							<i class="fa fa-file-text-o"></i>
							<div class="quick_modular_div">实验大纲</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'EDUCATIONADMIN'}">
						<a href="${pageContext.request.contextPath}/newoperation/experimentalMyAudit?currpage=1" class="quick_modular">
							<i class="fa fa-file-text-o"></i>
							<div class="quick_modular_div">实验大纲</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'TEACHER'}">
						<a href="${pageContext.request.contextPath}/newoperation/listTeachersOperationOutlines?currpage=1" class="quick_modular">
							<i class="fa fa-file-text-o"></i>
							<div class="quick_modular_div">实验大纲</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'STUDENT'}">
						<a href="${pageContext.request.contextPath}/newoperation/listStudentsOperationOutlines?currpage=1" class="quick_modular">
							<i class="fa fa-file-text-o"></i>
							<div class="quick_modular_div">实验大纲</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'SUPERADMIN'  || sessionScope.authorityName eq 'EDUCATIONADMIN'}">
						<a onclick="turnToBack('report')" class="quick_modular">
							<i class="fa fa-bar-chart"></i>
							<div class="quick_modular_div">系统报表</div>
						</a>
						<a onclick="turnToBack('system')" class="quick_modular">
							<i class="fa fa-cogs"></i>
							<div class="quick_modular_div">系统管理</div>
						</a>
						<a onclick="turnToBack('shareData')" class="quick_modular">
							<i class="fa fa-slideshare"></i>
							<div class="quick_modular_div">共享数据</div>
						</a>
					</c:if>
					<%-- <c:if test="${sessionScope.authorityName eq 'SUPERADMIN'}">
						<a href="${pageContext.request.contextPath}/newoperation/listOperationOutlinePermissions?currpage=1" class="quick_modular">
							<!--<i class="fa fa-anchor"></i>-->
							<i class="fa fa-shield"></i>
							<div class="quick_modular_div">大纲修订权限</div>
						</a>
					</c:if> --%>
					<c:if test="${sessionScope.authorityName eq 'COURSETEACHER' || sessionScope.authorityName eq 'SUPERADMIN'}">
						<a href="${pageContext.request.contextPath}/newtimetable/listSchoolCourseDetailPermissions?currpage=1" class="quick_modular">
							<i class="fa fa-calendar-check-o"></i>
							<div class="quick_modular_div">排课权限</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'TEACHER' or sessionScope.authorityName eq 'COURSETEACHER' or sessionScope.authorityName eq 'STUDENT' || sessionScope.authorityName eq 'SUPERADMIN'}">
						<a href="${pageContext.request.contextPath}/tms/index" class="quick_modular">
							<i class="fa fa-server"></i>
							<div class="quick_modular_div">课程平台</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'LABCENTERMANAGER' || sessionScope.authorityName eq 'SUPERADMIN'}">
							<a href="${pageContext.request.contextPath}/newtimetable/listLabSchoolCourseDetailPermissions?currpage=1" class="quick_modular">
								<i class="fa fa-calendar-check-o"></i>
								<div class="quick_modular_div">课程安排</div>
							</a>
							</c:if>
					<c:if test="${sessionScope.authorityName eq 'LABMANAGER' || sessionScope.authorityName eq 'SUPERADMIN'}">
						<a onclick="turnToBack('labReservation')" class="quick_modular">
							<i class="fa fa-users"></i>
							<div class="quick_modular_div">实验室预约</div>
						</a>
						<a onclick="turnToBack('labRoom')" class="quick_modular">
							<i class="fa fa-flask"></i>
							<div class="quick_modular_div">实验室管理</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.authorityName eq 'LABCENTERMANAGER'}">
						<a onclick="turnToBack('labRoom')" class="quick_modular">
							<i class="fa fa-flask"></i>
							<div class="quick_modular_div">实验室管理</div>
						</a>
					</c:if>
				</div>
			</div>

<decorator:body></decorator:body>
	<script type="text/javascript">
		function changeUserRole(){
			var auth = $("#currAuth").val();
			  $.ajax({
	           url:"${pageContext.request.contextPath}/self/changeUserRole",
	           data:{auth:auth},
	           type:"POST",
	           success:function(data){
	                 window.location.reload();
	           }
	         });
		}
		$(".quickbtn").dblclick(function(){
			window.location.href="${pageContext.request.contextPath}/self/myCenter";
		});
		
		function turnToBack(turnType){
				 $.ajax({
			           url:"${pageContext.request.contextPath}/self/setTurnTypeSession",
			           data:{sessionType:turnType},
			           type:"POST",
			           success:function(data){
			           		if(turnType == "system")
			                {
			                	window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#"+data+"/system/listUser?currpage=1";
			                }
			                if(turnType == "report")
			                {
			                	window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#"+data+"/report/comprehensiveReport?termBack=-1";
			                }
			                 if(turnType == "shareData")
			                {
			                	window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#"+data+"/findAllSchoolAcademy?page=1";
			                }
			                 if(turnType == "labReservation")
			                {
			                	window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#"+data+"/lab/labAnnexList?currpage=1";
			                }
			                if(turnType == "labRoom")
			                {
			                	window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#"+data+"/labRoom/selectCenter";
			                }
			                 if(turnType == "timetable")
			                {
			                	window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#"+data+"/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1";
			                }
			                 if(turnType == "myInfo")
			                {
			                	window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#"+data+"/personal/listMyInfo";
			                }
			                 if(turnType == "message")
			                {
			                	window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#"+data+"/personal/messageList?currpage=1";
			                }
			           }
	         	});
			}
	</script>
</html>