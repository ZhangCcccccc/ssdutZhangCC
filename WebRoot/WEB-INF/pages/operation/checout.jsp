<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<fmt:setBundle basename="bundles.projecttermination-resources" />

<html>
<head>
<meta name="decorator" content="iframe" />
<title><fmt:message key="html.title" />
</title>
<!-- <meta name="decorator" content="iframe"/> -->

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
	function addproject() {

	}
</script>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/favicon.gif" />
</head>
<body>
	<div class="navigation">
		<div id="navigation">
			<ul>
				<li><a href="javascript:void(0)">实验室运行管理</a>
				</li>
				<li class="end"><a href="javascript:void(0)">查看大纲</a>
				</li>
			</ul>
		</div>
	</div>

	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
			<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">

					<div class="content-box">
						<div class="title">
							<div id="title">查看</div>
							<a onclick="window.history.go(-1)" class="btn btn-return">返回</a>
						</div>

						<div class="new-classroom">
							<fieldset class="introduce-box">
								<legend>基本信息</legend>
								<table>
									<tr>
										<th width="100px">课程代码</th>
										<td width="400px">${infor.schoolCourseInfoByClassId.courseNumber }</td>
										</select>
										</td>
										<th>英文名称</th>
										<td>${infor.enName}</td>
									</tr>

									<th>面向专业</th>
									<td><c:forEach items="${infor.systemMajors}" var="a">${a.MName}&nbsp;&nbsp;</c:forEach>
									</td>
									</select>
									<%--</td>
									--%><th width="100px">课程学分</th>
									<td >${infor.COperationOutlineCredit.credit}</td>
									</tr>

									<th>课程名称</th>
									<td>${infor.schoolCourseInfoByClassId.courseName}</td>
									</select>
									</td>
									<th>课程性质</th>
									<td><c:forEach
											items="${infor.CDictionarys}" var="a">${a.CName}&nbsp;&nbsp;</c:forEach>
									</td>
									</tr><%--

									<th>后续课程</th>
									<td>${infor.schoolCourseInfoByFollowUpCourses.courseName}</td>
									--%></select>
									</td>
									<th>先修课程</th>
									<td><c:forEach
											items="${infor.schoolCourseInfoes}" var="sc">${sc.courseName}&nbsp;&nbsp;</c:forEach></td>
									<th>开课学期</th>
									<td><c:forEach items="${infor.studyStages }" var="ss">${ss.CName }&nbsp;&nbsp;</c:forEach></td>
									</tr>
									
									<th>课程类型</th>
									<td>${infor.courseDescription }</td>
									<th>使用教材</th>
									<td>${infor.useMaterials}</td>
									</tr>

								</table>
							</fieldset>
							<fieldset class="introduce-box">
								<legend>课程详细信息</legend>
								<table>
								<%--<tr>
									<th>课程简介</th>
								</tr>
								<td>${infor.courseDescription}</td>
								</tr>
								<tr>
									<th>选课建议</th>
								</tr>
								<td>${infor.coursesAdvice}</td>
								</tr>
								--%><tr>
									<th>课程任务和教学目标</th>
								</tr>
								<td>${infor.outlineCourseTeachingTarget}</td>
								</tr>
							</table>
							</fieldset>
							<fieldset class="introduce-box">
								<legend>本课程与其它课程的联系和分工</legend>
								<table>
								<th>联系和分工</th>
								</tr>
								<td>${infor.basicContentCourse}</td>
								</tr>
								<%--<th>（二）课程基本要求</th>
								</tr>
								--%></table>
							</fieldset>
							<fieldset class="introduce-box">
									<legend>学习目标及作业评定</legend>
									<table>
										<tr>
											<th>作业、考核成绩及成绩评定</th>
										</tr>
										<td>${infor.assResultsPerEvaluation}</td>
										</tr>
										<%--<tr>
											<th>课程任务和教学目标</th>
										</tr>
										<td>${infor.outlineCourseTeachingTargetOver}</td>
										</tr>
									--%></table>
							</fieldset>
							<fieldset class="introduce-box">
									<legend>附件</legend>
									<table>
									 <c:forEach items="${infor.commonDocuments}" var="k">
									 <tr><td>${k.documentName}</td><td><a href="${pageContext.request.contextPath}/operation/downloadfile?idkey=${k.id}">下载</a></td></tr>
									 </c:forEach>	
                                     
									</table>
							</fieldset>
							<fieldset class="introduce-box">
									<legend>实验项目与内容添加</legend>
									<table>
									<tr>
										<td>实验项目与内容添加</td>
										<td>
									</tr>
									<table>

										<tr>
											<td>实验项目编号</td>
											<td>实验项目名称</td>
											<td>实验类型</td>
											<td>实验时数</td>
											<td>每组人数</td>
											<td>实验室</td>
											<td>备注（必做/选做）</td>
										</tr>
										<c:forEach items="${infor.operationItems}" var="s">
											<tr>
												<td>${s.lpCodeCustom}</td>
												<td>${s.lpName}</td>
												<td>${s.CDictionaryByLpCategoryMain.CName}</td>
												<td>${s.lpDepartmentHours}</td>
												<td>${s.lpStudentNumberGroup}</td>
												<td><c:forEach items="${s.labRooms}" var="a">${a.labRoomName}/</c:forEach></td>
												<td></td>
											</tr>
										</c:forEach>
									</table>
								</table>
							</fieldset>
							</div>

								</div>
								<!-- </div> -->

								</div>

								</div>
								<!-- </div> -->

								</div>
</body>
<!-------------列表结束----------->
</html>