<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>无标题文档</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_LeftList.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_Searcher.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_SystemUI_Allpages.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">

</head>
<body>

<div class="iStyle_Conteiner">
<div class="iStyle_RightInner">
<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">排课</a>
			</li>
			<li class="end">
			排课安排
			</li>
		</ul>
	</div>
</div>
<div class="iStyle_Searchfeild">
<div class="iStyle_Tagsfeild"></div>
<div class="iStyle_Marksfeild">
     <div class="iStyle_Mark iStyle_ActiveMark" src="${pageContext.request.contextPath}/timetable/listTimetableAll?currpage=1&id=-1"><span>实验室课表</span></div>
     <div class="iStyle_Mark" src="${pageContext.request.contextPath}/timetable/listTimetableAppointmentTeacher"><span>教师课表</span></div>
     <div class="iStyle_Mark" src="${pageContext.request.contextPath}/timetable/listTimetableAppointmentCourse"><span>课程课表</span></div>
     
</div>

<div class="iStyle_Iframe">

     <iframe scrolling="yes" src="${pageContext.request.contextPath}/timetable/listTimetableAll?currpage=1&id=-1" style="width:1150px;height:500px;"></iframe>


</div>
</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_Icons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_LeftList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Downlist.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Allpages.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Interface.js"></script>
</body>
</html>
