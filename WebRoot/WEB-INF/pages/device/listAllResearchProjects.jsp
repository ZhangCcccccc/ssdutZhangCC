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
  <!-- 下拉的样式结束 --> 
  <script type="text/javascript"> 
//取消查询
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/device/listAllResearchProjects?page=1";
  }
  //跳转
  function targetUrl(url)
{
	location.href = url;
} 
//AJAX验证是否有不规范的字段
   
  </script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">实验设备开放</a></li>
		<li class="end"><a href="javascript:void(0)">课题组管理</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <%--<ul class="TabbedPanelsTabGroup">
  
			<li class="TabbedPanelsTab" tabindex="0" id="s0">
			<a href="${pageContext.request.contextPath}/asset/listAssets?page=1&category=0">试剂</a>
			</li>
		
			<li class="TabbedPanelsTab"  tabindex="0" id="s1">
			<a href="${pageContext.request.contextPath}/asset/listAssets?page=1&category=1">耗材</a>
			</li>
		
	  </ul>--%>
  <div class="TabbedPanelsContentGroup">
  
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">课题组列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/device/newResearchProject">新建课题组</a> 
	</div>
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/device/listAllResearchProjects?page=1" method="post" modelAttribute="researchProject">
			 <ul>
  				<li>编码： </li>
  				<li>
  					<form:select id="code" path="code" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listAllResearchProjects}" var="curr">
  							<form:option value="${curr.code}">${curr.code}</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>课题组名称： </li>
  				<li>
  					<form:select id="name" path="name" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listAllResearchProjects}" var="curr">
  							<form:option value="${curr.name}">${curr.name}</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>
			      <input type="button" value="取消" onclick="cancel()"/><input type="submit" value="查询"/></li>
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>序号</th> 
	    <th>编码</th> 
	    <th>课题组名称</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listResearchProjects}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(page-1)}</td> 
	    <td>${curr.code}</td>  
	    <td>${curr.name}</td> 
	     <td>
	      <a href="${pageContext.request.contextPath}/device/editResearchProject?id=${curr.id}">编辑</a> 
	      <a href="${pageContext.request.contextPath}/device/deleteResearchProject?id=${curr.id}" onclick="return confirm('确定删除？');">删除</a>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	</div>
		 
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
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/listAllResearchProjects?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/listAllResearchProjects?page=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}//device/listAllResearchProjects?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/device/listAllResearchProjects?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/listAllResearchProjects?page=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/listAllResearchProjects?page=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
