<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>学习</title>
    <meta name="decorator" content="none"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!-- import css-->
	
<script type="text/javascript">


	
	
	
    
</script>

</head>
  
<body>
<div class="gridContainer clearfix">

	<div class="arbd">

	<div class="kccv">
	
	<div class="kcrightl">
	
	<!--视频播放-->
		<div class="kctv"> 
		<div class="kccon">
		<!-- 判断是否是视频，还是文本，视频条件下再判断是否是在线还是本地的资源 -->		
			<c:if test="${videoType eq 'local'}">
				<!-- 视频大小不限制，按照默认大小显示<video width="320" height="240" controls="controls"> -->
				<video width="100%" height="500" controls="controls">
 	 			<source src="${pageContext.request.contextPath}${upload.url}" type="video/mp4">
				</video>
			</c:if>
			<c:if test="${videoType eq 'online'}">
			<embed src="${videoUrlPc}" 
			quality="high" width="1000" height="361" align="middle" allowScriptAccess="always" allowFullScreen="true" 
			mode="transparent" type="application/x-shockwave-flash"></embed>
			</c:if>

		</div><br>	
		</div>
	<!--视频播放-->
	</div>
	</div>	
	</div>
</div>

</body>
</html>
