<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="bundles.projecttermination-resources"/>

<html >  
<head>
 <meta name="decorator" content="iframe"/>
<title><fmt:message key="html.title"/></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>

<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" />
</head>
<body style="overflow:hidden">



<!-- 结项申报列表 -->
<!-- <div class="tab"> -->
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
<div class="content-box">	

<!-- <div class="list_top">
		<ul class="top_tittle">
			<li></li>
		</ul>
		<ul id="list-nav" >
			
		</ul>
		<ul class="new_bulid">
			<li class="new_bulid_1"><a onclick="window.history.go(-1)">返回</a></li>
		</ul>
 </div> -->


   <!--   <td>一、基本信息</td></tr> -->

 <table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter" >
 <tr>
		     <td>预约时间：</td>
		     <td>
		     	周次：<c:forEach items="${l.week }"  var="a" >${a }&nbsp;</c:forEach>
				星期：<c:forEach items="${l.day}"  var="d" >${d }&nbsp;</c:forEach>
				节次：<c:forEach items="${l.time}"  var="f" >${f }&nbsp;</c:forEach>
			</td> 
		</tr>  
<tr>
     <td>预约内容</td>
     <td colspan="3">${lab.reservations}</td>  
</tr>
<tr>
     <td>预约人</td>
     <td colspan="3">${lab.user.cname}</td>  
</tr>
<tr>
    <td>预约实验室</td>
    <td>${l.lab}</td>  
</tr>
</table>
<c:if test="${man>0 }">
<c:if test="${fn:contains(lab.labRoom.labRoomAdmins,user.username)}">
<c:if test="${l.cont==2 || l.cont==3}">
 <form:form  action="${pageContext.request.contextPath}/labReservation/auditsavelabreservtion?idkey=${l.id}&tage=${tage}"  method="post"  modelAttribute="labReservationAudit" target="_parent" >
   <table>
      <%-- <c:if test="${lab.CLabReservationType.id==1}"> --%>
      <c:if test="${lab.CDictionaryByLabReservetYpe.CCategory=='c_lab_reservation_type' && lab.CDictionaryByLabReservetYpe.CNumber=='1'}">
       <tr>
          <%-- <td>预约类型：</td><td colspan="4">${lab.CLabReservationType.name}</td> --%>
          <td>预约类型：</td><td colspan="4">${lab.CDictionaryByLabReservetYpe.CName}</td>
       </tr>
        <tr>
           <%-- <td>审核：</td><td colspan="4"><form:radiobutton path="COpreationAuditResults.id"  value="4" />拒绝  <form:radiobutton path="COpreationAuditResults.id"  value="1" />通过 </td> --%>
           <td>审核：</td><td colspan="4"><form:radiobutton path="CDictionary.id"  value="560" />拒绝  <form:radiobutton path="CDictionary.id"  value="561" />通过 </td>
       </tr>
        <tr>
           <td>审核意见：</td><td colspan="4"><form:input path="comments" /> </td>
       </tr>
       </c:if>
       <%-- <c:if test="${lab.CLabReservationType.id==2}"> --%>
       <c:if test="${lab.CDictionaryByLabReservetYpe.CCategory=='c_lab_reservation_type' && lab.CDictionaryByLabReservetYpe.CNumber=='2'}">
       <tr>
           <%-- <td>预约类型：</td><td colspan="4">${lab.CLabReservationType.name}</td> --%>
           <td>预约类型：</td><td colspan="4">${lab.CDictionaryByLabReservetYpe.CName}</td>
       </tr>
       <tr>
           <%-- <td>审核：</td><td colspan="4"><form:radiobutton path="COpreationAuditResults.id"  value="4" />拒绝  <form:radiobutton path="COpreationAuditResults.id"  value="1" />通过 </td> --%>
           <td>审核：</td><td colspan="4"><form:radiobutton path="CDictionary.id"  value="560" />拒绝  <form:radiobutton path="CDictionary.id"  value="561" />通过 </td>
       </tr>
        <tr>
           <td>审核意见：</td><td colspan="4"><form:input path="comments" /> </td>
       </tr>
       </c:if>
        <tr>
           <td colspan="5"><input type="submit" value="提交"> </td>
       </tr>
 </table>
   </form:form>
</c:if>
</c:if>
</c:if>

   <table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter" >
    <tr><td colspan="6">审核人信息 </td></tr>
 <c:forEach items="${admins}" var="s">
 <tr align="left" >
     <td>审核人：</td>
     <td>${s.user.cname}</td> 
     <td>工号:</td>
     <td>${s.user.username}</td> 
     <td>部门:</td>
     <td>${s.user.schoolAcademy.academyName}</td> 
      <td>联系方式:</td>
     <td>${s.user.telephone}</td> 
</tr>  
</c:forEach>
<c:if test="${l.cont==1 || l.cont==4}"> <!-- 审核完成 -->
		<table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter" >
		   <tr><td colspan="7">审核结果 </td></tr>
			 <c:forEach items="${lab.labReservationAudits}" var="audit" varStatus="i">
			 <c:if test="${i.count eq 1}">
				 <tr align="left" >
				     <td>审核人：</td>
				     <td>${audit.user.cname}[${audit.user.username}]</td> 
				     <td>审核结果:</td>
				     <td>${audit.CDictionary.CName}</td> 
				     <td>审核意见:</td>
				     <td>${audit.comments}</td> 
				</tr>
			</c:if>
			</c:forEach>
		</table>
	</c:if>
</table>
</div>
</div>
</div>
</div>

</div>
</body>
<!-------------列表结束----------->
</html>