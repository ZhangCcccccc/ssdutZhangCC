<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
    window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoomCourseCapacity?currpage=1";
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
	  <div id="title">实验室容量与课程的关系列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/labRoom/newLabRoomCourseCapacity">新建</a>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/labRoom/listLabRoomCourseCapacity?currpage=1" method="post" modelAttribute="labRoomCourseCapacity">
			 <ul>
  				<li>实验室： </li>
  				<li style="width: 300px;">
					<form:select path="labRoom.id" class="chzn-select">
						<form:option value="">请选择</form:option>
						<c:forEach  items="${listLabRooms}" var="curr">
							<form:option value="${curr.id}">${curr.labRoomName}[${curr.labRoomAddress}]</form:option>
						</c:forEach>
				      <%-- <form:options items="${listLabRooms}" itemLabel="labRoomName" itemValue="id"/> --%>
				    </form:select>
				</li>
				<li>课程： </li>
  				<li style="width: 300px;">
					<form:select path="schoolCourseDetail.courseDetailNo" class="chzn-select">
						<form:option value="">请选择</form:option>
						<c:forEach items="${listSchoolCourseDetails}" var="curr">
							<form:option value="${curr.courseDetailNo}">${curr.courseName}[${curr.courseDetailNo}]</form:option>
						</c:forEach>
				      <%-- <form:options items="${listSchoolCourseDetails}" itemLabel="courseName" itemValue="courseDetailNo"/> --%>
				    </form:select>
				</li>
  				<li>
			      <input type="button" value="取消" onclick="cancel();"/><input type="submit" value="查询"/></li>
  				</ul>
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>序号</th>
	    <th>实验室名称</th>
	    <th>课程名称</th>
	    <th>容量</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listLabRoomCourseCapacity}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count}</td>
	   	<td>${curr.labRoom.labRoomName}-${curr.labRoom.labRoomAddress}</td>
	    <td>${curr.schoolCourseDetail.courseName}-[${curr.schoolCourseDetail.schoolCourse.courseNo}]</td>
	    <td>${curr.capacity}</td>
	    <td>
	      <a href="${pageContext.request.contextPath}/labRoom/editLabRoomCourseCapacity?id=${curr.id}">编辑</a>
	      <a href="${pageContext.request.contextPath}/labRoom/deleteLabRoomCourseCapacity?id=${curr.id}" onclick="return confirm('确定删除？');">删除</a>
	    </td>
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
	<!-- 下拉框的js -->
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
