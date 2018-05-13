<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe" />
  
  <!-- 下拉框的样式 -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
	<!-- 下拉的样式结束 -->
	<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/layer/layer.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/cmsSystem/plugins/sweetalert/sweetalert.css" rel="stylesheet">
	
  <script type="text/javascript">

  </script>
 <style>
  
fieldset label {
    display: block;
    float: left;
    font-weight: bold;
    height: 25px;
    line-height: 25px;
    margin: -5px 0 5px;
    padding-left: 10px;
    text-shadow: 0 1px 0 #fff;
    text-transform: uppercase;
    width: 90%;
}  
</style>
</head>
  
<body>
<div class="main_container cf rel w95p ma">
  <!-- 内容栏开始 -->
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
  <!-- 标题 -->
  <div class="right-content">
	  <div id="TabbedPanels1" class="TabbedPanels">
		  <div class="TabbedPanelsContentGroup">
			  <div class="TabbedPanelsContent">
				  <div class="content-box">
				  	<div class="title">
				  	<c:if test="${flag eq 1}"><button onclick="addAgents()" class="btn btn-edit">添加硬件</button></c:if>
				  	
				  	</div>
					  	<table> 
							<thead>
							<tr>
								<th>硬件名称</th>
								<th>Ip</th>
								<th>端口</th>
								<th>PageCam</th>
								<th>物联服务器</th>
								<th>操作</th>
							</tr>
							</thead>
							
							<tbody>
							<c:if test="${flag eq 0}">
										<td>${labRoomDevice.labRoomAgent.CDictionary.CName }</td>
										<td>${labRoomDevice.labRoomAgent.hardwareIp }</td>
										<td>${ labRoomDevice.labRoomAgent.hardwarePort}</td>
										<td>
											${labRoomDevice.labRoomAgent.hardwareRemark }
										</td>
										<td>
											${labRoomDevice.labRoomAgent.commonServer.serverName }
										</td>
										<td><a href="${pageContext.request.contextPath}/labRoom/deleteLabRoomDeviceAgent?deviceId=${id}" onclick="return confirm('你确定删除吗？')">删除</a></td>
									</tr></c:if>
							</tbody>
						</table>
				  </div>
			  </div>
		  </div>
	  </div>
  </div>

    <!-- 添加物联硬件 -->
	<div id="addAgent" class="easyui-window " title="添加物联硬件" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 500px; height: 300px;">
				<form:form id="userForm" action="${pageContext.request.contextPath}/labRoom/saveLabRoomDeviceAgent?deviceId=${id}" method="post" >
				<div class="content-box">
									<input type="submit" value="添加" onclick="add();">
						<table class="tb" id="my_show">
							<thead>
							<tr>
								<th>选择</th>
								<th>硬件名称</th>
								<th>Ip</th>
								<th>端口</th>
								<th>PageCam</th>
								<th>物联服务器</th>
							</tr>
							</thead>
							
							<tbody>
							<c:forEach items="${listAgents }" var="curr" varStatus="i">
									<tr>
										<td><input type="radio" name="selectAgent" id="selectAgent" value="${curr.id }"></td>
										<td>${curr.CDictionary.CName }</td>
										<td>${curr.hardwareIp }</td>
										<td>${ curr.hardwarePort}</td>
										<td>
											${curr.hardwareRemark }
										</td>
										<td>
											${curr.commonServer.serverName }
										</td>
									</tr</c:forEach>
							</tbody>
						</table>
						</div>
						</form:form>
	</div>
    <!-- 下拉框的js -->
	<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
	
	<script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/iCheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/sweetalert/sweetalert.min.js"></script>
	
	<script type="text/javascript">
	function addAgents(){
		$("#addAgent").show();
		  var topPos = window.pageYOffset;
	      //使得弹出框在屏幕顶端可见
	      $('#addAgent').window({left:"100px", top:topPos+"10px"});

	    $("#addAgent").window('open'); 
	}
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
  </div>
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
