<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_LeftList.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_Searcher.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/js/SpryTabbedPanels.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js" type="text/javascript" ></script> --%>
</head>
<body>
<!-- 导航栏 -->
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
					<div class="title">学期管理
					<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING">
					<a class="btn btn-new" href="${pageContext.request.contextPath}/tcoursesite/newTerm">新建</a>
					</sec:authorize></div>
				</div>
				<table>
					<thead>
						<tr>
							<th>序号</th>
							<th>学期名称</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>学年</th>
							<th>学期代号</th>
							<sec:authorize ifAnyGranted="ROLE_PREEXTEACHING,ROLE_SUPERADMIN">
							<th>操作</th>
							</sec:authorize>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${terms}" var="current" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${current.termName}</td>
								<td><fmt:formatDate value="${current.termStart.time}"
										pattern="yyyy-MM-dd" />
								</td>
								<td><fmt:formatDate value="${current.termEnd.time}"
										pattern="yyyy-MM-dd" />
								</td>
								<td>${current.yearCode}</td>
								<td>${current.termCode}</td>
								<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING">
								<td><%-- <c:choose>
										<c:when test="${fn:length(current.schoolWeeks)==0}"> --%>
											<a title="修改" class="btn btn-common" href="${pageContext.request.contextPath}/tcoursesite/editTerm?idKey=${current.id}" >修改</a>
											&nbsp;&nbsp;</td>
								</sec:authorize>
							</tr>
						</c:forEach>
					</tbody>
					<%--<script type="text/javascript">
						$("tr:odd").addClass("one");
						$("tr:even").addClass("two");
					</script>--%>
				</table>
			</div>
			<!-- 新建的DIV -->
		</div>
	</div>
</div>
    </div>



</body>
</html>
