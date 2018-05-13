<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    window.location.href="${pageContext.request.contextPath}/expendable/listExpendableRecords?currpage=1&flag=0";
  }
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  </script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">耗材管理</a></li>
		<li class="end"><a href="javascript:void(0)">耗材申购</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">申购列表</div>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/expendable/listExpendableRecords?currpage=1&flag=0" method="post" modelAttribute="expendable">
			 <ul>
  				<li>订单编号： </li>
  				<li>
  					<form:select id="orderNumber" path="orderNumber" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listExpendables}" var="curr">
  							<form:option value="${curr.orderNumber}">${curr.orderNumber}</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>申购人： </li>
  				<li>
  					<form:select id="username" path="user.username" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${users}" var="curr">
  							<form:option value="${curr.key}">${curr.value}[${curr.key}]</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="cancel()"/></li>
  				</ul>
			 
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>序号</th>
	    <th>订单编号</th>
	    <th>类型</th>
	    <th>经销商名称</th>
	    <th>申购时间</th>
	    <th>总价</th>
	    <th>申购人</th>
	    <th>经费帐号</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listExpendableRecords}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(currpage-1)}</td>
	    <td>${curr.orderNumber}</td>
	    <td>${curr.expendableType}</td>
	    <td>${curr.supplier}</td>
	    <td><fmt:formatDate value="${curr.purchaseDate.time}" pattern="yyyy-MM-dd" /></td>
	    <td>${curr.arriveTotalPrice}</td>
	    <td>${curr.user.cname}</td>
	    <td>${curr.fundAccount}</td>
	    <td>
	      <a href="${pageContext.request.contextPath}/expendable/showExpendableRecordsbyOrderNumber?orderNumber=${curr.orderNumber}">查看订单详情</a>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableRecords?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableRecords?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/expendable/listExpendableRecords?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/expendable/listExpendableRecords?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableRecords?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableRecords?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
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
