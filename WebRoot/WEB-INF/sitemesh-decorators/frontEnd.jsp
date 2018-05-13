<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="decorator" content="none">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>西安电子科技大学</title>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/songProject/style.css" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/songProject/icon_s.png"/>
<style type="text/css" media="screen">
	@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css");
	@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css");
	@import url("${pageContext.request.contextPath}/css/style.css");
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/spring/Spring.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/browse.js"></script>
<script type="text/javascript">

</script>
<decorator:head></decorator:head>
</head>
<!-- logo开始  -->
<div id="top_suda"></div>
<!-- logo结束  -->

<!-- 菜单栏开始  -->
<div id="menu_suda">
   <ul>
      <li><a href="${pageContext.request.contextPath}/cms/index">首页</a></li>
      <li><a href="${pageContext.request.contextPath}/cms/labRoom/labRoomManagement?currpage=1">实验室管理</a></li>
      <li><a href="${pageContext.request.contextPath}/cms/device/deviceManagement?currpage=1">设备管理</a></li>
      <li><a href="${pageContext.request.contextPath}/cms/tcoursesite/listTCourseSite?currpage=1">课程管理</a></li>
      <div class="menu_suda_right">
      	 <c:if test="${!empty loginUser }">
      		您好，${loginUser.cname }
      		<a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp" target="_parent">退出 </a>
      	 </c:if>
      	 <c:if test="${empty loginUser }">
      		您好，请登录
      	 </c:if>
      </div>
     </ul>
</div>
<!-- 菜单栏结束  -->


<decorator:body></decorator:body>

<!-- 页脚开始  -->

<div id="footer_suda">
    <div class="footer_font_suda">
      <p>Copyright &copy;&nbsp;西安电子科技大学&nbsp;2016,&nbsp;All rights reserved</p>
      <p>南校区地址：陕西省西安市西沣路兴隆段266号&nbsp;邮编：710126&nbsp;北校区地址：陕西省西安市太白南路2号&nbsp;邮编：710071&nbsp;电话：029-88201000</p>
    </div>
</div>

<!-- 页脚结束  -->


</body>
</html>
