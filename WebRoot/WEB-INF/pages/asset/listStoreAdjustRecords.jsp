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
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/asset/listAssetRecords?currpage=1";
  }
  </script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">药品溶液管理</a></li>
		<li class="end"><a href="javascript:void(0)">在用物资</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <%--<ul class="TabbedPanelsTabGroup">
  
			<li class="TabbedPanelsTab selected" tabindex="0">
			<a href="${pageContext.request.contextPath}/devicePurchase/listDeviceStatus?currpage=1&category=0">药品试剂</a>
			</li>
		
			<li class="TabbedPanelsTab"  tabindex="0">
			<a href="${pageContext.request.contextPath}/devicePurchase/listCDeviceSupplier?currpage=1&category=1">低值材料</a>
			</li>
		
	  </ul>--%>
  <div class="TabbedPanelsContentGroup">
  
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">药品库存调整列表</div>
	  <a class="btn btn-new" onclick="cancel()">返回</a>
	</div>
	<%--<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/asset/listAssetRecords?currpage=1" method="post" modelAttribute="asset">
			 <ul>
  				<li>药品名称： </li>
  				<li>
  					<form:select id="chName" path="chName" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listAssetsAndQuantities}" var="curr">
  							<form:option value="${curr.key.chName}">${curr.key.chName}[${curr.key.specifications}]</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="cancel()"/></li>
		</form:form>
	</div>--%>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>序号</th> 
	    <th>药品名称</th>
	    <th>cas号</th>
	    <th>货号</th> 
	    <th>实验室</th> 
	    <th>药品柜名称</th> 
	    <th>药品柜编号</th> 
	    <th>现有数量</th> 
	    <th>操作</th> 
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listAccessRecords}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(currpage-1)}</td> 
	    <td>${curr.asset.chName}[${curr.asset.specifications}]</td>  
	    <td>${curr.asset.cas}</td>
	    <td>${curr.assetCabinetWarehouseAccess.assetAppRecord.styleNumber}</td>  
	    <td>${curr.assetCabinetWarehouse.assetCabinet.labRoom.labRoomName}</td> 
	    <td>${curr.assetCabinetWarehouse.assetCabinet.cabinetName}</td> 
	    <td>${curr.assetCabinetWarehouse.warehouseName}</td>
	    <td>${curr.cabinetQuantity}</td>
	    <td>
	    	<a href="${pageContext.request.contextPath}/asset/newAdjustStore?id=${curr.id}&adjustId=${id}">调整</a> 
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
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
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
