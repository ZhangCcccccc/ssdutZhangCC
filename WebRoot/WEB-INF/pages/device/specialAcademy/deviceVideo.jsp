<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>

<html>
<head>
<title></title>
<meta name="decorator" content="iframe"/>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-sacale=1.0,user-scalable=no"/>
<style type="text/css" media="screen">
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css");
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css");
			@import url("${pageContext.request.contextPath}/css/style.css");
</style>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">

<!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhulims/device/editDevice.js"></script>

<!-- 播放器所需文件 -->
	<!-- 此处的JQ版本和文件上传的有冲突，故注掉 -->
	<%-- <script src="${pageContext.request.contextPath}/mediaelement/build/jquery.js"></script> --%>
	
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/me-namespace.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/me-utility.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/me-i18n.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/me-plugindetector.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/me-featuredetection.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/me-mediaelements.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/me-shim.js" type="text/javascript"></script>
	
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/mep-library.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/mep-player.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/mep-feature-playpause.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/mep-feature-progress.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/mep-feature-time.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/mep-feature-speed.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/mep-feature-tracks.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/mep-feature-volume.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/mep-feature-stop.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/mediaelement/src/js/mep-feature-fullscreen.js" type="text/javascript"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/mediaelement/src/css/mediaelementplayer.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/mediaelement/src/css/mejs-skins.css" />	


<script type="text/javascript">

function uploadVideo(){
	var url = "${pageContext.request.contextPath}/device/editDeviceVideoRest/" + ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}" +"/"+${username}+ "/"+${page}+"/" + ${device.id}+ "/"+"${schoolDevice_allowAppointment}";
			 $('#searchFile1').window('open');
			 $('#video_upload').uploadify({
				'formData':{id:'${device.id}'},    //传值
	            'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
	            'uploader':'${pageContext.request.contextPath}/device/deviceVideoUpload;jsessionid=<%=session.getId()%>',		//提交的controller和要在火狐下使用必须要加的id
	            buttonText:'选择文件',
	             onQueueComplete : function(data) {//当队列中的所有文件全部完成上传时触发	
	      			    //当上传完所有文件的时候关闭对话框并且转到显示界面
	            	 $('#searchFile1').window('close');	    
	            	 //window.location.href="${pageContext.request.contextPath}/device/deviceVideo?deviceId="+${device.id};        	
	            	 window.location.href=url;        	
		}
	        });
			
		}
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/editDevice.js"></script>

<style>
.tool-box2 th{text-align:left;}
</style>
</head>


<body>

<div class="right-content">	
	<div class="tool-box1">
		<table class="equip_tab">
		    <tr>
				<td>
				    <span>设备编号：</span>
				    <p>${device.schoolDevice.deviceNumber}</p>
				</td>
				<td>
				    <span>设备名称：</span>
				    <p class="equip_name">${device.schoolDevice.deviceName}</p>
				</td>
				<td>
				    <span>仪器型号：</span>
				    <p>${device.schoolDevice.devicePattern}</p>
				</td>
			</tr>
			<tr>
				<td>
				    <span>所在实验室：</span>
				    <p>${device.labRoom.labRoomName}</p>
				</td>
				<td>
				    <span>生产国别：</span>
				    <p>${device.schoolDevice.deviceCountry}</p>
				</td>
				<td>
				    <span>生产厂家：</span>
				    <p>${device.schoolDevice.manufacturer}</p>
				</td>
			</tr>
		</table>
	</div>
	<div id="TabbedPanels1" class="TabbedPanels">
	 <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceInfoRest(${device.id})">设备详情</a>
		</li>
		<%-- <c:if test="${device.CActiveByAllowSecurityAccess.id == 1}"> --%>
		<c:if test="${device.CDictionaryByAllowSecurityAccess.CCategory=='c_active' && device.CDictionaryByAllowSecurityAccess.CNumber=='1'}">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceTrainingRest(${device.id})">培训计划</a>
		</li>
		</c:if>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceSettingRest(${device.id})">参数设置</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceImageRest(${device.id})">相关图片</a>
		</li>
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceVideoRest(${device.id})">相关视频</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceDocumentRest(${device.id})">相关文档</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceDimensionalCodeRest(${device.id})">二维码</a>
		</li>
	</ul>
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			
			<div class="content-box">
				<div class="title">
				<div id="title">设备视频</div>
					<a class="btn btn-new"  herf="#" onclick="openSetupLink();">返回</a>
				</div>
					<fieldset class="introduce-box">
						     <label>设备相关视频</label>
							<ul class="img-box">
						     <c:forEach items="${device.commonVideos}" var="video" varStatus="i">
						      <li>
						      <div id="video-container">
							  <video width="640" height="360" id="player1" controls="controls" preload="none" poster="">
							  <source src="${pageContext.request.contextPath}/${video.videoUrl}"  type="video/mp4" /> 	
							  <track kind="subtitles" src="${pageContext.request.contextPath}/mediaelement/media/mediaelement.srt" srclang="en"  ></track>
							  </video>
							  </div>
							  <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN,ROLE_LABMANAGER">
							  <%--<a class="btn btn-common" href="${pageContext.request.contextPath}/device/deleteLabRoomVideo?id=${video.id}">删除</a>
						      --%>
						      <a class="btn btn-common"  href="javascript:deleteLabRoomVideoRest(${video.id})" onclick="return confirm('确定要删除吗？')">删除</a>
						      </sec:authorize>
						      </li>
						     </c:forEach>
								
								</ul>
								<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN,ROLE_LABMANAGER">
								<input type="button" onclick="uploadVideo()" value="上传视频"/>
								</sec:authorize>
					 </fieldset>
		
  			<input type="hidden" id="labRoomId" value="${labRoomId }">
            <input type="hidden" id="deviceName" value="${deviceName }">
            <input type="hidden" id="deviceNumber" value="${deviceNumber }">
            <input type="hidden" id="username" value="${username }">
            <input type="hidden" id="page" value="${page }">
    		<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
      		<input type="hidden" id="schoolDevice_allowAppointment" value="${schoolDevice_allowAppointment}">
			</div>
		</div>
		<!-- 上传视频 -->
     <div id="searchFile1" class="easyui-window" title="上传视频" closed="true" iconCls="icon-add" style="width:400px;height:200px">
	    <form id="form_ video" method="post" enctype="multipart/form-data">
           <table  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
            <td>
          	<div id="queue">（文件格式：MP4）</div>
		    <input id="video_upload" name="file_upload" type="file" multiple="true">
            </tr>   
            </table>
         </form>
     </div> 
      <!-- 上传视频结束 --> 
	  </div>
	</div>
	
</div>

</body>
</html>
