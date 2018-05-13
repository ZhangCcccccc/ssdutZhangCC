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
	<legend>申领单基本信息</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<th>申请编号：</th><td>${assetReceive.receiveNo}</td>
				<th>申请时间：</th><td><fmt:formatDate value="${assetReceive.receiveDate.time}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>申领人：</th><td>${assetReceive.user.cname}</td>
				 <th>实验大纲：</th><td>
	    	<c:if test = "${assetReceive.type == 0}"><span class="title_focus">公用</span></c:if>
	    	<c:if test = "${assetReceive.type != 0}"><span class="title_focus">${assetReceive.operationItem.operationOutline.labOutlineName}</span></c:if>
	    </td>
				<th>实验项目：</th><td>
	    	<c:if test = "${assetReceive.type == 0}"><span class="title_focus">--</span></c:if>
	    	<c:if test = "${assetReceive.type != 0}"><span class="title_focus">${assetReceive.operationItem.lpName}</span></c:if>
	    </td>
				<th>实验内容：</th><td>${assetReceive.projectContent}</td>
			</tr>
			<tr>
			<th>开始时间：</th><td><fmt:formatDate value="${assetReceive.startData.time}" pattern="yyyy-MM-dd" /></td>
			<th>结束时间：</th><td><fmt:formatDate value="${assetReceive.endDate.time}" pattern="yyyy-MM-dd" /></td>
			</tr>
		</table>
	</fieldset>
  </div>
  </div>
  </div>
  </div>
  
	<form:form action="${pageContext.request.contextPath}/asset/saveAuditAssetReceive?id=${id}" method="POST" modelAttribute="assetReceive">
  <div class="right-content">
	  <div id="TabbedPanels1" class="TabbedPanels">
		  <div class="TabbedPanelsContentGroup">
			  <div class="TabbedPanelsContent">
				  <div class="content-box">
				  	<div class="title">
				         <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/listAssetReceiveAudits?currpage=1&status=3">返回审核列表</a>
				  	</div>
					  	<table> 
							<thead>
							<tr>
							    <th>选择</th>
								<th>药品名称</th>
								<th>药品单位</th>
								<th>申领数量</th>
							</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${listAssetReceiveRecords }" var="curr" varStatus="i">
									<tr>
									   <td><input type="checkbox" id="flag1" name="flag1" value="${curr.id }" checked="checked"></td>
										<td>${curr.asset.chName}</td>
										<c:if test="${curr.asset.specifications ne null}">
										<td>${unit[i.count-1]}</td>
										</c:if> 
										<td>${curr.quantity}</td>
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
	    <label>是否通过该药品申领：</label>
	    <form:radiobutton path="status" style="float:left;" value="1" id="status" name="status"/><label style="width:50px;padding-left:5px;">是</label>
	    <form:radiobutton path="status" style="float:left;" value="0" id="status" name="status"/><label style="width:50px;padding-left:5px;">否</label>
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
	 var expendableId;
	 function moveId(id){
		 expendableId = id;
	 }
		$(".changeAmount").click(function(){
			$(this).hide();//修改按钮隐藏
			$(this).parent().find(".edit-edit").slideDown();//修改信息显示
		});
		$(".edit-edit").blur(function(){
			$(this).hide();//修改按钮隐藏
			$(this).parent().find(".changeAmount").slideDown();//修改信息显示
			var amount = $(this).val();
			if(amount==''){
				amount=0;
			}
			$.ajax({
		        url:"${pageContext.request.contextPath}/operation/saveItemExpendableAmount?expendableId="+expendableId+"&amount="+amount,
		        type:"POST",
		        success:function(data){//AJAX查询成功
		        		if(data=="success"){
		        			window.history.go(0);
		        		}   
		        }
			});
		});
	</script>
</body>
</html>
