<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上海中医药大学解剖实验课</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/songProject/style.css" />
	
	
</head>
<body>


<div id="content_list">

<!-- 当前位置栏开始  -->
    <div class="nav">
			&nbsp;当前位置： 
			<a href="${pageContext.request.contextPath}/cms/courseSiteNew">首页</a>&nbsp;>&nbsp;
			<a href="${pageContext.request.contextPath}/cms/channel/findChannelList?tagId=9">${tCourseSiteChannel.channelName}</a>
    </div>
<!-- 当前位置栏结束  -->
    
<!-- 左侧栏开始  -->
    <div class="content_list_left">
	    <div class="list_left_title">${tCourseSiteChannel.channelName}</div>
	</div>
<!-- 左侧栏结束  -->	




<!-- 内容栏开始  -->		
	<div class="content_list_right">
	   	<div class="list_box">
	   
	      	<div class="list_box1"></div>

		  	<div class="list_box2">${tCourseSiteChannel.channelName}</div>
	       
		  	<div class="list_box3"></div>
		  
		  	<div class="list_box4">
               	<div id="videoshow">
					<embed allowfullscreen="true" allowscriptaccess="always"
						flashvars="file=/video/123.flv&amp;image=/video/1.jpg" 
								height="400" width="100%" id="mpl" name="mpl" quality="high"
						src="${pageContext.request.contextPath}/songProjectSwf/applicationTable.swf" 
								style="undefined" type="application/x-shockwave-flash"
						wmode="opaque">
				</div>
				
		  	</div>
		  	
			<div class="list_box5"></div>
			<div class="list_box6"></div>
			<div class="list_box7"></div>
		</div>		
	</div>
	
<!-- 内容栏结束  -->	

	
</div> 


</body>
</html>