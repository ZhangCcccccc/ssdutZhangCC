<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>实验报告--老师页面</title>
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
    
            <!-- ueditor编辑器 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script> 
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/tCourseSite/Calendar.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/> 

<!-- 上传插件的css和js -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>

</head>
<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="tCourseSiteId" value="${tCourseSite.id}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
<div class="course_con ma back_gray" >
        <div class="course_cont r back_gray">
            <div class="course_content">
            	<form:form id="myForm" action="${pageContext.request.contextPath}/tcoursesite/skill/saveReportByStudent?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}" 
            	method="POST" modelAttribute="tAssignmentGrading" 
            	onsubmit="return checkForm()" enctype="multipart/form-data">
                <div class="tab_content">
                    <script type="text/javascript">
                        var ue = UE.getEditor("exp_report");
                    </script> 
                    <p>
                    <form:hidden path="submitTime" id="submitTime"/>
                    <form:hidden path="TAssignment.id" value="${tAssignment.id}"/>
                    <form:hidden path="userByStudent.username" value="${user.username}"/>
                    </p>
                    <p>
                      <label class="f16 mb5 block">${tAssignment.id}实验报告</label>
                       <textarea id="exp_report" path="content"></textarea>
                    </p>
                    <p class="prla">
                      <input class="btn_blue nobd btn_file" type="file" name="file" value="文件上传" />
                    </p>
                    <p class="prla">
                      <input class="btn_blue nobd btn_file" id="save" type="submit" value="提交" onclick="$('#submitTime').val(1);">
                    </p>
            
                </div>
                </form:form>
            </div>
                 
            
        </div>
    </div>
    <script language="javascript" type="text/javascript">
    
    
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
</body>
</html>