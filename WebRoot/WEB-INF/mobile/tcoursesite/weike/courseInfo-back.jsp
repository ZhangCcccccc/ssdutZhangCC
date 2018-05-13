<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<html class="">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<title>课程 </title>
  	<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
  	<link href="${pageContext.request.contextPath}/css/boilerplate.css" rel="stylesheet">
  	<link href="${pageContext.request.contextPath}/css/weiklist5.css" rel="stylesheet">
  	
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-sacale=1.0,user-scalable=no"/>	
</head>

<body>
<div class="gridContainer clearfix">

	<div class="arbd">
	<%-- <!--<div class="zs"></div>-->
	<div class="header">
	<div class="dl">
		<div class="logo">
		<div class="logo-img"><img src="${pageContext.request.contextPath}/images/images/logo.png"></div>
		</div>
	</div>
			
	<div class="loginbarp">
		<ul id="button-login">
			<li class="login"><a href="#">登陆</a></li>
			<li class="registered"><a href="#">注册</a></li>		
		</ul>
	</div>

	<div class="mainmenu">
	<ul>
		<li><a href="${pageContext.request.contextPath}">首页</a></li>
			<li><a href="http://www.gvsun.net:3080/gvsuncms/viewContentByid?id=335">关于WeiKe</a></li>
			<li><a href="http://www.gengshang.com">关于我们</a></li>
	</ul>
	</div>
			
	<div class="loginbar">
		<ul id="button-login">
			<!-- <li class="login"><a href="#">登陆</a></li>
			<li class="registered"><a href="#">注册</a></li>		 -->		
		</ul>
	</div>
			
	</div> --%>
	<div class="kcclist">
		<div class="kcc">	
		<ul>
			<li >
			<div class="kcccimg">
				<img src="${pageContext.request.contextPath}${course.logoUrl}" >
			</div>
			<div class="kcmass">
				<h1><a href="#">${course.name}</a></h1>
				<h4><a href="#">老师：admin </a></h4>
			</div>
			</li>
			<li>
			<img style="height:80px;" src="${pageContext.request.contextPath}/upload/course${course.id}.png">
			<a href="${pageContext.request.contextPath}/course/${course.id}">进入课程</a>
			</li>
		</ul>	
		
		</div class="kccl">		
		
	</div>

	<div class="kcjj">
		<div class="kcvid">
			<video width="320" height="240" controls="controls">
 	 			<source src="${pageContext.request.contextPath}${upload.url}" type="video/mp4">
			</video>
		</div>
		<div class="kcsm">${course.introduction }</div>
	</div>
	<div class="sdf">
	<div class="ql">
		<div class="qqqw">
				<span class="qqtit1">课程目标/Learning Outcomes</span>
				<div class="mb">${course.outcomes}</div>
		</div>
		<div class="qqqw">
				<span class="qqtit2">教学大纲</span>
				<div class="mb">${course.syllabus}</div>
		</div>
		<div class="qqqw">
				<span class="qqtit3">教学日历/Calendar</span>
				<div class="mb"></div>
		</div>
		<div class="qqqw">
				<span class="qqtit4">考评方法、Evaluation</span>
				<div class="mb">${course.evaluation}</div>
		</div>
	</div>
	<div class="qr">
		<span class="qqtit5">课程动态</span>
		<div class="mb">
			<ul >
				<li>无动态</li>
				
			</ul>
		</div>	
	</div>
	</div>

	<div id="mobile_footer"></div>

	</div>
</div>

</body>
</html>