<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<!--直接排课  -->
<script>
function startTimetable(detailno){
var sessionId=$("#sessionId").val();
//获取当前屏幕的绝对位置
 var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#searchFile').window({left:"100px", top:topPos+"px"});
$('#searchFile').window('open');
$('#form_lab').attr("action","${pageContext.request.contextPath}/timetable/doDirectTimetable?detailno=" +detailno);

}

/*
*调整排课弹出窗口
*/
function doAdminTimetable(id){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/doAdminTimetable?id=' + id + '" style="width:100%;height:100%;"></iframe>'
$('#doAdmin').html(con);  
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#doAdmin').window({left:"0px", top:topPos+"px"});
$('#doAdmin').window('open');
}

/*
*查看学生名单
*/
function listTimetableStudents(courseDetailNo){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/doSearchStudent?courseDetailNo=' + courseDetailNo + '" style="width:100%;height:100%;"></iframe>'
$('#doSearchStudents').html(con);
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#doSearchStudents').window({left:"0px", top:topPos+"px"});  
$('#doSearchStudents').window('open');
}
</script>
<!--学期查询条件同步 -->
<script  type="text/javascript">
$(document).ready(function(){
$("#term").chosen().change(function(){

$.ajax({
         url:"${pageContext.request.contextPath}/timetable/getAdminCourseCodeList",
         dataType:"json",
         contentType: "application/x-www-form-urlencoded; charset=utf-8", 
         type:'GET',
         data:{term:$("#term").val()},
         complete:function(result)
         {
            var obj = eval(result.responseText); 
            var result2;
            $("#schoolCourseInfo_courseNumber").empty();
            
            result2 = result2 + "<option value='-1'>所有课程列表 </option>";
            for (var i = 0; i < obj.length; i++) { 
                 result2 = result2 + "<option value='" +obj[i].courseNumber + "'>" + obj[i].value + "</option>";
            }
            $("#schoolCourseInfo_courseNumber").append(result2);
            $("#schoolCourseInfo_courseNumber").trigger("liszt:updated");
            result2="";
          }
});
$("#schoolCourseInfo_courseNumber").trigger("liszt:updated");
});
}); 
</script>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
</head>

<div class="iStyle_RightInner">
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li class="end"><a href="javascript:void(0)">考勤与成绩管理</a></li>
	
</ul>
</div>
</div>


<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">

<ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=1&id=-1&status=-1">课程考勤</a>
		</li>
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="${pageContext.request.contextPath}/timetable/attendanceManageForLabIframe?currpage=1&id=-1&status=-1">实验室考勤</a>
		</li>
</ul>


<div class="TabbedPanelsContent">
<div class="tool-box">

<form:form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/attendanceManageForLabIframe?currpage=1&id=-1&status=-1" modelAttribute="labAttendance">
<ul>
   
   <li>实验室名称： </li>
    <li><form:input id="labName" path="labRoomName"/></li>
    <li>学号： </li>
    <li><form:input id="username" path="username"/></li>
    <li>姓名： </li>
    <li><form:input id="cname" path="cname"/></li>
    
    
    
    <li>
   		<input type="submit" value="查询" id="search" />
	</li> 
   <li>
		<a href="${pageContext.request.contextPath}/timetable/attendanceManageForLabIframe?currpage=1&status=-1&id=-1"><input type="button" value="取消查询"></a>
   </li>
</ul>
</form:form>
</div>
<div class="content-box">
<div class="title">考勤管理列表</div>
<table> 
<thead>
<tr>
   <th>序号</th>
   <th>实验室名称</th>
   <th>考勤时间</th>
   <th>学号</th>
   <th>姓名</th>
   
</tr>

</thead>
<tbody>
<c:forEach items="${labRoomAttendList}" var="current"  varStatus="i">	
<tr>
	<td>${i.count }</td>
	<td>${current.labRoomName }</td>
	<td>${current.attendanceTime }</td>
	<td>${current.username }</td>
	<td>${current.cname }</td>

</tr>


</c:forEach>
</tbody>
<!-- 分页导航 -->
<tr> 
    <td colspan="8" align="center" >
                 总页数:${pageModel.totalPage}页 <input type="hidden" value="${pageModel.lastPage}" id="totalpage" />&nbsp;总条数：${totalRecords}&nbsp;当前页：${page}&nbsp;
		   <a href="${pageContext.request.contextPath}/timetable/attendanceManageForLabIframe?currpage=${pageModel.firstPage}&term=${term }&status=-1" target="_self"> 首页</a> 
		   <a href="${pageContext.request.contextPath}/timetable/attendanceManageForLabIframe?currpage=${pageModel.previousPage}&term=${term }&status=-1"  target="_self">上一页 </a> 
		   <a href="${pageContext.request.contextPath}/timetable/attendanceManageForLabIframe?currpage=${pageModel.nextPage}&term=${term }&status=-1"  target="_self">下一页 </a> 
		   <a href="${pageContext.request.contextPath}/timetable/attendanceManageForLabIframe?currpage=${pageModel.lastPage}&term=${term }&status=-1" target="_self">末页 </a>
    </td>
</tr>		
</table>
</div>
</div>
</div>
</div>
</div>



<div id="searchFile" class="easyui-window" title="直接排课" closed="true" iconCls="icon-add" style="width:850px;height:450px">
</div>


<!-- 调整排课 -->
<div id="doAdmin" class="easyui-window" title="调整排课" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="查看学生名单" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>


<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
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

