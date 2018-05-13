<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<html >  
<head>
<meta name="decorator" content="iframe"/>  
<title><fmt:message key="html.title"/></title>
<!-- js导入分页js -->
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/operation/operationitme.js"></script>
 <link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" />
 <!-- 打印插件的引用 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
    //首页
	function first(){
		window.location.href="${pageContext.request.contextPath}/report/reportComplexItems?currpage=1&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms")%>";
	}
	//末页
	function last(){
		var totalPage=${pageModel.totalPage};
		window.location.href="${pageContext.request.contextPath}/report/reportComplexItems?currpage="+totalPage+"&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms")%>";
	}
	//上一页
	function previous(){
		var page=<%=request.getParameter("currpage")%>;
		if(page == 1)
		{
			page=1;
		}
		else
		{
			page = page-1;
		}
		window.location.href="${pageContext.request.contextPath}/report/reportComplexItems?currpage="+page+"&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms")%>";
	}
	//下一页
	function next(){
		var totalPage=${pageModel.totalPage};
		var page=<%=request.getParameter("currpage")%>;
		if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1
		}
		window.location.href="${pageContext.request.contextPath}/report/reportComplexItems?currpage="+page+"&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms")%>";
	}
</script>
 <!-- 打印插件的引用 -->
</head>
<body>

<!-- 结项申报列表 -->

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		<div class="content-box">
			<div class="title">
				<div id="title">实验项目管理</div>
				<a class="btn btn-new" onclick="window.history.go(-1);">返回</a>
			</div>
			<div class="content-box">
<table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter">
<thead>
<tr align="left">
	<th class="thead" width="4%" >序号</th>	
	<th class="thead" width="10%">实验项目名称</th>
	<th class="thead" width="10%">实验项目卡编号</th>
	<th class="thead" width="10%">所属实验室</th>
	<th class="thead" width="10%">实验大类</th>
	<th class="thead" width="10%">所属课程</th>
</tr>
</thead>
<tbody>

<!-- 根据control层传递参数projects，对未结项项目列表并操作 -->
<c:choose>
    <c:when test="${(i.count) % 2 == 0}">
    <c:set var="rowclass" value="rowtwo"/>
    </c:when>
    <c:otherwise>
	<c:set var="rowclass" value="rowone"/>
	</c:otherwise>
</c:choose>
<c:forEach items="${items}" var="s" varStatus="i">
<c:if test="${s.id!=0 }">
<tr align="left" class="${rowclass}" >
    <td>${i.count}</td>
    <td>${s.itemName}</td>
    <td>${s.experimentItemCardNumber}</td>
    <td>${s.labRoom.labRoomName}</td>
    <td>${s.COperationItemCategory.name}</td>
    <td>${s.schoolCourseByClassNo.courseName}(${s.schoolCourseByClassNo.schoolCourseInfo.courseNumber})</td>
</tr>
</c:if>
</c:forEach>

</tbody>
<!-- 分页导航 -->			
</table>
<div class="page">总共有:${totalRecords}条记录 &nbsp;
	 总页数:${pageModel.totalPage}页 &nbsp; 当前第:${pageModel.currPage}页<input type="hidden" value="${pageModel.lastPage}" id="totalpage" />&nbsp;<!-- 跳转到选择/输入的页面 -->跳转到
		   第 <select class="chzn-select" onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
		   <option value="${pageContext.request.contextPath}/report/reportComplexItems?currpage=<%=request.getParameter("currpage")%>&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms")%>">${pageModel.currPage}</option>
		   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
           <c:if test="${j.index!=pageModel.currPage}">
           <option value="${pageContext.request.contextPath}/report/reportComplexItems?currpage=${j.index}&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms")%>">${j.index}</option>
           </c:if>
           </c:forEach>
           </select> 页
    <a href="javascript:void(0)" onclick="first();" target="_self">首页</a>				    
    <a href="javascript:void(0)" onclick="previous();" target="_self">上一页</a>
    <a href="javascript:void(0)" onclick="next();" target="_self">下一页</a>
 	<a href="javascript:void(0)" onclick="last();" target="_self">末页</a>		
</div>

</div>
</div>
</div>

</div>
</div>
</body>
<!-------------列表结束----------->
</html>