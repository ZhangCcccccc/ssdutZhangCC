<br><%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<html>
<head>
<meta content="none" name="decorator">
	<meta charset="utf-8">
	<!--控制默认缩放大小、最小缩放大小、最大缩放大小。通过这个可以设置用户的最大和最小缩放程度。-->
	<meta name="viewport" content="width=device-width,user-scalable=no"/>
	<!--//表示手机模式设置为启用。-->
	<meta name="format-detection" content="telephone=no"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/allpages.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/lessonscontent.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/innerpage.css"/>
	<title>微课课程列表</title><script type="text/javascript">
</script>
</head>
<body >
	<div id="conteiner">
        <div id="header">
           <a class="course_content" href="#"><span>课时内容</span></a>
           <%-- <a href="${pageContext.request.contextPath}/myCenter">
           	<span><input type="button" value="我的成绩"/></span>
           	</a> --%>
            <div id="homebtm" onclick="jump('${pageContext.request.contextPath}/tcoursesite/weike/courses')">
                <img src="${pageContext.request.contextPath}/images/mobile/prev.png">
            </div>
        </div>
        <div id="lessonslist">
        <c:forEach items="${tCourseSite.wkChapters}" var="chapter">
        	<c:if test="${chapter.type==1}">
	        	<div class="chapter">
	             	章节<span>${chapter.seq}</span>${chapter.name}
	            </div>
	            <c:forEach items="${chapter.wkFolders}" var="folder" >
	            	<c:if test="${folder.type==1}">
	            		<div class="period" >
			            	视频<img src="${pageContext.request.contextPath}/images/mobile/accept.png" />
			            	
			            	<a class="title" href="${pageContext.request.contextPath}/tcoursesite/weike/video?tCourseSiteId=${tCourseSite.id}&folderId=${folder.id}">
			            	 ${folder.name}</a>
			            </div>
	            	</c:if>
	            </c:forEach>
	            <c:forEach items="${chapter.wkLessons}" var="lesson" >
	            	<c:forEach items="${chapter.wkFolders}" var="folder" >
		            	<c:if test="${folder.type==1}">
		            		<div class="period" >
				            	视频<img src="${pageContext.request.contextPath}/images/mobile/accept.png" />
				            	
				            	<a class="title" href="${pageContext.request.contextPath}/tcoursesite/weike/video?${folder.id}">
				            	 ${folder.name}</a>
				            </div>
		            	</c:if>
		            </c:forEach>
	            </c:forEach>
            </c:if>
           </c:forEach>
        </div>
        <div id="backbutton" onclick="goback()">
        	返回顶部
        </div>
    </div>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/allpages.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/resiz.js"></script>