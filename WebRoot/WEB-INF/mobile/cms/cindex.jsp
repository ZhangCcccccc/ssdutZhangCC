<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%--<head>
<title>东华大学实验室管理系统1111</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
</head>--%>

<head>
 <meta name="decorator" content="none">
<meta charset="utf-8">
<title>教学平台</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_LeftList.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_Searcher.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_SystemUI_Allpages.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-new.css" />
</head>
<!--header-->

<body >
<c:if test="${!empty user}">
	<%
		String redirectURL = request.getContextPath() + "/tcoursesite/weike/index";
		response.sendRedirect(redirectURL); 
	%>
</c:if>
<div class="head">
西安电子科技大学
</div>
<!-- login form-->
<div class="sit_module">
     <div class="sit_title"><h3>用户登录</h3></div>
    <div class="module_content">
     <form id="subform" name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
  <table  class="sit_login_form">
    <tbody>
      <tr>
  <th><label for="signin_username">用户名</label></th>
  <td><input class="username" type="text" name="j_username"  value=""/></td>
</tr>
<tr>
  <th><label for="signin_password">密  码</label></th>
  <td><input class="pwd" type="password" name="j_password" value="" /></td>
</tr>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="2" align="center"><br/>
            <input class="btn-submit" type="submit" value="登录"/>

        </td>
      </tr>
    </tfoot>
  </table>
</form>
       <div class="clear"></div>
    </div>
</div>
<div class="test" style="color:white" align="center">西安电子科技大学实验室智能管理系统新版本已经开始试用。<br>由于此系统尚未与统一身份认证对接，系统的用户名和密码为各位老师自己的工号！</div>



<!--footer-->
<div class="iStyle_Message">
© 2017苏州庚商教育智能科技有限公司|@POWER BY GVSUN
</div>

<!--footer-->            
</body>
</html>