<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta name="decorator" content="iframe" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
</head>
<body>
<!-- 导航栏 -->
<div class="navigation">
<div id="navigation">
<ul>
<li><a href="">系统管理</a></li>
<li><a href="${pageContext.request.contextPath}/system/listUser?currpage=1">用户管理</a></li>
<li class="end"><a href="">信息展示</a></li>
</ul>
</div>
</div>
<!-- 导航栏 -->
<div class="content-box">
				<div class="title">
					<div id="title">信息展示</div>
				
        			<a class="btn btn-return" onclick="window.history.go(-1)">返回</a>
				</div>

<!-- 导航栏 -->
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
				<%--<tr>
					<th>是否在校:</th>
					<td
					<c:choose>
                           		<c:when test="${users.userStatus==0}">
                           			否
                           		</c:when>
                           		<c:otherwise>
                           			是
                           		</c:otherwise>
                           </c:choose>
					
					</td>
					</tr>
					<tr>
					<th>辅导员工号:</th>
					<td>${users.teacherNumber}</td>
				</tr>--%>
				<c:choose>
                        <c:when test="${users.userRole==0}">
                     <%-- <tr>    		
					<th>专业:</th>
					<td>${users.schoolMajor.majorName}</td>
				</tr> --%>
				<tr>
					<th>班级:</th>
					<td>${users.schoolClasses.className}</td>
				</tr>
				</c:when>
				</c:choose>
				<%--<tr>
					<th>联系电话:</th>
					<td>${users.telephone}</td>
				</tr>
				<tr>
					<th>Email:</th>
					<td>${users.email}</td>
				</tr>
				<tr>
					<th>职称:</th>
					<td>${users.CMajorDirection.directionName}</td>
				</tr>
				<tr>					
					<th>学籍状态:</th>
					<td>${users.enrollmentStatus}</td>
				</tr>
				<tr>
					<th>是否在籍:</th>
					<td>${users.ifEnrollment}</td>
					<td>学生类别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${users.CStudentType.name}</td>
				</tr>
				--%><tr>
				<th>最后登陆时间:</th>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:MM:SS" type="both" value="${users.lastLogin.time}"/></td>
				</tr>
				<%--<tr>
					<td>所在年级:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${users.grade}</td>
					<td>创建时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${users.createdAt.time}"/></td>
				</tr>--%>
				<%--<tr>
					<td>更新时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${users.updatedAt.time}"/></td>
					<td>最后登录时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${users.lastLogin.time}"/></td>
				</tr>--%>
            </table>
          </div>
              </div>
          <div class="moudle_footer">
    </div>
</body>
</html>


