<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <script type="text/javascript">
  //取消查询
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/labRoom/listLabWorker?currpage=1";
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
	    <li><a href="javascript:void(0)">实验室管理</a></li>
		<li class="end"><a href="javascript:void(0)">实验室人员管理</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">实验工作人员列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/labRoom/newLabWorker">新建</a>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/labRoom/listLabWorker?currpage=1" method="post" modelAttribute="labWorker">
			 <ul>
  				<li>实验工作人员姓名： </li>
  				<li><form:input id="lw_name" path="lwName"/></li>
  				<li>
			      <input type="button" value="取消" onclick="cancel();"/><input type="submit" value="查询"/></li>
  				</ul>
			 
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>人员编号</th>
	    <th>姓名</th>
	    <th>性别</th>
	    <th>文化程度</th>
	    <th>毕业学校</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listLabWorker}" var="curr">
	  <tr>
	    <td>${curr.lwCodeCustom}</td>
	   	<td>${curr.lwName}</td>
	    <td><c:if test="${curr.lwSex=='女'}">女</c:if><c:if test="${curr.lwSex=='男'}">男</c:if></td>
	    <td>${curr.CDictionaryByLwAcademicDegree.CName}</td>
	    <td>${curr.lwGraduationSchool}</td>
	    <td>
	      <a href="${pageContext.request.contextPath}/labRoom/editLabWorker?labWorkerId=${curr.id}">编辑</a>
	      <a href="${pageContext.request.contextPath}/labRoom/deleteLabWorker?labWorkerId=${curr.id}" onclick="return confirm('确定删除？');">删除</a>
	    </td>
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
