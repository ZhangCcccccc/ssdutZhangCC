<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <title>综合报表</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <meta name="decorator" content="iframe"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/system_report/css/sci_reports.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/system_report/js/jquery-1.11.0.min.js"></script>
        <script data-require-id="sakura" src="${pageContext.request.contextPath}/system_report/js/sakura.js" async=""></script>
        <!-- ECharts单文件引入 -->
        <script src="${pageContext.request.contextPath}/system_report/js/echarts.js"></script>
        <script src="${pageContext.request.contextPath}/system_report/js/line.js"></script>
        <script src="${pageContext.request.contextPath}/system_report/js/bar.js"></script>
        <script src="${pageContext.request.contextPath}/system_report/js/pie.js"></script>
        
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/jquery-1.8.2.min.js"></script>
	<!-- 下拉框的样式 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
	<!-- 下拉的样式结束 -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/timetable/lmsReg.css">	
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
                    ${xAxisc}
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
                data: [${ratec}]   
            }]
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

// 取消查询
function cancel(){
	location.href = '${pageContext.request.contextPath}/report/comprehensiveReport?termBack=-1';
}

//查询
function searchtp()
{
	var centerNumbers = $("#centerNumber").val();
	if(centerNumbers != null)
	{
		document.queryForm.action="${pageContext.request.contextPath}/report/comprehensiveReport?termBack=-1";
		document.queryForm.submit();
	}
	else
	{
		alert("请选择实验中心！");
	}
}

// 取消查询
function canceltp(){
	location.href = '${pageContext.request.contextPath}/report/comprehensiveReport?termBack=-1';
}
    </script>

<style   media=print>     
  .Noprint{display:none;}     
  .PageNext{page-break-after:always;}   

    
</style>     
    </head>
    <body>   
   <script src="${pageContext.request.contextPath}/js/highcharts/highcharts.js"></script>
   <script src="${pageContext.request.contextPath}/js/highcharts/grid.js"></script>
 

 
   
<!-- ...............大综合报表 ..........................-->
        <div class="lab-chart" style="margin-right:1%;margin-top:15px;width:48%;float:left;">
        	<div class="tool-box">
			<form:form name="queryForm" action="${pageContext.request.contextPath}/report/comprehensiveReport?termBack=-1" method="post">
			<ul>
				<li>学期：</li>
				<li style="width:40%;">
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
			</ul>
			</form:form>
			</div>
            <h1 style="text-align:center;font-size:16px;font-weight:bold;padding:10px 0;">大综合报表</h1>
            <div id="lar-statement" style="height:420px;font-size:12px;"></div>
        </div>
        <script type="text/javascript">
            require(
                [
                    'echarts',
                    'echarts/chart/bar', 
                   
                    'echarts/chart/line', 
                    'echarts/chart/pie' 
                   
                ],
                function(ec) {
                  
                    var myChart = ec.init(document.getElementById('lar-statement'));

                    var option1 = {

                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            
                        type : 'shadow'       
                    }
                },
                legend: {
                    //data:['开设课程总数','开设项目总数','实验学时总和','教师总人数','学生总人数','学生批次总数']
                     data:['开设课程总数','开设项目总数','教师总人数','学生总人数','学生批次总数']
                },
                toolbox: {
                    show : true,
                    orient: 'vertical',
                    x: 'right',
                    y: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar', 'tiled']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : {
                            show : true,
                            type : 'category',
                            data :  [ ${str4}],
                           axisLabel : {
                                interval : 0,
                                formatter : function(params){
                                    var newParamsName = "";
                                    var paramsNameNumber = params.length;
                                    var provideNumber = 6;
                                    var rowNumber = Math.ceil(paramsNameNumber / provideNumber);
                                    if (paramsNameNumber > provideNumber) {
                                        for (var p = 0; p < rowNumber; p++) {
                                            var tempStr = "";
                                            var start = p * provideNumber;
                                            var end = start + provideNumber;
                                            if (p == rowNumber - 1) {
                                                tempStr = params.substring(start, paramsNameNumber);
                                            } else {
                                                tempStr = params.substring(start, end) + "\n";
                                            }
                                            newParamsName += tempStr;
                                        }

                                    } else {
                                        newParamsName = params;
                                    }
                                    return newParamsName
                                }

                            } 
                        },
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
               series : [
                    {
                        name:'开设课程总数',
                        type:'bar',
                       
                       // data:[9, 4, 20, 8]
                        data:[${s1}]
                    }, 
                    {
                        name:'开设项目总数',
                        type:'bar',
                       
                      //  data:[165, 97, 0, 170]
                        data:[${s2}]
                    },
                  /*   {
                        name:'实验学时总和',
                        type:'bar',
                       
                      //  data:[898,0,0,0]
                        data:[${s3}]
                    }, */
                    {
                        name:'教师总人数',
                        type:'bar',
                       
                    //    data:[17, 7, 20, 1]
                        data:[${s4}]
                    },
                    {
                        name:'学生总人数',
                        type:'bar',
                      
                     //   data:[1491, 891, 1956, 2584]
                        data:[${s5}]
                    },
                    {
                        name:'学生批次总数',
                        type:'bar',
                       
                       // data:[611, 65, 3321,20]
                        data:[${s6}]
                    },
                   
                ]
            };
                            myChart.setOption(option1,true);
                        }
                    );
        </script>
<!-- ...............大综合报表结束 ..........................-->

<!-- ...............各中心实验室利用率 ..........................-->
           <!--  <p>各中心实验室利用</p> -->
<div class="Noprint lab-chart" style="width:48%;float:left;margin-top:15px;">
	<div class="tool-box" style="margin-bottom:20px;">
	<form:form name="queryForm" action="${pageContext.request.contextPath}/report/comprehensiveReport?termBack=-1" method="post">
	<ul>
		<li>学期：</li>
		<li style="width:40%;">
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
	</ul>
	</form:form>
	</div>
	<div id="container" style="min-width: 310px; height: 420px; margin: 0 auto" class="Noprint"></div>

</div>

        
<!-- ..............各中心实验室利用率结束 ........................-->

<!-- ...............实验室数量统计 ..........................-->     
        <div class="lab-chart" style="margin:20px 0 0;width:97%;clear:both;height:420px;">
            <p>实验室数量统计</p>
            <div id="lab-quantity" style="height:420px;font-size:12px;"></div>
        </div>
        
        <script type="text/javascript">
            require(
                [
                    'echarts',
                    'echarts/chart/bar', 
                  
                    'echarts/chart/line', 
                    'echarts/chart/pie' 
                   
                ],
                function(ec) {
                    var myChart = ec.init(document.getElementById('lab-quantity'));
                    var option = {
                            title: {
                                text: '',
                                subtext: '',
                                left: 'center'
                            },
                            tooltip : {
                                 trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                /*  orient: 'vertical',
                                 top: 'middle', */
                                bottom: 10,
                                left: 'center',                      
                                 data: [${str1}]
                                // data:[]
                            },
                            formatter:function(val){return val.split("-").join("\n");},                            
                           series : [
	                                {
	                                    type: 'pie',
	                                    radius : '55%',
	                                    center: ['55%', '45%'],
	                                    selectedMode: 'single',
	                                    data:[${str}],
	                                  // data:[],
	                                    itemStyle: {
	                                        emphasis: {
	                                            shadowBlur: 10,
	                                            shadowOffsetX: 0,
	                                            shadowColor: 'rgba(0, 0, 0, 0.5)'
	                                        },
	                                        normal:{ 
	                                              label:{ 
	                                                show: true, 
	                                                formatter: '{b} : {c} ({d}%)' 
	                                              }, 
	                                              labelLine :{show:true} 
	                                            } 
	                                    }
	                                }
	                            ]
                        };

                    // 为echarts对象加载数据
                    myChart.setOption(option,true);
                }
            );
        </script>
<!-- ...............实验室数量统计结束 ..........................-->



<!-- ...............实验室教师人数 ..........................-->
        <div class="lab-chart" style="margin-right:1%;margin-top:15px;width:97%;height:450px;">  
        <div class="Noprint">
			<div class="tool-box">
			 
			<form:form name="queryForm" action="${pageContext.request.contextPath}/report/comprehensiveReport?termBack=-1" method="post">
			 <ul>
				<li>实验中心：</li>
				 <li style="width:20%;">
				<select id="centerNumber" name="centerNumber" class="chzn-select" style="width:200px;">
				<option value="">所有实验中心</option>
				<c:forEach items="${centersp}" var="curr">
					<c:if test="${curr.centerNumber == centerNumber}">
						<option value="${curr.centerNumber }" selected="selected">${curr.centerName }</option>
					</c:if>
					<c:if test="${curr.centerNumber != centerNumber}">
						<option value="${curr.centerNumber }">${curr.centerName }</option>
					</c:if>
				</c:forEach>
				</select>
				</li>
				<li><input type="submit" value="查询"/></li>
				<li><input type="button" onclick="canceltp()" value="取消查询"/></li>
			</ul>
			</form:form>
			</div>
			</div>        
            <p>实验室教师人数</p>
            <div id="basic-information" style="height:420px;font-size:12px;"></div>
        </div>
        <script type="text/javascript">
            require(
                [
                    'echarts',
                    'echarts/chart/bar',                    
                    'echarts/chart/line', 
                    'echarts/chart/pie'                  
                ],
                function(ec) {
                    var myChart = ec.init(document.getElementById('basic-information'));
                    var option4 = {
                            title: {
                                text: '',
                                subtext: '',
                                left: 'center'
                            },
                            tooltip : {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                // orient: 'vertical',
                                // top: 'middle',
                                bottom: 10,
                                left: 'center',
                                data: ['专职实验教师', '兼职实验教师']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '55%',
                                    center: ['50%', '45%'],
                                    selectedMode: 'single',
                                    data:[${stp}],
                                    itemStyle: {
                                        emphasis: {
                                            shadowBlur: 10,
                                            shadowOffsetX: 0,
                                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                                        },
                                        normal:{ 
                                              label:{ 
                                                show: true, 
                                                formatter: '{b} : {c} ({d}%)' 
                                              }, 
                                              labelLine :{show:true} 
                                            } 
                                    }
                                }
                            ]
                        };
                    // 为echarts对象加载数据
                    myChart.setOption(option4,true);
                }
            );
        </script>
<!-- ...............实验室教师人数结束 ..........................-->
        <!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
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

        <!-- 空白部分，不需要判断 -->
        <div class="none_blank" style="width:100%;height:100px;clear:both;"></div>
    </body>

</html>