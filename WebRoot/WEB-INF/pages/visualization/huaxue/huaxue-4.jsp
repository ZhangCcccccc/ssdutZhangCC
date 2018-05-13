<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
	<meta name="decorator" content="iframe"/>
    <title>化学实验楼4层</title>
    
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
                        <div class="actlist">
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
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/huaxue/04.png"/>
          
            

		    <div class="roomdiv" u0="0" u1="${labRoom410.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom410.id}" target="_blank">410</a></div>
            	<img class="roomimgy" style="left: 751px; top: 265px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/410-2.png">
            	<img class="roomimg" style="left: 751px; top: 265px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/410-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom410.labRoomNumber}</span><br/>
							<span>名称：${labRoom410.labRoomName}</span><br/>
							<span>使用面积：${labRoom410.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom410.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom410.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


		    <div class="roomdiv" u0="0" u1="${labRoom406.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom406.id}" target="_blank">406</a></div>
            	<img class="roomimgy" style="left: 940px; top: 300px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/406-2.png">
            	<img class="roomimg" style="left: 940px; top: 300px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/406-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom406.labRoomNumber}</span><br/>
							<span>名称：${labRoom406.labRoomName}</span><br/>
							<span>使用面积：${labRoom406.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom406.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom406.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>




		    <div class="roomdiv" u0="0" u1="${labRoom402.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom402.id}" target="_blank">402</a></div>
            	<img class="roomimgy" style="left: 1134px; top: 337px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/402-2.png">
            	<img class="roomimg" style="left: 1134px; top: 337px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/402-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom402.labRoomNumber}</span><br/>
							<span>名称：${labRoom402.labRoomName}</span><br/>
							<span>使用面积：${labRoom402.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom402.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom402.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

			
           <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle">415</div>
            	<img class="roomimgy" style="left: 224px; top: 272px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/415-2.png">
            	<img class="roomimg" style="left: 224px; top: 272px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/415-1.png">
                
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
            	<div class="roomtitle">409</div>
            	<img class="roomimgy" style="left: 445px; top: 312px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/409-2.png">
            	<img class="roomimg" style="left: 445px; top: 312px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/409-1.png">
                
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
           	  <div class="roomtitle">417</div>
            	<img class="roomimgy" style="left: 149px; top: 258px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/417-2.png">
            	<img class="roomimg" style="left: 149px; top: 258px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/417-1.png">
                
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
            
            
           
             <div class="roomdiv" u0="0" u1="${labRoom401.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom401.id}" target="_blank">401</a></div>
            	<img class="roomimgy" style="left: 1081px; top: 521px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/401-2.png">
            	<img class="roomimg" style="left: 1081px; top: 521px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/401-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom401.labRoomNumber}</span><br/>
							<span>名称：${labRoom401.labRoomName}</span><br/>
							<span>使用面积：${labRoom401.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom401.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom401.labRoomPhone}%</span><br/>
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
