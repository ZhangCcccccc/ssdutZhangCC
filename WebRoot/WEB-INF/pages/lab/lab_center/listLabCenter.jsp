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
    window.location.href="${pageContext.request.contextPath}/labCenter/listLabCenter?currpage=1";
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
	    <li><a href="javascript:void(0)">实验中心</a></li>
		<li class="end"><a href="javascript:void(0)">实验中心列表</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">实验中心列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/labCenter/newLabCenter">新建</a>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/labCenter/listLabCenter?currpage=1" method="post" modelAttribute="labCenter">
			 <ul>
  				<li>实验中心名称： </li>
  				<li><form:input id="lab_name" path="centerName"/></li>
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="cancel();"/></li>
  				</ul>
			 
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>中心编号</th>
	    <th>中心名称</th>
	    <th>中心主任</th>
	    <th>所属校区</th>
	    <th>所属学院</th>
	    <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_SUPERADMIN,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
	    <th>操作</th>
	    </sec:authorize>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listLabCenter}" var="curr">
	  <tr>
	    <td>${curr.centerNumber}</td>
	    <td>${curr.centerName}</td>
	    <td>${curr.userByCenterManager.cname}[${curr.userByCenterManager.username}]</td>
	    <td>${curr.systemCampus.campusName}</td>
	    <td>${curr.schoolAcademy.academyName}</td>
	    <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_SUPERADMIN,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
	    <td>
	      <a href="${pageContext.request.contextPath}/labCenter/editLabCenter?labCenterId=${curr.id}">编辑</a>
	      <a href="${pageContext.request.contextPath}/labCenter/deleteLabCenter?labCenterId=${curr.id}" onclick="return confirm('确定删除？');">删除</a>
	    </td>
	    </sec:authorize>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labCenter/listLabCenter?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labCenter/listLabCenter?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/labCenter/listLabCenter?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/labCenter/listLabCenter?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labCenter/listLabCenter?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labCenter/listLabCenter?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
