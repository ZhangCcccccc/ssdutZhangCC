<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
    <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <script src="${pageContext.request.contextPath}/js/Calendar.js" type="text/javascript"></script>
  <!-- 下拉的样式结束 -->
  <script type="text/javascript">
//取消查询
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1&assetAuditStatus=3";
  }
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  $(document).ready(function(){
      var s=${assetAuditStatus};
      if(s==3){
    	  $("#s3").addClass("TabbedPanelsTab selected");
    	  $("#s1").addClass("TabbedPanelsTab");
    	  $("#s2").addClass("TabbedPanelsTab");
    	  $("#s9").addClass("TabbedPanelsTab");
      }
      if(s==1){
    	  $("#s1").addClass("TabbedPanelsTab selected");
    	  $("#s3").addClass("TabbedPanelsTab");
    	  $("#s2").addClass("TabbedPanelsTab");
    	  $("#s9").addClass("TabbedPanelsTab");
      }
      if(s==2){
    	  $("#s2").addClass("TabbedPanelsTab selected");
    	  $("#s1").addClass("TabbedPanelsTab");
    	  $("#s3").addClass("TabbedPanelsTab");
    	  $("#s9").addClass("TabbedPanelsTab");
      }
      if(s==9){
    	  $("#s9").addClass("TabbedPanelsTab selected");
    	  $("#s3").addClass("TabbedPanelsTab");
    	  $("#s1").addClass("TabbedPanelsTab");
    	  $("#s2").addClass("TabbedPanelsTab");
      }
	});
  </script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">药品溶液管理</a></li>
		<li class="end"><a href="javascript:void(0)">申购审核</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
		
		<li class="TabbedPanelsTab" id="s3"><a href="${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1&assetAuditStatus=3">待审核</a></li>
		<li class="TabbedPanelsTab" id="s1"><a href="${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1&assetAuditStatus=1">审核通过</a></li>
		<li class="TabbedPanelsTab" id="s2"><a href="${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1&assetAuditStatus=2">审核拒绝</a></li>
		<li class="TabbedPanelsTab" id="s9"><a href="${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1&assetAuditStatus=9">全部</a></li>
</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">个人申购列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/exportAssetApp?currpage=${currpage}&assetAuditStatus=${assetAuditStatus}&isAudit=1">导出预算表</a>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/setSubmitTime">设置提交时间</a>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1&assetAuditStatus=${assetAuditStatus }" method="post" modelAttribute="assetApp">
			 <ul>
  				<li>申购编号： </li>
  				<li>
  					<form:select id="appNo" path="appNo" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listAssetApps}" var="curr">
  							<form:option value="${curr.appNo}">${curr.appNo}</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>申请人： </li>
  				<li>
  					<form:select id="user" path="user.cname" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${users}" var="curr">
  							<form:option value="${curr.user.cname}">${curr.user.cname}</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>申请日期： </li>
  				<li style="position: relative;top: -17px;"><input class="easyui-datebox"  id="startDate" value="${startDate}" name="startDate" onclick="new Calendar().show(this);"  readonly="readonly"/>
  				</li>
  				<li>至： </li>
  				<li style="position: relative;top: -17px;"><input class="easyui-datebox" id="endDate" value="${endDate}"name="endDate" onclick="new Calendar().show(this);"  readonly="readonly"/></li>
  				<li>
			      <input type="button" value="取消" onclick="cancel()"/><input type="submit" value="查询"/></li>
  				</ul>
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <%--<th>选择</th>
	    --%><th>序号</th>
	    <th>申购编号</th>
	    <th>申请日期</th>
	    <th>物资种类</th>
	     <th>实验大纲</th>
	    <th>实验项目</th>
	    <th>申购总金额</th>
	    <th>申请人</th>
	    <th>审核状态</th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listAssetApps}" var="curr" varStatus="i">
	  <tr><%--
	    <td><input type="checkbox" id="flag1" name="flag1" value="${curr.id }" checked="checked"></td>
	    --%><td>${i.count+pageSize*(currpage-1)}</td>
	    <td>${curr.appNo}</td>
	    <td><fmt:formatDate value="${curr.appDate.time}" pattern="yyyy-MM-dd" /></td>
	    <td>${num[i.count-1]}</td>
	     <td>
	    	<c:if test = "${curr.type == 0}">公用</c:if>
	    	<c:if test = "${curr.type != 0}">${curr.operationItem.operationOutline.labOutlineName}</c:if>
	    </td> 
	    <td>
	    	<c:if test = "${curr.type == 0}">--</c:if>
	    	<c:if test = "${curr.type != 0}">${curr.operationItem.lpName}</c:if>
	    </td>
	    <td>${totalPrice[i.count-1]}</td>
	     <td>${curr.user.cname}</td>
	    <c:if test="${curr.assetAuditStatus eq 4}"><td>已提交</td></c:if>
	    <c:if test="${curr.assetAuditStatus eq 3}"><td>审核中</td></c:if>
	    <c:if test="${curr.assetAuditStatus eq 1}"><td>审核通过</td></c:if>
	    <c:if test="${curr.assetAuditStatus eq 2}"><td><font color="red">审核拒绝</font></td></c:if>
	    <td>
	      <c:if test="${curr.assetAuditStatus eq 3}">
	      <a href="${pageContext.request.contextPath}/asset/auditAssetApp?id=${curr.id}">审核</a>
	      </c:if>
	      <a href="${pageContext.request.contextPath}/asset/getAssetAppInAudit?id=${curr.id}">查看</a> 
	    </td> 
	  </tr>
	  
	  </c:forEach>
	  </tbody>
	</table>
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
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1&assetAuditStatus=${assetAuditStatus }')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1?assetAuditStatus=${assetAuditStatus }&currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1?assetAuditStatus=${assetAuditStatus }&currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1?assetAuditStatus=${assetAuditStatus }&currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1?assetAuditStatus=${assetAuditStatus }&currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1?assetAuditStatus=${assetAuditStatus }&currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
