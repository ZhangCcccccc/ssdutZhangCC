<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>

<head>
<meta name="decorator" content="iframe" />
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_LeftList.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/is_Searcher.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/js/SpryTabbedPanels.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js" type="text/javascript" ></script> --%>
</head>
<!-- 导航栏 -->
<div class="navigation">
<div id="navigation">
<ul>
<li><a href="">系统管理</a></li>
<li class="end"><a href="${pageContext.request.contextPath}/system/listTerm?currpage=1">学期管理</a></li>
</ul>
</div>
</div>
<!-- 导航栏 -->
<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			
			<div class="content-box">
				<div class="title">
					<div class="title">学期管理
					<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING">
					<a class="btn btn-new" href="${pageContext.request.contextPath}/system/newTerm">新建</a>
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
								<td><c:choose>
										<c:when test="${current.flag==0 || empty current.flag}">
											<a title="修改" class="btn btn-common" href="${pageContext.request.contextPath}/system/editTerm?idKey=${current.id}" >修改</a>
											<a title="删除" class="btn btn-common" href="${pageContext.request.contextPath}/system/deleteTerm?idKey=${current.id}" onclick="return confirm('是否确定删除？');" >删除</a>
											<a title="生成周次" class="btn btn-common"
												href="${pageContext.request.contextPath}/system/createWeek?id=${current.id}" onclick="return confirm('生成周次后将不能修改学期日期，是否确定？');">生成周次</a> 
										</c:when>
										<c:otherwise>已生成周次
										<a title="查看" class="btn btn-common"
												href="${pageContext.request.contextPath}/system/viewSchoolTerm?idKey=${current.id}">查看</a></c:otherwise>
									</c:choose>&nbsp;&nbsp;</td>
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
			<%--<div class="edit-edit">
				<table>
					<tr>
						<th>Id
						</td>
						<td><input type="text"></input></td>
						<th>实验室名称
						</td>
						<td><input type="text"></input></td>
					</tr>
					<tr>
						<th>实验室编号
						</td>
						<td><input type="text"></input></td>
						<th>实验室英文名称
						</td>
						<td><input type="text"></input></td>
					</tr>
					<tr>
						<th>实验室简称
						</td>
						<td><input type="text"></input></td>
						<th>实验室类型
						</td>
						<td><input type="text"></input></td>
					</tr>
					<tr>
						<th>验室描述
						</td>
						<td><input type="text"></input></td>
						<th>实验室容量
						</td>
						<td><input type="text"></input></td>
					</tr>
				</table>
			</div>--%>
			<!-- 新建的DIV -->
			<%--<script type="text/javascript">
				$(".btn-edit").click(function() {
					//						  $(".edit-content").slideUp();
					//						  $(".edit-edit").slideDown();
					$(this).parent().next().slideUp();
					$(this).parent().next().next().slideDown();
					$(this).hide();
					$(this).next().show();
					$(this).next().next().show();
				});
				$(".btn-return").click(function() {
					//						 $(".edit-edit").slideUp();
					//						 $(".edit-content").slideDown();
					//						 $(".btn-return").hide();
					//						 $("#save").hide();
					//						 $(".btn-edit").show();
					$(this).hide()//她隐藏
					$(this).prev().hide()//她前面的隐藏
					$(this).prev().prev().show()//她前面的前面显示
					$(this).parent().next().slideDown()//她爸的后一个显示
					$(this).parent().next().next().slideUp()//她爸的后一个的后一个隐藏
				})
			</script>--%>
		</div>
	</div>
</div>
