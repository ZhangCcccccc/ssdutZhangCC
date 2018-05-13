<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.project-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
 <meta name="decorator" content="none"/> 

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
						<li><a href="javascript:void(0)">实验室及预约管理</a></li>
						<li class="end"><a href="javascript:void(0)">实验室预约管理</a></li>
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
              <%-- <td>审批流程名称</td> <td>${infor.CLabReservationType.name}</td><td>申请人：</td><td>${infor.user.cname}</td> --%>
              <td>审批流程名称</td> <td>${infor.CDictionaryByLabReservetYpe.CName}</td><td>申请人：</td><td>${infor.user.cname}</td>
         </tr>
         <tr >
             <td>实验室</td> <td>${infor.labRoom.labRoomName}</td><td></td><td></td>
         </tr>
      </table>
<div id="tt" class="easyui-tabs" style="height:910px;">
<div title="预约信息">
<iframe scrolling="yes" frameborder="0" id="myAtendList" src="${pageContext.request.contextPath}/labreservation/labteachershow?idkey=${infor.id}&tage=${tage}" style="width:100%;height:100%;" scrolling="no"  onload="this.height=this.contentWindow.document.documentElement.scrollHeight"></iframe>
</div>
<%-- <c:if test="${man>0}"> --%>
<div title="实验室管理员审核">
<iframe scrolling="yes" frameborder="0" id="myResponseList" src="${pageContext.request.contextPath}/labreservation/teacherLabreservationaudt?idkey=${infor.id}&tage=${tage}" style="width:100%;height:100%;" scrolling="no"  onload="this.height=this.contentWindow.document.documentElement.scrollHeight"></iframe>
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