<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>

<head>
	<meta name="decorator" content="iframe"/>
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-sacale=1.0,user-scalable=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/global_min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui-1.10.4.tabs.min.js"></script>
     <script type="text/javascript">

 
		function showImage(image){
			//alert(image.src);
			//$("#img").src=image.src;
			document.getElementById("img").src=image.src;
			$('#showImage').window({
				        top: 500   
				     });
			$('#showImage').window('open');
		}
		
	</script>
    
    <title>设备信息</title>
</head>

<body>
    <div class="equipment_title">
        ${device.schoolDevice.deviceName}
        <span class="return"><a href="${pageContext.request.contextPath}/cmsshow/showDevice?id=${device.id}">
        <img src="${pageContext.request.contextPath}/images/prev_03.png"></a></span>
    </div>
    <div id="nav_box">
        <ul class="nav">
            <li><a href="#tabs-1">详情</a>
            </li>
            <li><a href="#tabs-2">图片</a>
            </li>
            <li><a href="#tabs-3">视频</a>
            </li>
            <li><a href="#tabs-4">文档</a>
            </li>
        </ul>
        <div class="details_box">
            <!--详情-->
            <div id="tabs-1">
                <div class="equipment_details">
                    <ul>
                        <li><span>设备管理员：${device.user.cname}(${device.user.username})</span>
                        </li>
                        <li><span>设备状态：正常使用</span>
                        </li>
                        <li><span>收费标准：不计费</span>
                        </li>
                    </ul>
                    <ul>
                        <li><span>主要技术指标</span>
                        </li>
                        <li>${device.indicators}</li>
                    </ul>
                    <ul>
                        <li><span>功能应用范围</span>
                        </li>
                        <li>${device.function}</li>
                    </ul>
                </div>
            </div>
            <!--详情结束-->
            <!--图片-->
            <div id="tabs-2">
                <ul class="equipment_img_mobile">
	              <c:forEach items="${device.commonDocuments}" var="d">
	              <c:if test="${d.type==1}">
	              <li><img alt="" src="${pageContext.request.contextPath}/${d.documentUrl}" style="width:100%;" onclick="showImage(this);">
	               </li>
	              </c:if>
	              </c:forEach>
                </ul>
            </div>
            <!--图片结束-->
            <!--视频-->
            <div id="tabs-3">
				<ul class="equipment_img_mobile">
			     <c:forEach items="${device.commonVideos}" var="video" varStatus="i">
			      <li>
				      <div id="video-container">
					  <video width="100%" height="" id="player1" controls="controls" preload="none" poster="">
					  <source src="${pageContext.request.contextPath}/${video.videoUrl}"  type="video/mp4" /> 	
					  <track kind="subtitles" src="${pageContext.request.contextPath}/mediaelement/media/mediaelement.srt" srclang="en"  ></track>
					  </video>
					  </div>
			      </li>
			     </c:forEach>
					
				</ul>
            </div>
            <!--视频结束-->
            <!--文档-->
            <div id="tabs-4">
				<table>
				<tr>
				<th>序号</th>
				<th>文档名称</th>
				<th>操作</th>
				</tr>
				<c:forEach items="${device.commonDocuments}" var="d" varStatus="i">
				<c:set var="count" value="0" />
				<c:if test="${d.type==2}">
				<c:set var="count" value="${count+1}" />
				<tr>
				<td>${count}</td>
				<td>${d.documentName}</td>
				<td>
					<a href="${pageContext.request.contextPath}/device/downloadDocument?id=${d.id}">下载</a> 
					<a href="${pageContext.request.contextPath}/${d.documentUrl}" target="_blank">查看</a>
				</td>
				</tr>
				</c:if>
		    	
		     	</c:forEach>
		     	</table>
            </div>
            <!--文档结束-->
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $("#nav_box").tabs();
        });
    </script>
</body>


</html>
