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
<title>做题</title>
<script language="javascript" type="text/javascript">
	function validate(){
		var resualt=false;
		for(var i=0;i<document.frmvote.answer.length;i++){  
			    if(document.frmvote.answer[i].checked){
			      resualt=true;}
			}
			if(!resualt){
			    alert("请先答题！");}
			return resualt;
		}
		function save(){window.location.href="myScore?lessonId=${lesson.id }";}
</script>
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
	<div class="arbd">
	<div class="kccv">
	<div class="kcrightl">
	</div> 
		<div class="kctv"> 
		<h3>${lesson.title}</h3>
<div class="kccon">		
	<div class="panel">
<div class="topNav">
<!-- 序号-->
<c:forEach items="${questions}" var="current" varStatus="i">
	<c:if test="${current!=question.id}">
		<a href="test?questionId=${current}&lessonId=${lesson.id}" class=""><span>${i.count}</span></a>
	</c:if>
	<c:if test="${current==question.id}">
		<a href="test?questionId=${current}&lessonId=${lesson.id}" class=""><span><font  color="red">${i.count}</font></span></a>
	</c:if>
</c:forEach>

</div>

<div class="question">
	<!--题目-->
	<div class="title">${question .questionField}</div>
	<form action="check?questionId=${question.id}&lessonId=${lesson.id}" method="post" name="frmvote" onsubmit="return(validate());">
	<c:forEach items="${answers}" var="current" varStatus="i">
		<!-- 单选 -->
		<c:if test="${question.testType==1}">
	  	<input type="radio" name="answer" value="${current.id}" />${current.answerField} <br>
		<c:if test="${current.path.length()>0}">
		   <img src="${pageContext.request.contextPath}/${current.path}" width="200px" height="150px"/><br>
		</c:if>
	  	</c:if>  	
		<!-- 多选 -->		  
	    <c:if test="${question.testType==2}">
	    <input type="checkbox" name="answer" value="${current.id}">  ${current.answerField} <br>	
	  	</c:if>  	
		<!--判断-->	  
	    <c:if test="${question.testType==3}">
	    <input type="hidden" name="question" value="${current.id}"></input>
	    <input type="radio" name="answer" value="1"  />正确 <br>
	    <input type="radio" name="answer" value="0"  />错误
	  	</c:if> 	
	</c:forEach>
	
	<!--匹配-->		 	
	<c:forEach items="${childQuestions }" var="childQuestion" varStatus="i">
		${childQuestion.questionField} 
		<select name="${childQuestion.id }">
			<c:forEach items="${childAnswers }" var="childAnswer" varStatus="i">			
  			<option value ="${childAnswer.id}">${childAnswer.answerField}</option>
			</c:forEach>
		</select>
		<br>
	</c:forEach>
	<!--匹配-->	
	<!-- 提交答案 -->
	<br>
	<!--提交-->
	<c:if test="${isAnswered=='Answered'}">
		已经回答过了
	</c:if>
	<c:if test="${isAnswered=='notAnswered'}">
		<input type="submit" value="提交本题答案">
	</c:if>		
	<input type="button" onclick="save();" value="交卷"></input>
	</form>
</div>
</div>

</div></div>
	</div>

	</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/resiz.js"></script>
