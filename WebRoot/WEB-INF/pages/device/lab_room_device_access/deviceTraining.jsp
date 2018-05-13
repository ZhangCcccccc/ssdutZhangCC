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

<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->

<!-- 播放器所需文件 -->
	<%-- <script src="${pageContext.request.contextPath}/mediaelement/build/jquery.js"></script>
	
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
 --%>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
function uploadDocument(){
			 $('#searchFile').window({top: 1200});
			 $('#searchFile').window('open');
			 $('#file_upload').uploadify({
				'formData':{id:'${device.id}'},    //传值
	            'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
	            'uploader':'${pageContext.request.contextPath}/device/deviceImageUpload;jsessionid=<%=session.getId()%>',		//提交的controller和要在火狐下使用必须要加的id
	            buttonText:'选择文件',
	             onQueueComplete : function(data) {//当队列中的所有文件全部完成上传时触发	
	      			    //当上传玩所有文件的时候关闭对话框并且转到显示界面
	            	 $('#searchFile').window('close');
	            	window.location.href="${pageContext.request.contextPath}/device/editDeviceReservationInfo?id="+${device.id}; 	           	
				}
	        });
			
		}
	function uploadVideo(){
			 $('#searchFile1').window({top: 1300});
			 $('#searchFile1').window('open');
			 $('#video_upload').uploadify({
				'formData':{id:'${device.id}'},    //传值
	            'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
	            'uploader':'${pageContext.request.contextPath}/device/deviceVideoUpload;jsessionid=<%=session.getId()%>',		//提交的controller和要在火狐下使用必须要加的id
	            buttonText:'选择文件',
	             onQueueComplete : function(data) {//当队列中的所有文件全部完成上传时触发	
	      			    //当上传完所有文件的时候关闭对话框并且转到显示界面
	            	 $('#searchFile1').window('close');	    
	            	 window.location.href="${pageContext.request.contextPath}/device/editDeviceReservationInfo?id="+${device.id};        	
		}
	        });
			
		}
/**
*添加培训
*/		
function openwin(){
	 $("#openwindow").window({top: 1400});
     $("#openwindow").show();
     $("#openwindow").window('open');   
    
 }		
 
function showImage(image){
	//alert(image.src);
	//$("#img").src=image.src;
	document.getElementById("img").src=image.src;
	$('#showImage').window({
		        top: 1200   
		     });
	$('#showImage').window('open');
}	
</script>
<style>
.tool-box2 th{text-align:left;}
</style>
</head>


<body>

<div class="right-content">	
	<div class="tool-box1">
		<table>
			<tr>
				<th>设备编号</th><td>${device.schoolDevice.deviceNumber}</td>
				<th>设备名称</th><td>${device.schoolDevice.deviceName}</td>
			</tr>
			<tr>
				<th>所在实验室</th><td>${device.labRoom.labRoomName}</td>
				<th>生产国别</th><td>${device.schoolDevice.deviceCountry}</td>
			</tr>
			<tr>
				<th>仪器型号</th><td>${device.schoolDevice.devicePattern}</td>
				<th>生产厂家</th><td>${device.schoolDevice.manufacturer}</td>
			</tr>
		</table>
	</div>
	<div id="TabbedPanels1" class="TabbedPanels">
	 
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			
			<div class="content-box">
				<div class="title">设备管理</div>
					<form:form action="${pageContext.request.contextPath}/device/saveDeviceReservationInfo?deviceId=${device.id}" method="post" modelAttribute="device">
					<div class="new-classroom">
						<fieldset>
						    <label>设备管理员</label>
						      <form:select path="user.username" class="chzn-select">
								<form:options items="${users}" itemLabel="cname" itemValue="username"/>
								</form:select>
					</fieldset>	
					<fieldset>
						    <label>设备状态</label>
						      <%-- <form:select path="CDeviceStatus.id" class="chzn-select">
								<form:options items="${stutus}" itemLabel="name" itemValue="id"/>
								</form:select> --%>
								<form:select path="CDictionaryByDeviceStatus.id" class="chzn-select">
								<form:options items="${stutus}" itemLabel="CName" itemValue="id"/>
								</form:select>
					</fieldset>	
						<fieldset>
						    <label>所属类型</label>
						      <%-- <form:select path="CDeviceType.id" class="chzn-select">
								<form:options items="${types}" itemLabel="name" itemValue="id"/>
								</form:select> --%>
								<form:select path="CDictionaryByDeviceType.id" class="chzn-select">
								<form:options items="${types}" itemLabel="CName" itemValue="id"/>
								</form:select>
					</fieldset>	
					<fieldset>
						    <label>收费标准</label>
						      <%-- <form:select path="CDeviceCharge.id" class="chzn-select">
								<form:options items="${charges}" itemLabel="name" itemValue="id"/>
								</form:select> --%>
								<form:select path="CDictionaryByDeviceCharge.id" class="chzn-select">
								<form:options items="${charges}" itemLabel="CName" itemValue="id"/>
								</form:select>
					</fieldset>
						<fieldset>
						    <label>费用</label>
						     <form:input path="price"/>元
						</fieldset>
						<%-- <fieldset>
						    <label>保管员</label>
						     <form:input path="keepUser"/>
						</fieldset>	 --%>
					 <fieldset class="introduce-box">
						     <label>主要技术指标</label>
							<form:textarea path="indicators"/>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>功能应用范围</label>
							<form:textarea path="function"/>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>技术特色</label>
							<form:textarea path="features"/>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>主要应用领域</label>
							<form:textarea path="applications"/>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>设备图片</label>
								<ul class="img-box">
								<c:forEach items="${device.commonDocuments}" var="d">
								
						    	<li><img alt="" src="${pageContext.request.contextPath}/${d.documentUrl}" width="200px" height="150px" onclick="showImage(this);">
						    	<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN">
						    	<a class="btn btn-common" onclick="return confirm('确定要删除吗？')" href="${pageContext.request.contextPath}/device/deleteDeviceDocument?id=${d.id}">删除</a>
						    	</sec:authorize>
						    	 </li>
						    	
						    	
						     	</c:forEach>
						     	</ul>
						     	<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN">
						     	<input class="btn btn-common" type="button" onclick="uploadDocument()" value="上传图片"/>
						     	</sec:authorize>
					 </fieldset>
					  <fieldset class="introduce-box">
						     <label>设备相关视频</label>
							<ul class="img-box">
						     <c:forEach items="${device.commonVideos}" var="video" varStatus="i">
						      <li>
						      <div id="video-container">
							  <video width="640" height="360" id="player1" controls="controls" preload="none" poster="${pageContext.request.contextPath}/mediaelement/media/echo-hereweare.jpg">
							  <source src="${pageContext.request.contextPath}/${video.videoUrl}"  type="video/mp4" /> 	
							  <track kind="subtitles" src="${pageContext.request.contextPath}/mediaelement/media/mediaelement.srt" srclang="en"  ></track>
							  </video>
							  </div>
							  <a class="btn btn-common" onclick="return confirm('确定要删除吗？')" href="${pageContext.request.contextPath}/device/deleteLabRoomVideo?id=${video.id}">删除</a>
						      </li>
						     </c:forEach>
								
								</ul>
								<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN">
								<input type="button" onclick="uploadVideo()" value="上传视频"/>
								</sec:authorize>
					 </fieldset>
					 <fieldset class="introduce-box">
						      <input type="submit" value="提交"/>
							<input type="button" value="返回" onclick="javascript:history.go(-1);"/>
					 </fieldset>

					</div>
						
						
					</form:form>
	<div id="showImage" class="easyui-window" title="查看图片" closed="true" iconCls="icon-add" style="width:620px;height:340px">
		<center>
	   <img id="img" alt="" src="" width="600" height="300" border="0">
	   </center>
     </div>					
	 <!-- 上传图片 --> 				
	<div id="searchFile" class="easyui-window" title="上传图片" closed="true" iconCls="icon-add" style="width:400px;height:200px">
	    <form id="form_file" method="post" enctype="multipart/form-data">
           <table  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
            <td>
          	<div id="queue"></div>
		    <input id="file_upload" name="file_upload" type="file" multiple="true">
            </tr>   
            </table>
         </form>
     </div>	
      <!-- 上传图片结束 --> 	
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
     <!-- 培训开始 --> 
     <div class="title">本学期的培训</div>
				<div class="tool-box2">
					<input type="button" onclick="openwin();" value="添加">
					<a class="btn btn-normal" href="${pageContext.request.contextPath}/device/findAllTrainingByDeviceId?deviceId=${device.id}">查看之前的记录</a>
					<table>
						<tr>
							<th>培训时间</th>
							<th>培训地点</th>
							<th>培训内容</th>
							<th>培训教师</th>
							<th>培训人数</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${trainings}" var="t" varStatus="i">
						<tr>
							<td><fmt:formatDate value="${t.time.time}" pattern="yyyy-MM-dd"/> </td>
							<td>${t.address}</td>
							<td>${t.content}</td>
							<td>${t.user.cname}</td>
							<td>${t.number}</td>
							<td><a onclick="return confirm('确认要删除吗？')" href="${pageContext.request.contextPath}/device/deleteLabRoomDeviceTrain?id=${t.id}">删除</a> </td>
						</tr>
						</c:forEach>
						
					</table>
					<!-- 添加培训的层 -->
					<div id="openwindow" class="easyui-window " title="添加培训" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 500px; height: 300px;">
						<form:form action="${pageContext.request.contextPath}/device/saveLabRoomDeviceTraining?deviceId=${device.id}" method="post" modelAttribute="train">
							<table>
								<tr>
									<td>培训时间：</td>
									<td>
									<input type="text" id="time" name="time" onclick="new Calendar().show(this);"  readonly>
									</td>
								</tr>
								<tr>
									<td>培训地点：</td>
									<td>
									<form:input path="address"/>
									</td>
								</tr>
								<tr>
									<td>培训内容：</td>
									<td>
									<form:textarea path="content"/>
									</td>
								</tr>
								<tr>
									<td>培训教师：</td>
									<td>
									<form:select path="user.username" class="chzn-select">
									<form:option value="">请选择</form:option>
									<form:options items="${users}" itemLabel="cname" itemValue="username"/>
									</form:select>
									</td>
								</tr>
								<tr>
									<td>培训人数：</td>
									<td>
									<form:input path="number"/>
									</td>
								</tr>

								

								<tr>
									<td colspan='2'>
									<input type="submit" value="提交"/>
									<input type="button" value="取消"/>
									</td>
								</tr>

							</table>
							
						
						</form:form>
					</div>
					<!-- 添加培训的层结束 -->
					
				</div>
				<!-- 培训结束 -->
      	
			</div>
		</div>
		
	  </div>
	</div>
	<!-- <script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script> -->
</div>
<!-- 下拉框的js -->
						<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
						<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
						<script type="text/javascript">
						    var config = {
						      '.chzn-select': {search_contains : true},
						      '.chzn-select-deselect'  : {allow_single_deselect:true},
						      '.chzn-select-no-single' : {disable_search_threshold:10},
						      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
						      '.chzn-select-width'     : {width:"95%"}
						    }
						    for (var selector in config) {
						      $(selector).chosen(config[selector]);
						    }
						</script>
						<!-- 下拉框的js -->
</body>
</html>
