<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
  	<meta name="decorator" content="iframe"/>
</head>
  
<body>
<div class="navigation">
	<div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">实验项目</a></li>
		<li class="end"><a href="javascript:void(0)">实验项目汇总</a></li>
	  </ul>
	</div>
</div>
<div class="right-content">
    <div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
  	<div class="TabbedPanelsContent">
  	<div class="content-box">
  	<div class="title">
		<div id="title">实验项目汇总</div>
	</div>
	
	<table border="0" cellpadding="5" cellspacing="0" bgcolor="#F3EFEE" height="30" width="100%">
	<tbody>
		<tr>
	    	<td>
	    	<form:form name="queryForm" action="${pageContext.request.contextPath}/operation/project/searchProjectSummary?currpage=1&orderBy=${orderBy }" method="post" modelAttribute="operationItem">
				<ul>
	  				<li>实验室名称： 
	  				<form:input id="lab_name" path="labRoom.labRoomName"/>
	  				学期：
	  				  <form:select path="schoolTerm.id" id="term_id">
	  				    <form:option value="">--请选择--</form:option>
	  				    <form:options items="${schoolTerms}" itemLabel="termName" itemValue="id"/>
	  				  </form:select>
	  				<input type="submit" value="查询"/>
				    <input type="button" value="取消" onclick="cancel();"/></li>
				</ul>
			</form:form>
	    	</td>    
		</tr>
	</tbody>
	</table>
	
	<table class="tb" id="my_show">
	  <thead style="center-content">
		  <tr>
		    <th width=5%>房间号</th>
		    <th width=15%>实验室名称</th>
		    <th width=5%>序号</th>
		    <th width=20%>实验项目名称</th>
		    <th width=8%>实验者类别</th>
		    <th width=12%>面向专业</th>
		    <th width=5%>实验时数</th>
		    <th width=15%>课程名称</th>
		    <th width=5%>实验要求</th>
		    <th width=8%>实验类型</th>
		  </tr>
	  </thead>
	  
	  <tbody>
	  	<c:forEach items="${ProjectSummaries}" var="curr">
		    <tr> 
			  <td>${curr.labRoom.labRoomNumber}</td>
			  <td>${curr.labRoom.labRoomName}</td>
			  <td>${curr.lpCodeCustom}</td>
			  <td>${curr.lpName}</td>
			  <td>${curr.CDictionaryByLpCategoryStudent.CName}</td>
			  <td>${curr.systemMajor12.MName}</td>
			  <td>${curr.lpCourseHoursTotal}</td>
			  <td>${curr.schoolCourseInfo.courseName}</td>
			  <td>${curr.CDictionaryByLpCategoryRequire.CName}</td>
			  <td>${curr.CDictionaryByLpCategoryApp.CName}</td>
		    </tr>
		  </c:forEach>
	  </tbody>
	</table>
	
	<!-- 分页开始 -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="${pageContext.request.contextPath}/operation/project/projectsummary?currpage=1&orderBy=${orderBy }" target="_self">首页</a>			    
	<a href="${pageContext.request.contextPath}/operation/project/projectsummary?currpage=${pageModel.previousPage}&orderBy=${orderBy }" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/operation/project/projectsummary?currpage=${pageModel.currpage}&orderBy=${orderBy }">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/operation/project/projectsummary?currpage=${j.index}&orderBy=${orderBy }">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="${pageContext.request.contextPath}/operation/project/projectsummary?currpage=${pageModel.nextPage}&orderBy=${orderBy }" target="_self">下一页</a>
 	<a href="${pageContext.request.contextPath}/operation/project/projectsummary?currpage=${pageModel.lastPage}&orderBy=${orderBy }" target="_self">末页</a>
    </div>
    <!-- 分页结束 -->
    
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
