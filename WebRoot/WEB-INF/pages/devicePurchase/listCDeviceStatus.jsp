<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <script type="text/javascript">
//取消查询
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/labAnnex/listLabAnnex?currpage=1";
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
  
			<li class="TabbedPanelsTab selected" tabindex="0">
			<a href="${pageContext.request.contextPath}/devicePurchase/listDeviceStatus?currpage=1">设备状态字典</a>
			</li>
		
			<li class="TabbedPanelsTab"  tabindex="0">
			<a href="${pageContext.request.contextPath}/devicePurchase/listCDeviceSupplier?currpage=1">设备供应商</a>
			</li>
		
	  </ul>
  <div class="TabbedPanelsContentGroup">
  
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">设备状态列表</div>
	  <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_SUBEXCENTERDIRECTOR">
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/devicePurchase/newDeviceStatus?">新建设备状态</a>
	  </sec:authorize>
	</div>
	
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>序号</th> 
	    <th>设备状态名称</th>
	    <th>间隔天数</th>
	    <th>类型</th>
	    <th>顺序</th> 
	    <th>操作</th> 
	    <th>调序</th> 
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listCDeviceStatus}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(currpage-1)}</td> 
	    <td>${curr.statusName}</td>  
	    <td>
	    	<c:if test="${curr.flag eq 1}">${curr.intervalDate}</c:if>
	    	<c:if test="${curr.flag ne 1}">----</c:if>
	    </td>
	    <td>
	    	<c:if test="${curr.flag eq 1}">正常状态</c:if>
	    	<c:if test="${curr.flag eq 0}">特殊状态</c:if>
	    </td>
	    <td>
	    	<c:if test="${curr.flag eq 1}">${curr.statusOrder}</c:if>
	    	<c:if test="${curr.flag ne 1}">----</c:if>
	    </td>
	     <td>
	      <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_SUBEXCENTERDIRECTOR">
	      <a href="${pageContext.request.contextPath}/devicePurchase/editDeviceStatus?id=${curr.id}">修改</a> 
	      <a href="${pageContext.request.contextPath}/devicePurchase/deleteDeviceStatus?id=${curr.id}" onclick="return confirm('确定删除？');">删除</a>
	      </sec:authorize>
	    </td>
	    <td>
	    <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_SUBEXCENTERDIRECTOR">
	     <c:if test="${curr.statusOrder ne 1 && curr.flag eq 1}">
	     	<a href="${pageContext.request.contextPath}/devicePurchase/addStatusOrder?id=${curr.id}"><img src="${pageContext.request.contextPath}/images/up.JPG" width="15px" height="15px"/></a>
	     </c:if>
	     <c:if test="${curr.statusOrder eq 1 || curr.flag eq 0}">
			<a href="#"><img src="${pageContext.request.contextPath}/images/greyUp.JPG" width="15px" height="15px"/></a>
	     </c:if>
	    <c:if test="${curr.statusOrder ne totalNormalRecords && curr.flag eq 1}">
	    	<a href="${pageContext.request.contextPath}/devicePurchase/subStatusOrder?id=${curr.id}"><img src="${pageContext.request.contextPath}/images/down.jpg" width="15px" height="15px"/></a>
	    </c:if>
	    <c:if test="${curr.statusOrder eq totalNormalRecords || curr.flag eq 0}">
	    	<a href="#"><img src="${pageContext.request.contextPath}/images/greyDown.JPG" width="15px" height="15px"/></a>
	    </c:if>
	    </sec:authorize>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listDeviceStatus?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listDeviceStatus?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/devicePurchase/listDeviceStatus?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/devicePurchase/listDeviceStatus?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listDeviceStatus?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/devicePurchase/listDeviceStatus?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
