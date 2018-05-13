<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="org.springframework.security.authentication.AnonymousAuthenticationToken" %>
<fmt:setBundle basename="bundles.application-resources"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
	<title>Cloud CMS</title>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/xidlims/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.ui.tabs.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.ui-1.10.4.tabs.min.js" type="text/javascript"></script>
		<decorator:head />
    </head>
<body>
    <div class="top">
        <div class="header">
           <div class="logo">Cloud CMS</div>        
           <ul class="user-nav">
                <li><a href="${pageContext.request.contextPath}/cms/cindex" >首页</a></li>
                <li><a href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1" >实验管理</a></li>
                <li><a ><img src="${pageContext.request.contextPath}/images/user.png">${user.cname }</a>您好</li>
                <li><a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp"   target="_parent"><img src="${pageContext.request.contextPath}/images/switch.png">退出</a></li>
            </ul>
		    </div>
        <div class="main-menu">
            <ul class="nav">
                <li><a>网站管理</a>
                    <ul class="second-menu">
                        <li><a href="${pageContext.request.contextPath}/admin/site">查看网站</a></li>
                        <li><a  href="${pageContext.request.contextPath}/admin/newSite">添加网站</a></li>
                    </ul>
                </li>
                <li><a>栏目管理</a>
                     <ul class="second-menu">
                        <li><a  href="${pageContext.request.contextPath}/admin/channel">查看栏目</a></li>
        				<li><a href="${pageContext.request.contextPath}/admin/newChannel">新建栏目</a></li>                    
                    </ul>
                </li>
                <li><a>文章管理</a>
                	<ul class="second-menu">
                        <li><a   href="${pageContext.request.contextPath}/admin/article?page=1">查看文章</a></li>
                        <li><a   href="${pageContext.request.contextPath}/admin/newArticle">添加文章</a></li>                       
                    </ul>
                </li>
                <li><a>资源管理</a>
                	<ul class="second-menu">
                        <li><a   href="${pageContext.request.contextPath}/admin/resourcesList?type=0&page=1">查看图片</a></li>
                        <li><a   href="${pageContext.request.contextPath}/admin/addNewImage">添加图片</a></li>                       
                        <li><a   href="${pageContext.request.contextPath}/admin/resourcesList?type=1&page=1">查看视频</a></li>
                        <li><a   href="${pageContext.request.contextPath}/admin/addNewVideo">添加视频</a></li>  
                        <li><a   href="${pageContext.request.contextPath}/admin/documentsList?page=1">查看附件</a></li> 
                        <li><a   href="${pageContext.request.contextPath}/admin/newDocument">添加附件</a></li>                     
                    </ul>
                </li>
                <li><a>链接管理</a>
                	<ul class="second-menu">
                        <li><a   href="${pageContext.request.contextPath}/admin/linksList">查看链接</a></li>
                        <li><a   href="${pageContext.request.contextPath}/admin/linkNew">添加链接</a></li>                       
                    </ul>
                </li>
                <%-- <li><a>用户管理</a>
                	<ul class="second-menu">
                        <li><a   href="${pageContext.request.contextPath}/admin/usersList">查看用户</a></li>
                        <li><a   href="${pageContext.request.contextPath}/admin/userNew">添加用户</a></li>               
                    </ul>
                </li>                
                <li><a>校友管理</a>
                	<ul class="second-menu">
                        <li><a   href="${pageContext.request.contextPath}/admin/alumni?page=1">历届校友</a></li>
                        <li><a   href="${pageContext.request.contextPath}/admin/newAlumni">添加校友</a></li>                       
                    </ul>
                </li>
                <li><a>捐赠管理</a>
                	<ul class="second-menu">
                        <li><a   href="${pageContext.request.contextPath}/admin/project?page=1">募集项目</a></li>
                        <li><a   href="${pageContext.request.contextPath}/admin/newProject">添加项目</a></li>
                        <li><a   href="${pageContext.request.contextPath}/admin/info?page=1">募集名录</a></li>                       
                        <li><a   href="${pageContext.request.contextPath}/admin/newInfo">添加名录</a></li>                       
                    </ul>
                </li> --%>
            </ul>
        </div>
        <script type="text/javascript">
$(".nav li").hover(
           function(){
                $(this).children(".second-menu").find("li").stop().slideDown("400"); 
                $(this).addClass("focus") 
            },
            function(){ 
                 $(this).children(".second-menu").find("li").stop().slideUp("400");
                 $(this).removeClass("focus")
            }
        )


		</script>
    </div>
    <div class="content-box">
        <decorator:body/>
    </div>
    
    <div class="footer">Copyright © 2004 - 2015 庚商（中国）. All Rights Reserved</div>

</body>
</html>