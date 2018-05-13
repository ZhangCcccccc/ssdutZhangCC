<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="decorator" content="none">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上海中医药大学解剖实验课</title>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/songProject/style.css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/browse.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">

</script>
<decorator:head></decorator:head>
</head>

<!-- logo开始  -->
<div id="top">
   <div class="logo"><img src="${pageContext.request.contextPath}/images/songProject/logo1.png" /></div>
</div>
<!-- logo结束  -->

<!-- 菜单栏开始  -->
<div id="menu">
   <ul>
      <li><a href="${pageContext.request.contextPath}/cms/courseSiteNew">首页</a></li>
      <li><a href="${pageContext.request.contextPath}/cms/channel/findChannelList?tagId=12">师资队伍</a></li>
      <li><a href="${pageContext.request.contextPath}/cms/channel/findChannelList?tagId=13">课程资源</a></li>
      <li><a href="${pageContext.request.contextPath}/cms/channel/findChannelList?tagId=9" <c:if test="${empty loginUser }">onclick="alert('请先登录');return false;"</c:if>>申请表</a></li>
      <li><a href="${pageContext.request.contextPath}/cms/teaching/assignment/index">实验报告管理</a></li>
      <c:if test="${!empty loginUser && fn:contains(loginUser.authorities,'STUDENT') }">
	      <li><a href="${pageContext.request.contextPath}/cms/teaching/exam/index?cid=-1">题库与测试</a></li>
      </c:if>
      <li><a href="${pageContext.request.contextPath}/wk/CMS/weikeList">微课资源</a></li>
      <%-- <li><a href="${pageContext.request.contextPath}/cms/courseList">实验教学平台</a></li> --%>
      <li><a href="${pageContext.request.contextPath}/wk/CMS/fileList">实验互动平台</a></li>
      <li><a href="">数字解剖平台</a></li>
      <li><a href="${pageContext.request.contextPath}/cms/donation/index">关于遗体捐献</a></li>
	</ul>
</div>
<!-- 菜单栏结束  -->


<decorator:body></decorator:body>

<!-- 页脚开始  -->

<div id="footer_content0">
   <div id="footer_content1">
      <div class="footer_logo"><img src="${pageContext.request.contextPath}/images/songProject/logo2.png" /></div>
      <div class="footer_font"><p>2015 上海中医药大学&nbsp;&nbsp;版权所有&nbsp;&nbsp;2015&nbsp;Shanghai University of T.C.M All rights reserved</p>
        <p>地址：上海市浦东新区蔡伦路1200号&nbsp;&nbsp;上海中医药大学人体解剖学教研室&nbsp;&nbsp;邮编：201203</p></div>
	</div>
</div>

<!-- 页脚结束  -->


</body>
</html>
