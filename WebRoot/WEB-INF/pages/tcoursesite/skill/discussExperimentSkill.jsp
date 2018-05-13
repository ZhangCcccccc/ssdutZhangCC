<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>实验问答</title>
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

</head>
<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="tCourseSiteId" value="${tCourseSite.id}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
     <div class="course_con ma back_gray" >
        <div class="course_cont r back_gray">
            <div class="course_content">
                <div class="tab_content">
                    <ul class="tool_box cf rel zx2 pt5 bb pb10">
		                <li class="rel l pb5">
		                    <div class="wire_btn Lele l ml30 mt10 poi" onclick="editSkillDiscuss(-1)">
		                        <i class="fa fa-plus mr5"></i>发表帖子
		                    </div>
		                </li>
		            </ul>
		            <div class="f14 mb2 ">
		                <c:forEach items="${tDiscusses}" var="tDiscuss"  varStatus="i">
			                <div class="pl30 hf1 cf pt20 discussList ">
			                    <div class="l w5p lh30">
			                        <i class="fa bgb fa-user wh f16 h30 w30 lh30 tc br30 mt2"></i>
			                    </div>
			                    <div class="l w95p bb pb20 ">
			                        <div class=""><a class="bc hbc f18" href="#">${tDiscuss.title}</a></div>
			                        <div class="f10 gc cf lh16">
			                            <div class="l mr20">发布人：${tDiscuss.user.cname}</div>
			                            <div class="l mr20"><fmt:formatDate value="${tDiscuss.discussTime.time}" pattern="yyyy-MM-dd hh mm"></fmt:formatDate></div>
			                        </div>
			                        <div class="g3 lh30 mtb5">${tDiscuss.content}</div>
			                        <div class="cf f12">
			                            <a class="l poi mr20 hbc g3" onclick="replySkillDiscuss(${tDiscuss.id},${tCourseSite.id},${tExperimentSkill.id})">
			                            <i class="fa fa-comment-o f18"></i><span class="ml5">${fn:length(tDiscuss.TDiscusses)}</span></a>
			                            <%--<a class="l poi mr20 hbc g3"><i class="fa fa-eye-slash f18"></i><span class="ml5 ">未读1</span></a>
			                            --%>
			                            <c:if test="${flag==1}">
			                            <a class="l poi mr20 hbc g3" onclick="editSkillDiscuss(${tDiscuss.id})"><i class="fa fa-edit f18 mt1"></i></a>
			                            <a class="l poi mr20 hbc g3" onclick="deleteSkillDiscuss(${tDiscuss.id},${tCourseSite.id},${tExperimentSkill.id})"><i class="fa fa-trash-o f18"></i></a>
			                        	</c:if>
			                        </div>
			                    </div>
			                </div>
		                </c:forEach>
		            </div>
                </div>
            </div>
        </div>
    </div>
    <div class="window_box hide fix zx2  " id="editSkillDiscuss">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p20 bb f16">发表帖子</div>
            <form:form action="${pageContext.request.contextPath}/tcoursesite/skill/saveSkillDiscuss?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}" method="POST" modelAttribute="tDiscuss">
	            <div class="add_con p20">
	                <div class="add_module cf f14">
	                	<form:hidden path="id" id="id"/>
	                	<form:hidden path="type" id="type" value="100"/>
	                    <div class="cf w100p  mt10 mb20">
	                        <div class="lh25">标题</div>
	                        <div class="w100p">
	                            <form:input class="w98p lh25 br3 b1 plr5" type="text" path="title" id="title"/>
	                        </div>
	
	                    </div>
	                    <div class="cf w100p  mt10 mb20">
	                        <div class="">内容</div>
	                        <div class=" w100p">
	                            <form:textarea type="text" class="w98p b1 br3 h30 lh30 mt5 plr5" path="content" id="content"></form:textarea>
	                        </div>
	                    </div>
	                </div>
	                <div class="cf tc">
	                    <input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存"/>
	                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
	                        取消
	                    </div>
	                </div>
	            </div>
            </form:form>
        </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/experimentSkill.js"></script>
</body>
</html>