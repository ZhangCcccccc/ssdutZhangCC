<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <title>系统报表</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/system_report/css/sci_reports.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/system_report/js/jquery-1.11.0.min.js"></script>
        <script data-require-id="sakura" src="${pageContext.request.contextPath}/system_report/js/sakura.js" async=""></script>
        <!-- ECharts单文件引入 -->
        <script src="${pageContext.request.contextPath}/system_report/js/echarts.js"></script>
        <script src="${pageContext.request.contextPath}/system_report/js/line.js"></script>
        <script src="${pageContext.request.contextPath}/system_report/js/bar.js"></script>
        <script src="${pageContext.request.contextPath}/system_report/js/pie.js"></script>
    </head>
    <body>
<!-- ...........实验人员基本信息统计 ....................-->
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
                                data: ['博士', '硕士','本科','大专']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '65%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[
                                        
                                        {value:70, name: '博士'},
                                        {value:74, name: '硕士'},
                                        {value:37, name: '本科'},
                                        {value:17, name: '大专'},
                                      
                                
                                    ],
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
                    myChart.setOption(option);
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
                                data: ['博士', '硕士','本科','大专']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '65%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[
                                        
                                        {value:21, name: '博士'},
                                        {value:44, name: '硕士'},
                                        {value:44, name: '本科'},
                                        {value:3, name: '大专'},
                                      
                                
                                    ],
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
                    myChart.setOption(option);
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
                                data: ['35岁以下', '36-50岁','51-60岁','60岁以上']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '65%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[
                                        
                                        {value:54, name: '35岁以下'},
                                        {value:89, name: '36-50岁'},
                                        {value:51, name: '51-60岁'},
                                        {value:4, name: '60岁以上'},
                                      
                                
                                    ],
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
                    myChart.setOption(option);
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
                                    radius : '65%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[
                                        
                                        {value:2, name: '35岁以下'},
                                        {value:70, name: '36-50岁'},
                                        {value:34, name: '51-60岁'},
                                        {value:1, name: '60岁以上'},
                                      
                                
                                    ],
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
                    myChart.setOption(option);
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
                                data: ['教授', '副教授（高工）','讲师（工程师）','助工']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '65%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[
                                        
                                        {value:21, name: '教授'},
                                        {value:125, name: '副教授（高工）'},
                                        {value:49, name: '讲师（工程师）'},
                                        {value:1, name: '助工'},
                                      
                                
                                    ],
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
                    myChart.setOption(option);
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
                                data: ['教授', '副教授（高工）','讲师（工程师）','助工']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '65%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[
                                        
                                        {value:8, name: '教授'},
                                        {value:70, name: '副教授（高工）'},
                                        {value:34, name: '讲师（工程师）'},
                                        {value:5, name: '助工'},
                                      
                                
                                    ],
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
                    myChart.setOption(option);
                }
            );
        </script>
<!-- ...............实验人员基本信息统计结束 ..........................-->
        <!-- 空白部分，不需要判断 -->
        <div class="none_blank" style="width:100%;height:100px;clear:both;"></div>
    </body>

</html>