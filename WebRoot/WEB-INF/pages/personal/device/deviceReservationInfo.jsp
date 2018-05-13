<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.project-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
 <meta name="decorator" content="none"/> <!-- 可能会出错，跳转到massagelist页面的话，先删掉这一句试试 -->

<title>Edit <fmt:message key="project.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery/css/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/locale/easyui-lang-zh_CN.js"></script>
	<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
</head>

<body>
<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">实验室设备预约</a></li>
						<li class="end"><a href="javascript:void(0)">审核状态</a></li>
					</ul>
				</div>
			</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
<div class="content-box">		     
<form:form  modelAttribute="project">
 <table >
          <tr >
              <td>审批流程名称</td> <td>设备预约审核</td><td>申请人：</td><td>${reservation.userByReserveUser.cname}</td>
         </tr>
         <tr >
             <td>设备</td> <td>${reservation.labRoomDevice.schoolDevice.deviceName}</td><td></td><td></td>
         </tr>
      </table>
<div id="tt" class="easyui-tabs" style="height:910px;">
<div title="教师">
<iframe scrolling="yes" frameborder="0" id="myAtendList" src="${pageContext.request.contextPath}/personal/message/findTeacherReservationResult?id=${reservation.id}" style="width:100%;height:100%;" scrolling="no"  onload="this.height=this.contentWindow.document.documentElement.scrollHeight"></iframe>

</div>
<div title="实验室管理员审核">
<iframe scrolling="yes" frameborder="0" id="myResponseList" src="${pageContext.request.contextPath}/personal/message/findManagerReservationResult?id=${reservation.id}" style="width:100%;height:100%;" scrolling="no"  onload="this.height=this.contentWindow.document.documentElement.scrollHeight"></iframe>

</div>


</div>
</form:form>
<div class="clear">&nbsp;</div>
</div>

</div>
</div>
</div>
</div>
</body>
</html>