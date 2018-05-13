<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<head>
</head>
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
<!-- 导航栏 -->
<div id="TabbedPanels1" class="TabbedPanels" style="float:right;width:80%">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">新增用户</div>  
 <form:form action="${pageContext.request.contextPath}/tcoursesite/saveNewUser" method="POST" modelAttribute="User">
						<table>
							<tr>
								<td style="text-align:right">用户工号</td>
								<td><form:input path="username" required="true" />
								</td>
							</tr>
							<tr>
								<td style="text-align:right">用户密码</td>
								<td><form:input path="password" required="true" />
								</td>
							</tr>
							<tr>
								<td style="text-align:right">用户姓名</td>
								<td><form:input path="cname" required="true" />
								</td>
							</tr>
							<tr>
								<td style="text-align:right">学院</td>
								<td>
							    	<form:select  path="schoolAcademy.academyNumber" required="true">
							    			<option value="">请选择</option>
										<c:forEach items="${academys}" var="a">
							    				<option value="${a.academyNumber}">${a.academyName}</option>
							    		</c:forEach>
							    	</form:select>
							   	</td>
								</td>
							</tr>
							<tr>
								<td style="text-align:right">用户身份</td>
								<td>
									<form:select path="userRole" required="true" >
										<option value="0">学生</option>
										<option value="1">教师</option>
									</form:select>
								</td>
								
							</tr>
							<form:input type="hidden" path="createdAt" value="${duedate}" />
							<tr>
								<td colspan="2"><span class="pull-right">
										<button class="btn btn-xs btn-default btn-white btn-round"
											type="submit">保存</button>
										<button class="btn btn-xs btn-default btn-white btn-round"
											type="submit" onclick="window.history.go(-1)">取消</button> </span></td>
							</tr>
						</table>
					</form:form>
</div>
</div>
</div>
</div>
</div>