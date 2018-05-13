<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<script type="text/javascript">
//flag提示是否处理调课完成0不提示，1提示
var flag =${flag};
if(flag==1){
  //var mymes= window.confirm("提交后不能修改；如未排完课，请按取消按钮继续。");
  //if(mymes){
     var sUrl ="${pageContext.request.contextPath}/timetable/doOnlyNoGroupTimetableOk?term=${schoolTerm.id}&courseCode=${courseCodes.get(0).schoolCourse.courseCode}";
     //window.location.href= sUrl;
  //}
}
</script>

<script type="text/javascript">
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
    url:"${pageContext.request.contextPath}/timetable/reTimetable/getValidWeeksMap",
    dataType:"json",
    type:'GET',
    data:{term:${schoolTerm.id},weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(",")},
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

/*如果选择实验室，对实验设备进行关联的数据的联动  */
$("#labRooms").chosen().change(function(){
$("#labRoomDevice_id").html("");
var labRooms=[];
$($("#labRooms option:selected")).each(function(){
	
	labRooms.push(this.value);
});
$.ajax({
    url:"${pageContext.request.contextPath}/timetable/getValidLabroomDevice",
    dataType:"json",
    contentType:"application/json",
    data:{labrooms:labRooms.join(",")},
    complete:function(result)
    {
       var obj = eval(result.responseText); 
       var result2;
       for (var i = 0; i < obj.length; i++) { 
            result2 = result2 + "<option value='" +obj[i].id + "'>" + obj[i].value + "</option>";
          }; 
 
       $("#labRoomDevice_id").append(result2);
       $("#labRoomDevice_id").trigger("liszt:updated");
       result2="";
    }
});
$("#labRoomDevice_id").trigger("liszt:updated");
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
    url:"${pageContext.request.contextPath}/timetable/reTimetable/getValidWeeksMap",
    dataType:"json",
    type:'GET',
    data:{term:${schoolTerm.id},weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(",")},
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

</script>

<script type="text/javascript">
$(document).ready(function(){
/*如果选择实验项目，形成关联的选课数据的联动  */
$("#items").chosen().change(function(){
$.ajax({
    url:"${pageContext.request.contextPath}/timetable/reTimetable/getValidItemMap",
    dataType:"json",
    type:'GET',
    data:{itemid:$("#items option:selected").val()},
    complete:function(result)
    {
       var obj = eval(result.responseText); 
       if(obj[0].groups!=null&&obj[0].groups!="null"){
          $("#groups").attr("value",obj[0].groups); 
       }
       if(obj[0].groupCount!=null&&obj[0].groupCount!="null"){
           $("#groupCount").attr("value",obj[0].groupCount); 
       }
       if(obj[0].labhours!=null&&obj[0].labhours!="null"){
         $("#labhours").attr("value",obj[0].labhours); 
       }
    }
});

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
    url:"${pageContext.request.contextPath}/timetable/reTimetable/getValidWeeksMap",
    dataType:"json",
    type:'GET',
    data:{term:${schoolTerm.id},weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(",")},
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
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">日历选择详细信息</div>
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
   <th>选课人数</th>
   <td>${courseCodes.get(0).schoolCourseStudents.size()} </td>
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
     <th>授课教师     </th>
     <td>
       ${courseCodes.get(0).user.cname}
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
<font color=red>${warning }</font>
<hr>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">已排课程内容</div>
<table  > 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>课程编号</th>
   <th>课程名称</th>
   <th>选课组编号</th>
   <th>实验项目</th>
   <th width="140px;" colspan=2>
       <table>
       <tr>
          <td width="50px;">周次</td>
          <th width="50px;">星期</th>
          <td width="20px;">节次</td>
       </tr>
       </table>
   </th>
   <th>授课教师</th>
   <th>实验室</th>
</tr>
</thead>
<tbody>
<c:forEach items="${timetableAppointmentMap}" var="current"  varStatus="i">	
<tr>
    <c:if test="${timetableAppointmentMap.size()>1&&i.count==1 }">
        <td rowspan="${timetableAppointmentMap.size()}">${current.schoolCourseDetail.schoolCourse.schoolCourseInfo.courseNumber}</td>
    </c:if>
    <c:if test="${timetableAppointmentMap.size()==1 }">
        <td >
        ${current.schoolCourseInfo.courseNumber}
        </td>
    </c:if>
    
    <c:if test="${timetableAppointmentMap.size()>1&&i.count==1 }">
         <td rowspan="${timetableAppointmentMap.size()}">
             ${current.schoolCourseDetail.schoolCourse.courseName} 
         </td>
     </c:if>
     <c:if test="${timetableAppointmentMap.size()==1 }">
         <td >
             ${current.schoolCourseInfo.courseName}
         </td>
     </c:if>
   
    <c:if test="${timetableAppointmentMap.size()>1&&i.count==1 }">
        <td rowspan="${timetableAppointmentMap.size()}">${current.schoolCourseDetail.schoolCourse.courseCode}</td>
    </c:if>
    <c:if test="${timetableAppointmentMap.size()==1 }">
        <td >${current.schoolCourseDetail.schoolCourse.courseCode}</td>
    </c:if>
    
    <td>
       <c:forEach var="timetableItem" items="${current.timetableItemRelateds}">  
             <c:if test="${empty timetableItem.operationItem||timetableItem.operationItem.id==0}">
                 ${current.schoolCourseInfo.courseName}(课程名称)
             </c:if>
             <c:if test="${not empty timetableItem.operationItem&&timetableItem.operationItem.id!=0}">
                 <c:out value="${timetableItem.operationItem.lpName}" />&nbsp;
             </c:if>
         </c:forEach>
         <c:if test="${current.timetableItemRelateds.size()==0}">
             ${current.schoolCourseInfo.courseName}(课程名称)
         </c:if>
    </td>
    <td colspan=2>
     <table>
     <c:if test="${current.timetableAppointmentSameNumbers.size()==0}">
     <tr>    
             <td width="50px;">
             <c:if test="${current.startWeek==current.endWeek}">
             ${current.startWeek }
             </c:if>
             <c:if test="${current.startWeek!=current.endWeek}">
             ${current.startWeek }-${current.endWeek }
             </c:if>
             </td>
             <td width="50px;">${current.weekday}</td>
             <td width="20px;">
             <c:if test="${current.startClass==current.endClass}">
             ${current.startClass }
             </c:if>
             <c:if test="${current.startClass!=current.endClass}">
             ${current.startClass }-${current.endClass }
             </c:if>
             </td>
             
      </tr>
      </c:if>
     <c:if test="${current.timetableAppointmentSameNumbers.size()>0}">
         
         <c:set var="sameStart" value="-"></c:set>
         <c:forEach var="entry" items="${current.timetableAppointmentSameNumbers}"   varStatus="p"> 
         <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
         <c:if test="${fn:indexOf(sameStart,v_param)<0}">
         <tr>
         <td width="50px;">
         <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
         <c:forEach var="entry1" items="${current.timetableAppointmentSameNumbers}"  varStatus="q">  
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
         <td width="50px;">${current.weekday}</td>
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

    <td>
    <!--上课教师  -->
     <c:forEach var="entry" items="${current.timetableTeacherRelateds}">  
    <c:out value="${entry.user.cname}" />  
    </c:forEach> 
    </td>
    
  <!--上课教师  -->
     <td>
     <c:forEach var="entry" items="${current.timetableLabRelateds}">  
     <c:out value="${entry.labRoom.labRoomName}" />  
     </c:forEach> 
     </td>
</tr>
</c:forEach> 
</tbody>
</table>

<!--如果发布或调课完成，则不显示编辑排课调整内容  -->
<c:if test="${!(!empty timetableAppointmentMap&&(timetableAppointmentMap.iterator().next().status==2||timetableAppointmentMap.iterator().next().status==1))  }">
<div class="title">排课内容调整</div>
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/saveOnlyNoGroupReTimetable?courseCode=${courseCode}">

<table > 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>调整实验室</th>
   <th>选择实验项目</th>
   <th>调整星期</th>
   <th>调整节次</th>
   <th>调整周次</th>
   <th>调整教师</th>
</tr>
</thead>
<tbody>
<c:if test="${!empty timetableAppointmentMap&&timetableAppointmentMap.iterator().next().status!=2|| empty timetableAppointmentMap}">
<tr>
   <!--  <th>选择</th> -->
   <td>
     <select class="chzn-select" multiple="multiple" name="labRooms" id="labRooms" style="width:150px" required="true">
	    <c:forEach items="${labRoomMap}" var="current"  varStatus="i">
	        <c:if test="${current.key == labroom }">
	           <option value ="${current.key}" selected> ${current.value}</option>
	        </c:if>
	        <c:if test="${current.key != labroom }">
	           <option value ="${current.key}">${current.value}</option>
	        </c:if>
	    </c:forEach>
     </select><br>
     <c:if test="${selected_labCenter==12 }">
		<select class="chzn-select" data-placeholder='请选择实验室设备：' multiple="multiple"	name="labRoomDevice_id" id="labRoomDevice_id" items=""	invalidMessage="不能超过30个字符！" style="width:150px" required="true">
			<c:forEach items="" var="entry">
				<option value=""></option>
			</c:forEach>
		</select> 
   </c:if>
   </td>
   <td>
     <select class="chzn-select" multiple="multiple" data-placeholder='请选择实验项目：' name="items" id="items" style="width:140px" required="true">
	   <option value ="0" selected> ${courseCodes.get(0).schoolCourse.courseName}</option>
	   <c:forEach items="${timetableItemMap}" var="current"  varStatus="i">	
	       <option value ="${current.key}"> ${current.value}</option>
	   </c:forEach>
	 </select>
	 
   </td>
   <!--调整星期  -->
   <td>
     <select class="chzn-select"   name="weekday" id="weekday" style="width:140px" >
	   <c:forEach begin="1" end="7"  varStatus="i">
	       <c:if test="${i.count == appointment.weekday }">
               <option value ="${i.count}" selected> 星期${i.count}</option>
           </c:if>
           <c:if test="${i.count != appointment.weekday  }">
               <option value ="${i.count}"> 星期${i.count}</option>
           </c:if>
       </c:forEach>
     </select>
   </td>
   <!--调整节次  -->
   <td>
       <input type="hidden" name="classids" id="classids" value ="${classids}">
       <select class="chzn-select" multiple="multiple" data-placeholder='请选择节次：' name="classes" id="classes" style="width:140px" required="true">
           <!--如果星期重复，则节次累加  -->
           <%-- <c:if test="${classids<5 }">
           <option value ="${classids*2-1}" selected> ${classids*2-1}</option>
           <option value ="${classids*2}" selected> ${classids*2}</option>
           </c:if>
           <c:if test="${classids>=5 }">
           <option value ="${classids+4}" selected> ${classids+4}</option>
           </c:if> --%>
           <c:forEach var="class" varStatus="cStatus" begin="1" end="13">
           <option value ="${cStatus.index}" > ${cStatus.index}</option>
           </c:forEach>
       </select>
   </td>

   <td>
     <select class="chzn-select"  multiple="multiple"  name="weeks" data-placeholder='当前实验室的周次没有课选项：' id="weeks" style="width:140px"  required="true">
	   <c:forEach items="${weeks}" var="current"  varStatus="i">
	   <c:if test="${current<=16}">
       <option value ="${current}"> ${current}</option>
       </c:if>
        <c:if test="${current>16}">
       <option value ="${current}"> ${current}</option>
       </c:if>
       </c:forEach>
     </select>
   </td>
   <td> 
    <select class="chzn-select" multiple="multiple" name="teachers" id="teachers" data-placeholder='请选择授课教师：' style="width:150px" required="true">
       <option value ="${courseCodes.get(0).user.username}" selected> ${courseCodes.get(0).user.cname}:${courseCodes.get(0).user.username}:${courseCodes.get(0).user.schoolAcademy.academyName}</option>
	   <c:forEach items="${timetableTearcherMap}" var="current"  varStatus="i">
	        <c:if test="${current.key != courseCodes.get(0).user.username }">
	           <option value ="${current.key}"> ${current.value}</option>
	        </c:if>
	    </c:forEach>
     </select>
   </td>
</tr>
</c:if>
<tr>
   <td>实验准备（环境）(<font color=red>*必填</font>)</td>
   <td>实验组数</td>
   <td>每组人数</td>
   <td>实验学时数</td>
   <td>实验材料消耗费（元/组*时）</td>
   <td>&nbsp;</td>
</tr>
<tr>
   <td><input id="preparation" name="preparation" type="text" value=""  style="width:120px" class="easyui-validatebox"  required="true"></td>
   <td><input id="groups"name="groups" type="text"   style="width:120px" value=""  ></td>
   <td><input id="groupCount" name="groupCount" type="text" style="width:120px" value="" ></td>
   <td><input id="labhours" name="labhours" type="text"  style="width:120px" value="" ></td>
   <td><input id="consumablesCosts" name="consumablesCosts" type="text" value="0"  style="width:120px" class="easyui-numberbox" precision="2" required="true" ></td>
</tr> 
<tr>
   <td colspan=6>
   &nbsp;
   </td>
</tr>
<tr>
   <td colspan=6>
   <hr>
   </td>
</tr>
</c:if>
<tr>
   <td colspan=3>
   <input type="hidden" name="courseDetailNo" value="${courseCodes.get(0).courseDetailNo}">
   <input type="hidden" name="term" value="${schoolTerm.id}">
   <input type="hidden" name="term" value="${schoolTerm.id}">
   <c:if test="${!(!empty timetableAppointmentMap&&(timetableAppointmentMap.iterator().next().status==2||timetableAppointmentMap.iterator().next().status==1))  }">
   <input type="submit" value="提交">
   </c:if>
   &nbsp;
   <!-- <a href="javascript:window.parent.window.$('#showTimetable').window('close');"><input type="button" value="关闭"></a>
 --> 
 </td>
 <td  colspan=3>
<c:if test="${!empty timetableAppointmentMap&&timetableAppointmentMap.iterator().next().status!=2&&timetableAppointmentMap.iterator().next().status!=1  }">
<a class="btn btn-new" href="${pageContext.request.contextPath}/timetable/doOnlyNoGroupTimetableOk?term=${schoolTerm.id}&courseCode=${courseCodes.get(0).schoolCourse.courseCode}" ><font color=red>调课完成</font></a>
(<font color=red><b>请核实不同的分组是否调课完成，如果完成请点击完成调课</b></font>)
 </c:if>
<c:if test="${!empty timetableAppointmentMap&&(timetableAppointmentMap.iterator().next().status==2||timetableAppointmentMap.iterator().next().status==1) }">
<font color=red>调课已完成</font>
</c:if>
  
   </td>
</tr>
</tbody>
</table>
<hr>
</form>

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