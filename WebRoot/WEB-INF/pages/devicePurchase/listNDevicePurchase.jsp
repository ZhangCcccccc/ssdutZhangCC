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
    window.location.href="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchase?currpage=1&auditStatus=${auditStatus}";
  }
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  $(document).ready(function(){
      var s=${auditStatus};
      for(var i=0;i<=5;i++)
      {
      	if(i==s)
      	{
      		$("#s"+i).addClass("TabbedPanelsTab selected");
      	}
      	else
      	{
      		$("#s"+i).addClass("TabbedPanelsTab");
      	}
      }
	});
  </script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">设备申购管理</a></li>
		<li class="end"><a href="javascript:void(0)">设备申购</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" id="s5"><a href="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=1&auditStatus=5">全部</a></li>
		<li class="TabbedPanelsTab" id="s0"><a href="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=1&auditStatus=0">未提交</a></li>
		<%--<li class="TabbedPanelsTab" id="s1"><a href="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=1&auditStatus=1">已提交</a></li>--%>
		<li class="TabbedPanelsTab" id="s2"><a href="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=1&auditStatus=2">审核中</a></li>
		<li class="TabbedPanelsTab" id="s3"><a href="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=1&auditStatus=3">审核通过</a></li>
		<li class="TabbedPanelsTab" id="s4"><a href="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=1&auditStatus=4">审核拒绝</a></li>
</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">个人申购列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/devicePurchase/newDevicePurchase?">新建申购</a>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=1&auditStatus=${auditStatus}" method="post" modelAttribute="nDevicePurchase">
			 <ul>
  				<li>申购编号： </li>
  				<li>
  					<form:select id="purchaseNumber" path="purchaseNumber" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listPurchaseNumbers}" var="curr">
  							<form:option value="${curr.purchaseNumber}">${curr.purchaseNumber}</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>申购名称： </li>
  				<li>
  					<form:select id="useDirection" path="useDirection" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listNDevicePurchase}" var="curr">
  							<form:option value="${curr.useDirection}">${curr.useDirection}</form:option>
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
	    <th>申购编号</th>
	    <th>申购名称</th>
	    <th>申购金额</th>
	    <th>申购时间</th>
	    <th>申购人</th>
	    <th>当前状态</th>
	    <th>审核状态</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listNDevicePurchase}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(currpage-1)}</td>
	    <td>${curr.purchaseNumber}</td>
	    <td>${curr.useDirection}</td>
	    <td>${curr.purchaseCost}</td>
	    <td><fmt:formatDate value="${curr.createDate.time}" pattern="yyyy-MM-dd" /></td>
	    <td>${curr.user.cname}</td>
	     <td><c:if test="${curr.auditStatus eq 3}">${curr.CDeviceStatus.statusName}</c:if>
	     </td>
	    <c:if test="${curr.auditStatus eq 0}"><td>未提交</td></c:if>
	    <c:if test="${curr.auditStatus eq 1}"><td>未审核</td></c:if>
	    <c:if test="${curr.auditStatus eq 2}"><td>审核中</td></c:if>
	    <c:if test="${curr.auditStatus eq 3}"><td>审核通过</td></c:if>
	    <c:if test="${curr.auditStatus eq 4}"><td><font color="red">审核拒绝</font></td></c:if>
	    <td>
	      <a href="${pageContext.request.contextPath}/devicePurchase/getNDevicePurchase?id=${curr.id}">查看</a>
	     <c:if test="${curr.auditStatus eq 0}">
	      <a href="${pageContext.request.contextPath}/devicePurchase/editDevicePurchase?id=${curr.id}">编辑</a>
	      <a href="${pageContext.request.contextPath}/devicePurchase/deleteDevicePurchase?id=${curr.id}" onclick="return confirm('确定删除？');">删除</a>
	      </c:if>
	      <c:if test="${curr.auditStatus lt 1}">
	      <a href="${pageContext.request.contextPath}/devicePurchase/viewNDevicePurchase?id=${curr.id}">添加设备</a>
	      </c:if>
	      <c:if test="${curr.auditStatus eq 0}">
	      <a href="${pageContext.request.contextPath}/devicePurchase/submitDevicePurchase?id=${curr.id}">提交</a>
	     </c:if>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
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
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=1&auditStatus=${auditStatus}')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=${pageModel.previousPage}&auditStatus=${auditStatus}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=${pageModel.currpage}&auditStatus=${auditStatus}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=${j.index}&auditStatus=${auditStatus}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listNDevicePurchases?currpage=${pageModel.nextPage}&auditStatus=${auditStatus}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listNDevicePurchase?currpage=${pageModel.lastPage}&auditStatus=${auditStatus}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
