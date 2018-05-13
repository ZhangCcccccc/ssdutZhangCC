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
    window.location.href="${pageContext.request.contextPath}/asset/getAssetReceiveRecords?currpage=1&id=${id }";
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
	    <li><a href="javascript:void(0)">药品溶液管理</a></li>
		<li class="end"><a href="javascript:void(0)">物资记录</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <%--<ul class="TabbedPanelsTabGroup">
  <li class="TabbedPanelsTab" id="s9"><a href="${pageContext.request.contextPath}/asset/getAssetReceiveRecords?currpage=1&status=9">全部</a></li>
		<li class="TabbedPanelsTab" id="s2"><a href="${pageContext.request.contextPath}/asset/getAssetReceiveRecords?currpage=1&status=2">未提交</a></li>
		<li class="TabbedPanelsTab" id="s1"><a href="${pageContext.request.contextPath}/asset/getAssetReceiveRecords?currpage=1&status=1">审核通过</a></li>
		<li class="TabbedPanelsTab" id="s0"><a href="${pageContext.request.contextPath}/asset/getAssetReceiveRecords?currpage=1&status=0">审核拒绝</a></li>
	</ul>--%>
  <div class="TabbedPanelsContentGroup">
  
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">在用物资列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/listAssetRecords?currpage=1">返回</a> 
	</div>
	<%--<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/asset/getAssetReceiveRecords?currpage=1&status=9" method="post" modelAttribute="assetReceive">
			 <ul>
  				<li>申请编号： </li>
  				<li>
  					<form:select id="receiveNo" path="receiveNo" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listAssetReceives}" var="curr">
  							<form:option value="${curr.receiveNo}">${curr.receiveNo}</form:option>
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
	    <th>实验大纲</th>
	    <th>实验项目</th>
	    <th>领用时间</th>
	    <th>申请时间</th> 
	    <th>申领人</th>  
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listAssetReceiveRecords}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(currpage-1)}</td> 
	    <td>${curr.asset.chName}${curr.asset.specifications}</td>
	      <td>
	    	<c:if test = "${curr.assetReceive.type == 0}">公用</c:if>
	    	<c:if test = "${curr.assetReceive.type != 0}">${curr.assetReceive.operationItem.operationOutline.labOutlineName}</c:if>
	    </td> 
	    <td>
	    	<c:if test = "${curr.assetReceive.type == 0}">--</c:if>
	    	<c:if test = "${curr.assetReceive.type != 0}">${curr.assetReceive.operationItem.lpName}</c:if>
	    </td>
	    <td>
	    	<fmt:formatDate value="${curr.assetReceive.startData.time}" pattern="yyyy年MM月dd日 hh:mm" />-
	    	<fmt:formatDate value="${curr.assetReceive.endDate.time}" pattern="yyyy年MM月dd日 hh:mm" />
	    </td>  
	    <td><fmt:formatDate value="${curr.assetReceive.receiveDate.time}" pattern="yyyy-MM-dd" /></td>
	   	 <td>${curr.assetReceive.user.cname}</td> 
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
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/asset/getAssetReceiveRecords?currpage=1&id=${id }')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/asset/getAssetReceiveRecords?currpage=${pageModel.previousPage}&id=${id }')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}//asset/getAssetReceiveRecords?currpage=${pageModel.currpage}&id=${id }">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/asset/getAssetReceiveRecords?currpage=${j.index}&id=${id }">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/asset/getAssetReceiveRecords?currpage=${pageModel.nextPage}&id=${id }')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/asset/getAssetReceiveRecords?currpage=${pageModel.lastPage}&id=${id }')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
