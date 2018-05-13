<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    window.location.href="${pageContext.request.contextPath}/labconstruction/listLabConstructionAcceptances?currpage=1";
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
	    <li><a href="javascript:void(0)">实验室建设</a></li>
		<li class="end"><a href="javascript:void(0)">项目验收列表</a></li>
	  </ul>
	</div>
  </div>
  
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  
  <div class="content-box">
    <div class="title">
	  <div id="title">项目验收</div>
	  	
	  </div>
	<!-- 查询开始  -->
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/labconstruction/listLabConstructionAcceptances?currpage=1" method="post" modelAttribute="labConstructionAcceptance">
			 <ul>
			 	
  				<li>项目名称： </li>
  				<li><form:input id="project_name" path="labConstructionProject.projectName"/></li>
  				
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="cancel();"/></li>
  				</ul>
			 
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>项目编号</th>
	    <th>项目名称</th>
	    <th>创建时间</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listLabConstructionAcceptances}" var="curr">
	   <c:if test="${fn:contains(curr.labConstructionProject.MLabConstructionProjectUsers,username) || curr.labConstructionProject.user.username==user.username|| user.username==curr.labConstructionProject.userByCreatedBy.username}">
	   <!-- 上面这句话限制了页面上显示的是与当前当路人相关（当前登录人是项目负责人，填写者，项目成员）的项目 -->
	  <tr>
	    <td>${curr.labConstructionProject.projectNumber}</td>
	    <td>${curr.labConstructionProject.projectName}</td>
	    <td><fmt:formatDate value="${curr.createdAt.time}" pattern="yyyy-MM-dd"/></td>
	    
	    
	    <!-- 操作开始  -->
	    <td>
	    <c:if test="${curr.stage==0&&(user.username==curr.labConstructionProject.user.username || user.username==curr.labConstructionProject.user.username || fn:contains(superAdmins,username))}">  
	    <!-- 从当前登录人规定了提交，编辑，删除功能的使用权 -->
	      <a href="${pageContext.request.contextPath}/labconstruction/submitLabConstructionAcceptance?labConstructionAcceptanceId=${curr.id}" onclick="return confirm('此过程不可恢复，确认要提交吗？')">提交</a>
	      <a href="${pageContext.request.contextPath}/labconstruction/editLabConstructionAcceptance?labConstructionAcceptanceId=${curr.id }">编辑</a>
	      <a href="${pageContext.request.contextPath}/labconstruction/deleteLabConstructionAcceptance?labConstructionAcceptanceId=${curr.id }" onclick="return confirm('确认要删除吗？')">删除</a>
	    </c:if>
	      <a href="${pageContext.request.contextPath}/labconstruction/veiwLabConstructionAcceptance?labConstructionAcceptanceId=${curr.id }">查看</a>
	    </td>
	  </tr>
	  </c:if>
	  </c:forEach>
	  
	  </tbody>
	</table>
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionAcceptances?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionAcceptances?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/labconstruction/listLabConstructionProject?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/labconstruction/listLabConstructionProject?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionAcceptances?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionAcceptances?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    
  </div>
  </div>
  </div>
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
</body>
</html>
