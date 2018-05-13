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
<div id="content" >
<div id="tablewrapper">

<div class="list_top">
		<ul class="top_tittle">
			<li></li>
		</ul>
		<ul id="list-nav" >
			
		</ul>
		<ul class="new_bulid">
			<li class="new_bulid_1"><a onclick="window.history.go(-1)">返回</a></li>
		</ul>
 </div>


     <td>一、基本信息</td></tr>

 <table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter" >
 <tr align="center" >
     <td></td>
     <%-- <td>${l.CLabReservationWeek.name}</td> --%> 
     <td>${l.CDictionaryByLabReservetYpe.CName}</td> 
     <td>星期</td>
     <td>周${l.schoolWeekday.weekdayName}</td> 
     <td>周次</td>
     <td>${l.systemTime.timeName}</td> 
</tr>  
<tr>

     <td>预约内容</td>
     <td colspan="3">${l.reservations}</td>  
      

</tr>
<tr>
<td>备注</td>
     <td>${l.remarks}</td>  
</tr>
 </table>
 
</div>

</div>
</body>
<!-------------列表结束----------->
</html>