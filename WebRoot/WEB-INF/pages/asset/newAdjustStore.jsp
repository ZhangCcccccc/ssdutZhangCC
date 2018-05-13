<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 -->
  <script type="text/javascript"> 
//取消查询
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/asset/listStoreAdjustRecords?id=${adjustId}";
  }
  //跳转
  function targetUrl(url)
{
	location.href = url;
} 
  </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>  
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">药品溶液管理</a></li>
		    <li><a href="javascript:void(0)">在用物资</li>
		</ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title">药品库存调整操作
      </div>
	</div>
	<form:form action="${pageContext.request.contextPath}/asset/saveAdjustStore?id=${accessRecord.id}&adjustId=${adjustId}" method="POST" modelAttribute="assetAdjustRecord">
	<div class="new-classroom"> 
		<fieldset>
	    <label>调整方式:</label>
	   	 <form:select  class="chzn-select" id="type" name="type" path="type" required="true" > 
		<form:option value="1">添加</form:option>
		<form:option value="0">减少</form:option>
		</form:select>
	  	</fieldset>
	  	<fieldset> 
	    	<label>调整数量:</label>
	   	 <form:input id="quantity" name="quantity" path="quantity"/>
	   	 <c:if test = "${accessRecord.asset.specifications ne null}">${flag}</c:if>
	   	 <c:if test = "${accessRecord.asset.specifications eq null}">${accessRecord.asset.unit}</c:if>
	   	 </fieldset>
	  	<fieldset> 
	    	<label>负责人:</label>
	   	 <form:select id="chargePerson" class="chzn-select" name="chargePerson" path="user.username">
  			<form:option value="${currUser.username}">${currUser.cname}</form:option>
  				<c:forEach items="${users}" var="curr">
  					<form:option value="${curr.key}">${curr.value}</form:option>
  				</c:forEach>
  		</form:select>
	  	</fieldset>  
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="确定">
		  <input class="btn btn-return" type="button" value="返回" onclick="cancel()">
        </div>
	</div>
	</form:form>
		
	<!-- 下拉框的js -->
	<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		    var config = {
		      '.chzn-select': {search_contains : true},
		      '.chzn-select-deselect'  : {allow_single_deselect:true},
		      '.chzn-select-no-single' : {disable_search_threshold:10},
		      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
		      '.chzn-select-width'     : {width:"95%"}
		    }
		    for (var selector in config) {
		      $(selector).chosen(config[selector]);
		    }
		</script>
	<!-- 下拉框的js -->
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
