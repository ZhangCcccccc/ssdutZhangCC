<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta content="none" name="decorator">
     <title>学习(推荐使用苹果移动终端设备)</title>
<meta charset="utf-8">
<!--控制默认缩放大小、最小缩放大小、最大缩放大小。通过这个可以设置用户的最大和最小缩放程度。-->
<meta name="viewport" content="width=device-width,user-scalable=no"/>
<!--//表示手机模式设置为启用。-->
<meta name="format-detection" content="telephone=no"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/lessons.css"/>
<!--html5视频播放控件-->
  <link href="${pageContext.request.contextPath}/css/mobile/video-js.css" rel="stylesheet" type="text/css">
<title>课程内容</title>
<style>
	#videobox{
		width:94% !important
	}
	
	#buttonbox a{
		width:30% !important
	}
</style>
</head>
<body>
	<div id="conteiner">
	        <div id="buttonbox">
	        	<a href="${pageContext.request.contextPath}/tcoursesite/weike/video?tCourseSiteId=${tCourseSite.id}&folderId=${wkFolder.id}" >视频</a>
				<a href="${pageContext.request.contextPath}/tcoursesite/weike/image?tCourseSiteId=${tCourseSite.id}&folderId=${wkFolder.id}" >图片</a>
				<%--<a href="${pageContext.request.contextPath}/lesson/${lesson.id}/deep" > 扩展阅读</a>
				<a href="${pageContext.request.contextPath}/lesson/${lesson.id}/testStart">做题</a>
	        	--%>
	        	<a href="${pageContext.request.contextPath}/tcoursesite/weike/site?tCourseSiteId=${tCourseSite.id}">返回</a>
	        </div>
	        		<!-- 判断是在线还是本地的资源 -->
				<c:if test="${wkFolder.videoUrl==null||wkFolder.videoUrl eq ''}">
					<c:forEach items="${wkFolder.uploads}" var="upload" end="0">
	        			<video id="videobox" class="video-js vjs-default-skin" controls="controls" preload="none"  poster="http://video-js.zencoder.com/oceans-clip.png" data-setup="{}">
		                	<source src="${pageContext.request.contextPath}${upload.url}" type="video/mp4">
		                	<!-- <source src="http://video-js.zencoder.com/oceans-clip.mp4" type='video/mp4' /> -->
		                	<track kind="captions" src="${pageContext.request.contextPath}/js/mobile/captions.vtt" srclang="en" label="English" />
		            	</video>
	        		</c:forEach>
				
					<!-- 视频大小不限制，按照默认大小显示<video width="640" height="320" controls="controls"> -->
							
				</c:if>
				<c:if test="${wkFolder.videoUrl!=null&&wkFolder.videoUrl ne ''}">
				<iframe height=498 width=510 src="${wkFolder.videoUrl}" frameborder=0 allowfullscreen></iframe>			
				</c:if>
			
	</div>
	 <!-- 分享开始 -->
	<div class="jiathis_style_32x32" style="display:block;bottom:0px;right:1px!important;line-height:30px;position:fixed;text-align:center;color:#fff;padding-right:10px; ">
		<a class="jiathis_button_qzone"></a>
		<a class="jiathis_button_tsina"></a>
		<a class="jiathis_button_tqq"></a>
		<a class="jiathis_button_weixin"></a>
		<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
		<a class="jiathis_counter_style"></a>
	</div>
	<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1400041722306662" charset="utf-8"></script>
	<!-- 分享结束 -->
</body>
</html>
<script src="${pageContext.request.contextPath}/js/mobile/video.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/resiz.js"></script>
 <!-- <script>
    _V_.options.flash.swf = "${pageContext.request.contextPath}/css/mobile/video-js.swf";
  </script> -->
  <script src="${pageContext.request.contextPath}/js/mobile/video.js"></script>
  <!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
  <script>
  _V_.options.flash.swf = "${pageContext.request.contextPath}/css/mobile/video-js.swf";
  window.onresize = function (){
		resize();
		}
	resize();
	function resize(){
		var width=document.documentElement.clientWidth;
		var height=document.documentElement.clientHeight;
		videoresize(width,height);
		//document.getElementById("conteiner").style.height=960*width/640+'px';
		document.getElementById("conteiner").style.width=width+'px';
		document.getElementsByTagName("html").item(0).style.fontSize=120*width/640+'%';
	}
	function videoresize(width,height){	
		var video=document.getElementById("videobox");
		if(width<height){
			video.style.width="100%";
			video.style.height=video.offsetWidth/16*9+'px';
			video.style.top=(height-video.offsetHeight)/2+"px";
			video.style.left="0px";
		}
		else{
			video.style.height="88%";
			video.style.width=video.offsetHeight/9*16+"px";
			video.style.top="0px";
			video.style.left=(width-video.offsetWidth)/2+'px';
		}
	}
  </script>
