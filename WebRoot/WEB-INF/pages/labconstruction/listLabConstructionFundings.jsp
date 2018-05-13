<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 -->
  
  <script type="text/javascript">
  $(document).ready(function(){
        var s=${status};
        for(var i=0;i<=5;i++)
        {
        	if(i==s)
        	{
        		$("#s"+i).addClass("TabbedPanelsTab selected");
        	}
        	else
        	{
        		$("#s"+i).addClass("TabbedPanelsTab");
        	}
        }
	});
  //取消查询
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=1&status=${status}";
  }
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  
//查看当前审核状态的弹窗
  function viewStage(id)
  {
	  $.ajax({
		  url:"${pageContext.request.contextPath}/labconstruction/viewFundingStage?labConstructionFundingId="+id,
          type:"POST",
          success:function(data){//AJAX查询成功
                 document.getElementById("viewStage_body").innerHTML=data;
           
          }
	  });
	  $("#viewStage").show();
      $("#viewStage").window('open'); 
  }
</script>

</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">实验室建设</a></li>
		<li class="end"><a href="javascript:void(0)">项目经费列表</a></li>
	  </ul>
	</div>
  </div>
  
  <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" id="s0"><a href="${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=1&status=0">全部</a></li>
		<li class="TabbedPanelsTab" id="s1"><a href="${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=1&status=1">草稿</a></li>
		<li class="TabbedPanelsTab" id="s2"><a href="${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=1&status=2">审核中</a></li>
		<li class="TabbedPanelsTab" id="s3"><a href="${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=1&status=3">审核通过</a></li>
		<li class="TabbedPanelsTab" id="s4"><a href="${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=1&status=4">审核拒绝</a></li>
	</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  
  <div class="content-box">
    <div class="title">
	  <div id="title">项目经费</div>
	  	
	  </div>
	<!-- 查询开始  -->
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=1&status=${status}" method="post" modelAttribute="labConstructionFunding">
			 <ul>
			 	
  				<li>项目名称： </li>
  				<li><form:input id="project_name" path="labConstructionProject.projectName"/></li>
  				
  				<li>经费编号： </li>
  				<li><form:input id="funding_number" path="fundingNumber"/></li>
  				
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="cancel();"/></li>
  				</ul>
			 
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>项目编号</th>
	    <th>项目名称</th>
	    <th>经费编号</th>
	    <th>审核状态</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listLabConstructionFundings}" var="curr">
	  <c:if test="${isNonCollegeAuditor||(isCollegeAuditor&&curr.labConstructionProject.schoolAcademy.academyNumber==user.schoolAcademy.academyNumber)||fn:contains(curr.labConstructionProject.MLabConstructionProjectUsers,username) || curr.labConstructionProject.user.username==user.username|| user.username==curr.labConstructionProject.userByCreatedBy.username}">
	   <!-- 上面这句话限制了页面上显示的是当前登陆人需要审核的、当前登陆人所管辖范围内的、与当前当路人相关（当前登录人是项目负责人，填写者，项目成员）的项目 -->
	  <tr>
	    <td>${curr.labConstructionProject.projectNumber}</td>
	    <td>${curr.labConstructionProject.projectName}</td>
	    <td>${curr.fundingNumber}</td>
	    <td>
	    <c:if test="${curr.auditResults==1}">草稿</c:if>
	    <c:if test="${curr.auditResults==2}">
	    	<a  onclick="viewStage(${curr.id});">审核中</a>
			<c:if test="${curr.stage==0 && (fn:contains(excenterDirectors,username)||curr.labConstructionProject.user.username==user.username|| fn:contains(superAdmins,username))}">
	    		<a href="${pageContext.request.contextPath}/labconstruction/excenterFundingAudit?labConstructionFundingId=${curr.id}">实验室中心主任审核</a>
	    	</c:if>
	    	<c:if test="${curr.stage==1 && (fn:contains(assetManagers,username)||curr.labConstructionProject.user.username==user.username|| fn:contains(superAdmins,username))}">
	    		<a href="${pageContext.request.contextPath}/labconstruction/assetFundingAudit?labConstructionFundingId=${curr.id }">资产管理处审核</a>
	    	</c:if>
	    	<c:if test="${curr.stage==2 && (fn:contains(schoolLeaders,username)||curr.labConstructionProject.user.username==user.username|| fn:contains(superAdmins,username))}">
	    		<a href="${pageContext.request.contextPath}/labconstruction/schoolFundingAudit?labConstructionFundingId=${curr.id }">校领导审核</a>
	    	</c:if>
	    	<c:if test="${curr.stage==3 && (fn:contains(cfos,username)||curr.labConstructionProject.user.username==user.username|| fn:contains(superAdmins,username))}">
	    		<a href="${pageContext.request.contextPath}/labconstruction/cfoFundingAudit?labConstructionFundingId=${curr.id }">财务总监审核</a>
	    	</c:if>	    
	    </c:if>
	    <c:if test="${curr.auditResults==3}">审核通过
	    	<c:if test="${fn:contains(excenterDirectors,username)|| (fn:contains(assetManagers,username)||fn:contains(schoolLeaders,username)||fn:contains(cfos,username)||curr.labConstructionProject.user.username==user.username|| fn:contains(superAdmins,username))}">
	    		<a href="${pageContext.request.contextPath}/labconstruction/excenterFundingAudit?labConstructionFundingId=${curr.id }">查看审核结果</a>
	    	</c:if>
	    </c:if>
	    <c:if test="${curr.auditResults==4}">审核拒绝
	    	<c:if test="${fn:contains(excenterDirectors,username)|| (fn:contains(assetManagers,username)||fn:contains(schoolLeaders,username)||fn:contains(cfos,username)||curr.labConstructionProject.user.username==user.username|| fn:contains(superAdmins,username))}">
	    		<a href="${pageContext.request.contextPath}/labconstruction/excenterFundingAudit?labConstructionFundingId=${curr.id }">查看审核结果</a>
	    	</c:if>
	    </c:if>
	    </td>
	    
	    
	    <!-- 操作开始  -->
	    <td>
	    <c:if test="${(curr.auditResults==1 || curr.stage==-1) && 
	    (user.username==curr.labConstructionProject.user.username || user.username==curr.labConstructionProject.userByCreatedBy.username|| fn:contains(superAdmins,username)) }">  
	    <!-- 审核拒绝后 stage 会被置为-1 ，这句话从项目经费状态和当前登录人两个方面规定了提交，编辑，删除功能的使用权 -->
	      <a href="${pageContext.request.contextPath}/labconstruction/submitLabConstructionFunding?labConstructionFundingId=${curr.id}" onclick="return confirm('此过程不可恢复，确认要提交吗？')">提交</a>
	      <a href="${pageContext.request.contextPath}/labconstruction/editLabConstructionFunding?labConstructionFundingId=${curr.id }&status=${status}">编辑</a>
	      <a href="${pageContext.request.contextPath}/labconstruction/deleteLabConstructionFunding?labConstructionFundingId=${curr.id }&status=${status}" onclick="return confirm('确认要删除吗？')">删除</a>
	    </c:if>
	      <a href="${pageContext.request.contextPath}/labconstruction/veiwLabConstructionFunding?labConstructionFundingId=${curr.id }&status=${status}">查看</a>
	    <c:if test="${curr.auditResults==3 && curr.stage!=5 && (curr.labConstructionProject.user.username==user.username|| user.username==curr.labConstructionProject.userByCreatedBy.username)}">
	    	<a href="${pageContext.request.contextPath}/labconstruction/newLabConstructionAcceptance?labConstructionFundingId=${curr.id }&status=0">项目验收</a>  <!-- 点击跳转到项目验收的新建页面 -->
	    </c:if>  
	    </td>
	  </tr>
	  	  <div id="viewStage" class="easyui-window " title="查看审核进度" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 600px; height: 500px;">
			<div class="content-box" id="viewStage_body">
			
			</div>
		</div>
	  
	  </c:if>
	  </c:forEach>
	  
	  </tbody>
	</table>
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=1&status=${status}')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=${pageModel.previousPage}&status=${status}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/labconstruction/listLabConstructionProject?currpage=${pageModel.currpage}&status=${status}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/labconstruction/listLabConstructionProject?currpage=${j.index}&status=${status}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=${pageModel.nextPage}&status=${status}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionFundings?currpage=${pageModel.lastPage}&status=${status}')" target="_self">末页</a>
    </div>
    
  </div>
  </div>
  </div>
  </div>
  


  
  <!-- 下拉框的js -->
	<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		    var config = {
		      '.chzn-select': {search_contains : true},
		      '.chzn-select-deselect'  : {allow_single_deselect:true},
		      '.chzn-select-no-single' : {disable_search_threshold:10},
		      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
		      '.chzn-select-width'     : {width:"95%"}
		    }
		    for (var selector in config) {
		      $(selector).chosen(config[selector]);
		    }
	</script>
	<!-- 下拉框的js -->
</body>
</html>
