<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<title>课程管理 </title>
  	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
  	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
  	<link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet">
  	<link href="${pageContext.request.contextPath}/css/admin2.css" rel="stylesheet">
<script type="text/javascript" >
	$(function($) {
		
	});
</script>
</head>

<body huaban_screen_capture_injected="true" style="">

	<div class="container">
    <div class="row">  
    <div class="col-md-2">
    <jsp:include page="/WEB-INF/pages/other/systemMenu.jsp"/>
    
    </div>
  <div class="col-md-10">
        <div class="page-header">
    <a href="newCourse" class="btn btn-info btn-sm pull-right" target="_blank">创建课程</a>
    <h1>课程管理</h1>
  </div>

	<form  class="form-inline well well-sm" action="${pageContext.request.contextPath}/admin/searchCourse" method="post" >
    <div class="form-group">
		<select class="form-control" name="categoryId">
        <option value="">课程分类</option>
        </select>
    </div>
    <div class="form-group">
      <input class="form-control" type="text" placeholder="标题" name="search" >
    </div>
    
    <input class="btn btn-primary" type="submit" id="searchbut"></input>
	</form>

	<table class="table table-striped table-hover" id="course-table">
    <thead>
    	<tr>
      	<th>编号</th>
      	<th width="40%">名称</th>
      	<th>学生数</th>
      	<th>创建者</th>
      	<th>操作</th>
    	</tr>
    </thead>
    <tbody>
	
	<c:forEach items="${courses}" var="current" varStatus="i"> 
	<tr id="course-tr-6">
  		<td>${current.code}</td>
 		<td>
    		<a href="${pageContext.request.contextPath}/admin/course/${current.id}/base" target="_blank"><strong>${current.name}</strong></a><br>
    		<span class="text-muted text-sm">标签：--</span>
		</td>
  		<td>${fn:length(current.users)}</td>
  		<td>
  			<a >${current.manager }</a><br>
    		<span class="text-muted text-sm"><fmt:formatDate dateStyle="short" type="both" value="${current.cerateTime.time}"/></span>
    	</td>
 		<td><a onclick="return confirm('是否删除此条记录？')" title="删除" href="${pageContext.request.contextPath}/admin/deleteCourse?idKey=${current.id}">
        <img src="${pageContext.request.contextPath}/images/icn_trash.png" /></a></td>
	</tr>
	</c:forEach>                 
	</tbody>
	</table>

	</div>
	</div>
	</div>
<div id="modal" class="modal"></div>
</body>
</html>