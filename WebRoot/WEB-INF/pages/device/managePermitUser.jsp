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
<!-- 添加学生对应js -->
<script type="text/javascript">
function addStudent(page){
   var page=page;
   var cname=document.getElementById("cname").value;
   var username=document.getElementById("usernameStudent").value;
   $.ajax({
              url:"${pageContext.request.contextPath}/device/findStudentByCnameAndUsername?cname="+cname+"&username="+username+"&page="+page+"&deviceId="+${deviceId},
              type:"POST",
              success:function(data){//AJAX查询成功
                   //$(user_body).html(data);
                   $(user_body).html(data);
              }
   });
    $("#addStudent").show();
    $("#addStudent").window('open');   
    
 }

function addUser(){
       var usernameStr="";
        var flag; //判断是否一个未选   
        $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为CK_name的 checkbox  
                    if ($(this).attr("checked")) { //判断是否选中    
                        flag = true; //只要有一个被选择 设置为 true  
                    }  
                })  

        if (flag) {  
           $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                        if ($(this).attr("checked")) { //判断是否选中
                           usernameStr+=$(this).val()+"S";
                        }  
                    })  
                    
           //将要所有要添加的数据传给后台处理   
          window.location.href="${pageContext.request.contextPath}/device/savePermitUser?usernameStr="+usernameStr+"&deviceId="+${deviceId}; 
        } else {   
           alert("请至少选择一条记录");  
           }  
       }
//取消查询
function Cancel(){
   document.getElementById("cname").value="";
   document.getElementById("usernameStudent").value="";
   var cname="";
   var username="";
   $.ajax({
              url:"${pageContext.request.contextPath}/device/findStudentByCnameAndUsername?cname="+cname+"&username="+username+"&page=1"+"&deviceId="+${deviceId},
              type:"POST",
              success:function(data){//AJAX查询成功
                     //$(user_body).html(data);
                     $(user_body).html(data);
              }
            });
}


//跳转
function targetUrl(url)
{
  document.queryForm.action=url;
  document.queryForm.submit();
}

</script>

</head>
<body>
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li class="end"><a href="javascript:void(0)">已通过学生管理</a></li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">
	<div class="title">已通过学生列表
	<a class="btn btn-new" onclick="addStudent(1);">添加培训名单</a>
	</div>
</div>

<div class="tool-box">
	<!-- 查找框  -->
		<form:form name="queryForm" action="" method="post" modelAttribute="student">
			 <ul>
  				
  				<li><input type="hidden" value="查询"/>
			      <input type="hidden" value="取消" onclick="cancel();"/></li>
  				</ul>
		</form:form>
</div>

<table> 
<thead>
	<tr>
		<th>学号</th>
		<th>姓名</th>
		<th>身份</th>
		<th>所属学院</th>
		<th>通过途径</th>
		<th>操作</th>
	</tr>
</thead>
<tbody>

<c:forEach items="${students}" var="student">
	<tr>
		<td>${student.user.username}</td>
		<td>${student.user.cname}</td>
		<td>
			<c:if test="${student.user.userRole==0 }">
			学生
			</c:if>
			<c:if test="${student.user.userRole==2 }">
			研究生
			</c:if>
		</td>
		<td>${student.user.schoolAcademy.academyName }</td>
		<td>
			<c:choose>
				<c:when test="${student.flag eq 2 }">培训通过</c:when>
				<c:when test="${student.flag eq 3 }">手动添加</c:when>
			</c:choose>
		</td>
		<td>
			<a onclick="return confirm('删除以后不能恢复，真的要删除吗？')" href="${pageContext.request.contextPath }/device/deletePermitUser?id=${student.id}">删除</a>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
<div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/managePermitUser?deviceId=${deviceId}&currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/managePermitUser?deviceId=${deviceId}&currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option selected="selected" value="${pageContext.request.contextPath}/device/managePermitUser?deviceId=${deviceId}&currpage=${pageModel.currPage}">${pageModel.currPage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currPage}">
    <option value="${pageContext.request.contextPath}/device/managePermitUser?deviceId=${deviceId}&currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/managePermitUser?deviceId=${deviceId}&currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/managePermitUser?deviceId=${deviceId}&currpage=${pageModel.lastPage}')" target="_self">末页</a>
</div>
</div>
</div>
</div>
</div>
</div>
</div>

   <!-- 添加学生 -->
   
   <div id="addStudent" class="easyui-window " title="添加培训人员" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 600px; height: 500px;">
       <div class="content-box">
       <form:form id="userForm" method="post"   modelAttribute="student">
           <table class="tb" id="my_show">
               <tr>
                   <td>姓名：<form:input id="cname" path="user.cname"/></td>
                   <td>工号：<form:input id="usernameStudent" path="user.username"/>
                       
                   
                       <a onclick="addStudent(1);">搜索</a> 
                       <a onclick="Cancel();" >取消搜索</a>    
                       
                       
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
                           <th style="width:30% !important">工号</th>
                           <th style="width:30% !important">所属学院</th>
                           
                       </tr>
                   </thead>
                       
                   <tbody id="user_body">
                       
                   </tbody>
                   
           </table>
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


<script type="text/javascript">
					$(".btn-edit").click(function(){
						//$(".btn-edit").slideUp(); //原信息隐藏
						$(this).hide();//修改按钮隐藏
						$(".edit-edit").slideDown();//修改信息显示
					});
					$(".btn-return").click(function(){
						$(".edit-edit").slideUp();
						$(".btn-edit").slideDown();//修改信息显示
						//window.location.reload();//刷新页面
					})
</script>
</body>
</html>