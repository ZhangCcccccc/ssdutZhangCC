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
  	<script type="text/javascript">
		function targetUrl(url)
		{
    		document.queryForm.action=url;
    		document.queryForm.submit();
  		}
		
		function cancel(){
			window.location.href="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1";
		}
  	</script>
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
					<div class="title">选课组管理
					</div>
					<div class="tool-box">
					<form:form name="queryForm" action="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1" method="post" modelAttribute="tCourseSite">
					   	<ul>
							<li>选择学期：</li>
							<li>
								<form:select id="termId" path="schoolTerm.id" class="select_search">
								<form:option value="-1" >请选择</form:option>
									<c:forEach items="${schoolTerms}" var="schoolTerm">
										<form:option value="${schoolTerm.id}" >${schoolTerm.termName }</form:option>
									</c:forEach>
								</form:select>
						 	</li>
				          	<li>课程标题：</li>
				           	<li>
				           		<form:input id="title" path="title" />
							</li>
							<li>授课教师：</li>
				           	<li>
				           		<form:input id="userByCreatedByCname" path="userByCreatedBy.cname" />
							</li>
					        <li>
					        	<input type="submit" value="查询"/>
					    		<input type="button" value="取消" onclick="cancel();"/>
					    	</li>
						</ul>
					</form:form>
							
						</div>
					
				</div>
				<table>
					<thead>
						<tr>
							<th>课程名称</th>
							<th>选课组编号</th>
							<th>学期</th>
							<th>教师姓名</th>
							<th>是否开放</th>
							<th>学生名单</th>
							<sec:authorize ifAnyGranted="ROLE_TEACHER">
							<th>操作</th>
							</sec:authorize>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${TCourseSites}" var="current" varStatus="i">
							<tr>
								<td>${current.title}</td>
								<td> ${current.schoolCourseInfo.courseNumber} </td>
								<td>${current.schoolTerm.id}</td>								
								<td>${current.userByCreatedBy.cname}</td>
								<td>${current.isOpen}</td>
								<td><a  href="${pageContext.request.contextPath}/tcoursesite/SelectCourse/courseStudentsList?currpage=1&tCourseSiteId=${current.id }">${fn:length(current.TCourseSiteUsers)}</a></td>
								<sec:authorize ifAnyGranted="ROLE_TEACHER">
								<td><%-- <c:choose>
										<c:when test="${fn:length(current.schoolWeeks)==0}"> --%>
											<a title="删除" class="btn btn-common" href="${pageContext.request.contextPath}/tcoursesite/deleteSelectCourse?tCourseSiteId=${current.id}" onclick="return confirm('确认要删除吗？')">删除</a>
											<a title="修改" class="btn btn-common" href="${pageContext.request.contextPath}/tcoursesite/editSelectCourse?tCourseSiteId=${current.id}" >修改</a>
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
	<!-- 分页[s] -->
						<div class="page" >
					        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
					    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1')" target="_self">首页</a>			    
						<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
						第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
						<option value="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
						<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
					    <c:if test="${j.index!=pageModel.currpage}">
					    <option value="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=${j.index}">${j.index}</option>
					    </c:if>
					    </c:forEach></select>页
						<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
					 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=${pageModel.lastPage}')" target="_self">末页</a>
					    </div>
					    <!-- 分页[e] -->
</div>
    </div>



</body>
</html>
