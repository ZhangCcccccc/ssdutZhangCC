<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
	<meta name="decorator" content="iframe"/>
    <title>综合实验楼4层</title>
    
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/zonghe/floorview.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/zonghe/istyle-Rmenu.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/zonghe/allpages.css"/>
</head>

<body >
    <div class="htt"><img src="${pageContext.request.contextPath}/images/visualization/zonghe/ttt.png"></div>
	<div id="conteiner">
        <div id="viewbox">
        <div id="menubox">
            <div class="istyle-Rmenu">
                <div class="Rmenu-list">
                    <span class="unlist">楼层信息</span>
                    <div>
                        <div class="titlelee">综合实验楼</div>
                    </div>
              </div>
                <div class="Rmenu-list">
                    <span class="unlist">切换楼层</span>
                    <div>
                    	<div class="unlist">        
                            <a href="${pageContext.request.contextPath}/visualization/zongheBuildingFiveFloor">点击查看五层</a>
                      </div>
                        <div class="actlist">
                            <a href="${pageContext.request.contextPath}/visualization/zongheBuildingFourFloor">点击查看四层</a>
                      </div>
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/zongheBuildingThreeFloor">点击查看三层</a>
                      </div>
                        <div class="unlist">    
                            <a href="${pageContext.request.contextPath}/visualization/zongheBuildingTwoFloor">点击查看二层</a>
                      </div>
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/zongheBuildingOneFloor">点击查看一层</a>
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
        <img id="useper" src="${pageContext.request.contextPath}/images/visualization/zonghe/useper.png"/>
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/zonghe/03.png"/>
          
            
            	
            
            <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle">435</div>
            	<img class="roomimgy" style="left: 163px; top: 550px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/04-435-2.png">
            	<img class="roomimg" style="left: 163px; top: 550px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/04-435-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：</span><br/>
                        </div>
				  </div>
              </div>
            </div>


            <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle">413</div>
            	<img class="roomimgy" style="left: 987px; top: 460px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-313-2.png">
            	<img class="roomimg" style="left: 987px; top: 460px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-313-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：</span><br/>
                        </div>
				  </div>
              </div>
            </div>

			<div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle">416</div>
            	<img class="roomimgy" style="left: 1004px; top: 388px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-216-2.png">
            	<img class="roomimg" style="left: 1004px; top: 388px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-216-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：</span><br/>
                        </div>
				  </div>
              </div>
            </div>

			<div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle">405</div>
            	<img class="roomimgy" style="left: 1229px; top: 333px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-205-2.png">
            	<img class="roomimg" style="left: 1229px; top: 333px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-205-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：</span><br/>
                        </div>
				  </div>
              </div>
            </div>




			<div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle">401</div>
            	<img class="roomimgy" style="left: 1253px; top: 246px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-201-2.png">
            	<img class="roomimg" style="left: 1253px; top: 246px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-201-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：</span><br/>
                        </div>
				  </div>
              </div>
            </div>


			<div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle">406</div>
            	<img class="roomimgy" style="left: 1111px; top: 313px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-206-2.png">
            	<img class="roomimg" style="left: 1111px; top: 313px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-206-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：</span><br/>
                        </div>
				  </div>
              </div>
            </div>


			<div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle">402</div>
            	<img class="roomimgy" style="left: 1138px; top: 223px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-202-2.png">
            	<img class="roomimg" style="left: 1138px; top: 223px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-202-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：</span><br/>
							<span>名称：</span><br/>
							<span>使用面积：</span><br/>
							<span>实验室容量：</span><br/>
							<span>实验室利用率：</span><br/>
                        </div>
				  </div>
              </div>
            </div>



    </div>
    </div>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/zonghe/istyle-Rmenu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/zonghe/floor.js"></script>
</body>
</html>
