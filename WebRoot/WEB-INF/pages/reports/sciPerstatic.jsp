<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <title>系统报表</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <meta name="decorator" content="iframe"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/system_report/css/sci_reports_02.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/system_report/js/jquery-1.11.0.min.js"></script>
        <script data-require-id="sakura" src="${pageContext.request.contextPath}/system_report/js/sakura.js" async=""></script>
        <!-- ECharts单文件引入 -->
        <script src="${pageContext.request.contextPath}/system_report/js/echarts.js"></script>
        <script src="${pageContext.request.contextPath}/system_report/js/line.js"></script>
        <script src="${pageContext.request.contextPath}/system_report/js/bar.js"></script>
        <script src="${pageContext.request.contextPath}/system_report/js/pie.js"></script>
        
        <!-- 下拉的样式 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
        <!-- 下拉的样式 -->
        
         <script type="text/javascript">
//查询
function search()
{
	var centerNumbers = $("#centerNumber").val();
	if(centerNumbers != null)
	{
		document.queryForm.action="${pageContext.request.contextPath}/report/sciPerstatic";
		document.queryForm.submit();
	}
	else
	{
		alert("请选择实验中心");
	}
}

// 取消查询
function cancel(){
	location.href = '${pageContext.request.contextPath}/report/sciPerstatic';
}
</script>

    </head>
    <body>
<!-- ...........实验人员基本信息统计 ....................-->
<div class="Noprint">
<div class="tool-box">
<form:form name="queryForm" action="${pageContext.request.contextPath}/report/sciPerstatic" method="post">
 <ul style="width:80%;">
	<li>实验中心：</li>
	 <li style="width:15%;">
	<select id="centerNumber" name="centerNumber" class="chzn-select" style="width:200px;">
	<option value="" >所有实验中心</option>
	<c:forEach items="${centersa}" var="curr">
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
	<li><input type="button" onclick="cancel()" value="取消查询"/></li>
</ul>
</form:form>
</div>
</div>
        <p class="basic-message">实验人员基本信息统计</p>
        <div style="width:98%;height:100%;padding-bottom:50px;margin-left:1%;">
            <div class="lab-teacher" style="margin:0 1%;">
                <p>实验室教师学历结构</p>
                <div id="teacher-qualifications" style="height:420px;font-size:12px;"></div>
            </div>
            <div class="lab-teacher" style="margin:0 1%;">
                <p>专职实验教师学历结构</p>
                <div id="sci-teacher-qualifications" style="height:420px;font-size:12px;"></div>
            </div>
            <div class="lab-teacher" style="margin:20px 1% 0;">
                <p>实验教师年龄结构</p>
                <div id="teacher-age" style="height:420px;font-size:12px;"></div>
            </div>
            <div class="lab-teacher" style="margin:20px 1% 0;">
                <p>专职实验教师年龄结构</p>
                <div id="sci-teacher-age" style="height:420px;font-size:12px;"></div>
            </div>
            <div class="lab-teacher"  style="margin:20px 1% 0;">
                <p>实验教师职称结构</p>
                <div id="teacher-title" style="height:420px;font-size:12px;"></div>
            </div>
            <div class="lab-teacher" style="margin:20px 1% 0;">
                <p>专职实验教师职称结构</p>
                <div id="sci-teacher-title" style="height:420px;font-size:12px;"></div>
            </div>
        </div>

        <script type="text/javascript">
//  ..................实验室教师学历结构............... 
            require(
                [
                    'echarts',
                    'echarts/chart/bar', 
                    // 使用柱状图就加载bar模块，按需加载
                    'echarts/chart/line', 
                    'echarts/chart/pie' 
                    // 使用柱状图就加载bar模块，按需加载
                ],
                function(ec) {
                    var myChart = ec.init(document.getElementById('teacher-qualifications'));
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
                                // orient: 'vertical',
                                // top: 'middle',
                                bottom: 10,
                                left: 'center',
                                data: ['博士', '硕士','大学','大专']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '52%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[${ste}],
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
//  ..................专职实验教师学历结构............... 
            require(
                [
                    'echarts',
                    'echarts/chart/bar', 
                    // 使用柱状图就加载bar模块，按需加载
                    'echarts/chart/line', 
                    'echarts/chart/pie' 
                    // 使用柱状图就加载bar模块，按需加载
                ],
                function(ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('sci-teacher-qualifications'));

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
                                // orient: 'vertical',
                                // top: 'middle',
                                bottom: 10,
                                left: 'center',
                                data: ['博士', '硕士','大学','大专']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '52%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[${sste}],
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
//  ..................实验教师年龄结构............... 
            require(
                [
                    'echarts',
                    'echarts/chart/bar', 
                    // 使用柱状图就加载bar模块，按需加载
                    'echarts/chart/line', 
                    'echarts/chart/pie' 
                    // 使用柱状图就加载bar模块，按需加载
                ],
                function(ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('teacher-age'));
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
                                // orient: 'vertical',
                                // top: 'middle',
                                bottom: 10,
                                left: 'center',
                                data: ['35岁以下','36-50岁','51-60岁','60岁以上']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '52%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[${sta}],
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
//  ..................专职实验教师年龄结构............... 
            require(
                [
                    'echarts',
                    'echarts/chart/bar', 
                    // 使用柱状图就加载bar模块，按需加载
                    'echarts/chart/line', 
                    'echarts/chart/pie' 
                    // 使用柱状图就加载bar模块，按需加载
                ],
                function(ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('sci-teacher-age'));

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
                                // orient: 'vertical',
                                // top: 'middle',
                                bottom: 10,
                                left: 'center',
                                data: ['35岁以下', '36-50岁','51-60岁','60岁以上']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '52%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[${ssta}],
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
//  ..................实验教师职称结构............... 
            require(
                [
                    'echarts',
                    'echarts/chart/bar', 
                    // 使用柱状图就加载bar模块，按需加载
                    'echarts/chart/line', 
                    'echarts/chart/pie' 
                    // 使用柱状图就加载bar模块，按需加载
                ],
                function(ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('teacher-title'));

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
                                // orient: 'vertical',
                                // top: 'middle',
                                bottom: 10,
                                left: 'center',
                                data: ['教授', '副教授(高工)','讲师(工程师)','助工']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '52%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[${stl}],
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
//  ..................专职实验教师职称结构............... 
            require(
                [
                    'echarts',
                    'echarts/chart/bar', 
                    // 使用柱状图就加载bar模块，按需加载
                    'echarts/chart/line', 
                    'echarts/chart/pie' 
                    // 使用柱状图就加载bar模块，按需加载
                ],
                function(ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('sci-teacher-title'));

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
                                // orient: 'vertical',
                                // top: 'middle',
                                bottom: 10,
                                left: 'center',
                                data: ['教授', '副教授(高工)','讲师(工程师)','助工','工人']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '52%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[${sstl}],
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
<!-- ...............实验人员基本信息统计结束 ..........................-->
        <!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"98%"}
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