<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <title>综合报表</title>
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
 <!-- ...............实验室数量统计 ..........................-->     
        <div class="lab-chart" style="margin:5px 1% 0;">
            <ul class="search_m">
                <li>实验中心</li>
                <li>
                    <select name="" id="">
                        <option value="">--请选择--</option>
                        <option value="">机械工程实训中心</option>
                        <option value="">电工电子实训中心</option>
                    </select>
                </li>
                <li>
                    <input type="submit" value="查询">
                </li>
                <li>
                    <input type="submit" value="取消查询">
                </li>
            </ul>
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
                                // trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                // orient: 'vertical',
                                // top: 'middle',
                                bottom: 10,
                                left: 'center',
                                data: ['电工电子中心', '国家级通信工程专业实验教学示范中心','电子信息与通信工程学科专业实验教学中心','综合性工程训练中心']
                            },
                            formatter:function(val){return val.split("-").join("\n");},
                            series : [
                                {
                                    type: 'pie',
                                    radius : '65%',
                                    center: ['58%', '50%'],
                                    selectedMode: 'single',
                                    data:[
                                        {value:50, name: '电工电子中心'},
                                        {value:20, name: '国家级通信工程专业实验教学示范中心'},
                                        {value:10, name: '电子信息与通信工程学科专业实验教学中心'},
                                        {value:20, name: '综合性工程训练中心'},
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
<!-- ...............实验室数量统计结束 ..........................-->

<!-- ...............大综合报表 ..........................-->
        <div class="lab-chart" style="margin-right:1%;margin-top:5px;">
            <ul class="search_m">
                <li>学期</li>
                <li>
                    <select name="" id="">
                        <option value="">--请选择--</option>
                        <option value="">2016-2017第一学期</option>
                        <option value="">2016-2017第二学期</option>
                    </select>
                </li>
                <li>
                    <input type="submit" value="查询">
                </li>
                <li>
                    <input type="submit" value="取消查询">
                </li>
            </ul>
            <p>大综合报表</p>
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

                    var option = {

                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            
                        type : 'shadow'       
                    }
                },
                legend: {
                    data:['开设课程总数','开设项目总数','实验学时总和','教师总人数','学生总人数','学生批次总数']
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
                            data : ['电工电子中心','国家级通信工程专业实验教学示范中心','电子信息与通信工程学科专业实验教学中心','综合性工程训练中心'],
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
                       
                        data:[320, 332, 301, 334, 390, 330]
                    }, 
                    {
                        name:'开设项目总数',
                        type:'bar',
                       
                        data:[120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name:'实验学时总和',
                        type:'bar',
                       
                        data:[220, 182, 191, 234, 290, 330, 310]
                    },
                    {
                        name:'教师总人数',
                        type:'bar',
                       
                        data:[150, 232, 201, 154, 190, 330, 410]
                    },
                    {
                        name:'学生总人数',
                        type:'bar',
                      
                        data:[862, 1018, 964, 1026, 1679, 1600, 1570],
                    },
                    {
                        name:'学生批次总数',
                        type:'bar',
                       
                        data:[620, 732, 701, 734, 1090, 1130, 1120]
                    },
                   
                ]
            };

                            myChart.setOption(option);
                        }
                    );
        </script>
<!-- ...............大综合报表结束 ..........................-->

<!-- ...............各中心实验室利用率 ..........................-->
        <div class="lab-chart" style="margin:15px 1% 0;">
            <ul class="search_m">
                <li>学期</li>
                <li>
                    <select name="" id="">
                        <option value="">--请选择--</option>
                        <option value="">2016-2017第一学期</option>
                        <option value="">2016-2017第二学期</option>
                    </select>
                </li>
                <li>
                    <input type="submit" value="查询">
                </li>
                <li>
                    <input type="submit" value="取消查询">
                </li>
            </ul>
            <p>各中心实验室利用率</p>
            <div id="cen-ratio" style="height:420px;font-size:12px;"></div>
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
                   
                    var myChart = ec.init(document.getElementById('cen-ratio'));

                    var option = {
                        title : {
                            text: '',
                            subtext: ''
                        },
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['']
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        calculable : true,
                        xAxis : {
                            show : true,
                            type : 'category',
                            data : ['电工电子中心','国家级通信工程专业实验教学示范中心','电子信息与通信工程学科专业实验教学中心','综合性工程训练中心'],
                            axisLabel : {
                                interval : 0,
                                formatter : function(params){
                                    var newParamsName = "";
                                    var paramsNameNumber = params.length;
                                    var provideNumber = 5;
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
                        yAxis : {
                            type : 'value'
                        },
                        series : [ {
                            name : "人数",
                            type : "bar",
                            data : [2.0, 4.9, 7.0, 23.2]
                        } ]
                    };
           
                    // 为echarts对象加载数据
                    myChart.setOption(option);
                }
            );
        </script>
<!-- ..............各中心实验室利用率结束 ........................-->

<!-- ...............实验室教师人数 ..........................-->
        <div class="lab-chart" style="margin-right:1%;margin-top:15px;">
            <ul class="search_m">
                <li>实验中心</li>
                <li>
                    <select name="" id="">
                        <option value="">--请选择--</option>
                        <option value="">机械工程实训中心</option>
                        <option value="">电工电子实训中心</option>
                    </select>
                </li>
                <li>
                    <input type="submit" value="查询">
                </li>
                <li>
                    <input type="submit" value="取消查询">
                </li>
            </ul>
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
                                data: ['专职', '兼职']
                            },
                            series : [
                                {
                                    type: 'pie',
                                    radius : '65%',
                                    center: ['50%', '50%'],
                                    selectedMode: 'single',
                                    data:[
                                        
                                        {value:86, name: '专职'},
                                        {value:112, name: '兼职'},
                                       
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
<!-- ...............实验室教师人数结束 ..........................-->


        <!-- 空白部分，不需要判断 -->
        <div class="none_blank" style="width:100%;height:100px;clear:both;"></div>
    </body>

</html>