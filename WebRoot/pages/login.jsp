<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!--head-->
<head>
<meta charset="utf-8">
<title>实验室智能管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_LeftList.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_Searcher.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_SystemUI_Allpages.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css" />
</head>
<!--head-->
<!--header-->
<div class="head">
西安电子科技大学实验室管理系统
</div>
<!--header-->
<!-- login form-->
<div class="sit_module">
    <div class="sit_title"><h3>用户登录</h3></div>
    <div class="module_content">
      
     <form id="subform" name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'> 
  <table  class="sit_login_form">
  <tbody>
  <tr>
      <th>
         <font color="#FF0000">用户名或密码错误。请重新认证。</font>
         <a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp" target="_parent">退出 </a> 
      </th>
  </tr>
  </tbody>
  </table>
</form>
       <div class="clear"></div>
    </div>
</div>
<!--footer-->
<!--footer-->
<div class="iStyle_Message">
© 2017苏州庚商教育智能科技有限公司|@POWER BY GVSUN
</div>
<!--footer-->


<%-- <%
   	session.invalidate();
	String redirectURL = "http://my.gench.edu.cn/logout.portal";
	
	response.sendRedirect(redirectURL);
%> --%>

</body>
</html>