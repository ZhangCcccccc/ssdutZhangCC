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

	function checkFileType(){
		var string = $("#studentfile").val();
		if(string==""){
			alert("请下载模板，修改模板内容后上传！");
			return false;
		}else{
			var ss = string.split(".");
			var s = (ss[ss.length-1]).toLowerCase();			
			if(s=="xls"){
				$("#fileName").val(string);
				return true;
			}else{
				alert("请下载模板，修改模板内容后上传！");
				return false;
			}
		}
		
	}

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

//弹出选择学生窗口
function importStudents() {
    var sessionId=$("#sessionId").val();
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#importStudents').window({left:"0px", top:(topPos+20)+"px"}); 
    $("#importStudents").window('open');
}

//ajax查询班级用户列表  
function getSchoolClassesUser(classNumber){ 		
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/teaching/coursesite/getSchoolClassesUser",
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
		url: "${pageContext.request.contextPath}/teaching/coursesite/getSchoolClasses",
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
		           tableStr = tableStr + "<td><a class='btn btn-common' href='javascript:void(0)' onclick=\"getSchoolClassesUser('"+ key +"')\">" + values + "</a></td><tr>";
			   }else  if(currLine%3==1){
			       tableStr = tableStr + "<tr><td><a class='btn btn-common' href='javascript:void(0)' onclick=\"getSchoolClassesUser('"+ key +"')\">" + values + "</a></td>";
			   }
			   else  if(currLine%3==2){
			       tableStr = tableStr + "<td><a class='btn btn-common' href='javascript:void(0)' onclick=\"getSchoolClassesUser('"+ key +"')\">" + values + "</a></td>";
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
	<li><a href="javascript:void(0)">教学管理</a></li>
	<li><a href="javascript:void(0)">站点管理</a></li>
	<li class="end"><a href="javascript:void(0)">编辑站点</a></li>
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
    <div id="title">新建课程站点</div>
    </c:if>
	<c:if test="${flagId!=-1 }">
    <div id="title">编辑课程站点</div>
    </c:if>
</div>
<form:form action="${pageContext.request.contextPath}/teaching/coursesite/saveTCourseSite?flagID=${flagId}" method="post" modelAttribute="tCourseSite" enctype="multipart/form-data">
<fieldset class="introduce-box">
<legend>站点基本信息</legend>
<table id="listTable" width="50%" cellpadding="0">
<tr>
	<td>站点名称</td>
	<td>
	<form:input path="title" id="title" required="true" />
    </td>
	<td>选择专业</td>
	<td>
	<form:select class="chzn-select"  path="schoolMajor.majorNumber" id="schoolMajor_majorNumber" cssStyle="width:400px" >
         <c:if test="${flagId!=-1 }">
          <form:option value="${tCourseSite.schoolMajor.majorNumber}" label="${tCourseSite.schoolMajor.majorName}" />  
         </c:if>
         <c:forEach items="${schoolMajors}" var="curr"  varStatus="i">	

          <form:option value="${curr.key}" label="${curr.value}" />  
         </c:forEach>
    </form:select>
    </td>
</tr>

<tr>
	<td>选择学期</td>
	<td>
	<form:select class="chzn-select"  path="schoolTerm.id" id="schoolTerm_id" cssStyle="width:400px" >
         <c:if test="${flagId!=-1 }">
          <form:option value="${tCourseSite.schoolTerm.id}" label="${tCourseSite.schoolTerm.termName}" />  
         </c:if>
         <c:forEach items="${schoolTerms}" var="curr"  varStatus="i">	

          <form:option value="${curr.id}" label="${curr.termName}" />  
         </c:forEach>
    </form:select>
    
    </td>
	<td>选择课程</td>
	<td>
	<form:select class="chzn-select"  path="schoolCourseInfo.courseNumber" id="schoolCourseInfo_courseNumber" cssStyle="width:200px" required="true" >
         <c:forEach items="${schoolCourses}" var="curr"  varStatus="i">	
         <c:if test="${flagId!=-1 }">
         <form:option value="${tCourseSite.schoolCourseInfo.courseNumber}" selected="selected" label="${tCourseSite.schoolCourseInfo.courseNumber}:${tCourseSite.schoolCourseInfo.courseName}" />  
         </c:if>
         <form:option value="${curr.courseNumber}" label="${curr.courseNumber}:${curr.courseName}"/>  
         </c:forEach>
    </form:select>
</tr>

<tr>
	<td>站点编号 <font color=red>*</font></td>
	<td>
	<c:if test="${flagId==-1 }">
	<form:input path="siteCode" id="siteCode" value="code-${user.schoolAcademy.academyNumber}-${maxId}" required="true" />
	</c:if>
	<c:if test="${flagId!=-1 }">
	<form:input path="siteCode" id="siteCode" value="${tCourseSite.siteCode}" required="true" />
	</c:if>
	</td>
	</td>
	<td>选择教师</td>
	<td>
	<form:select class="chzn-select"  path="userByCreatedBy.username" id="user_username" cssStyle="width:400px">
	<form:options items="${timetableTearcherMap }" />  
	</form:select>
	
	
	</td>
</tr>
<tr>
<td>站点图片:</td>
<td> <img alt="站点图片" src="${pageContext.request.contextPath}/${tCourseSite.siteImage}" height="200px" width="300px"/> 
<input type="file" name="file"> </td>
<td>教师图片:</td>
<td>
<img alt="教师图片" src="${pageContext.request.contextPath}/${tCourseSite.teacherImage}" height="100px" width="150px"/>
<input type="file" name="teacherfile"> </td>
</tr>

<tr>
<td>是否开放:</td>
<td colspan=3>
<c:if test="${flagId==-1 }">
	<form:radiobutton path="isOpen" value="1"  checked="checked" />课程开放
	<form:radiobutton path="isOpen" value="0"  />课程不开放
</c:if>
<c:if test="${flagId!=-1 }">
   <c:if test="${tCourseSite.isOpen==1 }">
		<form:radiobutton path="isOpen" value="1" checked="checked"  />课程开放
		<form:radiobutton path="isOpen" value="0"  />课程不开放
   </c:if>
   <c:if test="${tCourseSite.isOpen!=1 }">
		<form:radiobutton path="isOpen" value="1" />课程开放
		<form:radiobutton path="isOpen" value="0" checked="checked"  />课程不开放
   </c:if>
</c:if>
</td>
</table>
</fieldset>

<fieldset class="introduce-box">
<legend>学生花名册</legend>
<table id="listTable" width="50%" cellpadding="0">
<tr>
    <c:if test="${flagId!=-1 }">
	<td>学生名单</td>
	
	<td>
	<a class="btn btn-common" href='javascript:void(0)'	onclick='importStudents()'>导入学生</a>
	<a class="btn btn-common" href='javascript:void(0)'	onclick='newStudents()'>选择添加</a>
	<a class="btn btn-common" href='${pageContext.request.contextPath}/teaching/coursesite/ListCourseStudents?id=${tCourseSite.id }'>查看已选学生名单</a>
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
	<textarea name="students" id="students" cols="104" /><c:forEach items="${tCourseSite.TCourseSiteUsers}" var="current">
${current.user.username }   </c:forEach></textarea>
	
	</c:if>
	
	</td>

</tr>
<tr>
<td>
   学生人数：${tCourseUser.size()}
   </td>
   </tr>
</table>
</fieldset>
<div>
    <input type="hidden" name="id" value="${tCourseSite.id }">
	<input onclick="history.go(0)"   type="submit" value="提交">&nbsp;&nbsp;
	<%-- <a  class="btn btn-common"  href="${pageContext.request.contextPath}/timetable/selfTimetable/listCourseCodes?currpage=1" >返回列表</a>
 --%></div>
</form:form>

</div>

</div>
</div>
</div>
</div>

<div id="newSchoolCourse" class="easyui-window panel-body panel-body-noborder window-body" title="自建课程" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width: 600px; height:400px;">
</div>

<!-- 弹出选择学生窗口 -->
<div id="newStudents" class="easyui-window" title="选择添加学生" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width:950px; height:800px;">
	<div class="TabbedPanelsContentGroup">
	<div class="TabbedPanelsContent">
	
	<div class="content-box">
	<form:form action="" method="post">
	<fieldset class="introduce-box">
         <legend>年级信息</legend>
         <div>
         <table id="listTable" width="85%" cellpadding="0">
          <tr><td><b>选择年级</b></td></tr>
          <tr>
         	<td>
         	<c:forEach items="${grade }" var="s" varStatus="i">
         	<c:if test="${s.grade>2010 }">
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

<!-- 弹出导入学生窗口 -->
<div id="importStudents" class="easyui-window" title="导入学生" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width:950px; height:500px;">
	<form:form action="${pageContext.request.contextPath}/teaching/coursesite/importStudents" enctype="multipart/form-data" onsubmit="return checkFileType()">
		<br>
		<p><b>导入学生（xls）</b></p>
		<hr>
		<br>
		<input type="file" name="file" id="studentfile" required="required"/>
		<input type="submit" value="提交">
		<br>下载：<a href="${pageContext.request.contextPath}/pages/model/teaching/coursesite/importStudents.xls">下载模板</a><br><br>
			范例：<br>
			<img src="${pageContext.request.contextPath}/images/model/teaching/coursesite/importStudents.png" heigth="20%" width="70%" /><br>
			说明：<br>
			下载模板，照模板格式填写数据，进行上传。<br>
			第一列是学生学号；<br>
			第二列是学生姓名。<br><br>
			学生初始密码为6个1：111111。
			
		</form:form>
	
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