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
    window.location.href="${pageContext.request.contextPath}/expendable/listExpendableInUse?currpage=1";
  }
  </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>  
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">耗材管理</a></li>
			<li><a href="javascript:void(0)">盘库</a></li>
		</ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title">盘库</div>
	</div>
	<form:form action="${pageContext.request.contextPath}/expendable/saveExpendablestock?id=${id}" method="POST" modelAttribute="expendableStockRecord">
	<div class="new-classroom"> 
	<fieldset>
	    <label>盘库数量：</label>
	    <input id="stockNumber" path="stockNumber" name="stockNumber"/>
	  </fieldset> 
	  <fieldset>
	    <label>盘库说明：</label>
	    <input id="remark" path="remark" name="remark"/>
	  </fieldset> 
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="确定">
          <input class="btn btn-return" type="button" value="返回" onclick="cancel()"/></li>
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
