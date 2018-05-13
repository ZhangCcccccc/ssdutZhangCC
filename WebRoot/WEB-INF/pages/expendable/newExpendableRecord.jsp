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

  </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>  
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">耗材管理</a></li>
			<li><a href="javascript:void(0)">耗材记录</a></li>
		</ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title">新建耗材记录</div>
	</div>
	<form:form action="${pageContext.request.contextPath}/expendable/saveExpendableRecord" method="POST" modelAttribute="expendable">
	<div class="new-classroom"> 
	<fieldset>
	    <label>耗材名称：</label>
	    <input id="expendableName" path="expendableName" name="expendableName"/>
	  </fieldset> 
	  <fieldset>
	    <label>耗材单价：</label>
	    <input id="unitPrice" path="unitPrice" name="unitPrice" placeholder="请输入数字，保留两位小数"/>
	  </fieldset> 
	  <fieldset>
	    <label>申购人：</label>
	    <form:select path="user.username"  class="chzn-select">
	    <form:option value="">请选择</form:option>
		    <c:forEach items="${users}" var="curr">
		    	<form:option value="${curr.key}">${curr.value}[${curr.key}]</form:option>
		    </c:forEach>
	    </form:select>
	  </fieldset> 
	  <fieldset>
	    <label>耗材类型：</label>
	    <input id="expendableType" path="expendableType" name="expendableType"/>
	  </fieldset>  
	  <fieldset>
	    <label>危化品标记：</label>
	    <input type="radio" name="ifDangerous" path="ifDangerous" value="1" checked="checked" />是
		<input type="radio" name="ifDangerous" path="ifDangerous" value="0" />否
	  </fieldset> 
	  <fieldset>
	    <label>危化品种类：：</label>
	    <input id="dangerousType" path="dangerousType" name="dangerousType"/>
	  </fieldset> 
	  <fieldset>
	    <label>品牌：</label>
	    <input id="brand" path="brand" name="brand"/>
	  </fieldset> 
	  <fieldset>
	    <label>规格：</label>
	    <input id="expendableSpecification" path="expendableSpecification" name="expendableSpecification"/>
	  </fieldset> 
	  <fieldset>
	    <label>包装单位：</label>
	    <input id="expendableUnit" path="expendableUnit" name="expendableUnit"/>
	  </fieldset> 
	  <fieldset>
	    <label>耗材位置：</label>
	    <input id="place" path="place" name="place"/>
	  </fieldset> 
	  <fieldset>
	    <label>耗材数量：</label>
	    <input id="quantity" path="quantity" name="quantity"/>
	  </fieldset>
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="确定">
		  <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(0)">
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
