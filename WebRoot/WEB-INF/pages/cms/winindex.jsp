<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
    <title>西安电子科技大学</title>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="Decorators" content="none">
    <meta name="Generator" content="gvsun">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/index/jquery.fullPage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/index/global_index.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.fullPage.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/globle_main.js"></script>
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
        <img src="${pageContext.request.contextPath}/images/window/logo.png" class="logo_pic">
    </div>
    <div id="dowebok">
        <div class="section section1">
         <c:forEach  items="${links}"  var="link">
          <div class="slide">
                    <a href="${pageContext.request.contextPath}/cms/cindex" >
                        <img src="${pageContext.request.contextPath}/${link.cmsResource.url}"  width="100%">
                     </a>
                      </div>
						</c:forEach>
        </div>
    </div>

<!--         <div class="bottom_link"> -->
<!--         <c:forEach items="${topchannels}" var="topChannel" varStatus="i" > -->
<!--         	<c:if test="${i.index==0 }"> -->
<!--     	    <img class="link_div_img" src="${pageContext.request.contextPath}/images/winindex/tp1.png"/> -->
<!--     	    <div class="link_div"> -->
            	
<!--             	<a href="${pageContext.request.contextPath}/cms/cindex" class="link_detail mt2p">首页</a> -->
<!--             	<a href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1" class="link_detail">${topChannel.title}</a> -->
<!--             </div> -->
<!--             </c:if> -->
<!-- 		</c:forEach> -->
		
<!--             <img class="link_div_img" src="${pageContext.request.contextPath}/images/winindex/tp2.png"/> -->

<!-- 			<div class="link_div"> -->
<!-- 				 <c:forEach items="${topchannels}" var="topChannel" varStatus="i" begin="1" end="2" > -->
<!-- 				 <c:if test="${i.index==1 }"> -->
<!-- 					<a href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1" class="link_detail mt2p">${topChannel.title }</a> -->
<!-- 				</c:if>		 -->
<!-- 				 <c:if test="${i.index==2 }">		 -->
<!-- 					<a href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1" class="link_detail">${topChannel.title }</a> -->
<!-- 					</c:if>		 -->
<!-- 				</c:forEach> -->
<!-- 			</div> -->
<!-- 		 <img class="link_div_img" src="${pageContext.request.contextPath}/images/winindex/tp2.png"/> -->

<!-- 			<div class="link_div"> -->
<!-- 				 <c:forEach items="${topchannels}" var="topChannel" varStatus="i" begin="3" end="4" > -->
<!-- 				 <c:if test="${i.index==3 }"> -->
<!-- 					<a href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1" class="link_detail mt2p">${topChannel.title }</a> -->
<!-- 				</c:if>		 -->
<!-- 				 <c:if test="${i.index==4 }">		 -->
<!-- 					<a href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1" class="link_detail">${topChannel.title }</a> -->
<!-- 					</c:if>		 -->
<!-- 				</c:forEach> -->
<!-- 			</div> -->
			
<!-- 			 <img class="link_div_img" src="${pageContext.request.contextPath}/images/winindex/tp2.png"/> -->

<!-- 			<div class="link_div"> -->
<!-- 				 <c:forEach items="${topchannels}" var="topChannel" varStatus="i" begin="5" end="6" > -->
<!-- 				 <c:if test="${i.index==5 }"> -->
<!-- 					<a href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1" class="link_detail mt2p">${topChannel.title }</a> -->
<!-- 				</c:if>		 -->
<!-- 				 <c:if test="${i.index==6 }">		 -->
<!-- 					<a href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1" class="link_detail">${topChannel.title }</a> -->
<!-- 					</c:if>		 -->
<!-- 				</c:forEach> -->
<!-- 			</div> -->
			
<!-- 			 <img class="link_div_img" src="${pageContext.request.contextPath}/images/winindex/tp2.png"/> -->

<!-- 			<div class="link_div"> -->
<!-- 				 <c:forEach items="${topchannels}" var="topChannel" varStatus="i" begin="7" end="8" > -->
<!-- 				 <c:if test="${i.index==7 }"> -->
<!-- 					<a href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1" class="link_detail mt2p">${topChannel.title }</a> -->
<!-- 				</c:if>		 -->
<!-- 				 <c:if test="${i.index==8 }">		 -->
<!-- 					<a href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1" class="link_detail">${topChannel.title }</a> -->
<!-- 					</c:if>		 -->
<!-- 				</c:forEach> -->
<!-- 			</div> -->
			
			
<!-- 			 <img class="link_div_img" src="${pageContext.request.contextPath}/images/winindex/tp2.png"/> -->

<!-- 			<div class="link_div"> -->
<!-- 				 <c:forEach items="${topchannels}" var="topChannel" varStatus="i" begin="9" end="10" > -->
<!-- 				 <c:if test="${i.index==9 }"> -->
<!-- 					<a href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1" class="link_detail mt2p">${topChannel.title }</a> -->
<!-- 				</c:if>		 -->
<!-- 				 <c:if test="${i.index==10 }">		 -->
<!-- 					<a href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1" class="link_detail">${topChannel.title }</a> -->
<!-- 					</c:if>		 -->
<!-- 				</c:forEach> -->
<!-- 			</div> -->
		<!-- 			<c:if test="${i.index==3 ||i.index==4 }"> -->
<!--             <img class="link_div_img" src="${pageContext.request.contextPath}/images/winindex/tp3.png"/> -->
<!--     	    <div class="link_div"> -->
<!--             	<c:if test="${i.index==3}"> -->
<!--             	<a href="" class="link_detail mt2p">${topChannel.title }</a> -->
<!--             	</c:if> -->
<!--             	 <c:if test="${i.index==4}"> -->
<!--             	<a href="" class="link_detail">${topChannel.title }</a> -->
<!--             		</c:if> -->
<!--             </div> -->
<!--             </c:if> -->
<!--             <c:if test="${i.index==5 ||i.index==6 }"> -->
<!--     	    <img class="link_div_img" src="${pageContext.request.contextPath}/images/winindex/tp4.png"/> -->
<!--     	    <div class="link_div"> -->
            	
<!--             	<c:if test="${i.index==5}"> -->
<!--             	<a href="" class="link_detail mt2p">${topChannel.title }</a> -->
<!--             	</c:if> -->
<!--             	 <c:if test="${i.index==6}"> -->
<!--             	<a href="" class="link_detail">${topChannel.title }</a> -->
<!--             		</c:if> -->
<!--             </div> -->
<!--             </c:if> -->
<!--             <c:if test="${i.index==7||i.index==8 }"> -->
<!--     	    <img class="link_div_img" src="${pageContext.request.contextPath}/images/winindex/tp5.png"/> -->
<!--     	    <div class="link_div"> -->
            	
<!--             	<c:if test="${i.index==7}"> -->
<!--             	<a href="" class="link_detail mt2p">${topChannel.title }</a> -->
<!--             	</c:if> -->
<!--             	 <c:if test="${i.index==8}"> -->
<!--             	<a href="" class="link_detail">${topChannel.title }</a> -->
<!--             		</c:if> -->
<!--             </div> -->
<!--             </c:if> -->
<!--             <c:if test="${i.index==9||i.index==10 }"> -->
<!--     	    <img class="link_div_img" src="${pageContext.request.contextPath}/images/winindex/tp6.png"/> -->
<!--     	    <div class="link_div"> -->
            	
<!--             	<c:if test="${i.index==9}"> -->
<!--             	<a href="" class="link_detail mt2p">${topChannel.title }</a> -->
<!--             	</c:if> -->
<!--             	 <c:if test="${i.index==10}"> -->
<!--             	<a href="" class="link_detail">${topChannel.title }</a> -->
<!--             		</c:if> -->
<!--             </div> -->
<!--             </c:if> -->
          
            
          
<!--     </div> -->
    
    
</body>

</html>