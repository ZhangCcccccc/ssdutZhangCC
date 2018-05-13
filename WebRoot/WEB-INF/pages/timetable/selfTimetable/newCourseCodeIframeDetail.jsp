<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<fmt:setBundle basename="bundles.projecttermination-resources" />
<jsp:useBean id="now" class="java.util.Date" /> 
<html>
<head>
<meta name="decorator" content="iframe" />
<title><fmt:message key="html.title" /></title>
<!-- <meta name="decorator" content="iframe"/> -->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>

<!-- 下拉框的样式 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->

<%--  <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery-1.8.3.min.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/thezzmes/icon.css" type="text/css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" />
<script type="text/javascript">

$(document).ready(function(){
$("#schoolCourse_courseNo").chosen().change(function(){

$("#courseCode").val($("#schoolCourse_courseNo").val()+ "-" + ${maxId});;

});
}); 

//弹出排课界面的方法
function newSchoolCourse() {
    var sessionId=$("#sessionId").val();
    var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/selfTimetable/newSelfSchoolCourseInfo?courseNumber=-1" style="width:100%;height:100%;"></iframe>'
    $("#newSchoolCourse").html(con); 
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#newSchoolCourse').window({left:"0px", top:topPos+"px"}); 
    $("#newSchoolCourse").window('open');
}
$(function(){
    $("#newSchoolCourse").window({
    onBeforeClose:function (){ //当面板关闭之前触发的事件
              if (confirm('窗口正在关闭，请确认您当前的操作已保存。\n 是否继续关闭窗口？')) {
                    window.location.reload();
                    $('#newSchoolCourse').window('close', true); 
                    //这里调用close 方法，true 表示面板被关闭的时候忽略onBeforeClose 回调函数。
              }else{
                    return false;
              } 
              
         }      
    })
    $("#newSchoolCourse").window({
        top: 0 ,
        left: 0  
    })
})


//弹出选择学生窗口
function newStudents() {
    var sessionId=$("#sessionId").val();
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#newStudents').window({left:"0px", top:topPos+"px"}); 
    $("#newStudents").window('open');
}

//ajax查询班级用户列表  
function getSchoolClassesUser(classNumber){ 		
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/timetable/selfTimetable/getSchoolClassesUser",
		data: {'classNumber':classNumber},
		dataType:'json',
		success:function(data){
			var jslength=1;
			var currLine=1;
			var allLine=1;
			for(var js2 in data){jslength++;}
			if(jslength==0){alert("本周无课程数据");}else{}

			var tableStr="<table id='listTable' width='80%' cellpadding='0'><tr><td><b>选择学生</b></td><td colspan=3><a class='btn btn-common' href='javascript:void(0)' onclick='putSchoolClassesUser()'>提交</a></td></tr>";//新建html字符
			$.each(data,function(key,values){  
			   if(currLine%4==0){
		           tableStr = tableStr + "<td><input name='username' id='username" + allLine + "' type='checkbox' value='" + key + "' checked='checked' />" + key + "：" + values + "</a></td><tr>";
			   }else  if(currLine%4==1){
			       tableStr = tableStr + "<tr><td><input name='username' id='username" + allLine + "' type='checkbox' value='" + key + "' checked='checked' />" + key + "：" + values + "</a></td>";
			   }else  if(currLine%4==2){
			       tableStr = tableStr + "<td><input name='username' id='username" + allLine + "' type='checkbox' value='" + key + "' checked='checked' />" + key + "：" + values + "</a></td>";
			   }else if(currLine%4==3){
			       tableStr = tableStr + "<td><input name='username' id='username" + allLine + "' type='checkbox' value='" + key + "' checked='checked' />" + key + "：" + values + "</a></td>";
			   }
			   //currLine=currLine%4;
			   jslength=jslength+1;
			   currLine = currLine +1;
			   allLine =allLine+1;
			 }); 
			 if(currLine%4==0){
			   tableStr = tableStr + "</table>";
			 }else if(currLine%4==1){
			   tableStr = tableStr + "<td>&nbsp;</td><td>&nbsp;</td><td&nbsp;></td></tr></table>";
			 }else if(currLine%4==2){
			   tableStr = tableStr + "<td>&nbsp;</td><td>&nbsp;</td></tr></table>";
			 }else if(currLine%4==3){
			   tableStr = tableStr + "<td>&nbsp;</td></tr></table>";
			 }
			
			 document.getElementById('schoolClassesUser').innerHTML=tableStr; 	
		},
		error:function(){
			//alert("加载课表失败!");
			}
	});
}

//ajax查询用户的班级表  
function getSchoolClasses(grade){ 		
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/timetable/selfTimetable/getSchoolClasses",
		data: {'grade':grade},
		dataType:'json',
		success:function(data){
			var jslength=1;
			var currLine=1;
			for(var js2 in data){jslength++;}
			if(jslength==0){alert("本周无课程数据");}else{}
			var tableStr="<table id='listTable' width='80%' cellpadding='0'><tr><td colspan=3><b>选择班级</b></td></tr>";//新建html字符
			$.each(data,function(key,values){  
			   if(currLine%3==0){
		           tableStr = tableStr + "<td><a class='btn btn-common' href='javascript:void(0)' onclick='getSchoolClassesUser("+ key +")'>" + values + "</a></td><tr>";
			   }else  if(currLine%3==1){
			       tableStr = tableStr + "<tr><td><a class='btn btn-common' href='javascript:void(0)' onclick='getSchoolClassesUser("+ key +")'>" + values + "</a></td>";
			   }
			   else  if(currLine%3==2){
			       tableStr = tableStr + "<td><a class='btn btn-common' href='javascript:void(0)' onclick='getSchoolClassesUser("+ key +")'>" + values + "</a></td>";
			   }
			   currLine=currLine+1
			   jslength=jslength+1;
			 }); 
			 tableStr = tableStr + "</tr></table>";
			 document.getElementById('schoolClasses').innerHTML=tableStr; 	
		},
		error:function(){
			//alert("加载课表失败!");
			}
	});
}

//ajax查询班级用户列表  
function putSchoolClassesUser(){ 		
	 var obj = document.getElementsByName("username");
	 var s='';//如果这样定义var s;变量s中会默认被赋个null值
     for(var i=0;i<obj.length;i++){
         if(obj[i].checked) //取到对象数组后，我们来循环检测它是不是被选中
         s+=obj[i].value+'\n';   //如果选中，将value添加到变量s中    
     }
     var str = $('#students').val() +'\n' +s;
     $('#students').val(str);
     $("#newStudents").window('close');
}
</script>

</head>
<body>
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li><a href="javascript:void(0)">自主排课管理</a></li>
	<li class="end"><a href="javascript:void(0)">新建选课组</a></li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">
    <c:if test="${flagId==-1 }">
    <div id="title">新建选课组</div>
    </c:if>
	<c:if test="${flagId!=-1 }">
    <div id="title">编辑选课组</div>
    </c:if>
</div>
<form:form action="${pageContext.request.contextPath}/timetable/selfTimetable/saveTimetableSelfCourse?flagID=${flagId}" method="post" modelAttribute="timetableSelfCourse">
<fieldset class="introduce-box">
<legend>选课组基本信息</legend>
<table id="listTable" width="50%" cellpadding="0">
<tr>
	<td>选择学期</td>
	<td>
	<form:select class="chzn-select"  path="schoolTerm.id" id="schoolTerm.id" cssStyle="width:400px" >
         <c:if test="${flagId!=-1 }">
          <form:option value="${timetableSelfCourse.schoolTerm.id}" label="${timetableSelfCourse.schoolTerm.termName}" />  
         </c:if>
         <c:forEach items="${schoolTerms}" var="curr"  varStatus="i">	

          <form:option value="${curr.id}" label="${curr.termName}" />  
         </c:forEach>
    </form:select>
    
    </td>
	<td>选择课程</td>
	<td>
	<form:select class="chzn-select"  path="schoolCourseInfo.courseNumber" id="schoolCourseInfo_courseNumber" cssStyle="width:200px" >
         <c:forEach items="${schoolCourses}" var="curr"  varStatus="i">	
         <c:if test="${flagId!=-1 }">
         <form:option value="${timetableSelfCourse.schoolCourseInfo.courseNumber}" selected="selected" label="${timetableSelfCourse.schoolCourseInfo.courseNumber}:${timetableSelfCourse.schoolCourseInfo.courseName}" />  
         </c:if>
         <form:option value="${curr.courseNumber}" label="${curr.courseNumber}:${curr.courseName}"/>  
         </c:forEach>
    </form:select>
    <a class="btn btn-common" href='javascript:void(0)'	onclick='newSchoolCourse()'>新建课程</a>
</tr>

<tr>
	<td>选课组编号 <font color=red>*</font></td>
	<td>
	<c:if test="${flagId==-1 }">
	<form:input path="courseCode" id="courseCode" value="code-${user.schoolAcademy.academyNumber}-${maxId}" required="true" readonly="true"/>
	</c:if>
	<c:if test="${flagId!=-1 }">
	<form:input path="courseCode" id="courseCode" value="${timetableSelfCourse.courseCode}" required="true" readonly="true"/>
	</c:if>
	</td>
	</td>
	<td>选择教师</td>
	<td>
	<form:select class="chzn-select"  path="user.username" id="user_username" cssStyle="width:400px">
	<c:if test="${flagId!=-1 }">
	<form:option value="${timetableSelfCourse.user.username}" selected="selected" label="${timetableSelfCourse.user.username}:${timetableSelfCourse.user.cname}" />  
	</c:if>
	<form:options items="${timetableTearcherMap }" />  
	</form:select>
	
	
	</td>
</tr>
<tr>
<td>选课人数：<font color=red>*</font></td>
<td><form:input path="courseCount" id="courseCount" cssStyle="width:50px" required="true" placeholder="仅可输入数字" class="easyui-numberbox" /></td>
<td></td>
<td></td>
</tr>
</table>
</fieldset>

<fieldset class="introduce-box">
<legend>学生花名册</legend>
<table id="listTable" width="50%" cellpadding="0">
<tr>
    <c:if test="${flagId!=-1 }">
	<td>学生名单</td>
	<td><a class="btn btn-common" href='javascript:void(0)'	onclick='newStudents()'>选择添加</a>
	</td>
	</c:if>
	<c:if test="${flagId==-1 }">
	<td colspan=2><font color=red>提交保存后，进行学生选课名单添加。</font></td>
	</c:if>
</tr>
<tr>
	<td colspan=2>
	<%request.setAttribute("n","\n");%>
	<c:if test="${flagId!=-1 }">
	<textarea name="students" id="students" cols="104" /><c:forEach items="${timetableSelfCourse.timetableCourseStudents}" var="current">
	${current.user.username }</c:forEach></textarea>
	</c:if>
	</td>
</tr>
</table>
</fieldset>
<div>
	<input type="submit" value="提交">&nbsp;&nbsp;
	<a  class="btn btn-common"  href="${pageContext.request.contextPath}/timetable/selfTimetable/listCourseCodes?currpage=1" >返回列表</a>
</div>
</form:form>

</div>

</div>
</div>
</div>
</div>

<div id="newSchoolCourse" class="easyui-window panel-body panel-body-noborder window-body" title="自建课程" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width: 800px; height:400px;">
</div>

<!-- 弹出选择学生窗口 -->
<div id="newStudents" class="easyui-window" title="选择添加学生" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width:800px; height:600px;">
	<div class="TabbedPanelsContentGroup">
	<div class="TabbedPanelsContent">
	
	<div class="content-box">
	<form:form action="" method="post">
	<fieldset class="introduce-box">
         <legend>年级信息</legend>
         <div>
         <table id="listTable" width="85%" cellpadding="0">
          <tr><td><b>选择年级</b></td><tr>
          <tr>
         	<td>
         	<c:forEach items="${grade }" var="s" varStatus="i">
         	<c:if test="${s.grade>'2010' }">
			 <a class='btn btn-common' href='javascript:void(0)' onclick='getSchoolClasses(${s.grade})' >${s.grade}</a>	
			</c:if>
			</c:forEach></td>
         </tr>
         </table>
         </div>
         <div id="schoolClasses"></div>
         <div id="schoolClassesUser"></div>
	</fieldset>
	</form:form>
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
<!-------------列表结束----------->
</html>