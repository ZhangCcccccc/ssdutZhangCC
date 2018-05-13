<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
<meta content="none" name="decorator">
	<meta charset="utf-8">
<!--控制默认缩放大小、最小缩放大小、最大缩放大小。通过这个可以设置用户的最大和最小缩放程度。-->
<meta name="viewport" content="width=device-width,user-scalable=no"/>
<!--//表示手机模式设置为启用。-->
<meta name="format-detection" content="telephone=no"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/allpages.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/login.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/allpages.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/resiz.js"></script>
<title>微课手机登录</title>
</head>
<%-- <body id="loginarbd" >
	<div class="header">
	<div class="dl">
		<div class="logo">
			<div class="logo-img">
			<img src="images/images/logo.png">
			</div>
		</div>
	</div>
			
	<div class="loginbarp">
		<ul id="button-login">
		<li class="login"><a href="#">登陆</a></li>
		<li class="registered"><a href="#">注册</a></li>				
		</ul>
	</div>

	<div class="mainmenu">
		<ul>
			<li><a href="#">首页</a></li>
			<li><a href="#">关于WeiKe</a></li>
			<li><a href="#">关于我们</a></li>
		</ul>
	</div>
			
	<div class="loginbar">
		<ul id="button-login">
			<li class="login"><a href="#">登陆</a></li>
			<li class="registered"><a href="#">注册</a></li>		
		</ul>
	</div>
			
	</div>
	<div class="login_box">
	<div class="panel panel-default panel-page">
	<div class="panel-heading"><h2>登录</h2></div>

	<form id="login-form" class="form-vertical" method="post" action='${pageContext.request.contextPath}/j_spring_security_check'>
	<div class="form-group">
          <label class="control-label" for="login_username">帐号</label>
          <div class="controls">
            <input class="form-control" id="login_username" type="text" name="j_username"  required="required" >
            <div class="help-block">请输入Email地址 / 用户昵称</div>
          </div>
	</div>
	<div class="form-group">
          <label class="control-label" for="login_password">密码</label>
          <div class="controls">
            <input class="form-control" id="login_password" type="password" name="j_password" required="required" >
          <div class="help-block" style="display:none;"></div></div>
	</div>

	<div class="form-group">
		<div class="controls">
            <span class="checkbox mtm pull-right">
            <label> <input type="checkbox" name="_spring_security_remember_me" checked="checked"> 记住我 </label>
            </span>
            <button type="submit" class="btn btn-fat btn-primary btn-large">登录</button>
		</div>
	</div>
	</form>

	<!-- <div class="ptl">
    	<a href="#">找回密码</a>
    	<span class="text-muted mhs">|</span>
   		<span class="text-muted">还没有注册帐号？</span>
    	<a href="#">立即注册</a>
	</div> -->
	<div ondblclick="xxx();" class="tt">
		<img src="images/images/tt.png" style="width:90%">
	</div>
	</div>
    
    </div>
	<div class="login_footer">
	</div>
<script type="text/javascript">
function xxx(){
	alert("WeiKe,only Gvsun.");
}
</script>
</body> --%>
<body>
<form id="login-form" class="form-vertical" method="post" action='${pageContext.request.contextPath}/j_spring_security_check'>
	<div id="conteiner">
    	<div id="header">
        	<img src="${pageContext.request.contextPath}/images/mobile/logo_b.png" style="width:90%;"/>
        </div>
        <div id="title">
            	请登陆后使用本站功能
            </div>
    	<div id="logbox">
        	
            <!-- <div id="namer">
            	用户名
            </div> -->
            <input class="form-control" id="namerinput" type="text" name="j_username"  required="required"  placeholder="用户名">
           <!--  <div id="coder">
            	密码
            </div>  -->
            <input class="form-control" id="coderinput" type="password" name="j_password" required="required" placeholder="密码">          
            <div id="enterbtn"><!--onClick="jump('mylessons.html')"-->
            	<input type="submit" value="登录"/>
            </div>
           <!--  <div id="registbtn" >
            	<input type="button" value="注册"/>
            </div> -->
        </div>
    </div>
</form>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/allpages.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/resiz.js"></script>
