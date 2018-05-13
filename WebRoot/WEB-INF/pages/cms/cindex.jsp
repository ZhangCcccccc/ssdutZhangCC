<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta name="decorator" content="cmscindex"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="Keywords" content="">
	<meta name="Author" content="chenyawen">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
   <title>西安电子科技大学</title>
    <!--<link href="css/lib.css" rel="stylesheet" type="text/css">-->
    <!--<link href="css/font.css" rel="stylesheet" type="text/css">-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/slide.js"></script>
</head>

<body>
    
    <div class="layout_main_container">
        <div class="layout_slider">
            <div class="left_banner">
                <img src="${pageContext.request.contextPath}/images/cms/16.jpg" class="info_bg"/>
        	    <div class="info_box">
        	        <p style="text-indent: 25px;">重视基础 跟踪前沿</p>
                    <p style="text-indent: 74px;">突出应用 体现创新</p>
                </div>
            </div>
            <div class="container">
                <div id="slides">
               <c:forEach  items="${links}"  var="link">
                    <a href="${link.linkUrl}" target="_blank">
                        <img src="${pageContext.request.contextPath}${link.cmsResource.url}" alt="西安电子科技大学"/ width="100%">
                     </a>
						</c:forEach>
                    
                    
                    <a href="#" class="slidesjs-previous slidesjs-navigation"><i class="icon-chevron-left icon-large"></i></a>
                    <a href="#" class="slidesjs-next slidesjs-navigation"><i class="icon-chevron-right icon-large"></i></a>
                </div>

            </div>
            <script>
            </script>
        </div>
        <div class="layout_middle_container">
            <div class="layout_middle_left">
                <div class="layout_middle_top">
                    <div class="left_log_in">
                        <video controls="controls" autoplay>
                             <source src="${pageContext.request.contextPath}/${CmsResource.url}" type="video/mp4">
                             <source src="${pageContext.request.contextPath}/${CmsResource.url}" type="video/ogg">
                             <source src="${pageContext.request.contextPath}/${CmsResource.url}" type="video/webm">
                             <object data="movie/xidlims.mp4">
                                 <embed src="${pageContext.request.contextPath}/${CmsResource.url}">
                             </object> 
                        </video>                        
                    </div>
                    <div class="right_news">
                        <div class="news_container">
                            <div class="news_list_container news_selected">
                                    
                                    
                                <span class="container_title title_selected"><a href="${pageContext.request.contextPath}/cms/channel/11?currpage=1">通知公告</a></span>
                                <ul class="news_list ">
                                
                                 <c:forEach  items="${artcles}"  var="SchoolNew">
                                 <c:if test="${SchoolNew.cmsChannel.title eq '通知公告'}">
                                  <li><div class="lightbtn">▶</div>
                                  <a href="${pageContext.request.contextPath}/cms/article/${SchoolNew.id}">${SchoolNew.title}</a>
                                  
                                  
                                 <span class="time"><fmt:formatDate type="date"  pattern="yy-MM-dd" value="${SchoolNew.createTime.time }"/></span>
                                   
                                  </li>
                                   </c:if>
                                   </c:forEach>
                                  
                                </ul> 
                            </div>
                            <div class="news_list_container">
                                <span class="container_title"><a href="${pageContext.request.contextPath}/cms/channel/12?currpage=1">学院新闻</a></span>
                                <ul class="news_list">
                                    <c:forEach  items="${artcles}"  var="SchoolNew">
                                 <c:if test="${SchoolNew.cmsChannel.title eq '学院新闻'}">
                                  <li><div class="lightbtn">▶</div>
                                  <a href="${pageContext.request.contextPath}/cms/article/${SchoolNew.id}">${SchoolNew.title}</a>
                                  <span class="time"><fmt:formatDate type="date"  pattern="yy-MM-dd" value="${SchoolNew.createTime.time }"/></span>
                                  
                                  
                                  </li>
                                   </c:if>
                                   </c:forEach>
                                </ul>
                            </div>
                             <div class="news_list_container">
                                <span class="container_title"><a href="${pageContext.request.contextPath}/cms/channel/49?currpage=1">常用下载</a></span>
                                <ul class="news_list">
                                    <c:forEach  items="${artcles}"  var="download">
                                 <c:if test="${download.cmsChannel.title eq '常用下载'}">
                                  <li><div class="lightbtn">▶</div>
                                  <a href="${pageContext.request.contextPath}/${download.cmsDocument.url}" download="${download.cmsDocument.profile }">${download.cmsDocument.profile}</a>
                                  <span class="time"><fmt:formatDate type="date"  pattern="yy-MM-dd" value="${download.createTime.time }"/></span>
                                  
                                  
                                  </li>
                                   </c:if>
                                   </c:forEach>
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
                <div clsss="layout_middle_bottom">
                    <div id='demo'>
                        <div id='indemo'>
                            <div id='demo1'>
                                <div>
                                <c:forEach items="${cmsLinks}" var="var">
                                    <img src="${pageContext.request.contextPath}/${var.cmsResource.url}" >
                                    </c:forEach>
                                </div>
                             </div>
                            <div id='demo2'></div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="layout_middle_right">
                <div class="icon-box">
			        <div class="tag mb30 ml10 pt7">
			            <img class="img-tag" src="${pageContext.request.contextPath}/images/cms/jiaoxueziyuan.jpg"/>
			           <span id="res" class="title-tag">实验室开放预约</span>
			        </div>
			        <div class="tag mb30 pt7">
			            <img class="img-tag" src="${pageContext.request.contextPath}/images/cms/tushuguan.jpg"/>
			            <span id="manage" class="title-tag">实验室管理</span>
		         	</div>			
		         	<div class="tag mb30 ml10">
			            <img class="img-tag" src="${pageContext.request.contextPath}/images/cms/xiaoyuanyikatong.jpg"/>
			           <span id="dev-res" class="title-tag">实验设备开放预约</span>
	        		</div>
         			<div class="tag mb30">
		        	    <img class="img-tag" src="${pageContext.request.contextPath}/images/cms/xiaolingdaoxinxiang.jpg"/>
        			     <span id="dev-mag" class="title-tag">实验设备管理</span>
        			</div>
		        	<div class="tag ml10 pb7">
	        		    <img class="img-tag" src="${pageContext.request.contextPath}/images/cms/gonggongxinxichaxun.jpg"/>
        			    <span id="asset" class="title-tag">药品溶液管理</span>
        			</div>	
        			<div class="tag pb7">
        			    <img class="img-tag" src="${pageContext.request.contextPath}/images/cms/wangfeichaxun.jpg"/>
        			    <span id="time" class="title-tag">实验预约排课</span>
			        </div>
	        	</div>		
            </div>
        </div>

        <div class="layout_bottom_container">
            <ul class="bottom_link">
            <c:if test="${not empty loginUser }">
                <a href="${pageContext.request.contextPath}/checkCenter" >
                    <li>
                		<div class="zoom_img">
                            <img src="${pageContext.request.contextPath}/images/cms/m1-3.jpg">
                        </div>
                        <!--<div class="grey"></div>-->
                        <span id="item" class="bottom_link_info">实验项目展示</span>
                    </li>
                </a>
                </c:if>
             <c:if test="${empty loginUser }">
             	 <li id="itemShow">
                		<div class="zoom_img">
                            <img src="${pageContext.request.contextPath}/images/cms/m1-3.jpg">
                        </div>
                        <!--<div class="grey"></div>-->
                        <span id="item" class="bottom_link_info">实验项目展示</span>
                        
                    </li>
             </c:if>  
             <%--<c:if test="${not empty loginUser }">  
                <a href="${pageContext.request.contextPath}/visualization/show/index" >
                    <li>
                		<div class="zoom_img">
     			            <img src="${pageContext.request.contextPath}/images/cms/m2-6.jpg"> 
     			        </div>
     			        <!--<div class="grey"></div>-->
                        <span class="bottom_link_info">知识地图</span>
                    </li>
                </a>
                </c:if>--%>
                <%--<c:if test="${empty loginUser }">
                 <li id="know_map">
                		<div class="zoom_img">
     			            <img src="${pageContext.request.contextPath}/images/cms/m2-6.jpg"> 
     			        </div>
     			        <!--<div class="grey"></div>-->
                        <span class="bottom_link_info">知识地图</span>
                    </li>
                </c:if>--%>
                <a href="${pageContext.request.contextPath}/tms/index" target="_blank">
                    <li>
                		<div class="zoom_img">
                            <img src="${pageContext.request.contextPath}/images/cms/m3.jpg"> 
                        </div>
                        <!--<div class="grey"></div>-->
                        <span class="bottom_link_info">微课平台</span>
                    </li>
                </a>
                <c:if test="${not empty loginUser }">
                <a href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#/xidlims/asset/listAssetApps?currpage=1&assetAuditStatus=9" >
                    <li>
                		<div class="zoom_img">
     			            <img src="${pageContext.request.contextPath}/images/cms/m4-1.jpg"> 
     			        </div>
     			        <!--<div class="grey"></div>-->
                        <span class="bottom_link_info">药品申购预算</span>
                    </li>
                </a>
                </c:if>
                <c:if test="${empty loginUser }">
                  <li id="asset_res">
                 		<div class="zoom_img">
      			            <img src="${pageContext.request.contextPath}/images/cms/m4-1.jpg"> 
      			        </div>
     			        <!--<div class="grey"></div>-->
                         <span class="bottom_link_info">药品申购预算</span>
                     </li>
                 </c:if>
                 <c:if test="${not empty loginUser }">
                 <a href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#/xidlims/report/teachingReport/monthRegister?currpage=1" >
                    <li>
                		<div class="zoom_img">
                            <img src="${pageContext.request.contextPath}/images/cms/m4.jpg"> 
                        </div>
                        <!--<div class="grey"></div>-->
                        <span class="bottom_link_info">系统报表1</span>
                    </li>
                </a>
                </c:if>
               <c:if test="${empty loginUser }">
                 	<li id="system">
                 		<div class="zoom_img">
                             <img src="${pageContext.request.contextPath}/images/cms/m4.jpg"> 
                         </div>
                         <!--<div class="grey"></div>-->
                         <span class="bottom_link_info">系统报表2</span>
                     </li>
                 </c:if>
            </ul>
           
            <div class="stitle_all">
            	<div class="stitle_bg"></div>
            	<div class="stitle_container">
            	<div class="ma oh">
            		<div class="l stitle">相关链接</div>
            		<a href="#" class="more_btn r mr10">more</a>
            	</div>
            	<!--<hr class="hr_link"/>-->
            	<div class="oh">
            	    <div class="stitle_div">
            		    <span class="title_img"></span>
            	        <a href="http://www.xidian.edu.cn/" target="_blank">西安电子科技大学</a>
            	    </div>
            	    
            	</div>
            	</div>
            </div>

            <div class="bottom2_link">
            <div class="bottom_left">
            <span class="bottom_title">快速链接</span>
            <ul class="bottom_link2">
                <li>
                	<a href="http://cgpt.suda.edu.cn" target="_blank">
                        <img src="${pageContext.request.contextPath}/images/cms/17.jpg">
                        <div class="grey2"></div>
                        <img class="bottom-pic2" src="${pageContext.request.contextPath}/images/cms/b1.png"/>
                        <span class="bottom_link_info2">设备采购</span>
                	</a>
                </li>
                <li>
                	<a href="http://sylc.suda.edu.cn" target="_blank">
     			        <img src="${pageContext.request.contextPath}/images/cms/13.jpg">    
     			        <div class="grey2"></div>
                        <img class="bottom-pic2" src="${pageContext.request.contextPath}/images/cms/b2-1.png"/>
                        <span class="bottom_link_info2">耗材采购</span>
                	</a>
                </li>
                <li>
                	<a href="http://222.192.23.250/WFManager/login.html" target="_blank">
                        <img src="${pageContext.request.contextPath}/images/cms/14.jpg"> 
                        <div class="grey2"></div>
                        <img class="bottom-pic2" src="${pageContext.request.contextPath}/images/cms/b3-1.png"/>
                        <span class="bottom_link_info2">财务系统</span>
                	</a>
                </li>
                <li>
                	<a href="http://jshs.eamn.net" target="_blank">
     			        <img src="${pageContext.request.contextPath}/images/cms/15.jpg">  
     			        <div class="grey2"></div>
                        <img class="bottom-pic2" src="${pageContext.request.contextPath}/images/cms/b4.png"/>
                        <span class="bottom_link_info2">资产登记</span>
                	</a>
                </li>
                <li>
                	<a href="http://lst.suda.edu.cn" target="_blank">
                        <img src="${pageContext.request.contextPath}/images/cms/16.jpg"> 
                        <div class="grey2"></div>
                        <img class="bottom-pic2" src="${pageContext.request.contextPath}/images/cms/b5-2.png"/>
                        <span class="bottom_link_info2">安全教育</span>
                	</a>
                </li>
            </ul>
            </div>
            <div class="bottom_right">
            	<div class="w100p">
            		<img class="link-pic2" src="${pageContext.request.contextPath}/images/cms/link-bg.png"/>
            		<span class="bottom_link_title">友情链接</span>
            	</div>
            	<select class="bottom_select" onchange="javascript:window.open(this.options[this.selectedIndex].value)">
             		<option selected="--校内站点--">--校内站点--</option>
            		<option value="http://www.xidian.edu.cn/">西安电子科技大学</option>
            		
             	</select>

            	<select class="bottom_select" onchange="javascript:window.open(this.options[this.selectedIndex].value)">
             		<option selected="--校外站点--">--校外站点--</option>
            		
             	</select>

            </div>
        </div>
        </div>
    </div>

  

    <script type="text/javascript"> 
    var flag = 0;
    if( ${loginUser.username != "" && loginUser.username != null} ){
   	flag = 1;    }
        $(function () {
            $('#slides').slidesjs({
                width: 940,
                height: 528,
                play: {
                    auto: true,
                    interval: 4000,

                },
                navigation: false
            });
        });
        var speed = 25; //数字越大速度越慢
        var tab = document.getElementById('demo');
        var tab1 = document.getElementById('demo1');
        var tab2 = document.getElementById('demo2');
        tab2.innerHTML = tab1.innerHTML;

        function Marquee() {
            if (tab2.offsetWidth - tab.scrollLeft <= 0)
                tab.scrollLeft -= tab1.offsetWidth
            else {
                tab.scrollLeft++;
            }
        }
        var MyMar = setInterval(Marquee, speed);
        tab.onmouseover = function () {
            clearInterval(MyMar)
        };
        tab.onmouseout = function () {
            MyMar = setInterval(Marquee, speed)
        };

        /* $("#demo").find("img").hover(
            function () {
                $(this).siblings().stop().fadeTo(200, 0.3)
            },
            function () {
                $(this).siblings().stop().fadeTo(200, 1)
            }
        );*/
        $(".news_list_container").hover(
            function () {
                $(this).find(".news_list").show();
                $(this).addClass("news_selected");
                $(this).siblings().find(".news_list").hide();
                $(this).siblings().removeClass("news_selected")
            },
            function () {
                /*$(this).find(".news_list").hide()*/
            }
        );
        $("#res").click(function(){
        	        	if(flag == 0) { 
        	        		alert("您还未登录，请先登录!");
        	        		window.location.href="${pageContext.request.contextPath}/admin/logRes";
        	        	}
        	        	else {
        	        		 window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#/xidlims/lab/labAnnexList?currpage=1";
        	        		
        	        	}
        	        });
        	       $("#manage").click(function(){
        	        	
        	       	if(flag == 0) {
        	       		alert("您还未登录，请先登录!");
        	       		window.location.href="${pageContext.request.contextPath}/admin/logManage";
        	       	}
        	        	else window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#/xidlims/labRoom/listLabRoom?currpage=1&orderBy=9";
        	        });
        	        $("#dev-mag").click(function(){
        	        	if(flag == 0) { 
        	        		alert("您还未登录，请先登录!");
        	        		window.location.href="${pageContext.request.contextPath}/admin/logDevMag";
        	        	}
        	        	else window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#/xidlims/device/listLabRoomDevice?page=1";
        	        });
        	        
        	        $("#dev-res").click(function(){
        	        	if(flag == 0) {  
        	        		alert("您还未登录，请先登录!");
        	        		window.location.href="${pageContext.request.contextPath}/admin/logDevRes";
        	        	}
        	        	else window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#/xidlims/device/listLabRoomDevice?page=1&isReservation=1";
        	        });
        	        $("#time").click(function(){
        	        	if(flag == 0) {  
        	        		alert("您还未登录，请先登录!");
        	        		window.location.href="${pageContext.request.contextPath}/admin/logTime";
        	        	}
        	        	else window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#/xidlims/timetable/selfTimetable/listSelfTimetable";
        	        });
        	        $("#asset").click(function(){
        	        	if(flag == 0) {  
        	        		alert("您还未登录，请先登录!");
        	        		window.location.href="${pageContext.request.contextPath}/admin/logAsset";
        	        	}
        	        	else window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#/xidlims/asset/auditAssetApps?currpage=1&assetAuditStatus=3";
        	        });
        	       $("#system").click(function(){
        	        	if(flag == 0) {  
        	        		alert("您还未登录，请先登录!");
        	        		window.location.href="${pageContext.request.contextPath}/admin/logSystem";
               	}  });
        	        $("#asset_res").click(function(){
        	        	if(flag == 0) {  
        	        		alert("您还未登录，请先登录!");
        	        		window.location.href="${pageContext.request.contextPath}/admin/logAssetRes";
        	        	}  });
        	        $("#itemShow").click(function(){
        	        	if(flag == 0) {  
        	        		alert("您还未登录，请先登录!");
        	        		window.location.href="${pageContext.request.contextPath}/admin/logItemShow";
        	        	}  });   
        	        $("#know_map").click(function(){
        	        	if(flag == 0) {  
        	        		alert("您还未登录，请先登录!");
        	        		window.location.href="${pageContext.request.contextPath}/admin/logKnowMap";
        	        	}  });   
    </script>
 
</body>

</html>