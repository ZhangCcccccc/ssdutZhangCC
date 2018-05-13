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
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/timetable/lmsReg.css">	
	<c:set var="rate" scope="session" value="" />
	<c:forEach items="${labRates}" var="curr" varStatus="i">
	    <c:choose>
	    <c:when test="${i.first}"><c:set var="rate" scope="session" value="${curr[5]}" /></c:when> 
	    <c:otherwise><c:set var="rate" scope="session" value="${rate},${curr[5]}" /></c:otherwise> 
	    </c:choose>
    </c:forEach>
	<script type="text/javascript">
$(function () {
        $('#container').highcharts({
            chart: {
                type: 'column'  //指定图表的类型，默认是折线图（line）
            },
            title: {  
                text: '实验室使用率报表' //大标题 
            },
            subtitle: {  
                text: ''  //副标题
            },
            xAxis: {  //横轴
                categories: [
                    ${xAxis}
                ]
            },
            yAxis: {
                min: 0,  //坐标轴的最小值
                title: {
                    text: '百分比(%)'  //指定y轴的标题
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.2f}%</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            credits:{
                enabled:false // 禁用版权信息（右下角的水印）
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2, 
                    borderWidth: 0
                }
            },
            series: [{  //加载的数据
                name: '实验室使用率',
                data: [${rate}]
    
            }],
            
            exporting: {  
            enabled: true  //是否支持导出图片
        }
        });
    });

//根据学年获得学期
function termChange()
{
	var term = $("#term");
	term.removeClass();
	term.addClass("chzn-select");
	var yearCode = $("#year_code").val();
	
	$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/report/getTermsByYearCode",
			data: {yearCode:yearCode},
			success: function(data){
					$("#term").html(data.terms);
					$("#term").trigger("liszt:updated");
					
			}
	});
}
//查询
function search()
{
	var terms = $("#term").val();
	if(terms != null)
	{
		document.queryForm.action="${pageContext.request.contextPath}/report/reportLargeDeviceUsedRate";
		document.queryForm.submit();
	}
	else
	{
		alert("请选择学期！");
	}
}


// 弹出实验室利用率
function showLabRate(centerId){
	location.href = '${pageContext.request.contextPath}/dataReport/reportLabRateRoom?centerId='+centerId+'&termId='+${selectedTermId};
}

// 取消查询
function cancel(){
	location.href = '${pageContext.request.contextPath}/dataReport/reportLabRate?termBack=-1';
}

// 导出查询
function searchForExport(){
	queryForm.action = '${pageContext.request.contextPath}/dataReport/exportReportLabRate';
	queryForm.submit();
}
</script>

<style   media=print>     
  .Noprint{display:none;}     
  .PageNext{page-break-after:   always;}     
</style>

</head>
<body>
<script src="${pageContext.request.contextPath}/js/highcharts/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts/exporting.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts/grid.js"></script>

<div class="title">
  <div id="title">报表统计--实验室使用率报表</div>
</div> 
<div class="Noprint">
<div class="tool-box">
<form:form name="queryForm" action="${pageContext.request.contextPath}/dataReport/reportLabRate?termBack=-1" method="post">
<ul>
	<li>学期：</li>
	<li>
	<select id="term" name="term" class="chzn-select" style="width:400px;">
	<c:forEach items="${selectTerms}" var="curr">
		<c:if test="${curr.id eq selectedTermId}">
			<option value="${curr.id }" selected="selected">${curr.termName }</option>
		</c:if>
		<c:if test="${curr.id ne selectedTermId}">
			<option value="${curr.id }">${curr.termName }</option>
		</c:if>
	</c:forEach>
	</select>
	</li>
	<li><input type="submit" value="查询"/></li>
	<li><input type="button" onclick="cancel()" value="取消查询"/></li>
	<li><input type="button" onclick="searchForExport();" value="导出"/></li>
</ul>
</form:form>
</div>
</div>

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto" class="Noprint"></div>
<br />
<div style="text-align:center;"><b><!-- 实验室利用率=实验室实际人时数 / 实验室额定人时数 -->实验室人时数</b></div>
<br />
<div class="content-box">
<table class="tb">
<thead>
  <tr>
    <th>中心名称</th>
    <th>本中心实验室学时数（实际）</th>
    <!-- <th>本中心实验室学时数（额定）</th>
    <th>实验室利用率（%）</th> -->
  </tr>
</thead>
<tbody>
  <c:forEach items="${labRates}" var="current"  varStatus="i">
  <tr>
    <td>${current[1]}</td>
    <td><a onclick="showLabRate(${current[0]})">${current[3]}</a></td>
    <%-- <td>${current[4]}</td>
    <td><a onclick="showLabRate(${current[0]})">${current[5]}</a></td> --%>
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