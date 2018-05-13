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
<style>
    .content-box td{
        padding:0;
    }
    .list_form td{
        height:50px;
        line-height:50px;
        padding:0 0 10px 0;
    }
</style>
  
  
<script type="text/javascript">
//跳转
function targetUrl(url)
{
  document.queryForm.action=url;
  document.queryForm.submit();
}

//取消查询
function cancel(){
	window.location.href="${pageContext.request.contextPath}/log/listOperationLog?currpage=1";
}
//全选
function checkAll()
{
  if($("#check_all").attr("checked"))
  {
    $(":checkbox").attr("checked", true);
  }
  else
  {
    $(":checkbox").attr("checked", false);
  }
}
function submitForm()
{
  var flag = false;  //是否有checkbox被选中
  var ids = "";
  $("input[name='items']:checked").each(function(){
      ids += $(this).val()+",";
		flag = true;
	});
	
	if(flag)
	{
	  if(ids.length > 0)
	  {
	  	ids = ids.substring(0, ids.length-1);  //去掉最后的逗号
	  }
	  location.href="${pageContext.request.contextPath}/log/deleteOperationLog?logIds="+ids;
	}
	else
	{
	  alert("您还没有勾选呦");
	}
}
</script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
		<li class="end"><a href="javascript:void(0)">系统日志列表</a></li>
	  </ul>
	</div>
  </div>
  
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">系统日志</div>
	  <%--<sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
	  <a class="btn btn-new" href="javascript:submitForm();" onclick="return confirm('系统日志清除后无法恢复，确定删除？');">清除选中日志</a>
	  </sec:authorize>
	--%></div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/log/listOperationLog?currpage=1" method="post" modelAttribute="systemLog">
			<table class="list_form" style="margin:0;width:90%;">
				<tbody>
					<tr>
						<td style="padding:0 10px 0 0;">
							<form:select path="superModule" style="width:120px" class="chzn-select">
								<form:option value="">操作模块</form:option>
								<form:option value="实验中心">实验中心</form:option>
								<form:option value="课程实验">课程实验</form:option>
								<form:option value="预约排课">预约排课</form:option>
								<form:option value="实验室建设">实验室建设</form:option>
								<form:option value="实验室及预约管理">实验室及预约管理</form:option>
								<form:option value="实验设备管理">实验设备管理</form:option>
								<form:option value="系统管理">系统管理</form:option>
							</form:select>
						</td>
						<td style="padding:0 10px 0 0;">
							<form:select path="userDetail" style="width:120px" class="chzn-select">
								<form:option value="">操作用户</form:option>
								<c:forEach items="${userDetailMap}" var="ud">
							  		<form:option value="${ud.key}">${sc.value }</form:option>
							  	</c:forEach>

							</form:select>
						</td>
						<td style="padding:0;">操作类型:</td>
						<td style="padding:0 10px 0 0;">
							<!-- 动作: 0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果 -->
							<form:select path="operationAction" style="width:120px" class="chzn-select" multiple="true">
								<form:option value="0">1、新建</form:option>
								<form:option value="1">2、编辑</form:option>
								<form:option value="2">3、查看</form:option>
								<form:option value="3">4、删除</form:option>
								<form:option value="4">5、提交</form:option>
								<form:option value="5">6、审核查看</form:option>
								<form:option value="6">7、保存</form:option>
								<form:option value="7">8、审核编辑后保存</form:option>
								<form:option value="8">9、导入</form:option>
								<form:option value="9">10、审核结果</form:option>
							</form:select>
						</td>
						<td style="padding:0;position:relative;left:20px;">操作时间:</td>
						<td style="padding:0 10px 0 0;position:relative;top:-7px;"><input class="easyui-datebox"  id="starttime" name="starttime"   type="text"  onclick="new Calendar().show(this);" style="width:100px;" />
						<span style="position: relative;top: 16px;">到</span>
						<input  class="easyui-datebox"  id="endtime" name="endtime"  type="text"  onclick="new Calendar().show(this);" style="width:100px;" />
						</td>
						<td style="padding:0 10px 0 0;">
					    	<input type="submit" value="查询"/>
						</td>
						<td style="padding:0 10px 0 0;">
							<input type="button" value="取消" onclick="cancel();"/>
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
		<thead>
		 <tr>
			<%--<th><input id="check_all" type="checkbox" onclick="checkAll();"/></th>
		   --%><th>序号</th>
		   <th>操作用户</th>
		   <th>操作对象</th>
		   <th>具体动作</th>
		   <th>操作时间</th>
		   <th>登录地址</th>
		 </tr>
		</thead>
		
		<tbody>
		 <c:forEach items="${operationLogs}" var="curr" varStatus="i">
		  <tr>
		  	<%--<td><input id="check_${curr.id}" name="items" type="checkbox" value="${curr.id}"/></td>
		  	--%><td>${i.count+(currpage-1)*pageSize }</td>
		  	<td>${curr.userDetail }</td>
		  	<td>${curr.objectiveDetail }</td>
		  	<td>
		  		<!-- 动作: 0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果 -->
		  		<c:choose>
		  			<c:when test="${curr.operationAction eq 0 }">新建</c:when>
		  			<c:when test="${curr.operationAction eq 1 }">编辑</c:when>
		  			<c:when test="${curr.operationAction eq 2 }">查看</c:when>
		  			<c:when test="${curr.operationAction eq 3 }">删除</c:when>
		  			<c:when test="${curr.operationAction eq 4 }">提交</c:when>
		  			<c:when test="${curr.operationAction eq 5 }">审核查看</c:when>
		  			<c:when test="${curr.operationAction eq 6 }">保存</c:when>
		  			<c:when test="${curr.operationAction eq 7 }">审核编辑后保存</c:when>
		  			<c:when test="${curr.operationAction eq 8 }">导入</c:when>
		  			<c:when test="${curr.operationAction eq 9 }">审核结果</c:when>
		  		</c:choose>
		  	</td>
		  	<td><fmt:formatDate value="${curr.calendarTime.time}" pattern="yyyy年MM月dd日   HH:mm:ss"/></td>
		  	<td>${curr.userIp }</td>
		  </tr>
		 </c:forEach>
		</tbody>
	</table>
	
	
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页,当前第${pageModel.currpage }页.
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/log/listOperationLog?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/log/listOperationLog?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/log/listOperationLog?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/log/listOperationLog?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/log/listOperationLog?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/log/listOperationLog?currpage=${pageModel.lastPage}')" target="_self">末页</a>
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
