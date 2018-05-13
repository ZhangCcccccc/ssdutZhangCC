<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />

<!-- 打印开始 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<!-- 打印结束 -->

<!-- 打印、导出组件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/LodopFuncs.js"></script>

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
<script type="text/javascript">
var array=new Array();
function batchAlterGroupStudents(){
    var flag; //判断是否一个未选   
    $("input[name='VoteOption1']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
        if ($(this).is(':checked')) { //判断是否选中    
            flag = true; //只要有一个被选择 设置为 true  
        } 
    })  
  if (flag) {  
     $("input[name='VoteOption1']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                  if ($(this).is(':checked')) { //判断是否选中
                      array.push($(this).val()); //将选中的值 添加到 array
                  }  
              })  
    //window.location.href="${pageContext.request.contextPath}/device/saveTrainSigleResultRest"; 
  //获取当前屏幕的绝对位置
	var topPos = window.pageYOffset;
    $('#batchAlterGroupStudents').window({left:"0px", top:topPos+"px"}); 
    $("#batchAlterGroupStudents").window('open');
    } else {   
    	alert("请至少选择一条记录");  
    	}  
	}

//批量调整学生分组
function moveGroup(){
	var s=[];
    $($("#group option:selected")).each(function(){
	  s.push($(this).val());
    });
	if(s[0]==${id}){alert("不能调整至当前分组，请重新选择！")}
	else{
	 	window.location.href="${pageContext.request.contextPath}/timetable/batchAlterGroupStudents?targetGroupId="+s[0]+"&array="+array;
		
	}
}
//添加分组学生
function addGroupStudents(){
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/appointment/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	           		$(user_body).html(data);
	           }
	});
    $("#addGroupStudents").show();
    var topPos = window.pageYOffset+50;
    $("#addGroupStudents").window({top:topPos+"px"});   
    $("#addGroupStudents").window('open');   
    
 }
 
 //打印功能
 $(document).ready(function(){
      $('#fullview').click(function(){
           $('.sit_sider_bar').animate( { width:'0'}, 500 );
           $('.sit_maincontent').animate( { width:'100%'}, 500 );
           $('.toggle,.toggleLink,#fullview,.sit_footer,.sit_sider_bar > h3').css("display","none");
           $('#fullview1').css("display","inline");
      });
  
      $('#fullview1').click(function(){
           $('.sit_sider_bar').animate( { width:'23%'}, 500 );
           $('.sit_maincontent').animate( { width:'75%'}, 500 );
           $('#fullview1').css("display","none");
           $('.toggle,#fullview,.toggleLink,.sit_footer,.sit_sider_bar > h3 ').css("display","inline");
      });
      
      $('#myPrint').click(function(){
           printPreview();
      });
});
 
//打印预览
	function printPreview(){
		LODOP=getLodop();  
		var strStyleCSS = "<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/css/style.css'>";
		    strStyleCSS +="<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/timetable/lmsReg.css'>";
		var strHtml = strStyleCSS+"<body>"+$("#myShow").html()+"</body>";
		LODOP.PRINT_INIT("");
		LODOP.SET_PRINT_STYLE("FontSize",18);  //字体大小
		LODOP.SET_PRINT_STYLE("Bold",1);  //是否粗体，1是，0否
		LODOP.ADD_PRINT_HTM(30,30,"RightMargin:30","BottomMargin:50",strHtml);
		LODOP.PREVIEW();
	}
	
//导出
var LODOP; //声明为全局变量 
var fileName;
	//导出excel文件  
	function SaveAsFile(){ 
	    fileName="${groupName }"+"学生名单.xls";
		LODOP=getLodop();   
		LODOP.PRINT_INIT(""); 
		LODOP.ADD_PRINT_TABLE(0,0,"100%","100%",$("#myShow").html()); 
		LODOP.SET_SAVE_MODE("Orientation",2); //Excel文件的页面设置：横向打印   1-纵向,2-横向;
		LODOP.SET_SAVE_MODE("PaperSize",9);  //Excel文件的页面设置：纸张大小   9-对应A4
		LODOP.SET_SAVE_MODE("Zoom",90);       //Excel文件的页面设置：缩放比例
		LODOP.SET_SAVE_MODE("CenterHorizontally",true);//Excel文件的页面设置：页面水平居中
		LODOP.SET_SAVE_MODE("CenterVertically",true); //Excel文件的页面设置：页面垂直居中
//		LODOP.SET_SAVE_MODE("QUICK_SAVE",true);//快速生成（无表格样式,数据量较大时或许用到） 
		LODOP.SET_SHOW_MODE("NP_NO_RESULT",true);  //解决chrome弹出框问题
		LODOP.SAVE_TO_FILE(fileName); 
	};
</script>
</head>
<body>
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?id=-1">查看学生选课情况</a></li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
	<div class="title">
	  <div id="title">学生选课列表
	  <c:if test="${timetableStyle eq 4}">
	 	<font color="blue"> 二次排课分组</font>
	  </c:if>
	  <c:if test="${timetableStyle eq 6}">
	  	<font color="blue"> 自主排课分组</font>
	  </c:if>
	  ;${groupName }</div>
	  <a class="btn btn-new" onclick="addGroupStudents();">添加学生</a>
	  <a class="btn btn-new" onclick="batchAlterGroupStudents();">调整分组</a>
	  <a class="btn btn-new" id="myPrint">打印</a>
	  <a class="btn btn-new" onclick="SaveAsFile();">导出</a>
	</div>
	
<div id="myShow">	
<table valign="center" cellpadding="5" cellspacing="0" align="center" width="100%" style="word-wrap:break-all"> 
<thead>
<tr>
  	<th>选择</th>
   <th>序号</th>
   <th>选课组编号</th>
   <th>课程名称</th>
   <th>学生编号</th>
   <th>学生姓名</th>
   <th>授课教师</th>
   <th>学院名称</th>
   <th>操作</th>
   
</tr>
</thead>
<tbody>
<c:forEach items="${studentMap}" var="current"  varStatus="i">	
<tr>
	<td><input type='checkbox' name='VoteOption1' value="${current.id}"></td>
     <td>${i.count}</td>
     <td>
       <!-- 去重显示选课组编号 -->
       <c:set var="courseCode" value=""></c:set>
        <c:forEach var="entry" items="${current.timetableGroup.timetableAppointments}">  
           <c:if test="${courseCode!=entry.courseCode }">
             <c:set var="courseCode" value="${entry.courseCode}"></c:set>
             ${entry.courseCode}
          </c:if>
        </c:forEach>
      
     </td>
     <td><!-- 去重显示课程名称 -->
         <c:set var="courseName" value=""></c:set>
         <c:forEach var="entry" items="${current.timetableGroup.timetableAppointments}">  
          <c:if test="${courseName!=entry.schoolCourseInfo.courseName }">
             <c:set var="courseName" value="${entry.schoolCourseInfo.courseName}"></c:set>
             ${entry.schoolCourseInfo.courseName}
          </c:if>
        </c:forEach></td>
     <td>${current.user.username}</td>
     <td>${current.user.cname}</td>
     <td>
         <c:forEach var="entry1" items="${timetableAppointment.timetableTeacherRelateds}">  
	          <c:out value="${entry1.user.cname}" />  
	     </c:forEach>
     </td> 
     <td>
        ${current.user.schoolAcademy.academyName }
     </td>
     <td>
	     <a onclick="return confirm('确定删除？')" href="${pageContext.request.contextPath}/timetable/deleteGroupStudent?id=${current.id}">删除</a>
     </td>
</tr>
</c:forEach> 
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
</div>
</div>


<div id="batchAlterGroupStudents" class="easyui-window " title="批量调整" align="center" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 300px; height: 300px;">
	<form action="" method="post">
		<table>
			<tr><td>分组调整</td></tr>
			<tr>
				<td>请选择：
					<select id="group" name="group" class="chzn-select" >
					<option value="">请选择</option>
					<c:forEach items="${groups}" var="g">
					<option value="${g.id}">${g.groupName}</option>
					</c:forEach>
					</select>		    				            
				</td>
			</tr>
			<tr>
				<td><input type="button" value="提交" onclick="moveGroup();"> </td>
				<td><input type="button" value="取消"> </td>
			</tr>
		</table>
	</form>
</div>


<!-- 批量添加分组学生 -->

<div id="addGroupStudents" class="easyui-window " title="添加分组学生" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 600px; height: 400px;">
		<div class="content-box">
		<form:form id="userForm" method="post"   modelAttribute="admin">
			<table class="tb" id="my_show">
				<tr>
					<td>姓名：<form:input id="cname" path="user.cname"/></td>
					<td>学号：<form:input id="username" path="user.username"/>
						<a onclick="queryUser();" >搜索</a>	
					</td>
					<td>
						<input type="button" value="添加" onclick="addUser();">
					</td>
				</tr>
			</table>
		</form:form>
		
		<table id="my_show">
					<thead>
						<tr>
							<th style="width:10% !important">选择</th>
							<th style="width:30% !important">姓名</th>
							<th style="width:30% !important">学号</th>
							<th style="width:30% !important">所属院系</th>
							
						</tr>
					</thead>
						
					<tbody id="user_body">
						
					</tbody>
					
			</table>
			</div>
<script type="text/javascript">
function addUser(){
        var array=new Array();
        var flag; //判断是否一个未选   
        $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为CK_name的 checkbox  
                    if ($(this).attr("checked")) { //判断是否选中    
                        flag = true; //只要有一个被选择 设置为 true  
                    }  
                })  

        if (flag) {  
           $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                        if ($(this).attr("checked")) { //判断是否选中
                            array.push($(this).val()); //将选中的值 添加到 array中 
                        }  
                    })  
           //alert(array);         
           //将要所有要添加的数据传给后台处理   
		   window.location.href="${pageContext.request.contextPath}/timetable/addGroupStudents?array="+array+"&id="+${id}; 
        } else {   
        	alert("请至少选择一条记录");  
        	}  
    	}

function queryUser(){
	
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/appointment/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  $(user_body).html(data);
	            
	           }
	});
	  
}

 //取消查询
function Cancel(){
	document.getElementById("cname").value="";
	document.getElementById("username").value="";
	var cname="";
	var username="";
	$.ajax({
	           url:"${pageContext.request.contextPath}/appointment/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  $(user_body).html(data);
	            
	           }
	         });
} 

//首页
function firstPage(page){
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/appointment/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  $(user_body).html(data);
	            
	           }
	});
}
//上一页
function previousPage(page){
	if(page==1){
			page=1;
		}else{
			page=page-1;
		}	
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	          url:"${pageContext.request.contextPath}/appointment/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  $(user_body).html(data);
	            
	           }
	});
}
//下一页
function nextPage(page,totalPage){
	if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1
		}	
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/appointment/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  $(user_body).html(data);
	           }
	});
}
//末页
function lastPage(page){
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/appointment/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  $(user_body).html(data);
	            
	           }
	});
}
</script>
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