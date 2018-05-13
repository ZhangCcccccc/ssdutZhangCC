<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
    <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/common/chosen/docsupport/prism.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/common/chosen/chosen.css" />
  <!-- 下拉的样式结束 -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/operation/itemDeviceAndConsumable.js"></script>
  
  <style>
  	select{
  		width:162px;
  		margin-left:10px;
  	}
  	.chzn-container{
  	width:162px !important;
  	margin-left:10px
  	}
  	.edit-content-box {
    border: 1px solid #9BA0AF;
    border-radius: 5px;
    overflow: visible;
    margin-top: 15px;
}
  </style>
</head>

<body>
<div class="main_container cf rel w95p ma">
 
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div>
	<fieldset class="introduce-box">
	<legend>申购单基本信息</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<th>申购编号：</th><td>${assetApp.appNo}</td>
				<th>申购人：</th><td>${assetApp.user.cname}</td>
			</tr>
			<tr>
				<th>物资种类：</th><td>${Num}</td>
				<th>申购总价：</th><td>${totalPrice}</td>
			</tr>
			<tr>
				<th>实验大纲：</th><td>
	    	<c:if test = "${assetApp.type == 0}"><span class="title_focus">公用</span></c:if>
	    	<c:if test = "${assetApp.type != 0}"><span class="title_focus">${assetApp.operationItem.operationOutline.labOutlineName}</span></c:if>
	    </td>
				<th>实验项目：</th><td>
	    	<c:if test = "${assetApp.type == 0}"><span class="title_focus">--</span></c:if>
	    	<c:if test = "${assetApp.type != 0}"><span class="title_focus">${assetApp.operationItem.lpName}</span></c:if>
	    </td>
			</tr>
		</table>
	</fieldset>
  </div>
  </div>
  </div>
  </div>
  
	<form:form action="${pageContext.request.contextPath}/asset/saveAuditAssetApp?id=${id}" method="POST" modelAttribute="assetApp">
  <div class="right-content">
	  <div id="TabbedPanels1" class="TabbedPanels">
		  <div class="TabbedPanelsContentGroup">
			  <div class="TabbedPanelsContent">
				  <div class="content-box">
				  	<div class="title">新增
				         <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/auditAssetApps?currpage=1&assetAuditStatus=3">返回审核列表</a>
				  	</div>
					  	<table> 
							<thead>
							<tr>
							    <th>选择</th>
							    <th>药品名称</th>
								<th>药品规格</th>
								<th>货号</th>
								<th>CAS号</th>
								<th>经销商</th>
								<th>数量</th>
								<th>单价</th>
								<th>总价</th>
								<th>是否可在实验材料系统中直接购买</th>
								<th>是否需要领取移液枪</th> 
								<th>请写明数量及规格</th>
								<th>备注</th>
							</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${listAssetAppRecords }" var="curr" varStatus="i">
									<tr>
									<td>
									<input type="checkbox" id="flag1" name="flag1" value="${curr.id }" checked="checked"></td>
										<td>${curr.asset.chName}</td>
										<td>${curr.asset.specifications}</td>
										<td>${curr.styleNumber}</td>
										<td>${curr.asset.cas}</td>
										<td>${curr.appSupplier}</td>
										<td>${curr.appQuantity}</td>
										<td>${curr.appPrice}</td>
										<td>${curr.appPrice*curr.appQuantity}</td>
										<td>${curr.appUsage}</td>
										<td>${curr.appSpec}</td>
										<td>
											<c:if test="${ curr.appSpec eq '是'}">${curr.appfinalSpec } ${curr.selectNumber }</c:if>
											<c:if test="${ curr.appSpec eq '否'}">--</c:if>
										</td>
										<td>${curr.mem}</td>
										 </tr>
									
								</c:forEach>
							</tbody>
						</table>
				  </div>
			  </div>
		  </div>
	  </div>
  </div>
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title">审核</div>
	</div>
	<div class="new-classroom"> 
	  <fieldset>
	    <label>是否通过该药品预算：</label>
	    <form:radiobutton path="assetAuditStatus" style="float:left;" value="1" id="assetAuditStatus" name="assetAuditStatus"/><label style="width:50px;padding-left:5px;">是</label>
	    <form:radiobutton path="assetAuditStatus" style="float:left;" value="2" id="assetAuditStatus" name="assetAuditStatus"/><label style="width:50px;padding-left:5px;">否</label>
	  </fieldset>
	<fieldset>
	
	  <form:hidden path="id"/>
	    <label>审核意见：</label>
	    <form:input path="mem" name="mem" id="mem"/>
	  </fieldset>
	     
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="确定">
		  <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
        </div>
	</div>
	</form:form>
		
		
		
		
		
			
		<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }"/>
		<input type="hidden" id="itemId" value="${operationItem.id }"/>
    <!-- 下拉框的js -->
	<script src="${pageContext.request.contextPath}/js/common/chosen/chosen.jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/common/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	// 弹窗
	function addRecords(){
		$("#addRecords").show();
	    $("#addRecords").window('open');  
	}
	
	
	
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
	 <script type="text/javascript">
	</script>
</body>
</html>
