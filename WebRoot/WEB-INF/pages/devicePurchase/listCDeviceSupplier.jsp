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
    window.location.href="${pageContext.request.contextPath}/devicePurchase/listCDeviceSupplier?currpage=1";
  }
  //跳转
  function targetUrl(url)
{
	location.href = url;
}
  
  </script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">设备申购管理</a></li>
		<li class="end"><a href="javascript:void(0)">设备字典</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
  
			<li class="TabbedPanelsTab " tabindex="0">
			<a href="${pageContext.request.contextPath}/devicePurchase/listDeviceStatus?currpage=1">设备状态字典</a>
			</li>
		
			<li class="TabbedPanelsTab selected"  tabindex="0">
			<a href="${pageContext.request.contextPath}/devicePurchase/listCDeviceSupplier?currpage=1">设备供应商</a>
			</li>
		
	  </ul>
  <div class="TabbedPanelsContentGroup">
  
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">设备供应商列表</div>
	  <sec:authorize ifAnyGranted="ROLE_TEACHER" >
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/devicePurchase/newCDeviceSupplier?">新建设备供应商</a>
	  </sec:authorize>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/devicePurchase/listCDeviceSupplier?currpage=1" method="post" modelAttribute="cDeviceSupplier">
			 <ul>
  				<li>供应商姓名： </li>
  				<li>
  					<form:select id="devicName" path="devicName" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listCDeviceSuppliers}" var="curr">
  							<form:option value="${curr.devicName}">${curr.devicName}</form:option>
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
	    <th>供应商姓名</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listCDeviceSuppliers}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(currpage-1)}</td> 
	    <td>${curr.devicName}</td>  
	     <td>
	      <a href="${pageContext.request.contextPath}/devicePurchase/editCDeviceSupplier?id=${curr.id}">修改</a> 
	      <a href="${pageContext.request.contextPath}/devicePurchase/deleteCDeviceSupplier?id=${curr.id}" onclick="return confirm('确定删除？');">删除</a>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listCDeviceSupplier?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listCDeviceSupplier?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/devicePurchase/listCDeviceSupplier?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/devicePurchase/listCDeviceSupplier?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listCDeviceSupplier?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listCDeviceSupplier?currpage=${pageModel.lastPage}')" target="_self">末页</a>
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
