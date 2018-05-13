<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />   

<html>
<head>
<meta name="decorator" content="iframe" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
 <script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#my_show").jqprint();
	});
});	
</script> 
<script type="text/javascript">
/*
*查看排课情况
*/
function listTimetableStudents(courseDetailNo){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/reTimetable/listGroupTimetableStudentSelected?courseDetailNo=' + courseDetailNo + '" style="width:100%;height:100%;"></iframe>'
$('#doSearchStudents').html(con);  
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#doSearchStudents').window({left:"0px", top:topPos+"px"});
$('#doSearchStudents').window('open');
}

/*
*查看排课情况
*/
function doSelectCourse(id){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/reTimetable/doGroupTimetableStudentSelect?id=' + id + '" style="width:100%;height:100%;"></iframe>'
$('#doSelectCourse').html(con);  
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#doSelectCourse').window({left:"0px", top:topPos+"px"});
$('#doSelectCourse').window('open');
}
</script>

<script type="text/javascript">
$(function(){
    $("#doSelectCourse").window({
       onBeforeClose:function (){ //当面板关闭之前触发的事件
              if (confirm('窗口正在关闭，请确认您当前的操作已保存。\n 是否继续关闭窗口？')) {
                    window.location.reload();
                    $('#doSelectCourse').window('close', true); 
                    //这里调用close 方法，true 表示面板被关闭的时候忽略onBeforeClose 回调函数。
              }else{
                    return false;
              } 
              
         }     
    })
})
</script>
</head>

<body>

<!-- 导航栏 -->
<div class="navigation">
<div id="navigation">
<ul>
<li><a href="">排课管理</a></li>
<li class="end"><a href="${pageContext.request.contextPath}/system/listUser?currpage=1">学生选课</a></li>
</ul>
</div>
</div>

<!-- 导航栏 -->
<div class="iStyle_RightInner">
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<!-- 查询、导出、打印 -->
<div class="tool-box">
    <form:form name="form" method="Post" action="${pageContext.request.contextPath}/system/listUser?currpage=1" modelAttribute="timetableGroup">
	<ul>
	    
	    <li> 学期课程或选课组：
	    <form:select class="chzn-select"  path="timetableBatch.courseCode" id="timetableBatch.courseCode" cssStyle="width:400px" >
	    <c:forEach items="${timetableGroups}" var="curr"  varStatus="i">	
	       <form:option value="${curr.timetableBatch.courseCode}" label="${curr.timetableAppointment.schoolCourseDetail.schoolTerm.termName};${curr.timetableBatch.courseCode};${curr.timetableBatch.batchName}" />  
	    </c:forEach>
        </form:select></li>
		<li><input type="button" value="查询"></li>
		<li><a href="javascript:void(0)"><input type="button" value="取消查询"></a></li>
		<li><input type="button" value="导出" ></li>
		<li><input type="button" value="打印" id="print"></li>
	</ul>
</form:form>
</div>       

<div class="content-box">
<div class="title">
<%-- 
<a href='javascript:void(0)' onclick='listTimetableStudents("${current.schoolCourseDetail.courseDetailNo }")'><input type="button" value="查看我的选课情况"></a>
 --%>
 查看我的选课情况
</div>
<table class="tb" cellspacing="0" id="my_show"> 
<thead>
<tr>
    <th>序号</th>
    <th>选课状态</th>
    <th>课程编号</th>
    <th width="13%">课程名称</th>
    <th>选课组编号</th>
    
    <th>
     <table>
     <tr><td width="50px;">周次</td>
         <td width="50px;">节次</td>
         <td width="50px;">星期</td>
         <td width="100px;">实验室</td>
         <td width="100px;">实验项目</td>
         <td width="50px;">授课教师</td>
      </tr>
     </table>
    </th>
    <th width="5%">批次</th>
    <th>选课时间</th>
    <th>操作</th>
</tr>
</thead>
<tbody>
<c:forEach items="${timetableGroups}" var="current"  varStatus="i">	
<tr>
   <td>${i.count}</td>
   <td>
   <c:if test="${current.timetableBatch.ifselect==1}">
                          学生选课                   
   </c:if>
   <c:if test="${current.timetableBatch.ifselect==0}">
        自动选课
   </c:if>
   &nbsp;</td>
   <td>
   ${current.timetableAppointments.iterator().next().schoolCourseInfo.courseNumber}
   </td>
   <td  width="60px;">
   ${current.timetableAppointments.iterator().next().schoolCourseInfo.courseName}
   </td>
   <td>${current.timetableBatch.courseCode}</td>
   <!--实验项目  -->
  <%--  <td>
    <c:forEach items="${current.timetableAppointment.timetableItemRelateds}" var="var"  varStatus="j">
    ${var.operationItem.itemName}<br>
    <c:if test="${var.operationItem.id==0||var.operationItem.id==null}">
              ${current.timetableAppointment.schoolCourse.courseName}(课程名称)&nbsp;
    </c:if>
    </c:forEach>
    
   </td> --%>
   
   <td>
   <c:forEach items="${timetableGroupStudents}" var="var" >
   <c:if test="${current.timetableBatch.id==var.timetableGroup.timetableBatch.id }">
   <c:forEach items="${var.timetableGroup.timetableAppointments}" var="var1">
    
<table>
     <c:if test="${var1.timetableAppointmentSameNumbers.size()==0}">
     <tr>    
             <td width="50px;">
             <c:if test="${var1.startWeek==var1.endWeek}">
             ${var1.startWeek }
             </c:if>
             <c:if test="${var1.startWeek!=var1.endWeek}">
             ${var1.startWeek }-${var1.endWeek }
             </c:if>
             </td>
              <td width="50px;">
             <c:if test="${var1.startClass==var1.endClass}">
             ${var1.startClass }
             </c:if>
             <c:if test="${var1.startClass!=var1.endClass}">
             ${var1.startClass }-${var1.endClass }
             </c:if>
             </td>
             <td width="50px;"> 星期${var1.weekday}
             </td>
             <td width="100px;">
             <!--显示实验室 -->
             <c:forEach items="${var1.timetableLabRelateds}" var="var2"  varStatus="j">
             ${var2.labRoom.labRoomName}<br>
             </c:forEach>
             </td>
             <td width="100px;">
             <!--显示实验项目 -->
             <c:forEach items="${var1.timetableItemRelateds}" var="var2"  varStatus="j">
             <c:if test="${not empty var2.operationItem||var2.operationItem.id!=0}">
                <c:out value="${var2.operationItem.lpName}" /><br>
             </c:if>
             <c:if test="${empty var2.operationItem&&var2.operationItem.id==0}">
              ${var1.schoolCourseInfo.courseName}(课程名称)<br>
             </c:if>
             </c:forEach>&nbsp;
             </td>
             <!--授课老师  -->
		     <td  width="50px;">
		       <c:forEach items="${var1.timetableTeacherRelateds}" var="var2"  varStatus="j">
		       ${var2.user.cname}<br>
		       </c:forEach>
		     </td>
      </tr>
      </c:if>
     <c:if test="${var1.timetableAppointmentSameNumbers.size()>0}">
         <c:set var="sameStart" value="-"></c:set>
         <c:forEach var="entry" items="${var1.timetableAppointmentSameNumbers}"   varStatus="p"> 
         <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
         <c:if test="${fn:indexOf(sameStart,v_param)<0}">
         <tr>
         
         <td width="50px;">
         <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
         <c:forEach var="entry1" items="${var1.timetableAppointmentSameNumbers}"  varStatus="q">  
             <c:if test="${entry.startClass==entry1.startClass}">
             <%-- <td>
             [${entry1.startClass }-${entry1.endClass }]
             </td> --%>
             <c:if test="${entry1.startWeek==entry1.endWeek}">
              ${entry1.startWeek }
             </c:if>
             <c:if test="${entry1.startWeek!=entry1.endWeek}">
              ${entry1.startWeek }-${entry1.endWeek }
             </c:if>
             
             </c:if>
         </c:forEach>
         </td>
         <td width="50px;">
             <c:if test="${entry.startClass==entry.endClass}">
             ${entry.startClass }
             </c:if>
             <c:if test="${entry.startClass!=entry.endClass}">
             ${entry.startClass }-${entry.endClass }
             </c:if>
         </td>
         <td width="50px;"> 星期${var1.weekday}
         </td>
          <td width="100px;">
             <c:forEach items="${var1.timetableLabRelateds}" var="var2"  varStatus="j">
             ${var2.labRoom.labRoomName}
             </c:forEach>
         </td>
         <td width="100px;">
             <!--显示实验项目 -->
             <c:forEach items="${var1.timetableItemRelateds}" var="var2"  varStatus="j">
             <c:if test="${not empty var2.operationItem||var2.operationItem.id!=0}">
                <c:out value="${var2.operationItem.lpName}" /><br>
             </c:if>
             <c:if test="${empty var2.operationItem&&var2.operationItem.id==0}">
              ${var1.schoolCourseInfo.courseName}(课程名称)<br>
             </c:if>
             </c:forEach>&nbsp;
             </td>
         <!--授课老师  -->
		     <td width="50px;">
		       <c:forEach items="${var1.timetableTeacherRelateds}" var="var2"  varStatus="j">
		       ${var2.user.cname}<br>
		       </c:forEach>
		     </td>
         </tr>
         </c:if>
         </c:forEach>
     </c:if>
     </table>

   </c:forEach>
   </c:if>
   </c:forEach>
   </td>
   <td>${current.timetableBatch.batchName}</td>
   <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.timetableBatch.startDate.time}" type="both"/>至<br>
       <fmt:formatDate pattern="yyyy-MM-dd" value="${current.timetableBatch.endDate.time}" type="both"/> 
   </td>
   <td>
       <!--日期变量转换为天的精度，进行后期比较大小  -->
       <c:set var="now1">
           <fmt:formatDate pattern="yyyyMMdd" value="${now}" type="both"/>
       </c:set>
       <c:set var="startDate1">
           <fmt:formatDate pattern="yyyyMMdd" value="${current.timetableBatch.startDate.time}" type="both"/>
       </c:set>
       <c:set var="endDate1">
           <fmt:formatDate pattern="yyyyMMdd" value="${current.timetableBatch.endDate.time}" type="both"/>
       </c:set>
       
       <!--判断是否当前批次下，学生已选课  -->
       <c:set var="batchSelect" value="0"></c:set>
       <c:if test="${timetableGroupStudents.size() >0}">
		   <c:forEach items="${timetableGroupStudents}" var="var2"  varStatus="j">
		         <c:if test="${var2.timetableGroup.timetableBatch.id==current.timetableBatch.id}">
			             <font color=red>选课已完成</font>
			             <c:set var="batchSelect" value="1"></c:set><br>
			             <c:if test="${startDate1<=now1&&endDate1>=now1}">
			                 <a class="btn btn-common" onclick="return confirm('确定退选分组？')" href="${pageContext.request.contextPath}/timetable/reTimetable/ejectStudentSelectGroup?group=${current.timetableBatch.id}" >退&nbsp;选</a>
                         </c:if>
			     </c:if>
		   </c:forEach>
	   </c:if>
   

	   <c:if test="${timetableGroupStudents.size() ==0||batchSelect==0}">
	        <c:if test="${startDate1<=now1&&endDate1>=now1}">
	   		    <a class="btn btn-common" href='${pageContext.request.contextPath}/timetable/reTimetable/doGroupTimetableStudentSelect?id=${current.timetableBatch.id}' >选课</a>
	        </c:if>
	        <c:if test="${startDate1>now1}">
	   		    <font color=red>选课时间尚未开始</font>
	        </c:if>
	        <c:if test="${endDate1<now1}">
	   		    <font color=red>选课时间已过期</font>
	        </c:if>
	   </c:if>
   </td>
</tr>
</c:forEach>
<tr>
<td colspan=12>
<%--<c:choose><c:when test='${newFlag}'>
    <div class="pagination">
    <a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.firstPage}" target="_self"><fmt:message key="firstpage.title"/></a>				    
	<a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.previousPage}" target="_self"><fmt:message key="previouspage.title"/></a>
	 第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/userList?currpage=${page}">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/userList?currpage=${j.index}">${j.index}</option></c:if></c:forEach></select>页
	<a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.nextPage}" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.lastPage}" target="_self"><fmt:message key="lastpage.title"/></a>
    </div>
    <div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
    <strong><fmt:message key="totalpage.title"/>:${pageModel.totalPage}&nbsp;</strong>
</div>
</c:when><c:otherwise>
<div class="pagination_desc" style="float: left">
    <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
</div>
</c:otherwise></c:choose>
--%></td>
</tr>
</tbody>
</table>    
</div>
</div>
</div>
</div>
</div>
</div>
<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="查看学生名单" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>
<!-- 进行学生选课 -->
<div id="doSelectCourse" class="easyui-window" title="进行学生选课" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

</body>
</html>

