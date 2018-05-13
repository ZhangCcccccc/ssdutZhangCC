<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta name="decorator" content="iframe"/>
	<title>绩效报表</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/jquery-1.8.2.min.js"></script>
	<!-- 下拉框的样式 -->
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/style.css" /> --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
	<!-- 下拉的样式结束 -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

	<script type="text/javascript">
	function back(){
		location.href = '${pageContext.request.contextPath}/dataReport/reportLabRate?termBack='+${termId};
	}
	
	</script>
	
	<style media=print>     
	  .Noprint{display:none;}     
	  .PageNext{page-break-after:always;}     
	</style>
    
</head>
<body>
<script src="${pageContext.request.contextPath}/js/highcharts/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts/exporting.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts/grid.js"></script>

<div class="title">
  <div id="title">报表统计--实验室使用率报表</div>
  <div class="Noprint">
  	<a class="btn btn-new" onclick="back()">返回</a>
  	<a class="btn btn-new" onclick="javascript:window.print();">打印</a>
  	<a class="btn btn-new" href="${pageContext.request.contextPath}/dataReport/exportReportLabRateRoom?centerId=${centerId}&termId=${termId}">导出</a>
  </div>
</div> 
<br />
<div style="text-align:center;"><b><!-- 实验室利用率=实验室实际人时数 / 实验室额定人时数 --></b></div>
<br />
<div class="content-box">
<table class="tb">
<thead>
  <tr>
    <th style="width:150px;">实验室名称</th>
    <th>实验室学时数（实际）</th>
    <th>课外人时数</th>
    <th>课内人时数</th>
    <th>面积</th>
    <th>设备金额</th>
    <!-- <th>实验室学时数（额定）</th>
    <th>实验室利用率（%）</th> -->
  </tr>
</thead>
<tbody>
  <c:forEach items="${labRates}" var="current"  varStatus="i">
  <tr>
    <td>${current[2]}</td>
    <td>${current[4]}</td>
    <td>${current[11]}</td>
    <td>${current[10]}</td>
    <td>${current[9]}</td>
    <td>${current[8]}</td>
   <%--  <td>${current[5]}</td>
    <td>${current[6]}</td> --%>
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
      '.chzn-select'           : {},
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