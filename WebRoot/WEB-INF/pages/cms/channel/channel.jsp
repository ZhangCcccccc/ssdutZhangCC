<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>article3</title>
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
				<span>您当前所在位置：</span> <a class="mlr3" href="${pageContext.request.contextPath}/cms/cindex">首页</a> <span class="mlr3">›</span>
				<a class="mlr3"href="${pageContext.request.contextPath}/cms/channel/${topchannel.id}?currpage=1" >${topchannel.title}</a><span class="mlr3">›</span>
				<c:if test="${not empty channel.cmsChannel.id}">
				<a class="mlr3" href="${pageContext.request.contextPath}/cms/channel/${channel.id}?currpage=1">${channel.title}</a>
				</c:if>
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
							<div class="">
							<a class="hoverwhite" href="${pageContext.request.contextPath}/cms/channel/${Childrenchannel.id}?currpage=1"  title="${Childrenchannel.title}">${Childrenchannel.title}</a>
							
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
						<div class="playbtn"float= "left">▶</div> <a class="playmessage" href="${pageContext.request.contextPath}/cms/article/${article.id}">${article.title} </a>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div class="main_container_right">
			<div class="right_title">
				<div>◆</div>
				<span>${channel.title}</span>
			</div>        	<div class="right_content">
        		<ul class="list_frame">
        			
        			 <c:forEach  items="${currarticles}"  var="article">
        			 <li>
        				    <div class="list_message">
        					    <div>▶▷</div>
        					   <a href="${pageContext.request.contextPath}/cms/article/${article.id}">${article.title}</a>
        				    </div>
        				    <div class="list_date"><fmt:formatDate type="date"  pattern="yy-MM-dd" value="${article.createTime.time }"/></div>
        			</li>
					</c:forEach>
				</ul>
        	</div>
			<div class="page-message">
		        <a class="page2 page-pos" href="${pageContext.request.contextPath}/cms/channel/${channelId}?currpage=${pageModel.lastPage}" target="_self">末页</a>
		        <a class="page2" href="${pageContext.request.contextPath}/cms/channel/${channelId}?currpage=${pageModel.nextPage}" target="_self">下一页</a>
		        <div class="page-select">
		        <!-- 跳转到选择/输入的页面start -->
		        <input type="hidden" value="${pageModel.lastPage}" id="totalpage" />
		        <div class="page-word">页</div>
		            <form>
		    	        <select class="page-number" onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
                            <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
		                        <c:if test="${j.index!=currpage}">
		                            <option value="${pageContext.request.contextPath}/cms/channel/${channelId} ?currpage=${j.index}">${j.index}</option>
		                        </c:if>
		                    </c:forEach>
                        </select>
		            </form>
		    	    <div class="page-word">第</div>
		        </div>
		        <input type="hidden" value="${currpage}" id="currpage" />
		        <!-- 跳转到选择/输入的页面end -->
		        <a class="page2" href="${pageContext.request.contextPath}/cms/channel/${channelId}?currpage=${pageModel.preiviousPage}" target="_self">上一页</a>
		        <a class="page2" href="${pageContext.request.contextPath}/cms/channel/${channelId}?currpage=1" target="_self">首页</a>
		        <div class="p-pos">
		          	${totalRecords}条记录 _ 共${pageModel.totalPage}页
		        </div>
    		</div>
			</div>
		</div>
		
	                  


	<script type="text/javascript">
		$(".layout_menu li").hover(function() {
			$(this).addClass("selected");
			$(this).addClass("selected");
			$(this).find(".sub_menu").stop().show();
			$(this).find(".sub_menu li").stop().slideDown();
		}, function() {
			$(this).find(".sub_menu li").stop().slideUp(100);
			$(this).removeClass("selected");
			$(this).find(".sub_menu").stop().delay(100).hide(1)
		})
	</script>

</body>
</html>