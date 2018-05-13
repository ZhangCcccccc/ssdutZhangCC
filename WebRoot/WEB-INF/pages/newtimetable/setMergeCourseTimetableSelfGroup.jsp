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
		<!-- 弹窗 -->
  		<script src="${pageContext.request.contextPath}/js/layer-v2.2/layer/layer.js" type="text/javascript"></script>
  		<script src="${pageContext.request.contextPath}/js/layer-v2.2/layer/extend/layer.ext.js" type="text/javascript"></script>
		
		<!-- 下拉框的样式 -->
 		 <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  		<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  		<!-- 下拉的样式结束 -->
	</head>

	<body>
		<div id="bgheight">
			<script type="text/javascript">
				$(function() {
					$("#bgheight").height($(window).height() / 1);
				})
			</script>
			<form name="form"
								action="${pageContext.request.contextPath}/newtimetable/doMergeCourseTimetableSelf?mergeId=${mergeId}"
								method="post">
			<div class="all_main_content ovh">
				<div class="w74p l">
					<div class="normal">
						<div class="gray_line">
							
						<table style="width:400px;">
							<tr>
								<td>
									<span>实验室:&nbsp;</span>
									<select name="labroom" class="chzn-select" style="width:300px;">
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
									<input type="hidden" value="${item }" id="item" name="item"/>
									<input type="hidden" value="${groupId}" id="groupId" name="groupId"/>
									<input type="hidden" value="0" id="showChosen" name="showChosen"/>
								</td>
							</tr>
						</table>
						<table>
							<tr>
							<td>
								<button type="button" onclick="queryLab()">查询</button>
								<button>取消</button>
							</td>
							</tr>
						</table>
							<!--<span class="o1 b r mr2p">40</span>
						<span class="o1 b r">容量：</span>
						<span class="b8e ls1 b r mr2p">EII-202电子线路实验室</span>-->
						</div>
						<div class="bgwo w100p ptb10">
							<table class="timetable_tab" cellspacing="0">
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
								            	<c:set var="countWeek" value="${countWeek+1 }"/>
								            	<c:set var="flag" value="0"/>
								            	<c:set var="dayName" value="0"/>
								            		<c:set var="isChosen" value="0"/>
										 		<c:forEach items="${weeks }" var="curr">
										 			<c:if test="${curr eq currWeek1[2] }">
										 				<c:set var="isChosen" value="1"/>
										 			</c:if>
										 		</c:forEach>
								            	<c:forEach items="${listLabTimetableAppointments }" var="currTimetable">
								            		<c:if test="${currTimetable.timetableAppointment.timetableAppointmentSameNumbers eq null || fn:length(currTimetable.timetableAppointment.timetableAppointmentSameNumbers) == 0 }">
								            			<c:if test="${currWeek1[2] >= currTimetable.timetableAppointment.startWeek && currWeek1[2] <= currTimetable.timetableAppointment.endWeek
									            		&& currTimetable.timetableAppointment.weekday == cStatus1.index 
									            		&& cStatus2.index > currTimetable.timetableAppointment.startClass/2 && cStatus2.index <= currTimetable.timetableAppointment.endClass/2}">
									            				<c:if test="${currTimetable.timetableAppointment.schoolCourseMerge.id eq  schoolCourseMerge.id }">
									            					<c:set var="flag" value="4"/>
									            				</c:if>
									            				<c:if test="${currTimetable.timetableAppointment.schoolCourseMerge.id  ne  schoolCourseMerge.id }">
									            					<c:set var="flag" value="3"/>
									            				</c:if>
										            	</c:if>
								            		</c:if>
								            		<c:if test="${currTimetable.timetableAppointment.timetableAppointmentSameNumbers ne null &&  fn:length(currTimetable.timetableAppointment.timetableAppointmentSameNumbers) > 0 }">
								            			<c:forEach items = "${currTimetable.timetableAppointment.timetableAppointmentSameNumbers}" var="currSame">
								            				<c:if test="${currWeek1[2] >= currSame.startWeek && currWeek1[2] <= currSame.endWeek
											            		&& currTimetable.timetableAppointment.weekday == cStatus1.index 
											            		&& cStatus2.index > currSame.startClass/2 && cStatus2.index <= currSame.endClass/2}">
											            				<c:if test="${currTimetable.timetableAppointment.schoolCourseMerge.id  eq  schoolCourseMerge.id }">
											            					<c:set var="flag" value="4"/>
											            				</c:if>
											            				<c:if test="${currTimetable.timetableAppointment.schoolCourseMerge.id  ne schoolCourseMerge.id }">
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
								            		<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId == null}">
									            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this"></td>
									            	</c:if>
									            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId != null}">
									            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this">0/${labRoom.labRoomCapacity }</td>
									            	</c:if>
								            	</c:if>
								            	<c:if test="${showChosen eq 1}">
								            		<c:if test="${isChosen eq 1 }">
								            			<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId == null}">
										            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this"></td>
										            	</c:if>
										            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId != null}">
										            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this">0/${labRoom.labRoomCapacity }</td>
										            	</c:if>
								            		</c:if>
								            		<c:if test="${isChosen eq 0 }">
								            			<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId == null}">
										            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this"></td>
										            	</c:if>
										            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId != null}">
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
								            	<c:if test="${showChosen ne 1}">
								            		<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId == null}">
									            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this"></td>
									            	</c:if>
									            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId != null}">
									            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this">0/${labRoom.labRoomCapacity }</td>
									            	</c:if>
								            	</c:if>
								            	<c:if test="${showChosen eq 1}">
								            		<c:if test="${isChosen eq 1 }">
								            			<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId == null}">
										            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this"></td>
										            	</c:if>
										            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId != null}">
										            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this">0/${labRoom.labRoomCapacity }</td>
										            	</c:if>
								            		</c:if>
								            		<c:if test="${isChosen eq 0 }">
								            			<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId == null}">
										            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this"></td>
										            	</c:if>
										            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4 && groupId != null}">
										            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this"></td>
										            	</c:if>
								            		</c:if>
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
									<td class="b wh ts ls1 hide" id="addHint">添加学生</td>
								</tr>
							</table>
							<i class="fa fa-times wh ts r mt7 mr1p close2" title="关闭"></i>
							<table style="float:right;" class="mr12p">
								<tr>
									<td>
										<button type="button" onclick="changeGroup()">确定</button>
										<button type="button" id="add" onclick="addStudents()">添加学生</button>
										<button type="button" id="finish" class="hide" onclick="finishAdd()">完成添加</button>
									</td>
								</tr>
							</table>
							<table style="float:right;">
								<tr>
									<td>
										<span class="wh">调整至:&nbsp;</span>
										<select id="selectGroup">
											<option value="">请选择</option>
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
							<table class="experimental_list_tab" cellspacing="0">
								<thead>
									<tr>
										<th>序号</th>
										<th>学号</th>
										<th>姓名</th>
										<th>班级</th>
										<th>学院</th>
										<th>是否冲突</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="body"></tbody>
								<!-- <tr>
									<td class="tc">1</td>
									<td class="tc">2016060101</td>
									<td class="tc">xxx</td>
									<td class="tc">150101</td>
									<td>通信工程学院</td>
									<td class="tc red">是</td>
								</tr>
								<tr>
									<td class="tc">2</td>
									<td class="tc">2016060102</td>
									<td class="tc">xx</td>
									<td class="tc">150102</td>
									<td>机械学院</td>
									<td class="tc">否</td>
								</tr> -->
							</table>
							<div class="page-message" id="studentPage">
								<!-- <a class="btn" href="#">末页</a>
								<a class="btn" href="#">下一页</a>
								<div class="page-select">
									<div class="page-word">页</div>
									<form>
										<select class="page-number">
											<option selected="1">1</option>
											<option>2</option>
											<option>3</option>
										</select>
									</form>
									<div class="page-word">第</div>
								</div>
								<a class="btn" href="#">上一页</a>
								<a class="btn" href="#">首页</a>
								<div class="p-pos">
									6条记录 • 共1页
								</div> -->
							</div>
						</div>

					</div>
				</div>
				<div class="w25p r">
					<div class="public_message">
						<div class="pub_tit">
							<div class="l">
								<span class="b">${schoolCourseMerge.courseName }<span class="r1 ml10">
								<a href='javascript:void(0)'onclick='listTimetableStudents("${courseDetailNo}",1)'>名单:</a>
								${schoolCourseMerge.studentNumbers}</span>人</span>
								<c:if test="${isComplete eq 1 }">
									<span class="bgb wh f13 p2">排课完成</span>
								</c:if>
								<c:if test="${isComplete ne 1 }">
									<span class="bgb wh f13 p2">正在排课</span>
								</c:if>
							</div>
							<button class="r" type="button" onclick="turnPage()">返回</button>
						</div>
						<div class="w85p mt15 ma">
							<div class="track example-1">
								<div class="inner">

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
							//
						</script>
						<div class="track_detail">
							<div class="course_method" style="width:90%">
								<form>
									<div class="l">
										<input type="hidden" value="1" id="style"/>
										<input type="radio" class="student_course" name="course_method" onclick="changeStyle(4)" />
										<span class="orange b bbo">学生自选</span>
									</div>
									<div class="l">
										<input type="radio" class="teacher_course" name="course_method" onclick="changeStyle(5)"/>
										<span>教师强排</span>
									</div>
									<div class="l">
										<input type="radio" checked="checked"  class="teacher_course" name="course_method" onclick="changeStyle(6)"/>
										<span>自主分批</span>
									</div>
									<div class="l">
										<input type="radio" class="teacher_course" name="course_method" onclick="changeStyle(7)"/>
										<span>公选排课</span>
									</div>
								</form>
							</div>
							<div class="student_select">
								<div class="student_select">
								<div class="pub_select">
									<div>
										<span class="pr5">批数:</span>
										<input style="width:42%;height:25px;float:left;margin:0 3%;" id="groupNumbers" name="groupNumbers" required="true"/>
									</div>
								<div>
									<button class="r mt5 mb10 mr5p" type="button" onclick="doMergeCourseTimetableSelf()">自动分批</button>
									<button class="r mt5 mb10 mr5p" type="button" onclick="doMergeCourseTimetableHand()">手动分批</button>
								</div>
								</div>
								</div>
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
		var selectGroup = -1;
		 $(document).ready(function(){
		 track.goToPage(parseInt($("#selectItemOrder").val()/4)+parseInt(1));
		 if($("#groupId").val() != null && $("#groupId").val() != ""){
		 	var els =document.getElementsByName("isChosen");
		 	var chosenWeeks = new Array();
			for (var i = 0, j = els.length; i < j; i++){
				if(els[i].value == 1){
					chosenWeeks.push(i+1);
				};
			}
		 	$.ajax({
				       url:"${pageContext.request.contextPath}/newtimetable/findConflictTime?groupId="+$("#groupId").val(),
				       data:{chosenWeeks:chosenWeeks.join(",")},
				       type:"POST",
				       success:function(data){//AJAX查询成功
				           for(var t in data) {
				           		var time = t;
				           		var num = data[t];
				           		//$("#"+time).removeClass("choose_this");
								if($("#"+time).hasClass("choose_this"))
								{
									$("#"+time).html(num+"/"+${capacity});
								}
				           }  
				       }
				});
		 }
		 });
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
/* 				$.ajax({
					url:"${pageContext.request.contextPath}/newtimetable/setTimetableGroupStudentsByGroupId?groupId="+id,
					type:"POST",
					success:function(data){//AJAX查询成功 */
						$("#groupName").removeClass("hide");
						$("#addHint").addClass("hide");
						$("#add").removeClass("hide");
						$("#finish").addClass("hide");
						selectGroup = id;
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
						           }
						});
/* 
				    }
				});
				 */
			}
			
			function deleteThisStudent(studentId){
				$.ajax({
				           url:"${pageContext.request.contextPath}/newtimetable/deleteThisStudent?studentId="+studentId,
				           
				           type:"POST",
				           success:function(data){//AJAX查询成功
				                viewStudents(data);  
				           }
				});
			}
			
			function addStudents(){
				$("#groupName").addClass("hide");
				$("#addHint").removeClass("hide");
				$("#add").addClass("hide");
				$("#finish").removeClass("hide");
				$.ajax({
				           url:"${pageContext.request.contextPath}/newtimetable/addStudentsInMergeCourse?groupId="+selectGroup+"&page=1&isMerge=1",
				           
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
				//$(".normal").removeClass("block").addClass("hide");
				//$(".student_list_check").removeClass("hide").addClass("block");
				$.ajax({
				           url:"${pageContext.request.contextPath}/newtimetable/addStudentsInMergeCourse?groupId="+id+"&page="+page+"&isMerge=1",
				           
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
			/*$(".student_course").click(
				function() {
					$(".student_select").removeClass("hide").addClass("block");
					$(".teacher_select").removeClass("block").addClass("hide");
					$(this).siblings("span").removeClass("g0").addClass("orange b bbo");
					$(".teacher_course").siblings("span").removeClass("orange b bbo").addClass("g0");
				}
			);
			$(".teacher_course").click(
				function() {
					$(".teacher_select").removeClass("hide").addClass("block");
					$(".student_select").removeClass("block").addClass("hide");
					$(this).siblings("span").removeClass("g0").addClass("orange b bbo");
					$(".student_course").siblings("span").removeClass("orange b bbo").addClass("g0");
				}
			);*/
			$(".item").click(
				function() {
					$(this).addClass("b bgs wh").removeClass("").siblings().removeClass("b bgs wh");
				}
			);
			
			var itemId = $("#firstItem").val();
			if(itemId == null)itemId = -1;
			function selectItem(id){
				itemId = id;
                // 将选中的item值赋给input框，方便后台request传值
                $("#item").val(id);
                // ajax 判断是否该课程下的其他项目已经有此种排课类型的分批
                $.ajax({
                    url:"${pageContext.request.contextPath}/newtimetable/ifBatchCopyReadyMerge?mergeId=${mergeId}&itemId="+itemId+"&type=${type}",
                    type:'GET',
                    success:function(data){
                        if(data=="yes"){// 可以复制
                            $.messager.confirm('提示','是否要复制已存在的分批？',function(r){
                                if(r) {// 若选择复制，则弹框
                                    $("#batch_chzn").window({left:"200px", top:"200px"});   
                                    $("#batch_chzn").window('open');
                                }else{// 若不选择复制，则直接进后台，自动生成
                                    document.form.action="${pageContext.request.contextPath}/newtimetable/doMergeCourseTimetableSelf?mergeId=${mergeId}";
                                    document.form.submit();
                                }
                            });
                        }else{// 不可以复制
                            document.form.action="${pageContext.request.contextPath}/newtimetable/doMergeCourseTimetableSelf?mergeId=${mergeId}";
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
                            document.form.action="${pageContext.request.contextPath}/newtimetable/doMergeCourseTimetableTeacher?mergeId=${mergeId}"
                            document.form.submit();
                        }
                    }
				});
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
		            document.form.submit();
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
						 if(checkNum  == 0 && $(e.target).hasClass("choose_this")){
						  alert("请先勾选排课组号！");
						 }
						  else{
						  	if($(e.target).hasClass("choose_this")){
						  	var group = checkObj.value;
						  	//教师先不管
							$.ajax({
						         url:"${pageContext.request.contextPath}/newtimetable/saveMergeCourseTimetable?mergeId=${mergeId}",
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
				if(style == 4)
				{
					document.form.action = "${pageContext.request.contextPath}/newtimetable/doMergeCourseTimetable?mergeId=${mergeId}";
				}
				else if(style == 5){
					document.form.action = "${pageContext.request.contextPath}/newtimetable/doMergeCourseTimetableTeacher?mergeId=${mergeId}";
				}
				else if(style == 6){
					document.form.action = "${pageContext.request.contextPath}/newtimetable/doMergeCourseTimetableSelf?mergeId=${mergeId}";
				}
				else if(style == 7){
					document.form.action = "${pageContext.request.contextPath}/newtimetable/doMergeCourseTimetablePublic?mergeId=${mergeId}";
				}
				document.form.submit();
			}
			
			
		
			
			function queryLab(){
					$("#groupId").val("");
		            document.form.submit();
			}
			
			
			function deleteSpecializedBasicCourseAppointment(appointmentId){
				$.ajax({
			         url:"${pageContext.request.contextPath}/newtimetable/deleteSpecializedBasicCourseAppointment?appointmentId="+appointmentId,
			         dataType:"json",
			         type:'GET',
			         complete:function(result)
			         {
			           window.location.reload();
			          }
			});
			}
			
			$(".select-week").click(
				function() {
					$("#week"+$(this).html()).val(1);
					$("#showChosen").val(1);
					document.form.submit();
				}
			);
			
			function saveActualStartDate(groupId,appointmentId){
				$.ajax({
			         url:"${pageContext.request.contextPath}/newtimetable/saveActualStartDate?actualStartDate="+$("#actualStartDate"+appointmentId).val()+"&groupId="+groupId+"&appointmentId="+appointmentId,
			         dataType:"json",
			         type:'GET',
			         complete:function(result)
			         {
			          }
				});
			}
			function saveActualEndDate(groupId,appointmentId){
				$.ajax({
			         url:"${pageContext.request.contextPath}/newtimetable/saveActualEndDate?actualEndDate="+$("#actualEndDate"+appointmentId).val()+"&groupId="+groupId+"&appointmentId="+appointmentId,
			         dataType:"json",
			         type:'GET',
			         complete:function(result)
			         {
			          }
				});
			}
			
			function completeBatchTimetable(){
				$.ajax({
			         url:"${pageContext.request.contextPath}/newtimetable/completeBatchTimetable?batchId=${batchId}",
			         dataType:"json",
			         type:'GET',
			         complete:function(result)
			         {
			         	window.location.reload();
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
			var itemId = $("#firstItem").val();
			if(itemId == null)itemId = -1;
			function selectItem(id){
				itemId = id;
				$("#item").val(id);
				document.form.submit();
			}
			function doMergeCourseTimetableSelf(){
				if($("#groupNumbers").val() != "" && $("#groupNumbers").val() != null){
					document.form.action = "${pageContext.request.contextPath}/newtimetable/setMergeCourseTimetableSelfGroup?mergeId=${mergeId}";
					document.form.submit()
				}
				else{
					alert("请填写批数！");
				}
			}
			
			function doMergeCourseTimetableHand(){
				if($("#groupNumbers").val() != "" && $("#groupNumbers").val() != null){
						  layer.ready(function(){
						        layer.open({
						            type: 2,
						            title: '手动分批',
						            fix: true,
						            maxmin:true,
						            shift:-1,
						            closeBtn: 1,
						            shadeClose: true,
						            move:false,
						            maxmin: false,
						            area: ['1200px', '600px'],
						            content:'${pageContext.request.contextPath}/newtimetable/inBatchesMerge?mergeId=${mergeId}&groupNumbers='+$("#groupNumbers").val()+"&item="+${item},
						            end: function(){
						            	window.location.href="${pageContext.request.contextPath}/newtimetable/doMergeCourseTimetableSelf?mergeId=${mergeId}";
						            }
						        });
						    });
				}
				else{
					alert("请填写批数！");
				}
			}
		</script>
		<script
						src="${pageContext.request.contextPath}/chosen/chosen_width.jquery.js"
						type="text/javascript"></script>

					<script
						src="${pageContext.request.contextPath}/chosen/docsupport/prism.js"
						type="text/javascript" charset="utf-8"></script>
							
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