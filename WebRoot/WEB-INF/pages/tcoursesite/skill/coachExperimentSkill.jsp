<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>实验指导</title>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/reset.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/lib.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/global.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/skill/experiment.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/picChange.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>

    <style type="text/css">
        .LeftBotton{
            background:#ccc;
           
            float:left;
            width:11px;
            cursor:pointer;
            position:absolute;
            top:0px;
            height:171px;
        }
        .RightBotton{
            
            float:right;
            background-color: #ccc;
            width:11px;
            cursor:pointer;
            position:absolute;
            top: 0px;
            height:171px;
            left:880px;
        }
        .blk_29{
            border: #ccc 1px solid;
            padding-right:0px ;
           
            padding-left: 0px;
            padding-bottom: 10px;
            overflow:hidden;
            
            padding-top: 10px;
            
            zoom:1;
            position:relative;
        }
    </style>
</head>
<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="tCourseSiteId" value="${tCourseSite.id}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
     <div class="course_con ma back_gray" >
        <div class="course_cont r back_gray">
            <div class="course_content">
                <div class="tab_content">
                    <div>
                        <h4 class="info_title">
                            实验指导书
                        </h4>
                        <div class="cf mt10">
                        	<c:forEach items="${quides}" var="quide"  varStatus="i">
                            <div class="w20p l">
                            <a href="${pageContext.request.contextPath}/tcoursesite/downloadFile?id=${quide.id}">
                            <img class="block m0a" src="${pageContext.request.contextPath}/images/tCourseSite/skill/pdf.png" height="100"  alt="" title="下载${quide.name}"/>
                            <div class="c" >${quide.name}</div>
                            </a>
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="mt10">
                        <h4 class="info_title">
                            实验图片
                        </h4>
                        <div class="blk_29 cf">
                        <div class="LeftBotton" id="LeftArr">
                            <img class="block pt80" src="${pageContext.request.contextPath}/images/tCourseSite/skill/icon_back_10.png" height="10" width="10" alt="" />
                        </div>
                        <div>
                            <div class="cf " id="scrollP">
                            	<c:forEach items="${images}" var="image"  varStatus="i">
                                <div class="l mtb15 pl20">
                                    <a href="#"><img class="block m0a" src="${pageContext.request.contextPath}${image.url}" height="100" alt="" /><p class="moa g3 tc f14">实验拓扑图</p></a>
                                </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="RightBotton" id="RightArr">
                            <img class="block pt80" src="${pageContext.request.contextPath}/images/tCourseSite/skill/icon_forward_10.png" height="10" width="10" alt="" />
                        </div>
                        </div>
                    </div>
                    <div class="mt10">
                        <h4 class="info_title">
                            实验视频
                        </h4>
                        <div>
                        	<c:forEach items="${videos}" var="video"  varStatus="i">
	                            <video width="100%" height="500" controls="controls">
				 	 				<source src="${pageContext.request.contextPath}${video.url}" type="video/mp4">
								</video>
							</c:forEach>
                        </div>
                    </div>
                    <div class="mt10">
                        <h4 class="info_title">
                            实验工具
                        </h4>
                        <div class="cf bgc">
                        	<c:forEach items="${tools}" var="tool"  varStatus="i">
                            <div class=" l mtb15 pl20">
                                <a href="${pageContext.request.contextPath}/tcoursesite/downloadFile?id=${tool.id}" download="${tool.name}">
                                <img src="${pageContext.request.contextPath}/images/tCourseSite/skill/exe.png" height="89"  alt="" />
                                <p class="moa gf tc f14">${tool.name}</p>
                                </a>
                            </div>
                            </c:forEach>

                        </div>
                    </div>
                    <div class="mt10">
                        <h4 class="info_title">
                            实验设备
                        </h4>
                        <div class="bgc cf">
                        	<c:forEach items="${devices}" var="device"  varStatus="i">
                        		<div class="l w30p mtb15"><img class="block m0a" src="${pageContext.request.contextPath}/images/tCourseSite/skill/server.png" height="75"  alt="" /><p class="moa gf tc f14">${device.schoolDevice.deviceName}</p></div>
                        	</c:forEach>
                            <%--
                            <div class="l w30p mtb15"><img class="block m0a" src="${pageContext.request.contextPath}/images/tCourseSite/skill/pc.png" height="75"  alt="" /><p class="moa gf tc f14">桌面电脑</p></div>
                            <div class="l w30p mtb15"><img class="block m0a" src="${pageContext.request.contextPath}/images/tCourseSite/skill/server.png" height="75" alt="" /><p class="moa gf tc f14">服务器</p></div>
                            <div class="l w30p mtb15"><img class="block m0a" src="${pageContext.request.contextPath}/images/tCourseSite/skill/cloud.png" height="75"  alt="" /><p class="moa gf tc f14">虚拟云</p></div>
                        	--%>
                        </div>
                    </div>
                </div>
                
            </div>
                 
            
        </div>
    </div>
    <script language="javascript" type="text/javascript">
      var scrollPic = new ScrollPic();
      scrollPic.scrollContId = "scrollP";//内容容器ID
      scrollPic.arrLeftId = "LeftArr";//左箭头ID
      scrollPic.arrRightId = "RightArr";//右箭头ID
      scrollPic.pageWidth = 180; //翻页宽度
      scrollPic.frameWidth = 908;//显示框宽度
      scrollPic.speed          = 10; //移动速度(单位毫秒，越小越快)
      scrollPic.space          = 10; //每次移动像素(单位px，越大越快)
      scrollPic.autoPlay       = false; //自动播放
      scrollPic.autoPlayTime   = 3; //自动播放间隔时间(秒)
      scrollPic.initialize(); //初始化
    </script>
    <script type="text/javascript"> 
        $(function () {
            $('select').searchableSelect();
        });
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/experimentSkill.js"></script>
</body>
</html>