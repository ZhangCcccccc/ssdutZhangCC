<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>庚商微课</title>
    <meta name="decorator" content="none">
    <meta name="Keywords" content="庚商微课">
    <meta name="Description" content="庚商微课">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link style="type/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index/global_min.css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/jquery/themes/default/easyui.css" rel="stylesheet" type="text/css" />
       	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
       	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
       	 
       	    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js" ></script>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式 -->


       	 <style>
       	.courses_list2 .title{
       	   line-height: 30px;
          padding-left: 5px;
         border-left: 3px solid #78b0ff;
       	}
       	 .introduce-box{
       	 margin-top: 15px;
          padding-left: 10px;
         margin: 15px 0px;
       	 }
       	 legend{
       	   padding-left:-5px;
       	 }
       	#submit{
   background-color: #5894e9;
    color: #fff;
    line-height: 40px;
    padding: 0 10px;
    cursor: pointer;
    border: none;
    }
    table tr{
      line-height:40px;
    }
       	 </style>
</head>
<body>
    <div class="header_bg">
        <div class="header">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/index/logo.png" />
            </div>
            <ul class="menu_container">
                <li>
                    <a href="${pageContext.request.contextPath}/tms/index">首页</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/tms/courseList?currpage=1">课程列表</a>
                </li>
                <c:if test="${!empty user}">
	                <li  class="select">
	                    <a href="${pageContext.request.contextPath}/tms/myCourseList?currpage=1">我的选课</a>
	                </li>
                </c:if>
                <sec:authorize ifAnyGranted="ROLE_TEACHER">
                <li>
                   <a href="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1" >系统管理</a>
                </li>
                </sec:authorize>
                <li >
                   <a href="${pageContext.request.contextPath}/visualization/show/index" >可视化</a>
                </li>
            </ul>
            <c:if test="${empty user}">
	            <div class="right_container">
	                <form>
	                    <input id="search" name="keyword" value="搜索你喜欢的" onfocus="this.value='';" onblur="if(this.value==''){this.value='搜索你喜欢的'}" />
	                    <input class="login_btn" type="button" value="登录" />
	                </form>
	            </div>
            </c:if>
            <c:if test="${!empty user}">
            <div class="right_container">
                <div style="text-align:right;font-size:12px;">
                    <%
					   if(session.getAttribute("selected_role").toString().equals("ROLE_TEACHER")){
					%>
     				    <font style="color:#fff;">${user.cname} 老师您好  </font>
     				<%}else if(session.getAttribute("selected_role").toString().equals("ROLE_STUDENT")){%>
     				    <font style="color:#fff;">${user.cname} 同学您好  </font>
     				<%}%>
     			   <a href="${pageContext.request.contextPath}/pages/logout-redirect.jsp" target="_parent"><font color=write><font style="color:#fff;">退出</font> </a>  
                </div> 
            </div>
            </c:if>
            <form id="subform" name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'> 
	            <div class="log-in">
	                <div class="log_in_box">
	                    <img class="close" src="${pageContext.request.contextPath}/images/index/msg_close.png" />
	                    <img src="${pageContext.request.contextPath}/images/index/logo_a.png" />
	                    <div class="sat_name"><span>会员登录</span>
	                    </div>
	                    <fram>
	                        <div class="username-box">
	                            <input type="text" name="j_username" placeholder="会员账号" />
	                        </div>
	                        <div class="password-box">
	                            <input type="password" name="j_password" placeholder="密码" />
	                        </div>
	                        <div class="log-in-box">
	                            <input type="submit" value="登录"  />
	                        </div>
	                    </fram>
	                </div>
	            </div>
            </form>
        </div>
    </div>
    <div class="lest_content ">
        <div class="left_nav">
        	<div class="left_user_box">
                <img src="${pageContext.request.contextPath}/images/index/user.png"/>
            </div>
            <div class="left_nav_box">
                <ul class="left_nav_list">
	                    <li <c:if test="${sites!=null }">class="selected"</c:if> style="opacity: 1;"> 
	                    	<c:if test="${type eq 'courseList' }">                   	
	                    		<a href="${pageContext.request.contextPath}/tms/courseList?currpage=1">课程列表</a> 
	                    	</c:if>      
	                    	<c:if test="${type eq 'myCourseList' }">
	                    		<a href="${pageContext.request.contextPath}/tms/myCourseList?currpage=1">我的课程</a>
	                    	</c:if>    	
	                    </li>
                    <c:if test="${!empty user}">
	                    <li <c:if test="${sites==null&&str!=null }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tms/listMyInfo?type=${type}">个人信息</a>
	                    </li>
	                    <li <c:if test="${sites==null&&str==null }"></c:if>><a href="${pageContext.request.contextPath}/tms/changePassword?type=${type}&password=">修改密码</a>
	                    </li>
	                    <c:if test="${type eq 'myCourseList' }">
		                    <li class="selected" ><a href="${pageContext.request.contextPath}/tms/coursesite/newCourseSite">新建课程</a>
		                    </li>
	                    </c:if>
                    </c:if>
                </ul>
            </div>  
            <div class="power2" style="display: block;">
                Power by <a href="http://www.gvsun.com" target="_blank">Gvsun</a>
            </div>
        </div>
        <div class="courses_list2">
		    <div class="title">
			    新建课程站点
			</div>
			<form:form action="${pageContext.request.contextPath}/tms/coursesite/saveTCourseSite?flagID=${flagId}" method="post" modelAttribute="tCourseSite" enctype="multipart/form-data">
			<fieldset class="introduce-box">
			<legend>站点基本信息</legend>
			<table id="listTable" cellpadding="0">
			<tr>
				<td>站点名称</td>
				<td>
				<form:input path="title" class="searchable-select-holder" id="title" required="true" />
			    </td>
			    
			    <td>选择学期</td>
				<td style="width:40%">
				<form:select class="select_chosen"  path="schoolTerm.id" id="schoolTerm_id" cssStyle="width:200px" >
			         <c:if test="${flagId!=-1 }">
			          <form:option value="${tCourseSite.schoolTerm.id}" label="${tCourseSite.schoolTerm.termName}" />  
			         </c:if>
			         <c:forEach items="${schoolTerms}" var="curr"  varStatus="i">	
			          <form:option value="${curr.id}" label="${curr.termName}" />  
			         </c:forEach>
			    </form:select>
			    
			    </td>
				<%--<td>选择专业</td>
				<td>
				<form:select class="chzn-select"  path="majorNumber" id="schoolMajor_majorNumber" cssStyle="width:400px" >
			         <c:if test="${flagId!=-1 }">
			          <form:option value="${tCourseSite.schoolMajor.majorNumber}" label="${tCourseSite.schoolMajor.majorName}" />  
			         </c:if>
			         <c:forEach items="${schoolMajors}" var="curr"  varStatus="i">	
			
			          <form:option value="${curr.key}" label="${curr.value}" />  
			         </c:forEach>
			    </form:select>
			    </td>
			--%>
			</tr>
			
			<tr>
				<td>站点编号 <font style="color:red">*</font></td>
				<td>
				<c:if test="${flagId==-1 }">
				<form:input path="siteCode" class="searchable-select-holder" id="siteCode" value="code-${user.schoolAcademy.academyNumber}-${maxId}" required="true" />
				</c:if>
				<c:if test="${flagId!=-1 }">
				<form:input path="siteCode"  class="searchable-select-holder" id="siteCode" value="${tCourseSite.siteCode}" required="true" />
				</c:if>
				</td>
				
				<%--<td>选择课程</td>
				<td>
				<form:select class="select_search"  path="schoolCourseInfo.courseNumber" id="schoolCourseInfo_courseNumber" cssStyle="width:200px" >
			         <c:forEach items="${schoolCourses}" var="curr"  varStatus="i">	
			         <c:if test="${flagId!=-1 }">
			         <form:option value="${tCourseSite.schoolCourseInfo.courseNumber}" selected="selected" label="${tCourseSite.schoolCourseInfo.courseNumber}:${tCourseSite.schoolCourseInfo.courseName}" />  
			         </c:if>
			         <form:option value="${curr.courseNumber}" label="${curr.courseNumber}:${curr.courseName}"/>  
			         </c:forEach>
			    </form:select>
			    </td>
			--%>
				<td>选择课程</td>
				<td>
				    <form:input path="schoolCourseInfo.courseNumber" id="CourseNumber" style="width:150px;" required="true" readOnly="true"/>
				    <a href="javascript:void(0)" onclick="getCourseNumber(1)"><img src="${pageContext.request.contextPath}/images/icn_search.png"  border="0" width="25px"  height="25px" style="margin-left:2px;" /></a>
				</td>
			</tr>
			
			<tr>
				<td>选择教师</td>
				<td>
				<div class=" w45p f14 r mt10" >
				<form:select  path="userByCreatedBy.username" id="user_username" cssStyle="width:200px" class="select_chosen">
				<form:options items="${timetableTearcherMap }" />  
				</form:select>
				</div>
				</td>
				
				<td>选择助教</td>
				<td>
				    <input name="userName" id="userName" style="width:150px;" required="true"/>
				    <a href="javascript:void(0)" onclick="getUsers(1)"><img src="${pageContext.request.contextPath}/images/icn_search.png"  border="0" width="25px"  height="25px" style="margin-left:2px;" /></a>
				</td>
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
			
			<div style="text-align: center">
			    <input type="hidden" name="id" value="${tCourseSite.id }">
				<input onclick="return checkSite()"  id="submit" type="submit" value="提交">&nbsp;&nbsp;
				<%-- <a  class="btn btn-common"  href="${pageContext.request.contextPath}/timetable/selfTimetable/listCourseCodes?currpage=1" >返回列表</a>
			 --%></div>
			</form:form>
		    
        </div>
    </div>

    <div class="footer_container">
        <div class="footer">
            <div class="copyright">Copyright ©2014 庚商教育智能科技 All Rights Reserved 沪ICP备14016833号</div>
            <div class="power">庚商微课 power by <a href="www.gvsun.com">Gvsun</a>
            </div>
        </div>
    </div>
    <div class="top">
        <img src="${pageContext.request.contextPath}/images/index/top.png">
    </div>
    
    <div id="searchUser" class="easyui-window" title="搜索助教" closed="true" iconCls="icon-add" style="width:710px;height:400px">
    <table width="100%">
        <tr>
            <td width="14%">姓名：</td>
            <td width="16%"><input id="cName" type="text" style="width: 100px" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))"/></td>
            <td width="10%"></td>
            <td width="29%"><input type="submit" onclick="searchUserSure(1);" value="确定" border="0" style="margin-top:1px"></td>
        </tr>
    </table>
    <table id="tt-edutype" align="center"></table>
    
        总记录:<strong id="teachingtotalRecords"></strong>条&nbsp;
                 总页数:<strong id="teachingtotalPage"></strong>页&nbsp;
                 当前页:第<strong id="teachingcurrpage"></strong>页&nbsp;
		   <a id="teachingfirstPage" href="javascript:void(0)" target="_self"> 首页</a> 	   
		   <a id="teachingpreviousPage" href="javascript:void(0)" target="_self">上一页 </a> 
		   <a id="teachingnextPage" href="javascript:void(0)" target="_self">下一页 </a> 
		   <a id="teachinglastPage" href="javascript:void(0)" target="_self">末页 </a>&nbsp;
		   <!-- 跳转到选择/输入的页面 -->
		   第 <select id="teachingselect" onchange="searchUserSure(this.options[this.selectedIndex].value)">
           </select> 页
	</div>
	
	<div id="searchCourseNumber" class="easyui-window" title="搜索课程" closed="true" iconCls="icon-add" style="width:710px;height:400px">
    <table width="100%">
        <tr>
            <td width="14%">课程名：</td>
            <td width="16%"><input id="courseName" type="text" style="width: 100px" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))"/></td>
            <td width="10%"></td>
            <td width="29%"><input type="submit" onclick="searchCourseNumber(1);" value="确定" border="0" style="margin-top:1px"></td>
        </tr>
    </table>
    <table id="courseMap" align="center"></table>
    
    	总记录:<strong id="totalRecords"></strong>条&nbsp;
                 总页数:<strong id="totalPage"></strong>页&nbsp;
                 当前页:第<strong id="currpage"></strong>页&nbsp;
		   <a id="firstPage" href="javascript:void(0)" target="_self"> 首页</a> 	   
		   <a id="previousPage" href="javascript:void(0)" target="_self">上一页 </a> 
		   <a id="nextPage" href="javascript:void(0)" target="_self">下一页 </a> 
		   <a id="lastPage" href="javascript:void(0)" target="_self">末页 </a>&nbsp;
		   <!-- 跳转到选择/输入的页面 -->
		   第 <select id="select" onchange="searchCourseNumber(this.options[this.selectedIndex].value)">
           </select> 页
           
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery-1.7.1.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.tablesorter.js"></script>
    <script type="text/javascript" >
    $(function () {
    	$('.select_chosen').chosen();
        $('.select_search').searchableSelect();
        $('.searchable-select-dropdown').attr("style","z-index:999");
    });
    
  //添加助教(显示所有)
    function getUsers(currpage) {
	$('#searchUser').window({left:"100px", top:"0px"});
	$('#searchUser').window('open');
	var name = "";
	$('#tt-edutype')
		.datagrid(
			{
				url : encodeURI(encodeURI("./../tcoursesite/searchUser?cName=" + name + "&currpage=" + currpage)),
				title : '添加助教',
				width : 670,
				height : 'auto',
				fitColumns : true,
				rownumbers : false,
				singleSelect : true,
				columns : [ [ {
					field : 'userName',
					title : '编号',
					width : 70
				},{
					field : 'cName',
					title : '姓名',
					width : 150
				},{
					field : 'do',
					title : '操作',
					width : 30,
					align : 'left'
				} ] ]
			});
	
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/tms/tcoursesite/searchUserPage?cName=" + name + "&currpage=" + currpage,
		dataType:'json',
		success:function(data){
			$.each(data,function(key,values){
				if(key=="totalRecords"||key=="totalPage"){
					//$("#"+key).attr("innerHTML",values);
					$("#teaching"+key).html(values);
				}else if(key=="currpage"){
					$("#teachingcurrpage").html(values);
					$("#teachingcCurrpage").html(values);
					$("#teachingcCurrpage").attr("value",values);
					//$("#indexTest").attr("test","${j.index!="+values+"}");
				}else if(key=="lastPage"){
					$("#teachinglastPage").attr("onclick","getUsers("+values+")");
					//data[currpage]
					var options = "";
					for(var i=1;i<values+1;i++){
						options += "<option value='"+i+"'>"+i+"</option>";
					}
					$("#teachingselect").html(options);
					$("#teachingselect").val(data['currpage']);
					//$("#cLastPage").attr("end",values);
				}else{
					$("#teaching"+key).attr("onclick", "getUsers("+values+")");
				}
			 }); 
		},
		error:function(){
			alert("信息错误！");
			}
	});
	
	};
	//添加助教(查询)
function searchUserSure(currpage) {
	var name = $('#searchUser #cName').val();
	$.ajax({
		async:false,
		type: "POST",
		url: encodeURI(encodeURI("${pageContext.request.contextPath}/tms/tcoursesite/searchUserPage?cName=" + name + "&currpage=" + currpage)),
		dataType:'json',
		success:function(data){
			$.each(data,function(key,values){
				if(key=="totalRecords"||key=="totalPage"){
					//$("#"+key).attr("innerHTML",values);
					$("#teaching"+key).html(values);
				}else if(key=="currpage"){
					$("#teachingcurrpage").html(values);
					$("#teachingcCurrpage").html(values);
					$("#teachingcCurrpage").attr("value",values);
					//$("#indexTest").attr("test","${j.index!="+values+"}");
				}else if(key=="lastPage"){
					$("#teachinglastPage").attr("onclick","searchUserSure("+values+")");
					//data[currpage]
					var options = "";
					for(var i=1;i<values+1;i++){
						options += "<option value='"+i+"'>"+i+"</option>";
					}
					$("#teachingselect").html(options);
					$("#teachingselect").val(data['currpage']);
					//$("#cLastPage").attr("end",values);
				}else{
					$("#teaching"+key).attr("onclick", "searchUserSure("+values+")");
				}
			 }); 
		},
		error:function(){
			alert("信息错误！");
			}
	});
	
	$('#tt-edutype')
		.datagrid(
			{
				url : encodeURI(encodeURI("./../tcoursesite/searchUser?cName=" + name + "&currpage=" + currpage)),
				title : '添加助教',
				width : 670,
				height : 'auto',
				fitColumns : true,
				rownumbers : false,
				singleSelect : true,
				columns : [ [ {
					field : 'userName',
					title : '编号',
					width : 70
				},{
					field : 'cName',
					title : '姓名',
					width : 150
				},{
					field : 'do',
					title : '操作',
					width : 30,
					align : 'left'
				} ] ]
			});
};

//添加课程
    function getCourseNumber(currpage) {
	$('#searchCourseNumber').window({left:"100px", top:"0px"});
	$('#searchCourseNumber').window('open');
	var name = "";
	$('#courseMap')
		.datagrid(
			{
				url : encodeURI(encodeURI("./../tcoursesite/searchCourseNumber?courseName=" + name + "&currpage=" + currpage)),
				title : '添加课程',
				width : 670,
				height : 'auto',
				fitColumns : true,
				rownumbers : false,
				singleSelect : true,
				columns : [ [ {
					field : 'courseNumber',
					title : '课程编号',
					width : 70
				},{
					field : 'courseName',
					title : '课程名',
					width : 150
				},{
					field : 'do',
					title : '操作',
					width : 30,
					align : 'left'
				} ] ]
			});
	
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/tms/tcoursesite/searchCourseNumberPage?courseName=" + name + "&currpage=" + currpage,
		dataType:'json',
		success:function(data){
			$.each(data,function(key,values){
				if(key=="totalRecords"||key=="totalPage"){
					//$("#"+key).attr("innerHTML",values);
					$("#"+key).html(values);
				}else if(key=="currpage"){
					$("#currpage").html(values);
					$("#cCurrpage").html(values);
					$("#cCurrpage").attr("value",values);
					//$("#indexTest").attr("test","${j.index!="+values+"}");
				}else if(key=="lastPage"){
					$("#lastPage").attr("onclick","getCourseNumber("+values+")");
					//data[currpage]
					var options = "";
					for(var i=1;i<values+1;i++){
						options += "<option value='"+i+"'>"+i+"</option>";
					}
					$("#select").html(options);
					$("#select").val(data['currpage']);
					//$("#cLastPage").attr("end",values);
				}else{
					$("#"+key).attr("onclick", "getCourseNumber("+values+")");
				}
			 }); 
		},
		error:function(){
			alert("信息错误！");
			}
	});
	
	};
	
function searchCourseNumber(currpage) {
	var name = $('#searchCourseNumber #courseName').val();
	$.ajax({
		async:false,
		type: "POST",
		url: encodeURI(encodeURI("${pageContext.request.contextPath}/tms/tcoursesite/searchCourseNumberPage?courseName=" + name + "&currpage=" + currpage)),
		dataType:'json',
		success:function(data){
			$.each(data,function(key,values){
				if(key=="totalRecords"||key=="totalPage"){
					//$("#"+key).attr("innerHTML",values);
					$("#"+key).html(values);
				}else if(key=="currpage"){
					$("#currpage").html(values);
					$("#cCurrpage").html(values);
					$("#cCurrpage").attr("value",values);
					//$("#indexTest").attr("test","${j.index!="+values+"}");
				}else if(key=="lastPage"){
					$("#lastPage").attr("onclick","searchCourseNumber("+values+")");
					//data[currpage]
					var options = "";
					for(var i=1;i<values+1;i++){
						options += "<option value='"+i+"'>"+i+"</option>";
					}
					$("#select").html(options);
					$("#select").val(data['currpage']);
					//$("#cLastPage").attr("end",values);
				}else{
					$("#"+key).attr("onclick", "searchCourseNumber("+values+")");
				}
			 }); 
		},
		error:function(){
			alert("信息错误！");
			}
	});
	
	$('#courseMap')
		.datagrid(
			{
				url : encodeURI(encodeURI("./../tcoursesite/searchCourseNumber?courseName=" + name + "&currpage=" + currpage)),
				title : '添加课程',
				width : 670,
				height : 'auto',
				fitColumns : true,
				rownumbers : false,
				singleSelect : true,
				columns : [ [ {
					field : 'courseNumber',
					title : '课程编号',
					width : 70
				},{
					field : 'courseName',
					title : '课程名',
					width : 150
				},{
					field : 'do',
					title : '操作',
					width : 30,
					align : 'left'
				} ] ]
			});
	
	
	
};

// 确认添加的用户；
function searchUserSelected(userName, cName) {
	//$('#searchUser').window('close');
	var username = $("#userName").val();
	username = username + "," + userName;
	$("#userName").val(username);
};

// 确认添加的课程；
function searchCourseNumberSelected(courseNumber, courseName) {
	var CourseNumber = courseNumber;
	$("#CourseNumber").val(CourseNumber);
};

function checkSite(){
	var CourseNumber = $("#CourseNumber").val();
	if(CourseNumber==""){
		alert("请选择课程");
		return false;
	}
		
	
}
</script>

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