<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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

//得到courseDetail的节次和周次信息
var startWeek=1;//开始周的全局变量
var endWeek=19;//结束周的全局变量
var canSubmit=true;
function checkForm(){
	if($("#labRooms").val()==""||$("#labRooms").val()==null){
		 alert("请选择实验室") 
	}else if($("#items").val()==""||$("#items").val()==null){
		alert("请选择实验项目")
	}else if($("#classes").val()==""||$("#classes").val()==null){
		alert("请选择节次")
	}else if($("#weeks").val()==""||$("#weeks").val()==null){
		alert("请选择周次")
	}else if($("#teachers").val()==""||$("#teachers").val()==null){
		alert("请选择上课教师")
	}else{
		if(checkStudents()){
			if(confirm('当前排课时间学生有冲突，是否继续排课？')){
				if (canSubmit==false){
			    	alert("请等待本次排课完成")
			             return;
			    }
			    canSubmit=false;
			    document.form.submit();
			}
		}else{
			if (canSubmit==false){
		    	alert("请等待本次排课完成")
		             return;
		    }
		    canSubmit=false;
		    document.form.submit();
		}
		
	    
	}
}
//flag提示是否处理调课完成0不提示，1提示
var flag =${flag};
if(flag==1){
  //var mymes= window.confirm("提交后不能修改；如未排完课，请按取消按钮继续。");
  //if(mymes){
     var sUrl ="${pageContext.request.contextPath}/timetable/doJudgeTimetableOk?courseCode=${schoolCourseDetailMap.get(0).schoolCourse.courseCode}&currpage=1";
     //parent.location.href= sUrl;
  //}
}

//判断当前选课组的排课时间内学生是否会有时间冲突
function checkStudents(){
	var discordant = true//为true说明当前时间安排有冲突
	
	$.ajax({
	    url:"${pageContext.request.contextPath}/timetable/checkStudents",
	    async:false,
	    type:'POST',
	    data:$("form").serialize(),
	    success:function(data){
	    	console.log(data);
	    	if(data == '0'){
	    		discordant = false;
	    	}
	    }	   
	});
	
	return discordant;
}
</script>

<script type="text/javascript">
var tableAppId=${tableAppId};//定义本条排课记录id的全局变量
$(document).ready(function(){
/*如果选择实验室，形成关联的选课数据的联动  */
$("#labRooms").chosen().change(function(){
	//alert(1111);
$("#weeks").html("");
var values=[];
$($("#classes option:selected")).each(function(){
	values.push(this.value);
});
var labRooms=[];
$($("#labRooms option:selected")).each(function(){
	//alert(22222);
	labRooms.push(this.value);
});

$.ajax({
    url:"${pageContext.request.contextPath}/timetable/reTimetable/getValidWeeksMapEdit",
    dataType:"json",
    type:'GET',
    data:{term:${schoolCourseDetailMap.get(0).schoolTerm.id},weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(","),tableAppId:tableAppId},
    complete:function(result)
    {
       var obj = eval(result.responseText); 
       var result2;
       for (var i = 0; i < obj.length; i++) { 
            //var val = obj.responseText[i]; 
            //result2 = result2 + "<option value='" +obj[i].id + "' selected>" + obj[i].value + "</option>";
            if($("#weekday").val()==obj[i].weekday&&obj[i].value<=endWeek&&obj[i].value>=startWeek){
               result2 = result2 + "<option value='" +obj[i].id + "'>" + obj[i].value + "</option>";
            }
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
    data:{term:${schoolCourseDetailMap.get(0).schoolTerm.id},weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(","),tableAppId:tableAppId},
    complete:function(result)
    {
       var obj = eval(result.responseText); 
       var result2;
       for (var i = 0; i < obj.length; i++) { 
            //var val = obj.responseText[i]; 
            //result2 = result2 + "<option value='" +obj[i].id + "' selected>" + obj[i].value + "</option>";
            if($("#weekday").val()==obj[i].weekday&&obj[i].value<=endWeek&&obj[i].value>=startWeek){
               result2 = result2 + "<option value='" +obj[i].id + "'>" + obj[i].value + "</option>";
            }
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
var detailString=$("#weekday").find("option:selected").text();//获取当前选择的option的text，注意不是value
var detailArray= new Array(); //定义一数组
detailArray=detailString.split(";"); //字符分割 
var classString=detailArray[1];
var classArray=new Array();
classArray=classString.split("-");
var startClass=parseInt(classArray[0]);//开始节次
var weekString=detailArray[2];
var weekArray= new Array(); 
weekArray=weekString.split("-"); //字符分割 
startWeek=parseInt(weekArray[0]);
endWeek=parseInt(weekArray[1].substring(0,weekArray[1].length-1));

$("#classes").html("");
$.ajax({
         url:"${pageContext.request.contextPath}/timetable/getTimetableClassesMapNew",
         dataType:"json",
         type:'GET',
         data:{term:${schoolCourseDetailMap.get(0).schoolTerm.id},weekday:$("#weekday").val(),labrooms:$("#labRooms").val(),courseCode:"${courseCode}",startClass:startClass},
         complete:function(result)
         {
            var obj = eval(result.responseText); 
            var result2;
            
            for (var i = 0; i < obj.length; i++) { 
                 //var val = obj.responseText[i]; 
                 if($("#weekday").val()==obj[i].weekday){
                   result2 = result2 + "<option value='" +obj[i].id + "'>" + obj[i].value + "</option>";
                 } 
            }; 
         
            $("#classes").append(result2);
            $("#classes").trigger("liszt:updated");
            result2="";
          }
});
$("#weeks").trigger("liszt:updated");
$("#classes").trigger("liszt:updated");
});
}); 

$(document).ready(function(){
/*如果选择实验室，形成关联的实验室设备的联动   2015-10-27 */
$("#labRooms").chosen().change(function(){
$("#devices").html("");
var labRooms=[];
$($("#labRooms option:selected")).each(function(){
	//alert(22222);
	labRooms.push(this.value);
});

$.ajax({
    url:"${pageContext.request.contextPath}/timetable/reTimetable/getLabroomDeviceMap",
    dataType:"json",
    type:'GET',
    data:{labrooms:labRooms.join(",")},
    complete:function(result)
    {
    	
       var obj = eval(result.responseText); 
       var result3;
       for (var i = 0; i < obj.length; i++) { 
    	   var deviceName=obj[i].deviceName;
               result3 = result3 + "<option value='" +obj[i].id + "' selected>" + "["+obj[i].value+"]"+ deviceName + "</option>";
       }; 
               
       $("#devices").append(result3);
       $("#devices").trigger("liszt:updated");
       result3="";
    }
});
$("#devices").trigger("liszt:updated");
});
//实验室设备资源关联
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

}); 

//默认不显示筛选设备的对话框
$(document).ready(function(){
	$("#labRoomDevices").css('display','none'); 
})

var deviceArray=new Array();// 备选设备，全局变量
// 判断是否允许教学之外的设备对外开放
function showDevice(){
	if($("input[id='allowUse']:checked").val()=='on'){// 允许，则显示设备筛选框
		$("#labRoomDevice_id option").each(function() {    
			$(this).attr("selected",false);
		});  
		$("#labRoomDevices").css('display',''); 
	}else {// 不允许，则默认将设备全选，并且不出现筛选框
		$("#labRoomDevices").css('display','none');
		$("#labRoomDevice_id option").each(function() {    
			$(this).attr("selected",true);
		});  
	}
}

</script>

</head>

<div class="iStyle_RightInner">
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li class="end">教务调整管理</li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">教务调整排课</div>
<table  > 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>课程编号</th>
   <th>课程名称</th>
   <th>选课组编号</th>
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
   <c:if test="${selected_labCenter eq 12 }">
   <th>是否允许<br>教学外设备对外开放</th>
   <th>实验室（设备）</th>
   </c:if>
   <c:if test="${selected_labCenter ne 12 }">
   <th>实验室</th>
   </c:if>

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
         ${current.schoolCourseDetail.schoolCourse.schoolCourseInfo.courseNumber}
         </td>
     </c:if>
     
     <c:if test="${timetableAppointmentMap.size()>1&&i.count==1 }">
         <td rowspan="${timetableAppointmentMap.size()}">
               ${current.schoolCourseDetail.schoolCourse.courseName}
         </td>
     </c:if> 
     <c:if test="${timetableAppointmentMap.size()==1 }">
         <td >
               ${current.schoolCourseDetail.schoolCourse.courseName}
         </td>
     </c:if>

     <c:if test="${timetableAppointmentMap.size()>1&&i.count==1 }">
         <td rowspan="${timetableAppointmentMap.size()}">${current.courseCode}</td>
     </c:if>
     <c:if test="${timetableAppointmentMap.size()==1 }">
         <td >${current.courseCode}</td>
     </c:if>
     
     <td>
             <c:forEach var="entry" items="${current.timetableItemRelateds}">  
             <c:out value="${entry.operationItem.lpName}" />&nbsp;
             <c:if test="${empty entry.operationItem||entry.operationItem.id==0}">
             ${current.schoolCourseDetail.schoolCourse.courseName}(课程名称)
             </c:if>
             </c:forEach>
             <c:if test="${current.timetableItemRelateds.size()==0}">
             ${current.schoolCourseDetail.schoolCourse.courseName}(课程名称)
             </c:if>

     </td>
     <td>${current.weekday}</td>
          
    <td colspan=2>
     <table>
     <c:if test="${current.timetableAppointmentSameNumbers.size()==0}">
     <tr>
             <td width="20px;">
             <c:if test="${current.startClass==current.endClass}">
             ${current.startClass }
             </c:if>
             <c:if test="${current.startClass!=current.endClass}">
             ${current.startClass }-${current.endClass }
             </c:if>
             </td>
             <td width="50px;">
             <c:if test="${current.startWeek==current.endWeek}">
             ${current.startWeek }
             </c:if>
             <c:if test="${current.startWeek!=current.endWeek}">
             ${current.startWeek }-${current.endWeek }
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
     
     <c:if test="${selected_labCenter eq 12 }"><!-- 纺织中心 -->
	     <!-- 是否允许教学外设备对外开放 -->
         <td>
            <c:if test="${current.deviceOrLab eq 1}">是</c:if>
            <c:if test="${current.deviceOrLab eq 2}">否</c:if>
         </td>
         
        <!--实验室(设备)  -->
         <td>
             <c:forEach var="entry" items="${current.timetableLabRelateds}">  
             <label title="<c:out value="${entry.labRoom.labRoomName}" />"><c:out value="${entry.labRoom.labRoomNumber}" /></label>
             <c:if test="${selected_labCenter eq 12 }"><!-- 纺织中心 -->
                <a target="_blank" href="${pageContext.request.contextPath}/timetable/listTeachingLabRoomDevice?timetableId=${current.id}&labRoomId=${entry.labRoom.id}">查看教学用设备</a>
             </c:if>    
             <br>  
             </c:forEach>
         </td>
	</c:if>
	
	<c:if test="${selected_labCenter ne 12 }"><!-- 非纺织中心 -->
		<td>
			<c:forEach var="entry" items="${current.timetableLabRelateds}">  
				<c:out value="${entry.labRoom.labRoomName}" />  
			</c:forEach>
		</td>	
	</c:if>
</tr>

</c:forEach> 
</tbody>
</table>
<br>

<div class="title">排课内容调整</div>
<c:if test="${tableAppId eq 0 }">
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/saveAdjustTimetable?currpage=${currpage}"  >
</c:if>
<c:if test="${tableAppId ne 0  }">
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/saveAdminTimetable" target=_parent>
</c:if>
<table > 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>选择实验室</th>
   <!-- <th>选择实验室设备</th> 暂时不用，隐掉-->
   <th>选择实验项目</th>
   <th>选择星期</th>
   <th>选择节次</th>
   <th>选择周次</th>

   <th>选择教师</th>
</tr>
</thead>
<tbody>
<tr>
  <!--  <th>选择</th> -->
   <td>
   <select class="chzn-select" multiple="multiple" data-placeholder='请选择实验室：' name="labRooms" id="labRooms" style="width:150px" required="true">
	  <c:forEach items="${labRoomMap}" var="current"  varStatus="i">	
	      <option value ="${current.key}"> ${current.value}</option>
	  </c:forEach>
   </select>
   </td><%--
   
    <td>
     <select class="chzn-select" multiple="multiple" data-placeholder='请选择实验设备：'  name="devices" id="devices" style="width:180px" required="false">
	</select>
   </td>  暂时不用，隐掉
   
   --%><td>
     <select class="chzn-select" multiple="multiple" data-placeholder='请选择实验项目：'  name="items" id="items" style="width:150px" required="true">
	  <option value ="0" selected> ${schoolCourseDetailMap.get(0).schoolCourse.courseName}</option>
	  <c:forEach items="${timetableItemMap}" var="current"  varStatus="i">	
	      <option value ="${current.key}"> ${current.value}</option>
	  </c:forEach>
	</select>
   </td>
   <td>
   <select class="chzn-select" name="weekday" id="weekday" style="width:120px">
     <!--如果星期重复，则去重  -->
     <option value ="">请选择</option>
	 <c:forEach items="${schoolCourseDetailMap}" var="current"  varStatus="i">
	    <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR">
                <option value ="${current.weekday}"> 星期${current.weekday};${current.startClass}-${current.endClass}节;${current.startWeek}-${current.endWeek}周</option>
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_EXCENTERDIRECTOR">
                <option value ="${current.weekday}"> 星期${current.weekday};${current.startClass}-${current.endClass}节;${current.startWeek}-${current.endWeek}周</option>
        </sec:authorize>
	 
     </c:forEach>
   </select>
   </td>
   <td>
  <select class="chzn-select" multiple="multiple" data-placeholder='请选择节次' name="classes" id="classes" style="width:150px" required="true">
   </select> 
   </td>
   <td>
   <select class="chzn-select"  multiple="multiple" data-placeholder='请选择排课周次' name="weeks" id="weeks" style="width:180px" required="true">
   </select>
   </td>
  
   <td>
  <select class="chzn-select" multiple="multiple" data-placeholder='请选择授课教师' name="teachers" id="teachers" style="width:150px" required="true">
	  <option value ="${schoolCourseDetailMap.get(0).user.username}" selected>${schoolCourseDetailMap.get(0).user.cname}:${schoolCourseDetailMap.get(0).user.username};${schoolCourseDetailMap.get(0).schoolAcademy.academyName}</option>
	  <c:forEach items="${timetableTearcherMap}" var="current"  varStatus="i">
	      <option value ="${current.key}" > ${current.value}</option>
	  </c:forEach>
   </select>
   </td>
</tr>
<c:if test="${selected_labCenter==12 }">
	<tr>
    	<td align=left>是否允许教学外设备对外开放：</td>
    	<td>
    		<input type="radio" id="allowUse" name="group1" onclick="showDevice()">是</input>
    		<input type="radio" id="forbidUse" name="group1" onclick="showDevice()">否</input>
    	</td>
    </tr>
    
	<tr  id="labRoomDevices">
	<td align=left>请选择实验室设备：</td>
	<td colspan=6>
		<select class="chzn-select" data-placeholder='请选择实验室设备：' multiple="multiple"	name="labRoomDevice_id" id="labRoomDevice_id" items=""	invalidMessage="不能超过30个字符！" style="width:450px"	required="true">
			<c:forEach items="" var="entry">
				<option value=""></option>
			</c:forEach>
		</select> 
	</td>
	</tr>
	</c:if>
<tr>
<td colspan=6>
&nbsp;
</td>
<tr>
<tr>
<td colspan=6>
<hr>
</td>
<tr>
<td colspan=4>
<input type="hidden" name="courseDetailNo" value="${schoolCourseDetailMap.get(0).courseDetailNo}">
<input type="hidden" name="courseCode" value="${courseCode}">
<input type="hidden" name="currpage" value="${currpage}">

<!--传递修改的排课表主键  -->
<input type="hidden" value="${tableAppId}" name="id">


<input type="button" onclick="checkForm()" value="提交">&nbsp;&nbsp;
</td>

<c:if test="${timetableAppointmentMap.size()>0}">
<td >
(<font color=red><b>请核实不同的星期是否调课完成，如果完成请点击完成调课</b></font>)
</td>
<td align="right">
<a class="btn btn-new" href="${pageContext.request.contextPath}/timetable/doJudgeTimetableOk?courseCode=${schoolCourseDetailMap.get(0).schoolCourse.courseCode}&currpage=1" target=_parent><font color=red>调课完成</font></a>
</td>
</c:if> 
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

