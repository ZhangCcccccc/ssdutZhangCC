<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
   <!-- 下拉框的样式 -->
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 --> 
  
  <!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  
  
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
  <script>
  function trimNumber(str){ 
		return str.replace(/\d+/g,''); 
	} 

  </script>
</head>

<body>
<div class="main_container cf rel w95p ma">
 
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div>
	<fieldset class="introduce-box">
	<legend>药品申领列表</legend>
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
			</tr>
			<tr>
			<th>开始时间：</th><td><fmt:formatDate value="${assetReceive.startData.time}" pattern="yyyy-MM-dd hh:mm" /></td>
			<th>结束时间：</th><td><fmt:formatDate value="${assetReceive.endDate.time}" pattern="yyyy-MM-dd hh:mm" /></td>
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
				  	<div class="title">
				         <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/listAssetReceives?currpage=1&status=9">返回</a>
				  	</div>
					  	<table> 
							<thead>
							<tr>
							<c:if test="${assetReceive.status eq 1 || assetReceive.status eq 0}">
									<th>审核结果</th>
								</c:if>
								<th>药品名称</th>
								<th>药品单位</th>
								<th>申领数量</th>
							</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${assetReceiveRecords }" var="curr" varStatus="i">
									<tr>
									<c:if test="${assetReceive.status eq 1 || assetReceive.status eq 0}">
											<c:if test="${curr.result eq 1 }">
												<td>√</td>
											</c:if>
											<c:if test="${curr.result eq 0 }">
												<td><font color="red">×</font></td>
											</c:if>
										</c:if>
										<td>${curr.asset.chName}</td>  
										<td>${unit[i.count-1]}</td> 
										<td>${curr.quantity}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				  </div>
				  <br>
					<c:if test="${assetReceive.status eq 1}"> <div class="right-content">
	  <div id="TabbedPanels1" class="TabbedPanels">
		  <div class="TabbedPanelsContentGroup">
			  <div class="TabbedPanelsContent">
				  <div class="content-box">
					<table> 
							<thead>
							<tr>
								<th>药品名称</th>
								<th>规格</th> 
								<th>申领数量</th>
								<th>存放位置</th>
								<th>药品柜名称</th>
								<th>药品柜编号</th>
								<th>分配数量</th>
							</tr>
							</thead> 
							<tbody>
								<c:forEach items="${receiveAllocationsAndUnits}" var="curr" varStatus="i">
									<tr>
										<td>${curr.key.asset.chName}</td>
										<td>${curr.key.asset.specifications}</td>  
										<td>${curr.key.assetReceiveRecord.quantity}${curr.value}</td>  
										<td>
											<c:if test="${curr.key.assetCabinetWarehouse eq null}">${curr.key.position}</c:if>
											<c:if test="${curr.key.assetCabinetWarehouse ne null}">${curr.key.assetCabinetWarehouse.assetCabinet.labRoom.labRoomName}</c:if>
										</td>
										<td>
											<c:if test="${curr.key.assetCabinetWarehouse eq null}">-</c:if>
											<c:if test="${curr.key.assetCabinetWarehouse ne null}">${curr.key.assetCabinetWarehouse.assetCabinet.cabinetName}</c:if>
										</td>
										<td>
											<c:if test="${curr.key.assetCabinetWarehouse eq null}">-</c:if>
											<c:if test="${curr.key.assetCabinetWarehouse ne null}">${curr.key.assetCabinetWarehouse.warehouseName}</c:if>
										</td>
										<td>${curr.key.quantity}${curr.value}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table></div></div></div></div>
					</c:if>
					<c:if test="${assetReceive.status ne 2 && assetReceive.status ne 3}">
				  <fieldset class="introduce-box">
							<legend>审核人信息</legend>
								<table id="listTable" width="50%" cellpadding="0">
									<tr>
										<th>姓名：</th><td>${assetReceiveAudit.user.cname}</td>
										<th>工号：</th><td>${assetReceiveAudit.user.username}</td>
										<th>部门：</th><td>${assetReceiveAudit.user.username}</td>
										<th>权限：</th><td>${assetReceiveAudit.user.username}</td>
									</tr>
							</table>
					</fieldset>
					<fieldset class="introduce-box">
							<legend>审核结果</legend>
								<table id="listTable" width="50%" cellpadding="0">
									<tr>
										<th>审核结果：</th>
										<td>
											<c:if test="${assetReceiveAudit.status eq 1}">审核通过</c:if>
											<c:if test="${assetReceiveAudit.status eq 2}">审核拒绝</c:if>
										</td>
										<th>审核意见：</th><td>${assetReceiveAudit.result}</td> 
										<th>审核时间：</th><td><fmt:formatDate value="${assetReceiveAudit.createDate.time}" pattern="yyyy-MM-dd" /></td>
									</tr>
							</table>
					</fieldset>
					</c:if>
			  </div>
		  </div>
	  </div>
  </div>
	 	 
		<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }"/>
		<input type="hidden" id="itemId" value="${operationItem.id }"/>
   <script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
   <script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
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
