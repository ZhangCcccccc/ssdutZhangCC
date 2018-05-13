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
<style>
	.iStyle_Iframe{
		margin:0px;
	}
</style>
</head>
<body>

<div class="iStyle_Conteiner">
<div class="iStyle_RightInner">

<div class="iStyle_Searchfeild">
<div class="iStyle_Tagsfeild"></div>
<div class="iStyle_Marksfeild">
<c:if test="${courseCode ==-1  }">
     <div class="iStyle_Mark iStyle_ActiveMark" src="${pageContext.request.contextPath}/timetable/selfTimetable/doSelectCourseCode?term=${term}&weekday=${weekday}&classids=${classids}&courseCode=${courseCode}&labroom=${labroom}"><span>选择选课组</span></div>
</c:if>
<c:if test="${courseCode !=-1  }">
     <c:if test="${groups ==-1 }">
     <div class="iStyle_Mark iStyle_ActiveMark" src="${pageContext.request.contextPath}/timetable/selfTimetable/doIframeNoGroupSelfTimetable?term=${term}&weekday=${weekday}&classids=${classids}&courseCode=${courseCode}&labroom=${labroom}&tableAppId=0"><span>不分批排课</span></div>
     <div class="iStyle_Mark" src="${pageContext.request.contextPath}/timetable/selfTimetable/doIframeGroupSelfTimetable?term=${term}&weekday=${weekday}&classids=${classids}&courseCode=${courseCode}&labroom=${labroom}"><span>分批排课</span></div>
     </c:if>
     <c:if test="${groups > 0 }">
     <div class="iStyle_Mark" src="${pageContext.request.contextPath}/timetable/selfTimetable/doIframeGroupSelfTimetable?term=${term}&weekday=${weekday}&classids=${classids}&courseCode=${courseCode}&labroom=${labroom}"><span>分批排课</span></div>
     </c:if>
     <c:if test="${groups ==0 }">
     <div class="iStyle_Mark iStyle_ActiveMark" src="${pageContext.request.contextPath}/timetable/selfTimetable/doIframeNoGroupSelfTimetable?term=${term}&weekday=${weekday}&classids=${classids}&courseCode=${courseCode}&labroom=${labroom}&tableAppId=0"><span>不分批排课</span></div>
     </c:if>
</c:if>
</div>
</div>

<div class="iStyle_Iframe">
<!--  -->
<c:if test="${courseCode !=-1  }">
     <c:if test="${groups ==-1 }">
     <iframe scrolling="yes" src="${pageContext.request.contextPath}/timetable/selfTimetable/doIframeNoGroupSelfTimetable?term=${term}&weekday=${weekday}&classids=${classids}&courseCode=${courseCode}&labroom=${labroom}&tableAppId=0" style="width:1000px;height:550px;">
     </iframe>     
     </c:if>
     <c:if test="${groups > 0 }">
     
     <iframe scrolling="yes" src="${pageContext.request.contextPath}/timetable/selfTimetable/doIframeGroupSelfTimetable?term=${term}&weekday=${weekday}&classids=${classids}&courseCode=${courseCode}&labroom=${labroom}" style="width:1000px;height:550px;">
     </iframe>     </c:if>
     <c:if test="${groups ==0 }">
     <iframe scrolling="yes" src="${pageContext.request.contextPath}/timetable/selfTimetable/doIframeNoGroupSelfTimetable?term=${term}&weekday=${weekday}&classids=${classids}&courseCode=${courseCode}&labroom=${labroom}&tableAppId=0" style="width:1000px;height:550px;">
     </iframe>     
     </c:if>
</c:if>

<c:if test="${courseCode ==-1  }"> 
     <iframe scrolling="yes" src="${pageContext.request.contextPath}/timetable/selfTimetable/doSelectCourseCode?term=${term}&weekday=${weekday}&classids=${classids}&courseCode=${courseCode}&labroom=${labroom}" style="width:1000px;height:550px;">
     </iframe>
</c:if>
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