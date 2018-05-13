<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>预习测试</title>
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
				<c:if test="${flag>0}">
	                <div class="tab_content">
	                
	               		<a href="${pageContext.request.contextPath}/tcoursesite/skill/prepareExamInfo?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id} ">查看题目</a>
	                    
	                    <table class="w100p f14 lh30">
	                        <thead>
	                            <th>姓名</th>
						        <th>学号/工号</th>
						        <th>角色</th>
						        <th>提交日期</th>
						        <th>调整</th>
						        <th>总分</th>
						        <th>评语</th>
						        <th>详情</th>
	                        </thead>
	                        <tbody>
	                        	<c:forEach items="${tAssignmentGradings}" var="current"  varStatus="i">
							   		<tr>
						       			<td>${current.userByStudent.cname }</td>
						       			<td>${current.userByStudent.username }</td>
						       			<td>
						       				<c:forEach begin="0" end="0" items="${current.userByStudent.authorities }" var="authority">
						       					${authority.cname }
						       				</c:forEach>
						       			</td>
								       	<td>
								       		<fmt:formatDate pattern="yyyy-MM-dd" value="${current.submitdate.time }" type="both"/>
								       	</td>
								       	<td> 
								       		<input type="text" id="finalScore" style="width: 40px;" value="${current.finalScore }" onchange="grade(this)" oninput="changeNumber(this,${current.TAssignment.TAssignmentAnswerAssign.score} )"/>
								       		<input type="hidden" id="assignGradeId" value="${current.accessmentgradingId }"/>
								       	</td>
										<td>
											<p id="finalScoreFont">${current.finalScore }</p>
										</td>
										<td>
								       		<input type="text" id="comments" value="${current.comments }" onchange="grade(this)"/>
								       	</td>
								       	<td>
								       		<a href="${pageContext.request.contextPath}/tcoursesite/skill/prepareExamDetail?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}&gradingId=${current.accessmentgradingId}">答题详情</a>
								       	</td>
									</tr>
								</c:forEach>
	                        </tbody>
	                    </table>
	                </div>
                </c:if>
                <c:if test="${flag==0}">
                	<c:if test="${examCan == 1 && exam.status == 1}">
                		<a href="${pageContext.request.contextPath}/tcoursesite/skill/prepareBeginExam?examId=${exam.id}&tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}">${exam.title}</a><br>
                	</c:if>
                	<c:if test="${examCan == 1 && exam.status != 1}">
                		老师还未发布预习测试
                	</c:if>
                	<c:if test="${examCan < 1}">
                		请查看实验微课后再答题
                	</c:if>
                	<c:if test="${examCan > 1}">
                		<div class="tab_content">
                			<table  id="my_show"> 
								<thead>
								    <tr>                   
								        <th>测验标题</th>
								        <th>提交日期</th>
								        <th>分数</th>
								        <th>详情</th>
								    </tr>
								</thead>
								<tbody>
									<c:forEach items="${tAssignmentGradings}" var="current"  varStatus="i">
								   		<tr>
											<td>${current.TAssignment.title}</td>
									       	<td>
									       		<fmt:formatDate pattern="yyyy-MM-dd" value="${current.submitdate.time }" type="both"/>
									       	</td>
											<td>
												<p id="finalScoreFont">${current.finalScore }</p>
											</td>
									       	<td>
									       		<a href="${pageContext.request.contextPath}/tcoursesite/skill/prepareExamDetail?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}&gradingId=${current.accessmentgradingId}">查看</a>
									       	</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
	                	</div>
                	</c:if>
                </c:if>
            </div>
        </div>
    </div>
    
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