<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>  
<head> 
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<meta name="decorator" content="iframe"/>
<title><fmt:message key="html.title"/></title>
<meta name="decorator" content="iframe"/>
<script type="text/javascript">
function check(){
 alert("保存成功");
}


</script>
<body>
<form:form action="${pageContext.request.contextPath}/device/saveCStaticValue" method="POST" modelAttribute="cStaticValue" onsubmit="return check()">  
<!-- <div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">设备管理</a></li>
			<li class="end"><a href="javascript:void(0)">设备报修</a></li>
		</ul>
	</div>
</div> -->
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		<div class="content-box">
		<div class="title">设备预约参数设置</div>
		<fieldset class="introduce-box">
			<legend>设备预约审核可用时间设置</legend>
				<table id="viewTable" cellpadding="0" cellspacing="1" class="tablesorter">
				<td><form:input path="staticValue" id="staticValue"  type="text"/></td>
	
				</table>
				<table>
				<tr>
				<td style="width:50px;">说明： </td>
				<td>请严格按照24小时制的 小时:分钟:秒格式进行设置</td>
				</tr>
				</table>
			</fieldset>
	<div>
	<td class="label" valign="top"></td>
<td>
</td>

	</div>
<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="保存"/></span>
	</form:form>

	<!--下拉列表开始-->
  <script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chzn-select'           : {},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
   <!--下拉列表结束-->
	
</body>
</html>