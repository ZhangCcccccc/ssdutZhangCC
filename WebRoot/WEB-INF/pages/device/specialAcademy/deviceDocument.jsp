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

<script type="text/javascript">

 



function uploadDocument(){
	var url = "${pageContext.request.contextPath}/device/editDeviceDocumentRest/" + ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}" +"/"+ ${username}+"/"+${page}+"/" + +${device.id}+ "/"+"${schoolDevice_allowAppointment}";
			 $('#searchFile').window('open');
			 $('#file_upload').uploadify({
				'formData':{id:'${device.id}'},    //传值
	            'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
	            'uploader':'${pageContext.request.contextPath}/device/deviceDocumentUpload;jsessionid=<%=session.getId()%>',		//提交的controller和要在火狐下使用必须要加的id
	            buttonText:'选择pdf文件',
	            'fileTypeDesc' : '支持格式:pdf', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
	            'fileTypeExts' : '*.pdf;',// 允许的格式 
	            'onUploadError':function(file, errorCode, errorMsg, errorString) {
	            	alert("上传失败，请检查文件格式！");
	        	} ,
	             onQueueComplete : function(data) {//当队列中的所有文件全部完成上传时触发	
	      			    //当上传玩所有文件的时候关闭对话框并且转到显示界面
	            	 $('#searchFile').window('close');
	            	//window.location.href="${pageContext.request.contextPath}/device/deviceDocument?deviceId="+${device.id}; 	           	
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
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceVideoRest(${device.id})">相关视频</a>
		</li>
		<li class="TabbedPanelsTab selected" tabindex="0">
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
				<div id="title">相关文档</div>
				<a class="btn btn-new"  herf="#" onclick="openSetupLink();">返回</a>
				<a class="btn btn-new" onclick="uploadDocument();">上传文档</a>
			</div>
					
						     
								<table>
								<tr>
								<th>序号</th>
								<th>文档名称</th>
								<th>操作</th>
								</tr>
								<c:forEach items="${device.commonDocuments}" var="d" varStatus="i">
								<c:set var="count" value="0" />
								<c:if test="${d.type==2}"><!-- 文档 -->
								<c:set var="count" value="${count+1}" />
								<tr>
								<td>${count}</td>
								<td>${d.documentName}</td>
								<td>
									<a href="${pageContext.request.contextPath}/device/downloadDocument?id=${d.id}">下载</a> 
									<%--<a href="${pageContext.request.contextPath}/device/deleteDeviceDocument?id=${d.id}">删除</a>
									--%>
									<a href="javascript:deleteDeviceDocumentRest(${d.id})"  onclick="return confirm('确定要删除吗？')" >删除</a>
									<a href="${pageContext.request.contextPath}/${d.documentUrl}" target="_blank">查看</a>
								</td>
								</tr>
								</c:if>
						    	
						     	</c:forEach>
						     	</table>
				
  			<input type="hidden" id="labRoomId" value="${labRoomId }">
			<input type="hidden" id="deviceName" value="${deviceName }">
			<input type="hidden" id="deviceNumber" value="${deviceNumber }">
			<input type="hidden" id="username" value="${username }">
			<input type="hidden" id="page" value="${page }">
    		<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
      		<input type="hidden" id="schoolDevice_allowAppointment" value="${schoolDevice_allowAppointment}">
			</div>
		</div>
		<!-- 上传图片 --> 				
	<div id="searchFile" class="easyui-window" title="上传文档" closed="true" iconCls="icon-add" style="width:400px;height:200px">
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
	  </div>
	</div>
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</div>

</body>
</html>
