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
	<legend>申购单基本信息</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<th>申请编号：</th><td>${assetApp.appNo}</td>
				<th>申购人：</th><td>${assetApp.user.cname}</td>
				<th>物资种类：</th><td>${num}</td>
				<th>申购总价：</th><td>${totalPrice}</td>
				<th>实验大纲：</th><td>
	    	<c:if test = "${assetApp.type == 0}"><span class="title_focus">公用</span></c:if>
	    	<c:if test = "${assetApp.type != 0}"><span class="title_focus">${assetApp.operationItem.operationOutline.labOutlineName}</span></c:if>
	    </td>
				<th><span>实验项目：</span></th><td>
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
  
  <div class="right-content">
	  <div id="TabbedPanels1" class="TabbedPanels">
		  <div class="TabbedPanelsContentGroup">
			  <div class="TabbedPanelsContent">
				  <div class="content-box">
				  	<div class="title">
				         <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/listAssetApps?currpage=1&assetAuditStatus=9">返回列表</a>
				  	</div>
					  	<table> 
							<thead>
							<tr>
								<c:if test="${assetApp.assetAuditStatus eq 1 || assetApp.assetAuditStatus eq 2}">
									<th>审核结果</th>
								</c:if>
								<th>药品名称</th>
								<th>药品规格</th>
								<th>cas号</th>
								<th>货号</th>
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
								<c:forEach items="${assetAppRecords }" var="curr" varStatus="i">
									<tr>
										<c:if test="${assetApp.assetAuditStatus eq 1 || assetApp.assetAuditStatus eq 2}">
											<c:if test="${curr.result eq 1 }">
												<td>√</td>
											</c:if>
											<c:if test="${curr.result eq 0 }">
												<td><font color="red">×</font></td>
											</c:if>
										</c:if>
										<td>${curr.asset.chName}</td>
										<td>${curr.asset.specifications}</td>
										<td>${curr.asset.cas}</td>
										<td>${curr.styleNumber}</td>
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
				  <br>
				  <c:if test="${assetApp.assetAuditStatus ne 4 && assetApp.assetAuditStatus ne 3}">
				  <fieldset class="introduce-box">
							<legend>审核人信息</legend>
								<table id="listTable" width="50%" cellpadding="0">
									<tr>
										<th>姓名：</th><td>${assetAppAudit.user.cname}</td>
										<th>工号：</th><td>${assetAppAudit.user.username}</td> 
									</tr>
							</table>
					</fieldset>
					<fieldset class="introduce-box">
							<legend>审核结果</legend>
								<table id="listTable" width="50%" cellpadding="0">
									<tr>
										<th>审核结果：</th>
										<td>
											<c:if test="${assetAppAudit.status eq 1}">审核通过</c:if>
											<c:if test="${assetAppAudit.status eq 2}">审核拒绝</c:if>
										</td>
										<th>审核意见：</th><td>${assetAppAudit.result}</td>
										<th>审核时间：</th><td><fmt:formatDate value="${assetAppAudit.createDate.time}" pattern="yyyy-MM-dd" /></td>
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
