<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <script type="text/javascript">
  <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 -->
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
	  <div id="title">申购详情列表</div>
	</div>
	
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>名称</th>
	    <th>危化品标记</th>
	    <th>危化品种类</th>
	    <th>品牌</th>
	    <th>类别</th>
	    <th>规格</th>
	    <th>单价</th>
	    <th>数量</th>
	    <th>包装单位</th>
	    <th>总价</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${expendable}" var="curr" varStatus="i">
	  <tr>
	     <td>${curr.expendableName}</td>
	    <c:if test="${curr.ifDangerous eq 0}">
	    <td>否</td></c:if>
	    <c:if test="${curr.ifDangerous eq 1}">
	    <td>是</td></c:if>
	    <c:if test="${curr.ifDangerous eq null}">
	    <td></td>
	    </c:if>
	    <td>${curr.dangerousType}</td>
	    <td>${curr.brand}</td>
	    <td>${curr.expendableSource}</td>
	    <td>${curr.expendableSpecification}</td>
	    <td>${curr.unitPrice}</td>
	    <td>${curr.quantity}</td>
	    <td>${curr.expendableUnit}</td>
	    <td>${curr.arriveTotalPrice}</td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
