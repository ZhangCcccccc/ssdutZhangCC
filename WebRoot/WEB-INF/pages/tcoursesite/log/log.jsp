<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html>

<head>
    <title>XDU实验实训教学平台</title>
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

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/echarts.js"></script>


</head>

<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
    <div class="course_con ma">
        <div class="course_cont r">
                   

            <div class="course_mod f14 mb2 poi">
                <div class="module_tit lh40 bgg  pl30 f18 ">
                    <span class="bc">访问</span> 
                    <i class="fa  g9 " >访问量${siteLogSize}</i>
                    <i class="fa  g9 " >访问人数${logUsersSize}</i>
                    <i class="fa  g9 " >本站点成员${siteUsersSize}</i>
                    <%--<i class="fa  g9 " >访问过本站点的站点成员 ${logUsersSize}</i>
                    <i class="fa  g9 " >访问量${logUsersSize}</i>
                    --%><i class="fa "></i>
                	<span class="f12"><fmt:formatDate value="${tMessage.releaseTime.time }" pattern="yyyy-MM-dd"></fmt:formatDate></span>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div id="main" class="tool_box cf rel zx2 pt5 pb10" style="width:600px;height:400px"></div>
                    </div> 
                </div>
            </div>
            
            <div class="course_mod f14 mb2 poi">
                <div class="module_tit lh40 bgg  pl30 f18 ">
                    <span class="bc">活动</span> 
                    <i class="fa  g9 " >事件${siteEventLogSize}</i>
                    <i class="fa  g9 " >使用最多的工具:${mostSiteModuleLogSize[0]}</i>
                    <i class="fa  g9 " >最活跃的用户:${mostSiteUserLogSize[0]}</i>
                	<span class="f12"><fmt:formatDate value="${tMessage.releaseTime.time }" pattern="yyyy-MM-dd"></fmt:formatDate></span>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        
                    </div> 
                </div>
            </div>
            
            <div class="course_mod f14 mb2 poi">
                <div class="module_tit lh40 bgg  pl30 f18 ">
                    <span class="bc">资源</span> 
                    <i class="fa  g9 " >文件${fileFolderSize}</i>
                	<span class="f12"><fmt:formatDate value="${tMessage.releaseTime.time }" pattern="yyyy-MM-dd"></fmt:formatDate></span>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        
                    </div> 
                </div>
            </div>
            
            
        </div>

    </div>

 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/echarts.mine.js"></script>
<script type="text/javascript">

</script>

</body>

</html>