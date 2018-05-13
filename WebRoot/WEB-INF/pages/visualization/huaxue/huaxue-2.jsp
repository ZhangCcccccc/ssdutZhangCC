<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
	<meta name="decorator" content="iframe"/>
    <title>化学实验楼2层</title>
    
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
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/huaxueBuildingThreeFloor">点击查看三层</a>
                      </div>
                        <div class="actlist">    
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
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/huaxue/02.png"/>

		    <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle">213</div>
            	<img class="roomimgy" style="left: 297px; top: 286px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/213-2.png">
            	<img class="roomimg" style="left: 297px; top: 286px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/213-1.png">
                
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



		    <div class="roomdiv" u0="0" u1="${labRoom210.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom210.id}" target="_blank">210</a></div>
            	<img class="roomimgy" style="left: 751px; top: 267px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/210-2.png">
            	<img class="roomimg" style="left: 751px; top: 267px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/210-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom210.labRoomNumber}</span><br/>
							<span>名称：${labRoom210.labRoomName}</span><br/>
							<span>使用面积：${labRoom210.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom210.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom210.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


		    <div class="roomdiv" u0="0" u1="${labRoom206.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom206.id}" target="_blank">206</a></div>
            	<img class="roomimgy" style="left: 939px; top: 301px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/206-2.png">
            	<img class="roomimg" style="left: 939px; top: 301px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/206-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom206.labRoomNumber}</span><br/>
							<span>名称：${labRoom206.labRoomName}</span><br/>
							<span>使用面积：${labRoom206.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom206.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom206.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>




		    <div class="roomdiv" u0="0" u1="${labRoom202.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom202.id}" target="_blank">202</a></div>
            	<img class="roomimgy" style="left: 1134px; top: 337px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/202-2.png">
            	<img class="roomimg" style="left: 1134px; top: 337px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/202-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom202.labRoomNumber}</span><br/>
							<span>名称：${labRoom202.labRoomName}</span><br/>
							<span>使用面积：${labRoom202.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom202.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom202.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle">209</div>
            	<img class="roomimgy" style="left: 445px; top: 313px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/209-2.png">
            	<img class="roomimg" style="left: 445px; top: 313px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/209-1.png">
                
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
           	  <div class="roomtitle">211</div>
            	<img class="roomimgy" style="left: 372px; top: 300px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/211-2.png">
            	<img class="roomimg" style="left: 372px; top: 300px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/211-1.png">
                
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
            
            
           
             <div class="roomdiv" u0="0" u1="${labRoom201.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom201.id}" target="_blank">201</a></div>
            	<img class="roomimgy" style="left: 1082px; top: 521px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/201-2.png">
            	<img class="roomimg" style="left: 1082px; top: 521px;" src="${pageContext.request.contextPath}/images/visualization/huaxue/201-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom201.labRoomNumber}</span><br/>
							<span>名称：${labRoom201.labRoomName}</span><br/>
							<span>使用面积：${labRoom201.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom201.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom201.labRoomPhone}%</span><br/>
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
