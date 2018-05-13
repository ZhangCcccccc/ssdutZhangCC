<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.user-resources"/>
<head>
<meta name="decorator" content="iframe" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
</head>
<body>

<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent0">
		<div class="content-box">
				<div class="title">
				<div id="title">参与指导实验项目教师</div>
				<a class="btn btn-new" onclick="window.history.go(-1);">返回</a>
				</div>
            <table  id="my_show"> 
                <thead>
                    <tr>                   
                        <th>序号</th>
                        <th>教师工号</th>
                        <th>教师姓名</th>
                        <th>学院</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${teachers}" var="current"  varStatus="i">	
                        <tr>
                           <td>${i.count}</td>
                           <td>${current.username}</td>
                           <td>${current.cname}</td>
                           <td>${current.schoolAcademy.academyName}</td>
                        </tr>
                        </c:forEach>
                </tbody>
<!-- 分页导航 -->
<%-- <tr> 
    <td colspan="11" align="center" >
                 总记录:<strong>${totalRecords}</strong>条&nbsp;
                 总页数:<strong>${pageModel.totalPage}</strong>页 <input type="hidden" value="${pageModel.lastPage}" id="totalpage" />&nbsp;
                 当前页:第<strong>${pageModel.currPage}</strong>页 <input type="hidden" value="${pageModel.currpage}" id="currpage" />&nbsp;
		   <a href="${pageContext.request.contextPath}/report/reportTeacherItemList?currpage=${pageModel.firstPage}&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms")%>" target="_self"> 首页</a> 	   
		   <a href="${pageContext.request.contextPath}/report/reportTeacherItemList?currpage=${pageModel.previousPage}&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms") %>"  target="_self">上一页 </a> 
		   <a href="${pageContext.request.contextPath}/report/reportTeacherItemList?currpage=${pageModel.nextPage}&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms") %>"  target="_self">下一页 </a> 
		   <a href="${pageContext.request.contextPath}/report/reportTeacherItemList?currpage=${pageModel.lastPage}&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms") %>" target="_self">末页 </a>&nbsp;
		   <!-- 跳转到选择/输入的页面 -->
		   第 <select class="chzn-select" onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
		   <option value="${pageContext.request.contextPath}/report/reportTeacherItemList?currpage=${page}&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms") %>">${page}</option>
		   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
           <c:if test="${j.index!=page}">
           <option value="${pageContext.request.contextPath}/report/reportTeacherItemList?currpage=${j.index}&academyNumber=<%=request.getParameter("academyNumber")%>&terms=<%=request.getParameter("terms") %>">${j.index}</option>
           </c:if>
           </c:forEach>
           </select> 页
    </td>
</tr> --%>
<!-- 分页导航 -->
</table>
</div>
</div>
</div>
<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {  	
      $(selector).chosen(config[selector]);
    }
</script>
<!-- 下拉框的js -->
</body>
</html>

