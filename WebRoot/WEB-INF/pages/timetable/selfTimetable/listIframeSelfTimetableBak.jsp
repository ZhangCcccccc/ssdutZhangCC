<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>我要排课</title>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/timetable/main.css" type="text/css" media="screen">
<style type="text/css">
 #fullview,#fullview1{
    margin-top:200px;
    width:1%;
    height:50px;
    border:1px solid #ccc;
    border-radius:5px;
    float:left;
    cursor:pointer;
    padding-top:40px;
    padding-left:1px;
    background:#f0f0f0;
    margin-left:-5px;
    }
</style>
</head>
<body>

<div style="left: 0px; top: 0px; width: 100%;" class="panel layout-panel layout-panel-center">
<div title="" class="layout-body panel-body panel-body-noheader" region="center" border="true" style="padding: 10px; background: none repeat scroll 0% 0% rgb(255, 255, 255); border: 1px solid rgb(204, 204, 204); width: 100%; height: 100%;">
<!-- 选课信息 -->
<div class="formall">  
<div class="selectedc" id="selectMessage">
<font color="red"><b>您已经选择了[${labroom.labRoomName}] [${schoolTerm.termName}]
<c:if test="${weekday==1 }">
[星期一] 
</c:if>
<c:if test="${weekday==2 }">
[星期二] 
</c:if>
<c:if test="${weekday==3 }">
[星期三] 
</c:if>
<c:if test="${weekday==4 }">
[星期四] 
</c:if>
<c:if test="${weekday==5 }">
[星期五] 
</c:if>
<c:if test="${weekday==6 }">
[星期六] 
</c:if>
<c:if test="${weekday==7 }">
[星期天] 
</c:if>
<c:if test="${classids==1 }">
[第一节~第二节] 
</c:if>
<c:if test="${classids==2 }">
[第三节~第四节]
</c:if>
<c:if test="${classids==3 }">
[第五节~第六节] 
</c:if>
<c:if test="${classids==4 }">
[第七节~第八节]
</c:if>
<c:if test="${classids==5 }">
[第九节] 
</c:if>
<c:if test="${classids==6 }">
[第十节] 
</c:if>
<c:if test="${classids==7 }">
[第十一节] 
</c:if>
<c:if test="${classids==8 }">
[第十二节] 
</c:if>
<c:if test="${classids==9 }">
[第十三节] 
</c:if>
</b>
</font>
</div> 
<hr>
<div class="addbingf">

<div class="col_left" id="timetableReg">
<div class="div_container">
<div class="div_title2" style="font-size: 14px"><font color="red">基本信息</font></div>
<!--实验基本信息开始-->
<table class="tb" id="my_show"> 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>选课组编号</th>
   <th>课程编号</th>
   <th>课程名称</th>
   <th>学期</th>
   <th>课程安排</th>
   <th>教师名称</th>

</tr>
</thead>
<tbody>

</tbody>
</table>

<!--实验基本信息开始-->
</div>
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/selfTimetable/saveAdjustTimetable">
 
<hr>
选择选课组：<select class="chzn-select"  name="courseDetailNo" id="courseDetailNo" style="width:850px">
	 <c:forEach items="${schedulingCourseMap}" var="current"  varStatus="i">	
         <option value ="${current.courseDetailNo}" >选课组编号：${current.schoolCourse.courseCode} 课程编号：${current.schoolCourse.schoolCourseInfo.courseNumber} 课程名称：${current.schoolCourse.courseName} 星期${current.weekday} ${current.startClass}-${current.endClass}[${current.startWeek}-${current.endWeek}] 授课教师：${current.user.cname}</option>
     </c:forEach>
</select> 
<br>
<hr>

<table class="tb" id="my_show"> 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>选择实验室</th>
   <th>选择星期</th>
   <th>选择节次</th>
   <th width=30%>选择周次</th>
  
</tr>
</thead>
<tbody>
<tr>
  <!--  <th>选择</th> -->
   <th>
        <input type="text" readonly value="${labroom.id }" name="labRooms" id="labRooms">
   </th>
   <th>
        <input type="text" readonly value="${weekday }" name="weekday" id="weekday">
   </th>
   <th>
   <select class="chzn-select" multiple="multiple" name="classes" id="classes" style="width:150px">
    <c:if test="${classids<=4 }">
          <option value ="${classids*2-1}" selected> ${classids*2-1}</option>
          <option value ="${classids*2}" selected> ${classids*2}</option>
          
    </c:if>
    <c:if test="${classids>4 }">
       <option value ="${classids+4}" selected> ${classids+4}</option>
    </c:if> 

   </select>
   </th>
   <th>
   <select class="chzn-select"  multiple="multiple"  name="weeks" id="weeks" style="width:250px">
	 <c:forEach var="week" varStatus="status" begin="1" end="20">
         <option value ="${status.index}" selected> ${status.index}</option>
     </c:forEach>
   </select>
   </th>
  
</tr>

</tbody>
</table>
<hr>
<input type="submit" value="提交">
</form>

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