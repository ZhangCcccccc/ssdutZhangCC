<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<jsp:useBean id="now" class="java.util.Date" />   
<html>
<head>

<meta name="decorator" content="iframe"/>
<title>我要排课</title>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/iconFont.css"	rel="stylesheet">

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	

</head>
<body>

<div class="iStyle_RightInner">


<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">基本信息</div>
<table  > 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>当前用户</th>
   <td>${user.cname }</td>
   <th>所属学院</th>
   <td>${user.schoolAcademy.academyName }</td>
   <th>所属学期</th>
   <td>${schoolTerm.termName} </td>
</tr>

<tr>
     <th >课程名称 </th>
     <td >
         ${courseCodes.get(0).schoolCourse.courseName}
     </td>
     <th >选课组编号</th>
     <td >
         ${courseCodes.get(0).schoolCourse.courseCode}
     </td>
     <th>选课人数     </th>
     <td>
       ${courseCodes.get(0).schoolCourseStudents.size()}
     </td>
</tr>
</thead>

</table>

</div>
</div>
</div>
</div>
</div>
<br>
<hr>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">


<form name="form2" method="Post" action="${pageContext.request.contextPath}/timetable/doGroupReTimetable">
<table> 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>批次</th>
   <th>人数</th>
   <th width="7%">选课形式</th>
   <!--    <th>选课开始时间</th>
   <th>选课结束时间</th> -->
   <th colspan=6>
   <table>
   <tr>

       <td width="130px;" colspan=2>
       <table>
       <tr>
          <td width="50px;">周次</td>
          <td width="40px;">星期</td>
          <td width="20px;">节次</td>
       </tr>
       </table>
      </td>
       <th width="15%">实验项目</th>
       <td width="15%">实验室</td>
       <td width="10%">教师</td>
       <td width="25%" colspan=2>排课</td>
       <td width="10%">组名</td>
   </tr>
   </table>
   <th colspan=4 width="18%">操作
   </th>
</tr>
</thead>
<tbody>

<c:set var="ifRowspan" value="0"></c:set>
<c:forEach items="${timetableGroups}" var="current"  varStatus="i">
<c:if test="${current.id eq groupId }">
<c:set var="rowspan" value="0"></c:set>	
<tr>
    <c:forEach items="${timetableGroups}" var="current1"  varStatus="j">	
         <c:if test="${current1.timetableBatch.batchName==current.timetableBatch.batchName }">
            <c:set value="${rowspan + 1}" var="rowspan" />
         </c:if>
     </c:forEach>
<c:if test="${i.count==1 }">
<%-- <td rowspan="${timetableGroups.size()}">${current.timetableBatch.courseCode  }</td> --%>
</c:if>

<!--显示批次 -->
<c:if test="${rowspan==1}">
   <td >${current.timetableBatch.batchName}</td>
</c:if>

<c:if test="${rowspan>1&&ifRowspan!=current.timetableBatch.batchName }">
   <td rowspan="${rowspan}">${current.timetableBatch.batchName}</td>
</c:if>

<!-- 当前分组的分配人数-->
<td>${current.numbers}</td>

<c:if test="${rowspan>1&&ifRowspan!=current.timetableBatch.batchName }">
<td rowspan="${rowspan}">
<c:if test="${current.timetableBatch.ifselect==0 }">
自动选课
</c:if>
<c:if test="${current.timetableBatch.ifselect==1 }">
学生选课
</c:if>
</td>
</c:if>

<c:if test="${rowspan==1}">
<td >
<c:if test="${current.timetableBatch.ifselect==0 }">
自动选课
</c:if>
<c:if test="${current.timetableBatch.ifselect==1 }">
学生选课
</c:if>
</td>
</c:if>

<td colspan=8>
<table>
<c:if test="${current.timetableAppointments.size()>0}">
<!-- timetableAppointment与timetableGroup多对多映射 -->
<c:forEach items="${current.timetableAppointments}" var="current2" varStatus="xh">	
<tr>
    
    <td colspan=2>
     <table>
     <c:if test="${current2.timetableAppointmentSameNumbers.size()==0}">
     <tr>
             <td width="50px;">
             <c:if test="${current2.startWeek==current2.endWeek}">
             ${current2.startWeek }
             </c:if>
             <c:if test="${current2.startWeek!=current2.endWeek}">
             ${current2.startWeek }-${current2.endWeek }
             </c:if>
             </td>
             <td width="40px;">   星期${current2.weekday}</td>
             <td width="20px;">
             <c:if test="${current2.startClass==current2.endClass}">
             ${current2.startClass }
             </c:if>
             <c:if test="${current2.startClass!=current2.endClass}">
             ${current2.startClass }-${current2.endClass }
             </c:if>
             </td>
             
      </tr>
      </c:if>
     <c:if test="${current2.timetableAppointmentSameNumbers.size()>0}">
         
         <c:set var="sameStart" value="-"></c:set>
         <c:forEach var="entry" items="${current2.timetableAppointmentSameNumbers}"   varStatus="p"> 
         <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
         <c:if test="${fn:indexOf(sameStart,v_param)<0}">
         <tr>
         <td width="50px;">
         <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
         <c:forEach var="entry1" items="${current2.timetableAppointmentSameNumbers}"  varStatus="q">  
             <c:if test="${entry.startClass==entry1.startClass}">
             <c:if test="${entry1.startWeek==entry1.endWeek}">
              ${entry1.startWeek }
             </c:if>
             <c:if test="${entry1.startWeek!=entry1.endWeek}">
              ${entry1.startWeek }-${entry1.endWeek }
             </c:if>
             
             </c:if>
         </c:forEach>
         </td>
         <td width="40px;">   星期${current2.weekday}</td>
         <td width="20px;">
             <c:if test="${entry.startClass==entry.endClass}">
             ${entry.startClass }
             </c:if>
             <c:if test="${entry.startClass!=entry.endClass}">
             ${entry.startClass }-${entry.endClass }
             </c:if>
         </td>
         
         </tr>
         </c:if>
         </c:forEach>
          
     </c:if>
     </table>
     </td>
   <!-- 获取分批相关的实验项目-->
   <td width="15%">
   <c:if test="${current.timetableAppointments.size()>0}">
       <c:forEach items="${current2.timetableItemRelateds}" var="current3"  varStatus="j">	
       <c:if test="${current3.operationItem==null||current3.operationItem.id==0}">
          ${current.timetableAppointments.iterator().next().schoolCourseInfo.courseName}(课程名称)&nbsp;
       </c:if>
       <c:if test="${current3.operationItem!=null&&current3.operationItem.id!=0}">
          ${current3.operationItem.lpName}<br>
       </c:if>
       </c:forEach> 
    </c:if>
    </td>
    <td width="15%">
         <!-- 获取排课相关的实验室，set变量 current3-->
         <c:forEach items="${current2.timetableLabRelateds}" var="current3" >	
             ${current3.labRoom.labRoomName}&nbsp;
         </c:forEach>
     </td>
    <td width="10%">
         <c:forEach items="${current2.timetableTeacherRelateds}" var="current4" >	
             ${current4.user.cname}&nbsp;
         </c:forEach>
    </td>
    
    <td width="10%">
      <c:if test="${current2.id ==null}">
      <c:if test="${current.timetableBatch.flag!=1}">
           <a class="btn btn-common" href="${pageContext.request.contextPath}/timetable/doGroupReTimetable?group=${current.id}&term=${schoolTerm.id}&courseCode=${courseCode}&appointment=${current2.id}">新排课</a>
      </c:if>
      </c:if>
      <c:if test="${current2.id !=null}">
      <c:if test="${current2.status!=2&&current.timetableAppointment.status!=1&&current.timetableBatch.flag!=1 }">
           <a onclick="return confirm('删除后，排课状态还原，发布、分组信息，学生选课信息还原！确定删除此条记录？')" href='${pageContext.request.contextPath}/timetable/reTimetable/deleteReGroupAppointment?id=${current2.id }'>删除</a>&nbsp;&nbsp;
      </c:if>
      <c:if test="${current.timetableAppointment.status==2||current.timetableAppointment.status==1 }">
           <font color=green>排课结束</font>
      </c:if>
      </c:if>
    </td>
    <c:if test="${xh.count ==1&&current2.status!=2&&current2.status!=1&&current.timetableBatch.flag!=1 }">
    <td width="15%" rowspan="${current.timetableAppointments.size()}">
        <a class="btn btn-common" href="${pageContext.request.contextPath}/timetable/doGroupReTimetable?group=${current.id}&term=${schoolTerm.id}&courseCode=${courseCode}&appointment=-1">新排课</a>
    </td>
    </c:if>
    
    <td width="10%"  align="center" style="border-top-width: 0px; border-right: #ff0000 0px solid; border-left: #ff0000 0px solid; border-bottom: #ff0000 0px solid;">${current.groupName}</td>
    
</tr>
</c:forEach>
</c:if>
<c:if test="${current.timetableAppointments.size()==0}">
<tr>
     <td width="55%" colspan=5> <font color="red">未有排课记录</font></td>
     <td width="25%" colspan=2>
             <a class="btn btn-common" href="${pageContext.request.contextPath}/timetable/doGroupReTimetable?group=${current.id}&term=${schoolTerm.id}&courseCode=${courseCode}&appointment=-1">新排课</a>
      </td>
      <td width="10%">${current.groupName}</td>
</tr>
 </c:if>
 
</table>
<c:if test="${rowspan==1}">
<td rowspan="${rowspan}">
<c:if test="${current.timetableAppointment.status!=2&&current.timetableAppointment.status!=1&&current.timetableBatch.flag!=1 }">
<c:set var="ifArrange" value="1"></c:set>
<c:forEach items="${timetableGroups}" var="current1"  varStatus="j">
<c:if test="${current1.timetableAppointment.id ==null}">
<c:set var="ifArrange" value="0"></c:set>
</c:if>
</c:forEach>
<c:if test="${ifArrange==1}">
<a class="btn btn-common" href="${pageContext.request.contextPath}/timetable/doReGroupTimetableOk?term=${schoolTerm.id}&courseCode=${courseCodes.get(0).schoolCourse.courseCode}&batchId=${current.timetableBatch.id}" target=_parent>调课完成</a>
</c:if>
<c:if test="${ifArrange==0}">
正在排课
</c:if>
</c:if>
<c:if test="${current.timetableBatch.flag==1}">
<font color=red>调课已完成</font>
</c:if>
</td>
</c:if>
<c:if test="${rowspan>1&&ifRowspan!=current.timetableBatch.batchName }">
<td rowspan="${rowspan}">
<c:if test="${current.timetableAppointment.status!=2&&current.timetableAppointment.status!=1&&current.timetableBatch.flag!=1 }">
<c:set var="ifArrange2" value="1"></c:set>
<c:forEach items="${timetableGroups}" var="current2" >
<c:if test="${current2.timetableAppointments.size()==0}">
<c:set var="ifArrange2" value="0"></c:set>
</c:if>
</c:forEach>
<c:if test="${ifArrange2==1}">
<a class="btn btn-common" href="${pageContext.request.contextPath}/timetable/doReGroupTimetableOk?term=${schoolTerm.id}&courseCode=${courseCodes.get(0).schoolCourse.courseCode}&batchId=${current.timetableBatch.id}" target=_parent>调课完成</a>
</c:if>
<c:if test="${ifArrange2==0}">
正在排课
</c:if></c:if>
<c:if test="${current.timetableBatch.flag==1}">
<font color=red>调课已完成</font>
</c:if>
</td>

<c:set var="ifRowspan" value="${current.timetableBatch.batchName }"></c:set>
</c:if>

</tr>
</c:if>
</c:forEach> 
</tbody>
</table>
</form>

</div>
</div>
</div>
</div>
<hr>

</div>
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
</body>
</html>