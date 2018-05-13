<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta name="decorator" content="iframe"/>
	<title>学院绩效报表</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/jquery-1.8.2.min.js"></script>
	<!-- 下拉框的样式 -->
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/style.css" /> --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
	<!-- 下拉的样式结束 -->
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	
	<script type="text/javascript">
    $(function () {
        $('#container').highcharts({
            chart: {
                type: 'column'  //指定图表的类型，默认是折线图（line）
            },
            title: {  
                text: '学院报表' //大标题 
            },
            subtitle: {  
                text: ''  //副标题
            },
            xAxis: {  //横轴
                categories: [
               '实验室利用率',
               '大型设备平均利用率',
               '大型实验设备仪器台数使用率',
               '实验室专职人员平均接待师生人时数',
               '实验项目开出率',
               '教师参加实验指导比例',
               '独立实验课、大型综合性实验课比例',
               '人才培养效率'    
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
                name: '${academy.academyName}',
                data: [${academyRate.labRate},${academyRate.largeDeviceTimeRate},${academyRate.largeDeviceUsedRate},${academyRate.labAdminRate},${academyRate.itemsRate},${academyRate.teacherItemRate},${academyRate.complexItemRate},${academyRate.studentTrainRate}]
            }],
            
            exporting: {  
            enabled: false  //是否支持导出图片
        }
        });
    });
    
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
	function search()
	{
		var terms = $("#term").val();
		if(terms != null)
		{
			document.queryForm.action="${pageContext.request.contextPath}/report/reportAcademyMain?academyNumber=${academy.academyNumber}";
			document.queryForm.submit();
		}
		else
		{
			alert("请选择学期！");
		}
	}
	</script>
</head>

<body>
<script src="${pageContext.request.contextPath}/js/highcharts/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts/exporting.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts/grid.js"></script>

<div class="title">
  <div id="title">报表统计--${academy.academyName}</div>
</div> 
<div class="tool-box">
				<form:form name="queryForm" method="post" modelAttribute="labRoom">
					<ul>
    				<li>学年： </li>
    				<li>
    				<form:select id="year_code" path="labRoomNumber" onchange="termChange();">
	 				<form:options items="${termsMap}" />
	 				</form:select>
    				</li>
    				<li>学期：</li>
	 				<li>
	 				<form:select id="term" path="labRoomName" class="chzn-select" data-placeholder="请选择学期" multiple="true" style="width:400px;">
	 				<form:options items="${selectTerms}" selected="true"/>
	 				<form:options items="${otherTerms}"/>
	 				</form:select>
	 				</li>
    				<li><input type="button" value="查询" onclick="search();" /></li>
    				</ul>
				</form:form>
		       </div>

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<br />
<div class="content-box">
<style>
	.tb td,th{
	text-indent:3.5em;
	}
</style>
<table class="tb">
<thead>
  <tr>
    <th width="40%">序号</th>
    <th width="40%">指标名称</th>
    <th width="20%">指标数值</th>
  </tr>
</thead>
<tbody>
    <tr>
      <td align-text="center;">1</td>  
      <td>实验室利用率</td>  
      <td><a href="${pageContext.request.contextPath}/report/reportAcademyLabRate?academyNumber=${academy.academyNumber}&terms=${labRoom.labRoomName}">${academyRate.labRate}%</a></td>  
    </tr>
    <tr>
      <td>2</td>  
      <td>大型设备平均利用率</td>  
      <td><a href="${pageContext.request.contextPath}/report/reportAcademyLargeDeviceTimeRate?academyNumber=${academy.academyNumber}&terms=${labRoom.labRoomName}">${academyRate.largeDeviceTimeRate}%</a></td>  
    </tr>
    <tr>
      <td>3</td>  
      <td>大型实验设备仪器台数使用率</td>  
      <td><a href="${pageContext.request.contextPath}/report/reportAcademyLargeDeviceUsedRate?academyNumber=${academy.academyNumber}&terms=${labRoom.labRoomName}">${academyRate.largeDeviceUsedRate}%</a></td>  
    </tr>
    <tr>
      <td>4</td>  
      <td>实验室专职人员平均接待师生人时数</td>  
      <td><a href="${pageContext.request.contextPath}/report/reportAcademyLabAdminRate?academyNumber=${academy.academyNumber}&terms=${labRoom.labRoomName}">${academyRate.labAdminRate}</a></td>  
    </tr>
    <tr>
      <td>5</td>  
      <td>实验项目开出率</td>  
      <td><a href="${pageContext.request.contextPath}/report/reportAcademyItemsRate?academyNumber=${academy.academyNumber}&terms=${labRoom.labRoomName}">${academyRate.itemsRate}%</a></td>  
    </tr>
    <tr>
      <td>6</td>  
      <td>教师参加实验指导比例</td>  
      <td><a href="${pageContext.request.contextPath}/report/reportAcademyTeacherItemRate?academyNumber=${academy.academyNumber}&terms=${labRoom.labRoomName}">${academyRate.teacherItemRate}%</a></td>  
    </tr>
    <tr>
      <td>7</td>  
      <td>独立实验课、大型综合性实验课比例</td>  
      <td><a href="${pageContext.request.contextPath}/report/reportAcademyComplexItemRate?academyNumber=${academy.academyNumber}&terms=${labRoom.labRoomName}">${academyRate.complexItemRate}%</a></td>  
    </tr>
    <tr>
      <td>8</td>  
      <td>人才培养率</td>  
      <td><a href="${pageContext.request.contextPath}/report/reportAcademyStudentTrainRate?academyNumber=${academy.academyNumber}&terms=${labRoom.labRoomName}">${academyRate.studentTrainRate}%</a></td>  
    </tr>
    <tr>
      <td>9</td>  
      <td>综合指数</td>  
      <td>${academyRate.score}</td>  
    </tr>
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