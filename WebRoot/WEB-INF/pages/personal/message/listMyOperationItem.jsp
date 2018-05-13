<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<meta name="decorator" content="iframe"/>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
   <!--分页js  -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/operation/operationoutline.js"></script>
   <!--分页js  -->
 <!-- 打印插件的引用 -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
  <script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#list_top").jqprint();
	})
	});
</script>
</head>

<body>
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">个人中心</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/timetable/MyOperationItem?page=1">我的实验项目</a></li>
</ul>
</div>
</div>


<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
<form:form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/selectMyOperationItem?page=1" modelAttribute="operationItem">
	<ul>
	    <li> 实验室：
	    <form:select class="chzn-select"  path="id" id="id" cssStyle="width:600px" >
	    <c:forEach items="${operationItem1}" var="current"  varStatus="i">	
	       <form:option value ="${current.id}" label="${current.lpName}" />  
	    </c:forEach>
        </form:select>
        </li>
		<li><input type="submit" value="查询" onclick="exportAll('${pageContext.request.contextPath}/timetable/selectMyOperationItem?page=1');"></li>
		<li><input type="button" value="导出" onclick="exportAll('${pageContext.request.contextPath}/timetable/exportMyOperationItem?page=${page}');"></li>
		<li><input type="button" value="打印" id="print"></li>
	</ul>
</form:form>
</div>
<div class="content-box">
<div class="title">我的实验室项目</div>
<table>
						<thead>
							<tr>
							<th class="thead" width="5%" >序号</th>	
							<th class="thead" width="5%">实验室编号</th>
							<th class="thead" width="5%">实验室名称</th>
							<th class="thead" width="10%">所属课程</th>
							<th class="thead" width="35%">创建时间</th>
							</tr>
						</thead>
					<tbody>

				<c:forEach items="${operationItem2}" var="s" varStatus="i">
				<tr  align="left">
				    <td>${i.index+1 }</td>
				    <td>${s.itemNumber}</td>
				    <td>${s.lpName }</td>
				    <td>${s.schoolCourseByClassNo.courseNo}</td>
				            	<td >
 <fmt:formatDate dateStyle="default" type="both" value="${s.createTime.time}"/>
        	</td>
</tr>
					</c:forEach>
					</tbody>						
					</table>
<!-- 分页导航 -->
<tr> 
    <td colspan="11" align="center" >
                 总页数: ${pageModel.totalPage}页 <input type="hidden" value="${pageModel.lastPage}"  />&nbsp;
                 当前页:第 ${pageModel.nextPage}页 <input type="hidden" value="${pageModel.currpage}" />&nbsp;
		   <a href="${pageContext.request.contextPath}/timetable/MyOperationItem?page=${pageModel.firstPage}" target="_self"> 首页</a> 		   
		   <a href="${pageContext.request.contextPath}/timetable/MyOperationItem?page=${pageModel.previousPage}"  target="_self">上一页 </a> 
		   <a href="${pageContext.request.contextPath}/timetable/MyOperationItem?page=${pageModel.nextPage}"  target="_self">下一页 </a> 
		   <a href="${pageContext.request.contextPath}/timetable/MyOperationItem?page=${pageModel.lastPage}" target="_self">末页 </a>
    </td>
</tr>			
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
</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>

