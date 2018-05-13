<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>项目立项-学院主管领导审核</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<%--<script src="${pageContext.request.contextPath}/js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
--%><script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
</head>
<body>

<div class="iStyle_Conteiner">
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">实验建设</a></li>
	<li><a href="javascript:void(0)">项目立项审核</a></li>
	<li class="end">学院主管领导审核</li>
</ul>
</div>
</div>
<div class="right-content">	
	 <div class="content-box">
    <table>
      <tr>
        <th>实验室项目编号：</th><td>${labConstructionProject.projectNumber}</td>
        <th>实验室项目名称：</th><td>${labConstructionProject.projectName}</td>
        <th>所属院（系、部）：</th><td>${labConstructionProject.schoolAcademy.academyNumber}</td>
        <th>项目负责人：</th><td>${labConstructionProject.user.username}</td>
      </tr>
      <tr>
        <th>联系电话：</th><td>${labConstructionProject.telephone}</td>
        <th>Email地址：</th><td>${labConstructionProject.email}</td>
        <th>预算总额：</th><td>${labConstructionProject.projectBudget}</td>
        <th>填报时间：</th><td><fmt:formatDate value="${labConstructionProject.createdAt.time}" pattern="yyyy-MM-dd"/>  </td>
      </tr>
      <tr>
        <th>项目分析：</th><td>${labConstructionProject.projectAnalysis}</td>
      </tr>
      <tr>  
        <th>建设方案：</th><td>${labConstructionProject.projectScheme}</td>
      </tr>
      <tr>  
        <th>审核状态：</th>
        <td>
			<c:if test="${labConstructionProject.auditResults==1}">草稿</c:if>
		    <c:if test="${labConstructionProject.auditResults==2}">审核中</c:if>
		    <c:if test="${labConstructionProject.auditResults==3}">审核通过</c:if>
		    <c:if test="${labConstructionProject.auditResults==4}">审核拒绝</c:if>
		</td>
      </tr>
    </table>
  </div>
  
  
  
	<div id="TabbedPanels1" class="TabbedPanels">
	
	<!-- 标题栏  -->
	  <ul class="TabbedPanelsTabGroup">
	  	
			<li class="TabbedPanelsTab selected" tabindex="0">
			<a href="${pageContext.request.contextPath}/labconstruction/collegeProjectAudit?labConstructionProjectId=${labConstructionProject.id }">学院主管领导审核</a>
			</li>
			
			<li class="TabbedPanelsTab" tabindex="0">
			<a href="${pageContext.request.contextPath}/labconstruction/deanProjectAudit?labConstructionProjectId=${labConstructionProject.id }">教务处审核</a>
			</li>
		
			<li class="TabbedPanelsTab" tabindex="0">
			<a href="${pageContext.request.contextPath}/labconstruction/schoolProjectAudit?labConstructionProjectId=${labConstructionProject.id }">校领导审核</a>
			</li>
		
	  </ul>
	  
	<!-- 内容栏 -->  
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">审核意见</div>
				<c:if test="${labConstructionProject.stage==0 }"> <!-- 未审核阶段 -->
				<c:if test="${fn:contains(collegeLeaders,user.username) && labConstructionProject.auditResults==2}"> <!-- 当前登陆人是学院主管领导,当前处于审核中状态 -->
						<form:form action="${pageContext.request.contextPath}/labconstruction/saveCollegeProjectAudit?labConstructionProjectId=${labConstructionProject.id}" method="post" modelAttribute="collegeProjectAudit">
							<table>
								<tr>
									<td>审核意见</td>
									<td><form:textarea path="comments"/> </td>
								</tr>
								<tr>
									<td>结果</td>
									<td>
									<form:radiobuttons path="CDictionary.id" items="${results}" itemLabel="CName" itemValue="id"/>
									</td>
								</tr>
								
								<tr>
									<td>
										<input type="submit" value="提交">
										<input type="button" value="取消">
									</td>
								</tr>
								
							</table>
							</form:form>				
				</c:if>	
				
				</c:if>
				
				
				<c:if test="${fn:indexOf(collegeLeaders,user.username)==-1 && labConstructionProject.stage<=0 && labConstructionProject.stage!=-1 }">  
				<!-- 当前登录人不是学院主管领导，当前阶段还没有到学院领导审核阶段，并且当前不是审核拒绝状态 -->
					<table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter" >
					    <tr><td colspan="6">学院主管领导信息 </td></tr>
					 <c:forEach items="${collegeLeaders}"  var="cl">
						 <tr align="left" >
						     <td>姓名：   ${cl.cname}</td> 
						     <td>工号：   ${cl.username}</td> 
							 <td>部门：   ${cl.schoolAcademy.academyName}</td> 
						     <td>联系方式：   ${cl.telephone}</td> 
						 </tr> 
					 </c:forEach>
					
					</table>
				
				
				</c:if>	
				
				
			<c:if test="${labConstructionProject.stage>0 || labConstructionProject.stage==-1}">   <!-- 想要看到当前的审核意见，必须高于当前阶段/或者审核拒绝状态也可以 -->
				<tr>
					<td>审核意见：</td>
					<td>${collegeProjectAuditDone.comments}</td>
				</tr>
				<tr>	
					<td>审核结果：</td>
					<td>${collegeProjectAuditDone.CDictionary.CName}</td>
				</tr>
			</c:if>
				
			</div>
		</div>
		
	  </div>
	</div>
	
	
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</div>


</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_Icons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_LeftList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Downlist.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Allpages.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Interface.js"></script>
</body>
</html>
