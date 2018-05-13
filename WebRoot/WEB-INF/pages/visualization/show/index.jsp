<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>西安电子科技大学可视化</title>
    <meta name="decorator" content="none"/>
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/visualization/floor/style.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/visualization/floor/table.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/visualization/floor/layout_header.css" rel="stylesheet" type="text/css">
    <%-- <link href="${pageContext.request.contextPath}/css/visualization/floor/panorama_viewer.css" rel='stylesheet' type='text/css'> --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/floor/jquery-1.11.0.min.js"></script>
    <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/floor/jquery.panorama_viewer.js"></script> --%>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/floor/drag.js"></script>
    <!--<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
    <script type="text/javascript" src="js/perfect-scrollbar.js"></script>
<link href="css/perfect-scrollbar.css" rel='stylesheet' type='text/css'>-->
    <!-- <script type="text/javascript">
       /*  setInterval("TT()", 10); */

        function TT() {
            var Height = $(window).height();
            var Width = $(window).width()
            $(".pv-inner,.drag_container,.panorama").height(Height);
            $(".drag_container").width(Width);
            var img_w=$("#viewer_img").width();
        	$(".panorama").width(img_w);
        	//console.log("实际宽度"+$(".panorama").width())
        }
    </script> -->
    <style>
        * {
            margin: 0px;
        }
        .panorama {
            position: relative;
            width:960px;
            
            margin:0 auto;
        }
        
        .panorama img {
            width: 100%;
           
            position:relative;
        }
        .drag_container{
        	position:relative;
        	    z-index: 0;
        	overflow:hidden;
        	width:960px;
        	margin:auto;
        }
        .layout_logobg_container form{
        position:absolute;
        top:10px;
        right:19px;
        }
        #search{
        	color:#cecece;
        	

        }
        .login_btn{
        	color:#333;
        }
        .schedule a{
        	font-size:12px;
        	background:#44B2ED;
        	height:18px;
        	display:block;
        	line-height:18px;
        	width:25px;
        	border-radius:5px;
        	color:#fff;
        }
        /* .schedule{
        	position: relative;
		    left: 15px;
		    top: -10px;} */
		.schedule_box table{
    		width:570px;
    		height:330px;
    		    border-collapse: collapse;
    	}
    	.schedule_box table td,.schedule_box table th{
    		border: 1px dotted #CECDCD;
    		height:25px;
    		padding: 0 2px;
    	}
    	.schedule_box table th{
    		font-size:14px;
    	}
    	.schedule_box table td{
    		font-size:12px;
    	}
    	caption{
    		height:45px;
    		line-height:45px;
    		font-size:18px;
    		
    	}
    	 .schedule_box{
    	    position: absolute;
		    z-index: 2;
		    background: rgba(255,255,255,0.95);
		    top: 15px;
		    left: 15px;
		    border-radius: 3px;
		    box-shadow: 0 0 5px #333;
		    overflow: hidden;
		    display:none;
	    }
	   
    </style> 
</head>

<body style="" class="">
			
			
				 
			
			 <form id="subform" name='f'
				action='${pageContext.request.contextPath}/j_spring_security_check'
				method='POST'>
				<div class="log-in">
					<div class="log_in_box">
						<img class="close"
							src="${pageContext.request.contextPath}/images/polytechnic/msg_close.png" />
						<img
							src="${pageContext.request.contextPath}/images/polytechnic/logo_a.png" />
						<div class="sat_name">
							管理登录
						</div>
						<fram>
						<div class="username-box">
							<input type="text" name="j_username" placeholder="会员账号" />
						</div>
						<div class="password-box">
							<input type="password" name="j_password" placeholder="密码" />
						</div>
						<div class="log-in-box">
							<input type="submit" value="登录" />
						</div>
						</fram>
					</div>
				</div>
			</form> 

	<div class="layout_logobg_container">
		<img src="${pageContext.request.contextPath}/images/visualization/floor/logo.png" class="cms_logo">
		<c:if test="${empty user}">
					<form>
						<input id="search" style="height: 21px;" name="keyword" value="搜索你喜欢的"
							onfocus="this.value='';"
							onblur="if(this.value==''){this.value='搜索你喜欢的'}" />
							<input class="login_btn" type="button" value="登录" />
					</form>
				</c:if>
				<c:if test="${!empty user}">
					<div style="position: absolute;
					    right: 10px;
					    top: 36px;font-size:12px;">
					    <a href="${pageContext.request.contextPath}/cms/cindex"
							<font style="color:#fff;">首页</font>
						</a>
						<font style="color:#fff;">教师 ${user.cname} </font>
							<%
								//前端登录标记
										session.removeAttribute("LOGINTYPE");
							%>
							<a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp"
								target="_parent"><font color=write><font style="color:#fff;">退出</font>
							</a>
					</div>
				</c:if> 
		<!-- <div class="right_container">
			<p>中文</p>
			<p>English</p>
		</div> -->
		<div class="room_nav" style="bottom:0px;">
            <div class="room_nav_container">
            </div>
        </div>
        </div>
    <div class="layout_panorama_container" style="margin-top:40px;">
        <script>
            // Use $(window).load() on live site instead of document ready. This is for the purpose of running locally only
            /* $(document).ready(function () {
                $(".panorama").panorama_viewer({
                    repeat: true,
                    direction: "horizontal", //让你定义的滚动方向。可接受的价值观是“水平”和“垂直”。默认值是水平
                    animationTime: 0, //这允许你设置时间当图像被拖。设置为0以使其瞬间。默认值是700。
                    easing: "ease-out",
                    // 宽度选项 "ease", "linear", "ease-in", "ease-out", "ease-in-out", and "cubic-bezier(...))". 默认值”。 "ease-out".
                    overlay: true // 真/假 这个隐藏的初始指令覆盖
                });
            }); */
            
            $(".login_btn").click(
                function () {
                    $(".log-in").show();
                });
            $(".close").click(
                    function () {
                        $(".log-in").hide();
                    }
                )
        </script>
        
        <div class="layout_panorama_container">
 		<div class="drag_container">
		 	<c:forEach items="${buils}" var="build"  varStatus="i">
		    <div id="build${build.buildNumber}" class="equipment_icon" style="left:${100*build.xCoordinate}%;top:${100*build.yCoordinate}%;">
		    <a href='${pageContext.request.contextPath}/visualization/show/floor?buildNumber=${build.buildNumber}' class="schedule" data-value="${build.buildNumber}">
		    <img src="${pageContext.request.contextPath}/images/visualization/floor/icon.png"  >
		    <span class="num">
		    <c:if test="${build.buildNumber ne '12'}">
		    ${build.buildNumber}</c:if></span>
		   
		    </a>
		    	 <!-- <div class="schedule"><a href="javascript:void(0);">课表</a></div> -->
		    </div>
		    </c:forEach>
		    <div class="schedule_box">
		    	<table>
		    	<caption>4号楼课表</caption>
		    	<tr>
		    		<th>课程名称</th>
		    		<th>班级</th>
		    		<th>实验室</th>
		    		<th>上课时间</th>
		    		<th>教师</th>
		    	</tr>
		    	<tr>
		    		<td>系统解剖学</td>
		    		<td>2015级口腔医学</td>
		    		<td>404幢4202</td>
		    		<td>周一第5,6,7节{第1-18周}</td>
		    		<td>张晔</td>
		    	</tr>
		    	
		    	<tr>
		    		<td>系统解剖学</td>
		    		<td>2015级医学影像学</td>
		    		<td>404幢4201</td>
		    		<td>周一第5,6,7节{第1-18周}</td>
		    		<td>陈尔齐</td>
		    	</tr>
		    	<tr>
		    		<td>组织学与胚胎学(一)</td>
		    		<td>2015级放射医学</td>
		    		<td>404幢4316</td>
		    		<td>周一第5,6,7节{第3-16周}</td>
		    		<td>任芳芳</td>
		    	</tr>
		    	<tr>
		    		<td>组织学与胚胎学(一)</td>
		    		<td>2015级放射医学</td>
		    		<td>404幢4320</td>
		    		<td>周一第5,6,7节{第3-16周}</td>
		    		<td>余水长</td>
		    	</tr>
		    	<tr>
		    		<td>病理学（二）</td>
		    		<td>2014级法医学,口腔医学,预防医学</td>
		    		<td>404幢4325</td>
		    		<td>周一第5,6,7节{第12-18周}</td>
		    		<td>侯燕</td>
		    	</tr>
		    	<tr>
		    		<td>医学微生物学（一）</td>
		    		<td>2014级临床医学,医学影像学</td>
		    		<td>404幢4406</td>
		    		<td>周一第5,6，7节{第3-13周}</td>
		    		<td>房红莹</td>
		    	</tr>
		    	<tr>
		    		<td>组织学与胚胎学(一)</td>
		    		<td>2015级放射医学</td>
		    		<td>404幢4316</td>
		    		<td>周一第5,6,7节{第3-16周}</td>
		    		<td>任芳芳</td>
		    	</tr>
		    	
		    	<tr>
		    		<td>解剖生理学</td>
		    		<td>医15生药</td>
		    		<td>404#-4118</td>
		    		<td>周一第5,6,7,8节{第12-16周}</td>
		    		<td>王琳辉</td>
		    	</tr>
		    	</table>
		    	<script type="text/javascript">
		    		$("tr:odd").css("background","rgba(245, 245, 245, 0.9)")

		    		$(".schedule").hover(
		    			function(){
		    			var N=$(this).attr("data-value")
		    			if( N==4 ){
		    			$(".schedule_box").show()
		    			}
		    			
		    			},
		    			function(){
		    			$(".schedule_box").hide()
		    			}
		    		)
		    		
		    		
		    	</script>
		    </div>
        <div class="panorama "  style="position:relative; background-color:#FFFFFF;left:0px;top:0px;filter:alpha(opacity=100);opacity:1;">
            <img src="${pageContext.request.contextPath}/images/visualization/superschool.jpg" id="viewer_img">
        </div>
        </div>
        </div>
        
    
        
    </div><%--
   
    <div class="layout_link_container">
    	
    	<div class="layout_link_box">
    	<div class="address">
    	<div class="link_title">联系我们</div>
 ${site.bottomContent}
    </div>
    	<div class="link_box">
			<div class="link_title">友情链接</div>
			<ul class="link_list">
	   		  <c:forEach items="${Links2}"  var="link" end="11">
	   		      <li><a href="${link.linkUrl}">${link.linkName}</a></li>
              </c:forEach>
			</ul>
		</div>
		</div>
    </div>
    --%><!-- <div class="layout_footer">
    
    <div class="layout_power">
    	<p class="copyright">Copyright © 2009  上海中医药大学版权所有  沪ICP备09008682号-2</p>
        <p class="power">power by <a href="http://www.gvsun.com">Gvsun</a>
        </p>
    </div>
    </div> -->
    <script>
        $(".news_list_container").hover(
            function () {
                $(this).find(".news_list").show();
                $(this).addClass("news_selected");
                $(this).siblings().find(".news_list").hide();
                $(this).siblings().removeClass("news_selected")
            },
            function () {
                /* $(this).find(".news_list").hide() */
            }
        );
       /*  $(".layout_panorama_container").hover(
            function () {
                $(".overview_banner").stop().fadeOut(150)
            },
            function () {
                $(".overview_banner").stop().delay(150).fadeIn(150)
            });  */
           
        /* $(".buliding_list li a").hover(
            function(){
            	//var str = 
                $(".building_banner").stop().delay(150).fadeIn(150)
            },
            function(){
                $(".building_banner").stop().fadeOut(150)
            }
        ) */ 
        /*var i = $(".buliding_list li").length;           
        $(".buliding_list li").width($('.buliding_list').width() / i)*/
    </script>
</body>

</html>