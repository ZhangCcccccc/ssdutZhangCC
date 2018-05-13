<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
	<meta name="decorator" content="iframe"/>
    <title>4号学院楼4层</title>
    
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/4hxyl/floorview.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/4hxyl/istyle-Rmenu.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/4hxyl/allpages.css"/>
</head>

<body >
    <div class="htt"><img src="${pageContext.request.contextPath}/images/visualization/4hxyl/ttt.png"></div>
	<div id="conteiner">
        <div id="viewbox">
        <div id="menubox">
            <div class="istyle-Rmenu">
                <div class="Rmenu-list">
                    <span class="unlist">楼层信息</span>
                    <div>
                        <div class="titlelee">四号学院楼</div>
                    </div>
                </div>
                <div class="Rmenu-list">
                    <span class="unlist">切换楼层</span>
                    <div>
						 <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingSixFloor">点击查看六层</a>
                      </div>
                    	<div class="unlist">        
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingFiveFloor">点击查看五层</a>
                      </div>
                        <div class="actlist">
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingFourFloor">点击查看四层</a>
                      </div>
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingThreeFloor">点击查看三层</a>
                      </div>
                        <div class="unlist">    
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingTwoFloor">点击查看二层</a>
                      </div>
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingOneFloor">点击查看一层</a>
                        </div>

                    </div>
                </div>
                <div class="Rmenu-list">
                    <span class="unlist">查看选项</span>
                     <div id="buttonbox">
                        <div class="actlist" onClick="shown(0)">查看分布地图</div>
                        <div class="unlist" onClick="shown(1)">查看实验室利用率</div>
                        <!-- <div class="unlist" onClick="shown(2)">查看实验项开出率</div>
                        <div class="unlist" onClick="shown(3)">查看综合实验比率</div>
                        <div class="unlist" onClick="shown(4)">查看大型设备机时率</div>
                        <div class="unlist" onClick="shown(5)">查看大型设备利用率</div>
                        <div class="unlist" onClick="shown(6)">查看教师参与率率</div>
                        <div class="unlist" onClick="shown(7)">查看人才培养率</div> -->
                    </div>
                </div>
            </div>
        </div>
        <img id="useper" src="${pageContext.request.contextPath}/images/visualization/4hxyl/useper.png"/>
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/4hxyl/04.png"/>
          
 
          <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="left:2px !important;">4120-4118</div>
            	<img class="roomimgy" style="left: 115px; top: 273px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4120-4118-2.png">
            	<img class="roomimg" style="left: 115px; top: 273px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4120-4118-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>


          <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle">4170-4172</div>
            	<img class="roomimgy" style="left: 173px; top: 102px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4170-2.png">
            	<img class="roomimg" style="left: 173px; top: 102px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4170-1.png">
                
                <div class="roomtext" style="left:360px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>

          <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle">4122</div>
            	<img class="roomimgy" style="left: 147px; top: 244px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4122-2.png">
            	<img class="roomimg" style="left: 147px; top: 244px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4122-1.png">
                
                <div class="roomtext" style="left:300px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>




 <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:410px !important;left:0px !important;">4112</div>
            	<img class="roomimgy" style="left: 51px; top: 402px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4112-4110-2.png">
            	<img class="roomimg" style="left: 51px; top: 402px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4112-4110-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>




          <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:359px !important;left:13px !important;">4114</div>
            	<img class="roomimgy" style="left: 84px; top: 370px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4114-2.png">
            	<img class="roomimg" style="left: 84px; top: 370px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4114-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>




          <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle">4116</div>
            	<img class="roomimgy" style="left: 100px; top: 338px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4116-2.png">
            	<img class="roomimg" style="left: 100px; top: 338px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4116-1.png">
                
                <div class="roomtext" style="left:243px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>



           <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:527px !important; left:18px !important;">4107-4105</div>
            	<img class="roomimgy" style="left: 110px; top: 449px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4107-4105-2.png">
            	<img class="roomimg" style="left: 110px; top: 449px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4107-4105-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>



           <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle">4103-4101</div>
            	<img class="roomimgy" style="left: 165px; top: 462px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4103-4101-2.png">
            	<img class="roomimg" style="left: 165px; top: 462px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4103-4101-1.png">
                
                <div class="roomtext" style="left:350px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>



        <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:547px !important;">4099-4097</div>
            	<img class="roomimgy" style="left: 212px; top: 471px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4099-4097-2.png">
            	<img class="roomimg" style="left: 212px; top: 471px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/4099-4097-1.png">
                
                <div class="roomtext" style="left:360px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>
            
    </div>
    </div>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/4hxyl/istyle-Rmenu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/4hxyl/floor.js"></script>
</body>
</html>
