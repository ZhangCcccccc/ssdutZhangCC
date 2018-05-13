<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/lessonslist.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/innerpage.css"/>
<title>微课课程列表</title>
<style type="text/css" media="screen"> 
.tests{width:200!important;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;} 
</style>
</head>
<body>
	<div id="conteiner">
        <div id="header">
            <span>我的课程</span>
            <div id="homebtm" onclick="jump('${pageContext.request.contextPath}/tcoursesite/weike/index')">
                <img src="${pageContext.request.contextPath}/images/mobile/home.png">
            </div>
        </div>
        <%-- <div id="marker">
            <div onclick="jump('${pageContext.request.contextPath}/course')" class="markerdiv">全部课程</div>
            <div onclick="jump('${pageContext.request.contextPath}/course')" class="aimdiv">我的课程</div>
            <div onclick="jump('${pageContext.request.contextPath}/myCenter')" class="markerdiv">查看成绩</div>
            <div style="clear:both; display:none;"></div>
        </div> --%>
    	<div id="lessonslist">
	<c:forEach items="${courses}" var="current" varStatus="i"> 
	<tr>
		<div class="lesdiv" onclick="enterCourse(${current.id})" style="border-bottom: 0.2em solid #FFFFFF;">
               <img src="${pageContext.request.contextPath}${current.siteImage}">
                <div class="title">
                	<!-- <div class="tests"> -->
                		<strong>${current.title}</strong>
                	<!-- </div> -->
                	<br>
                	<div class="mes">
    				<%--<span >标签：--</span><br/>
    				<span class="period">课时：196 已学：84</span>
    				--%></div>
    			</div>
         </div>
	</tr>
	</c:forEach>
        <div id="backbutton" onClick="goback()">
        	返回顶部
        </div>
    </div>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/mobile/allpages.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/resiz.js"></script>
<script type="text/javascript" >
	function enterCourse(id){
		window.location.href="${pageContext.request.contextPath}/tcoursesite/weike/site?tCourseSiteId="+id;
	}
</script>