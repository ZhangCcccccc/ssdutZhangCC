<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta content="none" name="decorator">
     <title>学习</title>
<meta charset="utf-8">
<!--控制默认缩放大小、最小缩放大小、最大缩放大小。通过这个可以设置用户的最大和最小缩放程度。-->
<meta name="viewport" content="width=device-width,user-scalable=no"/>
<!--//表示手机模式设置为启用。-->
<meta name="format-detection" content="telephone=no"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/lessons.css"/>
<!--html5视频播放控件-->
  <link href="${pageContext.request.contextPath}/css/mobile/video-js.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/allpages.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/myscore.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/innerpage.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/extratest.css"/>
<style>
 .stitle{
	background-color:#F60;
	color:#FFF;
	font-size:1.2em;
	text-align:left;
	font-weight:bold;
	width:100%;
	}
	.sstitle{
	background-color:#666;
	color:#FFF;
	width:100%;
	}
	.ssstitle{
	background-color:#CCC;
	width:100%;
	}
</style>
<title>课程内容</title>
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
<div class="gridContainer clearfix">
		<div class="kctv"> 
		<h3>${lesson.title}</h3>
			<div class="kccon">
					  <div class="stitle">成绩</div>
						<span>你的得分：</span><span>50</span>
                        <span>总分：</span><span>100</span>
						<div class="stitle">答题状况</div>
						<c:forEach items="${records}" var="record" varStatus="i">
						<div class="sstitle">题目</div>
                        <div>${record.question.questionField}
                        </div>
                        <div class="ssstitle">得分</div>
                        <div>${record.points}</div>
						<div class="ssstitle">回答情况</div>
						<div>${ record.answerResult}</div>
						<div class="ssstitle">正确答案</div>
							<div>
								<c:forEach items="${record.question.answers}" var="answer" varStatus="j">
								<c:if test="${answer.isCorrect==true}">
								${answer.answerField} <br>
								</c:if>
								</c:forEach>
							</div>
						</c:forEach>
						</div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/resiz.js"></script>