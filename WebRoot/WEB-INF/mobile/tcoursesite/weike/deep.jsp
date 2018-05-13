<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta content="none" name="decorator">
    <meta charset="utf-8">
<!--控制默认缩放大小、最小缩放大小、最大缩放大小。通过这个可以设置用户的最大和最小缩放程度。-->
<meta name="viewport" content="width=device-width,user-scalable=no"/>
<!--//表示手机模式设置为启用。-->
<meta name="format-detection" content="telephone=no"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/lessons.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/extratest.css"/>
<title>扩展阅读</title>
<style>
.kccon img{
 width:100%;
}
</style>
</head>
  
<body>
	<div id="conteiner">
	        <div id="buttonbox">
	        	<a href="${pageContext.request.contextPath}/lesson/${lesson.id}/video" >视频</a>
				<a href="${pageContext.request.contextPath}/lesson/${lesson.id}/pic" >图片</a>
				<a href="${pageContext.request.contextPath}/lesson/${lesson.id}/deep" > 扩展阅读</a>
				<a href="${pageContext.request.contextPath}/lesson/${lesson.id}/testStart">做题</a>
	        	<a href="${pageContext.request.contextPath}/course/${course.id}?courseId=${course.id}">返回</a>
	        </div>
	        <div id="extrabox"> 
				<h3>${lesson.title}</h3>
				<div class="kccon">
					${lesson.deep}测试
				</div>
			</div>
	 </div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/resiz.js"></script>
</html>
