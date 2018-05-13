<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<fmt:setBundle basename="bundles.projecttermination-resources" />

<html>
<head>
<meta name="decorator" content="iframe" />
<title><fmt:message key="html.title" />
</title>
<!--分页js  -->
 <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/operation/operationoutline.js"></script>
<!--分页js  -->

<!-- 打印插件的引用 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#ssssss").jqprint();
	})
	});
	
function exportAlls(){
  window.location.href="${pageContext.request.contextPath}/newoperation/experimentalmanagement?currpage=1";
}

//弹出批量导入实验大纲窗口
function importOperationOutlines(){
	$('#importOperationOutlines').window({left:"50px",top:"50px"});
  	$("#importOperationOutlines").window('open');
}
</script>
<!-- 打印插件的引用 -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css"
	rel="stylesheet">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/favicon.gif" />
</head>

<body>
	<div class="navigation">
		<div id="navigation">
			<ul>
				<li><a href="javascript:void(0)">实验室运行管理</a></li>
				<li class="end"><a href="javascript:void(0)">实验大纲管理</a></li>
			</ul>
		</div>
	</div>


	<!-- 结项申报列表 -->
	<!-- <div class="tab"> -->
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
			<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">实验大纲管理</div>
							<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING"><a class="btn btn-new"
								href="${pageContext.request.contextPath}/operation/newoperationproject">新建</a></sec:authorize>
							<input class="btn btn-new" type="button" value="打印" id="print">
							<input class="btn btn-new" type="button" value="导出"
								onclick="exportAll('${pageContext.request.contextPath}/operation/outline/exportOutline?page=${currpage}');">
							<a class="btn btn-new" href="###" onclick="importOperationOutlines();" >导入实验大纲</a>
						</div>
						<div class="tool-box">
							<form:form name="form"
								action="${pageContext.request.contextPath}/operation/experimentalmanagement?currpage=1"
								method="post" modelAttribute="operationOutline">
								<ul>
								   <li>实验大纲名称</li>
								<li><form:input path="labOutlineName"  />
									</li>
								</ul>
								<ul>
								<li style="margin-left:15px;">学期</li>
								<li style="float:left;">
								<select id="searchterm" name="searchterm">
											<option value="">全部学期</option>
       								<c:forEach items="${schoolTerm}" var="current"> 
       										<option value ="${current.id}" <c:if test="${cDictionary.id==current.id}"> selected="selected" </c:if>>${current.CName}</option>
        						</c:forEach>
    						</select></li>
        						<li style="overflow:hidden;">
        						       <input style="margin: 0 0 0 25px;" type="button"
										onclick="exportAll('${pageContext.request.contextPath}/operation/experimentalmanagement?currpage=1');"
										value="搜索">
										</li>
										<li>
										<input type="button"
										onclick="exportAlls()"
										value="取消搜索">
									</li>
								</ul>
							</form:form>
						</div>
						<div class="content-box">
							<table  id="ssssss">
								<thead>
									<tr align="left">
										<th class="thead" width="3%">序号</th>
										<th class="thead" width="15%">大纲名称</th>
										<th class="thead" width="10%">课程编号</th>
										<th class="thead" width="15%">课程名称</th>
										<th class="thead" width="5%">教师</th>
										<th class="thead" width="10%">学院</th>
										<th class="thead" width="10%">学期</th>
										<th class="thead" width="10%">操作</th>

									</tr>
								</thead>
								<tbody>
 
									<!-- 根据control层传递参数projects，对未结项项目列表并操作 -->
									<c:forEach items="${Outlinelist}" varStatus="i" var="s">
										<c:choose>
											<c:when test="${(i.count) % 2 == 0}">
												<c:set var="rowclass" value="rowtwo" />
											</c:when>
											<c:otherwise>
												<c:set var="rowclass" value="rowone" />
											</c:otherwise>
										</c:choose>
										<tr align="left" class="${rowclass}">
											<td>${i.index+1 }</td>
											<td>${s.labOutlineName}</td>
											<td>${s.schoolCourseInfoByClassId.courseNumber}</td>
											<td>${s.schoolCourseInfoByClassId.courseName}</td>
											<td>${s.user.cname}</td>
											<td><c:forEach items="${s.schoolAcademies}" var="sa">
													${sa.academyName}<br>
												</c:forEach></td>
											<td>
												<c:forEach items="${s.studyStages}" var="cd">
													${cd.CName}<br>
												</c:forEach>
											</td>
											
											<%--<td>${s.schoolTerm.termName }
											</td>--%>
											<td>
											<a href="${pageContext.request.contextPath}/operation/checkout?idkey=${s.id}"  > 查看</a> 
											<sec:authorize ifAnyGranted="ROLE_EXPERIMENTALTEACHING,ROLE_SUPERADMIN">
											<a href="${pageContext.request.contextPath}/operation/editoutline?idkey=${s.id}"  > 编辑</a> 
											<a href="${pageContext.request.contextPath}/operation/delectuotline?idkey=${s.id}" onclick="return confirm('您确认删除吗？')">删除</a>
											</sec:authorize>
											<%--<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_TEACHER">
											<!-- <a href="javascript:void(0)"  >下载</a> -->
											</sec:authorize>
											--%></td>
										</tr>
									</c:forEach>
								</tbody>
								<!-- 分页导航 -->

							</table>
							<div class="page">${totalRecords}条记录 &nbsp;
								共${pageModel.totalPage}页 <input type="hidden"
									value="${pageModel.lastPage}" id="totalpage" />&nbsp;
									<input
									type="hidden" value="${currpage}" id="currpage" /> <a href="javascript:void(0)"
									onclick="first();" target="_self">首页</a> <a href="javascript:void(0)"
									onclick="previous();" target="_self">上一页</a> <a href="javascript:void(0)"
									<!-- 跳转到选择/输入的页面 -->
		   第 <select class="chzn-select" onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
		   <option value="${pageContext.request.contextPath}/operation/experimentalmanagement?currpage=${currpage}">${currpage}</option>
		   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
           <c:if test="${j.index!=currpage}">
           <option value="${pageContext.request.contextPath}/operation/experimentalmanagement?currpage=${j.index}">${j.index}</option>
           </c:if>
           </c:forEach>
           </select> 页
									
									 <input
									type="hidden" value="${currpage}" id="currpage" /> <a href="javascript:void(0)"
									onclick="next();" target="_self">下一页</a> <a href="javascript:void(0)"
									onclick="last();" target="_self">末页</a>
								</td>
							</div>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>
	    <!-- 弹出导入实验大纲窗口 -->
		<div id= "importOperationOutlines"  class ="easyui-window panel-body panel-body-noborder window-body" title= "导入模版" modal="true" dosize="true" maximizable= "true" collapsible ="true" minimizable= "false" closed="true" iconcls="icon-add" style=" width: 600px; height :400px;">
        <form:form name="importForm" action="${pageContext.request.contextPath}/operation/importOperationOutlines" enctype ="multipart/form-data">
	         <br>
	         <input type="file" id="file" name ="file"  required="required" />
	         <input type="submit"  value ="开始上传" />
	         <br><br><hr><br>
		<a href="${pageContext.request.contextPath}/pages/importSample/大纲上传范例.xls">点击此处</a>，下载范例<br><br><hr><br>
		
		<a href="${pageContext.request.contextPath}/pages/importSample/编号名称对应表.xlsx">点击此处</a>，下载编号名称对应表<br><br><hr><br>
		示例图片：<br>
		<img src="${pageContext.request.contextPath}/images/importSample/operationSample.png" heigth ="65%" width="65%" />
        </form:form>
        </div>
</body>
<!-------------列表结束----------->
</html>