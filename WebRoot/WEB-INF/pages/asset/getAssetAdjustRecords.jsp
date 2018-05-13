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
    window.location.href="${pageContext.request.contextPath}/asset/getAssetAdjustRecords?currpage=1&id="+${id} ;
  }
  //跳转
  function targetUrl(url)
{
	location.href = url;
}
  function importAsset(){
	  	$('#importAsset').window({left:"50px",top:"50px"});
	  	$("#importAsset").window('open');
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
	  <div id="title">药品库存调整记录</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/listAssetRecords?currpage=1">返回</a> 
	</div>
	<%--<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/asset/getAssetAdjustRecords?id=${id}&currpage=1" method="post" modelAttribute="assetAdjustRecord">
			 <ul>
  				<li>药品名称： </li>
  				<li>
  					<form:select id="id" path="asset.id" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listAssetAdjustRecords}" var="curr">
  							<form:option value="${curr.asset.id}">${curr.asset.chName}[${curr.asset.specifications}]</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>类型： </li>
  				<li>
  					<form:select id="type" path="type" class="chzn-select"> 
						<form:option value="1">增加</form:option> 
						<form:option value="0">增加</form:option>
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
	    <th>调整时间</th>
	    <th>调整类型</th>  
	    <th>调整数量</th> 
	    <th>单位</th>
	    <th>存放位置</th>  
	    <th>药品柜名称</th>
	    <th>药品柜编号</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listAssetAdjustRecords}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(currpage-1)}</td> 
	    <td>${curr.asset.chName}[${curr.asset.specifications}]</td>   
	    <td><fmt:formatDate value="${curr.date.time}" pattern="yyyy-MM-dd" /></td> 
	    <td> 
	    	<c:if test="${curr.type eq 1}">增加</c:if>
	    	<c:if test="${curr.type eq 0}">减少</c:if>
	    </td> 
	    <td>${curr.quantity}</td>
	    <td>${curr.unit}</td>
	    <c:if test = "${curr.assetCabinetWarehouseAccessRecord.assetCabinetWarehouse ne null}">
	    <td>${curr.assetCabinetWarehouseAccessRecord.assetCabinetWarehouse.assetCabinet.labRoom.labRoomName}</td> 
	    <td>${curr.assetCabinetWarehouseAccessRecord.assetCabinetWarehouse.assetCabinet.cabinetName}</td> 
	    <td>${curr.assetCabinetWarehouseAccessRecord.assetCabinetWarehouse.warehouseName}</td></c:if>
	     <c:if test = "${curr.assetCabinetWarehouseAccessRecord.assetCabinetWarehouse eq null}"><td>${curr.assetCabinetWarehouseAccessRecord.position}</td>
	     <td>--</td>
	     <td>--</td>
	     </c:if>  
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
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/asset/getAssetAdjustRecords?currpage=1&id=${id}')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/asset/getAssetAdjustRecords?currpage=${pageModel.previousPage}&id=${id}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}//asset/getAssetAdjustRecords?currpage=${pageModel.currpage}&id=${id}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/asset/getAssetAdjustRecords?currpage=${j.index}&id=${id}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/asset/getAssetAdjustRecords?currpage=${pageModel.nextPage}&id=${id}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/asset/getAssetAdjustRecords?currpage=${pageModel.lastPage}&id=${id}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
