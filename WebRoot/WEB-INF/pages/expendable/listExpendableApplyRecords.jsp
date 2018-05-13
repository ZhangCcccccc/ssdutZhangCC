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
    window.location.href="${pageContext.request.contextPath}/expendable/listExpendableApplyRecords?currpage=1&id=${id}";
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
		<li class="end"><a href="javascript:void(0)">申领</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">申领记录列表</div>
	</div>
	
	<%--<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/expendable/listExpendableApplyRecords?currpage=1&id=${id}" method="post" modelAttribute="expendableApply">
			 <ul>
			 <li>耗材名称： </li>
  				<li>
  					<form:select id="expendableName" path="expendable.expendableName" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listExpendableNames}" var="curr">
  							<form:option value="${curr.expendableName}">${curr.expendableName}</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>危化品标识： </li>
  				<li>
  					<form:select id="ifDangerous" path="expendable.ifDangerous" class="chzn-select">
  						<form:option value=""> 选择</form:option>
  						<form:option value="1">是</form:option>
  						<form:option value="0">否</form:option>
  					</form:select>
  				</li>
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="cancel()"/></li>
  				</ul>
  				</form:form>
	</div>--%>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>序号</th>
	    <th>商品名称</th>
	    <th>危化品标识</th>
	    <th>危化品种类</th>
	    <th>规格</th>
	    <th>品牌</th>
	    <th>数量</th>
	    <th>使用类型</th>
	    <th>日期</th>
	    <th>申领人</th>
	    <th>申领说明</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listExpendableApplyRecords}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(currpage-1)}</td>
	    <td>${curr.expendable.expendableName}</td>
	    
	    <c:if test="${curr.expendable.ifDangerous eq 0}">
	    <td>否</td></c:if>
	    <c:if test="${curr.expendable.ifDangerous eq 1}">
	    <td>是</td></c:if>
	    <c:if test="${empty curr.expendable.ifDangerous}">
	    <td></td>
	    </c:if>
	    
	    <c:choose>
	    <c:when test="${empty curr.expendable.dangerousType}"><td></td></c:when>
	    <c:otherwise><td>${curr.expendable.dangerousType}</td></c:otherwise>
	    </c:choose>
	    
	    <td>${curr.expendable.expendableSpecification}</td>
	    <td>${curr.expendable.brand}</td>
	    <td>${curr.expendableNumber}</td>
	    <td>${curr.expendable.expendableSource}</td>
	    <td><fmt:formatDate value="${curr.borrowTime.time}" pattern="yyyy-MM-dd" /></td>
	    <td>${curr.user.cname}</td>
	    <td>${curr.remarks}</td>
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
	<!-- 分页[s] -->
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableApplyRecords?id=${id }&currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableApplyRecords?id=${id }&currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/expendable/listExpendableApplyRecords?id=${id }&currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/expendable/listExpendableApplyRecords?id=${id }&currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableApplyRecords?id=${id }&currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableApplyRecords?id=${id }&currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
