<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>无标题文档</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_LeftList.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_Searcher.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_SystemUI_Allpages.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<style>
	body{
		overflow:scroll !important;
	}
</style>

</head>
<body>

<!--教师角色  -->
<%
if(session.getAttribute("selected_role").toString().equals("ROLE_TEACHER")){
%>
	<div class="iStyle_Conteiner">
		<div class="iStyle_RightInner">
			<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">教学管理</a>
						</li>
						<li class="end"><a href="javascript:void(0)">练习与测验</a>
						</li>
					</ul>
				</div>
			</div>

			<div class="right-content">
				<div id="TabbedPanels1" class="TabbedPanels">
					<div class="TabbedPanelsContentGroup">
						<div class="TabbedPanelsContent">
							<div class="tool-box">
								<form:form name="form" method="Post"
									action="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=1&id=-1&status=${status }">
									<br>
									<ul>

										<li></li>
										<li><mytag:JspSecurity realm="add" menu="exam">
												<a
													href="${pageContext.request.contextPath}/teaching/exam/newExam"><input
													type="button" value="新建测验">
												</a>
											</mytag:JspSecurity></li>
										<li>或 <mytag:JspSecurity realm="add" menu="exam">
												<a
													href="${pageContext.request.contextPath}/teaching/test/newTest"><input
													type="button" value="新建考试">
												</a>
											</mytag:JspSecurity></li>
										<%-- <li><a href="${pageContext.request.contextPath}/timetable/groupAdmin?currpage=1&status=${status }&id=-1"><input type="button" value="分批管理"></a></li> 
 --%>
									</ul>
									<br>
									<br>
								</form:form>
							</div>
						</div>
					</div>
				</div>
				<div class="iStyle_Searchfeild">
					<div class="iStyle_Tagsfeild"></div>
					<div class="iStyle_Marksfeild" id="abc">
						<mytag:JspSecurity realm="check" menu="exam">
							<div class="iStyle_Mark iStyle_ActiveMark"
								src="${pageContext.request.contextPath}/teaching/exam/examList?status=0">
								<span>可发布测验</span>
							</div>
							<div class="iStyle_Mark"
								src="${pageContext.request.contextPath}/teaching/exam/examList?status=1">
								<span>已发布测验</span>
							</div>
							<div class="iStyle_Mark"
								src="${pageContext.request.contextPath}/teaching/test/testList?status=0">
								<span>未发布考试</span>
							</div>
							<div class="iStyle_Mark"
								src="${pageContext.request.contextPath}/teaching/test/testList?status=1">
								<span>已发布考试</span>
							</div>
						</mytag:JspSecurity>

					</div>
				</div>

				<div class="iStyle_Iframe" id="ssd">
					<iframe scrolling="yes"
						src="${pageContext.request.contextPath}/teaching/exam/examList?status=0"
						id="mainframe"> </iframe>

				</div>
			</div>
		</div>
	</div>

	<!--学生角色  -->
<%}else if(session.getAttribute("selected_role").toString().equals("ROLE_STUDENT")){%>
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">教学管理</a></li>
	<li class="end">练习与测验</li>
	
	<%--<a class="btn btn-new" href="javascript:void(0);" onclick="window.history.go(-1)">返回</a>
--%></ul>
</div>
</div>

<br>
&nbsp;<b>参加测验</b>
<br>

<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent0">
<!-- 查询、导出、打印 -->
<div class="content-box">
<table  id="my_show"> 
<thead>
    <tr>                   
        <th>序号</th>
        <th>标题</th>
        <th>提交次数/提交限制</th>
        <th>过期日期</th>
    </tr>
</thead>
<tbody>
<c:forEach items="${tAssignments}" var="current"  varStatus="i">
<tr>    
        <td>
        ${i.count }
        </td>
        <td><a href="${pageContext.request.contextPath}/teaching/exam/beginExam?examId=${current.id}" target="_blank">${current.title}</a><br>
        </td>
        <td><font color=red>${current.submitTimeForStudent }</font>/<font color=green>${current.TAssignmentControl.timelimit==0?'无限制':current.TAssignmentControl.timelimit }</font></td>
        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TAssignmentControl.duedate.time}" type="both"/></td>

</tr>
</c:forEach>
</tbody>
<!-- 分页导航 -->

</table>
</div>
</div>
</div>

<br>
<b>已提交测试</b>
<br>
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent0">
<!-- 查询、导出、打印 -->
<div class="content-box">
<table  id="my_show"> 
<thead>
    <tr>                   
        <th>序号</th>
        <%--<th>试卷序号</th>
        --%><th>试卷标题</th>
        <%--<th>试卷介绍</th>
        --%><th>成绩</th>
        <th>提交日期</th>
        <th>满分分值</th>
    </tr>
</thead>
<tbody>
<c:forEach items="${TAssignmentGrading}" var="current"  varStatus="i">
<tr>    
        <td>
        ${i.count }
        </td>
        <%--<td>${current.TAssignment.id}</td>
        --%><td>${current.TAssignment.title}</td>
        <%--<td>${current.TAssignment.content}</td>
        --%><td>
         ${current.finalScore}
        </td>
        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.submitdate.time}" type="both"/></td>
        <td><font color=red>${current.TAssignment.TAssignmentAnswerAssign.score }</font></td>
</tr>
</c:forEach>
</tbody>
<!-- 分页导航 -->

</table>
</div>
</div>
</div>
<%} %>
<script type="text/javascript">		
$(document).ready(function(e) {
		var hei=0;
		var is_debug_resize=setInterval(function(){
			if($("#mainframe").length>0){
				//hei=0;
				
			//	right_frame.document.body.scrollHeight
				hei=$("#mainframe").contents().find("html").get(0).scrollHeight;
				//console.log(hei);
				$("#mainframe").css({"height":hei+'px'});
				$("#ssd").css({"height":hei+'px'});
			}
		},100);
	});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_Icons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_LeftList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Downlist.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Allpages.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Interface.js"></script>
</body>
</html>
