<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
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
    window.location.href="${pageContext.request.contextPath}/labProject/listLabProject?currpage=1";
  }
  //跳转
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
	    <li><a href="javascript:void(0)">实验项目</a></li>
		<li class="end"><a href="javascript:void(0)">实验项目列表</a></li>
	  </ul>
	</div>
  </div>
  
  <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" id="s0"><a href="${pageContext.request.contextPath}/labProject/listLabProject?currpage=1&status=0">全部</a></li>
		<li class="TabbedPanelsTab" id="s1"><a href="${pageContext.request.contextPath}/labProject/listLabProject?currpage=1&status=1">草稿</a></li>
		<li class="TabbedPanelsTab" id="s2"><a href="${pageContext.request.contextPath}/labProject/listLabProject?currpage=1&status=2">审核中</a></li>
		<li class="TabbedPanelsTab" id="s3"><a href="${pageContext.request.contextPath}/labProject/listLabProject?currpage=1&status=3">审核通过</a></li>
		<li class="TabbedPanelsTab" id="s4"><a href="${pageContext.request.contextPath}/labProject/listLabProject?currpage=1&status=4">审核拒绝</a></li>
	</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  
  <div class="content-box">
    <div class="title">
	  <div id="title">实验项目列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/labProject/newLabProject">新建</a>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/labProject/listLabProject?currpage=1" method="post" modelAttribute="labProject">
			 <ul>
  				<li>实验项目名称： </li>
  				<li><form:input id="lp_name" path="lpName"/></li>
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="cancel();"/></li>
  				</ul>
			 
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>实验编号</th>
	    <th>实验名称</th>
	    <th>学期</th>
	    <th>所属实验中心</th>
	    <th>中心主任</th>
	    <th>所属实验室</th>
	    <th>所属课程</th>
	    <th>创建者</th>
	    <th>指定审核人</th>
	    <th>审核状态</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listLabProject}" var="curr">
	  <tr>
	    <td>${curr.lpCodeCustom}</td>
	    <td>${curr.lpName}</td>
	    <td>${curr.schoolTerm.termName}</td>
	    <td>${curr.labRoom.labCenter.centerName}</td>
	    <td>${curr.labRoom.labCenter.userByCenterManager.cname}[${curr.labRoom.labCenter.userByCenterManager.username}]</td>
	    <td>${curr.labRoom.labRoomName}</td>
	    <td>${curr.schoolCourse.CName}</td>
	    <td></td>
	    <td></td>
	    <td>${curr.CDictionaryByLpStatusCheck.CName}</td>
	    <td>
	      <a href="${pageContext.request.contextPath}/labProject/editLabProject?labProjectId=${curr.id}">编辑</a>
	      <a href="${pageContext.request.contextPath}/labProject/deleteLabProject?labProjectId=${curr.id}" onclick="return confirm('确定删除？');">删除</a>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labProject/listLabProject?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labProject/listLabProject?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/labProject/listLabProject?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/labProject/listLabProject?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labProject/listLabProject?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labProject/listLabProject?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    
  </div>
  </div>
  </div>
  </div>
</body>
</html>
