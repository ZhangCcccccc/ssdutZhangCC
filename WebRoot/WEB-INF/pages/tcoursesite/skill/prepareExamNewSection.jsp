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
                <div class="tab_content">
                	<!--题目内容  -->
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
						    <c:if test="${tAssignmentSection.id==null }">
							    <div id="title">新建测验题目</div>
							</c:if>
							<c:if test="${tAssignmentSection.id!=null }">
							    <div id="title">编辑测验题目</div>
							</c:if>
						</div> 
						
						<form:form action="${pageContext.request.contextPath}/tcoursesite/skill/SavePrepareExamSection?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}" method="POST" modelAttribute="tAssignmentSection">
							<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr> 
							    	<th><label style="margin-left: 16px">题目序号：</label></th>
							    	<th><form:input path="sequence" id="sequence" required="true" class="easyui-numberbox" placeholder="输入数字"/></th>
	                                </tr>
				                    <tr> 
							    	<th><label style="margin-left: 16px">题目：</label></th>
							    	<th><form:input path="description" id="description" required="true" /></th>
	                                </tr>
                                    <tr> 
								    <th><label style="margin-left: 16px">创建时间：</label></th>
									<th><input type="text" name="createdTime" id="createdTime" onclick="new Calendar().show(this);" value="<fmt:formatDate value="${tAssignmentSection.createdTime.time }" pattern="yyyy-MM-dd"></fmt:formatDate>"  required="true" /></th>
                                    </tr>
									</thead>
							 	<form:hidden path="status"/>
					 	        <form:hidden path="user.username" />
				 	         	<form:hidden path="TAssignment.id" />
							    <form:hidden path="id" />
							   </table>
							</div>
							<div class="moudle_footer">
						        <div class="submit_link">
						        	<input class="btn" id="save" type="submit" value="确定">
									<input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
						        </div>
						    </div>
			
						</form:form>
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