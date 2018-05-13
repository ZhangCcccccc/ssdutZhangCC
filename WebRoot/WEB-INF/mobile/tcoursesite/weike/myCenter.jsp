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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/myscore.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/innerpage.css"/>
<title>微课课程列表</title>
</head>
<body>
<div id="conteiner">
        <div id="header"><!-- ${pageContext.request.contextPath}/course/${course.id}?courseId=${course.id} -->
            <a href=""><span><input type="button" value="课时内容"/></span></a>
           <a href="${pageContext.request.contextPath}/myCenter"><span><input type="button" value="我的成绩"/></span></a>
            <div id="homebtm" onClick="jump('${pageContext.request.contextPath}/course')" >
                <img src="${pageContext.request.contextPath}/images/mobile/home.png"/>
            </div>
        </div>
        <div id="scorebox">
        	<div id="title">
        		<!-- <span>章节</span> -->
                <span>测试名称</span>
                <span>成绩</span>
            </div>
            <!-- 遍历成绩 -->
			<p><!-- <span>测试名称</span> <span>成绩</span> --></p>
				<c:forEach items="${gbs}" var="gb" varStatus="i">
		 <div id="scorebox">
            	<div class="scorelists grey">
                <div class="testname">
                	${gb.test.name}
                </div>
                <div class="score">
                	${gb.score}
                </div>
            	</div>
            </div>
				</c:forEach>
        		</div>
        <div id="backbutton" onClick="goback()">
        	返回顶部
        </div>
    </div></body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/allpages.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/resiz.js"></script>