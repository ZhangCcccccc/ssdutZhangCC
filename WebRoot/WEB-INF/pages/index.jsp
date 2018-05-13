<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
    <meta name="decorator" content="none">
    <title>gvsun</title>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/xidlims/index/jquery.fullPage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/xidlims/index/global_index.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/index/jquery.easing.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/index/jquery.fullPage.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/index/globle_main.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/index/animatecolor-plugin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/index/jquery.rotate.min.js"></script>
    <style>

#menu a { color: #333; text-decoration: none;}
#menu .active a { color: #89abe3; font-size: 24px;}
</style>
</head>
<body>
    <!--<ul id="menu">
	<li data-menuanchor="page1" class="active"><a href="#page1">第一屏</a></li>
	<li data-menuanchor="page2"><a href="#page2">第二屏</a></li>
	<li data-menuanchor="page3"><a href="#page3">第三屏</a></li>
	<li data-menuanchor="page4"><a href="#page4">第四屏</a></li>
</ul>-->
    <div class="header_container">
        <img src="${pageContext.request.contextPath}/images/xidlims/index/logo.png" class="logo_pic">
        <div class="menu_container">
            <img src="${pageContext.request.contextPath}/images/xidlims/index/menu.png" class="menu_pic">
            <img src="${pageContext.request.contextPath}/images/xidlims/index/close.png" class="close_pic">
            <div class="menu_box" >
                <ul class="menu_list" id="menu">
                    <li data-menuanchor="page1" class="active"><a class="cb bln" href="#page1">首页${user.username }</a></li>
                    <li data-menuanchor="page2"><a href="#page2">新闻${user.cname }</a></li>
                    <li data-menuanchor="page3"><a href="#page3">链接</a></li>
                    <!--<li><a href="javascript:void(0);">我们</a></li>
                    <li><a href="javascript:void(0);">链接</a></li>-->
                </ul>
            </div>
        </div>
    </div>
    <div id="dowebok">
        <div class="section section1">
            <div class="slide">
                <img src="${pageContext.request.contextPath}/images/xidlims/index/slide.png">
            </div>
            <div class="slide">
                <img src="${pageContext.request.contextPath}/images/xidlims/index/slide1.png">
            </div>
            <div class="slide">
                <img src="${pageContext.request.contextPath}/images/xidlims/index/slide2.png">
            </div>
            <div class="slide">
                <img src="${pageContext.request.contextPath}/images/xidlims/index/slide3.png">
            </div>
        </div>
        <div class="section section2">
            <div class="content_container">
                <div class="left_contatiner">
                    <div class="title_container">
                        <div class="tit_en"><img src="${pageContext.request.contextPath}/images/xidlims/index/NEWS.png">
                        </div>
                        <div class="tit_cn">最新新闻</div>
                    </div>
                    <div class="more">
                        <a href="javascript:void(0);">更多</a>
                    </div>
                </div>
                <div class="right_container">
                    <div class="news_list">
                        <a href="javascript:void(0);">
                            <div class="date_container">
                                <div class="day">22</div>
                                <div class="year_month">2016-02</div>
                            </div>
                            <div class="news_content">
                                <div class="news_titile"><span class="title_decorate"></span>校党委十一届十一次全体会议及全校干部大会召开
                                </div>
                                <div class="news_info">
                                    2月21日上午，校党委十一届十一次全体会议及全校干部大会在本部敬贤堂召开。
                                </div>
                            </div>
                        </a>
                    </div>

                    <div class="news_list">
                        <a href="javascript:void(0);">
                            <div class="date_container">
                                <div class="day">18</div>
                                <div class="year_month">2016-02</div>
                            </div>
                            <div class="news_content">
                                <div class="news_titile"><span class="title_decorate"></span>中国第十四届JESSUP国际法模拟法庭比赛在我校开幕</div>
                                <div class="news_info">
                                    2月17日上午，中国第十四届Jessup国际法模拟法庭比赛在我校王健法学院东吴大讲堂隆重开幕。
                                </div>
                            </div>
                        </a>
                    </div>

                    <div class="news_list">
                        <a href="javascript:void(0);">
                            <div class="date_container">
                                <div class="day">01</div>
                                <div class="year_month">2016-02</div>
                            </div>
                            <div class="news_content">
                                <div class="news_titile"><span class="title_decorate"></span>FUNSOM研究院李述汤院士、刘庄教授入选2015年全球最具影响力科学思想...</div>
                                <div class="news_info">
                                    近日，汤森路透公司（Thomson Reuters）出版了“2015全球最有影响力科学思想科学家名录”（World’s Most Influential Scientific Minds 2015），我校功能纳米与软物质研究院（FUNSOM研究院）
                                </div>
                            </div>
                        </a>
                    </div>

                </div>
            </div>

            <!--<p>滚动到第二屏后的回调函数执行的效果</p>-->
        </div>
        <div class="section section3">
            <div class="content_container">
                <div class="link_container">
                    <div class="link_decoration"></div>
                    <div class="link_title">快速链接</div>
                    <ul class="link_list">
                        <li><a href="javascript:void(0);">耗材采购</a>
                        </li>
                        <li><a href="javascript:void(0);">物资采购</a>
                        </li>
                        <li><a href="javascript:void(0);">资产等级</a>
                        </li>
                        <li><a href="javascript:void(0);">安全培训</a>
                        </li>
                        <li><a href="javascript:void(0);">设备采购</a>
                        </li>
                    </ul>
                </div>
                <div class="link_container">
                    <div class="link_decoration"></div>
                    <div class="link_title">相关链接${test}</div>
                    <ul class="link_list">
                        <li><a href="javascript:void(0);">西安电子科技大学课堂实录</a>
                        </li>
                        <li><a href="javascript:void(0);">西安电子科技大学课程中心</a>
                        </li>
                        <li><a href="javascript:void(0);">医学部共享平台</a>
                        </li>
                        <li><a href="javascript:void(0);">网易公开课</a>
                        </li>
                        <li><a href="javascript:void(0);">中国大学视频公开课</a>
                        </li>
                    </ul>
                </div>
                <div class="link_container">
                    <div class="link_decoration"></div>
                    <div class="link_title">校内站点</div>
                    <ul class="link_list">
                        <li><a href="javascript:void(0);">耗材采购</a>
                        </li>
                        <li><a href="javascript:void(0);">物资采购</a>
                        </li>
                        <li><a href="javascript:void(0);">资产等级</a>
                        </li>
                        <li><a href="javascript:void(0);">安全培训</a>
                        </li>
                        <li><a href="javascript:void(0);">设备采购</a>
                        </li>
                    </ul>
                </div>
                <div class="link_container">
                    <div class="link_decoration"></div>
                    <div class="link_title">校外站点</div>
                    <ul class="link_list">
                        <li><a href="javascript:void(0);">西安电子科技大学课堂实录</a>
                        </li>
                        <li><a href="javascript:void(0);">西安电子科技大学课程中心</a>
                        </li>
                        
                        <li><a href="javascript:void(0);">网易公开课</a>
                        </li>
                        <li><a href="javascript:void(0);">中国大学视频公开课</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(".link_container").hover(
            function () {
                $(this).find(".link_decoration").stop().animate({
                    backgroundColor: '#89abe3'
                }, 100);
                $(this).find(".link_title").stop().animate({
                    color: '#89abe3'
                }, 100)
            },
            function () {
                $(this).find(".link_decoration").stop().animate({
                    backgroundColor: '#EBEDF0'
                }, 100);
                $(this).find(".link_title").stop().animate({
                    color: '#000'
                }, 100)
            }
        )
        $(".news_list").hover(
            function () {
                $(this).find(".date_container").stop().animate({
                    backgroundColor: '#89abe3',
                    color:'#fff'
                },200)

            },
            function () {
                $(this).find(".date_container").stop().animate({
                    backgroundColor: '#fff',
                    color:'#999a9b'
                },200)
            }
        )
        setInterval("TT()", 10);

        function TT() {
            var Height = $(window).height();
            $(".menu_box").height(Height);
        }
        $('.menu_pic').click(
            function(){
                $(this).delay(500).fadeOut(200);
                $(this).rotate({
                    angle: 0,
                 animateTo: 1080,
                 easing: $.easing.easeOutCubic
                });
                $('.close_pic').delay(500).fadeIn(200);
                $('.menu_box').animate({
                    right:"0px"
                })
            }
        );
        $('.close_pic').click(
            function(){
                $(this).delay(500).fadeOut(200);
                $(this).rotate({
                    angle: 0,
                 animateTo: 1080,
                 easing: $.easing.easeOutCubic
                });
                $('.menu_pic').delay(500).fadeIn(200);
                $('.menu_box').animate({
                    right:"-225px"
                })
            }
        )
    </script>

</body>

</html>