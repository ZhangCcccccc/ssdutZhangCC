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

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/floor/jquery-1.11.0.min.js"></script>	
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/floor/jquery.nav.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/floor/jquery.rotate.min.js"></script>
    <!--<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
    <script type="text/javascript" src="js/perfect-scrollbar.js"></script>
<link href="css/perfect-scrollbar.css" rel='stylesheet' type='text/css'>-->
    <script type="text/javascript">
        setInterval("TT()", 10);

        function TT() {
            var Height = $(window).height();
            var Width = $(window).width()
            $(".room_panorama").height(Height);
       	 var s = $(".room_list li").length;
         var a = 900 / (s - 1);
         for (var i = 0; i < s; i++) {
             var d = a * i
             $(".room_list li").eq(i).css("left", d);

         }
        }
    </script>
        <script type="text/javascript">
        //切换楼层
        function changeFloor(buildNumber,floor){
        	//获取楼层li
        	var floorLis =document.getElementById('floorUl').getElementsByTagName('li');
        	//切换楼层的房间列表
        	$.ajax({
        		type: 'POST',
        		url: '${pageContext.request.contextPath}/visualization/show/changeFloor',
        		data: {'buildNumber':buildNumber,'floor':floor},
        		success:function(data){
        			$("#room_list").html(data);
        			//右边点击自动更改左边楼层
        			$(".floor_nav_floor").val(floor); 
        			//左边点击自动更改右边楼层
        			for(var i = 1;i <= floorLis.length; i++){
        				$("#floor"+i).attr("class", "");
        			}
        			$("#floor"+floor).attr("class", "selected_floor");
        		}
        	});
        	//切换楼层第一个房间全景图
        	$.ajax({
        		type: 'POST',
        		url: '${pageContext.request.contextPath}/visualization/show/changeFloorFirstRoomImage',
        		data: {'buildNumber':buildNumber,'floor':floor,'type':3},
        		success:function(data){
        			//var str = "<img src='${pageContext.request.contextPath}/"+data+"' >";
        			//document.getElementById('panorama').innerHTML = str;
        			if(data != null && data != ""){
        				$(".demo_photo").attr("src", "${pageContext.request.contextPath}/"+data);
        			}
        		}
        	});
        	//切换楼层第一个房间显示信息
        	$.ajax({
        		type: 'POST',
        		url: '${pageContext.request.contextPath}/visualization/show/changeFloorFirstRoom',
        		data: {'buildNumber':buildNumber,'floor':floor},
        		success:function(data){
        			$("#room_banner").html(data);
        			$(".room_list li").hover(
                            function () {
                            	var id = $(this).find("p").html();//教室id
                                $.ajax({
                            		type: 'POST',
                            		url: '${pageContext.request.contextPath}/visualization/show/changeRoomImage',
                            		data: {'id':id,'type':1},
                            		success:function(data){
                            			$(".room_img").attr("src", "${pageContext.request.contextPath}/"+data);
                            		}
                            	});
                            }
                        )
        		}
        	});
        	//切换楼层第一个房间图片
            $.ajax({
           		type: 'POST',
           		url: '${pageContext.request.contextPath}/visualization/show/changeFloorFirstRoomImage',
           		data: {'buildNumber':buildNumber,'floor':floor,'type':1},
           		success:function(data){
           			$(".room_img").attr("src", "${pageContext.request.contextPath}/"+data);
            	}
            });
        	
        	//切换楼层第一个房间详细信息
        	$.ajax({
        		type: "POST",
        		url: "${pageContext.request.contextPath}/visualization/show/changeFloorFirstRoomMap",
        		data: {'buildNumber':buildNumber,'floor':floor},
        		dataType:'json',
        		success:function(data){
        			$.each(data,function(key,values){  
        				//alert(key+"="+values);
        				//document.getElementById(key).innerHTML=values;
        				document.getElementById(key).innerHTML=values;
        				$("#"+key).val(""+values);
        			 }); 
        		},
        		error:function(){
        			alert("此楼层暂无房间信息！");
        			}
        	});
        	$(".floor_img").attr("src", "${pageContext.request.contextPath}/images/visualization/floor/floor_img"+floor+".png");
        	
            
        }
      //切换实验室
      //切换实验室展示信息
        function changeRoom(id){
        	$.ajax({
        		type: 'POST',
        		url: '${pageContext.request.contextPath}/visualization/show/changeRoom',
        		data: {'id':id},
        		success:function(data){
        			$("#room_banner").html(data);
        		}
        	});
        	//切换实验室全景图
        	$.ajax({
        		type: 'POST',
        		url: '${pageContext.request.contextPath}/visualization/show/changeRoomImage',
        		data: {'id':id,'type':3},
        		success:function(data){
        			//var str = "<img src='${pageContext.request.contextPath}/"+data+"' >";
        			//document.getElementById('panorama').innerHTML = str;
        			if(data != null && data != ""){
        				$(".demo_photo").attr("src", "${pageContext.request.contextPath}/"+data);
        			}
        		}
        	});
        	//切换实验室详细信息map
            $.ajax({
       		type: "POST",
    		url: "${pageContext.request.contextPath}/visualization/show/changeRoomMap",
    		data: {'id':id},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){
    				if(key=="isUsed"||key=="appointment"){
    					if(values==0){
    						$("#"+key+"2").attr("checked","checked");
    						$("#label"+key+"2").css("background-image","url('${pageContext.request.contextPath}/images/visualization/floor/radio.png')");
    						$("#"+key+"1").removeAttr("checked");
    						$("#label"+key+"1").css("background-image","url('${pageContext.request.contextPath}/images/visualization/floor/unradio.png')");
    					}else{
    						$("#"+key+"1").attr("checked","checked");
    						$("#label"+key+"1").css("background-image","url('${pageContext.request.contextPath}/images/visualization/floor/radio.png')");
    						$("#"+key+"2").removeAttr("checked");
    						$("#label"+key+"2").css("background-image","url('${pageContext.request.contextPath}/images/visualization/floor/unradio.png')");
    					}
    					
    				}else{
    					document.getElementById(key).innerHTML=values;
        				$("#"+key).val(""+values);
    				}
    			 }); 
    		},
    		error:function(){
    			alert("暂无该房间信息！");
    			}
    	});
            
        }
      
        function roomDetails() {
            $('html,body').animate({
                scrollTop: $('.floor_info').offset().top
            }, 200);

        }
        
        //切换楼宇
	function changeBuilding(buildNumber){
		window.location = '${pageContext.request.contextPath}/visualization/show/floor?buildNumber='+buildNumber;
		
		
	}
        
    </script>
</head>

<body style="" class="selected_floor">
	<div class="layout_logo_container" >
        <a href="${pageContext.request.contextPath}/${site.siteurl}">
        <img src="${pageContext.request.contextPath}/images/visualization/floor/sub_logo.png">
        </a>
        </div>

    <div class="layout_panorama_container">
    
    	
        
        <div id="panorama" class="room_panorama " >
            <c:forEach items="${labRoom.commonDocuments}" var="d">
				<c:if test="${d.type==3}">
					<img src="${pageContext.request.contextPath}/${d.documentUrl}" class="demo_photo">
				</c:if>
			</c:forEach>        </div>
        
        <div class="layout_nav_slidebar">
            <div class="building_position position_label"><img src="${pageContext.request.contextPath}/images/visualization/superschool.jpg" id="school" >
                <img src="${pageContext.request.contextPath}/images/visualization/floor/icon.png" id="building_position_icon" style="left:${systemBuild.xCoordinate}">
               	<script>
               		var L="${systemBuild.xCoordinate}"
               		var T="${systemBuild.yCoordinate}"
               		var l=L*100+"%"
               		var t=T*100+"%"
               		$("#building_position_icon").css({
               			left:l,
               			top:t
               		})
               	</script>
               	
            </div>
            <div class="floor_position position_label">
                <img src="${pageContext.request.contextPath}/images/visualization/floor/floor_img1.png" usemap="#floor_map" id="floor_img" class="floor_img">
                <!-- <svg style="width:100%;height:100%;position:relative;z-index:1">
                    <polygon points="16 94,17 101,10 102,15 105,22 103,103 120,99 125,113 130,115 125,118 124,165 110,165 100,118 110" style="" />
                </svg> -->
                <div class="floor_nav">
                    <select onchange="changeBuilding(this.options[this.selectedIndex].value);">
						<c:forEach items="${systemBuilds}" var="build"  varStatus="i">
			    				<option value="${build.buildNumber}" <c:if test='${buildNumber == build.buildNumber}'> selected='selected'
     															</c:if>>${build.buildName}</option>
			    		</c:forEach>
				    </select>
				    <!-- 楼层显示 -->
                    <ul class="floor_nav_floor" id="floorUl">
                        <li><a id="floor1" href='javascript:void(0)' onclick="changeFloor(${buildNumber},'1')" class="selected_floor">1楼</a></li>
	                   	<c:forEach begin="2" end="${records}" step="1" varStatus="i">
			    			<li><a id="floor${i.index}" href='javascript:void(0)' onclick="changeFloor(${buildNumber},${i.index})">${i.index}楼</a></li>
			    		</c:forEach>
                    </ul>
                    
                </div>
                <!--<map name="floor_map" id="floor_map">
                    <area class="selected_map" shape="circle" coords="0,0,30" href ="venus.html" alt="Venus" />  
                </map>-->
            </div>
            <div class="room_position position_label"><img src="${pageContext.request.contextPath}/images/visualization/floor/defaultFloor.png" class="room_img">
                <!--<svg style="width:100%;height:100%;position:absolute;top:0px;left:0px;z-index:1">
                    <polygon points="99 106,99 125,129 125,129 106" style="" />
                    <polygon points="77 106,77 125,98 125,98 106" style="fill: none;" />
                </svg>-->
            </div>
        </div>
        <div class="banner_room">
            <div id="room_banner" class="room_banner">
                <h2 id="labRoomName">${labRoom.labRoomNumber}&nbsp;&nbsp;${labRoom.labRoomName}</h2>
                <div class="banner_info">
                    <p id="labRoomCapacity">容量：${labRoom.labRoomCapacity}人</p>
                    <p id="labRoomArea">面积：${labRoom.labRoomArea}平方米</p>
                    <p id="CActiveByLabRoomActive">状态：可用、可预约
                    <%--<c:if test="${labRoom.CActiveByLabRoomActive.id == 1 }">
                                                             可用、
                    </c:if>
                    <c:if test="${labRoom.CActiveByLabRoomActive.id == 2 }">
                                                             不可用、
                    </c:if>
                    <c:if test="${labRoom.CActiveByLabRoomReservation.id == 1 }">可预约
                    </c:if>
                    <c:if test="${labRoom.CActiveByLabRoomReservation.id == 2 }">不可用
                    </c:if>
                    --%></p>
                    <c:if test="${labRoom.isUsed eq 1}">
                    <a class="room_info_btn" href='http://www.baidu.com' target="_blank" >实验室监控
                    </a>
                    </c:if>
                    <a class="room_visualization" target="_blank" href="${pageContext.request.contextPath}/visualization/show/roomImage?id=${labRoom.id}">实验室可视化
                    </a>
                    <%--<c:if test="${labRoom.labRoomNumber eq '4412' }">
					<a class="room_visualization" target="_blank" href="http://www.gvsun.net:3380/wk/course/140" value="4412">实验室资源
                    </a>
					</c:if>
					<c:if test="${labRoom.labRoomNumber eq '4118' }">
					<a class="room_visualization" target="_blank" href="http://www.gvsun.net:3380/wk/course/82" value="4118">实验室资源
                    </a>
					</c:if>
					<c:if test="${labRoom.labRoomNumber ne '4118'&&labRoom.labRoomNumber ne '4412' }">
					<a class="room_visualization" target="_blank" href="http://www.gvsun.net:3380/wk/" value="">实验室资源
                    </a>
					</c:if>
                --%>
                	<a class="room_visualization" target="_blank" href="${pageContext.request.contextPath}/tms/courseList?currpage=1" value="">实验室资源
                	</a>
                </div>
            </div>
        </div>
        <div class="sub_room_nav">
            <div class="roomlist_nav_container">
                <div class="room_list_bg"></div>
                <div class="building_choice">
                    <div class="choice_building">
                        <select style="border:0px;" onchange="changeBuilding(this.options[this.selectedIndex].value);">
							<c:forEach items="${systemBuilds}" var="build"  varStatus="i">
				    				<option style="background: #dea757;" value="${build.buildNumber}" <c:if test='${buildNumber == build.buildNumber}'> selected='selected'
	     															</c:if>>${build.buildName}</option>
				    		</c:forEach>
				    	</select>
                    </div>
                        
                    <div id="choise_floor" class="choise_floor">
						<select style="border:0px;" class="floor_nav_floor"
							onchange="changeFloor('${buildNumber}',this.options[this.selectedIndex].value)">
							<option value="1">1楼</option>
							<c:forEach begin="2" end="${records}" step="1" varStatus="i">
								<option value="${i.index}">${i.index}楼</option>
							</c:forEach>
						</select>
					</div>
                </div>
                <ul id="room_list" class="room_list ">
                    <c:forEach items="${labRooms}" var="labRoom"  varStatus="i">
                    <li ><a href='javascript:void(0)' onclick="changeRoom(${labRoom.id})"><span>${labRoom.labRoomNumber}</span>&nbsp;&nbsp;${labRoom.labRoomName}</a>
                    <p style="display:none;">${labRoom.id}</p>
                    </li>
                    </c:forEach>
                </ul>
                <!--<svg style="width:100%;">
                    <text fill="white" transform=" rotate(45)" style="font-size: 10px; font-weight: bold;">1121 H-600透射电镜室</text>
                </svg>-->
                <script type="text/javascript">
                   
                </script>
            </div>
        </div>
    </div>
    <div class="floor_info_container">
        <div class="floor_info">
            <ul id="nav">
                <li><a href="#intro">教室详情</a>
                </li>
                <li><a class="exception" href="#top">返回顶部</a>
                </li>

            </ul>
            <div class="wrap">
                <div id="intro" class="dowebok">
                    <div class="title_box" >
                        <h2 class="layout_intro_tit">教室详情</h2>
                        <!-- <input calss="" type="button" value="编辑"> -->
                    </div>
					<div class="data_container">
						<table>
							<tr>
								<th style="width:10%">实验室名称</th><td id="labRoomName">${labRoom.labRoomNumber}&nbsp;&nbsp;${labRoom.labRoomName}</td>
								<th style="width:10%">实验室地点</th><td id="adress">${labRoom.systemRoom.systemBuild.systemCampus.campusName}<!-- 校区 -->
                            ${labRoom.systemRoom.systemBuild.buildName} <!-- 楼栋 -->
                            ${labRoom.systemRoom.roomName}(${labRoom.systemRoom.roomNo})</td>
								<th style="width:10%">管理机构</th><td id="departmentNumber">${labRoom.systemRoom.departmentNumber}</td>
							</tr>
							<tr>
								<th>实验室编号</th><td id="labRoomNumber">${labRoom.labRoomNumber}</td>
								<th>实验室类型</th><td id="CLabRoomType">${labRoom.CDictionaryByLabRoom.CName}</td>
								<th>实验室容量</th><td id="labRoomCapacity">${labRoom.labRoomCapacity}人</td>
							</tr>
							<tr>
								<th>是否可用</th>
								<td id="CActiveByLabRoomActive">
									<input name="isUsed" type="radio" disabled="true" id="isUsed1" value="1" <c:if test="${labRoom.labRoomActive == 1}">checked="checked"</c:if>><label for="isUsed1">是</label>
                                    	&nbsp;
                                    <input name="isUsed" type="radio" disabled="true" id="isUsed2" value="0" <c:if test="${labRoom.labRoomActive == 0}">checked="checked"</c:if>><label for="isUsed2">否</label>
                                </td>
								<th>是否可预约</th>
								<td id="CActiveByLabRoomReservation">
									<input name="isappointment" type="radio" disabled="true" id="appointment1" value="1" <c:if test="${labRoom.labRoomReservation == 1}">checked="checked"</c:if>><label for="appointment1">是</label>
                                    	&nbsp;
                                    <input name="isappointment" type="radio" disabled="true" id="appointment2" value="0" <c:if test="${labRoom.labRoomReservation == 0}">checked="checked"</c:if>><label for="appointment2">否</label>
								</td>
								<th>使用面积</th><td id="labRoomArea">${labRoom.labRoomArea}平方米</td>
							</tr>
							<tr>
								<th>实验室简介</th>
								<td id="labRoomIntroduction" colspan="5">
									${labRoom.labRoomIntroduction}
								</td>
							</tr>
							
						</table>
					</div>

                </div>
            </div>
        </div>
    </div>
    <div class="layout_info_container">
        <div class="news_container" style="height: 30px;">
                
        </div>
    </div>

    <div class="layout_link_container">
    	
    	<div class="layout_link_box">
    	<div class="address">
    	<div class="link_title">联系我们</div>
    <%-- ${site.bottomContent} --%>
 <font style="font-size:12px;">西安电子科技大学</font>   
    
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
    <div class="layout_footer">
    
    <div class="layout_power">
    	<p class="copyright">Copyright © 2016  西安电子科技大学</p>
        <p class="power">power by <a href="http://www.gvsun.com">Gvsun</a>
        </p>
    </div>
    </div>
    <script>
    $(".room_list li").hover(
            function () {
            	//alert($(this).find("span").html());
            	//var str = $(this).find("span").html();//教室number
            	var id = $(this).find("p").html();//教室id
                //$(".room_img").attr("src", "${pageContext.request.contextPath}/images/visualization/floor/room"+${buildNumber}+str+".png");
                $.ajax({
            		type: 'POST',
            		url: '${pageContext.request.contextPath}/visualization/show/changeRoomImage',
            		data: {'id':id,'type':1},
            		success:function(data){
            			$(".room_img").attr("src", "${pageContext.request.contextPath}/"+data);
            		}
            	});
            }
        )
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

        $(".room_list li a").click(
            function () {

                $(".building_banner").stop().fadeIn(150)
            }
        )
        $(".room_list li").first().addClass("room_selected");
        $(".room_list li").click(
            function () {
                $(this).addClass("room_selected")
                $(this).siblings().removeClass("room_selected")
            }
        )
        $(".room_info_btn").click(function () {
            $('html,body').animate({
                scrollTop: $('.floor_info').offset().top
            }, 200);

        });
        var s = $(".room_list li").length;
        var a = 900 / (s - 2);
        /*console.log(a);*/
        for (var i = 0; i < s; i++) {
            var d = a * i
            $(".room_list li").eq(i).css("left", d);

        }
        $(function () {
            var elm = $('#nav');
            var startPos = $(elm).offset().top;
            $.event.add(window, "scroll", function () {
                var p = $(window).scrollTop();
                $(elm).css('position', ((p) > startPos) ? 'fixed' : 'absolute');
                $(elm).css('top', ((p) > startPos) ? '100px' : '');
            });
        });

        $(function () {
            $('#nav').onePageNav({
                filter: ':not(.exception)'
            });
        });
        $('.room_list li').rotate(50);
        $(".floor_nav_floor li").click(
            	function(){
            		 $(this).find("a").addClass("selected_floor");
            		 $(this).siblings().find("a").removeClass("selected_floor");
            	}	
            );

        /*var i = $(".buliding_list li").length;           
        $(".buliding_list li").width($('.buliding_list').width() / i)*/
    </script>
</body>

</html>