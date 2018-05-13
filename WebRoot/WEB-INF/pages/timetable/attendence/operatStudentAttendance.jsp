<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />



<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	

<script>
//修改考勤
function editStudentAttendance(id){
var url="${pageContext.request.contextPath}/timetable/editStudentAttendance?id="+id;
var con = "<iframe scrolling='yes' id='message' frameborder='0'  src='"+url+"' style='width:100%;height:100%;''></iframe>";
$('#doSearchStudents').html(con);  
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#doSearchStudents').window({left:"0px", top:topPos+"px"});  
$('#doSearchStudents').window('open');
}

function check(){
$("#alls").attr("value","");
var c=document.getElementById("s").getElementsByTagName("input");  
var a=$("#VoteOption1s").val();
     var f="";
       for(var i=0;i<c.length;i++){
       
          if(c[i].type=="checkbox"){  
           if(a==1){
             $("#a"+c[i].value+"").attr("checked",true);
             f+=c[i].value+",";
              $("#VoteOption1s").attr("value",2);
          } 
           if(a==2){
             $("#a"+c[i].value+"").attr("checked",false);
              $("#VoteOption1s").attr("value",1);
          }   
         
          }
      }
      $("#alls").attr("value",f);
      
}
function checks(){
$("#alls").attr("value","");
var c=document.getElementById("s").getElementsByTagName("input");  

     var f="";
       for(var i=0;i<c.length;i++){
       
          if(c[i].type=="checkbox" && c[i].checked==true){  
          
             $("#a"+c[i].value+"").attr("checked",true);
             f+=c[i].value+",";
              $("#VoteOption1s").attr("value",2);
          
          }
      }
      $("#alls").attr("value",f);
      


}

function updateScore(id,score){
	window.location.href="${pageContext.request.contextPath}/timetable/saveAttendanceScore?id="+id+"&score="+score;
	
}
</script>

</head>
<body>
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<!-- <li><a href="javascript:void(0)">考勤管理</a></li> -->
	<li><a href="javascript:void(0)">查看学生本课（第${week}周）考勤情况${attendancetableByWeek.size()}</a></li>
</ul>
</div>
</div>

<!-- 学生名单 -->
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">
	<div id="title">全选<input type="checkbox" value="1" id="VoteOption1s" onclick="check()"></input></div>
	
	<form:form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/quickAttendance?&id=${id}&idKey=${week}&courseDetailNo=${courseDetailNo}&flag=${flag}">
	<c:if test="${timeMark==true}">
	<input type="submit" value="快速考勤" class="btn btn-edit">
	</c:if>
	<input type="hidden" id="alls" name="alls"> 
	</form:form>
</div>

<table id="s"> 
<thead>
<tr>
   <th>选择</th>
   <th>序号</th>
   <th>学号</th>
   <th>姓名</th>
   <th>考勤机考勤</th>
   <th>手动考勤</th>
   <th>成绩</th>
   <th>考勤时间</th>
   <th>备注</th>
   <th>操作</th>
</tr>
</thead>
<tbody>
<c:forEach items="${attendancetableByWeek}" var="current"  varStatus="i">
<tr>

    <td><input type="checkbox" onclick="checks();"  value="${current.id}" id="a${current.id}"></td>
    <th>${i.count}</th>
    <td>${current.userByUserNumber.username} <input type="hidden" name="username" value="${current.userByUserNumber.username}"/> </td>
    <td>${current.userByUserNumber.cname}</td>
     
     <td>
     <c:if test="${current.attendanceMachine==1 }">
      √
     </c:if>
     <c:if test="${current.attendanceMachine==0||current.attendanceMachine==null}">
      ×
     </c:if>
     </td>
     
     <td>
     <c:if test="${current.actualAttendance==1 }">
      √
     </c:if>
     <c:if test="${current.actualAttendance==0||current.actualAttendance==null}">
      ×
     </c:if>
     </td>
     <td><input type="text" name="score" value="${current.score}" onchange="updateScore(${current.id},this.value);"/> </td>
     <td><fmt:formatDate value="${current.attendDate.time}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
     
     <td>${current.memo}</td>
    
    <td> 
    <c:if test="${timeMark==true}">
    <a href='${pageContext.request.contextPath}/timetable/saveManualAttendance?id=${current.id}'>手动考勤</a>&nbsp;&nbsp;
     <a href='javascript:void(0)' onclick="editStudentAttendance(${current.id});">修改考勤</a>
     </c:if>
     <c:if test="${timeMark==false}">
    	时间未到,不允许考勤！
    </c:if>
     </td>
     
     
</tr>
</c:forEach> 
</tbody>
</table>

<form:form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/listTimetableTerm" modelAttribute="schoolCourseDetail">

</form:form>

</div>
</div>
</div>
</div>
</div>
</div>

<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="修改学生考勤" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
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
    };
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
</script>
<!-- 下拉框的js -->
</body>
</html>