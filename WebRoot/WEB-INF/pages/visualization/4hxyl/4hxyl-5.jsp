<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
	<meta name="decorator" content="iframe"/>
    <title>4号学院楼5层</title>
    
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
                    	<div class="actlist">        
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
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/4hxyl/05.png"/>
          
            

			 <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle">5138</div>
            	<img class="roomimgy" style="left: 671px; top: 86px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5138-2.png">
            	<img class="roomimg" style="left: 671px; top: 86px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5138-1.png">
                
                <div class="roomtext" style="left:780px !important;">
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
            	<div class="roomtitle">5135</div>
            	<img class="roomimgy" style="left: 630px; top: 176px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5135-2.png">
            	<img class="roomimg" style="left: 630px; top: 176px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5135-1.png">
                
                <div class="roomtext" >
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
            	<div class="roomtitle" style="top:299px !important;">5116</div>
            	<img class="roomimgy" style="left: 66px; top: 337px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5116-2.png">
            	<img class="roomimg" style="left: 65px; top: 337px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5116-1.png">
                
                <div class="roomtext" >
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
            	<div class="roomtitle" style="top:339px !important;left:14px !important;">5114</div>
            	<img class="roomimgy" style="left: 53px; top: 364px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5114-2.png">
            	<img class="roomimg" style="left: 53px; top: 364px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5114-1.png">
                
                <div class="roomtext" >
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
            	<div class="roomtitle" style="top:401px !important; left:5px !important;">5112-5110</div>
            	<img class="roomimgy" style="left: 33px; top: 402px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5112-5110-2.png">
            	<img class="roomimg" style="left: 33px; top: 402px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5112-5110-1.png">
                
                <div class="roomtext" >
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
            	<div class="roomtitle" style="top:524px !important;left:0px !important;">5107-5105</div>
            	<img class="roomimgy" style="left: 108px; top: 450px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5107-5105-2.png">
            	<img class="roomimg" style="left: 108px; top: 450px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5107-5105-1.png">
                
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
            	<div class="roomtitle" style="left:198px !important;">5103-5101</div>
            	<img class="roomimgy" style="left: 164px; top: 462px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5103-5001-2.png">
            	<img class="roomimg" style="left: 164px; top: 462px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5103-5001-1.png">
                
                <div class="roomtext" style="left:370px !important;">
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
            	<div class="roomtitle" style="top:570px !important;">5099-5097</div>
            	<img class="roomimgy" style="left: 212px; top: 470px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5099-5097-2.png">
            	<img class="roomimg" style="left: 212px; top: 470px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5099-5097-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom5097.labRoomNumber}</span><br/>
							<span>名称：${labRoom5097.labRoomName}</span><br/>
							<span>使用面积：${labRoom5097.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom5097.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>


            <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="left:320px !important;">5086-5084</div>
            	<img class="roomimgy" style="left: 404px; top: 396px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5086-5084-2.png">
            	<img class="roomimg" style="left: 404px; top: 396px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5086-5084-1.png">
                
                <div class="roomtext" >
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
            	<div class="roomtitle" >5042</div>
            	<img class="roomimgy" style="left: 626px; top: 510px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5042-2.png">
            	<img class="roomimg" style="left: 626px; top: 510px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5042-1.png">
                
                <div class="roomtext" style="left:750px !important;">
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
            	<div class="roomtitle" >5018</div>
            	<img class="roomimgy" style="left: 960px; top: 575px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5018-2.png">
            	<img class="roomimg" style="left: 960px; top: 575px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/5018-1.png">
                
                <div class="roomtext" >
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
    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/4hxyl/istyle-Rmenu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/4hxyl/floor.js"></script>
</body>
</html>
