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
  <!-- 日历 -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>  
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
	<legend>设备详情</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<th>申购编号：</th><td>${nDevicePurchase.purchaseNumber}</td>
				<th>申购名称：</th><td>${nDevicePurchase.useDirection}</td>
				<th>申购金额：</th><td>${nDevicePurchase.purchaseCost}</td>
				<th>申购时间：</th><td><fmt:formatDate value="${nDevicePurchase.createDate.time}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>申购人：</th><td>${nDevicePurchase.user.cname}</td>
				<th>设备状态：</th><td>${nDevicePurchase.CDeviceStatus.statusName}</td>
				<th>经费编号：</th><td>${nDevicePurchase.costCode}</td>
				<th>经费类型：</th><td>${nDevicePurchase.CDictionaryByCostType.CName}</td>
			</tr>
			<tr>
				<th>申购原因：</th><td>${nDevicePurchase.CDictionaryByCostReason.CName}</td>
			</tr>
			<tr>
				<th>申购说明：</th><td>${nDevicePurchase.devicePurchaseReason}</td>
			</tr>
			<tr>
				<th>规格需求：</th><td>${nDevicePurchase.purchaseFormat}</td>
			</tr>
		</table>
	</fieldset>
  </div>
  </div>
  </div>
  </div>
  
  <div class="right-content">
	  <div id="TabbedPanels1" class="TabbedPanels">
		  <div class="TabbedPanelsContentGroup">
			  <div class="TabbedPanelsContent">
				  <div class="content-box">
				  	<div class="title">设备状态
				  	</div>
					  	<table> 
							<thead>
							<tr>
								<th>序号</th>
								<th>时间</th>
								<th>设备状态</th> 
							</tr>
							</thead>
							<tbody>
									<c:forEach items="${listDeviceStatusRecords}" var="curr" varStatus="i">
									  <tr>
									    <td>${i.count}</td>
									    <td><fmt:formatDate value="${curr.endDate.time}" pattern="yyyy-MM-dd" /></td>
									    <td>${curr.statusName}</td> 
									  </tr>
	 							 </c:forEach>
							</tbody>
						</table>
				  </div>
			  </div>
		  </div>
	  </div>
  </div>
  			
    <!-- 下拉框的js -->
	<script src="${pageContext.request.contextPath}/js/common/chosen/chosen.jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/common/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	// 弹窗
	function modifyEndDate(){
		$("#modifyEndDate").show();
	    $("#modifyEndDate").window('open');  
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
