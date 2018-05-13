<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%-- <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/> --%>
<fmt:setBundle basename="bundles.lab-resources"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
	<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
    <head>
        <title>东华大学实验室管理系统</title> 
        <style type="text/css" media="screen">
			/* @import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css");
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css");
		    @import url("${pageContext.request.contextPath}/css/style.css"); */
		</style>
		<style>
		    .nametab{
		        font-size:16px;
		        font-family:microsoft yahei;
		        margin-top:4px;
		        font-weight:bold;
		        letter-spacing:1px;
		        background:#fafafa;
		    }
		</style>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
       <%--  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script> --%>
		<% if (((HttpServletRequest)pageContext.getRequest()).getServletPath().equals("/index.jsp")){ %>
		<%-- <link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet" type="text/css" />  --%>
		<% } %>     
		<% if (((HttpServletRequest)pageContext.getRequest()).getServletPath().equals("/pages/login.jsp")){ %>
		<%-- <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />  --%>
		<% } %>		
		<%-- <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" /> --%>
		<decorator:head />
		<!-- <script type="text/javascript">
		 function matchDate(startDate,endDate){
		 <c:forEach items="${openTimes}" var="current"  varStatus="i">
		 var start=<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" type="both" value="${current.startDate.time}"/>;
		 var end=<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" type="both" value="${current.endDate.time}"/>
		 </c:forEach>
		 return 1;
		 }
		</script> -->
    </head>
   <body ><!-- onload="init();" -->
  
	<div id="scheduler_here" class="dhx_cal_container" style='width:100%; height:100%;'>
		<div class="dhx_cal_navline">
			<div class="dhx_cal_prev_button">&nbsp;</div>
			<div class="dhx_cal_next_button">&nbsp;</div>
			<div class="dhx_cal_today_button"></div>
			<%--<div class="dhx_cal_date" style="margin-top:30px;"></div>
		--%>
		    <div class="dhx_cal_date"></div>
		<!-- 
			<div class="dhx_cal_tab" name="matrix_tab" style="right:280px;"></div> -->
	    <div class="dhx_cal_tab" name="day_tab" style="right:76px;"></div>
        <div class="dhx_cal_tab" name="week_tab" style="right:140px;"></div>
        <div class="dhx_cal_tab" name="month_tab" style="right:76px;"></div>
        <div class="dhx_cal_tab" name="year_tab" style="right:76px;"></div>
      <!--   <div class="dhx_cal_tab" name="matrix_tab" style="right:280px;"><a onclick="window.history.go(-1)">返回</a></div> -->
      	
        <div class="nametab" name="name_tab">设备:${schoolDeviceName} </div>
		</div>
		<div class="dhx_cal_header">
		</div>
		<div class="dhx_cal_data">
		</div>		
	</div>
</body>
</html>
