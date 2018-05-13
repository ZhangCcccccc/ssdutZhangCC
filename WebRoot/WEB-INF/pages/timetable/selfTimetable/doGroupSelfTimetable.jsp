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
var tableAppId=${tableAppId};//定义本条排课记录id的全局变量
$(document).ready(function(){
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
         data:{term:${schoolTerm.id},weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(","),tableAppId:tableAppId},
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

$("#classes").chosen().change(function(){
$("#classes").trigger("liszt:updated");
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
         data:{term:${schoolTerm.id},weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(","),tableAppId:tableAppId},
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
    data:{term:${schoolTerm.id},weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(","),tableAppId:tableAppId},
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
</tr>

<tr>
     <th >课程名称 </th>
     <td >
         ${courseCodes.schoolCourseInfo.courseName}
     </td>
     <th >选课组编号</th>
     <td >
         ${courseCodes.courseCode}
     </td>
     <th>授课教师     </th>
     <td>
       ${courseCodes.user.cname}
     </td>
</tr>
<tr>
     <th >批次名称 </th>
     <td >
         ${group.timetableBatch.batchName}
     </td>
     <th >分组名称</th>
     <td >
         ${group.groupName}
     </td>
     <th>分组人数     </th>
     <td>
       ${group.numbers}
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

<div class="title">排课内容调整</div>
<c:if test="${tableAppId eq 0 ||tableAppId eq -1}">
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/selfTimetable/saveGroupSelfTimetable?courseCode=${courseCodes.id}&appointment=${appointment.id}">
</c:if>
<c:if test="${tableAppId ne 0 && tableAppId ne -1}">
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/saveAdminTimetable" target=_parent>
</c:if>
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
<tr>
  <!--  <th>选择</th> -->
   <td>
     <select class="chzn-select" multiple="multiple" name="labRooms" id="labRooms" style="width:180px">
	    <c:forEach items="${labRoomMap}" var="current"  varStatus="i">
	        <c:if test="${current.key eq labRoom.id }">
	           <option value ="${current.key}" selected> ${current.value}</option>
	        </c:if>

	        <c:if test="${current.key != labRoom.id }">
	           <option value ="${current.key}"> ${current.value}</option>
	        </c:if>
	    </c:forEach>
	    <c:if test="${!empty appointment}">
	    <c:forEach items="${appointment.timetableLabRelateds}" var="current"  varStatus="i">
	         <option value ="${current.labRoom.id}" selected> ${current.labRoom.labRoomNumber}</option>
	    </c:forEach>
	    </c:if>
     </select><br>
   </td>
   <td>
     <select class="chzn-select" multiple="multiple" name="items" id="items" style="width:150px"   required="true">
	   <option value ="0"> ${courseCodes.schoolCourseInfo.courseName}</option>
	  
	   <c:forEach items="${timetableItemMap}" var="current"  varStatus="i">	
	       <option value ="${current.key}">${current.value}</option>
	   </c:forEach> 
	   <c:if test="${appointment.timetableItemRelateds.size()>0}">
	    <c:forEach items="${appointment.timetableItemRelateds}" var="current"  varStatus="i">
	         <option value ="${current.operationItem.id}" selected> 
	          <c:if test="${empty current.operationItem||current.operationItem.id==0}">
	          ${courseCodes.schoolCourseInfo.courseName}
	          </c:if>
	         <c:if test="${not empty current.operationItem&&current.operationItem.id!=0}">
              ${current.operationItem.lpName}
              </c:if>
	         </option>
	    </c:forEach>
	    </c:if> 

	     
	 </select>
	 <input type="hidden" name="group" id="group" value ="${group.id}">
   </td>
   <!--调整星期  -->
   <td>
     <select class="chzn-select"   name="weekday" id="weekday" style="width:80px">
	   <c:forEach begin="1" end="7"  varStatus="i">
	       <c:if test="${i.count == appointment.weekday }">
               <option value ="${i.count}" selected> 星期${i.count}</option>
           </c:if>
           <c:if test="${i.count !=appointment.weekday  }">
               <option value ="${i.count}"> 星期${i.count}</option>
           </c:if>
       </c:forEach>
     </select>
   </td>
   <td>
       <input type="hidden" name="classids" id="classids" value ="${classids}">
       <select class="chzn-select" data-placeholder="选择节次" multiple="multiple"  name="classes" id="classes" style="width:120px" required="true">
	       <c:forEach begin="1" end="13"  varStatus="i">
             <option value ="${i.count}"> ${i.count}</option>
           </c:forEach>
       </select>
   </td>

   <td>
     <select class="chzn-select" data-placeholder="选择周次"  multiple="multiple"  name="weeks" id="weeks" style="width:180px" required="true">
	   <c:forEach begin="1" end="23"  varStatus="i">
          <option value ="${i.count}"> ${i.count}</option>
       </c:forEach>
     </select>
   </td>
   <td>
    <select class="chzn-select" multiple="multiple" name="teachers" id="teachers" style="width:120px" required="true">
	    <c:forEach items="${timetableTearcherMap}" var="current"  varStatus="i">
	        <c:if test="${current.key eq courseCodes.user.username }">
	           <option value ="${current.key}" selected> ${current.value}</option>
	        </c:if>
	        <c:if test="${current.key != courseCodes.user.username }">
	           <option value ="${current.key}"> ${current.value}</option>
	        </c:if>
	    </c:forEach>
     </select>
   </td>
</tr>

	<tr>
    	<td align=left>是否允许教学外设备对外开放：</td>
    	<td>
    		<input type="radio" id="allowUse" name="group1" onclick="showDevice()">是</input>
    		<input type="radio" id="forbidUse" name="group1" onclick="showDevice()">否</input>
    	</td>
    </tr>
    
	<tr  id="labRoomDevices">
	<td align=left>请选择本实验项目所用设备：</td>
	<td colspan=6>
		<select class="chzn-select" data-placeholder='请选择本实验项目所用设备：' multiple="multiple"	name="labRoomDevice_id" id="labRoomDevice_id" items=""	invalidMessage="不能超过30个字符！" style="width:450px">
			<c:forEach items="" var="entry">
				<option value=""></option>
			</c:forEach>
		</select> 
	</td>
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
<tr>
   <td colspan=6>
   
  <%--  <input type="hidden" name="courseDetailNo" value="${courseCodes.courseDetailNo}"> --%>
  <input type="hidden" value="${tableAppId}" name="id">
  <input type="hidden" name="courseCode" value="${courseCode}">
   <input type="submit" value="提交">
   &nbsp;<a href="javascript:history.go(-1)"><input type="button" value="返回"></a>
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