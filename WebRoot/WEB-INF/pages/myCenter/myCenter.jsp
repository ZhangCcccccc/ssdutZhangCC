<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

	<head>
		<title></title>
		<meta name="Generator" content="gvsun">
		<meta name="decorator" content="new"/>
		<meta name="Author" content="chenyawen">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="Keywords" content="">
		<meta name="Description" content="">
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/lib.css" />
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
	<style>
	        body{
	            position:absolute;
	            width:100%;
	            height:100%;
	            overflow:hidden;
	        }
	        #bgheight{
	            position:absolute;
	            width:100%;
	            height:100%;
	        }
	        .main_content{
	            position:absolute!important;
	            left:2.5%;
	            height:78%;
	        }
			.quick_above {
				width: 60.5%!important;
				right: 0;
			}
			
			.quick_modular {
				width: 7.7%!important;
                margin: 0 0 0 0.6%!important;
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
				width:7.2%!important;
                margin: 0 0 0 1%!important;
                border-radius: 10px;
                box-sizing:border-box;
			}
			
			.modular i {
				font-size: 1.8em;
				bottom: 23%;
			}
			
			.modular_div {
				font-size: 12px;
				bottom: 13%;
			}
			
			.notice_table{
			    width:100%;
			    font-size:13px;
			    text-align:left;
			}
			.notice_table th{
			    font-weight:bold;
			    line-height:24px;
			    white-space:nowrap;
			    padding:0 5px;
			}
			.notice_table td{
			    line-height: 21px;
                vertical-align: text-top;
                padding: 0 5px 5px;
			}
			a{
			    color:#333;
			    white-space:nowrap;
			}
			a:hover{
			   cursor:pointer;
			   color:#6d8dd5;
			   text-shadow:0.5px 0.5px 0.5px rgba(255,255,255,0.5);
			    border-bottom:1px solid #6d8dd5;
			}
			.table_message .notice_tab{width:100%;}
			.table_message .link_tab{width:display:none;}
		</style>


	</head>
    <% session.setAttribute("LOGINTYPE","center"); %>
	<body>
		<div id="bgheight">
			<%--<script type="text/javascript">
				$(function() {
					$("#bgheight").height($(window).height() / 1);
					$("#maincontent").height($(window).height() / 1.3);
				})
			</script>
			--%><div class="main_content" id="maincontent">
				<div class="personal_message">
					<div class="per_font">个人信息</div>
					<div class="per_img">
						<img src="${pageContext.request.contextPath}/${user.photo}" />
					</div>
					<div class="per_font">${user.cname }</div>
					<div>${user.schoolAcademy.academyName }</div>
					<div><c:forEach items="${authorities }" var="curr">
							<c:if test="${sessionScope.authorityName eq  curr.authorityName}">
								${curr.cname }
							</c:if>
						</c:forEach> | <a onclick="turnToBack('myInfo')">个人中心</a></div>
				</div>
				<div class="right_content">
					<div class="table_message">
						<div class="notice_tab">
							<div class="table_content">
								<div class="table_tit">我的消息</div>

									<div>
										<table class="notice_table">
											<tr>
												<th width="10%">消息人</th>
												<th width="35%">消息标题</th>
												<th width="20%">消息人单位</th>
												<th width="15%">发送时间</th>
												<th width="10%">消息状态</th>
												<th width="10%">操作</th>
											</tr>
											<c:forEach items="${messages }" var="current" varStatus="i">
													<tr>
														<td>${current.sendUser}</td>
														<td>${current.title }</td>
														<td>${current.sendCparty }</td>
														<td><fmt:formatDate value="${current.createTime.time}" pattern="yyyy/MM/dd"/></td>
														<td><c:if test="${current.messageState==0}">
											            	未读
											            	</c:if>
											            	<c:if test="${current.messageState==1}">
											            	已读
											            	</c:if> 
											            </td>
														<td>
															<a onclick='viewMessage(${current.id})'>查看</a>
															<a onclick='deleteMessage(${current.id})'>删除</a>
														</td>
													</tr>
											</c:forEach>
										</table>
									</div>
								<ul>
								<li>
									<div></div>
									<span><a onclick="turnToBack('message')">more</a></span>
								</li> 
								</ul>
							</div>
							<div class="notice_show" title="隐藏左边">
								<i class="fa fa-hand-o-left"></i>
							</div>
						</div>
						<div class="link_tab">
							<div class="link_line">
								<div class="line_tit">教师排课</div>
								<div class="line_content">
									<a href="#" class="link_modular">
										<i class="fa fa-user-plus"></i>
										<div class="link_modular_div">我的排课</div>
									</a>
									<i class="fa fa-long-arrow-right line-space"></i>
									<a href="#" class="link_modular">
										<i class="fa fa-calendar-check-o"></i>
										<div class="link_modular_div">排课权限</div>
									</a>
									<i class="fa fa-long-arrow-right line-space"></i>
									<a href="#" class="link_modular">
										<i class="fa fa-calendar"></i>
										<div class="link_modular_div">我的课表</div>
									</a>
								</div>
							</div>
							<div class="link_line">
								<div class="line_tit">学生选课</div>
								<div class="line_content">
									<a href="#" class="link_modular">
										<i class="fa fa-th"></i>
										<div class="link_modular_div">我的选课</div>
									</a>
									<i class="fa fa-long-arrow-right line-space"></i>
									<a href="#" class="link_modular">
										<i class="fa fa-calendar"></i>
										<div class="link_modular_div">我的课表</div>
									</a>
								</div>
							</div>
							<div class="link_line">
								<div class="line_tit">大纲管理</div>
								<div class="line_content">
									<a href="#" class="link_modular">
										<i class="fa fa-file-text-o"></i>
										<div class="link_modular_div">实验大纲</div>
									</a>
								</div>
							</div>
							<div class="link_line">
								<div class="line_tit">课程学习</div>
								<div class="line_content">
									<a href="#" class="link_modular">
										<i class="fa fa-server"></i>
										<div class="link_modular_div">课程平台</div>
									</a>
								</div>
							</div>
							<div class="link_show" title="隐藏右边">
								<i class="fa fa-hand-o-right"></i>
							</div>
						</div>
						<div class="notice_link_reset" title="恢复初始状态"></div>
					</div>
					<div class="lesson_message">
						<p>我的应用</p>
						<div class="modular_content">
							<%-- <c:if test="${sessionScope.authorityName eq 'STUDENT'}">
							<a href="${pageContext.request.contextPath}/newtimetable/myTimetableStudent" class="modular">
								<i class="fa fa-calendar"></i>
								<div class="modular_div">我的课表</div>
							</a>
							</c:if> 
							<c:if test="${sessionScope.authorityName eq 'STUDENT'}">
							<a href="${pageContext.request.contextPath}/newtimetable/doStudentCourseSelect" class="modular">
								<i class="fa fa-calculator"></i>
								<div class="modular_div">我的选课</div>
							</a>
							 <a onclick="turnToBack('labReservation')" class="modular">
									<i class="fa fa-users"></i>
									<div class="modular_div">实验室预约</div>
							</a>
							</c:if>
							<c:if test="${sessionScope.authorityName eq 'TEACHER'}">
								<a href="${pageContext.request.contextPath}/newtimetable/myTimetableTeacher" class="modular">
									<i class="fa fa-calendar"></i>
									<div class="modular_div">我的课表</div>
								</a>
							</c:if>
							<c:if test="${sessionScope.authorityName eq 'COURSETEACHER'}">
								<a href="${pageContext.request.contextPath}/newtimetable/myTimetableCourseTeacher" class="modular">
									<i class="fa fa-calendar"></i>
									<div class="modular_div">我的课表</div>
								</a>
							</c:if>
							<c:if test="${sessionScope.authorityName eq 'SUPERADMIN' || sessionScope.authorityName eq 'LABCENTERMANAGER'}">
								<a href="${pageContext.request.contextPath}/newtimetable/myTimetableAdmin" class="modular">
									<i class="fa fa-calendar"></i>
									<div class="modular_div">我的课表</div>
								</a>
							</c:if>
							<c:if test="${sessionScope.authorityName eq 'EXCENTERDIRECTOR'}">
								<a href="${pageContext.request.contextPath}/newtimetable/myTimetableLabDirector" class="modular">
									<i class="fa fa-calendar"></i>
									<div class="modular_div">我的课表</div>
								</a>
							</c:if>
							<c:if test="${sessionScope.authorityName eq 'TEACHER' or sessionScope.authorityName eq 'COURSETEACHER' || sessionScope.authorityName eq 'SUPERADMIN' || sessionScope.authorityName eq 'LABCENTERMANAGER'}">
								<a href="${pageContext.request.contextPath}/newtimetable/listMySchedule?currpage=1" class="modular">
									<i class="fa fa-user-plus"></i>
									<div class="modular_div">我的排课</div>
								</a>
							</c:if> 
							<c:if test="${sessionScope.authorityName eq 'TEACHER' or sessionScope.authorityName eq 'COURSETEACHER' || sessionScope.authorityName eq 'SUPERADMIN' || sessionScope.authorityName eq 'LABCENTERMANAGER'}">
								<a onclick="turnToBack('timetable')" class="modular">
									<i class="fa fa-user-plus"></i>
									<div class="modular_div">排课管理</div>
								</a>
							</c:if>--%>
							<c:if test="${sessionScope.authorityName eq 'ASSOCIATEDEAN' or sessionScope.authorityName eq 'EXPERIMENTALTEACHING' || sessionScope.authorityName eq 'SUPERADMIN' || sessionScope.authorityName eq 'COURSETEACHER' || sessionScope.authorityName eq 'LABCENTERMANAGER'}">
							<a href="${pageContext.request.contextPath}/newoperation/experimentalmanagement?currpage=1" class="modular">
								<i class="fa fa-file-text-o"></i>
								<div class="modular_div">实验大纲</div>
							</a>
							</c:if>
							<c:if test="${sessionScope.authorityName eq 'EDUCATIONADMIN'}">
							<a href="${pageContext.request.contextPath}/newoperation/experimentalMyAudit?currpage=1" class="modular">
								<i class="fa fa-file-text-o"></i>
								<div class="modular_div">实验大纲</div>
							</a>
							</c:if>
							<c:if test="${sessionScope.authorityName eq 'TEACHER'}">
							<a href="${pageContext.request.contextPath}/newoperation/listTeachersOperationOutlines?currpage=1" class="modular">
								<i class="fa fa-file-text-o"></i>
								<div class="modular_div">实验大纲</div>
							</a>
							</c:if>
							<c:if test="${sessionScope.authorityName eq 'STUDENT'}">
							<a href="${pageContext.request.contextPath}/newoperation/listStudentsOperationOutlines?currpage=1" class="modular">
								<i class="fa fa-file-text-o"></i>
								<div class="modular_div">实验大纲</div>
							</a>
							</c:if>
							<%-- <c:if test="${sessionScope.authorityName ne 'STUDENT'}">
								<a onclick="turnToBack('report')" class="modular">
									<i class="fa fa-bar-chart"></i>
									<div class="modular_div">系统报表</div>
								</a>
							</c:if> --%>
							<c:if test="${sessionScope.authorityName eq 'SUPERADMIN'  || sessionScope.authorityName eq 'EDUCATIONADMIN'}">
							
							<a onclick="turnToBack('system')" class="modular">
								<i class="fa fa-cogs"></i>
								<div class="modular_div">系统管理</div>
							</a>
							<!-- <a onclick="turnToBack('shareData')" class="modular">
								<i class="fa fa-slideshare"></i>
								<div class="modular_div">共享数据</div>
							</a> -->
							</c:if>
							<%-- <c:if test="${sessionScope.authorityName eq 'SUPERADMIN'}">
								<a href="${pageContext.request.contextPath}/newoperation/listOperationOutlinePermissions?currpage=1" class="modular">
								<!--<i class="fa fa-anchor"></i>-->
								<i class="fa fa-shield"></i>
								<div class="modular_div">大纲修订权限</div>
								</a>
							</c:if> --%>
							<c:if test="${sessionScope.authorityName eq 'COURSETEACHER' || sessionScope.authorityName eq 'SUPERADMIN' || sessionScope.authorityName eq 'LABCENTERMANAGER'}">
							<a href="${pageContext.request.contextPath}/newtimetable/listSchoolCourseDetailPermissions?currpage=1" class="modular">
								<i class="fa fa-calendar-check-o"></i>
								<div class="modular_div">课程安排</div>
							</a>
							</c:if>
							<%-- <c:if test="${sessionScope.authorityName eq 'LABCENTERMANAGER' || sessionScope.authorityName eq 'SUPERADMIN'}">
							<a href="${pageContext.request.contextPath}/newtimetable/listLabSchoolCourseDetailPermissions?currpage=1" class="modular">
								<i class="fa fa-calendar-check-o"></i>
								<div class="modular_div">课程安排</div>
							</a>
							</c:if> --%>
							<c:if test="${sessionScope.authorityName eq 'TEACHER' or sessionScope.authorityName eq 'COURSETEACHER' or sessionScope.authorityName eq 'STUDENT' || sessionScope.authorityName eq 'SUPERADMIN'}">
							<a href="${pageContext.request.contextPath}/tms/index" class="modular">
								<i class="fa fa-server"></i>
								<div class="modular_div">课程平台</div>
							</a>
							</c:if>
							<%-- <c:if test="${sessionScope.authorityName eq 'LABMANAGER' || sessionScope.authorityName eq 'SUPERADMIN' || sessionScope.authorityName eq 'LABCENTERMANAGER'}">
								<a onclick="turnToBack('labReservation')" class="modular">
									<i class="fa fa-users"></i>
									<div class="modular_div">实验室预约</div>
								</a>
							</c:if>
							<c:if test="${sessionScope.authorityName eq 'LABMANAGER' || sessionScope.authorityName eq 'SUPERADMIN' || sessionScope.authorityName eq 'LABCENTERMANAGER' || sessionScope.authorityName eq 'TEACHER'}">
								<a onclick="turnToBack('labRoom')" class="modular">
									<i class="fa fa-flask"></i>
									<div class="modular_div">实验室管理</div>
								</a>
							</c:if> --%>
						</div>
					</div>
				</div>
			</div>

		</div>
		<script type="text/javascript">
			function viewMessage(id){
				$.ajax({
					type: "POST",
					url: "${pageContext.request.contextPath}/setMsgStateNew?id="+id,
					dataType:'json',
					success:function(data){
						if(data[2] == 'timetableStudent'){
							window.location.href="${pageContext.request.contextPath}"+data[1];
						}
						if(data[2] == 'timetable'){
							$.ajax({
				           url:"${pageContext.request.contextPath}/self/setTurnTypeSession",
				           data:{sessionType:'timetable'},
				           type:"POST",
				           success:function(s){
				           	window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#"+"${pageContext.request.contextPath}"+data[1];
				           }
				           });
						}
						if(data[2] =='labReservation'){
							$.ajax({
				           url:"${pageContext.request.contextPath}/self/setTurnTypeSession",
				           data:{sessionType:'labReservation'},
				           type:"POST",
				           success:function(s){
				           	window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#"+"${pageContext.request.contextPath}"+data[1];
				           }
				           });
						}
					},
					error:function(){
						alert("数据加载失败，请检查网络");
					}
				});
			}
			$(".notice_show").click(
				function() {
					$(".notice_tab").hide();
					$(".link_tab").show().addClass("w100p");
					$(".notice_link_reset").show();
					$(".link_line").removeClass("link_line").addClass("link_line2");
				}
			);
			$(".link_show").click(
				function() {
					$(".link_tab").hide();
					$(".notice_tab").show().addClass("w100p");
					$(".notice_link_reset").show();
				}
			);
			$(".notice_link_reset").click(
				function() {
					$(".link_tab").show().removeClass("w100p").addClass("w49_9p");
					$(".notice_tab").show().removeClass("w100p").addClass("w49_9p");
					$(".notice_link_reset").hide();
					$(".link_line2").removeClass("link_line2").addClass("link_line");
				}
			);
			$(".quickbtn").click(
				function() {
					$(".quick_above").show();
				}
			);
			$(document).bind("click", function() {
				$('.quick_above').hide();
			});
			$(".quickbtn").click(function(event) {
				event.stopPropagation();

			});
			$(".quick_above").click(function(event) {
				event.stopPropagation();

			});
			$(window).scroll(function() {
				$(".quick_above").hide();
			});
			function deleteMessage(id){  
		    	if(confirm("删除?"))
		    	{
		    		$.ajax({
		    	        url:"${pageContext.request.contextPath}/deleteMessage?idKey="+id,
		    	        type: 'POST',   
		    	        async: false,  
		    	        cache: false,  
		    	        contentType: false,  
		    	        processData: false, 
		    	        
		    	        error: function(request) {
		    	            alert("请求错误");
		    	        },
		    	        success: function(data) {
		    	        	window.location.reload(); 
		    	        }
		    	    });
		    		
		    	}
		    }
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

	</body>

</html>


