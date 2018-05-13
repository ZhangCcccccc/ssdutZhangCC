<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!doctype html>
<html>
<head>
    <meta name="decorator" content="iframe"/>
    <title>4号学院楼1层</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/4hxyl/floorview.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/4hxyl/istyle-Rmenu.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualization/4hxyl/allpages.css"/>
</head>

<body>
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
                        <div class="unlist">    
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingTwoFloor">点击查看二层</a>
                      </div>
                        <div class="actlist">
                            <a href="${pageContext.request.contextPath}/visualization/fourBuildingOneFloor">点击查看一层</a>
                        </div>

                    </div>
                </div>
                <div class="Rmenu-list">
                    <span class="unlist">查看选项</span>
                     <div id="buttonbox">
                        <div class="actlist" onClick="shown(0)">查看分布地图</div>
                        <div class="unlist" onClick="shown(1)">查看实验室利用率</div>
                       <!--  <div class="unlist" onClick="shown(2)">查看实验项开出率</div>
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
       	  <img class="backgroundimg" src="${pageContext.request.contextPath}/images/visualization/4hxyl/01.png"/>
          
            

            <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1073_1077.id}" target="_blank">1077-1073</a></div>
            	<img class="roomimgy" style="left: 785px; top: 236px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1077-1073-2.png">
            	<img class="roomimg" style="left: 785px; top: 236px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1077-1073-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1073_1077.labRoomNumber}</span><br/>
							<span>名称：${labRoom1073_1077.labRoomName}</span><br/>
							<span>使用面积：${labRoom1073_1077.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1073_1077.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
				  </div>
              </div>
            </div>
            				



            <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:250px !important;left:914px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1071.id}" target="_blank">1071</a></div>
            	<img class="roomimgy" style="left: 885px; top: 255px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1071-2.png">
            	<img class="roomimg" style="left: 885px; top: 255px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1071-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1071.labRoomNumber}</span><br/>
							<span>名称：${labRoom1071.labRoomName}</span><br/>
							<span>使用面积：${labRoom1071.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1071.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
				  </div>
              </div>
            </div>



            <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1063.id}" target="_blank">1063</a></div>
            	<img class="roomimgy" style="left: 986px; top: 274px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1063-2.png">
            	<img class="roomimg" style="left: 986px; top: 274px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1063-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1063.labRoomNumber}</span><br/>
							<span>名称：${labRoom1063.labRoomName}</span><br/>
							<span>使用面积：${labRoom1063.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1063.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom1057.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:285px !important; left:1102px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1057.id}" target="_blank">1061-1057</a></div>
            	<img class="roomimgy" style="left: 1011px; top: 277px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1061-1057-2.png">
            	<img class="roomimg" style="left: 1011px; top: 277px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1061-1057-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1057.labRoomNumber}</span><br/>
							<span>名称：${labRoom1057.labRoomName}</span><br/>
							<span>使用面积：${labRoom1057.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1057.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1057.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


            <div class="roomdiv" u0="0" u1="${labRoom1004.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1004.id}" target="_blank">1004</a></div>
            	<img class="roomimgy" style="left: 1064px; top: 409px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1004-2.png">
            	<img class="roomimg" style="left: 1064px; top: 409px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1004-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1004.labRoomNumber}</span><br/>
							<span>名称：${labRoom1004.labRoomName}</span><br/>
							<span>使用面积：${labRoom1004.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1004.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1004.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


            <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="left:987px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1010.id}" target="_blank">1010</a></div>
            	<img class="roomimgy" style="left: 1032px; top: 469px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1010-2.png">
            	<img class="roomimg" style="left: 1032px; top: 469px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1010-1.png">
                
                <div class="roomtext" style="left:650px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1010.labRoomNumber}</span><br/>
							<span>名称：${labRoom1010.labRoomName}</span><br/>
							<span>使用面积：${labRoom1010.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1010.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


            <div class="roomdiv" u0="0" u1="${labRoom1064.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1064.id}" target="_blank">1066-1064</a></div>
            	<img class="roomimgy" style="left: 950px; top: 138px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1066-1064-2.png">
            	<img class="roomimg" style="left: 950px; top: 138px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1066-1064-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1064.labRoomNumber}</span><br/>
							<span>名称：${labRoom1064.labRoomName}</span><br/>
							<span>使用面积：${labRoom1064.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1064.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1064.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>
			
            <div class="roomdiv" u0="0" u1="${labRoom1060.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:200px !important;left:1087px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1060.id}" target="_blank">1060</a></div>
            	<img class="roomimgy" style="left: 1023px; top: 155px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1060-2.png">
            	<img class="roomimg" style="left: 1023px; top: 155px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1060-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1060.labRoomNumber}</span><br/>
							<span>名称：${labRoom1060.labRoomName}</span><br/>
							<span>使用面积：${labRoom1060.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1060.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1060.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>
            
            <div class="roomdiv" u0="0" u1="0" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="left:778px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1070_1074.id}" target="_blank">1074-1070</a></div>
            	<img class="roomimgy" style="left: 876px; top: 127px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1074-1070-2.png">
            	<img class="roomimg" style="left: 876px; top: 127px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1074-1070-1.png">
                
                <div class="roomtext" style="left:450px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1070_1074.labRoomNumber}</span><br/>
							<span>名称：${labRoom1070_1074.labRoomName}</span><br/>
							<span>使用面积：${labRoom1070_1074.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1070_1074.labRoomCapacity}人</span><br/>
							<span>实验室利用率：0%</span><br/>
                        </div>
				  </div>
              </div>
            </div>
            
            <div class="roomdiv" u0="0" u1="${labRoom1156.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:0px !important; left:428px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1156.id}" target="_blank">1156</a></div>
            	<img class="roomimgy" style="left: 457px; top: 51px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1156-2.png">
            	<img class="roomimg" style="left: 457px; top: 51px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1156-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1156.labRoomNumber}</span><br/>
							<span>名称：${labRoom1156.labRoomName}</span><br/>
							<span>使用面积：${labRoom1156.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1156.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1156.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom1160_1166.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="left:243px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1160_1166.id}" target="_blank">1166-1160</a></div>
            	<img class="roomimgy" style="left: 357px; top: 31px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1166-1160-2.png">
            	<img class="roomimg" style="left: 357px; top: 31px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1166-1160-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1160_1166.labRoomNumber}</span><br/>
							<span>名称：${labRoom1160_1166.labRoomName}</span><br/>
							<span>使用面积：${labRoom1160_1166.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1160_1166.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1160_1166.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>			
          
            
            <div class="roomdiv" u0="0" u1="${labRoom1152_1154.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1152_1154.id}" target="_blank">1154-1152</a></div>
            	<img class="roomimgy" style="left: 500px; top: 59px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1154-1152-2.png">
            	<img class="roomimg" style="left: 500px; top: 59px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1154-1152-1.png">
                
                <div class="roomtext" style="left:680px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1152_1154.labRoomNumber}</span><br/>
							<span>名称：${labRoom1152_1154.labRoomName}</span><br/>
							<span>使用面积：${labRoom1152_1154.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1152_1154.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1152_1154.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom1144_1150.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:62px !important; left:658px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1144_1150.id}" target="_blank">1150-1144</a></div>
            	<img class="roomimgy" style="left: 548px; top: 65px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1150-1144-2.png">
            	<img class="roomimg" style="left: 548px; top: 65px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1150-1144-1.png">
                
                <div class="roomtext" style="left:300px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1144_1150.labRoomNumber}</span><br/>
							<span>名称：${labRoom1144_1150.labRoomName}</span><br/>
							<span>使用面积：${labRoom1144_1150.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1144_1150.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1144_1150.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom1141_1143.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:298px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1141_1143.id}" target="_blank">1143-1141</a></div>
            	<img class="roomimgy" style="left: 532px; top: 163px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1143-1141-2.png">
            	<img class="roomimg" style="left: 532px; top: 163px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1143-1141-1.png">
                
                <div class="roomtext" style="left:700px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1141_1143.labRoomNumber}</span><br/>
							<span>名称：${labRoom1141_1143.labRoomName}</span><br/>
							<span>使用面积：${labRoom1141_1143.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1141_1143.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1141_1143.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom1147.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"  style="top:218px !important;left:458px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1147.id}" target="_blank">1147</a></div>
            	<img class="roomimgy" style="left: 514px; top: 155px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1147-2.png">
            	<img class="roomimg" style="left: 514px; top: 155px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1147-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1147.labRoomNumber}</span><br/>
							<span>名称：${labRoom1147.labRoomName}</span><br/>
							<span>使用面积：${labRoom1147.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1147.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1147.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

            <div class="roomdiv" u0="0" u1="${labRoom1153_1157.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:148px !important; left:513px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1153_1157.id}" target="_blank">1157-1153</a></div>
            	<img class="roomimgy" style="left: 416px; top: 139px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1157-1153-2.png">
            	<img class="roomimg" style="left: 416px; top: 139px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1157-1153-1.png">
                
                <div class="roomtext" style="left:700px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1153_1157.labRoomNumber}</span><br/>
							<span>名称：${labRoom1153_1157.labRoomName}</span><br/>
							<span>使用面积：${labRoom1153_1157.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1153_1157.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1153_1157.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

			 <div class="roomdiv" u0="0" u1="${labRoom1159_1161.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1159_1161.id}" target="_blank">1161-1159</a></div>
            	<img class="roomimgy" style="left: 377px; top: 130px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1161-1159-2.png">
            	<img class="roomimg" style="left: 377px; top: 130px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1161-1159-1.png">
                
                <div class="roomtext" style="left:600px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1159_1161.labRoomNumber}</span><br/>
							<span>名称：${labRoom1159_1161.labRoomName}</span><br/>
							<span>使用面积：${labRoom1159_1161.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1159_1161.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1159_1161.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

			 <div class="roomdiv" u0="0" u1="${labRoom1169_1171.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="top:111px !important;left:142px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1071.id}" target="_blank">1169-1171</a></div>
            	<img class="roomimgy" style="left: 244px; top: 111px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1171-1169-2.png">
            	<img class="roomimg" style="left: 244px; top: 111px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1171-1169-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1169_1171.labRoomNumber}</span><br/>
							<span>名称：${labRoom1169_1171.labRoomName}</span><br/>
							<span>使用面积：${labRoom1169_1171.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1169_1171.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1169_1171.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>

			 <div class="roomdiv" u0="0" u1="${labRoom1165.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
           	  <div class="roomtitle" style="left:302px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1165.id}" target="_blank">1165</a></div>
            	<img class="roomimgy" style="left: 322px; top: 120px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1165-2.png">
            	<img class="roomimg" style="left: 322px; top: 120px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1165-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1165.labRoomNumber}</span><br/>
							<span>名称：${labRoom1165.labRoomName}</span><br/>
							<span>使用面积：${labRoom1165.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1165.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1165.labRoomPhone}%</span><br/>
                        </div>
				  </div>
              </div>
            </div>


          <div class="roomdiv" u0="0" u1="${labRoom1109.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1109.id}" target="_blank">1110-1109</a></div>
            	<img class="roomimgy" style="left: 50px; top: 403px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1110-1109-2.png">
            	<img class="roomimg" style="left: 50px; top: 403px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1110-1109-1.png">
                
                <div class="roomtext" style="left:243px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1109.labRoomNumber}</span><br/>
							<span>名称：${labRoom1109.labRoomName}</span><br/>
							<span>使用面积：${labRoom1109.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1109.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1109.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>

           <div class="roomdiv" u0="0" u1="${labRoom1101.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1101.id}" target="_blank">1107-1101</a></div>
            	<img class="roomimgy" style="left: 109px; top: 453px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1107-1101-2.png">
            	<img class="roomimg" style="left: 109px; top: 453px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1107-1101-1.png">
                
                <div class="roomtext" style="left:300px !important;">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1101.labRoomNumber}</span><br/>
							<span>名称：${labRoom1101.labRoomName}</span><br/>
							<span>使用面积：${labRoom1101.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1101.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1101.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>

           <div class="roomdiv" u0="0" u1="${labRoom1093.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" style="top:473px !important;left:270px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1093.id}" target="_blank">1099-1093</a></div>
            	<img class="roomimgy" style="left: 200px; top: 469px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1099-1093-2.png">
            	<img class="roomimg" style="left: 200px; top: 469px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1099-1093-1.png">
                
                <div class="roomtext" style="left:450px !important;">
                	<div>
                    	<div class="sh">
							<span>名称：${labRoom1093.labRoomName}</span><br/>
							<span>使用面积：${labRoom1093.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1093.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1093.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>



           <div class="roomdiv" u0="0" u1="${labRoom1039.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" ><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1039.id}" target="_blank">1043-1039</a></div>
            	<img class="roomimgy" style="left: 597px; top: 513px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1043-1039-2.png">
            	<img class="roomimg" style="left: 597px; top: 513px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1043-1039-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1039.labRoomNumber}</span><br/>
							<span>名称：${labRoom1039.labRoomName}</span><br/>
							<span>使用面积：${labRoom1039.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1039.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1039.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>



           <div class="roomdiv" u0="0" u1="${labRoom1025.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle" ><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1025.id}" target="_blank">1029-1025</a></div>
            	<img class="roomimgy" style="left: 834px; top: 593px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1029-1025-2.png">
            	<img class="roomimg" style="left: 834px; top: 593px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1029-1025-1.png">
                
                <div class="roomtext" >
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1025.labRoomNumber}</span><br/>
							<span>名称：${labRoom1025.labRoomName}</span><br/>
							<span>使用面积：${labRoom1025.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1025.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1025.labRoomPhone}%</span><br/>
                        </div>
					</div>
                </div>
            </div>           
            



    <div class="roomdiv" u0="0" u1="${labRoom1019.labRoomPhone}" u2="1" u3="0.1" u4="0.7" u5="0.66" u6="0.74">
            	<div class="roomtitle"  style="top:696px !important;"><a href="${pageContext.request.contextPath}/visualization/labRoomDetail?id=${labRoom1019.id}" target="_blank">1023-1019</a></div>
            	<img class="roomimgy" style="left: 917px; top: 611px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1023-2.png">
            	<img class="roomimg" style="left: 917px; top: 611px;" src="${pageContext.request.contextPath}/images/visualization/4hxyl/1023-1.png">
                
                <div class="roomtext">
                	<div>
                    	<div class="sh">
							<span>编号：${labRoom1019.labRoomNumber}</span><br/>
							<span>名称：${labRoom1019.labRoomName}</span><br/>
							<span>使用面积：${labRoom1019.labRoomArea}平方米</span><br/>
							<span>实验室容量：${labRoom1019.labRoomCapacity}人</span><br/>
							<span>实验室利用率：${labRoom1019.labRoomPhone}%</span><br/>
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
