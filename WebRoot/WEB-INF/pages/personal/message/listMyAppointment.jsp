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
	<li class="end"><a href="${pageContext.request.contextPath}/timetable/MyAppointment?page=1">我的实验室预约</a></li>
</ul>
</div>
</div>


<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
<form:form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/selectMyAppointment?page=1" modelAttribute="labReservation">
	<ul>
	    <li> 实验室：
	    <form:select class="chzn-select"  path="id" id="id" cssStyle="width:600px" >
	    <c:forEach items="${labReservation1}" var="current"  varStatus="i">	
	       <form:option value ="${current.id}" label="${current.CLabReservationType.name};${current.user.cname}" />  
	    </c:forEach>
        </form:select>
        </li>
		<li><input type="button" value="查询" onclick="exportAll('${pageContext.request.contextPath}/timetable/selectMyAppointment?page=${page}');"></li>
		<li><input type="button" value="导出" onclick="exportAll('${pageContext.request.contextPath}/timetable/exportMyAppointment?page=${page}');"></li>
		<li><input type="button" value="打印" id="print"></li>
	</ul>
</form:form>
</div>
<div class="content-box">
<div class="title">我的实验室预约</div>
<table>
<thead>
 <tr><th>预约性质</th>
    <th >活动名称</th>
   <th >周次</th>
   <th >星期</th>
   <th >节次</th>
   <th >预约实验室</th>
   <th >内容</th>
<!--     <th >学生名单</th> -->
   <th >审批状态</th>
<!--    <center><th colspan="2" >操作</th></center>   -->
</tr>
</thead>

<tbody >
<c:forEach items="${labReservation2}" var="s">
<tr align="center">
     <td >${s.nametype}</td>
       <td >${s.name}</td>
     <td>第[<c:forEach items="${s.week }"  var="a" ><c:if test="${a!=null}">${a }&nbsp;</c:if></c:forEach>]周 </td>
      <td>星期[<c:forEach items="${s.day}"  var="d" ><c:if test="${d!=null}">${d }&nbsp;</c:if></c:forEach>]</td> 
    <td> 第[ <c:forEach items="${s.time}"  var="f" ><c:if test="${f!=null}"> ${f}&nbsp;</c:if></c:forEach>]节</td>
     <td>${s.lab}</td>
     <td>${s.start}</td>
<%--      <td><a href="${pageContext.request.contextPath}/lab/findstudentforlabreation?idkey=${s.id}&currpage=1">查看学生名单</a></td> --%>
     <td><c:if test="${s.cont==1}">审核通过</c:if>
         <c:if test="${s.cont==2}">审核中</c:if>
         <c:if test="${s.cont==4}">审核拒绝</c:if>
         <c:if test="${s.cont==3}">未审核</c:if> </td>
         
<%--      <td>
     <c:if test="${s.cont==1}">
       <c:if test="${s.release==1 }">已发布</c:if>
        <c:if test="${s.release==null }"> <a href="${pageContext.request.contextPath}/lab/publishedcourses?idkey=${s.id}&tage=${tage}">发布</a></c:if>
        </c:if>
     <c:if test="${s.cont==2}"><a href="${pageContext.request.contextPath}/lab/checkButton?idkey=${s.id}&tage=${tage}">查看</a></c:if>
     <c:if test="${s.cont==3}"><a href="${pageContext.request.contextPath}/lab/checkButton?idkey=${s.id}&tage=${tage}">查看</a></c:if>
     <c:if test="${s.cont==4}"><a href="${pageContext.request.contextPath}/lab/checkButton?idkey=${s.id}&tage=${tage}">审核</a></c:if> 
     </td> --%>
<%--      <td><a href="javascript:void(0)" onclick="zln();"><img src="${pageContext.request.contextPath}/images/01.jpg" width="20px" height="20px"></a>
         <img src="${pageContext.request.contextPath}/images/03.png" width="20px" height="20px"></a>
     </td> --%>
</tr>
</c:forEach>
</tbody>
<!-- 分页导航 -->
<tr> 
    <td colspan="11" align="center" >
                 总页数: ${pageModel.totalPage}页 <input type="hidden" value="${pageModel.lastPage}"  />&nbsp;
                 当前页:第 ${pageModel.currPage}页 <input type="hidden" value="${pageModel.currpage}" />&nbsp;
		   <a href="${pageContext.request.contextPath}/timetable/MyAppointment?page=${pageModel.firstPage}" target="_self"> 首页</a> 		   
		   <a href="${pageContext.request.contextPath}/timetable/MyAppointment?page=${pageModel.previousPage}"  target="_self">上一页 </a> 
		   <a href="${pageContext.request.contextPath}/timetable/MyAppointment?page=${pageModel.nextPage}"  target="_self">下一页 </a> 
		   <a href="${pageContext.request.contextPath}/timetable/MyAppointment?page=${pageModel.lastPage}" target="_self">末页 </a>
    </td>
</tr>			
</table>
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

