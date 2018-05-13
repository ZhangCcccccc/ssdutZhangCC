<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script src="${pageContext.request.contextPath}/js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>  

<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery-1.8.3.min.js"></script>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
function uploadDocument(){
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
*导入题库
*/		
 function openexam(){
     $("#openexam").show();
     $("#openexam").window('open');   
    
 }	
 	
</script>

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
						<fieldset>
						    <label>保管员</label>
						     <form:input path="keepUser"/>
						</fieldset>	
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
								
						    	<li><img alt="" src="${pageContext.request.contextPath}/${d.documentUrl}" width="200px" height="150px">
						    	<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN">
						    	<a class="btn btn-common"  href="${pageContext.request.contextPath}/device/deleteDeviceDocument?id=${d.id}" onclick="return confirm('确定要删除吗？')">删除</a>
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
						      <embed src="${pageContext.request.contextPath}/${video.videoUrl}" autostart="true" loop="true" width="450" height="400"></embed>
						      		<a class="btn btn-common" href="${pageContext.request.contextPath}/device/deleteLabRoomVideo?id=${video.id}" onclick="return confirm('确定要删除吗？')">删除</a>
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
						
					</form:form>
					
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
			</div>
		</div>
		<!--题库管理 -->
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">在线考试设置 
				<span><input type="button" onclick="openexam();" value="导入题库"></span>
				<a href="javascript:void(0)">查看所有试题</a>
				</div>
				<div class="tool-box2">
					<table>
						<thead>
						<tr>
							<th>选择题</th>
							<th>判断题</th>
							<th>允许答题次数</th>
							<th>通过名单</th>
						</tr>
						</thead>
						<tr>
						<td>5</td>
						<td>5</td>
						<td>5</td>
						<td>5</td>
						</tr>
						
					</table>
					
					<!-- 导入题库 -->
					<div id="openexam" class="easyui-window" title="导入题库" closed="true" modal="true" minimizable="true" style="width:600px;height: 300px;padding:20px">
						<form:form action="${pageContext.request.contextPath}/device/editDeviceExportexam?idkey=${device.id}" method="post" enctype="multipart/form-data">
							<table>
								<tr>
									<td>选择题库：</td>
									<td>
									<input type="file" id="file" name="file"  readonly="readonly">
									</td>
									<td><input type="submit" value="导入"></td>
								</tr>
								</table>
						</form:form>
					</div>
					<!--  导入题库结束 -->
				</div>
			</div>
		</div>
		<!-- 题库管理结束 -->
	  </div>
	</div>
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</div>
</body>
</html>
