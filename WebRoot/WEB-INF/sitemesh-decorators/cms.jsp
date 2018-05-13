<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
	<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
   <head>
    <meta name="decorator" content="none">
    <title>我的课程</title>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/cms/global_min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/cms/header_footer.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/animatecolor-plugin.js"></script>
    </head>
	<body class="all1">	
		<div class="header">
        <!--<audio src="http://202.121.32.182:3280/portal/templates/1016zzyjoomla/images/music.mp3" autoplay="true" playcount="-1"></audio>-->
	        <div class="header_box">
	            <div class="logo_box">
	                <img src="${pageContext.request.contextPath}/images/index/logo.png" class="logo_pic">
	            </div>
	            <div class="nav_box">
	                <ul>
	                    <li><a href="javascript:void(0);">题库管理</a>
	                    </li>
	                    <li><a href="javascript:void(0);">实验课程资源管理</a>
	                    </li>
	                    <li><a href="javascript:void(0);">基础数据管理</a>
	                    </li>
	                    <li class="nav_selected"><a href="javascript:void(0);">我的课程</a>
	                    </li>
	                </ul>
	            </div>
	            <div class="user_box_top">
	                <img src="${pageContext.request.contextPath}/images/index/user.png">
	            </div>
	        </div>
        </div>
		<!-- 底部 --> 
		<div class="footer_box">
                <div class="footer">
                    <p>Copyright ©2014 庚商教育智能科技 All Rights Reserved 沪ICP备14016833号 </p>
                    <p>电话：021-54244128 传真：021-54188185 电子邮箱：gs@gengshang.com</p>
                </div>
            </div>
	</body>
</html>
