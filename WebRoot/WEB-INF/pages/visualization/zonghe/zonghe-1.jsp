<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
	<meta name="decorator" content="iframe"/>
    <title>综合实验楼1层</title>
	
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
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/zongheBuildingFourFloor">点击查看四层</a>
                      </div>
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/zongheBuildingThreeFloor">点击查看三层</a>
                      </div>
                        <div class="unlist">    
                            <a href="${pageContext.request.contextPath}/visualization/zongheBuildingTwoFloor">点击查看二层</a>
                      </div>
                        <div class="actlist">
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
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/zonghe/01.png"/>
          
            
            	

			<div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle">130</div>
            	<img class="roomimgy" style="left: 196px; top: 41px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-130-2.png">
            	<img class="roomimg" style="left: 196px; top: 41px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-130-1.png">
                
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
           	  <div class="roomtitle">117</div>
            	<img class="roomimgy" style="left: 816px; top: 426px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-117-2.png">
            	<img class="roomimg" style="left: 816px; top: 426px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-117-1.png">
                
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
           	  <div class="roomtitle">126</div>
            	<img class="roomimgy" style="left: 439px; top: 82px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-126-2.png">
            	<img class="roomimg" style="left: 439px; top: 82px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-126-1.png">
                
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
           	  <div class="roomtitle">128</div>
            	<img class="roomimgy" style="left: 369px; top: 71px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-128-2.png">
            	<img class="roomimg" style="left: 369px; top: 71px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-128-1.png">
                
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
           	  <div class="roomtitle">115</div>
            	<img class="roomimgy" style="left: 988px; top: 460px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-115-2.png">
            	<img class="roomimg" style="left: 988px; top: 460px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-115-1.png">
                
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
           	  <div class="roomtitle">109</div>
            	<img class="roomimgy" style="left: 1200px; top: 425px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-109-2.png">
            	<img class="roomimg" style="left: 1200px; top: 425px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-109-1.png">
                
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
           	  <div class="roomtitle">106</div>
            	<img class="roomimgy" style="left: 1111px; top: 313px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-106-2.png">
            	<img class="roomimg" style="left: 1111px; top: 313px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-106-1.png">
                
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
           	  <div class="roomtitle">136</div>
            	<img class="roomimgy" style="left: 155px; top: 168px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-136-2.png">
            	<img class="roomimg" style="left: 155px; top: 168px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-136-1.png">
                
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
           	  <div class="roomtitle">129</div>
            	<img class="roomimgy" style="left: 252px; top: 282px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-129-2.png">
            	<img class="roomimg" style="left: 252px; top: 282px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-129-1.png">
                
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
           	  <div class="roomtitle">125</div>
            	<img class="roomimgy" style="left: 325px; top: 295px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-125-2.png">
            	<img class="roomimg" style="left: 325px; top: 295px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-125-1.png">
                
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
           	  <div class="roomtitle">123</div>
            	<img class="roomimgy" style="left: 427px; top: 314px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-123-2.png">
            	<img class="roomimg" style="left: 427px; top: 314px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-123-1.png">
                
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
           	  <div class="roomtitle">121</div>
            	<img class="roomimgy" style="left: 514px; top: 329px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-121-2.png">
            	<img class="roomimg" style="left: 514px; top: 329px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/01-121-1.png">
                
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
