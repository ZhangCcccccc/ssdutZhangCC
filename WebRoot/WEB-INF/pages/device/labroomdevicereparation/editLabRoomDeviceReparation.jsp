
<%@page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.labroomdevicereparation-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/> 
 
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->

</head>
<body>
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">易耗品赔偿流水账-修改</a></li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
		<form:form action="${pageContext.request.contextPath}/device/saveLabRoomDeviceReparation" method="POST" modelAttribute="labroomdevicereparation">
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
				
				<tr>
				<form:hidden path="id"/>
			<td class="label" valign="top"> 易耗品名称: </td>
			<td><form:select id="table1_ID"   path="CConsumables.id" required="true">    
    		<form:options cssStyle="width:200px;"  items="${ID1}" itemLabel="name" itemValue="id"/>
    		</form:select>
    		</td>
			
			
			<td class="label" valign="top"> 实验室: </td>
			<td><form:select id="table1_ID"   path="labRoom.id" required="true">    
    		<form:options cssStyle="width:200px;"  items="${ID2}" itemLabel="labRoomName" itemValue="id"/>
    		</form:select>
	        </td>
	        
			</tr>
			
			<tr>
			<td class="label" valign="top"> 赔偿数量: </td>
			<td><form:input  id="nation_id"  path="amount" required="true"/> </td>
	        </td>
	        
			<td class="label" valign="top">赔偿单价: </td>
			<td><form:input  id="nation_id"  path="price" required="true"/> </td>
	        
			</tr>
			
			<tr>
			<td class="label" valign="top"> 记录人: </td>
			<td>
            <form:select class="chzn-select"  path="userByCreateUser.username" id="userByCreateUser.username" required="true" cssStyle="width:200px" >
	  <c:forEach items="${ID3}" var="current1"  varStatus="i">	
	    <form:option value="${current1.username}" >工号：${current1.username} 姓名：${current1.cname}</form:option>
	  </c:forEach>
   </form:select>
	        </td>
	        
			<td class="label" valign="top"> 赔偿人: </td>
			<td>
			<form:select class="chzn-select"  path="userByReparationUser.username" id="userByCreateUser.username" required="true" cssStyle="width:200px" >
	  <c:forEach items="${ID3}" var="current1"  varStatus="i">	
	    <form:option value="${current1.username}" >工号：${current1.username} 姓名：${current1.cname}</form:option>
	  </c:forEach>
   </form:select>
			
	        </td>
	        
			</tr>
			
			<tr>
			<td class="label" valign="top">导师/辅导员: </td>
			<td>
			<form:select class="chzn-select"  path="userByTeacher.username" id="userByCreateUser.username" required="true" cssStyle="width:200px" >
	  <c:forEach items="${ID3}" var="current1"  varStatus="i">	
	    <form:option value="${current1.username}" >工号：${current1.username} 姓名：${current1.cname}</form:option>
	  </c:forEach>
   </form:select>
			
	        </td>
			
			</tr>
					
				</tbody>
			</table>
			<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="保存"/></span>
			<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'save', event:'onclick'}));</script>
			
			<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
</script>
<!-- 下拉框的js -->
			
		</form:form>
		<div class="clear">&nbsp;</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>