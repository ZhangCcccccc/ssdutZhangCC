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
		
	<c:set var="rate" scope="session" value="" />
	<c:forEach items="${studentTrainRates}" var="curr" varStatus="i">
	    <c:choose>
	    <c:when test="${i.first}"><c:set var="rate" scope="session" value="${curr.labRoomManagerAgencies}" /></c:when> 
	    <c:otherwise><c:set var="rate" scope="session" value="${rate},${curr.labRoomManagerAgencies}" /></c:otherwise> 
	    </c:choose>
    </c:forEach>
	
	<script type="text/javascript">
$(function () {
        $('#container').highcharts({
            chart: {
                type: 'column'  //指定图表的类型，默认是折线图（line）
            },
            title: {  
                text: '人才培养率 ' //大标题 
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
                    text: '百分比 (%)'  //指定y轴的标题
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.2f} %</b></td></tr>',
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
                name: '人才培养率',
                data: [${rate}]
    
            }],
            exporting: {  
            enabled: false  //是否支持导出图片
        }
        });
    });
	</script>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/highcharts/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts/exporting.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts/grid.js"></script>

<div class="title">
  <div id="title">报表统计--人才培养率 </div>
</div> 
<div class="tool-box">
<form:form id="queryForm" name="queryForm" method="post" modelAttribute="labRoom">
<ul>
	<li>学年： </li>
	<li>
	<form:select id="year_code" path="labRoomNumber" onchange="termChange();">
	<form:options items="${termsMap}" />
	</form:select>
	</li>
	<li>学期：</li>
	<li>
	<form:select id="term" path="labRoomName" class="chzn-select" multiple="true" style="width:400px;">
	<form:options items="${selectTerms}" selected="true"/>
	<form:options items="${otherTerms}"/>
	</form:select>
	</li>
	<li><input type="button" value="查询" onclick="search();"/></li>
</ul>
</form:form>
</div>

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<br />
<div style="text-align:center;"><b>额定人才培养量 = [实验室现有的设备经费额(10万元以下) + 10万元以上大型仪器设备投入经费折算值 + 实验室使用面积 × 0.5万 ]/(3 × 学科系数) </b></div>
<div style="text-align:center;"><b>人才培养效率 = (本科生+研究生)/额定人才培养量</b></div>
<br />
<div class="content-box">
<table class="tb">
<thead>
  <tr>
    <th>学院名称</th>
    <th>当前本科生数</th>
    <th>当前研究生数</th>
    <th>现有设备经费额（10万以下）</th>
    <th>现有设备经费额（10万以上）</th>
    <th>实验室使用面积</th>
    <th>学科系数</th>
    <th>人才培养效率</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${studentTrainRates}" var="curr">
    <tr>
      <td>${curr.labRoomName}</td> <%-- 学院名称 --%>
      <td>${curr.labRoomAddress}</td>
      <td>${curr.labRoomCapacity}</a></td>
      <td><a style="color:#0000ff;" href="${pageContext.request.contextPath}/report/reportDeviceAcademy?academyNumber=${curr.labRoomPhone}">${curr.labRoomNumber}</a></td>
      <td><a style="color:#0000ff;" href="${pageContext.request.contextPath}/report/reportLargeDeviceAcademy?academyNumber=${curr.labRoomPhone}">${curr.labRoomEnName}</a></td>
      <td><a style="color:#0000ff;" href="${pageContext.request.contextPath}/report/reportLabArea?academyNumber=${curr.labRoomPhone}">${curr.labRoonAbbreviation}</a></td>
      <td>${curr.labRoomSubject}</td>
      <td>${curr.labRoomManagerAgencies}%</td>
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