<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="now" class="java.util.Date" />   
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>我要排课</title>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	

</head>
<body>

<div class="iStyle_RightInner">
<br>
<hr>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<table  > 
<thead>
<tr>
  <!--  <th>选择</th> -->
  <th >选课组编号</th>
     <td >
         ${courseCode}
     </td>
     <th>选课人数     </th>
     <td>
       ${courseCodes.get(0).schoolCourseStudents.size()}
     </td>
     <th >课程名称 </th>
     <td >
         ${courseCodes.get(0).schoolCourse.courseName}
     </td>
</tr>
<tr>
    <%--  <th >项目编号 </th>
     <td >
         ${item.itemNumber}
     </td>
     <th >项目名称 </th>
     <td >
         ${item.itemName}
     </td> --%>
     <th >授课教师</th>
     <td >
         ${courseCodes.get(0).user.cname}
     </td>
     <th>所属学院     </th>
     <td>
       ${courseCodes.get(0).schoolAcademy.academyName}
     </td>
     <th>所属学期</th>
   <td>${courseCodes.get(0).schoolTerm.termName} </td>
</tr>
</thead>
</table>
</div>
</div>
</div>
</div>
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/saveTimetableGroup" target=_self>

<%-- <div class="right-content">
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/saveTimetableGroup" target=_self>
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<table  > 
<thead>
<tr>
  <!--  <th>选择</th> -->
  <th colspan="2">选择相关的实验项目(<font color="red">*</font>为必填)</th>
</tr>
</thead>
<tbody>
<tr>
   <td>实验项目选择(<font color="red">*</font>)</td>
   <td>
      <select class="chzn-select" data-placeholder="选择实验项目" multiple="multiple" name="item" id="item" style="width:550px"  required="true">
	   <option value ="-1" selected> ${courseCodes.get(0).schoolCourse.courseName}</option>
	   
	   <c:forEach items="${courseCodeItemList}" var="current"  varStatus="i">	
	       <option value ="${current.id}"> ${current.itemNumber},${current.itemName}</option>
	   </c:forEach>
	  </select>
   </td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
</div> --%>
<input type="hidden" name="item" value="-1">

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box" style="margin-bottom:30px;">
<!--分组的计数  -->
<input type="hidden" name="countGroup" value="${countGroup }">

<input type="hidden" name="term" value="${courseCodes.get(0).schoolTerm.id}">
<input type="hidden" name="labroom" value="${labroom }">
<input type="hidden" name="item" value="${item.id }">
<!--选课组编号  -->
<input type="hidden" name="courseCode" value="${courseCode}">
<!--分组计数  -->
<input type="hidden" name="countGroup" value="${countGroup}">
<!--批次名称  -->
<input type="hidden" name="batchName" value="${batchName}">
<!--选课形式  -->
<input type="hidden" name="ifselect" value="${ifselect}">
<!--开始选课时间 -->
<input type="hidden" name="startDate" value="${startDate}">
<!--结束选课时间  -->
<input type="hidden" name="endDate" value="${endDate}">
<table > 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th colspan=3>对选定的选课组进行分组处理</th>
    <th></th>  
</tr>
</thead>
<tbody>

</tbody>
</table>

<table> 
<thead>

<tr>
  <!--  <th>选择</th> -->
   <th>序号</th>
   <th>组名</th>
   <th>人数</th>
   <th>选课开始日期</th>
   <th>选课结束日期</th>
   <th>选课形式</th>
</tr>
</thead>
<tbody>

<c:forEach var="current" begin="1" end="${countGroup }" step="1">
<tr>

<td>${current}</td>
<td>
<input type="text" name="groupName" value="第${current }组"></td>
<td>
<c:if test="${current==countGroup }">
<input type="text" name="numbers" value='<fmt:parseNumber value="${courseCodes.get(0).schoolCourseStudents.size()-courseCodes.get(0).schoolCourseStudents.size()/countGroup*(countGroup-1)}" integerOnly="true"/>'>
</c:if>
<c:if test="${current!=countGroup }">
<input type="text" name="numbers" value='<fmt:parseNumber value="${courseCodes.get(0).schoolCourseStudents.size()/countGroup}" integerOnly="true"/>'>
</c:if>
</td>

<td>${startDate}</td>
<td>${endDate}</td>

<c:if test="${current==1 }">
   <td rowspan="${countGroup}" >
      <c:if test="${ifselect==0 }">
                自动选课 
      </c:if>
      <c:if test="${ifselect==1 }">
               学生选课      
      </c:if>
   </td>
</c:if>
</tr>
</c:forEach>

</tbody>
</table>
<br>
<hr>
<div style="margin:8px 10px;">
<a href="javascript:void(0)"><input type="submit" value="确定" onclick="return isVailid()"></a>&nbsp;
<a href="${pageContext.request.contextPath}/timetable/doIframeGroupReTimetable?term=${courseCodes.get(0).schoolTerm.id}&courseCode=${courseCode}"><input type="button" value="返回"></a>
</div>
</div>
</div>
</div>
</div>
</div>
</form>
</div>
</div>
</div>
<hr>

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
    
    function isVailid() { 
	    if(parseInt($("#endDate").val().replace(/\-/g,""))<parseInt($("#startDate").val().replace(/\-/g,""))){
	        alert($("#endDate").val().replace(/\-/g,"")+"输入的起始日期大于结束日期！"+$("#startDate").val().replace(/\-/g,""));
	        return false;
	    }

    }
    
</script>
<!-- 下拉框的js -->
</body>
</html>