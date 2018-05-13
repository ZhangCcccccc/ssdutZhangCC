<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta name="decorator" content="iframe"/>
    <title>选择类型</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css"
	media="screen" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/is_LeftList.css"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/is_Searcher.css"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/is_SystemUI_Allpages.css"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/iconFont.css" />
    <script type="text/javascript">
    $(function() { 
		$("#checkCenter").show();
	  	$("#checkCenter").window('open'); 
	});
	
	</script>
	
	<style>
.box1{border: 1px solid #ccc;
	border-radius: 5px;
	background-color: #fdfdfd;
	width: 99.5%;
	margin: 0 auto;
	height: auto;}
.box1 ul{list-style-image: url('../images/icn_jump.png');
	width: 100%;
	margin-top:10px;
	overflow: hidden;
	margin-right: 5%;
	}
.panel-tool{display:none;}
.box1 li{float: left;
	margin: 10px;
	width: 31%;}
.box1 li a{color: #666;
font: 14px/20px '黑体';}
.box1 li a:hover{color: #77BACE;
text-decoration: underline;}
.tjump{width: 95%;
	margin-top: 15px;
	font: bold 18px/38px '黑体';
	background: url('../images/jump_bg.png') no-repeat -2px top;
	height: 38px;
	padding-left: 20px;
	color: #FFF;
	}
</style>


  </head>
  
   <body>
     
	<div class="iStyle_Conteiner">
		
			
			<div class="iStyle_Iframe">
				
				<%-- <iframe src="${pageContext.request.contextPath}/personal/message/mySelfInfo?tage=0&currpage=1"> </iframe> --%>
				<div  id="checkCenter" class="easyui-window " title="选择类型" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width:960px; ">
		
		 		<div class="box1">
  				<div class="tjump">请选择</div>
				<ul>
				<c:forEach items="${centers}" var="center">
				<li><a href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=9&cid=${center.id}">${center.centerName}</a></li>
				</c:forEach>
				
				</ul>
  				</div>
			
	 			</div>
			</div>
			
			
		</div>
	</div>
 
     
	
   </body> 
</html>
