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
    window.location.href="${pageContext.request.contextPath}/asset/listAssetRecords?currpage=1";
  }
  //跳转
  function targetUrl(url)
{
	 document.queryForm.action = url;
	document.queryForm.submit();
}
  
  function listOperationItems(){
	    var operationOutlineId = document.getElementById("operationOutline").value; 
	    if(operationOutlineId == '0'){
	    }
	    else{
	        $.ajax({
		        url:"${pageContext.request.contextPath}/asset/findoperationItemsByoperationOutlineId?operationOutlineId="+operationOutlineId,
		        type: 'POST',    
		        async: false,  
		        cache: false,  
		        contentType: false,  
		        processData: false, 
		        success:function(data){
		            $("#operationItem").html(data.operationItems);
		            $("#operationItem").trigger("liszt:updated");
		        }
		    });
	    }
	}
  </script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">药品溶液管理</a></li>
		<li class="end"><a href="javascript:void(0)">物资记录</a></li>
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
	  <div id="title">物资记录列表</div>
	</div>
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/asset/listAssetRecords?currpage=1" method="post" modelAttribute="assetReceive">
			 <ul>
  				<li>药品名称： </li>
  				<li style="width:270px;">
  					<form:select id="id" path="id" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listAssets}" var="curr">
  							<form:option value="${curr.id}">${curr.chName}[${curr.specifications}]</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>药品项目类型： </li>
  				<li style="width:150px;">
  					<form:select class="chzn-select" path="type" id="operationOutline" name="operationOutline" onChange="listOperationItems()">
	     				<form:option value="">请选择</form:option>
	     				<form:option value="0">公用</form:option>
  						<c:forEach items="${operationOutlines}" var="curr">
  							<form:option value="${curr.id}">${curr.labOutlineName}</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>实验项目名称： </li>
  				<li style="width:290px;">
  					<form:select class="chzn-select" path="operationItem.id" id="operationItem" name="operationItem">
	     		<form:option value="">请选择</form:option>
	     		<c:forEach items="${operationItemList}" var="curr">
  							<form:option value="${curr.id}">${curr.lpName}</form:option>
  						</c:forEach>
  		</form:select>
  				</li>
  				<li>
			      <input type="button" value="取消" onclick="cancel()"/><input type="submit" value="查询"/></li>
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>序号</th> 
	    <th>药品名称</th>
	    <th>实验大纲</th>
		<th>实验项目</th>
	    <th>现有数量</th> 
	    <th>是否达到警戒线</th> 
	    <sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
	    <th>操作</th> 
	    </sec:authorize>
	    <th>查看记录</th> 
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${accesss}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(currpage-1)}</td> 
	    <td>${curr.asset.chName}[${curr.asset.specifications}]</td>  
	    <td>
	    	<c:if test = "${curr.type == 0}">公用</c:if>
	    	<c:if test = "${curr.type != 0}">${curr.operationItem.operationOutline.labOutlineName}</c:if>
	    </td> 
	    <td>
	    	<c:if test = "${curr.type == 0}">--</c:if>
	    	<c:if test = "${curr.type != 0}">${curr.operationItem.lpName}</c:if>
	    </td>
	    <td>
			<c:if test="${curr.asset.specifications eq null}">${intPart[i.count-1]}${unit[i.count-1]}</c:if>
			<c:if test="${curr.asset.specifications ne null}">${intPart[i.count-1]}${curr.asset.unit}${decimalPart[i.count-1]*spec[i.count-1]}${unit[i.count-1]}</c:if>
	    </td>  
	    <td>
	    <c:if test="${curr.asset.flag eq 1 }">
	    	<c:if test = "${nums[i.count-1] le curr.asset.assetLimit}"><font color="red">已达到</font></c:if>
	    	<c:if test = "${nums[i.count-1] gt curr.asset.assetLimit}">未达到</c:if>
	    	</c:if>
	    </td>
	     <sec:authorize ifAnyGranted="ROLE_SUPERADMIN"> 
	     <td> 
	      <a href="${pageContext.request.contextPath}/asset/listStoreAdjustRecords?id=${curr.id} ">库存调整</a> 
	    </td>
	    </sec:authorize> 
	    <td>
	    	<a href="${pageContext.request.contextPath}/asset/getAssetReceiveRecords?id=${curr.id}&currpage=1">申领记录</a> 
	    	 <sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
	    	<a href="${pageContext.request.contextPath}/asset/getAssetAdjustRecords?currpage=1&id=${curr.id}">调整记录</a> 
	    	</sec:authorize>
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
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/asset/listAssetRecords?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/asset/listAssetRecords?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}//asset/listAssetRecords?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/asset/listAssetRecords?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/asset/listAssetRecords?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/asset/listAssetRecords?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
