<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>


<html>
<head>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>

 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script> 

 <script type="text/javascript">
  $(function(){
     $("#print").click(function(){
	$("#my_show").jqprint();
	});
	$("#lab_name").focus();
	$("#lab_name").bind('keydown', function (e) {

            var key = e.which;

            if (key == 13) {

                e.preventDefault();
              document.form.action="${pageContext.request.contextPath}/selectLabList";
	           document.form.submit();

            }

        });
  });


	//首页
	function first(url){
		document.queryForm.action=url;
		document.queryForm.submit();
	}
	//末页
	function last(url){
		document.queryForm.action=url;
		document.queryForm.submit();
	}
	//上一页
	function previous(url){
		var page=${page};
		if(page==1){
			page=1;
		}else{
			page=page-1;
		}
		//alert("上一页的路径："+url+page);
		document.queryForm.action=url+page;
		document.queryForm.submit();
	}
	//下一页
	function next(url){
		var totalPage=${pageModel.totalPage};
		var page=${page};
		if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1
		}
		//alert("下一页的路径："+page);
		document.queryForm.action=url+page;
		document.queryForm.submit();
	}
	
	function cancel(){
		window.location.href="${pageContext.request.contextPath}/tcoursesite/listUserAuthorityDetail?Id=${Id}&page=1";
	}
 </script>
 
<script>
function newUserPeople(){
   var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	var academy=document.getElementById("academy").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/tcoursesite/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&academy="+academy+"&authorityId="+${authority.id}+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	});
	
   $('#doSearchStudents').window('open');
    }
//添加
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
		   //window.location.href="${pageContext.request.contextPath}/appointment/saveLabRoomAdmin?roomId="+${labRoom.id}+"&array="+array; 
		   window.location.href="${pageContext.request.contextPath}/tcoursesite/saveUserAuthority?authorityId="+${authority.id}+"&array="+array; 
        } else {   
        	alert("请至少选择一条记录");  
        	}  
    	}
//查询
function queryUser(){
	
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	var academy=document.getElementById("academy").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/tcoursesite/findUserByCnameAndUsername",
	           data: {'cname':cname,'username':username,'academy':academy,'authorityId':${authority.id},'page':1},
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	});
	  
}
    
//首页
function firstPage(page){
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/tcoursesite/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&authorityId="+${authority.id}+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
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
	          url:"${pageContext.request.contextPath}/tcoursesite/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&authorityId="+${authority.id}+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
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
	           url:"${pageContext.request.contextPath}/tcoursesite/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&authorityId="+${authority.id}+"&page="+page,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	});
}
//末页
function lastPage(page){
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
	           url:"${pageContext.request.contextPath}/tcoursesite/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&authorityId="+${authority.id}+"&page="+page,
       		   type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	});
}    
</script> 
</head>
<body>

<div class="lest_content">
		<div class="left_nav">
        	<div class="left_user_box">
                <img src="${pageContext.request.contextPath}/images/index/user.png"/>
            </div>
            <div class="left_nav_box">
                <ul class="left_nav_list">
	                <sec:authorize ifAnyGranted="ROLE_TEACHER">
	                    <li <c:if test="${select eq 'tcourse' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1">选课组管理</a>
	                    </li>
	                </sec:authorize>
	                <sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
	                    <li <c:if test="${select eq 'authority' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listUserAuthority">权限管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'user' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=1">用户管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'term' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listTerm?currpage=1">学期管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'visualization' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/visualization/roomList?page=1">可视化管理</a>
	                    </li>
		            </sec:authorize>
                </ul>
            </div>  
            <div class="power2" style="display: block;">
                Power by <a href="http://www.gvsun.com" target="_blank">Gvsun</a>
            </div>
        </div>
<div id="TabbedPanels1" class="TabbedPanels"  style="float:right;width:80%">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
			<div class="title">
				<div id="title">用户信息列表&nbsp;&nbsp;用户组:[${authority.authorityName}]${authority.cname}</div>
				
				<a class="btn btn-new" onclick="window.history.go(-1)">返回</a>
			</div>   	
			<div class="tool-box">
				<form:form name="queryForm" action="${pageContext.request.contextPath}/tcoursesite/listUserAuthorityDetail?page=1&Id=${Id}" method="post" modelAttribute="formUser">
					 <ul>
    				<li>用户姓名：</li>
    				<li><form:input id="name" path="cname"/></li>
    				
    				<li><input type="submit" value="查询"/></li>
    				
    				<%-- <a class="btn btn-new" href="${pageContext.request.contextPath}/tcoursesite/newUserAuthority?Id=${Id}&page=${page}">新建</a> --%>
    				<sec:authorize ifAnyGranted="ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_SUPERADMIN,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
    				<a class="btn btn-new" href='javascript:void(0)' onclick="newUserPeople();">添加</a>
    				</sec:authorize>
    				</ul>
				</form:form>
		       </div>
    		<!-- 实验室列表 -->
    		<div class="content-box">   		
            <table  class="tb"  id="my_show"> 
                <thead>
                    <tr>
                        <th><center>用户工号</center></th>
                        <th><center>用户姓名</center></th>
                        <th><center>学院/部门</center></th>
                        <sec:authorize ifAnyGranted="ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_SUPERADMIN,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
                        <th><center>操作</center></th>
                        </sec:authorize>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${listUser}" var="current"  varStatus="i">
                           <tr>
                           <td><center>${current.username}</center></td>
                           <td><center>${current.cname}</center></td>
                           
                           <td><center>${current.schoolAcademy.academyName}</center></td>
                           <sec:authorize ifAnyGranted="ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_SUPERADMIN,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
                           <td>
                           <center>
                           <a href="${pageContext.request.contextPath}/tcoursesite/deleteUserAuthority?username=${current.username}&Id=${Id}&page=${page}" onclick="return confirm('确认要删除吗？')">删除</a>
                           </center>
                           </td>
                           </sec:authorize>
                           </tr>
                    </c:forEach> 
                </tbody>
            </table>
         <div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/tcoursesite/listUserAuthorityDetail?Id=${Id}&page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/tcoursesite/listUserAuthorityDetail?Id=${Id}&page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/tcoursesite/listUserAuthorityDetail?Id=${Id}&page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/tcoursesite/listUserAuthorityDetail?Id=${Id}&page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/tcoursesite/listUserAuthorityDetail?Id=${Id}&page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/tcoursesite/listUserAuthorityDetail?Id=${Id}&page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>   
            
            
            <!-- 弹出框 -->
     <div id="doSearchStudents" class="easyui-window" title="添加用户权限" modal="true" maximizable="false" minimizable="false" closed="true" iconCls="icon-add" style="width:800px;height:500px">
     <div class="content-box">
     	
			<table class="tb" id="my_show">
				<tr>
					<td>
					姓名：<input id="cname" type="text"/>
					</td>
					<td>
					工号：<input id="username" type="text"/>
					</td>
				</tr>
				<tr>	
					<td>
					部门：
					<select id="academy" class="chzn-select">
					<option value="">请选择</option>
					<c:forEach items="${academys}" var="a">
					<option value="${a.academyNumber}">${a.academyName}</option>
					</c:forEach>
					</select>
					<input type="button" value="搜索" onclick="queryUser();">
					</td>
					
					<td>
					<input type="button" value="添加" onclick="addUser();">
					</td>
				</tr>
			</table>
		
		
		<table id="my_show">
					<thead>
						<tr>
							
							<th style="width:30% !important">姓名</th>
							<th style="width:30% !important">工号</th>
							<th style="width:30% !important">所属学院</th>
							<th style="width:10% !important">选择</th>
							
						</tr>
					</thead>
						
					<tbody id="user_body">
						
					</tbody>
					
			</table>
     </div>
     </div>

    
    
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
						      '.chzn-select': {search_contains : true},
						      '.chzn-select-deselect'  : {allow_single_deselect:true},
						      '.chzn-select-no-single' : {disable_search_threshold:10},
						      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
						      '.chzn-select-width'     : {width:"95%"}
						    }
						    for (var selector in config) {
						      $(selector).chosen(config[selector]);
						    }
						</script>
					<!-- 下拉框的js -->

</body>
</html>