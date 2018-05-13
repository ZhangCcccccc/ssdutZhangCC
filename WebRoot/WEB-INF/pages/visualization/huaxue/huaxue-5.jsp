<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
	<meta name="decorator" content="iframe"/>
    <title>化学实验楼5层</title>
    
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/huaxue/floorview.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/huaxue/istyle-Rmenu.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/huaxue/allpages.css"/>
</head>

<body >
    <div class="htt"><img src="${pageContext.request.contextPath}/images/visualization/huaxue/ttt.png"></div>
	<div id="conteiner">
        <div id="viewbox">
        <div id="menubox">
            <div class="istyle-Rmenu">
                <div class="Rmenu-list">
                    <span class="unlist">楼层信息</span>
                    <div>
                        <div class="titlelee">化学实验楼</div>
                    </div>
                </div>
                <div class="Rmenu-list">
                    <span class="unlist">切换楼层</span>
                    <div>
                    	<div class="actlist">        
                            <a href="${pageContext.request.contextPath}/visualization/huaxueBuildingFiveFloor">点击查看五层</a>
                      </div>
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/huaxueBuildingFourFloor">点击查看四层</a>
                      </div>
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/huaxueBuildingThreeFloor">点击查看三层</a>
                      </div>
                        <div class="unlist">    
                            <a href="${pageContext.request.contextPath}/visualization/huaxueBuildingTwoFloor">点击查看二层</a>
                      </div>
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/huaxueBuildingOneFloor">点击查看一层</a>
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
        <img id="useper" src="${pageContext.request.contextPath}/images/visualization/huaxue/useper.png"/>
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/huaxue/05.png"/>
          
            
            
		    <div class="roomdiv" u0="0" u1="${labRoom510.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom510.id}" target="_blank">510</a></div>
            	<img class="roomimgy" style="left: 750px; top: 268px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/510-2.png">
            	<img class="roomimg" style="left: 750px; top: 268px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/510-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom510.labRoomNumber}</span><br/>
							<span>名称：${labRoom510.labRoomName}</span><br/>
							<span>使用面积：${labRoom510.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom510.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom510.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


		    <div class="roomdiv" u0="0" u1="${labRoom506.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom506.id}" target="_blank">506</a></div>
            	<img class="roomimgy" style="left: 939px; top: 301px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/506-2.png">
            	<img class="roomimg" style="left: 939px; top: 301px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/506-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom506.labRoomNumber}</span><br/>
							<span>名称：${labRoom506.labRoomName}</span><br/>
							<span>使用面积：${labRoom506.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom506.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom506.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


		    <div class="roomdiv" u0="0" u1="${labRoom502.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom502.id}" target="_blank">502</a></div>
            	<img class="roomimgy" style="left: 1134px; top: 337px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/502-2.png">
            	<img class="roomimg" style="left: 1134px; top: 337px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/502-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom502.labRoomNumber}</span><br/>
							<span>名称：${labRoom502.labRoomName}</span><br/>
							<span>使用面积：${labRoom502.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom502.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom502.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>



            <div class="roomdiv" u0="0" u1="${labRoom509.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom509.id}" target="_blank">509</a></div>
            	<img class="roomimgy" style="left: 295px; top: 285px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/509-2.png">
            	<img class="roomimg" style="left: 295px; top: 285px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/509-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom509.labRoomNumber}</span><br/>
							<span>名称：${labRoom509.labRoomName}</span><br/>
							<span>使用面积：${labRoom509.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom509.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom509.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>
            
    </div>
    </div>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/huaxue/istyle-Rmenu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/huaxue/floor.js"></script>
</body>
</html>
