
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="org.springframework.security.authentication.AnonymousAuthenticationToken" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>中国高校私有MOOCs平台-首页</title>
	<meta name="decorator" content="none"/> 
	<link rel="stylesheet" type="text/css" href="css/index.css"> 
	<link rel="stylesheet" type="text/css" href="css/Untitled-1.css"> 
</head>
<body  >
	<!-- 头部 开始-->
	<div>
		<div class="header">
			<!-- logo -->
			<div class="dl">
				<div class="logo">
				<div class="logo-img"><img src="${pageContext.request.contextPath}/images/images/logo_b.png"></div>
				</div>
			</div>
			<!-- 首页 ,关于WeiKe,关于我们-->	
			<div class="mainmenu">
			<ul>
				<li><a href="${pageContext.request.contextPath}">首页</a></li>
				<li><a href="http://www.gvsun.net:3080/gvsuncms/viewContentByid?id=335">关于WeiKe</a></li>
				<li><a href="http://www.gengshang.com">关于我们1</a></li>
			</ul>
			</div>
			<!-- login -->	
		<div class="loginbar">
		<ul id="button-login">
			<%Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  		if (auth != null && (!AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass()))) {%>
	  		<li class="login"><sec:authentication property="principal.username"/></li>
        	<li class="login"><a href="${pageContext.request.contextPath}/pages/logout-redirect.jsp" target="_parent">
        	<img src="${pageContext.request.contextPath}/images/icn_logout.png" /></a></li>
	   		<%}else{%>	
	      	<li class="login"><a href="${pageContext.request.contextPath}/admin/course?currpage=1">登陆</a></li>
	  		<%}%>									
		</ul>
		</div>
				
		</div>
	</div>
	<!-- 头部结束 -->
	<div id="conteiner">
    	<div id="downbotton">
        	<a>点击展开全部课程</a>
        </div>
        <div id="mainbody" class="innerdiv">
        	<div id="headbody" class="innerdiv">
            	<img id="headbodybg" src="images/indexImage/bhindex.jpg"/>
                <div id="mes1">
                	<pre>中国高校私有MOOCs平台</pre>
                </div>
                <ul id="mes2">
					<li>共有${csize}门课程</li>
					<li>共有注册用户${usize}名</li>
					<li>共有资源${fsize}个</li>
                </ul>
                
                <div id="mes3">
                	<pre>庚商智能教育科技有限公司</pre>
                </div>
                <div id="searchdiv">
                <form:form action="${pageContext.request.contextPath}/searchCourse"  method="post">
                	<input id="searcher"  name="search"></input>
                    <img id="searchpic" onMouseOver="searchover('#searchpic')" onMouseOut="searchout('#searchpic')"  src="images/indexImage/searchbut.png"/>
				</form:form>                
                </div>
                <div id="mes1">
                </div>
                <div id="istylePic">
                </div>
                <div id="istyletext" style="display:none;">
               	
                	<span>${fn:length(coueses)}</span>
                    <table id="piclinks">
                    	<tr>
                    	<c:forEach items="${coueses}" var="course">
                        	<th>${pageContext.request.contextPath}${course.logoUrl}</th>
                           
                        </c:forEach>
                        </tr>
                        <tr>
                        <c:forEach items="${coueses}" var="course">
                        	<td>${pageContext.request.contextPath}/courseinfo/${course.id }</td>
                        </c:forEach>
                        </tr>
                    </table>
                    <table id="pictext">
                    	<tr>
                        	<c:forEach items="${coueses}" var="course">
                        	<th>${course.name}</th>
                        	</c:forEach>
                            
                        </tr>
                        <tr>
                        	<c:forEach items="${coueses}" var="course">
                        	<td>${course.name}</td>
                        	</c:forEach>
                        </tr>
                    </table>
                </div>
                <img src="images/indexImage/leftar.png" style="position:absolute; bottom:70px;"/>
                <img src="images/indexImage/rightar.png" style="position:absolute; bottom:70px; right:0px;"/>
            </div>
            <div id="middlebody" class="innerdiv">
            	<div id="midcon">
                	
                 </div>
                 <div style="float:inherit; clear:both"></div>
            </div>
            <div id="footbody" class="innerdiv">
            	<div id="mespic">
                	<img src="images/indexImage/select.png"/>
                    <br/>
                    <img id='joinpic' src="images/indexImage/joinbot.png" onMouseOver="searchover('#joinpic')" onMouseOut="searchout('#joinpic')" onClick=""/>
                </div>
            </div>
        </div>
        <div id="mainbody-l" class="innerdiv">
        </div>
	</div>
	
</body>

</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/istylePic-0.8.js"></script>
