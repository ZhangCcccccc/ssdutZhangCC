<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>实验实训教学平台</title>
    <meta name="decorator" content="none">
    <meta name="Keywords" content="西安电子科技大学微课">
    <meta name="Description" content="西安电子科技大学微课">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link style="type/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/tms/index/global_min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.glide.js"></script>


</head>
<%
	
	session.setAttribute("SITELOGINTYPE", "tms");
	System.out.println(session.getAttribute("LOGINTYPE"));
%>
<body>
    <div class="header_bg">
        <div class="header">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/index/logo_XDU.png" />
            </div>
            <ul class="menu_container">
                <li class="select">
                    <a href="/xidlims/self/myCenter" target=_self>首页</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/tms/courseList?currpage=1">课程列表</a>
                </li>
                <c:if test="${!empty user}">
                <li>
                   <a href="${pageContext.request.contextPath}/tms/myCourseList?currpage=1" >我的选课</a>
                </li>
                <%--<sec:authorize ifAnyGranted="ROLE_TEACHER">
                <li>
                   <a href="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1" >系统管理</a>
                </li>
                </sec:authorize>--%>
                <%-- <li >
                   <a href="${pageContext.request.contextPath}/visualization/show/index" >可视化</a>
                </li> --%>
                </c:if>
            </ul>
            <c:if test="${empty user}">
	            <div class="right_container">
	                <form>
	                    <input id="search" name="keyword" value="搜索你喜欢的" onfocus="this.value='';" onblur="if(this.value==''){this.value='搜索你喜欢的'}" />
	                    <input class="login_btn" type="button" value="登录" />
	                </form>
	            </div>
            </c:if>
            <c:if test="${!empty user}">
            <div class="right_container">
                <div style="text-align:right;font-size:12px;">
						<a href="/xidlims/self/myCenter"
							<font style="color:#fff;">首页</font>
						</a>
						<sec:authorize ifAnyGranted="ROLE_TEACHER">
							<a href="${pageContext.request.contextPath}/test?labCenterId=-1#/xidlims/personal/messageList?currpage=1"
								<font style="color:#fff;">返回系统</font>
							</a>
                		</sec:authorize>
					<%
						//前端登录标记
						session.setAttribute("SITELOGINTYPE", "index");
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
            <form id="subform" name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'> 
	            <div class="log-in">
	                <div class="log_in_box" >
	                    <img class="close" src="${pageContext.request.contextPath}/images/index/msg_close.png" />
	                    <img src="${pageContext.request.contextPath}/images/index/logo_a.png" />
	                    <div class="sat_name"><span>用户登录</span>
	                    </div>
	                    <fram>
	                        <div class="username-box">
	                            <input type="text" name="j_username" placeholder="用户账号" />
	                        </div>
	                        <div class="password-box">
	                            <input type="password" name="j_password" placeholder="密码" />
	                        </div>
	                        <div class="log-in-box">
	                            <input type="submit" value="登录"  />
	                        </div>
	                    </fram>
	                </div>
	            </div>
            </form>
        </div>
    </div>
    <div class="slider">
        <ul class="slider__wrapper">
            <li class="slider__item">
                <div class="box">
                    <img src="${pageContext.request.contextPath}/images/index/slide3.png">
                </div>
            </li>
            <li class="slider__item">
                <div class="box">
                    <img src="${pageContext.request.contextPath}/images/index/slide5.png">
                </div>
            </li>
            <li class="slider__item">
                <div class="box">
                    <img src="${pageContext.request.contextPath}/images/index/slide4.png">
                </div>
            </li>
            <li class="slider__item">
                <div class="box">
                    <img src="${pageContext.request.contextPath}/images/index/slide1.png">
                </div>
            </li>
        </ul>
    </div>
    <%--<div class="ad_container">
        <ul class="ad">
            <li>
                <div class="ad_pic_box">
                	<a href="http://www.gvsun.net:3380/tms/content?contentId=905" target="_blank">
	                    <img src="${pageContext.request.contextPath}/images/index/img_1.png" class="ad_pic">
	                    <div class="notice">
	                    	<div class="title_s1">智慧教学空间</div>
	                    	<div class="title_s2">Sapiential Teaching Space</div>
	                    </div>
                    </a>
                </div>
            </li>
            <li>
                <div class="ad_pic_box">
                	<a href="http://www.gvsun.net:3380/tms/content?contentId=904" target="_blank">
	                    <img src="${pageContext.request.contextPath}/images/index/img_2.png" class="ad_pic">
	                    <div class="notice">
	                    	<div class="title_s1">实验教学空间</div>
	                    	<div class="title_s2">Experimental Teaching Space</div>
	                    </div>
                    </a>
                </div>
            </li>
            <li>
                <div class="ad_pic_box">
                	<a href="http://www.gvsun.net:3380/tms/content?contentId=906" target="_blank">
	                    <img src="${pageContext.request.contextPath}/images/index/img_3.png" class="ad_pic">
	                    <div class="notice">
	                    	<div class="title_s1">创新创客空间</div>
	                    	<div class="title_s2">Innovative Maker Space</div>
	                    </div>
                    </a>
                </div>
            </li>
            <li>
                <div class="ad_pic_box">
                	<a target="_blank" href="http://www.gvsun.net:3380/tms/content?contentId=907">
	                    <img src="${pageContext.request.contextPath}/images/index/img_4.png" class="ad_pic">
	                    <div class="notice">
	                    	<div class="title_s1">实验教学空间</div>
	                    	<div class="title_s2">Experimental Research Space</div>
	                    </div>
                    </a>
                </div>
            </li>
        </ul>
        <div class="ad_decorate">

        </div>
    </div>--%>
    <div class="courses_container">
        <div class="courses_bar">
        	<a class="title3"  href="${pageContext.request.contextPath}/tms/courseList?currpage=1">课程列表</a>
        </div>
        <div class="courses_list">
            <ul>
            	<c:forEach items="${allsites}" var="site" end="4" varStatus="i">
	                <li class="ml1">
	                    <div class="courses_box">
	                        <div class="courses_pic">
	                            <img src="${pageContext.request.contextPath}/${site.siteImage}">
	                        </div>
	                        <div class="courses_title_container">
	                            <h4>${site.title}</h4>
	                        </div>
	                        <div class="courses_intro">
	                            <div class="teacher">
	                                <div class="teacher_pic">
	                                    <img src="${pageContext.request.contextPath}/${site.teacherImage}">
	                                </div>
	                                <div class="teacher_intro">
	                                    <div class="teacher_name">
	                                        	${site.userByCreatedBy.cname}
	                                    </div>
	                                    <div class="job_description"></div>
	                                </div>
	                            </div>
	                            <div class="intro_courses">
	                                            ${site.description}
	                            </div>                            
	                        </div>
	                        <div class="courses_intro_tit">
	                            <a href="${pageContext.request.contextPath}/tcoursesite?tCourseSiteId=${site.id}" target="self">
	                                <span class="course_tit">进入站点</span>
	                                <div class="arrow">
	                                	<span>＞</span>
	                                </div>
	                            </a>
	                        </div>
	                    </div>
	                </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="footer_container">
        <div class="footer">
            <div class="copyright">Copyright ©2016 西安电子科技大学 All Rights Reserved</div>
            <div class="power">XDU微课 power by<a href="www.gvsun.com">Gvsun</a>
            </div>
        </div>
    </div>
    <div class="top">
        <img src="${pageContext.request.contextPath}/images/index/top.png">
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/global.js"></script>
    <script type="text/javascript">
        
    </script>
</body>
</html>