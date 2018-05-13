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
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  
  </script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">实验室及预约管理</a></li>
		<li class="end"><a href="javascript:void(0)">实验室管理</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">实验室列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/labAnnex/newLabAnnex?labCenterId=${cid}">新建</a>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/labAnnex/listLabAnnex?currpage=1" method="post" modelAttribute="labAnnex">
			 <ul>
  				<li>实验室名称： </li>
  				<li><form:input id="lab_name" path="labName"/></li>
  				<li>
			      <input type="button" value="取消" onclick="cancel();"/><input type="submit" value="查询"/></li>
  				</ul>
			 
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>实验室名称</th>
	    <th>实验室分室数量</th>
	    <th>学院名称</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listLabAnnex}" var="curr">
	  <tr>
	    <td>${curr.labName}</td>
	    <td>${curr.labNumber}</td>
	    <td>${curr.labCenter.schoolAcademy.academyName}</td>
	    <td>
	      <a href="${pageContext.request.contextPath}/labAnnex/getLabAnnex?id=${curr.id}">查看</a>
	      <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER" >
	      <a href="${pageContext.request.contextPath}/labAnnex/editLabAnnex?labAnnexId=${curr.id}">编辑</a>
	      </sec:authorize>
	      <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER" >
	      <a href="${pageContext.request.contextPath}/labAnnex/deleteLabAnnex?labAnnexId=${curr.id}" onclick="return confirm('确定删除？');">删除</a>
	      </sec:authorize>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labAnnex/listLabAnnex?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labAnnex/listLabAnnex?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/labAnnex/listLabAnnex?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/labAnnex/listLabAnnex?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labAnnex/listLabAnnex?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labAnnex/listLabAnnex?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
