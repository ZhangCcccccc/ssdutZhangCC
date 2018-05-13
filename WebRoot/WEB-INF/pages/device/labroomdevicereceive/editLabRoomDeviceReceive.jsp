<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="bundles.projecttermination-resources"/>

<html >  

<head> 
<meta name="decorator" content="iframe"/> 
<title><fmt:message key="html.title"/></title>
<!-- <meta name="decorator" content="iframe"/> -->

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" />
  
  <!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
  
</head>



<body>
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">易耗品流水账——修改</a></li>
	</ul>
</div>
</div>
<!-- 结项申报列表 -->
<div id="content" >
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
	<form:form action="${pageContext.request.contextPath}/device/saveLabRoomDeviceReceive" method="POST" modelAttribute="labroomdevicereceive">

      <table id="viewTable" cellspacing="0" cellpadding="0">
      	
      	
      							<!-- <td> -->
							<c:choose>
								<c:when test='${newFlag}' >
							<form:input id="table1_num" path="id" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "table1_num",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="table1.id.help"/>", constraints : {places:0}}})); </script>
								</c:when>
								<c:otherwise>
							<%-- ${labroomdevicereceive.id} --%>
						&nbsp;
									<form:hidden id="table1_num" path="id"/>
								</c:otherwise>
							</c:choose>
					<!-- 	</td> -->

      	
      	
			<tr><td class="label" >易耗品名称 </td>
			<td>
			
			<form:select id="table1_ID"   path="CConsumables.id" >    
    		<form:options cssStyle="width:200px;"  items="${ID1}" itemLabel="name" itemValue="id"/>
    		</form:select>
            </td>
            <td class="label" >实验室</td>
            <td><form:select id="table1_ID"   path="labRoom.id" >    
    		<form:options cssStyle="width:200px;"  items="${ID2}" itemLabel="labRoomName" itemValue="id"/>
    		</form:select>
    		</td></tr>
    		 <tr><td class="label" >项目名称</td>
            <td><form:select id="table1_ID"   path="operationItem.id" >    
    		<form:options cssStyle="width:200px;"  items="${ID3}" itemLabel="itemName" itemValue="id"/>
    		</form:select>
    		</td>
			<td>申领数量
				<td><form:input  id="nation_id"  path="amount" required="true"/> </td>
			</td></tr>	
			<tr><td class="label" >记录人</td>
            <td>
            <form:select class="chzn-select"  path="userByCreateUser.username" id="userByCreateUser.username" cssStyle="width:200px" >
	  <c:forEach items="${ID4}" var="current1"  varStatus="i">	
	    <form:option value="${current1.username}" >工号：${current1.username} 姓名：${current1.cname}</form:option>
	  </c:forEach>
   </form:select>
   
    		</td>
    		<td class="label" >申领人</td>
            <td>
            <form:select class="chzn-select"  path="userByReceiveUser.username" id="userByCreateUser.username" cssStyle="width:200px" >
	  <c:forEach items="${ID4}" var="current1"  varStatus="i">	
	    <form:option value="${current1.username}" >工号：${current1.username} 姓名：${current1.cname}</form:option>
	  </c:forEach>
   </form:select>
    		</td>
    		<td class="label" >管理人</td>
            <td>
            <form:select class="chzn-select"  path="userByManagerUser.username" id="userByCreateUser.username" cssStyle="width:200px" >
	  <c:forEach items="${ID4}" var="current1"  varStatus="i">	
	    <form:option value="${current1.username}" >工号：${current1.username} 姓名：${current1.cname}</form:option>
	  </c:forEach>
   </form:select>
    		</td>
    		</tr>
			</tbody></table>
			
			
<table id="viewTable" cellspacing="0" cellpadding="0">
<td>描述<td><form:input id="nation_id" path="description" style="width: 915px;height: 50px;padding: 20px"></form:input></td></td> 
</table>
<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="保存"/></span>

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
</div>
</div>
</div>
</div>
</div>
</div>
</body>			

<!-------------列表结束----------->
</html>