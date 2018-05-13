<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
    <meta name="decorator" content="iframe"/>
    <title>4号学院楼3层</title>
    
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
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingFourFloor">点击查看四层</a>
                      </div>
                        <div class="actlist">
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
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/4hxyl/03.png"/>
          
            <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle">3069-3067</div>
            	<img class="roomimgy" style="left: 877px; top: 250px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3069-3067-2.png">
            	<img class="roomimg" style="left: 877px; top: 250px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3069-3067-1.png">
                
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
           	  <div class="roomtitle" style="119px !important;left:939px !important;">3064</div>
            	<img class="roomimgy" style="left: 993px; top: 144px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3064-2.png">
            	<img class="roomimg" style="left: 993px; top: 144px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3064-1.png">
                
                <div class="roomtext" style="left:600px !important;">
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
           	  <div class="roomtitle" style="top:118px!important;left:1052px !important;">3062</div>
            	<img class="roomimgy" style="left: 1011px; top: 147px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3062-2.png">
            	<img class="roomimg" style="left: 1011px; top: 147px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3062-1.png">
                
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
           	  <div class="roomtitle">3169-3171</div>
            	<img class="roomimgy" style="left: 242px; top: 105px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3171-3169-2.png">
            	<img class="roomimg" style="left: 242px; top: 105px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3171-3169-1.png">
                
                <div class="roomtext" style="left:450px !important;">
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
            	<div class="roomtitle" >3120-3118</div>
            	<img class="roomimgy" style="left: 115px; top: 273px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3120-3118-2.png">
            	<img class="roomimg" style="left: 115px; top: 273px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3120-3118-1.png">
                
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
            	<div class="roomtitle" style="top:305px !important;left:36px !important;">3116</div>
            	<img class="roomimgy" style="left: 99px; top: 338px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3116-2.png">
            	<img class="roomimg" style="left: 99px; top: 338px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3116-1.png">
                
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
            	<div class="roomtitle" style="left:18px !important;">3114</div>
            	<img class="roomimgy" style="left: 82px; top: 370px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3114-2.png">
            	<img class="roomimg" style="left: 82px; top: 370px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3114-1.png">
                
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
            	<div class="roomtitle" style="top:525px !important; left:7px !important;">3107-3105</div>
            	<img class="roomimgy" style="left: 109px; top: 451px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3107-3105-2.png">
            	<img class="roomimg" style="left: 109px; top: 451px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3107-3105-1.png">
                
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
            	<div class="roomtitle">3101</div>
            	<img class="roomimgy" style="left: 162px; top: 460px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3101-2.png">
            	<img class="roomimg" style="left: 162px; top: 460px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3101-1.png">
                
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
            	<div class="roomtitle" style="top:549px !important;">3097</div>
            	<img class="roomimgy" style="left: 213px; top: 470px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3097-2.png">
            	<img class="roomimg" style="left: 213px; top: 470px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3097-1.png">
                
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
           	  <div class="roomtitle"  style="left:268px !important;">3093</div>
            	<img class="roomimgy" style="left: 222px; top: 478px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3093-2.png">
            	<img class="roomimg" style="left: 222px; top: 478px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3093-1.png">
                
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
            	<div class="roomtitle" >3031</div>
            	<img class="roomimgy" style="left: 783px; top: 615px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3031-2.png">
            	<img class="roomimg" style="left: 783px; top: 615px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3031-1.png">
                
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
            	<div class="roomtitle" >3023</div>
            	<img class="roomimgy" style="left: 911px; top: 640px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3023-2.png">
            	<img class="roomimg" style="left: 911px; top: 640px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3023-1.png">
                
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

    </div>
    </div>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/4hxyl/istyle-Rmenu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/4hxyl/floor.js"></script>
</body>
</html>
