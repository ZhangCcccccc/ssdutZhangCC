<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>


<html>
<head>
<meta name="decorator" content="iframe"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式 -->	
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_LeftList.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_Searcher.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_SystemUI_Allpages.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">人员管理</a></li>
						<li class="end"><a href="javascript:void(0)">新建</a></li>
					</ul>
				</div>
			</div>



<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
			<div class="title">
				<div id="title">新建</div>
				
			</div>   	
    		<!-- 实验室列表 -->
    		<!-- <div class="content-box">   --> 		
            <form:form action="${pageContext.request.contextPath}/cConsumables/saveCConsumables" method="POST" modelAttribute="cconsumables">
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
				
				<tr>
				<form:hidden path="id"/>
				
				
			<td width="350px">易耗品名称：
				<form:input  id="name" path="name" required="true"></form:input> 	    				    				            
			</td> 
			
			<td width="350px">易耗品计量单位：
			<form:input  id="measurementUnit" path="measurementUnit" required="true"></form:input> 	    				    				            
			</td>
			
			    </tr>
			
			<tr>
			
	        <td>易耗品标准：<form:input  id="consumablesStandard" path="consumablesStandard" required="true"></form:input>  </td>
	       
			</tr>
				</tbody>
			</table>    
      
      <center><input class="btn" type="submit" value="确定">
            <button class="btn" onclick="window.history.go(-1)" href="javascript:void(0)">取消</button></center>
		</form:form>
    
<!-- </div> -->
</div>
</div>
</div>
</div>
</body>
</html>