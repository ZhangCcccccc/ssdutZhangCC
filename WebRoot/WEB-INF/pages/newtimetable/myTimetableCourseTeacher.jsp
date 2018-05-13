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
		
		<!-- 下拉框的样式 -->
 		 <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  		<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  		<!-- 下拉的样式结束 --> 
  		<!-- 下拉的样式结束 --> 
  		<script type="text/javascript"	src="${pageContext.request.contextPath}/js/browse.js"></script>
  		<!-- 下拉的样式结束 --> 
  		<!-- 打印开始 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
		<!-- 打印结束 -->
	
		<!-- 打印、导出组件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/LodopFuncs.js"></script>
		<script type="text/javascript">
			/* $(document).ready(function(){
				var term = $("#term").val();
				$.ajax({
					type: "POST",
					url: "${pageContext.request.contextPath}/newtimetable/getCourseByTerm",
					data: {termId:term},
					success: function(data){
						$("#courseId").html(data.courses);
						$("#courseId").trigger("liszt:updated");
					}
				});
			})
			 */
			//$("#term").change(function(){
			function getCourseByTerm(){
				var term = $("#term").val();
				$.ajax({
					type: "POST",
					url: "${pageContext.request.contextPath}/newtimetable/getCourseByTerm",
					data: {termId:term},
					success: function(data){
						$("#courseId").html(data.courses);
						$("#courseId").trigger("liszt:updated");
					}
				});
			};
		</script>
	</head>

	<body>
		<div id="bgheight">
			<script type="text/javascript">
				$(function() {
					$("#bgheight").height($(window).height() / 1);
				})
			</script>
			<div class="all_main_content">
				<div class="gray_line">
				<form name="form"
								action="${pageContext.request.contextPath}/newtimetable/myTimetableCourseTeacher"
								method="post">
					<table style="width:400px;">
						<tr>
							<td>
								<span>学期:&nbsp;</span>
								<select id="term" name="term" class="chzn-select" style="width:300px;" onchange="getCourseByTerm();">
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
					<table style="width:400px;">
						<tr>
							<td>
								<span>课程:&nbsp;</span>
								<select name="courseId" class="chzn-select" style="width:300px;" id="courseId">
									<option value="">请选择</option>
									<c:forEach items="${courses}" var="curr">
									<c:if test="${courseId == curr.key}">
										<option value="${curr.key}" selected>${curr.value}</option>
									</c:if>
									<c:if test="${courseId != curr.key }">
										<option value="${curr.key}">${curr.value}</option>
									</c:if>	
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<table style="width:400px;">
						<tr>
							<td>
								<span>实验室:&nbsp;</span>
								<select name="labId" class="chzn-select" style="width:300px;">
									<option value="">请选择</option>
									<c:forEach items="${labRoomMap}" var="curr">
									<c:if test="${labId == curr.key}">
										<option value="${curr.key}" selected>${curr.value}</option>
									</c:if>
									<c:if test="${labId != curr.key }">
										<option value="${curr.key}">${curr.value}</option>
									</c:if>	
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<table>
						<tr>
							<td>
								<button type="button" onclick="query()">查询</button>
								<button type="button" onclick="cancel()">取消</button>
							</td>
						</tr>
					</table>
					</form>
					<i class="fa fa-print r mt10 mr2p f18 poi" title="打印" onclick="printTimetable()"></i>
					<a class="fa fa-file-excel-o r mt10 mr1p f18 poi" title="导出" onclick="exportTimetable()"></a>
				</div>
				<div class="bgwo w100p ptb10">
					<table class="timetable_tab" cellspacing="0" id="myShow">
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
								            	<c:set var="courseNumber" value=""/>
								            	<c:set var="content" value=""/>
								            	<c:set var="groupId" value=""/>
								            	<c:set var="startDate" value=""/>
								            	<c:set var="endDate" value=""/>
								            	<c:set var="sameCount" value="0"/>
								            	<c:forEach items="${appointments}" var="currTimetable">
								            		<!-- 无跳周跳节 -->
								            		<c:if test="${currTimetable.timetableAppointmentSameNumbers eq null || fn:length(currTimetable.timetableAppointmentSameNumbers) == 0 }">
								            			<c:if test="${currWeek1[2] >= currTimetable.startWeek && currWeek1[2] <= currTimetable.endWeek
									            		&& currTimetable.weekday == cStatus1.index 
									            		&& cStatus2.index > currTimetable.startClass/2 && cStatus2.index <= currTimetable.endClass/2}">
									            				<c:set var="flag" value="3"/>
									            				<c:if test="${type eq 1 }">
									            					<c:set var="teachers" value=""/>
									            					<c:if test="${currTimetable.timetableTeacherRelateds eq null}">
									            						<c:set var="teachers" value="课题组"/>
									            					</c:if>
									            					<c:if test="${currTimetable.timetableTeacherRelateds ne null}">
										            					<c:forEach items="${currTimetable.timetableTeacherRelateds}" var="teacher">
													            			<c:set var="teachers" value="${teachers}&nbsp;${teacher.user.cname}"/>
													            		</c:forEach>
									            					</c:if>
									            					<c:set var="temp2" value=""/>
									            					<c:forEach items="${currTimetable.timetableLabRelateds }" var="labRelated">
									            						<c:set var="temp2" value="${temp2}<br>${labRelated.labRoom.labRoomAddress}"></c:set>
									            					</c:forEach>
									            					<c:if test="${sameCount == 0}">
										            					<!-- 合班 -->
										            					<c:if test="${currTimetable.schoolCourseMerge ne null}">
											            					<c:set var="courseNumber" value="${currTimetable.schoolCourseMerge.courseNumber}${temp2}<br>${teachers}<br>"/>
											            				</c:if>
											            				<!-- 不合班 -->
											            				<c:if test="${currTimetable.schoolCourseMerge eq null}">
											            					<c:set var="courseNumber" value="${currTimetable.schoolCourseDetail.courseNumber}${temp2}<br>${teachers}<br>"/>
											            				</c:if>
											            			</c:if>
											            			<c:if test="${sameCount != 0}">
										            					<!-- 合班 -->
										            					<c:if test="${currTimetable.schoolCourseMerge ne null}">
											            					<c:set var="courseNumber" value="${courseNumber}<br>${currTimetable.schoolCourseMerge.courseNumber}${temp2}<br>${teachers}<br>"/>
											            				</c:if>
											            				<!-- 不合班 -->
											            				<c:if test="${currTimetable.schoolCourseMerge eq null}">
											            					<c:set var="courseNumber" value="${courseNumber}<br>${currTimetable.schoolCourseDetail.courseNumber}${temp2}<br>${teachers}<br>"/>
											            				</c:if>
											            			</c:if>
										            				<c:set var="sameCount" value="${sameCount+1}"/>
									            				</c:if>
									            				<c:if test="${type ne 1}">
									            					<c:set var="teachers" value=""/>
									            					<c:if test="${currTimetable.timetableTeacherRelateds eq null}">
									            						<c:set var="teachers" value="课题组"/>
									            					</c:if>
									            					<c:if test="${currTimetable.timetableTeacherRelateds ne null}">
										            					<c:forEach items="${currTimetable.timetableTeacherRelateds}" var="teacher">
													            			<c:set var="teachers" value="${teachers}&nbsp;${teacher.user.cname}"/>
													            		</c:forEach>
									            					</c:if>
									            					<c:set var="codeCustom" value=""/>
												            		<c:forEach items="${currTimetable.timetableItemRelateds}" var="currItem">
												            			<c:set var="codeCustom" value="${currItem.operationItem.lpCodeCustom}"/>
												            		</c:forEach>
												            		<c:if test="${empty codeCustom}">
												            			<c:set var="codeCustom" value="${currTimetable.schoolCourseDetail.courseNumber}"/>
												            		</c:if>
									            					<c:set var="temp1" value=""/>
									            					<c:set var="temp2" value=""/>
									            					<c:forEach items="${currTimetable.timetableGroups}" var="group">
									            						<c:set var="temp1" value="${temp1 }${codeCustom}-${group.groupName}"/>
									            						<c:set var="groupId" value="${group.id}"/>
									            					</c:forEach>
									            					<c:if test="${currTimetable.timetableLabRelateds ne null}">
										            					<c:forEach items="${currTimetable.timetableLabRelateds }" var="labRelated">
										            						<c:set var="temp2" value="${temp2}<br>${labRelated.labRoom.labRoomAddress}"></c:set>
										            					</c:forEach>
										            				</c:if>
									            					<c:set var="content" value="${temp1}"/>
									            					<c:if test="${not empty currTimetable.actualStartDate }">
									            						<c:set var="startDate"><fmt:formatDate value='${currTimetable.actualStartDate.time}' type='date' pattern='HH:mm'/></c:set>
									            						<c:set var="temp2" value="${temp2}<br>${startDate}"/>
									            					</c:if>
									            					<c:if test="${not empty currTimetable.actualEndDate }">
									            						<c:set var="endDate"><fmt:formatDate value='${currTimetable.actualEndDate.time}' type='date' pattern='HH:mm'/></c:set>
									            						<c:set var="temp2" value="${temp2}-${endDate}"/>
									            					</c:if>
									            					<c:set var="link"><a href="${pageContext.request.contextPath}/newtimetable/viewTimetableGroupStudents?groupId=${groupId}&courseId=${courseId }&termId=${termId }&labId=${labId }&currpage=1">${content }</a></c:set>
								            						<c:set var="temp2" value="${link}${temp2}"/>
								            						<c:if test="${sameCount == 0}">
								            							<c:set var="courseNumber" value="${courseNumber}${temp2}<br>${teachers}<br>"/>
								            						</c:if>
								            						<c:if test="${sameCount != 0}">
								            							<c:set var="courseNumber" value="${courseNumber}<br>${temp2}<br>${teachers}<br>"/>
								            						</c:if>
								            						<c:set var="sameCount" value="${sameCount+1}"/>
									            				</c:if>
										            	</c:if>
								            		</c:if>
								            		<c:if test="${currTimetable.timetableAppointmentSameNumbers ne null &&  fn:length(currTimetable.timetableAppointmentSameNumbers) > 0 }">
								            			<c:forEach items = "${currTimetable.timetableAppointmentSameNumbers}" var="currSame">
								            				<c:if test="${currWeek1[2] >= currSame.startWeek && currWeek1[2] <= currSame.endWeek
										            		&& currTimetable.weekday == cStatus1.index 
										            		&& cStatus2.index > currSame.startClass/2 && cStatus2.index <= currSame.endClass/2}">
									            				<c:set var="flag" value="3"/>
									            				<c:if test="${type eq 1 }">
									            					<c:set var="teachers" value=""/>
									            					<c:if test="${currTimetable.timetableTeacherRelateds eq null}">
									            						<c:set var="teachers" value="课题组"/>
									            					</c:if>
									            					<c:if test="${currTimetable.timetableTeacherRelateds ne null}">
										            					<c:forEach items="${currTimetable.timetableTeacherRelateds}" var="teacher">
													            			<c:set var="teachers" value="${teachers}&nbsp;${teacher.user.cname}"/>
													            		</c:forEach>
									            					</c:if>
									            					<c:if test="${sameCount eq 0}">
										            					<c:if test="${currTimetable.schoolCourseMerge ne null}">
											            					<c:set var="courseNumber" value="${currTimetable.schoolCourseMerge.courseNumber }<br>${teachers}<br>"/>
											            				</c:if>
											            				<c:if test="${currTimetable.schoolCourseMerge eq null}">
											            					<c:set var="courseNumber" value="${currTimetable.schoolCourseDetail.courseNumber }<br>${teachers}<br>"/>
											            				</c:if>
									            					</c:if>
									            					<c:if test="${sameCount ne 0}">
										            					<c:if test="${currTimetable.schoolCourseMerge ne null}">
											            					<c:set var="courseNumber" value="${courseNumber}<br>${currTimetable.schoolCourseMerge.courseNumber }<br>${teachers}<br>"/>
											            				</c:if>
											            				<c:if test="${currTimetable.schoolCourseMerge eq null}">
											            					<c:set var="courseNumber" value="${courseNumber}<br>${currTimetable.schoolCourseDetail.courseNumber }<br>${teachers}<br>"/>
											            				</c:if>
									            					</c:if>
										            				<c:set var="sameCount" value="${sameCount+1}"/>
									            				</c:if>
									            				<c:if test="${type ne 1}">
									            					<c:set var="teachers" value=""/>
									            					<c:if test="${currTimetable.timetableTeacherRelateds eq null}">
									            						<c:set var="teachers" value="课题组"/>
									            					</c:if>
									            					<c:if test="${currTimetable.timetableTeacherRelateds ne null}">
										            					<c:forEach items="${currTimetable.timetableTeacherRelateds}" var="teacher">
													            			<c:set var="teachers" value="${teachers}&nbsp;${teacher.user.cname}"/>
													            		</c:forEach>
									            					</c:if>
									            					<c:set var="codeCustom" value=""/>
												            		<c:forEach items="${currTimetable.timetableItemRelateds }" var="currItem">
												            			<c:set var="codeCustom" value="${currItem.operationItem.lpCodeCustom}"/>
												            		</c:forEach>
									            					<c:set var="temp1" value=""/>
									            					<c:set var="temp2" value=""/>
										            					<c:forEach items="${currTimetable.timetableGroups}" var="group">
										            						<c:set var="temp1" value="${temp1 }${codeCustom}-${group.groupName}"/>
										            						<c:set var="groupId" value="${group.id}"/>
										            					</c:forEach>
										            					<c:if test="${currTimetable.timetableLabRelateds ne null}">
											            					<c:forEach items="${currTimetable.timetableLabRelateds }" var="labRelated">
											            						<c:set var="temp2" value="${temp2}<br>${labRelated.labRoom.labRoomAddress}"></c:set>
											            					</c:forEach>
											            				</c:if>
										            					<c:set var="content" value="${temp1}"/>
										            					<c:if test="${not empty currTimetable.actualStartDate }">
										            						<c:set var="startDate"><fmt:formatDate value='${currTimetable.actualStartDate.time }' type='date' pattern='HH:mm'/></c:set>
										            						<c:set var="temp2" value="${temp2}<br>${startDate}"/>
										            					</c:if>
										            					<c:if test="${not empty currTimetable.actualEndDate }">
										            						<c:set var="endDate"><fmt:formatDate value='${currTimetable.actualEndDate.time }' type='date' pattern='HH:mm'/></c:set>
										            						<c:set var="temp2" value="${temp2}-${endDate}"/>
										            					</c:if>
										            					<c:set var="link"><a href="${pageContext.request.contextPath}/newtimetable/viewTimetableGroupStudents?groupId=${groupId}&courseId=${courseId }&termId=${termId }&labId=${labId }&currpage=1">${content }</a></c:set>
									            						<c:set var="temp2" value="${link}${temp2}"/>
									            						<c:if test="${sameCount == 0}">
									            							<c:set var="courseNumber" value="${courseNumber }${temp2}<br>${teachers}"/>
									            						</c:if>
									            						<c:if test="${sameCount != 0}">
									            							<c:set var="courseNumber" value="${courseNumber }<br>${temp2}<br>${teachers}"/>
									            						</c:if>
									            						<c:set var="sameCount" value="${sameCount+1}"/>
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
										 		<c:if test="${empty specialSchoolWeeks}">
										 		</c:if>
										 		<c:if test="${flag == 1}">
								            		<td rowspan="5">${dayName }</td>
								            	</c:if>
								            	<c:if test="${flag == 3}">
								            		<td>
								            			${courseNumber }
								            		</td>
								            	</c:if>
								            	<c:if test="${flag != 1 && flag != 2 && flag != 3}">
								            		<td></td>
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
								            	<c:if test="${not empty specialSchoolWeeks}">
								            	<c:set var="flag" value="0"/>
								            	<c:set var="dayName" value="0"/>
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
										 		<c:if test="${flag == 1}">
								            		<td  rowspan="5">${dayName }</td>
								            	</c:if>
								            	<c:if test="${flag != 1 && flag != 2}">
								            		<td></td>
								            	</c:if>
										 		</c:if>
										 		<c:if test="${empty specialSchoolWeeks}">
										 			<td></td>
										 		</c:if>
										 	</c:forEach>
							       		</tr>
							       	</c:forEach>
							     </c:forEach>
							     </c:if>
					</table>
					<table class="experimental_list_tab" cellspacing="0" style="margin:25px auto 0;">
						<tr>
							<th>课程</th>
							<th>代号</th>
							<th>学时</th>
							<th>学分</th>
							<th>性质</th>
							<th>考核方式</th>
							<th>上课教室</th>
						</tr>
						<c:forEach items="${courseInfos}" var="curr">
								<tr>
									<c:set var="outline" value="0"/>
									<c:forEach items="${curr.operationOutlinesForClassId }" var="currOutline">
										<c:set var="outline" value="${currOutline }"/>	
									</c:forEach>
									<td class="timetable_tab_tit">${curr.courseName }</td>
									<td>${curr.courseNumber }</td>
									<td>${outline.period }</td>
									<td>${outline.COperationOutlineCredit.credit}</td>
									<td>${outline.courseDescription}</td>
									<td>${outline.assResultsPerEvaluation }</td>
									<td></td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
		</div>
		</div>
		<style>
			.experimental_list_tab td {
				text-align: center;
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
			
			function cancel(){
  				window.location.href="${pageContext.request.contextPath}/newtimetable/myTimetableCourseTeacher";
			}
			
			function exportTimetable(){
				document.form.action="${pageContext.request.contextPath}/newtimetable/exportTimetable";
				document.form.submit();
			}
			function query(){
				document.form.action="${pageContext.request.contextPath}/newtimetable/myTimetableCourseTeacher";
				document.form.submit();
			}
			
			
			function printTimetable(){
				$('#myShow').jqprint();
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