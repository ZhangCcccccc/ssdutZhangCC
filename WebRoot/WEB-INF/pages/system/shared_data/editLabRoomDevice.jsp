<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 -->
  <script type="text/javascript"> 
   
  </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>  
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">学院设备</a></li>
		    <li> <a href="javascript:void(0)">新建编辑学院设备</a> </li> 
		</ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title">
       	新建编辑学院设备
      </div>
	</div>
	<form:form action="${pageContext.request.contextPath}/saveLabRoomDevice" method="POST" modelAttribute="labRoomDevice">
	<div class="new-classroom"> 
		<fieldset> 
	    	<label>设备:<font color="red">*</font></label>
	   	 <form:select path="schoolDevice.deviceNumber" id="deviceNumber" required ="true"  class="chzn-select">
				<form:option value="">请选择</form:option>
				<c:forEach items="${schoolDevices }" var="curr" varStatus="i">
					<form:option value="${curr.deviceNumber }">${curr.deviceName }[${curr.deviceNumber }]</form:option>
				</c:forEach>
		</form:select>
	  	</fieldset>
	  	<fieldset> 
	    	<label>所属实验室<font color="red">*</font></label>
	   	 <form:select path="labRoom.id" id="labRoom" required ="true"  class="chzn-select">
				<form:option value="">请选择</form:option>
				<c:forEach items="${labRooms}" var="curr" varStatus="i">
					<form:option value="${curr.id }">${curr.labRoomName}</form:option>
				</c:forEach>
		</form:select>
	  	</fieldset>
	  	<fieldset>
						    <label>设备管理员<font color="red">*</font></label>
						      <form:select id="usernameManager" path="user.username" required ="true"  class="chzn-select">
						      <c:if test="${not empty device.user.username }">
						      	<form:option value="${device.user.username }">
						      	[${device.user.username }]${device.user.cname }
						      	</form:option>
						      </c:if>
						      <c:if test="${empty device.user.username }">
						      	<form:option value="">
						      	- - - 请选择- - - 
						      	</form:option>
						      </c:if>	
						      	<c:forEach items="${users}" var="t">
					              <form:option value="${t.key}">[${t.value}]${t.key}</form:option>
					             </c:forEach>
								</form:select>
					</fieldset>	
					<fieldset>
						    <label>管理员电话</label>
						     <form:input id="managerTelephone" path="managerTelephone"/>
					</fieldset>	
					<fieldset>
						    <label>管理员邮箱</label>
						     <form:input id="managerMail" path="managerMail"/>
					</fieldset>	
					<fieldset>
						    <label>管理员办公室</label>
						     <form:input id="managerOffice" path="managerOffice"/>
					</fieldset>	
					<fieldset>
						    <label>设备状态</label>
						      <%-- <form:select id="status" path="CDeviceStatus.id" class="chzn-select">
						      	<form:option value="1">正常使用</form:option>
								<form:options items="${stutus}" itemLabel="name" itemValue="id"/>
								</form:select> --%>
								<form:select id="status" path="CDictionaryByDeviceStatus.id" class="chzn-select">
								<form:option value="">请选择</form:option>
								<form:options items="${stutus}" itemLabel="CName" itemValue="id"/>
								</form:select>
					</fieldset>	
						<fieldset>
						    <label>所属类型</label>
						      <%-- <form:select id="type" path="CDeviceType.id" class="chzn-select">
						      	<form:option value="">请选择</form:option>
								<form:options items="${types}" itemLabel="name" itemValue="id"/>
								</form:select> --%>
								<form:select id="type" path="CDictionaryByDeviceType.id" class="chzn-select">
								<form:option value="">请选择</form:option>
								<form:options items="${types}" itemLabel="CName" itemValue="id"/>
								</form:select>
					</fieldset>	
					<fieldset>
						    <label>收费标准</label>
						      <%-- <form:select id="charge" path="CDeviceCharge.id" class="chzn-select">
						      	<form:option value="">请选择</form:option>
								<form:options items="${charges}" itemLabel="name" itemValue="id"/>
								</form:select> --%>
								<form:select id="charge" path="CDictionaryByDeviceCharge.id" class="chzn-select">
								<form:option value="">请选择</form:option>
								<form:options items="${charges}" itemLabel="CName" itemValue="id"/>
								</form:select>
					</fieldset>
					<%-- <c:if test="${device.isAuditTimeLimit eq 1 &&(not empty device.CActiveByTeacherAudit&&device.CActiveByTeacherAudit.id eq 1)}"> --%>
					<c:if test="${device.isAuditTimeLimit eq 1 &&(not empty device.CDictionaryByTeacherAudit && device.CDictionaryByTeacherAudit.CCategory=='c_active' && device.CDictionaryByTeacherAudit.CNumber=='1')}">
					<fieldset>
						    <label>预约审核时间限制</label>
						      <form:select id="auditTimeLimit" path="auditTimeLimit">
						      	<form:option value="48">48小时（两天）</form:option>
						      	<form:option value="12">12小时</form:option>
						      	<form:option value="24">24小时（一天）</form:option>
						      	<form:option value="36">36小时</form:option>
						      	<form:option value="60">60小时</form:option>
						      	<form:option value="72">72小时（三天）</form:option>
						      	<form:option value="84">84小时</form:option>
						      	<form:option value="96">96小时（四天）</form:option>
							  </form:select>
					</fieldset>
					</c:if>
					<c:if test="${device.isAuditTimeLimit ne 1 }">
						<input type="hidden" id="auditTimeLimit" value="-1">
					</c:if>
						<fieldset>
						    <label>费用</label>
						     <form:input id="price" path="price"/>元
						</fieldset>
						
					 <fieldset class="introduce-box">
						     <label>主要技术指标</label>
							<form:textarea id="indicators" path="indicators"/>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>功能应用范围</label>
							<form:textarea id="functionOfDevice" path="function"/>
					 </fieldset><%--
					 <fieldset class="introduce-box">
						     <label>技术特色</label>
							<form:textarea path="features"/>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>主要应用领域</label>
							<form:textarea path="applications"/>
					 </fieldset>
					--%>
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="确定">
		  <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
        </div>
	</div>
	</form:form>
	
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
		    
		    function checkUnique(){
		    	var deviceNumber = document.gerElementById("deviceNumber").value;
		    	
		    }
		</script>
	<!-- 下拉框的js -->
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
