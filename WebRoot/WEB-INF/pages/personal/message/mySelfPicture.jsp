<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipmentlend-resources"/>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/SpryTabbedPanels.js"> 
 </script>
 <link type="text/css" rel="stylesheet" href="css/door/style.css"></link>

<link href="css/door/media-queries.css" type="text/css" rel="stylesheet"></link>

<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>

<script src="js/jquery.glide.js" type="text/javascript"></script>

<!--

 Slider Plugin 

-->

<script src="js/jquery.glide.js" type="text/javascript"></script>

<link type="text/css" rel="stylesheet" href="css/bdsstyle.css"></link>

<!--

 html5.js for IE less than 9 

-->

<!--

[if lt IE 9]>
	<script src="js/html5.js"></script>…

-->

<!--

 css3-mediaqueries.js for IE less than 9 

-->

<!--

[if lt IE 9]>
	<script src="js/css3-mediaqueries.j…

-->

<style></style>

<style class="firebugResetStyles" charset="utf-8" type="text/css"></style>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="logo" >
		<div class="mobile-action"></div>
		<img src="${pageContext.request.contextPath}/images/logodoor.png" id="logo">
</div>
	
	<div class="content">
	
	<table><thead>
	<th></th>
	</thead><tbody>
	<c:forEach items="${labRooms}" var="s">
	<tr><td>
		<ul class="picture-box">
			<li>
				<a href="${pageContext.request.contextPath}/images/logodoor.png">
				<!--判断  commonDocuments是否为空，空的话就显示默认图片，不为空就遍历显示-->
				<c:if test="${!empty s.commonDocuments}"><!--先判断  commonDocuments有记录-->
			     	<c:forEach var="entry" items="${s.commonDocuments}" >   
				 	<img src="${pageContext.request.contextPath}/${entry.documentUrl}" width="400" height="300">	 
				 	<c:if test="${entry.documentUrl==null||fn:length(entry.documentUrl)==0}"><!-- 有记录之后判断  该记录是否为空或者该记录字段长度为0 -->
				 		<img src="${pageContext.request.contextPath}/images/meiyou.png" width="400" height="300">
				 	</c:if>
				 	</c:forEach>
				 </c:if>
				 <c:if test="${empty s.commonDocuments}">
				 	<img src="${pageContext.request.contextPath}/images/meiyou.png" width="400" height="300">
				 </c:if>
				 
				 
				<br>   			 
 
					${s.labRoomName}<br>
				</a>
			</li>
			
			</ul></td>
			</tr></c:forEach>
			</tbody></table>
	</div>
	</div>
	<div class="power">
		Copyright 2010 东华大学 沪ICP备05003365 技术支持：GVSUN
	</div>
  </div>
</body>
</html>

