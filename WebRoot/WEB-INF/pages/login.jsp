<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta name="decorator" content="none" />
<title>Cloud CMS </title>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/systemBack/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/systemBack/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/systemBack/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/facebox.js"></script> --%>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/jquery.wysiwyg.js"></script>
</head>
<% 
   //前端登录标记
   session.setAttribute("LOGINTYPE","admin");  
%>
<body id="login">
<div id="login-wrapper" class="png_bg">
  <div id="login-top">
    <h1>Cloud CMS</h1>
    <!-- Logo (221px width) -->
    <a href="javascript:void(0);"><img id="logo" src="${pageContext.request.contextPath}/images/systemBack/logo.png" alt="Simpla Admin logo" /></a> </div>
  <!-- End #logn-top -->
  <div id="login-content">
	<form action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
      <p>
        <label>Username</label>
			<input    class="text-input"     name="j_username" placeholder="请输入用户名"></input>
      </p>
      <div class="clear"></div>
      <p>
        <label>Password</label>
					<input   class="text-input"   name="j_password" placeholder="请输入密码" type="password"></input>
      </p>
      <div class="clear"></div>
<!--       <p id="remember-password">
        <input type="checkbox" />
        Remember me </p> -->
      <div class="clear"></div>
      <p>
        <input class="button" type="submit" value="Sign In" />
      </p>
    </form>
  </div>
  <!-- End #login-content -->
</div>
<!-- End #login-wrapper -->
</body>
</html>
