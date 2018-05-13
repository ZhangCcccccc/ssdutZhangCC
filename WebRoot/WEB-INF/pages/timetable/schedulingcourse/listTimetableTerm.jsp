<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.lab-resources" />
<head>
<meta name="decorator" content="iframe" />
<link href="${pageContext.request.contextPath}/css/iconFont.css"
	rel="stylesheet">
<!-- 下拉框的样式 -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<!-- 打印插件的引用 -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
  <script type="text/javascript">
$(document).ready(function(){
/*如果选择实验室，形成关联的选课数据的联动  */
$("#labRoom_id").chosen().change(function(){
var labRooms=[];
$($("#labRoom_id option:selected")).each(function(){
	labRooms.push(this.value);
});
var isConflict = "yes";
var checkText=[];
$($("#labRoom_id option:selected")).each(function(){
	checkText.push(this.value);
});

$.ajax({
    url:"${pageContext.request.contextPath}/timetable/getDirectValid",
    dataType:"json",
    type:'GET',
    data:{term:${term},labrooms:labRooms.join(","),courseCode:$("#courseCode").val()},
    complete:function(json)
    {
       if(json.responseText=="no"){
       for (var i = 0; i < checkText.length; i++) {
          $("#labRoom_id option[value='" +  checkText[i]+ "']").attr("selected",false);;
          
       }
                 $("#labRoom_id").trigger("liszt:updated");
         alert("所选择的实验室资源冲突，请重新选择或者用调整排课操作，谢谢。");
       }
       
    }
});

});

$("#labRoom_id").chosen().change(function(){
$("#labRoomDevice_id").html("");
var labRooms=[];
$($("#labRoom_id option:selected")).each(function(){
	
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

/*如果选择实验室，对实验设备进行关联的数据的联动  */
$("#labRoom_id").chosen().change(function(){
$("#devices").html("");
var labRooms=[];
$($("#labRoom_id option:selected")).each(function(){
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
               result3 = result3 + "<option value='" +obj[i].id + "' >" +  deviceName + "</option>";
       }; 
               
       $("#devices").append(result3);
       $("#devices").trigger("liszt:updated");
       result3="";
    }
});
$("#devices").trigger("liszt:updated");
});

/*
 *直接排课弹出窗口
 */
$("#deviceButton").live('click',function(){
	var devices=[];
	$($("#devices option:selected")).each(function(){
		devices.push(this.value);
	});
    var isConflict=0;
	$.ajax({
	    url:"${pageContext.request.contextPath}/timetable/academy/getLabroomDeviceValid",
	    dataType:"json",
	    async: true,
	    type:'GET',
	    data:{devices:devices.join(","),courseCode:$("#courseCode").val()},
	    complete:function(json){
	       if(json.responseText=="no"){
	         alert("所选择的实验室资源冲突，请重新选择或者用调整排课操作，谢谢。");
	         isConflict = 0;
	       }
	       if(json.responseText=="yes"){
	         isConflict = 1;
	       }
	       if(isConflict==1){
	    	   
	    	   if($("#labRoom_id").val()==""||$("#labRoom_id").val()==null){
	    			alert("请选择实验室")
	    		}else if($("#teacherRelated").val()==""||$("#teacherRelated").val()==null){
	    			alert("请选择上课老师")
	    		}else if($("#tutorRelated").val()==""||$("#tutorRelated").val()==null){
	    			alert("请选择指导老师")
	    		}else{
				   $("#form_lab").submit();
	    		}
		   }
 
	    }
	    
	});
	
})

})
</script>


<script>

/*
 *直接排课弹出窗口
 */
function startTimetable(courseCode) {
	var sessionId = $("#sessionId").val();
	$("#courseCode").val(courseCode)
	//获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#doStart').window({left:"0px", top:topPos+"px"});
	$('#doStart').window('open');
	$('#form_lab').attr(
			"action",
			"${pageContext.request.contextPath}/timetable/doDirectTimetable?courseCode=" + courseCode+"&currpage=${pageModel.currpage}");

}
/*
 *调整排课弹出窗口
 */
function doAdjustTimetable(courseCode) {
	var sessionId = $("#sessionId").val();
	var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/openAdjustTimetable?currpage=${pageModel.nextPage-1}&flag=0&courseCode='+ courseCode
			+'&tableAppId='+0+'" style="width:100%;height:100%;"></iframe>'
		$('#doAdjust').html(con);
		//获取当前屏幕的绝对位置
        var topPos = window.pageYOffset;
        //使得弹出框在屏幕顶端可见
        $('#doAdjust').window({left:"0px", top:topPos+"px"});
		$('#doAdjust').window('open');
}
/*
 *查看排课弹出窗口
 */
function listTimetable(courseCode) {
	var sessionId = $("#sessionId").val();
	var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/openSearchTimetable?courseCode='
			+ courseCode + '" style="width:100%;height:100%;"></iframe>'
	$('#doSearch').html(con);
	//获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#doSearch').window({left:"0px", top:topPos+"px"});
	$('#doSearch').window('open');
}

/*
 *查看学生名单
 */
function listTimetableStudents(courseDetailNo) {
	var sessionId = $("#sessionId").val();
	var con = '<iframe scrolling="yes" id="message1" frameborder="0"  src="${pageContext.request.contextPath}/timetable/openSearchStudent?courseDetailNo='
			+ courseDetailNo
			+ '" style="width:100%;height:100%;"></iframe>'
	$('#doSearchStudents').html(con);
	$("#doSearchStudents").show();
	//获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#doSearchStudents').window({left:"px", top:topPos+"px"});
	$('#doSearchStudents').window('open');
}
</script>

<!--学期查询条件同步 -->
<script  type="text/javascript">
$(document).ready(function(){
$("#term").chosen().change(function(){

$.ajax({
         url:"${pageContext.request.contextPath}/timetable/getCourseCodeList",
         dataType:"json",
         contentType: "application/x-www-form-urlencoded; charset=utf-8", 
         type:'GET',
         data:{term:$("#term").val()},
         complete:function(result)
         {
            var obj = eval(result.responseText); 
            var result2;
            $("#schoolCourse_courseNo").empty();
            
            result2 = result2 + "<option value='-1'>所有选课组</option>";
            for (var i = 0; i < obj.length; i++) { 
                 result2 = result2 + "<option value='" +obj[i].courseNo + "'>" + obj[i].value + "</option>";
                 
        
            }
            $("#schoolCourse_courseNo").append(result2);
            $("#schoolCourse_courseNo").trigger("liszt:updated");
            result2="";
          }
});
$("#schoolCourse_courseNo").trigger("liszt:updated");
});
}); 
	
	// 默认不显示筛选设备的对话框
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

<!-- 下拉的样式 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
</head>

<body>
<div class="iStyle_RightInner">
<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">排课管理</a>
			</li>
			<li class="end">
			教务排课管理
			</li>
		</ul>
	</div>
</div>

<input type="hidden" id="courseCode" value="">
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
	<form:form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/listTimetableTerm?currpage=1&id=-1"	modelAttribute="schoolCourseDetail">
	<ul>
	   <li>学期：</li>
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
		<li>选课组： </li>
		<li>
		<form:select class="chzn-select"	path="schoolCourse.courseNo" id="schoolCourse_courseNo"	cssStyle="width:500px">
				<form:option value="-1"	label="所有选课组" />
				<c:forEach items="${schedulingCourseAllMap}" var="current"	varStatus="i">
					<form:option value="${current.key}"	label="${current.value}" />
				</c:forEach>
		</form:select></li>
		<li><input type="submit" value="查询"></li>
		<li><a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?currpage=1&id=-1"><input type="button" value="取消查询"></a></li>

	</ul>
	</form:form>
</div>
<div class="content-box">
<div class="title">教务排课管理列表</div>
<table>
<thead>
<tr>
	<!--  <th>选择</th> -->
	<th>序号</th>
	<th>课程编号</th>
	<th>课程名称</th>
	<th>选课组编号</th>
	<th>学期</th>
	<th>课程安排</th>
	<th>教师名称</th>
	<th>学生名单</th>
	<th colspan=3>操作</th>
</tr>
</thead>
<tbody>
<c:set var="count" value="1"></c:set>
<c:set var="ifRowspan" value="0"></c:set>
<c:set var="ifCodeRowspan" value="0"></c:set>

<c:forEach items="${schedulingCourseMap}" var="current"	varStatus="i">
<c:set var="rowspan" value="0"></c:set>
<c:set var="rowcodespan" value="0"></c:set>

<tr>
<!--  <td></td> -->
<c:forEach items="${schedulingCourseMap}" var="current1" varStatus="j">
	<c:if test="${current1.schoolCourse.schoolCourseInfo.courseNumber==current.schoolCourse.schoolCourseInfo.courseNumber }">
		<c:set value="${rowspan + 1}" var="rowspan" />
	</c:if>
	<c:if test="${current1.schoolCourse.courseCode==current.schoolCourse.courseCode }">
		<c:set value="${rowcodespan + 1}" var="rowcodespan" />
	</c:if>
</c:forEach>

<!--课程编号  -->
<c:if test="${rowspan>1&&ifRowspan!=current.schoolCourse.schoolCourseInfo.courseNumber }">
	<td rowspan="${rowspan }">${count}</td>
</c:if>
<c:if test="${rowspan==1 }">
	<td>${count}</td>
</c:if>
<c:if test="${rowspan>1&&ifRowspan!=current.schoolCourse.schoolCourseInfo.courseNumber }">
	<td rowspan="${rowspan }">${current.schoolCourse.schoolCourseInfo.courseNumber}</td>
	<c:set var="count" value="${count+1}"></c:set>
</c:if>
<c:if test="${rowspan==1 }">
	<td>${current.schoolCourse.schoolCourseInfo.courseNumber}</td>
	<c:set var="count" value="${count+1}"></c:set>
</c:if>

<!--课程名称  -->
<c:if test="${rowspan>1&&ifRowspan!=current.schoolCourse.schoolCourseInfo.courseNumber }">
	<td rowspan="${rowspan }">${current.schoolCourse.courseName}</td>
	<c:set var="ifRowspan" value="${current.schoolCourse.schoolCourseInfo.courseNumber }"></c:set>
</c:if>
<c:if test="${rowspan==1 }">
	<td>${current.schoolCourse.courseName}</td>
</c:if>

<!-- 选课组编号 -->
<c:if test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td rowspan="${rowcodespan }">${current.schoolCourse.courseCode}</td>
</c:if>
<c:if test="${rowcodespan==1 }">
	<td>${current.schoolCourse.courseCode}</td>
</c:if>

<!--学期  -->
<c:if test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td rowspan="${rowcodespan }">${current.schoolCourse.schoolTerm.termName}</td>
</c:if>
<c:if test="${rowcodespan==1 }">
	<td>${current.schoolCourse.schoolTerm.termName}</td>
</c:if>

<!-- 课程安排  -->
<td><c:if test="${current.weekday==1 }">
   星期一${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
 </c:if> <c:if test="${current.weekday==2 }">
       星期二${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
 </c:if> <c:if test="${current.weekday==3 }">
       星期三${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
 </c:if> <c:if test="${current.weekday==4 }">
       星期四${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
 </c:if> <c:if test="${current.weekday==5 }">
       星期五${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
 </c:if> <c:if test="${current.weekday==6 }">
       星期六${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
 </c:if> <c:if test="${current.weekday==7 }">
       星期七${current.startClass }-${current.endClass }[${current.startWeek }-${current.endWeek }]
 </c:if></td>

<!-- 教师名称  -->
<td >${current.user.cname}${current.user.username}&nbsp;</td>
<!-- 学生名单  -->
<c:if
	test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td rowspan="${rowcodespan }"><a href='javascript:void(0)'
		onclick='listTimetableStudents("${current.courseDetailNo }")'>名单：
		<!--计算state为1的选课学生  -->
		<c:set value="0" var="counta" />
		<c:forEach items="${current.schoolCourseStudents}" var="schoolCourseStudentSets" varStatus="k">
		    <c:if test="${schoolCourseStudentSets.state==1 }">
		       <c:set value="${counta+1 }" var="counta" />
		    </c:if>
		</c:forEach>${counta} </a></td>
</c:if>
<c:if test="${rowcodespan==1 }">
	<td><a href='javascript:void(0)'
		onclick='listTimetableStudents("${current.courseDetailNo }")'>名单：
			<c:set value="0" var="counta" />
		<c:forEach items="${current.schoolCourseStudents}" var="schoolCourseStudentSets" varStatus="k">
		    <c:if test="${schoolCourseStudentSets.state==1 }">
		       <c:set value="${counta+1 }" var="counta" />
		    </c:if>
		</c:forEach>${counta} </a> </a></td>
</c:if>

<!-- 操作 直接排课 -->
<c:if test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td rowspan="${rowcodespan }">
	    <c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==1}">
			<font color=green>直接排课完成</font>
		</c:if> 
		<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==10}">
		</c:if> 
		<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==10}">

		</c:if> 
		<c:if test="${current.timetableAppointments.size()==0}">
		    <!--课程相关的教师以及主任具有直接排课的权限  -->
		    <sec:authorize ifNotGranted="ROLE_EXCENTERDIRECTOR">
		    <sec:authorize ifAnyGranted="ROLE_TEACHER">
		    <c:if test="${current.user.username==user.username}">
			<a  href='javascript:void(0)' onclick='startTimetable("${current.schoolCourse.courseCode }")'>直接排课</a>
			</c:if>
			</sec:authorize>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR">
			<a  href='javascript:void(0)' onclick='startTimetable("${current.schoolCourse.courseCode }")'>直接排课</a>
			</sec:authorize>
		</c:if>
		<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==3||current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==4||current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==5}">
			&nbsp;
		</c:if>
		<!-- type等于1表示是从教务排课生成对应教学课程 -->
		<a href="${pageContext.request.contextPath}/tcoursesite/createTCourseSite?type=1&courseNo=${current.schoolCourse.courseNo}">生成教学对应课程</a>
		</td>
</c:if>
<c:if test="${rowcodespan==1 }">
	<td>
	   <c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==1}">
			<font color=green>直接排课完成</font>
		</c:if> 
		<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==10}">
		</c:if> 
		<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==10}">

		</c:if> 
		<c:if test="${current.timetableAppointments.size()==0}">
		    <!--课程相关的教师以及主任具有直接排课的权限  -->
		    <sec:authorize ifNotGranted="ROLE_EXCENTERDIRECTOR">
		    <sec:authorize ifAnyGranted="ROLE_TEACHER">
		    <c:if test="${current.user.username==user.username}">
			<a  href='javascript:void(0)' onclick='startTimetable("${current.schoolCourse.courseCode}")'>直接排课</a>
			</c:if>
			</sec:authorize>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR">
			<a  href='javascript:void(0)' onclick='startTimetable("${current.schoolCourse.courseCode}")'>直接排课</a>
			</sec:authorize>
		</c:if>
		<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==3||current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==4||current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==5}">
			&nbsp;
		</c:if>
		<!-- type等于1表示是从教务排课生成对应教学课程 -->
		<a href="${pageContext.request.contextPath}/tcoursesite/createTCourseSite?type=1&courseNo=${current.schoolCourse.courseNo}">生成教学对应课程</a>
	</td>
</c:if>

<!-- 操作 调整排课 -->
<c:if test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td rowspan="${rowcodespan }">
	<c:if test="${current.timetableAppointments.size()==0}">
	    
		    <!--课程相关的教师以及主任具有调整排课的权限  -->
		    <sec:authorize ifNotGranted="ROLE_EXCENTERDIRECTOR">
		    <sec:authorize ifAnyGranted="ROLE_TEACHER">
		    <c:if test="${current.user.username==user.username}">
			<a  href='javascript:void(0)' onclick='doAdjustTimetable("${current.schoolCourse.courseCode }")'>调整排课</a>&nbsp;&nbsp;
			</c:if>
			</sec:authorize>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR">
			<a  href='javascript:void(0)' onclick='doAdjustTimetable("${current.schoolCourse.courseCode }")'>调整排课</a>&nbsp;&nbsp;
			</sec:authorize>
    </c:if> 
	<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==1}">
    </c:if> 
    <c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==2}">
        <c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().status!=10}">
    	<font color=green> 调整排课完成</font>
        </c:if>
        <c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().status==10}">
             <!--课程相关的教师以及主任具有调整排课的权限  -->
            <sec:authorize ifNotGranted="ROLE_EXCENTERDIRECTOR">
		    <sec:authorize ifAnyGranted="ROLE_TEACHER">
		    <c:if test="${current.user.username==user.username}">
			<a href='javascript:void(0)' onclick='doAdjustTimetable("${current.schoolCourse.courseCode }")'><font color=red>正在调课</font>	</a>&nbsp;&nbsp;
			</c:if>
			</sec:authorize>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR">
			<a href='javascript:void(0)' onclick='doAdjustTimetable("${current.schoolCourse.courseCode }")'><font color=red>正在调课</font>	</a>&nbsp;&nbsp;
			</sec:authorize>
        </c:if>
	</c:if>
	<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==3||current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==4||current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==5}">
				&nbsp;
	</c:if>
	</td> 
</c:if>

<c:if test="${rowcodespan==1 }">
	<td>
		<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==1}">
		</c:if> 
		<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==10}">
		</c:if> 
		<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==10}">

		</c:if> 
		<c:if test="${current.timetableAppointments.size()==0}">
		    <!--课程相关的教师以及主任具有调整排课的权限  -->
		    <sec:authorize ifNotGranted="ROLE_EXCENTERDIRECTOR">
		    <sec:authorize ifAnyGranted="ROLE_TEACHER">
		    <c:if test="${current.user.username==user.username}">
			<a  href='javascript:void(0)' onclick='doAdjustTimetable("${current.schoolCourse.courseCode }")'>调整排课</a>&nbsp;&nbsp;
			</c:if>
			</sec:authorize>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR">
			<a  href='javascript:void(0)' onclick='doAdjustTimetable("${current.schoolCourse.courseCode }")'>调整排课</a>&nbsp;&nbsp;
			</sec:authorize>
		</c:if>
		<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==2}">
        <c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().status!=10}">
    	<font color=green> 调整排课完成</font>
        </c:if>
        <c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().status==10}">
             <!--课程相关的教师以及主任具有调整排课的权限  -->
            <sec:authorize ifNotGranted="ROLE_EXCENTERDIRECTOR">
		    <sec:authorize ifAnyGranted="ROLE_TEACHER">
		    <c:if test="${current.user.username==user.username}">
			<a href='javascript:void(0)' onclick='doAdjustTimetable("${current.schoolCourse.courseCode }")'><font color=red>正在调课</font>	</a>&nbsp;&nbsp;
			</c:if>
			</sec:authorize>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR">
			<a href='javascript:void(0)' onclick='doAdjustTimetable("${current.schoolCourse.courseCode }")'><font color=red>正在调课</font>	</a>&nbsp;&nbsp;
			</sec:authorize>
        </c:if>
	</c:if>
		<c:if test="${current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==3||current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==4||current.timetableAppointments.size()>0&&current.timetableAppointments.iterator().next().timetableStyle==5}">
			&nbsp;
		</c:if> 
	</td>
</c:if>

<!-- 操作 查看排课 -->
<c:if test="${rowcodespan>1&&ifCodeRowspan!=current.schoolCourse.courseCode }">
	<td rowspan="${rowcodespan }">
		<c:if test="${current.timetableAppointments.size()==0 }">
			<font color=orange>尚无排课记录</font>
		</c:if> 
		<c:if test="${current.timetableAppointments.size()>0 }">
			<a  href='javascript:void(0)'	onclick='listTimetable("${current.schoolCourse.courseCode }")'>查看排课</a>
		</c:if> &nbsp;
	</td>
	<c:set var="ifCodeRowspan"	value="${current.schoolCourse.courseCode }"></c:set>
</c:if>
<c:if test="${rowcodespan==1 }">
	<td>
	    <c:if	test="${current.timetableAppointments.size()==0 }">
			<font color=orange>尚无排课记录</font>
		</c:if> 
		<c:if test="${current.timetableAppointments.size()>0 }">
			<a href='javascript:void(0)'	onclick='listTimetable("${current.schoolCourse.courseCode }")'>查看排课</a>
		</c:if> &nbsp;
	</td>
	
</c:if>
</tr>
</c:forEach>
</tbody>
<!-- 分页导航 -->

</table>
<a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?currpage=${pageModel.firstPage}&term=${termId }&id=<%=request.getParameter("id") %>" target="_self">首页</a>				    
<a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?currpage=${pageModel.previousPage}&term=${termId }&id=<%=request.getParameter("id") %>" target="_self">上一页</a>
 第
<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
    <option value="${pageContext.request.contextPath}/timetable/selfTimetable/listCourseCodes?currpage=${page}">${page}</option>
    <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
        <c:if test="${j.index!=page}">
        <option value="${pageContext.request.contextPath}/timetable/listTimetableTerm?currpage=${j.index}&term=${termId }&id=<%=request.getParameter("id") %>">${j.index}</option>
        </c:if>
    </c:forEach>
</select>页
<a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?currpage=${pageModel.nextPage}&term=${termId }&id=<%=request.getParameter("id") %>" target="_self">下一页</a>
<a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?currpage=${pageModel.lastPage}&term=${termId }&id=<%=request.getParameter("id") %>" target="_self">末页</a>

<!-- 调整排课 -->
<div id="doAdjust" class="easyui-window" title="调整排课" closed="true"  modal="true"	iconCls="icon-add" style="width:1000px;height:600px"></div>

<!-- 调整排课 -->
<div id="doSearch" class="easyui-window" title="查看排课" closed="true"  modal="true"	iconCls="icon-add" style="width:1000px;height:500px"></div>

<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="查看学生名单" modal="true"	closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

</div>

<div class="pagination_desc" style="float: left">
   共<strong>${totalRecords}</strong> 
   条记录<strong>
   总页数:${pageModel.totalPage}&nbsp;</strong>

</div>
<br>
</div>
</div>
</div>
</div>
</div>
</div>
<!-- 直接排课 -->
<div id="doStart" class="easyui-window" title="直接排课" closed="true" modal="true"	iconCls="icon-add" style="width:850px;height:450px">
<div class="navigation">
<div id="navigation">
	<ul>
		<li><a href="javascript:void(0)">排课管理</a>
		</li>
		<li class="end">
		<a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?id=-1">教务直接排课</a>
		</li>
	</ul>
</div>
</div>
<br>
<form:form id="form_lab" action="" method="post" modelAttribute="timetableAppointment">
当前学期：${schoolTerm.termName} 当前周次：${week }
<!-- schoolCourseDetail的no -->
<hr>
<br>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">

<table border="0" align="center">
<tr>
	<td></td>
	<td></td>
</tr>
	<!--根据cstaticValue的配置选项确定是否可选设备资源  -->
<c:if test="${empty cStaticValue ||cStaticValue.staticValue !='1'}">
	<tr>
	<td align=left>请选择实验室：</td>
	<td>
		<select class="chzn-select" data-placeholder='请选择实验室：' multiple="multiple"	name="labRoom_id" id="labRoom_id" items="${labRoomMap}"	invalidMessage="不能超过30个字符！" style="width:450px"	required="true">
			<c:forEach items="${labRoomMap}" var="entry">
				<option value="${entry.key}">${entry.value}</option>
			</c:forEach>
		</select> 
		<input type="hidden" name="devices" id="devices">
	</td></tr>
	<c:if test="${selected_labCenter==12 }">
	
	<tr>
    	<td align=left>是否允许教学外设备对外开放：</td>
    	<td>
    		<input type="radio" id="allowUse" name="group1" onclick="showDevice()">是</input>
    		<input type="radio" id="forbidUse" name="group1" onclick="showDevice()">否</input>
    	</td>
    </tr>
	
	
	<tr id="labRoomDevices">
	<td align=left>请选择实验室设备：</td>
	<td>
		<select class="chzn-select" data-placeholder='请选择实验室设备：' multiple="multiple"	name="labRoomDevice_id" id="labRoomDevice_id" items=""	invalidMessage="不能超过30个字符！" style="width:450px"	required="true">
			<c:forEach items="" var="entry">
				<option value=""></option>
			</c:forEach>
		</select> 
	</td></tr>
	</c:if>
</c:if>
<c:if test="${!empty cStaticValue &&cStaticValue.staticValue == '1'}">
    <tr>
		<td align=left>&nbsp;</td>
		<td>
    </tr>
	<tr>
	    <td align=left>请选择实验室：</td>
	    <td>
		<select class="chzn-select" data-placeholder='请选择实验室：' name="labRoom_id" id="labRoom_id" items="${labRoomMap}"	invalidMessage="不能超过30个字符！" style="width:450px"	required="true">
			<option value="">请选择</option>
			<c:forEach items="${labRoomMap}" var="entry">
				<option value="${entry.key}">${entry.value}</option>
			</c:forEach>
		</select> </td></tr>
		<tr>
		<td align=left>&nbsp;</td>
		<td>
    </tr>
		<tr>
		<td align=left>请选择实验设备：</td>
	    <td>
		<select class="chzn-select" multiple="multiple" data-placeholder='请选择实验设备：'  name="devices" id="devices" style="width:450px" required="false">
	    </select> </td>
     </tr>
</c:if>

<tr>
	<td align=left>&nbsp;</td>
	<td>
</tr>
<tr>
	<td align=left>请选择上课老师：</td>
	<td>
	<select class="chzn-select"	data-placeholder='请选择上课老师：' multiple="multiple" name="teacherRelated" id="teacherRelated" style="width:450px"	required="true">
		<c:forEach items="${timetableTearcherMap}" var="entry">
			<option value="${entry.key}">${entry.value}</option>
		</c:forEach>
	</select>
</tr>
<tr>
	<td align=left>&nbsp;</td>
	<td>
</tr>
<tr>
	<td align=left>请选择指导老师：</td>
	<td>
    <select class="chzn-select"	data-placeholder='请选择指导老师：' multiple="multiple"	name="tutorRelated" id="tutorRelated" style="width:450px"	required="true" invalidMessage="不能超过50个字符！">
		<c:forEach items="${timetableTearcherMap}" var="entry">
			<option value="${entry.key}">${entry.value}</option>
		</c:forEach>
	</select>
</tr>
<!--如果是纺织学院，则可以配置实验设备资源  -->
<c:if test="${!empty cStaticValue &&cStaticValue.staticValue == '1'}">
<tr>
	<td align=left>请选择指导老师：</td>
	<td>
    <select class="chzn-select"	data-placeholder='请选择指导老师：' multiple="multiple"	name="tutorRelated" id="tutorRelated" style="width:450px"	required="true" invalidMessage="不能超过50个字符！">
		<c:forEach items="${timetableTearcherMap}" var="entry">
			<option value="${entry.key}">${entry.value}</option>
		</c:forEach>
	</select>
</tr>
</c:if>
<tr>
	<td align=left>&nbsp;</td>
	<td>
</tr>

</table>
</div>
</div>
</div>
</div>
</div>

<br>
<br>
<br>
    <input type="button" id="deviceButton" name="deviceButton" value="确定">
	</form:form>
</div>
<div class="right-content">
	<div id="TabbedPanels1" class="TabbedPanels">
		<div class="TabbedPanelsContentGroup1">
			<div class="TabbedPanelsContent">
			<div class="content-box">

</div>
</div>
</div>
</div>
</div>

<!-- 下拉框的js -->
<script	src="${pageContext.request.contextPath}/chosen/chosen.jquery.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/chosen/docsupport/prism.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var config = {
	'.chzn-select' : {
		search_contains : true
	},
	'.chzn-select-deselect' : {
		allow_single_deselect : true
	},
	'.chzn-select-no-single' : {
		disable_search_threshold : 10
	},
	'.chzn-select-no-results' : {
		no_results_text : 'Oops, nothing found!'
	},
	'.chzn-select-width' : {
		width : "95%"
	}
}
for ( var selector in config) {
	$(selector).chosen(config[selector]);
}
</script>
<!-- 下拉框的js -->

</body>
</html>

