<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<%-- 
<!-- jQuery Configuration -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/facebox.js"></script>
 --%>
<!-- jQuery WYSIWYG Plugin -->
<title>Cloud CMS</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/jquery.wysiwyg.js"></script>
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/jquery.datePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/jquery.date.js"></script>
<decorator:head/>
</head>

<body>


  <div id="sidebar">
    <div id="sidebar-wrapper">
      <!-- Sidebar with logo and menu -->
      <h1 id="sidebar-title"><a href="javascript:void(0);">Cloud  CMS</a></h1>
      <!-- Logo (221px wide) -->
      <a href="javascript:void(0);"><img id="logo" src="${pageContext.request.contextPath}/images/systemBack/logo.png" alt="Simpla Admin logo" /></a>
      <!-- Sidebar Profile links -->
      <div id="profile-links"> Hello, <a href="javascript:void(0);" title="Edit your profile">${user.cname }</a><br />
        <br />
        <!-- <a href="javascript:void(0);" title="View the Site">View the Site</a> --> | <a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp"   target="_parent"   title="Sign Out">登出</a> </div>
      <ul id="main-nav">
        <!-- Accordion Menu -->
        <li> <a  href="${pageContext.request.contextPath}/admin/"   class="nav-top-item no-submenu">
          控制台 </a> </li>
        <li> <a  class="nav-top-item"> 网站 </a>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/newSite">添加网站</a></li>
            <li><a  href="${pageContext.request.contextPath}/admin/site">管理网站</a></li>
          </ul>
        </li>
        <li> <a  class="nav-top-item"> 栏目 </a>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/newChannel">添加栏目</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/channel">管理栏目</a></li>
          </ul>
        </li>
        <li> <a  class="nav-top-item"> 文章 </a>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/newArticle">添加文章</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/article?page=1">管理文章</a></li>
          </ul>
        </li>
        <li> <a  class="nav-top-item"> 资源 </a>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/resourcesList?type=0&page=1">管理图片</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/addNewImage">增加图片</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/resourcesList?type=1&page=1">管理视频</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/addNewVideo">增加视频</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/documentsList?page=1">查看附件</a></li> 
            <li><a href="${pageContext.request.contextPath}/admin/newDocument">添加附件</a></li>                     
          </ul>
        </li>
        <li> <a  class="nav-top-item"> 链接 </a>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/linksList">管理链接</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/linkNew">添加链接</a></li>
          </ul>
        </li>        
        <%-- <li> <a  class="nav-top-item"> 用户 </a>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/usersList">管理用户</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/userNew">添加用户</a></li>
          </ul>
        </li>
        <li> <a  class="nav-top-item"> 校友 </a>
          <ul>
            <li><a   href="${pageContext.request.contextPath}/admin/alumni?page=1">历届校友</a></li>
            <li><a   href="${pageContext.request.contextPath}/admin/newAlumni">添加校友</a></li>
          </ul>
        </li>
        <li> <a  class="nav-top-item"> 募集 </a>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/project?page=1">募集项目</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/newProject">添加项目</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/info?page=1">募集名录</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/newInfo">添加名录</a></li>
          </ul>
        </li> --%>
      </ul>
      <!-- End #main-nav -->
    </div>
  </div>

<decorator:body/>
</body>
<!-- Download From www.exet.tk-->
</html>
