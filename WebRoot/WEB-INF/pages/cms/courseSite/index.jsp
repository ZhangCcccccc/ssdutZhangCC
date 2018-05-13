<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>庚商教学平台</title>
    <meta name="decorator" content="none">
    <meta name="Keywords" content="庚商教学平台">
    <meta name="Description" content="庚商教学平台">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index/global_min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.glide.js"></script>


</head>
<% 
   //前端登录标记
   session.setAttribute("LOGINTYPE","CMS");  
%> 
<body>
    <div class="header_bg">
        <div class="header">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/index/logo.png" />
            </div>
            <ul class="menu_container">
                <li class="select">
                    <a href="${pageContext.request.contextPath}/cms/index" target=_self>首页</a>
                </li>
                 <c:if test="${!empty user}">
                <li>
                    <a href="${pageContext.request.contextPath}/cms/courseList" target=_self>课程列表</a>
                </li>
                </c:if>
                <li>
                    <a href="javascript:void(0);">关于我们</a>
                </li>
                <c:if test="${!empty user}">
                <li>
                    <a href="${pageContext.request.contextPath}/cms/myCourseList" target=_self>我的选课</a>
                </li>
                </c:if>
                
            </ul>
            <c:if test="${empty user}">
            <div class="right_container">
                <form >
                    <input id="search" name="keyword" value="搜索你喜欢的" onfocus="this.value='';" onblur="if(this.value==''){this.value='搜索你喜欢的'}" />
                    <input class="login_btn" type="button" value="登录" />
                </form>
            </div>
            </c:if>
            <c:if test="${!empty user}">
            <div class="right_container">
                <div style="text-align:right;font-size:12px;">
                    <%
					   if(session.getAttribute("selected_role").toString().equals("ROLE_TEACHER")){
					%>
     				    <font style="color:#fff;">${user.cname} 老师您好  </font>
     				<%}else if(session.getAttribute("selected_role").toString().equals("ROLE_STUDENT")){%>
     				    <font style="color:#fff;">${user.cname} 同学您好  </font>
     				<%}else if(session.getAttribute("selected_role").toString().equals("ROLE_FAMILYMEMBER")){%>
 				    <font style="color:#fff;">${user.cname} &nbsp; &nbsp;您好  </font>
 				<%}%>
     			   <a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp" target="_parent"><font color=write><font style="color:#fff;">退出</font> </a>  
                </div> 
            </div>
            </c:if>
            <form id="subform" name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'> 
            <div class="log-in">
                <div class="log_in_box">
                    <img class="close" src="${pageContext.request.contextPath}/images/index/msg_close.png" />
                    <font color="red" size=5><b>庚商实践教学</b></font>
                    <div class="sat_name"><span>用户登录</span>
                    </div>
                    <fram>
                        <div class="username-box">
                            <input type="text"  name="j_username"  placeholder="用户账号" />
                        </div>
                        <div class="password-box">
                            <input type="password" name="j_password"  placeholder="密码" />
                        </div>
                        <div class="log-in-box">
                            <input type="submit" value="登录" />
                        </div>
                        
     
      	<%--<div class="box" >
      		本版本更新内容：1.修复空文件夹不能删除bug2.增加学生附件单独下载功能3.修改部分文字
      		
      	</div>

                    --%></fram>
                </div>
            </div>
            </form>
        </div>
    </div>
    <div class="slider">
        <ul class="slider__wrapper">
            <li class="slider__item">
                <div class="box">
                    <img src="${pageContext.request.contextPath}/images/index/slide.png">
                </div>
            </li>
            <li class="slider__item">
                <div class="box">
                    <img src="${pageContext.request.contextPath}/images/index/slide2.png">
                </div>
            </li>
        </ul>
    </div>
    <div class="ad_container">
        <ul class="ad">
            <li>
                <div class="ad_pic_box">
                    <img src="${pageContext.request.contextPath}/images/index/img_1.png" class="ad_pic">
                    <div class="notice"><img src="${pageContext.request.contextPath}/images/index/wek_1.png">
                    </div>
                </div>
            </li>
            <li>
                <div class="ad_pic_box">
                    <img src="${pageContext.request.contextPath}/images/index/img_2.png" class="ad_pic">
                    <div class="notice"><img src="${pageContext.request.contextPath}/images/index/wek_2.png">
                    </div>
                </div>
            </li>
            <li>
                <div class="ad_pic_box">
                    <img src="${pageContext.request.contextPath}/images/index/img_3.png" class="ad_pic">
                    <div class="notice"><img src="${pageContext.request.contextPath}/images/index/wek_3.png">
                    </div>
                </div>
            </li>
            <li>
                <div class="ad_pic_box">
                    <img src="${pageContext.request.contextPath}/images/index/img_4.png" class="ad_pic">
                    <div class="notice"><img src="${pageContext.request.contextPath}/images/index/wek_4.png">
                    </div>
                </div>
            </li>
        </ul>
        <div class="ad_decorate">

        </div>
    </div>
    <%-- <div class="introduce_container">
        <div class="intro intro1">
            <div class="intro1_text hide_text">
                <img src="${pageContext.request.contextPath}/images/index/dis_1.png" class="intro1_text_dis">
            </div>
            <div class="computer_dis">
                <img src="${pageContext.request.contextPath}/images/index/dis.png">
            </div>
        </div>

        <div class="intro intro2">
            <div class="computer_test">
                <img src="${pageContext.request.contextPath}/images/index/test.png">
            </div>
            <div class="boy_test">
                <img src="${pageContext.request.contextPath}/images/index/boy.png">
            </div>
            <div class="sun_test">
                <img src="${pageContext.request.contextPath}/images/index/sun.png">
            </div>
            <div class="shadow_test">
                <img src="${pageContext.request.contextPath}/images/index/shadow.png">
            </div>
            <div class="intro2_text hide_text">
                <img src="${pageContext.request.contextPath}/images/index/test1.png" class="intro1_text_dis">
            </div>
        </div>

        <div class="intro intro3">
            <div class="intro3_text hide_text">
                <img src="${pageContext.request.contextPath}/images/index/step1.png" class="intro1_text_dis">
            </div>
            <div class="computer_step">
                <img src="${pageContext.request.contextPath}/images/index/step.png">
            </div>
            <div class="trophy_step">
                <img src="${pageContext.request.contextPath}/images/index/trophy.png">
            </div>
            <div class="star_stype">
                <img src="${pageContext.request.contextPath}/images/index/star.png" class="star1">
                <img src="${pageContext.request.contextPath}/images/index/star.png" class="star2">
                <img src="${pageContext.request.contextPath}/images/index/star.png" class="star3">
                <img src="${pageContext.request.contextPath}/images/index/star.png" class="star4">
            </div>
        </div>
    </div> --%>
    <div class="courses_container">
        <div class="courses_bar"><img src="${pageContext.request.contextPath}/images/index/courses.png">
        </div>
        <div class="courses_list">
            <ul>
            	<c:forEach items="${sites}" var="site" varStatus="i">
            	<li>
                    <div class="courses_box">
                        <div class="courses_pic">
                            <img src="${pageContext.request.contextPath}/${site.siteImage}">
                        </div>
                        <div class="courses_title_container">
                            <h4>${site.title}</h4>
                            <!-- <span class="">24天前更新</span>
                            <span class="message">9999</span>
                            <span class="mumber">9999</span> -->
                        </div>
                        <div class="courses_intro">
                            <div class="teacher">
                                <div class="teacher_pic">
                                    <img src="${pageContext.request.contextPath}/${site.teacherImage}">
                                </div>
                                <div class="teacher_intro">
                                    <div class="teacher_name">授课教师：${site.userByCreatedBy.cname}</div>
                                    <div class="job_description"><!-- 复旦大学 副教授 --></div>
                                </div>
                            </div>
                            <div class="intro_courses">
                                ${site.description}
                            </div>
                        </div>
                        
                        <c:if test="${site.title!='人体解剖学实验课'}">
	                        <div class="courses_intro_tit">
	                            <a href="${pageContext.request.contextPath}/cms/courseSite?id=${site.id}" target="blank">
	                                <span class="course_tit">进入站点</span><span class="go"></span>
	                            </a>
	                        </div>
                        </c:if>
                          <c:if test="${site.title=='人体解剖学实验课'}">
	                        <div class="courses_intro_tit">
	                            <a href="${pageContext.request.contextPath}/cms/courseSiteNew" target="blank">
	                                <span class="course_tit">进入站点</span><span class="go"></span>
	                            </a>
	                        </div>
                        </c:if>
                    </div>
                </li>
            	</c:forEach>
                
                
                
            </ul>
            <!-- 更多课程 -->
            <!-- <div class="more_courses">
                <a href="javascript:void(0);"></a>
            </div> -->
        </div>
    </div>
    <div class="footer_container">
        <div class="footer">
            <div class="copyright">Copyright ©2015 庚商教育智能科技 All Rights Reserved 沪ICP备14016833号</div>
            <div class="power">庚商实训教学系统 power by <a href="www.gvsun.com">Gvsun</a>
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
