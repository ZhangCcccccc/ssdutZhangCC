<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
</head>
<body>
<!-- 导航栏 -->
    <div class="lest_content " >
		<div class="left_nav">
        	<div class="left_user_box">
                <img src="${pageContext.request.contextPath}/images/index/user.png"/>
            </div>
            <div class="left_nav_box">
                <ul class="left_nav_list">
	                <sec:authorize ifAnyGranted="ROLE_TEACHER">
	                    <li <c:if test="${select eq 'tcourse' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1">选课组管理</a>
	                    </li>
	                </sec:authorize>
	                <sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
	                    <li <c:if test="${select eq 'authority' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listUserAuthority">权限管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'user' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=1">用户管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'term' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listTerm?currpage=1">学期管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'visualization' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/visualization/roomList?page=1">可视化管理</a>
	                    </li>
		            </sec:authorize>
                </ul>
            </div>  
            <div class="power2" style="display: block;">
                Power by <a href="http://www.gvsun.com" target="_blank">Gvsun</a>
            </div>
        </div>
<div id="TabbedPanels1" class="TabbedPanels" style="float:right;width:80%">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">
					<div id="title">信息展示</div>
				
        			<a class="btn btn-return" onclick="window.history.go(-1)">返回</a>
				</div>
    <div id="userInfo" >
              <table>
				<tr>
					<th>用户工号:</th>
					<td>${users.username}</td>
				<tr>
				<tr>
					<th>用户姓名:</th>
					<td>${users.cname}</td>
				</tr>
				<tr>
					<th>用户身份:</th>
					<td>
						<c:choose>
                        <c:when test="${users.userRole==0}">
                         			学生
                        </c:when>
                        <c:otherwise>
                           			教师
                         </c:otherwise>
                           </c:choose></td>
                         </tr>
                         <tr>
                       <th>
					学院/部门:</th>
					<td>${users.schoolAcademy.academyName}</td>
				</tr>
				<c:choose>
                <c:when test="${users.userRole==0}">
                <tr>    		
					<th>专业:</th>
					<td>${users.majorNumber}</td>
				</tr>
				<tr>
					<th>班级:</th>
					<td>${users.schoolClasses.className}</td>
				</tr>
				</c:when>
				</c:choose>
				<%--<tr>
				<th>最后登陆时间:</th>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:MM:SS" type="both" value="${users.lastLogin.time}"/></td>
				</tr> --%>
            </table>
	</div>
</div>
</div>
</div>
</div>
         <div class="moudle_footer">
   		 </div>
    </div>
</body>
</html>


