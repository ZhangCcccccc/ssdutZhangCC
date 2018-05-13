<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>项目经费-资产管理处审核</title>
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
	<li><a href="javascript:void(0)">项目经费审核</a></li>
	<li class="end">资产管理处审核</li>
</ul>
</div>
</div>
<div class="right-content">	
	 <div class="content-box">
    <table>
      <tr>
        <th>项目负责人：</th><td>${labConstructionProject.user.username}</td>
        <th>项目名称：</th><td>${labConstructionProject.projectName}</td>
        <th>项目编号：</th><td>${labConstructionProject.projectNumber}</td>
        <th>经费编号：</th><td>${labConstructionFunding.fundingNumber}</td>
      </tr>
      <tr>  
        <th>审核状态：</th>
        <td>
			<c:if test="${labConstructionPurchase.auditResults==1}">草稿</c:if>
		    <c:if test="${labConstructionPurchase.auditResults==2}">审核中</c:if>
		    <c:if test="${labConstructionPurchase.auditResults==3}">审核通过</c:if>
		    <c:if test="${labConstructionPurchase.auditResults==4}">审核拒绝</c:if>
		</td>
      </tr>
    </table>
  </div>
  
  
   <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab selected" id="s1">设备详单</li>
	</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
    <div class="content-box">
    <table>
      <thead>
      <tr>
		<th>序号</th>
		<th>设备名称</th>
		<th>型号/规格</th>
		<th>数量</th>
		<th>设备单价（元）</th>
	  </tr>
      </thead>
      <tbody>
      <c:forEach items="${labConstructionDevices}" var="curr" varStatus="i">
      <tr>
		<td>${i.count}</td>
		<td>${curr.deviceName}</td>
		<td>${curr.deviceModel}</td>
		<td>${curr.deviceQuantity}</td>
		<td>${curr.devicePriceSig}</td>
      </tr>
      </c:forEach>
      </tbody>
    </table>
    </div>
  </div>
  </div>
  </div>
  
  
	<div id="TabbedPanels1" class="TabbedPanels">
	
	<!-- 标题栏  -->
	  <ul class="TabbedPanelsTabGroup">
	  	
			<li class="TabbedPanelsTab" tabindex="0">
			<a href="${pageContext.request.contextPath}/labconstruction/excenterFundingAudit?labConstructionFundingId=${labConstructionFunding.id }">实验室中心主任</a>
			</li>
			
			<li class="TabbedPanelsTab selected" tabindex="0">
			<a href="${pageContext.request.contextPath}/labconstruction/assetFundingAudit?labConstructionFundingId=${labConstructionFunding.id }">资产管理处审核</a>
			</li>
		
			<li class="TabbedPanelsTab" tabindex="0">
			<a href="${pageContext.request.contextPath}/labconstruction/schoolFundingAudit?labConstructionFundingId=${labConstructionFunding.id }">校领导审核</a>
			</li>
		
			<li class="TabbedPanelsTab" tabindex="0">
			<a href="${pageContext.request.contextPath}/labconstruction/cfoFundingAudit?labConstructionFundingId=${labConstructionFunding.id }">财务总监审核</a>
			</li>
	  </ul>
	  
	<!-- 内容栏 -->  
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">审核意见</div>
				<c:if test="${labConstructionFunding.stage==1 }"> <!-- 资产管理处审核阶段 -->
				<c:if test="${fn:contains(assetManagers,user.username) && labConstructionFunding.auditResults==2}"> <!-- 当前登陆人是资产管理员,当前处于审核中状态 -->
						<form:form action="${pageContext.request.contextPath}/labconstruction/saveAssetFundingAudit?labConstructionFundingId=${labConstructionFunding.id}" method="post" modelAttribute="assetFundingAudit">
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
				
				
				<c:if test="${fn:indexOf(assetManagers,user.username)==-1 && labConstructionFunding.stage<=1 && labConstructionFunding.stage!=-1 }">  
				<!-- 当前登录人不是资产管理员，当前阶段还没有到验资产管理处审核阶段，并且当前不是审核拒绝状态 -->
					<table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter" >
					    <tr><td colspan="6">资产管理员信息 </td></tr>
					 <c:forEach items="${assetManagers}"  var="am">
						 <tr align="left" >
						     <td>姓名：   ${am.cname}</td> 
						     <td>工号：   ${am.username}</td> 
							 <td>部门：   ${am.schoolAcademy.academyName}</td> 
						     <td>联系方式：   ${am.telephone}</td> 
						 </tr> 
					 </c:forEach>
					
					</table>
				
				
				</c:if>	
				
				
			<c:if test="${labConstructionFunding.stage>1 || labConstructionFunding.stage==-1}">   <!-- 想要看到当前的审核意见，必须高于当前阶段/或者审核拒绝状态也可以 -->
				<tr>
					<td>审核意见：</td>
					<td>${assetFundingAuditDone.comments}</td>
				</tr>
				<tr>	
					<td>审核结果：</td>
					<td>${assetFundingAuditDone.CDictionary.CName}</td>
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
