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
			<div class="all_main_content ovh">
				<div class="w74p l">
					<div class="gray_line">
						<form name="form"
								action="${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetablePublic?courseDetailNo=${courseDetailNo}"
								method="post">
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
								</td>
							</tr>
						</table>
						<table>
							<tr>
							<td>
								<button>查询</button>
								<button>取消</button>
							</td>
							</tr>
						</table>
						</form>
						<span class="o1 b r mr2p">${labRoom.labRoomCapacity }</span>
						<span class="o1 b r">容量：</span>
						<span class="b8e ls1 b r mr2p">${labRoom.labRoomName }</span>
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
				<div class="w25p message_container r">
					<c:if test="${isComplete eq 0 }">
					<div class="public_message">
						<div class="pub_tit">
							<div class="l">
								<span class="b">${schoolCourseDetail.courseName }<span class="r1 ml10">
								<a href='javascript:void(0)'onclick='listTimetableStudents("${courseDetailNo}",0)'>名单:</a>${fn:length(schoolCourseDetail.schoolCourseStudents)}</span>人</span>
								<span class="bgb wh f13 p2">正在排课</span>
							</div>
							<button class="r" onclick="turnPage()">返回</button>
								<c:if test="${isPartComplete eq 0 }">
								
								<button class="r" type="button" onclick="completeAll('${courseDetailNo}')">全部完成</button>
							</c:if>
						</div>
						<div class="track_detail">
							<div class="course_method cf" style="width:90%;height:auto;">
								<form>
									<div class="l">
										<input type="hidden" value="1" id="style"/>
										<input type="radio" class="student_course" name="course_method" onclick="changeStyle(1)" />
										<span >学生自选</span>
									</div>
									<div class="l">
										<input type="radio" class="teacher_course" name="course_method" onclick="changeStyle(2)"/>
										<span>教师强排</span>
									</div>
									<div class="l">
										<input type="radio" class="teacher_course" name="course_method" onclick="changeStyle(3)"/>
										<span>自主分批</span>
									</div>
									<div class="l">
										<input type="radio" class="teacher_course" name="course_method"  checked="checked" onclick="changeStyle(4)"/>
										<span>开放排课</span>
									</div>
								</form>
							</div>
						</div>
						<div class="pub_select">
							<div>
								<span class="pr5">选课开始:</span>
								<input style="width:42%;height:25px;float:left;margin:0 3%;" id="courseStartTime"  class="Wdate datepicker" value="${courseStartTime}"   onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" type="text" name="date"   readonly />
							</div>
							<div>
								<span class="pr5">选课结束:</span>
								<span>每节课开始前</span>
								<input name="courseEndMinute" value="${schoolCourseDetail.courseEndMinute}" id="courseEndMinute"/>
								<span>分钟</span>
							</div>
							<div>
								<span class="pr5">退选要求:</span>
								<span>每节课开始前</span>
								<input name="withdrawalMinute" value="${schoolCourseDetail.withdrawalMinute}"  id="withdrawalMinute"/>
								<span>分钟</span>
							</div>
							<div>
								<span class="pr5">最多退选次数:</span>
								<input name="withdrawalTimes" value="${schoolCourseDetail.withdrawalTimes}"  id="withdrawalTimes"/>
								<span>次</span>
							</div>
							<div>
								<span class="pr5">选课要求:</span>
								<textarea placeholder="请输入选课要求" name="courseRequirement" id="courseRequirement">${schoolCourseDetail.courseRequirement}</textarea>
							</div>
						</div>
						
						 <c:forEach items="${listThisCourseLabTimetableAppointments}" var="curr" varStatus="i">
							<div class="course_select cf">
							<div>
								<span>${i.count }</span>
								<span>第${curr.timetableAppointment.startWeek }周</span>
								<span>星期${curr.timetableAppointment.weekday}</span>
								<span>${curr.timetableAppointment.startClass}-${curr.timetableAppointment.endClass }</span>
								<a class="fa fa-times r close" title="关闭" onclick="deletePublicElectiveCourseAppointment(${curr.timetableAppointment.id})"></a>
							</div>
							</div>
						</c:forEach> 
						<div class="sample">
						</div>
						<div class="w100p ovh">
							<button class="r mt5 mb10 mr5p" type="button" onclick="sendMessageAll('${courseDetailNo}')">发送消息</button>
							<button class="r mt5 mb10 mr5p" type="button" onclick="completeBatchTimetable()">完成</button>
						</div>
					</div>
					</c:if>
					<c:if test="${isComplete eq 1 }">
					<div class="public_message">
						<div class="pub_tit">
							<div class="l">
								<span class="b">${schoolCourseDetail.courseName }<span class="r1 ml10">${fn:length(schoolCourseDetail.schoolCourseStudents)}</span>人</span>
								<span class="bgb wh f13 p2">排课完成</span>
							</div>
							<button class="r" onclick="turnPage()">返回</button>
							<button class="r" type="button" onclick="sendMessageAll(${courseDetailNo})">发送消息</button>
						</div>
						<div class="track_detail">
							<div class="course_method" style="width:90%">
								<form>
									<div class="l mr2p">
										<input type="hidden" value="1" id="style"/>
										<input type="radio" class="student_course" name="course_method" onclick="changeStyle(1)" />
										<span class="orange b bbo">学生自选</span>
									</div>
									<div class="l mr2p">
										<input type="radio" class="teacher_course" name="course_method" onclick="changeStyle(2)"/>
										<span>教师强排</span>
									</div>
									<div class="l">
										<input type="radio" class="teacher_course" name="course_method" onclick="changeStyle(3)"/>
										<span>自主分批</span>
									</div>
									<div class="l">
										<input type="radio" class="teacher_course" name="course_method"  checked="checked" onclick="changeStyle(4)"/>
										<span>公选排课</span>
									</div>
								</form>
							</div>
						</div>
						<div class="pub_select">
							<div>
								<span class="pr5">选课开始:</span>
								${courseStartTime}
							</div>
							<div>
								<span class="pr5">选课结束:</span>
								<span>每节课开始前</span>
								<span>${schoolCourseDetail.courseEndMinute}</span>
								<span>分钟</span>
							</div>
							<div>
								<span class="pr5">退选要求:</span>
								<span>每节课开始前</span>
								<span>${schoolCourseDetail.withdrawalMinute}</span>
								<span>分钟</span>
							</div>
							<div>
								<span class="pr5">最多退选次数:</span>
								<span>${schoolCourseDetail.withdrawalTimes}</span>
								<span>次</span>
							</div>
							<div>
								<span class="pr5">选课要求:</span>
								<span>${schoolCourseDetail.courseRequirement}</span>
							</div>
						</div>
						<c:forEach items="${listThisCourseLabTimetableAppointments}" var="curr" varStatus="i">
							<div class="course_select cf">
							<div>
								<span>${i.count }</span>
								<span>第${curr.timetableAppointment.startWeek }周</span>
								<span>星期${curr.timetableAppointment.weekday}</span>
								<span>${curr.timetableAppointment.startClass}-${curr.timetableAppointment.endClass }</span>
							</div>
							</div>
						</c:forEach>
						<div class="sample">
						</div>
						<div class="w100p ovh">
						</div>
					</div>
					</c:if>
				</div>
			</div>
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
		</style>
		<!-- <script type="text/javascript">
						function turnPage(){
							//track.goToPage(3);
							window.location.href = "${pageContext.request.contextPath}/newtimetable/listMySchedule?currpage="+${currpage};
						}
				</script> -->
		<script type="text/javascript">
			$(function(){
				var $height =$(".message_container").height();
				if($height>1278){
					$(".message_container").css("height","1278px");
			 		$(".message_container").css("overflow-y","scroll");
				}
			 	
			})
			var timetableNum = ${timetableLength};
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

			$(".timetable_tab td").click(
				function() {
					$(".course_select").removeClass("hide").addClass("block");
				}
			);
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
			
			
			function chooseThis(week,weekday,classes){
				timetableNum = timetableNum + 1;
				var $span1 = $("<span name='orderNum'>"+timetableNum+"</span>");
				var $span2 = $("<span>第"+week+"周</span>");
				var $span3 = $("<span>星期"+weekday+"</span>");
				var $span4 = $("<span>"+classes+"-"+classes*2+"</span>");
				var $i = $("<i class=fa fa-times r close' title='关闭'></i>");
				var $div = $("<div></div>");
				$div.append($span1);
				$div.append($span2);
				$div.append($span3);
				$div.append($span4);
				$div.append($i);
				var $divNew = $("<div class='course_select cf'></div>");
				$divNew.append($div)
				$(".public_message").find(".course_select:last").before($divNew);
			}
			
			$(".choose_this").click(
				function(e) {
						if($(e.target).hasClass("choose_this")){
							
						 var id = $(e.target).attr('id');
						 var s = id.split("-");
					
		            	         $.ajax({
		            	            url:"${pageContext.request.contextPath}/newtimetable/saveSinglePublicElectiveCourseTimetable?courseDetailNo=${courseDetailNo}",
		            	            type:'POST',
		            	            data:{term:${schoolTerm.id},week:s[0], weekday:s[1],selectClass:s[2],labRoom:${roomId}},
		            	            error:function (request){
		            	              alert('请求错误!');
		            	         },
						         success:function(result)
						         {
						            if(result == "0" )
						            {
						            alert("请先生成批次");
						            }
						         	if(result == "-1")
						            {
						            	alert("该时间段已经被其他排课占用，请您添加其他时间段排课");
						            }
						            else{
						            	 $(e.target).removeClass("choose_this");
										 $(e.target).css("backgroundColor", "green");
										 timetableNum = timetableNum + 1;
						            	var $span1 = $("<span  name='orderNum'>"+timetableNum+"</span>");
										var $span2 = $("<span>第"+s[0]+"周</span>");
										var $span3 = $("<span>星期"+s[1]+"</span>");
										var $span4 = $("<span>"+(parseInt(s[2])*2-1)+"-"+(parseInt(s[2])*2)+"</span>");
										var $i = $("<i class='fa fa-times r close' title='关闭' onclick='deletePublicElectiveCourseAppointment("+result+")'></i>");
										var $week = $("<input name='selectWeek' type='hidden' value='"+s[0]+"'/>");
										var $weekday = $("<input name='selectWeekday' type='hidden' value='"+s[1]+"'/>");
										var $classes = $("<input name='selectClass' type='hidden' value='"+s[2]+"'/>");
										var $div = $("<div></div>");
										$div.append($span1);
										$div.append($span2);
										$div.append($span3);
										$div.append($span4);
										$div.append($i);
										$div.append($week);
										$div.append($weekday);
										$div.append($classes);
										var $divNew = $("<div class='course_select cf sample' id='d"+id+"'></div>");
										$divNew.append($div)
										$(".public_message").find(".sample:last").after($divNew);
						            }
						          }
						});
						}
					}
			);
			
			function unchoose_this(week,weekday,classes){
				timetableNum = timetableNum-1;
				$("#d"+week+"-"+weekday+"-"+classes).remove();
				$("#"+week+"-"+weekday+"-"+classes).css('background','none');
				var obj = document.getElementsByName("orderNum");
				for(i=0;i<obj.length;i++){
				   obj[i].innerText=(i+${timetableLength}+1);
				}
			}
			
			function doPublicElectiveCourseTimetable(){
				var weeks = new Array();
				var weekdays = new Array();
				var classes = new Array();
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
				var courseStartTime=$("#courseStartTime").val();
				var courseEndMinute=$("#courseEndMinute").val();
				var withdrawalMinute=$("#withdrawalMinute").val();
				var withdrawalTimes=$("#withdrawalTimes").val();
				var courseRequirement=$("#courseRequirement").val();
				if(courseStartTime == null){
					courseStartTime = -1;
				}
				if(courseEndMinute == null){
					courseEndMinute = -1;
				}
				if(withdrawalTimes == null){
					withdrawalTimes = -1;
				}
				if(withdrawalMinute == null){
					withdrawalMinute = -1;
				}
				if(courseRequirement == null){
					courseRequirement = -1;
				}
				$.ajax({
			         url:"${pageContext.request.contextPath}/newtimetable/savePublicElectiveCourseTimetable?courseDetailNo=${courseDetailNo}",
			         dataType:"json",
			         type:'GET',
			         data:{term:${schoolTerm.id},weeks:weeks.join(","), weekdays:weekdays.join(","),classes:classes.join(","),labRoom:${roomId}
			         ,courseStartTime:courseStartTime,courseEndMinute:courseEndMinute, withdrawalMinute:withdrawalMinute
			         ,withdrawalTimes:withdrawalTimes ,courseRequirement:courseRequirement},
			         complete:function(result)
			         {
			           window.location.reload();
			          }
			});
			}
			
			
			function deletePublicElectiveCourseAppointment(appointmentId){
				$.messager.confirm('提示','确定删除？',function(r){
		  			if(r) {
			  			$.ajax({
					         url:"${pageContext.request.contextPath}/newtimetable/deleteAppointment?appointmentId="+appointmentId,
					         dataType:"json",
					         type:'GET',
					         complete:function(result)
					         {
					           window.location.reload();
					          }
						});
		  			}else {
		  				//window.location.reload();
		  			}
		  		});
			}
			
			
			function completeBatchTimetable(){
			var weeks = new Array();
				var weekdays = new Array();
				var classes = new Array();
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
				var courseStartTime=$("#courseStartTime").val();
				var courseEndMinute=$("#courseEndMinute").val();
				var withdrawalMinute=$("#withdrawalMinute").val();
				var withdrawalTimes=$("#withdrawalTimes").val();
				var courseRequirement=$("#courseRequirement").val();
				if(courseStartTime == null){
					courseStartTime = -1;
				}
				if(courseEndMinute == null){
					courseEndMinute = -1;
				}
				if(withdrawalTimes == null){
					withdrawalTimes = -1;
				}
				if(withdrawalMinute == null){
					withdrawalMinute = -1;
				}
				if(courseRequirement == null){
					courseRequirement = -1;
				}
				$.ajax({
			         url:"${pageContext.request.contextPath}/newtimetable/savePublicElectiveCourseTimetable?courseDetailNo=${courseDetailNo}",
			         dataType:"json",
			         type:'GET',
			         data:{term:${schoolTerm.id},weeks:weeks.join(","), weekdays:weekdays.join(","),classes:classes.join(","),labRoom:${roomId}
			         ,courseStartTime:courseStartTime,courseEndMinute:courseEndMinute, withdrawalMinute:withdrawalMinute
			         ,withdrawalTimes:withdrawalTimes ,courseRequirement:courseRequirement},
			         complete:function(result)
			         {
			           
			          }
			});
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