<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.pageModel-resources" />
<html>
<head>
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/css/iconFont.css">
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/iconFont.css"	rel="stylesheet">
<meta name="decorator" content="iframe" />
<script>
/*
 *直接排课弹出窗口
 */
function startTimetable(courseCode) {
	var sessionId = $("#sessionId").val();
	$('#doStart').window({
		left : "100px",
		top : "0px"
	});
	$('#doStart').window('open');
	$('#doStart').window('open');
	$('#form_lab').attr(
			"action",
			"${pageContext.request.contextPath}/timetable/doDirectTimetable?courseCode=" + courseCode+"&currpage=${pageModel.nextPage-1}");

}
/*
 *调整排课弹出窗口
 */
function doAdjustTimetable(courseCode) {
	var sessionId = $("#sessionId").val();
	var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/openAdjustTimetable?currpage=${pageModel.nextPage-1}&courseCode='
				+ courseCode + '" style="width:100%;height:100%;"></iframe>'
		$('#doAdjust').html(con);
		$('#doAdjust').window({
			left : "0px",
			top : "0px"
		});
		$('#doAdjust').window('open');
}
/*
 *查看排课弹出窗口
 */
function listTimetable(courseCode) {
	var sessionId = $("#sessionId").val();
	var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/openSearchTimetable?courseCode='
			+ courseCode + '" style="width:100%;height:100%;"></iframe>'
	$('#doSearch').html(con);
	$('#doSearch').window({
		left : "0px",
		top : "0px"
	});
	$('#doSearch').window('open');
}

/*
 *查看学生名单
 */
function listTimetableStudents(courseDetailNo) {
	var sessionId = $("#sessionId").val();
	var con = '<iframe scrolling="yes" id="message1" frameborder="0"  src="${pageContext.request.contextPath}/timetable/openSearchStudent?courseDetailNo='
			+ courseDetailNo
			+ '" style="width:100%;height:100%;"></iframe>'
	$('#doSearchStudents').html(con);
	$('#doSearchStudents').window({
		left : "0px",
		top : "0px"
	});
	$('#doSearchStudents').window('open');
}
</script>

<!-- 下拉的样式 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
</head>

<body>
<div class="iStyle_RightInner">
<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">共享数据</a>
			</li>
			<li class="end">
			选课组管理
			</li>
		</ul>
	</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
	<form:form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/listTimetableTerm?currpage=1&id=-1"	modelAttribute="schoolCourseDetail">
	<ul>
		<li>选课组： 
		<form:select class="chzn-select"	path="schoolCourse.courseNo" id="schoolCourse.courseNo"	cssStyle="width:350px">
				<c:forEach items="${schedulingCourseAllMap}" var="current"	varStatus="i">
					<form:option value="${current.courseNo}"	label="${current.schoolTerm.termName}; ${current.userByTeacher.cname}:${current.userByTeacher.username};${current.courseName}" />
				</c:forEach>
		</form:select></li>
		<li><input type="submit" value="查询"></li>
		<li><a href="${pageContext.request.contextPath}/sharedData/findAllCourseSchedule?currpage=1&id=-1"><input type="button" value="取消查询"></a></li>

	</ul>
	</form:form>
</div>
<div class="content-box">
<div class="title">选课组基本信息</div>
<table>
<thead>
<tr>
	<th>序号</th>
	<th>课程编号</th>
	<th>课程名称</th>
	<th>选课组编号</th>
	<th>学期</th>
	<th></th>
	<th>教师名称</th>
	<th></th>
	<th colspan=3></th>
</tr>
</thead>
<tbody>
<c:set var="count" value="1"></c:set>
<c:set var="ifRowspan" value="0"></c:set>
<c:set var="ifCodeRowspan" value="0"></c:set>

<c:forEach items="${schedulingCourseMap}" var="current"	varStatus="i">
<c:set var="rowspan" value="0"></c:set>
<c:set var="rowcodespan" value="0"></c:set> 

<tr>
<!--  <td></td> -->
<c:forEach items="${schedulingCourseMap}" var="current1" varStatus="j">
	<c:if test="${current1.schoolCourse.schoolCourseInfo.courseNumber==current.schoolCourse.schoolCourseInfo.courseNumber }">
		<c:set value="${rowspan + 1}" var="rowspan" />
	</c:if>
	<c:if test="${current1.schoolCourse.courseCode==current.schoolCourse.courseCode }">
		<c:set value="${rowcodespan + 1}" var="rowcodespan" />
	</c:if>
</c:forEach>

<!--课程编号  -->
<c:if test="${rowspan>1&&ifRowspan!=current.schoolCourse.schoolCourseInfo.courseNumber }">
	<td rowspan="${rowspan }">${count}</td>
</c:if>
<c:if test="${rowspan==1 }">
	<td>${count}</td>
</c:if>
<c:if test="${rowspan>1&&ifRowspan!=current.schoolCourse.schoolCourseInfo.courseNumber }">
	<td rowspan="${rowspan }">${current.schoolCourse.schoolCourseInfo.courseNumber}</td>
	<c:set var="count" value="${count+1}"></c:set>
</c:if>
<c:if test="${rowspan==1 }">
	<td>${current.schoolCourse.schoolCourseInfo.courseNumber}</td>
	<c:set var="count" value="${count+1}"></c:set>
</c:if>

<!--课程名称  -->
<c:if test="${rowspan>1&&ifRowspan!=current.schoolCourse.schoolCourseInfo.courseNumber }">
	<td rowspan="${rowspan }">${current.schoolCourse.courseName}</td>
	<c:set var="ifRowspan" value="${current.schoolCourse.schoolCourseInfo.courseNumber }"></c:set>
</c:if>
<c:if test="${rowspan==1 }">
	<td>${current.schoolCourse.courseName}</td>
</c:if>

<!-- 选课组编号 -->
<c:if test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td rowspan="${rowcodespan }">${current.schoolCourse.courseCode}</td>
</c:if>
<c:if test="${rowcodespan==1 }">
	<td>${current.schoolCourse.courseCode}</td>
</c:if>

<!--学期  -->
<c:if test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td rowspan="${rowcodespan }">${current.schoolCourse.schoolTerm.termName}</td>
</c:if>
<c:if test="${rowcodespan==1 }">
	<td>${current.schoolCourse.schoolTerm.termName}</td>
</c:if>

<!-- 课程安排  -->
<td></td>

<!-- 教师名称  -->
<td >${current.user.cname}${current.user.username}&nbsp;</td>
<!-- 学生名单  -->
<c:if
	test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td rowspan="${rowcodespan }"></td>
</c:if>
<c:if test="${rowcodespan==1 }">
	<td></td>
</c:if>

<!-- 操作 直接排课 -->
<c:if test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td rowspan="${rowcodespan }">
	   
		</td>
</c:if>
<c:if test="${rowcodespan==1 }">
	<td>
	  
	</td>
</c:if>

<!-- 操作 调整排课 -->
<c:if test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td>
	</td> 
</c:if>

<c:if test="${rowcodespan==1 }">
	<td>
	
	</td>
</c:if>

<!-- 操作 查看排课 -->
<c:if test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td rowspan="${rowcodespan }">
		
	</td>
	<c:set var="ifCodeRowspan"	value="${current.schoolCourse.courseCode }"></c:set>
</c:if>
<c:if test="${rowcodespan==1 }">
	<td>
	    
	</td>
	
</c:if>
</tr>
</c:forEach>
</tbody>
<!-- 分页导航 -->

</table>
<a href="${pageContext.request.contextPath}/sharedData/findAllCourseSchedule?currpage=${pageModel.firstPage}&id=<%=request.getParameter("id") %>" target="_self"><fmt:message key="firstpage.title"/></a>				    
<a href="${pageContext.request.contextPath}/sharedData/findAllCourseSchedule?currpage=${pageModel.previousPage}&id=<%=request.getParameter("id") %>" target="_self"><fmt:message key="previouspage.title"/></a>
 第
<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
    <option value="${pageContext.request.contextPath}/sharedData/findAllCourseSchedule?currpage=${page}">${page}</option>
    <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
        <c:if test="${j.index!=page}">
        <option value="${pageContext.request.contextPath}/sharedData/findAllCourseSchedule?currpage=${j.index}&id=<%=request.getParameter("id") %>">${j.index}</option>
        </c:if>
    </c:forEach>
</select>页
<a href="${pageContext.request.contextPath}/sharedData/findAllCourseSchedule?currpage=${pageModel.nextPage}&id=<%=request.getParameter("id") %>" target="_self">下一页</a>
<a href="${pageContext.request.contextPath}/sharedData/findAllCourseSchedule?currpage=${pageModel.lastPage}&id=<%=request.getParameter("id") %>" target="_self"><fmt:message key="lastpage.title"/></a>
</div>

<div class="pagination_desc" style="float: left">
   共<strong>${totalRecords}</strong>条记录 
   <strong>总页数:${pageModel.totalPage}&nbsp;</strong>

</div>
<br>
</div>
</div>
</div>
</div>
</div>
</div>
<!-- 直接排课 -->
<div id="doStart" class="easyui-window" title="直接排课" closed="true"	iconCls="icon-add" style="width:850px;height:450px">
<div class="navigation">
<div id="navigation">
	<ul>
		<li><a href="javascript:void(0)">排课管理</a>
		</li>
		<li class="end">
		<a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?id=-1">教务直接排课</a>
		</li>
	</ul>
</div>
</div>
<br>
<form:form id="form_lab" action="" method="post" modelAttribute="timetableAppointment">
当前学期：${schoolTerm.termName} 当前周次：${week }
<!-- schoolCourseDetail的no -->
<hr>
<br>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
<table border="0" align="center">
<tr>
	<td></td>
	<td></td>
</tr>
<tr>
	<td align=left>请选择实验室：</td>
	<td>
	<select class="chzn-select" data-placeholder='请选择实验室：' multiple="multiple"	name="labRoom_id" id="labRoom_id" items="${labRoomMap}"	invalidMessage="不能超过30个字符！" style="width:450px"	required="true">
		<c:forEach items="${labRoomMap}" var="entry">
			<option value="${entry.key}">${entry.value}</option>
		</c:forEach>
	</select> 
	<br>
</tr>
<tr>
	<td align=left>&nbsp;</td>
	<td>
</tr>
<tr>
	<td align=left>请选择上课老师：</td>
	<td>
	<select class="chzn-select"	data-placeholder='请选择上课老师：' multiple="multiple" name="teacherRelated" id="teacherRelated" style="width:450px"	required="true">
		<c:forEach items="${timetableTearcherMap}" var="entry">
			<option value="${entry.key}">${entry.value}</option>
		</c:forEach>
	</select>
</tr>
<tr>
	<td align=left>&nbsp;</td>
	<td>
</tr>
<tr>
	<td align=left>请选择指导老师：</td>
	<td>
<select class="chzn-select"	data-placeholder='请选择指导老师：' multiple="multiple"	name="tutorRelated" id="tutorRelated" style="width:450px"	required="true" invalidMessage="不能超过50个字符！">
		<c:forEach items="${timetableTearcherMap}" var="entry">
			<option value="${entry.key}">${entry.value}</option>
		</c:forEach>
	</select>
</tr>
<tr>
	<td align=left>&nbsp;</td>
	<td>
</tr>

</table>
</div>
</div>
</div>
</div>
</div>

<br>
<br>
<br>
<input type="submit" value="确定">
	</form:form>
</div>

<!-- 调整排课 -->
<div id="doAdjust" class="easyui-window" title="调整排课" closed="true"	iconCls="icon-add" style="width:1000px;height:600px"></div>

<!-- 调整排课 -->
<div id="doSearch" class="easyui-window" title="查看排课" closed="true"	iconCls="icon-add" style="width:1000px;height:500px"></div>

<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="查看学生名单"	closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

<!-- 下拉框的js -->
<script	src="${pageContext.request.contextPath}/chosen/chosen.jquery.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/chosen/docsupport/prism.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var config = {
	'.chzn-select' : {
		search_contains : true
	},
	'.chzn-select-deselect' : {
		allow_single_deselect : true
	},
	'.chzn-select-no-single' : {
		disable_search_threshold : 10
	},
	'.chzn-select-no-results' : {
		no_results_text : 'Oops, nothing found!'
	},
	'.chzn-select-width' : {
		width : "95%"
	}
}
for ( var selector in config) {
	$(selector).chosen(config[selector]);
}
</script>
<!-- 下拉框的js -->

</body>
</html>

