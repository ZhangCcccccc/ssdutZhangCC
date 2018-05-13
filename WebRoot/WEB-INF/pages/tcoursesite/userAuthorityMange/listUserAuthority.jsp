<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<!--直接排课  -->

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
</head>

<body>
	<div class="lest_content">
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
	<div id="TabbedPanels1" class="TabbedPanels"  style="float:right;width:80%">
		<div class="TabbedPanelsContentGroup">
			<div class="TabbedPanelsContent">
				<div class="content-box">
				<div class="title">用户权限组列表</div>
					<table> 
					<thead>
					<tr>
					   <th>序号</th>
					   <th>用户组标识</th>
					   <th>用户组名称</th>
					   <th>用户组人数</th>
					   <th>操作</th>
					</tr>
					</thead>
					<tbody>
					<c:if test="${isSUPERADMIN}">
					
					<c:forEach items="${AllUserAuthority}" var="a"  varStatus="i">	
					
					<tr>
					    <th>${i.count}</th>
					    <td>${a.authorityName}</td>
					    <td>${a.authorityCname}</td>
					    <td>${a.authorityNumber}</td>
					    
					    <td>
					    <a class="btn btn-common" href='${pageContext.request.contextPath}/tcoursesite/listUserAuthorityDetail?page=1&Id=${a.authorityId}'>查看</a>&nbsp;&nbsp;
					    </td>
					    
					</tr>
					
					</c:forEach> 
					</c:if>
					<c:if test="${!isSUPERADMIN && isEXCENTERDIRECTOR}">
					<c:forEach items="${userAuthorityForEXCENTERDIRECTOR}" var="a"  varStatus="i">	
					
					<tr>
					    <th>${i.count}</th>
					    <td>${a.authorityName}</td>
					    <td>${a.authorityCname}</td>
					    <td>${a.authorityNumber}</td>
					    
					    <td>
					    <a class="btn btn-common" href='${pageContext.request.contextPath}/tcoursesite/listUserAuthorityDetail?page=1&Id=${a.authorityId}'>查看</a>&nbsp;&nbsp;
					    </td>
					    
					</tr>
					
					</c:forEach>
					</c:if>
					
					</tbody>
					
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
