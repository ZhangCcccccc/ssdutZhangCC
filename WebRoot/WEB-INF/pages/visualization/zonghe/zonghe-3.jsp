<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
	<meta name="decorator" content="iframe"/>
    <title>综合实验楼3层</title>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/zonghe/floorview.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/zonghe/istyle-Rmenu.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/zonghe/allpages.css"/>nk rel="stylesheet" type="text/css" href="z-css/allpages.css"/>
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
                        <div class="actlist">
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
           	  <div class="roomtitle">328</div>
            	<img class="roomimgy" style="left: 196px; top: 38px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-328-2.png">
            	<img class="roomimg" style="left: 196px; top: 38px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-328-1.png">
                
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
           	  <div class="roomtitle">326</div>
            	<img class="roomimgy" style="left: 439px; top: 82px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-326-2.png">
            	<img class="roomimg" style="left: 439px; top: 82px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-326-1.png">
                
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
           
            
            <div class="roomdiv" u0="0" u1="${labRoom317.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom317.id}" target="_blank">317</a></div>
            	<img class="roomimgy" style="left: 816px; top: 426px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-217-2.png">
            	<img class="roomimg" style="left: 816px; top: 426px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-217-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom317.labRoomNumber}</span><br/>
							<span>名称：${labRoom317.labRoomName}</span><br/>
							<span>使用面积：${labRoom317.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom317.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom317.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


            <div class="roomdiv" u0="0" u1="${labRoom313.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom313.id}" target="_blank">313</a></div>
            	<img class="roomimgy" style="left: 987px; top: 460px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-313-2.png">
            	<img class="roomimg" style="left: 987px; top: 460px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-313-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom313.labRoomNumber}</span><br/>
							<span>名称：${labRoom313.labRoomName}</span><br/>
							<span>使用面积：${labRoom313.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom313.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom313.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>







			<div class="roomdiv" u0="0" u1="${labRoom316.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom316.id}" target="_blank">316</a></div>
            	<img class="roomimgy" style="left: 1004px; top: 388px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-216-2.png">
            	<img class="roomimg" style="left: 1004px; top: 388px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-216-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom316.labRoomNumber}</span><br/>
							<span>名称：${labRoom316.labRoomName}</span><br/>
							<span>使用面积：${labRoom316.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom316.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom316.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

			<div class="roomdiv" u0="0" u1="${labRoom305.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom305.id}" target="_blank">305</a></div>
            	<img class="roomimgy" style="left: 1229px; top: 333px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-205-2.png">
            	<img class="roomimg" style="left: 1229px; top: 333px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-205-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom305.labRoomNumber}</span><br/>
							<span>名称：${labRoom305.labRoomName}</span><br/>
							<span>使用面积：${labRoom305.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom305.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom305.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


			<div class="roomdiv" u0="0" u1="${labRoom301.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom301.id}" target="_blank">301</a></div>
            	<img class="roomimgy" style="left: 1253px; top: 246px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-201-2.png">
            	<img class="roomimg" style="left: 1253px; top: 246px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-201-1.png">
                
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

			<div class="roomdiv" u0="0" u1="${labRoom309.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom309.id}" target="_blank">309</a></div>
            	<img class="roomimgy" style="left: 1200px; top: 425px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-209-2.png">
            	<img class="roomimg" style="left: 1200px; top: 425px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-209-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom309.labRoomNumber}</span><br/>
							<span>名称：${labRoom309.labRoomName}</span><br/>
							<span>使用面积：${labRoom309.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom309.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom309.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

						
			<div class="roomdiv" u0="0" u1="${labRoom306.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom306.id}" target="_blank">306</a></div>
            	<img class="roomimgy" style="left: 1111px; top: 313px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-206-2.png">
            	<img class="roomimg" style="left: 1111px; top: 313px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-206-1.png">
                
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
            	<img class="roomimgy" style="left: 1138px; top: 223px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-202-2.png">
            	<img class="roomimg" style="left: 1138px; top: 223px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/02-202-1.png">
                
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
           	  <div class="roomtitle">334</div>
            	<img class="roomimgy" style="left: 115px; top: 130px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-334-2.png">
            	<img class="roomimg" style="left: 115px; top: 130px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-334-1.png">
                
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
           	  <div class="roomtitle">321</div>
            	<img class="roomimgy" style="left: 252px; top: 280px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-321-2.png">
            	<img class="roomimg" style="left: 252px; top: 280px;" src="${pageContext.request.contextPath}/images/visualization/zonghe/03-321-1.png">
                
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
