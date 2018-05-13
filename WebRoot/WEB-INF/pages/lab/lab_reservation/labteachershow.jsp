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
<div id="tablewrapper" class="content-box">

<div class="list_top">
		<ul class="top_tittle">
			<li></li>
		</ul>
		<ul id="list-nav" >
			
		</ul>
		<ul class="new_bulid">
			
		</ul>
 </div>


	
<div class="content-box">

		<fieldset class="introduce-box">
								<legend>基本信息</legend>

 <table id="listTable"   >
 <tr align="center" >
 
     <th  >预约性质</th>
     <td  >${infor.nametype}</td> 
     </select></td>      
      <th  >活动名称</th>
     <td >${infor.name }</td>  
    </tr>  
 <tr> 
	 <th >周次</th>
     <td ><c:forEach items="${infor.week }"  var="a" >${a },</c:forEach> </td> 
     </select></td> 	
	 <th >星期</th>
     <td ><c:forEach items="${infor.day}"  var="d" >${d },</c:forEach></td> 
    </tr>
      <tr>      
      <th >节次</th>
     <td ><c:forEach items="${infor.time}"  var="f" >${f },</c:forEach></td> 
     </select></td> 	
	 <th >预约实验室</th>
     <td >${infor.lab}</td>      
      </tr>
    
      <tr> 
	 <th >内容</th>
     <td >${infor.start}</td> 
	 <td ></td>
     <td ></td>   
     </tr>
 </table>
 </fieldset>
 
 </div>
 
</div><!-- </div> -->

</div>
</body>
<!-------------列表结束----------->
</html>