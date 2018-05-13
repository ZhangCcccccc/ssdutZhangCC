<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.user-resources"/>
<head>
<meta name="decorator" content="iframe" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
 <script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#my_show").jqprint();
	});
});	
</script> 
<script type="text/javascript">
function exportSelect(){
	 document.form.action="exportExcelSelectUser";
	 document.form.submit();
	}
function exportAll(s){
	document.form.action=s;
	document.form.submit();
}
</script>
</head>
<body>
<!-- 导航栏 -->
<div class="navigation">
<div id="navigation">
<ul>
<li><a href="javascript:void(0)">共享数据</a></li>
<li class="end"><a href="${pageContext.request.contextPath}/system/listUser?currpage=1">用户管理</a></li>
</ul>
</div>
</div>
<!-- 导航栏 -->
<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent0">
		<!-- 查询、导出、打印 -->
		<div class="tool-box">
    <form:form name="form" method="Post" action="${pageContext.request.contextPath}/findAllUserCard?currpage=1" modelAttribute="user">
	<ul>
	    <li> 用户：
	    <form:select class="chzn-select"  path="username" id="username" cssStyle="width:600px" >
	    <c:forEach items="${userMap}" var="curr"  varStatus="i">	
	       <form:option value="${curr.username}" label="${curr.username};${curr.cname};${curr.schoolAcademy.academyName}" />  
	    </c:forEach>
        </form:select></li>
		<li><input type="submit" value="查询"></li>
		<li><a href="${pageContext.request.contextPath}/findAllUserCard?currpage=1"><input type="button" value="取消查询"></a></li>
		<li><input type="button" value="导出" onclick="exportAll('${pageContext.request.contextPath}/system/exportUserList?page=${page}');"></li>
		<li><input type="button" value="打印" id="print"></li>
	</ul>
</form:form>
</div>
<!-- 查询、导出、打印 -->

		<div class="content-box">
				<div class="title">用户管理</div>
            <table  id="my_show"> 
                <thead>
                    <tr>                   
                        <th>序号</th>
                        <th><fmt:message key="user.number"/></th>
                        <th><fmt:message key="user.name"/></th>
                        <th>卡号</th>
                        <th><fmt:message key="operation"/></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${userMap}" var="current"  varStatus="i">	
                        <tr>
                           <td>${i.count}</td>
                           <td>${current.username}</td>
                           <td>${current.cname}</td>
                           <td>${current.cardno}</td>
                           
                           <td><a class="btn btn-normal" href="${pageContext.request.contextPath}/findUserCardById?num=${current.username}&">查询</a></td>
                        </tr>
                        </c:forEach>
                </tbody>
<!-- 分页导航 -->
<tr> 
    <td colspan="11" align="center" >
                 共<strong>${totalRecords}</strong>条记录&nbsp;
                 总页数:<strong>${pageModel.totalPage}</strong>页 <input type="hidden" value="${pageModel.lastPage}" id="totalpage" />&nbsp;
                 当前页:第<strong>${pageModel.nextPage-1}</strong>页 <input type="hidden" value="${pageModel.currpage}" id="currpage" />&nbsp;
		   <a href="${pageContext.request.contextPath}/findAllUserCard?currpage=${pageModel.firstPage}" target="_self"> 首页</a> 	   
		   <a href="${pageContext.request.contextPath}/findAllUserCard?currpage=${pageModel.previousPage}"  target="_self">上一页 </a> 
		   <a href="${pageContext.request.contextPath}/findAllUserCard?currpage=${pageModel.nextPage}"  target="_self">下一页 </a> 
		   <a href="${pageContext.request.contextPath}/findAllUserCard?currpage=${pageModel.lastPage}" target="_self">末页 </a>&nbsp;
		   <!-- 跳转到选择/输入的页面 -->
		   第 <select class="chzn-select" onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
		   <option value="${pageContext.request.contextPath}/findAllUserCard?currpage=${page}">${page}</option>
		   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
           <c:if test="${j.index!=page}">
           <option value="${pageContext.request.contextPath}/findAllUserCard?currpage=${j.index}">${j.index}</option>
           </c:if>
           </c:forEach>
           </select> 页
    </td>
</tr>
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

