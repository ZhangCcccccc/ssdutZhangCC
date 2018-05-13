<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" import="java.util.*" import="java.sql.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
    <%
    String sUrl = ((HttpServletRequest) pageContext.getRequest()).getServletPath(); 
    if (sUrl.indexOf("index.jsp")>-1||sUrl.indexOf("checkCenter")>-1){
    %>
    <meta http-equiv="refresh" content="0; url=${pageContext.request.contextPath}/test?labCenterId=-1">   
    <%}else if(session.getAttribute("LOGINTYPE")!=null && session.getAttribute("LOGINTYPE").equals("res")){
    	%>
    	<meta http-equiv="refresh" content="0; url=${pageContext.request.contextPath}/lab/labAnnexList?currpage=1">     
    	<%}
    else if( session.getAttribute("LOGINTYPE")==null||(sUrl.indexOf("index.jsp")>-1&&!"ZYY".equals(session.getAttribute("LOGINTYPE")))){
    	%>
    	<meta http-equiv="refresh" content="0; url=${pageContext.request.contextPath}/self/myCenter">     
    	<%}else{%>
    <title>实验室智能管理系统</title>
    <meta http-equiv="P3P" content='CP="IDC DSP COR CURa ADMa  OUR IND PHY ONL COM STA"'>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iStyle_Feelings_Base.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
    <!--以下六行不得删除！-->
    <!--[if IE 8] > 
	<meta http-equiv="X-UA-Compatible" content="IE=7"/> 
<![endif]-->
    <!--[if IE 7]>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome-ie7.min.css"/>
<![endif]-->
    <script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_Feelings_Core.js"></script>
    <script>
        var istyle_feelings_core = null;
        $(document).ready(function (e) {
            /*
		settings: 框架主设置
			以下属性为true时
			testmod: 启动测试模式
			autourl: 自动根据iframe刷新url
			ie7compat: 打开ie7前进后退按钮适应性
			unselectable: 框架内文本无法被选中
			nocookies: 强制关闭所有框架控件的cookies
		
		menu: 主菜单设置
			以下属性为true时
			switchon: 启动主菜单
			algintoiframe: 边缘与主显示容器边缘对齐
			autoresize: 自动适应宽度
			
		tree: 侧边栏设置
			以下属性为true时
			switchon: 启动侧边栏
			cookies: 启用cookies
			scrolling: 启动滚动条
			mousewheel: 启动鼠标滚轮控制
			scrollbutton: 卷动菜单按钮
			allwayshowmark: 始终显示二级菜单上角标注（注，IE7下无效）
			dragwidth: 启用用户拖拽宽度
			
			带有配置的属性
			longitudinaldom: 控件上方和下方的DOM结构，class或id，用于自适应
				例如 'longitudinaldom':'.iStyle_Feelings_Header,.iStyle_Feelings_Footer'
			latitudinaldom: 控件左方和右方的dom结构，class或id，用于自适应
				例如 'latitudinaldom':'.iStyle_Feelings_Maininner'
			width: 用于指定侧边栏的默认宽度
				px值，指定固定像素值
			hover_animate: 鼠标移入菜单项变化特效
				false 关闭鼠标移入时动画
				0-1的值 为鼠标离开时的透明度 移入时透明度为1			
		
		minwindow: 主菜单单设置
			以下属性为true时
			switchon: 启动框架内悬浮小窗口
			multiwin: 启用多窗口
			
		userinteraction: 用户交互设置
		self-adaption_target: 启动自适应的对象		
	*/
            istyle_feelings_core = new iStyle_Feelings_Core({
                'settings': {
                    'target': '.iStyle_Feelings_Conteiner[name="demo"]',
                    'testmode': false,
                    'autourl': true,
                    'ie7compat': true,
                    'unselectable': true,
                    'nocookies': false
                },
                'menu': {
                    'switchon': true,
                    'algintoiframe': true,
                    'autoresize': false,
                    'hover_animate': '0.77'
                },
                'tree': {
                    'switchon': true,
                    'cookies': true,
                    'scrolling': true,
                    'mousewheel': true,
                    'scrollbutton': true,
                    'allwayshowmark': false,
                    'dragwidth': false,
                    'longitudinaldom': '.iStyle_Feelings_Header,.iStyle_Feelings_Footer',
                    'latitudinaldom': '.iStyle_Feelings_Maininner',
                    'hover_animate': '0.77',
                    'width': 'auto'
                },
                'minwindow': {
                    'switchon': false,
                    'cookies': false,
                    'multiwin': true
                },

                'userinteraction': {
                    'self_adaption': {
                        'target': '.iStyle_Feelings_Maininner',
                        'longitudinaldom': '.iStyle_Feelings_Header,.iStyle_Feelings_Footer',
                        'latitudinaldom': '.iStyle_Feelings_Tree'
                    }
                }
            });
        });
    </script>
</head>

<body class="iStyle_Feelings_Conteiner" name="demo">
    <div class="iStyle_Feelings_Conteiner" name="demo">
        <div class="iStyle_Feelings_Header">
                                 西安电子科技大学实验室管理系统&nbsp;<font size="4">${center.centerName}</font>
            <div class="iStyle_Feelings_LogoBox">
                <!--<img src="images/logo_01.jpg" alt="">-->
            </div>
            <div class="iStyle_Feelings_UsersBox">
                <form>
                    <label>
                    	<a class="button" href="${pageContext.request.contextPath}/self/myCenter">首页</a>  
                    	<%-- <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_LABORATORYMAINTENANCE">
                    		<a class="button" href="${pageContext.request.contextPath}/admin/">网站管理</a>  
                    	</sec:authorize>	 --%>
						   ${sessionScope.authorityCName}
						   <i class="icon-user-2"></i>   
						   ${user.cname} 您好  
						   <a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp" target="_parent">退出 </a>  
						  <%-- <sec:authorize ifNotGranted="ROLE_STUDENT">
						   <a class="button" href="${pageContext.request.contextPath}/checkCenter">切换中心</a>  
						   </sec:authorize> --%>
                    </label>
                </form>
            </div>
        </div>
        <div class="iStyle_Feelings_Tree" slided="false">
            <div class="iStyle_Feelings_Tree_SlideButton"></div>
            <jsp:include page="/WEB-INF/sitemesh-common/menuAdmin.jsp" />
        </div>
        <div class="iStyle_Feelings_Maininner">
            <%
			if (((HttpServletRequest) pageContext.getRequest()).getServletPath().equals("/index.jsp")||sUrl.equals("/checkCenter")){
			}else{
			%>
				<decorator:body />
			<%}%>
            <iframe class="iStyle_Feelings_Iframe" name="iStyle_Feelings_Iframe" frameborder="no" border="0">
            </iframe>
        </div>
        <div class="iStyle_Feelings_Footer"> © 2014上海庚商网络信息技术有限公司|@POWER BY GVSUN </div>
        <div class="cookiestest">
        </div>
    </div>
<%} %>
</body>

</html>