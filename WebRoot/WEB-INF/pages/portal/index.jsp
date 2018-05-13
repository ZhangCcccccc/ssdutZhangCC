<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <meta charset="utf-8" />
    <meta name="renderer" content="webkit" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>西安电子科技大学</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xidlims/index/style.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xidlims/index/jquery.fullPage.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xidlims/index/global_index.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/index/jquery.fullPage.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/index/jquery.easing.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/index/global_main.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/index/animatecolor-plugin.js"></script>
    <script type="text/javascript">
        setInterval("TT()", 10);
        function TT() {
            var Height = $(window).height();
            $(".menu_box").height(Height - 38);
        }
    </script>
</head>

<body>
    <div class="menu_container">
        <div class="menu_icon">
            <img src="${pageContext.request.contextPath}/images/xidlims/index/menu.png" class="icon_menu">
            
        </div>
        <ul id="menu" >
            <li data-menuanchor="page1" class="active"><a href="#page1">首页</a>
            </li>
            <li data-menuanchor="page2"><a href="#page2">报表</a>
            </li>
            <c:set var="count" value="0"></c:set>
            <c:forEach items="${cmsChannels }" var="cmsChannel" varStatus="i">
            	<li data-menuanchor="page${i.count+2 }"><a href="#page${i.count+2 }">${cmsChannel.title }</a>
            	</li>
            	<c:set var="count" value="${i.count+2 }"></c:set>
            </c:forEach>
            <li data-menuanchor="page${count+1 }"><a href="#page${count+1 }">链接</a>
            </li>
        </ul>
        <div class="menu_box" value="">
        	<div class="menu_left_box">
        		<img src="${pageContext.request.contextPath}/images/xidlims/index/new logo.png" class="menu_logo">
        	</div>
        	
    		<img src="${pageContext.request.contextPath}/images/xidlims/index/close.png" class="icon_close">
    		<img src="${pageContext.request.contextPath}/images/xidlims/index/sat name.png" class="sat_name">
        </div>
    </div>


    <div id="dowebok">
        <div class="section section1">
            <div class="content_container">
                <div class="slide">
                    <img src="${pageContext.request.contextPath}/images/xidlims/index/slider.png">
                </div>
                <div class="slide">
                    <img src="${pageContext.request.contextPath}/images/xidlims/index/slider1.png">
                </div>

                <div class="sites_name_container">
                    <img src="${pageContext.request.contextPath}/images/xidlims/index/new%20logo.png">
                    <p>
                        <img src="${pageContext.request.contextPath}/images/xidlims/index/sat_name.png">
                </div>
                <div class="motto_box">
                	<img src="${pageContext.request.contextPath}/images/xidlims/index/motto.png" > 
                </div>
            </div>
        </div>
		<div class="section section2">
            <div class="content_container">


                <div class="chart_container">
                	<div class="charts_box">
                		<div class="charts_name">
                			<img src="${pageContext.request.contextPath}/images/xidlims/index/form.png">
                			<p>课程报表</p>
                		</div>
                		<ul class="charts_list">
                			<li><a href="javascript:void(0);">2015-2016学年度第一学期实验教学日历</a></li>
                			<li><a href="javascript:void(0);">PTC尝味能力检测</a></li>
                			<li><a href="javascript:void(0);">2014-2015学年度第一学期实验教学日历</a></li>
                			<li><a href="javascript:void(0);">2014-2015学年度第二学期实验教学日历</a></li>
                			<li><a href="javascript:void(0);">血型鉴定</a></li>
                		</ul>                		
                	</div>
                	<div class="charts_box">
                		<div class="charts_name">
                			<img src="${pageContext.request.contextPath}/images/xidlims/index/form.png">
                			<p>课程报表</p>
                		</div>
                		<ul class="charts_list">
                			
                			<li><a href="javascript:void(0);">2014-2015学年度第一学期实验教学日历</a></li>
                			<li><a href="javascript:void(0);">2014-2015学年度第二学期实验教学日历</a></li>
                			<li><a href="javascript:void(0);">血型鉴定</a></li>
                			<li><a href="javascript:void(0);">2015-2016学年度第一学期实验教学日历</a></li>
                			<li><a href="javascript:void(0);">PTC尝味能力检测</a></li>
                		</ul>                		
                	</div>
                	<div class="charts_box">
                		<div class="charts_name">
                			<img src="${pageContext.request.contextPath}/images/xidlims/index/form.png">
                			<p>课程报表</p>
                		</div>
                		<ul class="charts_list">
                			<li><a href="javascript:void(0);">2015-2016学年度第一学期实验教学日历</a></li>
                			<li><a href="javascript:void(0);">PTC尝味能力检测</a></li>
                			
                			<li><a href="javascript:void(0);">血型鉴定</a></li>
                			<li><a href="javascript:void(0);">2014-2015学年度第一学期实验教学日历</a></li>
                			<li><a href="javascript:void(0);">2014-2015学年度第二学期实验教学日历</a></li>
                		</ul>                		
                	</div>
                	
                </div>
            </div>
        </div>
        <c:set var="count" value="0"></c:set>
        <c:forEach items="${cmsChannels }" var="cmsChannel" varStatus="i">
        	<div class="section section${i.count+2 }">
       			<div class="content_container">
	                <div class="chart_container">
	                	<c:forEach items="${cmsChannel.cmsChannels }" end="3" var="childrenChannel" varStatus="j">
		                	
		                	<div class="charts_box">
		                		<div class="charts_name">
		                			<img src="${pageContext.request.contextPath}/images/xidlims/index/form.png">
		                			<a href="${pageContext.request.contextPath}/cms/channel/findBrotherCmsChannelList?id=${childrenChannel.id}"><p>${childrenChannel.title }</p></a>
		                		</div>
		                		<ul class="charts_list">
		                			<c:forEach items="${childrenChannel.cmsArticles }" end="4" var="cmsArticle">
		                				<li><a href="javascript:void(0);">${cmsArticle.title }</a></li>
		                			</c:forEach>
		                		</ul>                		
		                	</div>
       					</c:forEach>
	                </div>
	            </div>
	        </div>
	        <c:set var="count" value="${i.count+2 }"></c:set>
        </c:forEach>
        <div class="section section${count+1 }">
            <div class="content_container">
                <div class="link_container">
                	<div class="link_box">
                		<div class="charts_name">
                			<p class="decorate_line"></p>
	                       <p>快速链接</p>
	                	</div>
	                	<ul class="charts_list">
	                		<li><a href="javascript:void(0);">材料申购</a></li>
	                		<li><a href="javascript:void(0);">物资采购</a></li>
	                		<li><a href="javascript:void(0);">资产等级</a></li>
	                		<li><a href="javascript:void(0);">安全培训</a></li>
	                		<li><a href="javascript:void(0);">设备采购</a></li>
	                	</ul>
                	</div>
                	<div class="link_box">
                		<div class="charts_name">
                			<p class="decorate_line"></p>
	                			<p>相关链接</p>
	                	</div>
	                	<ul class="charts_list">
	                		<li><a href="javascript:void(0);">西安电子科技大学课堂实录</a></li>
	                		<li><a href="javascript:void(0);">西安电子科技大学课堂中心</a></li>
	                		
	                		<li><a href="javascript:void(0);">网易公开课</a></li>
	                		<li><a href="javascript:void(0);">中国大学视频公开课</a></li>
	                	</ul>
                	</div>
                	<div class="link_box">
                		<div class="charts_name">
                			<p class="decorate_line"></p>
	                			<p>站内链接</p>
	                	</div>
	                	<ul class="charts_list">
	                		<li><a href="javascript:void(0);">材料申购</a></li>
	                		<li><a href="javascript:void(0);">物资采购</a></li>
	                		<li><a href="javascript:void(0);">资产等级</a></li>
	                		<li><a href="javascript:void(0);">安全培训</a></li>
	                		<li><a href="javascript:void(0);">设备采购</a></li>
	                	</ul>
                	</div>
                	<div class="link_box">
                		<div class="charts_name">
                			<p class="decorate_line"></p>
	                			<p>校外链接</p>
	                			
	                	</div>
	                	<ul class="charts_list">
	                		<li><a href="javascript:void(0);">西安电子科技大学课堂实录</a></li>
	                		<li><a href="javascript:void(0);">西安电子科技大学课堂中心</a></li>
	                		
	                		<li><a href="javascript:void(0);">网易公开课</a></li>
	                		<li><a href="javascript:void(0);">中国大学视频公开课</a></li>
	                	</ul>
                	</div>
	            </div>
	        </div>
	    </div>
    </div>
	    <script type="text/javascript">
	    	$('.icon_menu').click(
        function () {
            $(this).parent().siblings('.menu_box')
            	.attr("value","open")
            	.fadeIn(100)
                .animate({
                    left:19
                },100);
            $(this).parent().siblings('#menu').find(".active")
            	.addClass("active_list")
            	.children().animate({borderLeftColor: '#FFF'},100)
            $('.icon_close').fadeIn(100);
            stop()
        });
    $('.icon_close').click(
	    function(){
	        $(this).fadeOut(100);
	        $(this).parent('.menu_box')
	        	.attr("value","close")
                .animate({
                	left:-3500
                },100)
                .fadeOut(100);
            $(this).parent().siblings('#menu').find(".active")
            	.removeClass("active_list")
        		.children().animate({borderLeftColor: '#3069db'},100)
        	stop()
	    });
	    $("#menu li").click(
			function(){
				$(this).siblings().find("a").animate({borderLeftColor: '#3069db'},100)
				$("#menu").find(".active_list").removeClass("active_list")
				$(".menu_box")
				.attr("value","close")
		        .animate({
		        	left:-3500
		        },100)
		        $("#menu li:not(.active)")
		        .css("color","#e6e6e6")
		        stop()
		    	}
		)
$(".link_box,.charts_box,.news_box,.aboutUs_box").hover(
	function(){
		$(this).find(".decorate_line").stop().fadeIn()
		$(this).find(".charts_name p").animate({
            color:'#3069db'
        },100)//alert("ad")
	},
	function(){
		$(this).find(".decorate_line").stop().fadeOut()
        $(this).find(".charts_name p").animate({
            color:'#333'
        },100)
	}
)
	    </script>
</body>

</html>