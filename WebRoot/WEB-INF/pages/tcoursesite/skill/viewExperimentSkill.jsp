<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>查看实验内容--老师</title>
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
                	<c:if test="${flag==1}">
                    <div class="r lab_item_ctrl">
                        <a class="btn_lab btn_view" href="javascript:void(0)" onclick="editExperiment(${tExperimentSkill.id})">编辑实验内容</a>                                
                    </div>
                    </c:if>
                    <div class="info">
                        <h4 class="info_title">
                            基本信息
                        </h4>
                        <div class="info_content">
                            <div class="w45p l f16 info_detail ">实验名称:${tExperimentSkill.experimentName}</div>
                            <div class="w45p l f16 info_detail ">实验编号:${tExperimentSkill.experimentNo}</div>
                            <%--<div class="w45p l f16 info_detail ">项目所属实验室:${labRoomNames}</div>
                            <div class="w45p l f16 info_detail ">实验类别:
                            <c:if test="${tExperimentSkill.experimentType==1}">
                            	基础型
                            </c:if>
                            <c:if test="${tExperimentSkill.experimentType==2}">
                            	设计型
                            </c:if>
                            <c:if test="${tExperimentSkill.experimentType==3}">
                            	创新型
                            </c:if>
                            <c:if test="${tExperimentSkill.experimentType==4}">
                            	综合型
                            </c:if>
                            <c:if test="${tExperimentSkill.experimentType==5}">
                            	演示型
                            </c:if>
                            <c:if test="${tExperimentSkill.experimentType==6}">
                            	验证型
                            </c:if>
                            </div>
                            <div class="w45p l f16 info_detail ">是否启用:
                            <c:if test="${tExperimentSkill.experimentIsopen==1}">
                            	启用
                            </c:if>
                            <c:if test="${tExperimentSkill.experimentIsopen==0}">
                            	未启用
                            </c:if>
                            </div>
                            <div class="w45p l f16 info_detail ">实验版本:${tExperimentSkill.experimentVersion}</div>
                        --%></div>
                    </div>
                    <div>
                        <h4 class="info_title">
                            实验目的
                        </h4>
                        <p class="f16 info_detail">${tExperimentSkill.experimentGoal}</p>
                    </div>
                    <div>
                        <h4 class="info_title">
                            实验描述
                        </h4>
                        <div>
                            <p class="f16 info_detail">${tExperimentSkill.experimentDescribe}</p>
                        </div>
                    </div>
                </div>
                
            </div>
                 
            
        </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/experimentSkill.js"></script>
</body>
</html>