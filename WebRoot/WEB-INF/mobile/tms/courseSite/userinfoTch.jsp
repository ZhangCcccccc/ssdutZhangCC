<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="decorator" content="none">
    <meta name="Keywords" content="庚商微课">
    <meta name="Description" content="庚商微课">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
    <title>个人信息--老师</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global_min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lib.css" />
    
</head>
<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
    <!-- 菜单栏开始  -->
    <div class="header_bg">
        <div class="header">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/logo.png" />
            </div>
            <ul class="menu_container">
                <li>
                    <a href="${pageContext.request.contextPath}/tms/index">首页</a>
                </li>
               <c:if test="${type eq 'courseList' }">                   	
		        	<li class="select">
	                    <a href="${pageContext.request.contextPath}/tms/courseList?currpage=1">课程列表</a>
	                </li>
	                <c:if test="${!empty user}">
		                <li>
		                    <a href="${pageContext.request.contextPath}/tms/myCourseList?currpage=1">我的选课</a>
		                </li>
	                </c:if>
	            </c:if>      
	            <c:if test="${type eq 'myCourseList' }">
	                <li>
	                    <a href="${pageContext.request.contextPath}/tms/courseList?currpage=1">课程列表</a>
	                </li>
	                <c:if test="${!empty user}">
		                <li class="select">
		                    <a href="${pageContext.request.contextPath}/tms/myCourseList?currpage=1">我的选课</a>
		                </li>
	                </c:if>
	            </c:if>  
                <sec:authorize ifAnyGranted="ROLE_TEACHER">
                <li>
                   <a href="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1" >系统管理</a>
                </li>
                <li >
                   <a href="${pageContext.request.contextPath}/visualization/show/index" >可视化</a>
                </li>
                </sec:authorize>
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
    <!-- 菜单栏结束  -->
    <div class="lest_content ">
        <div class="left_nav">
            <div class="left_user_box">
                <img src="${pageContext.request.contextPath}/images/user.png"/>
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
	                    <li <c:if test="${sites==null&&str==null }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tms/changePassword?type=${type}&password=">修改密码</a>
	                    </li>
	                    <c:if test="${type eq 'myCourseList' }">
		                    <li ><a href="${pageContext.request.contextPath}/tms/coursesite/newCourseSite">新建课程</a>
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
            <div class="cf mb20">
            <div class="l ml20 w20p h200" >
                <span class="f14">个人简介:</span>
                <img id="userPhoto" 
                	<c:if test="${empty user.photoUrl}">src="${pageContext.request.contextPath}/images/tCourseSite/system/userInfo/default_user_photo.png"</c:if>
                	<c:if test="${not empty user.photoUrl}">src="${pageContext.request.contextPath}/${user.photoUrl}"</c:if> 
                	class="block ma  mt10 h120" alt="" />
                <div class="bbtn bgb f14  mt10 poi tc br3 wh w80 ma mt10" onclick="photoUpload()">
                        上传照片
                </div>
            </div>
            <div id="showWindow" class="">
            	<table class="r h200 info_list f14" style="width:70%;">
	                <tr>
	                    <td>姓名：${user.cname}</td>
		                <td>工号：${user.username}</td>
	                </tr>
	                <tr>
	                    <td>邮箱：${user.email}</td>
	                    <td>电话：${user.telephone}</td>
	                </tr>
	                <tr>
	                    <td>职称：${user.majorDirection}</td>
	                    <td>专业：${user.schoolAcademy.academyName}</td>
	                </tr>
	                <tr>
	                    <td>博士学科：${user.doctorMajor}</td>
	                    <td>部门：${str}</td>
	                </tr>
	                <tr>
	                    <td>硕士学科：${user.masterMajor}</td>
	                    <td>职务：${user.duties}</td>
	                </tr>
	                <tr>
	                    <td>学士学科：${user.bachelorMajor}</td>
	                    <td></td>
	                </tr>
	                <tr>
	                	<td>
	                		<div class="bbtn bgb f14  mt10 poi tc br3 wh w80 ma mt10" onclick="editInfo()">
			                                     编辑信息
			                </div>
                		</td>
	                </tr>
            	</table>
            </div>
	        <div id="editWindow" class="hide">
	            <form:form action="${pageContext.request.contextPath}/tms/saveMyInfo?type=${type }" method="POST" onsubmit="return checkMail()" modelAttribute="user" enctype="multipart/form-data">	
	            	<table class="r h200 info_list f14" style="width:70%;">
						<tr>
							<form:hidden path="username"/>
						</tr>
		                <tr>
		                    <td>姓名：${user.cname}</td>
			                <td>工号：${user.username}</td>
		                </tr>
		                <tr>
		                    <td>邮箱：<form:input id="email"  path="email" validtype="email" invalidMessage="邮箱格式不正确,正确格式如xxx@xxx.xxx" value="${user.email}" /></td>
		                    <td>电话：<form:input id="username"  path="telephone" class="easyui-numberbox" placeholder="仅可输入数字" value="${user.telephone}" /></td>
		                </tr>
		                <tr>
		                    <td>职称：${user.majorDirection}</td>
		                    <td>专业：${user.schoolAcademy.academyName}</td>
		                </tr>
		                <tr>
		                    <td>博士学科：<form:input id="doctorMajor"  path="doctorMajor" value="${user.doctorMajor}"/></td>
		                    <td>部门：${str}</td>
		                </tr>
		                <tr>
		                    <td>硕士学科：<form:input id="masterMajor"  path="masterMajor" value="${user.masterMajor}"/></td>
		                    <td>职务：<form:input id="duties"  path="duties" value="${user.duties}"/></td>
		                </tr>
		                <tr>
		                    <td>学士学科：<form:input id="bachelorMajor"  path="bachelorMajor" value="${user.bachelorMajor}"/></td>
		                    <td></td>
		                </tr>
		                <tr>
		                	<td><input id="save" type="submit" value="保存"></td>
		                </tr>
	            	</table>
	            </form:form>
            </div>
            </div>
            <!-- 额外tag开始 -->
			<c:forEach items="${tags}" var="tag" varStatus="i">
				<div class="ml20 w80p mb10">
					<span class="f14">${tag.title }:</span>
					<textarea id="${tag.id}" onchange="saveTag(${tag.id});" class="w100p mt5 nb h120"><c:forEach items="${infos }" var="info" varStatus="j"><c:if test="${tag.id == info.tagId }">${info.content }</c:if></c:forEach></textarea>
				</div>
			</c:forEach>
			<!-- 额外tag结束 -->
			<sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
				<div class="bbtn bgb f14  mt10 poi tc br3 wh w80 ma mt10" onclick="addTagWindow()">添加标签</div>
			</sec:authorize>
        </div>
    </div>
    <!-- 页脚开始  -->
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
    <!-- 页脚结束  -->
    
    <!--添加文件开始-->
	<div class="window_box hide fix zx2  " id="photoUpload">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
             <div class="add_tit p10 bb f16">个人照片上传</div>
            <div class="add_con p10">
                <div class="add_module cf">
                    <div class="tab_box">
                    <div class="tab_list f14 mb2">
                    <input type="file" name="file" id="uploadUploadifyPic" />
                    </div>
                    </div>
                </div>
                <div class="cf tc">
                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">取消</div>
                </div>
            </div>
        </div>
    </div>
    <!--添加文件结束-->
    
    <!--添加标签开始-->
	<div class="window_box hide fix zx2  " id="addTag">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
             <div class="add_tit p10 bb f16">添加标签</div>
            <div class="add_con p10">
                <div class="add_module cf">
                    <div class="tab_box">
                    <div class="tab_list f14 mb2">
                    	标题：
                    <input id="tagTitle" />
                    	类型：
                    <select id="tagType">
							<option value="0">学生</option>
							<option value="1">教师</option>
					</select>
                    </div>
                    </div>
                </div>
                <div class="cf tc">
                    <div class="btn bgc l ml30 mt10 poi plr20 br3" onclick="addTag();">确定</div>
                </div>
            </div>
        </div>
    </div>
    <!--添加标签结束-->
    
     <!-- 上传插件的css和js -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>
    
    <script type="text/javascript" >
    //上传个人照片	
    function photoUpload(){
    	$("#photoUpload").fadeIn(100);
    	//上传个人照片	
    	$("#uploadUploadifyPic").uploadify({
    		'auto' : true, 		
        	'swf': $("#contextPath").val()+'/swfupload/swfupload.swf',  
        	'uploader':$("#contextPath").val()+'/tcoursesite/userinfo/photoUpload;jsessionid='+$("#sessionId").val(),	
        	//提交的controller和要在火狐下使用必须要加的id
        	'buttonText':'选择图片',
        	cancelImage:'/swfupload/uploadify-cancel.png',
        	'simUploadLimit' : 99, // 一次同步上传的文件数目
            'fileSizeLimit' : 0, //300mb,以字节为单位，0为不限制。1MB:1*1024*1024
            'queueSizeLimit' : 99, // 队列中同时存在的文件个数限制
            'fileTypeDesc' : '支持格式:jpg/jpeg等', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
            'fileTypeExts' : '*.jpg;*.jpeg;*.png;*.bmp;*.gif;',// 允许的格式      
            'multi': false,
            'onUploadError':function(file, errorCode, errorMsg, errorString) {
            	alert("上传失败，请检查文件大小和格式！");
        	} ,
        	'onUploadSuccess' : function(file, data, response) {
        		$("#userPhoto").attr("src",'${pageContext.request.contextPath}'+data.toString());
        		$("#photoUpload").fadeOut(100);
        		location.reload();
    		},
    		onQueueComplete : function(event, data) {//当队列中的所有文件全部完成上传时触发　 
    		}
    	});
    }
  	//保存个人信息标签
	function saveTag(tagId){
		var content = document.getElementById(tagId).value;
		//填写时自动保存
		$.ajax({
	 		type: 'POST',
	 		url: '${pageContext.request.contextPath}/tcoursesite/userinfo/saveContent',
	 		data: {'tagId':tagId,'content':content},
	 	});
  	}
	//打开添加标签窗口
	function addTagWindow(){
		$("#addTag").fadeIn(100);
	}
	//添加标签
	function addTag(){
		//tag标题
		var title = document.getElementById("tagTitle").value;
		//tag类型
		var type = document.getElementById("tagType").value;
		$.ajax({
	 		type: 'POST',
	 		url: '${pageContext.request.contextPath}/tcoursesite/userinfo/addTag',
	 		data: {'title':title,'type':type},
	 	});
		location.reload();
	}
	$(".close_icon").click(function(){
		$("#photoUpload").fadeOut(100);
		$("#addTag").fadeOut(100);
	});
	function editInfo(){
		$("#showWindow").addClass("hide");
		$("#editWindow").removeClass("hide");
	}
	</script>
</body>
</html>