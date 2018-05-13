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

</head>
<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="tCourseSiteId" value="${tCourseSite.id}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
	<input type="hidden"  id="now" value="${now}"/>
	<input type="hidden"  id="skillId" value="${tExperimentSkill.id}"/>
     <div class="course_con ma back_gray" >
        <div class="course_cont r back_gray">
            
            <div class="course_content">
                
                
                <div class="tab_content">
                    <table class="w100p f14 lh30">
                        <thead>
                            <th class="w25p tl">作业标题</th>
                            <th class="w10p tl">学生姓名</th>
                            <th class="w15p tl">成绩</th>
                            <c:if test="${sessionScope.authorityName eq 'TEACHER' or sessionScope.authorityName eq 'COURSETEACHER' || sessionScope.authorityName eq 'SUPERADMIN' || sessionScope.authorityName eq 'LABCENTERMANAGER'}">
                            <th class="w10p tl">评语</th>
                            <th class="w10p tl">评分</th>
                            </c:if>
                            <th class="w10p tl">操作</th>
                            <th class="w15p tl">学生提交时间</th>
                            <th class="w15p tl">教师查看时间</th>
                        </thead>
                        <tbody>
                        	<c:forEach items="${tAssignmentGradings}" var="current"  varStatus="i">
                            <tr>
                                <td>${tAssignment.title}</td>
                                <td>${current.userByStudent.cname}</td>
                                <td><c:if test="${current.finalScore != null }">
						       			<font id="finalScoreFont">${current.finalScore }</font>
						       		</c:if>
						       		<c:if test="${current.finalScore == null }">
						       			<font id="finalScoreFont">尚未批改</font>
						       		</c:if>
						       		/${current.TAssignment.TAssignmentAnswerAssign.score }
					       		</td>
					       		<c:if test="${sessionScope.authorityName eq 'TEACHER' or sessionScope.authorityName eq 'COURSETEACHER' || sessionScope.authorityName eq 'SUPERADMIN' || sessionScope.authorityName eq 'LABCENTERMANAGER'}">
                                <td><input type="text" id="comments" value="${current.comments }" onchange="grade(this)" class="clear-input"/></td>
                                <td>
                                	<input type="text" id="finalScore" style="width: 45px;" value="${current.finalScore }" onchange="grade(this)" oninput="changeNumber(this,${current.TAssignment.TAssignmentAnswerAssign.score} )" class="clear-input"/>
									<input type="hidden" id="assignGradeId" value="${current.accessmentgradingId }"/>
                                </td>
                                </c:if>
                                
                                <td>
									<a href="javascript:void(0)" onclick="downloadOneFile(${current.accessmentgradingId})">下载</a>
									<a href="javascript:void(0)" onclick="lookReport(${current.accessmentgradingId})">查看</a>
								</td>
                                <td>
                                	<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${current.submitdate.time }" type="both"/>
                                </td>
                                <td>
                                	<span><fmt:formatDate pattern="yyyy-MM-dd HH-mm" value="${current.gradeTime.time }" type="both"/></span>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
            </div>
                 
            
        </div>
    </div>
    
<!--查看实验报告开始-->
<div class="window_box hide fix zx2  " id="lookReport">
	<div class="window_con bgw b1 br3 rel bs10 ">
    	<span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
    	<div class="add_tit p20 bb f16">图片预览</div>
    	<div class="add_con p20 tc">
     		<div><label id="content">图片名字</label></div>
     		<div><a href="javascript:void(0)" id="upImage" onclick="" disabled="true">上一个</label>
     			<a href="javascript:void(0)" id="downImage" onclick="">下一个</label>
     		</div>
        </div>
    </div>
</div>
<!--查看实验报告结束-->
    	
    
    <script language="javascript" type="text/javascript">

  //实验问答教师给学生评分
    function grade(obj){
    	var now = $("#now").val(); 
    	var tCourseSiteId = $("#tCourseSiteId").val(); 
    	var assignGradeId = $(obj).parent().parent().find("#assignGradeId").val(); 
    	var comments = $(obj).parent().parent().find("#comments").val();
    	var finalScore = $(obj).parent().parent().find("#finalScore").val();
    	$.ajax({
    		url: $("#contextPath").val()+'/tcoursesite/skill/gradeReport?tCourseSiteId='+tCourseSiteId+'&assignGradeId='+assignGradeId+'&comments='+comments+'&finalScore='+finalScore,
    		type:'post',
    		async:false,  // 设置同步方式
            cache:false,
    		success:function(data){
    			if(finalScore!=""){
    				$("#isGraded").val("已评分");
    			}
    			$("span").html('<fmt:formatDate pattern="yyyy-MM-dd HH-mm" value="${now}" type="both"/>');
    			$("#finalScoreFont").html(finalScore);
    		}
    	}); 
    }
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/experimentSkill.js"></script>
</body>
</html>