<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta name="decorator" content="iframe"/>
	<title>绩效报表</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.tablesorter.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() 
	    { 
	        $("table").tablesorter({
	        sortList:[[2,0]],  //初始化按照第三列升序
	        //以下列取消排序
	        headers:{0:{sorter:false},3:{sorter:false},4:{sorter:false},5:{sorter:false},6:{sorter:false},7:{sorter:false},8:{sorter:false},9:{sorter:false},10:{sorter:false}}
	        }); 
	    } 
    );
	</script>
	
	<!-- 下拉框的样式 -->
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/style.css" /> --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
	<!-- 下拉的样式结束 -->
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<c:set var="labRate" scope="session" value="" />
	<c:set var="largeDeviceTimeRate" scope="session" value="" />
	<c:set var="largeDeviceUsedRate" scope="session" value="" />
	<c:set var="labAdminRate" scope="session" value="" />
	<c:set var="itemsRate" scope="session" value="" />
	<c:set var="teacherItemRate" scope="session" value="" />
	<c:set var="complexItemRate" scope="session" value="" />
	<c:set var="studentTrainRate" scope="session" value="" />
    <c:forEach items="${rates}" var="curr" varStatus="i">
	    <c:choose>
	    <c:when test="${i.first}"><c:set var="labRate" scope="session" value="${curr.labRate}" /></c:when> 
	    <c:otherwise><c:set var="labRate" scope="session" value="${labRate},${curr.labRate}" /></c:otherwise> 
	    </c:choose>
    </c:forEach>
    <c:forEach items="${rates}" var="curr" varStatus="i">
	    <c:choose>
	    <c:when test="${i.first}"><c:set var="largeDeviceTimeRate" scope="session" value="${curr.largeDeviceTimeRate}" /></c:when> 
	    <c:otherwise><c:set var="largeDeviceTimeRate" scope="session" value="${largeDeviceTimeRate},${curr.largeDeviceTimeRate}" /></c:otherwise> 
	    </c:choose>
    </c:forEach>
    <c:forEach items="${rates}" var="curr" varStatus="i">
	    <c:choose>
	    <c:when test="${i.first}"><c:set var="largeDeviceUsedRate" scope="session" value="${curr.largeDeviceUsedRate}" /></c:when> 
	    <c:otherwise><c:set var="largeDeviceUsedRate" scope="session" value="${largeDeviceUsedRate},${curr.largeDeviceUsedRate}" /></c:otherwise> 
	    </c:choose>
    </c:forEach>
    <c:forEach items="${rates}" var="curr" varStatus="i">
	    <c:choose>
	    <c:when test="${i.first}"><c:set var="labAdminRate" scope="session" value="${curr.labAdminRate}" /></c:when> 
	    <c:otherwise><c:set var="labAdminRate" scope="session" value="${labAdminRate},${curr.labAdminRate}" /></c:otherwise> 
	    </c:choose>
    </c:forEach>
    <c:forEach items="${rates}" var="curr" varStatus="i">
	    <c:choose>
	    <c:when test="${i.first}"><c:set var="itemsRate" scope="session" value="${curr.itemsRate}" /></c:when> 
	    <c:otherwise><c:set var="itemsRate" scope="session" value="${itemsRate},${curr.itemsRate}" /></c:otherwise> 
	    </c:choose>
    </c:forEach>
    <c:forEach items="${rates}" var="curr" varStatus="i">
	    <c:choose>
	    <c:when test="${i.first}"><c:set var="teacherItemRate" scope="session" value="${curr.teacherItemRate}" /></c:when> 
	    <c:otherwise><c:set var="teacherItemRate" scope="session" value="${teacherItemRate},${curr.teacherItemRate}" /></c:otherwise> 
	    </c:choose>
    </c:forEach>
    <c:forEach items="${rates}" var="curr" varStatus="i">
	    <c:choose>
	    <c:when test="${i.first}"><c:set var="complexItemRate" scope="session" value="${curr.complexItemRate}" /></c:when> 
	    <c:otherwise><c:set var="complexItemRate" scope="session" value="${complexItemRate},${curr.complexItemRate}" /></c:otherwise> 
	    </c:choose>
    </c:forEach>
    <c:forEach items="${rates}" var="curr" varStatus="i">
	    <c:choose>
	    <c:when test="${i.first}"><c:set var="studentTrainRate" scope="session" value="${curr.studentTrainRate}" /></c:when> 
	    <c:otherwise><c:set var="studentTrainRate" scope="session" value="${studentTrainRate},${curr.studentTrainRate}" /></c:otherwise> 
	    </c:choose>
    </c:forEach>
	<script type="text/javascript">
$(function () {
        $('#container').highcharts({
            chart: {
                type: 'column'  //指定图表的类型，默认是折线图（line）
            },
            title: {  
                text: '综合报表' //大标题 
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
                name: '实验室利用率',
                data: [${labRate}]
            },{  //加载的数据
                name: '大型设备平均利用率',
                data: [${largeDeviceTimeRate}]
    
            },{  //加载的数据
                name: '大型实验设备仪器台数使用率',
                data: [${largeDeviceUsedRate}]
    
            },{  //加载的数据
                name: '实验室专职人员平均接待师生人时数',
                data: [${labAdminRate}]
    
            },{  //加载的数据
                name: '实验项目开出率',
                data: [${itemsRate}]
    
            },{  //加载的数据
                name: '教师参加实验指导比例',
                data: [${teacherItemRate}]
    
            },{
                name: '独立实验课、大型综合性实验课比例',
                data: [${complexItemRate}]
    
            },{
                name: '人才培养效率',
                data: [${studentTrainRate}]
    
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
		document.queryForm.action="${pageContext.request.contextPath}/report/reportMain";
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
  <div id="title">报表统计--综合报表</div>
  <sec:authorize ifAnyGranted="ROLE_EXPERIMENTALTEACHING,ROLE_SUPERADMIN">
  <a class="btn btn-new" href="${pageContext.request.contextPath}/report/computeReport" onclick="return confirm('计算需要一段时间，是否继续？');">报表计算</a>
  <a class="btn btn-new" href="${pageContext.request.contextPath}/report/reportParameterList">参数设置</a>
  </sec:authorize> 
  <%-- <a class="btn btn-new" target="_blank" href="${pageContext.request.contextPath}/report/reportAcademyMain?academyNumber=0205">学院报表</a> --%>
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
<table class="tb by_gvsun">
<style>
.by_gvsun td{
	border: 1px dotted #DBDBDB;
}
</style>
<thead>
  <tr>
    <th>学院名称</th>
    <th>综合指数</th>
    <th>绩效排名</th>
    <th><a href="${pageContext.request.contextPath}/report/reportLabRate" target="_blank">实验室利用率</a></th>
    <th><a href="${pageContext.request.contextPath}/report/reportLargeDeviceTimeRate" target="_blank">大型设备平均利用率</a></th>
    <th><a href="${pageContext.request.contextPath}/report/reportLargeDeviceUsedRate" target="_blank">大型实验设备仪器台数使用率</a></th>
    <th><a href="${pageContext.request.contextPath}/report/reportLabAdminRate" target="_blank">实验室专职人员平均接待师生人时数</a></th>
    <th><a href="${pageContext.request.contextPath}/report/reportItemsRate" target="_blank">实验项目开出率</a></th>
    <th><a href="${pageContext.request.contextPath}/report/reportTeacherItemRate" target="_blank">教师参加实验指导比例</a></th>
    <th><a href="${pageContext.request.contextPath}/report/reportComplexItemRate" target="_blank">独立实验课、大型综合性实验课比例</a></th>
    <th><a href="${pageContext.request.contextPath}/report/reportStudentTrainRate" target="_blank">人才培养效率</a></th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${rates}" var="curr">
    <tr>
      <td>${curr.schoolAcademy.academyName}</td>    <%-- 学院名称 --%>
      <td>${curr.score}</td>  <%-- 综合指数 --%>
      <td>${curr.rank}</td>  <%-- 绩效排名 --%>
      <td>${curr.labRate}%</td>   <%-- 实验室利用率 --%>
      <td>${curr.largeDeviceTimeRate}%</td>  <%-- 大型设备平均利用率 --%>
      <td>${curr.largeDeviceUsedRate}%</td>  <%-- 大型实验设备仪器台数使用率 --%>
      <td>${curr.labAdminRate}</td>  <%-- 实验室专职人员平均接待师生人时数 --%>
      <td>${curr.itemsRate}%</td>  <%-- 实验项目开出率 --%>
      <td>${curr.teacherItemRate}%</td>  <%-- 教师参加实验指导比例 --%>
      <td>${curr.complexItemRate}%</td>  <%-- 独立实验课、大型综合性实验课比例 --%>
      <td>${curr.studentTrainRate}%</td>  <%-- 人才培养效率 --%>
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