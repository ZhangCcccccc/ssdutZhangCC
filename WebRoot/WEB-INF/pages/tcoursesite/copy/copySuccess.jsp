<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<%--<%@ include file="/WEB-INF/sitemesh-decorators/course.jsp" %>
--%><html>

<head>
    <title>XDU实验实训教学平台</title>
    <meta name="decorator" content="none"/>
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
    <link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>

</head>

<body>
	<div>
        <div class="top ">
            <div class="header ma wh f24 cf">
                <!--<img src="images/logo1.png">-->
                <div class="sat_name l">XDU实验实训教学平台</div>
                <div class="user f12 r">
                    <ul class="">
                        <li class="my_course l ml15"><a href="javascript:void(0);" class="wh">我的课程</a></li>
                      	<li class="user_name l ml15"><a href="javascript:void(0);" class="wh"><i class="fa fa-user fa-cir mr5"></i>${user.cname}</a></li>
                        <li class="log_out l ml15"><a href="${pageContext.request.contextPath}/pages/logout-redirect.jsp" class="wh">退出</a></li>
                    </ul>
                </div>
            </div>

        </div>
        <div class="course_info ma mb30 ">
            <div class="copy_nav cf p30">
                <div class="l tc w33p rel ">
                    <div class="f24">选择学习单元</div>
                    <div class="ma f24 h60 w60 bgb tc lh60 br30 rel zx2 wh">1</div>
                    <span class="di_l h10 w45p block bgb abs r0 zx1 t75"></span>
                </div>
                <div class="l tc w33p rel ">
                    <div class="f24">选择目标课程</div>
                    <div class="ma f24 h60 w60 bgb tc lh60 br30 rel zx2 wh">2</div>
                    <span class="di_l h10 w45p block bgb abs r0 zx1 t75"></span>
                    <span class="di_l h10 w45p block bgb abs l0 zx1 t75"></span>
                </div>
                <div class="l tc w33p rel ">
                    <div class="f24">完成复制</div>
                    <div class="ma f24 h60 w60 bgb tc lh60 br30 rel zx2 wh">1</div>
                    <span class="di_l h10 w45p block bgb abs l0 zx1 t75"></span>
                </div>
            </div>

        </div>

    </div>


    <div class="course_con ma">
            <div class="course_mod f14 mb2">
            	<form:form id="form" action="${pageContext.request.contextPath}/tcoursesite/copy/saveTCourseSites?allIdsString=${allIdsString}" method="POST" modelAttribute="tAssignment" enctype="multipart/form-data" >
		            <table class="w100p">
		                 <tr class="lh40 bgg  pl30 f18">
		                 	<th class="w5p tl"></th>
		                    <th class="w30p tl">名称</th>
		                 	<th class="w10p tl ">创建人</th>
		                    <th class="w15p tl">创建时间</th>
		                    <th class="w20p tl">分类信息</th>
		                    <th class="tl w10p">操作</th>                                    
		                 </tr>
		               <c:forEach items="${tCourseSites }" var="tCourseSite" varStatus="i">
		                 <tr>
		                    <td>
		                    </td>
		                    <td>
		                    <a href="${pageContext.request.contextPath}/tcoursesite?tCourseSiteId=${tCourseSite.id}">
		                    ${tCourseSite.title}</a></td>
		                    <td></td>
		                    <td><%--<fmt:formatDate value="${questionPool.createdTime.time }" pattern="yyyy-MM-dd"/></td>--%>
		                    <td></td>
		                    <td>
		                    </td>
		                 </tr>
		               </c:forEach>
		
		            </table>
		            <div class="cf mt20 mb20">
		                <div class="w50p l">
		                    <div class="bbtn bgb f14 r mt10 poi tc br3 wh w80" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite?tCourseSiteId=${tCourseSite.id}'">
		                       	 返回到课程内容
		                    </div>
		                </div>
		            </div>
	            </form:form>
            </div>
        
        
    	</div>
  
        
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

</body>

</html>