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
  <script type="text/javascript">
//取消查询
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/labAnnex/listLabAnnex?currpage=1";
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
		<li class="end"><a href="javascript:void(0)">申领审核</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
		
		<li class="TabbedPanelsTab" id="s4"><a href="${pageContext.request.contextPath}/expendable/listExpendableApplies?currpage=1&flag=0">待审核</a></li>
		<li class="TabbedPanelsTab" id="s2"><a href="${pageContext.request.contextPath}/expendable/listExpendableApplies?currpage=1&flag=2">审核通过</a></li>
		<li class="TabbedPanelsTab" id="s3"><a href="${pageContext.request.contextPath}/expendable/listExpendableApplies?currpage=1&flag=3">审核拒绝</a></li>
		<li class="TabbedPanelsTab" id="s4"><a href="${pageContext.request.contextPath}/expendable/listExpendableApplies?currpage=1&flag=1">审核中</a></li>
		<li class="TabbedPanelsTab" id="s1"><a href="${pageContext.request.contextPath}/expendable/listExpendableApplies?currpage=1&flag=9">全部</a></li>
</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">审核列表</div>
	</div>
	
	<%--<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchase?currpage=1" method="post" modelAttribute="nDevicePurchase">
			 <ul>
  				<li>申购编号： </li>
  				<li><form:input id="purchaseNumber" path="purchaseNumber"/></li>
  				<li>申购名称： </li>
  				<li><form:input id="useDirection" path="useDirection"/></li>
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="window.history.go(0)"/></li>
  				</ul>
			 
		</form:form>
	</div>
	
	--%><table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>序号</th>
	    <th>商品名称</th>
	    <th>危化品标记</th>
	    <th>危化品种类</th>
	    <th>包装单位</th>
	    <th>单价</th>
	    <th>数量</th>
	    <th>申领时间</th>
	    <th>申领人</th>
	    <th>状态</th>
	    <th>审核人</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listExpendableApplies}" var="curr" varStatus="i">
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
	    
	    <td>${curr.expendable.expendableUnit}</td>
	    <td>${curr.expendable.unitPrice}</td>
	    <td>${curr.expendableNumber}</td>
	    <td><fmt:formatDate value="${curr.borrowTime.time}" pattern="yyyy-MM-dd" /></td>
	    <td>${curr.user.cname}</td>
	    <c:if test="${curr.flag eq 0}"><td>未审核</td></c:if>
	    <c:if test="${curr.flag eq 1}"><td>审核中</td></c:if>
	    <c:if test="${curr.flag eq 2}"><td>审核通过</td></c:if>
	    <c:if test="${curr.flag eq 3}"><td><font color="red">审核拒绝</font></td></c:if>
	    <td>
	    <c:forEach items="${curr.expendableApplyAuditRecords}" var="auditRecords">
	    <c:if test = "${auditRecords.auditResult eq 1}">
			${auditRecords.user.cname}（通过）<br>
		</c:if>
		<c:if test = "${auditRecords.auditResult eq 0}">
			${auditRecords.user.cname}<font color="red">（拒绝）</font><br>
		</c:if>
		</c:forEach>
	    </td>
	    <td>
	      <c:if test="${curr.expendable.user.username eq userName}">
	      <c:if test="${curr.flag eq 0}">
	      <a href="${pageContext.request.contextPath}/expendable/auditExpendableApplyByOwner?id=${curr.id}">去审核</a>
	      </c:if>
	       </c:if>
	      <sec:authorize ifAllGranted="ROLE_EXCENTERDIRECTOR">
	      <c:if test="${curr.flag eq 1}">
	      <a href="${pageContext.request.contextPath}/expendable/auditExpendableApply?id=${curr.id}">去审核</a>
	      </c:if>
	      </sec:authorize>
	    </td>
	  </tr>
	  
	  </c:forEach>
	  </tbody>
	</table>
	<!-- 下拉框的js -->
	<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableApplies?flag=${flag }&currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableApplies?flag=${flag }&currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/expendable/listExpendableApplies?flag=${flag }&currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/expendable/listExpendableApplies?flag=${flag }&currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableApplies?flag=${flag }&currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableApplies?flag=${flag }&currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
