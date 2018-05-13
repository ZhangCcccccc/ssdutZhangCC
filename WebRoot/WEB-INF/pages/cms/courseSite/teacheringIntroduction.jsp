<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>


<html>

<head>
    <title>教学介绍</title>
    <meta name="decorator" content="none"/>
    
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    
    <link href="${pageContext.request.contextPath}/css/courseSite/header_footer.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/courseSite/course.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/animatecolor-plugin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/jquery.animate-shadow.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/jquery.autosize.js"></script>

</head>

<body>
    <div class="header">
        <div class="header_box">
            <div class="logo_box">
                <img src="${pageContext.request.contextPath}/images/courseSite/logo.png" class="logo_pic">
            </div>
            <div class="nav_box">
                <ul>
                    <li><a href="javascript:void(0);">题库管理</a>
                    </li>
                    <li><a href="javascript:void(0);">实验课程资源管理</a>
                    </li>
                    <li><a href="javascript:void(0);">基础数据管理</a>
                    </li>
                    <li class="nav_selected"><a href="javascript:void(0);">我的课程</a>
                    </li>
                </ul>
            </div>
            <div class="user_box_top">
                <img src="${pageContext.request.contextPath}/images/courseSite/user.png">
            </div>
        </div>
    </div>
    <div class="header_img">
        <div class="course_img"><img src="${pageContext.request.contextPath}/images/courseSite/img1.png"> </div>
        <div class="course_explain">
            <div>
                <p class="courde_name">${course.title}</p>
                <p class="courde_teacher">讲师：${course.userByCreatedBy.cname}</p>
                <p class="courde_state">${course.description}</p>
            </div>
            <div class="tool">
                <div class="apply"><a href="javascript:void(0);">课程报名</a>
                </div>
                <div class="evaluate"><a href="javascript:void(0);">课程评价</a>
                </div>
                <div class="evaluate_star">
                    <div class="gray"><img src="${pageContext.request.contextPath}/images/courseSite/star_gray.png">
                    </div>
                    <div class="color" style="width:89px"><img src="${pageContext.request.contextPath}/images/courseSite/star_color.png">
                    </div>

                </div>
                <div class="score"> 4.2 <span>（20人评价）</span>
                </div>

            </div>
            <div class="share_code">
                <div class="share"><img src="${pageContext.request.contextPath}/images/courseSite/share.png">
                </div>
                <div class="code"><img src="${pageContext.request.contextPath}/images/courseSite/2_code.png">
                </div>

                <div>
                    <!-- JiaThis Button BEGIN -->
                    <div class="jiathis_style">
                        <a class="jiathis_button_qzone"></a>
                        <a class="jiathis_button_tsina"></a>
                        <a class="jiathis_button_tqq"></a>
                        <a class="jiathis_button_weixin"></a>
                        <a class="jiathis_button_renren"></a>
                    </div>
                    <!-- JiaThis Button END -->
                    <div class="big_code_pic">
                        <img src="${pageContext.request.contextPath}/images/courseSite/img8.png">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="discuss_content_box">
            <div class="crumbs_nav">
                <a href="${pageContext.request.contextPath}/cms/courseSite?id=${course.id}">课程主页</a>
                <a href="javascript:void(0);">${channel.channelName}</a>
            </div>
            <div class="article_container">
                <div class="right_content_article">
                	<c:forEach items="${channel.TCourseSiteArticals}" var="artical" varStatus="i">
                	<c:if test="${i.count==1}">
                	${artical.content}
                	</c:if>
                	</c:forEach>
                </div>

            </div>
        </div>
        <div class="footer">
            <p>Copyright ©2014 庚商教育智能科技 All Rights Reserved 沪ICP备14016833号 </p>
            <p>电话：021-54244128 传真：021-54188185 电子邮箱：gs@gengshang.com</p>
        </div>
    </div>
    <div class="top">
        <img src="${pageContext.request.contextPath}/images/courseSite/top.png">
    </div>

    <script type="text/javascript">
        $(".crumbs_nav a:last").css("background", "none")


        /* 分享和二维码*/
        $(".big_code_pic,.jiathis_style").hide()
        $(".code").click(
            function () {
                $(".big_code_pic").fadeToggle()
            }
        )
        $(".share").click(
                function () {
                    $(".jiathis_style").fadeToggle()
                }
            )
            /* 分享和二维码结束*/
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/style.js"></script>
    <script type="text/javascript" src="http://v3.jiathis.com/code_mini/jia.js" charset="utf-8"></script>
    <!--分享功能-->
</body>

</html>