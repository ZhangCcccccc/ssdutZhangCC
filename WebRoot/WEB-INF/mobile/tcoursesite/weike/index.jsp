<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta content="none" name="decorator">
<!--控制默认缩放大小、最小缩放大小、最大缩放大小。通过这个可以设置用户的最大和最小缩放程度。-->
<meta name="viewport" content="width=device-width,user-scalable=no" />
<!--//表示手机模式设置为启用。-->
<meta name="format-detection" content="telephone=no" />
<title>微课手机首页</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mobile/index.css" />
</head>
<body>
	<div id="conteiner">
		<div id="inner">
			<div id="header">
				<%--<img class="logo_pic"
					src="${pageContext.request.contextPath}/images/mobile/logo_b.png" style="width:90%;"/>
				 <img src="${pageContext.request.contextPath}/images/mobile/logo.png"/> 
				 --%><img class="logo_pic"
					src="${pageContext.request.contextPath}/images/mobile/courses.png" /> 
				 <%--<img class="logo_img"
					src="${pageContext.request.contextPath}/images/mobile/logo_b.png" /> 
			--%></div>
			
			<c:if test="${empty user}">
	            <div class="right_container">
	                <input class="login_btn" type="button" value="登录" onClick="jump('${pageContext.request.contextPath}')"/>
	            </div>
            </c:if>
            <c:if test="${!empty user}">
            <div class="right_container">
                <div style="text-align:right;font-size:12px;">
                    <%
					   if(session.getAttribute("selected_role").toString().equals("ROLE_TEACHER")){
					%>
     				    <font style="color:#000;">${user.cname} 老师您好  </font>
     				<%}else if(session.getAttribute("selected_role").toString().equals("ROLE_STUDENT")){%>
     				    <font style="color:#000;">${user.cname} 同学您好  </font>
     				<%}%>
     			   <a href="${pageContext.request.contextPath}/pages/logout-redirect.jsp" target="_parent"><font color=write><font style="color:#000;">退出</font> </a>  
                </div> 
            </div>
            </c:if>
			
			
			<div id="mylessons">
				<div id="mylesmes2">Time and tide wait for no man ,we should
					make the best of every minute.</div>
				<%-- <img src="${pageContext.request.contextPath}/images/mobile/sheet.png"/> --%>
				<div id="dater">
					<Span id="year"> </span> <span id="date"> </span>
					<div id="day"></div>
				</div>
			</div>
			<div id="buttonbox">
				<form action="${pageContext.request.contextPath}/tcoursesite/weike/courses"
					method="post" id="form1">
					<div id="searchlessons" style="border:3px solid ##33CCFF">
						<input type="search" id="searcher" name="search" />
						<div>
							<a onclick="submit();"><img
								src="${pageContext.request.contextPath}/images/mobile/searchbut.png" />
							</a>
						</div>
					</div>
					<!-- 分享开始 -->
					<!-- JiaThis Button BEGIN -->
					<div class="jiathis_style_32x32" style="    overflow: hidden;
    margin-top: 5px;margin-left:auto; margin-right:auto;width:317px;">
						<a class="jiathis_button_cqq"></a> <a class="jiathis_button_qzone"></a>
						<a class="jiathis_button_tsina"></a> <a class="jiathis_button_tqq"></a>
						<a class="jiathis_button_weixin"></a> <a
							class="jiathis_button_renren"></a> <a
							href="http://www.jiathis.com/share"
							class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
						<a class="jiathis_counter_style"></a>
					</div>
					<script type="text/javascript"
						src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
					<!-- JiaThis Button END -->
					<!-- 分享结束 -->
				</form>
			</div>
			<div id="lessonslist" onClick="jump('${pageContext.request.contextPath}/tcoursesite/weike/courses')">
				<%-- <div>
                	<img src="${pageContext.request.contextPath}/images/mobile/books.png"/>
                </div> --%>
				<span id="leslistmes"> 点击开始学习 </span>
			</div>

		</div>
</body>
</html>
<SCRIPT>
	var date = new Date();
	document.getElementById('year').innerHTML = date.getFullYear() + '/';
	document.getElementById('date').innerHTML = date.getMonth() + 1 + '/'
			+ date.getDate();
	document.getElementById('day').innerHTML = '星期'
			+ '日一二三四五六'.charAt(new Date().getDay());
	document.onreadystatechange = function() {
		document.documentElement.scrollLeft = (document.documentElement.scrollWidth - document.documentElement.offsetWidth);
	};
	window.onresize = function() {
		resize();
	}
	window.onload = function() {
		resize();
	}
	function resize() {
		var width = document.documentElement.clientWidth;
		if (width > document.documentElement.clientHeight)
			document.getElementById("header").style.display = "none";
		else
			document.getElementById("header").style.display = "block";
		document.getElementById("conteiner").style.width = width + 'px';
		//document.getElementById("conteiner").style.height=960*width/550+'px';
		document.getElementsByTagName("html").item(0).style.fontSize = 120
				* width / 640 + '%';
	}
	function submit() {
		if ($("#searcher").val().length != 0) {
			$("#form1").action = "${pageContext.request.contextPath}/searchCourse";
			$("#form1").submit();
		} else {
			alert("请输入要查询的内容！");
		}
	}
</SCRIPT>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/mobile/allpages.js"></script>