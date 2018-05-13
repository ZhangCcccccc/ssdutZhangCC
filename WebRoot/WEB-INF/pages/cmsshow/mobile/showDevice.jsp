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
        上海商学院现代流通中心实验室专业企业实训平台
    </div>
    <div class="equipment_name">
        ${device.schoolDevice.deviceName}
    </div>
    <div class="equipment_basic_box">

        <div class="equipment_pic">
           <label>设备图片</label>
              <ul class="img-box">
              <c:set var="count" value="0" />
              <c:forEach items="${device.commonDocuments}" var="d">
              <li>
              <c:if test="${d.type==1}">
              <c:set var="count" value="${count+1 }" />
	              <c:if test="${count==1}">
	              	<li><img alt="" src="${pageContext.request.contextPath}/${d.documentUrl}" style="width:100%" onclick="showImage(this);">
	              </c:if>
              </c:if>
               </li>
              </c:forEach>
              </ul>
        </div>
        <div class="equipment_basic">
            <ul>
                <li><span>设备名称：${device.schoolDevice.deviceName}</span>
                </li>
                <li><span>设备编号：${device.schoolDevice.deviceNumber}</span>
                </li>
            </ul>
            <ul>
                <li><span>所在实验室：${device.labRoom.labRoomName}</span>
                </li>
                <li><span>生产国别：${device.schoolDevice.deviceCountry}</span>
                </li>
                <li><span>仪器型号：${device.schoolDevice.devicePattern}(${device.schoolDevice.deviceFormat})</span>
                </li>
                <li><span>生产厂家：${device.schoolDevice.manufacturer}</span>
                </li>
            </ul>
            <div class="details"><a href="${pageContext.request.contextPath}/cmsshow/showDeviceInfo?id=${device.id}">设备详情</a>
            </div>
        </div>
    </div>
</body>

</html>
