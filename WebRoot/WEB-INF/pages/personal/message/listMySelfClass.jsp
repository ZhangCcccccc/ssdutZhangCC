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
$('#searchFile').window({left:"100px", top:"0px"});
$('#searchFile').window('open');
$('#searchFile').window('open');
$('#form_lab').attr("action","${pageContext.request.contextPath}/timetable/openDirectTimetable?detailno=" +detailno);
}

/*
*调整排课弹出窗口
*/
function doAdminTimetable(id){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/doAdminTimetable?id=' + id + '" style="width:100%;height:100%;"></iframe>'
$('#doAdmin').html(con);  
$('#doAdmin').window({left:"0px", top:"0px"});
$('#doAdmin').window('open');
}

/*
*查看学生名单
*/
function listTimetableStudents(courseDetailNo){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/openSearchStudent?courseDetailNo=' + courseDetailNo + '" style="width:100%;height:100%;"></iframe>'
$('#doSearchStudents').html(con);  
$('#doSearchStudents').window({left:"0px", top:"0px"});
$('#doSearchStudents').window('open');
}
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
	<li><a href="javascript:void(0)">个人中心</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/timetable/mySelfClass?currpage=1">我的课表</a></li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
<form:form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/mySelfClass" modelAttribute="schoolCourseDetail">
<table class="list_form">
<tr>
   <td>   
   <form:select class="chzn-select"  path="id" id="id" cssStyle="width:900px" >
	  <c:forEach items="${timetableAppointment}" var="current"  varStatus="i">	
	      <form:option value ="${current.id}" label="选课组编号：${current.schoolCourseDetail.schoolCourse.courseCode} 课程名称：${current.schoolCourseDetail.schoolCourse.courseName}; 课程安排：星期${current.weekday} ${current.startClass}-${current.endClass}[${current.startWeek}-${current.endWeek}] " />  
	  </c:forEach>
   </form:select>&nbsp;
   </td> 
   <td align="left">
   <input type="submit" value="查询" id="search" />&nbsp;&nbsp;&nbsp;
   </td>   
</tr>
</table>
</form:form>
</div>
<div class="content-box">
<div class="title">教务排课管理列表</div>
<table> 
<thead>
<tr>
<!--    <th>选择</th>  -->
   <th>序号</th>
   <th>选课组编号</th>
   <th>实验项目名称</th>
   <th>周次</th>
   <th>星期</th>
   <th>节次</th>
   <th>上课教师</th>
   <th>指导教师</th>
   <th>实验室</th>
   <th>学生名单</th>
<!--    <th>排课方式</th>
   <th colspan=2>操作</th>
 
   <th></th> -->
   
</tr>
</thead>
<tbody>
<c:set var="ifRowspan" value="0"></c:set>
<c:set var="count" value="1"></c:set>
<c:forEach items="${timetableAppointment}" var="current"  varStatus="i">	
<c:set var="rowspan" value="0"></c:set>
<tr>
    <c:forEach items="${timetableAppointment}" var="current1"  varStatus="j">	
         <c:if test="${current1.schoolCourseDetail.schoolCourse.courseCode==current.schoolCourseDetail.schoolCourse.courseCode }">
            <c:set value="${rowspan + 1}" var="rowspan" />
         </c:if>
     </c:forEach>
    <%--  <c:if test="${rowspan>1&&ifRowspan!=current.schoolCourseDetail.schoolCourse.courseCode }">
          <td  rowspan="${rowspan }"><input type='checkbox' name='VoteOption1' value=1></td>  
     </c:if>
     <c:if test="${rowspan==1 }">
          <td><input type='checkbox' name='VoteOption1' value=1></td>
     </c:if> --%>
     <c:if test="${rowspan>1&&ifRowspan!=current.schoolCourseDetail.schoolCourse.courseCode }">
          <td rowspan="${rowspan }">${count}</td>
          <c:set var="count" value="${count+1}"></c:set>
     </c:if>
     <c:if test="${rowspan==1 }">
         <td >${count}</td>
         <c:set var="count" value="${count+1}"></c:set>
     </c:if>
     <c:if test="${rowspan>1&&ifRowspan!=current.schoolCourseDetail.schoolCourse.courseCode }">
         <td rowspan="${rowspan }">${current.schoolCourseDetail.schoolCourse.courseCode}</td>
     </c:if>
    
     <c:if test="${rowspan==1 }">
         <td >${current.schoolCourseDetail.schoolCourse.courseCode} </td>
     </c:if>
     <td >
        <c:forEach var="entry" items="${current.timetableItemRelateds}">  
        <c:out value="${entry.operationItem.lpName}" />&nbsp;&nbsp;&nbsp;
        </c:forEach>
        <c:if test="${current.timetableItemRelateds.size()==0}">
         ${current.schoolCourseDetail.schoolCourse.courseName}(课程名称)
        </c:if>
     </td>
     
     <td>
     <c:if test="${current.weekday==1 }">
           星期一${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==2 }">
           星期二${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==3 }">
           星期三${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==4 }">
           星期四${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==5 }">
           星期五${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==6 }">
           星期六${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
     </c:if>
     <c:if test="${current.weekday==7 }">
           星期七${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
     </c:if>
     </td>
     <td>${current.weekday}</td>
     <td>[${current.startClass}-${current.endClass}]</td>
     <td>
     <!--上课教师  -->
     <c:forEach var="entry" items="${current.timetableTeacherRelateds}">  
     <c:out value="${entry.user.cname}" />  
     </c:forEach> 
     </td>
     <td>
     <!--指导教师  -->
     <c:forEach var="entry" items="${current.timetableTutorRelateds}">  
     <c:out value="${entry.user.cname}" />  
     </c:forEach>
     </td>
     <td>
     <!--相关实验分室  -->
     <c:forEach var="entry" items="${current.timetableLabRelateds}">  
     <c:out value="${entry.labRoom.labRoomName}" /><br>  
     </c:forEach>
     </td>
     <td>
         <a href='javascript:void(0)' onclick='listTimetableStudents("${current.schoolCourseDetail.courseDetailNo }")'>名单：${current.schoolCourseDetail.schoolCourseStudents.size()}</a>
                   <c:set var="ifRowspan" value="${current.schoolCourseDetail.schoolCourse.courseCode }"></c:set>
         </td>
   <%--   <!-- 排课方式-->
     <td >
        <c:if test="${current.timetableStyle==1}">
              <font color=green>教务直接排课</font>
        </c:if>
        <c:if test="${current.timetableStyle==2}">
                           教务调整排课
        </c:if>
        <c:if test="${current.timetableStyle==3}">
                          二次不分组排课
        </c:if>
        <c:if test="${current.timetableStyle==4}">
                          二次分组排课              
        </c:if>
        <c:if test="${current.timetableStyle==5}">
                           自主排课
        </c:if>
     </td>
     <td>
     <a href='${pageContext.request.contextPath}/timetable/deleteAppointment?id=${current.id }'>删除</a>&nbsp;&nbsp;
		   <a href='javascript:void(0)' onclick='doAdminTimetable("${current.id }")'>修改排课</a>
     </td>
     
     <c:if test="${rowspan>1&&ifRowspan!=current.schoolCourseDetail.schoolCourse.courseCode }">
          <td rowspan="${rowspan }">
          <c:if test="${current.status==10 }">
                           排课进行中
          </c:if>
          <c:if test="${current.status==1 }">
                              已发布
          </c:if>
         <c:if test="${current.status==2 }">
          <a href="${pageContext.request.contextPath}/timetable/doReleaseTimetable?courseCode=${current.schoolCourseDetail.schoolCourse.courseCode }" >发布课程</a>
          </c:if>
          </td>
          <c:set var="ifRowspan" value="${current.schoolCourseDetail.schoolCourse.courseCode }"></c:set>
           
     </c:if>
     <c:if test="${rowspan==1 }">
         <td>
          <c:if test="${current.status==10 }">
                      排课进行中
          </c:if>
          <c:if test="${current.status==1 }">
                              已发布
          </c:if>
          <c:if test="${current.status==2 }">
          <a href="${pageContext.request.contextPath}/timetable/doReleaseTimetable?courseCode=${current.schoolCourseDetail.schoolCourse.courseCode }" >发布课程</a>
          </c:if>
          </td>
     </c:if> --%>
</tr>
</c:forEach> 
</tbody>
<!-- 分页导航 -->
<tr> 
 <%--    <td colspan="13" align="center" >
                                 总页数:${pageModel.totalPage}页 <input type="hidden" value="${pageModel.lastPage}" id="totalpage" />&nbsp;
                                 当前页:第${pageModel.currpage}页 <input type="hidden" value="${pageModel.currpage}" id="currpage" />&nbsp;
		   <a href="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=${pageModel.firstPage}&id=<%=request.getParameter("id") %>&status=<%=request.getParameter("status") %>" target="_self"> 首页</a> 
		   <a href="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=${pageModel.previousPage}&id=<%=request.getParameter("id") %>&status=<%=request.getParameter("status") %>"  target="_self">上一页 </a> 
		   <a href="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=${pageModel.nextPage}&id=<%=request.getParameter("id") %>&status=<%=request.getParameter("status") %>"  target="_self">下一页 </a> 
		   <a href="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=${pageModel.lastPage}&id=<%=request.getParameter("id") %>&status=<%=request.getParameter("status") %>" target="_self">末页 </a>
    </td> --%>
</tr>		
</table>
</div>
</div>
</div>
</div>
</div>
</div>

<div id="searchFile" class="easyui-window" title="直接排课" closed="true" iconCls="icon-add" style="width:850px;height:450px">
<br>
<form:form id="form_lab" action="" method="post"  modelAttribute="timetableAppointment">
当前学期：${schoolTerm.termName} 当前周次：${week }
<!-- schoolCourseDetail的no -->

<hr>
<br>
<table>
<tr>
   <td></td>
   <td></td>
</tr>

</table>
<br>
<br>
<br>
<hr>
<input type="submit" value="确定">
</form:form>
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
