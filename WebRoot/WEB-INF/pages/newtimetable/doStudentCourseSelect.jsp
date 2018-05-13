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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
	</head>
	<body>
		<div id="bgheight">
			<script type="text/javascript">
				$(function() {
					$("#bgheight").height($(window).height() / 1);
				})
			</script>
			<div class="all_main_content">
				<div class="w100p">
					<div class="grey_line">
						<form name="form"
								action="${pageContext.request.contextPath}/newtimetable/doStudentCourseSelect"
								method="post">
						<table>
							<tr>
								<td>
									<span>学期:&nbsp;</span>
									<select name="term">
										<c:forEach items="${schoolTerms }" var="curr">
											<c:if test="${termId == curr.id }">
												<option value="${curr.id }" selected>${curr.termName }</option>
											</c:if>
											<c:if test="${termId != curr.id }">
												<option value="${curr.id }">${curr.termName }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
							</tr>
						</table>
						<table>
							<tr>
								<td>
									<button>查询</button>
									<button type="button" onclick="cancel()">取消</button>
								</td>
							</tr>
						</table>
						</form>
					</div>
					<div class="bgwo w100p ptb10 ovh">
						<table class="timetable_tab" cellspacing="0">
							<input type="hidden" value="${selectCourseDetailNo }" id="selectCourseDetailNo"/>
							<input type="hidden" value="${selectMergeId }" id="selectMergeId"/>
							<tr>
								<th colspan="2" rowspan="3" class="timetable_th">
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
							 		<td>${currWeek1[2] }</td>
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
								            	<c:set var="courseNumber" value="0"/>
								            	<c:forEach items="${appointments}" var="currTimetable">
								            		<c:if test="${currTimetable.timetableAppointmentSameNumbers eq null || fn:length(currTimetable.timetableAppointmentSameNumbers) == 0 }">
								            			<c:if test="${currWeek1[2] >= currTimetable.startWeek && currWeek1[2] <= currTimetable.endWeek
									            		&& currTimetable.weekday == cStatus1.index 
									            		&& cStatus2.index > currTimetable.startClass/2 && cStatus2.index <= currTimetable.endClass/2}">
									            				<c:set var="flag" value="3"/>
									            				<c:if test="${currTimetable.timetableStyle == 24 || currTimetable.timetableStyle == 25 || currTimetable.timetableStyle == 26}">
									            					<c:set var="courseNumber" value="${currTimetable.schoolCourseMerge.courseNumber }"/>
									            				</c:if>
									            				<c:if test="${currTimetable.timetableStyle != 24 && currTimetable.timetableStyle != 25 && currTimetable.timetableStyle != 26}">
									            					<c:set var="courseNumber" value="${currTimetable.schoolCourseDetail.courseNumber }"/>
									            				</c:if>
										            	</c:if>
								            		</c:if>
								            		<c:if test="${currTimetable.timetableAppointmentSameNumbers ne null &&  fn:length(currTimetable.timetableAppointmentSameNumbers) > 0 }">
								            			<c:forEach items = "${currTimetable.timetableAppointmentSameNumbers}" var="currSame">
								            				<c:if test="${currWeek1[2] >= currSame.startWeek && currWeek1[2] <= currSame.endWeek
										            		&& currSame.weekday == cStatus1.index 
										            		&& cStatus2.index > currSame.startClass/2 && cStatus2.index <= currSame.endClass/2}">
										            				<c:set var="flag" value="3"/>
										            				<c:if test="${currTimetable.timetableStyle == 24 || currTimetable.timetableStyle == 25 || currTimetable.timetableStyle == 26}">
									            					<c:set var="courseNumber" value="${currSame.timetableAppointment.schoolCourseMerge.courseNumber }"/>
										            				</c:if>
										            				<c:if test="${currTimetable.timetableStyle != 24 && currTimetable.timetableStyle != 25 && currTimetable.timetableStyle != 26}">
										            					<c:set var="courseNumber" value="${currSame.timetableAppointment.schoolCourseDetail.courseNumber }"/>
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
								            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index !=1}">
								            			<c:set var="flag" value="2"/>
								            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
								            		</c:if>
										 		</c:forEach>
										 		</c:if>
										 		<c:if test="${flag == 1}">
								            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" rowspan="5">${dayName }</td>
								            	</c:if>
								            	<c:if test="${flag == 3}">
								            		<td class="chose_td" id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" >${courseNumber }</td>
								            	</c:if>
								            	<c:if test="${flag != 1 && flag != 2 && flag != 3}">
								            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"></td>
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
								            	<c:set var="courseNumber" value="0"/>
								            	<c:forEach items="${appointments}" var="currTimetable">
								            		<c:if test="${currTimetable.timetableAppointmentSameNumbers eq null || fn:length(currTimetable.timetableAppointmentSameNumbers) == 0 }">
								            			<c:if test="${currWeek1[2] >= currTimetable.startWeek && currWeek1[2] <= currTimetable.endWeek
									            		&& currTimetable.weekday == cStatus1.index 
									            		&& cStatus2.index > currTimetable.startClass/2 && cStatus2.index <= currTimetable.endClass/2}">
									            				<c:set var="flag" value="3"/>
									            				<c:if test="${currTimetable.timetableStyle == 24 || currTimetable.timetableStyle == 25 || currTimetable.timetableStyle == 26}">
									            					<c:set var="courseNumber" value="${currTimetable.schoolCourseMerge.courseNumber }"/>
									            				</c:if>
									            				<c:if test="${currTimetable.timetableStyle != 24 && currTimetable.timetableStyle != 25 && currTimetable.timetableStyle != 26}">
									            					<c:set var="courseNumber" value="${currTimetable.schoolCourseDetail.courseNumber }"/>
									            				</c:if>
										            	</c:if>
								            		</c:if>
								            		<c:if test="${currTimetable.timetableAppointmentSameNumbers ne null &&  fn:length(currTimetable.timetableAppointmentSameNumbers) > 0 }">
								            			<c:forEach items = "${currTimetable.timetableAppointmentSameNumbers}" var="currSame">
								            				<c:if test="${currWeek1[2] >= currSame.startWeek && currWeek1[2] <= currSame.endWeek
										            		&& currSame.weekday == cStatus1.index 
										            		&& cStatus2.index > currSame.startClass/2 && cStatus2.index <= currSame.endClass/2}">
										            				<c:set var="flag" value="3"/>
										            				<c:if test="${currTimetable.timetableStyle == 24 || currTimetable.timetableStyle == 25 || currTimetable.timetableStyle == 26}">
									            					<c:set var="courseNumber" value="${currSame.timetableAppointment.schoolCourseMerge.courseNumber }"/>
										            				</c:if>
										            				<c:if test="${currTimetable.timetableStyle != 24 && currTimetable.timetableStyle != 25 && currTimetable.timetableStyle != 26}">
										            					<c:set var="courseNumber" value="${currSame.timetableAppointment.schoolCourseDetail.courseNumber }"/>
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
								            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index !=1}">
								            			<c:set var="flag" value="2"/>
								            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
								            		</c:if>
										 		</c:forEach>
										 		</c:if>
										 		<c:if test="${flag == 1}">
								            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" rowspan="5">${dayName }</td>
								            	</c:if>
								            	<c:if test="${flag == 3}">
								            		<td class="chose_td" id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}">${courseNumber }</td>
								            	</c:if>
								            	<c:if test="${flag != 1 && flag != 2 && flag != 3}">
								            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"></td>
								            	</c:if>
										 	</c:forEach>
							       		</tr>
							       	</c:forEach>
							     </c:forEach>
							     </c:if>
						</table>
						<div class="public_message">
							<div class="pub_tit">
								<div class="l">
									<span class="b">我的选课列表</span>
								</div>
								<button class="r" onclick="turntotms()">课程平台</button>
							</div>
							<table class="experimental_list_tab" cellspacing="0">
								<tr>
									<th>序号</th>
									<th>课程编号</th>
									<th>课程名称</th>
									<th>代课教师</th>
									<th>选课开始时间</th>
									<th>选课结束时间</th>
									<th>选课</th>
								</tr>
								<c:set var="courseDetailNo" value="0"/>
									<c:set var="courseCount" value="0"/>
									<c:forEach items="${timetableGroups }" var="curr2" varStatus="i2">
									<c:set var="courseRequire" 
                                    value="${curr2.timetableAppointments.iterator().next().schoolCourseDetail.courseRequirement }"></c:set>
    
										<c:if test="${curr2.timetableBatch.courseCode != courseDetailNo }">
										<c:set var="courseCount" value="${courseCount+1 }"/>
										<c:set var="thisCourse" value="0"/>
										<c:set var="courseDetailNo" value="${curr2.timetableBatch.courseCode }"/>
										<input type="hidden" value="${curr2.timetableBatch.courseCode }" id="courseDetailNo${courseCount }"/>
										<c:forEach items="${curr2.timetableAppointments}" var="currApp">
											<c:set var="thisCourse" value="${currApp.schoolCourseDetail }"/>
										</c:forEach>
										<c:set var="courseDone" value="0"/>
										<c:forEach items="${thisCourse.schoolCourseStudents }" var="currCourseStudent">
											<c:if test="${currCourseStudent.userByStudentNumber.username eq user.username && currCourseStudent.isSelect eq 1 }">
												<c:set var="courseDone" value="1"/>
											</c:if>
										</c:forEach>
										<tbody>
											
											<tr class="selection_chosen_class" title="双击收回表格内容">
												<td class="tc">${courseCount }</td>
												<td class="timetable_tab_tit poi" title="点击进入学生实验大纲"><a href="${pageContext.request.contextPath}/newoperation/viewCourseOperationOutlines?courseCode=${curr2.timetableBatch.courseCode}&type=${curr2.timetableBatch.type}&currpage=1">${thisCourse.courseNumber}</a></td>
												<td>${thisCourse.courseName }</td>
												<td class="tc">
													<c:forEach items="${thisCourse.users }" var="teacher">
														${teacher.cname }
													</c:forEach>
												</td>
												<td class="tc"><fmt:formatDate value="${thisCourse.courseStart.time}" pattern="yyyy-MM-dd" /><br><fmt:formatDate value="${thisCourse.courseStart.time}" pattern="HH:mm:ss" /></td>
												<td class="tc"><fmt:formatDate value="${thisCourse.courseEnd.time}" pattern="yyyy-MM-dd" /><br><fmt:formatDate value="${thisCourse.courseEnd.time}" pattern="HH:mm:ss" /></td>
												<c:set var="courseStart">
												<fmt:formatDate value="${thisCourse.courseStart.time}" pattern="yyyy-MM-dd" type="date"/>
												</c:set>
												<c:set var="currentday">
												<fmt:formatDate value="${currTime.time}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>
												</c:set>
												<c:set var="courseEnd">
												<fmt:formatDate value="${thisCourse.courseEnd.time}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>
												</c:set>
												<td class="tc">
													<c:if test="${courseDone eq 1}">
														<i class="fa fa-calculator f18 blue tsw poi hide" id="select${courseDetailNo}" title="开始选课"></i>
														<i class="fa fa-check f18 red tsy poi" title="查看选课"></i>
													</c:if>
													<c:if test="${courseDone eq 0}">
														<c:if test="${currTime.time >= thisCourse.courseStart.time &&  currTime.time <= thisCourse.courseEnd.time}">
															<i class="fa fa-calculator f18 blue tsw poi active" id="select${courseDetailNo}" title="开始选课"></i>
														</c:if>
														<c:if test="${currTime.time < thisCourse.courseStart.time || currTime.time > thisCourse.courseEnd.time}">
															<i class="fa fa-calculator f18 grey tsw poi" id="select${courseDetailNo}" title="开始选课"></i>
														</c:if>
													</c:if>
												</td>
											</tr>
									<tr class="selection_tab_f hide">
										<td colspan="7">
											<table class="selection_tab bgw">
												<c:set var="batchId" value="0"/>
												<c:set var="bacthCount" value="0"/>
												<c:forEach items="${timetableGroups }" var="curr" varStatus="i">
														<c:if test="${curr.timetableBatch.courseCode == curr2.timetableBatch.courseCode}">
															<c:set var="isBatchSelect" value="0"/>
															<c:forEach items="${curr.timetableBatch.timetableBatchStudents }" var="currBatchStudent">
																<c:if test="${currBatchStudent.user.username eq user.username && not empty currBatchStudent.timetableGroup}">
																	<c:set var="isBatchSelect" value="1"/>
																</c:if>
															</c:forEach>
														<tbody>
															<c:if test="${batchId != curr.timetableBatch.id }">
															<c:set var="bacthCount" value="${bacthCount+1 }"/>
															<c:set var="batchId" value="${curr.timetableBatch.id }"/>
															<tr class="selection_chosen">
																<td class="tc">${bacthCount}</td>
																<td class="selection_chosen_tit" title="双击收回表格内容">实验${curr.timetableBatch.operationItem.orderNumber}：${curr.timetableBatch.operationItem.lpName}</td>
																<td class="tc">
																	<c:if test="${isBatchSelect eq 1 || courseDone eq 1}">
																		<i class="fa fa-sign-out f14 g0 hbo poi hide" title="批次勾选" batch-select="${curr.timetableBatch.id }"></i>
																		<i class="fa fa-check-square-o f14 red hbo poi" title="点击查看实验详情"></i>
																	</c:if>
																	<c:if test="${isBatchSelect eq 0 && courseDone eq 0 }">
																		<i class="fa fa-sign-out f14 g0 hbo poi" title="批次勾选" batch-select="${curr.timetableBatch.id }"></i>
																		<i class="fa fa-check-square-o f14 red hbo poi hide" title="点击查看实验详情"></i>
																	</c:if>
																</td>
															</tr>
															
															<tr class="selection_tab_detail_f hide">
																<td colspan="3">
																	<form name="form2" action="" method="get">
																		<table class="selection_tab_detail">
																			<c:set var="groupCount" value="0"/>
																			<c:forEach items="${timetableGroups }" var="curr1" varStatus="i1">
																			<c:if test="${curr1.timetableBatch.id == curr.timetableBatch.id}">
																				<c:set var="groupCount" value="${groupCount+1 }"/>
																				<c:set var="isSelect" value="0"/>
																				<c:forEach items="${curr1.timetableGroupStudentses }" var="currStudent">
																					<c:if test="${currStudent.user.username eq user.username }">
																						<c:set var="isSelect" value="1"/>
																					</c:if>
																				</c:forEach>
																				<c:if test="${isSelect eq 1}">
																				<span>${courseRequire }</span>
																				
																				<tr class="bggrass">
																					<td class="tc">${groupCount }</td>
																					<td class="tc">${curr1.groupName }</td>
																					<td>
																						<c:forEach items="${curr1.timetableAppointments }" var="currTimetable">
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers eq null || fn:length(currTimetable.timetableAppointmentSameNumbers) == 0 }">
																								第${currTimetable.startWeek }周 周${currTimetable.weekday } ${currTimetable.startClass }-${currTimetable.endClass }节&nbsp
																							</c:if>
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers ne null &&  fn:length(currTimetable.timetableAppointmentSameNumbers) > 0 }">
																								<c:forEach items = "${currTimetable.timetableAppointmentSameNumbers}" var="currSame">
																									第${currSame.startWeek }周 周${currSame.weekday } ${currSame.startClass }-${currSame.endClass }节&nbsp;
																								</c:forEach>
																							</c:if>
																						</c:forEach>
																					</td>
																					<td>
																						${curr1.labRoom.labRoomName }
																					</td>
																					<c:if test="${isBatchSelect eq 0 && courseDone eq 0 }">
																						<td class="tc">
																							<c:if test="${isSelect eq 1 || courseDone eq 1}">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" checked="checked" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																							<c:if test="${isSelect eq 0 && courseDone eq 0 }">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																						</td>
																					</c:if>
																					<c:if test="${isBatchSelect eq 1 || courseDone eq 1}">
																						<td class="tc hide">
																							<c:if test="${isSelect eq 1 || courseDone eq 1}">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" checked="checked" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																							<c:if test="${isSelect eq 0 && courseDone eq 0 }">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																						</td>
																					</c:if>
																				</tr>
																				</c:if>
																				<c:if test="${isSelect eq 0}">
																				<span>${courseRequire }</span>
																				
																				<tr>
																					<td class="tc">${groupCount }</td>
																					<td class="tc">${curr1.groupName }</td>
																					<td>
																						<c:forEach items="${curr1.timetableAppointments }" var="currTimetable">
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers eq null || fn:length(currTimetable.timetableAppointmentSameNumbers) == 0 }">
																								第${currTimetable.startWeek }周 周${currTimetable.weekday } ${currTimetable.startClass }-${currTimetable.endClass }节&nbsp
																							</c:if>
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers ne null &&  fn:length(currTimetable.timetableAppointmentSameNumbers) > 0 }">
																								<c:forEach items = "${currTimetable.timetableAppointmentSameNumbers}" var="currSame">
																									第${currSame.startWeek }周 周${currSame.weekday } ${currSame.startClass }-${currSame.endClass }节&nbsp;
																								</c:forEach>
																							</c:if>
																						</c:forEach>
																					</td>
																					<td>
																						${curr1.labRoom.labRoomName }
																					</td>
																					<c:if test="${isBatchSelect eq 0 && courseDone eq 0 }">
																						<td class="tc">
																							<c:if test="${isSelect eq 1 || courseDone eq 1}">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" checked="checked" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																							<c:if test="${isSelect eq 0 && courseDone eq 0 }">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																						</td>
																					</c:if>
																					<c:if test="${isBatchSelect eq 1 || courseDone eq 1}">
																						<td class="tc hide">
																							<c:if test="${isSelect eq 1 || courseDone eq 1}">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" checked="checked" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																							<c:if test="${isSelect eq 0 && courseDone eq 0 }">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																						</td>
																					</c:if>
																				</tr>
																				</c:if>
																			</c:if>
																			</c:forEach>
																		</table>
																		<c:if test="${isBatchSelect eq 1 }">
																			<input type="button" class="btn selection_detail_confirm r mr2p mb10  hide" value="确定" onclick="selectGroup(this,${curr.timetableBatch.id})">
																			<c:if test="${courseDone eq 0}">
																				<input type="reset" class="btn selection_detail_cancel r mr2p mb10" value="退选" onclick="unSelectGroup(this,${curr.timetableBatch.id})">
																			</c:if>
																		</c:if>
																		<c:if test="${isBatchSelect eq 0 }">
																			<input type="button" class="btn selection_detail_confirm r mr2p mb10" value="确定" onclick="selectGroup(this,${curr.timetableBatch.id})">
																			<c:if test="${courseDone eq 0}">
																				<input type="reset" class="btn selection_detail_cancel r mr2p mb10 hide" value="退选" onclick="unSelectGroup(this,${curr.timetableBatch.id})">
																			</c:if>
																		</c:if>
																	</form>
																</td>
															</tr>
															</c:if>
														</tbody>
														</c:if>
												</c:forEach>
											</table>
										
										<c:if test="${courseDone eq 0 }">	
										<%--<input type="button" class="submit_confirm" value="确认提交" onclick="courseSubmit(${courseCount})" title="提交之后不可再次编辑"/>--%>
										</c:if>
										</td>
									</tr>
								</tbody>
								</c:if>
								</c:forEach>
								
										<c:set var="mergeId" value="0"/>
										<c:forEach items="${timetableGroupMergeCourses }" var="curr2" varStatus="i2">
										<c:set var="courseRequire" 
                                    value="${curr2.timetableAppointments.iterator().next().schoolCourseMerge.courseRequirement }"></c:set>
                                    
										<c:if test="${curr2.timetableBatch.courseCode != mergeId }">
										<c:set var="courseCount" value="${courseCount+1 }"/>
										<c:set var="thisCourse" value="0"/>
										<c:set var="mergeId" value="${curr2.timetableBatch.courseCode }"/>
										<c:forEach items="${curr2.timetableAppointments}" var="currApp">
											<c:set var="thisCourse" value="${currApp.schoolCourseMerge }"/>
										</c:forEach>
										<c:set var="courseDone" value="0"/>
										<c:forEach items="${thisCourse.schoolCourseDetails }" var="courseDetail">
											<c:forEach items="${courseDetail.schoolCourseStudents }" var="currCourseStudent">
												<c:if test="${currCourseStudent.userByStudentNumber.username eq user.username && currCourseStudent.isSelect eq 1 }">
													<c:set var="courseDone" value="1"/>
												</c:if>
											</c:forEach>
										</c:forEach>
										<tbody>
											
											<tr class="selection_chosen_class" title="双击收回表格内容">
												<td class="tc">${courseCount }</td>
												<td class="timetable_tab_tit poi" title="点击进入学生实验大纲"><a href="${pageContext.request.contextPath}/newoperation/viewCourseOperationOutlines?courseCode=${curr2.timetableBatch.courseCode}&type=${curr2.timetableBatch.type}&currpage=1">${thisCourse.courseNumber}</a></td>
												<td>${thisCourse.courseName }</td>
												<td class="tc">
													<c:forEach items="${thisCourse.schoolCourseDetails }" var="detail" varStatus="k">
														<c:if test="${k.count  == 1}">
															<c:forEach items="${detail.users }" var="teacher">
																${teacher.cname }
															</c:forEach>
														</c:if>
														
													</c:forEach>
												</td>
												<td class="tc"><fmt:formatDate value="${thisCourse.courseStart.time}" pattern="yyyy-MM-dd" /><br><fmt:formatDate value="${thisCourse.courseStart.time}" pattern="HH:mm:ss" /></td>
												<td class="tc"><fmt:formatDate value="${thisCourse.courseEnd.time}" pattern="yyyy-MM-dd" /><br><fmt:formatDate value="${thisCourse.courseEnd.time}" pattern="HH:mm:ss" /></td>
												<c:set var="courseStart">
												<fmt:formatDate value="${thisCourse.courseStart.time}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>
												</c:set>
												<c:set var="currentday">
												<fmt:formatDate value="${currTime.time}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>
												</c:set>
												<c:set var="courseEnd">
												<fmt:formatDate value="${thisCourse.courseEnd.time}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>
												</c:set>
												<td class="tc">
													<c:if test="${courseDone eq 1}">
														<i class="fa fa-calculator f18 blue tsw poi hide" title="开始选课" id="select${mergeId }"></i>
														<i class="fa fa-check f18 red tsy poi" title="查看选课"></i>
													</c:if>
													<c:if test="${courseDone eq 0}">
														<c:if test="${currTime.time >= thisCourse.courseStart.time &&  currTime.time <= thisCourse.courseEnd.time}">
															<i class="fa fa-calculator f18 blue tsw poi active" title="开始选课" id="select${mergeId }"></i>
														</c:if>
														<c:if test="${currTime.time < thisCourse.courseStart.time || currTime.time > thisCourse.courseEnd.time}">
															<i class="fa fa-calculator f18 grey tsw poi" title="开始选课" id="select${mergeId }"></i>
														</c:if>
													</c:if>
												</td>
											</tr>
									<tr class="selection_tab_f hide">
										<td colspan="7">
											<table class="selection_tab bgw">
												<c:set var="batchId" value="0"/>
												<c:set var="bacthCount" value="0"/>
												<c:forEach items="${timetableGroupMergeCourses}" var="curr" varStatus="i">
														<c:if test="${curr.timetableBatch.courseCode == curr2.timetableBatch.courseCode}">
															<c:set var="isBatchSelect" value="0"/>
															<c:forEach items="${curr.timetableBatch.timetableBatchStudents }" var="currBatchStudent">
																<c:if test="${currBatchStudent.user.username eq user.username && not empty currBatchStudent.timetableGroup}">
																	<c:set var="isBatchSelect" value="1"/>
																</c:if>
															</c:forEach>
														<tbody>
															<c:if test="${batchId != curr.timetableBatch.id }">
															<c:set var="bacthCount" value="${bacthCount+1 }"/>
															<c:set var="batchId" value="${curr.timetableBatch.id }"/>
															<tr class="selection_chosen">
																<td class="tc">${bacthCount}</td>
																<td class="selection_chosen_tit" title="双击收回表格内容">实验${curr.timetableBatch.operationItem.orderNumber}：${curr.timetableBatch.operationItem.lpName}</td>
																<td class="tc">
																	<c:if test="${isBatchSelect eq 1 || courseDone eq 1}">
																		<i class="fa fa-sign-out f14 g0 hbo poi hide" title="批次勾选" batch-select="${curr.timetableBatch.id }"></i>
																		<i class="fa fa-check-square-o f14 red hbo poi" title="点击查看实验详情"></i>
																	</c:if>
																	<c:if test="${isBatchSelect eq 0 && courseDone eq 0 }">
																		<i class="fa fa-sign-out f14 g0 hbo poi" title="批次勾选" batch-select="${curr.timetableBatch.id }"></i>
																		<i class="fa fa-check-square-o f14 red hbo poi hide" title="点击查看实验详情"></i>
																	</c:if>
																</td>
															</tr>
															
															<tr class="selection_tab_detail_f hide">
																<td colspan="3">
																	<form name="form2" action="" method="get">
																		<table class="selection_tab_detail">
																			<c:set var="groupCount" value="0"/>
																			<c:forEach items="${timetableGroupMergeCourses }" var="curr1" varStatus="i1">
																			<c:if test="${curr1.timetableBatch.id == curr.timetableBatch.id}">
																				<c:set var="groupCount" value="${groupCount+1 }"/>
																				<c:set var="isSelect" value="0"/>
																				<c:forEach items="${curr1.timetableGroupStudentses }" var="currStudent">
																					<c:if test="${currStudent.user.username eq user.username }">
																						<c:set var="isSelect" value="1"/>
																					</c:if>
																				</c:forEach>
																				<c:if test="${isSelect eq 1}">
																				<span>${courseRequire }</span>
																				<tr class="bggrass">
																					<td class="tc">${groupCount }</td>
																					<td class="tc">${curr1.groupName }</td>
																					<td>
																						<c:forEach items="${curr1.timetableAppointments }" var="currTimetable">
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers eq null || fn:length(currTimetable.timetableAppointmentSameNumbers) == 0 }">
																								第${currTimetable.startWeek }周 周${currTimetable.weekday } ${currTimetable.startClass }-${currTimetable.endClass }节&nbsp
																							</c:if>
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers ne null &&  fn:length(currTimetable.timetableAppointmentSameNumbers) > 0 }">
																								<c:forEach items = "${currTimetable.timetableAppointmentSameNumbers}" var="currSame">
																									第${currSame.startWeek }周 周${currSame.weekday } ${currSame.startClass }-${currSame.endClass }节&nbsp;
																								</c:forEach>
																							</c:if>
																						</c:forEach>
																					</td>
																					<td>
																						${curr1.labRoom.labRoomName }
																					</td>
																					<c:if test="${isBatchSelect eq 0 && courseDone eq 0 }">
																						<td class="tc">
																							<c:if test="${isSelect eq 1 || courseDone eq 1}">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" checked="checked" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																							<c:if test="${isSelect eq 0 && courseDone eq 0 }">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																						</td>
																					</c:if>
																					<c:if test="${isBatchSelect eq 1 || courseDone eq 1}">
																						<td class="tc hide">
																							<c:if test="${isSelect eq 1 || courseDone eq 1}">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" checked="checked" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																							<c:if test="${isSelect eq 0 && courseDone eq 0 }">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																						</td>
																					</c:if>
																				</tr>
																				</c:if>
																				<c:if test="${isSelect eq 0}">
																				<span>${courseRequire }</span>
																				<tr>
																					<td class="tc">${groupCount }</td>
																					<td class="tc">${curr1.groupName }</td>
																					<td>
																						<c:forEach items="${curr1.timetableAppointments }" var="currTimetable">
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers eq null || fn:length(currTimetable.timetableAppointmentSameNumbers) == 0 }">
																								第${currTimetable.startWeek }周 周${currTimetable.weekday } ${currTimetable.startClass }-${currTimetable.endClass }节&nbsp
																							</c:if>
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers ne null &&  fn:length(currTimetable.timetableAppointmentSameNumbers) > 0 }">
																								<c:forEach items = "${currTimetable.timetableAppointmentSameNumbers}" var="currSame">
																									第${currSame.startWeek }周 周${currSame.weekday } ${currSame.startClass }-${currSame.endClass }节&nbsp;
																								</c:forEach>
																							</c:if>
																						</c:forEach>
																					</td>
																					<td>
																						${curr1.labRoom.labRoomName }
																					</td>
																					<c:if test="${isBatchSelect eq 0 && courseDone eq 0 }">
																						<td class="tc">
																							<c:if test="${isSelect eq 1 || courseDone eq 1}">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" checked="checked" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																							<c:if test="${isSelect eq 0 && courseDone eq 0 }">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																						</td>
																					</c:if>
																					<c:if test="${isBatchSelect eq 1 || courseDone eq 1}">
																						<td class="tc hide">
																							<c:if test="${isSelect eq 1 || courseDone eq 1}">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" checked="checked" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																							<c:if test="${isSelect eq 0 && courseDone eq 0 }">
																								<input class="detail_check detail_check${curr.timetableBatch.id }" type="checkbox" id="dc1" name="detail_check${curr.timetableBatch.id }" value="${curr1.id }" onclick="check(this,${curr1.timetableBatch.id})"/>
																							</c:if>
																						</td>
																					</c:if>
																				</tr>
																				</c:if>
																			</c:if>
																			</c:forEach>
																		</table>
																		<c:if test="${isBatchSelect eq 1 }">
																			<input type="button" class="btn selection_detail_confirm r mr2p mb10  hide" value="确定" onclick="selectGroup(this,${curr.timetableBatch.id})">
																			<c:if test="${courseDone eq 0}">
																				<input type="reset" class="btn selection_detail_cancel r mr2p mb10" value="退选" onclick="unSelectGroup(this,${curr.timetableBatch.id})">
																			</c:if>
																		</c:if>
																		<c:if test="${isBatchSelect eq 0 }">
																			<input type="button" class="btn selection_detail_confirm r mr2p mb10" value="确定" onclick="selectGroup(this,${curr.timetableBatch.id})">
																			<c:if test="${courseDone eq 0}">
																				<input type="reset" class="btn selection_detail_cancel r mr2p mb10 hide" value="退选" onclick="unSelectGroup(this,${curr.timetableBatch.id})">
																			</c:if>
																		</c:if>
																	</form>
																</td>
															</tr>
															</c:if>
														</tbody>
														</c:if>
												</c:forEach>
											</table>
										
										<c:if test="${courseDone eq 0 }">	
										<%--<input type="button" class="submit_confirm" value="确认提交" onclick="courseMergeSubmit(${mergeId})" title="提交之后不可再次编辑"/>--%>
										</c:if>
										</td>
									</tr>
								</tbody>
								</c:if>
								</c:forEach>
								<!--可删除开始-->
								<!--可删除结束-->
								<c:set var="courseDetailNo" value="0"/>
									<c:forEach items="${timetableGroupPublicCourses }" var="curr2" varStatus="i2">
									<c:set var="courseRequire" 
                                    value="${curr2.timetableAppointments.iterator().next().schoolCourseDetail.courseRequirement }"></c:set>
      
										<c:if test="${curr2.timetableBatch.courseCode != courseDetailNo }">
										<c:set var="courseCount" value="${courseCount+1 }"/>
										<c:set var="thisCourse" value="0"/>
										<c:set var="courseDetailNo" value="${curr2.timetableBatch.courseCode }"/>
										<input type="hidden" value="${curr2.timetableBatch.courseCode }" id="courseDetailNo${courseCount }"/>
										<c:forEach items="${curr2.timetableAppointments}" var="currApp">
											<c:set var="thisCourse" value="${currApp.schoolCourseDetail }"/>
										</c:forEach>
										<c:set var="courseDone" value="0"/>
										<c:forEach items="${thisCourse.schoolCourseStudents }" var="currCourseStudent">
											<c:if test="${currCourseStudent.userByStudentNumber.username eq user.username && currCourseStudent.isSelect eq 1 }">
												<c:set var="courseDone" value="1"/>
											</c:if>
										</c:forEach>
										<tbody>
											<c:set var="groupCount" value="0"/>
											<tr class="selection_chosen_class" title="双击收回表格内容">
												<td class="tc">${courseCount }</td>
												<td class="timetable_tab_tit poi" title="点击进入学生实验大纲"><a href="${pageContext.request.contextPath}/newoperation/viewCourseOperationOutlines?courseCode=${curr2.timetableBatch.courseCode}&type=${curr2.timetableBatch.type}&currpage=1">${thisCourse.courseNumber}</a></td>
												<td>${thisCourse.courseName }</td>
												<td class="tc">
													<c:forEach items="${thisCourse.users }" var="teacher">
														${teacher.cname }
													</c:forEach>
												</td>
												<td class="tc"><fmt:formatDate value="${thisCourse.courseStartTime.time}" pattern="yyyy-MM-dd" /><br><fmt:formatDate value="${thisCourse.courseStartTime.time}" pattern="HH:mm:ss" /></td>
												<td class="tc"></td>
												<td class="tc">
													<c:if test="${courseDone eq 1}">
														<i class="fa fa-calculator f18 blue tsw poi hide" title="开始选课" id="select${courseDetailNo}"></i>
														<i class="fa fa-check f18 red tsy poi" title="查看选课"></i>
													</c:if>
													<c:if test="${courseDone eq 0}">
														<i class="fa fa-calculator f18 blue tsw poi active public_select" course-select ="${courseDetailNo}" title="开始选课" id="select${courseDetailNo}"></i>
														<i class="fa fa-check f18 red tsy poi hide" title="查看选课"></i>
													</c:if>
												</td>
											</tr>
											<tr class="selection_tab_f hide">
												<td colspan="7">
													<table class="selection_tab bgw">
														<tbody>
															<tr class="selection_tab_detail_f">
																<td colspan="3">
																	<form name="form2" action="" method="get">
																		<table class="selection_tab_detail">
																			<c:forEach items="${timetableGroupPublicCourses }" var="curr1" varStatus="i">
																				<c:if test="${curr1.timetableBatch.courseCode == curr2.timetableBatch.courseCode}">
																				<c:set var="isSelect" value="0"/>
																				<c:forEach items="${curr1.timetableGroupStudentses }" var="currStudent">
																					<c:if test="${currStudent.user.username eq user.username }">
																						<c:set var="isSelect" value="1"/>
																					</c:if>
																				</c:forEach>
																				<c:set var="groupCount" value="${groupCount+1 }"/>
																				<c:if test="${isSelect eq 1}">
																				<span>${courseRequire }</span>
																				<tr class="bggrass">
																					<td class="tc">${groupCount }</td>
																					<td class="tc">${curr1.groupName }</td>
																					<td>
																						<c:forEach items="${curr1.timetableAppointments }" var="currTimetable">
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers eq null || fn:length(currTimetable.timetableAppointmentSameNumbers) == 0 }">
																								第${currTimetable.startWeek }周 周${currTimetable.weekday } ${currTimetable.startClass }-${currTimetable.endClass }节&nbsp
																							</c:if>
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers ne null &&  fn:length(currTimetable.timetableAppointmentSameNumbers) > 0 }">
																								<c:forEach items = "${currTimetable.timetableAppointmentSameNumbers}" var="currSame">
																									第${currSame.startWeek }周 周${currSame.weekday } ${currSame.startClass }-${currSame.endClass }节&nbsp;
																								</c:forEach>
																							</c:if>
																						</c:forEach>
																					</td>
																					<td>
																						<c:forEach items="${curr1.timetableAppointments }" var="currTimetable">
																							<c:forEach items="${currTimetable.timetableLabRelateds}" var="currLab">
																								${currLab.labRoom.labRoomName }
																							</c:forEach>
																						</c:forEach>
																					</td>
																						<td>
																							<c:if test="${isSelect eq 0}">
																							<input type="button" class="btn selection_detail_confirm r mr2p mb10"  onclick="selectPublicGroup(this,${curr1.id})" value="选择">
																							<input type="reset" class="btn selection_detail_cancel r mr2p mb10 hide"  onclick="unSelectPublicGroup(this,${curr1.id})"  value="退选">
																							</c:if>
																							<c:if test="${isSelect eq 1}">
																							<input type="button" class="btn selection_detail_confirm r mr2p mb10  hide"  onclick="selectPublicGroup(this,${curr1.id})" value="选择">
																							<input type="reset" class="btn selection_detail_cancel r mr2p mb10"  onclick="unSelectPublicGroup(this,${curr1.id})"  value="退选">
																							</c:if>
																						</td>
																				</tr>
																				</c:if>
																				<c:if test="${isSelect eq 0}">
																				<span>${courseRequire }</span>
																				<tr>
																					<td class="tc">${groupCount }</td>
																					<td class="tc">${curr1.groupName }</td>
																					<td>
																						<c:forEach items="${curr1.timetableAppointments }" var="currTimetable">
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers eq null || fn:length(currTimetable.timetableAppointmentSameNumbers) == 0 }">
																								第${currTimetable.startWeek }周 周${currTimetable.weekday } ${currTimetable.startClass }-${currTimetable.endClass }节&nbsp
																							</c:if>
																							<c:if test="${currTimetable.timetableAppointmentSameNumbers ne null &&  fn:length(currTimetable.timetableAppointmentSameNumbers) > 0 }">
																								<c:forEach items = "${currTimetable.timetableAppointmentSameNumbers}" var="currSame">
																									第${currSame.startWeek }周 周${currSame.weekday } ${currSame.startClass }-${currSame.endClass }节&nbsp;
																								</c:forEach>
																							</c:if>
																						</c:forEach>
																					</td>
																					<td>
																						<c:forEach items="${curr1.timetableAppointments }" var="currTimetable">
																							<c:forEach items="${currTimetable.timetableLabRelateds}" var="currLab">
																								${currLab.labRoom.labRoomName }
																							</c:forEach>
																						</c:forEach>
																					</td>
																						<td>
																							<c:if test="${isSelect eq 0}">
																							<input type="button" class="btn selection_detail_confirm r mr2p mb10"  onclick="selectPublicGroup(this,${curr1.id})" value="选择">
																							<input type="reset" class="btn selection_detail_cancel r mr2p mb10 hide"  onclick="unSelectPublicGroup(this,${curr1.id})"  value="退选">
																							</c:if>
																							<c:if test="${isSelect eq 1}">
																							<input type="button" class="btn selection_detail_confirm r mr2p mb10  hide"  onclick="selectPublicGroup(this,${curr1.id})" value="选择">
																							<input type="reset" class="btn selection_detail_cancel r mr2p mb10"  onclick="unSelectPublicGroup(this,${curr1.id})"  value="退选">
																							</c:if>
																						</td>
																				</tr>
																				</c:if>
																			</c:if>
																			</c:forEach>
																		</table>
																	</form>
																</td>
															</tr>
														</tbody>
													</table>
												<%-- <input type="submit" class="submit_confirm" value="确认提交" onclick="courseSubmit(${courseCount})"  title="提交之后不可再次编辑"/> --%>
												</td>
											</tr>
										</tbody>
										</c:if>
									</c:forEach>
							</table>
						</div>
					</div>

				</div>
			</div>
		</div>
		</div>
		</div>
		<style>
			.experimental_list_tab {
				margin: 13px auto;
			}
			
			.experimental_list_tab tr:hover {
				background: #ffffd0;
			}
			
			.experimental_list_tab tr:hover .timetable_tab_tit {
				color: slateblue;
				background: #fff;
			}
			
			.timetable_tab {
				float: left;
				width: 59.5%;
				margin: 13px 0 13px 0.8%;
			}
			
			.timetable_tab_tit:hover {
				color: #0000ff!important;
				text-decoration: underline;
			}
			
			.timetable_tab .timetable_th {
				background-image: url(../images/img/timetanble2.png);
				font-size: 12px!important;
			}
			
			.timetable_tab tr:hover {
				background: #fff;
			}
			
			.public_message {
				width: 37.5%;
				margin: 13px 0.8% 13px 0;
				float: right;
			}
			
			.all_main_content .timetable_tab tr th,
			.timetable_tab td,
			.experimental_list_tab tr td,
			.experimental_list_tab tr th {
				font-size: 12px;
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
			
			.temp_td1 {
				background: green;
			}
			
			.temp_td2 {
				background: yellow;
			}
			
			.select_time {
				background: red;
			}
		</style>
		<script type="text/javascript">
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
		/* 	$(".fa-sign-out").click(
				function() {
					$(this).parent().parent().siblings(".selection_tab_detail_f").removeClass("hide").addClass("blocktr");
					$(this).parent().parent(".selection_chosen").addClass("bgyellow");
				}
			); */
			
			
			 // 删除实验室建设项目
	  	$('[batch-select]').each(function(i,e){
		  	$(e).on('click',function(){
            	$(this).parent().parent().siblings(".selection_tab_detail_f").removeClass("hide").addClass("blocktr");
				$(this).parent().parent(".selection_chosen").addClass("bgyellow");
				$.ajax({
	 				url:'${pageContext.request.contextPath}/newtimetable/findAppointmentsByBatchId?batchId='+$(e).attr('batch-select'),
	                 type:'POST', 
	 				error:function (request){
	 					alert('请求错误!');
	 				},
	 				success:function(data){ 
	 					var obj1 = document.getElementsByClassName("temp_td1");
	 					var ids1 = new Array();
	 					if(obj1.length != 0){
	 						for(i = 0; i < obj1.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								ids1.push($(obj1[i]).attr('id'));
							}
	 					}
	 					for(i = 0; i < ids1.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								$("#"+ids1[i]).removeClass("temp_td1");
								$("#"+ids1[i]).html("");
							}
	 					var obj2 = document.getElementsByClassName("temp_td2");
	 					var ids2 = new Array();
					    if(obj2.length != 0){
					    	for(i = 0; i < obj2.length; i++){
					    			//var s = $(obj[k]).html();
					    			//$(obj[k]).html(s.substring(0,s.length-3));
									ids2.push($(obj2[i]).attr('id'));
							}	
					    } 
					    for(i = 0; i < ids2.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								$("#"+ids2[i]).removeClass("temp_td2");
								var s = $("#"+ids2[i]).html();
								s = s.substring(0,s.length-3);
								$("#"+ids2[i]).html(s);
							}      
	 					for(var t in data) {
				           		var time = t;
				           		var num = data[t];
				           		if($("#"+time).hasClass("chose_td")){
				           			$("#"+time).addClass("temp_td2");
				           			$("#"+time).html($("#"+time).html()+num);
				           		}
								else{
									$("#"+time).addClass("temp_td1");
				           			$("#"+time).html(num);
								}
				           }  
	 				}         
	 			});
         });                                              
		});
	  	
			$(".selection_chosen_tit").dblclick(
				function() {
					$(this).parent().siblings(".selection_tab_detail_f").removeClass("blocktr").addClass("hide");
					$(this).parent(".selection_chosen").removeClass("bgyellow");
				}
			);
			$(".fa-check-square-o").click(
				function() {
					$(this).parent().parent().siblings(".selection_tab_detail_f").removeClass("hide").addClass("blocktr");
					$(this).parent().parent(".selection_chosen").addClass("bgyellow");
				}
			);
			$(".fa-calculator").click(
				function() {
					if($(this).hasClass("active")){
						$(this).parent().siblings(".timetable_tab_tit").addClass("bgw");
						$(this).parent().parent().siblings(".selection_tab_f").removeClass("hide").addClass("blocktr");
						$(this).parent().parent(".selection_chosen_class").addClass("bglb");
					}
				}
			);
			$(".fa-check").click(
				function() {
					$(this).parent().siblings(".timetable_tab_tit").addClass("bgw");
					$(this).parent().parent().siblings(".selection_tab_f").removeClass("hide").addClass("blocktr");
					$(this).parent().parent(".selection_chosen_class").addClass("bglb");
				}
			);
			$(".selection_chosen_class").dblclick(
				function() {
					$(this).children(".timetable_tab_tit").removeClass("bgw");
					$(this).siblings(".selection_tab_f").removeClass("blocktr").addClass("hide");
					$(this).removeClass("bglb");
					//$(".selection_tab_detail_f").removeClass("blocktr").addClass("hide");
					$(".selection_chosen").removeClass("bgyellow");
				}
			);
			/* $(".selection_detail_confirm").click(
				function() {
					$(this).removeClass("block").addClass("hide");
					$(this).siblings(".selection_detail_cancel").removeClass("hide").addClass("block");
					$(this).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-sign-out").removeClass("block").addClass("hide");
					$(this).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-check-square-o").removeClass("hide").addClass("block");
					$(this).siblings(".selection_tab_detail").children().children().children().children(".detail_check").parent(".tc").addClass("hide");
				}
			);
			$(".selection_detail_confirm").click(
				function() {
					if($(this).siblings(".selection_tab_detail").children().children().children().children("input[type=checkbox]").is(':checked'))
						$(this).siblings(".selection_tab_detail").children().children().children().children("input[type=checkbox]:checked").parent().parent().addClass("bggrass").siblings().addClass("hide");

				}
			); */
			/* $(".selection_detail_cancel").click(
				function() {
					$(this).removeClass("block").addClass("hide");
					$(this).siblings(".selection_detail_confirm").removeClass("hide").addClass("block");
					$(this).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-sign-out").removeClass("hide").addClass("block");
					$(this).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-check-square-o").removeClass("block").addClass("hide");
					$(this).siblings(".selection_tab_detail").children().children().children().children(".detail_check").parent(".tc").removeClass("hide");
				    $(this).siblings(".selection_tab_detail").children().children().children().children("input[type=checkbox]:checked").parent().parent().removeClass("bggrass").siblings().removeClass("hide");
				}
			); */
			/*$(".submit_confirm").click(
				function() {
					$(this).removeClass("block").addClass("hide");
					$(this).parent().parent().siblings(".selection_chosen_class").children().children(".fa-calculator").addClass("hide");
					$(this).parent().parent().siblings(".selection_chosen_class").children().children(".fa-check").removeClass("hide").addClass("block");
					$(".selection_detail_cancel").addClass("hide");
				    $(".selection_detail_confirm").addClass("hide");
				    alert("点击提交之后不可再次编辑，确认提交？")
				}
			);*/
			
			function courseSubmit(courseCount)
			{
				if(window.confirm('点击提交之后不可再次编辑，确认提交？')){
					window.location.href="${pageContext.request.contextPath}/newtimetable/courseSubmit?courseDetailNo="+$("#courseDetailNo"+courseCount).val();
				}
				else{
					
				}
			}
			function courseMergeSubmit(mergeId)
			{
				if(window.confirm('点击提交之后不可再次编辑，确认提交？')){
					window.location.href="${pageContext.request.contextPath}/newtimetable/courseMergeSubmit?mergeId="+mergeId;
				}
				else{
					
				}
			}
			function check(obj, batchId) {
		    $('.detail_check'+batchId).each(function () {
		        if (this != obj)
		            $(this).attr("checked", false);
		        else {
		            if ($(this).prop("checked"))
		                $(this).attr("checked", true);
		            else
		                $(this).attr("checked", false);
		        }
		    });
		}
		
		function selectGroup(object, batchId){
			 var obj = document.getElementsByName("detail_check"+batchId);
		  var groupId = -1;
		  var flag = 0;
		  for(k in obj){
		      if(obj[k].checked)
		      {
		      	flag = 1;
		       	groupId = obj[k].value;
		       }
		  }
		  if(flag == 0){
		  	alert("请先选择批次!");
		  }else{
		  	$.ajax({
	 				url:'${pageContext.request.contextPath}/newtimetable/selectGroup?groupId='+groupId,
	                 type:'POST', 
	 				error:function (request){
	 					alert('请求错误!');
	 				},
	 				success:function(data){ 
	 					if(data.result =="success"){
	 						$(object).removeClass("block").addClass("hide");
							$(object).siblings(".selection_detail_cancel").removeClass("hide").addClass("block");
							$(object).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-sign-out").removeClass("block").addClass("hide");
							$(object).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-check-square-o").removeClass("hide").addClass("block");
							$(object).siblings(".selection_tab_detail").children().children().children().children(".detail_check").parent(".tc").addClass("hide");
							if($(object).siblings(".selection_tab_detail").children().children().children().children("input[type=checkbox]").is(':checked'))
								$(object).siblings(".selection_tab_detail").children().children().children().children("input[type=checkbox]:checked").parent().parent().addClass("bggrass");
								var obj1 = document.getElementsByClassName("temp_td1");
			 					var ids1 = new Array();
			 					if(obj1.length != 0){
			 						for(i = 0; i < obj1.length; i++){
			 							//var s = $(obj[k]).html();
							    		//$(obj[k]).html(s.substring(0,s.length-3));
										//$(obj1[k]).removeClass("temp_td1");
										ids1.push($(obj1[i]).attr('id'));
									}
			 					}
			 					for(i = 0; i < ids1.length; i++){
			 							//var s = $(obj[k]).html();
							    		//$(obj[k]).html(s.substring(0,s.length-3));
										//$(obj1[k]).removeClass("temp_td1");
										$("#"+ids1[i]).removeClass("temp_td1");
										$("#"+ids1[i]).html("");
									}
			 					var obj2 = document.getElementsByClassName("temp_td2");
			 					var ids2 = new Array();
							    if(obj2.length != 0){
							    	for(i = 0; i < obj2.length; i++){
							    			//var s = $(obj[k]).html();
							    			//$(obj[k]).html(s.substring(0,s.length-3));
											ids2.push($(obj2[i]).attr('id'));
									}	
							    } 
							    for(i = 0; i < ids2.length; i++){
			 							//var s = $(obj[k]).html();
							    		//$(obj[k]).html(s.substring(0,s.length-3));
										//$(obj1[k]).removeClass("temp_td1");
										$("#"+ids2[i]).removeClass("temp_td2");
										var s = $("#"+ids2[i]).html();
										s = s.substring(0,s.length-3);
										$("#"+ids2[i]).html(s);
									}      
								var table = data.mapTimetable;
								for(var t in table) {
					           		var time = t;
					           		var num = table[t];
									$("#"+time).addClass("select_time");
					           		$("#"+time).html(num);
				           		}  
			 			}
			 			else if(data.result == "timeFail"){
			 				alert("该批次时间与已选课程存在冲突!");
			 			}else{
			 				alert("该批次已选满！");
			 			}
	 				}         
	 			});
		  }
			
		}
		
		function unSelectGroup(object, batchId){
			$.ajax({
	 				url:'${pageContext.request.contextPath}/newtimetable/unSelectGroup?batchId='+batchId,
	                 type:'POST', 
	 				error:function (request){
	 					alert('请求错误!');
	 				},
	 				success:function(data){ 
	 					
	 					if(data.result =="success"){
	 						$(object).removeClass("block").addClass("hide");
					$(object).siblings(".selection_detail_confirm").removeClass("hide").addClass("block");
					$(object).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-sign-out").removeClass("hide").addClass("block");
					$(object).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-check-square-o").removeClass("block").addClass("hide");
					$(object).siblings(".selection_tab_detail").children().children().children().children(".detail_check").parent(".tc").removeClass("hide");
				    $(object).siblings(".selection_tab_detail").children().children().children().children("input[type=checkbox]:checked").parent().parent().removeClass("bggrass").siblings().removeClass("hide");
	 						var table = data.mapTimetable;
								for(var t in table) {
					           		var time = t;
					           		var num = table[t];
									$("#"+time).removeClass("select_time");
					           		$("#"+time).html("");
				           		} 
								  $('.detail_check'+batchId).each(function () {
							       $(this).attr("checked", false);
							    });
	 					}  
	 					
	 					$.ajax({
	 				url:'${pageContext.request.contextPath}/newtimetable/findAppointmentsByBatchId?batchId='+batchId,
	                 type:'POST', 
	 				error:function (request){
	 					alert('请求错误!');
	 				},
	 				success:function(data){ 
	 					var obj1 = document.getElementsByClassName("temp_td1");
	 					var ids1 = new Array();
	 					if(obj1.length != 0){
	 						for(i = 0; i < obj1.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								ids1.push($(obj1[i]).attr('id'));
							}
	 					}
	 					for(i = 0; i < ids1.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								$("#"+ids1[i]).removeClass("temp_td1");
								$("#"+ids1[i]).html("");
							}
	 					var obj2 = document.getElementsByClassName("temp_td2");
	 					var ids2 = new Array();
					    if(obj2.length != 0){
					    	for(i = 0; i < obj2.length; i++){
					    			//var s = $(obj[k]).html();
					    			//$(obj[k]).html(s.substring(0,s.length-3));
									ids2.push($(obj2[i]).attr('id'));
							}	
					    } 
					    for(i = 0; i < ids2.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								$("#"+ids2[i]).removeClass("temp_td2");
								var s = $("#"+ids2[i]).html();
								s = s.substring(0,s.length-3);
								$("#"+ids2[i]).html(s);
							}      
	 					for(var t in data) {
				           		var time = t;
				           		var num = data[t];
				           		if($("#"+time).hasClass("chose_td")){
				           			$("#"+time).addClass("temp_td2");
				           			$("#"+time).html($("#"+time).html()+num);
				           		}
								else{
									$("#"+time).addClass("temp_td1");
				           			$("#"+time).html(num);
								}
				           }  
	 				}         
	 			});    
	 				}   
	 			});
		}
		
		function turntotms(){
			window.location.href="${pageContext.request.contextPath}/tms/index";
		}
		
		
		$('[course-select]').each(function(i,e){
		  	$(e).on('click',function(){
            	//$(this).parent().parent().siblings(".selection_tab_detail_f").removeClass("hide").addClass("blocktr");
				//$(this).parent().parent(".selection_chosen").addClass("bgyellow");
				$.ajax({
	 				url:'${pageContext.request.contextPath}/newtimetable/findAppointmentsByCourseDetailNo?courseDetailNo='+$(e).attr('course-select'),
	                 type:'POST', 
	 				error:function (request){
	 					alert('请求错误!');
	 				},
	 				success:function(data){ 
	 					var obj1 = document.getElementsByClassName("temp_td1");
	 					var ids1 = new Array();
	 					if(obj1.length != 0){
	 						for(i = 0; i < obj1.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								ids1.push($(obj1[i]).attr('id'));
							}
	 					}
	 					for(i = 0; i < ids1.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								$("#"+ids1[i]).removeClass("temp_td1");
								$("#"+ids1[i]).html("");
							}
	 					var obj2 = document.getElementsByClassName("temp_td2");
	 					var ids2 = new Array();
					    if(obj2.length != 0){
					    	for(i = 0; i < obj2.length; i++){
					    			//var s = $(obj[k]).html();
					    			//$(obj[k]).html(s.substring(0,s.length-3));
									ids2.push($(obj2[i]).attr('id'));
							}	
					    } 
					    for(i = 0; i < ids2.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								$("#"+ids2[i]).removeClass("temp_td2");
								var s = $("#"+ids2[i]).html();
								s = s.substring(0,s.length-3);
								$("#"+ids2[i]).html(s);
							}      
	 					for(var t in data) {
				           		var time = t;
				           		var num = data[t];
				           		if($("#"+time).hasClass("chose_td")){
				           			$("#"+time).addClass("temp_td2");
				           			$("#"+time).html($("#"+time).html()+num);
				           		}
								else{
									$("#"+time).addClass("temp_td1");
				           			$("#"+time).html(num);
								}
				           }  
	 				}         
	 			});
         });                                              
		});
	  	
	  	
	  	function selectPublicGroup(object,groupId){
	  		$.ajax({
	 				url:'${pageContext.request.contextPath}/newtimetable/judgeSelectTimeIsOk?groupId='+groupId,
	                 type:'POST', 
	 				error:function (request){
	 					alert('请求错误!');
	 				},
	 				success:function(data){ 
	 					if(data == "ok"){
	 						$.ajax({
			 				url:'${pageContext.request.contextPath}/newtimetable/selectPublicGroup?groupId='+groupId,
			                 type:'POST', 
			 				error:function (request){
			 					alert('请求错误!');
			 				},
			 				success:function(data){ 
			 					if(data.result =="success"){
			 						$(object).removeClass("block").addClass("hide");
									$(object).siblings(".selection_detail_cancel").removeClass("hide").addClass("block");
									$(object).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-sign-out").removeClass("block").addClass("hide");
									$(object).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-check-square-o").removeClass("hide").addClass("block");
									$(object).siblings(".selection_tab_detail").children().children().children().children(".detail_check").parent(".tc").addClass("hide");
										$(object).parent().parent().addClass("bggrass");
										var obj1 = document.getElementsByClassName("temp_td1");
					 					var ids1 = new Array();
					 					if(obj1.length != 0){
					 						for(i = 0; i < obj1.length; i++){
					 							//var s = $(obj[k]).html();
									    		//$(obj[k]).html(s.substring(0,s.length-3));
												//$(obj1[k]).removeClass("temp_td1");
												ids1.push($(obj1[i]).attr('id'));
											}
					 					}
					 					for(i = 0; i < ids1.length; i++){
					 							//var s = $(obj[k]).html();
									    		//$(obj[k]).html(s.substring(0,s.length-3));
												//$(obj1[k]).removeClass("temp_td1");
												$("#"+ids1[i]).removeClass("temp_td1");
												$("#"+ids1[i]).html("");
											}
					 					var obj2 = document.getElementsByClassName("temp_td2");
					 					var ids2 = new Array();
									    if(obj2.length != 0){
									    	for(i = 0; i < obj2.length; i++){
									    			//var s = $(obj[k]).html();
									    			//$(obj[k]).html(s.substring(0,s.length-3));
													ids2.push($(obj2[i]).attr('id'));
											}	
									    } 
									    for(i = 0; i < ids2.length; i++){
					 							//var s = $(obj[k]).html();
									    		//$(obj[k]).html(s.substring(0,s.length-3));
												//$(obj1[k]).removeClass("temp_td1");
												$("#"+ids2[i]).removeClass("temp_td2");
												var s = $("#"+ids2[i]).html();
												s = s.substring(0,s.length-3);
												$("#"+ids2[i]).html(s);
											}      
										var table = data.mapTimetable;
										for(var t in table) {
							           		var time = t;
							           		var num = table[t];
											$("#"+time).addClass("select_time");
							           		$("#"+time).html(num);
						           		}  
					 			}
					 			else if(data.result == "timeFail"){
					 				alert("该批次时间与已选课程存在冲突!");
					 			}else{
					 				alert("该批次已选满！");
					 			}
			 				}         
			 			});
	 					}
	 					else{
	 						alert("已超出选课时间，无法选择！");
	 					}
	 				}         
	 			});
		}
		
		
		function unSelectPublicGroup(object, groupId){
			$.ajax({
	 				url:'${pageContext.request.contextPath}/newtimetable/unSelectPublicGroup?groupId='+groupId,
	                 type:'POST', 
	 				error:function (request){
	 					alert('请求错误!');
	 				},
	 				success:function(data){ 
	 					
	 					if(data.result =="success"){
	 						$(object).removeClass("block").addClass("hide");
					$(object).siblings(".selection_detail_confirm").removeClass("hide").addClass("block");
					$(object).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-sign-out").removeClass("hide").addClass("block");
					$(object).parent().parent().parent().siblings(".selection_chosen").children().children(".fa-check-square-o").removeClass("block").addClass("hide");
					$(object).siblings(".selection_tab_detail").children().children().children().children(".detail_check").parent(".tc").removeClass("hide");
				    $(object).parent().parent().removeClass("bggrass");
	 						var table = data.mapTimetable;
								for(var t in table) {
					           		var time = t;
					           		var num = table[t];
									$("#"+time).removeClass("select_time");
					           		$("#"+time).html("");
				           		} 
				           	
	 					$.ajax({
	 				url:'${pageContext.request.contextPath}/newtimetable/findAppointmentsByCourseDetailNo?courseDetailNo='+data.courseDetailNo,
	                 type:'POST', 
	 				error:function (request){
	 					alert('请求错误!');
	 				},
	 				success:function(data){ 
	 					var obj1 = document.getElementsByClassName("temp_td1");
	 					var ids1 = new Array();
	 					if(obj1.length != 0){
	 						for(i = 0; i < obj1.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								ids1.push($(obj1[i]).attr('id'));
							}
	 					}
	 					for(i = 0; i < ids1.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								$("#"+ids1[i]).removeClass("temp_td1");
								$("#"+ids1[i]).html("");
							}
	 					var obj2 = document.getElementsByClassName("temp_td2");
	 					var ids2 = new Array();
					    if(obj2.length != 0){
					    	for(i = 0; i < obj2.length; i++){
					    			//var s = $(obj[k]).html();
					    			//$(obj[k]).html(s.substring(0,s.length-3));
									ids2.push($(obj2[i]).attr('id'));
							}	
					    } 
					    for(i = 0; i < ids2.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								$("#"+ids2[i]).removeClass("temp_td2");
								var s = $("#"+ids2[i]).html();
								s = s.substring(0,s.length-3);
								$("#"+ids2[i]).html(s);
							}      
	 					for(var t in data) {
				           		var time = t;
				           		var num = data[t];
				           		if($("#"+time).hasClass("chose_td")){
				           			$("#"+time).addClass("temp_td2");
				           			$("#"+time).html($("#"+time).html()+num);
				           		}
								else{
									$("#"+time).addClass("temp_td1");
				           			$("#"+time).html(num);
								}
				           }  
	 				}         
	 			});    
	 					}  
	 					else if(data.result =="chooseFailed"){
	 						alert("超出退选次数，无法退选!");
	 					}
	 					else if(data.result =="timeFailed"){
	 						alert("超出退选时间，无法退选！");
	 					}
	 				}   
	 			});
		}
		
		 $(document).ready(function(){
		  var courseDetailNo = $("#selectCourseDetailNo").val();
		  var mergeId = $("#selectMergeId").val();
		 	if(courseDetailNo != 0){
		 		var a = document.getElementById("select"+courseDetailNo);
		 		if($(a).hasClass("active")){
					$(a).parent().siblings(".timetable_tab_tit").addClass("bgw");
					$(a).parent().parent().siblings(".selection_tab_f").removeClass("hide").addClass("blocktr");
					$(a).parent().parent(".selection_chosen_class").addClass("bglb");
				}
				if($(a).hasClass("public_select")){
					$.ajax({
	 				url:'${pageContext.request.contextPath}/newtimetable/findAppointmentsByCourseDetailNo?courseDetailNo='+courseDetailNo,
	                 type:'POST', 
	 				error:function (request){
	 					alert('请求错误!');
	 				},
	 				success:function(data){ 
	 					var obj1 = document.getElementsByClassName("temp_td1");
	 					var ids1 = new Array();
	 					if(obj1.length != 0){
	 						for(i = 0; i < obj1.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								ids1.push($(obj1[i]).attr('id'));
							}
	 					}
	 					for(i = 0; i < ids1.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								$("#"+ids1[i]).removeClass("temp_td1");
								$("#"+ids1[i]).html("");
							}
	 					var obj2 = document.getElementsByClassName("temp_td2");
	 					var ids2 = new Array();
					    if(obj2.length != 0){
					    	for(i = 0; i < obj2.length; i++){
					    			//var s = $(obj[k]).html();
					    			//$(obj[k]).html(s.substring(0,s.length-3));
									ids2.push($(obj2[i]).attr('id'));
							}	
					    } 
					    for(i = 0; i < ids2.length; i++){
	 							//var s = $(obj[k]).html();
					    		//$(obj[k]).html(s.substring(0,s.length-3));
								//$(obj1[k]).removeClass("temp_td1");
								$("#"+ids2[i]).removeClass("temp_td2");
								var s = $("#"+ids2[i]).html();
								s = s.substring(0,s.length-3);
								$("#"+ids2[i]).html(s);
							}      
	 					for(var t in data) {
				           		var time = t;
				           		var num = data[t];
				           		if($("#"+time).hasClass("chose_td")){
				           			$("#"+time).addClass("temp_td2");
				           			$("#"+time).html($("#"+time).html()+num);
				           		}
								else{
									$("#"+time).addClass("temp_td1");
				           			$("#"+time).html(num);
								}
				           }  
	 				}         
	 			});
				}
		 	}
		 	if(mergeId != 0){
		 		var a = document.getElementById("select"+mergeId);
		 		if($(a).hasClass("active")){
					$(a).parent().siblings(".timetable_tab_tit").addClass("bgw");
					$(a).parent().parent().siblings(".selection_tab_f").removeClass("hide").addClass("blocktr");
					$(a).parent().parent(".selection_chosen_class").addClass("bglb");
				}
		 	}
		 });
		</script>
	</body>

</html>