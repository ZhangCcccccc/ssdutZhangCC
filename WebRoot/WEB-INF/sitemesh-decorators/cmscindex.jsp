<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<% 
   //前端登录标记
   session.setAttribute("LOGINTYPE","back");  
%> 
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta name="decorator" content="none"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="Keywords" content="">
    <title>西安电子科技大学</title>
	<meta name="Author" content="chenyawen">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/index/global_long_grey2.css" rel="stylesheet" type="text/css">
    <!--<link href="css/lib.css" rel="stylesheet" type="text/css">-->
    <!--<link href="css/font.css" rel="stylesheet" type="text/css">-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/slide.js"></script>
    <decorator:head></decorator:head>
</head>
		<body>
    <div class="layout_header_container">
    	<div class="">
		    <div class="mobile-action"></div>
		    <img class="titlebg" src="${pageContext.request.contextPath}/images/cms/g13.gif">
		    <a href="${pageContext.request.contextPath}/cms/winindex">
		    <img src="${pageContext.request.contextPath}/images/cms/logo.png" height="76" class="logotitle"></a>	
		    <div class="other-message">
		        <a>设为首页</a>
		        <a>English</a>
		        <%--<a href="${pageContext.request.contextPath}/">登录</a>--%>
		        <c:if test="${!empty loginUser }"> 
      				<a href="${pageContext.request.contextPath}/login">进入系统</a>
      	 		</c:if>
      	 		<c:if test="${empty loginUser }">
      				<a href="${pageContext.request.contextPath}/">登录</a>
      	 		</c:if>
		    </div>		
		    <div class="time-message-bg"></div>
		    <div class="time-message">		    
		        <span>${nowDate}</span>		        
		    </div>
		     <div class="search-box">
 			    <img src="${pageContext.request.contextPath}/images/cms/search1.jpg"/>
				<form class="login_form" name='f'
					action='${pageContext.request.contextPath}/cms/search'
					method='POST'>
					<input class="search_input" type="text" name="j_username"
						placeholder="输入关键字"  />
 				<input class="search-btn" type="submit" value="搜索" />
					</form>
			</div>
			
			<c:if test="${!empty loginUser }"> 
				<div class="user_name">
				    <span>您好！</span>
				    <span>${loginUser.cname}</span>
      				<a class="cb" href="${pageContext.request.contextPath}/pages/logout-redirect.jsp" target="_parent">退出 </a>
      			</div> 
      	 	</c:if>
	    </div>
        <div class="layout_menu_container">
			<ul class="layout_menu">
			    
				<li>  <a class="cb bln" href="${pageContext.request.contextPath}/cms/cindex">首页</a>  </li>
				<c:forEach items="${channels}" var="topChannel">
				<c:if test="${empty topChannel.cmsChannel.id}">
				<c:if test="${!(topChannel.title eq '常用下载') }">
					<li><a class="cb"
						href="${pageContext.request.contextPath}/cms/channel/${topChannel.id}?currpage=1">${topChannel.title}</a>
						<ul class="sub_menu">
					
					<c:forEach items="${channels}" var="childchannel">
					<c:if test="${childchannel.cmsChannel.id eq topChannel.id}">
					<li><a class="cb"
						href="${pageContext.request.contextPath}/cms/channel/${childchannel.id}?currpage=1">${childchannel.title}</a>
						</li>
						
						</c:if>					
				    </c:forEach>
					 </ul>
					</li>
					</c:if>
					</c:if>					
				</c:forEach>
				<li><a class="cb"
						href="${pageContext.request.contextPath}/tms/courseList?currpage=1">在线课程留言</a>
						<ul class="sub_menu">
					 </ul>
					</li>
			</ul>
        </div>
        
    </div>
        <div class="content-box">
        <decorator:body/>
    
    
    
<!--     底部 -->
  <div class="footer-box">
	<div class="footer" style="width:95%;">
		<div class="address">
			
			<div class="title address-tit">南校区地址：陕西省西安市西沣路兴隆段266号</div>
			<div class="title address-tit">北校区地址：陕西省西安市太白南路2号</div>
		</div>
		<div class="friend">
			
			<div class="title address-tit">联系电话：029-88201000</div>
		</div>
		<div class="friend">
			
			<%--<div class="title friend-tit"><a href="http://nano.suda.edu.cn"><div class="link-fff">苏州大学网站首页</div><a></div>--%>
				
		</div>
    </div>
	<div class="power">
		Copyright ©2016 西安电子科技大学 All Rights Reserved 技术支持：GVSUN
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
        <SCRIPT LANGUAGE="JavaScript">  
        var myDate = new Date();  
        myDate.getYear();       //获取当前年份(2位)  
        myDate.getFullYear();   //获取完整的年份(4位,1970-????)  
        myDate.getMonth();      //获取当前月份(0-11,0代表1月)  
        myDate.getDate();       //获取当前日(1-31)  
        myDate.getDay();        //获取当前星期X(0-6,0代表星期天)  
        myDate.getTime();       //获取当前时间(从1970.1.1开始的毫秒数)  
        myDate.getHours();      //获取当前小时数(0-23)  
        myDate.getMinutes();    //获取当前分钟数(0-59)  
        myDate.getSeconds();    //获取当前秒数(0-59)  
        myDate.getMilliseconds();   //获取当前毫秒数(0-999)  
        myDate.toLocaleDateString();    //获取当前日期  
        var mytime=myDate.toLocaleTimeString();    //获取当前时间  
        myDate.toLocaleString( );       //获取日期与时间  
    </SCRIPT>  
	</body>
	
</html>
