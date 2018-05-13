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
<title>课程平台</title>
		<style type="text/css" media="screen">
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css");
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css");
			@import url("${pageContext.request.contextPath}/css/style.css");
		</style>
    <link style="type/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/tms/index/global_min.css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
</head>
<decorator:head></decorator:head>
<body>
<!-- 菜单栏开始  -->
    <div class="header_bg">
        <div class="header">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/index/logo.png" />
            </div>
            <ul class="menu_container">
                <li>
                    <a href="${pageContext.request.contextPath}/tms/index">首页</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/tms/courseList?currpage=1">课程列表</a>
                </li>
                <c:if test="${!empty user}">
	                <li>
	                    <a href="${pageContext.request.contextPath}/tms/myCourseList?currpage=1">我的选课</a>
	                </li>
                </c:if>
                <%--<sec:authorize ifAnyGranted="ROLE_TEACHER">
                <li class="select">
                   <a href="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1" >系统管理</a>
                </li>
                </sec:authorize>--%>
                <li >
                   <a href="${pageContext.request.contextPath}/visualization/show/index" >可视化</a>
                </li>
            </ul>
            <c:if test="${!empty user}">
            <div class="right_container">
                <div style="text-align:right;font-size:12px;">
						<a href="${pageContext.request.contextPath}/cms/cindex"
							<font style="color:#fff;">首页</font>
						</a>
						<sec:authorize ifAnyGranted="ROLE_TEACHER">
							<a href="${pageContext.request.contextPath}/test?labCenterId=-1#/xidlims/personal/messageList?currpage=1"
								<font style="color:#fff;">返回系统</font>
							</a>
                		</sec:authorize>
					<%
						//前端登录标记
						//session.removeAttribute("LOGINTYPE");
					%>
                    <%
					   if(session.getAttribute("selected_role").toString().equals("ROLE_TEACHER")){
					%>
     				    <font style="color:#fff;">${user.cname} 老师您好  </font>
     				<%}else if(session.getAttribute("selected_role").toString().equals("ROLE_STUDENT")){%>
     				    <font style="color:#fff;">${user.cname} 同学您好  </font>
     				<%}%>
     			   <a href="${pageContext.request.contextPath}/pages/logout-redirect.jsp" target="_parent"><font color=write><font style="color:#fff;">退出</font> </a>  
                </div> 
            </div>
            </c:if>
        </div>
    </div>

<!-- 菜单栏结束  -->


<decorator:body></decorator:body>

<!-- 页脚开始  -->
    <div class="footer_container">
        <div class="footer">
            <div class="copyright">Copyright ©2014 庚商教育智能科技 All Rights Reserved 沪ICP备14016833号</div>
            <div class="power">庚商微课 power by <a href="www.gvsun.com">Gvsun</a>
            </div>
        </div>
    </div>
    <div class="top">
        <img src="${pageContext.request.contextPath}/images/index/top.png">
    </div>

<!-- 页脚结束  -->

</body>
</html>
