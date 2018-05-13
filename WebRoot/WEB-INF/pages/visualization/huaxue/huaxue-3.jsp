<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
	<meta name="decorator" content="iframe"/>
    <title>化学实验楼3层</title>
    
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
                    	<div class="unlist">        
                            <a href="${pageContext.request.contextPath}/visualization/huaxueBuildingFiveFloor">点击查看五层</a>
                      </div>
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/huaxueBuildingFourFloor">点击查看四层</a>
                      </div>
                        <div class="actlist">
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
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/huaxue/03.png"/>
          


		    <div class="roomdiv" u0="0" u1="${labRoom310.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom310.id}" target="_blank">310</a></div>
            	<img class="roomimgy" style="left: 751px; top: 265px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/310-2.png">
            	<img class="roomimg" style="left: 751px; top: 265px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/310-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom310.labRoomNumber}</span><br/>
							<span>名称：${labRoom310.labRoomName}</span><br/>
							<span>使用面积：${labRoom310.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom310.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom310.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


		    <div class="roomdiv" u0="0" u1="${labRoom306.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom306.id}" target="_blank">306</a></div>
            	<img class="roomimgy" style="left: 939px; top: 301px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/306-2.png">
            	<img class="roomimg" style="left: 939px; top: 301px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/306-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom306.labRoomNumber}</span><br/>
							<span>名称：${labRoom306.labRoomName}</span><br/>
							<span>使用面积：${labRoom306.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom306.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom306.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>




		    <div class="roomdiv" u0="0" u1="${labRoom302.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom302.id}" target="_blank">302</a></div>
            	<img class="roomimgy" style="left: 1134px; top: 337px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/302-2.png">
            	<img class="roomimg" style="left: 1134px; top: 337px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/302-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom302.labRoomNumber}</span><br/>
							<span>名称：${labRoom302.labRoomName}</span><br/>
							<span>使用面积：${labRoom302.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom302.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom302.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>
			
<div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle">315</div>
            	<img class="roomimgy" style="left: 224px; top: 272px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/315-2.png">
            	<img class="roomimg" style="left: 224px; top: 272px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/315-1.png">
                
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
           	  <div class="roomtitle">317</div>
            	<img class="roomimgy" style="left: 149px; top: 258px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/317-2.png">
            	<img class="roomimg" style="left: 149px; top: 258px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/317-1.png">
                
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
            
            
           
             <div class="roomdiv" u0="0" u1="${labRoom301.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom301.id}" target="_blank">301</a></div>
            	<img class="roomimgy" style="left: 1081px; top: 521px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/301-2.png">
            	<img class="roomimg" style="left: 1081px; top: 521px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/301-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom301.labRoomNumber}</span><br/>
							<span>名称：${labRoom301.labRoomName}</span><br/>
							<span>使用面积：${labRoom301.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom301.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom301.labRoomPhone}%</span><br/>
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
