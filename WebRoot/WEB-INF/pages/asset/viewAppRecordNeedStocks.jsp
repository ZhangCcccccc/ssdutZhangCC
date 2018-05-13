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
    window.location.href="${pageContext.request.contextPath}/asset/listAssetStocks?currpage=1";
  }
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  $(document).ready(function(){  
	}); 
  </script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">药品溶液管理</a></li>
		<li class="end"><a href="javascript:void(0)">药品入库</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <%--<ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" id="s5"><a href="${pageContext.request.contextPath}/asset/listAccessStocks?currpage=1">未入库</a></li>
		<li class="TabbedPanelsTab" id="s0"><a href="${pageContext.request.contextPath}/asset/listAccessStocks?currpage=1">已入库</a></li>  
		<li class="TabbedPanelsTab" id="s2"><a href="${pageContext.request.contextPath}/asset/listAccessStocks?currpage=1">全部</a></li> 
</ul>--%>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
   <div>
	<fieldset class="introduce-box">
	<legend>申购单基本信息</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<th>申请编号：</th><td>${assetApp.appNo}</td>
				<th>申购人：</th><td>${assetApp.user.cname}</td>
				<th>物资种类：</th><td>${num}</td>
				<th>申购总价：</th><td>${totalPrice}</td>
				<th>实验大纲：</th><td>
	    	<c:if test = "${assetApp.type == 0}"><span class="title_focus">公用</span></c:if>
	    	<c:if test = "${assetApp.type != 0}"><span class="title_focus">${assetApp.operationItem.operationOutline.labOutlineName}</span></c:if>
	    </td>
				<th>实验项目：</th><td>
	    	<c:if test = "${assetApp.type == 0}"><span class="title_focus">--</span></c:if>
	    	<c:if test = "${assetApp.type != 0}"><span class="title_focus">${assetApp.operationItem.lpName}</span></c:if>
	    </td>
			</tr>
		</table>
	</fieldset>
  </div>
  <div class="content-box">
 
    <div class="title">
	  <div id="title">药品申购记录列表</div>
	</div>
	
	<%--<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/asset/listAccessStocks?currpage=1" method="post" modelAttribute="assetApp">
			 <ul>
  				<li>申购编号： </li>
  				<li>
  					<form:select id="appNo" path="appNo" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listAssetApps}" var="curr">
  							<form:option value="${curr.appNo}">${curr.appNo}</form:option>
  						</c:forEach>
  					</form:select>
  				</li>  
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="cancel()"/>
			      </li>
  				</ul>
			 
		 </form:form>
	</div>--%>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>药品名称</th>
	    <th>申购数量</th>
	    <th>状态</th>
	    <th>操作</th> 
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${assetAppRecords}" var="curr" varStatus="i">
	  <tr>
	    <td>${curr.asset.chName}[${curr.asset.specifications}]</td>
	    <td>${curr.appQuantity}</td> 
	    <td>
	     <c:if test ="${curr.result eq 0 }">审核拒绝</c:if>
	     <c:if test="${curr.asset.category  ne 1}">
	     <c:if test ="${curr.result eq 1 && curr.stockStatus eq 1}">已入库</c:if>
	     <c:if test ="${curr.result eq 1 && curr.stockStatus eq 2}">入库中</c:if>
	     <c:if test ="${curr.result eq 1 && curr.stockStatus eq 0}">未入库</c:if>
	     </c:if>
	    </td> 
	    <td> 
	    <c:if test="${curr.asset.category  ne 1}">
	    <c:if test ="${curr.result eq 1 && curr.stockStatus eq 1}"><a href="${pageContext.request.contextPath}/asset/viewLocationMessages?id=${(accesss.get(i.count-1)).id}&appId=${id}">查看</a> </c:if>
	    <c:if test ="${curr.result eq 1 && curr.stockStatus eq 0}"><a href="${pageContext.request.contextPath}/asset/newAccessStocks?id=${curr.id}">入库</a> </c:if>
	    <c:if test ="${curr.result eq 1 && curr.stockStatus eq 2}"><a href="${pageContext.request.contextPath}/asset/setLocationMessages?id=${(accesss.get(i.count-1)).id}&isClickCompleteStock=0&appId=${id}">继续入库</a> </c:if>    
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
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
