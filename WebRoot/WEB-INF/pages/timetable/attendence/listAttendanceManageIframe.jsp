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
	<li><a href="javascript:void(0)">考勤管理</a></li>
	<li class="end"><a href="javascript:void(0)">课程考勤与成绩管理</a></li>
	
</ul>
</div>
</div>


<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">

<ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=1&id=-1&status=-1">课程考勤与成绩管理</a>
		</li>
		<%--<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/timetable/labRoomResource?page=1">实验室考勤</a>
		</li>--%>
</ul>


<div class="TabbedPanelsContent">
<div class="tool-box">

<form:form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=1&id=-1&status=-1" modelAttribute="timetableAppointment">
<ul style="position:relative;left:-11%;">
   <li>
   <select class="chzn-select" id="term" name="term" style="width:180px">
       <c:forEach items="${schoolTerms}" var="current">
    	    <c:if test="${current.id == term}">
    	       <option value ="${current.id}" selected>${current.termName} </option>
    	    </c:if>
    	    <c:if test="${current.id != term}">
    	       <option value ="${current.id}" >${current.termName} </option>
    	    </c:if>		
        </c:forEach>
    </select>
   </li>
   
   
   <li>
   <form:select class="chzn-select"  path="schoolCourseInfo.courseNumber" id="schoolCourseInfo_courseNumber" cssStyle="width:650px" >
	  <form:option value="-1"	label="所有课程列表  " />
	  <c:forEach items="${timetableAppointmentAll}" var="current"  varStatus="i">	
	      <!--添加相关排课的授课老师查询项username，cname  -->
	      <c:set var="searchChar" value="" />
	      <c:forEach items="${current.timetableTeacherRelateds}" var="current1" >	
	         <c:set var="searchChar" value="${searchChar}[${current1.user.cname}${current1.user.username}]" />  
	      </c:forEach>
	      <form:option value ="${current.schoolCourseInfo.courseNumber}" label="选课组编号：${current.courseCode} 课程名称：${current.schoolCourse.courseName} 课程安排：星期${current.weekday} ${current.startClass}-${current.endClass}[${current.startWeek}-${current.endWeek}授课教师：${searchChar } " />  
	  </c:forEach>
   </form:select>&nbsp;
   </li> 
   <li>
    <li>
   <form:select class="chzn-select"  path="detail" id="teacher" cssStyle="width:650px" >
	  <form:option value="-1"	label="上课教师  " />
	  <c:forEach items="${teachers}" var="current"  varStatus="i">	
	     <form:option value ="${current.username}" label="${current.cname }[${current.username }] " />  
	  </c:forEach>
   </form:select>&nbsp;
   </li> 
   <li>
     <a href="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=1&status=-1&id=-1"><input type="button" value="取消查询"></a>
     <input type="submit" value="查询" id="search" />  
   </li> 
   </td>
</tr>
</table>
</form:form>
</div>
<div class="content-box">
<div class="title">
    <span style="float: left;position: absolute;left: 15px;">课程考勤管理列表</span>
</div>
<table> 
<thead>
<tr>
   <th>序号</th>
   <th>选课组编号</th>
   <th>课程名称</th>
   <th>实验室</th>
   <th>上课教师</th>
   <th>学期</th>
   <th>周次</th>
   <th>星期</th>
   <th>节次</th>
   <th>周次考勤/成绩</th>
   <th>学期考勤</th>
   <th>学期成绩</th>
   
</tr>

</thead>
<tbody>
<c:set var="ifRowspan" value="0"></c:set>
<c:set var="count" value="1"></c:set>
<c:forEach items="${timetableAppointments}" var="current"  varStatus="i">	
<c:set var="rowspan" value="0"></c:set>

<tr>
    <c:forEach items="${timetableAppointments}" var="current1"  varStatus="j">
         <c:if test="${current1.courseCode==current.courseCode }">
            <c:set value="${rowspan + 1}" var="rowspan" />
         </c:if>
     </c:forEach>
     
     <c:if test="${rowspan>1&&ifRowspan!=current.courseCode }">
          <td rowspan="${rowspan }">${count}</td>
          <c:set var="count" value="${count+1}"></c:set>
     </c:if>
     <c:if test="${rowspan==1 }">
         <td >${count}</td>
         <c:set var="count" value="${count+1}"></c:set>
     </c:if>
     <c:if test="${rowspan>1&&ifRowspan!=current.courseCode }">
         <td rowspan="${rowspan }">${current.courseCode}</td>
     </c:if>
    
     <c:if test="${rowspan==1 }">
         <td >${current.courseCode} </td>
     </c:if>
      
      
     <td >
        <c:forEach var="entry" items="${current.timetableItemRelateds}">  
           <c:if test="${not empty entry.operationItem&&entry.operationItem.id!=0}">
              <c:out value="${entry.operationItem.lpName}" /><br>
           </c:if>
           <c:if test="${entry.operationItem==null||entry.operationItem.id==0}">
            <c:if test="${current.schoolCourse.courseNo!=null||current.schoolCourse.courseNo!=''}">
              ${current.schoolCourse.courseName}&nbsp;
              </c:if>
             <c:if test="${current.timetableSelfCourse.id!=null}">
              ${current.timetableSelfCourse.schoolCourseInfo.courseNumber}&nbsp;
              </c:if>  
           </c:if>
           
        </c:forEach>
        <c:if test="${current.timetableItemRelateds.size()==0}">
         ${current.schoolCourse.courseName}
        </c:if>
        
     </td>
    

     
     <td>
     <!--相关实验分室  -->
     <c:forEach var="entry" items="${current.timetableLabRelateds}"> 
    <label title="<c:out value="${entry.labRoom.labRoomName}" />"><c:out value="${entry.labRoom.labRoomNumber}" /></label>  
     </c:forEach>
     </td>
     
     <td>
     <!--上课教师  -->
     <c:forEach var="entry" items="${current.timetableTeacherRelateds}">  
     <c:out value="${entry.user.cname}" />  
     </c:forEach> 
     </td>
     <!--学期-->
     <td>
     <c:if test="${current.timetableStyle ne 5 && current.timetableStyle ne 6 }">
     	${current.schoolCourseDetail.schoolCourse.schoolTerm.termName}
     </c:if>	
     <c:if test="${current.timetableStyle eq 5 || current.timetableStyle eq 6 }">
     	${current.timetableSelfCourse.schoolTerm.termName}
     </c:if>	
     </td>
    <td colspan="3" >
    <table>
     <!--课程周次 连续时读取timetable_appointment-->
     <c:if test="${current.timetableAppointmentSameNumbers.size()==0}">  
     <tr>
     <td>[${current.startWeek}-${current.endWeek}]</td>
     
     <td>
     <c:if test="${current.weekday==1 }">
           星期一
     </c:if>
     <c:if test="${current.weekday==2 }">
           星期二
     </c:if>
     <c:if test="${current.weekday==3 }">
           星期三
     </c:if>
     <c:if test="${current.weekday==4 }">
           星期四
     </c:if>
     <c:if test="${current.weekday==5 }">
           星期五
     </c:if>
     <c:if test="${current.weekday==6 }">
           星期六
     </c:if>
     <c:if test="${current.weekday==7 }">
           星期日
     </c:if>
     </td>   
     <td>[${current.startClass}-${current.endClass}]</td>
     </tr>
     </c:if>
     
       <!--课程周次 不连续时读取timetable_appointment_same_number-->
     <c:if test="${current.timetableAppointmentSameNumbers.size()>0}">
     <c:forEach items="${current.timetableAppointmentSameNumbers }" var="s">
     <tr>
     <td style="width:70px">
     <c:if test="${s.startWeek!=s.endWeek}">
     [${s.startWeek}-${s.endWeek}]
     </c:if>
     <c:if test="${s.startWeek==s.endWeek}">
     ${s.startWeek}
     </c:if>
     </td>
     
     <td>
     <c:if test="${current.weekday==1 }">
           星期一
     </c:if>
     <c:if test="${current.weekday==2 }">
           星期二
     </c:if>
     <c:if test="${current.weekday==3 }">
           星期三
     </c:if>
     <c:if test="${current.weekday==4 }">
           星期四
     </c:if>
     <c:if test="${current.weekday==5 }">
           星期五
     </c:if>
     <c:if test="${current.weekday==6 }">
           星期六
     </c:if>
     <c:if test="${current.weekday==7 }">
           星期日
     </c:if>
     </td>  
     <!-- 节次 -->
     <td> 
     <c:if test="${s.startClass!=s.endClass }">
     [${s.startClass}-${s.endClass}]
      </c:if>
      <c:if test="${s.startClass==s.endClass }">
      ${s.startClass}
      </c:if>
     </td>
     </tr>
     </c:forEach>
     </c:if>
     </table>
    </td> 
    <!-- 周次考勤 -->
     <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_TEACHER">
     <td>
     <a class="btn btn-common" href='${pageContext.request.contextPath}/timetable/detailattendanceManage?idKey=${current.id}'>考勤:
     	<!-- 直接排课、调整排课、二次不分组排课 -->
    	<c:if test="${current.timetableStyle!=4&&current.timetableStyle!=6&&current.timetableStyle!=5}">
			${current.schoolCourseDetail.schoolCourseStudents.size()}
    	</c:if>
    	<!-- 二次分组排课 -->
    	<c:if test="${current.timetableStyle==4}">
         <c:forEach items="${current.timetableGroups}" var="varGroup">
              ${varGroup.timetableGroupStudentses.size()}
          </c:forEach>  
     	</c:if>
     	<!-- 自主排课 -->
 		<c:if test="${current.timetableStyle==5}">
         	${current.timetableSelfCourse.timetableCourseStudents.size()}
     	</c:if>
     	<!-- 自主分组排课 -->
     	<c:if test="${current.timetableStyle==6}">
         <c:forEach items="${current.timetableGroups}" var="varGroup">
               ${varGroup.timetableGroupStudentses.size()}
         </c:forEach>         
     	</c:if>
	</a>&nbsp;&nbsp;
	
	</td>
     </sec:authorize>
     
		  <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_TEACHER">
		   <c:if test="${rowspan>1&&ifRowspan!=current.courseCode }">
        	 	<td rowspan="${rowspan }">
		  		<a class="btn btn-common" href='${pageContext.request.contextPath}/timetable/checkStudentAttendance?id=${current.id}'>查看</a>&nbsp;&nbsp;</td>
		   </c:if>
		   <c:if test="${rowspan==1}">
         		<td >
         		<a class="btn btn-common" href='${pageContext.request.contextPath}/timetable/checkStudentAttendance?id=${current.id}'>查看</a>&nbsp;&nbsp;</td>
		   </c:if>
          </sec:authorize>

     	    <c:if test="${rowspan>1&&ifRowspan!=current.schoolCourseDetail.schoolCourse.courseCode }">
           	<c:set var="ifRowspan" value="${current.schoolCourseDetail.schoolCourse.courseCode }"></c:set>
           	<c:set var="ifRowspan" value="${current.courseCode }"></c:set>
     		</c:if>
     		<td><a class="btn btn-common" href='${pageContext.request.contextPath}/timetable/checkTearmAttendance?id=${current.id}'>查看</a></td>
     
</tr>

</c:forEach> 
</tbody>
<!-- 分页导航 -->
<tr> 
    <td colspan="8" align="center" >
                 总页数:${pageModel.totalPage}页 <input type="hidden" value="${pageModel.lastPage}" id="totalpage" />&nbsp;总条数：${totalRecords}&nbsp;当前页：${page}&nbsp;
		   <a href="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=${pageModel.firstPage}&term=${term }&status=-1&id=<%=request.getParameter("id") %>" target="_self"> 首页</a> 
		   <a href="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=${pageModel.previousPage}&term=${term }&status=-1&id=<%=request.getParameter("id") %>"  target="_self">上一页 </a> 
		   <a href="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=${pageModel.nextPage}&term=${term }&status=-1&id=<%=request.getParameter("id") %>"  target="_self">下一页 </a> 
		   <a href="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=${pageModel.lastPage}&term=${term }&status=-1&id=<%=request.getParameter("id") %>" target="_self">末页 </a>
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

