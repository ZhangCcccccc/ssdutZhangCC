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
    window.location.href="${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=1&status=${status}";
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
		  url:"${pageContext.request.contextPath}/labconstruction/viewProjectStage?labConstructionProjectId="+id,
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
		<li class="end"><a href="javascript:void(0)">项目立项列表</a></li>
	  </ul>
	</div>
  </div>
  
  <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" id="s0"><a href="${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=1&status=0">全部</a></li>
		<li class="TabbedPanelsTab" id="s1"><a href="${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=1&status=1">草稿</a></li>
		<li class="TabbedPanelsTab" id="s2"><a href="${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=1&status=2">审核中</a></li>
		<li class="TabbedPanelsTab" id="s3"><a href="${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=1&status=3">审核通过</a></li>
		<li class="TabbedPanelsTab" id="s4"><a href="${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=1&status=4">审核拒绝</a></li>
	</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  
  <div class="content-box">
    <div class="title">
	  <div id="title">项目立项</div>
	  	<a class="btn btn-new" href="${pageContext.request.contextPath}/labconstruction/newLabConstructionProject?status=${status}">新建</a>
	  </div>
	<!-- 查询开始  -->
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=1&status=${status}" method="post" modelAttribute="labConstructionProject">
			 <ul>
			 	<li>项目编号： </li>
  				<li><form:input id="project_number" path="projectNumber"/></li>
  				<li>项目名称： </li>
  				<li><form:input id="project_name" path="projectName"/></li>
  				<li>项目负责人：</li>
  				<li>
	  				<form:select path="user.username"  id="project_manager" class="chzn-select">
	  				    <form:option value="">请选择</form:option>
	  				    <form:options items="${users}"/>
	  				</form:select>
  				</li>
  				
  				
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
	    <th>所属学院</th>
	    <th>项目负责人</th>
	    <th>联系电话</th>
	    <th>E-mail</th>
	    <th>预算总额</th>
	    <th>审核状态</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listLabConstructionProjects}" var="curr">
	  <c:if test="${isNonCollegeAuditor||(isCollegeAuditor&&curr.schoolAcademy.academyNumber==user.schoolAcademy.academyNumber)||fn:contains(curr.MLabConstructionProjectUsers,username) || curr.user.username==user.username|| user.username==curr.userByCreatedBy.username}">
	  <!-- 上面这句话限制了页面上显示的是当前登陆人需要审核的、当前登陆人所管辖范围内的、与当前当路人相关（当前登录人是项目负责人，填写者，项目成员）的项目 -->
	  <tr>
	    <td>${curr.projectNumber}</td>
	    <td>${curr.projectName}</td>
	    <td>${curr.schoolAcademy.academyName}</td>
	    <td>${curr.user.cname}</td>
	    <td>${curr.telephone}</td>
	    <td>${curr.email}</td>
	    <td>${curr.projectBudget}</td>
	    <td>
	    <c:if test="${curr.auditResults==1}">草稿</c:if>
	    <c:if test="${curr.auditResults==2}">
	    
	    	<a  onclick="viewStage(${curr.id});">审核中</a>
	    	<c:if test="${curr.stage==0 && (fn:contains(collegeLeaders,username)||curr.user.username==user.username|| user.username==curr.userByCreatedBy.username || fn:contains(superAdmins,user.username))}">
	    		<a href="${pageContext.request.contextPath}/labconstruction/collegeProjectAudit?labConstructionProjectId=${curr.id }">学院主管领导审核</a>
	    	</c:if>
	    	<c:if test="${curr.stage==1 && (fn:contains(deanWorkers,username)||curr.user.username==user.username|| user.username==curr.userByCreatedBy.username || fn:contains(superAdmins,user.username))}">
	    		<a href="${pageContext.request.contextPath}/labconstruction/deanProjectAudit?labConstructionProjectId=${curr.id }">教务处审核</a>
	    	</c:if>
	    	<c:if test="${curr.stage==2 && (fn:contains(schoolLeaders,username)||curr.user.username==user.username|| user.username==curr.userByCreatedBy.username || fn:contains(superAdmins,user.username))}">
	    		<a href="${pageContext.request.contextPath}/labconstruction/schoolProjectAudit?labConstructionProjectId=${curr.id }">校领导审核</a>
	    	</c:if>
	    </c:if><!-- 加超链接 点击审核 -->
	    <c:if test="${curr.auditResults==3}">审核通过
		    <c:if test="${fn:contains(collegeLeaders,username)||fn:contains(deanWorkers,username)||fn:contains(schoolLeaders,username)||curr.user.username==user.username|| user.username==curr.userByCreatedBy.username || fn:contains(superAdmins,username)}">
		    	<a href="${pageContext.request.contextPath}/labconstruction/collegeProjectAudit?labConstructionProjectId=${curr.id }">查看审核结果</a>
		    </c:if>
	    </c:if>
	    <c:if test="${curr.auditResults==4}">审核拒绝
	    	<c:if test="${fn:contains(collegeLeaders,username)||fn:contains(deanWorkers,username)||fn:contains(schoolLeaders,username)||curr.user.username==user.username|| user.username==curr.userByCreatedBy.username || fn:contains(superAdmins,username)}">
		    	<a href="${pageContext.request.contextPath}/labconstruction/collegeProjectAudit?labConstructionProjectId=${curr.id }">查看审核结果</a>
		    </c:if>
	    </c:if>
	    </td>
	    
	    
	    <!-- 操作开始  -->
	    <td>
	    <c:if test="${(curr.auditResults==1 || curr.stage==-1) && 
	    (user.username==curr.userByCreatedBy.username || user.username==curr.user.username || fn:contains(superAdmins,username)) }">  
	    <!-- 审核拒绝后 stage 会被置为-1 ，这句话从项目立项状态和当前登录人两个方面规定了提交，编辑，删除功能的使用权 -->
	      <a href="${pageContext.request.contextPath}/labconstruction/submitLabConstructionProject?labConstructionProjectId=${curr.id }" onclick="return confirm('此过程不可恢复，确认要提交吗？')">提交</a>
	      <a href="${pageContext.request.contextPath}/labconstruction/editLabConstructionProject?labConstructionProjectId=${curr.id }&status=${status}">编辑</a>
	      <a href="${pageContext.request.contextPath}/labconstruction/deleteLabConstructionProject?labConstructionProjectId=${curr.id }&status=${status}" onclick="return confirm('确认要删除吗？')">删除</a>
	    </c:if>
	      <a href="${pageContext.request.contextPath}/labconstruction/veiwLabConstructionProject?labConstructionProjectId=${curr.id }&status=${status}">查看</a>
	    <c:if test="${curr.auditResults==3 && curr.stage==3 && (user.username==curr.userByCreatedBy.username || user.username==curr.user.username)}">  
	    <!-- 新建一次项目建设以后，stage会被设置为5，从而确保不会有一个项目立项生成了两个项目建设这种情况出现 -->
	    	<a href="${pageContext.request.contextPath}/labconstruction/newLabConstructionPurchase?labConstructionProjectId=${curr.id }&status=0">项目建设</a>  <!-- 点击跳转到项目建设的新建页面 -->
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
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=1&status=${status}')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=${pageModel.previousPage}&status=${status}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/labconstruction/listLabConstructionProject?currpage=${pageModel.currpage}&status=${status}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/labconstruction/listLabConstructionProject?currpage=${j.index}&status=${status}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=${pageModel.nextPage}&status=${status}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labconstruction/listLabConstructionProjects?currpage=${pageModel.lastPage}&status=${status}')" target="_self">末页</a>
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
