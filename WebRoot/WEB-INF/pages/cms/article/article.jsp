<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
     <meta name="decorator" content="cmscindex"/>
    <meta name="Keywords" content="">
	<meta name="Author" content="chenyawen">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/article/article.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/css/index/global_long_grey2.css" rel="stylesheet" type="text/css">
    <!--<link href="css/lib.css" rel="stylesheet" type="text/css">-->
    <!--<link href="css/font.css" rel="stylesheet" type="text/css">-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/slide.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/slide.js"></script>
    
</head>
<body>
	<div class="layout_header_container">
		<div class="title_detail" style="clear:both;">
			<div class="title_detail_bg"></div>
			<div class="title_detail_message">
				<span>您当前所在位置：</span> <a class="mlr3">首页</a> <span class="mlr3">›</span>
				<a class="mlr3">${topchannel.title}</a> <span class="mlr3">›</span> <a class="mlr3">${article.cmsChannel.title}</a>
			</div>
		</div>
	</div>
	<div class="layout_main_container2">
		<div class="main_container_left" id="main_container_left">
			<div class="left_above">
				<div class="title_chose">
					<img src="${pageContext.request.contextPath}/images/article/manwalk2.gif"/> <span>${topchannel.title}</span>
				</div>
				<div class="titles">
					
					<c:forEach items="${childchannel}" var="Childrenchannel">							
							<div class="titles_selected"><a class="hoverwhite" href="${pageContext.request.contextPath}/cms/channel/${Childrenchannel.id}?currpage=1">${Childrenchannel.title}</a>
						</div>
						</c:forEach>
						
       				
				</div>
			</div>
			<div class="left_bottom">
				<div class="title_fire">
					<div class="fl">
						<img class="img_fire" src="${pageContext.request.contextPath}/images/article/fire.gif" />
					</div>
					<span>今日热点</span>
				</div>
				<ul class="news">
					<c:forEach items="${hotarticles}"  var="article" end='2'> 
				
					<li>
						<div class="playbtn">▶</div> <a class="playmessage" href="${pageContext.request.contextPath}/cms/article/${article.id}">${article.title} </a>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div class="main_container_right">
			<div class="right_title">
				<div>◆</div>
				<span>${topchannel.title}</span>
			</div>
			<div class="right_content">
					<div class="right_word">
						<div class="article_title">
							${article.title}
						</div>
						<c:if test="${article.cmsChannel.title eq '学院新闻'}">
						<div class="article_othermessage">
							<div class="amessage " style="text-align:center;">
								<span >发布时间:</span>
								<span ><fmt:formatDate type="date"  pattern="yy-MM-dd" value="${article.createTime.time }" /></span>
							</div>
							
						</div>
						</c:if>
						<c:if test="${article.cmsChannel.title eq '通知公告'}">
						<div class="article_othermessage">
							<div class="amessage " style="text-align:center;">
								<span class="">发布时间:</span>
								<span class=""><fmt:formatDate type="date"  pattern="yy-MM-dd" value="${article.createTime.time }" /></span>
							</div>
							
						</div>
						</c:if>
						<c:if test="${article.cmsChannel.title eq '常用下载'}">
							<div class="right_word"><a href="${pageContext.request.contextPath}/${article.cmsDocument.url}" download="${article.cmsDocument.profile }">${article.cmsDocument.profile }</a></div>
						</c:if>
						<div class="right_word">${article.news }</div>
					</div>
					<c:if test="${not empty article.cmsResource}">
					<div class="right_img">
						<img src="${pageContext.request.contextPath}/${article.cmsResource.url}">
					</div>
              	    </c:if>
					<div class="dothr"></div>
					<div class="page_content">
						<div>
							<span>上一篇:</span>
							<a href="${pageContext.request.contextPath}/${siteUrl}/previousArticle/${article.id}"></a>
						</div>
						<div>
							<span>下一篇:</span>
							<a href="${pageContext.request.contextPath}/${siteUrl}/nextArticle/${article.id}"></a>
						</div>
					</div>
				</div>
				</div>

	</div>
	<script type="text/javascript">
        
        $(".layout_menu li").hover(
            function () {
                $(this).addClass("selected");
                $(this).addClass("selected");
                $(this).find(".sub_menu").stop().show();
                $(this).find(".sub_menu li").stop().slideDown();
            },
            function () {
                $(this).find(".sub_menu li").stop().slideUp(100);
                $(this).removeClass("selected");
                $(this).find(".sub_menu").stop().delay(100).hide(1)
            }
        )
    </script>

</body>
</html>