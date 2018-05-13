<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/newtimetable/jquery.coolautosuggest.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.coolautosuggest.css" />
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
</head>
<body>
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?id=-1">查看学生选课情况</a></li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">学生选课列表</div>
<form:form name="form" action="${pageContext.request.contextPath}/timetable/openSearchStudent?courseDetailNo=${courseDetailNo}&merge=${merge}" method="post" >
<table style="width:400px;">
<span>班级:&nbsp;</span>
<tr>
<td>
<input id="schoolCourseClass" name="schoolCourseClass"  style="width:200px;">
</td>
</tr>
</table>

<table style="width:400px;">
<tr>
<span>学号:&nbsp;</span>
<td>
<input id="schoolCourseStudent" name="schoolCourseStudent"  style="width:200px;">
</td>
</tr>
</table>

<table>
<td>
<button>查询</button>
<button type="button" onclick="cancel()">取消</button>
</td>
</tr>
</table>

<table style="width:400px;">
<tr>
<span>学号:&nbsp;</span>
<td>
<input id="addStudent" name="addStudent"  style="width:200px;">
<input type="hidden" id="queryStudent" name="queryStudent"  style="width:200px;">
</td>
</tr>
</table>

<table>
<td>
<button type="button" onclick="addSchoolStudent()">添加</button>
</td>
</tr>
</table>
</form:form>
<table> 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>序号</th>
   <th>选课组编号</th>
   <th>课程名称</th>
   <th>学生编号</th>
   <th>学生姓名</th>
   <th>授课教师</th>
   <th>学院名称</th>
   <th>操作</th>
   
</tr>
</thead>
<tbody>
<c:forEach items="${studentMap}" var="current"  varStatus="i">	
<tr>
     <th>${i.count}</th>
     <td>${current.schoolCourseDetail.schoolCourse.courseCode}</td>
     <td>${current.schoolCourseDetail.schoolCourse.courseName}</td>
     <td>${current.userByStudentNumber.username}</td>
     <td>${current.userByStudentNumber.cname}</td>
     <td>${current.userByTeacherNumber.cname}</td>
     <td>${current.schoolAcademy.academyName }</td>
     <td><input type="button" onclick="deleteStudent(${current.userByStudentNumber.username})" value="删除"></td>
     
</tr>
</c:forEach> 
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
</div>

<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
//输入框绑定的方法
	$("#addStudent").coolautosuggest({
		url:"${pageContext.request.contextPath}/timetable/findStudent?userName=",
		onSelected:function(result){
			$("#addStudent").val(result.data);
			$("#queryStudent").val(result.id);
		}
	});
function addSchoolStudent(){
var userName = $("#queryStudent").val();
$.ajax({
		url:"${pageContext.request.contextPath}/timetable/addStudent",
		data:{"userName":userName,"courseDetailNo":'${courseDetailNo}',"merge":${merge}},
			type:"POST",
			dataType:"text",
			success:function(data){
			if(data == "success"){
			   alert("成功添加");
			   location.href="${pageContext.request.contextPath}/timetable/openSearchStudent?courseDetailNo=${courseDetailNo}&merge=${merge}";
			}else{
				alert("添加失败");
				}
			}
			});
}
function cancel(){
  	window.location.href="${pageContext.request.contextPath}/timetable/openSearchStudent?courseDetailNo=${courseDetailNo}&merge=${merge}";
			}
// 删除学生
function deleteStudent(userName){
   $.ajax({
		url:"${pageContext.request.contextPath}/timetable/deleteStudent",
		data:{"userName":userName,"courseDetailNo":'${courseDetailNo}',"merge":${merge}},
			type:"POST",
			dataType:"text",
			success:function(data){
			if(data == "success"){
			   alert("成功删除");
			   location.href="${pageContext.request.contextPath}/timetable/openSearchStudent?courseDetailNo=${courseDetailNo}&merge=${merge}";
			}else{
				alert("删除失败");
				}
			}
			});
}
</script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
</script>
<!-- 下拉框的js -->
</body>
</html>