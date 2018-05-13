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
    
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
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
                    <div class="mt10">
                        <h4 class="info_title">
                        </h4>
                        <div>
                        	<c:forEach items="${videos}" var="video"  varStatus="i">
                        		<c:if test="${video.url eq null or video.url == ''}">
                        			<embed src="http://player.youku.com/player.php/sid/${fn:substring(video.videoUrl,fn:indexOf(video.videoUrl,'.html')-17,fn:indexOf(video.videoUrl,'.html')-2)}/v.swf"
										quality="high" width="1000" height="361" align="middle" allowScriptAccess="always" allowFullScreen="true" 
										mode="transparent" type="application/x-shockwave-flash"></embed>
                        		</c:if>
                        		<c:if test="${video.url ne null and  video.url != '' }">
		                            <video width="100%" height="500" controls="controls">
					 	 				<source src="${pageContext.request.contextPath}${video.url}" type="video/mp4">
									</video>
								</c:if>
							</c:forEach>
                        </div>
                    </div>
                </div>
                
            </div>
                 
            
        </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
</body>
</html>