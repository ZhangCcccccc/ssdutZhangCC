<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta name="decorator" content="iframe"/>
  	<script type="text/javascript">
		function targetUrl(url)
		{
    		document.queryForm.action=url;
    		document.queryForm.submit();
  		}
  	</script>
</head>
  
<body>
	<div class="navigation">
		<div id="navigation">
			<ul>
				<li><a href="javascript:void(0)">教学课程</a>
				</li>
				<li class="end"><a href="javascript:void(0)">课程列表</a>
				</li>
			</ul>
		</div>
	</div>

	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
			<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">课程列表</div>
						</div>

						<div class="tool-box">
							<form:form name="queryForm"
								action="${pageContext.request.contextPath}/tcoursesite/listTCourseSite?currpage=1"
								method="post" modelAttribute="tCourseSite">
								<ul>
									<li>学期：</li>
									<li>
										<form:select id="termId" path="schoolTerm.id" >
											<form:option value="">请选择</form:option>
											<c:forEach items="${terms}" var="term">
												<form:option value="${term.id}">${term.termName }</form:option>
											</c:forEach>
										</form:select>
									</li>
									<li>课程名称：</li>
									<li><form:input id="title" path="title" /></li>
									<li>
										<input type="button" value="取消" onclick="window.history.go(0)" />
										<input type="submit" value="查询" /> 
									</li>
								</ul>

							</form:form>
						</div>

						<table class="tb" id="my_show">
							<thead>
								<tr>
									<th>课程名称</th>
									<th>学期</th>
									<th>教师名称</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${tCourseSites}" var="curr">
									<tr>
										<td>${curr.title}</td>
										<td>${curr.schoolTerm.termName}</td>
										<td>
											${curr.userByCreatedBy.cname }
										</td>
										<td><a href="${pageContext.request.contextPath}/tcoursesite?tCourseSiteId=${curr.id}" target="_parent">详情</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 分页[s] -->
						<div class="page" >
					        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
					    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/tcoursesite/listTCourseSite?currpage=1')" target="_self">首页</a>			    
						<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/tcoursesite/listTCourseSite?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
						第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
						<option value="${pageContext.request.contextPath}/tcoursesite/listTCourseSite?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
						<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
					    <c:if test="${j.index!=pageModel.currpage}">
					    <option value="${pageContext.request.contextPath}/tcoursesite/listTCourseSite?currpage=${j.index}">${j.index}</option>
					    </c:if>
					    </c:forEach></select>页
						<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/tcoursesite/listTCourseSite?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
					 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/tcoursesite/listTCourseSite?currpage=${pageModel.lastPage}')" target="_self">末页</a>
					    </div>
					    <!-- 分页[e] -->
					</div>
  				</div>
  			</div>
  		</div>
  	</div>
</body>
</html>
