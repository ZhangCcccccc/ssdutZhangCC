<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>讨论</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!-- import css-->
	<link href="${pageContext.request.contextPath}/css/video.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	if (document.getElementById){
		document.write('<style type="text/css">\n')
		document.write('.submenu{display: none;}\n')
		document.write('</style>\n')
	}

	function SwitchMenu(obj){
		if(document.getElementById){
			var el = document.getElementById(obj);
			var ar = document.getElementById("masterdiv").getElementsByTagName("ul"); 
			if(el.style.display != "block"){
				for (var i=0; i<ar.length; i++){
				if (ar[i].className=="submenu") //DynamicDrive.com change
				ar[i].style.display = "none";
				}
				el.style.display = "block";
				}else{
		el.style.display = "none";
			}
	}
	}
</script>
<style type="text/css">

body,td,th {
font-size: 12px;
background:#f0f0f0;
}
.pluns {width:800px;margin:0 auto;background:#fff;border:solid 1px #e6e6e6;}
.plunst {color:#1e50a2;line-height:32px;margin:1px;padding:0 8px 0 8px;}
.plunst span {float:right;color:#666666;}
.plunsb {padding:5px 8px 8px 8px;line-height:18px;margin:1px;}

.plung {margin:5px;background:#ffffee;border:solid 1px #999999;}
.plungt {color:#1e50a2;line-height:32px;margin:1px;padding:0 8px 0 8px;}
.plungt span {float:right;color:#666666;}
.plungb {padding:5px 8px 8px 8px;line-height:18px;margin:1px;}
</style>
</head>
  
<body>
<div>

	<div class="arbd">
	<div class="kcclist">
		<div class="kcc">	
			<ul>
			<li>
				<div class="kccimg">
			<img src="${pageContext.request.contextPath}${course.logoUrl}" >
				</div>
			<div class="kcmass">
				<h1><a href="#">${course.name}</a></h1>
			<h4><a href="#">作者：</a></h4>
				</div>
			</li></ul></div>
		</div>
	<div class="kccv">
	<div class="kcleftl">
	<div id="masterdiv">
	<c:forEach items="${course.chapters}" var="chapter">		
		<div  class="menutitle"  onclick="SwitchMenu('sub${chapter.id}')"><img src="images/m.png">章节${chapter.seq}： ${chapter.name}</div>
		<div>
		<ul class="submenu" id="sub${chapter.id}">
			<c:forEach items="${chapter.lessons}" var="lesson" >
			<li><a href="lesson/${lesson.id}/learn">课时${lesson.seq}：${lesson.title}</a> </li>
			</c:forEach>
		</ul>
		</div>
	</c:forEach>					
	</div>
	</div>

	<div class="kcrightl">
		<div class="kct">
		<ul>
			<li ><a href="${pageContext.request.contextPath}/lesson/${lesson.id}/learn" class="video">视频</a></li>
			<li ><a href="${pageContext.request.contextPath}/lesson/${lesson.id}/testStart" class="test">做题</a></li>
			<li ><a href="${pageContext.request.contextPath}/lesson/${lesson.id}/deep" class="read">扩展阅读</a></li>
			<li ><a href="${pageContext.request.contextPath}/lesson/${lesson.id}/discuss" class="discuss">讨论区</a></li>
		</ul>
		</div>
		<div class="kctv"> 
		
	<div class="kccon">
	
	<!-- 遍历每个父评论 -->
	<c:forEach items="${parentDiscusses}" var="parentDiscusse" >
		<div class="plunst">
		<span><fmt:formatDate dateStyle="short" type="both" value="${parentDiscusse.discussTime.time}"/>发</span>
		网友 ip：123.129.*.*：
		</div> 
	
	
	<c:forEach items="${childDiscusses}" var="childDiscuss" >
		<c:if test="${childDiscuss.topDiscuss==parentDiscusse.id}">
			<div class="plung">
		</c:if>		
	</c:forEach>
	
	<c:forEach items="${childDiscusses}" var="childDiscuss" >
		<c:if test="${childDiscuss.topDiscuss==parentDiscusse.id}">
		<div class="plungt">
  		<span><fmt:formatDate dateStyle="short" type="both" value="${childDiscuss.discussTime.time}"/>发</span>
 		网友 ip：123.129.*.*：
		</div>
		<p class="plungb">
   		${childDiscuss.content}
		</p>
		</div>
		</c:if>
	</c:forEach>
	
		<p class="plunsb">${parentDiscusse.content} <a> 回复(回复已有评论，未做)</a></p>
		
	</c:forEach>
<br>
	<div class="discussform" >
	<!-- 新回复输入框 -->
	<form:form action="${pageContext.request.contextPath}/newParentDiscuss" method="POST" modelAttribute="discuss">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
		<tr><td><form:textarea id="discuss_content" path="content"  /></td>
		</tr>
		<form:hidden path="lessonId" value="${lesson.id}"/>
		</tbody>
		</table>
		<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="新帖子"/></span>
	</form:form>
	<!-- 新回复输入框 -->
	</div>
	
	
		
	</div>
		</div>
	</div>

	</div>


		<div id="mobile_footer">
			
		</div>
	
	</div>
</div>

	


</body>
</html>
