<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

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
    window.location.href="${pageContext.request.contextPath}/operation/listOpenTimes";
  }
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  
</script>	
  
  
  <style>
    .t_style ul li{
      float:left;
    }
  </style>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">药品溶液管理</a></li>
		<li class="end"><a href="javascript:void(0)">电源控制器时间列表</a></li>
	  </ul>
	</div>
  </div>
  
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">电源控制器时间列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/newOpenTime">新增</a>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/asset/listOpenTimes" method="post" modelAttribute="iotSharePowerOpentime">
			 <ul>
  				<li>学期</li>
  				<li>
  				  <form:select path="schoolTerm.id" id="term_id">
  				    <form:option value="">请选择</form:option>
  				    <form:options items="${schoolTerms}" itemLabel="termName" itemValue="id"/>
  				  </form:select>
  				</li> 
  			</ul>
  			<ul>	
  				<li><input type="button" value="取消" onclick="cancel();"/><input type="submit" value="查询"/> &nbsp;</li>  
  			</ul>
		</form:form>
	</div>
	 
	<table class="tb" id="my_show">
	  <thead>
	  <tr> 
	    <th>序号</th>
	    <th>学期</th>
	    <th>周几</th>
	    <th>开箱时间</th>  
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listOpenTimes}" var="curr" varStatus="i">
	  <tr> 
	    <td>${i.count+pageSize*(currpage-1)}</td> 
	    <td>${curr.schoolTerm.termName}</td>
	    <td>${curr.schoolWeekday.weekdayName}</td> 
	    <td><fmt:formatDate value="${curr.startTime.time}" pattern="HH:mm:ss" />-
	  <fmt:formatDate value="${curr.endTime.time}" pattern="HH:mm:ss" /></td>
	      <td><a href="${pageContext.request.contextPath}/asset/editOpenTime?id=${curr.id}">编辑</a>
	      <a href="${pageContext.request.contextPath}/asset/deleteOpenTime?id=${curr.id}" onclick="return confirm('确定删除？');">删除</a>
	    </td>
	    <!-- isMine=3   表示从导入页面进入的查看 -->
	  </tr>
	  </c:forEach><%--
	  <tr>
	    <td colspan="100" style="text-align:right;"><input type="button" value="导入整个学期" onclick="submitTermForm();"/></td>
	  </tr>
	  --%></tbody>
	</table> 
    
  </div>
  </div>
  </div>
  </div>
  
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
</body>
</html>
