<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	

<script type="text/javascript">
var tableAppId=${tableAppId};//定义本条排课记录id的全局变量
$(document).ready(function(){
/*如果选择实验室，形成关联的选课数据的联动  */
$("#labRooms").chosen().change(function(){
$("#weeks").html("");
var values=[];
$($("#classes option:selected")).each(function(){
	values.push(this.value);
});
var labRooms=[];
$($("#labRooms option:selected")).each(function(){
	labRooms.push(this.value);
});

$.ajax({
    url:"${pageContext.request.contextPath}/timetable/reTimetable/getValidWeeksMapEdit",
    dataType:"json",
    type:'GET',
    data:{term:${term},weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(","),tableAppId:tableAppId},
    complete:function(result)
    {
       var obj = eval(result.responseText); 
       var result2;
       for (var i = 0; i < obj.length; i++) { 
            //var val = obj.responseText[i]; 
            //result2 = result2 + "<option value='" +obj[i].id + "' selected>" + obj[i].value + "</option>";
           
            result2 = result2 + "<option value='" +obj[i].id + "'>" + obj[i].value + "</option>";
          }; 
       $("#weeks").append(result2);
       $("#weeks").trigger("liszt:updated");
       result2="";
    }
});
$("#weeks").trigger("liszt:updated");
});

/*如果选择節次，形成关联的选课数据的联动  */
$("#classes").chosen().change(function(){
$("#weeks").html("");
var values=[];
$($("#classes option:selected")).each(function(){
	values.push(this.value);
});
var labRooms=[];
$($("#labRooms option:selected")).each(function(){
	labRooms.push(this.value);
});

$.ajax({
    url:"${pageContext.request.contextPath}/timetable/reTimetable/getValidWeeksMapEdit",
    dataType:"json",
    type:'GET',
    data:{term:${term},weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(","),tableAppId:tableAppId},
    complete:function(result)
    {
       var obj = eval(result.responseText); 
       var result2;
       for (var i = 0; i < obj.length; i++) { 
            //var val = obj.responseText[i]; 
            //result2 = result2 + "<option value='" +obj[i].id + "' selected>" + obj[i].value + "</option>";
            result2 = result2 + "<option value='" +obj[i].id + "'>" + obj[i].value + "</option>";
          }; 
       $("#weeks").append(result2);
       $("#weeks").trigger("liszt:updated");
       result2="";
    }
});
$("#weeks").trigger("liszt:updated");
});

});

$(document).ready(function(){
$("#weekday").chosen().change(function(){
$("#weeks").html("");
var values=[];
$($("#classes option:selected")).each(function(){
	values.push(this.value);
});
var labRooms=[];
$($("#labRooms option:selected")).each(function(){
	labRooms.push(this.value);
});
$.ajax({
    url:"${pageContext.request.contextPath}/timetable/reTimetable/getValidWeeksMapEdit",
    dataType:"json",
    type:'GET',
    data:{term:${term},weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(","),tableAppId:tableAppId},
    complete:function(result)
    {
       var obj = eval(result.responseText); 
       var result2;
       for (var i = 0; i < obj.length; i++) { 
                  result2 = result2 + "<option value='" +obj[i].id + "'>" + obj[i].value + "</option>";
       }; 
       $("#weeks").append(result2);
       $("#weeks").trigger("liszt:updated");
       result2="";
    }
});

$("#weeks").trigger("liszt:updated");

});
}); 

</script>

</head>
<body>
<div class="iStyle_RightInner">
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?id=-1">排课管理修改</a></li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">排课管理列表</div>
<table class="tb" id="my_show"> 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>课程名称</th>
   <th>实验项目</th>
   <th>星期</th>
   <th width="90px;" colspan=2>
       <table>
       <tr>
          <td width="20px;">节次</td>
          <td width="50px;">周次</td>
       </tr>
       </table>
   </th>
   <th>授课教师</th>
   <th>指导教师</th>
   <th>实验室</th>
</tr>
</thead>
<tbody>

<%-- <c:forEach items="${timetableAppointmentMap}" var="current"  varStatus="i">	 --%>
<tr>
     <td>${timetableAppointmentMap.schoolCourseInfo.courseName}</td>
      <!--对应实验项目  -->
     <td>
        <c:forEach var="entry" items="${timetableAppointmentMap.timetableItemRelateds}">  
           <c:if test="${entry.operationItem!=null&&entry.operationItem.id!=0}">
              <c:out value="${entry.operationItem.lpName}" /><br>
           </c:if>
           <c:if test="${entry.operationItem==null||entry.operationItem.id==0}">
              ${timetableAppointmentMap.schoolCourseInfo.courseName}(课程名称)&nbsp;
           </c:if>
        </c:forEach>
        <c:if test="${timetableAppointmentMap.timetableItemRelateds.size()==0}">
         ${timetableAppointmentMap.schoolCourseInfo.courseName}(课程名称)
        </c:if>
        
     </td>
     <td>${timetableAppointmentMap.weekday}</td>
     <td colspan=2 width="90px;" >
     <table>
     <c:if test="${timetableAppointmentMap.timetableAppointmentSameNumbers.size()==0}">
     <tr>
             <td width="20px;">
             <c:if test="${timetableAppointmentMap.startClass==timetableAppointmentMap.endClass}">
             ${timetableAppointmentMap.startClass }
             </c:if>
             <c:if test="${timetableAppointmentMap.startClass!=timetableAppointmentMap.endClass}">
             ${timetableAppointmentMap.startClass }-${timetableAppointmentMap.endClass }
             </c:if>
             </td>
             <td width="50px;">
             <c:if test="${timetableAppointmentMap.startWeek==timetableAppointmentMap.endWeek}">
             ${timetableAppointmentMap.startWeek }
             </c:if>
             <c:if test="${timetableAppointmentMap.startWeek!=timetableAppointmentMap.endWeek}">
             ${timetableAppointmentMap.startWeek }-${timetableAppointmentMap.endWeek }
             </c:if>
             </td>
      </tr>
      </c:if>
     <c:if test="${timetableAppointmentMap.timetableAppointmentSameNumbers.size()>0}">
         
         <c:set var="sameStart" value="-"></c:set>
         <c:forEach var="entry" items="${timetableAppointmentMap.timetableAppointmentSameNumbers}"   varStatus="p"> 
         <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
         <c:if test="${fn:indexOf(sameStart,v_param)<0}">
         <tr>
         <td width="20px;">
             <c:if test="${entry.startClass==entry.endClass}">
             ${entry.startClass }
             </c:if>
             <c:if test="${entry.startClass!=entry.endClass}">
             ${entry.startClass }-${entry.endClass }
             </c:if>
         </td>
         <td width="50px;">
         <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
         <c:forEach var="entry1" items="${timetableAppointmentMap.timetableAppointmentSameNumbers}"  varStatus="q">  
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
         </tr>
         </c:if>
         </c:forEach>
          
     </c:if>
     </table>
     </td>
     <td>
     <!--上课教师  -->
     <c:forEach var="entry" items="${timetableAppointmentMap.timetableTeacherRelateds}">  
     <c:out value="${entry.user.cname}" />  
     </c:forEach> 
     </td>
     <td>
     <!--指导教师  -->
     <c:forEach var="entry" items="${timetableAppointmentMap.timetableTutorRelateds}">  
     <c:out value="${entry.user.cname}" />  
     </c:forEach>
     </td>
     <td>
      <!--相关实验分室  -->
     <c:forEach var="entry" items="${timetableAppointmentMap.timetableLabRelateds}">  
     <c:out value="${entry.labRoom.labRoomName}" /><br>  
     </c:forEach></td>

</tr>
<%-- </c:forEach>  --%>
</tbody>
</table>
<br>

<div class="title">排课内容修改</div>
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/saveAdminTimetable" target=_parent>
<input type="hidden" name="term" value="${term}">
<input type="hidden" name="courseDetailNo" value="${timetableAppointmentMap.schoolCourseDetail.courseDetailNo}">
<c:if test="${timetableAppointmentMap.timetableStyle==5||timetableAppointmentMap.timetableStyle==6}">
<input type="hidden" name="courseCode" value="${timetableAppointmentMap.timetableSelfCourse.id}">
</c:if>
<c:if test="${timetableAppointmentMap.timetableStyle==1||timetableAppointmentMap.timetableStyle==2||timetableAppointmentMap.timetableStyle==3}">
<input type="hidden" name="courseCode" value="${timetableAppointmentMap.courseCode}">
</c:if>

<!--传递修改的排课表主键  -->
<input type="hidden" value="${timetableAppointmentMap.id}" name="id"> 
<table> 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>调整实验室</th>
   <th>调整实验项目</th>
   <th>调整星期</th>
   <th>调整节次</th>
   <th>调整周次</th>
  <!--  <th>调整指导教师</th> -->
   <th>调整授课教师</th>
</tr>
</thead>
<tbody>
<tr>
  <!--  <th>选择</th> -->
   <td>
   <select class="chzn-select" multiple="multiple" name="labRooms" id="labRooms" style="width:150px">
	  <c:forEach items="${timetableAppointmentMap.timetableLabRelateds}" var="current"  varStatus="i">	
	      <option value ="${current.labRoom.id}" selected>${current.labRoom.labRoomNumber}&nbsp; ${current.labRoom.labRoomName}</option>
	  </c:forEach>
	  <c:forEach items="${labRoomMap}" var="current"  varStatus="i">
	      <option value ="${current.key}"> ${current.value}</option>
	  </c:forEach>
   </select>
   </td>
   <td>
     <select class="chzn-select" multiple="multiple" name="items" id="items" style="width:150px"   required="true">
       <c:forEach items="${timetableItemMap}" var="current"  varStatus="i">	
	       <option value ="${current.key}"> ${current.key}${current.value}</option>
	   </c:forEach> 
	    <c:if test="${timetableAppointmentMap.timetableItemRelateds.size()>0}">
	    <c:forEach items="${timetableAppointmentMap.timetableItemRelateds}" var="current"  varStatus="i">
	        <c:if test="${current.operationItem==null||current.operationItem.id==0}">
	             <option value ="0" selected> 
                     ${timetableAppointmentMap.schoolCourseInfo.courseName}
	              </option>
             </c:if>
	         <c:if test="${current.operationItem!=null&&current.operationItem.id!=0}">
                <option value ="${current.operationItem.id}" selected> 
                ${current.operationItem.lpName}
                </option>
             </c:if>
        </c:forEach>
	    </c:if>  
	    <c:if test="${timetableAppointmentMap.timetableItemRelateds.size()==0}">
	         <option value ="0" selected> 
             ${timetableAppointmentMap.schoolCourseInfo.courseName}(课程名称)
             </option>
	    </c:if>  
	 </select>
   </td>
   <td>
   <select class="chzn-select" name="weekday" id="weekday" style="width:80px" >
     <!--如果星期重复，则去重  -->
	 <c:forEach var="item" varStatus="status" begin="1" end="7">
	 <c:if test="${status.index == timetableAppointmentMap.weekday }">
        <option value ="${status.index}" selected> 星期${status.index}</option>
	 </c:if>
	 <c:if test="${status.index != timetableAppointmentMap.weekday }">
        <option value ="${status.index}"> 星期${status.index}</option>
	 </c:if>
     </c:forEach>
   </select>
   </td>
   <td>
   <c:set var="arrayValue" value=",1,2,3,4,5,6,7,8,9,10,11,12," />
   <c:set var="array" value="${fn:split(arrayvalue,',')}"/> 
   <select class="chzn-select" multiple="multiple" name="classes" id="classes" style="width:150px" required="true">

     <c:if test="${timetableAppointmentMap.timetableAppointmentSameNumbers.size()==0 }">
     
	 <c:forEach var="item" varStatus="status" begin="${timetableAppointmentMap.startClass}" end="${timetableAppointmentMap.endClass}">
         <option value ="${status.index}" selected> ${status.index}</option>
         <c:set var="temp" value=",${status.index}," />
         <c:set var="arrayValue" value="${fn:replace(arrayValue, temp, ',')}" />
     </c:forEach>
     <c:set var="array" value="${fn:split(arrayValue, ',')}"/> 
        <c:forEach var="entry" items="${array}" varStatus="i"> 
        <option value ="${entry}"> ${entry}</option>
        </c:forEach>
     </c:if>
     
     <c:if test="${timetableAppointmentMap.timetableAppointmentSameNumbers.size()>0 }">
         <c:set var="sameStart" value="-"></c:set>
         <c:forEach var="entry" items="${timetableAppointmentMap.timetableAppointmentSameNumbers}"   varStatus="p"> 
         <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
         <c:if test="${fn:indexOf(sameStart,v_param)<0}">
         
            <c:if test="${entry.startClass==entry.endClass}">
            <option value ="${entry.startClass}" selected> ${entry.startClass}</option>
            <c:set var="temp" value=",${entry.startClass}," />
            <c:set var="arrayValue" value="${fn:replace(arrayValue, temp, ',')}" />
            </c:if>
            <c:if test="${entry.startClass!=entry.endClass}">
            <c:forEach var="item" varStatus="status" begin="${entry.startClass}" end="${entry.endClass}">
             <option value ="${status.index}" selected> ${status.index}</option>
             <c:set var="temp" value=",${status.index}," />
             <c:set var="arrayValue" value="${fn:replace(arrayValue, temp, ',')}" />
            </c:forEach>
            
            </c:if>
            <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
        </c:if>
        </c:forEach>
        <c:set var="array" value="${fn:split(arrayValue, ',')}"/> 
        <c:forEach var="entry" items="${array}" varStatus="i"> 
        <option value ="${entry}"> ${entry}</option>
        </c:forEach>
     </c:if>
     
   </select>
   </td>
   
   <td>
   <select class="chzn-select" multiple="multiple" name="weeks" id="weeks" style="width:150px" required="true">
     <c:if test="${timetableAppointmentMap.timetableAppointmentSameNumbers.size()>0 }">
         <c:set var="sameStart" value="-"></c:set>
         <c:forEach var="entry" items="${timetableAppointmentMap.timetableAppointmentSameNumbers}"   varStatus="p"> 
         <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
         <c:if test="${fn:indexOf(sameStart,v_param)<0&&p.count==1}">
         <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
         <c:forEach var="entry1" items="${timetableAppointmentMap.timetableAppointmentSameNumbers}"  varStatus="q"> 
         <c:if test="${entry.startClass==entry1.startClass}"> 
         <c:if test="${entry1.startWeek!=entry1.endWeek}">
             <c:forEach var="item" varStatus="status" begin="${entry1.startWeek}" end="${entry1.endWeek}">
             <option value ="${status.index}" selected> ${status.index}</option>
             </c:forEach>
         </c:if>
         <c:if test="${entry1.startWeek==entry1.endWeek}">
             <option value ="${entry1.startWeek}" selected> ${entry1.startWeek}</option>
         </c:if>
         </c:if>
         </c:forEach>
         </c:if>
         </c:forEach>
     </c:if>
     
     <c:if test="${timetableAppointmentMap.timetableAppointmentSameNumbers.size()==0 }">
	 <c:forEach var="item" varStatus="status" begin="${timetableAppointmentMap.startWeek}" end="${timetableAppointmentMap.endWeek}">
     <option value ="${status.index}" selected> ${status.index}</option>
     </c:forEach>
     </c:if>
   </select>
   </td>
   <%-- <td>
  <select class="chzn-select" multiple="multiple" data-placeholder='请选择指导教师：' name="tutors" id="tutors" style="width:120px">
	  <c:forEach items="${timetableAppointmentMap.timetableTutorRelateds}" var="current"  varStatus="i">	
	      <option value ="${current.user.username}" selected> ${current.user.cname}</option>
	  </c:forEach>
	   <c:forEach items="${timetableTearcherMap}" var="current"  varStatus="i">
	      <option value ="${current.key}" > ${current.value}</option>
	  </c:forEach>
   </select>
   
   </td> --%>
   <td>
  <select class="chzn-select" multiple="multiple" name="teachers" data-placeholder='请选择授课教师：' id="teachers" style="width:120px">
	  <c:forEach items="${timetableAppointmentMap.timetableTeacherRelateds}" var="current"  varStatus="i">	
	      <option value ="${current.user.username}" selected> ${current.user.cname}</option>
	  </c:forEach>
	   <c:forEach items="${timetableTearcherMap}" var="current"  varStatus="i">
	      <option value ="${current.key}" > ${current.value}</option>
	   </c:forEach>
   </select>
   
   </td>
</tr>
<tr>
<td colspan=7>
&nbsp;
</td>
<tr>
<tr>
<td colspan=7>
<hr>
</td>
<tr>
<td colspan=7>
<input type="submit" value="提交">
</td>
</tr>
</tbody>
</table>
</form>
</div>
</div>
</div>
</div>
</div>
</div>
<br>
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
    
</script>
<!-- 下拉框的js -->

