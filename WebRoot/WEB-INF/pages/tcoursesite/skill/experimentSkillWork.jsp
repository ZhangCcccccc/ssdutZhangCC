<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>课程内容</title>
    <meta name="decorator" content="course">
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
    <link href="${pageContext.request.contextPath}/css/tCourseSite/skill/experiment.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
</head>
<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="tCourseSiteId" value="${tCourseSite.id}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
     <div class="course_con ma back_gray" >
        <div class="course_cont r back_gray">
            <div class="course_infobox">
                <h4 class="course_infobox_title">
                    <span>${tCourseSite.title}</span>
                    <input onclick="addExperimentSkillProfile('${tCourseSite.experimentSkillProfile}')" type="button" class="r" value="编辑概要"/>
                </h4>
                
                <div class="course_infobox_profile">
                	<p>${tCourseSite.experimentSkillProfile}</p>
                </div>
                <div class="course_schedule">
               		<c:forEach begin="1" end="${skillDoList[0]}" step="1" varStatus="j" var="current">	
               			<div class="bggreen"></div>
                   	</c:forEach>
                   	<c:forEach begin="1" end="${skillDoList[1]}" step="1" varStatus="j" var="current">	
               			<div class="bgb"></div>
                   	</c:forEach>
                   	<c:forEach begin="1" end="${skillDoList[2]}" step="1" varStatus="j" var="current">	
               			<div class="bgg"></div>
                   	</c:forEach>
                    <p class="f16">( ${skillDoList[0]}/${fn:length(tExperimentSkills)})</p>
                </div>
            </div>
            <div class="course_content">
                <div class="f_right lab_item_ctrl">
                    <a class="btn_lab btn_blue" href="#">同步实验项目</a>
                    <a class="btn_lab btn_blue" href="javascript:void(0)" onclick="editExperiment(-1)">新建</a>
                                
                </div>
                <div class="exp_list">
                    实验列表
                </div>
                <div class="labs">
                	<c:forEach items="${tExperimentSkills}" var="experiment"  varStatus="i">
                    <div class="lab_item">
                        <div class="lab_item_status">
                        <c:if test="${i.index+1 <= skillDoList[0]}">
                        <img src="${pageContext.request.contextPath}/images/tCourseSite/skill/lab-ok.png" alt="" />
                        </c:if>
                        <c:if test="${i.index+1 > skillDoList[0] && i.index+1 <= skillDoList[0]+skillDoList[1]}">
                        <img src="${pageContext.request.contextPath}/images/tCourseSite/skill/lab-unlock.png" alt="" />
                        </c:if>
                        <c:if test="${i.index+1 > skillDoList[0] + skillDoList[1]&& i.index+1 <= fn:length(tExperimentSkills)}">
                        <img src="${pageContext.request.contextPath}/images/tCourseSite/skill/lab-lock.png" alt="" />
                        </c:if>
                        </div>
                        <div class="lab_item_index">第${experiment.sort}节</div>
                        <div class="lab_item_title">${experiment.experimentName}</div>
                        <div class=" f12 g9">上课时间：<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${experiment.startdate.time }"/>
                        -<fmt:formatDate type="date"  pattern="HH:mm" value="${experiment.duedate.time }"/></div>
                        <div class="f_right lab_item_ctrl">
                            <a class="btn_lab btn_view" href="${pageContext.request.contextPath}/tcoursesite/skill/viewExperimentSkill?tCourseSiteId=${tCourseSite.id}&skillId=${experiment.id}">查看实验项目</a>
                            <a class="btn_lab btn_green" href="#">开始实验</a>
                            <a class="btn_lab btn_blue" href="#">实验作业</a>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    
<%-- 创建文件夹窗口--%>
<div class="window_box hide fix zx2 " id="addExperimentSkillProfile">
    <div class="window_con bgw b1 br3 rel bs10 ">
        <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
        <div class="add_tit p20 bb f16" id="folderDivName">编辑实验技能概要</div>
        <form:form action="${pageContext.request.contextPath}/tcoursesite/skill/saveExperimentSkillProfile?tCourseSiteId=${tCourseSite.id}" method="POST">
	        <div class="add_con p20">
	            <div class="add_module cf f14">
	                <div class="cf w100p  mt10 mb20">
	                    <div class="lh25">概要</div>
	                    <div class="w100p">
	                        <input class="w98p lh25 br3 b1 plr5" type="text"  name="experimentSkillProfile" id="experimentSkillProfile"/>
	                    </div>
	                </div>
	            </div>
	            <div class="cf tc">
	                <input  type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存"/>
	                <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">取消</div>
	            </div>
	        </div>
        </form:form>
    </div>
</div>
    <script type="text/javascript">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/experimentSkill.js"></script>
</body>
</html>