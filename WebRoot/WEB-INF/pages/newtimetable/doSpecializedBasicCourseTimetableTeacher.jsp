<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<title></title>
		<meta name="Generator" content="gvsun">
		<meta name="Author" content="chenyawen">
		<meta name="decorator" content="new"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="Keywords" content="">
		<meta name="Description" content="">
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/lib.css" />
		<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/silver.css" media="screen">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.silver_track.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.silver_track_recipes.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.silver_track.navigator.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.silver_track.bullet_navigator.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
		
		<!-- 公用js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/newtimetable/timetableShare.js"></script>
		
		<!-- 下拉框的样式 -->
 		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  		<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  		<!-- 下拉的样式结束 --> 
  			<style type="text/css">
  			.cbm_box .chzn-container,.course_basic_message .cbm_box{
  				
  				height:auto !important;
  			}
  			
  			.course_basic_message .cbm_box,.course_select div,.course_select{
  				overflow:visible !important;
  			}
  			.search-choice span{
  				width:auto !important;
  				background:none !important;
  				color:#333 !important;
  			}
  			#teacher-chzn{
  				height:auto !important;
  			}
  			.chzn-container-multi .chzn-results{
  				height:auto;
  			}
  		</style>
	</head>

	<body>
		<div id="bgheight">
			<script type="text/javascript">
				$(function() {
					$("#bgheight").height($(window).height() / 1);
				})
			</script>
			<form name="form"
								action="${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetableTeacher?courseDetailNo=${courseDetailNo}"
								method="post">
			<div class="all_main_content ovh">
				<div class="w74p l">
					<div class="normal">
						<div class="gray_line">
						
						<table style="width:400px;">
							<tr>
								<td>
									<span>实验室:&nbsp;</span>
									<select name="labroom" id="labroom" class="chzn-select" style="width:300px;">
										<option value="-1">请选择</option>
										<c:forEach items="${labRoomMap}" var="curr">
											<c:if test="${labRoom.id eq curr.key }">
												<option value="${curr.key }" selected="selected">${curr.value }</option>
											</c:if>
											<c:if test="${labRoom.id ne curr.key }">
												<option value="${curr.key }">${curr.value }</option>
											</c:if>
										</c:forEach>
									</select>
									<input type="hidden" value="${item}" id="item" name="item"/>
									<input type="hidden" value="${groupId}" id="groupId" name="groupId"/>
									<input type="hidden" value="0" id="showChosen" name="showChosen"/>
								</td>
							</tr>
						</table>
						<table>
							<tr>
							<td>
							<button type="button" onclick="queryLab()">查询</button>
								<button type="button" onclick="cancel()">取消</button>
							</td>
							</tr>
						</table>
						</div>
						<div class="bgwo w100p ptb10">
							<table class="timetable_tab" cellspacing="0" style="table-layout:fixed;">
								<tr>
									<th colspan="2" rowspan="3" class="timetable_th" style="width:;">
										<span class="tt_pa1">月</span>
										<span class="tt_pa2">周</span>
										<span class="tt_pa3">日期</span>
										<span class="tt_pa4">课次</span>
										<span class="tt_pa5">星期</span>
									</th>
									<c:set var="tempWeek" value="0"/>
								 	<c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
								 			<c:set var="countWeek" value="0"/>
								 			
								 		<c:forEach items="${schoolTermWeeks }" var="currWeek2" varStatus="i">
								 			<c:if test="${currWeek1[6] == currWeek2[6] }">
								 				<c:set var="countWeek" value="${countWeek+1 }"/>
								 			</c:if>
								 		</c:forEach>
								 		<c:if test="${currWeek1 [6] != tempWeek && countWeek>1}">
								 			<th colspan="${countWeek }">${currWeek1 [6]}月</th>
								 		</c:if>
								 		<c:if test="${countWeek==1}">
								 			<th>${currWeek1 [6]}月</th>
								 		</c:if>
								 		<c:set var="tempWeek" value="${currWeek1[6] }"/>
								 	</c:forEach>
								</tr>
								<tr>
								<c:set var="tempWeek" value="0"/>
							 	<c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
							 		<c:set var="flag" value="0"/>
							 		<c:forEach items="${weeks }" var="curr">
							 			<c:if test="${curr eq currWeek1[2] }">
							 				<c:set var="flag" value="1"/>
							 			</c:if>
							 		</c:forEach>
							 		<c:if test="${flag == 1 }">
							 			<td class="select-week selected-week">${currWeek1[2] }</a></td>
							 			<input type="hidden" name="isChosen" id="week${currWeek1[2] }" value="1"/> 
							 		</c:if>
							 		<c:if test="${flag == 0 }">
							 			<td class="select-week">${currWeek1[2] }</a></td>
							 			<input type="hidden" name="isChosen" id="week${currWeek1[2] }" value="0"/> 
							 		</c:if>
							 		
							 	</c:forEach>
								</tr>
								<tr>
								<c:set var="tempWeek" value="0"/>
							 	<c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
							 		<th>
							 			<c:if test="${currWeek1[6]==currWeek1[7]}">${currWeek1[8]}<br>|<br>${currWeek1[9]}</c:if>
							 			<c:if test="${currWeek1[6]!=currWeek1[7]}">${currWeek1[6]}/${currWeek1[8]}<br>|<br>${currWeek1[7]}/${currWeek1[9]}</c:if>
									</th>
							 	</c:forEach>
							</tr>
								<c:forEach var="class1"  varStatus="cStatus1" begin="${schoolTermWeeks[0][10]}" end="7">
							       	<c:forEach var="class2" varStatus="cStatus2" begin="1" end="5">
							       		<tr>
							       			<c:if test="${cStatus2.index==1}">
							       				<c:if test="${cStatus1.index==1}">
										               <td rowspan="5">一</td>  
										            </c:if>
										            <c:if test="${cStatus1.index==2}">
										               <td rowspan="5">二</td>  
										            </c:if>
										            <c:if test="${cStatus1.index==3}">
										               <td rowspan="5">三</td>
										            </c:if>
										            <c:if test="${cStatus1.index==4}">
										               <td rowspan="5">四</td>  
										            </c:if>
										            <c:if test="${cStatus1.index==5}">
										               <td rowspan="5">五</td>  
										            </c:if>
										            <c:if test="${cStatus1.index==6}">
										               <td rowspan="5">六</td>
										            </c:if>
										            <c:if test="${cStatus1.index==7}">
										               <td rowspan="5">日</td>
										            </c:if> 
								               <th>1-2</th>
								            </c:if>
								            <c:if test="${cStatus2.index==2}">
								               <th>3-4</th>  
								            </c:if>
								            <c:if test="${cStatus2.index==3}">
								               <th>5-6</th>
								            </c:if>
								            <c:if test="${cStatus2.index==4}">
								               <th>7-8</th>
								            </c:if>
								            <c:if test="${cStatus2.index==5}">
										            <th>晚</th>
								            </c:if>
								            <c:set var="countWeek" value="0"/>
								              <c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
								              	<c:set var="isChosen" value="0"/>
										 		<c:forEach items="${weeks }" var="curr">
										 			<c:if test="${curr eq currWeek1[2] }">
										 				<c:set var="isChosen" value="1"/>
										 			</c:if>
										 		</c:forEach>
								            	<c:set var="countWeek" value="${countWeek+1 }"/>
								            	<c:set var="flag" value="0"/>
								            	<c:set var="dayName" value="0"/>
								            	<c:forEach items="${listLabTimetableAppointments }" var="currTimetable">
								            		<c:if test="${currTimetable.timetableAppointment.timetableAppointmentSameNumbers eq null || fn:length(currTimetable.timetableAppointment.timetableAppointmentSameNumbers) == 0 }">
								            			<c:if test="${currWeek1[2] >= currTimetable.timetableAppointment.startWeek && currWeek1[2] <= currTimetable.timetableAppointment.endWeek
									            		&& currTimetable.timetableAppointment.weekday == cStatus1.index 
									            		&& cStatus2.index > currTimetable.timetableAppointment.startClass/2 && cStatus2.index <= currTimetable.timetableAppointment.endClass/2}">
									            				<c:if test="${currTimetable.timetableAppointment.schoolCourseDetail.courseDetailNo eq  schoolCourseDetail.courseDetailNo}">
									            					<c:set var="flag" value="4"/>
									            				</c:if>
									            				<c:if test="${currTimetable.timetableAppointment.schoolCourseDetail.courseDetailNo ne  schoolCourseDetail.courseDetailNo}">
									            					<c:set var="flag" value="3"/>
									            				</c:if>
										            	</c:if>
								            		</c:if>
								            		<c:if test="${currTimetable.timetableAppointment.timetableAppointmentSameNumbers ne null &&  fn:length(currTimetable.timetableAppointment.timetableAppointmentSameNumbers) > 0 }">
								            			<c:forEach items = "${currTimetable.timetableAppointment.timetableAppointmentSameNumbers}" var="currSame">
								            				<c:if test="${currWeek1[2] >= currSame.startWeek && currWeek1[2] <= currSame.endWeek
											            		&& currTimetable.timetableAppointment.weekday == cStatus1.index 
											            		&& cStatus2.index > currSame.startClass/2 && cStatus2.index <= currSame.endClass/2}">
											            				<c:if test="${currTimetable.timetableAppointment.schoolCourseDetail.courseDetailNo eq  schoolCourseDetail.courseDetailNo}">
											            					<c:set var="flag" value="4"/>
											            				</c:if>
											            				<c:if test="${currTimetable.timetableAppointment.schoolCourseDetail.courseDetailNo ne  schoolCourseDetail.courseDetailNo}">
											            					<c:set var="flag" value="3"/>
											            				</c:if>
												            	</c:if>
								            			</c:forEach>
								            		</c:if>
								            		
								            	</c:forEach>
								            	<c:if test="${not empty specialSchoolWeeks}">
									            	<c:forEach items="${specialSchoolWeeks }" var="specialWeek1" varStatus="i">
									            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index ==1}">
									            			<c:set var="flag" value="1"/>
									            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
									            		</c:if>
									            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index !=1 && flag != 3}">
									            			<c:set var="flag" value="2"/>
									            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
									            		</c:if>
											 		</c:forEach>
										 		</c:if>
										 		<c:if test="${flag == 3}">
								            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" bgcolor="red"></td>
								            	</c:if>
								            	<c:if test="${flag == 4}">
								            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" bgcolor="green"></td>
								            	</c:if>
										 		<c:if test="${flag == 1}">
								            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" rowspan="5">${dayName }</td>
								            	</c:if>
								            	<c:if test="${showChosen ne 1}">
									            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4}">
									            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this">
									            			<c:if test="${roomId ne -1}">
									            				<%-- 0/${capacity} --%>
									            				<i class="fa fa-check" style="color:#1Abd9d" title="0/${capacity}"></i>
									            			</c:if>
									            		</td>
									            	</c:if>
								            	</c:if>
								            	<c:if test="${showChosen eq 1}">
								            		<c:if test="${isChosen eq 1 }">
										            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4}">
										            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this">
											            		<c:if test="${roomId ne -1}">
										            				<%-- 0/${capacity} --%>
										            				<i class="fa fa-check" style="color:#1Abd9d" title="0/${capacity}"></i>
										            			</c:if>
										            		</td>
										            	</c:if>
								            		</c:if>
								            		<c:if test="${isChosen eq 0 }">
										            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4}">
										            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this"></td>
										            	</c:if>
								            		</c:if>
								            	</c:if>
										 	</c:forEach>
							       		</tr>
							       	</c:forEach>
							     </c:forEach>
							     <c:if test="${schoolTermWeeks[0][11] != 7 && schoolTermWeeks[0][10] != 1}">
     								<c:forEach var="class1"  varStatus="cStatus1" begin="1" end="${schoolTermWeeks[0][11]}">
							       	<c:forEach var="class2" varStatus="cStatus2" begin="1" end="5">
							       		<tr>
							       			<c:if test="${cStatus2.index==1}">
							       				<c:if test="${cStatus1.index==1}">
										               <td rowspan="5">一</td>  
										            </c:if>
										            <c:if test="${cStatus1.index==2}">
										               <td rowspan="5">二</td>  
										            </c:if>
										            <c:if test="${cStatus1.index==3}">
										               <td rowspan="5">三</td>
										            </c:if>
										            <c:if test="${cStatus1.index==4}">
										               <td rowspan="5">四</td>  
										            </c:if>
										            <c:if test="${cStatus1.index==5}">
										               <td rowspan="5">五</td>  
										            </c:if>
										            <c:if test="${cStatus1.index==6}">
										               <td rowspan="5">六</td>
										            </c:if>
										            <c:if test="${cStatus1.index==7}">
										               <td rowspan="5">日</td>
										            </c:if> 
								               <th>1-2</th>
								            </c:if>
								            <c:if test="${cStatus2.index==2}">
								               <th>3-4</th>  
								            </c:if>
								            <c:if test="${cStatus2.index==3}">
								               <th>5-6</th>
								            </c:if>
								            <c:if test="${cStatus2.index==4}">
								               <th>7-8</th>
								            </c:if>
								            <c:if test="${cStatus2.index==5}">
										            <th>晚</th>
								            </c:if>
								            <c:set var="countWeek" value="0"/>
								              <c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
								            	<c:set var="countWeek" value="${countWeek+1 }"/>
								            	<c:set var="flag" value="0"/>
								            	<c:set var="dayName" value="0"/>
								            	<c:forEach items="${listLabTimetableAppointments }" var="currTimetable">
								            		<c:if test="${currWeek1[2] >= currTimetable.timetableAppointment.startWeek && currWeek1[2] <= currTimetable.timetableAppointment.endWeek
								            		&& currTimetable.timetableAppointment.weekday == cStatus1.index 
								            		&& cStatus2.index > currTimetable.timetableAppointment.startClass/2 && cStatus2.index <= currTimetable.timetableAppointment.endClass/2}">
								            				<c:if test="${currTimetable.timetableAppointment.schoolCourseDetail.courseDetailNo eq  schoolCourseDetail.courseDetailNo}">
								            					<c:set var="flag" value="4"/>
								            				</c:if>
								            				<c:if test="${currTimetable.timetableAppointment.schoolCourseDetail.courseDetailNo ne  schoolCourseDetail.courseDetailNo}">
								            					<c:set var="flag" value="3"/>
								            				</c:if>
									            	</c:if>
								            	</c:forEach>
								            	<c:if test="${not empty specialSchoolWeeks}">
									            	<c:forEach items="${specialSchoolWeeks }" var="specialWeek1" varStatus="i">
									            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index ==1}">
									            			<c:set var="flag" value="1"/>
									            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
									            		</c:if>
									            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index !=1 && flag != 3}">
									            			<c:set var="flag" value="2"/>
									            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
									            		</c:if>
											 		</c:forEach>
										 		</c:if>
										 		<c:if test="${flag == 3}">
								            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" bgcolor="red"></td>
								            	</c:if>
								            	<c:if test="${flag == 4}">
								            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" bgcolor="green"></td>
								            	</c:if>
										 		<c:if test="${flag == 1}">
								            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" rowspan="5">${dayName }</td>
								            	</c:if>
								            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4}">
								            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this"></td>
								            	</c:if>
										 	</c:forEach>
							       		</tr>
							       	</c:forEach>
							     </c:forEach>
							     </c:if>
							</table>
						</div>
					</div>
					<div class="student_list_check hide">
						<div class="grey_line bgw bg6d">
							<table>
								<tr>
									<td class="b wh ts ls1" id="groupName">第一批学生名单</td>
									<td class="b wh ts ls1 hide" id="addHint">移动学生
									</td>
								</tr>
							</table>
							<i class="fa fa-times wh ts r mt7 mr1p close2" title="关闭"></i>
							<table style="float:right;" class="mr12p">
								<tr>
									<td>
									    
										<button type="button" id="add" onclick="changeStudents(${group.id})">移动学生</button>
										<button type="button" id="finish" class="hide" onclick="finishAdd()">完成添加</button>
									</td>
								</tr>
							</table>
							<table style="float:right;">
								<tr>
									<td>
										<span class="wh"><font color="black">选中学生调整至:&nbsp;</font></span>
										<select id="selectGroup" name="selectGroup">
											<c:forEach items="${groups}" var="curr">
											   <option value="${curr.id }">${curr.groupName }</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</table>
						</div>

						<div class="grey_line">
							<table>
								<tr>
									<td>
										<span>学号:&nbsp;</span>
										<select id="username">
											<option>请选择</option>
											<option>2016060101</option>
											<option>2017060102</option>
										</select>
									</td>
								</tr>
							</table>
							<table>
								<tr>
									<td>
										<span>班级:&nbsp;</span>
										<select id="selectClass">
											<option>请选择</option>
											<option>xxxx001</option>
											<option>xxxx002</option>
										</select>
									</td>
								</tr>
							</table>
							<table>
								<tr>
									<td>
										<span>学院:&nbsp;</span>
										<select id="academy">
											<option>请选择</option>
											<option>通信工程学院</option>
											<option>机械学院</option>
										</select>
									</td>
								</tr>
							</table>
							<table>
								<tr>
									<td>
										<button type="button" onclick="query()">查询</button>
								
										<button type="button" onclick="cancelQuery()">取消</button>
									</td>
								</tr>
							</table>
						</div>
						<div class="bgwo w100p ptb10">
							<input type="hidden" name="groupId" id="groupId" />
							<table name="viewStudent" id="viewStudent" class="experimental_list_tab" cellspacing="0">
								<thead>
									<tr>
										<th>序号</th>
										<th>学号</th>
										<th>姓名</th>
										<th>班级</th>
										<th>学院</th>
										<th>是否冲突</th>
										<th>操作<input id="check_all" name="check_all" type="checkbox" onclick="checkAll();"/></th>
									</tr>
								</thead>
								<tbody id="body"></tbody>
								
							</table>
							<div class="page-message" id="studentPage">
								
							</div>
						</div>

					</div>
				</div>
				<div class="w25p r message_container">
					<div class="public_message">
						<div class="pub_tit">
							<div class="l">
								  <span class="b">${schoolCourseDetail.courseName }<span class="r1 ml10">
								<a href='javascript:void(0)'onclick='listTimetableStudents("${courseDetailNo}",0)'>名单:</a>${fn:length(schoolCourseDetail.schoolCourseStudents)}</span>人</span>
								<c:if test="${isPartComplete eq 1 }">
									<span class="bgb wh f13 p2">排课完成</span>
								</c:if>
								<c:if test="${isPartComplete ne 1 }">
									<span class="bgb wh f13 p2">正在排课</span>
								</c:if>
							</div>
							<button class="r"  onclick="turnPage()">返回</button>
							
							<c:if test="${isPartComplete eq 1 }">
								<button class="r" type="button" onclick="sendMessageByBacth('${courseDetailNo}',${batchId})">发送消息</button>
							</c:if>
							<c:if test="${isPartComplete ne 1 }">
								<button class="r" type="button" onclick="sendMessageAllMerge('${mergeId}')">发送消息</button>
							</c:if>
							<c:if test="${isComplete ne 1 }">
								<button class="r" type="button" onclick="completeAll('${courseDetailNo}')">全部完成</button>
							</c:if>
						</div>
						<div class="w85p mt15 ma">
							<div class="track example-1">
								<div class="inner">
									<!-- 实验项目  -->
									<div class="view-port">
										<div class="slider-container" id="example-1">
											<c:if test="${item eq -1}">
												<div class="item bgs wh b" onclick="selectItem(-1)">
													课程<input type="hidden" id="firstItem" value="-1"/>
												</div>
											</c:if>	
											<c:if test="${item ne -1}">
												<div class="item " onclick="selectItem(-1)">
													课程
												</div>
											</c:if>	
											<c:forEach items="${items}" var="curr" varStatus="i">
												<c:if test="${curr.id == item }">
													<div class="item bgs wh b" title="${curr.lpDepartmentHours }课时" onclick="selectItem(${curr.id})">
														${curr.lpName }<input type="hidden" id="firstItem" value="${curr.id }"/>
														<input id="selectItemOrder" type="hidden" value="${i.count }"/>
													</div>
												</c:if>
												<c:if test="${curr.id != item}">
													<div class="item " title="${curr.lpDepartmentHours }课时" onclick="selectItem(${curr.id})">
														${curr.lpName }
													</div>
												</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
								<div class="pagination">
									<a class="prev"><i class="fa fa-angle-double-left" title="上一页"></i></a>
									<a class="next"><i class="fa fa-angle-double-right" title="下一页"></i></a>
								</div>
							</div>
						</div>
						<script type="text/javascript">
							var track = $(".slider-container").silverTrack();
							var parent = track.container.parents(".track");

							track.install(new SilverTrack.Plugins.Navigator({
								prev: $("a.prev", parent),
								next: $("a.next", parent)
							}));

							track.install(new SilverTrack.Plugins.BulletNavigator({
								container: $(".bullet-pagination", parent)
							}));

							track.start();
						</script>
						
						<div class="track_detail">
							<div class="course_method cf" style="width:90%;height:auto;">
								<form>
									<div class="l">
									<input type="hidden" id="currpage" value="${currpage}"/>
										<input type="hidden" value="1" id="style"/>
										<input type="radio" class="student_course" name="course_method" onclick="changeStyle(1)" />
										<span >学生自选</span>
									</div>
									<div class="l">
										<input type="radio" checked="checked"  class="teacher_course" name="course_method" onclick="changeStyle(2)"/>
										<span>教师强排</span>
									</div>
									<div class="l">
										<input type="radio" class="teacher_course" name="course_method" onclick="changeStyle(3)"/>
										<span>自主分批</span>
									</div>
									<div class="l">
										<input type="radio" class="teacher_course" name="course_method" onclick="changeStyle(4)"/>
										<span>公选排课</span>
									</div>
								</form>
							</div>
							<c:if test="${isPartComplete  eq 0}">
							<div class="student_select">
								<c:forEach items="${groups }" var="group" varStatus="i">
								<c:if test="${group.timetableStyle == 22}">
								<div class="course_select cf" id="course_select${group.id }">
									<div>
										<c:if test="${groupId == group.id }">
											<input type="checkbox" class="check_btn" name="checkGroup" value="${group.id }" checked onclick="check(this)"/>
										</c:if>
										<c:if test="${groupId != group.id }">
											<input type="checkbox" class="check_btn" name="checkGroup" value="${group.id }" onclick="check(this)"/>
										</c:if>
										<span class="b o1">${group.groupName }&nbsp;&nbsp;&nbsp;</span>
										<span>人数：</span>
										<input type="text" class="check_text" id="groupnum${group.id}" value="${group.numbers}" readonly/>
									</div>
									<c:forEach items ="${ group.timetableAppointments}" var="curr">
										<c:set var="isThisItem" value="0"/>
											<c:forEach items="${curr.timetableItemRelateds }" var="selectItem">
												<c:if test="${selectItem.operationItem.id == item}">
													<c:set var="isThisItem" value="1"/>
												</c:if>
											</c:forEach>
										<c:if test="${isThisItem == 1 || item==-1}">
											<a class="fa fa-times r close" id="close${curr.id }" title="关闭" onclick="deleteSpecializedBasicCourseAppointment(${curr.id})"></a>
											<div class="course_basic_message" id="sample${curr.id }">
												<div class="cbm_box">
													<div>实验室：</div>
													<c:forEach items="${curr.timetableLabRelateds }" var="selectLab">
														${selectLab.labRoom.labRoomAddress}
													</c:forEach>
												</div>
												<div class="cbm_box">
													<div></div>
												</div>
												<div class="cbm_box">
													<div>星期：</div>
													星期${curr.weekday}
												</div>
												<div class="cbm_box">
													<div>周次：</div>
													${curr.startWeek}
												</div>
												<div class="cbm_box">
													<div>节次：</div>
													<c:if test="${curr.timetableAppointmentSameNumbers == null || fn:length(curr.timetableAppointmentSameNumbers) == 0}">
														第${curr.startClass }-${curr.endClass }节
													</c:if>
													<c:if test="${curr.timetableAppointmentSameNumbers != null && fn:length(curr.timetableAppointmentSameNumbers) != 0}">
														第
														<c:forEach items ="${curr.timetableAppointmentSameNumbers}" var="currSame" >
															${currSame.startClass }-${currSame.endClass },
														</c:forEach>
														节
													</c:if>
												</div>
												<div class="cbm_box">
													<div>教师：</div>
													<select id="teacher${curr.id }" multiple="true" class="chzn-select"  onchange="saveTeacher(${curr.id })">
														<option value="">请选择</option>
														<c:forEach items="${schoolCourseDetail.users }" var="currTea">
															<c:set value="0" var="flag"></c:set>
															<c:forEach items="${curr.timetableTeacherRelateds }" var="selectTea">
																<c:if test="${currTea.username eq selectTea.user.username }">
																	<c:set value="1" var="flag"></c:set>
																</c:if>
																
															</c:forEach>
															<c:if test="${flag eq 1 }">
																<option value="${currTea.username }" selected>${currTea.cname }</option>
															</c:if>
															<c:if test="${flag eq 0 }">
																<option value="${currTea.username }">${currTea.cname }</option>
															</c:if>
														</c:forEach>
													</select>
												</div>
												<div class="cbm_box">
													<div>开始：</div>
													<input style="width:40%;height:25px;float:left;margin:0 3%;" id="actualStartDate${curr.id }"  class="Wdate datepicker" value="<fmt:formatDate value="${curr.actualStartDate.time }" type="date" pattern="HH:mm"/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'HH:mm', isShowToday:false})" type="text" name="date"  onchange="saveActualStartDate(${group.id },${curr.id })"  readonly />
												</div>
												<div class="cbm_box">
													<div>结束：</div>
													<input style="width:40%;height:25px;float:left;margin:0 3%;" id="actualEndDate${curr.id }"  class="Wdate datepicker" value="<fmt:formatDate value="${curr.actualEndDate.time }" type="date" pattern="HH:mm"/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'HH:mm', isShowToday:false})" type="text" name="date"  onchange="saveActualEndDate(${group.id },${curr.id })"  readonly />
												</div>
											</div>
										</c:if>
									</c:forEach>
									<div class="sample">
									</div>
									<a class="student_list" title="点击查看学生名单" onclick="viewStudents(${group.id})">查看学生名单</a>
								</div>
								</c:if>
								</c:forEach>
							</div>
							
							<c:if test="${has_timetable eq 0}">
                                <button class="r mt5 mb10 mr5p" type="button" onclick="delGroup('${courseDetailNo}',${type}, ${item })">取消分批</button>
                            </c:if>
                            <c:if test="${has_timetable eq 1}">
                                <button class="r mt5 mb10 mr5p" type="button" onclick="alert('已经排课，无法取消分批')"><font color="white">取消分批</font></button>
                            </c:if>
							
							</c:if>
						
							<c:if test="${isPartComplete eq 1}">
							<div class="student_select">
								<c:forEach items="${groups }" var="group" varStatus="i">
								<c:if test="${group.timetableStyle == 22}">
								<div class="course_select cf" id="course_select${group.id }">
									<div>
										<input type="hidden" class="check_btn" name="checkGroup" value="${group.id }" />
											<span class="b o1">${group.groupName }&nbsp;&nbsp;&nbsp;</span>
										<span>人数：</span>
										<span>${group.numbers}</span>
										<input type="checkbox" class="check_btn_message" name="checkGroupMessage" value="${group.id }"/>
									</div>
									<div class="sample">
									</div>
									<c:forEach items ="${ group.timetableAppointments}" var="curr">
										<c:set var="isThisItem" value="0"/>
											<c:forEach items="${curr.timetableItemRelateds }" var="selectItem">
												<c:if test="${selectItem.operationItem.id == item}">
													<c:set var="isThisItem" value="1"/>
												</c:if>
											</c:forEach>
										<c:if test="${isThisItem == 1 || item==-1}">
											<div class="course_basic_message">
												<div class="cbm_box">
													<div>实验室：</div>
													<c:forEach items="${curr.timetableLabRelateds }" var="selectLab">
														${selectLab.labRoom.labRoomAddress}
													</c:forEach>
												</div>
												<div class="cbm_box">
													<div></div>
												</div>
												<div class="cbm_box">
													<div>星期：</div>
													星期${curr.weekday}
												</div>
												<div class="cbm_box">
													<div>周次：</div>
													${curr.startWeek}
												</div>
												<div class="cbm_box">
													<div>节次：</div>
													<c:if test="${curr.timetableAppointmentSameNumbers == null || fn:length(curr.timetableAppointmentSameNumbers) == 0}">
														第${curr.startClass }-${curr.endClass }节
													</c:if>
													<c:if test="${curr.timetableAppointmentSameNumbers != null && fn:length(curr.timetableAppointmentSameNumbers) != 0}">
														第
														<c:forEach items ="${curr.timetableAppointmentSameNumbers}" var="currSame" >
															${currSame.startClass }-${currSame.endClass },
														</c:forEach>
														节
													</c:if>
												</div>
												<div class="cbm_box">
													<div>教师：</div>
														<c:forEach items="${curr.timetableTeacherRelateds }" var="selectTea">
															${selectTea.user.cname }
														</c:forEach>
												</div>
												<div class="cbm_box">
													<div>开始：</div>
													<fmt:formatDate value="${curr.actualStartDate.time }" type="date" pattern="HH:mm"/>
												</div>
												<div class="cbm_box">
													<div>结束：</div>
													<fmt:formatDate value="${curr.actualEndDate.time }" type="date" pattern="HH:mm"/>
												</div>
											</div>
										</c:if>
									</c:forEach>
									<a class="student_list" title="点击查看学生名单" onclick="viewStudents(${group.id})">查看学生名单</a>
								</div>
								</c:if>
								</c:forEach>
							</div>
							</c:if>
						
							<div class="w100p ovh">
								<c:if test="${isPartComplete  eq 0 }">
									<button class="r mt5 mb10 mr5p" type="button" onclick="completeBatchTimetable(${batchId})">完成</button>
								</c:if>
								<c:if test="${isPartComplete eq 1 }">
									<button class="r mt5 mb10 mr5p" type="button" onclick="sendDetailMessageByGroupMerge('${mergeId}')">发送消息</button>
									<button class="r mt5 mb10 mr5p" type="button" onclick="modifyBatchTimetable(${batchId})">修改</button>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
			</form>
		</div>
		</div>
		
		<div id="batch_chzn" class="easyui-window " title="批次复制" align="left" title="" modal="true" 
			maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 500px; height: 300px;">
			<div class="content-box">
				<table>
					<ul>
						<li>
							<b>选择将要复制分批</b>
						</li>
						<li>
							请选择分批：
							<select id="batch_chose" class="chosen-select">
				                <c:forEach items="${batches}" var="batch">
				                	<option value="${batch.id}">${batch.operationItem.lpName}</option>
				                </c:forEach>
							</select>
							<input type="button" value="确定复制" onclick="copy_batch()">
						</li>
					</ul>
				</table>
			</div>
				<!-- 查看学生名单 -->
						<div id="doSearchStudents" class="easyui-window" title="查看学生名单" modal="true"	closed="true" iconCls="icon-add" style="width:1000px;height:500px">
					</div>
		</div>
		
		<style>
			.timetable_tab .timetable_th {
				background-image: url(../images/img/timetanble2.png);
			}
			
			.timetable_tab tr:hover {
				background: #fff;
			}
			
			.tt_pa1 {
				position: absolute;
				right: 1%;
				top: -3%;
			}
			
			.tt_pa2 {
				position: absolute;
				right: 3%;
				top: 16%;
			}
			
			.tt_pa3 {
				position: absolute;
				right: 10%;
				top: 38%;
			}
			
			.tt_pa4 {
				position: absolute;
				right: 39%;
				top: 62%;
			}
			
			.tt_pa5 {
				position: absolute;
				left: 2%;
				bottom: 2%;
			}
			.input_text {
			    float: left;
			    width: 40px;
			    height: 16px;
			    border: 1px solid #bbb;
			    border-radius: 3px;
			    margin: 0 5px 0 0;
			}
			
			.selected-week {
				background: yellow;
			}
		</style>
		<script type="text/javascript">
		$(function(){
				var $height =$(".message_container").height();
				if($height>1278){
					$(".message_container").css("height","1278px");
			 		$(".message_container").css("overflow-y","scroll");
				}
			 	
			})
		//全选
		function checkAll()
		{
		  if($("#check_all").is(":checked"))
		  {
		    $("input:checkbox[name^=L]:checkbox").prop("checked", true);
		  }
		  else
		  {
		    $("input:checkbox[name^=L]:checkbox").prop("checked", false);
		  }
		}
		
		var selectGroup = -1;
		 $(document).ready(function(){
			 track.goToPage(parseInt($("#selectItemOrder").val()/4)+parseInt(1));
			 if($("#groupId").val() != null && $("#groupId").val() != ""){// 如果勾选了组，那么进行判冲；不勾选则不判冲
			 	// 此处学生判冲
			 	generateConflict();
			 }
		 });
		 
		// 学生判冲
		 function generateConflict(){
			 var els =document.getElementsByName("isChosen");
		 	 var chosenWeeks = new Array();
			 for (var i = 0, j = els.length; i < j; i++){
				if(els[i].value == 1){
					chosenWeeks.push(i+1);
				};
			 }
			 // 找到已经选择的分组
			 var groupId = $("#groupId").val();
              $.ajax({
                    url:"${pageContext.request.contextPath}/newtimetable/findConflictTime?groupId="+groupId,
                    data:{chosenWeeks:chosenWeeks.join(",")},
                    type:"POST",
                    success:function(data){//AJAX查询成功
                        if($("#groupnum"+groupId).val()!=null && $("#groupnum"+groupId).val()!=''){
                            $('.choose_this').each(function(){
                                /* $(this).html("0/"+$("#groupnum"+groupId).val()); */
                                $(this).html("<i class='fa fa-check' style='color:#1Abd9d' title='0/"+$("#total"+groupId).val()+"'></i>");
                             });
                        }
                        for(var t in data) {
                             var time = t;
                             var num = data[t];
                             //$("#"+time).removeClass("choose_this");
                             if($("#"+time).hasClass("choose_this"))
                             {
                                 
                                 var totalNumber = 0;
                                 if($("#groupnum"+groupId).val()!=null && $("#groupnum"+groupId).val()!=''){// 当前组下的人数
                                     totalNumber = $("#groupnum"+groupId).val();
                                 }else{// 实验室容量
                                     totalNumber = ${capacity};
                                 }
                                 /* if(num/totalNumber>=0.8){
                                   //$("#"+time).html("");
                                   //$("#"+time).removeClass("choose_this").css("background-color","grey");
                                   $("#"+time).html(num+"/"+totalNumber);
                               }else */ 
                               if(num/totalNumber<=0.2){
                                   //$("#"+time).html("闲");
                                  // $("#"+time).html(num+"/"+totalNumber);
                                   $("#"+time).html("<i class='fa fa-bell-o' style='color:#ffb400' title='"+num+"/"+totalNumber+"' ></i>");
                               }else{
                                   //$("#"+time).html(num+"/"+totalNumber);
                                   $("#"+time).html("<i class='fa fa-exclamation' style='color:#fb3838' title='"+num+"/"+totalNumber+"'></i>");
                               }
                                 
                             }
                        }  
                    }
             });
          }
		 
			$(".quickbtn").click(
				function() {
					$(".quick_above").addClass("block");
				}
			);
			$(document).bind("click", function() {
				$('.quick_above').removeClass("block");
			});
			$(".quickbtn").click(function(event) {
				event.stopPropagation();

			});
			$(".quick_above").click(function(event) {
				event.stopPropagation();

			});
			$(window).scroll(function() {
				$(".quick_above").removeClass("block");
			});
			/*$(".close").click(
				function() {
					$(".course_select").addClass("hide");
				}
			);*/
			$(".close2").click(
				function() {
					$(".normal").removeClass("hide").addClass("block");
					$(".student_list_check").removeClass("block").addClass("hide");
				}
			);
			$(".student_list").click(
				function() {
					$(".normal").removeClass("block").addClass("hide");
					$(".student_list_check").removeClass("hide").addClass("block");
				}
			);
			function viewStudents(id){
				/* $.ajax({
					url:"${pageContext.request.contextPath}/newtimetable/setTimetableGroupStudentsByGroupId?groupId="+id,
					type:"POST",
					success:function(data){//AJAX查询成功 */
						selectGroup = id;
						//$("#selectGroup option[value='"+ id + "']").remove(); 
	
						$("#groupId").val(id);
						$("#selectGroup").val(id);
						$(".normal").removeClass("block").addClass("hide");
						$(".student_list_check").removeClass("hide").addClass("block");
						$.ajax({
						           url:"${pageContext.request.contextPath}/newtimetable/findTimetableGroupStudentsByGroupId?groupId="+id+"&page=1",
						           
						           type:"POST",
						           success:function(data){//AJAX查询成功
						                  document.getElementById("body").innerHTML=data.content;
						            		document.getElementById("studentPage").innerHTML=data.p;
						            		$("#username").html(data.username);
											$("#username").trigger("liszt:updated");	
											$("#selectClass").html(data.schoolClass);
											$("#selectClass").trigger("liszt:updated");		
											$("#academy").html(data.academy);
											$("#academy").trigger("liszt:updated");
											$("#groupName").html(data.groupName+"学生名单");
						                    $("#total"+id).val(data.total);
						           }
						});
				/* 	}
				});
				 */
			}
			//移动学生
			function changeStudents(){
					form.action= "${pageContext.request.contextPath}/newtimetable/changeStudents?type=0";
					form.submit();
			}
			function changeGroup(){
			 var groupId = $("#selectGroup").val();
			 viewStudents(groupId);
			}
			function getStudents(id,page){
				var username =$("#username").val();
                var selectClass =$("#selectClass").val();
                var academy =$("#academy").val();
                if(page==-1){// 取消
					username ='';
	                selectClass ='';
	                academy ='';
                }
                $.ajax({
                           url:"${pageContext.request.contextPath}/newtimetable/findTimetableGroupStudentsByGroupId?groupId="+selectGroup+"&page="+page+"&username="+username+"&selectClass="+selectClass+"&academy="+academy,
                           
				           type:"POST",
				           success:function(data){//AJAX查询成功
				                  document.getElementById("body").innerHTML=data.content;
				            		document.getElementById("studentPage").innerHTML=data.p;
				            		$("#username").html(data.username);
									$("#username").trigger("liszt:updated");
									
									$("#selectClass").html(data.schoolClass);
									$("#selectClass").trigger("liszt:updated");
									
									$("#academy").html(data.academy);
									$("#academy").trigger("liszt:updated");
									
									$("#groupName").html(data.groupName+"学生名单");
				           }
				});
			}
			
			function addStudents(){
				$("#groupName").addClass("hide");
				$("#addHint").removeClass("hide");
				$("#add").addClass("hide");
				$("#finish").removeClass("hide");
				$.ajax({
				           url:"${pageContext.request.contextPath}/newtimetable/addStudentsInMergeCourse?groupId="+selectGroup+"&page=1&isMerge=0",
				           
				           type:"POST",
				           success:function(data){//AJAX查询成功
				                document.getElementById("body").innerHTML=data.content;
				            		document.getElementById("studentPage").innerHTML=data.p;
				            		$("#username").html(data.username);
									$("#username").trigger("liszt:updated");
									
									$("#selectClass").html(data.schoolClass);
									$("#selectClass").trigger("liszt:updated");
									
									$("#academy").html(data.academy);
									$("#academy").trigger("liszt:updated");
									
									$("#groupName").html(data.groupName+"学生名单");
				           }
				});
			}
			
			function addThisStudent(username){
				$.ajax({
				           url:"${pageContext.request.contextPath}/newtimetable/addThisStudent?username="+username+"&groupId="+selectGroup,
				           
				           type:"POST",
				           success:function(data){//AJAX查询成功
				           	addStudents();
				           }
				});
			}
			function finishAdd(){
				 viewStudents(selectGroup);  
			}
			
			function getAddStudents(id,page){
				$.ajax({
				           url:"${pageContext.request.contextPath}/newtimetable/addStudentsInMergeCourse?groupId="+id+"&page="+page+"&isMerge=0",
				           
				           type:"POST",
				           success:function(data){//AJAX查询成功
				                  document.getElementById("body").innerHTML=data.content;
				            		document.getElementById("studentPage").innerHTML=data.p;
				            		$("#username").html(data.username);
									$("#username").trigger("liszt:updated");
									
									$("#selectClass").html(data.schoolClass);
									$("#selectClass").trigger("liszt:updated");
									
									$("#academy").html(data.academy);
									$("#academy").trigger("liszt:updated");
									
									$("#groupName").html(data.groupName+"学生名单");
				           }
				});
			}
			function query(){
				var username =$("#username").val();
				var selectClass =$("#selectClass").val();
				var academy =$("#academy").val();
				$.ajax({
				           url:"${pageContext.request.contextPath}/newtimetable/findTimetableGroupStudentsByGroupId?groupId="+selectGroup+"&page=1&username="+username+"&selectClass="+selectClass+"&academy="+academy,
				           
				           type:"POST",
				           success:function(data){//AJAX查询成功
				                  document.getElementById("body").innerHTML=data.content;
				            		document.getElementById("studentPage").innerHTML=data.p;
				            		$("#username").html(data.username);
									$("#username").trigger("liszt:updated");
									
									$("#selectClass").html(data.schoolClass);
									$("#selectClass").trigger("liszt:updated");
									
									$("#academy").html(data.academy);
									$("#academy").trigger("liszt:updated");
							
									$("#groupName").html(data.groupName+"学生名单");
				           }
				});
			}
			
			function cancelQuery(){
				// 取消标志   page为-1
				getStudents(selectGroup,-1);
			}
			$(".timetable_tab td").click(
				function() {
					$(".course_select").removeClass("hide").addClass("block");
				}
			);
			$(".item").click(
				function() {
					$(this).addClass("b bgs wh").removeClass("").siblings().removeClass("b bgs wh");
				}
			);
			
			var itemId = $("#firstItem").val();
			if(itemId == null) itemId = -1;
			function selectItem(id){
				itemId = id;
				// 将选中的item值赋给input框，方便后台request传值
				$("#item").val(id);
				// ajax 判断是否该课程下的其他项目已经有此种排课类型的分批
				$.ajax({
					url:"${pageContext.request.contextPath}/newtimetable/ifBatchCopyReady?courseDetailNo=${courseDetailNo}&itemId="+itemId+"&type=${type}",
                    type:'GET',
                    success:function(data){
                    	if(data=="yes"){// 可以复制
                            $.messager.confirm('提示','是否要复制已存在的分批？',function(r){
                                if(r) {// 若选择复制，则弹框
                                    $("#batch_chzn").window({left:"200px", top:"200px"});   
                                    $("#batch_chzn").window('open');
                                }else{// 若不选择复制，则直接进后台，自动生成
                                    document.form.action="${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetableTeacher?courseDetailNo=${courseDetailNo}"
                                    document.form.submit();
                                }
                            });
                        }else{// 不可以复制
                            document.form.action="${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetableTeacher?courseDetailNo=${courseDetailNo}"
                            document.form.submit();
                        }
                    }
				});
			}
			
			// 复制分批
			function copy_batch(){
				$.ajax({
					url:"${pageContext.request.contextPath}/newtimetable/copyBatch?itemId="+itemId+"&sourceBatchId="+$("#batch_chose").val(),
                    type:'GET',
                    success:function(data){
                    	if(data=="success"){// 复制完成
                            document.form.action="${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetableTeacher?courseDetailNo=${courseDetailNo}"
                            document.form.submit();
                        }
                    }
				});
			}
			     /*
 			 *查看学生名单
 			 */
			function listTimetableStudents(courseDetailNo,merge) {
				var sessionId = $("#sessionId").val();
				var con = '<iframe scrolling="yes" id="message1" frameborder="0"  src="${pageContext.request.contextPath}/timetable/openSearchStudent?courseDetailNo='
			    + courseDetailNo + '&merge='+merge+'" style="width:100%;height:100%;"></iframe>'
				$('#doSearchStudents').html(con);
				$("#doSearchStudents").show();
				//获取当前屏幕的绝对位置
   			 var topPos = window.pageYOffset;
    			//使得弹出框在屏幕顶端可见
   			 $('#doSearchStudents').window({left:"px", top:topPos+"px"});
				$('#doSearchStudents').window('open');
			}
			function check(obj) {
		    $('.check_btn').each(function () {
		        if (this != obj)
		            $(this).attr("checked", false);
		        else {
		            if ($(this).prop("checked"))
		                $(this).attr("checked", true);
		            else
		                $(this).attr("checked", false);
		            $("#groupId").val(obj.value);
		            
		            // document.form.submit(); // 选择分组时，不要跳转页面，只做学生判冲   贺子龙修改
				 	generateConflict();
		            
		        }
		    });
		}
			
			$(".choose_this").click(
				function(e) {
						 var obj = document.getElementsByName("checkGroup");
						 var checkNum = 0;
						 var checkObj;
						 for(i=0;i<obj.length;i++){
						   		if(obj[i].checked){
						   			checkNum++;
						   			checkObj = obj[i];
						   		}
						}
						 var id = $(e.target).attr('id');
						var s = id.split("-");
						 if(${roomId} == -1){
						 }
						 else if(checkNum  == 0 && $(e.target).hasClass("choose_this")){
						  alert("请先勾选排课组号！");
						 }
						  else{
						  	if($(e.target).hasClass("choose_this")){
						  	var group = checkObj.value;
						  	//教师先不管
							$.ajax({
						         url:"${pageContext.request.contextPath}/newtimetable/saveSingleSpecializedBasicCourseTimetable?courseDetailNo=${courseDetailNo}",
						        type:'POST',
		            	            data:{term:${schoolTerm.id},week:s[0], weekday:s[1],selectClass:s[2],group: group,labRoom:${roomId}
			         , teacher:s[2],item:itemId},
		            	            error:function (request){
		            	              alert('请求错误!');
		            	         },
						         success:function(data)
						         {
						         	if(data.result == "-1"){
						         		alert("该时间段已经被其他排课占用，请您添加其他时间段排课");
						         	}
						         	else{
						         			$(e.target).removeClass("choose_this");
											$(e.target).css("backgroundColor", "green");
											$(e.target).html("");
						         			//window.location.reload();
						         			if(data.oldAppointmentId == -1){
												 var $input1 = $("星期"+s[1]);
												 var $sd1 = $("<div>星期：</div>");
												 var $input2 = $("第"+s[0]+"周");
												 var $sd2 = $("<div>周次：</div>");
												 var $input3 = $((parseInt(s[2])*2-1)+"-"+(parseInt(s[2])*2)+"节");
												 var $sd3 = $("<div>节次：</div>");
												 var $input4 = $("星期"+s[0]);
												 var $sd4 = $("<div>教师：</div>");
											  	 var $div1 = $("<div class='cbm_box'>"+"星期"+s[1]+"</div>");	
											  	 var $div2 = $("<div class='cbm_box'>"+s[0]+"</div>");	
											  	 var $div3 = $("<div class='cbm_box'>第"+(parseInt(s[2])*2-1)+"-"+(parseInt(s[2])*2)+"节"+"</div>");	
											  	 var $div4 = $("<div class='cbm_box'></div>");	
											  	 var $div5 = $("<div class='cbm_box'></div>");	
											  	 var $div6 = $("<div class='cbm_box'></div>");	
											  	 var $sd5 = $("<div>开始：</div>");
											  	 var $sd6 = $("<div>结束：</div>");
												$div1.append($sd1);
												$div1.append($input1);
												
												$div2.append($sd2);
												$div2.append($input2);
												
												$div3.append($sd3);
												$div3.append($input3);
												
												$div4.append($sd4);
												$div4.append(data.teacher);
												
												$div5.append($sd5);
												$div5.append(data.actualStartDate);
												
												$div6.append($sd6);
												$div6.append(data.actualEndDate);
												var $div = $("<div class='course_basic_message sample' id='sample"+data.result+"'></div>");
												
												var $selectGroup = $("<input name='selectGroup' type='hidden' value='"+group+"'/>");
												var $week = $("<input name='selectWeek' type='hidden' value='"+s[0]+"'/>");
												var $weekday = $("<input name='selectWeekday' type='hidden' value='"+s[1]+"'/>");
												var $classes = $("<input name='selectClass' type='hidden' value='"+s[2]+"'/>");
												
												
												$div.append($div1);
												$div.append($div2);
												$div.append($div3);
												$div.append($div4);
												$div.append($div5);
												$div.append($div6);
												
												$div.append($week);
												$div.append($weekday);
												$div.append($classes);
												$div.append($selectGroup);
												$("#course_select"+checkObj.value).find(".sample:last").after($div);
												$("#course_select"+checkObj.value).find("#sample"+data.result).before(data.close);
												
						         			}
						         			else{
						         				 $("#close"+data.oldAppointmentId).hide();
						         				 $("#sample"+data.oldAppointmentId).hide();
						         				 var $input1 = $("星期"+s[1]);
												 var $sd1 = $("<div>星期：</div>");
												 var $input2 = $("第"+s[0]+"周");
												 var $sd2 = $("<div>周次：</div>");
												 var $input3 = $(data.classes+"节");
												 var $sd3 = $("<div>节次：</div>");
												 var $input4 = $("星期"+s[0]);
												 var $sd4 = $("<div>教师：</div>");
											  	 var $div1 = $("<div class='cbm_box'>"+"星期"+s[1]+"</div>");	
											  	 var $div2 = $("<div class='cbm_box'>"+s[0]+"</div>");	
											  	 var $div3 = $("<div class='cbm_box'>第"+data.classes+"节"+"</div>");	
											  	 var $div4 = $("<div class='cbm_box'></div>");	
											  	 var $div5 = $("<div class='cbm_box'></div>");	
											  	 var $div6 = $("<div class='cbm_box'></div>");	
											  	 var $sd5 = $("<div>开始：</div>");
											  	 var $sd6 = $("<div>结束：</div>");
												$div1.append($sd1);
												$div1.append($input1);
												
												$div2.append($sd2);
												$div2.append($input2);
												
												$div3.append($sd3);
												$div3.append($input3);
												
												$div4.append($sd4);
												$div4.append(data.teacher);
												
												$div5.append($sd5);
												$div5.append(data.actualStartDate);
												
												$div6.append($sd6);
												$div6.append(data.actualEndDate);
												var $div = $("<div class='course_basic_message sample' id='sample"+data.result+"'></div>");
												
												var $selectGroup = $("<input name='selectGroup' type='hidden' value='"+group+"'/>");
												var $week = $("<input name='selectWeek' type='hidden' value='"+s[0]+"'/>");
												var $weekday = $("<input name='selectWeekday' type='hidden' value='"+s[1]+"'/>");
												var $classes = $("<input name='selectClass' type='hidden' value='"+s[2]+"'/>");
												
												
												$div.append($div1);
												$div.append($div2);
												$div.append($div3);
												$div.append($div4);
												$div.append($div5);
												$div.append($div6);
												
												$div.append($week);
												$div.append($weekday);
												$div.append($classes);
												$div.append($selectGroup);
												$("#course_select"+checkObj.value).find(".sample:last").after($div);
												$("#course_select"+checkObj.value).find("#sample"+data.result).before(data.close);
						         			}
						         		
						         	}
						         }
							});
						 }
						
					}
				}
			);
			
			function doSpecializedBasicCourseTimetable(){
				var weeks = new Array();
				var weekdays = new Array();
				var classes = new Array();
				var groups = new Array();
				var selectWeek = document.getElementsByName("selectWeek");
				for(i=0;i<selectWeek.length;i++){
				   weeks.push(selectWeek[i].value);
				}
				var selectWeekday = document.getElementsByName("selectWeekday");
				for(i=0;i<selectWeekday.length;i++){
				   weekdays.push(selectWeekday[i].value);
				}
				var selectClass = document.getElementsByName("selectClass");
				for(i=0;i<selectClass.length;i++){
				   classes.push(selectClass[i].value);
				}
				
				
				var selectGroup = document.getElementsByName("selectGroup");
				for(i=0;i<selectGroup.length;i++){
				   groups.push(selectGroup[i].value);
				}
				var basicCourseStart=$("#basicCourseStart").val();
				var basicCourseEnd=$("#basicCourseEnd").val();
				//教师先不管
				$.ajax({
			         url:"${pageContext.request.contextPath}/newtimetable/saveSpecializedBasicCourseTimetable?courseDetailNo=${courseDetailNo}",
			         dataType:"json",
			         type:'GET',
			         data:{term:${schoolTerm.id},weeks:weeks.join(","), weekdays:weekdays.join(","),classes:classes.join(","),groups: groups.join(","),labRoom:${roomId}
			         , teachers:classes.join(","),item:itemId,basicCourseStart:basicCourseStart,basicCourseEnd:basicCourseEnd},
			         complete:function(result)
			         {
			          	
			          }
			});
			}
			
			function changeStyle(style){
				if(style == 1)
				{
					document.form.action = "${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetable?courseDetailNo=${courseDetailNo}&currpage=${currpage}";
				}
				else if(style == 2){
					document.form.action = "${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetableTeacher?courseDetailNo=${courseDetailNo}&currpage=${currpage}";
				}
				else if(style == 3){
					document.form.action = "${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetableSelf?courseDetailNo=${courseDetailNo}&currpage=${currpage}";
				}
				else{
					document.form.action = "${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetablePublic?courseDetailNo=${courseDetailNo}&currpage=${currpage}";
				}
				document.form.submit();
			}
			
			function queryLab(){
				$("#groupId").val("");
	            document.form.submit();
			}
			
			$(".select-week").click(
				function() {
					$("#week"+$(this).html()).val(1);
					$("#showChosen").val(1);
					document.form.submit();
				}
			);
			
			function cancel(){
				window.location.href = "${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetableTeacher?courseDetailNo=${courseDetailNo}";
			}
		</script>
		<script src="${pageContext.request.contextPath}/chosen/chosen_width.jquery.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		    var config = {
		      '.chzn-select'           : {width:"70%", search_contains : true},
		      '.chzn-select-deselect'  : {allow_single_deselect:true},
		      '.chzn-select-no-single' : {disable_search_threshold:10},
		      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
		      '.chzn-select-width'     : {width:"70%"}
		    }
		
		    for (var selector in config) {
		      $(selector).chosen(config[selector]);
		    }
		</script>
	</body>

</html>