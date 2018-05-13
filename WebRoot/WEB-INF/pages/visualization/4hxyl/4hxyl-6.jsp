<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
	<meta name="decorator" content="iframe"/>
    <title>4号学院楼6层</title>
    
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
						 <div class="actlist">
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingSixFloor">点击查看六层</a>
                      </div>
                    	<div class="unlist">        
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingFiveFloor">点击查看五层</a>
                      </div>
                        <div class="unlist">
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
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/4hxyl/06.png"/>
          
            
          <div class="roomdiv" u0="0" u1="${labRoom6065.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="left:670px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom6065.id}" target="_blank">6065</a></div>
            	<img class="roomimgy" style="left: 612px; top: 173px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/6065-2.png">
            	<img class="roomimg" style="left: 612px; top: 173px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/6065-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom6065.labRoomNumber}</span><br/>
							<span>名称：${labRoom6065.labRoomName}</span><br/>
							<span>使用面积：${labRoom6065.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom6065.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom6065.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>


          <div class="roomdiv" u0="0" u1="${labRoom6073.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom6073.id}" target="_blank">6077-6073</a></div>
            	<img class="roomimgy" style="left: 487px; top: 143px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/6077-6073-2.png">
            	<img class="roomimg" style="left: 487px; top: 143px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/6077-6073-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom6073.labRoomNumber}</span><br/>
							<span>名称：${labRoom6073.labRoomName}</span><br/>
							<span>使用面积：${labRoom6073.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom6073.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom6073.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>


           <div class="roomdiv" u0="0" u1="${labRoom6029.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="left:466px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom6029.id}" target="_blank">6029</a></div>
            	<img class="roomimgy" style="left: 495px; top: 550px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/6029-2.png">
            	<img class="roomimg" style="left: 495px; top: 550px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/6029-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom6029.labRoomNumber}</span><br/>
							<span>名称：${labRoom6029.labRoomName}</span><br/>
							<span>使用面积：${labRoom6029.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom6029.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom6029.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>



           <div class="roomdiv" u0="0" u1="${labRoom6023.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="left:558px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom6023.id}" target="_blank">6023</a></div>
            	<img class="roomimgy" style="left: 573px; top: 568px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/6023-2.png">
            	<img class="roomimg" style="left: 573px; top: 568px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/6023-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom6023.labRoomNumber}</span><br/>
							<span>名称：${labRoom6023.labRoomName}</span><br/>
							<span>使用面积：${labRoom6023.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom6023.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom6023.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>




    <div class="roomdiv" u0="0" u1="${labRoom6019.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" ><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom6019.id}" target="_blank">6019</a></div>
            	<img class="roomimgy" style="left: 639px; top: 577px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/6019-2.png">
            	<img class="roomimg" style="left: 639px; top: 577px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/6019-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom6019.labRoomNumber}</span><br/>
							<span>名称：${labRoom6019.labRoomName}</span><br/>
							<span>使用面积：${labRoom6019.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom6019.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom6019.labRoomPhone}%</span><br/>
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
