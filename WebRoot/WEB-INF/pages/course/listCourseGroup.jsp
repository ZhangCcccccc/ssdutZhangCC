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
    window.location.href="${pageContext.request.contextPath}/course/listCourseGroup?currpage=1";
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
	    <li><a href="javascript:void(0)">学院课程</a></li>
		<li class="end"><a href="javascript:void(0)">选课组</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">选课组</div>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/course/listCourseGroup?currpage=1" method="post" modelAttribute="courseGroup">
			 <ul>
  				<li>学期： </li>
  				<li>
  				  <form:select path="schoolTerm.id">
  				    <form:options items="${listSchoolTerm}" itemLabel="termName" itemValue="id"/>
  				  </form:select>
  				</li>
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="cancel();"/></li>
  				</ul>
			 
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>选课组编号</th>
	    <th>[课程编号]课程名称</th>
	    <th>[班级编号]班级名称</th>
	    <th>教师</th>
	    <th>学期</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listCourseGroup}" var="curr">
	  <tr>
	    <td>${curr.cgCodeCustom}</td>
	    <td>[${curr.schoolCourse.CNumber}]${curr.schoolCourse.CName}</td>
	    <td>[${curr.cgClassNo}]${curr.cgClassName}</td>
	    <td>[${curr.user.username}]${curr.user.cname}</td>
	    <td>${curr.schoolTerm.termName}</td>
	    <td><a href="${pageContext.request.contextPath}/course/courseGroupDetail?cgId=${curr.cgId}">查看</a></td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/course/listCourseGroup?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/course/listCourseGroup?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/course/listCourseGroup?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/course/listCourseGroup?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/course/listCourseGroup?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/course/listCourseGroup?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
