<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.ccoursetype-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#my_show").jqprint();
	})
	});
</script>  
</head>

<body id="my_show">
      		<ul class="sit_title tabs" style="background: #e0e0e3;float: right;width: auto;">
      			<%--<li><a href="${pageContext.request.contextPath}/">概览</a></li>--%>
      			<li><a href="${pageContext.request.contextPath}/exportTimeAttendance"><fmt:message key="export"/></a></li>
                <li><a id="print" href="javascript:void(0)"><fmt:message key="print"/></a></li>
            	<li><a onclick="window.history.go(-1)" href="javascript:void(0)"><fmt:message key="back"/></a></li>
            	
            </ul>
            <div class="width_full" style="float: left;margin-top: 0px;width: 95%;">
            <!--课程信息-->
            	<div style="float: left;margin-top: 0px;font-size: 14px;">
            		<strong><fmt:message key="teacher.name"/></strong>
            		<span class="smv">${course.userByTeacher.cname}</span>
            		<strong><fmt:message key="course.name"/></strong>
            		<span class="smv">${course.courseName}</span>
            		<strong><fmt:message key="student.total"/></strong>
            		<span class="smv">${num}</span>
            		<strong><fmt:message key="team"/></strong>
            		<span class="smv">${course.schoolTerm.termName}</span>
            		<strong><fmt:message key="print.date"/></strong>
            		<span class="smv">${date}</span>
           	   </div>
            <!--课程信息-->
         	<!--考勤总表-->
            <table cellspacing="0" cellpadding="0" border="0" style="width:100%;border-right:solid 1px black;border-top:solid 1px black;border-bottom: solid 1px black;">
            <thead style="text-align: center">
            <tr >
            <%--<th width="5%" rowspan="2" style="border:solid 1px black;">序号</th> --%>
            <th width="10%" rowspan="2" style="border:solid 1px black;"><fmt:message key="student.number"/></th> 
            <th width="15%" rowspan="2" style="border-right:solid 1px black;border-top:solid 1px black;border-bottom: solid 1px black;">姓名</th>
            <th  width="10%" rowspan="2" style="border-right:solid 1px black;border-top:solid 1px black;border-bottom: solid 1px black;">班级</th>
            <th width="60%" align="center" colspan="${weeks}" style="border-right:solid 1px black;border-top:solid 1px black;border-bottom: solid 1px black;">周次</th>
            </tr>
            <tr>
            <c:forEach items="${week}" var="cur">
            <th width="60/('${weeks}')" style="border-right:solid 1px black;border-bottom: solid 1px black">
            	${cur}
            </th>
			</c:forEach>
            </tr>	
            </thead>
            <tbody style="text-align: center">
            	<c:forEach items="${students}" var="current" varStatus="i">
            	<tr>
            		<%--<td style="border-right:solid 1px black;border-left:solid 1px black;border-bottom: solid 1px black">${current.id}</td>--%>
            		<td style="border-right:solid 1px black;border-left:solid 1px black;border-bottom: solid 1px black">${current.userByStudentNumber.username}</td>
            		<td style="border-right:solid 1px black;border-left:solid 1px black;border-bottom: solid 1px black">${current.userByStudentNumber.cname}</td>
            		<td style="border-right:solid 1px black;border-left:solid 1px black;border-bottom: solid 1px black">${current.userByStudentNumber.schoolClasses.classNumber}</td>
            		<%--<td style="border-right:solid 1px black;border-left:solid 1px black;border-bottom: solid 1px black">--%>
            	<c:forEach items="${week}" var="curr">
            		<td width="60/('${weeks}')" style="border-right:solid 1px black;border-bottom: solid 1px black">
            		<c:choose>
                        	<c:when test="${timeAttendance==0}"><a href="${pageContext.request.contextPath}/courseSelect?idkey=${idkey}&num=${course.schoolCourseInfo.courseNumber}&currpage=1">0</a></c:when>
                        	<c:otherwise><a href="${pageContext.request.contextPath}/courseSelect?idkey=${idkey}&num=${course.schoolCourseInfo.courseNumber}&currpage=1">1</a></c:otherwise></c:choose>/<c:choose>
                        	<c:when test="${arrangeWeek==0}"><a href="${pageContext.request.contextPath}/courseSelect?idkey=${idkey}&num=${course.schoolCourseInfo.courseNumber}&currpage=1">0</a></c:when>
                        	<c:otherwise><a href="${pageContext.request.contextPath}/courseSelect?idkey=${idkey}&num=${course.schoolCourseInfo.courseNumber}&currpage=1">1</a></c:otherwise>
                    </c:choose>
            		<%--<c:forEach items="${list}" var="l">
            			${l}
            		</c:forEach>--%>
            		</td>
            	</c:forEach>
            	</tr>
            	</c:forEach>
            	
            	
            	<tr>
            	
            	</tr>
            </tbody> 
            </table>
            <!--考勤总表-->
            </div>
</body>
</html>

