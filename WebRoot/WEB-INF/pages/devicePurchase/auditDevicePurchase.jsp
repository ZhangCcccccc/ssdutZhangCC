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
	<legend>设备申购信息</legend>
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
				  	<div class="title">新增设备
				  	<c:if test="${isNew eq 1}">
				         <a class="btn btn-new" onclick="addRecords();">添加</a>
				         <a class="btn btn-new" href="${pageContext.request.contextPath}/devicePurchase/submitDevicePurchase?id=${id}">提交</a></c:if>
				         <a class="btn btn-new" href="${pageContext.request.contextPath}/devicePurchase/listNDevicePurchase?currpage=1">返回设备申购列表</a>
				  	</div>
					  	<table> 
							<thead>
							<tr>
								<th>设备名称</th>
								<th>申请数量</th>
								<th>单价</th>
								<th>领用人</th>
								<th>拟存放地</th>
								<th>品牌</th>
								<th>型号</th>
								<th>规格需求</th>
								<th>备注</th>
								<th>分中心主任审核结果</th>
								<th>分中心主任意见</th>
								<th>中心主任审核结果</th>
								<th>中心主任意见</th>
								<th>操作</th>
							</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${nDevicePurchaseDetails }" var="curr" varStatus="i">
									<tr>
										<td>${curr.deviceName}</td>
										<td>${curr.deviceQuantity}</td>
										<td>${curr.devicePrice}</td>
										<td>${curr.user.cname }</td>
										<td>${curr.place}</td>
										<td>${curr.deviceSupplier}</td>
										<td>${curr.deviceModel}</td>
										<td>${curr.deviceFormat}</td>
										<td>${curr.remark}</td>
										<c:if test="${curr.ifPassed eq null}"><td></td></c:if>
										<c:if test="${curr.ifPassed eq 0}"><td>未通过</td></c:if>
										<c:if test="${curr.ifPassed eq 1}"><td>分中心主任通过</td></c:if>
										<td>${curr.auditAdvice}</td>
										<c:if test="${curr.ifPassedCenter eq null}"><td></td></c:if>
										<c:if test="${curr.ifPassedCenter eq 0}"><td>未通过</td></c:if>
										<c:if test="${curr.ifPassedCenter eq 1}"><td>中心主任通过</td></c:if>
										<td>${curr.auditAdviceCenter}</td>
										<td>
										<c:if test="${curr.NDevicePurchase.auditStatus eq 1}">
										<a href="${pageContext.request.contextPath}/devicePurchase/auditDevicePurchaseDetail?id=${curr.id}">分中心主任意见</a>
										</c:if>
										<c:if test="${curr.NDevicePurchase.auditStatus eq 2}">
										<a href="${pageContext.request.contextPath}/devicePurchase/auditDevicePurchaseDetail2?id=${curr.id}">中心主任意见</a>
										</c:if>
										</td>
									</tr>
									
								</c:forEach>
							</tbody>
						</table>
				  </div>
			  </div>
		  </div>
	  </div>
  </div>
	<div id="addRecords" class="easyui-window " title="添加" align="left" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 500px; height: 250px;">
					<form:form action="${pageContext.request.contextPath}/devicePurchase/savedevicePurchaseDetail?id=${id}" modelAttribute="nDevicePurchaseDetail">
						<table>
							<tr>
								<td>设备名称：</td>
								<td><form:input path="deviceName" required="true"/>
								</td>
							</tr>
							<tr>
								<td>申请数量：</td>
								<td><form:input path="deviceQuantity" />
								</td>
							</tr>
							<tr>
								<td>单价：</td>
								<td><form:input path="devicePrice" required="true"/>
								</td>
							</tr>
							<tr>
						    <label>领用人：</label>
						<form:select path="user.username" class="chzn-select">
						<form:option value="">请选择</form:option>
						<form:options items="${users}" itemLabel="cname" itemValue="username"/>
						</form:select>
						    </tr>
							<tr>
								<td>拟存放地：</td>
								<td><form:input path="place" required="true"/>
								</td>
							</tr>
							<tr>
								<td>品牌：</td>
								<td><form:input path="deviceSupplier" required="true"/>
								</td>
							</tr>
							<tr>
								<td>型号：</td>
								<td><form:input path="deviceModel" required="true"/>
								</td>
							</tr>
							<tr>
								<td>规格需求：</td>
								<td><form:input path="deviceFormat" required="true"/>
								</td>
							</tr>
							<tr>
								<td>备注：</td>
								<td><form:input path="remark" required="true"/>
								</td>
							</tr>
							
							<tr>
								<td><input type="submit" value="保存">
								</td>
							</tr>
						</table>
					</form:form>
				</div>	
		
  
  
  <c:if test="${isNew eq 0}">
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title">审核</div>
	</div>
	<form:form action="${pageContext.request.contextPath}/devicePurchase/saveAuditDevicePurchaseR?id=${id}" method="POST" modelAttribute="nDevicePurchase">
	<div class="new-classroom"> 
	  <fieldset>
	    <label>是否通过该设备申请：</label>
	    <form:radiobutton path="auditStatus" value="2" id="auditStatus" name="auditStatus"/><label>是</label>
	    <form:radiobutton path="auditStatus" value="4" id="auditStatus" name="auditStatus" /><label>否</label>
	  </fieldset>
	<fieldset>
	
	  <form:hidden path="id"/>
	    <label>审核意见：</label>
	    <form:input path="auditAdvice" name="auditAdvice" id="auditAdvice"/>
	  </fieldset>
	     
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="确定">
		  <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
        </div>
	</div>
	</form:form>
	</c:if>	
		
		
		
		
		
			
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
