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
<body>
<form:form action="${pageContext.request.contextPath}/device/saveNewDeviceRepair" method="POST" modelAttribute="labRoomDeviceRepair">  
<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">设备管理</a></li>
			<li class="end"><a href="javascript:void(0)">设备报修</a></li>
		</ul>
	</div>
</div>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		<div class="content-box">
		<div class="title">设备保修</div>
		<fieldset class="introduce-box">
			<legend>硬件问题<input type="hidden" value="" id="xsd"></legend>
				<table id="viewTable" cellpadding="0" cellspacing="1" class="tablesorter">
				<td><input name="hardwareFailure" type="checkbox" value="开机故障">开机故障</td>
				<td><input name="hardwareFailure" type="checkbox" value="显示故障">显示故障</td>
				<td><input name="hardwareFailure" type="checkbox" value="网络故障">网络故障</td>
				<td><input name="hardwareFailure" type="checkbox" value="其他故障">其他故障</td>
				<style>
					input{height:auto;
						position:relative;
						top:3px;}
				</style>
				</table>
				<table>
				<tr>
				<td style="width:50px;">说明 </td>
				<td><form:textarea id="hardwareFailure" path="hardwareFailure"  /></td>
				</tr>
				</table>
			</fieldset>

	<fieldset class="introduce-box">
	    <legend>软件问题<input type="hidden" value="" id="xsd"></legend>		
		<table id="listTable"  cellpadding="0" cellspacing="0" class="tablesorter" >
		
		<tr>
		<td>
			<%-- 软件故障选择:<form:select path="CLabRoomDeviceChoice.id" items="${map}">
			</form:select> --%>
			<%-- 软件故障选择:<form:select path="CDictionaryByFailureChoice.id" items="${map}">
			</form:select> --%>
			软件故障选择:<form:select path="CDictionaryByFailureChoice.id" >
							<c:forEach items="${choices}" var="cDictionary">
								<form:option value="${cDictionary.id }">${cDictionary.CName }</form:option>
							</c:forEach>
						</form:select>
		</td>
		<td>
			<%-- 分区故障选择:<form:select path="CLabRoomDevicePartition.id" items="${map1}" >
		    </form:select> --%> 
			<%-- 分区故障选择:<form:select path="CDictionaryByPartitionId.id" items="${map1}" >
		    </form:select> --%> 
			分区故障选择:<form:select path="CDictionaryByPartitionId.id">
							<c:forEach items="${partitions}" var="cDictionary">
								<form:option value="${cDictionary.id }">${cDictionary.CName }</form:option>
							</c:forEach>
		   				</form:select>
		</td>
		</tr>
		</table>
		<table>
		
		<tr>
		<td style="width:50px;">说明 </td>
		<td><form:textarea id="softwareFailure"  path="softwareFailure" /> 
		
		<form:hidden path="labRoomDevice.id" value="${td}" />
		
		</td></tr>
		</table>
	
	</fieldset>


	<div>
	<td class="label" valign="top"></td>
<td><form:hidden path="user.username" value="${username}" />
	<%-- <form:hidden path="CLabRoomDeviceRepairStatus.id" value="1"/> --%>
	<form:hidden path="CDictionaryByStatusId.id" value="655"/><!-- 对应c_dictionary表中c_category为“c_lab_room_device_repair_status”，c_number为1的记录 -->
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