<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
    <meta name="decorator" content="iframe"/>
    <title>4号学院楼2层</title>
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
                        <div class="unlist">
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingThreeFloor">点击查看三层</a>
                      </div>
                        <div class="actlist">    
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
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/4hxyl/02.png"/>
          
            <div class="roomdiv" u0="0" u1="${labRoom2073.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="left:819px !important;top: 213px !important;;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2073.id}" target="_blank">2077-2073</a></div>
            	<img class="roomimgy" style="left: 784px; top: 232px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2077-2073-2.png">
            	<img class="roomimg" style="left: 784px; top: 232px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2077-2073-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2073.labRoomNumber}</span><br/>
							<span>名称：${labRoom2073.labRoomName}</span><br/>
							<span>使用面积：${labRoom2073.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2073.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2073.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>
            				



            <div class="roomdiv" u0="0" u1="${labRoom2065.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="left:968px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2065.id}" target="_blank">2071-2065</a></div>
            	<img class="roomimgy" style="left: 883px; top: 251px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2071-2065-2.png">
            	<img class="roomimg" style="left: 883px; top: 251px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2071-2065-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2065.labRoomNumber}</span><br/>
							<span>名称：${labRoom2065.labRoomName}</span><br/>
							<span>使用面积：${labRoom2065.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2065.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2065.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom2057.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:320px !important;left:1101px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2057.id}" target="_blank">2063-2057</a></div>
            	<img class="roomimgy" style="left: 984px; top: 269px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2063-2057-2.png">
            	<img class="roomimg" style="left: 984px; top: 269px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2063-2057-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2057.labRoomNumber}</span><br/>
							<span>名称：${labRoom2057.labRoomName}</span><br/>
							<span>使用面积：${labRoom2057.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2057.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2057.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2010.id}" target="_blank">2010</a></div>
            	<img class="roomimgy" style="left: 1032px; top: 468px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2010-2.png">
            	<img class="roomimg" style="left: 1032px; top: 468px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2010-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2010.labRoomNumber}</span><br/>
							<span>名称：${labRoom2010.labRoomName}</span><br/>
							<span>使用面积：${labRoom2010.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2010.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom2064.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:95px !important; left:1013px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2064.id}" target="_blank">2066-2064</a></div>
            	<img class="roomimgy" style="left: 951px; top: 137px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2066-2064-2.png">
            	<img class="roomimg" style="left: 951px; top: 137px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2066-2064-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2064.labRoomNumber}</span><br/>
							<span>名称：${labRoom2064.labRoomName}</span><br/>
							<span>使用面积：${labRoom2064.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2064.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2064.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>



			
            <div class="roomdiv" u0="0" u1="${labRoom2060.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:134px !important; left:1068px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2060.id}" target="_blank">2062-2060</a></div>
            	<img class="roomimgy" style="left: 1024px; top: 150px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2062-2060-2.png">
            	<img class="roomimg" style="left: 1024px; top: 150px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2062-2060-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2060.labRoomNumber}</span><br/>
							<span>名称：${labRoom2060.labRoomName}</span><br/>
							<span>使用面积：${labRoom2060.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2060.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2060.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>



            <div class="roomdiv" u0="0" u1="${labRoom2056.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:173px !important; left:1130px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2056.id}" target="_blank">2058-2056</a></div>
            	<img class="roomimgy" style="left: 1072px; top: 157px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2058-2056-2.png">
            	<img class="roomimg" style="left: 1072px; top: 157px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2058-2056-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2056.labRoomNumber}</span><br/>
							<span>名称：${labRoom2056.labRoomName}</span><br/>
							<span>使用面积：${labRoom2056.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2056.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2056.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>



            
            <div class="roomdiv" u0="0" u1="${labRoom2068.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:56px !important; left:886px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2068.id}" target="_blank">2074-2068</a></div>
            	<img class="roomimgy" style="left: 875px; top: 123px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2074-2068-2.png">
            	<img class="roomimg" style="left: 875px; top: 123px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2074-2068-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2068.labRoomNumber}</span><br/>
							<span>名称：${labRoom2068.labRoomName}</span><br/>
							<span>使用面积：${labRoom2068.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2068.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2068.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>
            
            <div class="roomdiv" u0="0" u1="${labRoom2162.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2162.id}" target="_blank">2162</a></div>
            	<img class="roomimgy" style="left: 412px; top: 40px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2162-2.png">
            	<img class="roomimg" style="left: 412px; top: 40px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2162-1.png">
                
                <div class="roomtext" style="left:520px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2162.labRoomNumber}</span><br/>
							<span>名称：${labRoom2162.labRoomName}</span><br/>
							<span>使用面积：${labRoom2162.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2162.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2162.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom2166.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="left:308px !Important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2166.id}" target="_blank">2166</a></div>
            	<img class="roomimgy" style="left: 357px; top: 30px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2166-2.png">
            	<img class="roomimg" style="left: 357px; top: 30px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2166-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2166.labRoomNumber}</span><br/>
							<span>名称：${labRoom2166.labRoomName}</span><br/>
							<span>使用面积：${labRoom2166.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2166.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2166.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>			


          
            
            <div class="roomdiv" u0="0" u1="${labRoom2158.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="left:514px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2158.id}" target="_blank">2158</a></div>
            	<img class="roomimgy" style="left: 456px; top: 48px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2158-2.png">
            	<img class="roomimg" style="left: 456px; top: 48px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2158-1.png">
                
                <div class="roomtext" style="left:680px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2158.labRoomNumber}</span><br/>
							<span>名称：${labRoom2158.labRoomName}</span><br/>
							<span>使用面积：${labRoom2158.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2158.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2158.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom2144_2150.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="left:610px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2144_2150.id}" target="_blank">2150-2144</a></div>
            	<img class="roomimgy" style="left: 548px; top: 64px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2150-2144-2.png">
            	<img class="roomimg" style="left: 548px; top: 64px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2150-2144-1.png">
                
                <div class="roomtext" style="left:250px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2144_2150.labRoomNumber}</span><br/>
							<span>名称：${labRoom2144_2150.labRoomName}</span><br/>
							<span>使用面积：${labRoom2144_2150.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2144_2150.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2144_2150.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom2136_2140.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:98px !important; left:762px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2136_2140.id}" target="_blank">2140-2136</a></div>
            	<img class="roomimgy" style="left: 669px; top: 86px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2140-2136-2.png">
            	<img class="roomimg" style="left: 669px; top: 86px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2140-2136-1.png">
                
                <div class="roomtext" style="left:380px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2136_2140.labRoomNumber}</span><br/>
							<span>名称：${labRoom2136_2140.labRoomName}</span><br/>
							<span>使用面积：${labRoom2136_2140.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2136_2140.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2136_2140.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


            <div class="roomdiv" u0="0" u1="${labRoom2131_2133.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:325px !important;left:640px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2131_2133.id}" target="_blank">2133-2131</a></div>
            	<img class="roomimgy" style="left: 684px; top: 186px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2133-2131-2.png">
            	<img class="roomimg" style="left: 684px; top: 186px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2133-2131-1.png">
                
                <div class="roomtext" style="left:300px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2131_2133.labRoomNumber}</span><br/>
							<span>名称：${labRoom2131_2133.labRoomName}</span><br/>
							<span>使用面积：${labRoom2131_2133.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2131_2133.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2131_2133.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom2135_2137.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:174px !important; left:678px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2135_2137.id}" target="_blank">2137-2135</a></div>
            	<img class="roomimgy" style="left: 630px; top: 176px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2137-2135-2.png">
            	<img class="roomimg" style="left: 630px; top: 176px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2137-2135-1.png">
                
                <div class="roomtext" style="left:320px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2135_2137.labRoomNumber}</span><br/>
							<span>名称：${labRoom2135_2137.labRoomName}</span><br/>
							<span>使用面积：${labRoom2135_2137.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2135_2137.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2135_2137.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


            <div class="roomdiv" u0="0" u1="${labRoom2139_2141.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"  style="top:298px !important;left:482px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2139_2141.id}" target="_blank">2141-2139</a></div>
            	<img class="roomimgy" style="left: 583px; top: 167px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2141-2139-2.png">
            	<img class="roomimg" style="left: 583px; top: 167px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2141-2139-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2139_2141.labRoomNumber}</span><br/>
							<span>名称：${labRoom2139_2141.labRoomName}</span><br/>
							<span>使用面积：${labRoom2139_2141.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2139_2141.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2139_2141.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>




			 <div class="roomdiv" u0="0" u1="${labRoom2147_2153.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:215px !important; left:383px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2147_2153.id}" target="_blank">2153-2147</a></div>
            	<img class="roomimgy" style="left: 464px; top: 143px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2153-2147-2.png">
            	<img class="roomimg" style="left: 464px; top: 143px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2153-2147-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2147_2153.labRoomNumber}</span><br/>
							<span>名称：${labRoom2147_2153.labRoomName}</span><br/>
							<span>使用面积：${labRoom2147_2153.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2147_2153.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2147_2153.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>
            
 

			 <div class="roomdiv" u0="0" u1="${labRoom2143_2145.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:135px !important;left:584px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2143_2145.id}" target="_blank">2145-2143</a></div>
            	<img class="roomimgy" style="left: 535px; top: 160px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2145-2143-2.png">
            	<img class="roomimg" style="left: 535px; top: 160px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2145-2143-1.png">
                
                <div class="roomtext" style="left:250px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2143_2145.labRoomNumber}</span><br/>
							<span>名称：${labRoom2143_2145.labRoomName}</span><br/>
							<span>使用面积：${labRoom2143_2145.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2143_2145.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2143_2145.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>			

			 <div class="roomdiv" u0="0" u1="${labRoom2169_2171.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2169_2171.id}" target="_blank">2169-2171</a></div>
            	<img class="roomimgy" style="left: 241px; top: 105px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3171-3169-2.png">
            	<img class="roomimg" style="left: 241px; top: 105px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/3171-3169-1.png">
                
                <div class="roomtext" style="left:420px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2169_2171.labRoomNumber}</span><br/>
							<span>名称：${labRoom2169_2171.labRoomName}</span><br/>
							<span>使用面积：${labRoom2169_2171.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2169_2171.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2169_2171.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>



			 <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:125px !important;left:364px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2163_2165.id}" target="_blank">2165-2163</a></div>
            	<img class="roomimgy" style="left: 325px; top: 119px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2165-2163-2.png">
            	<img class="roomimg" style="left: 325px; top: 119px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2165-2163-1.png">
                
                <div class="roomtext" style="left:550px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2163_2165.labRoomNumber}</span><br/>
							<span>名称：${labRoom2163_2165.labRoomName}</span><br/>
							<span>使用面积：${labRoom2163_2165.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2163_2165.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
				  </div>
              </div>
            </div>



          <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:274px !important; left:200px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2118_2120.id}" target="_blank">2120-2118</a></div>
            	<img class="roomimgy" style="left: 114px; top: 273px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2120-2118-2.png">
            	<img class="roomimg" style="left: 114px; top: 273px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2120-2118-1.png">
                
                <div class="roomtext" style="left:360px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2118_2120.labRoomNumber}</span><br/>
							<span>名称：${labRoom2118_2120.labRoomName}</span><br/>
							<span>使用面积：${labRoom2118_2120.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2118_2120.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>


          <div class="roomdiv" u0="0" u1="${labRoom2122.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle"  style="top:210px !important; left:15px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2122.id}" target="_blank">2124-2122</a></div>
            	<img class="roomimgy" style="left: 145px; top: 214px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2124-2122-2.png">
            	<img class="roomimg" style="left: 145px; top: 214px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2124-2122-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2122.labRoomNumber}</span><br/>
							<span>名称：${labRoom2122.labRoomName}</span><br/>
							<span>使用面积：${labRoom2122.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2122.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2122.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>


          <div class="roomdiv" u0="0" u1="${labRoom2170_2172.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:151px !important;left:43px !Important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2170_2172.id}" target="_blank">2170-2172</a></div>
            	<img class="roomimgy" style="left: 170px; top: 106px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2170-2172-2.png">
            	<img class="roomimg" style="left: 170px; top: 106px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2170-2172-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2170_2172.labRoomNumber}</span><br/>
							<span>名称：${labRoom2170_2172.labRoomName}</span><br/>
							<span>使用面积：${labRoom2170_2172.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2170_2172.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2170_2172.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>



          <div class="roomdiv" u0="0" u1="${labRoom2168.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:41px !important;left:166px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2168.id}" target="_blank">2168</a></div>
            	<img class="roomimgy" style="left: 227px; top: 66px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2168-2.png">
            	<img class="roomimg" style="left: 227px; top: 66px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2168-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2168.labRoomNumber}</span><br/>
							<span>名称：${labRoom2168.labRoomName}</span><br/>
							<span>使用面积：${labRoom2168.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2168.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2168.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>





          <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:345px !important;left:158px !Important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2114_2116.id}" target="_blank">2116-2114</a></div>
            	<img class="roomimgy" style="left: 82px; top: 338px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2116-2114-2.png">
            	<img class="roomimg" style="left: 82px; top: 338px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2116-2114-1.png">
                
                <div class="roomtext" style="left:320px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2114_2116.labRoomNumber}</span><br/>
							<span>名称：${labRoom2114_2116.labRoomName}</span><br/>
							<span>使用面积：${labRoom2114_2116.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2114_2116.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>






          <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:417px !Important;left:129px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2110_2112.id}" target="_blank">2112-2110</a></div>
            	<img class="roomimgy" style="left: 46px; top: 402px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2112-2110-2.png">
            	<img class="roomimg" style="left: 46px; top: 402px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2112-2110-1.png">
                
                <div class="roomtext" style="left:320px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2110_2112.labRoomNumber}</span><br/>
							<span>名称：${labRoom2110_2112.labRoomName}</span><br/>
							<span>使用面积：${labRoom2110_2112.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2110_2112.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>







           <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:525px !important; left:0px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2105_2107.id}" target="_blank">2107-2105</a></div>
            	<img class="roomimgy" style="left: 107px; top: 449px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2107-2105-2.png">
            	<img class="roomimg" style="left: 107px; top: 449px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2107-2105-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2105_2107.labRoomNumber}</span><br/>
							<span>名称：${labRoom2105_2107.labRoomName}</span><br/>
							<span>使用面积：${labRoom2105_2107.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2105_2107.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>



           <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	 <div class="roomtitle" style="top:538px !important; left:175px !Important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2101_2103.id}" target="_blank">2103-2101</a></div>
            	<img class="roomimgy" style="left: 154px; top: 458px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2103-2101-2.png">
            	<img class="roomimg" style="left: 154px; top: 458px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2103-2101-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2101_2103.labRoomNumber}</span><br/>
							<span>名称：${labRoom2101_2103.labRoomName}</span><br/>
							<span>使用面积：${labRoom2101_2103.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2101_2103.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
             </div>
          </div>



           <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:619px !important; left:483px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2043_2045.id}" target="_blank">2045-2043</a></div>
            	<img class="roomimgy" style="left: 620px; top: 548px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2045-2043-2.png">
            	<img class="roomimg" style="left: 620px; top: 548px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2045-2043-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2043_2045.labRoomNumber}</span><br/>
							<span>名称：${labRoom2043_2045.labRoomName}</span><br/>
							<span>使用面积：${labRoom2043_2045.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2043_2045.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>


            <div class="roomdiv" u0="0" u1="${labRoom2039.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:500px !important; left:700px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2039.id}" target="_blank">2041-2039</a></div>
            	<img class="roomimgy" style="left: 673px; top: 559px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2041-2039-2.png">
            	<img class="roomimg" style="left: 673px; top: 559px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2041-2039-1.png">
                
                <div class="roomtext" style="left:880px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2039.labRoomNumber}</span><br/>
							<span>名称：${labRoom2039.labRoomName}</span><br/>
							<span>使用面积：${labRoom2039.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2039.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2039.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


           <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:645px !important; left:631px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2035_2037.id}" target="_blank">2037-2035</a></div>
            	<img class="roomimgy" style="left: 726px; top: 570px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2037-2035-2.png">
            	<img class="roomimg" style="left: 726px; top: 570px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2037-2035-1.png">
                
                <div class="roomtext" style="left:250px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2035_2037.labRoomNumber}</span><br/>
							<span>名称：${labRoom2035_2037.labRoomName}</span><br/>
							<span>使用面积：${labRoom2035_2037.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2035_2037.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
                </div>
            </div>           
            



    <div class="roomdiv" u0="0" u1="${labRoom2031.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:540px !important;left:808px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2031.id}" target="_blank">2033-2031</a></div>
            	<img class="roomimgy" style="left: 781px; top: 578px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2033-2031-2.png">
            	<img class="roomimg" style="left: 781px; top: 578px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2033-2031-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2031.labRoomNumber}</span><br/>
							<span>名称：${labRoom2031.labRoomName}</span><br/>
							<span>使用面积：${labRoom2031.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2031.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2031.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>  


    <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
   	  <div class="roomtitle"  style="top:580px !important; left:858px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2027_2029.id}" target="_blank">2029-2027</a></div>
            	<img class="roomimgy" style="left: 832px; top: 587px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2029-2027-2.png">
            	<img class="roomimg" style="left: 832px; top: 587px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2029-2027-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2027_2029.labRoomNumber}</span><br/>
							<span>名称：${labRoom2027_2029.labRoomName}</span><br/>
							<span>使用面积：${labRoom2027_2029.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2027_2029.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
					</div>
      </div>
          </div>  
            


   <div class="roomdiv" u0="0" u1="${labRoom2023.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:620px !important; left:955px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom2023.id}" target="_blank">2025-2023</a></div>
            	<img class="roomimgy" style="left: 884px; top: 600px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2025-2023-2.png">
            	<img class="roomimg" style="left: 884px; top: 600px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/2025-2023-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom2023.labRoomNumber}</span><br/>
							<span>名称：${labRoom2023.labRoomName}</span><br/>
							<span>使用面积：${labRoom2023.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom2023.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom2023.labRoomPhone}%</span><br/>
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
